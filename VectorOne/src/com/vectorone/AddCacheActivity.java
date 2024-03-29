package com.vectorone;

import com.data.Cache;
import com.data.DataClass;
import com.data.Model;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class AddCacheActivity extends Activity {

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_addcache);
		RelativeLayout relativlayout = (RelativeLayout) findViewById(R.id.addCache);
		relativlayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		
		final EditText cacheName = (EditText) findViewById(R.id.CacheNameText);
		final EditText cacheDiscription = (EditText) findViewById(R.id.cacheDiscriptionText);

		Button add_button = (Button) findViewById(R.id.add_button);

		add_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DataClass.caches.add(new Model(new Cache(cacheName.getText().toString(), new GeoPoint(DataClass.getMylat(), DataClass.getMylng()), cacheDiscription.getText().toString(),true)));
				onBackPressed();
			}
		});

		Button cancel_button = (Button) findViewById(R.id.cancel_button);

		cancel_button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

	}
}
