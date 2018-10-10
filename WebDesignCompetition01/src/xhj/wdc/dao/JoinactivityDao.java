package xhj.wdc.dao;

import java.util.List;

import xhj.wdc.domain.Joinactivity;

public interface JoinactivityDao {

	// 根据手机号和活动id从数据库里查询，返回结果
		public Joinactivity findByPhoneActivityid(String phone,String activityid);
	
	// 添加参加活动的信息
	void addJoinActivity(Joinactivity joinActivity);

	// 改变参加的状态
	void updateJoinStatus(int joinStatus, String phone, String activityid);

	// 查询2个当前用户最新参加活动信息
	List<Joinactivity> getJoinActivityByPhone(String phone);

}