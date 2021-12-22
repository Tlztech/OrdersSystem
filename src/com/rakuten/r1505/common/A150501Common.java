package com.rakuten.r1505.common;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.r1505.form.F150501;
import com.rakuten.r1505.form.MeisaiList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A150501Common {
	public void keisan(F150501 f150501) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			Map<String,Object> map =  ActionContext.getContext().getSession();
			int companyId;
			if (null == map.get("comp")) {
				companyId = -1;
			} else {
				companyId = (int)map.get("comp");
			}
			conn = JdbcConnection.getConnection();
			sql = "select distinct t1.juchubango,t1.shouhinbango,t3.tanka*? seikyutanka,t1.hassoukosu "
					+ "from hassou_tbl t1 left join common_order_detail_tbl t3 "
					+ "on t1.JUCHUBANGO = t3.JUCHUBANGO and t1.SHOUHINBANGO = t3.SHOUHINBANGO "
					+ "where  t1.CREATE_TIME between ? and  ? and t1.juchubango in (select order_id from company_order_tbl where order_id like ? AND (COMPANY_ID = ? OR ? = 0 OR ? = 1)) and t1.SHUBETSU = '0' order by t1.juchubango";
			ps = conn.prepareStatement(sql);
			ps.setString(1, f150501.getJishu());
			ps.setString(2, f150501.getStartDay() + " 00:00:00");
			ps.setString(3, f150501.getEndDay() + " 23:00:00");
			if ("トレンド最前線".equals(f150501.getHiseikyusha())) {
				ps.setString(4, "306%");
				ps.setInt(5, companyId);
				ps.setInt(6, companyId);
				ps.setInt(7, companyId);
			} else if ("勝意有限会社".equals(f150501.getHiseikyusha())) {
				ps.setString(4, "308%");
				ps.setInt(5, companyId);
				ps.setInt(6, companyId);
				ps.setInt(7, companyId);
			}
			rs = ps.executeQuery();
			List<MeisaiList> meisaiList = new ArrayList<MeisaiList>();
			while (rs.next()) {
				MeisaiList meisai = new MeisaiList();
				meisaiList.add(meisai);
				meisai.setJuchubango(rs.getString("t1.juchubango"));
				String tanka1 = "0";
				if (!Utility.isEmptyString(rs.getString("seikyutanka"))) {
					tanka1 = rs.getString("seikyutanka");
				}
				meisai.setTanka(tanka1);
				meisai.setKosu(rs.getString("t1.hassoukosu"));
				meisai.setShohinbango(rs.getString("t1.shouhinbango"));
				meisai.setKingaku("0");
				if (!Utility.isEmptyString(rs.getString("seikyutanka"))
						&& !Utility.isEmptyString(rs.getString("t1.hassoukosu"))) {
					BigDecimal tanka = new BigDecimal(rs.getString("seikyutanka"));
					BigDecimal kosu = new BigDecimal(rs.getString("t1.hassoukosu"));
					tanka.setScale(0, BigDecimal.ROUND_HALF_UP);
					kosu.setScale(0, BigDecimal.ROUND_HALF_UP);
					BigDecimal kingaku = new BigDecimal("0");
					kingaku = tanka.multiply(kosu).setScale(0, BigDecimal.ROUND_HALF_UP);
					meisai.setKingaku(kingaku.toString());
					meisai.setTanka(tanka.setScale(0, BigDecimal.ROUND_HALF_UP).toString());
				}
			}
			f150501.setMeisaiList(meisaiList);

			sql = "SELECT distinct t2.chumonbango,t1.HAISOHOHO FROM hassou_tbl t1 left join common_order_tbl t2 on t1.JUCHUBANGO = t2.CHUMONBANGO"
					+ " where juchubango in (select order_id from company_order_tbl where order_id like ? AND (COMPANY_ID = ? OR ? = 0 OR ? = 1)) and shubetsu = '0' "
					+ "and t1.CREATE_TIME between ? and  ? and t1.haisohoho = ?";
			ps = conn.prepareStatement(sql);
			if ("トレンド最前線".equals(f150501.getHiseikyusha())) {
				ps.setString(1, "306%");
				ps.setInt(2, companyId);
				ps.setInt(3, companyId);
				ps.setInt(4, companyId);
			} else if ("勝意有限会社".equals(f150501.getHiseikyusha())) {
				ps.setString(1, "308%");
				ps.setInt(2, companyId);
				ps.setInt(3, companyId);
				ps.setInt(4, companyId);
			}
			ps.setString(3, f150501.getStartDay() + " 00:00:00");
			ps.setString(4, f150501.getEndDay() + " 23:00:00");
			ps.setString(5, "1");
			rs = ps.executeQuery();
			Long i = 0L;
			while (rs.next()) {
				i++;
			}

			ps.setString(4, "2");
			rs = ps.executeQuery();
			Long j = 0L;
			while (rs.next()) {
				j++;
			}
			f150501.setTakyubinkosu(String.valueOf(i));
			f150501.setTakyubinkingaku(String
					.valueOf(Integer.valueOf(f150501.getTakyubintanka()) * Long.valueOf(f150501.getTakyubinkosu())));
			f150501.setMerubinkosu(String.valueOf(j));
			f150501.setMerubinkingaku(String
					.valueOf(Integer.valueOf(f150501.getMerubintanka()) * Long.valueOf(f150501.getMerubinkosu())));
			if (f150501.isDaibikiChk()) {
				sql = "SELECT distinct t2.chumonbango,t2.GOKEISHOHIN,t2.GOKEISOURYOU FROM hassou_tbl t1 left join common_order_tbl t2 "
						+ "on t1.JUCHUBANGO = t2.CHUMONBANGO "
						+ "where juchubango in (select order_id from company_order_tbl where order_id like ? AND (COMPANY_ID = ? OR ? = 0 OR ? = 1)) and shubetsu = '0' and t1.CREATE_TIME between ? and ? and t2.OSHIHARAISTS like '代金%'";
				ps = conn.prepareStatement(sql);
				if ("トレンド最前線".equals(f150501.getHiseikyusha())) {
					ps.setString(1, "306%");
					ps.setInt(2, companyId);
					ps.setInt(3, companyId);
					ps.setInt(4, companyId);
				} else if ("勝意有限会社".equals(f150501.getHiseikyusha())) {
					ps.setString(1, "308%");
					ps.setInt(2, companyId);
					ps.setInt(3, companyId);
					ps.setInt(4, companyId);
				}
				ps.setString(3, f150501.getStartDay() + " 00:00:00");
				ps.setString(4, f150501.getEndDay() + " 23:00:00");
				rs = ps.executeQuery();
				Long kingaku = 0l;
				f150501.setDaibikikosu("1");
				while (rs.next()) {
					kingaku = (kingaku + rs.getLong("GOKEISHOHIN") + rs.getLong("GOKEISOURYOU"));
				}
				f150501.setDaibikikingaku(String.valueOf(-kingaku));
				f150501.setDaibikitanka(String.valueOf(-kingaku));
			} else {
				f150501.setDaibikikosu("");
				f150501.setDaibikikingaku("");
				f150501.setDaibikitanka("");
			}

			if (!f150501.isTesuryoChk()) {
				f150501.setTesuryokingaku("");
				f150501.setTesuryotanka("");
			} else {

				if (Utility.isEmptyString(f150501.getTesuryotanka())) {
					f150501.setTesuryotanka("0");
				}
				f150501.setTesuryokingaku(f150501.getTesuryotanka());
			}
			Long total = 0L;
			total = total + Long.valueOf(f150501.getTakyubinkingaku());
			total = total + Long.valueOf(f150501.getMerubinkingaku());
			if (f150501.isDaibikiChk()) {
				total = total + Long.valueOf(f150501.getDaibikikingaku());
			}
			if (f150501.isTesuryoChk()) {
				total = total + Long.valueOf(f150501.getTesuryokingaku());
			}
			for (MeisaiList meisai : f150501.getMeisaiList()) {
				total = total + Long.valueOf(meisai.getKingaku());
			}

			if (!Utility.isEmptyString(f150501.getHimoku1()) && !Utility.isEmptyString(f150501.getTanka1())
					&& !Utility.isEmptyString(f150501.getKosu1())) {
				f150501.setKingaku1(
						String.valueOf(Long.valueOf(f150501.getTanka1()) * Long.valueOf(f150501.getKosu1())));
				total = total + Long.valueOf(f150501.getKingaku1());
			} else {
				f150501.setHimoku1("");
				f150501.setTanka1("");
				f150501.setKingaku1("");
				f150501.setKosu1("");
			}
			if (!Utility.isEmptyString(f150501.getHimoku2()) && !Utility.isEmptyString(f150501.getTanka2())
					&& !Utility.isEmptyString(f150501.getKosu2())) {
				f150501.setKingaku2(
						String.valueOf(Long.valueOf(f150501.getTanka2()) * Long.valueOf(f150501.getKosu2())));
				total = total + Long.valueOf(f150501.getKingaku2());
			} else {
				f150501.setHimoku2("");
				f150501.setTanka2("");
				f150501.setKingaku2("");
				f150501.setKosu2("");
			}
			if (!Utility.isEmptyString(f150501.getHimoku3()) && !Utility.isEmptyString(f150501.getTanka3())
					&& !Utility.isEmptyString(f150501.getKosu3())) {
				f150501.setKingaku3(
						String.valueOf(Long.valueOf(f150501.getTanka3()) * Long.valueOf(f150501.getKosu3())));
				total = total + Long.valueOf(f150501.getKingaku3());
			} else {
				f150501.setHimoku3("");
				f150501.setTanka3("");
				f150501.setKingaku3("");
				f150501.setKosu3("");
			}
			if (!Utility.isEmptyString(f150501.getHimoku4()) && !Utility.isEmptyString(f150501.getTanka4())
					&& !Utility.isEmptyString(f150501.getKosu4())) {
				f150501.setKingaku4(
						String.valueOf(Long.valueOf(f150501.getTanka4()) * Long.valueOf(f150501.getKosu4())));
				total = total + Long.valueOf(f150501.getKingaku4());
			} else {
				f150501.setHimoku4("");
				f150501.setTanka4("");
				f150501.setKingaku4("");
				f150501.setKosu4("");
			}

			f150501.setKokei(String.valueOf(total));
			f150501.setGokei(String.valueOf(total));
			f150501.setSeikyukingaku(String.valueOf(total));
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public F150501 getSeikyusho(String mode) throws Exception {
		F150501 f150501 = new F150501();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  qingqiushu where seikyuid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, mode);
			rs = ps.executeQuery();
			while (rs.next()) {
				f150501.setStartDay(rs.getString("StartDay"));
				f150501.setEndDay(rs.getString("endDay"));
				f150501.setHiseikyusha(rs.getString("Hiseikyusha"));
				f150501.setSeikyukingaku(rs.getString("Seikyukingaku"));
				f150501.setSeikyusha1(rs.getString("Seikyusha1"));
				f150501.setSeikyusha2(rs.getString("Seikyusha2"));
				f150501.setSeikyusha3(rs.getString("Seikyusha3"));
				f150501.setSeikyusha4(rs.getString("Seikyusha4"));
				f150501.setSeikyusha5(rs.getString("Seikyusha5"));
				f150501.setSeikyusha6(rs.getString("Seikyusha6"));
				f150501.setMerubintanka(rs.getString("Merubintanka"));
				f150501.setMerubinkosu(rs.getString("Merubinkosu"));
				f150501.setMerubinkingaku(rs.getString("Merubinkingaku"));
				f150501.setTakyubintanka(rs.getString("Takyubintanka"));
				f150501.setTakyubinkosu(rs.getString("Takyubinkosu"));
				f150501.setTakyubinkingaku(rs.getString("Takyubinkingaku"));
				f150501.setTesuryotanka(rs.getString("Tesuryotanka"));
				f150501.setTesuryokosu(rs.getString("Tesuryokosu"));
				f150501.setTesuryokingaku(rs.getString("Tesuryokingaku"));
				f150501.setDaibikitanka(rs.getString("Daibikitanka"));
				f150501.setDaibikikingaku(rs.getString("Daibikikingaku"));
				f150501.setDaibikikosu(rs.getString("Daibikikosu"));
				f150501.setHimoku1(rs.getString("Himoku1"));
				f150501.setTanka1(rs.getString("Tanka1"));
				f150501.setKosu1(rs.getString("Kosu1"));
				f150501.setKingaku1(rs.getString("Kingaku1"));
				f150501.setHimoku2(rs.getString("Himoku2"));
				f150501.setTanka2(rs.getString("Tanka2"));
				f150501.setKosu2(rs.getString("Kosu2"));
				f150501.setKingaku2(rs.getString("Kingaku2"));
				f150501.setHimoku3(rs.getString("Himoku3"));
				f150501.setTanka3(rs.getString("Tanka3"));
				f150501.setKosu3(rs.getString("Kosu3"));
				f150501.setKingaku3(rs.getString("Kingaku3"));
				f150501.setHimoku4(rs.getString("Himoku4"));
				f150501.setTanka4(rs.getString("Tanka4"));
				f150501.setKosu4(rs.getString("Kosu4"));
				f150501.setKingaku4(rs.getString("Kingaku4"));
				f150501.setKokei(rs.getString("Kokei"));
				f150501.setGokei(rs.getString("Gokei"));
				f150501.setJishu(rs.getString("Jishu"));
				f150501.setCreateDay(rs.getString("CREATE_TIME").substring(0, 10));
				f150501.setHozozumiFlg("1");
				if (!Utility.isEmptyString(rs.getString("Daibikikingaku"))) {
					f150501.setDaibikiChk(true);
				}
				if (!Utility.isEmptyString(rs.getString("Tesuryokingaku"))) {
					f150501.setTesuryoChk(true);
				}
			}
			sql = "SELECT * from  qingqiushudetail where seikyuid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, f150501.getStartDay().replace("-", ""));
			rs = ps.executeQuery();
			List<MeisaiList> meisaiList = new ArrayList<MeisaiList>();
			MeisaiList meisai = null;
			while (rs.next()) {
				meisai = new MeisaiList();
				meisaiList.add(meisai);
				meisai.setJuchubango(rs.getString("juchubango"));
				meisai.setKingaku(rs.getString("kingaku"));
				meisai.setKosu(rs.getString("kosu"));
				meisai.setShohinbango(rs.getString("shohinbango"));
				meisai.setTanka(rs.getString("tanka"));
			}
			f150501.setMeisaiList(meisaiList);

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return f150501;
	}
}
