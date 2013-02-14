package com.vectorone;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.RelativeLayout;

public class SettingsActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_settings);
		RelativeLayout relativelayout = (RelativeLayout) findViewById(R.id.settings_layout);
		relativelayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));

	}
}
