package com.healthslife.fragments;


import com.healthslife.R;
import com.healthslife.widget.CircleProgress;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NewDistanceFragment extends Fragment {
	
	//layout
	View view;
	
	//Searching
	//EditText type in distance
	EditText mEditTextDistance;
	//Search Ensure
	ImageButton mButtonSearch;
	
	//Type selected
	//Two Button Selector below the Circle
	RelativeLayout mButtonDistance;
	TextView mTextViewDistance;//need to be set Number
	RelativeLayout mButtonPace;
	TextView mTextViewPace;//need to be set number
	
	//Circle-->Distance  I use view .setVisible(INVISIBLE|VISIBLE) to control it
	CircleProgress mCircle;
	FrameLayout mFrameLayoutDistance;
	//Number in Circle
	TextView mCircledistance;
	TextView mCircledistanceAim;
	//Circle-->Pace
	CircleProgress mCirclePace;
	FrameLayout mFrameLayoutPace;
	//Number in Circle
	TextView mCircleTextViewPace;
	TextView mCircleTextViewPaceAim;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container ==null) {
			return null;
		}
		view = inflater.inflate(R.layout.fragment_new_distance_fragment, container);
		findView();
		underCircleTwoButtonOnClick();
		SearchOnClick();
		//TODO-I need you set percent of the CircleProgress 
		//              and number of number textViews
		return view;
	}
	
	private void SearchOnClick(){
		mButtonSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				getSearchDestination();
			}
		});
	}
	/**
	 * get destination from editText
	 */
	private String getSearchDestination(){
		return mEditTextDistance.getText().toString();
	}
	
	private void underCircleTwoButtonOnClick(){
		mButtonDistance.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mFrameLayoutDistance.setVisibility(View.VISIBLE);
				mFrameLayoutPace.setVisibility(View.INVISIBLE);
			}
		});
		
		mButtonPace.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mFrameLayoutPace.setVisibility(View.VISIBLE);
				mFrameLayoutDistance.setVisibility(View.INVISIBLE);
			}
		});
	}
	
	private void setDistanceNumber(String number){
		mCircledistance.setText(number);
		mTextViewDistance.setText(number);
	}
	private void setPaceNumber(){
		
	}
	
	
	private void findView(){
		mButtonSearch = (ImageButton) view.findViewById(R.id.ensureSearch_button);
		mEditTextDistance = (EditText) view.findViewById(R.id.editText_distance_run_destance);
		mButtonDistance = (RelativeLayout) view.findViewById(R.id.image_Button_distance);
		mButtonPace = (RelativeLayout) view.findViewById(R.id.image_Button_pace);
		mTextViewDistance   = (TextView) view.findViewById(R.id.distance_bottom_txt);
		mTextViewPace = (TextView) view.findViewById(R.id.pace_bottom_txt);
 		
		mCircle = (CircleProgress) view.findViewById(R.id.fragment_distance_porbar);
		mFrameLayoutDistance = (FrameLayout) view.findViewById(R.id.fragment_distance_run);
 		mCircledistance = (TextView) view.findViewById(R.id.run_distance_txt);
 		mCircledistanceAim = (TextView)view.findViewById(R.id.aim_run_distance);
 		
 		mCirclePace = (CircleProgress) view.findViewById(R.id.fragment_pace_porbar);
 		mFrameLayoutPace = (FrameLayout) view.findViewById(R.id.fragment_pace_run);
 		mCircleTextViewPace = (TextView)view.findViewById(R.id.run_pace_txt);
 		mCircleTextViewPaceAim = (TextView)view.findViewById(R.id.aim_pace_distance);
	}
}