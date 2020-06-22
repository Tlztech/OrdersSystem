package com.rakuten.r1302.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1301.form.F130101;
import com.rakuten.util.JdbcConnection;

public class A13020111Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F130101 f130101 = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "update tbl00028 set password_value = ? where password_id = ? and password_key = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, f130101.isCheckhitiyo() ? "1" : "0");
			ps.setString(2, "1");
			ps.setString(3, f130101.getCheckPass());

			int count = ps.executeUpdate();

			if (count == 0) {
				addError(null, "•—©`•π•Ô©`•…’`§Í");
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
		setTitle("V130201:∞kÀÕÑI¿Ì“ª”E");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F130101 getF130101() {
		return f130101;
	}

	public void setF130101(F130101 f130101) {
		this.f130101 = f130101;
	}

}
