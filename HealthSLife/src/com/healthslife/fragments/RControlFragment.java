package com.healthslife.fragments;


import com.astuetz.PagerSlidingTabStrip;
import com.healthslife.R;
import com.healthslife.control.fragment.ControlFragment;
import com.healthslife.control.fragment.LearnFragment;
import com.healthslife.healthtest.EcgFragment;
import com.healthslife.healthtest.HeartRateFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RControlFragment extends Fragment{
	private PagerSlidingTabStrip tabs;
	private ViewPager mViewpager;
	private MyPagerAdapter mAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_remote_control, container,
				false);
		tabs = (PagerSlidingTabStrip) root.findViewById(R.id.remotetabs);
		mViewpager = (ViewPager) root.findViewById(R.id.remotepager);
		mAdapter = new MyPagerAdapter(getActivity().getSupportFragmentManager());
		mViewpager.setAdapter(mAdapter);
		tabs.setViewPager(mViewpager);
		// mViewpager.getCurrentItem();
		return root;
	}

	public int getCurrentPager() {
		return mViewpager.getCurrentItem();
	}

	private class MyPagerAdapter extends FragmentStatePagerAdapter {
		String[] titleStrings;
		Fragment[] fragments;

		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
			titleStrings = getResources().getStringArray(
					R.array.remote_title);
			fragments = new Fragment[titleStrings.length];
		}

		@Override
		public CharSequence getPageTitle(int position) {
			CharSequence title = titleStrings[position];
			return title;
		}

		@Override
		public int getCount() {
			return titleStrings.length;
		}

		public Fragment getFragment(int position) {
			return fragments[position];
		}

		@Override
		public Fragment getItem(int arg0) {
			Fragment fragment = null;
			switch (arg0) {
			case 0:
				fragment = new ControlFragment();
				break;
			case 1:
				fragment = new LearnFragment();
				break;
			default:
				break;
			}
			fragments[arg0] = fragment;
			return fragment;
		}
	}
}
