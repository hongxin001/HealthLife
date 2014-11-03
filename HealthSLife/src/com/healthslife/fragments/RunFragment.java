package com.healthslife.fragments;

import com.healthslife.R;
import com.healthslife.widget.CircleProgress;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
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
	//ImageView of Calorie == 1碗米饭
	ImageView mImageViewTip;
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
	/**
	 * sliding percent(%) of Circle with animation
	 * @param percent
	 */
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

	/**
	 * change the Image of Calorie == 1碗 米饭
	 * 1，2，3，4
	 */
	private void changeImage(int num){
		if(mImageViewTip != null){
			switch (num) {
			case 1:
				mImageViewTip.setImageDrawable(getResources().getDrawable(R.drawable.tips1));
				break;
			case 2:
				mImageViewTip.setImageDrawable(getResources().getDrawable(R.drawable.tips2));
				break;
			case 3:
				mImageViewTip.setImageDrawable(getResources().getDrawable(R.drawable.tips3));
				break;
			case 4:
				mImageViewTip.setImageDrawable(getResources().getDrawable(R.drawable.tips4));
				break;
			default:
				break;
			}
		}
	}
	
	private void findView(){
		mImageRun = (ImageButton) view.findViewById(R.id.run_button);
		mImageGolf = (ImageButton) view.findViewById(R.id.golf_button);
		mImagePushUp = (ImageButton) view.findViewById(R.id.push_up_button);
		mTextViewAim = (TextView) view.findViewById(R.id.aim_run_calorie);
		mTextViewBurnt = (TextView) view.findViewById(R.id.run_calorie_txt);
		mCircleProgress = (CircleProgress) view.findViewById(R.id.fragment_run_porbar);
		mImageViewTip = (ImageView) view.findViewById(R.id.image_view_tip);
	}
}
