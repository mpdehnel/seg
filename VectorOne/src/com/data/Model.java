package com.data;

import java.util.Comparator;

import com.google.android.maps.GeoPoint;

public class Model implements Comparable<Model> {

	private String name;
	private boolean selected;
	private Cache cache;

	public Model(Cache cache) {
		this.name = cache.getName();
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
		return cache;
	}

	@Override
	public int compareTo(Model another) {
		String sorttype = DataClass.SortType;
		if (sorttype.equals("name")) {
			return cache.getName().compareTo(another.getCach().getName());
		}
		if (sorttype.equals("distance")) {
			int d1 = SegMathClass.calculateDistanceinMeter(
					DataClass.myGeoPoint, cache.getGeopoint());
			int d2 = SegMathClass.calculateDistanceinMeter(
					DataClass.myGeoPoint, another.getCach().getGeopoint());
			if (d1 < d2) {
				return -1;
			} else if (d1 == d2) {
				return 0;
			} else {
				return 1;
			}
		}
		return 0;
	}

}