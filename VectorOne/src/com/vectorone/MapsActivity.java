package com.vectorone;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;
import android.widget.ZoomButtonsController.OnZoomListener;

import com.data.DataClass;
import com.data.GeoLocation;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

@SuppressLint("ShowToast")
public class MapsActivity extends MapActivity implements LocationListener {

	private MapView mapView;
	// private GeoLocation locclac;
	private LocationManager locationManager;
	private String provider;
	private Location location;
	private double lat;
	private double lng;
	private MapController mc;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_maps);
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
		

		// TEST _____________________________________________________
		
		
		
		
		
		mc = mapView.getController();
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Criteria criteria = new Criteria();

		provider = locationManager.getBestProvider(criteria, false);
		// provider = locationManager.GPS_PROVIDER;
		location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			onLocationChanged(location);
			mc.setZoom(17);
		} else {
			lng = 54 * Math.pow(10, 6);
			lat = -1 * Math.pow(10, 6);

			mc.animateTo(new GeoPoint((int) lat, (int) lng));
			mc.setZoom(17);
		}

		// locclac = new GeoLocation(this, mapView);
		addOverlay(R.drawable.roterpunkt, new GeoPoint((int) getLat(),
				(int) getLng()), "Hi", "Here i am!");
		for (int i = 0; i < DataClass.caches.size(); i++) {
			// map points;
			int teamColour = DataClass.caches.get(i).getTeamcolour();
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

	public void update() {

	}

	@Override
	public void onLocationChanged(Location location) {
		lng = location.getLongitude() * Math.pow(10, 6);
		lat = location.getLatitude() * Math.pow(10, 6);
		mc.animateTo(new GeoPoint((int) lat, (int) lng));
		
		

	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	public double getLat() {
		return lat;
	}

	public double getLng() {
		return lng;
	}

	/* Request updates at startup */
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 100, 1, this);
	}

	/* Remove the locationListener updates when Activity is paused */
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

}