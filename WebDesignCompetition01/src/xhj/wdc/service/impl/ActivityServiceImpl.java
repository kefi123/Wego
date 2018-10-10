package xhj.wdc.service.impl;

import java.util.List;
import java.util.Random;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import xhj.wdc.dao.ActivityDao;
import xhj.wdc.dao.jdbc.impl.ActivityDaoImpl;
import xhj.wdc.domain.Activity;
import xhj.wdc.service.ActivityService;

public class ActivityServiceImpl implements ActivityService {
	private ActivityDao ad = new ActivityDaoImpl();

	// 随机返回所有活动中的一个
	public Activity randomActivity() {
		List<Activity> list = ad.getAllActivity();

		int count = ad.getCount();

		Random rand = new Random();

		int randNum = rand.nextInt(count);
		int i = 0;

		for (Activity activity : list) {
			if (i == randNum) {
				return activity;
			} else
				i++;
		}

		return null;
	}

	// 发布活动
	public void release_activity(Activity activity) {
		ad.addActivity(activity);
	}

	// 根据类型显示最新的3个活动
	public List<Activity> display_activity(int type) {
		List<Activity> list = ad.getNew3(type);
		return list;
	}

	// 活动详情
	public Activity activityDetail(String id) {
		Activity activity = ad.findById(id);
		return activity;
	}

	// 取消活动
	public boolean cancelActivity(Activity activity) {

		boolean f = true;

		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
		
		DateTime startDate =DateTime.parse(activity.getStarttime(),format);

		DateTime nowDate = new DateTime();
		
		int days = Days.daysBetween(nowDate, startDate).getDays();


		if (days < 2) {
			// 距离活动开始两天之内的话无法取消
			f = false;

		} else {
			ad.updateStatus(3, activity.getActivityid());
		}
		return f;
	}

	// 查看当前用户发布的活动
	public List<Activity> lookReleasedActivity(String phone) {
		List<Activity> list = ad.getActivityByPhone(phone);
		return list;
	}

	// 修改活动信息
	public void updateActivity(Activity activity) {
		ad.updateActivity(activity);
	}
}
