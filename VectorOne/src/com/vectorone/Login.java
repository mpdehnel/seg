package com.vectorone;

import java.util.LinkedList;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends Activity {

	private LinkedList<Integer> history = new LinkedList<Integer>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		history.add(R.layout.activity_login);
		setContentView(R.layout.activity_login);

	}

	/* Request updates at startup */
	protected void onResume() {
		super.onResume();

	}

	/* Remove the locationlistener updates when Activity is paused */
	@Override
	protected void onPause() {
		super.onPause();

	}

	public void login(View view) {
		String username = ((EditText) findViewById(R.id.Username)).getText()
				.toString();
		String password = ((EditText) findViewById(R.id.password)).getText()
				.toString();
		LoginValidation LogVal = new LoginValidation();
		if (LogVal.isUser(username, password)) {
			history.add(R.layout.activity_menue);
			setContentView(R.layout.activity_menue);
		} else {
			// TODO:Error
		}
	}

	public void clear() {
		((EditText) findViewById(R.id.Username)).setText("");
		((EditText) findViewById(R.id.password)).setText("");

	}

	public void registration(View view) {
		//TODO SERVERADDRESSE VIA Constant CLass
		Uri uri = Uri.parse("http://www.google.com");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);
		
	}

	public void user(View view) {
		history.add(R.layout.activity_user);
		setContentView(R.layout.activity_user);
	}

	public void cachselect(View view) {
		history.add(R.layout.activity_cachselect);
		setContentView(R.layout.activity_cachselect);
	}

	public void settings(View view) {
		history.add(R.layout.activity_settings);
		setContentView(R.layout.activity_settings);
	}

	public void maps(View view) {
		history.add(R.layout.activity_maps);
		setContentView(R.layout.activity_maps);
	}

	@Override
	public void onBackPressed() {
		if (history.size() > 1) {
			history.removeLast();
			setContentView(history.getLast());
		}

	}

}
