package com.vectorone;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.data.Cache;
import com.data.DataClass;
import com.data.ListAdapter;
import com.data.Model;
import com.google.android.maps.GeoPoint;

import android.app.ListActivity;
import android.os.Bundle;
import android.provider.ContactsContract.Contacts.Data;

import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.Toast;

public class CacheSelectActivity extends ListActivity {

	/** Called when the activity is first created. */

	private static ArrayAdapter<Model> adapter;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		adapter = new ListAdapter(this, DataClass.caches);

		setListAdapter(adapter);

	}

	private List<Model> initModel(Cache[] items) {
		for (int j = 0; j < items.length; j++) {

			DataClass.caches.add(addModel(items[j]));
		}
		return DataClass.caches;
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

		super.onBackPressed();
	}

}