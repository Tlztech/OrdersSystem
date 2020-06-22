package com.rakuten.r0801.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r0801.bean.ViviCommodityInfo;
import com.rakuten.r0801.common.A0801Common;
import com.rakuten.r0801.form.F080201;
import com.rakuten.r0801.form.ShohinList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A08010202Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F080201 f080201 = null;
	private String logKey = null;
	private static final String STATUS_SHINKI = "01";
	private static final String STATUS_KOUSHIN = "02";
	private static final String STATUS_SAKUJO = "03";
	private A0801Common a0801Common = new A0801Common();

	protected void exec() throws Exception {
		String mekabango = "";
		String rinku = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = null;
		String shumokubango = f080201.getShumokubango();
		if ("2".equals(getMode())) {
			int index = Integer.valueOf(getRowIndex());
			mekabango = f080201.getShohinList().get(index).getMekabango();
			rinku = f080201.getShohinList().get(index).getRinku();
			koushin(rinku, mekabango, shumokubango);
			time = format.format(new Date());
			updateTime(shumokubango, mekabango, time);

		} else if ("1".equals(getMode())) {
			String shumokuRinku = getShumokuRinku(shumokubango);
			List<ShohinList> shohinList = a0801Common
					.getShohinList(shumokubango);
			// List<ShohinList> shohinList =
			// a0801Common.getShohinListFromExcel();
			List<ViviCommodityInfo> infoList = a0801Common.getViviInfoList(
					shumokuRinku, logKey);
			// List<ViviCommodityInfo> infoList = a0801Common
			// .getShohinListFromExcel();
			List<ViviCommodityInfo> insertList = getInsertList(infoList,
					shohinList);
			List<ShohinList> updateList = getUpdateList(infoList, shohinList);
			List<ShohinList> sakujoList = getSakujoList(infoList, shohinList);

			if (!Utility.isEmptyList(insertList)) {
				a0801Common.insertDBShousai(insertList, shumokubango);
				for (int i = 0; i < insertList.size(); i++) {
					updateStatus(shumokubango, insertList.get(i)
							.getMeikahinban(), STATUS_SHINKI);
				}
			}
			if (!Utility.isEmptyList(updateList)) {
				updateKagakuAndZaiko(shumokubango, updateList);
				for (int i = 0; i < updateList.size(); i++) {
					updateStatus(shumokubango,
							updateList.get(i).getMekabango(), STATUS_KOUSHIN);
				}
			}
			if (!Utility.isEmptyList(sakujoList)) {
				for (int i = 0; i < sakujoList.size(); i++) {
					updateStatus(shumokubango,
							sakujoList.get(i).getMekabango(), STATUS_SAKUJO);
				}
			}
			time = format.format(new Date());
			for (int i = 0; i < shohinList.size(); i++) {
				updateTime(shumokubango, shohinList.get(i).getMekabango(), time);
			}
		}

	}

	private void updateKagakuAndZaiko(String shumokubango,
			List<ShohinList> updateList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		conn = JdbcConnection.getConnection();

		try {
			sql = "UPDATE TBL00020 SET KAKAKU = ? , ZAIKO = ? WHERE SHUMOKU_BANGO = ? AND MEKA_BANGO = ?";
			for (int i = 0; i < updateList.size(); i++) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, updateList.get(i).getKakaku());
				ps.setString(2, updateList.get(i).getZaiko());
				ps.setString(3, shumokubango);
				ps.setString(4, updateList.get(i).getMekabango());
				ps.executeUpdate();
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

	private List<ShohinList> getSakujoList(List<ViviCommodityInfo> infoList,
			List<ShohinList> shohinList) {
		List<ShohinList> sakujoList = new ArrayList<ShohinList>();
		for (int i = 0; i < shohinList.size(); i++) {
			boolean ariFlg = false;
			String mekabango = shohinList.get(i).getMekabango();
			for (int j = 0; j < infoList.size(); j++) {
				if (mekabango.equals(infoList.get(j).getMeikahinban())) {
					ariFlg = true;
					break;
				}
			}
			if (!ariFlg) {
				sakujoList.add(shohinList.get(i));
			}
		}

		return sakujoList;
	}

	private List<ShohinList> getUpdateList(List<ViviCommodityInfo> infoList,
			List<ShohinList> shohinList) {
		List<ShohinList> updateList = new ArrayList<ShohinList>();
		for (int i = 0; i < shohinList.size(); i++) {
			String mekabango = shohinList.get(i).getMekabango();
			for (int j = 0; j < infoList.size(); j++) {
				if (mekabango.equals(infoList.get(j).getMeikahinban())) {
					if (!infoList.get(j).getKakaku()
							.equals(shohinList.get(i).getKakaku())
							|| !infoList.get(j).getZaiko()
									.equals(shohinList.get(i).getZaiko())) {
						updateList.add(shohinList.get(i));
					}

				}
			}
		}

		return updateList;
	}

	private List<ViviCommodityInfo> getInsertList(
			List<ViviCommodityInfo> infoList, List<ShohinList> shohinList) {
		List<ViviCommodityInfo> insertList = new ArrayList<ViviCommodityInfo>();
		for (int i = 0; i < infoList.size(); i++) {
			boolean ariFlg = false;
			String mekabango = infoList.get(i).getMeikahinban();
			for (int j = 0; j < shohinList.size(); j++) {
				if (mekabango.equals(shohinList.get(j).getMekabango())) {
					ariFlg = true;
					break;
				}
			}
			if (!ariFlg) {
				insertList.add(infoList.get(i));
			}
		}

		return insertList;
	}

	private void koushin(String rinku, String mekabango, String shumokubango)
			throws Exception {
		ViviCommodityInfo viviCommodityInfo = getInfoVivi(rinku, mekabango);
		if (viviCommodityInfo == null) {
			// 失效
			updateStatus(shumokubango, mekabango, STATUS_SAKUJO);
		} else {
			ShohinList shohin = getShohin(shumokubango, mekabango);
			if (!shohin.getZaiko().equals(viviCommodityInfo.getZaiko())
					|| !shohin.getKakaku()
							.equals(viviCommodityInfo.getKakaku())) {
				updateShohin(viviCommodityInfo.getZaiko(),
						viviCommodityInfo.getKakaku(), shumokubango, mekabango);
				updateStatus(shumokubango, mekabango, STATUS_KOUSHIN);
			}
		}

	}

	private String getShumokuRinku(String shumokubango) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String rinku = null;
		conn = JdbcConnection.getConnection();

		try {
			sql = "SELECT RINKU FROM TBL00019 WHERE SHUMOKU_BANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shumokubango);
			rs = ps.executeQuery();
			while (rs.next()) {
				rinku = rs.getString("RINKU");
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return rinku;
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
	private ViviCommodityInfo getInfoVivi(String url, String mekabango)
			throws Exception {
		ViviCommodityInfo commodityInfo = null;
		WebClient webClient = new WebClient();
		HtmlPage page = null;
		String htmltext = null;

		Utility.addLog("打开商品链接" + url, logKey);

		page = webClient.getPage(url);
		// 商品詳細
		htmltext = page.asXml();

		if (!Utility.isEmptyString(mekabango)) {
			if (!htmltext.contains(mekabango)) {
				return null;
			}
		}

		// 开始解析
		System.out.println("开始解析...........");
		Utility.addLog("开始解析...........", logKey);
		HtmlDivision division = page.getHtmlElementById("division-itemdetail");
		HtmlDivision division2 = page.getHtmlElementById("division-itemlead");
		String divText = division.asXml().replace("<br/>", "");
		String divText2 = division2.asXml();
		String price = "";
		if (divText2.contains("¥")) {
			price = divText2.substring(divText2.indexOf("¥"));
			price = price.substring(0,
					price.indexOf("<span class=\"ic-membersp\">"));
			price = price.replace("<span class=\"fontM fontB\">", "");
			price = price.replace("¥", "");
			price = price.replace("　", "");
			price = price.replace("</span>", "");
			price = price.replace("<big class=\"fontL fontB fc-d64545\">", "");
			price = price.replace("</big>", "");
			price = price.replace("<span class=\"fontM fontB fc-d64545\">", "");
		}
		Map<String, String> dateMap = new HashMap<String, String>();
		String elemete = null;
		String value = null;
		while (divText.contains("<dt>")) {
			divText = divText.substring(divText.indexOf("<dt>"));
			elemete = divText.substring(4, divText.indexOf("</dt>"));
			value = divText.substring(divText.indexOf("<dd>") + 4,
					divText.indexOf("</dd>"));
			if (value.contains("<p class=\"mgn-t10\">")) {
				value = value.substring(0,
						value.indexOf("<p class=\"mgn-t10\">"));
			}
			divText = divText.replaceFirst("<dt>", "");
			dateMap.put(elemete.replace("\r\n", "").trim(),
					value.replaceFirst("\r\n", "").trim());
		}
		commodityInfo = new ViviCommodityInfo();

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
		commodityInfo.setSaizu(dateMap.get("サイズ").replace("�", "cm"));
		// 在庫
		divText = page.getHtmlElementById("division-itemselect").asText()
				.replace("COLOR/SIZE	STOCK	CART	返品不可", "")
				.replace("\r\nTweet", "").trim();
		commodityInfo.setZaiko(divText);

		String shousai = htmltext.substring(htmltext
				.indexOf("<p class=\"fontM\">") + 17);

		shousai = shousai.substring(0, shousai.indexOf("</p>")).trim();
		shousai = shousai.replace("<br/>", "\r\n");
		commodityInfo.setShouhinshousai(shousai);
		// 備考
		// commodityInfo.setBiko(tempUrlList.get(i)[1]);
		// 画像リスト
		List<String> picList = new ArrayList<String>();
		divText = page.getHtmlElementById("wrapper-itempct").asXml();
		String picUrl = null;
		while (divText.contains("http://img.netvivi.cc/")) {
			divText = divText.substring(divText
					.indexOf("http://img.netvivi.cc/"));
			picUrl = divText.substring(
					divText.indexOf("http://img.netvivi.cc/"),
					divText.indexOf("\""));
			picList.add(picUrl);
			divText = divText.replaceFirst("http://img.netvivi.cc/", "");
		}

		// 解析结束

		return commodityInfo;
	}

	private ShohinList getShohin(String shumokubango, String mekabango)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		ShohinList shohinInfo = null;
		conn = JdbcConnection.getConnection();

		try {
			sql = "SELECT * FROM TBL00020 WHERE SHUMOKU_BANGO = ? AND MEKA_BANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shumokubango);
			ps.setString(2, mekabango);
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinInfo = new ShohinList();

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
		return shohinInfo;

	}

	private void updateShohin(String zaiko, String kakaku, String shumokubango,
			String mekabango) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		conn = JdbcConnection.getConnection();

		try {
			sql = "UPDATE TBL00020 SET ZAIKO = ? , KAKAKU = ? WHERE MEKA_BANGO = ? AND SHUMOKU_BANGO = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, zaiko);
			ps.setString(2, kakaku);
			ps.setString(3, mekabango);
			ps.setString(4, shumokubango);
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	private void updateStatus(String shumokubango, String mekabango,
			String status) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		conn = JdbcConnection.getConnection();

		try {
			sql = "UPDATE TBL00020 SET UPDATED = ? WHERE MEKA_BANGO = ? AND SHUMOKU_BANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, mekabango);
			ps.setString(3, shumokubango);
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	private void updateTime(String shumokubango, String mekabango, String time)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		conn = JdbcConnection.getConnection();

		try {
			sql = "UPDATE TBL00020 SET UPDATE_TIME = ? WHERE MEKA_BANGO = ? AND SHUMOKU_BANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, time);
			ps.setString(2, mekabango);
			ps.setString(3, shumokubango);
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	// private List<String[]> getRinku(String shumokubango) throws Exception {
	// Connection conn = null;
	// PreparedStatement ps = null;
	// String sql = null;
	// ResultSet rs = null;
	// List<String[]> resultList = new ArrayList<String[]>();
	// conn = JdbcConnection.getConnection();
	//
	// try {
	// sql = "SELECT * FROM TBL00020 WHERE SHUMOKU_BANGO = ?";
	// ps = conn.prepareStatement(sql);
	// ps.setString(1, shumokubango);
	// rs = ps.executeQuery();
	// while (rs.next()) {
	// resultList.add(new String[] { rs.getString("RINKU"),
	// rs.getString("MEKA_BANGO") });
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// conn.rollback();
	// throw e;
	// } finally {
	// conn.close();
	// }
	// return resultList;
	// }

	protected void init() {
		setTitle("V080201:日本网站商品清单-照会");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F080201 getF080201() {
		return f080201;
	}

	public void setF080201(F080201 f080201) {
		this.f080201 = f080201;
	}

	public String getLogKey() {
		return logKey;
	}

	public void setLogKey(String logKey) {
		this.logKey = logKey;
	}

}
