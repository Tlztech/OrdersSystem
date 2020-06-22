package com.rakuten.r1301.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;

public class A13010112Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String result = "";
	private String orderNo = "";
	private String thisSize = "";
	private String unsokaisha = "";

	protected void exec() throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		try {

			conn = JdbcConnection.getConnection();

			String sql = "delete from kaisha_size_tbl where juchubango = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			ps.execute();

			sql = "insert into kaisha_size_tbl values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			ps.setString(2, unsokaisha);
			ps.setString(3, thisSize);
			ps.execute();

			conn.commit();
			result = "1";
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

	public String getThisSize() {
		return thisSize;
	}

	public void setThisSize(String thisSize) {
		this.thisSize = thisSize;
	}

	public String getUnsokaisha() {
		return unsokaisha;
	}

	public void setUnsokaisha(String unsokaisha) {
		this.unsokaisha = unsokaisha;
	}

}
