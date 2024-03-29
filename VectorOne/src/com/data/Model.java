package com.data;

public class Model {

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

}