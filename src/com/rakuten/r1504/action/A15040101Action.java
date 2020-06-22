package com.rakuten.r1504.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1504.form.F150401;
import com.rakuten.r1504.form.SeikyushoList;
import com.rakuten.util.JdbcConnection;

public class A15040101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F150401 f150401 = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  qingqiushu";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<SeikyushoList> seikyushoList = new ArrayList<SeikyushoList>();
			SeikyushoList seikyusho = null;
			while (rs.next()) {
				seikyusho = new SeikyushoList();
				seikyusho.setStartDay(rs.getString("startday"));
				seikyusho.setEndDay(rs.getString("endday"));
				seikyusho.setHiseikyusha(rs.getString("hiseikyusha"));
				seikyusho.setSeikyuid(rs.getString("seikyuid"));
				seikyusho.setSeikyukingaku(rs.getString("seikyukingaku"));
				seikyusho.setSeikyusha(rs.getString("seikyusha1"));
				seikyusho.setCreateDay(rs.getString("CREATE_TIME"));
				seikyushoList.add(seikyusho);
			}
			f150401 = new F150401();
			f150401.setSeikyushoList(seikyushoList);
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public F150401 getF150401() {
		return f150401;
	}

	public void setF150401(F150401 f150401) {
		this.f150401 = f150401;
	}

	protected void init() throws Exception {
		setTitle("V150401:请求书一览");
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

}
