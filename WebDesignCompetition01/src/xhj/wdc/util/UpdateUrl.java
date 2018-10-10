package xhj.wdc.util;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import xhj.wdc.domain.Activity;

public class UpdateUrl {

	public static void main(String[] args) {
		try {

			String ori_url = "119.29.35.43";
			String now_url = "localhost:8080";

			String sql = "select * from activity";
			QueryRunner qr = new QueryRunner(DbManager.getSource());
			DbManager.startTran();
			List<Activity> list = qr.query(sql, new BeanListHandler<Activity>(Activity.class));
			for (Activity ac : list) {
				String picpath = ac.getPicpath();
				picpath = picpath.replace(ori_url, now_url);
				
				sql = "update activity set picpath=? where activityid=?";
				qr.update(sql, picpath, ac.getActivityid());
				
				DbManager.commit();
			}
			System.out.println("-------end----------");
		} catch (SQLException e) {
			DbManager.rollback();
			DbManager.release();
			e.printStackTrace();
		}

	}

}
