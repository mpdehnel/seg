package com.vectorone;

import android.app.Activity;
import android.os.Bundle;

public class CamActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cam);
		SpinnerListener.setFlag(false);

	}

}
