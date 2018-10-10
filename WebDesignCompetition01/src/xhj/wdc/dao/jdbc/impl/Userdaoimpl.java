package xhj.wdc.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import xhj.wdc.dao.Userdao;
import xhj.wdc.domain.User;
import xhj.wdc.util.JDBCUtil;

public class Userdaoimpl implements Userdao {

	//添加用户
	public void addUser(User user){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="insert into user(phone,username,nickname,realname,password,sex,profilepic) values(?,?,?,?,?,?,?)";
		
		try{
			conn=JDBCUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1, user.getPhone());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getNickname());
			ps.setString(4, user.getRealname());
			ps.setString(5, user.getPassword());
			ps.setString(6, user.getSex());
			ps.setString(7, user.getProfilepic());
			
			ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			JDBCUtil.free(rs, ps, conn);
		}
	}
	
	//根据用户名查询用户
	public User findByUsername(String username){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql="select phone,username,nickname,realname,password,sex,profilepic from user where username=?";
		
		try{
			User user = new User();
			conn=JDBCUtil.getConnection();
			ps=conn.prepareStatement(sql);
			ps.setString(1,username);
			
			rs=ps.executeQuery();
			while(rs.next()){
				user.setPhone(rs.getString( "phone"));
				user.setUsername( rs.getString( "username"));
				user.setNickname(rs.getString( "nickname"));
				user.setRealname(rs.getString("realname"));
				user.setPassword( rs.getString( "password"));
				user.setSex(rs.getString("sex"));
				user.setProfilepic(rs.getString("profilepic"));
				return user;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		finally{
			JDBCUtil.free(rs, ps, conn);
		}
		
		return null;
	}
	//根据电话号码查询用户
		public User findByPhone(String phone){
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql="select phone,username,nickname,realname,password,sex,profilepic from user where phone=?";
			
			try{
				User user = new User();
				conn=JDBCUtil.getConnection();
				ps=conn.prepareStatement(sql);
				ps.setString(1,phone);
				
				rs=ps.executeQuery();
				while(rs.next()){
					user.setPhone(rs.getString( "phone"));
					user.setUsername( rs.getString( "username"));
					user.setNickname(rs.getString( "nickname"));
					user.setRealname(rs.getString("realname"));
					user.setPassword( rs.getString( "password"));
					user.setSex(rs.getString("sex"));
					user.setProfilepic(rs.getString("profilepic"));
					return user;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			finally{
				JDBCUtil.free(rs, ps, conn);
			}
			
			return null;
		}
}
