package com.vectorone;

import com.data.DataClass;
import com.data.SpinnerListener;
import com.data.User;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SettingsActivity extends Activity {

	private RelativeLayout relativelayout;
	private TextView label_unit;
	private TextView lable_team;
	private TextView lable_maxdistance;
	public Spinner spinner_unit;
	public Spinner spinner_team;
	public Spinner spinner_distance;
	private SpinnerListener spinner_listener;
	private Button button_apply;
	private User user = DataClass.user;
	private TextView lable_cache;
	private Spinner spinner_cache;
	private TextView lable_sort;
	private Spinner spinner_sort;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_settings);

		initdatafields();
		setupListener();
		setupfont();
		setupbackgroundimage();
		setupdatafields();

	}

	private void setupfont() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#DECD87");
		int buttoncolor = Color.parseColor("#45250F");
		float textsize = 22;

		button_apply.setTextColor(buttoncolor);
		button_apply.setTypeface(font);
		button_apply.setTextSize(textsize);
		button_apply.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.buttonsmall));

		label_unit.setTextSize(textsize);
		label_unit.setTextColor(textcolor);
		label_unit.setTypeface(font);

		lable_maxdistance.setTextSize(textsize);
		lable_maxdistance.setTextColor(textcolor);
		lable_maxdistance.setTypeface(font);

		lable_team.setTextSize(textsize);
		lable_team.setTextColor(textcolor);
		lable_team.setTypeface(font);
		
		lable_cache.setTextSize(textsize);
		lable_cache.setTextColor(textcolor);
		lable_cache.setTypeface(font);
		lable_cache.setText("Visited"+System.getProperty("line.separator")+"Caches");
		
		lable_sort.setTextSize(textsize);
		lable_sort.setTextColor(textcolor);
		lable_sort.setTypeface(font);
		

	}

	private void setupListener() {
		spinner_distance.setOnItemSelectedListener(spinner_listener);
		spinner_team.setOnItemSelectedListener(spinner_listener);
		spinner_unit.setOnItemSelectedListener(spinner_listener);
		spinner_cache.setOnItemSelectedListener(spinner_listener);
		spinner_sort.setOnItemSelectedListener(spinner_listener);
		button_apply.setOnClickListener(clickhandler);
	}

	private void setupbackgroundimage() {

		relativelayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
	}

	private void setupdatafields() {
		Log.i("MAIN", "setupFields");
		if (user.getSettings_unit() != null) {
			if (user.getSettings_unit().equals("m/km")) {
				Log.i("MAIN", "km");
				spinner_unit.setSelection(0);
			} else {
				Log.i("MAIN", "feet");
				spinner_unit.setSelection(1);
			}
		}
		if (user.getSettings_team() != null) {
			Log.i("MAIN", "team");
			spinner_team.setSelection(getPosinArray(getResources()
					.getStringArray(R.array.Team), user.getSettings_team()));
		}
		if (user.getSettings_maxdistance() != null) {
			Log.i("MAIN", "distance");
			spinner_distance.setSelection(getPosinArray(getResources()
					.getStringArray(R.array.Distance), user
					.getSettings_maxdistance()));
		}

	}

	private int getPosinArray(String[] data, String date) {
		for (int i = 0; i < data.length; i++) {
			if (data[i].equals(date)) {
				Log.i("MAIN", "inarray-date-"+i+"--"+date+"---");
				return i;
			}
		}
		return 0;
	}

	private void initdatafields() {
		relativelayout = (RelativeLayout) findViewById(R.id.settings_layout);
		label_unit = (TextView) findViewById(R.id.Unit);
		lable_team = (TextView) findViewById(R.id.team_lable);
		lable_maxdistance = (TextView) findViewById(R.id.max_distance);
		spinner_unit = (Spinner) findViewById(R.id.spinner_unit);
		spinner_team = (Spinner) findViewById(R.id.spinner_team);
		spinner_distance = (Spinner) findViewById(R.id.spinner_distance);
		spinner_listener = new SpinnerListener(DataClass.user, this);
		button_apply = (Button) findViewById(R.id.set);
		lable_cache = (TextView) findViewById(R.id.settings_cache);
		spinner_cache = (Spinner) findViewById(R.id.spinner_cache);
		lable_sort =(TextView) findViewById(R.id.settings_Sortby);
		spinner_sort=(Spinner) findViewById(R.id.spinner_SortBy);
	}

	private OnClickListener clickhandler = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (v == button_apply) {
				Log.i("MAIN", "Update User online and database");
				Intent intent = new Intent(getBaseContext(), MenuActivity.class);
				startActivity(intent);
				finish();
			}
		}
	};
}
