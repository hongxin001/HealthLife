package com.healthslife.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.healthslife.db.DataBaseHelper;
import com.healthslife.run.dao.RunRecord;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.GenericRawResults;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.QueryBuilder;


public class SportsDB {
	private DataBaseHelper helper;

	public SportsDB(Context context) {
		helper = OpenHelperManager.getHelper(context, DataBaseHelper.class);
	}
	
	public void add(SportsRecord record){
		RuntimeExceptionDao<SportsRecord, Integer> dao = helper.getRuntimeExceptionDao(SportsRecord.class);
		if (record != null) {
			dao.create(record);
		}
	}
	
	public void delete(int id){
		RuntimeExceptionDao<SportsRecord, Integer> dao = helper.getRuntimeExceptionDao(SportsRecord.class);
		dao.deleteById(id);
	}
	
	public int getTotalNumbykind(int kind){
		RuntimeExceptionDao<SportsRecord, Integer> dao = helper.getRuntimeExceptionDao(SportsRecord.class);
		String condition = "";
		switch (kind) {
		case 1:
		case 2:
		case 3:
			condition = "<4";
			break;
		case 4:
			condition = "=4";
			break;
		case 5:
			condition = "=5";
			break;
		default:
			condition = "<4";
		}
		GenericRawResults<String[]> rawResults = dao.queryRaw("select sum(num) from SportsRecord where kind"+condition);
		int value=0;
		try {
			List<String[]>resultList = rawResults.getResults();
			if(resultList!=null){
				String tempString = resultList.get(0)[0];
				if(tempString!=null){
					value = Integer.valueOf(tempString);
				}
			}
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return value;
	}
	
	public List<SportsRecord> querybykind(int kind){
		RuntimeExceptionDao<SportsRecord, Integer> dao = helper.getRuntimeExceptionDao(SportsRecord.class);
		QueryBuilder<SportsRecord, Integer> qb = dao.queryBuilder();
		List<SportsRecord> list = new ArrayList<SportsRecord>();
		try {
			qb.where().eq("kind", kind);
			list.addAll(qb.query());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}
