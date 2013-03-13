package com.vectorone;

import com.data.DataClass;
import com.data.DatabaseCacheHandler;
import com.data.MyHttpClient;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Window;
import android.widget.VideoView;

public class IntroActivity extends Activity {

	private MyHttpClient httpClient = new MyHttpClient(DataClass.server);
	private static final int SPLASH_DISPLAY_TIME = 5000; // splash screen delay

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.intro);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		new DataClass();
		
		// PlayMovie();

		DatabaseCacheHandler dbhandler = new DatabaseCacheHandler(
				getApplicationContext());

		// //////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////
		Log.i("MAIN", "network"+DataClass.haveNetworkConnection(this));
		if (DataClass.haveNetworkConnection(this)) {
			try {
				Log.i("HTTPCLIENTUSER", " have to pull from database Online");
				DataClass.addCachesFromDataBase(httpClient
						.getCachesfromDatabase("test"));
				handdeldatabase(dbhandler);
				Log.i("HTTPCLIENTUSER", " pull from database Online");

			} catch (Exception e) {
				Log.i("MAIN", "Caches error pull from database");
				dbhandler.getAllCache();
				e.printStackTrace();
			}
		} else {
			Log.i("MAIN", "Caches error pull from database");
			dbhandler.getAllCache();
		}

		// //////////////////////////////////////////////////////////
		// //////////////////////////////////////////////////////////

		new Handler().postDelayed(new Runnable() {
			public void run() {
				
				Intent intent = new Intent();
				intent.setClass(IntroActivity.this, MainLogInActivity.class);

				IntroActivity.this.startActivity(intent);
				IntroActivity.this.finish();

				// transition from splash to main menu
				overridePendingTransition(R.anim.activityfadein,
						R.anim.splashfadeout);

			}
		}, SPLASH_DISPLAY_TIME);
	}

	private void handdeldatabase(DatabaseCacheHandler dbhandler) {
		dbhandler.remove();
		dbhandler = new DatabaseCacheHandler(getApplicationContext());
		for (int i = 0; i < DataClass.caches.size(); i++) {
			dbhandler.addCache(DataClass.caches.get(i).getCach());
		}
	}

	private void PlayMovie() {
		VideoView myVideoView = (VideoView) findViewById(R.id.intro);
		String urlpaht = "android.resource://" + getPackageName() + "/"
				+ R.raw.segintro;

		myVideoView.setVideoURI(Uri.parse(urlpaht));
		myVideoView.setClickable(false);
		myVideoView.requestFocus();
		myVideoView.start();
	}
}
