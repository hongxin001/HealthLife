package com.healthslife.control.tools;


public enum CommandLinePrefixEnum {
	//control name prefix
	CONTROL_NAME_PREFIX	("|SL"),	
	//user name prefix 
	USERNAME_TITLE("|ID"),
	//password prefix
	PASSWORD_TITLE("|KY"),	
	//new password prefix
	NEW_PASSWORD_TITLE("OY"),	
	//room number prefix 
	ROOM_NUMBER_TITLE("|RM"),
	//air condition state prefix
	AIR_CONDITION_STATE_TITLE("|KT"),	
	//switch state prefix
	SWITCH_STATE_TITLE("|JK");
	
	private String name;

	CommandLinePrefixEnum(String str){
		setName(str);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	@Override
	public String toString() {
		return this.name;
	}
	
	
	
}
