package com.vectorone;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity {

	private Button cachesSelectButton;
	private Button settingsButton;
	private Button meButton;
	private String username;

	private OnClickListener clickhandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clickhandle(v);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menue);
		Intent intent = getIntent();
		username = intent.getStringExtra("username");

		cachesSelectButton = (Button) findViewById(R.id.cachselectbutton);
		meButton = (Button) findViewById(R.id.mebutton);
		settingsButton = (Button) findViewById(R.id.settingsbutton);

		cachesSelectButton.setOnClickListener(clickhandler);
		meButton.setOnClickListener(clickhandler);
		settingsButton.setOnClickListener(clickhandler);

		Spinner spinner = (Spinner) findViewById(R.id.FindingViewSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.FindingViews,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new SpinnerListener(this, adapter));
	}

	private void clickhandle(View v) {
		Intent intent;

		if (v == cachesSelectButton) {
			startActivity(new Intent(getApplicationContext(),
					CacheListActivity.class));
		}
		if (v == meButton) {
			intent = new Intent(getApplicationContext(), MeActivity.class);
			startActivity(intent);
		}
		if (v == settingsButton) {
			intent = new Intent(getApplicationContext(), SettingsActivity.class);
			startActivity(intent);
		}

	}

}
