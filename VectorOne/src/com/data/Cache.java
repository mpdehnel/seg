package com.data;

import com.google.android.maps.GeoPoint;

public class Cache {

	private GeoPoint geopoint;
	private String descripton;
	private String name;
	private int teamcolor;

	public Cache(String name, GeoPoint geopoint, String description) {
		this.name = name;
		this.geopoint = geopoint;
		this.descripton = description;
	}

	public String getDescripton() {
		
		if(DataClass.myGeoPoint!=null){
		
		return descripton
				+ "\n"
				+ "Distance:   "
				+ SegMathClass.calculateDistance(DataClass.myGeoPoint,
						this.geopoint) +"km"+ "\n" + "Direction:   "+SegMathClass.calculateDirection(DataClass.myGeoPoint, this.geopoint);
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

}
