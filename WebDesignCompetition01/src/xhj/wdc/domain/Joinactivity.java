package xhj.wdc.domain;

import java.util.Date;

public class Joinactivity {
	private String cphone;
	private String cactivityid;
	private Date jointime;
	private int joinstatus;
	private User user;
	private Activity activity;
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
	
	public String getCphone() {
		return cphone;
	}
	public void setCphone(String cphone) {
		this.cphone = cphone;
	}
	public String getCactivityid() {
		return cactivityid;
	}
	public void setCactivityid(String cactivityid) {
		this.cactivityid = cactivityid;
	}
	public Date getJointime() {
		return jointime;
	}
	public void setJointime(Date jointime) {
		this.jointime = jointime;
	}
	public int getJoinstatus() {
		return joinstatus;
	}
	public void setJoinstatus(int joinstatus) {
		this.joinstatus = joinstatus;
	}
	
	
}
