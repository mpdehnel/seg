package com.vectorone;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.data.Cache;
import com.data.DataClass;
import com.data.Model;
import com.data.MyHttpClient;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddCacheActivity extends Activity {

	private EditText cacheNameText;
	private EditText cacheDiscriptionText;
	private Button add_button;
	private Button cancel_button;
	private RelativeLayout relativlayout;
	private TextView cacheNameView;
	private TextView CacheDiscriptionView;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_addcache);

		initfields();
		setupListener();
		setupbackgoundimages();
		setupfonts();

	}

	private void setupfonts() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#DECD87");
		int buttoncolor = Color.parseColor("#45250F");
		float textsize = 22;
		
		cacheNameText.setTypeface(font);
		cacheNameText.setTextColor(textcolor);
		cacheNameText.setTextSize(textsize);
		
		cacheDiscriptionText.setTypeface(font);
		cacheDiscriptionText.setTextColor(textcolor);
		cacheDiscriptionText.setTextSize(textsize);
		
		cacheNameView.setTypeface(font);
		cacheNameView.setTextColor(textcolor);
		cacheNameView.setTextSize(textsize);
		
		CacheDiscriptionView.setTypeface(font);
		CacheDiscriptionView.setTextColor(textcolor);
		CacheDiscriptionView.setTextSize(textsize);
		
		cancel_button.setTextColor(buttoncolor);
		cancel_button.setTypeface(font);		
		cancel_button.setTextSize(textsize);

		add_button.setTextColor(buttoncolor);
		add_button.setTypeface(font);	
		add_button.setTextSize(textsize);
	}

	private void setupbackgoundimages() {
		relativlayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonmedium);
		Drawable textfieldbackground = getResources().getDrawable(
				R.drawable.textentry);
		add_button.setBackgroundDrawable(buttonimage);
		cancel_button.setBackgroundDrawable(buttonimage);
		
		cacheNameText.setBackgroundDrawable(textfieldbackground);
		cacheDiscriptionText.setBackgroundDrawable(textfieldbackground);
		
		
	}

	private void setupListener() {
		add_button.setOnClickListener(clickhandler);
		cancel_button.setOnClickListener(clickhandler);
	}

	private void initfields() {

		cacheNameText = (EditText) findViewById(R.id.CacheNameText);
		cacheDiscriptionText = (EditText) findViewById(R.id.cacheDiscriptionText);
		relativlayout = (RelativeLayout) findViewById(R.id.addCache);
		add_button = (Button) findViewById(R.id.add_button);
		cancel_button = (Button) findViewById(R.id.cancel_button);
		CacheDiscriptionView =(TextView) findViewById(R.id.CacheDiscription);
		cacheNameView=(TextView) findViewById(R.id.CacheName);
	

	}

	private void clickhandle(View v) {
		if (v == add_button) {
			DataClass.caches
					.add(new Model(new Cache(cacheNameText.getText().toString(),
							new GeoPoint(DataClass.getMylat(), DataClass
									.getMylng()), cacheDiscriptionText.getText()
									.toString(), true)));
			MyHttpClient http = new MyHttpClient(DataClass.server);
			try {
				http.addCacheToDatabase(getApplicationContext(), DataClass
						.getMylng(), DataClass.getMylat(), cacheNameText.getText()
						.toString(), cacheDiscriptionText.getText().toString());
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (v == cancel_button) {
			onBackPressed();
		}
	}

	private OnClickListener clickhandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clickhandle(v);
		}

	};
}
