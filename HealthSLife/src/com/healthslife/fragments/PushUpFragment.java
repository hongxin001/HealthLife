package com.healthslife.fragments;

import com.healthslife.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class PushUpFragment extends Fragment {
	public static PushUpFragment self;
	public static PushUpFragment getInstance(){
		if (self == null) {
			return self = new PushUpFragment();
		}
		return self;
	}
	//Layout
	View view;
	//Number of Push Up Aim need to do
	TextView mTextViewAim;
	//Number of have done 
	TextView mTextViewHaveDone;
	//Button Ensure
	ImageButton mImageButtonEnsure;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_push_up, null ,false);
		findView();
		setOnClickListener();
		return view;
	}
	private void setOnClickListener(){
		mImageButtonEnsure.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	private void findView(){
		mTextViewAim = (TextView) view.findViewById(R.id.fragment_push_up);
		mTextViewHaveDone = (TextView) view.findViewById(R.id.fragment_push_up_textView_have_done);
		mImageButtonEnsure = (ImageButton)view.findViewById(R.id.fragment_push_up_imageButton_ensure);
	}
}
