package com.data;

import com.google.android.maps.GeoPoint;

public class Cache {

	private GeoPoint geopoint;
	private String descripton;
	private String name;
	private int teamcolor;
	private boolean islessthan50m;

	private boolean founded;

	public Cache(String name, GeoPoint geopoint, String description,
			boolean founded) {
		this.name = name;
		this.geopoint = geopoint;
		this.descripton = description;
		this.founded = founded;
	}

	public Cache() {
		// TODO Auto-generated constructor stub
	}

	public String getDescripton() {

		if (DataClass.myGeoPoint != null) {

			return descripton
					+ "\n"
					+ "Distance:   "
					+ SegMathClass.calculateDistance(DataClass.myGeoPoint,
							this.geopoint)
					+ "\n"
					+ "Direction:   "
					+ SegMathClass.calculateDirection(DataClass.myGeoPoint,
							this.geopoint);
		}
		return descripton;
	}

	public void setDescripton(String descripton) {
		this.descripton = descripton;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTeamcolour() {
		return teamcolor;
	}

	public void setTeamcolor(int teamcolor) {
		this.teamcolor = teamcolor;
	}

	public GeoPoint getGeopoint() {
		return geopoint;
	}

	public void setGeopoint(GeoPoint geopoint) {
		this.geopoint = geopoint;
	}

	public boolean isFound() {
		return founded;
	}

	public void setfounded(Boolean found) {
		this.founded = found;
	}

	public boolean isIslessthan50m() {
		String distance = SegMathClass.calculateDistance(DataClass.myGeoPoint,
				this.geopoint);
		if (distance.charAt(distance.length() - 2) != 'k') {
			int distanceInMeter = Integer.valueOf(distance.substring(0,
					distance.length() - 1));
			if (distanceInMeter <= 50) {
				setfounded(true);
				return true;
			} else
				return false;
		}

		return false;

	}

}
