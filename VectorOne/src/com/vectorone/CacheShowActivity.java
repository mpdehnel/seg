package com.vectorone;

import java.io.IOException;

import com.data.DataClass;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CacheShowActivity extends Activity {

	private RelativeLayout relativlayout;
	private TextView CacheName;
	private TextView CacheDiscription;
	private TextView question;
	private Button yes_with_routing;
	private Button yes_Button;
	private Button no_Button;
	private int cacheindex;
	private boolean frommap;
	private Button rate_button;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_cachshow);
		Intent intent = getIntent();

		// TODO: mapsboolen handel
		cacheindex = intent.getIntExtra("CacheIndex", -1);
		frommap = intent.getBooleanExtra("frommaps", false);

		initfields();
		handelfrommap();
		setupListener();
		setupfont();
		setupbackgroundimage();

		CacheName.setText(DataClass.caches.get(cacheindex).getCach().getName());
		CacheDiscription.setText(DataClass.caches.get(cacheindex).getCach()
				.getDescripton());

	}

	private void handelfrommap() {
		if (frommap) {
			no_Button.setVisibility(Button.INVISIBLE);
			yes_Button.setText("Back to Map");
			question.setVisibility(TextView.INVISIBLE);
			yes_with_routing.setVisibility(Button.INVISIBLE);
		} else {
			no_Button.setVisibility(Button.VISIBLE);
			question.setVisibility(TextView.VISIBLE);
			yes_with_routing.setVisibility(Button.VISIBLE);
			yes_Button.setText("yes");
			yes_with_routing.setText("Route");
		}
	}

	private void initfields() {
		relativlayout = (RelativeLayout) findViewById(R.id.cacheshowlayout);
		CacheName = (TextView) findViewById(R.id.CachName);
		CacheDiscription = (TextView) findViewById(R.id.CacheDiscription);
		yes_Button = (Button) findViewById(R.id.yes_button);
		yes_with_routing = (Button) findViewById(R.id.routing_button);
		no_Button = (Button) findViewById(R.id.no_button);
		rate_button = (Button) findViewById(R.id.ratethisCache);
		question = (TextView) findViewById(R.id.doyouwant);

	}

	private void setupListener() {
		yes_with_routing.setOnClickListener(clickhandler);
		yes_Button.setOnClickListener(clickhandler);
		no_Button.setOnClickListener(clickhandler);
		rate_button.setOnClickListener(clickhandler);
	}

	private void setupbackgroundimage() {
		relativlayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonsmall);
		Drawable buttonimageinaktiv = getResources().getDrawable(
				R.drawable.buttonmediumgrey);

		yes_Button.setBackgroundDrawable(buttonimage);
		yes_with_routing.setBackgroundDrawable(buttonimage);
		no_Button.setBackgroundDrawable(buttonimage);
		if (DataClass.caches.get(cacheindex).getCach().isFound()) {
			rate_button.setBackgroundDrawable(buttonimage);
		} else {
			rate_button.setBackgroundDrawable(buttonimageinaktiv);
		}
	}

	private void setupfont() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#DECD87");
		int buttoncolor = Color.parseColor("#45250F");
		float textsize = 22;
		CacheName.setTypeface(font);
		CacheName.setTextColor(textcolor);

		CacheDiscription.setTypeface(font);
		CacheDiscription.setTextColor(textcolor);

		question.setTypeface(font);
		question.setTextColor(textcolor);

		yes_Button.setTypeface(font);
		yes_Button.setTextSize(textsize);
		yes_Button.setTextColor(buttoncolor);

		no_Button.setTypeface(font);
		no_Button.setTextSize(textsize);
		no_Button.setTextColor(buttoncolor);

		rate_button.setTypeface(font);
		rate_button.setTextSize(textsize);
		rate_button.setTextColor(buttoncolor);

		yes_with_routing.setTypeface(font);
		yes_with_routing.setTextSize(textsize);
		yes_with_routing.setTextColor(buttoncolor);

		// yes_with_routing.setVisibility(Button.INVISIBLE);

	}

	private void clickhandle(View v) {
		if (v == no_Button) {

			finish();
			DataClass.caches.get(cacheindex).setSelected(false);
			startActivity(new Intent(getApplicationContext(),
					CacheSelectActivity.class));

		}
		if (v == yes_Button) {
			if (frommap) {
				finish();
				startActivity(new Intent(getApplicationContext(),
						MapsActivity.class));

			} else {
				finish();
				DataClass.caches.get(cacheindex).setSelected(true);
				startActivity(new Intent(getApplicationContext(),
						CacheSelectActivity.class));
			}
		}
		if (v == yes_with_routing) {
			finish();
			DataClass.caches.get(cacheindex).setSelected(true);
			DataClass.routingpoint = DataClass.caches.get(cacheindex).getCach()
					.getGeopoint();
			startActivity(new Intent(getApplicationContext(),
					CacheSelectActivity.class));
		}
	}

	@Override
	public void onBackPressed() {
		if (!frommap) {
			finish();
			startActivity(new Intent(getApplicationContext(),
					CacheSelectActivity.class));
		} else {
			finish();
			startActivity(new Intent(getApplicationContext(),
					MapsActivity.class));
		}
		super.onBackPressed();
	}

	private OnClickListener clickhandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clickhandle(v);
		}

	};

}
