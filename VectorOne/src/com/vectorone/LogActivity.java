package com.vectorone;

import com.data.DataClass;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Window;
import android.widget.TextView;

public class LogActivity extends Activity{

	private TextView log;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_log);
		
		
		
		
		log = (TextView) findViewById(R.id.logText);
		log.setMovementMethod(ScrollingMovementMethod.getInstance());
		log.setText(DataClass.log);
	}

}
