package com.vectorone;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.data.GeoLocation;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

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
		
		
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(R.drawable.roterpunkt);
		ItemOverlay itemizedoverlay = new ItemOverlay(drawable, this);
		/*
		 * 
		 * add Overlay!!!l
		 * 
		 */
		GeoPoint point = new GeoPoint((int)locclac.getLat(),(int)locclac.getLng());
		OverlayItem overlayitem = new OverlayItem(point, "Hi", "Here i am!");
		
		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

}