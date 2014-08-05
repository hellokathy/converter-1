package com.bliksem.scientificunitconverter;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class LengthFragment extends Fragment {

	HashMap<String, String> unitSymbols = new HashMap();

	public LengthFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.length_fragment, container, false);
		

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		ArrayList<String> mLengthUnits = new ArrayList<String>();

		String[] length_array = getResources().getStringArray(R.array.length_units);

		for (int i = 0; i < length_array.length; ++i) {
			mLengthUnits.add(length_array[i]);
		}

		Spinner spinner = (Spinner) getView().findViewById(R.id.myspinner);

		ArrayAdapter<CharSequence> spinner_adapter = ArrayAdapter.createFromResource(getActivity(),
				R.array.length_units, android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears
		spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(spinner_adapter);

		StableArrayAdapter adapter = new StableArrayAdapter(getActivity().getApplicationContext(),
				R.layout.listview_row, mLengthUnits);
		DynamicListView listView = (DynamicListView) getView().findViewById(R.id.listview);

		listView.setCheeseList(mLengthUnits);
		listView.setAdapter(adapter);
		listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

	}

}
