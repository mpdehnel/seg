package com.data;

public class Cach {

	private double distance;
	private String discripton;
	private String name;
	private double direction;
	private int teamcolor;

	public Cach(String name, double direction, double distance,
			String discription) {
		this.name = name;
		this.direction = direction;
		this.distance = distance;
		this.discripton = discription;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getDiscripton() {
		return discripton;
	}

	public void setDiscripton(String discripton) {
		this.discripton = discripton;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public int getTeamcolor() {
		return teamcolor;
	}

	public void setTeamcolor(int teamcolor) {
		this.teamcolor = teamcolor;
	}
	@Override
	public String toString() {
		
		return this.name+"  "+this.direction+"    "+this.distance;
	}

}
