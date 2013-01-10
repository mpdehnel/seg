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
		addOverlay(R.drawable.roterpunkt, new GeoPoint((int) locclac.getLat(),
				(int) locclac.getLng()), "Hi", "Here i am!");

	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public void addOverlay(int id_image, GeoPoint where, String topic,
			String discription) {
		List<Overlay> mapOverlays = mapView.getOverlays();
		Drawable drawable = this.getResources().getDrawable(id_image);
		ItemOverlay itemizedoverlay = new ItemOverlay(drawable, this);
		OverlayItem overlayitem = new OverlayItem(where, topic, discription);

		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
	}

}