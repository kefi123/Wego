package xhj.wdc.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;

import xhj.wdc.dao.ActivityDao;
import xhj.wdc.dao.JoinactivityDao;
import xhj.wdc.dao.Userdao;
import xhj.wdc.domain.Joinactivity;
import xhj.wdc.util.DbManager;
import xhj.wdc.util.JDBCUtil;

public class JoinactivityDaoImpl implements JoinactivityDao {

	// 根据手机号和活动id从数据库里查询，返回结果
	public Joinactivity findByPhoneActivityid(String phone, String activityid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select cphone,cactivityid,jointime,joinstatus from joinactivity where cactivityid=? and cphone=?";

		try {
			Joinactivity joinactivity = new Joinactivity();
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, activityid);
			ps.setString(2, phone);

			rs = ps.executeQuery();
			while (rs.next()) {
				joinactivity.setCactivityid(rs.getString("cactivityid"));
				joinactivity.setCphone(rs.getString("cphone"));
				joinactivity.setJointime(rs.getDate("jointime"));
				joinactivity.setJoinstatus(rs.getInt("joinstatus"));
				return joinactivity;
			}
		} catch (SQLException e) {
			throw new RuntimeException("查询的时候出错了");
		} finally {
			JDBCUtil.free(rs, ps, conn);
		}

		return null;
	}

	// 添加参加活动的信息
	public void addJoinActivity(Joinactivity joinActivity) {
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			conn = JDBCUtil.getConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			String sql = "insert into joinactivity(cphone,cactivityid,jointime) values('" + joinActivity.getCphone()
					+ "','" + joinActivity.getCactivityid() + "',now())";
			st.executeUpdate(sql);

			sql = "update activity set currentsize=currentsize+1 where activityid='" + joinActivity.getCactivityid()
					+ "'";
			st.executeUpdate(sql);
			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, st, conn);
		}
	}

	// 改变当前人数
	public void updateCurrentSize(String cactivityid) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, ps, conn);
		}
	}

	// 改变参加的状态
	public void updateJoinStatus(int joinStatus, String phone, String activityid) {

		try {
			String sql = "delete from joinactivity  where cphone=? and cactivityid=?";
			QueryRunner qr;
			qr = new QueryRunner(DbManager.getSource());
			DbManager.startTran();
			qr.update(sql, phone, activityid);
			
			sql = "update activity set currentsize=currentsize-1 where activityid=?";
			qr.update(sql, activityid);
			DbManager.commit();
		} catch (SQLException e) {
			DbManager.rollback();
			DbManager.release();
			e.printStackTrace();
		}

	}

	// 查询2个当前用户最新参加活动信息
	public List<Joinactivity> getJoinActivityByPhone(String phone) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select cphone,cactivityid,jointime,joinstatus from joinactivity where cphone=? ORDER BY jointime desc limit 0,2 ";
		List<Joinactivity> list = new ArrayList<Joinactivity>();
		try {
			Joinactivity joinActivity = null;
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, phone);

			rs = ps.executeQuery();
			while (rs.next()) {
				joinActivity = new Joinactivity();

				String cphone = rs.getString("cphone");
				String cactivityid = rs.getString("cactivityid");

				joinActivity.setCphone(cphone);
				joinActivity.setCactivityid(cactivityid);
				joinActivity.setJointime(rs.getDate("jointime"));
				joinActivity.setJoinstatus(rs.getInt("joinstatus"));

				ActivityDao ad = new ActivityDaoImpl();
				Userdao ud = new Userdaoimpl();

				joinActivity.setActivity(ad.findById(cactivityid));
				joinActivity.setUser(ud.findByPhone(cphone));

				list.add(joinActivity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, ps, conn);
		}

		return list;
	}
}
