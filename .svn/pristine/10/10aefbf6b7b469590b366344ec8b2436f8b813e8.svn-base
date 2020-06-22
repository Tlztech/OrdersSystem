package com.rakuten.r0301.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;
import com.rakuten.util.Utility;

public class GetCommodityCategoryMaxIdAp {

	public String execute() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = SqlUtility.getSql("SQLR0001007");
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			String maxCateGoryId = null;
			while (rs.next()) {
				maxCateGoryId = rs.getString("CATEGORY_ID");
				if (Utility.isEmptyString(maxCateGoryId)) {
					maxCateGoryId = "10000";
				} else {
					maxCateGoryId = String.valueOf(Integer
							.valueOf(maxCateGoryId) + 1);
				}
			}
			// commit
			conn.commit();
			return maxCateGoryId;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
