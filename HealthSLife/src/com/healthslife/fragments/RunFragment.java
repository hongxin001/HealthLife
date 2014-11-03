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

public class RunFragment extends Fragment {
	// 3 button at bottom
	ImageButton mImageRun;
	ImageButton mImageGolf;
	ImageButton mImagePushUp;
	//Aim
	TextView mTextViewAim;
	//Calorie already burnt;
	TextView mTextViewBurnt;
	//Circle
	CircleProgress mCircleProgress;
	//layout
	View view;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
	            return null;
	    }
		view = inflater.inflate(R.layout.fragment_run_lei, container);
		findView();
		CompleteListener();
		return view;
	}
	
	public void setCircleProgress(float percent){
		mCircleProgress.slideToProgress((int)(percent*mCircleProgress.getMax()));
	}
	private void CompleteListener(){
		mCircleProgress.setCompleteListener(new CircleProgress.CompleteListener() {
			@Override
			public void complete() {
				
				
			}
		});
	}
	
	private void findView(){
		mImageRun = (ImageButton) view.findViewById(R.id.run_button);
		mImageGolf = (ImageButton) view.findViewById(R.id.golf_button);
		mImagePushUp = (ImageButton) view.findViewById(R.id.push_up_button);
		mTextViewAim = (TextView) view.findViewById(R.id.aim_run_calorie);
		mTextViewBurnt = (TextView) view.findViewById(R.id.run_calorie_txt);
		mCircleProgress = (CircleProgress) view.findViewById(R.id.fragment_run_porbar);
	}
	
	
}
