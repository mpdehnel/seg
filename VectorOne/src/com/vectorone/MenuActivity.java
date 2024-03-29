package com.vectorone;

import com.data.DataClass;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity {

	private Button cachesSelectButton;
	private Button settingsButton;
	private Button meButton;
	private Button logout;
	private TextView usernameTextView;
	private String username;

	private OnClickListener clickhandler = new OnClickListener() {
		@Override
		public void onClick(View v) {
			clickhandle(v);
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_menue);
		GridLayout gridlayout = (GridLayout) findViewById(R.id.menue_layout);
		gridlayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		Intent intent = getIntent();
		username = intent.getStringExtra("username");
		usernameTextView = (TextView) findViewById(R.id.MenueUsername);
		usernameTextView.setText(username);
		cachesSelectButton = (Button) findViewById(R.id.cachselectbutton);
		meButton = (Button) findViewById(R.id.mebutton);
		settingsButton = (Button) findViewById(R.id.settingsbutton);
		logout = (Button) findViewById(R.id.logout_button);

		cachesSelectButton.setOnClickListener(clickhandler);
		meButton.setOnClickListener(clickhandler);
		settingsButton.setOnClickListener(clickhandler);
		logout.setOnClickListener(clickhandler);

		Spinner spinner = (Spinner) findViewById(R.id.FindingViewSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.FindingViews,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(new SpinnerListener(this, adapter));
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

	}

	@Override
	public void onBackPressed() {
		final Dialog dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setContentView(R.layout.dialog_logout);
		TextView text = (TextView) dialog.findViewById(R.id.dialog_Text);
		text.setBackgroundColor(Color.BLACK);
		text.setText("What You want to do?");
		text.setTextSize(30);

		Button cancelButton = (Button) dialog
				.findViewById(R.id.dialogButtonCancel);
		cancelButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		Button logoutButton = (Button) dialog
				.findViewById(R.id.dialogButtonLogOut);
		logoutButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				DataClass.clear();

				startActivity(new Intent(getApplicationContext(),
						MainLogInActivity.class));
			}

		});

		Button closeAppButton = (Button) dialog
				.findViewById(R.id.dialogButtonClose);
		closeAppButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		dialog.show();

	}
}
