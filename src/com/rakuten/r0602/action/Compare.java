package com.rakuten.r0602.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class Compare {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		List<String[]> csvList_3eshop = new ArrayList<String[]>();
		List<String[]> csvList_3eshop_cat = new ArrayList<String[]>();
		List<String[]> csvList_3eshop_sel = new ArrayList<String[]>();
		CsvTool csvTool = new CsvTool();

		csvList_3eshop = Utility.readCsvFile(new File("C:\\dl-item202005171134-1.csv"), false);
//		csvList_3eshop_sel = Utility.readCsvFile(new File("d:\\qfaf.csv"),
//				false);
		// csvList_3eshop.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\ddk\\item.csv"), true));
		// csvList_3eshop.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\lf\\item.csv"), true));
		// csvList_3eshop.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\lyq\\item.csv"), true));
		// csvList_3eshop.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\nzk\\item.csv"), true));
		// csvList_3eshop.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\sjpj\\item.csv"), true));
		// csvList_3eshop.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\xbx\\item.csv"), true));
		// csvList_3eshop.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\ydz\\item.csv"), true));

		// csvList_3eshop_cat = Utility.readCsvFile(
		// new File("F:\\data\\item-cat20141017190823.csv"), false);
		// csvList_3eshop_cat.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\ddk\\cat.csv"), true));
		// csvList_3eshop_cat.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\lf\\cat.csv"), true));
		// csvList_3eshop_cat.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\lyq\\cat.csv"), true));
		// csvList_3eshop_cat.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\nzk\\cat.csv"), true));
		// csvList_3eshop_cat.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\sjpj\\cat.csv"), true));
		// csvList_3eshop_cat.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\xbx\\cat.csv"), true));
		// csvList_3eshop_cat.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\ydz\\cat.csv"), true));

		// csvList_3eshop_sel = Utility.readCsvFile(
		// new File("F:\\data\\select20141017190820.csv"), false);
		// csvList_3eshop_sel.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\ddk\\sel.csv"), true));
		// csvList_3eshop_sel.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\lf\\sel.csv"), true));
		// csvList_3eshop_sel.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\lyq\\sel.csv"), true));
		// csvList_3eshop_sel.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\nzk\\sel.csv"), true));
		// csvList_3eshop_sel.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\sjpj\\sel.csv"), true));
		// csvList_3eshop_sel.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\xbx\\sel.csv"), true));
		// csvList_3eshop_sel.addAll(Utility.readCsvFile(new File(
		// "D:\\jinyan\\ydz\\sel.csv"), true));
		//
		// csvTool.convert3ecatToOthercat(csvList_3eshop_cat, "citycat");
		// csvTool.convert3eToOthershop(csvList_3eshop, "citycat");
		// csvList_3eshop_sel = csvTool.removeIyahonjyaku(csvList_3eshop_sel);
		// csvList_3eshop_sel = csvTool.removeSale(csvList_3eshop_sel);

		// Utility.writeCsvFile(csvList_3eshop, "d://item.csv");
		// Utility.writeCsvFileTemp(csvList_3eshop_sel, "d://sel.csv");
		// Utility.writeCsvFileTemp(csvList_3eshop_cat, "d://cat.csv");

		// List<String[]> picList = csvTool.getPicUrl_123mart(csvList_3eshop);
		// csvTool.downloadPic(picList);
		List<String[]>  picList = csvTool.getPicUrl_123mart(csvList_3eshop);
		csvTool.downloadPic(picList);
//		csvTool.downloadPicLocal(picList);
		// List<String> dataList = new ArrayList<String>();
		// for (String[] dataArr : csvList_3eshop_sel) {
		// if (dataArr[1].startsWith("sale")) {
		// dataArr[1] = dataArr[1].replace("sale", "");
		// }
		// dataList.add(dataArr[1] + dataArr[6] + dataArr[8]);
		// System.out.println(dataArr[1] + dataArr[6] + dataArr[8]);
		// }
		// bijiao(dataList);
		System.out.println("done");

	}

	public static void bijiao(List<String> dataList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String[]> databaseArr = new ArrayList<String[]>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "select * from tbl00012";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				databaseArr.add(new String[] {
						rs.getString("commodity_id")
								+ rs.getString("detail_no".replace("-0-0", "")),
						rs.getString("stock_jp") });

			}

			for (String[] base : databaseArr) {
				boolean ariFlg = false;
				for (String threee : dataList) {
					if (threee.equals(base[0])) {
						ariFlg = true;
						break;
					}
				}
				if (!ariFlg) {
					System.out.println(base[0] + "      " + base[1]);
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
