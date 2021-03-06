package com.rakuten.r1301.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;

public class A13010202Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String result = "";
	private String orderNo = "";

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "select count(*) count from tbl00024 where CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (rs.getLong("count") > 0) {
					result = "1";
				}
			}

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	protected void init() throws Exception {
		setTitle("V130201:�k�̈́I��һ�E");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
