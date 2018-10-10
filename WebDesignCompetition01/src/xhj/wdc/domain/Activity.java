package xhj.wdc.domain;

import java.util.Date;

public class Activity {
	private String activityid;
	private String title;
	private String picpath;
	private String starttime;
	private String endtime;
	private int type;
	private int status;
	private int ispay;
	private float money;
	private int limitsize;
	private int currentsize;
	private String place;
	private int clicknum;
	private String phone;
	private Date addtime;
	private String description;
	
	
	
	@Override
	public String toString() {
		return "Activity [activityid=" + activityid + ", title=" + title + ", picpath=" + picpath + ", starttime="
				+ starttime + ", endtime=" + endtime + ", type=" + type + ", status=" + status + ", ispay=" + ispay
				+ ", money=" + money + ", limitsize=" + limitsize + ", currentsize=" + currentsize + ", place=" + place
				+ ", clicknum=" + clicknum + ", phone=" + phone + ", addtime=" + addtime + ", description="
				+ description + "]";
	}
	public String getActivityid() {
		return activityid;
	}
	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPicpath() {
		return picpath;
	}
	public void setPicpath(String picpath) {
		this.picpath = picpath;
	}
	
	public String getStarttime() {
		return starttime;
	}
	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}
	public String getEndtime() {
		return endtime;
	}
	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getIspay() {
		return ispay;
	}
	public void setIspay(int ispay) {
		this.ispay = ispay;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public int getLimitsize() {
		return limitsize;
	}
	public void setLimitsize(int limitsize) {
		this.limitsize = limitsize;
	}
	public int getCurrentsize() {
		return currentsize;
	}
	public void setCurrentsize(int currentsize) {
		this.currentsize = currentsize;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getClicknum() {
		return clicknum;
	}
	public void setClicknum(int clicknum) {
		this.clicknum = clicknum;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
