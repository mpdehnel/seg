package com.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.vectorone.R;

public class DataClass {
	public static LinkedList<Cache> selectedCaches = new LinkedList<Cache>();
	public static User user;
	public static String log;
	public static List<Model> caches = new ArrayList<Model>();

	public DataClass() {
		
		
		selectedCaches.add(new Cache("Cache1", new GeoPoint((int) (54 * 1E6),
				(int) (-3 * 1E6)), "test1"));
		selectedCaches.add(new Cache("Cache2", new GeoPoint(0, 0), "test2"));
		
		
		user = new User("Robert", "robby", "RED", 12, 1245, R.drawable.avatar);
		for (int i = 0; i < 300; i++)
			log = log + i + "\n";
	}
	
	public static void clear() {
		// TODO; clear the recived data;
		// call by logout button meneu screen

	}
	
	
}
