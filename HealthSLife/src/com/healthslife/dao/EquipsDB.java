package com.healthslife.dao;

import java.util.List;

import android.content.Context;

import com.healthslife.db.DataBaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class EquipsDB {
	private DataBaseHelper databaseHelper = null;
	
	public EquipsDB(Context ctx) {
		// TODO Auto-generated constructor stub
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(ctx,
					DataBaseHelper.class);
		}
	}
	
	public void add(Equips e){
		RuntimeExceptionDao<Equips, Integer> dao = databaseHelper
				.getRuntimeExceptionDao(Equips.class);
		dao.create(e);
	}
	
	public List<Equips> queryall(){
		RuntimeExceptionDao<Equips, Integer> dao = databaseHelper
				.getRuntimeExceptionDao(Equips.class);
		return dao.queryForAll();		
	}
	
	protected void releaseDataHelper() {
		/*
		 * 释放资源
		 */
		if (databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			databaseHelper = null;
		}
	}
}
