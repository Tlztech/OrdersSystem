package com.rakuten.r0503.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class UpdCommodityByIdAp {

	public void execute(String commodityId, String status) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		try {
			conn = JdbcConnection.getConnection();
			String sql = SqlUtility.getSql("SQLR0001019");

			ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, date);
			ps.setString(3, "123mart");
			ps.setString(4, commodityId);

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
