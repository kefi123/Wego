package junit.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.Test;

import xhj.wdc.dao.jdbc.impl.ActivityDaoImpl;
import xhj.wdc.domain.Activity;

public class ActivityDaoImpltest {

	private ActivityDaoImpl adi = new ActivityDaoImpl();

	// 测试时间的转化
	@Test
	public void testDate() {

		// Date date1=new Date("2017-11-30 12:30:49");
		// System.out.println(date1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = "2017-11-30 12:30:49";
		try {
			System.out.println(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	// 改变活动的状态
	// @Test
	public void updateStatustest() {
		adi.updateStatus(3, "123412");
		System.out.println("end");
	}

	// 测试添加活动
	// @Test
	public void addActivitytest() {
		Activity activity = new Activity();

		activity.setActivityid("123412");
		activity.setTitle("封龙山一日游");
		activity.setPicpath("localhost:8080//xxx.jgp");
		activity.setStarttime("2017-11-30 12:30:49");
		activity.setEndtime("2017-12-20 12:30:49");
		activity.setType(1);
		activity.setIspay(1);
		activity.setMoney(100f);
		activity.setLimitsize(100);
		activity.setCurrentsize(20);
		activity.setPlace("铁大");
		activity.setClicknum(1000);
		activity.setPhone("15732119771");
		activity.setDescription("这个活动非常的有意义！！！！！！！！！！！！！！！！！！！！！！！");

		adi.addActivity(activity);
		System.out.println("end");
	}

	// 测试通过id查询活动信息
	// @Test
	public void findbyidtest() {
		Activity ac = adi.findById("123");
		System.out.println(ac.toString());
	}

	// 测试根据需要的活动类型查询最新的3个活动的方法
	 //@Test
	public void gettop4test() {
		List<Activity> list = adi.getNew3(1);
		// System.out.println(list);
		for (Activity ac : list) {
			System.out.println(ac.toString());
		}
	}

	// 查询当前用户发布的活动
	//@Test
	public void getActivitybyphonetest() {
		List<Activity> list = adi.getActivityByPhone("15732119771");
		// System.out.println(list);
		for (Activity ac : list) {
			System.out.println(ac.toString());
		}
	}

	// 测试删除活动的方法
	// @Test
	public void deleteactivitytest() {
		adi.deleteActivity("123");
		System.out.println("end");
	}

	// 测试修改活动
	// @Test
	public void updateActivitytest() {
		Activity activity = new Activity();

		activity.setActivityid("1234");
		activity.setTitle("日租房");
		activity.setPicpath("localhost:8080//xxx.jgp");
		activity.setStarttime("2017-11-23 12:30:49");
		activity.setEndtime("2017-12-02 12:30:49");
		activity.setType(1);
		activity.setStatus(2);
		activity.setIspay(1);
		activity.setMoney(100f);
		activity.setLimitsize(100);
		activity.setPlace("铁大");
		activity.setPhone("15732119771");
		activity.setDescription("这个活动非常的有意义！！！！！！！！！！！！！！！！！！！！！！！");

		adi.updateActivity(activity);
		System.out.println("end");
	}

}
