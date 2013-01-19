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
		if (adapter == null) {
			adapter = new ListAdapter(this, initModel());
		} else {
			adapter = new ListAdapter(this, DataClass.caches);
		}

		setListAdapter(adapter);

	}

	private List<Model> initModel() {

		DataClass.caches.add(addModel(new Cache("Cache10", new GeoPoint(
				54768637, -1573577), "TestCache1")));
		DataClass.caches.add(addModel(new Cache("Cache20", new GeoPoint(
				41768637, -1401577), "TestCache2")));
		DataClass.caches.add(addModel(new Cache("Cache30", new GeoPoint(
				53768637, -1673577), "TestCache3")));

		// Initially select one of the items
		DataClass.caches.get(1).setSelected(true);
		return DataClass.caches;
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