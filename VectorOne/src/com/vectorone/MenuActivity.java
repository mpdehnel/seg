package com.vectorone;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.data.DataClass;
import com.data.MyHttpClient;
import com.findCache.MapsActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity {

	private Button cachesSelectButton;
	private Button settingsButton;
	private Button meButton;
	private Button search;
	private Button logout;
	private Button dialoglogoutButton;
	private Button dialogcloseAppButton;
	private Button dialogcancelButton;
	private Dialog dialog;
	private TextView usernameTextView;
	private RelativeLayout layout;
	private Vibrator vibrate;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menue);
		layout = (RelativeLayout) findViewById(R.id.menue_layout);
		initfields();
		setupListener();
		setupbackgoundimages();
		setupfonts();
		usernameTextView.setText("Hello  " + DataClass.user.getUsername());

	}

	private void initfields() {
		usernameTextView = (TextView) findViewById(R.id.MenueUsername);
		cachesSelectButton = (Button) findViewById(R.id.cachselectbutton);
		meButton = (Button) findViewById(R.id.mebutton);
		settingsButton = (Button) findViewById(R.id.settingsbutton);
		logout = (Button) findViewById(R.id.logout_button);
		search = (Button) findViewById(R.id.button_maps);
		RelativeLayout relativ = (RelativeLayout) findViewById(R.id.test);
		// relativ.setBackgroundDrawable(getResources().getDrawable(R.drawable.databar));
		relativ.addView(new DrawableStatusbar(this));
		vibrate = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		relativ.addView(new Backgroundview(this, getResources().getDrawable(
				R.drawable.databar)));
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
		search.setOnClickListener(clickhandler);
		dialogcancelButton.setOnClickListener(clickhandler);
		dialogcloseAppButton.setOnClickListener(clickhandler);
		dialoglogoutButton.setOnClickListener(clickhandler);
	}

	private void setupfonts() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int buttoncolor = Color.parseColor("#45250F");
		int textcolor = Color.parseColor("#45250F");
		float textsize = 22;

		cachesSelectButton.setTypeface(font);
		cachesSelectButton.setTextSize(textsize);
		cachesSelectButton.setTextColor(buttoncolor);

		usernameTextView.setTypeface(font);
		usernameTextView.setTextSize(textsize);
		usernameTextView.setTextColor(textcolor);

		search.setTypeface(font);
		search.setTextSize(textsize);
		search.setTextColor(buttoncolor);

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
		// Drawable buttonimage = getResources().getDrawable(
		// R.drawable.buttonmedium);
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonmedium);

		layout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));

		cachesSelectButton.setBackgroundDrawable(buttonimage);
		search.setBackgroundDrawable(buttonimage);
		settingsButton.setBackgroundDrawable(buttonimage);
		meButton.setBackgroundDrawable(buttonimage);
		logout.setBackgroundDrawable(buttonimage);
		dialogcancelButton.setBackgroundDrawable(buttonimage);
		dialogcloseAppButton.setBackgroundDrawable(buttonimage);
		dialoglogoutButton.setBackgroundDrawable(buttonimage);

	}

	private void clickhandle(View v) {
		Intent intent;
		vibrate.vibrate(50);
		if (v == cachesSelectButton) {
			finish();
			startActivity(new Intent(getApplicationContext(),
					CacheSelectActivity.class));
		}
		if (v == meButton) {
			finish();
			intent = new Intent(getApplicationContext(), MeActivity.class);
			startActivity(intent);
		}
		if (v == settingsButton) {
			finish();
			intent = new Intent(getApplicationContext(), SettingsActivity.class);
			startActivity(intent);
		}

		if (v == logout) {
			dialog.dismiss();
			finish();
			pushlog();
			DataClass.log=new StringBuilder();
			intent = new Intent(getApplicationContext(),
					MainLogInActivity.class);
			startActivity(intent);
		}
		if (v == search) {
			finish();
			intent = new Intent(getApplicationContext(), ViewActivity.class);
			startActivity(intent);
		}
		if (v == dialogcancelButton) {
			dialog.dismiss();
		}
		if (v == dialoglogoutButton) {
			finish();
			dialog.dismiss();
			pushlog();
			DataClass.log=new StringBuilder();
			intent = new Intent(getApplicationContext(),
					MainLogInActivity.class);
			startActivity(intent);
		}
		if (v == dialogcloseAppButton) {
			dialog.dismiss();
			finish();
		}

	}

	private void pushlog() {
		MyHttpClient http = new MyHttpClient(DataClass.server);
		try {
			http.setuserlog(DataClass.user.getUsername(), DataClass.log.toString());
		} catch (ClientProtocolException e) {
		//	Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		} catch (IOException e) {
			//Toast.makeText(getBaseContext(),e.getMessage(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}

	}

	@Override
	public void onBackPressed() {
		dialog.show();
		//finish();
	}

	private OnClickListener clickhandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clickhandle(v);
		}
	};

}
