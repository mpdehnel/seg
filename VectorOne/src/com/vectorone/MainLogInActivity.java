package com.vectorone;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.data.Cache;
import com.data.DataClass;
import com.data.Model;
import com.data.MyHttpClient;
import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.Toast;

public class MainLogInActivity extends Activity {

	private OnClickListener clickhandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			try {
				clickhandle(v);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	private Button loginbutton;
	private Button clearbutton;
	private Button registerbutton;
	private EditText usernameText;
	private MyHttpClient httpClient = new MyHttpClient(
			"http://www.netroware.co.uk/test/");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		new DataClass();
		setContentView(R.layout.activity_login);

		GridLayout gridlayout = (GridLayout) findViewById(R.id.login_layout);
		gridlayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));

		loginbutton = (Button) findViewById(R.id.loginbutton);
		clearbutton = (Button) findViewById(R.id.clearbutton);
		registerbutton = (Button) findViewById(R.id.registrationbutton);
		registerbutton.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.red_dot));

		loginbutton.setOnClickListener(clickhandler);
		clearbutton.setOnClickListener(clickhandler);
		registerbutton.setOnClickListener(clickhandler);
		usernameText = ((EditText) findViewById(R.id.UsernameTextField));
		usernameText.setBackgroundColor(Color.WHITE);
		usernameText.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.tets));

	}

	private void clickhandle(View v) throws IOException {
		Intent intent;

		if (v == loginbutton) {

			String username = usernameText.getText().toString();
			String password = ((EditText) findViewById(R.id.PasswordTextField))
					.getText().toString();
			Runnable getCachesThread = new Runnable() {
				
				@Override
				public void run() {
					try {
						DataClass.addCachesFromDataBase(httpClient
								.getCachesfromDatabase("test"));
						DataClass.caches.add(new Model(new Cache("Martins", new GeoPoint(54772993,-1576238), "thats his home", false)));
					} catch (ClientProtocolException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}					
				}
			};
			getCachesThread.run();
			
			

			if (/*httpClient.isUser(username, password)*/true) {
				
				finish();
				intent = new Intent(getApplicationContext(), MenuActivity.class);
				intent.putExtra("username", username);
				startActivity(intent);

			} else {
				Toast.makeText(this, "Unkowen User/Password combination",
						Toast.LENGTH_LONG).show();
			}

		}
		if (v == registerbutton) {
			Uri uri = Uri.parse("http://www.google.com");
			intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		}
		if (v == clearbutton) {
			// TODO:
		}

	}

	@Override
	public void onBackPressed() {
		finish();
		super.onBackPressed();
	}
	
}
