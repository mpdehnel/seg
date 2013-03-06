package com.vectorone;

import java.io.IOException;

import org.apache.http.client.HttpClient;

import com.data.DataClass;
import com.data.MyHttpClient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

public class DrawableStatusbar extends View {

	public DrawableStatusbar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void onDraw(Canvas canvas) {
		
		int greenportion = DataClass.greenportion;
		int redportion = DataClass.redportion+20;
		int purpleportion = DataClass.purpleportion;
		int blueportion = DataClass.blueportion;
		int red = Color.parseColor("#930000");
		int blue = Color.parseColor("#338000");
		int green = Color.parseColor("#006680");
		int purple = Color.parseColor("#67215C");
		int hight=36;

		Paint paint = new Paint();
		paint.setStyle(Paint.Style.FILL);
		paint.setColor(red);
		canvas.drawRect(18, 10, redportion, hight, paint);
		paint.setColor(blue);
		canvas.drawRect(redportion, 10, redportion + blueportion, hight, paint);
		paint.setColor(green);
		canvas.drawRect(redportion + blueportion, 10, redportion + blueportion
				+ greenportion, hight, paint);
		paint.setColor(purple);
		canvas.drawRect(redportion + blueportion + greenportion, 10, 300, hight,
				paint);

		super.onDraw(canvas);

	}
}