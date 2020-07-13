package shohinmodel;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

import batch.bean.StockBean;

public class YahooStock {

	public static void main(String[] args) throws Exception {
		List<StockBean> stockBeanList = getStockFromDB();
		List<YahooStockInputBean> yahooStockInputBeanList = new ArrayList<YahooStockInputBean>();
		List<String[]> outCsvDataList = new ArrayList<String[]>();
		List<String[]> csvDataList = Utility.readCsvFile(new File("D:\\data201607071451.csv"), false);
		String[] title = new String[] { "path", "name", "code", "sub-code", "price", "sale-price", "options",
				"additional1", "product-category", "spec1", "spec2", "spec3", "spec4", "spec5", "sp-additional" };
		outCsvDataList.add(title);
		String[] getTitle = csvDataList.get(0);
		int pathIndex = -1;
		int nameIndex = -1;
		int codeIndex = -1;
		int priceIndex = -1;
		int salepriceIndex = -1;
		int optionsIndex = -1;
		int additional2Index = -1;
		int spadditionalIndex = -1;
		int subcodeIndex = -1;
		int spec1Index = -1;
		int spec2Index = -1;
		int spec3Index = -1;
		int spec4Index = -1;
		int spec5Index = -1;
		int productcategoryIndex = -1;

		for (int i = 0; i < getTitle.length; i++) {
			String titlename = getTitle[i];
			if ("path".equals(titlename)) {
				pathIndex = i;
			} else if ("name".equals(titlename)) {
				nameIndex = i;
			} else if ("code".equals(titlename)) {
				codeIndex = i;
			} else if ("price".equals(titlename)) {
				priceIndex = i;
			} else if ("options".equals(titlename)) {
				optionsIndex = i;
			} else if ("sale-price".equals(titlename)) {
				salepriceIndex = i;
			} else if ("additional2".equals(titlename)) {
				additional2Index = i;
			} else if ("sp-additional".equals(titlename)) {
				spadditionalIndex = i;
			} else if ("spec1".equals(titlename)) {
				spec1Index = i;
			} else if ("spec2".equals(titlename)) {
				spec2Index = i;
			} else if ("spec3".equals(titlename)) {
				spec3Index = i;
			} else if ("spec4".equals(titlename)) {
				spec4Index = i;
			} else if ("spec5".equals(titlename)) {
				spec5Index = i;
			} else if ("product-category".equals(titlename)) {
				productcategoryIndex = i;
			} else if ("sub-code".equals(titlename)) {
				subcodeIndex = i;
			}
		}
		for (int i = 1; i < csvDataList.size(); i++) {
			String[] csvData = csvDataList.get(i);
			YahooStockInputBean yahooStockInputBean = new YahooStockInputBean();
			yahooStockInputBeanList.add(yahooStockInputBean);
			List<String> yokonameList = new ArrayList<String>();
			List<String> shitaganameList = new ArrayList<String>();
			List<List<String>> optionsList = new ArrayList<List<String>>();
			String[] optionsmotoList = csvData[optionsIndex].split("\n");

			for (String optionsmoto : optionsmotoList) {
				String[] option = optionsmoto.split(" ");
				if (Utility.isEmptyString(option[0])) {
					continue;
				}
				List<String> shoriOption = new ArrayList<String>();
				for (int j = 1; j < option.length; j++) {
					shoriOption.add(option[j]);
				}
				optionsList.add(shoriOption);

			}
			for (String yokoname : optionsList.get(0)) {
				yokonameList.add(yokoname);
			}
			boolean shitagaariFlg = false;
			if (optionsList.size() > 1) {
				String shitaganameTest = optionsList.get(1).get(0);
				for (StockBean stockbean : stockBeanList) {
					if (csvData[codeIndex].equals(stockbean.getCommodity_id())) {
						if (stockbean.getDetail_name_shitaga().equals(shitaganameTest.replace("　", " "))) {
							shitagaariFlg = true;
							break;
						}
					}
				}
			}

			yahooStockInputBean.setShohinbango(csvData[codeIndex]);
			yahooStockInputBean.setYokonameList(yokonameList);
			if (shitagaariFlg) {
				for (String shitaganame : optionsList.get(1)) {
					shitaganameList.add(shitaganame);
				}
			}
			yahooStockInputBean.setShitagaNamList(shitaganameList);

		}
		List<String[]> htmlList = getStock(yahooStockInputBeanList, stockBeanList);
		for (String[] csvdata : csvDataList) {
			for (String[] html : htmlList) {
				if (html[0].equals(csvdata[codeIndex])) {
					String path = csvdata[pathIndex];
					String name = csvdata[nameIndex];
					String code = csvdata[codeIndex];
					String price = csvdata[priceIndex];
					String saleprice = csvdata[salepriceIndex];
					String options = csvdata[optionsIndex];
					if (!options.contains("納期について 6〜8営業日以内に発送となります")) {
						options += "\r\n\r\n納期について 6〜8営業日以内に発送となります";
					}
					String additional1 = html[1];
					String spadditional = csvdata[spadditionalIndex];
					if (!spadditional.contains("<!-- end -->")) {
						spadditional = html[2] + spadditional;
					} else {
						spadditional = spadditional.substring(spadditional.indexOf("<!-- end -->") + 12,
								spadditional.length());
						spadditional = html[2] + spadditional;
					}
					String spec1 = csvdata[spec1Index];
					String spec2 = csvdata[spec2Index];
					String spec3 = csvdata[spec3Index];
					String spec4 = csvdata[spec4Index];
					String spec5 = csvdata[spec5Index];
					String subcode = csvdata[subcodeIndex];
					String productcategory = csvdata[productcategoryIndex];
					outCsvDataList.add(new String[] { path, name, code, subcode, price, saleprice, options, additional1,
							productcategory, spec1, spec2, spec3, spec4, spec5, spadditional });
				}
			}

		}
		Utility.writeCsvFile(outCsvDataList, "D:\\yahooNouki.csv");
	}

