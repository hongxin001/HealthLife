package com.healthslife.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class AppSetting {
	private static final String PREFERENCE_NAME = "health";
	private static final String COUNT_DOWN = "COUNT_DOWN";
	private static final String RUN_MUSIC = "RUN_MUSIC";
	private static final String RUN_ALARM = "RUN_ALARM";
	private static final String REMOTE_ACCOUNT = "REMOTE_ACCOUNT";
	private static final String REMOTE_PSW = "REMOTE_PSW";
	private static final String REMOTE_LISTEN = "REMOTE_LISTEN";
	
	private static final String TELNUM = "TELNUM";
	private static final String RUN = "RUN";
	private static final String PUSH = "PUSH";
	private static final String GOLF = "GOLF";
	private static final String WEIGHT = "WEIGHT";
	private static final String HEIGHT = "HEIGHT";
	
	private static final String LIGHT = "";
	private Context mContext;

	public AppSetting(Context context) {
		mContext = context.getApplicationContext();
	}

	public int getCountDown() {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		return preferences.getInt(COUNT_DOWN, 5);
	}

	public void setCountDown(int time) {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		Editor editor = preferences.edit();
		editor.putInt(COUNT_DOWN, time);
		editor.commit();
	}

	public boolean getRunMusicState() {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		return preferences.getBoolean(RUN_MUSIC, true);
	}

	public void setRunMusicState(boolean isOn) {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		Editor editor = preferences.edit();
		editor.putBoolean(RUN_MUSIC, isOn);
		editor.commit();
	}

	public boolean getRunAlarmState() {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		return preferences.getBoolean(RUN_ALARM, true);
	}

	public void setRunAlarmState(boolean isOn) {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		Editor editor = preferences.edit();
		editor.putBoolean(RUN_ALARM, isOn);
		editor.commit();
	}
	
	public String getRemoteAccount(){
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		return preferences.getString(REMOTE_ACCOUNT, "00068");
	}
	
	public void setRemoteAccount(String account) {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		Editor editor = preferences.edit();
		editor.putString(REMOTE_ACCOUNT, account);
		editor.commit();
	}
	
	public String getRemotepsw(){
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		return preferences.getString(REMOTE_PSW, "1994");
	}
	
	public void setRemotepsw(String psw) {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		Editor editor = preferences.edit();
		editor.putString(REMOTE_PSW, psw);
		editor.commit();
	}
	
	public boolean getRemoteListen() {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		return preferences.getBoolean(REMOTE_LISTEN, true);
	}

	public void setRemoteListen(boolean isOn) {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		Editor editor = preferences.edit();
		editor.putBoolean(REMOTE_LISTEN, isOn);
		editor.commit();
	}

	public  int getRun() {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		return preferences.getInt(RUN, 0);
	}

	public void setRun(int run) {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		Editor editor = preferences.edit();
		editor.putInt(RUN, run);
		editor.commit();
	}

	public int getPush() {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		return preferences.getInt(PUSH, 0);
	}

	public void setPush(int push) {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		Editor editor = preferences.edit();
		editor.putInt(PUSH,push);
		editor.commit();
	}

	public int getGolf() {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		return preferences.getInt(GOLF, 0);
	}

	public void setGolf(int golf) {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		Editor editor = preferences.edit();
		editor.putInt(GOLF, golf);
		editor.commit();
	}

	public int getWeight() {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		return preferences.getInt(WEIGHT, 0);
	}

	public void setWeight(int weight) {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		Editor editor = preferences.edit();
		editor.putInt(WEIGHT, weight);
		editor.commit();
	}

	public int getHeight() {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		return preferences.getInt(HEIGHT, 0);
	}

	public void setHeight(int height) {
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		Editor editor = preferences.edit();
		editor.putInt(HEIGHT, height);
		editor.commit();
	}

	public String getTelNUM(){
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		return preferences.getString(TELNUM, "");
	}
	
	public void setTelNUM(String telnum){
		SharedPreferences preferences = mContext.getSharedPreferences(PREFERENCE_NAME, 0);
		Editor editor = preferences.edit();
		editor.putString(TELNUM, telnum);
		editor.commit();
	}
	
}
