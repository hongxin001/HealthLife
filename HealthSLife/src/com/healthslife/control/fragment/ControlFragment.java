package com.healthslife.control.fragment;

import java.net.DatagramSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.healthslife.R;
import com.healthslife.control.tools.AirConditionStateEnum;
import com.healthslife.control.tools.ControlNameEnum;
import com.healthslife.control.tools.RemoteControlClient;
import com.healthslife.control.tools.SendCommandLine;
import com.healthslife.control.tools.UDPreceiver;
import com.healthslife.control.tools.UdpHelper;
import com.healthslife.setting.AppSetting;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class ControlFragment extends Fragment {
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn7;
	private Button btn8;
	ExecutorService executorService;
	private UdpHelper helper;
	Thread tReceived;
	int mMessageCountInt = 0;
	DatagramSocket server;
	String mdata = "";
	Thread mReceiveThread;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		WifiManager manager = (WifiManager) this.getActivity()
				.getSystemService((Context.WIFI_SERVICE));
		WifiManager.MulticastLock lock = manager
				.createMulticastLock("testWifi");

		helper = new UdpHelper(manager);

		// 传递WifiManager对象，以便在UDPHelper类里面使用MulticastLock
		// helper.addObserver(MsgReceiveService.this);

		executorService = Executors.newFixedThreadPool(5);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = (View) inflater.inflate(R.layout.fragment_control, container,
				false);
		btn1 = (Button) v.findViewById(R.id.button1);
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// UDPreceiver udp = new UDPreceiver(getActivity(),"");
				// udp.startMessageReceiver();
				// Runnable run = udp.getIncomingMessageAnalyseRunnable();

				executorService.submit(new Runnable() {
					@Override
					public void run() {
						Context ctx = (Context) getActivity();
						RemoteControlClient client = RemoteControlClient
								.getInstance(ctx);
						client.config("115.28.45.241", "59995", AppSetting.ACCOUNT_N, AppSetting.PSW_N);
						SendCommandLine line = new SendCommandLine();
						line.setControlName(ControlNameEnum.CONTROL)
								.setAirConditionState(
										AirConditionStateEnum.CONTROL_ONE)
								.setUsername(AppSetting.ACCOUNT_N).setPassword(AppSetting.PSW_N);

						Log.v("string", line.getSendCommandString());
						// UdpHelper helper = new UdpHelper();

						client.send(line);

						// helper.StartListen();
					}
				});
			}
		});
		return v;
	}
}
