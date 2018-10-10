package junit.test;

import org.junit.Test;

import xhj.wdc.util.ServiceUtil;

public class ServiceUtiltest {
	
	@Test
	public void md5test(){
		System.out.println(ServiceUtil.md5("kefi123"));
		System.out.println(ServiceUtil.md5("kefi123"));
	}
}
