package com.rakuten.r1301.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;

public class A13010108Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String result = "";
	private String orderNo = "";
	private String data1 = "";
	private String data2 = "";
	private String data3 = "";
	private String data4 = "";

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "UPDATE common_order_tbl SET OTODOKEBISHITEI = ? , OTODOKEJIKANTAI1 = ? , OTODOKEJIKANTAI2 = ? , OTODOKEJIKANTAI3 = ? WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, data1);
			ps.setString(2, data2);
			ps.setString(3, data3);
			ps.setString(4, data4);
			ps.setString(5, orderNo);
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

	/**
	 * @return the data1
	 */
	public String getData1() {
		return data1;
	}

	/**
	 * @param data1
	 *            the data1 to set
	 */
	public void setData1(String data1) {
		this.data1 = data1;
	}

	/**
	 * @return the data2
	 */
	public String getData2() {
		return data2;
	}

	/**
	 * @param data2
	 *            the data2 to set
	 */
	public void setData2(String data2) {
		this.data2 = data2;
	}

	/**
	 * @return the data3
	 */
	public String getData3() {
		return data3;
	}

	/**
	 * @param data3
	 *            the data3 to set
	 */
	public void setData3(String data3) {
		this.data3 = data3;
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

	public String getData4() {
		return data4;
	}

	public void setData4(String data4) {
		this.data4 = data4;
	}

}
