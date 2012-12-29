package com.vectorone;

import com.data.DataBaseConnector;
import com.data.LoginValidation;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainLogInActivity extends Activity {

	private OnClickListener clickhandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clickhandle(v);
		}
	};
	private DataBaseConnector dbcon;
	private Button loginbutton;
	private Button clearbutton;
	private Button registerbutton;
	private LoginValidation loginvalidator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		dbcon = new DataBaseConnector("IP", 8800);
		setContentView(R.layout.activity_login);

		loginbutton = (Button) findViewById(R.id.loginbutton);
		clearbutton = (Button) findViewById(R.id.clearbutton);
		registerbutton = (Button) findViewById(R.id.registrationbutton);

		loginbutton.setOnClickListener(clickhandler);
		clearbutton.setOnClickListener(clickhandler);
		registerbutton.setOnClickListener(clickhandler);

	}

	private void clickhandle(View v) {
		Intent intent;

		if (v == loginbutton) {
			String username = ((EditText) findViewById(R.id.UsernameTextField))
					.getText().toString();
			String password = ((EditText) findViewById(R.id.PasswordTextField))
					.getText().toString();

			if (dbcon.loginvalidate(username, password)) {

				intent = new Intent(getApplicationContext(), MenuActivity.class);
				intent.putExtra("username", username);
				startActivity(intent);
			} else {
				// TODO: ERROR wrong password//user
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
}