package com.vectorone;

import com.data.DataClass;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MeActivity extends Activity {
	private TextView name;
	private TextView nickname;
	private TextView team;
	private TextView totalpoints;
	private TextView totalcaches;
	private ImageView image;
	private Button logbutton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_user);

		name = (TextView) findViewById(R.id.name);
		nickname = (TextView) findViewById(R.id.nickname);
		team = (TextView) findViewById(R.id.team);
		totalpoints = (TextView) findViewById(R.id.totalPoints);
		totalcaches = (TextView) findViewById(R.id.totalNumberOfChaches);
		image = (ImageView) findViewById(R.id.avatar);
		logbutton= (Button) findViewById(R.id.log_button);
		
		GridLayout gridlayout = (GridLayout) findViewById(R.id.me_layout);
		gridlayout.setBackgroundDrawable(getResources().getDrawable(
				R.drawable.background));

		name.setText("Name: " + DataClass.user.getUsername());

		nickname.setText("Nickname: " + DataClass.user.getNickname());
		team.setText("Team: " + DataClass.user.getTeam());
		totalpoints.setText("TotalPoints: " + DataClass.user.getTotalpoints());
		totalcaches.setText("TotalCaches: " + DataClass.user.getTotalcaches());
		image.setImageDrawable(getResources().getDrawable(
				DataClass.user.getImage()));
		
		logbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getApplicationContext(),
						LogActivity.class));
			}
		});

	}
}