package com.rakuten.r0801.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0801.common.A0801Common;
import com.rakuten.r0801.form.F080101;
import com.rakuten.r0801.form.F080201;
import com.rakuten.r0801.form.ShohinList;
import com.rakuten.util.JdbcConnection;

public class A08010201Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F080101 f080101 = null;
	private F080201 f080201 = null;
	private A0801Common a0801Common = new A0801Common();

	protected void exec() throws Exception {
		int index = Integer.valueOf(getRowIndex());
		String shumokubango = f080101.getShumokuList().get(index)
				.getShumokubango();
		List<ShohinList> shohinList = getShohinList(shumokubango);
		String shumokumei = a0801Common.getShumokumei(shumokubango);
		f080201 = new F080201();
		f080201.setShohinList(shohinList);
		f080201.setShumokumei(shumokumei);
		f080201.setShumokubango(shumokubango);
		f080201.setResultCount(String.valueOf(shohinList.size()));

	}

	private List<ShohinList> getShohinList(String shumokubango)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<ShohinList> shohinList = new ArrayList<ShohinList>();
		ShohinList shohinInfo = null;
		conn = JdbcConnection.getConnection();

		try {
			sql = "SELECT * FROM TBL00020 WHERE SHUMOKU_BANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shumokubango);
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinInfo = new ShohinList();
				shohinList.add(shohinInfo);

				shohinInfo.setMekabango(rs.getString("MEKA_BANGO"));
				shohinInfo.setBurando(rs.getString("BURANDO"));
				shohinInfo.setShouhinmei(rs.getString("SHOUHINMEI"));
				shohinInfo.setRinku(rs.getString("RINKU"));
				shohinInfo.setSozai(rs.getString("SOZAI"));
				shohinInfo.setKara(rs.getString("KARA"));
				shohinInfo.setKakaku(rs.getString("KAKAKU"));
				shohinInfo.setSaizu(rs.getString("SAIZU"));
				shohinInfo.setZaiko(rs.getString("ZAIKO"));
				shohinInfo.setShouhinshousai(rs.getString("SHOUHIN_SHOUSAI"));
				shohinInfo.setBiko(rs.getString("BIKO"));
				shohinInfo.setTaobaoStatus(rs.getString("STATUS"));
				shohinInfo.setShouhinStatus(rs.getString("UPDATED"));
				shohinInfo.setSaishukoshinbi(rs.getString("UPDATE_TIME")
						.replace(".0", ""));
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return shohinList;

	}

	protected void init() {
		setTitle("V080201:日本网站商品清单-照会");

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

	public F080201 getF080201() {
		return f080201;
	}

	public void setF080201(F080201 f080201) {
		this.f080201 = f080201;
	}

}
