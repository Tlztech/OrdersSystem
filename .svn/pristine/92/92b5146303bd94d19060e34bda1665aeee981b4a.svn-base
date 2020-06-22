package com.rakuten.r0301.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class DelCommodityCategoryAp {

	public void execute(String cateId) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = SqlUtility.getSql("SQLR0001006");
			ps = conn.prepareStatement(sql);
			ps.setString(1, cateId);
			ps.execute();
			// commit
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
