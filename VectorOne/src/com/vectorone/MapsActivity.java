package com.vectorone;

import java.io.InputStream;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ScaleDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomButtonsController.OnZoomListener;

import com.data.Cache;
import com.data.DataClass;
import com.data.Model;
import com.data.SegMathClass;
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
	private TextView scale;

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
		scale = (TextView) findViewById(R.id.scale);
		scale.setText("|_______|");

		// locclac = new GeoLocation(this, mapView);

		mapView.getZoomButtonsController().setOnZoomListener(
				new OnZoomListener() {

					@Override
					public void onZoom(boolean zoomIn) {
						if (zoomIn) {
							mc.zoomIn();
							retextScale(mapView.getZoomLevel());

						} else {
							mc.zoomOut();
							retextScale(mapView.getZoomLevel());
						}

					}

					@Override
					public void onVisibilityChanged(boolean visible) {
						retextScale(mapView.getZoomLevel());
					}
				});

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		Criteria criteria = new Criteria();

		provider = locationManager.getBestProvider(criteria, false);
		// provider = locationManager.GPS_PROVIDER;
		location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			onLocationChanged(location);
			mc.setZoom(15);
		} else {
			lng = 54 * Math.pow(10, 6);
			lat = -1 * Math.pow(10, 6);

			mc.animateTo(new GeoPoint((int) lat, (int) lng));
			mc.setZoom(15);

		}
		setNewCache = (Button) findViewById(R.id.setMyPositionasNewCacheButto);
		setNewCache.setBackgroundColor(Color.GREEN);
		setNewCache.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getApplicationContext(),
						AddCacheActivity.class);

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

	private void retextScale(int zoomLevel) {
		String space = "__________";
		int distance = 0;
		// Toast.makeText(getApplicationContext(), zoomLevel+"a",
		// Toast.LENGTH_SHORT).show();
		// scale.setText("|"+space+"|");
		// Toast.makeText(getApplicationContext(), scale.getWidth()+"w",
		// Toast.LENGTH_SHORT).show();
		double calcdistance = (40075.017 / 256.0) * 110
				/ Math.pow(2, zoomLevel - 1);// *0.621371192;// meter
		if (calcdistance > 50) {
			distance = (int) calcdistance;
			scale.setText("|" + space + "|" + distance + "km");
		} else {
			distance = (int) (calcdistance * 1000);
			scale.setText("|" + space + "|" + distance + "m");
		}

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
		DataClass.setMylat((int) lat);
		DataClass.setMylng((int) lng);
		DataClass.setMyGeoPoint();
		mc.animateTo(DataClass.getMyGeoPoint());
		removeallOverlaysandaddnew();
		addOverlay(R.drawable.roterpunkt, new GeoPoint((int) getLat(),
				(int) getLng()), "Hi", "Here i am!");
		if (DataClass.routing > 0)
			drawroute(DataClass.routing);
		check_If_I_Found_a_Cache();
	}

	private void check_If_I_Found_a_Cache() {
		for (int i = 0; i < DataClass.selectedCaches.size(); i++) {
			if (DataClass.selectedCaches.get(i).isIslessthan50m()) {
				final Dialog dialog = new Dialog(this);
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
				dialog.setContentView(R.layout.dialog_cache_found);
				TextView text = (TextView) dialog
						.findViewById(R.id.dialog_cachefound_Text);
				text.setBackgroundColor(Color.BLACK);
				text.setText("Awesome You found the Cache" + "\n"
						+ DataClass.selectedCaches.get(i).getName());
				text.setTextSize(30);

				Button cancelButton = (Button) dialog
						.findViewById(R.id.dialogButtonOk);
				cancelButton.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
					}
				});
				playSound();
				dialog.show();

			}
		}
	}

	private void playSound() {
		new Thread() {
			public void run() {
				MediaPlayer mp = MediaPlayer.create(getApplicationContext(),
						R.raw.bossdeath);
				mp.start();
			}
		}.start();

	}

	private void drawroute(int routecache) {
		Cache target = DataClass.caches.get(routecache).getCach();

		GeoPoint start = DataClass.myGeoPoint;
		GeoPoint targetpoint = target.getGeopoint();

		int id_image = R.drawable.cross;
		int numberofcross = 100;

		String cachname = target.getName();
		int longi = (start.getLongitudeE6() - targetpoint.getLongitudeE6())
				/ numberofcross;
		int lati = (start.getLatitudeE6() - targetpoint.getLatitudeE6())
				/ numberofcross;

		for (int i = 0; i < numberofcross; i++) {

			addOverlay(id_image, new GeoPoint(start.getLatitudeE6() - lati * i,
					start.getLongitudeE6() - longi * i), "way to" + cachname, i
					+ "%to target");
		}

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

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

}