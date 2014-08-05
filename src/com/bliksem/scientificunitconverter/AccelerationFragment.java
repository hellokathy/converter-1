package com.bliksem.scientificunitconverter;

import java.util.ArrayList;
import java.util.HashMap;

import javax.measure.quantity.Acceleration;
import javax.measure.unit.SI;
import javax.measure.unit.Unit;

import static javax.measure.unit.NonSI.*;
import static javax.measure.unit.SI.*;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class AccelerationFragment extends Fragment {

	Unit<Acceleration> CENTIMETERS_PER_SQUARE_SECOND = CENTI(SI.METERS_PER_SQUARE_SECOND);
	Unit<Acceleration> FOOT_PER_SQUARE_SECOND = SI.METERS_PER_SQUARE_SECOND.times(0.30480);
	Unit<Acceleration> STANDARD_GRAVITY = SI.METERS_PER_SQUARE_SECOND.times(9.80665);
	Unit<Acceleration> GAL = CENTIMETERS_PER_SQUARE_SECOND;
	Unit<Acceleration> MILLIGAL = MILLI(GAL);
	Unit<Acceleration> MICROGAL = MICRO(GAL);
	Unit<Acceleration> G_UNIT = STANDARD_GRAVITY;
	Unit<Acceleration> MILES_PER_SQUARE_SECOND = SI.METERS_PER_SQUARE_SECOND.times(1609.34348501);
	Unit<Acceleration> INCHES_PER_SQUARE_SECOND = SI.METERS_PER_SQUARE_SECOND.times(0.0254);
	Unit<Acceleration> YARDS_PER_SQUARE_SECOND = SI.METERS_PER_SQUARE_SECOND.times(0.9144);

	HashMap<String, String> unitSymbols = new HashMap();
	HashMap<String, Unit<Acceleration>> unitObjects = new HashMap();
	HashMap<String, String> unitNiceNames = new HashMap();

	public AccelerationFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.length_fragment, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		this.setup();

		ArrayList<String> mLengthUnits = new ArrayList<String>();

		String[] length_array = getResources().getStringArray(R.array.length_units);

		for (int i = 0; i < length_array.length; ++i) {
			mLengthUnits.add(length_array[i]);
		}

		Spinner spinner = (Spinner) getView().findViewById(R.id.myspinner);

		ArrayAdapter<CharSequence> spinner_adapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.length_units, android.R.layout.simple_spinner_item);

		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(spinner_adapter);

		StableArrayAdapter adapter = new StableArrayAdapter(getActivity().getApplicationContext(),
				R.layout.listview_row, mLengthUnits);
		DynamicListView listView = (DynamicListView) getView().findViewById(R.id.listview);

		listView.setCheeseList(mLengthUnits);
		listView.setAdapter(adapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

	}

	private void setup() 
	{
		
		// unit symbols
		unitSymbols.put("METERS_PER_SQUARE_SECOND","m/s\u00B2");
		unitSymbols.put("CENTIMETERS_PER_SQUARE_SECOND","cm/s\u00B2");
		unitSymbols.put("FOOT_PER_SQUARE_SECOND","ft/s\u00B2");
		unitSymbols.put("STANDARD_GRAVITY","g\u2080");
		unitSymbols.put("GAL","Gal");
		unitSymbols.put("MILLIGAL","mGal");
		unitSymbols.put("MICROGAL","\u0085Gal");
		unitSymbols.put("G_UNIT","g");
		unitSymbols.put("MILES_PER_SQUARE_SECOND","mi/s\u00B2");
 		unitSymbols.put("INCHES_PER_SQUARE_SECOND","in/s\u00B2");
		unitSymbols.put("YARDS_PER_SQUARE_SECOND","yd/s\u00B2");
		
		// unit nice names
		unitNiceNames.put("METERS_PER_SQUARE_SECOND","meters per second squared");
		unitNiceNames.put("CENTIMETERS_PER_SQUARE_SECOND","centimeter per second squared");
		unitNiceNames.put("FOOT_PER_SQUARE_SECOND","foot per second squared");
		unitNiceNames.put("STANDARD_GRAVITY","standard gravity");
		unitNiceNames.put("GAL","galileo");
		unitNiceNames.put("MILLIGAL","milli galileo");
		unitNiceNames.put("MICROGAL","micro galileo");
		unitNiceNames.put("G_UNIT","g-force");
		unitNiceNames.put("MILES_PER_SQUARE_SECOND","miles per second squared");
		unitNiceNames.put("INCHES_PER_SQUARE_SECOND","inches per second squared");
		unitNiceNames.put("YARDS_PER_SQUARE_SECOND","yards per second squared");
		
		// unit objects
		unitObjects.put("METERS_PER_SQUARE_SECOND",METERS_PER_SQUARE_SECOND);
		unitObjects.put("CENTIMETERS_PER_SQUARE_SECOND",CENTIMETERS_PER_SQUARE_SECOND);
		unitObjects.put("FOOT_PER_SQUARE_SECOND",FOOT_PER_SQUARE_SECOND);
		unitObjects.put("STANDARD_GRAVITY",STANDARD_GRAVITY);
		unitObjects.put("GAL",GAL);
		unitObjects.put("MILLIGAL",MILLIGAL);
		unitObjects.put("MICROGAL",MICROGAL);
		unitObjects.put("G_UNIT",G_UNIT);
		unitObjects.put("MILES_PER_SQUARE_SECOND",MILES_PER_SQUARE_SECOND);
		unitObjects.put("INCHES_PER_SQUARE_SECOND",INCHES_PER_SQUARE_SECOND);
		unitObjects.put("YARDS_PER_SQUARE_SECOND",YARDS_PER_SQUARE_SECOND);
		
	}
}
