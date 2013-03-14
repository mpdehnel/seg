package com.vectorone;

import java.io.IOException;

import com.data.Cache;
import com.data.DataClass;
import com.data.DatabaseCacheHandler;
import com.data.DatabaseUserHandler;
import com.data.Model;
import com.data.MyHttpClient;
import com.data.User;
import com.findCache.RadarActivity;
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
	private Button help;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		// new DataClass();
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
		help.setOnClickListener(clickhandler);

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
		help = (Button) findViewById(R.id.tutorial);
		vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

	}

	private void setupbackgroundimage() {
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonsmall);

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
		help.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.buttonreallysmall));

	}

	private void setupfont() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#45250F");
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

		help.setTypeface(font);
		help.setTextSize(textsize);
		help.setTextColor(buttoncolor);

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
		//	Log.i("MAIN", "username:" + username + "---password:" + password);

			//Log.i("MAIN",
				//	"network"
					//		+ DataClass
						//			.haveNetworkConnection(getApplicationContext()));
			if (DataClass.haveNetworkConnection(getApplicationContext())) {
				try {
					if (!username.equals("") && !password.equals("")
							&& httpClient.isUser(username, password)) {
					//	Log.i("MAIN", "online");
						httpClient.getusersettings(username);
						httpClient.getportiondata();
						httpClient.getuserlog(username);
						httpClient.getVisitedCache(username);
						DataClass.addtolog(username + " logged in");

						DatabaseUserHandler dbuser = new DatabaseUserHandler(
								getApplicationContext());
						DatabaseCacheHandler dbhandler = new DatabaseCacheHandler(
								getApplicationContext());
						handdeldatabase(dbhandler);
						dbuser.remove();
						dbuser.addUserInfo(DataClass.user);

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
				//	Toast.makeText(getApplicationContext(),
					//		"ee" + e.getMessage(), Toast.LENGTH_LONG).show();
					//Toast.makeText(getApplicationContext(),
						//	"Connection Problems Login", Toast.LENGTH_LONG)
							//.show();

				}
			} else {
				DatabaseUserHandler dbuser = new DatabaseUserHandler(
						getApplicationContext());
				dbuser.getUser();
				if (username.equals(DataClass.user.getUsername())
						&& password.equals(DataClass.user.getPassword())) {
					Toast.makeText(getApplicationContext(),
							"No internet =>Offlinemode", Toast.LENGTH_LONG)
							.show();
					finish();
					intent = new Intent(getApplicationContext(),
							MenuActivity.class);
					intent.putExtra("username", username);
					startActivity(intent);
				} else {
					Toast.makeText(
							getApplicationContext(),
							"No internet and Unkown User/Password combination  ",
							Toast.LENGTH_LONG).show();
				}
			}

		}
		if (v == registerbutton) {
			finish();
			intent = new Intent(getApplicationContext(),
					AddNewUserActivity.class);
			startActivity(intent);
		}
		if (v == clearbutton) {
			usernameText.setText("");
			passwordText.setText("");

		}
		if (v == playgound) {
			finish();
			intent = new Intent(getApplicationContext(),
					PlaygroundActivity.class);
			startActivity(intent);
		}
		if (v == help) {
			Toast.makeText(
					getBaseContext(),
					"Please have a look on our website for a Tutorial \n http://www.cachecaptains.co.uk/tutorial.pdf",
					Toast.LENGTH_LONG * 2).show();
			/*
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.cachecaptains.co.uk/tutorial.pdf""));
			startActivity(browserIntent);*/
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
	}

	private void handdeldatabase(DatabaseCacheHandler dbhandler) {
		dbhandler.remove();
		dbhandler = new DatabaseCacheHandler(getApplicationContext());
		for (int i = 0; i < DataClass.caches.size(); i++) {
			dbhandler.addCache(DataClass.caches.get(i).getCach());
		}
	}

}
