package junit.test;

import org.junit.Test;

import xhj.wdc.util.GetPropertiesUtil;

public class PropertiesTest {
	@Test
	public void test() {
		try {
			System.out.println(GetPropertiesUtil.getUrl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
