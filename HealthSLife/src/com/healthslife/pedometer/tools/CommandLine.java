package com.healthslife.pedometer.tools;

public class CommandLine {

	
	public String username;
	public String password;
	public String newPassword;
	public String roomNumber;
	public String controlName;
	public String switchState;
	public String airConditionState;
	public CommandLine() {
		this.roomNumber = RoomNumberEnum.ROOM_ONE.toString();
        this.controlName = ControlNameEnum.CONTROL.toString();
        this.switchState = "0000000000000000";
        this.username = null;
        this.password = null;
        this.newPassword = null;
        this.airConditionState = null;
	}
	

}
