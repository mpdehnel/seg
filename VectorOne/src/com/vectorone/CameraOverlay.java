package com.vectorone;

import com.data.DataClass;
import com.google.android.maps.GeoPoint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CameraOverlay extends View {
	private int x;
	private int y;

	public CameraOverlay(Context context) {
		super(context);

	}

	@Override
	protected void onDraw(Canvas canvas) {
		for (int i = 0; i < DataClass.caches.size(); i++) {
			// map points;
			int teamColour = DataClass.caches.get(i).getTeamcolour();
			GeoPoint gp = DataClass.caches.get(i).getGeopoint();
			String name = DataClass.caches.get(i).getName();
			String description = DataClass.caches.get(i).getDescripton();
			// TODO: get my position calculate place for overlay;

			Paint paint = new Paint();
			paint.setStyle(Paint.Style.FILL);
			paint.setColor(Color.RED);
			// for loop for the targets

			canvas.drawCircle(40, 40, 20, paint);

		}
		super.onDraw(canvas);

	}

}