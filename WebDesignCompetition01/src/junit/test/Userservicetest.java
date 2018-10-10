package junit.test;

import org.junit.Test;

import xhj.wdc.domain.User;
import xhj.wdc.service.impl.UserServiceImpl;
import xhj.wdc.util.ServiceUtil;

public class Userservicetest {
	
	//@Test
	public void userservicetest(){
		UserServiceImpl us=new UserServiceImpl();
		System.out.println(us.login("kefi123", "kefi123"));
	}
	 //@Test
		public void addUsertest() {
			User user = new User();
			user.setPhone("15732119774");
			user.setUsername("kefi124");
			user.setNickname("kefi123");
			user.setRealname("徐浩军");
			// 对密码需要进行md5加密
			user.setPassword(ServiceUtil.md5("kefi123"));
			user.setSex("男");
			user.setProfilepic("localhost:3306//xxx.jpg");

			UserServiceImpl us=new UserServiceImpl();
			
			System.out.println(us.register(user));

		}
}
