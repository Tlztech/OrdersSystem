package com.rakuten.common.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class getSozaiJpstrAction extends BaseAction {

	private static final long serialVersionUID = -5541718078289452647L;
	private String result = null;
	private String cnstr = null;

	protected void exec() throws Exception {
		result = "";

		String[] cnstrArr = cnstr.split(",");

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = null;

			sql = "SELECT * FROM dic_tbl where c_name = ? and id = '101'";

			for (String str : cnstrArr) {
				String jpstr = "";
				ps = conn.prepareStatement(sql);
				ps.setString(1, str);
				rs = ps.executeQuery();
				while (rs.next()) {
					jpstr = rs.getString("j_name");
				}

				result += jpstr + ",";
			}
			if (result.contains(",")) {
				result = result.substring(0, result.length() - 1);
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

	public String getCnstr() {
		return cnstr;
	}

	public void setCnstr(String cnstr) {
		this.cnstr = cnstr;
	}
}
