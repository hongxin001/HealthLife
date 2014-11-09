package com.healthslife.utils;

import java.util.Calendar;

import com.healthslife.control.tools.AirConditionData;
import com.healthslife.control.tools.ReceiveCommandLine;
import com.healthslife.control.tools.SwitchStateEnum;

public class tools {
	public static SwitchStateEnum getSwitchResult(String i,int position){
		ReceiveCommandLine line = new ReceiveCommandLine(i);
		return line.getSwitchState().get(position);
	}
	public static AirConditionData getAirConditionResult(String i){
		return new ReceiveCommandLine(i).getData();
	}
	
	public static int getSeason(){
		int i = 0;
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH) + 1;
		switch(month){
		case 3:
		case 4:
		case 5:
			i = 1;
			break;
		case 6:
		case 7:
		case 8:
			i = 2;
			break;
		case 9:
		case 10:
		case 11:
			i = 3;
			break;
		case 12:
		case 1:
		case 2:
			i = 4;
			break;
		default:
		}
		return i;
	}
}
