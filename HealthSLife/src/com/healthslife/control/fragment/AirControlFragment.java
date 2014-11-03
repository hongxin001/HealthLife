package com.healthslife.control.fragment;

import com.healthslife.R;
import com.healthslife.control.tools.ControlTools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AirControlFragment extends Fragment implements OnClickListener {
	Button Btn1;
	Button Btn2;
	Button Btn3;
	Button Btn4;
	Button Btn5;
	Button Btn6;
	Button Btn7;
	Button Btn8;
	private ControlTools tools;
	private String answer;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		tools = new ControlTools();
		tools.init(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = (View) inflater.inflate(R.layout.fragment_air_control,
				container, false);
		Btn1 = (Button) v.findViewById(R.id.btn1);
		Btn2 = (Button) v.findViewById(R.id.btn2);
		Btn3 = (Button) v.findViewById(R.id.btn3);
		Btn4 = (Button) v.findViewById(R.id.btn4);
		Btn5 = (Button) v.findViewById(R.id.btn5);
		Btn6 = (Button) v.findViewById(R.id.btn6);
		Btn7 = (Button) v.findViewById(R.id.btn7);
		Btn8 = (Button) v.findViewById(R.id.btn8);
		Btn1.setOnClickListener(this);
		Btn2.setOnClickListener(this);
		Btn3.setOnClickListener(this);
		Btn4.setOnClickListener(this);
		Btn5.setOnClickListener(this);
		Btn6.setOnClickListener(this);
		Btn7.setOnClickListener(this);
		Btn8.setOnClickListener(this);
		return v;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn1:
			answer = tools.airControl(1);
			break;
		case R.id.btn2:
			answer = tools.airControl(2);
			break;
		case R.id.btn3:
			answer = tools.airControl(3);
			break;
		case R.id.btn4:
			answer = tools.airControl(4);
			break;
		case R.id.btn5:
			answer = tools.airControl(5);
			break;
		case R.id.btn6:
			answer = tools.airControl(6);
			break;
		case R.id.btn7:
			answer = tools.airControl(7);
			break;
		case R.id.btn8:
			answer = tools.airControl(8);
			break;
		default:
		}

	}

}
