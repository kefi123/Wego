package xhj.wdc.service;

import java.util.List;

import xhj.wdc.domain.Joinactivity;

public interface JoinActivityService {

	// 添加参加活动的信息
	String  addJoinActivity(Joinactivity ja);

	// 退出活动
	void quitActivity(String phone, String activityid);

	// 查看最新参加的两个活动
	List<Joinactivity> lookJoinedActivity(String phone);

}