package com.game.memory;

import java.util.LinkedList;

import com.vectorone.R;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;

public class Game_memory_Activity extends Activity {

	private int numberoflights = 24;
	private CheckBox[] lights = new CheckBox[numberoflights];
	private LinkedList<Integer> field = new LinkedList<Integer>();
	private int tmpBoxindex = -1;
	private Game_memory_Time gametime;
	private boolean withpoints;
	private static final String TAG = "GameMemory";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_keepopen_game);
		Intent intent = getIntent();
		withpoints = intent.getBooleanExtra("withpoints", true);
		generatefiled(field);
		Log.i(TAG, field.toString());
		intitfields();

	}

	private static void generatefiled(LinkedList<Integer> field) {
		int position = 0;
		for (int i = 0; i < 24; i++) {
			position = generate(field);

			field.add(position);
		}
	}

	private static int generate(LinkedList<Integer> field) {
		int i = (int) (Math.random() * 24);
		while (field.contains(i)) {
			i = (int) (Math.random() * 24);
		}
		return i;
	}

	private void intitfields() {
		for (int i = 0; i < numberoflights; i++) {
			lights[i] = (CheckBox) findViewById(getid("light" + (i + 1)));
			lights[i].setChecked(false);
			lights[i].setOnClickListener(clickhandler);
		}
		for (int i = 0; i < field.size(); i = i + 2) {
			switch (i) {
			case 0:
				lights[field.get(i)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamebluecross);
				lights[field.get(i + 1)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamebluecross);
				break;
			case 2:
				lights[field.get(i)]
						.setButtonDrawable(R.drawable.checkbox_memory_gameredcross);
				lights[field.get(i + 1)]
						.setButtonDrawable(R.drawable.checkbox_memory_gameredcross);
				break;
			case 4:
				lights[field.get(i)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamegreencross);
				lights[field.get(i + 1)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamegreencross);
				break;
			case 6:
				lights[field.get(i)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamepurplecross);
				lights[field.get(i + 1)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamepurplecross);
				break;
			case 8:
				lights[field.get(i)]
						.setButtonDrawable(R.drawable.checkbox_memory_gameblueflag);
				lights[field.get(i + 1)]
						.setButtonDrawable(R.drawable.checkbox_memory_gameblueflag);
				break;
			case 10:
				lights[field.get(i)]
						.setButtonDrawable(R.drawable.checkbox_memory_gameredflag);
				lights[field.get(i + 1)]
						.setButtonDrawable(R.drawable.checkbox_memory_gameredflag);
				break;
			case 12:
				lights[field.get(i)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamegreenflag);
				lights[field.get(i + 1)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamegreenflag);
				break;
			case 14:
				lights[field.get(i)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamepurpelflag);
				lights[field.get(i + 1)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamepurpelflag);
				break;
			case 16:
				lights[field.get(i)]
						.setButtonDrawable(R.drawable.checkbox_memory_gameopenbox);
				lights[field.get(i + 1)]
						.setButtonDrawable(R.drawable.checkbox_memory_gameopenbox);
				break;
			case 18:
				lights[field.get(i)]
						.setButtonDrawable(R.drawable.checkbox_memory_gameskullflag);
				lights[field.get(i + 1)]
						.setButtonDrawable(R.drawable.checkbox_memory_gameskullflag);
				break;
			case 20:
				lights[field.get(i)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamemapclosed);
				lights[field.get(i + 1)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamemapclosed);
				break;
			case 22:
				lights[field.get(i)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamemapopen);
				lights[field.get(i + 1)]
						.setButtonDrawable(R.drawable.checkbox_memory_gamemapopen);
				break;

			default:
				break;
			}
		}

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

	private int getindexfromid(int id) {
		for (int i = 0; i < lights.length; i++) {
			if (lights[i].getId() == id) {
				return i;
			}
		}
		return 0;
	}

	private boolean issameImage(int index) {

		int image1 = field.indexOf(tmpBoxindex);
		int image2 = field.indexOf(index);

		Log.i(TAG, "click1:" + image1 + "---Click2:" + image2);
		boolean issame = false;

		if (image1 % 2 == 0 && image2 % 2 == 1) {

			issame = image1 == image2 - 1;

		} else if (image1 % 2 == 1 && image2 % 2 == 0) {
			issame = image2 == image1 - 1;
		}
		return issame;
	}

	private void calculatepoints() {
		if (withpoints) {

			// TODO Auto-generated method stub
		}
	}

	private OnClickListener clickhandler = new OnClickListener() {

		@Override
		public void onClick(View v) {
			CheckBox aktuell = (CheckBox) findViewById(v.getId());
			if (aktuell.isChecked()) {
				if (gametime == null) {
					gametime = new Game_memory_Time(120400, 90,
							(TextView) findViewById(R.id.time), lights);
					gametime.start();
				}

				if (tmpBoxindex != -1) {

					if (issameImage(getindexfromid(v.getId()))) {
						tmpBoxindex = -1;
					} else {
						CheckBox click1 = lights[tmpBoxindex];
						CheckBox click2 = ((CheckBox) findViewById(v.getId()));
						click2.setChecked(true);
						new Game_memory_Time(1000, 100, click1, click2, lights)
								.start();
						tmpBoxindex = -1;
					}
				} else {
					tmpBoxindex = getindexfromid(v.getId());
				}
			} else {
				aktuell.setChecked(true);
			}
		}

	};

}