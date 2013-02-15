package com.game.memory;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.widget.CheckBox;
import android.widget.TextView;

public class Game_memory_Time extends CountDownTimer {

	private CheckBox click1;
	private CheckBox click2;
	private TextView t1;
	private long millisInFuture;
	private CheckBox[] lights;

	public Game_memory_Time(long millisInFuture, long countDownInterval,
			CheckBox click1, CheckBox click2,CheckBox[] lights) {
		super(millisInFuture, countDownInterval);
		this.click1 = click1;
		this.click2 = click2;
		this.lights=lights;
		for(int i=0;i<lights.length;i++){
			lights[i].setEnabled(false);
		}
	
	}
	
	public Game_memory_Time(long millisInFuture, long countDownInterval,TextView t1,CheckBox[] lights) {
		super(millisInFuture, countDownInterval);
		this.millisInFuture=millisInFuture;
		this.t1=t1;
		this.lights=lights;
		t1.setTextColor(Color.WHITE);
	}

	@Override
	public void onFinish() {
		if(click1!=null&&click2!=null){
		click1.setChecked(false);
		click2.setChecked(false);
		for(int i=0;i<lights.length;i++){
			lights[i].setEnabled(true);
		}
		}
		if(t1!=null&&lights!=null){
			for(int i=0;i<lights.length;i++){
				lights[i].setChecked(false);
			}
			
		}
	}

	@Override
	public void onTick(long millisUntilFinished) {
		if(t1!=null)
			t1.setText((millisInFuture-millisUntilFinished)/1000+"");
	}

}