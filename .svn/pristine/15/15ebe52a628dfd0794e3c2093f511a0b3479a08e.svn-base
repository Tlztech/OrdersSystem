package com.rakuten.common.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class CheckShohinAriAction extends BaseAction {

	private static final long serialVersionUID = -5541718078289452647L;
	private String result = null;
	private String shohinbango = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT COUNT(*) COUNT FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, Utility.getCommodityId(shohinbango));
			ps.setString(2, Utility.getDetailN0(shohinbango));

			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("COUNT");
			}
			if (count > 0) {
				result = "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
	}

	protected void init() {
	}

	protected void isValidated() throws Exception {
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}
}
