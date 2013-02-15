package com.game.keepopen;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.CheckBox;
import android.widget.TextView;

public class Game_KeepOpen_Time extends CountDownTimer {

	private CheckBox[] lights;
	private TextView t1;
	private long millisInFuture;
	private long time;
	private int speedfaktor = 15;
	private Game_keepopen_Activity game;

	public Game_KeepOpen_Time(long millisInFuture, long countDownInterval,
			CheckBox[] lights, TextView textView, Game_keepopen_Activity gameActivity) {
		super(millisInFuture, countDownInterval);
		this.lights = lights;
		this.millisInFuture = millisInFuture / 100;
		this.t1 = textView;
		this.game = gameActivity;
		t1.setTextColor(Color.WHITE);
	}

	@Override
	public void onFinish() {
		// TODO
	}

	@Override
	public void onTick(long millisUntilFinished) {
		if(!isfull()){
		time = millisInFuture - (millisUntilFinished / 100);

		t1.setText(time + "");
		if (time % speedfaktor == 0) {
			setSpeedfactor(time);
			lights[generate()].setChecked(false);
		}
		}else{
			game.setHardcodeListener(true);
		}
	}

	private void setSpeedfactor(long time) {
		if (time > 100)
			speedfaktor = 10;
		if (time > 200)
			speedfaktor = 8;
		if (time > 300)
			speedfaktor = 5;
		if (time > 400)
			speedfaktor = 3;
		if (time > 450)
			speedfaktor = 2;
		if (time > 500)
			speedfaktor = 1;

	}

	private int generate() {
		int i = (int) (Math.random() * 24);
		while (!lights[i].isChecked() && !isfull()) {
			i = (int) (Math.random() * 24);
		}
		return i;
	}

	private boolean isfull()  {
		for(int i=0;i<lights.length;i++){
			if(lights[i].isChecked())
				return false;
		}
		game.stop(time);
		return true;
	}

}