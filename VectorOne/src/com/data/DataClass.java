package com.data;

import java.util.LinkedList;

import com.google.android.maps.GeoPoint;
import com.vectorone.R;

public class DataClass {
public static LinkedList<Cache> caches= new LinkedList<Cache>();
public static User user;
public static String log;


public DataClass() {
	caches.add(new Cache("Cache1", new GeoPoint((int) (54*1E6), (int) (-3*1E6)), "test1"));
	caches.add(new Cache("Cache2", new GeoPoint(0, 0),"test2"));
	user= new User("Robert", "robby", "RED", 12, 1245, R.drawable.avatar);
	for(int i=0; i<300;i++)
		log=log+i+"\n";
	
	
}


public static void clear() {
	//TODO; clear the recived data;
	//call by logout button meneu screen
	
}
}
