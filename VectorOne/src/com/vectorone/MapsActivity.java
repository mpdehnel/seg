package com.vectorone;

import android.os.Bundle;

import com.data.GeoLocation;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class MapsActivity extends MapActivity {

	private MapView mapView;
	private GeoLocation locclac;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		SpinnerListener.setFlag(false);
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
		locclac = new GeoLocation(this, mapView);

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}