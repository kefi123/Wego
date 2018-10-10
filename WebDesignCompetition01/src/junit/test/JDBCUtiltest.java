package junit.test;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import xhj.wdc.util.JDBCUtil;

public class JDBCUtiltest {

	@Test
	// 测试JDBCUtil.getConnection是否能获取连接
	public void getConn() {
		Connection conn = null;
		try {
			conn = JDBCUtil.getConnection();
			System.out.println("conn:" + conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// JDBCUtil.free(null, null, conn);
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("conn:" + conn);
			System.out.println("end");
		}
	}
}
