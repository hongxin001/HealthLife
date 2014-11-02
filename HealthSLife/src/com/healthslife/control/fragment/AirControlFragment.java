package com.healthslife.control.fragment;

import com.healthslife.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AirControlFragment extends Fragment implements OnClickListener{
	Button closeBtn;
	Button openBtn;
	Button coldBtn;
	Button hotBtn;
	Button upBtn;
	Button downBtn;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = (View)inflater.inflate(R.layout.fragment_air_control,container, false);
		closeBtn = (Button)v.findViewById(R.id.closebtn);
		openBtn = (Button)v.findViewById(R.id.openbtn);
		//coldBtn = (Button)v.findViewById(R.id.coldbtn);
		//hotBtn = (Button)v.findViewById(R.id.hotbtn);
		//upBtn = (Button)v.findViewById(R.id.upbtn);
		//downBtn = (Button)v.findViewById(R.id.downbtn);
		closeBtn.setOnClickListener(this);
		openBtn.setOnClickListener(this);
		coldBtn.setOnClickListener(this);
		hotBtn.setOnClickListener(this);
		upBtn.setOnClickListener(this);
<<<<<<< HEAD
		downBtn.setOnClickListener(this);
=======
		downBtn.setOnClickListener(this);		
>>>>>>> 3609bfb5d68069af724f8038246947df667b0ee8
		return v;
	
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.closebtn:
			break;
		case R.id.openbtn:
			break;
		default:
		}
		
	}
	
}
