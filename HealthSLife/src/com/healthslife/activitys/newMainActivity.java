package com.healthslife.activitys;

import com.healthslife.R;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class newMainActivity extends Activity {
	
	DrawerLayout drawer;
	RelativeLayout mButtonExercise;
	RelativeLayout mButtonHeartRate;
	RelativeLayout mButtonHouseConl;
	RelativeLayout mButtonDataCenter;
	RelativeLayout mButtonSetting;
	TextView mTextViewTelephoneNumber;
	TextView mTextViewCalorie;
	TextView mTextViewHeartRate;
	/**
	 * Action Bar Sliding drawer
	 */
	ImageButton open_menu;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏
		setContentView(R.layout.activity_new_main);
		setActionBarLayout();
		findDrawerView();
		setNumber();
		OnClick();	
	}
	
	public void setActionBarLayout() {
        ActionBar actionBar = getActionBar();
        if (null != actionBar) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = mInflater.inflate(R.layout.vice_actionbar, null);
            open_menu = (ImageButton) view.findViewById(R.id.sliding_drawer);
            TextView viceText = (TextView) view.findViewById(R.id.vice_text);
            viceText.getPaint().setFakeBoldText(true);
            ActionBar.LayoutParams layout = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(view, layout);
        }
    }
	
	
	private void findDrawerView(){
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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