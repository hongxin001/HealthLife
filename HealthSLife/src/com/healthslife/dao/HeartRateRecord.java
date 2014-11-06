package com.healthslife.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class HeartRateRecord {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField
	private int heartRate;
	@DatabaseField
	private long timestamp;
	
	
	
	public HeartRateRecord() {}
	
	public HeartRateRecord(int heartRate, long timestamp) {
		super();
		this.heartRate = heartRate;
		this.timestamp = timestamp;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getHeartRate() {
		return heartRate;
	}
	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
