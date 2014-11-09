package com.healthslife.control.fragment;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.healthslife.R;
import com.healthslife.control.tools.AirConditionStateEnum;
import com.healthslife.control.tools.ControlNameEnum;
import com.healthslife.control.tools.ControlTools;
import com.healthslife.control.tools.LoginStateEnum;
import com.healthslife.control.tools.RemoteControlClient;
import com.healthslife.control.tools.SendCommandLine;
import com.healthslife.control.tools.SwitchStateEnum;

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

public class LightControlFragment extends Fragment implements OnClickListener {
	Button openBtn;
	Button closeBtn;
	ExecutorService executorService;

    
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = (View)inflater.inflate(R.layout.fragment_light_control, container,false);
		closeBtn = (Button) v.findViewById(R.id.light_closebtn);
		openBtn = (Button) v.findViewById(R.id.light_openbtn);
		closeBtn.setOnClickListener(this);
		openBtn.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.light_openbtn:
			sendLine(1);
			break;

		case R.id.light_closebtn:
			sendLine(0);
			break;

		default:
			break;
		}
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
	}
	
	void sendLine(int i) {
		String result = null;
		final int num = i;
		ControlTools tools = new ControlTools();
		tools.init(getActivity());
		String answer = tools.lightControl(i,3);
		
	}
}
