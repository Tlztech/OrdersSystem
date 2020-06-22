package com.rakuten.r1701.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;

public class A17010203Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	String id = null;
	String shumokuid = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "DELETE FROM tbl00033 WHERE id = ? and shumokuid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, shumokuid);
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
		setTitle("V170102:商品分类详细");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShumokuid() {
		return shumokuid;
	}

	public void setShumokuid(String shumokuid) {
		this.shumokuid = shumokuid;
	}

}
