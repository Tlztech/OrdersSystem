package com.rakuten.r0801.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0801.form.F080101;
import com.rakuten.r0801.form.ShumokuList;
import com.rakuten.util.JdbcConnection;

public class A08010101Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F080101 f080101 = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		f080101 = new F080101();
		List<ShumokuList> shumokuList = new ArrayList<ShumokuList>();
		f080101.setShumokuList(shumokuList);
		ShumokuList shumoku = null;
		try {
			conn = JdbcConnection.getConnection();

			sql = "SELECT * FROM TBL00019";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				shumoku = new ShumokuList();
				shumokuList.add(shumoku);

				shumoku.setShumokubango(rs.getString("SHUMOKU_BANGO"));
				shumoku.setShumokuMeisho(rs.getString("SHUMOKUMEI"));
				shumoku.setKoshinbi(rs.getString("UPDATE_TIME"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		f080101.setResultCount(String.valueOf(f080101.getShumokuList().size()));
	}

	protected void init() {
		setTitle("V080101:日本网站商品清单");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F080101 getF080101() {
		return f080101;
	}

	public void setF080101(F080101 f080101) {
		this.f080101 = f080101;
	}

}
