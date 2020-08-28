package com.rakuten.r1601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1601.form.F160101;
import com.rakuten.util.JdbcConnection;

public class A16010112Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F160101 f160101 = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		String chumonbango = f160101.getChumonbango();
		try {
			conn = JdbcConnection.getConnection();
			sql = "delete from kaisha_size_tbl where juchubango = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, chumonbango);
			ps.executeUpdate();
			ps.close();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
			addError(null, "操作終わった");
		}
	}

	protected void init() {
		setTitle("V160101:小工具");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F160101 getF160101() {
		return f160101;
	}

	public void setF160101(F160101 f160101) {
		this.f160101 = f160101;
	}
}
