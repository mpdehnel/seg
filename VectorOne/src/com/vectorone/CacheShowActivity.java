package com.vectorone;

import java.io.IOException;

import com.data.DataClass;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
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

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_cachshow);
		Intent intent = getIntent();
		cacheindex = intent.getIntExtra("CacheIndex", -1);

		initfields();
		setupListener();
		setupfont();
		setupbackgroundimage();

		CacheName.setText(DataClass.caches.get(cacheindex).getCach().getName());
		CacheDiscription.setText(DataClass.caches.get(cacheindex).getCach()
				.getDescripton());

	}

	private void initfields() {
		relativlayout = (RelativeLayout) findViewById(R.id.cacheshowlayout);
		CacheName = (TextView) findViewById(R.id.CachName);
		CacheDiscription = (TextView) findViewById(R.id.CacheDiscription);
		yes_Button = (Button) findViewById(R.id.yes_button);
		yes_with_routing = (Button) findViewById(R.id.routing_button);
		no_Button = (Button) findViewById(R.id.no_button);
		question = (TextView) findViewById(R.id.doyouwant);

	}

	private void setupListener() {
		yes_with_routing.setOnClickListener(clickhandler);
		yes_Button.setOnClickListener(clickhandler);
		no_Button.setOnClickListener(clickhandler);
	}

	private void setupbackgroundimage() {
		relativlayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
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

		yes_with_routing.setVisibility(Button.INVISIBLE);

	}

	private void clickhandle(View v) {
		if (v == no_Button) {
			DataClass.caches.get(cacheindex).setSelected(false);
			startActivity(new Intent(getApplicationContext(),
					CacheSelectActivity.class));
		}
		if (v == yes_Button) {
			DataClass.caches.get(cacheindex).setSelected(true);
			startActivity(new Intent(getApplicationContext(),
					CacheSelectActivity.class));
		}
		if (v == yes_with_routing) {
			DataClass.routing = cacheindex;
			DataClass.caches.get(cacheindex).setSelected(true);
			startActivity(new Intent(getApplicationContext(),
					CacheSelectActivity.class));
		}
	}

	@Override
	public void onBackPressed() {
		startActivity(new Intent(getApplicationContext(),
				CacheSelectActivity.class));
		super.onBackPressed();
	}

	private OnClickListener clickhandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clickhandle(v);
		}

	};

}
