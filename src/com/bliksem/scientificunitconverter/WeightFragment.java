package com.bliksem.scientificunitconverter;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WeightFragment extends Fragment
{

	public WeightFragment()
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.weight_fragment, container, false);

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState)
	{
		super.onActivityCreated(savedInstanceState);

		Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");

		TextView txt = (TextView) getView().findViewById(R.id.textView12);
		txt.setTypeface(font);

	}

}
