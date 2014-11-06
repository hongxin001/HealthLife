package com.healthslife.fragments;

import java.util.Timer;
import java.util.TimerTask;

import com.healthslife.R;
import com.healthslife.widget.CircleProgress;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class HeartRateFragment extends Fragment {
	//layout
	View view;
	//CircleProgress
	CircleProgress mCircleProgress;
	//TextView
	TextView mTextViewNumber;
	ImageButton mImageButtonStart;
	//RelativeLayout contains heartRate wave
	RelativeLayout reLayout;
	HeartRateHandler handler;
	RelativeLayout relativeLayout;
	TimerTask timerTask;
	Timer timer;
	Interpolator interpolator;
	public static HeartRateFragment self;
	public static HeartRateFragment getInstance(){
		if (self == null){
			return self = new HeartRateFragment();
			
		}
		return self;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_heart_rate_l, container);
		handler = new HeartRateHandler();
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
		reLayout = (RelativeLayout) view.findViewById(R.id.fragment_heart_rate_l_relativelayout_grid);
	}
	
	/**
	 * a invalid heart peek invoke it to update UI
	 */
	public void haveHeartRatePeek(){
		handler.sendEmptyMessage(0);
	}
	
	private class HeartRateHandler extends Handler{
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				  ImageView imageView = new ImageView(getActivity());
                  imageView.setImageDrawable(getResources().getDrawable(R.drawable.rate_src));
                  RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
                          ,RelativeLayout.LayoutParams.WRAP_CONTENT);
                  imageView.setLayoutParams(lp);
                  imageView.setX(-120f);
                  imageView.setScaleX(2f);
                  relativeLayout.addView(imageView);
                  imageView.animate().x(1200f);
                  imageView.animate().setInterpolator(interpolator);
                  imageView.animate().setDuration(3000);
                  imageView.animate().start();
				break;

			default:
				break;
			}
		}
	}
}
