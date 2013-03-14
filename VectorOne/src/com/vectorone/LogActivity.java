package com.vectorone;

import com.data.DataClass;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.widget.GridLayout;
import android.widget.TextView;

public class LogActivity extends Activity {

	private TextView log;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_log);

		GridLayout background = (GridLayout) findViewById(R.id.log_layout);
		background.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));

		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#45250F");

		log = (TextView) findViewById(R.id.logText);
		log.setMovementMethod(ScrollingMovementMethod.getInstance());
		log.setTypeface(font);
		log.setTextColor(textcolor);
		log.setTextSize(20);
		log.setText(DataClass.log.toString());
	}

	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(getBaseContext(), MeActivity.class));
	}
}
