package com.data;

import android.location.Location;

import com.google.android.maps.GeoPoint;
import com.vectorone.R.drawable;

public class SegMathClass {

	public static String calculateDistance(GeoPoint start, GeoPoint target) {
		Location A = new Location("A");
		A.setLatitude(((double) start.getLatitudeE6()) / 1E6);
		A.setLongitude(((double) start.getLongitudeE6()) / 1E6);
		Location B = new Location("B");
		B.setLatitude(((double) target.getLatitudeE6()) / 1E6);
		B.setLongitude(((double) target.getLongitudeE6()) / 1E6);
		float distance=Math.round(A.distanceTo(B));
		if (DataClass.unit.equals("km")) {
			if (distance > 5000) {
				return Math.round(distance/ 1000) + "km";
			} else {
				return Math.round(distance)+ "m";
			}
		} else if (DataClass.unit.equals("ft")) {
			
			if(Math.round(distance*3.2808399)>5280){
				return Math.round(distance*3.2808399)/5280+"mi";
			}
			
			
			return Math.round(distance*3.2808399)+"ft";
		}
		return Math.round(distance)+"m";

	}

	public static int calculateDistanceinMeter(GeoPoint start, GeoPoint target) {
		Location A = new Location("A");
		A.setLatitude(((double) start.getLatitudeE6()) / 1E6);
		A.setLongitude(((double) start.getLongitudeE6()) / 1E6);
		Location B = new Location("B");
		B.setLatitude(((double) target.getLatitudeE6()) / 1E6);
		B.setLongitude(((double) target.getLongitudeE6()) / 1E6);
		return (int) Math.round(A.distanceTo(B));

	}
	
	public static int calculateDistanceinFeet(GeoPoint start, GeoPoint target) {
		Location A = new Location("A");
		A.setLatitude(((double) start.getLatitudeE6()) / 1E6);
		A.setLongitude(((double) start.getLongitudeE6()) / 1E6);
		Location B = new Location("B");
		B.setLatitude(((double) target.getLatitudeE6()) / 1E6);
		B.setLongitude(((double) target.getLongitudeE6()) / 1E6);
		return (int) Math.round(A.distanceTo(B)*3.2808399);

	}

	public static String calculateDirection(GeoPoint start, GeoPoint target) {
		Location A = new Location("A");
		A.setLatitude(((double) start.getLatitudeE6()) / 1E6);
		A.setLongitude(((double) start.getLongitudeE6()) / 1E6);
		Location B = new Location("B");
		B.setLatitude(((double) target.getLatitudeE6()) / 1E6);
		B.setLongitude(((double) target.getLongitudeE6()) / 1E6);

		float degree = A.bearingTo(B);
		if (degree < 0)
			degree = 360 + degree;

		String direction = "test";
		if (degree < 22.5 || degree > 337.5)
			direction = "N";
		if (degree >= 22.5 && degree < 67.5)
			direction = "NE";
		if (degree >= 67.5 && degree < 112.5)
			direction = "E";
		if (degree >= 112.5 && degree < 157.5)
			direction = "SE";
		if (degree >= 157.5 && degree < 202.5)
			direction = "S";
		if (degree >= 202.5 && degree < 247.5)
			direction = "SW";
		if (degree >= 247.5 && degree < 292.5)
			direction = "W";
		if (degree >= 292.5 && degree < 337.5)
			direction = "NW";

		degree = Math.round(degree);
		return degree + "°" + direction;
	}
}
