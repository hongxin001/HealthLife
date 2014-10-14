package com.healthslife.control.tools;


public class SendCommandLine extends CommandLine {


	public SendCommandLine() {
		super();
		
	}
	
	
	public SendCommandLine setUsername(String str) {
		this.username=str;
		return this;
	}
	public SendCommandLine setPassword(String str) {
		this.password	=	str;
		return this;
	}
	public  SendCommandLine setNewPassword(String string) {
		this.newPassword	=	string;
		return this;
	}
	
	public SendCommandLine setRoomNumber(RoomNumberEnum roomNumberEnum) {
		this.roomNumber	=	roomNumberEnum.toString();
		return this;
	}
	
	public  SendCommandLine setControlName(ControlNameEnum controlNameEnum) {
		this.controlName	=	controlNameEnum.toString();
		return this;
	}
	
	public  SendCommandLine setAirConditionState(AirConditionStateEnum airConditionStateEnum) {
		this.airConditionState=airConditionStateEnum.toString();
		return this;
	}
	
	public  SendCommandLine setSwitchState(int position,SwitchStateEnum switchStateEnum) {
		if (position>=0) {
			char[] sCharArray	=	this.switchState.toCharArray();
			sCharArray[position]=switchStateEnum.getName();
			this.switchState=String.valueOf(sCharArray);
		}		
		return this;	
	}
	
	public  String getSendCommandString() {
		return "("
				+CommandLinePrefixEnum.CONTROL_NAME_PREFIX.toString()+controlName
				+CommandLinePrefixEnum.USERNAME_TITLE.toString()+username
				+CommandLinePrefixEnum.PASSWORD_TITLE.toString()+password
				+this.getNewPasswordFull()
				+CommandLinePrefixEnum.SWITCH_STATE_TITLE.toString()+switchState
				+this.getAirConditionFull()
				+CommandLinePrefixEnum.ROOM_NUMBER_TITLE.toString()+roomNumber
				+"|)";
	}
	
	private String getNewPasswordFull()
	{
		if (this.newPassword==null) 
			return "";
		else {
			return CommandLinePrefixEnum.NEW_PASSWORD_TITLE.toString()+newPassword;
		}
	}
	
	private String getAirConditionFull() {
		if (this.airConditionState==null) {
			return "";
		}
		else {
			return CommandLinePrefixEnum.AIR_CONDITION_STATE_TITLE.toString()+airConditionState;
		}
	}
	
}
