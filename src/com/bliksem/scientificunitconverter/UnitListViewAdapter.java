package com.bliksem.scientificunitconverter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class UnitListViewAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<UnitListViewRow> navDrawerRows;

	public UnitListViewAdapter(Context context, ArrayList<UnitListViewRow> navDrawerItems) {
		this.context = context;
		this.navDrawerRows = navDrawerItems;
	}

	@Override
	public int getCount() {
		return navDrawerRows.size();
	}

	@Override
	public Object getItem(int position) {
		return navDrawerRows.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.navigation_drawer_row, null);
		}

		TextView txtNiceName = (TextView) convertView.findViewById(R.id.unit_listview_row_nicename);
		TextView txtSymbol = (TextView) convertView.findViewById(R.id.unit_listview_row_symbol);
		TextView txtResult = (TextView) convertView.findViewById(R.id.unit_listview_row_result);

		txtNiceName.setText(navDrawerRows.get(position).getNiceName());
		txtSymbol.setText(navDrawerRows.get(position).getSymbol());
		txtResult.setText("99.65401");

		return convertView;
	}

}
