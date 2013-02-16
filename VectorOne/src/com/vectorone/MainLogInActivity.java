package com.vectorone;

import com.data.Cache;
import com.data.DataClass;
import com.data.DatabaseCacheHandler;
import com.data.Model;
import com.data.MyHttpClient;
import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainLogInActivity extends Activity {

	private Button loginbutton;
	private Button clearbutton;
	private Button registerbutton;
	private EditText usernameText;
	private EditText passwordText;
	private TextView usernamelabel;
	private TextView passwordlabel;
	private Vibrator vibrate;

	private RelativeLayout relativlayout;

	private MyHttpClient httpClient = new MyHttpClient(DataClass.server);
	private Button playgound;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		new DataClass();
		setContentView(R.layout.activity_login);

		initdatafields();
		setupListener();
		setupfont();
		setupbackgroundimage();
	}

	private void setupListener() {
		loginbutton.setOnClickListener(clickhandler);
		clearbutton.setOnClickListener(clickhandler);
		registerbutton.setOnClickListener(clickhandler);
		playgound.setOnClickListener(clickhandler);

	}

	private void initdatafields() {
		loginbutton = (Button) findViewById(R.id.loginbutton);
		clearbutton = (Button) findViewById(R.id.clearbutton);
		registerbutton = (Button) findViewById(R.id.registrationbutton);
		usernameText = (EditText) findViewById(R.id.UsernameTextField);
		passwordText = (EditText) findViewById(R.id.PasswordTextField);
		usernamelabel = (TextView) findViewById(R.id.userlabel);
		passwordlabel = (TextView) findViewById(R.id.passwordlabel);
		relativlayout = (RelativeLayout) findViewById(R.id.login_layout);
		playgound = (Button) findViewById(R.id.playground);
		vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
	}

	private void setupbackgroundimage() {
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonmedium);

		Drawable textfieldbackground = getResources().getDrawable(
				R.drawable.textentry);
		relativlayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		usernameText.setBackgroundDrawable(textfieldbackground);
		passwordText.setBackgroundDrawable(textfieldbackground);

		loginbutton.setBackgroundDrawable(buttonimage);
		clearbutton.setBackgroundDrawable(buttonimage);
		registerbutton.setBackgroundDrawable(buttonimage);
		playgound.setBackgroundDrawable(buttonimage);

	}

	private void setupfont() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#DECD87");
		int buttoncolor = Color.parseColor("#45250F");
		float textsize = 22;
		usernameText.setTypeface(font);
		usernameText.setTextColor(textcolor);

		loginbutton.setTypeface(font);
		loginbutton.setTextSize(textsize);
		loginbutton.setTextColor(buttoncolor);

		clearbutton.setTypeface(font);
		clearbutton.setTextSize(textsize);
		clearbutton.setTextColor(buttoncolor);

		registerbutton.setTypeface(font);
		registerbutton.setTextSize(textsize);
		registerbutton.setTextColor(buttoncolor);
		usernamelabel.setTextColor(textcolor);
		usernamelabel.setTextSize(textsize);
		usernamelabel.setTypeface(font);

		passwordlabel.setTextColor(textcolor);
		passwordlabel.setTextSize(textsize);
		passwordlabel.setTypeface(font);
		
		playgound.setTextColor(buttoncolor);
		playgound.setTypeface(font);
		playgound.setTextSize(textsize);
	}

	private void clickhandle(View v) {
		Intent intent;
		vibrate.vibrate(50);
		if (v == loginbutton) {
			String username = usernameText.getText().toString();
			String password = ((EditText) findViewById(R.id.PasswordTextField))
					.getText().toString();
			Log.i("MAIN", "username:" + username + "---password:" + password);

			username="martin";
			password="test";
			try {
				if (!username.equals("") && !password.equals("")
						&& httpClient.isUser(username, password)) {

					Toast.makeText(getApplicationContext(),
							"Connected to the database!", Toast.LENGTH_LONG)
							.show();
					DataClass.addtolog(username + " logged in");
					DatabaseCacheHandler dbhandler = new DatabaseCacheHandler(
							getApplicationContext());
					try {
						DataClass.addCachesFromDataBase(httpClient
								.getCachesfromDatabase("test"));
						dbhandler.remove();
						dbhandler = new DatabaseCacheHandler(
								getApplicationContext());
						for (int i = 0; i < DataClass.caches.size(); i++) {
							dbhandler.addCache(DataClass.caches.get(i)
									.getCach());
						}

					} catch (Exception e) {

						dbhandler.getAllCache();

					}
					DataClass.caches.add(new Model(new Cache(
							"DurhamUniversitaet", new GeoPoint(54767542,
									-1571993), // new GeoPoint(
							// 54767442, -1570993),
							"thats CLC Main Entry", false, 666)));
					finish();
					intent = new Intent(getApplicationContext(),
							MenuActivity.class);
					intent.putExtra("username", username);
					startActivity(intent);

				} else {
					Toast.makeText(getApplicationContext(),
							"Unkown User/Password combination",
							Toast.LENGTH_LONG).show();
				}
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), "No internet ",
						Toast.LENGTH_LONG).show();
			}

		}
		if (v == registerbutton) {
			Uri uri = Uri.parse("http://www.google.com");
			intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
		if (v == clearbutton) {
			finish();
			intent = new Intent(getApplicationContext(),
					MapsActivity.class);
			startActivity(intent);
			
			//usernameText.setText("");
			//passwordText.setText("");
			
		}
		if(v==playgound){
			intent = new Intent(getApplicationContext(),PlaygroundActivity.class);
			startActivity(intent);
		}

	}

	private OnClickListener clickhandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clickhandle(v);

		}
	};

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

}
