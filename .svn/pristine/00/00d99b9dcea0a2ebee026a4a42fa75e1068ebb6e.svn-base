package com.rakuten.common.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.util.JdbcConnection;

public class checkUrlAction extends BaseAction {

	private static final long serialVersionUID = -5541718078289452647L;
	private String result = null;
	private String url = null;

	protected void exec() throws Exception {
		result = "";
		String checkstr = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = null;
			checkstr = url.substring(url.lastIndexOf("/")+1);
			checkstr = checkstr.substring(0, checkstr.indexOf(".html"));
			sql = "SELECT count(*) count FROM tbl00023 where shiireurl like ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + checkstr + "%");
			rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getInt("count") > 0) {
					result = "1";
				} else {
					result = "0";
				}
			}

		} catch (

		Exception e)

		{
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally

		{
			conn.close();
		}

	}

	protected void init() {
	}

	protected void isValidated() throws Exception {
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
