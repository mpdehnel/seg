package com.data;

import com.google.android.maps.GeoPoint;

public class Cache {

	private int _id;
	private GeoPoint geopoint;
	private String descripton;
	private String name;
	private int teamcolor;
	private boolean founded;
	public boolean israted=false;

	public Cache(String name, GeoPoint geopoint, String description,
			boolean founded, int id) {
		this.name = name;
		this.geopoint = geopoint;
		this.descripton = description;
		this.founded = founded;
		this._id = id;
	}

	public Cache() {
		// TODO Auto-generated constructor stub
	}

	public int getID() {
		return this._id;
	}

	public void setID(int id) {
		this._id = id;
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

	public boolean isIslessthanXXXm(int xxx) {
		int distance = SegMathClass.calculateDistance1(DataClass.myGeoPoint,
				this.geopoint);
		if (distance <= xxx) {
			return true;
		} else {
			return false;
		}

	}

}
