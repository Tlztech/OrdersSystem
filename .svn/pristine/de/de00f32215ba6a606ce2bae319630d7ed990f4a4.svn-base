package com.rakuten.r0801.common;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.rakuten.r0801.bean.ViviCommodityInfo;
import com.rakuten.r0801.form.ShohinList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A0801Common {
	public static final String VIVI_BASE_URL = "http://www.netvivi.cc/";

	public List<ViviCommodityInfo> getViviInfoList(String url, String logKey)
			throws Exception {
		String lastPage = null;
		System.out.println("正在打开虚拟浏览器.............");
		Utility.addLog("正在打开虚拟浏览器.............", logKey);
		WebClient webClient = new WebClient();
		System.out.println("正在载入页面" + url);
		Utility.addLog("正在载入页面" + url, logKey);
		HtmlPage page = webClient.getPage(url);
		// html 源代码
		String htmltext = page.asXml();
		System.out.println("获取商品页数.............");
		Utility.addLog("获取商品页数.............", logKey);

		String pageInfo = htmltext.substring(
				htmltext.indexOf("<div class=\"division-pagenavi floatR\">"),
				htmltext.indexOf("<a class=\"nextpostslink\""));

		pageInfo = pageInfo.substring(pageInfo.indexOf("<a href"));
		while (pageInfo.contains("<a href")) {
			lastPage = pageInfo.substring(pageInfo.indexOf("<a href"),
					pageInfo.indexOf("\">") + 2);
			pageInfo = pageInfo.replace(lastPage, "");
			if (pageInfo.indexOf("<a href") >= 0) {
				pageInfo = pageInfo.substring(pageInfo.indexOf("<a href"));
			}
		}
		String pageIndex = lastPage.substring(lastPage.indexOf("PAGEIDX=") + 8);
		pageIndex = pageIndex.substring(0, pageIndex.indexOf("&amp;"));

		int pages = Integer.valueOf(pageIndex) + 1;
		System.out.println("共有" + pages + "页商品");
		Utility.addLog("共有" + pages + "页商品", logKey);
		int i = 0;
		String pageUrl = lastPage.replace("<a href=\"", "").replace("\">", "");
		pageUrl = pageUrl.substring(0, pageUrl.indexOf("PAGEIDX=") + 8)
				+ "ddpeggg"
				+ pageUrl.substring(pageUrl.indexOf("PAGEIDX=") + 9);
		pageUrl = VIVI_BASE_URL + pageUrl;
		List<ViviCommodityInfo> infoList = new ArrayList<ViviCommodityInfo>();
		do {
			boolean repeatFlg = true;
			i++;
			System.out.println("解析第" + i + "页中.............");
			Utility.addLog("解析第" + i + "页中.............", logKey);
			if (i > 1) {
				page = webClient.getPage(pageUrl.replace("ddpeggg",
						String.valueOf(i - 1)).replace("&amp;", "&"));
			}
			while (repeatFlg) {
				try {
					htmltext = page.asXml();
					infoList.addAll(getInfoVivi(htmltext, webClient, page, i,
							logKey));
					repeatFlg = false;
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}

		} while (i < pages);

		return infoList;

	}

	/**
	 * 获取vivi商品详细信息
	 * 
	 * @param htmltext1
	 * @param page
	 * @param webClient
	 * @param index
	 * @return
	 * @throws Exception
	 */
	private List<ViviCommodityInfo> getInfoVivi(String htmltext,
			WebClient webClient, HtmlPage page, int index, String logKey)
			throws Exception {
		List<ViviCommodityInfo> viviCommodityInfoList = new ArrayList<ViviCommodityInfo>();
		ViviCommodityInfo commodityInfo = null;
		List<String[]> tempUrlList = new ArrayList<String[]>();
		String tempUrl = null;
		String tempString = null;
		String htmltext1 = htmltext;
		while (htmltext1.contains("item.php?")) {
			htmltext1 = htmltext1.substring(htmltext1.indexOf("item.php?"));
			tempUrl = VIVI_BASE_URL
					+ htmltext1.substring(0, htmltext1.indexOf("\""));
			htmltext1 = htmltext1.replaceFirst("item.php?", "");
			tempString = htmltext1.substring(0,
					htmltext1.indexOf("ほしいものリストに追加"));
			if (tempString.contains("<span class=\"fc-1a1a1a\">")) {
				tempString = tempString.substring(tempString
						.indexOf("<span class=\"fc-1a1a1a\">"));
				tempString = tempString.replace("<span class=\"fc-1a1a1a\">",
						"");
				tempString = tempString.substring(0,
						tempString.indexOf("</span>"));
			} else {
				tempString = "";
			}
			htmltext1 = htmltext1.replaceFirst("item.php?", "");
			tempUrlList.add(new String[] { tempUrl,
					tempString.replace("\r\n", "").trim() });
		}

		System.out.println("第" + index + "页中共有"
				+ String.valueOf(tempUrlList.size()) + "件商品");
		Utility.addLog(
				"第" + index + "页中共有" + String.valueOf(tempUrlList.size())
						+ "件商品", logKey);

		for (int i = 0; i < tempUrlList.size(); i++) {
			System.out.println("解析第" + index + "页中第" + String.valueOf(i + 1)
					+ "件商品");
			Utility.addLog("解析第" + index + "页中第" + String.valueOf(i + 1)
					+ "件商品", logKey);
			System.out.println("打开商品链接" + tempUrlList.get(i)[0]);
			Utility.addLog("打开商品链接" + tempUrlList.get(i)[0], logKey);
			boolean repeatFlg = true;
			while (repeatFlg) {
				try {

					page = webClient.getPage(tempUrlList.get(i)[0]);

					// 开始解析
					System.out.println("开始解析...........");
					Utility.addLog("开始解析...........", logKey);
					HtmlDivision division = page
							.getHtmlElementById("division-itemdetail");
					HtmlDivision division2 = page
							.getHtmlElementById("division-itemlead");
					String divText = division.asXml().replace("<br/>", "");
					String divText2 = division2.asXml();
					String price = "";
					if (divText2.contains("¥")) {
						price = divText2.substring(divText2.indexOf("¥"));
						price = price.substring(0,
								price.indexOf("<span class=\"ic-membersp\">"));
						price = price.replace("<span class=\"fontM fontB\">",
								"");
						price = price.replace("¥", "");
						price = price.replace("　", "");
						price = price.replace("</span>", "");
						price = price.replace(
								"<big class=\"fontL fontB fc-d64545\">", "");
						price = price.replace("</big>", "");
						price = price.replace(
								"<span class=\"fontM fontB fc-d64545\">", "");
					}
					Map<String, String> dateMap = new HashMap<String, String>();
					String elemete = null;
					String value = null;
					while (divText.contains("<dt>")) {
						divText = divText.substring(divText.indexOf("<dt>"));
						elemete = divText
								.substring(4, divText.indexOf("</dt>"));
						value = divText.substring(divText.indexOf("<dd>") + 4,
								divText.indexOf("</dd>"));
						if (value.contains("<p class=\"mgn-t10\">")) {
							value = value.substring(0,
									value.indexOf("<p class=\"mgn-t10\">"));
						}
						divText = divText.replaceFirst("<dt>", "");
						dateMap.put(elemete.replace("\r\n", "").trim(), value
								.replaceFirst("\r\n", "").trim());
					}
					commodityInfo = new ViviCommodityInfo();
					viviCommodityInfoList.add(commodityInfo);
					commodityInfo.setKakaku(price);
					// メーカー品番
					commodityInfo.setMeikahinban(dateMap.get("メーカー品番"));
					// ブランド
					commodityInfo.setBrank(dateMap.get("ブランド"));
					// リンク
					commodityInfo.setLink(page.getUrl().toString());
					// カテゴリ
					// commodityInfo.setKategori(kategori);
					// 商品名
					commodityInfo.setShouhinmei(dateMap.get("商品名"));
					// 素材
					commodityInfo.setSozai(dateMap.get("素材"));
					// カラー
					commodityInfo.setKara(dateMap.get("カラー"));
					// サイズ
					commodityInfo.setSaizu(dateMap.get("サイズ")
							.replace("�", "cm"));
					// 在庫
					divText = page.getHtmlElementById("division-itemselect")
							.asText().replace("COLOR/SIZE	STOCK	CART	返品不可", "")
							.replace("\r\nTweet", "").trim();
					commodityInfo.setZaiko(divText);
					// 商品詳細
					htmltext = page.asXml();
					String shousai = htmltext.substring(htmltext
							.indexOf("<p class=\"fontM\">") + 17);

					shousai = shousai.substring(0, shousai.indexOf("</p>"))
							.trim();
					shousai = shousai.replace("<br/>", "\r\n");
					commodityInfo.setShouhinshousai(shousai);
					// 備考
					commodityInfo.setBiko(tempUrlList.get(i)[1]);
					// 画像リスト
					List<String> picList = new ArrayList<String>();
					divText = page.getHtmlElementById("wrapper-itempct")
							.asXml();
					String picUrl = null;
					while (divText.contains("http://img.netvivi.cc/")) {
						divText = divText.substring(divText
								.indexOf("http://img.netvivi.cc/"));
						picUrl = divText.substring(
								divText.indexOf("http://img.netvivi.cc/"),
								divText.indexOf("\""));
						picList.add(picUrl);
						divText = divText.replaceFirst(
								"http://img.netvivi.cc/", "");
					}

					// System.out.println("下载图片...........");
					// for (int j = 0; j < picList.size(); j++) {
					// File file = new File("D:/temp/" + baseLocal + "/"
					// + commodityInfo.getMeikahinban() + "/");
					// if (!file.exists()) {
					// file.mkdirs();
					// }
					// downloadPic(
					// picList.get(j),
					// "D:/temp/" + baseLocal + "/"
					// + commodityInfo.getMeikahinban() + "/",
					// commodityInfo.getMeikahinban() + "_"
					// + String.valueOf(j + 1) + ".jpg");
					// }
					repeatFlg = false;
					// 解析结束
				} catch (Exception e) {
					continue;
				}

			}
		}
		return viviCommodityInfoList;
	}

	public void insertDB(List<ViviCommodityInfo> infoList,
			String shumokumeisho, String shumokurinku) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		String kanribango = null;
		String shumokubango = null;
		ResultSet rs = null;
		conn = JdbcConnection.getConnection();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		try {
			sql = "SELECT MAX(KANRIBANGO) KANRIBANGO FROM TBL00020";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				kanribango = rs.getString("KANRIBANGO");
			}
			if (Utility.isEmptyString(kanribango)) {
				kanribango = "10000000";
			}

			sql = "SELECT MAX(SHUMOKU_BANGO) SHUMOKU_BANGO FROM TBL00019";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				shumokubango = rs.getString("SHUMOKU_BANGO");
			}
			if (Utility.isEmptyString(kanribango)) {
				shumokubango = "10000000";
			} else {
				shumokubango = String.valueOf(Integer.valueOf(kanribango) + 1);
			}

			for (int i = 0; i < infoList.size(); i++) {
				kanribango = String.valueOf(Integer.valueOf(kanribango) + 1);
				ViviCommodityInfo info = infoList.get(i);

				sql = "INSERT INTO tbl00020(KANRIBANGO,SHUMOKU_BANGO,MEKA_BANGO,BURANDO,SHOUHINMEI,"
						+ "RINKU,KATEGORI,SOZAI,KARA,SAIZU,ZAIKO,SHOUHIN_SHOUSAI,BIKO,STATUS,UPDATED,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER,KAKAKU)VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				ps = conn.prepareStatement(sql);
				ps.setString(1, kanribango);
				ps.setString(2, shumokubango);
				ps.setString(3, info.getMeikahinban());
				ps.setString(4, info.getBrank());
				ps.setString(5, info.getShouhinmei());
				ps.setString(6, info.getLink());
				ps.setString(7, info.getKategori());
				if (info.getSozai().length() > 1000) {
					info.setSozai(info.getSozai().substring(0, 1000));
				}
				ps.setString(8, info.getSozai());
				ps.setString(9, info.getKara());
				ps.setString(10, info.getSaizu());
				ps.setString(11, info.getZaiko());
				if (info.getShouhinshousai().length() > 2500) {
					info.setShouhinshousai(info.getShouhinshousai().substring(
							0, 2500));
				}
				ps.setString(12, info.getShouhinshousai());
				ps.setString(13, info.getBiko());
				ps.setString(14, "00");
				ps.setString(15, "00");
				ps.setString(16, date);
				ps.setString(17, "admin");
				ps.setString(18, date);
				ps.setString(19, "admin");
				ps.setString(20, info.getKakaku());

				ps.execute();

			}

			sql = "INSERT INTO tbl00019(SHUMOKU_BANGO,SHUMOKUMEI,RINKU,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER) VALUES (?,?,?,?,?,?,?)";

			ps = conn.prepareStatement(sql);
			ps.setString(1, shumokubango);
			ps.setString(2, shumokumeisho);
			ps.setString(3, shumokurinku);
			ps.setString(4, date);
			ps.setString(5, "admin");
			ps.setString(6, date);
			ps.setString(7, "admin");
			ps.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	public void insertDBShousai(List<ViviCommodityInfo> infoList,
			String shumokubango) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		String kanribango = null;
		ResultSet rs = null;
		conn = JdbcConnection.getConnection();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		try {
			sql = "SELECT MAX(KANRIBANGO) KANRIBANGO FROM TBL00020";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				kanribango = rs.getString("KANRIBANGO");
			}
			if (Utility.isEmptyString(kanribango)) {
				kanribango = "10000000";
			}

			for (int i = 0; i < infoList.size(); i++) {
				kanribango = String.valueOf(Integer.valueOf(kanribango) + 1);
				ViviCommodityInfo info = infoList.get(i);

				sql = "INSERT INTO tbl00020(KANRIBANGO,SHUMOKU_BANGO,MEKA_BANGO,BURANDO,SHOUHINMEI,"
						+ "RINKU,KATEGORI,SOZAI,KARA,SAIZU,ZAIKO,SHOUHIN_SHOUSAI,BIKO,STATUS,UPDATED,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER,KAKAKU)VALUES"
						+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

				ps = conn.prepareStatement(sql);
				ps.setString(1, kanribango);
				ps.setString(2, shumokubango);
				ps.setString(3, info.getMeikahinban());
				ps.setString(4, info.getBrank());
				ps.setString(5, info.getShouhinmei());
				ps.setString(6, info.getLink());
				ps.setString(7, info.getKategori());
				if (info.getSozai().length() > 1000) {
					info.setSozai(info.getSozai().substring(0, 1000));
				}
				ps.setString(8, info.getSozai());
				ps.setString(9, info.getKara());
				ps.setString(10, info.getSaizu());
				ps.setString(11, info.getZaiko());
				if (info.getShouhinshousai().length() > 2500) {
					info.setShouhinshousai(info.getShouhinshousai().substring(
							0, 2500));
				}
				ps.setString(12, info.getShouhinshousai());
				ps.setString(13, info.getBiko());
				ps.setString(14, "00");
				ps.setString(15, "00");
				ps.setString(16, date);
				ps.setString(17, "admin");
				ps.setString(18, date);
				ps.setString(19, "admin");
				ps.setString(20, info.getKakaku());

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

	public List<ShohinList> getShohinList(String shumokubango) throws Exception {
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

	public String getShumokumei(String shumokubango) throws Exception {
		String shumokumei = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		conn = JdbcConnection.getConnection();

		try {
			sql = "SELECT * FROM TBL00019 WHERE SHUMOKU_BANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shumokubango);
			rs = ps.executeQuery();
			while (rs.next()) {
				shumokumei = rs.getString("SHUMOKUMEI");
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return shumokumei;
	}

	public List<ViviCommodityInfo> getShohinListFromExcel() throws Exception {
		HSSFRow row = null;
		HSSFSheet sheet = null;
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
				"D:/temp.xls"));
		sheet = workbook.getSheetAt(0);
		List<ViviCommodityInfo> shohinList = new ArrayList<ViviCommodityInfo>();
		int i = 1;
		ViviCommodityInfo shohin = null;
		while (true) {
			row = sheet.getRow(i);
			if (row == null) {
				break;
			}
			if (Utility.isEmptyString(row.getCell(1).getStringCellValue())) {
				break;
			}
			shohin = new ViviCommodityInfo();
			// メーカー品番
			shohin.setMeikahinban(row.getCell(1).getStringCellValue());
			// ブランド
			shohin.setBrank(row.getCell(2).getStringCellValue());
			// 商品名
			shohin.setShouhinmei(row.getCell(5).getStringCellValue());
			// 素材
			shohin.setSozai(row.getCell(6).getStringCellValue());
			// カラー
			shohin.setKara(row.getCell(7).getStringCellValue());
			// サイズ
			shohin.setSaizu(row.getCell(8).getStringCellValue());
			// 在庫
			shohin.setZaiko(row.getCell(9).getStringCellValue());
			// 商品詳細
			shohin.setShouhinshousai(row.getCell(10).getStringCellValue());
			// 備考
			shohin.setBiko(row.getCell(11).getStringCellValue());
			shohin.setKakaku("5,000");
			shohin.setLink(row.getCell(3).getStringCellValue());

			shohinList.add(shohin);
			

			i++;

		}

		return shohinList;
	}

}
