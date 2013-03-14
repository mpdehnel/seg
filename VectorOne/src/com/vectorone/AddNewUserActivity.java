package com.vectorone;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.data.DataClass;
import com.data.DatabaseUserHandler;
import com.data.MyHttpClient;
import com.data.Registrationsverify;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.Settings.System;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AddNewUserActivity extends Activity {

	private LinearLayout layout;
	private TextView[] labels = new TextView[8];
	private EditText[] entrys = new EditText[6];
	private Button createButton;
	private Button cancelButton;
	private CheckBox hardcore;
	private final int username = 0;
	private final int email = 1;
	private final int postcode = 2;
	private final int date = 3;
	private final int password = 4;
	private final int passwordconfirm = 5;
	private boolean change;
	private Vibrator vibrator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_addnewuser);
		Intent intent = getIntent();
		change = intent.getBooleanExtra("change", false);
		initdatafields();
		setupListener();
		setupfont();
		setupbackgroundimage();
	}

	private void setupbackgroundimage() {
		layout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));

		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonsmall);
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
		int textcolor = Color.parseColor("#45250F");
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
		if (change) {
			createButton.setText("Change");
			labels[5].setVisibility(Button.INVISIBLE);
			labels[3].setVisibility(Button.INVISIBLE);
			labels[4].setVisibility(Button.INVISIBLE);
			labels[5].setVisibility(Button.INVISIBLE);
			labels[6].setVisibility(Button.INVISIBLE);
			labels[7].setVisibility(Button.INVISIBLE);
			hardcore.setVisibility(CheckBox.INVISIBLE);
			entrys[3].setVisibility(EditText.INVISIBLE);
			entrys[1].setVisibility(EditText.INVISIBLE);
			entrys[2].setVisibility(EditText.INVISIBLE);
			entrys[3].setVisibility(EditText.INVISIBLE);
			entrys[0].setText(DataClass.user.getUsername());
			entrys[0].setEnabled(false);
			entrys[4].setText(DataClass.user.getPassword());
			entrys[5].setText(DataClass.user.getPassword());

		} else {
			createButton.setText("Create");
			labels[5].setVisibility(Button.VISIBLE);
			labels[3].setVisibility(Button.VISIBLE);
			labels[4].setVisibility(Button.VISIBLE);
			labels[5].setVisibility(Button.VISIBLE);
			labels[6].setVisibility(Button.VISIBLE);
			labels[7].setVisibility(Button.VISIBLE);
			hardcore.setVisibility(CheckBox.VISIBLE);
			entrys[0].setEnabled(true);
			entrys[3].setVisibility(EditText.VISIBLE);
			entrys[1].setVisibility(EditText.VISIBLE);
			entrys[2].setVisibility(EditText.VISIBLE);
			entrys[3].setVisibility(EditText.VISIBLE);
		}

	}

	private void setupListener() {
		cancelButton.setOnClickListener(clickhandler);
		createButton.setOnClickListener(clickhandler);
		hardcore.setOnCheckedChangeListener(cheacklister);

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
		labels[7] = (TextView) findViewById(R.id.Dificulty);
		createButton = (Button) findViewById(R.id.CreateUser);
		cancelButton = (Button) findViewById(R.id.Cancel);
		hardcore = (CheckBox) findViewById(R.id.hardcoreMode);
		hardcore.setButtonDrawable(R.drawable.checkbox_hardcore);
		entrys[0] = (EditText) findViewById(R.id.UsernameInput);
		entrys[1] = (EditText) findViewById(R.id.EmailInput);
		entrys[2] = (EditText) findViewById(R.id.PostCodeInput);
		entrys[3] = (EditText) findViewById(R.id.Date);
		entrys[4] = (EditText) findViewById(R.id.PasswordInput);
		entrys[5] = (EditText) findViewById(R.id.PasswordInputConfirm);
		vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

	}

	private OnClickListener clickhandler = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent;
			vibrator.vibrate(50);
			Registrationsverify validator = new Registrationsverify();
			if (v == cancelButton) {
				intent = new Intent(getApplicationContext(),
						MainLogInActivity.class);
				startActivity(intent);
			}
			/*
			 * if(v==hardcore){ hardcore.setChecked(!hardcore.isChecked());
			 * 
			 * }
			 */

			if (v == createButton) {
				MyHttpClient client = new MyHttpClient(DataClass.server);
				if (!change) {
					boolean properuser = true;
					if (!validator.isProperEmail(entrys[email].getText()
							.toString())) {
						Toast.makeText(getApplicationContext(),
								"No proper Email", Toast.LENGTH_SHORT).show();
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
					if (!validator.isProperDate(entrys[date].getText()
							.toString())) {
						Toast.makeText(getApplicationContext(),
								"wrong date format", Toast.LENGTH_SHORT).show();
						properuser = false;
					}
					if (!validator.isProperPostCode(entrys[postcode].getText()
							.toString())) {
						Toast.makeText(getApplicationContext(),
								"wrong postcode", Toast.LENGTH_SHORT).show();
						properuser = false;

					}
					if (!validator.olderthan13(entrys[date].getText()
							.toString())) {
						Toast.makeText(getApplicationContext(),
								"You have to be older than 13",
								Toast.LENGTH_SHORT).show();
						properuser = false;
					}
					if (properuser) {

						try {

							String result = client.addNewUser(entrys[username]
									.getText().toString(), entrys[password]
									.getText().toString(),
									calculateHTTPrequest());
							Toast.makeText(getApplicationContext(), result,
									Toast.LENGTH_LONG).show();
							if (result.equals("Sucsessfully Created")) {
								client.getportiondata();
								DataClass
										.addtolog("Welcome: "
												+ entrys[username].getText()
														.toString());
								DataClass.addtolog(entrys[username] + " logged in");
								DatabaseUserHandler dbuser = new DatabaseUserHandler(
										getApplicationContext());
								dbuser.remove();
								dbuser.addUserInfo(DataClass.user);

								finish();
								intent = new Intent(getApplicationContext(),
										MenuActivity.class);
								startActivity(intent);
							}
						} catch (Exception e) {
							// Toast.makeText(getBaseContext(), e.getMessage(),
							// Toast.LENGTH_SHORT).show();
						}
					}
				} else {
					String password = entrys[4].getText().toString();
					String password2 = entrys[5].getText().toString();
					if (password.equals(password2)) {
						try {
							Toast.makeText(
									getApplicationContext(),
									client.changepassword(
											DataClass.user.getUsername(),
											password), Toast.LENGTH_SHORT)
									.show();
						} catch (ClientProtocolException e) {
							// Toast.makeText(getApplicationContext(),
							// e.getMessage(), Toast.LENGTH_SHORT).show();
							e.printStackTrace();
						} catch (IOException e) {
							// Toast.makeText(getApplicationContext(),
							// e.getMessage(), Toast.LENGTH_SHORT).show();
							e.printStackTrace();
						}
					} else {
						Toast.makeText(getApplicationContext(),
								"Passwords dosent match", Toast.LENGTH_SHORT)
								.show();
					}
				}
			}
		}

		private String calculateHTTPrequest() {
			String postcode1 = entrys[postcode].getText().toString();
			postcode1 = postcode1.replace(" ", "%20");

			StringBuilder request = new StringBuilder();
			request.append("register.php?username=");
			request.append(entrys[username].getText().toString());
			request.append("&password=");
			request.append(entrys[password].getText().toString());
			request.append("&email=");
			request.append(entrys[email].getText().toString());
			request.append("&dob=");
			request.append(entrys[date].getText().toString());
			request.append("&postcode=");
			request.append(postcode1);
			request.append("&hardcore=");
			if (hardcore.isChecked()) {
				request.append(1);
			} else {
				request.append(0);
			}
			return request.toString();
		}
	};

	@Override
	public void onBackPressed() {
		if (change) {
			finish();
			startActivity(new Intent(getApplicationContext(), MeActivity.class));
		} else {

			finish();
			startActivity(new Intent(getApplicationContext(),
					MainLogInActivity.class));
		}
	};

	private OnCheckedChangeListener cheacklister = new OnCheckedChangeListener() {

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
				boolean isChecked) {
			vibrator.vibrate(50);
			hardcore.setChecked(isChecked);
		}
	};

}
