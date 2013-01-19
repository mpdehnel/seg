package com.data;

import com.google.android.maps.GeoPoint;

public class Model {

	private String name;
	private boolean selected;
	private Cache cache;
	private static GeoPoint mylocation = new GeoPoint(134127, 525233);

	public Model(Cache cache) {
		this.name = cache.toString()
				+ SegMathClass.calculateDirection(mylocation,
						cache.getGeopoint())
				+ "--"
				+ SegMathClass.calculateDistance(mylocation,
						cache.getGeopoint());
		this.cache = cache;
		selected = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Cache getCach() {
		// TODO Auto-generated method stub
		return cache;
	}

	public static void setMyPosition(GeoPoint gp) {
		mylocation = gp;
	}

}