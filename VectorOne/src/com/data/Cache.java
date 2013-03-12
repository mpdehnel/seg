package com.data;

import com.google.android.maps.GeoPoint;

public class Cache {

	private int _id;
	private GeoPoint geopoint;
	private String descripton;
	private String name;
	private int teamcolor;
	private boolean founded;
	private boolean israted = false;
	private float rating;
	

	private String macAdd = "";

	public Cache(String name, GeoPoint geopoint, String description,
			boolean founded, int id, int teamcolor, String macAdd) {
		this.name = name;
		this.geopoint = geopoint;
		this.descripton = description;
		this.founded = founded;
		this.teamcolor = teamcolor;
		this.macAdd = macAdd;
		this._id = id;
	}

	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public boolean israted() {
		return israted;
	}

	public void setIsrated(boolean israted) {
		this.israted = israted;
	}

	public int getTeamcolor() {
		return teamcolor;
	}

	public String getMacAdd() {
		return macAdd;
	}

	public void setMacAdd(String macAdd) {
		this.macAdd = macAdd;
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
		String value=""; 
		if (DataClass.myGeoPoint != null) {
			value = descripton
					+ "\n"
					+ "Distance:   "
					+ SegMathClass.calculateDistance(DataClass.myGeoPoint,
							this.geopoint)
					+ "\n"
					+ "Direction:   "
					+ SegMathClass.calculateDirection(DataClass.myGeoPoint,
							this.geopoint) + "\n" + "Location long/lat:   "
					+ this.geopoint.getLongitudeE6() / Math.pow(10, 6) + "/"
					+ this.geopoint.getLatitudeE6() / Math.pow(10, 6) + "\n"
					+ "Team:" + this.getTeamcolour() + "\n"+"Rating:"+this.getRating()+"\n";

		}
		if (macAdd != "") {
			value = value + "MacAddress: " + this.macAdd;
		}

		return value;
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
		int distance = SegMathClass.calculateDistanceinMeter(
				DataClass.myGeoPoint, this.geopoint);
		if (distance <= xxx) {
			return true;
		} else {
			return false;
		}

	}

	public boolean equals(Cache cache) {
		if (this._id == cache._id) {
			return true;
		}
		return false;
	}
	
	public float getRating() {
		return rating;
	}

	public void setRating(Float float1) {
		this.rating = float1;
	}
	public void setRated(Boolean b){
		this.israted=b;
	}

}
