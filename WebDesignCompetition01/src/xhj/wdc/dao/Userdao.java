package xhj.wdc.dao;

import xhj.wdc.domain.User;

public interface Userdao {

	// 添加用户
	void addUser(User user);

	// 根据用户名查询用户
	User findByUsername(String username);

	// 根据电话号码查询用户
	User findByPhone(String phone);
}