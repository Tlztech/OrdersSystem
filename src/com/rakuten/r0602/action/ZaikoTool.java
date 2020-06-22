package com.rakuten.r0602.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class ZaikoTool {

	public static List<String[]> zaikoKoshin(List<String[]> selCsvFile,
			boolean realFlg, boolean recovery) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String[]> resultSelCsvFile = new ArrayList<String[]>();
		try {
			conn = JdbcConnection.getConnection();

			for (String[] dataArr : selCsvFile) {
				// 非库存行
				if (!"i".equals(dataArr[2])) {
					if ("項目選択肢用コントロールカラム".equals(dataArr[0])) {
						resultSelCsvFile.add(dataArr);
					}
					continue;
				}

				String shohinbango = dataArr[1];
				if (shohinbango.startsWith("sale")) {
					shohinbango = shohinbango.replace("sale", "");
				}
				System.out.println(shohinbango);
				String detail_no1 = "";
				String detail_no2 = "";

				if (!Utility.isEmptyString(dataArr[6])) {
					detail_no1 = dataArr[6];
				}
				if (!Utility.isEmptyString(dataArr[8])) {
					detail_no2 = dataArr[8];
				}

				String commodity_id = shohinbango + detail_no1 + detail_no2;
				// 将此货号去库存列表搜索
				sql = "SELECT STOCK_JP,DEL_FLG FROM TBL00012 WHERE CONCAT(COMMODITY_ID,DETAIL_NO) = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, commodity_id);
				rs = ps.executeQuery();

				String delFlg = "0";
				String stock_jp = "0";
				while (rs.next()) {
					delFlg = rs.getString("DEL_FLG");
					stock_jp = rs.getString("STOCK_JP");
				}

				if ("1".equals(delFlg) || realFlg) {
					// 进不到货
					dataArr[10] = stock_jp;
				} else if (recovery) {
					dataArr[10] = "999";
				}

				resultSelCsvFile.add(dataArr);
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();

		}

		return resultSelCsvFile;
	}

	public static List<String> getNewShohin(List<String[]> itemCsvFileRes,
			List<String[]> itemCsvFileTar) {
		List<String> newShohinbangoList = new ArrayList<String>();

		for (String[] dataRes : itemCsvFileRes) {
			boolean ariFlg = false;
			String bangoRes = dataRes[1];
			if (bangoRes.startsWith("sale")) {
				bangoRes = bangoRes.replace("sale", "");
			}
			for (String[] dataTar : itemCsvFileTar) {
				String bangoTar = dataTar[1];
				if (bangoTar.startsWith("sale")) {
					bangoTar = bangoTar.replace("sale", "");
				}
				
				if (bangoRes.equals(bangoTar)) {
					ariFlg = true;
					break;
				}
			}
			if (!ariFlg) {
				newShohinbangoList.add(bangoRes);
			}
		}

		return newShohinbangoList;
	}
}
