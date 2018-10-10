package xhj.wdc.dao;

import java.util.List;

import xhj.wdc.domain.Activity;

public interface ActivityDao {

	// 添加活动
	void addActivity(Activity activity);

	// 通过id查询活动
	Activity findById(String id);

	// 查询当前用户发布的活动
	List<Activity> getActivityByPhone(String phone);

	// 查询最新的3个活动
	List<Activity> getNew3(int type);

	// 根据id删除活动
	void deleteActivity(String id);

	// 修改活动
	void updateActivity(Activity activity);

	// 改变活动的状态
	void updateStatus(int status, String activityid);

	// 查询所有的活动
	List<Activity> getAllActivity();
	
	//查询活动的总数
	int getCount();
	
	// 得到总行数
	int getCountRow();
	
	// 返回当前页需要显示的活动
	List<Activity> getCustByPage(int from, int count);
}