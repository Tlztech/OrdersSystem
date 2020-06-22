package com.rakuten.r0601.common;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.r0601.form.F060105;
import com.rakuten.r0601.form.MeisaiList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A060105Common {

	public F060105 getDefaultValue(String waybillNo) throws Exception {
		F060105 f060105 = new F060105();

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		f060105 = new F060105();
		try {
			conn = JdbcConnection.getConnection();
			sql = "select * from baoguandanmuban where mubanid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "001");
			rs = ps.executeQuery();
			while (rs.next()) {
				f060105.setWayBillNo(waybillNo);
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
			}

			String[] dateandweight = getDateAndWeight(waybillNo, conn);
			f060105.setWeight(dateandweight[1]);
			if (!Utility.isEmptyString(dateandweight[0])) {
				f060105.setDateOfExportation(Utility
						.formatData2(dateandweight[0]));
			}

			List<String[]> shohinbangoList = new ArrayList<String[]>();
			sql = "select commodity_id,quantity from tbl00014 where waybill_no = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, waybillNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinbangoList
						.add(new String[] { rs.getString("commodity_id"),
								rs.getString("quantity") });
			}
			List<MeisaiList> meisaiList = getFenlei(shohinbangoList, conn);
			f060105.setMeisaiList(meisaiList);
			BigDecimal totalValue = new BigDecimal(0).setScale(2,
					BigDecimal.ROUND_HALF_UP);
			Integer totalPcs = 0;
			for (MeisaiList meisai : meisaiList) {
				if (!Utility.isEmptyString(meisai.getTotalValue())) {
					totalValue = totalValue.add(new BigDecimal(meisai
							.getTotalValue()).setScale(2,
							BigDecimal.ROUND_HALF_UP));
				}
				totalPcs = totalPcs + Integer.valueOf(meisai.getPcs());
			}
			f060105.setTotalPcs(String.valueOf(totalPcs));
			f060105.setTotalValue(totalValue.setScale(2,
					BigDecimal.ROUND_HALF_UP).toString());
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return f060105;
	}

	private String[] getDateAndWeight(String waybillno, Connection conn)
			throws Exception {

		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String[] result = new String[2];

		sql = "select * from tbl00013 where waybill_no = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, waybillno);
		rs = ps.executeQuery();
		while (rs.next()) {
			result[0] = rs.getString("DELIVER_DAY");
			result[1] = rs.getString("weight");
		}

		return result;
	}

	public List<MeisaiList> getFenlei(List<String[]> shohinbangoList,
			Connection conn) throws Exception {

		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<MeisaiList> meisaiList = new ArrayList<MeisaiList>();
		List<String[]> categoryList = new ArrayList<String[]>();
		sql = "select category_id from tbl00011 where commodity_id = ?";
		ps = conn.prepareStatement(sql);
		for (String[] shohinbango : shohinbangoList) {
			shohinbango[0] = Utility.getCommodityId(shohinbango[0]);
			ps.setString(1, shohinbango[0]);
			rs = ps.executeQuery();
			boolean ariFlg = false;
			String categoryid = shohinbango[0];
			if (rs.next()) {
				if (!Utility.isEmptyString(rs.getString("category_id"))
						&& !"100001".equals(rs.getString("category_id"))) {
					categoryid = rs.getString("category_id");
				}
			}
			for (String[] category : categoryList) {
				if (category[0].equals(categoryid)) {
					ariFlg = true;
					category[1] = String.valueOf(Integer.valueOf(category[1])
							+ Integer.valueOf(shohinbango[1]));
				}
			}
			if (!ariFlg) {
				categoryList.add(new String[] { categoryid, shohinbango[1] });
			}
		}

		sql = "select * from tbl00010 where category_id = ?";
		ps = conn.prepareStatement(sql);
		for (String[] cate : categoryList) {
			MeisaiList meisai = new MeisaiList();

			ps.setString(1, cate[0]);
			rs = ps.executeQuery();

			if (rs.next()) {
				meisai.setDescription(rs.getString("category_name") + " "
						+ rs.getString("name_jpn") + " " + "("
						+ rs.getString("sozai_chn") + ")");

				BigDecimal kakaku = new BigDecimal(rs.getString("kakaku"))
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal kosu = new BigDecimal(cate[1]).setScale(2,
						BigDecimal.ROUND_HALF_UP);
				meisai.setTotalValue(kakaku.multiply(kosu)
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				meisai.setUnitValue(kakaku.toString());
			} else {
				meisai.setDescription(cate[0]);
			}
			meisai.setPcs(cate[1]);
			meisai.setOrigin("CN");

			meisaiList.add(meisai);
		}

		return meisaiList;
	}

	public MeisaiList getMeisaiInfo(String cateId) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		MeisaiList meisai = new MeisaiList();
		try {
			conn = JdbcConnection.getConnection();
			sql = "select * from tbl00010 where category_id = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, cateId);
			rs = ps.executeQuery();

			while (rs.next()) {
				meisai.setDescription(rs.getString("category_name") + " "
						+ rs.getString("name_jpn") + " " + "("
						+ rs.getString("sozai_chn") + ")");
				if (!Utility.isEmptyString(rs.getString("kakaku"))) {
					meisai.setUnitValue(new BigDecimal(rs.getString("kakaku"))
							.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
				}

			}

			meisai.setOrigin("CN");

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return meisai;
	}
}
