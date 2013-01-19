package com.outsourced;

import com.data.Cache;
import com.data.DataClass;
import com.data.GeoLocation;
import com.google.android.maps.GeoPoint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.view.View;

public class RadarTargetOverlay extends View {
	private double distanc;
	private double diffx;
	private double diffy;

	public RadarTargetOverlay(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(Color.RED);

		for (int i = 0; i < DataClass.selectedCaches.size(); i++) {
			GeoPoint gp = DataClass.selectedCaches.get(i).getGeopoint();
			calculate(gp);
			canvas.drawCircle((float) diffx+120, (float) diffy+120, 10, paint);
		}

		super.onDraw(canvas);
	}

	private void calculate(GeoPoint gp) {

		int lon = -1543000;
		int lat = 54782800;
		GeoPoint mygp = new GeoPoint(lat, lon);

		Location my = new Location("my");
		my.setLatitude(lat / 1E6);
		my.setLongitude(lon / 1E6);
		Location target = new Location("target");
		target.setLatitude(gp.getLatitudeE6() / 1E6);
		target.setLongitude(gp.getLongitudeE6() / 1E6);

		distanc = my.distanceTo(target);

		diffx = my.getLatitude() - gp.getLatitudeE6();
		diffy = my.getLongitude() - gp.getLongitudeE6();

	}

}
