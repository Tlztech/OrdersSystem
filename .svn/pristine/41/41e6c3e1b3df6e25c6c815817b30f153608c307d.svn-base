package com.rakuten.r1301.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A13010111Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String result = "";
	private String orderNo = "";
	private String thisSize = "";

	protected void exec() throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String address = "";
			conn = JdbcConnection.getConnection();
			String sql = "select SOUFUSAKIJUSHOTODOFUKEN from common_order_tbl where chumonbango = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				address = rs.getString("SOUFUSAKIJUSHOTODOFUKEN");
			}

			Map<String, Integer> soryoMap = Utility.getSoryoMap();
			String kaisha = Utility.getBestSoryo(address, thisSize, soryoMap);

			sql = "delete from kaisha_size_tbl where juchubango = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			ps.execute();

			sql = "insert into kaisha_size_tbl values(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			ps.setString(2, kaisha);
			ps.setString(3, thisSize);
			ps.execute();

			conn.commit();
			result = kaisha;
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

}
