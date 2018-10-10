package xhj.wdc.service;

import xhj.wdc.domain.User;

public interface UserService {

	// 登录
	User login(String username, String password);

	// 注册
	String register(User user);

}