package com.vectorone;

import android.app.Activity;
import android.os.Bundle;

import com.data.GeoLocation;
import com.google.android.maps.MapView;

public class RadarActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_radar);
		SpinnerListener.setFlag(false);

	}

}