	private static List<YahooStockBean> convertToYahooStockBean(List<StockBean> StockBeanList) {
		List<YahooStockBean> yahooStockBeanList = new ArrayList<YahooStockBean>();
		String shohinbango = "";
		YahooStockBean yahooStockBean = null;
		for (StockBean stockbean : StockBeanList) {
			if (Utility.isEmptyString(shohinbango) || !shohinbango.equals(stockbean.getCommodity_id())) {
				yahooStockBean = new YahooStockBean();
				yahooStockBeanList.add(yahooStockBean);
				yahooStockBean.setShohinbango(stockbean.getCommodity_id());
			}
			yahooStockBean.getStockBeanList().add(stockbean);
			shohinbango = stockbean.getCommodity_id();
		}
		return yahooStockBeanList;

	}

	public static String getStockHtmlPC(YahooStockInputBean yahooStockInputBean, YahooStockBean yahooStockBean) {
		String html = "";
		List<StockBean> stockBeanList = yahooStockBean.getStockBeanList();
		List<String> yokoList = yahooStockInputBean.getYokonameList();
		List<String> shitagaList = yahooStockInputBean.getShitagaNamList();
		if (!Utility.isEmptyList(shitagaList)) {
			html = "<table width=\"550px\" align=\"center\" style=\"font-size:12px\" cellpadding=\"0\" cellspacing=\"0\">";
			html += " <tr height=\"35px\">";
			html += "  <td width=\"50px\" style=\"border:1px solid #000;\">&nbsp;</td>";
			for (String yokoname : yokoList) {
				html += "<td width=\"93px\" align=\"center\" style=\"border-top:1px solid #000;border-bottom:1px solid #000;border-right:1px solid #000;background-color:#B2B2B2\">";
				html += yokoname;
				html += "</td>";
			}
			html += "</tr>";

			for (String shitaganame : shitagaList) {
				html += "<tr height=\"35px\">";
				html += "<td align=\"center\" style=\"border:1px solid #000;border-top:0px;background-color:#B2B2B2\">";
				html += shitaganame;
				html += "</td>";
				for (String yokoname : yokoList) {
					for (StockBean stockBean : stockBeanList) {
						if (stockBean.getDetail_name_yoko().equals(yokoname.replace("　", " "))
								&& stockBean.getDetail_name_shitaga().equals(shitaganame.replace("　", " "))) {
							html += "<td align=\"center\" style=\"border-bottom:1px solid #000;border-right:1px solid #000;\">";
							if (stockBean.isNyukafukaFlg()) {
								html += "×";
							} else if (stockBean.getStock_jp_kano() > 0) {
								html += "<font color=\"green\">即納</font>";
							} else if (stockBean.getStock_unsochu_kano() > 0 || stockBean.getStock_sh_kano() > 0) {
								html += "<font color=\"yellow\">4-5営業日</font>";
							} else {
								html += "<font color=\"red\">7-10営業日</font>";
							}
							html += "</td>";
						}
					}
				}
				html += "</tr>";
			}
		} else {
			html = "<!-- end --><table width=\"550px\" align=\"center\" style=\"font-size:12px\" cellpadding=\"0\" cellspacing=\"0\">";
			html += " <tr height=\"35px\">";
			for (String yokoname : yokoList) {
				html += "<td width=\"93px\" align=\"center\" style=\"border:1px solid #000;background-color:#B2B2B2\">";
				html += yokoname;
				html += "</td>";
			}
			html += "</tr>";
			html += "<tr height=\"35px\">";
			for (StockBean stockBean : stockBeanList) {
				for (String yokoname : yokoList) {
					if (stockBean.getDetail_name_yoko().equals(yokoname.replace("　", " "))) {

						html += "<td align=\"center\" style=\"border:1px solid #000;\">";
						if (stockBean.isNyukafukaFlg()) {
							html += "×";
						} else if (stockBean.getStock_jp_kano() > 0) {
							html += "<font color=\"green\">即納</font>";
						} else if (stockBean.getStock_unsochu_kano() > 0 || stockBean.getStock_sh_kano() > 0) {
							html += "<font color=\"yellow\">4-5営業日</font>";
						} else {
							html += "<font color=\"red\">7-10営業日</font>";
						}
						html += "</td>";
					}
				}

			}
			html += "</tr>";
		}

		html += " </table><!-- end -->";
		
		return html;

	}

