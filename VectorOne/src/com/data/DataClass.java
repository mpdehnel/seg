package com.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import android.R.integer;

import com.google.android.maps.GeoPoint;
import com.vectorone.R;

public class DataClass {
	public static LinkedList<Cache> selectedCaches = new LinkedList<Cache>();
	public static User user;
	public static String log="Log-Diary";
	public static List<Model> caches = new ArrayList<Model>();
	public static int mylat;
	public static GeoPoint myGeoPoint;
	public static GeoPoint routingpoint;
	public static int mylng;
	public static int routing;
	public static String server = "http://www.netroware.co.uk/test/";
	public static int greenportion = 25;
	public static int redportion = 25;
	public static int purpleportion = 25;
	public static int blueportion = 25;

	public static void clear() {
		selectedCaches = new LinkedList<Cache>();
		caches = new ArrayList<Model>();

		// TODO; clear the recived data;
		// call by logout button meneu screen

	}

	public static int getMylng() {
		return mylng;
	}

	public static int getMylat() {
		return mylat;
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
		for (int i = 0; i < cachesfromDatabase.length; i++) {
			caches.add(new Model(cachesfromDatabase[i]));
		}
	}
	
	public static void addtolog(String msg){
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd @ HH:mm:ss");
		Date now= new Date();
		log=df.format(now)+":"+msg+"\n";
	}

	

}
