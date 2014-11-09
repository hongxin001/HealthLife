package com.healthslife.fragments;

import com.baidu.navisdk.BNaviEngineManager.NaviEngineInitListener;
import com.baidu.navisdk.BaiduNaviManager;
import com.baidu.navisdk.BaiduNaviManager.OnStartNavigationListener;
import com.baidu.navisdk.comapi.routeplan.RoutePlanParams.NE_RoutePlan_Mode;
import com.healthslife.R;
import com.healthslife.activitys.BNavigatorActivity;
import com.healthslife.widget.CircleProgress;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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
	public static NewDistanceFragment self;
	public static NewDistanceFragment getInstance(){
		if (self == null) {
			return self = new NewDistanceFragment();
		
		}
		return self;
	}
	// layout
	View view;

	// Searching
	// EditText type in distance
	EditText mEditTextDistance;
	// Search Ensure
	ImageButton mButtonSearch;

	// Type selected
	// Two Button Selector below the Circle
	RelativeLayout mButtonDistance;
	TextView mTextViewDistance;// need to be set Number
	RelativeLayout mButtonPace;
	TextView mTextViewPace;// need to be set number

	// Circle-->Distance I use view .setVisible(INVISIBLE|VISIBLE) to control it
	CircleProgress mCircle;
	FrameLayout mFrameLayoutDistance;
	// Number in Circle
	TextView mCircledistance;
	TextView mCircledistanceAim;
	// Circle-->Pace
	CircleProgress mCirclePace;
	FrameLayout mFrameLayoutPace;
	// Number in Circle
	TextView mCircleTextViewPace;
	TextView mCircleTextViewPaceAim;
	private boolean mIsEngineInitSuccess = false;
	private final static String ACCESS_KEY = "	g7roxmwdB8dUQwTrdOpuLcgF";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		BaiduNaviManager.getInstance().initEngine(getActivity(),
				getSdcardDir(), mNaviEngineInitListener, ACCESS_KEY, null);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (container == null) {
			return null;
		}
		view = inflater.inflate(R.layout.fragment_new_distance_fragment,
				null,false);
		findView();
		underCircleTwoButtonOnClick();
		SearchOnClick();
		// TODO-I need you set percent of the CircleProgress
		// and number of number textViews
		
		return view;
	}

	private void SearchOnClick() {
		mButtonSearch.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				BaiduNaviManager.getInstance().launchNavigator(getActivity(),
						40.05087, 116.30142, "百度大厦", 39.90882, 116.39750,
						"北京天安门", NE_RoutePlan_Mode.ROUTE_PLAN_MOD_MIN_TIME, // 算路方式
						true, // 真实导航
						BaiduNaviManager.STRATEGY_FORCE_ONLINE_PRIORITY, // 在离线策略
						new OnStartNavigationListener() { // 跳转监听

							@Override
							public void onJumpToNavigator(Bundle configParams) {
								Intent intent = new Intent(getActivity(),
										BNavigatorActivity.class);
								intent.putExtras(configParams);
								startActivity(intent);
							}

							@Override
							public void onJumpToDownloader() {
							}
						});
				// getSearchDestination();
			}
		});
	}

	/**
	 * get destination from editText
	 */
	private String getSearchDestination() {
		return mEditTextDistance.getText().toString();
	}

	private void underCircleTwoButtonOnClick() {
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

	private void setDistanceNumber(String number) {
		mCircledistance.setText(number);
		mTextViewDistance.setText(number);
	}

	private void setPaceNumber() {

	}

	private void findView() {
		mButtonSearch = (ImageButton) view
				.findViewById(R.id.ensureSearch_button);
		mEditTextDistance = (EditText) view
				.findViewById(R.id.editText_distance_run_destance);
		mButtonDistance = (RelativeLayout) view
				.findViewById(R.id.image_Button_distance);
		mButtonPace = (RelativeLayout) view
				.findViewById(R.id.image_Button_pace);
		mTextViewDistance = (TextView) view
				.findViewById(R.id.distance_bottom_txt);
		mTextViewPace = (TextView) view.findViewById(R.id.pace_bottom_txt);
		mCircle = (CircleProgress) view
				.findViewById(R.id.fragment_distance_porbar);
		mFrameLayoutDistance = (FrameLayout) view
				.findViewById(R.id.fragment_distance_run);
		mCircledistance = (TextView) view.findViewById(R.id.run_distance_txt);
		mCircledistanceAim = (TextView) view
				.findViewById(R.id.aim_run_distance);
		mCirclePace = (CircleProgress) view
				.findViewById(R.id.fragment_pace_porbar);
		mFrameLayoutPace = (FrameLayout) view
				.findViewById(R.id.fragment_pace_run);
		mCircleTextViewPace = (TextView) view.findViewById(R.id.run_pace_txt);
		mCircleTextViewPaceAim = (TextView) view
				.findViewById(R.id.aim_pace_distance);
	}

	private NaviEngineInitListener mNaviEngineInitListener = new NaviEngineInitListener() {
		public void engineInitSuccess() {
			// 导航初始化是异步的，需要一小段时间，以这个标志来识别引擎是否初始化成功，为true时候才能发起导航
			mIsEngineInitSuccess = true;
		}

		public void engineInitStart() {
		}

		public void engineInitFail() {
		}
	};

	private String getSdcardDir() {
		if (Environment.getExternalStorageState().equalsIgnoreCase(
				Environment.MEDIA_MOUNTED)) {
			return Environment.getExternalStorageDirectory().toString();
		}
		return null;
	}
}