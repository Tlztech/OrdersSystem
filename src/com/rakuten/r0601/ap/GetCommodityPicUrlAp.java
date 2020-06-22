package com.rakuten.r0601.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class GetCommodityPicUrlAp {
	public String execute(String commodityId, String detailNo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = SqlUtility.getSql("SQLR0001021");
			ps = conn.prepareStatement(sql);
			ps.setString(1, commodityId);
			ps.setString(2, detailNo);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString("PIC_URL");
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
