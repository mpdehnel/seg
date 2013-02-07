package com.vectorone;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.data.Cache;
import com.data.DataClass;
import com.data.Model;
import com.data.MyHttpClient;
import com.google.android.maps.GeoPoint;
import com.vectorone.R.drawable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
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
	private RelativeLayout relativlayout;

	private MyHttpClient httpClient = new MyHttpClient(DataClass.server);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
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
	}
		

	private void clickhandle(View v) throws IOException {
		Intent intent;

		if (v == loginbutton) {

			String username = usernameText.getText().toString();
			String password = ((EditText) findViewById(R.id.PasswordTextField))
					.getText().toString();

			if (/* httpClient.isUser(username, password) */true) {

				 Toast.makeText(this, "Connected to the database!",Toast.LENGTH_LONG).show();

				 DataClass.addCachesFromDataBase(httpClient
							.getCachesfromDatabase("test"));
				DataClass.caches.add(new Model(new Cache("DurhamUniversitaet",
						new GeoPoint(54767542, -1571993), //new GeoPoint(
								//54767442, -1570993), 
								"thats CLC Main Entry",
						false)));
				finish();
				intent = new Intent(getApplicationContext(), MenuActivity.class);
				intent.putExtra("username", username);
				startActivity(intent);

			} else {
				Toast.makeText(this, "Unkown User/Password combination",
						Toast.LENGTH_LONG).show();
			}

		}
		if (v == registerbutton) {
			Uri uri = Uri.parse("http://www.google.com");
			intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
		if (v == clearbutton) {
			Toast.makeText(this, "ToDo:;)", Toast.LENGTH_LONG).show();

		}

	}

	private OnClickListener clickhandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			try {
				clickhandle(v);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	};

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}

}
