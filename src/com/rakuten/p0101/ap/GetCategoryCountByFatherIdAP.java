package com.rakuten.p0101.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.p0101.bean.GetCommodityCategoryByFatherIdInput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class GetCategoryCountByFatherIdAP {

	public Long execute(GetCommodityCategoryByFatherIdInput input)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String Id = input.getID();
			String sql = SqlUtility.getSql("SQLR0001010");
			ps = conn.prepareStatement(sql);
			ps.setString(1, Id);
			ResultSet rs = ps.executeQuery();
			Long count = 0l;
			while (rs.next()) {
				count = rs.getLong("COUNT");
			}

			// commit
			conn.commit();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
