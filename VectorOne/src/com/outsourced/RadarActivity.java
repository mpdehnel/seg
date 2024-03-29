package com.outsourced;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.widget.FrameLayout;

import com.google.android.maps.MapView;
import com.vectorone.R;
import com.vectorone.R.id;
import com.vectorone.R.layout;

public class RadarActivity extends Activity {
	private FrameLayout preview;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_radar);
		preview = (FrameLayout) findViewById(R.id.radar_preview);
		preview.setBackgroundColor(Color.GREEN);
		preview.addView(new RadarBackgroudOverlay(this));

	}

}
