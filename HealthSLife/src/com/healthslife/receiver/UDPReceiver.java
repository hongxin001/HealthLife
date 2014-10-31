package com.healthslife.receiver;

import com.healthslife.control.tools.ReceiveCommandLine;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class UDPReceiver extends BroadcastReceiver{
	
	private int temp;
	private int humi;
	
	private static final String UDP_BROADCAST = "UDPBroadcast";

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getHumi() {
		return humi;
	}

	public void setHumi(int humi) {
		this.humi = humi;
	}
	
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		String msg = intent.getStringExtra("message");
		Log.i(UDP_BROADCAST, msg);
		ReceiveCommandLine line = new ReceiveCommandLine(msg);
		this.temp=line.getData().getTemperature();
		this.humi=line.getData().getHumidity();
	}
	
	
}
