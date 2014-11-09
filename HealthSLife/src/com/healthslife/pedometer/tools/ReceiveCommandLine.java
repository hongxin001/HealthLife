package com.healthslife.pedometer.tools;
import java.util.ArrayList;


public class ReceiveCommandLine extends CommandLine {
	
	public String receiveCommandLineString;
	public LoginStateEnum loginState	=	LoginStateEnum.SUCCESS;
	public AirConditionData data=null;
	public ArrayList<SwitchStateEnum> switchState;
	
	
	public String getReceiveCommandLineString() {
		return receiveCommandLineString;
	}


	public void setReceiveCommandLineString(String receiveCommandLineString) {
		this.receiveCommandLineString = receiveCommandLineString;
	}


	public LoginStateEnum getLoginState() {
		return loginState;
	}


	public void setLoginState(LoginStateEnum logicState) {
		this.loginState = logicState;
	}


	public AirConditionData getData() {
		return data;
	}


	public void setData(AirConditionData data) {
		this.data = data;
	}


	public ArrayList<SwitchStateEnum> getSwitchState() {
		return switchState;
	}


	public void setSwitchState(ArrayList<SwitchStateEnum> switchState) {
		this.switchState = switchState;
	}

	public ReceiveCommandLine() {

	}

	public ReceiveCommandLine(String str) {
		setReceiveCommandLineString(str);
		this.analysisReceivedCommandLine();
	} 
	
	private void analysisReceivedCommandLine() {
		String[] commandArray	=	this.receiveCommandLineString.split("(|)|\\|"); 
		ArrayList<String> commandList =	new ArrayList<String>();
		for (String string : commandArray) {
			if (!string.trim().equals("")) {
				commandList.add(string);
			}
		}
		
		this.analysisLoginState(commandList.get(0));
		
		switch(loginState)
		{
		case SUCCESS:
			break;
		case PASSWORD_ERROR:
			return;
		case OFF_LINE:
			return ;
		case PASSWORD_CHANGED:
			return;
		default:
			break;
			
		}
		
		this.analysisSwitchState(commandList.get(3));
		this.analysisAirConditionData(commandList.get(5));
	}
	
	private void analysisLoginState(String str) {
		String sTmpString	=	str.substring(2-1+1);
		for (LoginStateEnum loginSateEnum : LoginStateEnum.values()) {
			if (loginSateEnum.toString().equals(sTmpString)) {
				this.loginState=loginSateEnum;
			}
		}
	}
	
	private void analysisAirConditionData(String str) {
		int temperature=0;
		int humidity=0;
		
		temperature	=	Integer.parseInt(str.substring(11,2));
		humidity=Integer.parseInt(str.substring(13,3));
		AirConditionData sRet = new AirConditionData(temperature,humidity);
		setData(sRet);
	}
	
	private void analysisSwitchState(String str) {
		char[] sCharArray	=	str.toCharArray();
		for (int i = 0; i < 16; i++) {
			switch(sCharArray[2+i])
			{
			case 'A':
				this.switchState.add(SwitchStateEnum.SWITCH_OFF);
				break;
			case '0':
				this.switchState.add(SwitchStateEnum.SWITCH_ZERO);
				break;
			case 'Z':
				this.switchState.add(SwitchStateEnum.SWITCH_ON);
				break;
			default:
				break;	
			}
		}
	}	
}
