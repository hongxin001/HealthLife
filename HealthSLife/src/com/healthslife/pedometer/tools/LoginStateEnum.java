package com.healthslife.pedometer.tools;

public enum LoginStateEnum {
	SUCCESS("YK"),
	PASSWORD_ERROR("LR"),
	OFF_LINE("LO"),
	PASSWORD_CHANGED("LE");
	
	private String nameString;
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	LoginStateEnum(String str){
		this.setNameString(str);
	}
	
	@Override
	public String toString() {
		return getNameString();
	}
}
