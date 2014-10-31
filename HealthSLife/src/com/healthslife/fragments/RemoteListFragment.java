package com.healthslife.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;

import com.healthslife.R;
import com.healthslife.control.fragment.AirControlFragment;
import com.healthslife.control.fragment.LightControlFragment;
import com.healthslife.receiver.UDPReceiver;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RemoteListFragment extends Fragment {
	private ArrayList<HashMap<String, String>> list;
	private HashMap<String, String> air, light;
	private ListView mList;
	private MyAdapter mAdapter;
	private Fragment controlFragment;
	FragmentManager manager ;
	ExecutorService executorService;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		list = new ArrayList<HashMap<String, String>>();
		air = new HashMap<String, String>();
		air.put("type", "air");
		air.put("name", "空调");
		list.add(air);

		light = new HashMap<String, String>();
		light.put("type", "light");
		light.put("name", "灯");
		list.add(light);
		
		manager = getActivity().getSupportFragmentManager();
		
		
		
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreateView(inflater, container,savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_remote_list, null, false);
		mList = (ListView)view.findViewById(R.id.remote_list);
		mAdapter = new MyAdapter(getActivity());
		mList.setAdapter(mAdapter);
		
		mList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				HashMap<String,String> map = mAdapter.getItem(position);
				String type = map.get("type");
				String name = map.get("name");
				if(type.equals("air")){
					controlFragment = new AirControlFragment();
				}else if(type.equals("light")){
					controlFragment = new LightControlFragment();
				}
				manager.beginTransaction().replace(R.id.main_content_layout, controlFragment).commit();
			}
			
		});
		
		
		return view;
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

	class MyAdapter extends BaseAdapter {
		private LayoutInflater mInflater;
		UDPReceiver receiver = new UDPReceiver();

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
		public HashMap<String, String> getItem(int position) {
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

			HashMap<String, String> map = (HashMap) getItem(position);
			String type = map.get("type");

			if (type.equals("air")) {
				RemoteAirViewHolder holder;
				if (convertView == null) {
					convertView = mInflater.inflate(
							R.layout.item_air_condition, null);
					holder = new RemoteAirViewHolder();
					holder.mRemoteName = (TextView) convertView
							.findViewById(R.id.remote_name);
					holder.mRemoteTempNum = (TextView) convertView
							.findViewById(R.id.remote_temp_num);
					holder.mRemotehumiNum = (TextView) convertView
							.findViewById(R.id.remote_humi_num);
					convertView.setTag(holder);
				} else {
					holder = (RemoteAirViewHolder) convertView.getTag();
				}
				
				holder.mRemoteName.setText(map.get("name"));
				holder.mRemoteTempNum.setText("34");
				holder.mRemotehumiNum.setText("35");
			} else if (type.equals("light")) {
				RemoteLightViewHolder holder;
				if (convertView == null) {
					convertView = mInflater.inflate(R.layout.item_light, null);
					holder = new RemoteLightViewHolder();
					holder.mRemoteName = (TextView) convertView
							.findViewById(R.id.remote_light_name);
					convertView.setTag(holder);
				} else {
					holder = (RemoteLightViewHolder) convertView.getTag();
				}
				holder.mRemoteName.setText(map.get("name"));
			}else{
				Log.v("type", "error");
			}
			return convertView;
		}
		
	
	}

	public final class RemoteAirViewHolder {
		public TextView mRemoteName;
		private TextView mRemoteTemp;
		private TextView mRemoteTempNum;
		private TextView mRemotehumi;
		private TextView mRemotehumiNum;
	}

	public final class RemoteLightViewHolder {
		public TextView mRemoteName;
	}
	
}
