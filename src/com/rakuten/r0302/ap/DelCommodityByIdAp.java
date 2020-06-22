package com.rakuten.r0302.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class DelCommodityByIdAp {

	public void execute(String commodityId) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = SqlUtility.getSql("SQLR0001015");
			ps = conn.prepareStatement(sql);
			ps.setString(1, commodityId);
			ps.execute();

			sql = SqlUtility.getSql("SQLR0001016");
			ps = conn.prepareStatement(sql);
			ps.setString(1, commodityId);
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
