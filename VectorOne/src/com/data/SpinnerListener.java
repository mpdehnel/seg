package com.data;

import com.vectorone.R;
import com.vectorone.SettingsActivity;

import android.R.anim;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;

public class SpinnerListener implements OnItemSelectedListener {

	private User user;
	private SettingsActivity settings;

	public SpinnerListener(User user, SettingsActivity settings) {
		this.user = user;
		this.settings = settings;
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		String item = parent.getItemAtPosition(pos).toString();

		if (item.equals("m-km")) {
			user.setSettings_unit(item);
			DataClass.unit = "km";
		}
		if (item.equals("ft-mile")) {
			user.setSettings_unit(item);
			DataClass.unit = "ft";
		}
		if (item.equals("500")) {
			user.setSettings_maxdistance(item);
		}
		if (item.equals("1000")) {
			user.setSettings_maxdistance(item);
		}
		if (item.equals("2000")) {
			user.setSettings_maxdistance(item);
		}
		if (item.equals("5000")) {
			user.setSettings_maxdistance(item);
		}
		if (item.equals("10000")) {
			user.setSettings_maxdistance(item);
		}
		if (item.equals("All")) {
			if (parent.getId() == R.id.spinner_cache) {
				user.setSettings_visited(item);
			//	Log.i("MAIN", item + "--Cache");
			}
			if (parent.getId() == R.id.spinner_distance) {
				user.setSettings_maxdistance(item);
				//Log.i("MAIN", item + "--distance");
			}
			if (parent.getId() == R.id.spinner_team) {
				user.setSettings_team(item);
				//Log.i("MAIN", item + "--team");
			}
		}
		if (item.equals("My")) {
			user.setSettings_team(item);
		}
		if (item.equals("NotMy")) {
			user.setSettings_team(item);
		}
		if (item.equals("Yes")) {
			user.setSettings_visited(item);
		}
		if (item.equals("No")) {
			user.setSettings_visited(item);
		}
		if (item.equals("name")) {
			DataClass.SortType = "name";
		}
		if (item.equals("distance")) {
			DataClass.SortType = "distance";
		}

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
}