	public static String getStockHtmlSP(YahooStockInputBean yahooStockInputBean, YahooStockBean yahooStockBean) {
		String html = "";
		List<StockBean> stockBeanList = yahooStockBean.getStockBeanList();
		List<String> yokoList = yahooStockInputBean.getYokonameList();
		List<String> shitagaList = yahooStockInputBean.getShitagaNamList();
		if (!Utility.isEmptyList(shitagaList)) {
			html = "<table style=\"border:1px solid\" width=\"100%\" cellspacing = \"0\">";
			html += " <tr>";
			html += "  <td>&nbsp;</td>";
			for (String yokoname : yokoList) {
				html += "<td style=\"border:1px solid\">";
				html += yokoname;
				html += "</td>";
			}
			html += "</tr>";

			for (String shitaganame : shitagaList) {
				html += "<tr>";
				html += "<td style=\"border:1px solid\">";
				html += shitaganame;
				html += "</td>";
				for (String yokoname : yokoList) {
					for (StockBean stockBean : stockBeanList) {
						if (stockBean.getDetail_name_yoko().equals(yokoname.replace("　", " "))
								&& stockBean.getDetail_name_shitaga().equals(shitaganame.replace("　", " "))) {
							html += "<td style=\"border:1px solid\">";
							if (stockBean.isNyukafukaFlg()) {
								html += "×";
							} else if (stockBean.getStock_jp_kano() > 0) {
								html += "即納";
							} else if (stockBean.getStock_unsochu_kano() > 0 || stockBean.getStock_sh_kano() > 0) {
								html += "4-5営業日";
							} else {
								html += "7-10営業日";
							}
							html += "</td>";
						}
					}
				}
				html += "</tr>";
			}
		} else {
			html = "<table style=\"border:1px solid\" width=\"100%\" cellspacing = \"0\">";
			html += " <tr>";
			for (String yokoname : yokoList) {
				html += "<td style=\"border:1px solid\">";
				html += yokoname;
				html += "</td>";
			}
			html += "</tr>";
			html += "<tr>";
			for (StockBean stockBean : stockBeanList) {
				for (String yokoname : yokoList) {
					if (stockBean.getDetail_name_yoko().equals(yokoname.replace("　", " "))) {

						html += "<td style=\"border:1px solid\">";
						if (stockBean.isNyukafukaFlg()) {
							html += "×";
						} else if (stockBean.getStock_jp_kano() > 0) {
							html += "即納";
						} else if (stockBean.getStock_unsochu_kano() > 0 || stockBean.getStock_sh_kano() > 0) {
							html += "4-5営業日";
						} else {
							html += "7-10営業日";
						}
						html += "</td>";
					}
				}

			}
			html += "</tr>";
		}

		html += " </table><!-- end -->";
		if (html.length() <= 9000) {
			html = html.replace("<td style=\"border:1px solid\">7-10営業日</td>",
					"<td style=\"border:1px solid;color:red\">7-10営業日</td>");
			html = html.replace("<td style=\"border:1px solid\">4-5営業日</td>",
					"<td style=\"border:1px solid;color:yellow\">4-5営業日</td>");
			html = html.replace("<td style=\"border:1px solid\">即納</td>",
					"<td style=\"border:1px solid;color:green\">即納</td>");
		}
		return html;

	}

