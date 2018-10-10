package junit.test;

import org.junit.Test;

import xhj.wdc.dao.Userdao;
import xhj.wdc.dao.jdbc.impl.Userdaoimpl;
import xhj.wdc.domain.User;
import xhj.wdc.util.ServiceUtil;

public class UserDaoTest {
	private Userdao userdao = new Userdaoimpl();

	// @Test
	public void testchar() {
		char a = '男';
		System.out.println(a);
	}

	// @Test
	public void addUsertest() {
		User user = new User();
		user.setPhone("15732119773");
		user.setUsername("kefi123");
		user.setNickname("kefi123");
		user.setRealname("徐浩军");
		// 对密码需要进行md5加密
		user.setPassword(ServiceUtil.md5("kefi123"));
		user.setSex("男");
		user.setProfilepic("localhost:3306//xxx.jpg");

		userdao.addUser(user);
		System.out.println("end");

	}

	@Test
	public void findByusernametest() {
		User user = userdao.findByUsername("kefi12");
		if (user != null)
			System.out.println(user.toString());
		else
			System.out.println("end");

	}
}
