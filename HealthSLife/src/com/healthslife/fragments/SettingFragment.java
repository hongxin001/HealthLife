package com.healthslife.fragments;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.healthslife.R;
import com.healthslife.control.tools.ControlNameEnum;
import com.healthslife.control.tools.RemoteControlClient;
import com.healthslife.control.tools.SendCommandLine;
import com.healthslife.dialog.MyAlertDialog;
import com.healthslife.setting.AppSetting;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SettingFragment extends Fragment {

	private ImageView runAlarmImageView;
	private ImageView runMusicImageView;
	private View aboutUsView;
	private View checkUpdateView;
	private View countDownTimeView;

	private TextView countDownTextView;

	private MyAlertDialog checkUpdatetDialog;
	private MyAlertDialog aboutUsDialog;
	private Dialog countDownTimeDialog;
	private View countDownBtn;
	private View countUpBtn;
	private TextView countDownTxt;
	private AppSetting mAppSetting;
	private AlertDialog.Builder dialog;
	private EditText edit_account;
	private EditText edit_psw;
	private ExecutorService executorService;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_setting, null);
		runAlarmImageView = (ImageView) view
				.findViewById(R.id.setting_run_alarm_img);
		runMusicImageView = (ImageView) view
				.findViewById(R.id.setting_run_music_img);
		aboutUsView = view.findViewById(R.id.setting_about_us_layout);
		checkUpdateView = view.findViewById(R.id.setting_check_update_layout);
		countDownTimeView = view.findViewById(R.id.setting_count_down_layout);
		countDownTextView = (TextView) view
				.findViewById(R.id.setting_count_down_txt);
		mAppSetting = new AppSetting(getActivity());
		setView();
		initDialog();
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		executorService = Executors.newFixedThreadPool(5);
	}

	private void setView() {
		runAlarmImageView.setOnClickListener(myOnClickListener);
		runMusicImageView.setOnClickListener(myOnClickListener);
		if (mAppSetting.getRunAlarmState()) {
			runAlarmImageView.setImageResource(R.drawable.ic_red_switch_on);
		} else {
			runAlarmImageView.setImageResource(R.drawable.ic_red_switch_off);
		}
		if (mAppSetting.getRunMusicState()) {
			runMusicImageView.setImageResource(R.drawable.ic_red_switch_on);
		} else {
			runMusicImageView.setImageResource(R.drawable.ic_red_switch_off);
		}

		aboutUsView.setOnClickListener(myOnClickListener);
		checkUpdateView.setOnClickListener(myOnClickListener);
		countDownTimeView.setOnClickListener(myOnClickListener);
		countDownTextView.setText(String.valueOf(mAppSetting.getCountDown()));
	}

	private void initDialog() {
		checkUpdatetDialog = new MyAlertDialog(
				SettingFragment.this.getActivity());
		checkUpdatetDialog.setTitle("检查版本更新");
		checkUpdatetDialog.setContent("该版本已经是最新版本,无需更新");
		checkUpdatetDialog.setCancelable(true);
		checkUpdatetDialog.setNegativeButtonVisibility(View.GONE);

		aboutUsDialog = new MyAlertDialog(SettingFragment.this.getActivity());
		aboutUsDialog.setTitle("关于我们");
		aboutUsDialog.setContent("ASCII联合工作室");
		aboutUsDialog.setCancelable(true);
		aboutUsDialog.setPositiveButtonVisibility(View.GONE);
		aboutUsDialog.setNegativeButtonVisibility(View.GONE);

		countDownTimeDialog = new Dialog(SettingFragment.this.getActivity(),
				R.style.fullScreenDialog);
		countDownTimeDialog.setCancelable(true);
		View view = LayoutInflater.from(getActivity()).inflate(
				R.layout.layout_set_count_down, null);
		countDownTimeDialog.setContentView(view);
		countDownBtn = view.findViewById(R.id.set_count_down_img);
		countUpBtn = view.findViewById(R.id.set_count_up_img);
		countDownBtn.setOnClickListener(countDownTimeBtnClick);
		countUpBtn.setOnClickListener(countDownTimeBtnClick);
		countDownTxt = (TextView) view
				.findViewById(R.id.set_count_down_time_txt);
		countDownTxt.setText(String.valueOf(mAppSetting.getCountDown()));

		dialog = new AlertDialog.Builder(this.getActivity());
		LayoutInflater inflater = (LayoutInflater) this.getActivity()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.dialog_account_setting, null);
		dialog.setView(layout);
		edit_account = (EditText) layout.findViewById(R.id.edit_account);
		edit_account = (EditText) layout.findViewById(R.id.edit_psw);

		dialog.setPositiveButton("搜索", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				final String account = edit_account.getText().toString();
				final String psw = edit_psw.getText().toString();
				executorService.submit(new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						Context ctx = (Context) getActivity();
						RemoteControlClient client = RemoteControlClient
								.getInstance(ctx);
						client.configNetwork("115.28.45.241", "59995");
						SendCommandLine line = new SendCommandLine();
						line.setControlName(ControlNameEnum.LOGIN)
								.setUsername(account).setPassword(psw);
						Log.v("lineText", line.getSendCommandString());
						client.send(line);
					}
				});

			}
		});
	}

	public OnClickListener myOnClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			if (checkUpdateView == v) {
				checkUpdatetDialog.show();
			} else if (aboutUsView == v) {
				aboutUsDialog.show();
			} else if (countDownTimeView == v) {
				countDownTxt
						.setText(String.valueOf(mAppSetting.getCountDown()));
				countDownTimeDialog.show();
			} else if (runAlarmImageView == v) {
				if (mAppSetting.getRunAlarmState()) {
					mAppSetting.setRunAlarmState(false);
					runAlarmImageView
							.setImageResource(R.drawable.ic_red_switch_off);
				} else {
					mAppSetting.setRunAlarmState(true);
					runAlarmImageView
							.setImageResource(R.drawable.ic_red_switch_on);
				}
			} else if (runMusicImageView == v) {
				if (mAppSetting.getRunMusicState()) {
					mAppSetting.setRunMusicState(false);
					runMusicImageView
							.setImageResource(R.drawable.ic_red_switch_off);
				} else {
					mAppSetting.setRunMusicState(true);
					runMusicImageView
							.setImageResource(R.drawable.ic_red_switch_on);
				}
			}

		}
	};
	public OnClickListener countDownTimeBtnClick = new OnClickListener() {

		@Override
		public void onClick(View v) {
			String valueString = countDownTxt.getText().toString();
			int value;
			try {
				value = Integer.valueOf(valueString);
			} catch (NumberFormatException e) {
				value = 5;
			}
			if (v == countDownBtn) {
				if (value >= 2) {
					value--;
					countDownTxt.setText(String.valueOf(value));
					countDownTextView.setText(String.valueOf(value));
					mAppSetting.setCountDown(value);
				}
			} else if (v == countUpBtn) {
				if (value <= 4) {
					value++;
					countDownTxt.setText(String.valueOf(value));
					countDownTextView.setText(String.valueOf(value));
					mAppSetting.setCountDown(value);
				}
			}

		}
	};

}
