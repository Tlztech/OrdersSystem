package com.rakuten.common.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class GetJiaoyiFenleiListAction extends BaseAction {

	private static final long serialVersionUID = -5541718078289452647L;
	private String result = null;
	private String area = null;
	private String kubun = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			conn = JdbcConnection.getConnection();
			if (!Utility.isEmptyString(kubun)) {
				sql = "SELECT * FROM fenleiinfo WHERE area = ? AND kubun = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, area);
				ps.setString(2, kubun);
			} else {
				sql = "SELECT * FROM fenleiinfo WHERE area = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, area);

			}
			rs = ps.executeQuery();
			while (rs.next()) {
				if (Utility.isEmptyString(result)) {
					result = rs.getString("kanribango");
					result = result + "%" + rs.getString("fenlei_name");
				} else {
					result = result + "&" + rs.getString("kanribango");
					result = result + "%" + rs.getString("fenlei_name");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
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

	public String getKubun() {
		return kubun;
	}

	public void setKubun(String kubun) {
		this.kubun = kubun;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
