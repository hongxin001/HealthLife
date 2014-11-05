package com.healthslife.dao;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class SportsRecord {
	@DatabaseField(generatedId=true)
	private int id;
	@DatabaseField
	private int userid;
	@DatabaseField
	private String kind;
	@DatabaseField
	private int num;
	@DatabaseField
	private long createtime;
	@DatabaseField
	private long starttime;
	@DatabaseField
	private long lasttime;
	
	
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public long getCreatetime() {
		return createtime;
	}
	public void setCreatetime(long createtime) {
		this.createtime = createtime;
	}
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	public long getLasttime() {
		return lasttime;
	}
	public void setLasttime(long lasttime) {
		this.lasttime = lasttime;
	}
	
	
	public SportsRecord() {
		// TODO Auto-generated constructor stub
	}
	public SportsRecord(int userid, String kind, int num, long createtime,
			long starttime, long lasttime) {
		super();
		this.userid = userid;
		this.kind = kind;
		this.num = num;
		this.createtime = createtime;
		this.starttime = starttime;
		this.lasttime = lasttime;
	}
	
	
	
	
}
