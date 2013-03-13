package com.findCache;

import java.util.List;

import com.data.CacheRadarCords;
import com.data.DataClass;
import com.data.Model;
import com.data.SegMathClass;
import com.google.android.maps.GeoPoint;
import com.vectorone.R;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class RadarTargetOverlay extends View {
	private double distanc;
	private double degree;
	private double diffx;
	private double diffy;
	private float midx = 160;
	private float midy = 240;
	private String[][] distancelabels;
	private int zoomlevel;
	private RadarActivity radar;

	public RadarTargetOverlay(RadarActivity radar, Context context,
			int zoomlevel, String[][] distancelabels) {
		super(context);
		this.zoomlevel = zoomlevel;
		this.radar = radar;
		this.distancelabels = distancelabels;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setAntiAlias(true);
		Resources res = getResources();
		for (int i = 0; i < DataClass.selectedCaches.size(); i++) {
			int image;
			if (DataClass.selectedCaches.get(i).isFound()) {
				image = R.drawable.treasureopen;// point image;
			} else {
				String teamColour = DataClass.selectedCaches.get(i)
						.getTeamcolour();
				if (teamColour.equals("Red Reefers")) {
					image = R.drawable.crossred;
				} else if (teamColour.equals("Green Gulls")) {
					image = R.drawable.crossgreen;
				} else if (teamColour.equals("Blue Bandits")) {
					image = R.drawable.crossblue;
				} else if (teamColour.equals("Purple Perils")) {
					image = R.drawable.crosspurple;
				} else {
					image = R.drawable.treasureclosed;
				}

			}
			Bitmap bitmap = BitmapFactory.decodeResource(res, image);

			GeoPoint gp = DataClass.selectedCaches.get(i).getGeopoint();
			calculate(gp);
			radar.addCachecords(new CacheRadarCords(DataClass.selectedCaches
					.get(i), (int) (diffx + midx), (int) (diffy + midy)));
			canvas.drawBitmap(bitmap, (float) diffx + midx - 12, (float) diffy
					- 12 + midy, paint);
		}

		super.onDraw(canvas);
	}

	private void calculate(GeoPoint gp) {

		GeoPoint mygp = DataClass.myGeoPoint;
		distanc = SegMathClass.calculateDistanceinMeter(mygp, gp);
		degree = SegMathClass.calculateDirectionDegree(mygp, gp) - 90;
		// Log.i("RADAR", "degree="+degree);
		// Log.i("RADAR",
		// "Scale="+Integer.parseInt(distancelabels[distancelabels.length-zoomlevel][3]));
		diffx = 200
				* distanc
				* Math.cos(degree / 360 * 2 * Math.PI)
				/ Integer.parseInt(distancelabels[distancelabels.length
						- zoomlevel][3]);
		diffy = 200
				* distanc
				* Math.sin(degree / 360 * 2 * Math.PI)
				/ Integer.parseInt(distancelabels[distancelabels.length
						- zoomlevel][3]);
		// Log.i("RADAR", "x="+diffx);
		// Log.i("RADAR", "y="+diffy);
		// Log.i("RADAR", "distance="+distanc );

	}

}
