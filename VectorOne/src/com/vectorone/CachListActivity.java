package com.vectorone;

import java.util.ArrayList;
import java.util.List;

import com.data.Listadapter;
import com.data.Model;

import android.app.ListActivity;
import android.os.Bundle;

import android.widget.ArrayAdapter;

public class CachListActivity extends ListActivity {

	/** Called when the activity is first created. */

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		// Create an array of Strings, that will be put to our ListActivity
		ArrayAdapter<Model> adapter = new Listadapter(this, getModel());
		setListAdapter(adapter);

	}

	private List<Model> getModel() {
		List<Model> list = new ArrayList<Model>();
		list.add(get("Cach1   4.2    12°"));
		list.add(get("Cach2"));
		list.add(get("Cach3"));
	
		// Initially select one of the items
		list.get(1).setSelected(true);
		return list;
	}

	private Model get(String s) {
		return new Model(s);
	}

}