package com.bliksem.scientificunitconverter;

import java.util.HashMap;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.joanzapata.android.iconify.IconDrawable;
import com.joanzapata.android.iconify.Iconify.IconValue;

public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks
{

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;
	private boolean inSettings = false;
	private Integer mDrawerNumber;

	private HashMap<Integer, IconValue> mIcons;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(R.id.navigation_drawer);

		mTitle = getTitle();

		mIcons = new HashMap<Integer, IconValue>();

		mIcons.put(0, IconValue.fa_tachometer);
		mIcons.put(1, IconValue.fa_crop);
		mIcons.put(2, IconValue.fa_arrows_alt);
		mIcons.put(3, IconValue.fa_space_shuttle);
		mIcons.put(4, IconValue.fa_tint);
		mIcons.put(5, IconValue.fa_bolt);
		mIcons.put(6, IconValue.fa_database);
		mIcons.put(7, IconValue.fa_cutlery);
		mIcons.put(8, IconValue.fa_upload);
		mIcons.put(9, IconValue.fa_gavel);
		mIcons.put(10, IconValue.fa_bars);
		mIcons.put(11, IconValue.fa_bolt);
		mIcons.put(12, IconValue.fa_bolt);
		mIcons.put(13, IconValue.fa_bolt);
		mIcons.put(14, IconValue.fa_shield);
		mIcons.put(15, IconValue.fa_lightbulb_o);
		mIcons.put(16, IconValue.fa_fire);
		mIcons.put(17, IconValue.fa_arrows_h);
		mIcons.put(18, IconValue.fa_star);
		mIcons.put(19, IconValue.fa_star_half_full);
		mIcons.put(20, IconValue.fa_star_o);
		mIcons.put(21, IconValue.fa_magnet);
		mIcons.put(22, IconValue.fa_magnet);
		mIcons.put(23, IconValue.fa_magnet);
		mIcons.put(24, IconValue.fa_magnet);
		mIcons.put(25, IconValue.fa_exchange);
		mIcons.put(26, IconValue.fa_square);
		mIcons.put(27, IconValue.fa_truck);
		mIcons.put(28, IconValue.fa_wrench);
		mIcons.put(29, IconValue.fa_arrows_alt);
		mIcons.put(30, IconValue.fa_bomb);
		mIcons.put(31, IconValue.fa_fighter_jet);
		mIcons.put(32, IconValue.fa_fire);
		mIcons.put(33, IconValue.fa_clock_o);
		mIcons.put(34, IconValue.fa_flask);
		mIcons.put(35, IconValue.fa_lock);
		mIcons.put(36, IconValue.fa_location_arrow);

		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position)
	{
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number)
	{
		mDrawerNumber = number - 1;
		String[] navMenuTitles = getResources().getStringArray(R.array.unit_groups);
		mTitle = navMenuTitles[number - 1];
		getActionBar().setIcon(new IconDrawable(this, mIcons.get(number - 1)).colorRes(R.color.ab_item).actionBarSize());
	}

	public void restoreActionBar()
	{
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
		getActionBar().setIcon(new IconDrawable(this, mIcons.get(mDrawerNumber)).colorRes(R.color.ab_item).actionBarSize());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{

		if (!mNavigationDrawerFragment.isDrawerOpen())
		{
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();

		if (inSettings)
		{
			this.backFromSettingsFragment();
			restoreActionBar();
			return;
		}

		finish();

	}

	private void backFromSettingsFragment()
	{
		inSettings = false;
		getFragmentManager().popBackStack();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		int id = item.getItemId();

		if (id == R.id.action_settings)
		{
			inSettings = true;
			getActionBar().setTitle("Settings");
			getActionBar().setIcon(new IconDrawable(this, IconValue.fa_cog).colorRes(R.color.ab_item).actionBarSize());
			getFragmentManager().beginTransaction().replace(R.id.container, new SettingsFragment()).addToBackStack("settings").commit();
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment
	{

		private static final String ARG_SECTION_NUMBER = "section_number";

		public static PlaceholderFragment newInstance(int sectionNumber)
		{
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment()
		{
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View rootView = inflater.inflate(R.layout.fragment_main, container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity)
		{
			super.onAttach(activity);
			((MainActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}
}
