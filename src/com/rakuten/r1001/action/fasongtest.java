package com.rakuten.r1001.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class fasongtest {

	public static void main(String[] args) throws Exception {

		boolean result = true;

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			List<String[]> OrderS = new ArrayList<String[]>();

			OrderS.add(new String[]{"308759-20190911-00029740","767634421422"});
			OrderS.add(new String[]{"308759-20190912-00697703","767634421433"});
			OrderS.add(new String[]{"308759-20190914-00035744","767634421444"});
			OrderS.add(new String[]{"308759-20190914-00735702","429310542776"});
			OrderS.add(new String[]{"308759-20190914-00717724","767634421455"});
			OrderS.add(new String[]{"308759-20190914-00718724","401295160464"});
			OrderS.add(new String[]{"308759-20190914-00045733","767634421466"});
			OrderS.add(new String[]{"308759-20190914-00663718","767634421470"});
			OrderS.add(new String[]{"308759-20190914-00669701","767634421481"});
			OrderS.add(new String[]{"308759-20190914-00670710","787801"});
			OrderS.add(new String[]{"308759-20190914-00681722","767634421492"});
			OrderS.add(new String[]{"308759-20190914-00667714","429310542846"});
			OrderS.add(new String[]{"308759-20190914-00682722","191274887801"});
			OrderS.add(new String[]{"308759-20190914-00046748","305000"});
			OrderS.add(new String[]{"308759-20190914-00683722","767634421514"});
			OrderS.add(new String[]{"308759-20190915-00722709","767634421525"});
			OrderS.add(new String[]{"308759-20190915-00718708","429310542883"});
			OrderS.add(new String[]{"308759-20190915-00682704","401295160486"});
			OrderS.add(new String[]{"308759-20190915-00702732","767634421536"});
			OrderS.add(new String[]{"308759-20190915-00672710","767634421540"});
			OrderS.add(new String[]{"308759-20190915-00710731","429310542916"});
			OrderS.add(new String[]{"308759-20190915-00044738","429310542920"});
			OrderS.add(new String[]{"308759-20190915-00683704","767634421551"});
			OrderS.add(new String[]{"308759-20190915-00698725","401295160501"});
			OrderS.add(new String[]{"308759-20190915-00673729","429310542942"});
			OrderS.add(new String[]{"308759-20190915-00723709","429310542953"});
			OrderS.add(new String[]{"308759-20190915-00688719","767634421573"});
			OrderS.add(new String[]{"308759-20190915-00641717","429310542975"});
			OrderS.add(new String[]{"308759-20190915-00757730","767634421584"});
			OrderS.add(new String[]{"308759-20190915-00043746","429310542990"});
			OrderS.add(new String[]{"308759-20190916-00685704","429310543001"});
			OrderS.add(new String[]{"308759-20190916-00725709","401295160545"});
			OrderS.add(new String[]{"308759-20190916-00683706","429310543012"});
			OrderS.add(new String[]{"308759-20190916-00687704","767634421595"});
			OrderS.add(new String[]{"308759-20190916-00705732","767634421606"});
			OrderS.add(new String[]{"308759-20190916-00039741","767634421610"});
			OrderS.add(new String[]{"308759-20190916-00701716","429310543056"});
			OrderS.add(new String[]{"308759-20190916-00711731","429310543815"});
			OrderS.add(new String[]{"308759-20190916-00692707","767634421621"});
			OrderS.add(new String[]{"308759-20190916-00030737","429310543071"});
			OrderS.add(new String[]{"308759-20190916-00726709","767634421632"});
			OrderS.add(new String[]{"308759-20190916-00675729","429310543093"});
			OrderS.add(new String[]{"308759-20190916-00693707","401295160571"});
			OrderS.add(new String[]{"308759-20190916-00729712","429310543104"});
			OrderS.add(new String[]{"308759-20190916-00040741","429310543115"});
			OrderS.add(new String[]{"308759-20190916-00702716","191274887812"});
			OrderS.add(new String[]{"308759-20190916-00676729","191274887823"});
			OrderS.add(new String[]{"308759-20190916-00737702","767634421643"});
			OrderS.add(new String[]{"308759-20190916-00697721","401295160615"});
			OrderS.add(new String[]{"308759-20190916-00684706","401295160626"});
			OrderS.add(new String[]{"308759-20190916-00699725","429310543130"});
			OrderS.add(new String[]{"308759-20190916-00738702","767634421654"});
			OrderS.add(new String[]{"308759-20190917-00712731","401295160630"});
			OrderS.add(new String[]{"308759-20190917-00673710","767634421665"});
			OrderS.add(new String[]{"308759-20190917-00032737","767634421676"});
			OrderS.add(new String[]{"308759-20190917-00041743","767634421680"});
			OrderS.add(new String[]{"308759-20190917-00673723","429310543185"});
			OrderS.add(new String[]{"308759-20190917-00048735","767634421691"});
			OrderS.add(new String[]{"308759-20190917-00029734","767634421702"});
			OrderS.add(new String[]{"308759-20190917-00038744","767634421713"});
			OrderS.add(new String[]{"308759-20190917-00686705","767634421735"});
			OrderS.add(new String[]{"308759-20190917-00039744","191274887834"});
			OrderS.add(new String[]{"308759-20190917-00739702","767634421746"});
			OrderS.add(new String[]{"308759-20190917-00049735","767634421750"});
			OrderS.add(new String[]{"308759-20190917-00670701","767634421761"});
			OrderS.add(new String[]{"308759-20190917-00703716","401295160663"});
			OrderS.add(new String[]{"308759-20190917-00046746","429310543270"});
			OrderS.add(new String[]{"308759-20190917-00668718","429310543281"});
			OrderS.add(new String[]{"308759-20190917-00669718","429310543292"});
			OrderS.add(new String[]{"308759-20190917-00759730","429310543303"});
			OrderS.add(new String[]{"308759-20190917-00670718","429310543325"});
			OrderS.add(new String[]{"308759-20190917-00051735","767634421783"});
			OrderS.add(new String[]{"308759-20190917-00696707","767634421794"});
			OrderS.add(new String[]{"308759-20190918-00041744","429310543351"});
			OrderS.add(new String[]{"308759-20190918-00041742","429310543362"});
			OrderS.add(new String[]{"308759-20190918-00031747","401295160674"});
			OrderS.add(new String[]{"308759-20190918-00674710","767631533233"});
			OrderS.add(new String[]{"308759-20190918-00760730","191274887845"});
			OrderS.add(new String[]{"308759-20190918-00675723","767631533244"});
			OrderS.add(new String[]{"308759-20190918-00723724","401295160696"});
			OrderS.add(new String[]{"308759-20190918-00643717","767631533255"});
			OrderS.add(new String[]{"308759-20190919-00709732","767631533266"});
			OrderS.add(new String[]{"308759-20190920-00034747","429310543410"});
			OrderS.add(new String[]{"308759-20190920-00710732","429310543421"});
			OrderS.add(new String[]{"308759-20190920-00044744","429310543443"});
			OrderS.add(new String[]{"308759-20190920-00734712","767631533303"});
			OrderS.add(new String[]{"308759-20190920-00683710","429310543465"});
			OrderS.add(new String[]{"308759-20190921-00700707","429310543476"});
			OrderS.add(new String[]{"308759-20190921-00646717","429310543480"});
			OrderS.add(new String[]{"308759-20190921-00054748","767631533336"});
			OrderS.add(new String[]{"308759-20190921-00676701","429310543502"});
			OrderS.add(new String[]{"308759-20190921-00746711","767631533340"});
			OrderS.add(new String[]{"308759-20190921-00647717","429310543524"});
			OrderS.add(new String[]{"308759-20190921-00038740","767631533351"});
			OrderS.add(new String[]{"308759-20190921-00033734","429310543546"});
			OrderS.add(new String[]{"308759-20190922-00713732","767631494545"});
			OrderS.add(new String[]{"308759-20190922-00736712","767631494560"});
			OrderS.add(new String[]{"308759-20190922-00747711","429310543583"});
			OrderS.add(new String[]{"308759-20190922-00709716","429310543594"});
			OrderS.add(new String[]{"308759-20190922-00057738","429310543605"});
			OrderS.add(new String[]{"308759-20190922-00049743","429310543616"});
			OrderS.add(new String[]{"308759-20190922-00734709","429310543620"});
			OrderS.add(new String[]{"308759-20190922-00703713","767631574990"});
			OrderS.add(new String[]{"308759-20190923-00678701","429310543631"});
			OrderS.add(new String[]{"308759-20190923-00706703","767631494604"});
			OrderS.add(new String[]{"308759-20190923-00686710","767631494615"});
			OrderS.add(new String[]{"308759-20190923-00681714","429310543664"});
			OrderS.add(new String[]{"308759-20190923-00651717","429310543723"});
			OrderS.add(new String[]{"308759-20190923-00653717","767634421816"});
			OrderS.add(new String[]{"308759-20190923-00746720","429310543734"});
			OrderS.add(new String[]{"308759-20190923-00763730","429310543745"});
			OrderS.add(new String[]{"308759-20190923-00710728","767634421842"});
			OrderS.add(new String[]{"308759-20190923-00730708","767634421853"});
			OrderS.add(new String[]{"308759-20190923-00711728","429310543756"});
			OrderS.add(new String[]{"308759-20190923-00056748","429310543760"});
			OrderS.add(new String[]{"308759-20190923-00059735","429310543771"});
			OrderS.add(new String[]{"308759-20190924-00062733","767634421890"});

			List<String> errorList = new ArrayList<String>();
			for (String[] order : OrderS) {

				String haisohoho = "";
				String orderNo = order[0];
				String toiawasebango = order[1];
				List<String[]> hasoList = new ArrayList<String[]>();
				String chumonsts1 = "";
				sql = "select chumonsts1 from common_order_tbl where chumonbango = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, orderNo);
				rs = ps.executeQuery();
				while (rs.next()) {
					chumonsts1 = rs.getString("chumonsts1");
				}
				if ("3".equals(chumonsts1)) {
					System.out.println(orderNo + "已发送，跳过");
					continue;
				} else {
					System.out.println(orderNo);
				}
				sql = "SELECT HAISOUHOHO FROM common_order_tbl WHERE chumonbango like ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, orderNo);
				rs = ps.executeQuery();
				while (rs.next()) {
					haisohoho = rs.getString("HAISOUHOHO");
				}
				if ("メール便".equals(haisohoho)) {
					haisohoho = "2";
				} else if (haisohoho.contains("宅")) {
					haisohoho = "1";
				}

				sql = "SELECT * FROM common_order_detail_tbl WHERE JUCHUBANGO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, orderNo);
				rs = ps.executeQuery();
				while (rs.next()) {
					hasoList.add(new String[] { rs.getString("SHOUHINBANGO"), rs.getString("KOSU") });
				}

				boolean flg = true;
				for (int i = 0; i < hasoList.size(); i++) {
					String shoribango = Utility.getShoribango();
					String[] shohin = hasoList.get(i);
					String shouhinbango = shohin[0];
					String kosu = shohin[1];
					sql = "SELECT STOCK_JP FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, Utility.getCommodityId(shouhinbango));
					ps.setString(2, Utility.getDetailN0(shouhinbango));

					rs = ps.executeQuery();
					String stockjp = "0";
					while (rs.next()) {
						stockjp = rs.getString("STOCK_JP");

					}
					if ((Integer.valueOf(stockjp) - Integer.valueOf(kosu)) < 0) {
						errorList.add(orderNo);

						flg = false;
						break;
					}
					if (!flg) {
						continue;
					}
					sql = "UPDATE TBL00012 SET STOCK_JP = ? WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);

					ps.setString(1, String.valueOf(Integer.valueOf(stockjp) - Integer.valueOf(kosu)));

					ps.setString(2, Utility.getCommodityId(shouhinbango));
					ps.setString(3, Utility.getDetailN0(shouhinbango));

					ps.execute();

					int j = 1;
					sql = "INSERT INTO hassou_tbl (SHORIBANGO,JUCHUBANGO,SHOUHINBANGO,HASSOUKOSU,TOIAWASEBANGO,SHUBETSU,UNSOKAISHA,HAISOHOHO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(j++, shoribango);
					ps.setString(j++, orderNo);
					ps.setString(j++, shouhinbango);
					ps.setString(j++, kosu);
					ps.setString(j++, toiawasebango);
					ps.setString(j++, "0");
					ps.setString(j++, Utility.getUnsokaisha(orderNo));
					ps.setString(j++, haisohoho);
					ps.setString(j++, Utility.getDateTime());
					ps.setString(j++, Utility.getUser());
					ps.setString(j++, Utility.getDateTime());
					ps.setString(j++, Utility.getUser());
					ps.execute();
				}

				sql = "UPDATE common_order_tbl SET CHUMONSTS1 = ? WHERE CHUMONBANGO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, "3");
				ps.setString(2, orderNo);
				ps.executeUpdate();

				sql = "insert into tbl00024 values(?,?,?,?,?,?)";

				ps = conn.prepareStatement(sql);
				ps.setString(1, orderNo);
				ps.setString(2, "0");
				ps.setString(3, Utility.getDateTime());
				ps.setString(4, Utility.getUser());
				ps.setString(5, Utility.getDateTime());
				ps.setString(6, Utility.getUser());

				ps.execute();

				if (result) {
					conn.commit();

				}
				for (String msg : errorList) {
					System.out.println(msg + "错误");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

}
