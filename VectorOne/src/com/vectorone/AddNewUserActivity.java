package com.vectorone;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;

public class AddNewUserActivity extends Activity{

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_addnewuser);
		
		initdatafields();
		setupListener();
		setupfont();
		setupbackgroundimage();
	}

	private void setupbackgroundimage() {
		// TODO Auto-generated method stub
		
	}

	private void setupfont() {
		// TODO Auto-generated method stub
		
	}

	private void setupListener() {
		// TODO Auto-generated method stub
		
	}

	private void initdatafields() {
		// TODO Auto-generated method stub
		
	}
}
