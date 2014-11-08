package com.healthslife.fragments;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;

import com.healthslife.R;
import com.healthslife.activitys.HeartRateResultActivity;
import com.healthslife.dao.HeartRateDB;
import com.healthslife.dao.HeartRateRecord;
import com.healthslife.healthtest.ImgAnalysis;
import com.healthslife.healthtest.HeartRateFragment.dataAndTime;
import com.healthslife.healthtest.ImgAnalysis.ImgCaptureListener;
import com.healthslife.healthtest.Timer;
import com.healthslife.widget.CircleProgress;
import com.healthslife.widget.CircleProgress.CompleteListener;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HeartRateFragment extends Fragment implements View.OnClickListener {

	private static final int BEFORTEST = 0;
	private static final int TESTING = 1;
	private static final int AFTERTEST = 2;

	private static final int NORMAL = 1;
	private static final int AFTERSPORT = 2;
	private static final int UPPERBOUND = 99999999;
	private static final int LOWERBOUND = 0;
	// layout
	View view;
	// CircleProgress
	CircleProgress mCircleProgress;
	// TextView
	TextView mTextViewNumber;
	ImageButton mImageButtonStart;
	// RelativeLayout contains heartRate wave
	RelativeLayout reLayout;
	HeartRateHandler handler;
	RelativeLayout relativeLayout;
	TimerTask timerTask;
	// Timer timer;
	Interpolator interpolator;
	public static HeartRateFragment self;

	private int testState = BEFORTEST;// 0测试开始前 1测试中 2测试结束
	private ImgAnalysis mImgAnalysis;
	private Timer mTimer;
	private ArrayList<dataAndTime> dataList = new ArrayList<dataAndTime>();
	private int wrongDataNum = 0;
	private int peakPairs[][] = new int[15][2];// 峰值对 每对表示相邻的两个峰值在dataList中的位置
	private int peakPairsIndex = 0;
	// private long timeMill = 0;//计时器 毫秒
	private int lastPeakIndex = 0;
	private int averageHeartRate = 0;
	private long currentTime;

	public static HeartRateFragment getInstance() {
		if (self == null) {
			return self = new HeartRateFragment();

		}
		return self;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.fragment_heart_rate_l, null, false);
		LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		view.setLayoutParams(lp);
		handler = new HeartRateHandler();
		findView();

		mImgAnalysis = new ImgAnalysis(inflater.getContext(),
				(RelativeLayout) view.findViewById(R.id.new_blank));
		mImgAnalysis.setImgCaptureListener(mImgCaptureListener);
		mTimer = new Timer();
		mCircleProgress.setCompleteListener(new CompleteListener() {
			@Override
			public void complete() {
				// 测量完成（进度条100%）触发完成事件
				currentTime = new Date().getTime();
				reSet();
				Intent intent = new Intent(getActivity(),HeartRateResultActivity.class);
				intent.putExtra("data", averageHeartRate);
				startActivity(intent);
				// refreshData();
				// saveData();
			}
		});
		return view;
	}

	private void findView() {
		mTextViewNumber = (TextView) view.findViewById(R.id.heart_rate_txt);
		mCircleProgress = (CircleProgress) view
				.findViewById(R.id.fragment_heart_rate_porbar);
		mCircleProgress.setOnClickListener(this);
		// mImageButtonStart = (ImageButton) view
		// .findViewById(R.id.fragment_heart_rate_l_start);
		reLayout = (RelativeLayout) view
				.findViewById(R.id.fragment_heart_rate_l_relativelayout_grid);
	}

	/**
	 * a invalid heart peek invoke it to update UI
	 */
	public void haveHeartRatePeek() {
		handler.sendEmptyMessage(0);
	}

	private class HeartRateHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 0:
				ImageView imageView = new ImageView(getActivity());
				imageView.setImageDrawable(getResources().getDrawable(
						R.drawable.rate_src));
				RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
						RelativeLayout.LayoutParams.WRAP_CONTENT,
						RelativeLayout.LayoutParams.WRAP_CONTENT);
				imageView.setLayoutParams(lp);
				imageView.setX(-120f);
				imageView.setScaleX(2f);
				reLayout.addView(imageView);
				imageView.animate().x(1200f);
				imageView.animate().setInterpolator(interpolator);
				imageView.animate().setDuration(6000);
				imageView.animate().start();
				break;

			default:
				break;
			}
		}
	}

	private ImgCaptureListener mImgCaptureListener = new ImgCaptureListener() {

		@Override
		public void onCapture(int[] rgb, int height, int width) {
			int total = 0;
			for (int i : rgb) {
				total += i;
			}
			// 判断灰度值是否在合理的范围内，如果在加入dataList，如果不在舍弃数据并且连续n组数据不符合进度置零
			if (total > UPPERBOUND || total < LOWERBOUND) {
				wrongDataNum++;
				if (wrongDataNum > 20)
					mCircleProgress.setProgress(0);
				return;
			}
			wrongDataNum = 0;
			long tempMills = mTimer.getMillis();
			if (dataList.size() == 0
					|| (tempMills - dataList.get(dataList.size() - 1).time) > 100) {
				dataList.add(new dataAndTime(total, mTimer.getMillis()));
				Log.e("Lei", "analysisData");
				analysisData();
			}
		}
	};

	private void analysisData() {
		// 找出peakpairs……
		if (mTimer.getSecond() < 2) {
			return;
		}
		int pairFirstPeak = findPeaks(lastPeakIndex);
		if (pairFirstPeak == -1) {
			return;
		}
		int pairSecondPeak = findPeaks(pairFirstPeak);
		if (pairSecondPeak == -1) {
			return;
		}
		haveHeartRatePeek();
		lastPeakIndex = pairSecondPeak;
		long temp = dataList.get(pairSecondPeak).time
				- dataList.get(pairFirstPeak).time;
		if (temp < 500 || temp > 1500)
			return;
		peakPairs[peakPairsIndex][0] = pairFirstPeak;
		peakPairs[peakPairsIndex][1] = pairSecondPeak;
		peakPairsIndex++;
		computeHeartRate();

		mCircleProgress.slideToProgress(peakPairsIndex * 100);

		if (peakPairsIndex >= 10) {
			Log.e("Lei", "complete");
			mImgAnalysis.stopCaptureImg();
			Toast.makeText(getActivity(), "test complete", Toast.LENGTH_SHORT)
					.show();

		}
	}

	private void startTest() {
		if (testState != BEFORTEST || mImgAnalysis.startCaptureImg() == false) {
			Log.e("======>", "started false");
			return;
		}
		// 开始测量心率
		testState = TESTING;
		mTimer.startTimer();
		mTextViewNumber.setText("00");
		// tipsTxt.setText("心率识别中………");
		Log.e("======>", "started");
	}

	public int findPeaks(int start) {
		int N = 1;// 峰值间距
		for (int i = start + N; i < dataList.size() - N; i++) {
			boolean isPeak = true;
			for (int j = 0; j < N; j++) {
				if (dataList.get(i - j).data < dataList.get(i - j - 1).data
						|| dataList.get(i + j).data < dataList.get(i + j + 1).data) {
					isPeak = false;
				}
			}
			if (isPeak) {
				return i;
			}
		}
		return -1;
	}

	public class dataAndTime {
		public int data;
		public long time;

		public dataAndTime(int data, long time) {
			this.data = data;
			this.time = time;
		}

	}

	private void computeHeartRate() {
		long totalTime = 0;
		for (int i = 0; i < peakPairsIndex; i++) {
			totalTime += dataList.get(peakPairs[i][1]).time
					- dataList.get(peakPairs[i][0]).time;
		}
		float temp = (60000f / (float) totalTime) * (float) peakPairsIndex;
		averageHeartRate = (int) temp;

		Log.v("average", String.valueOf(averageHeartRate));

		mTextViewNumber.setText(averageHeartRate + "");
	}

	private void reSet() {
		testState = BEFORTEST;
		mCircleProgress.setProgress(0);
		dataList.clear();
		peakPairsIndex = 0;
		mTimer.resetAndStop();
		wrongDataNum = 0;
		lastPeakIndex = 0;
	}

	public void saveData() {
		HeartRateDB mDB = new HeartRateDB(getActivity());
		mDB.add(new HeartRateRecord(averageHeartRate, currentTime));
	}

	@Override
	public void onPause() {
		super.onPause();
		closeTest();
	}

	private void closeTest() {
		reSet();
	}

	boolean willStart = true;

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fragment_heart_rate_porbar:
			startTest();
			break;

		default:
		}
	}
}
