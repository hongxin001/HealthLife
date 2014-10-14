package com.healthslife.control.tools;
public enum SwitchStateEnum {
	SWITCH_ON('Z'),
    SWITCH_OFF('A'),
    SWITCH_ZERO('0');
	
	private char name;
	
	SwitchStateEnum(char str){
		this.setName(str);
	}

	public char getName() {
		return name;
	}

	public void setName(char name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return String.valueOf(getName());
	}
}
