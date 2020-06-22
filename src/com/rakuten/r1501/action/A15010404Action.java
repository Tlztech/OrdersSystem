package com.rakuten.r1501.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;

public class A15010404Action extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String result = null;
	private String zhangwuNo = null;
	private String fenleimingcheng = null;

	protected void exec() throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			conn = JdbcConnection.getConnection();
			sql = "update alipay_tbl set type=? where zhangwuliushuihao = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, fenleimingcheng);
			ps.setString(2, zhangwuNo);
			ps.execute();
			// commit
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

	protected void init() throws Exception {

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getZhangwuNo() {
		return zhangwuNo;
	}

	public void setZhangwuNo(String zhangwuNo) {
		this.zhangwuNo = zhangwuNo;
	}

	public String getFenleimingcheng() {
		return fenleimingcheng;
	}

	public void setFenleimingcheng(String fenleimingcheng) {
		this.fenleimingcheng = fenleimingcheng;
	}

}
