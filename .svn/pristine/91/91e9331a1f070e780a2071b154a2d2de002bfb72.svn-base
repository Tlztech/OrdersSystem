package com.rakuten.common.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class CountKokoku extends BaseAction {
	private String result = null;

	private String kokokuId = null;
	private String ipAddress = null;
	private String targetURL = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getTargetURL() {
		return targetURL;
	}

	public void setTargetURL(String targetURL) {
		this.targetURL = targetURL;
	}

	@Override
	protected void init() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("Access-Control-Allow-Origin", "*");
		Connection conn = null;
		PreparedStatement ps = null;
		if (Utility.isEmptyString(targetURL)) {
			targetURL = "";
		}
		try {
			conn = JdbcConnection.getConnection();
			String sql = "insert into tbl00030 values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, kokokuId);
			ps.setString(2, ipAddress);
			ps.setString(3, targetURL);
			ps.setString(4, Utility.getDateTime());
			ps.execute();

			conn.commit();
			result="{\"s\":\"ok\"}";
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

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getKokokuId() {
		return kokokuId;
	}

	public void setKokokuId(String kokokuId) {
		this.kokokuId = kokokuId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

}
