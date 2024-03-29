package com.vectorone;

import com.data.DataClass;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CacheShowActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_cachshow);
		Intent intent = getIntent();
		final int cacheindex = intent.getIntExtra("CacheIndex", -1);

		RelativeLayout relativlayout = (RelativeLayout) findViewById(R.id.cacheshowlayout);
		relativlayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		

		TextView CacheName = (TextView) findViewById(R.id.CachName);
		TextView CacheDiscription = (TextView) findViewById(R.id.CacheDiscription);
		
		CacheName.setText(DataClass.caches.get(cacheindex).getCach().getName());
		CacheDiscription.setText(DataClass.caches.get(cacheindex).getCach().getDescripton());
		
		Button yes_with_routing = (Button) findViewById(R.id.routing_button);
		
		yes_with_routing.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				DataClass.routing=cacheindex;
				DataClass.caches.get(cacheindex).setSelected(true);
				onBackPressed();
			}
		});
		
		
		Button yes_Button = (Button) findViewById(R.id.yes_button);
		yes_Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DataClass.caches.get(cacheindex).setSelected(true);
				onBackPressed();

			}
		});

		Button no_Button = (Button) findViewById(R.id.no_button);
		no_Button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DataClass.caches.get(cacheindex).setSelected(false);
				onBackPressed();
			}
		});

		if (cacheindex >= 0 && DataClass.caches.size() < cacheindex) {

		}

	}

}
