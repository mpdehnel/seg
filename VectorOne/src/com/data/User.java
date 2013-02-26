package com.data;

import com.vectorone.R;

public class User {
	private String username;
	private String nickname;
	private int id;
	private String team;
	private int totalcaches;
	private int totalpoints;
	private String password;

	public User(String username, String nickname, String team,
			int totalchaches, int totalpoints, int drawable, int id, String password) {
		this.id = id;
		this.nickname = nickname;
		this.username = username;
		this.team = team;
		this.totalcaches = totalchaches;
		this.totalpoints = totalpoints;
		this.password=password;

	}

	public User() {
		// TODO Auto-generated constructor stub
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

	public int getTotalcaches() {
		return totalcaches;
	}

	public void setTotalcaches(int totalchaches) {
		this.totalcaches = totalchaches;
	}

	public int getTotalpoints() {
		return totalpoints;
	}

	public void setTotalpoints(int totalpoints) {
		this.totalpoints = totalpoints;
	}


	public String toString() {
		String tmp = "ID:" + id + " Name:" + username + " nickname:" + nickname
				+ " totalcaches:" + totalcaches + " totalpoints:" + totalpoints
				+ " teamcolor:" + team;
		return tmp;

	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setId(int id) {
		this.id = id;
	}
	


}
