package com.healthslife.activitys;

import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.healthslife.R;
import com.healthslife.activitys.BaseActivity;
import com.healthslife.activitys.ExerciseActivity;
import com.healthslife.fragments.HeartRateFragment;
import com.healthslife.fragments.RemoteListFragment;
import com.healthslife.fragments.SettingFragment;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.RemoteCallbackList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
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

public class newMainActivity extends BaseActivity{
	
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
	boolean isOpen;
	
	
	RemoteListFragment mFragmentRemoteList;
	SettingFragment mFragmentSetting;
	
	
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
	                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 去掉信息栏	 	Log.e("TAG","lei");
		setContentView(R.layout.activity_new_main);
		findDrawerView();
		setActionBarLayout();

		setNumber();
		OnClick();	
	}
	
	private RemoteListFragment getRemoteListFragment(){
		if (mFragmentRemoteList == null){
			mFragmentRemoteList = RemoteListFragment.getInstance();
		}
		return mFragmentRemoteList;
	}
	
	private SettingFragment getSettingFragment(){
		if (mFragmentSetting == null){
			mFragmentSetting = SettingFragment.getInstance();
		}
		return mFragmentSetting;
	}
	
	public void setActionBarLayout() {
        ActionBar actionBar = getActionBar();
        if (null != actionBar) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayUseLogoEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
            LayoutInflater mInflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = mInflater.inflate(R.layout.new_main_actionbar, null);
            open_menu = (ImageButton) view.findViewById(R.id.sliding_drawer);
            open_menu.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if (isOpen) {
						drawer.closeDrawers();
						isOpen = false;
					} else {
						drawer.openDrawer(GravityCompat.START);
						isOpen = true;
					}	
				}
			});
            TextView viceText = (TextView) view.findViewById(R.id.vice_text);
            viceText.getPaint().setFakeBoldText(true);
            ActionBar.LayoutParams layout = new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            actionBar.setCustomView(view, layout);
        }
    }
	
	private void selectItem(Fragment f) {
		FragmentManager manager = getSupportFragmentManager();
		manager.beginTransaction().replace(R.id.main_content , f).commit();
	}
	
	@Override
	public void setTitle(CharSequence title) {
		getActionBar().setTitle(title);
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
				setTitle("Running");
				closeDrawer();
			}
		});
		
		mButtonHeartRate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setTitle("HeartRate");
				selectItem(HeartRateFragment.getInstance());
				closeDrawer();
			}
		});
		
		mButtonHouseConl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectItem(getRemoteListFragment());
				setTitle("HouseCtrl");
				closeDrawer();
			}
		});
		mButtonDataCenter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				setTitle("DataCenter");
				closeDrawer();
			}
		});
		mButtonSetting.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				selectItem(getSettingFragment());
				setTitle("Setting");
				closeDrawer();
			}
		});
	}
	
	
	
	
	private void closeDrawer(){
		if(drawer!= null){
			drawer.closeDrawer(GravityCompat.START);
			isOpen = false;
		}
	}
}