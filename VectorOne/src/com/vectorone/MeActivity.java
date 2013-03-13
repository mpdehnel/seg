
package com.vectorone;

import com.data.DataClass;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MeActivity extends Activity {
	private TextView name;
	private TextView team;
	private TextView totalpoints;
	private TextView totalcaches;
	private Button logbutton;
	private RelativeLayout layout;
	private boolean formmaps;
	private TextView location;
	private TextView curretentPoints;
	private Button change;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user);
		Intent intent = getIntent();
		formmaps = intent.getBooleanExtra("frommaps", false);
		initdatafields();
		setupListener();
		setupfont();
		setupbackgroundimage();
		setupdatafields();

	}

	private void setupdatafields() {
		name.setText("Name: " + DataClass.user.getUsername());
		team.setText("Team: " + DataClass.user.getTeam());
		totalpoints.setText("TotalPoints: " + DataClass.user.getTotalpoints());
		totalcaches.setText("TotalCaches: " + DataClass.user.getTotalcaches());
		curretentPoints.setText("CurrentPoints: "+DataClass.user.getCurrentPoints());
		double lat = Math.round(DataClass.mylat / Math.pow(10, 4)) / 100;
		double lng = Math.round(DataClass.mylng / Math.pow(10, 4)) / 100;

		location.setText("Location: " + System.getProperty("line.separator")
				+ "Lat=" + lat + System.getProperty("line.separator")
				+ "Long=" + lng);
	}

	private void setupbackgroundimage() {
		layout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));
		Drawable buttonimage = getResources().getDrawable(
				R.drawable.buttonmedium);
		logbutton.setBackgroundDrawable(buttonimage);
		change.setBackgroundDrawable(buttonimage);
	}

	private void setupfont() {
		Typeface font = Typeface
				.createFromAsset(getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#DECD87");
		int buttoncolor = Color.parseColor("#45250F");
		float textsize = 17;

		name.setTypeface(font);
		name.setTextColor(textcolor);
		name.setTextSize(textsize);


		team.setTypeface(font);
		team.setTextColor(textcolor);
		team.setTextSize(textsize);

		totalpoints.setTypeface(font);
		totalpoints.setTextColor(textcolor);
		totalpoints.setTextSize(textsize);

		location.setTypeface(font);
		location.setTextColor(textcolor);
		location.setTextSize(textsize);

		totalcaches.setTypeface(font);
		totalcaches.setTextColor(textcolor);
		totalcaches.setTextSize(textsize);
		
		curretentPoints.setTypeface(font);
		curretentPoints.setTextColor(textcolor);
		curretentPoints.setTextSize(textsize);

		logbutton.setTypeface(font);
		logbutton.setTextSize(textsize);
		logbutton.setTextColor(buttoncolor);
		
		change.setTypeface(font);
		change.setTextSize(textsize);
		change.setTextColor(buttoncolor);


	}

	private void setupListener() {
		logbutton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						LogActivity.class));
			}
		});
		
		change.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(getApplicationContext(), AddNewUserActivity.class);
				intent.putExtra("change", true);
				startActivity(intent);				
			}
		});
	}

	private void initdatafields() {
		name = (TextView) findViewById(R.id.name);
		team = (TextView) findViewById(R.id.team);
		totalpoints = (TextView) findViewById(R.id.totalPoints);
		totalcaches = (TextView) findViewById(R.id.totalNumberOfChaches);
		logbutton = (Button) findViewById(R.id.log_button);
		layout = (RelativeLayout) findViewById(R.id.me_layout);
		location = (TextView) findViewById(R.id.location);
		curretentPoints=(TextView)findViewById(R.id.currentpoints);
		change =(Button)findViewById(R.id.Change);
	}

	@Override
	public void onBackPressed() {
		finish();
		startActivity(new Intent(getApplicationContext(),
				MenuActivity.class));
	}
}