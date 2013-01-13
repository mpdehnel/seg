package com.vectorone;

import java.util.List;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Window;

import com.data.DataClass;
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
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_maps);
		SpinnerListener.setFlag(false);
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
		locclac = new GeoLocation(this, mapView);
		addOverlay(R.drawable.roterpunkt, new GeoPoint((int) locclac.getLat(),
				(int) locclac.getLng()), "Hi", "Here i am!");
		for (int i = 0; i < DataClass.caches.size(); i++) {
			//map points;
			int teamColour=  DataClass.caches.get(i).getTeamcolour();
			int image = R.drawable.roterpunkt;// point image;
			GeoPoint gp = DataClass.caches.get(i).getGeopoint();
			String name = DataClass.caches.get(i).getName();
			String description = DataClass.caches.get(i).getDescripton();
			addOverlay(image, gp, name, description);
		}

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