package com.rakuten.r0302.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;

public class A03020205Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String result = "";
	private String data1 = "";
	private String data2 = "";

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "UPDATE tbl00011 SET SOURCE = ?, UPDATE_TIME = ? WHERE commodity_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, data2);
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			ps.setString(2, date);
			ps.setString(3, data1);
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
		setTitle("V130201:�k�̈́I��һ�E");
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

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

}
