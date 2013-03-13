package com.game.keepopen;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.widget.CheckBox;
import android.widget.TextView;

public class Game_KeepOpen_Time extends CountDownTimer {

	private CheckBox[] lights;
	private TextView t1;
	private long millisInFuture;
	private long time;
	private int counter=0;
	private int speedfaktor = 15;
	private Game_keepopen_Activity game;

	public Game_KeepOpen_Time(long millisInFuture, long countDownInterval,
			CheckBox[] lights, TextView textView, Game_keepopen_Activity gameActivity) {
		super(millisInFuture, countDownInterval);
		this.lights = lights;
		this.millisInFuture = millisInFuture / 100;
		this.t1 = textView;
		this.game = gameActivity;
		Typeface font = Typeface
				.createFromAsset(game.getAssets(), "fonts/bebas.ttf");
		int textcolor = Color.parseColor("#DECD87");
		t1.setTextColor(textcolor);
		t1.setTypeface(font);
		t1.setTextSize(22);
		
	}

	@Override
	public void onFinish() {
		// TODO
	}

	@Override
	public void onTick(long millisUntilFinished) {
		if(!isfull()){
		time = millisInFuture - (millisUntilFinished / 100);

		t1.setText("KeepOpenTime:"+time/10.0 + "s");
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
		
		if(counter<1){
			counter++;
		game.stop(time);
		}
		return true;
	}
	
	

}