	public static List<String[]> getStock(List<YahooStockInputBean> yahooStockInputBeanList,
			List<StockBean> StockBeanList) throws Exception {
		List<String[]> htmlList = new ArrayList<>();

		List<YahooStockBean> yahooStockBeanList = convertToYahooStockBean(StockBeanList);
		for (YahooStockInputBean yahooStockInputBean : yahooStockInputBeanList) {
			for (YahooStockBean yahooStockBean : yahooStockBeanList) {
				if (yahooStockInputBean.getShohinbango().equals(yahooStockBean.getShohinbango())) {

					htmlList.add(new String[] { yahooStockBean.getShohinbango(),
							getStockHtmlPC(yahooStockInputBean, yahooStockBean),
							getStockHtmlSP(yahooStockInputBean, yahooStockBean) });

				}
			}
		}

		return htmlList;

	}

	private static List<StockBean> getStockFromDB() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StockBean> stockList = new ArrayList<StockBean>();
		List<String[]> unsochuArr = new ArrayList<String[]>();
		StockBean stockbean = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "select commodity_id,detail_no,comm_describe,stock_jp,stock_sh,del_flg from tbl00012";

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {

				stockbean = new StockBean();
				stockList.add(stockbean);
				stockbean.setCommodity_id(rs.getString("commodity_id"));
				stockbean.setDetail_no(rs.getString("detail_no").replace("-0-0", ""));
				stockbean.setStock_jp(rs.getInt("stock_jp"));
				stockbean.setStock_jp_kano(rs.getInt("stock_jp"));
				stockbean.setStock_sh(rs.getInt("stock_sh"));
				stockbean.setStock_sh_kano(rs.getInt("stock_sh"));
				stockbean.setNyukafukaFlg("0".equals(rs.getString("del_flg")) ? false : true);
				String desc[] = rs.getString("comm_describe").split("\r\n");
				if (desc != null && desc.length > 0) {
					stockbean.setDetail_name_yoko(desc[0]);
				}
				if (desc != null && desc.length > 1) {
					stockbean.setDetail_name_shitaga(desc[1]);
				}
			}

			sql = "select t2.commodity_id, t2.quantity from tbl00014 t2 left join tbl00013 t1 on t1.waybill_no = t2.waybill_no where t1.status = '00'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				unsochuArr.add(new String[] { rs.getString("commodity_id"), rs.getString("t2.quantity") });
			}

			OrderCommon orderCommon = new OrderCommon();
			OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
			List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean.getShouhinStsBeanList();

			for (StockBean stock : stockList) {
				String shohinbango = (stock.getCommodity_id()
						+ (Utility.isEmptyString(stock.getDetail_no()) ? "" : stock.getDetail_no()));
				for (ShouhinStsBean shohin : shouhinStsBeanList) {
					if (shohin.getShouhinbango().equals(shohinbango)) {
						stock.setStock_jp_kano(Integer.valueOf(shohin.getNokorikosuJp()));
						stock.setStock_sh_kano(Integer.valueOf(shohin.getNokorikosuSh()));
						stock.setStock_nyukachu_kano(Integer.valueOf(shohin.getNokorikosuNyuka()));
					}
				}
				for (String[] nyuka : unsochuArr) {
					if (nyuka[0].equals(shohinbango)) {
						if (stock.getStock_nyukachu_kano() < 0) {
							stock.setStock_nyukachu_kano(Integer.valueOf(nyuka[1]));
						}
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
		return stockList;
	}

}
