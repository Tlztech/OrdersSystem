package com.rakuten.r1304.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1304.form.F130401;
import com.rakuten.util.JdbcConnection;

public class A13040102Action extends BaseAction {

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

		try {
			conn = JdbcConnection.getConnection();
			sql = "UPDATE baoguandanmuban SET rcompanyname = ?, raddress = ?, rcity = ?, rprovince = ?, rcountry = ?, rpostcode = ?, rcontactname = ?, rtelephone = ?, scompanyname = ?, saddress = ?, scity = ?, sprovince = ?, scountry = ?, spostcode = ?, scontactname = ?, stelephone = ? WHERE mubanId = ? ";
			ps = conn.prepareStatement(sql);
			int i = 0;

			ps.setString(++i, f130401.getRcompanyname());
			ps.setString(++i, f130401.getRaddress());
			ps.setString(++i, f130401.getRcity());
			ps.setString(++i, f130401.getRprovince());
			ps.setString(++i, f130401.getRcountry());
			ps.setString(++i, f130401.getRpostcode());
			ps.setString(++i, f130401.getRcontactname());
			ps.setString(++i, f130401.getRtelephone());
			ps.setString(++i, f130401.getScompanyname());
			ps.setString(++i, f130401.getSaddress());
			ps.setString(++i, f130401.getScity());
			ps.setString(++i, f130401.getSprovince());
			ps.setString(++i, f130401.getScountry());
			ps.setString(++i, f130401.getSpostcode());
			ps.setString(++i, f130401.getScontactname());
			ps.setString(++i, f130401.getStelephone());
			ps.setString(++i, "001");
			ps.execute();
			conn.commit();
			addError(null, "保存成功");
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
