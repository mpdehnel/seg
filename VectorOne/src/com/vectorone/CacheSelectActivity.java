package com.vectorone;

import java.util.ArrayList;
import java.util.List;

import com.data.ListAdapter;
import com.data.Model;

import android.app.ListActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;
import android.widget.GridLayout;
import android.widget.Toast;

public class CacheSelectActivity extends ListActivity {

	/** Called when the activity is first created. */
	private static List<Model> list = new ArrayList<Model>();
	private static ArrayAdapter<Model> adapter;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		if (adapter == null) {
			adapter = new ListAdapter(this, initModel());
		} else {
			adapter = new ListAdapter(this, list);
		}

		setListAdapter(adapter);

	}

	private List<Model> initModel() {

		list.add(addModel("Cache 1   4.2    12°"));
		list.add(addModel("Cache 2"));
		list.add(addModel("Cache 3"));

		// Initially select one of the items
		list.get(1).setSelected(true);
		return list;
	}

	private List<Model> initModel(String[] items) {
		for (int j = 0; j < items.length; j++) {

			list.add(addModel(items[j]));
		}
		return list;
	}

	public void addListItem(String item) {
		list.add(addModel(item));
	}

	private Model addModel(String s) {
		return new Model(s);
	}

}