package com.vectorone;

import com.data.DataClass;
import com.findCache.MapsActivity;
import com.findCache.RadarActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

public class ViewActivity extends Activity {
	private Button maps;
	private Button radar;
	private Button back;
	private Vibrator vibrator;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_view);
		RelativeLayout layout = (RelativeLayout) findViewById(R.id.activity_view);
		layout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		initbuttons();

	}

	private void initbuttons() {
		maps = (Button) findViewById(R.id.button_maps);
		radar = (Button) findViewById(R.id.button_radar);
		back = (Button) findViewById(R.id.back);
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int buttoncolor = Color.parseColor("#45250F");
		float textsize = 22;
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonsmall);

		maps.setTypeface(font);
		
		maps.setTextSize(textsize);
		if(DataClass.user.isHardcore()){
		maps.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonsmallgrey));
		maps.setTextColor(Color.BLACK);
		maps.setEnabled(false);
		}else{
			maps.setBackgroundDrawable(buttonimage);
			maps.setTextColor(buttoncolor);
			maps.setEnabled(true);
		}
		radar.setTypeface(font);
		radar.setTextColor(buttoncolor);
		radar.setTextSize(textsize);
		radar.setBackgroundDrawable(buttonimage);

		back.setTypeface(font);
		back.setTextColor(buttoncolor);
		back.setTextSize(textsize);
		back.setBackgroundDrawable(buttonimage);

		maps.setOnClickListener(clickhandeler);
		radar.setOnClickListener(clickhandeler);
		back.setOnClickListener(clickhandeler);
	}

	private OnClickListener clickhandeler = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent;
			vibrator.vibrate(50);
			if (v == maps) {
				finish();
				intent = new Intent(getApplicationContext(), MapsActivity.class);
				startActivity(intent);
			}
			if (v == radar) {
				finish();
				intent = new Intent(getApplicationContext(),
						RadarActivity.class);
				startActivity(intent);
			}
			if (v == back) {
				finish();
				intent = new Intent(getApplicationContext(), MenuActivity.class);
				startActivity(intent);
			}
		}
	};

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent intent;
		finish();
		intent = new Intent(getApplicationContext(), MenuActivity.class);
		startActivity(intent);

	}

}
