package com.data;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
	public static int mylat=0;
	public static GeoPoint myGeoPoint=new GeoPoint(0, 0);
	public static GeoPoint routingpoint=new GeoPoint(0, 0);
	public static int mylng=0;
	public static int routing;
	public static String server = "http://www.netroware.co.uk/test/";
	public static int greenportion = 25;
	public static int redportion = 25;
	public static int purpleportion = 25;
	public static int blueportion = 25;
	public static String SortType="name";

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
					Log.i("WIFI", "WIFI");
				}
			if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
				if (ni.isConnected()) {
					haveConnectedMobile = true;
					Log.i("WIFI", "Mobile");
				}
		}
		return haveConnectedWifi || haveConnectedMobile;
	}

	public static List<Model> getcacheswithfilter() {
		List<Model> filterCachs= new LinkedList<Model>();
		
		String filterunit = user.getSettings_unit();
		String filtermaxDistance = user.getSettings_maxdistance();
		String filterTeam = user.getSettings_team();
		String filterVisited = user.getSettings_visited(); // Yes No All

		for (int i = 0; i < caches.size(); i++) {
			Cache todecide = caches.get(i).getCach();
			Model addtolist = caches.get(i);
			int maxdistance;
			int distance;

			if (filtermaxDistance.equals("All")) {
				maxdistance = Integer.MAX_VALUE;
			} else {
				maxdistance = Integer.parseInt(filtermaxDistance.substring(1));
			}
			if (filterunit.equals("m/km")) {
				distance = SegMathClass.calculateDistanceinMeter(myGeoPoint,
						todecide.getGeopoint());
			} else {
				distance = SegMathClass.calculateDistanceinFeet(myGeoPoint,
						todecide.getGeopoint());
			}
			boolean checkdistance = false;
			if (distance < maxdistance) {
				checkdistance = true;
			}

			boolean Checkteam = false;
			if (filterTeam.equals("My")
					&& todecide.getTeamcolour() == user.getTeam()) {
				Checkteam = true;
			} else if (filterTeam.equals("NotMy")
					&& todecide.getTeamcolour() != user.getTeam()) {
				Checkteam = true;
			} else if (filterTeam.equals("All")) {
				Checkteam = true;
			}

			boolean checkVisited = false;
			if (filterVisited.equals("Yes") && todecide.isFound()) {
				checkVisited = true;
			} else if (filterVisited.equals("No") && !todecide.isFound()) {
				checkVisited = true;
			} else if (filterVisited.equals("All")) {
				checkVisited = true;
			}
			if(Checkteam&&checkdistance&&checkVisited){
				filterCachs.add(addtolist);
			}
		}
		
		
		
		
		Collections.sort(filterCachs);
	
		return filterCachs;
	}

	

}
