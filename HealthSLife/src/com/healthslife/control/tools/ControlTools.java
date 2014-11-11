package com.healthslife.control.tools;

import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.healthslife.setting.AppSetting;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.StrictMode;
import android.util.Log;

public class ControlTools {
	private DatagramSocket ds = null;
	private WifiManager.MulticastLock lock;
	private ExecutorService executorService;
	private RemoteControlClient client;
	private SendCommandLine line;
	private String message;
	private ReceiveCommandLine answer;
	public void init(Context ctx) {
		this.executorService = Executors.newFixedThreadPool(5);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		WifiManager manager = (WifiManager) ctx
				.getSystemService((Context.WIFI_SERVICE));
		lock = manager.createMulticastLock("testWifi");
		
		client = RemoteControlClient
				.getInstance(ctx);
		client.config("115.28.45.241", "59995", AppSetting.ACCOUNT_N, AppSetting.PSW_N);
		line = new SendCommandLine();
	}
	
	public LoginStateEnum verify(){
		line.setControlName(ControlNameEnum.LOGIN);
		executorService.submit(new Runnable() {

			@Override
			public void run() {
				message = client.send(line);
				Log.v("message:", message);
			}
		});
		ReceiveCommandLine l = new ReceiveCommandLine(message);
		return l.getLoginState();
	}
	
	
	public String airControl(int i) {
		switch (i) {
		case 0:
			line.setControlName(ControlNameEnum.CONTROL).setAirConditionState(AirConditionStateEnum.CONTROL_ONE);
			break;
		case 1:
			line.setControlName(ControlNameEnum.CONTROL).setAirConditionState(AirConditionStateEnum.CONTROL_TWO);
			break;
		case 2:
			line.setControlName(ControlNameEnum.CONTROL).setAirConditionState(AirConditionStateEnum.CONTROL_THREE);
			break;
		case 3:
			line.setControlName(ControlNameEnum.CONTROL).setAirConditionState(AirConditionStateEnum.CONTROL_FOUR);
			break;
		case 4:
			line.setControlName(ControlNameEnum.CONTROL).setAirConditionState(AirConditionStateEnum.CONTROL_FIVE);
			break;
		case 5:
			line.setControlName(ControlNameEnum.CONTROL).setAirConditionState(AirConditionStateEnum.CONTROL_SIX);
			break;
		case 6:
			line.setControlName(ControlNameEnum.CONTROL).setAirConditionState(AirConditionStateEnum.CONTROL_SEVEN);
			break;
		case 7:
			line.setControlName(ControlNameEnum.CONTROL).setAirConditionState(AirConditionStateEnum.CONTROL_EIGHT);
			break;
		default:
			break;
		}
		executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				message = client.send(line);
				Log.v("message:",message );
			}
		});
		return message;
	}

	public String lightControl(int i,int port) {
		if(i == 0){
			line.setControlName(ControlNameEnum.CONTROL).setSwitchState(port,SwitchStateEnum.SWITCH_OFF);
		}else{
			line.setControlName(ControlNameEnum.CONTROL).setSwitchState(port,SwitchStateEnum.SWITCH_ON);
		}
		executorService.submit(new Runnable() {
			
			@Override
			public void run() {
				message = client.send(line);
				Log.v("message:","wei:"+message );
			}
		});
		return message;
	}
	
	public void destroy(){
		
	}
	
	
	
}
