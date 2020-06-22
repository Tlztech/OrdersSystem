package com.rakuten.r1304.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1304.form.F130401;
import com.rakuten.util.JdbcConnection;

public class A13040101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F130401 f130401 = new F130401();

	public F130401 getF130401() {
		return f130401;
	}

	public void setF130401(F130401 f130401) {
		this.f130401 = f130401;
	}

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  baoguandanmuban where mubanId = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "001");
			rs = ps.executeQuery();
			while (rs.next()) {
				f130401.setRcompanyname(rs.getString("rcompanyname"));
				f130401.setRaddress(rs.getString("raddress"));
				f130401.setRcity(rs.getString("rcity"));
				f130401.setRprovince(rs.getString("rprovince"));
				f130401.setRcountry(rs.getString("rcountry"));
				f130401.setRpostcode(rs.getString("rpostcode"));
				f130401.setRcontactname(rs.getString("rcontactname"));
				f130401.setRtelephone(rs.getString("rtelephone"));
				f130401.setScompanyname(rs.getString("scompanyname"));
				f130401.setSaddress(rs.getString("saddress"));
				f130401.setScity(rs.getString("scity"));
				f130401.setSprovince(rs.getString("sprovince"));
				f130401.setScountry(rs.getString("scountry"));
				f130401.setSpostcode(rs.getString("spostcode"));
				f130401.setScontactname(rs.getString("scontactname"));
				f130401.setStelephone(rs.getString("stelephone"));
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
		setTitle("V130401:报关单模板设置");
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

}
