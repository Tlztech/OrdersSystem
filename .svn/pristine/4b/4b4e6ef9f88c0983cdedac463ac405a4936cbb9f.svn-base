package com.rakuten.r1601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1601.form.F160101;
import com.rakuten.util.JdbcConnection;

public class A16010103Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F160101 f160101 = null;

	protected void exec() throws Exception {
		String chumonbango = f160101.getChumonbango();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "delete from tbl00027 where chumonbango = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, chumonbango);
			int count1 = ps.executeUpdate();
			sql = "delete from common_order_tbl where chumonbango = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, chumonbango);
			int count2 = ps.executeUpdate();
			sql = "delete from common_order_detail_tbl where juchubango = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, chumonbango);
			ps.executeUpdate();
			if (count1 == 1 && count2 == 1) {
				addError(null, "操作成功");
				conn.commit();
			} else {
				addError(null, "操作失败");
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {

			conn.close();
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
