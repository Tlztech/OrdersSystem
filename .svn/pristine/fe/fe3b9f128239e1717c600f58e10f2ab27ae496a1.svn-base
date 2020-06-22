package com.rakuten.r0301.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rakuten.r0301.bean.AddCommodityCategoryInput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class AddCommodityCategoryAp {

	public void execute(AddCommodityCategoryInput input) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = SqlUtility.getSql("SQLR0001005");
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			ps = conn.prepareStatement(sql);
			ps.setString(1, input.getCategoryId());
			ps.setString(2, "100001");
			ps.setString(3, input.getCategoryName());
			ps.setString(4, "0");
			ps.setString(5, date);
			ps.setString(6, "123mart");
			ps.setString(7, date);
			ps.setString(8, "123mart");
			ps.setString(9, input.getNameJpn());
			ps.setString(10, input.getSozaiJpn());
			ps.setString(11, input.getSozaiChn());
			ps.setString(12, input.getKakaku());
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
