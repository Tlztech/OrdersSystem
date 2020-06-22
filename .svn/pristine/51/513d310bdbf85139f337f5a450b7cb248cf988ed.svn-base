package com.rakuten.r0301.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rakuten.r0301.bean.UpdCommodityCategoryInput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class UpdCommodityCategoryAp {

	public void execute(UpdCommodityCategoryInput input) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = SqlUtility.getSql("SQLR0001008");
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			ps = conn.prepareStatement(sql);
			ps.setString(1, input.getCategoryName());
			ps.setString(2, "100001");
			ps.setString(3, date);
			ps.setString(4, "123mart");
			ps.setString(5, input.getNameJpn());
			ps.setString(6, input.getSozaiJpn());
			ps.setString(7, input.getSozaiChn());
			ps.setString(8, input.getKakaku());
			ps.setString(9, input.getCategoryId());
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
