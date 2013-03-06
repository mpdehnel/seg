package com.data;

public class User {
	private String username;
	private int id;
	private int team;
	private int totalcaches;
	private int totalpoints;
	private String password;
	private String settings_maxdistance = "All";
	private String settings_unit = "m/km";
	private String settings_team = "All";
	private String Settings_visited = "All";
	private int currentPoints;


	public User(String username, String nickname, int team, int totalchaches,
			int totalpoints, int drawable, int id, String password,int currentpoints) {
		this.id = id;
		this.username = username;
		this.team = team;
		this.totalcaches = totalchaches;
		this.totalpoints = totalpoints;
		this.currentPoints=currentPoints;
		this.password = password;

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

	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
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
		String tmp = "ID:" + id + " Name:" + username + " totalcaches:"
				+ totalcaches + " totalpoints:" + totalpoints + " team:"
				+ team;
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

	public String getSettings_maxdistance() {
		return settings_maxdistance;
	}

	public void setSettings_maxdistance(String settings_maxdistance) {
		this.settings_maxdistance = settings_maxdistance;
	}

	public String getSettings_unit() {
		return settings_unit;
	}

	public void setSettings_unit(String settings_unit) {
		this.settings_unit = settings_unit;
	}

	public String getSettings_team() {
		return settings_team;
	}

	public void setSettings_team(String settings_team) {
		this.settings_team = settings_team;
	}

	public void setSettings_visited(String item) {
		this.Settings_visited = item;
	}

	public String getSettings_visited() {
		return Settings_visited;
	}

	public int getCurrentPoints() {
		return currentPoints;
	}
	
	public void setCurrentPoints(int points) {
		this.currentPoints=points;
	}

}
