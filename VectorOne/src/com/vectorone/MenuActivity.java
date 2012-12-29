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

	private Button cachsselectbutton;
	private Button settingsbutton;
	private Button mebutton;
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
		username = intent.getStringExtra("unsername");

		cachsselectbutton = (Button) findViewById(R.id.cachselectbutton);
		mebutton = (Button) findViewById(R.id.mebutton);
		settingsbutton = (Button) findViewById(R.id.settingsbutton);

		cachsselectbutton.setOnClickListener(clickhandler);
		mebutton.setOnClickListener(clickhandler);
		settingsbutton.setOnClickListener(clickhandler);

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

		if (v == cachsselectbutton) {
			startActivity(new Intent(getApplicationContext(),
					CachListActivity.class));
		}
		if (v == mebutton) {
			intent = new Intent(getApplicationContext(), MeActivity.class);
			startActivity(intent);
		}
		if (v == settingsbutton) {
			intent = new Intent(getApplicationContext(), SettingsActivity.class);
			startActivity(intent);
		}

	}

}