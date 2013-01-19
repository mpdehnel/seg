package com.vectorone;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.data.DataClass;
import com.data.Model;
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
	private List<Overlay> mapOverlays;
	private Button setNewCache;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_maps);
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setBuiltInZoomControls(true);
		mapOverlays = mapView.getOverlays();
		removeallOverlaysandaddnew();
		mc = mapView.getController();

		// locclac = new GeoLocation(this, mapView);

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

		setNewCache = (Button) findViewById(R.id.setMyPositionasNewCacheButto);
		setNewCache.setBackgroundColor(Color.GREEN);
		setNewCache.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),AddCacheActivity.class);
					intent.putExtra("lng", lng);
					intent.putExtra("lat", lat);
					
					startActivity(intent);
			
			}
		});

	}

	private void removeallOverlaysandaddnew() {
		for (int i = 0; i < mapOverlays.size(); i++)
			mapOverlays.remove(i);
		for (int i = 0; i < DataClass.selectedCaches.size(); i++) {
			// map points;
			int teamColour = DataClass.selectedCaches.get(i).getTeamcolour();
			int image = R.drawable.roterpunkt;// point image;
			GeoPoint gp = DataClass.selectedCaches.get(i).getGeopoint();
			String name = DataClass.selectedCaches.get(i).getName();
			String description = DataClass.selectedCaches.get(i)
					.getDescripton();
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
		// Toast.makeText(this, mapOverlays.size() + "asd", Toast.LENGTH_SHORT)
		// .show();
		Drawable drawable = this.getResources().getDrawable(id_image);
		ItemOverlay itemizedoverlay = new ItemOverlay(drawable, this);
		OverlayItem overlayitem = new OverlayItem(where, topic, discription);

		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
	}

	@Override
	public void onLocationChanged(Location location) {
		lng = location.getLongitude() * Math.pow(10, 6);
		lat = location.getLatitude() * Math.pow(10, 6);
		mc.animateTo(new GeoPoint((int) lat, (int) lng));
		removeallOverlaysandaddnew();
		addOverlay(R.drawable.roterpunkt, new GeoPoint((int) getLat(),
				(int) getLng()), "Hi", "Here i am!");
		
		Model.setMyPosition(new GeoPoint((int) lat, (int) lng));

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