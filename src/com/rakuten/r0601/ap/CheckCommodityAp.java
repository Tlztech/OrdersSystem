package com.rakuten.r0601.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class CheckCommodityAp {

	public boolean execute(String commodityId,String detailNo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = SqlUtility.getSql("SQLR0001020");
			ps = conn.prepareStatement(sql);
			ps.setString(1, commodityId);
			ps.setString(2, detailNo);

			ResultSet rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("COUNT");
			}

			if (count == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
	
	public boolean execute(String commodityId,String detailNo, int companyId) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "SELECT COUNT(*) COUNT FROM TBL00012 WHERE COMMODITY_ID in (select commodity_id from company_commodity_tbl where commodity_id = ? AND (COMPANY_ID = ?)) AND " + 
					" DETAIL_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, commodityId);
			ps.setInt(2, companyId);
			ps.setString(3, detailNo);

			ResultSet rs = ps.executeQuery();
			int count = 0;
			while (rs.next()) {
				count = rs.getInt("COUNT");
			}

			if (count == 0) {
				return false;
			} else {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
