package com.rakuten.r0602.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class Copy_2_of_Compare {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		List<String[]> csvList_3eshop = new ArrayList<String[]>();
		List<String[]> csvList_3eshop_cat = new ArrayList<String[]>();
		List<String[]> csvList_3eshop_sel = new ArrayList<String[]>();
		CsvTool csvTool = new CsvTool();

		csvList_3eshop = Utility.readCsvFile(new File("D:\\11.csv"), false);
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

		csvList_3eshop_cat = Utility.readCsvFile(new File("D:\\ccghe.csv"),
				false);
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

		csvList_3eshop_sel = Utility.readCsvFile(new File("D:\\22.csv"), false);
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

		String[] shouhinbangoArr = new String[] { "cpwt001-05-M",
				"cpwt001-05-S", "cpwt001-05-XL", "djzydk001-05",
				"djzydk003-05-M", "djzytx003-04", "gzxlyq038-10",
				"gzxlyq059-07-M", "gzxlyq081-05-S", "gzxlyq081-10-M",
				"gzxlyq081-10-S", "nvddk009-A7", "nvddk012-01", "nvsw003-05",
				"nvsw005-01", "nvsw006-05", "nzcs008-07-L", "nzctx001-B-05",
				"nzctx001-C-03", "nzctx001-C-11", "nzctx002-B18",
				"nzctx002-B19", "nzctx002-B6", "nzctx002-B3", "nzctx002-B7",
				"nzctx003-02", "nzctx003-06", "nzctx003-16", "nzctx004-08",
				"nzctx004-16", "nzctx007-01", "nzctx007-05", "nzctx007-06",
				"nzctx012-01-L", "nzctx014-05", "nzddk022-20", "nzddk026-04",
				"nzddk026-19", "nzddk031-14-M", "nzddk031-16-L",
				"nzddk031-16-M", "nzddk032-02-M", "nzddk032-06-M",
				"nzddk032-21-M", "nzddk032-18-M", "nzddk033-F-08",
				"nzddk033-F-21", "nzddk034-08-M", "nzddk037-161",
				"nzddk039-162", "nzddk041-162", "nzddk044-05-M", "nzk002-D1",
				"nzk030-16", "nzlf003-13-F", "nzlf005-05-F", "nzlf008-11-XL",
				"nzlf009-05-XL", "nzlf009-11-F", "nzlf010-XL", "nzlf012-04-M",
				"nzlf015-04-L", "nzlf015-11-L", "nzlf015-12-F",
				"nzlf017-05-XL", "nzlf018-05-F", "nzlf022-13-M", "nzlf026-06",
				"nzlyq028-01", "nzlyq035-10-M", "nzlyq037-05-M",
				"nzlyq037-10-XL", "nzlyq038-05-L", "nzlyq038-05-M",
				"nzlyq071-M", "nzlyq071-S", "nzlyq071-L", "nzlyq075-S",
				"nzlyq082-01-S", "nzlyq082-01-M", "nzlyq082-01-L",
				"nzlyq084-05-XL", "nzlyq094-07-M", "nzlyq104-10-L",
				"nzlyq109-10-L", "nzlyq110-S", "nzlyq12-10-L", "nzlyq122-05-L",
				"nzlyq125-07-M", "nzlyq125-07-S", "nzlyq15-07-L",
				"nzlyq15-07-XL", "nzlyq154-XL", "nzlyq193-05-L",
				"nzlyq193-05-S", "nzlyq253-S", "nzpy007-XXXL", "nzwt004-S",
				"nzwt006-13-s", "nzwt005-07-S", "nzwt010-05-L", "nzwt012-05-L",
				"nzwt015-10-S", "nzwt020-05-S", "nzwt022-18-L", "nzwt023-01-M",
				"nzwt028-05-S", "nzwt028-13-S", "nzwt029-02-M",
				"nzwt039-10-XL", "nzwt047-01-L", "nzwt047-01-M",
				"nzwt047-13-L", "nzwt047-13-S", "nzwt050-13", "nzydz05-05",
				"nzydz05-10", "nzydz07-01", "nzydz20-01", "nzydz20-10",
				"se0003-A1-M", "se0007-05-L", "se0008-05-M", "se0008-05-XL",
				"se0008-06-XL", "se0008-20-M", "se0008-20-XL", "se0008-21-L",
				"se0008-21-M", "se0015-05", "se0014-13-S", "se0010-16-M",
				"se0030-17-XXL", "se0030-20-XXL", "se0045-05-L",
				"se0045-11-XXL", "se0045-11-XXXL", "se0045-18-XXL",
				"sjpj042-5-05", "sjpj058-5-01", "sjpj058-5-02", "sjpj058-5-05",
				"sjpj101-02-4/4S", "sjpj102-04-4/4S", "sjpj123-4-2",
				"sjpj126-5-1", "sjpj131-5-3", "sjpj160-22", "sjpj160-5",
				"sjpj164-2", "sjpj164-8", "sjpj165-4", "sjpj204-5",
				"sjpj206-5", "tzlyq06-01-13", "tzlyq06-01-5", "tzlyq06-01-7",
				"tzlyq06-01-9", "tzlyq06-06-5", "tzlyq06-06-7",
				"tzlyq07-04-120", "tzlyq07-04-130", "tzlyq07-07-110",
				"tzlyq07-07-120", "tzlyq06-10-9", "tzlyq06-10-5", "xbx007-01",
				"xbx018-01", "xfpy002-M", "xfpy003-S", "xfpy006-L",
				"xfpy013-XL", "xfpy017-M", "xfpy014-05-XL", "xfpy018-S",
				"xphmz003-12", "xzylyq002-S", "xzylyq003-S", "xzytx001-01-L",
				"xzytx001-01-S", "xzywt006-M", "zfqny200-1", "zfqny208-1",
				"zfqny208-2", "zfqydz108-1", "zfqydz108-2", "zfqydz150-1",
				"zfqydz755-1", "zfqydz755-2", "cpwt001-18-S", "gzxlyq059-07-L",
				"lzsw04-05", "lzsw04-A1", "nvsw002-05", "nvsw003-01",
				"nvsw006-01", "nvsw014-05", "nzctx001-A-01", "nzctx001-A-03",
				"nzctx001-A-05", "nzctx001-B-11", "nzctx002-B12",
				"nzctx002-B16", "nzctx002-B17", "nzctx004-01", "nzctx004-06",
				"nzctx004-07", "nzctx014-18", "nzddk022-162", "nzddk022-19",
				"nzddk026-05", "nzddk026-06", "nzddk029-05", "nzddk029-161",
				"nzddk032-12-L", "nzddk032-12-M", "nzddk048-04", "nzddk048-13",
				"nzk021-05", "nzlf009-06-F", "nzlyq037-10-M", "nzlyq109-10-XL",
				"nzlyq121-L", "nzlyq20-08-S", "nzmz06-01", "nzwt005-06-S",
				"nzydz20-05", "nzzfz004-A1", "sjpj102-04-5", "sjpj173-10",
				"spjl11-01", "xbx018-05", "xbx018-19", "cpwt001-18-L",
				"cpwt001-18-M", "gzxlyq059-07-S", "gzxlyq081-04-S",
				"nvddk009-B7", "nzctx001-C-10", "nzctx014-01", "nzlyq037-05-L",
				"nzlyq109-05-XL", "sjpj042-5-01", "sjpj123-5-1", "sjpj173-1",
				"sjpj173-07", "zfqydz132-1", "zfqzq977-1", "cpwt001-18-XL",
				"nzctx004-05", "nzsp004-01", "nzsp004-05", "nzsp004-10",
				"nvsw005-02" };
		String[] kosuArr = new String[] { "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
				"1", "1", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2",
				"2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2",
				"2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "2",
				"2", "2", "2", "2", "2", "2", "2", "2", "2", "2", "3", "3",
				"3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3", "3",
				"3", "4", "5", "6", "7", "7", "33" };
		List<String[]> itemShoriList = new ArrayList<String[]>();
		// List<String[]> selShoriList = new ArrayList<String[]>();
		for (String[] item : csvList_3eshop) {
			for (String shouhinbango : shouhinbangoArr) {
				if (Utility.getCommodityId(shouhinbango).equals(item[1])) {
					boolean ariFlg = false;
					for (String[] tempArr : itemShoriList) {
						if (tempArr[1].equals(Utility
								.getCommodityId(shouhinbango))) {
							ariFlg = true;
						}
					}
					if (!ariFlg) {
						itemShoriList.add(item);
					}
				}
			}
		}

		for (String[] sel : csvList_3eshop_sel) {
			sel[10] = "0";
			for (int i = 0; i < shouhinbangoArr.length; i++) {
				String shouhinbango = shouhinbangoArr[i];
				if (shouhinbango.equals(sel[1]
						+ (Utility.isEmptyString(sel[6]) ? "" : sel[6])
						+ (Utility.isEmptyString(sel[8]) ? "" : sel[8]))) {
					sel[10] = kosuArr[i];
				}
			}
		}

		Utility.writeCsvFile(itemShoriList, "d://item.csv");
		Utility.writeCsvFileTemp(csvList_3eshop_sel, "d://sel.csv");
		Utility.writeCsvFileTemp(csvList_3eshop_cat, "d://cat.csv");

		// List<String[]> picList = csvTool.getPicUrl_123mart(csvList_3eshop);
		// csvTool.downloadPic(picList);
		// List<String> dataList = new ArrayList<String>();
		// for (String[] dataArr : csvList_3eshop_sel) {
		// if (dataArr[1].startsWith("sale")) {
		// dataArr[1] = dataArr[1].replace("sale", "");
		// }
		// dataList.add(dataArr[1] + dataArr[6] + dataArr[8]);
		// // System.out.println(dataArr[1] + dataArr[6] + dataArr[8]);
		// }
		// bijiao(dataList);
		// System.out.println("done");

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
								+ rs.getString("detail_no").replace("-0-0", ""),
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
