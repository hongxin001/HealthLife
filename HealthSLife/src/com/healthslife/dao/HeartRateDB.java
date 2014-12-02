package com.healthslife.dao;

import java.util.Date;
import java.util.List;

import android.content.Context;

import com.healthslife.db.DataBaseHelper;
import com.healthslife.healthtest.dao.HeartRateHisRecord;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

public class HeartRateDB {
	private DataBaseHelper databaseHelper = null;

	public HeartRateDB(Context ctx) {
		// TODO Auto-generated constructor stub
		if (databaseHelper == null) {
			databaseHelper = OpenHelperManager.getHelper(ctx,
					DataBaseHelper.class);
		}
	}

	public void add(HeartRateRecord record) {
		RuntimeExceptionDao<HeartRateRecord, Integer> dao = databaseHelper
				.getRuntimeExceptionDao(HeartRateRecord.class);
		dao.create(record);
	}

	public List<HeartRateRecord> queryall() {
		RuntimeExceptionDao<HeartRateRecord, Integer> dao = databaseHelper
				.getRuntimeExceptionDao(HeartRateRecord.class);
		return dao.queryForAll();
	}

	public HeartRateRecord getLast() {
		RuntimeExceptionDao<HeartRateRecord, Integer> dao = databaseHelper
				.getRuntimeExceptionDao(HeartRateRecord.class);
		int size = dao.queryForAll().size();
		if (size == 0)
			return new HeartRateRecord(0, new Date().getTime());
		return dao.queryForAll().get(size-1);
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
