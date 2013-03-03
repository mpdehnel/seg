package com.vectorone;

import java.util.LinkedList;
import java.util.List;

import com.data.Cache;
import com.data.DataClass;
import com.data.ListAdapter;
import com.data.Model;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class CacheSelectActivity extends ListActivity {

	/** Called when the activity is first created. */

	private static ArrayAdapter<Model> adapter;
	private List<Model> caches;
	

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getListView().setBackgroundDrawable(
				getResources().getDrawable(R.drawable.background));
		caches = DataClass.getcacheswithfilter();
		if (caches.size() == 0) {
			Toast.makeText(getApplicationContext(),
					"No Cache fullfills filters", Toast.LENGTH_LONG).show();
		} else {
			
			adapter = new ListAdapter(this, caches);// DataClass.caches);
			setListAdapter(adapter);
			getListView().setOnItemClickListener(clickhandler);
		}

	}

	@Override
	public void onBackPressed() {
		DataClass.selectedCaches = new LinkedList<Cache>();
		for (int i = 0; i < caches.size(); i++) {
			if (caches.get(i).isSelected())
				DataClass.selectedCaches.add(caches.get(i).getCach());
		}
		startActivity(new Intent(getApplicationContext(), MenuActivity.class));
		super.onBackPressed();
	}
	private OnItemClickListener clickhandler=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			
		}
	};

}