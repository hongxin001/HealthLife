package com.healthslife.control.fragment;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.healthslife.R;
import com.healthslife.control.tools.AirConditionStateEnum;
import com.healthslife.control.tools.ControlNameEnum;
import com.healthslife.control.tools.RemoteControlClient;
import com.healthslife.control.tools.SendCommandLine;
import com.healthslife.control.tools.UdpHelper;
import com.healthslife.services.UDPListenerService;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class LearnFragment extends Fragment {
	private Button btn1;
	private Button btn2;
	private Button btn3;
	private Button btn4;
	private Button btn5;
	private Button btn6;
	private Button btn7;
	private Button btn8;
	private String account;
	private String password;
	private ExecutorService executorService;
	private UdpHelper udphelper;
	private Thread tReceived;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		executorService = Executors.newFixedThreadPool(5);
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stu
		View v = (View) inflater.inflate(R.layout.fragment_learn, container,
				false);
		btn1 = (Button) v.findViewById(R.id.lbutton1);

		Context ctx = (Context) getActivity();
		final RemoteControlClient client = RemoteControlClient.getInstance(ctx);
		client.config("115.28.45.241", "59995", "00068", "1994");
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				executorService.submit(new Runnable() {
					@Override
					public void run() {

						SendCommandLine line = new SendCommandLine();
						line.setControlName(ControlNameEnum.CONTROL)
								.setAirConditionState(
										AirConditionStateEnum.LEARNING_ONE)
								.setUsername("00068").setPassword("1994");
						Log.v("string", line.getSendCommandString());
						client.send(line);
					}
				});
				
				// Toast.makeText(getActivity(), "请先连接盒子",
				// Toast.LENGTH_LONG).show();
				
			}
		});
		return v;
	}

}
