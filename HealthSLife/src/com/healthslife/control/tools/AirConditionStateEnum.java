package com.healthslife.control.tools;

public enum AirConditionStateEnum {
	CONTROL_ONE("K00"),
    CONTROL_TWO("K01"),
	CONTROL_THREE("K02"),
    CONTROL_FOUR("K03"),
    CONTROL_FIVE("K04"),
    CONTROL_SIX("K05"),
    CONTROL_SEVEN("K06"),
    CONTROL_EIGHT("K07"),
    LEARNING_ONE("S00"),
    LEARNING_TWO("S01"),
    LEARNING_THREE("S02"),
    LEARNING_FOUR("S03"),
    LEARNING_FIVE("S04"),
    LEARNING_SIX("S05"),
    LEARNING_SEVEN("S06"),
    LEARNING_EIGHT("S07");
	
	private String name =null;
	
	AirConditionStateEnum(String str){
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
