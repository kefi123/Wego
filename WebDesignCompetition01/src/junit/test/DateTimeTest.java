package junit.test;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import xhj.wdc.service.JoinActivityService;
import xhj.wdc.service.impl.JoinActivityServiceImpl;

public class DateTimeTest {
	// 测试退出活动
	@Test
	public void test01() {
		// // DateTime startDate = new DateTime(activity.getStarttime());
		//// DateTimeFormatter format=DateTimeFormat.forPattern("yyyy-MM-dd");
		//
		// DateTime nowDate = new DateTime("2017-12-18 08:00");
		//// DateTime date1 = nowDate.plusDays(3);
		////
		//// int days = Days.daysBetween(nowDate, date1).getDays();
		//// System.out.println(days);
		//// DateTime
		// date1=DateTime.parse(nowDate.plusDays(3).toString(),format);
		//
		// System.out.println(nowDate.toString("yyyy-MM-dd"));
		//// System.out.println(date1.toString());
		
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
		// 时间解析
		DateTime dateTime2 = DateTime.parse("2017-12-18 08:00", format);
		System.out.println(dateTime2.toString());

	}
}
