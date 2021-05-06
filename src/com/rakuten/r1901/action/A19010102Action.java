package com.rakuten.r1901.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1901.form.F190101;
import com.rakuten.util.JdbcConnection;

public class A19010102Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F190101 f190101;

	@Override
	protected void init() throws Exception {
		setTitle("V190101:パスワード変更");
	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		
		try {
			conn = JdbcConnection.getConnection();
			
			sql = "UPDATE `rakuten`.`tbl00001` SET `PASSWORD` = ? WHERE USER_NAME = 'god'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, f190101.getNewpassword());
			int result = ps.executeUpdate();
			if (result == 1) {
				conn.commit();
				addError(null, "変更成功");
			} else {
				conn.rollback();
				addError(null, "変更失敗");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the f190101
	 */
	public F190101 getF190101() {
		return f190101;
	}

	/**
	 * @param f190101 the f190101 to set
	 */
	public void setF190101(F190101 f190101) {
		this.f190101 = f190101;
	}
	
}
