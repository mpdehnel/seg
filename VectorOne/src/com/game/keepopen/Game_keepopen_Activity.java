package com.game.keepopen;

import com.vectorone.R;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.TextView;

public class Game_keepopen_Activity extends Activity {

	private int numberoflights = 24;
	private CheckBox[] lights = new CheckBox[numberoflights];
	private boolean hardcore = false;
	private Game_KeepOpen_Time gametime;
	private MediaPlayer mp;
	private boolean withpoints;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_keepopen_game);
		Intent intent= getIntent();
		withpoints= intent.getBooleanExtra("withpoints", true);
		for (int i = 0; i < numberoflights; i++) {
			lights[i] = (CheckBox) findViewById(getid("light" + (i + 1)));
			lights[i].setButtonDrawable(R.drawable.checkbox_keepopen_game);
			lights[i].setChecked(true);
		}
		setHardcodeListener(hardcore);
		gametime = new Game_KeepOpen_Time(120000, 100, lights,
				(TextView) findViewById(R.id.time), this);

		gametime.start();

		PlaySound();

	}

	private int getid(String name) {
		if (name.equals("light1")) {
			return R.id.light1;
		}
		if (name.equals("light2")) {
			return R.id.light2;
		}
		if (name.equals("light3")) {
			return R.id.light3;
		}
		if (name.equals("light4")) {
			return R.id.light4;
		}
		if (name.equals("light5")) {
			return R.id.light5;
		}
		if (name.equals("light6")) {
			return R.id.light6;
		}
		if (name.equals("light7")) {
			return R.id.light7;
		}
		if (name.equals("light8")) {
			return R.id.light8;
		}
		if (name.equals("light9")) {
			return R.id.light9;
		}
		if (name.equals("light10")) {
			return R.id.light10;
		}
		if (name.equals("light11")) {
			return R.id.light11;
		}
		if (name.equals("light12")) {
			return R.id.light12;
		}
		if (name.equals("light13")) {
			return R.id.light13;
		}
		if (name.equals("light14")) {
			return R.id.light14;
		}
		if (name.equals("light15")) {
			return R.id.light15;
		}
		if (name.equals("light16")) {
			return R.id.light16;
		}
		if (name.equals("light17")) {
			return R.id.light17;
		}
		if (name.equals("light18")) {
			return R.id.light18;
		}
		if (name.equals("light19")) {
			return R.id.light19;
		}
		if (name.equals("light20")) {
			return R.id.light20;
		}
		if (name.equals("light21")) {
			return R.id.light21;
		}
		if (name.equals("light22")) {
			return R.id.light22;
		}
		if (name.equals("light23")) {
			return R.id.light23;
		}
		if (name.equals("light24")) {
			return R.id.light24;
		}
		return 0;

	}

	private void PlaySound() {
		new Thread() {
			public void run() {
				mp = MediaPlayer.create(getApplicationContext(),
						R.raw.gamesound);
				mp.start();
			}
		}.start();
	}

	public void stop(long time) {
		mp.stop();
		//calculatepoints();
	}

	private void calculatepoints() {
		if(withpoints){
			//TODO:
		}
	}
	
	@Override
	public void onBackPressed() {
		mp.stop();
		super.onBackPressed();
	}
	

	public void setHardcodeListener(boolean hardcore) {
		if (hardcore) {
			for (int i = 0; i < lights.length; i++) {
				lights[i].setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						for (int k = 0; k < lights.length; k++)
							if (lights[k].isChecked() && v == lights[k]) {
								lights[k].setChecked(false);
							}
					}
				});
			}
		}
	}

}