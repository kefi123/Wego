package junit.test;

import org.junit.Test;

import xhj.wdc.service.JoinActivityService;
import xhj.wdc.service.impl.JoinActivityServiceImpl;

public class JoinActivityServiceTest {
	// 测试退出活动
	@Test
	public void test01()
	{
		JoinActivityService jas = new JoinActivityServiceImpl();

		jas.quitActivity("15732119771","2b81fa29-a5ef-47f1-a8b5-764740b65c5d");
	}
}
