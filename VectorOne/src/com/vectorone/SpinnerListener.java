package com.vectorone;

import com.outsourced.CamActivity;
import com.outsourced.RadarActivity;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;

public class SpinnerListener implements OnItemSelectedListener {

	private MenuActivity menuActivity;
	private ArrayAdapter<CharSequence> adapter;
	private static boolean flag=false;

	

	public SpinnerListener(MenuActivity menuActivity, ArrayAdapter<CharSequence> adapter) {
		this.menuActivity = menuActivity;
		this.adapter=adapter;
	}

	

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		
		if(flag){
		if (parent.getItemAtPosition(pos).toString().equals("Map")) {
			Intent intent = new Intent(menuActivity.getApplicationContext(),
					MapsActivity.class);

			menuActivity.startActivity(intent);
		}
		if (parent.getItemAtPosition(pos).toString().equals("Cam")) {
			Intent intent = new Intent(menuActivity.getApplicationContext(),
					CamActivity.class);

			menuActivity.startActivity(intent);
		}
		if (parent.getItemAtPosition(pos).toString().equals("Radar")) {
			Intent intent = new Intent(menuActivity.getApplicationContext(),
					RadarActivity.class);

			menuActivity.startActivity(intent);
		}
		parent.setSelection(adapter.getPosition("MapView"));
		}
		flag=true;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
	
	public static void setFlag(boolean flag) {
		SpinnerListener.flag = flag;
	}

}
