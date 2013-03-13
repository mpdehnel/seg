package com.game.memory;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.widget.CheckBox;
import android.widget.TextView;

public class Game_memory_Time extends CountDownTimer {

	private CheckBox click1;
	private CheckBox click2;
	private TextView t1;
	private long millisInFuture;
	private CheckBox[] lights;
	private Game_memory_Activity memory;
	private long time;
	private int counter=0;

	public Game_memory_Time(long millisInFuture, long countDownInterval,
			CheckBox click1, CheckBox click2, CheckBox[] lights,
			Game_memory_Activity memory) {
		super(millisInFuture, countDownInterval);
		this.click1 = click1;
		this.click2 = click2;
		this.lights = lights;
		this.memory = memory;
		for (int i = 0; i < lights.length; i++) {
			lights[i].setEnabled(false);
		}

	}

	public Game_memory_Time(long millisInFuture, long countDownInterval,
			TextView t1, CheckBox[] lights,Game_memory_Activity memory) {
		super(millisInFuture, countDownInterval);
		this.millisInFuture = millisInFuture;
		this.t1 = t1;
		this.memory=memory;
		this.lights = lights;
		Typeface font = Typeface
				.createFromAsset(memory.getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#DECD87");
		t1.setTextColor(textcolor);
		t1.setTypeface(font);
		t1.setTextSize(17);
	}

	@Override
	public void onFinish() {
		if (click1 != null && click2 != null) {
			click1.setChecked(false);
			click2.setChecked(false);
			for (int i = 0; i < lights.length; i++) {
				lights[i].setEnabled(true);
			}
		}
		if (t1 != null && lights != null) {
			for (int i = 0; i < lights.length; i++) {
				lights[i].setChecked(false);
			}

		}
	}

	@Override
	public void onTick(long millisUntilFinished) {
		if (t1 != null && !finished()&&counter<1) {
			time = (millisInFuture - millisUntilFinished) / 1000;
			t1.setText(time + "");
		}
	}

	private boolean finished() {
		for (int i = 0; i < lights.length; i++) {
			if (!lights[i].isChecked()) {
				return false;
			}
		}
		
		counter++;
		memory.stop(time);
		return true;
	}

}
