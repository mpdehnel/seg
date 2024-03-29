package com.data;

import android.location.Location;

import com.google.android.maps.GeoPoint;

public class SegMathClass {

	public static String calculateDistance(GeoPoint start, GeoPoint target) {
		Location A = new Location("A");
		A.setLatitude(((double) start.getLatitudeE6()) / 1E6);
		A.setLongitude(((double) start.getLongitudeE6()) / 1E6);
		Location B = new Location("B");
		B.setLatitude(((double) target.getLatitudeE6()) / 1E6);
		B.setLongitude(((double) target.getLongitudeE6()) / 1E6);
		if (Math.round(A.distanceTo(B)) > 5000) {
			return Math.round(A.distanceTo(B))/1000 + "km";
		} else {
			return Math.round(A.distanceTo(B)) + "m";
		}

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
