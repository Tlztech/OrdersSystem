package com.rakuten.r1101.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;

public class A11010103Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	String s_id = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "DELETE FROM dic_tbl WHERE s_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, s_id);
			ps.execute();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	protected void init() {
		setTitle("V110101:ÎÄ°¸×Öµä");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the s_id
	 */
	public String getS_id() {
		return s_id;
	}

	/**
	 * @param s_id
	 *            the s_id to set
	 */
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
}
