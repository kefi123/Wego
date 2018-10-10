package xhj.wdc.service;

import java.util.List;

import xhj.wdc.domain.Activity;

public interface ActivityService {

	// 发布活动
	void release_activity(Activity activity);

	// 根据类型显示最新的3个活动
	public List<Activity> display_activity(int type);

	// 活动详情
	Activity activityDetail(String id);

	// 取消活动
	boolean cancelActivity(Activity activity);

	// 查看当前用户发布的活动
	List<Activity> lookReleasedActivity(String phone);

	// 修改活动信息
	void updateActivity(Activity activity);

	// 随机返回所有活动中的一个
	Activity randomActivity();
	
}