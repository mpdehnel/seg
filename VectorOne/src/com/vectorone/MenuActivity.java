package com.vectorone;

import com.data.DataClass;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity {

	private Button cachesSelectButton;
	private Button settingsButton;
	private Button meButton;
	private Button mapsButton;
	private Button logout;
	private Button dialoglogoutButton;
	private Button dialogcloseAppButton;
	private Button dialogcancelButton;
	private Dialog dialog;
	private TextView usernameTextView;
	private String username;
	private GridLayout gridlayout;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menue);
		gridlayout = (GridLayout) findViewById(R.id.menue_layout);

		Intent intent = getIntent();
		username = intent.getStringExtra("username");
		initfields();
		setupListener();
		setupbackgoundimages();
		setupfonts();
		usernameTextView.setText(username);

	}

	private void initfields() {
		usernameTextView = (TextView) findViewById(R.id.MenueUsername);
		cachesSelectButton = (Button) findViewById(R.id.cachselectbutton);
		meButton = (Button) findViewById(R.id.mebutton);
		settingsButton = (Button) findViewById(R.id.settingsbutton);
		logout = (Button) findViewById(R.id.logout_button);
		mapsButton = (Button) findViewById(R.id.button_maps);
		RelativeLayout relativ= (RelativeLayout) findViewById(R.id.test);
		//relativ.setBackgroundDrawable(getResources().getDrawable(R.drawable.databar));
		relativ.addView(new DrawableStatusbar(this));
		relativ.addView(new Backgroundview(this,getResources().getDrawable(R.drawable.databar)));
		intidialog();
		dialoglogoutButton = (Button) dialog
				.findViewById(R.id.dialogButtonLogOut);
		dialogcloseAppButton = (Button) dialog
				.findViewById(R.id.dialogButtonClose);

		dialogcancelButton = (Button) dialog
				.findViewById(R.id.dialogButtonCancel);
	}

	private void intidialog() {
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_logout);
		TextView text = (TextView) dialog.findViewById(R.id.dialog_Text);
		text.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		text.setText("What You want to do?");
		text.setTextSize(30);
	}

	private void setupListener() {
		cachesSelectButton.setOnClickListener(clickhandler);
		meButton.setOnClickListener(clickhandler);
		settingsButton.setOnClickListener(clickhandler);
		logout.setOnClickListener(clickhandler);
		mapsButton.setOnClickListener(clickhandler);
		dialogcancelButton.setOnClickListener(clickhandler);
		dialogcloseAppButton.setOnClickListener(clickhandler);
		dialoglogoutButton.setOnClickListener(clickhandler);
	}

	private void setupfonts() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int buttoncolor = Color.parseColor("#45250F");
		float textsize = 22;

		cachesSelectButton.setTypeface(font);
		cachesSelectButton.setTextSize(textsize);
		cachesSelectButton.setTextColor(buttoncolor);

		mapsButton.setTypeface(font);
		mapsButton.setTextSize(textsize);
		mapsButton.setTextColor(buttoncolor);

		meButton.setTypeface(font);
		meButton.setTextSize(textsize);
		meButton.setTextColor(buttoncolor);

		settingsButton.setTypeface(font);
		settingsButton.setTextSize(textsize);
		settingsButton.setTextColor(buttoncolor);

		logout.setTypeface(font);
		logout.setTextSize(textsize);
		logout.setTextColor(buttoncolor);

		dialogcancelButton.setTypeface(font);
		dialogcancelButton.setTextSize(textsize);
		dialogcancelButton.setTextColor(buttoncolor);

		dialogcloseAppButton.setTypeface(font);
		dialogcloseAppButton.setTextSize(textsize);
		dialogcloseAppButton.setTextColor(buttoncolor);

		dialoglogoutButton.setTypeface(font);
		dialoglogoutButton.setTextSize(textsize);
		dialoglogoutButton.setTextColor(buttoncolor);
	}

	private void setupbackgoundimages() {
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonmedium);
		gridlayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));

		cachesSelectButton.setBackgroundDrawable(buttonimage);
		mapsButton.setBackgroundDrawable(buttonimage);
		settingsButton.setBackgroundDrawable(buttonimage);
		meButton.setBackgroundDrawable(buttonimage);
		logout.setBackgroundDrawable(buttonimage);
		dialogcancelButton.setBackgroundDrawable(buttonimage);
		dialogcloseAppButton.setBackgroundDrawable(buttonimage);
		dialoglogoutButton.setBackgroundDrawable(buttonimage);
		

	}

	private void clickhandle(View v) {
		Intent intent;

		if (v == cachesSelectButton) {
			startActivity(new Intent(getApplicationContext(),
					CacheSelectActivity.class));
		}
		if (v == meButton) {
			intent = new Intent(getApplicationContext(), MeActivity.class);
			startActivity(intent);
		}
		if (v == settingsButton) {
			intent = new Intent(getApplicationContext(), SettingsActivity.class);
			startActivity(intent);
		}
		if (v == logout) {
			finish();
			DataClass.clear();
			intent = new Intent(getApplicationContext(),
					MainLogInActivity.class);
			startActivity(intent);
		}
		if (v == mapsButton) {
			intent = new Intent(getApplicationContext(), MapsActivity.class);
			startActivity(intent);
		}
		if (v == dialogcancelButton) {
			dialog.dismiss();
		}
		if (v == dialoglogoutButton) {
			finish();
			DataClass.clear();
			intent = new Intent(getApplicationContext(),
					MainLogInActivity.class);
			startActivity(intent);
		}
		if (v == dialogcloseAppButton) {

		}

	}

	@Override
	public void onBackPressed() {

		dialog.show();

	}

	private OnClickListener clickhandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clickhandle(v);
		}
	};

}
