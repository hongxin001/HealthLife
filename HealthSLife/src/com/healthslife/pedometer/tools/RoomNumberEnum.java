package com.healthslife.pedometer.tools;

public enum RoomNumberEnum {
	ROOM_ONE("00"),
    ROOM_TWO("01"),
    ROOM_THREE("02"),
    ROOM_FOUR("03"),
    ROOM_FIVE("04"),
    ROOM_SIX("05");
	
	private String name;
	
	RoomNumberEnum(String str){
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
    	return this.getName();
    }
    
}
