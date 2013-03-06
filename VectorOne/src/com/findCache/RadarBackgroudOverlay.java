package com.findCache;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class RadarBackgroudOverlay extends View {
	private String[][] distancelabels ;
	private int zoomlevel;

	public RadarBackgroudOverlay(Context context, int zoomlevel,String[][] distancelabels) {
		super(context);
		this.zoomlevel = zoomlevel;
		this.distancelabels=distancelabels;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		float midx = 160;
		float midy = 240;
		Paint paint = new Paint();
		paint.setStyle(Paint.Style.STROKE);
		paint.setColor(Color.GRAY);
		paint.setAntiAlias(true);
	
		canvas.drawCircle(midx, midy, 2, paint);
		canvas.drawCircle(midx, midy, 50, paint);

		canvas.drawText(distancelabels[distancelabels.length - zoomlevel][0],
				midx-10, midy - 50, paint);

		canvas.drawCircle(midx, midy, 100, paint);
		canvas.drawText(distancelabels[distancelabels.length - zoomlevel][1],
				midx-10, midy - 100, paint);
		canvas.drawCircle(midx, midy, 150, paint);
		canvas.drawText(distancelabels[distancelabels.length - zoomlevel][2],
				midx-10, midy - 150, paint);
		canvas.drawCircle(midx, midy, 200, paint);
		canvas.drawText(distancelabels[distancelabels.length - zoomlevel][3],
				midx-10, midy - 200, paint);
		super.onDraw(canvas);
	}
}
