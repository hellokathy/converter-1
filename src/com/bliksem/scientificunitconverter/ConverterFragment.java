package com.bliksem.scientificunitconverter;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import javax.measure.quantity.Acceleration;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

public class ConverterFragment extends Fragment implements OnItemSelectedListener
{

	TreeMap<String, String> unitSymbols = new TreeMap<String, String>();
	TreeMap<String, Unit<?>> unitObjects = new TreeMap<String, Unit<?>>();
	TreeMap<String, String> unitNiceNames = new TreeMap<String, String>();
	TreeMap<String, String> unitConversionUnit = new TreeMap<String, String>();
	TreeMap<String, Double> unitTimes = new TreeMap<String, Double>();

	private ArrayList<UnitListViewRow> unitListViewRows;
	private UnitListViewAdapter unitListViewAdapter;
	ArrayList<String> niceNamesList;

	private HashMap<String, String> specialChars = new HashMap<String, String>();

	Spinner spinner;
	Integer spinner_position;
	EditText amount;
	ListView listView;

	Converter converter;
	CSVParser csvparser;

	public final static Unit<Acceleration> METERS_PER_SQUARE_SECOND = SI.METRES_PER_SQUARE_SECOND;

	DecimalFormat decimalFormat;

	public ConverterFragment()
	{

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{

		Bundle b = getArguments();
		Integer unitGroup = b.getInt("unitGroup");

		converter = Converter.getInstance();
		converter.initUnitMaps();
		
		csvparser = CSVParser.getInstance();

		try
		{
			csvparser.parse_csv(this.getAssetInputStream(unitGroup));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		unitSymbols = csvparser.getUnitSymbols();
		unitNiceNames = csvparser.getUnitNiceNames();
		unitConversionUnit = csvparser.getUnitConversionUnit();
		unitTimes = csvparser.getUnitTimes();
		unitObjects = converter.getUnitObjects(unitConversionUnit, unitTimes);

		this.initialiseConverter();
		
		specialChars.put("[squared]", "\u00B2");
		specialChars.put("[micro]", "\u00B5");
		specialChars.put("[earth]", "\u2080");
		
		decimalFormat = new DecimalFormat("#.##########");

		View rootView = inflater.inflate(R.layout.acceleration_fragment, container, false);

		amount = (EditText) rootView.findViewById(R.id.amount);
		spinner = (Spinner) rootView.findViewById(R.id.spinner);
		listView = (ListView) rootView.findViewById(R.id.listview);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		this.populateDataSet(spinner_position, Double.parseDouble(amount.getText().toString()));

		amount.addTextChangedListener(new TextWatcher()
		{
			public void afterTextChanged(Editable s)
			{
				Log.d("VIC", "afterTextChanged: " + s.toString());
				unitListViewAdapter.notifyDataSetChanged();
			}

			public void beforeTextChanged(CharSequence s, int start, int count, int after)
			{
			}

			public void onTextChanged(CharSequence s, int start, int before, int count)
			{
			}
		});

		spinner.setOnItemSelectedListener(this);

		ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_item, niceNamesList);

		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(spinner_adapter);

		unitListViewAdapter = new UnitListViewAdapter(getActivity().getApplicationContext(), unitListViewRows);

		listView.setAdapter(unitListViewAdapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long arg3)
	{
		spinner_position = position;
		this.populateDataSet(position, Double.parseDouble(amount.getText().toString()));
		unitListViewAdapter.notifyDataSetChanged();

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0)
	{
	}

	private void populateDataSet(Integer position, Double amount)
	{
		unitListViewRows = new ArrayList<UnitListViewRow>();

		niceNamesList = new ArrayList<String>();

		for (String key : unitNiceNames.keySet())
		{
			String nicename = unitNiceNames.get(key);
			String symbol = unitSymbols.get(key);

			// ///////////
			// convert! //
			// ///////////

			Double d = unitObjects.get("CENTIMETERS_PER_SQUARE_SECOND").getConverterTo(unitObjects.get(key)).convert(amount);
			String result = decimalFormat.format(d).toString();

			// replace special characters
			for (String k : specialChars.keySet())
			{
				nicename = nicename.replace(k, specialChars.get(k));
				symbol = symbol.replace(k, specialChars.get(k));
			}

			unitListViewRows.add(new UnitListViewRow(nicename, symbol, result));
			niceNamesList.add(nicename);
		}

	}

	private void initialiseConverter()
	{

		// 3. populate unitObjects TreeMap

		for (String key : unitNiceNames.keySet())
		{
			Unit<?> newObj = null;
			if (unitTimes.get(key).equals(1.0))
			{
				newObj = METERS_PER_SQUARE_SECOND;
			} else
			{
				newObj = METERS_PER_SQUARE_SECOND.times(unitTimes.get(key));
			}

			unitObjects.put(key, newObj);
		}

	}

	private InputStream getAssetInputStream(Integer unitGroup) throws IOException
	{
		InputStream is = null;

		switch (unitGroup) {
		case 1:
			is = getActivity().getAssets().open("Acceleration.csv");
			break;
		default:
			is = getActivity().getAssets().open("Acceleration.csv");
			break;
		}
		return is;

	}

}
