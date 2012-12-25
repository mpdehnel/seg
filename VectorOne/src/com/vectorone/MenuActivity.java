package com.vectorone;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MenuActivity extends Activity {

	private Button cachsselectbutton;
	private Button mapsbutton;
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
		mapsbutton = (Button) findViewById(R.id.mapsbutton);
		mebutton = (Button) findViewById(R.id.mebutton);
		settingsbutton = (Button) findViewById(R.id.settingsbutton);

		cachsselectbutton.setOnClickListener(clickhandler);
		mebutton.setOnClickListener(clickhandler);
		mapsbutton.setOnClickListener(clickhandler);
		settingsbutton.setOnClickListener(clickhandler);
	}

	private void clickhandle(View v) {
		Intent intent;

		if (v == cachsselectbutton) {
			startActivity(new Intent(getApplicationContext(),CachListActivity.class));
		}
		if (v == mebutton) {
			intent = new Intent(getApplicationContext(), MeActivity.class);
			startActivity(intent);
		}
		if (v == mapsbutton) {
			startActivity(new Intent(getApplicationContext(),MapsActivity.class));
		}
		if (v == settingsbutton) {
			intent = new Intent(getApplicationContext(), SettingsActivity.class);
			startActivity(intent);
		}
		
}

}
