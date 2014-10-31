package com.healthslife.activitys;


import org.w3c.dom.Text;

import com.healthslife.R;
import com.healthslife.R.id;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserStateActivity extends BaseActivity {
	RelativeLayout mButtonExercise;
	RelativeLayout mButtonHeartRate;
	RelativeLayout mButtonHouseConl;
	RelativeLayout mButtonDataCenter;
	RelativeLayout mButtonSetting;
	TextView mTextViewTelephoneNumber;
	TextView mTextViewCalorie;
	TextView mTextViewHeartRate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_state);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		findView();
		setNumber();
		OnClick();
	}
	private void findView(){
		mButtonExercise = (RelativeLayout) findViewById(R.id.exercise);
		mButtonHeartRate = (RelativeLayout) findViewById(R.id.heartrate);
		mButtonHouseConl = (RelativeLayout) findViewById(R.id.house_cont);
		mButtonDataCenter = (RelativeLayout) findViewById(R.id.data_center);
		mButtonSetting = (RelativeLayout) findViewById(R.id.setting);
		mTextViewTelephoneNumber = (TextView) findViewById(R.id.telephone_number);
		mTextViewCalorie = (TextView) findViewById(R.id.calorie);
		mTextViewHeartRate = (TextView) findViewById(R.id.heartRate);
	}
	
	private void setNumber(){
		setTelephoneNumber();
		setCarolie();
		setHeartRate();
	}
	private void setTelephoneNumber(){
		mTextViewTelephoneNumber.setText(""+1306180386);
	}
	
	private void setCarolie(){
		mTextViewCalorie.setText("31");
	}
	
	private void setHeartRate(){
		mTextViewHeartRate.setText("75");
	}
	private void OnClick(){
		mButtonExercise.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(new Intent(UserStateActivity.this,));
				overridePendingTransition(R.anim.in_from_right,R.anim.out_to_left);
			}
		});
		
		mButtonHeartRate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {	
			}
		});
		
		mButtonHouseConl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		mButtonDataCenter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
			}
		});
		mButtonSetting.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
			}
		});
	}
	
	
}
