package com.vectorone;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.data.DataClass;
import com.data.MyHttpClient;
import com.data.SpinnerAdapter;
import com.data.SpinnerListener;
import com.data.User;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
	private Vibrator vibrator;

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
		lable_cache.setText("Visited" + System.getProperty("line.separator")
				+ "Caches");

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
			if (user.getSettings_unit().equals("m-km")) {
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

		if (user.getSettings_visited() != null) {
			Log.i("MAIN", "visited");
			spinner_cache
					.setSelection(getPosinArray(
							getResources().getStringArray(R.array.Cachs),
							user.getSettings_visited()));
		}

		if (user.getSettings_sorted() != null) {
			Log.i("MAIN", "sorted");
			spinner_sort.setSelection(getPosinArray(getResources()
					.getStringArray(R.array.sort), DataClass.SortType));
		}

	}

	private int getPosinArray(String[] data, String date) {
		for (int i = 0; i < data.length; i++) {
			if (data[i].equals(date)) {
				Log.i("MAIN", "inarray-date-" + i + "--" + date + "---");
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
		spinner_unit.setAdapter(new SpinnerAdapter(getApplicationContext(), R.layout.spinner_row, getResources().getStringArray(R.array.Unit)));
		spinner_team = (Spinner) findViewById(R.id.spinner_team);
		spinner_team.setAdapter(new SpinnerAdapter(getApplicationContext(), R.layout.spinner_row, getResources().getStringArray(R.array.Team)));
		spinner_distance = (Spinner) findViewById(R.id.spinner_distance);
		spinner_distance.setAdapter(new SpinnerAdapter(getApplicationContext(), R.layout.spinner_row, getResources().getStringArray(R.array.Distance)));
		spinner_listener = new SpinnerListener(DataClass.user, this);
		button_apply = (Button) findViewById(R.id.set);
		lable_cache = (TextView) findViewById(R.id.settings_cache);
		spinner_cache = (Spinner) findViewById(R.id.spinner_cache);
		spinner_cache.setAdapter(new SpinnerAdapter(getApplicationContext(), R.layout.spinner_row, getResources().getStringArray(R.array.Cachs)));
		lable_sort = (TextView) findViewById(R.id.settings_Sortby);
		spinner_sort = (Spinner) findViewById(R.id.spinner_SortBy);
		spinner_sort.setAdapter(new SpinnerAdapter(getApplicationContext(), R.layout.spinner_row, getResources().getStringArray(R.array.sort)));
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	}

	@Override
	public void onBackPressed() {
		Log.i("MAIN", "Update User online and database");
		upload();
		vibrator.vibrate(50);
		Intent intent = new Intent(getBaseContext(), MenuActivity.class);
		startActivity(intent);
		finish();

	}

	private void upload() {
		MyHttpClient http=new MyHttpClient(DataClass.server);
		User user=DataClass.user;
		try {
			http.usersettings(user.getUsername(), user.getSettings_unit(), user.getSettings_maxdistance(), user.getSettings_visited(), user.getSettings_team(),DataClass.SortType);
		} catch (ClientProtocolException e) {
			Toast.makeText(getBaseContext(), "Connection Error settings not online next login you have to re-set it", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (IOException e) {
			Toast.makeText(getBaseContext(), "Connection Error settings not online next login you have to re-set it", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
	}

	private OnClickListener clickhandler = new OnClickListener() {

		@Override
		public void onClick(View v) {
			vibrator.vibrate(50);
			if (v == button_apply) {
				upload();
				Log.i("MAIN", "Update User online and database");
				Intent intent = new Intent(getBaseContext(), MenuActivity.class);
				startActivity(intent);
				finish();
			}
		}

	};
}
