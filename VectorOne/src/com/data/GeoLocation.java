package com.data;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.vectorone.MainLogInActivity;
import com.vectorone.MapsActivity;
import android.content.Context;
import android.location.*;
import android.os.Bundle;
import android.widget.Toast;

public class GeoLocation implements LocationListener {
	private LocationManager locationManager;
	private String provider;
	private MapsActivity gmapsActivity;
	private double lat;
	private double lng;
	private MapController mc;
	
	public GeoLocation(MapsActivity app, MapView mapView) {
		this.gmapsActivity=app;
		mc = mapView.getController();
		// Get the location manager
		locationManager = (LocationManager) gmapsActivity.getSystemService(Context.LOCATION_SERVICE);
		// Define the criteria how to select the locatioin provider -> use
		// default
		// Criteria criteria = new Criteria();
		// provider = locationManager.getBestProvider(criteria, false);
		provider = locationManager.GPS_PROVIDER;
		Location location = locationManager.getLastKnownLocation(provider);

		// Initialize the location fields
		if (location != null) {
			System.out.println("Provider " + provider + " has been selected.");
			onLocationChanged(location);
		} else {
			lng=54*Math.pow(10,6 );
			lat=-1*Math.pow(10, 6);
		
			mc.animateTo(new GeoPoint((int) lat,(int) lng));
			mc.setZoom(17);
		}
		
	}




	/* Request updates at startup */
	protected void onResume() {
		locationManager.requestLocationUpdates(provider, 400, 1, this);
	}

	/* Remove the locationlistener updates when Activity is paused */
	protected void onPause() {
		locationManager.removeUpdates(this);
	}

	@Override
	public void onLocationChanged(Location location) {
		lng=location.getLongitude()*Math.pow(10, 6);
		lat=location.getLatitude()*Math.pow(10, 6);
		
		mc.animateTo(new GeoPoint((int) lat,(int) lng));
		mc.setZoom(17);
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(gmapsActivity, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onProviderDisabled(String provider) {
		Toast.makeText(gmapsActivity, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}
}