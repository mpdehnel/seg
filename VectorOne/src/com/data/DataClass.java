package com.data;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.R.integer;

import com.google.android.maps.GeoPoint;
import com.vectorone.R;

public class DataClass {
	public static LinkedList<Cache> selectedCaches = new LinkedList<Cache>();
	public static User user;
	public static String log;
	public static List<Model> caches = new ArrayList<Model>();
	public static int mylat;
	public static GeoPoint myGeoPoint;
	public static int mylng;
	public static int routing;

	public DataClass() {

		user = new User("Robert", "robby", "RED", 12, 1245, R.drawable.avatar,1);
		for (int i = 0; i < 300; i++)
			log = log + i + "\n";
	}

	public static void clear() {
		// TODO; clear the recived data;
		// call by logout button meneu screen

	}

	public static int getMylng() {
		return new Integer(mylng);
	}

	public static int getMylat() {
		return new Integer(mylat);
	}

	public static void setMyGeoPoint() {
		myGeoPoint = new GeoPoint(getMylat(), getMylng());
	}

	public static GeoPoint getMyGeoPoint() {
		return myGeoPoint;
	}

	public static void setMylat(int mylat) {
		DataClass.mylat = mylat;
	}

	public static void setMylng(int mylng) {
		DataClass.mylng = mylng;
	}

	public static void addCachesFromDataBase(Cache[] cachesfromDatabase) {
			caches = new LinkedList<Model>();
		for(int i=0;i<cachesfromDatabase.length;i++){
			caches.add(new Model(cachesfromDatabase[i]));
		}
	}

}
