package com.bliksem.scientificunitconverter;

import static javax.measure.unit.SI.CENTI;

import static javax.measure.unit.SI.*;
import static javax.measure.unit.SI.MICRO;
import static javax.measure.unit.SI.MILLI;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.CSVReadProc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import javax.measure.quantity.Acceleration;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import android.app.Fragment;
import android.content.res.AssetFileDescriptor;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class AccelerationFragment extends Fragment implements OnItemSelectedListener
{

	TreeMap<String, String> unitSymbols = new TreeMap<String, String>();
	TreeMap<String, Unit<Acceleration>> unitObjects = new TreeMap<String, Unit<Acceleration>>();
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

	Unit<Acceleration> STANDARD_CONVERSION = SI.METERS_PER_SQUARE_SECOND;
	
	DecimalFormat decimalFormat;

	public AccelerationFragment()
	{

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		this.initialiseConverter();

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
			
			//////////////
			// convert! //
			//////////////
			
			Double d = unitObjects.get("CENTIMETERS_PER_SQUARE_SECOND").getConverterTo( unitObjects.get( key )).convert(amount);
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
		// 1. initialize special characters

		specialChars.put("[squared]", "\u00B2");
		specialChars.put("[micro]", "\u00B5");
		specialChars.put("[earth]", "\u2080");

		// 2. read appropriate csv assets file

		CSVReader reader = null;
		try
		{
			reader = new CSVReader(new InputStreamReader(getActivity().getAssets().open("Acceleration.csv")));
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		String[] nextLine;
		try
		{
			while ((nextLine = reader.readNext()) != null)
			{
				if (nextLine[0].equals("name") && nextLine[1].equals("symbol"))
				{
					continue;
				}

				unitSymbols.put(nextLine[0], nextLine[1]);
				unitNiceNames.put(nextLine[0], nextLine[2]);
				unitConversionUnit.put(nextLine[0], nextLine[3]);
				unitTimes.put(nextLine[0], Double.parseDouble(nextLine[4]));
			}
		} catch (NumberFormatException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			reader.close();
		} catch (IOException e)
		{
			e.printStackTrace();
		}

		// 3. populate unitObjects TreeMap

		for (String key : unitNiceNames.keySet())
		{
			Unit<Acceleration> newObj = null;
			if (unitTimes.get(key).equals(1.0))
			{
				newObj = STANDARD_CONVERSION;
			} else
			{
				newObj = STANDARD_CONVERSION.times(unitTimes.get(key));
			}

			unitObjects.put(key, newObj);
		}
		
		// 4. Decimal Format
	    decimalFormat = new DecimalFormat("#.##########");
	}

}
