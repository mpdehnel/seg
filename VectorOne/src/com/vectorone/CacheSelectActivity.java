package com.vectorone;

import java.util.LinkedList;
import com.data.Cache;
import com.data.DataClass;
import com.data.ListAdapter;
import com.data.Model;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;

public class CacheSelectActivity extends ListActivity {

	/** Called when the activity is first created. */

	private static ArrayAdapter<Model> adapter;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		adapter = new ListAdapter(this, DataClass.caches);
		getListView().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.background));
		setListAdapter(adapter);

	}

	public void addListItem(Cache item) {
		DataClass.caches.add(addModel(item));
	}

	private Model addModel(Cache cache) {
		return new Model(cache);
	}

	@Override
	public void onBackPressed() {
		DataClass.selectedCaches = new LinkedList<Cache>();
		for (int i = 0; i < DataClass.caches.size(); i++) {
			if (DataClass.caches.get(i).isSelected())
				DataClass.selectedCaches.add(DataClass.caches.get(i).getCach());
		}
		startActivity(new Intent(getApplicationContext(),
				MenuActivity.class));
		super.onBackPressed();
	}

}