package com.rakuten.r1301.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;

public class A13010110Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String result = "";
	private String orderNo = "";
	private String unsokaisha = "";
	private String denpyobango = "";

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "UPDATE hassou_tbl SET unsokaisha = ? , toiawasebango = ?  WHERE juchubango = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, unsokaisha);
			ps.setString(2, denpyobango);
			ps.setString(3, orderNo);
			int count = ps.executeUpdate();

			
			if (count > 0) {
				conn.commit();
				result = "true";
			}
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
	 * @return the unsokaisha
	 */
	public String getUnsokaisha() {
		return unsokaisha;
	}

	/**
	 * @param unsokaisha
	 *            the unsokaisha to set
	 */
	public void setUnsokaisha(String unsokaisha) {
		this.unsokaisha = unsokaisha;
	}

	/**
	 * @return the denpyobango
	 */
	public String getDenpyobango() {
		return denpyobango;
	}

	/**
	 * @param denpyobango
	 *            the denpyobango to set
	 */
	public void setDenpyobango(String denpyobango) {
		this.denpyobango = denpyobango;
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
