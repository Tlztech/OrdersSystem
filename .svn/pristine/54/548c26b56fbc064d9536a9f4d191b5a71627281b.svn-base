package com.rakuten.r1001.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.util.JdbcConnection;

public class A10010222Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	OrderCommon orderCommon = new OrderCommon();
	private String juchubango = null;
	private String shohinbango = null;
	private String result = null;

	protected void isValidated() throws Exception {
		String[] shouhinbangoArr = shohinbango.split("&");

		Connection conn = null;
		PreparedStatement ps = null;

		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			for (String bango : shouhinbangoArr) {
				sql = "SELECT COUNT(*) COUNT FROM tbl00026 WHERE CHUMONBANGO = ? AND SHOUHINBANGO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, juchubango);
				ps.setString(2, bango);

				rs = ps.executeQuery();

				while (rs.next()) {
					Long count = rs.getLong("COUNT");
					if (count == 0) {
						String sql2 = "INSERT INTO TBL00026 VALUES(?,?)";
						ps = conn.prepareStatement(sql2);
						ps.setString(1, juchubango);
						ps.setString(2, bango);
						ps.execute();
					}
				}
			}

			conn.commit();
			result = "true";
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	public String getJuchubango() {
		return juchubango;
	}

	public void setJuchubango(String juchubango) {
		this.juchubango = juchubango;
	}

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
