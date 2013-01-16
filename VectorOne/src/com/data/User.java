package com.data;

public class User {
	private String username;
	private String nickname;
	private String team;
	private int totalcaches;
	private int totalpoints;
	private int image;

	public User(String username, String nickname, String team,
			int totalchaches, int totalpoints, int drawable) {
		this.image = drawable;
		this.nickname = nickname;
		this.username = username;
		this.team = team;
		this.totalcaches = totalchaches;
		this.totalpoints = totalpoints;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getTotalchaches() {
		return totalcaches;
	}

	public void setTotalchaches(int totalchaches) {
		this.totalcaches = totalchaches;
	}

	public int getTotalpoints() {
		return totalpoints;
	}

	public void setTotalpoints(int totalpoints) {
		this.totalpoints = totalpoints;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

}
