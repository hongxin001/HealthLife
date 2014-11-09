package com.healthslife.pedometer.tools;

public enum ControlNameEnum {
	CONTROL("LK"),
	LOGIN("LD"),
	CHANGE_PASSWORD("LE"),
	ERROR_PASSWORD("LR"),
	OFF_LINE("LO");
	
	private String name;
	
	ControlNameEnum(String str){
		this.setName(str);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Override 
	public String toString() {
		return getName();
	}
}
