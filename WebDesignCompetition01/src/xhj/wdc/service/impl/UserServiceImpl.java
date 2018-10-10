package xhj.wdc.service.impl;

import xhj.wdc.dao.Userdao;
import xhj.wdc.dao.jdbc.impl.Userdaoimpl;
import xhj.wdc.domain.User;
import xhj.wdc.service.UserService;

public class UserServiceImpl implements UserService {
	private Userdao userdao = new Userdaoimpl();

	// 登录
	public User login(String username, String password) {

		// 通过用户名查找用户
		User user = userdao.findByUsername(username);

		return user;
	}

	// 注册
	public String register(User user) {
		String message = "";

		// 首先根据用户名和手机号码检查数据库里是否已经存在该用户的信息
		User user1 = userdao.findByUsername(user.getUsername());
		User user2 = userdao.findByPhone(user.getPhone());

		if (user1 != null) {
			message="该用户名已被人使用！";
		} else if (user2 != null) {
			message="该手机号已被人注册！";
		} else {
			userdao.addUser(user);
			message = "注册成功！";
		}

		return message;
	}
}
