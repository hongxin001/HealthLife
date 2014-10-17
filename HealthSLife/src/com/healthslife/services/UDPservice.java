package com.healthslife.services;

import java.util.Observable;
import java.util.Observer;

import com.healthslife.control.tools.UdpHelper;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.IBinder;

public class UDPservice extends Service implements Observer{
	private UdpHelper udphelper;
	private Thread tReceived;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub

		WifiManager manager = (WifiManager) this
				.getSystemService(Context.WIFI_SERVICE);
		udphelper = new UdpHelper(manager);

		// 传递WifiManager对象，以便在UDPHelper类里面使用MulticastLock
		udphelper.addObserver(UDPservice.this);
		tReceived = new Thread(udphelper);
		tReceived.start();
		super.onCreate();
	}

	@Override
	public void update(Observable observable, Object data) {
		// TODO Auto-generated method stub
		
	}
}
