package com.rakuten.r1002.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Util;
import com.rakuten.r1002.form.JuchujoutaiList;
import com.rakuten.r1002.form.JuchushousaiiList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A1002Common {
	public List<JuchujoutaiList> getJuchujoutaiList(String juchubango)
			throws Exception {
		List<JuchujoutaiList> juchujoutaiList = new ArrayList<JuchujoutaiList>();
		JuchujoutaiList juchujoutai = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from order_sts_tbl where replace (juchubango,\"-\",\"\") = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, juchubango.replace("-", ""));
			rs = ps.executeQuery();
			while (rs.next()) {

				String ststxtid = rs.getString("order_sts");
				List<String[]> orderStsTextList = getOrderstsText(ststxtid);
				for (String[] stsinfo : orderStsTextList) {

					juchujoutai = new JuchujoutaiList();
					juchujoutaiList.add(juchujoutai);
					juchujoutai.setStsdata(stsinfo[0]);
					juchujoutai.setJoutai(stsinfo[1]);
					juchujoutai.setStsid(stsinfo[2]);
					if (!"1".equals(stsinfo[2]) && !"2".equals(stsinfo[2])
							&& !"3".equals(stsinfo[2])
							&& !"4".equals(stsinfo[2])) {

						juchujoutai.setEditable(true);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return juchujoutaiList;
	}

	public void setHasozumiSts(List<String[]> shoriList) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();

			for (String[] shoriinfo : shoriList) {
				sql = "update order_sts_tbl set order_sts = concat(order_sts,?) where juchubango = ?";
				ps = conn.prepareStatement(sql);
				String shoribango = Utility.getShoribango();
				ps.setString(1, "&" + shoribango + "%" + Utility.getDateTime());
				ps.setString(2, shoriinfo[0]);
				ps.execute();
				String hasobungen = "";
				sql = "select * from order_sts_text_tbl where shoribango = '5'";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					hasobungen = rs.getString("ststext");
					hasobungen = hasobungen.replace("&{haisokaisha}",
							Utility.getHaisokaishaName(shoriinfo[1]));
					hasobungen = hasobungen.replace("&{denpyobango}",
							shoriinfo[2]);
					hasobungen = hasobungen.replace("&{searchurl}",
							Utility.getSearchUrl(shoriinfo[1]));
				}
				sql = "insert into order_sts_text_tbl values(?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shoribango);
				ps.setString(2, hasobungen);
				ps.setString(3, "1");
				ps.execute();

				sql = "update order_sts_detail_tbl set order_sts = ? where juchubango = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, "発送完了");
				ps.setString(2, shoriinfo[0]);

				ps.execute();
			}

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public List<JuchushousaiiList> getJuchushousaiiList(String juchubango)
			throws Exception {
		List<JuchushousaiiList> juchushousaiiList = new ArrayList<JuchushousaiiList>();
		JuchushousaiiList juchushousai = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  order_sts_detail_tbl where replace (juchubango,\"-\",\"\") = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, juchubango.replace("-", ""));
			rs = ps.executeQuery();
			while (rs.next()) {
				String shohinbango = rs.getString("shohinbango");
				juchushousai = new JuchushousaiiList();
				juchushousaiiList.add(juchushousai);
				juchushousai.setPicurl(Utility.getPicUrl(Utility
						.getCommodityId(shohinbango)));
				juchushousai.setShohinbango(shohinbango);
				juchushousai.setKosu(rs.getString("kosu"));
				juchushousai.setShohinjoutai(rs.getString("order_sts"));
				juchushousai.setShohinmei(Utility.getShohinmei(Utility
						.getCommodityId(shohinbango)));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return juchushousaiiList;
	}

	public List<String[]> getOrderstsText(String ststxtid) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String[]> orderStsTextList = new ArrayList<String[]>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  order_sts_text_tbl where shoribango = ?";
			ps = conn.prepareStatement(sql);

			if (!Utility.isEmptyString(ststxtid)) {
				String[] idArr = ststxtid.split("&");
				for (String id : idArr) {
					String[] idInfoArr = id.split("%");
					ps.setString(1, idInfoArr[0]);
					rs = ps.executeQuery();
					while (rs.next()) {
						orderStsTextList.add(new String[] { idInfoArr[1],
								rs.getString("ststext"), idInfoArr[0] });
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return orderStsTextList;

	}
}
