package xhj.wdc.dao.jdbc.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import xhj.wdc.dao.ActivityDao;
import xhj.wdc.domain.Activity;
import xhj.wdc.util.DbManager;
import xhj.wdc.util.JDBCUtil;

public class ActivityDaoImpl implements ActivityDao {

	// 添加活动
	public void addActivity(Activity activity) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "insert into activity(activityid,title,picpath,starttime,endtime,type,status,ispay,money,limitsize,currentsize,place,clicknum,phone,addtime,description) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),?)";

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, activity.getActivityid());
			ps.setString(2, activity.getTitle());
			ps.setString(3, activity.getPicpath());
			ps.setString(4, activity.getStarttime());
			ps.setString(5, activity.getEndtime());
			ps.setInt(6, activity.getType());
			ps.setInt(7, 0);
			ps.setInt(8, activity.getIspay());
			ps.setFloat(9, activity.getMoney());
			ps.setInt(10, activity.getLimitsize());
			ps.setInt(11, activity.getCurrentsize());
			ps.setString(12, activity.getPlace());
			ps.setInt(13, activity.getClicknum());
			ps.setString(14, activity.getPhone());
			ps.setString(15, activity.getDescription());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, ps, conn);
		}
	}

	// 通过id查询活动
	public Activity findById(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select activityid,title,picpath,starttime,endtime,type,status,ispay,money,limitsize,currentsize,place,clicknum,phone,addtime,description from activity where activityid=?";

		try {
			Activity activity = new Activity();
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();
			while (rs.next()) {
				activity.setActivityid(rs.getString("activityid"));
				activity.setTitle(rs.getString("title"));
				activity.setPicpath(rs.getString("picpath"));
				activity.setStarttime(rs.getString("starttime"));
				activity.setEndtime(rs.getString("endtime"));
				activity.setType(rs.getShort("type"));
				activity.setStatus(rs.getShort("status"));
				activity.setIspay(rs.getShort("ispay"));
				activity.setMoney(rs.getFloat("money"));
				activity.setLimitsize(rs.getInt("limitsize"));
				activity.setCurrentsize(rs.getInt("currentsize"));
				activity.setPlace(rs.getString("place"));
				activity.setClicknum(rs.getInt("clicknum"));
				activity.setPhone(rs.getString("phone"));
				activity.setAddtime(rs.getTimestamp("addtime"));
				activity.setDescription(rs.getString("description"));

				return activity;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, ps, conn);
		}

		return null;
	}

	// 查询2个当前用户最新发布的活动
	public List<Activity> getActivityByPhone(String phone) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select activityid,title,picpath,starttime,endtime,type,status,ispay,money,limitsize,currentsize,place,clicknum,phone,addtime,description from activity where phone=? ORDER BY addtime desc limit 0,2 ";
		List<Activity> list = new ArrayList<Activity>();
		try {
			Activity activity = null;
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, phone);

			rs = ps.executeQuery();
			while (rs.next()) {
				activity = new Activity();

				activity.setActivityid(rs.getString("activityid"));
				activity.setTitle(rs.getString("title"));
				activity.setPicpath(rs.getString("picpath"));
				activity.setStarttime(rs.getString("starttime"));
				activity.setEndtime(rs.getString("endtime"));
				activity.setType(rs.getShort("type"));
				activity.setStatus(rs.getShort("status"));
				activity.setIspay(rs.getShort("ispay"));
				activity.setMoney(rs.getFloat("money"));
				activity.setLimitsize(rs.getInt("limitsize"));
				activity.setCurrentsize(rs.getInt("currentsize"));
				activity.setPlace(rs.getString("place"));
				activity.setClicknum(rs.getInt("clicknum"));
				activity.setPhone(rs.getString("phone"));
				activity.setAddtime(rs.getTimestamp("addtime"));
				activity.setDescription(rs.getString("description"));

				list.add(activity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, ps, conn);
		}

		return list;
	}

	// 查询最新的3个活动
	public List<Activity> getNew3(int type) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select activityid,title,picpath,starttime,endtime,type,status,ispay,money,limitsize,currentsize,place,clicknum,phone,addtime,description from activity where type=? order by addtime desc limit 0,3 ";
		List<Activity> list = new ArrayList<Activity>();
		try {
			Activity activity = null;
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, type);

			rs = ps.executeQuery();
			while (rs.next()) {
				activity = new Activity();

				activity.setActivityid(rs.getString("activityid"));
				activity.setTitle(rs.getString("title"));
				activity.setPicpath(rs.getString("picpath"));
				activity.setStarttime(rs.getString("starttime"));
				activity.setEndtime(rs.getString("endtime"));
				activity.setType(rs.getShort("type"));
				activity.setStatus(rs.getShort("status"));
				activity.setIspay(rs.getShort("ispay"));
				activity.setMoney(rs.getFloat("money"));
				activity.setLimitsize(rs.getInt("limitsize"));
				activity.setCurrentsize(rs.getInt("currentsize"));
				activity.setPlace(rs.getString("place"));
				activity.setClicknum(rs.getInt("clicknum"));
				activity.setPhone(rs.getString("phone"));
				activity.setAddtime(rs.getTimestamp("addtime"));
				String description = rs.getString("description");
				if (description.length() > 40) {
					description = description.substring(0, 41);
					description += "。。。";
				}
				activity.setDescription(description);

				list.add(activity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, ps, conn);
		}

		return list;
	}

	// 查询活动的总数
	public int getCount() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select count(*) from activity";
		int num = 0;
		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, ps, conn);
		}

		return num;
	}

	// 查询所有的活动
	public List<Activity> getAllActivity() {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select activityid,title,picpath,starttime,endtime,type,status,ispay,money,limitsize,currentsize,place,clicknum,phone,addtime,description from activity";
		List<Activity> list = new ArrayList<Activity>();
		try {
			Activity activity = null;
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				activity = new Activity();

				activity.setActivityid(rs.getString("activityid"));
				activity.setTitle(rs.getString("title"));
				activity.setPicpath(rs.getString("picpath"));
				activity.setStarttime(rs.getString("starttime"));
				activity.setEndtime(rs.getString("endtime"));
				activity.setType(rs.getShort("type"));
				activity.setStatus(rs.getShort("status"));
				activity.setIspay(rs.getShort("ispay"));
				activity.setMoney(rs.getFloat("money"));
				activity.setLimitsize(rs.getInt("limitsize"));
				activity.setCurrentsize(rs.getInt("currentsize"));
				activity.setPlace(rs.getString("place"));
				activity.setClicknum(rs.getInt("clicknum"));
				activity.setPhone(rs.getString("phone"));
				activity.setAddtime(rs.getTimestamp("addtime"));
				activity.setDescription(rs.getString("description"));

				list.add(activity);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, ps, conn);
		}

		return list;
	}

	// 根据id删除活动
	public void deleteActivity(String id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "delete from activity where activityid=?";

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, ps, conn);
		}
	}

	// 修改活动
	public void updateActivity(Activity activity) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "update activity set title=?,picpath=?,starttime=?,endtime=?,type=?,status=?,ispay=?,money=?,limitsize=?,place=?,phone=?,description=? where activityid=?";

		try {
			conn = JDBCUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, activity.getTitle());
			ps.setString(2, activity.getPicpath());
			ps.setString(3, activity.getStarttime());
			ps.setString(4, activity.getEndtime());
			ps.setInt(5, activity.getType());
			ps.setInt(6, activity.getStatus());
			ps.setInt(7, activity.getIspay());
			ps.setFloat(8, activity.getMoney());
			ps.setInt(9, activity.getLimitsize());
			ps.setString(10, activity.getPlace());
			ps.setString(11, activity.getPhone());
			ps.setString(12, activity.getDescription());
			ps.setString(13, activity.getActivityid());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.free(rs, ps, conn);
		}
	}

	// 改变活动的状态
	public void updateStatus(int status, String activityid) {

		try {
			if (status == 3) {
				String sql = "update activity set status=? where activityid=?";
				QueryRunner qr = new QueryRunner(DbManager.getSource());
				qr.update(sql, status,activityid);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 得到总行数
	public int getCountRow() {
		String sql = "select count(*) from activity where type=1";
		try {
			QueryRunner runner = new QueryRunner(DbManager.getSource());
			return ((Long) runner.query(sql, new ScalarHandler())).intValue();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	// 返回当前页需要显示的活动
	public List<Activity> getCustByPage(int from, int count) {
		String sql = "select * from activity where type=1 limit ?,? ";
		try {
			QueryRunner runner = new QueryRunner(DbManager.getSource());
			return runner.query(sql, new BeanListHandler<Activity>(Activity.class), from, count);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
}
