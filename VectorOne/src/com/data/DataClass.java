package com.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import android.R.integer;
import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.vectorone.R;

public class DataClass {
	public static String unit = "km";
	public static LinkedList<Cache> selectedCaches = new LinkedList<Cache>();
	public static User user;
	public static String log = "Log-Diary";
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

	public static YesNoIDontCare visited = YesNoIDontCare.IDONTCARE;
	public static YesNoIDontCare myteam = YesNoIDontCare.IDONTCARE;
	public static int distance;

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

	public static void addCachesFromDataBase(Cache[] cachesfromDatabase) {
		caches = new LinkedList<Model>();
		for (int i = 0; i < cachesfromDatabase.length; i++) {
			caches.add(new Model(cachesfromDatabase[i]));
		}
	}

	public static void addtolog(String msg) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd @ HH:mm:ss");
		Date now = new Date();
		log = df.format(now) + ":" + msg + "\n";
	}

	public static List<Model> getcacheswithfilter() {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean haveNetworkConnection(Context cx) {
		boolean haveConnectedWifi = false;
		boolean haveConnectedMobile = false;

		ConnectivityManager cm = (ConnectivityManager) cx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo[] netInfo = cm.getAllNetworkInfo();
		for (NetworkInfo ni : netInfo) {
			if (ni.getTypeName().equalsIgnoreCase("WIFI"))
				if (ni.isConnected()) {
					haveConnectedWifi = true;
					Log.i("MAIN", "WIFI");
				}
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected()) {
					haveConnectedMobile = true;
					Log.i("MAIN", "Mobile");
				}
		}
		return haveConnectedWifi || haveConnectedMobile;
	}

}
