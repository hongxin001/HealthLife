package com.healthslife.fragments;

import com.healthslife.R;
import com.healthslife.widget.CircleProgress;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class HeartRateFragment extends Fragment {
	//layout
	View view;
	//CircleProgress
	CircleProgress mCircleProgress;
	//TextView
	TextView mTextViewNumber;
	ImageButton mImageButtonStart;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_heart_rate_l, container);
		findView();
		setOnClick();
		return view;
	}
	private void setOnClick(){
		mImageButtonStart.setOnClickListener(new View.OnClickListener() {		
			@Override
			public void onClick(View v) {
				mImageButtonStart.setImageDrawable(getResources().getDrawable(R.drawable.btn_start_button_selected));			
			}
		});
	}
	private void findView(){
		mTextViewNumber = (TextView) view.findViewById(R.id.heart_rate_txt);
		mCircleProgress = (CircleProgress)view.findViewById(R.id.fragment_heart_rate_porbar);
		mImageButtonStart = (ImageButton)view.findViewById(R.id.fragment_heart_rate_l_start);
	}
}
