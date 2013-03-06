package com.vectorone;


import com.data.DataClass;
import com.data.MyHttpClient;
import com.data.Registrationsverify;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewUserActivity extends Activity {

	private LinearLayout layout;
	private TextView[] labels = new TextView[7];
	private EditText[] entrys = new EditText[6];
	private Button createButton;
	private Button cancelButton;
	private final int username = 0;
	private final int email = 1;
	private final int postcode = 2;
	private final int date = 3;
	private final int password = 4;
	private final int passwordconfirm = 5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_addnewuser);
		initdatafields();
		setupListener();
		setupfont();
		setupbackgroundimage();
	}

	private void setupbackgroundimage() {
		layout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonmedium);
		Drawable textfieldimage = getResources().getDrawable(
				R.drawable.textentry);
		createButton.setBackgroundDrawable(buttonimage);
		cancelButton.setBackgroundDrawable(buttonimage);
		for (int i = 0; i < entrys.length; i++) {
			entrys[i].setBackgroundDrawable(textfieldimage);
		}

	}

	private void setupfont() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#DECD87");
		int buttoncolor = Color.parseColor("#45250F");
		float textsize = 22;
		for (int i = 0; i < entrys.length - 2; i++) {
			entrys[i].setTypeface(font);
			entrys[i].setTextSize(textsize);
			entrys[i].setTextColor(textcolor);
		}
		entrys[4].setTextSize(textsize);
		entrys[5].setTextSize(textsize);
		for (int i = 0; i < labels.length; i++) {
			labels[i].setTypeface(font);
			labels[i].setTextSize(textsize);
			labels[i].setTextColor(textcolor);
		}

		cancelButton.setTextColor(buttoncolor);
		cancelButton.setTypeface(font);
		cancelButton.setTextSize(textsize);
		createButton.setTextColor(buttoncolor);
		createButton.setTypeface(font);
		createButton.setTextSize(textsize);

	}

	private void setupListener() {
		cancelButton.setOnClickListener(clickhandler);
		createButton.setOnClickListener(clickhandler);
	}

	private void initdatafields() {
		layout = (LinearLayout) findViewById(R.id.AddNewUser);
		labels[0] = (TextView) findViewById(R.id.UsernameLabel);
		labels[1] = (TextView) findViewById(R.id.passwordAddNewUser);
		labels[2] = (TextView) findViewById(R.id.PasswordConfirm);
		labels[3] = (TextView) findViewById(R.id.Email);
		labels[4] = (TextView) findViewById(R.id.PostCode);
		labels[5] = (TextView) findViewById(R.id.DateOfBirth);
		labels[6] = (TextView) findViewById(R.id.format);
		createButton = (Button) findViewById(R.id.CreateUser);
		cancelButton = (Button) findViewById(R.id.Cancel);

		entrys[0] = (EditText) findViewById(R.id.UsernameInput);
		entrys[1] = (EditText) findViewById(R.id.EmailInput);
		entrys[2] = (EditText) findViewById(R.id.PostCodeInput);
		entrys[3] = (EditText) findViewById(R.id.Date);
		entrys[4] = (EditText) findViewById(R.id.PasswordInput);
		entrys[5] = (EditText) findViewById(R.id.PasswordInputConfirm);

	}

	private OnClickListener clickhandler = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent;
			Registrationsverify validator = new Registrationsverify();
			if (v == cancelButton) {
				intent = new Intent(getApplicationContext(),
						MainLogInActivity.class);
				startActivity(intent);
			}
			if (v == createButton) {
				boolean properuser = true;
				if (!validator
						.isProperEmail(entrys[email].getText().toString())) {
					Toast.makeText(getApplicationContext(), "No proper Email",
							Toast.LENGTH_SHORT).show();
					properuser = false;
				}
				if (!validator.checkPasswordsAreEqual(entrys[password]
						.getText().toString(), entrys[passwordconfirm]
						.getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"Passwords are not equal", Toast.LENGTH_SHORT)
							.show();
					properuser = false;
				}
				if (!validator.isProperDate(entrys[date].getText().toString())) {
					Toast.makeText(getApplicationContext(),
							"wrong date format", Toast.LENGTH_SHORT).show();
					properuser = false;
				}
				if (!validator.isProperPostCode(entrys[postcode].getText()
						.toString())) {
					Toast.makeText(getApplicationContext(), "wrong postcode",
							Toast.LENGTH_SHORT).show();
					properuser = false;

				}
				if (properuser) {
					Toast.makeText(getApplicationContext(),
							"User Created-Check Emails for Verifiy",
							Toast.LENGTH_LONG).show();
					intent = new Intent(getApplicationContext(),
							MainLogInActivity.class);
					try{
					MyHttpClient client = new MyHttpClient(DataClass.server);
					Toast.makeText(getApplicationContext(),
							client.addNewUser(calculateHTTPrequest()),
							Toast.LENGTH_LONG).show();
					}catch (Exception e) {
						Toast.makeText(getBaseContext(), "Connection Error", Toast.LENGTH_SHORT).show();
					}
				}
			}
		}

		private String calculateHTTPrequest() {
			StringBuilder request = new StringBuilder();
			request.append("createuser.php?username=");
			request.append(entrys[username]);
			request.append("&password=");
			request.append(entrys[password]);
			request.append("&email=");
			request.append(entrys[email]);
			request.append("&dateofbrith=");
			request.append(entrys[date]);
			request.append("&postcode=");
			request.append(entrys[postcode]);

			return request.toString();
		}
	};
}
