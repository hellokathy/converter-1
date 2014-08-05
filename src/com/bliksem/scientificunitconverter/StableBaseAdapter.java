package com.bliksem.scientificunitconverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.joanzapata.android.iconify.Iconify;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class StableBaseAdapter extends BaseAdapter {

	final int INVALID_ID = -1;

	HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	private Context context;
	private ArrayList<UnitListViewRow> navDrawerRows;

	public StableBaseAdapter(Context context, ArrayList<UnitListViewRow> navDrawerItems, List<String> objects) {
		this.context = context;
		this.navDrawerRows = navDrawerItems;
		
		for (int i = 0; i < objects.size(); ++i) {
			mIdMap.put(objects.get(i), i);
		}
	}

	@Override
	public long getItemId(int position) {
		if (position < 0 || position >= mIdMap.size()) {
			return INVALID_ID;
		}
		//String item = getItem(position);
		return 55;
		//return mIdMap.get(item);
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public int getCount() {
		return navDrawerRows.size();
	}

	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.unit_listview_row, null);
		}

		TextView txtNiceName = (TextView) convertView.findViewById(R.id.unit_listview_row_nicename);
		TextView txtSymbol = (TextView) convertView.findViewById(R.id.unit_listview_row_symbol);
		TextView txtResult = (TextView) convertView.findViewById(R.id.unit_listview_row_result);

		txtNiceName.setText(navDrawerRows.get(position).getNiceName());
		txtSymbol.setText(navDrawerRows.get(position).getSymbol());
		txtResult.setText("99.340504");      
	
		return convertView;	
		
	}

	@Override
	public Object getItem(int position) {
		
		return null;
	}
}
