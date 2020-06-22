package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.common.A060105Common;
import com.rakuten.r0601.form.F060105;
import com.rakuten.r0601.form.MeisaiList;
import com.rakuten.util.JdbcConnection;

public class A06010501Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String waybillNo = null;
	F060105 f060105 = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		f060105 = new F060105();
		try {
			conn = JdbcConnection.getConnection();
			sql = "select * from baoguandan where waybillno = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, waybillNo);
			rs = ps.executeQuery();
			if (rs.next()) {

				f060105.setWayBillNo(waybillNo);
				f060105.setWeight(rs.getString("weight"));
				f060105.setDimensions(rs.getString("DIMENSIONS"));
				f060105.setDateOfExportation(rs.getString("dateofexportation"));
				f060105.setRcompanyName(rs.getString("rcompanyname"));
				f060105.setRaddress(rs.getString("raddress"));
				f060105.setRcity(rs.getString("rcity"));
				f060105.setRprovince(rs.getString("rprovince"));
				f060105.setRcountry(rs.getString("rcountry"));
				f060105.setRpostcode(rs.getString("rpostcode"));
				f060105.setRcontactName(rs.getString("rcontactname"));
				f060105.setRtelephone(rs.getString("rtelephone"));
				f060105.setScompanyName(rs.getString("scompanyname"));
				f060105.setSaddress(rs.getString("saddress"));
				f060105.setScity(rs.getString("scity"));
				f060105.setSprovince(rs.getString("sprovince"));
				f060105.setScountry(rs.getString("scountry"));
				f060105.setSpostcode(rs.getString("spostcode"));
				f060105.setScontactName(rs.getString("scontactname"));
				f060105.setStelephone(rs.getString("stelephone"));
				f060105.setTotalPcs(rs.getString("totalpcs"));
				f060105.setTotalValue(rs.getString("totalvalue"));
				sql = "select * from baoguandandetail where waybillno = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, waybillNo);
				rs2 = ps.executeQuery();

				List<MeisaiList> meisaiList = new ArrayList<MeisaiList>();
				f060105.setMeisaiList(meisaiList);
				MeisaiList meisai = null;
				while (rs2.next()) {
					meisai = new MeisaiList();
					meisaiList.add(meisai);
					meisai.setDescription(rs2.getString("DESCRIPTION"));
					meisai.setOrigin(rs2.getString("origin"));
					meisai.setPcs(rs2.getString("pcs"));
					meisai.setTotalValue(rs2.getString("totalValue"));
					meisai.setUnitValue(rs2.getString("unitValue"));
				}
				f060105.setHozozumiFlg("1");
			} else {
				A060105Common common = new A060105Common();
				f060105 = common.getDefaultValue(waybillNo);
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
		setTitle("V060105:报关单");

	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public F060105 getF060105() {
		return f060105;
	}

	public void setF060105(F060105 f060105) {
		this.f060105 = f060105;
	}

}
