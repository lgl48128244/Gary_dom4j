package commons;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCfanye {

	public static void main(String[] args) {
		printPage(5, 3);
	}

	public static void printPage(int pageSize, int page) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int begin = (page - 1) * pageSize + 1;
		int end = page * pageSize;
		String query = "select * from emp";
		String sql = "select * from (select a.*, rownum rn from (" + query + ") a where rownum<= ?) where rn>= ?";
		System.out.println(sql);
		try {
			conn = DataBaseUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, end);
			ps.setInt(2, begin);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("ename"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DataBaseUtil.closeConnStmtRs(conn, ps, rs);
		}
	}
}