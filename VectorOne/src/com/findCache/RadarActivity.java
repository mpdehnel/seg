package com.findCache;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.data.Cache;
import com.data.CacheRadarCords;
import com.data.DataClass;
import com.data.Model;
import com.game.keepopen.Game_keepopen_Activity;
import com.game.memory.Game_memory_Activity;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.vectorone.AddCacheActivity;
import com.vectorone.CacheShowActivity;
import com.vectorone.MenuActivity;
import com.vectorone.PlaygroundActivity;
import com.vectorone.R;
import com.vectorone.R.id;
import com.vectorone.R.layout;

public class RadarActivity extends Activity implements LocationListener {

	private RelativeLayout preview;
	private Button ZoomIn;
	private Button ZoomOut;
	private Button addnewCache;
	private int zoomlvl = 1;
	private RadarActivity radar;
	private Vibrator vibrator;
	private String[][] distancelabels = { { "10", "20", "30", "40" },
			{ "50", "100", "150", "200" }, { "200", "400", "600", "800" },
			{ "500", "1000", "1500", "2000" },
			{ "1000", "2000", "3000", "4000" },
			{ "5000", "10000", "15000", "20000" } };

	private LinkedList<CacheRadarCords> cachecords = new LinkedList<CacheRadarCords>();
	private LocationManager locationManager;
	private String provider;
	private Location location;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_radar);
		this.radar = this;
		preview = (RelativeLayout) findViewById(R.id.radar_preview);
		preview.setBackgroundColor(Color.BLACK);
		preview.addView(new RadarBackgroudOverlay(getBaseContext(), 1,
				distancelabels));
		preview.addView(new RadarTargetOverlay(radar, getBaseContext(), 1,
				distancelabels));
		preview.setOnTouchListener(touchhandler);

		initButtons();
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		provider = LocationManager.GPS_PROVIDER;
		location = locationManager.getLastKnownLocation(provider);
		if (location != null) {
			onLocationChanged(location);
		} else {
			DataClass.mylng = (int) (54 * Math.pow(10, 6));
			DataClass.mylat = (int) (-1 * Math.pow(10, 6));
		}

	}

	private void initButtons() {
		ZoomIn = (Button) findViewById(R.id.radar_zoomin);
		ZoomOut = (Button) findViewById(R.id.radar_zoomout);
		addnewCache = (Button) findViewById(R.id.addnewCachRadar);
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int buttoncolor = Color.parseColor("#45250F");
		float textsize = 22;
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonsmall);

		ZoomIn.setTypeface(font);
		ZoomIn.setTextColor(buttoncolor);
		ZoomIn.setTextSize(textsize);
		ZoomIn.setBackgroundDrawable(buttonimage);

		ZoomOut.setTypeface(font);
		ZoomOut.setTextColor(buttoncolor);
		ZoomOut.setTextSize(textsize);
		ZoomOut.setBackgroundDrawable(buttonimage);

		addnewCache.setTypeface(font);
		addnewCache.setTextColor(buttoncolor);
		addnewCache.setTextSize(textsize);
		addnewCache.setBackgroundDrawable(buttonimage);

		ZoomIn.setOnClickListener(clickhandeler);
		ZoomOut.setOnClickListener(clickhandeler);
		addnewCache.setOnClickListener(clickhandeler);
	}

	public void addCachecords(CacheRadarCords cache) {
		cachecords.add(cache);
	}

	private OnClickListener clickhandeler = new OnClickListener() {

		@Override
		public void onClick(View v) {
			vibrator.vibrate(50);
			if (v == ZoomIn && zoomlvl < distancelabels.length) {
				zoomlvl++;
				cachecords = new LinkedList<CacheRadarCords>();
				preview.removeAllViews();
				preview.addView(ZoomIn);
				preview.addView(ZoomOut);
				preview.addView(new RadarTargetOverlay(radar, getBaseContext(),
						zoomlvl, distancelabels));
				preview.addView(new RadarBackgroudOverlay(getBaseContext(),
						zoomlvl, distancelabels));
			}
			if (v == ZoomOut && zoomlvl > 1) {
				zoomlvl--;
				cachecords = new LinkedList<CacheRadarCords>();
				preview.removeAllViews();
				preview.addView(ZoomIn);
				preview.addView(ZoomOut);
				preview.addView(new RadarTargetOverlay(radar, getBaseContext(),
						zoomlvl, distancelabels));
				preview.addView(new RadarBackgroudOverlay(getBaseContext(),
						zoomlvl, distancelabels));
			}
			if (v == addnewCache) {
				finish();
				Intent intent = new Intent(getApplicationContext(),
						AddCacheActivity.class);
				intent.putExtra("fromMaps", false);

				startActivity(intent);
			}

		}
	};
	private OnTouchListener touchhandler = new OnTouchListener() {

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			if (v == preview) {
				int x = (int) event.getX();
				int y = (int) event.getY();

				for (int i = 0; i < cachecords.size(); i++) {
					int distance = Math.abs(x - cachecords.get(i).x)
							+ Math.abs(y - cachecords.get(i).y);
					Log.i("RADAR", "b" + distance);
					if (distance < 10) {
						List<Model> filterlist = DataClass
								.getcacheswithfilter();
						for (int j = 0; j < filterlist.size(); j++) {
							Log.i("RADAR", cachecords.get(i).cache.getID()
									+ "--"
									+ filterlist.get(j).getCach().getID());
							if (cachecords.get(i).cache.getID() == filterlist
									.get(j).getCach().getID()) {
								vibrator.vibrate(50);
								Intent intent = new Intent(getBaseContext(),
										CacheShowActivity.class);
								intent.putExtra("CacheIndex", j);
								finish();
								startActivity(intent);
							}
						}
					}
				}

				Log.i("RADAR", event.getX() + "x---y" + event.getY());
			}
			return false;
		}
	};

	@Override
	public void onLocationChanged(Location location) {
		DataClass.mylat = (int) (location.getLatitude() * Math.pow(10, 6));
		DataClass.mylng = (int) (location.getLongitude() * Math.pow(10, 6));
		DataClass.setMyGeoPoint();
		preview.removeAllViews();
		preview.addView(ZoomIn);
		preview.addView(ZoomOut);
		preview.addView(new RadarTargetOverlay(radar, getBaseContext(),
				zoomlvl, distancelabels));
		preview.addView(new RadarBackgroudOverlay(getBaseContext(), zoomlvl,
				distancelabels));

		check_If_I_Found_a_Cache();
	}

	@Override
	public void onProviderDisabled(String provider) {

		Toast.makeText(this, "Disabled provider " + provider,
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onProviderEnabled(String provider) {
		Toast.makeText(this, "Enabled new provider " + provider,
				Toast.LENGTH_SHORT).show();

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	private void check_If_I_Found_a_Cache() {
		for (int i = 0; i < DataClass.selectedCaches.size(); i++) {
			Cache cache = DataClass.selectedCaches.get(i);

			if (cache.isIslessthanXXXm(20) && !cache.isFound()) {
				cache.setfounded(true);
				DataClass.log.append("Cache found:" + cache.getName());
				int choice = (int) (Math.random() * 2);
				if (choice == 0) {
					DataClass.log.append("Game: KeepOpen");
					Intent intent = new Intent(getApplicationContext(),
							Game_keepopen_Activity.class);
					startActivity(intent);
				} else {
					DataClass.log.append("Game: Memory");
					Intent intent = new Intent(getApplicationContext(),
							Game_memory_Activity.class);
					startActivity(intent);
				}

			}
		}
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
		vibrator.vibrate(50);
		finish();
		Intent intent = new Intent(getBaseContext(), MenuActivity.class);
		startActivity(intent);
	}
}
