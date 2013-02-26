package com.vectorone;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ZoomButtonsController.OnZoomListener;

import com.data.Cache;
import com.data.DataClass;
import com.data.SegMathClass;
import com.game.memory.Game_memory_Activity;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.*;
import com.maps.route.GoogleParser;
import com.maps.route.Parser;
import com.maps.route.Route;
import com.maps.route.RouteOverlay;

@SuppressLint("ShowToast")
public class MapsActivity extends MapActivity implements LocationListener {

	private MapView mapView;
	private LocationManager locationManager;
	private String provider;
	private RouteOverlay routeOverlay;
	private Location location;
	private MapController mc;
	private List<Overlay> mapOverlays;
	private Button zoomin;
	private Button zoomout;
	private Button setNewCache;
	private TextView scale;
	private Button refresh_button;
	private CheckBox satelitenmode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_maps);
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.setSatellite(false);
		mapOverlays = mapView.getOverlays();

		removeallOverlaysandaddnew();
		mc = mapView.getController();
		scale = (TextView) findViewById(R.id.scale);
		scale.setText("|_______|");
		mapView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				retextScale(mapView.getZoomLevel());
				if (event.getPointerCount() > 1) {
					return true;
				}
				return false;
			}
		});

		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		provider = LocationManager.GPS_PROVIDER;
		location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			onLocationChanged(location);
			mc.setZoom(15);
		} else {
			DataClass.mylng = (int) (54 * Math.pow(10, 6));
			DataClass.mylat = (int) (-1 * Math.pow(10, 6));

			mc.animateTo(new GeoPoint(DataClass.mylat, DataClass.mylng));
			mc.setZoom(15);

		}

		initbuttons();
		handellistener();
		setupButtons();

	}

	private void initbuttons() {
		setNewCache = (Button) findViewById(R.id.setMyPositionasNewCacheButto);
		refresh_button = (Button) findViewById(R.id.refreshbutton);
		zoomin = (Button) findViewById(R.id.ZoomIn);
		zoomout = (Button) findViewById(R.id.ZoomOut);
		satelitenmode = (CheckBox) findViewById(R.id.Satelitenmode);
	}

	private void handellistener() {
		setNewCache.setOnClickListener(clickhandler);
		refresh_button.setOnClickListener(clickhandler);
		zoomin.setOnClickListener(clickhandler);
		zoomout.setOnClickListener(clickhandler);
		satelitenmode.setOnClickListener(clickhandler);
	}

	private void setupButtons() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		float textsize = 22;
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonmedium);
		Drawable zoombuttonimage = getResources().getDrawable(
				R.drawable.buttonsmall);
		int buttontextcolor = Color.parseColor("#45250F");

		setNewCache.setBackgroundDrawable(buttonimage);
		setNewCache.setTypeface(font);
		setNewCache.setTextColor(buttontextcolor);
		setNewCache.setTextSize(textsize);

		refresh_button.setBackgroundDrawable(buttonimage);
		refresh_button.setTypeface(font);
		refresh_button.setTextColor(buttontextcolor);
		refresh_button.setTextSize(textsize);

		zoomin.setBackgroundDrawable(zoombuttonimage);
		zoomin.setTypeface(font);
		zoomin.setTextColor(buttontextcolor);
		zoomin.setTextSize(textsize);

		zoomout.setBackgroundDrawable(zoombuttonimage);
		zoomout.setTypeface(font);
		zoomout.setTextColor(buttontextcolor);
		zoomout.setTextSize(textsize);

		satelitenmode.setButtonDrawable(getResources().getDrawable(
				R.drawable.checkbox_satelitemode));

	}

	private void removeallOverlaysandaddnew() {

		for (int i = 0; i < mapOverlays.size(); i++)
			mapOverlays.remove(i);
		for (int i = 0; i < DataClass.selectedCaches.size(); i++) {
			// map points;

			int teamColour = DataClass.selectedCaches.get(i).getTeamcolour();
			int image;
			if (DataClass.selectedCaches.get(i).isFound()) {
				image = R.drawable.treasureopen;// point image;
			} else {
				switch (teamColour) {
				case 1:
					image = R.drawable.crossred;
					break;
				case 2:
					image = R.drawable.crossgreen;
					break;
				case 3:
					image = R.drawable.crossblue;
					break;
				case 4:
					image = R.drawable.crosspurple;
					break;
				default:
					image = R.drawable.treasureclosed;
					break;
				}
				
				
				
			}
			GeoPoint gp = DataClass.selectedCaches.get(i).getGeopoint();
			String name = DataClass.selectedCaches.get(i).getName();
			String description = DataClass.selectedCaches.get(i)
					.getDescripton();

			addOverlay(image, gp, name, description);
		}
		addOverlay(R.drawable.flagred, new GeoPoint(DataClass.mylat,
				DataClass.mylng), "Hi", "Here i am!");
		if (routeOverlay != null) {
			mapView.getOverlays().add(routeOverlay);
			mapView.invalidate();
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
		double calcdistance = (40075.017 / 256.0) * 110
				/ Math.pow(2, zoomLevel - 1);
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
		Drawable drawable = this.getResources().getDrawable(id_image);
		ItemOverlay itemizedoverlay = new ItemOverlay(drawable, this);
		OverlayItem overlayitem = new OverlayItem(where, topic, discription);

		itemizedoverlay.addOverlay(overlayitem);
		mapOverlays.add(itemizedoverlay);
	}

	@Override
	public void onLocationChanged(Location location) {
		DataClass.mylat = (int) (location.getLatitude() * Math.pow(10, 6));
		DataClass.mylng = (int) (location.getLongitude() * Math.pow(10, 6));
		DataClass.setMyGeoPoint();
		mc.animateTo(DataClass.getMyGeoPoint());
		removeallOverlaysandaddnew();
		check_If_I_Found_a_Cache();
	}

	private void check_If_I_Found_a_Cache() {
		for (int i = 0; i < DataClass.selectedCaches.size(); i++) {
			if (DataClass.selectedCaches.get(i).isIslessthanXXXm(20)
					&& !DataClass.selectedCaches.get(i).isFound()) {
				DataClass.selectedCaches.get(i).setfounded(true);
				playSound();
				Intent intent = new Intent(getApplicationContext(),
						CacheShowActivity.class);
				intent.putExtra("CacheIndex", i);
				intent.putExtra("maps", true);
				startActivity(intent);

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

	/* Request updates at startup */
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(provider, 500, 1, this);
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

	// //////////////////////////////////////////////////////////////////
	// //////////////////////////////////////////////////////////////////

	private Route directions(final GeoPoint start, final GeoPoint dest) {
		if (SegMathClass.calculateDistance1(start, dest) < 10000) {
			Parser parser;
			// https://developers.google.com/maps/documentation/directions/#JSON
			// <-
			// get api
			String jsonURL = "http://maps.googleapis.com/maps/api/directions/json?";
			final StringBuffer sBuf = new StringBuffer(jsonURL);
			sBuf.append("origin=");

			sBuf.append(((double) start.getLatitudeE6()) / 1E6);
			sBuf.append(",");
			sBuf.append(((double) start.getLongitudeE6()) / 1E6);
			sBuf.append("&destination=");
			sBuf.append(((double) dest.getLatitudeE6()) / 1E6);
			sBuf.append(",");
			sBuf.append(((double) dest.getLongitudeE6()) / 1E6);
			sBuf.append("&sensor=false&avoid=highways&mode=bicycling");
			Log.i("MAP", sBuf.toString());
			parser = new GoogleParser(sBuf.toString());
			Route r = parser.parse();
			if (r == null) {
				Toast.makeText(getApplicationContext(),
						"Sry no path between you and the Cache",
						Toast.LENGTH_LONG).show();
			}
			return r;
		} else {
			Toast.makeText(getApplicationContext(),
					"Get colser to the Cache to use this funktion",
					Toast.LENGTH_LONG).show();
			return null;
		}

	}

	private OnClickListener clickhandler = new OnClickListener() {

		@Override
		public void onClick(View v) {

			if (v == setNewCache) {
				finish();
				Intent intent = new Intent(getApplicationContext(),
						AddCacheActivity.class);

				startActivity(intent);

			}
			if (v == refresh_button) {
				removeallOverlaysandaddnew();
				GeoPoint routing = DataClass.routingpoint;
				if (routing != null) {
					Route route = directions(DataClass.getMyGeoPoint(), routing);
					if (route != null) {
						routeOverlay = new RouteOverlay(route, Color.BLUE);
						removeallOverlaysandaddnew();
					}

				} else {
					Toast.makeText(getApplicationContext(),
							"You haven't select a cach for Routing",
							Toast.LENGTH_SHORT).show();
				}

			}
			if (v == zoomin && mapView.getZoomLevel() < 17) {
				Log.i("MAP", mapView.getZoomLevel()+"asd");
				zoomout.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonsmall));
				mc.zoomIn();
				retextScale(mapView.getZoomLevel());
				if (mapView.getZoomLevel() == 17) {
					zoomin.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonsmallgrey));
					zoomin.setEnabled(false);
				}
			}
			if (v == zoomout) {
				mc.zoomOut();
				retextScale(mapView.getZoomLevel());
				zoomin.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonsmall));
				zoomin.setEnabled(true);
				Log.i("MAP", mapView.getZoomLevel()+"asd");
				if(mapView.getZoomLevel()==1){
					zoomout.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonsmallgrey));
				}
			}
			if (v == satelitenmode) {
				mapView.setSatellite(satelitenmode.isChecked());
			}
		}
	};
}