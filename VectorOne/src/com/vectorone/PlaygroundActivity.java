package com.vectorone;

import com.game.keepopen.Game_keepopen_Activity;
import com.game.memory.Game_memory_Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;

public class PlaygroundActivity extends Activity {

	private Button memory_button;
	private Button keepopen_button;
	private Button comingsoon1_button;
	private Button comingsoon2_button;
	private Button comingsoon3_button;
	private Button comingsoon4_button;
	private Button comingsoon5_button;
	private Button comingsoon6_button;
	private RelativeLayout layout;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_playground);

		initfields();
		setupBackdrounds();
		setupfonts();
		setuplistener();

	}

	private void setuplistener() {
		keepopen_button.setOnClickListener(clickhandler);
		memory_button.setOnClickListener(clickhandler);
	}

	private void setupfonts() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int buttoncolor = Color.parseColor("#45250F");
		float textsize = 22;

		memory_button.setTextSize(textsize);
		memory_button.setTextColor(buttoncolor);
		memory_button.setTypeface(font);

		keepopen_button.setTextSize(textsize);
		keepopen_button.setTextColor(buttoncolor);
		keepopen_button.setTypeface(font);

		comingsoon1_button.setTextSize(textsize);
		comingsoon1_button.setTextColor(buttoncolor);
		comingsoon1_button.setTypeface(font);

		comingsoon2_button.setTextSize(textsize);
		comingsoon2_button.setTextColor(buttoncolor);
		comingsoon2_button.setTypeface(font);

		comingsoon3_button.setTextSize(textsize);
		comingsoon3_button.setTextColor(buttoncolor);
		comingsoon3_button.setTypeface(font);

		comingsoon4_button.setTextSize(textsize);
		comingsoon4_button.setTextColor(buttoncolor);
		comingsoon4_button.setTypeface(font);

		comingsoon5_button.setTextSize(textsize);
		comingsoon5_button.setTextColor(buttoncolor);
		comingsoon5_button.setTypeface(font);

		comingsoon6_button.setTextSize(textsize);
		comingsoon6_button.setTextColor(buttoncolor);
		comingsoon6_button.setTypeface(font);

	}

	private void initfields() {
		memory_button = (Button) findViewById(R.id.memory_button);
		keepopen_button = (Button) findViewById(R.id.KeepOpen_button);
		comingsoon1_button = (Button) findViewById(R.id.ComingSoon1);
		comingsoon2_button = (Button) findViewById(R.id.ComingSoon2);
		comingsoon3_button = (Button) findViewById(R.id.ComingSoon3);
		comingsoon4_button = (Button) findViewById(R.id.ComingSoon4);
		comingsoon5_button = (Button) findViewById(R.id.ComingSoon5);
		comingsoon6_button = (Button) findViewById(R.id.ComingSoon6);
		layout = (RelativeLayout) findViewById(R.id.playground_layout);

	}

	private void setupBackdrounds() {
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonmedium);
		Drawable buttonimageinaktiv=getResources().getDrawable(R.drawable.buttonmediumgrey);
		layout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		memory_button.setBackgroundDrawable(buttonimage);
		keepopen_button.setBackgroundDrawable(buttonimage);
		comingsoon1_button.setBackgroundDrawable(buttonimageinaktiv);
		comingsoon2_button.setBackgroundDrawable(buttonimageinaktiv);
		comingsoon3_button.setBackgroundDrawable(buttonimageinaktiv);
		comingsoon4_button.setBackgroundDrawable(buttonimageinaktiv);
		comingsoon5_button.setBackgroundDrawable(buttonimageinaktiv);
		comingsoon6_button.setBackgroundDrawable(buttonimageinaktiv);
	}
	
	private OnClickListener clickhandler=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent;
			if(v==keepopen_button){
				finish();
				intent = new Intent(getApplicationContext(), Game_keepopen_Activity.class);
				intent.putExtra("withpoints", false);
				startActivity(intent);
			}
			if(v==memory_button){
				finish();
				intent = new Intent(getApplicationContext(), Game_memory_Activity.class);
				intent.putExtra("withpoints", false);
				startActivity(intent);
			}
		}
	};
}
