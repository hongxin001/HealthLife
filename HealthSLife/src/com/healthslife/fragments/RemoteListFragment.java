package com.healthslife.fragments;

import java.util.ArrayList;
import java.util.HashMap;

import com.healthslife.R;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class RemoteListFragment extends Fragment{
	private ArrayList<HashMap<String, String>> list;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
	
	@Override
	public void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	
	class MyAdapter extends BaseAdapter{
		 private LayoutInflater mInflater;
		 public MyAdapter(Context context) {
			// TODO Auto-generated constructor stub
			 this.mInflater = LayoutInflater.from(context);
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return list.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			RemoteViewHolder holder;
			if(convertView == null){
				convertView = mInflater.inflate(R.layout.item_air_condition, null);
				holder = new RemoteViewHolder();
				holder.mRemoteName = (TextView)convertView.findViewById(R.id.remote_name);
				holder.mRemoteTempNum = (TextView)convertView.findViewById(R.id.remote_temp_num);
				holder.mRemotehumiNum = (TextView)convertView.findViewById(R.id.remote_humi_num);
				convertView.setTag(holder);
			}else{
				holder = (RemoteViewHolder)convertView.getTag();
			}
			
			final HashMap<String,String> map = list.get(position);
			
			holder.mRemoteName.setText(map.get("remotename"));
			holder.mRemoteTempNum.setText(map.get("remotetempnum"));
			holder.mRemotehumiNum.setText("remotehuminum");
			
			return null;
		}
		
	}
	public final class RemoteViewHolder{
		public TextView mRemoteName;
		private TextView mRemoteTemp;
		private TextView mRemoteTempNum;
		private TextView mRemotehumi;
		private TextView mRemotehumiNum;

	}
}
