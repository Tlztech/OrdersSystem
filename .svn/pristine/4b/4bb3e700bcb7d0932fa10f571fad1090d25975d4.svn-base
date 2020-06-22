package com.rakuten.r0901.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.TextPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.WebResponse;
import com.gargoylesoftware.htmlunit.html.HtmlButton;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlRadioButtonInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.rakuten.r0901.form.F090101;
import com.rakuten.r0901.form.Meisai;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A0901Common {

	public List<Meisai> getMeisaiList(F090101 f090101) throws Exception {
		String torihikinengappiStart = Utility.strTrim(f090101
				.getTorihikinengappiStart());
		String torihikinengappiEnd = Utility.strTrim(f090101
				.getTorihikinengappiEnd());
		String torihikikubun = Utility.strTrim(f090101.getTorihikikubun());
		String tekiyou = f090101.getTekiyou();
		if (!Utility.isEmptyString(tekiyou)) {
			tekiyou = Utility.hankakuKatakanaToZenkakuKatakana(tekiyou)
					.replace("　", " ").trim();
		}
		String kakuninsts = Utility.strTrim(f090101.getKakuninsts());
		String oshiharaikingakuStart = Utility.strTrim(f090101
				.getOshiharaikingakuStart());
		String oshiharaikingakuEnd = Utility.strTrim(f090101
				.getOshiharaikingakuEnd());
		String otukarikingakuStart = Utility.strTrim(f090101
				.getOtukarikingakuStart());
		String otukarikingakuEnd = Utility.strTrim(f090101
				.getOtukarikingakuEnd());
		String memo = Utility.strTrim(f090101.getMemo());

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			List<Meisai> meisaiList = new ArrayList<Meisai>();
			Meisai meisai = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM TBL00021 WHERE 1=1 ";

			if (Utility.isEmptyString(oshiharaikingakuStart)) {
				oshiharaikingakuStart = "0";
			}
			if (Utility.isEmptyString(otukarikingakuStart)) {
				otukarikingakuStart = "0";
			}
			if (Utility.isEmptyString(torihikinengappiStart)) {
				torihikinengappiStart = "0";
			}
			if (Utility.isEmptyString(oshiharaikingakuEnd)) {
				oshiharaikingakuEnd = "999999999";
			}
			if (Utility.isEmptyString(otukarikingakuEnd)) {
				otukarikingakuEnd = "999999999";
			}
			if (Utility.isEmptyString(torihikinengappiEnd)) {
				torihikinengappiEnd = "999999999";
			}

			if (!Utility.isEmptyString(torihikikubun)) {
				sql += " AND TORIHIKIKUBUN = '" + torihikikubun + "'";
			}
			if (!Utility.isEmptyString(tekiyou)) {
				sql += " AND TEKIYOU LIKE  '%" + tekiyou + "%'";
			}
			if (!Utility.isEmptyString(kakuninsts)) {
				sql += " AND KAKUNINSTS = " + kakuninsts + "";
			}
			if (!Utility.isEmptyString(memo)) {
				sql += " AND MEMO LIKE  '%" + memo + "%'";
			}

			sql += " AND OSHIHARAIKINGAKU >= " + oshiharaikingakuStart;
			sql += " AND OSHIHARAIKINGAKU <= " + oshiharaikingakuEnd;
			sql += " AND OTUKARIKINGAKU >= " + otukarikingakuStart;
			sql += " AND OTUKARIKINGAKU <= " + otukarikingakuEnd;
			sql += " AND TORIHIKINENGAPPI >= "
					+ torihikinengappiStart.replace("-", "");
			sql += " AND TORIHIKINENGAPPI <= "
					+ torihikinengappiEnd.replace("-", "");

			sql += " ORDER BY KANRIBANGO DESC";

			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				meisai = new Meisai();
				meisai.setKanribango(rs.getString("KANRIBANGO"));
				meisai.setTorihikinengappi(Utility.formatData(rs
						.getString("TORIHIKINENGAPPI")));
				meisai.setOshiharaikingaku(Utility.numberFormat(rs
						.getString("OSHIHARAIKINGAKU")));
				meisai.setOtukarikingaku(Utility.numberFormat(rs
						.getString("OTUKARIKINGAKU")));
				meisai.setTorihikikubun(rs.getString("TORIHIKIKUBUN"));
				meisai.setSashihikisanko(Utility.numberFormat(rs
						.getString("SASHIHIKISANKO")));
				meisai.setTekiyou(rs.getString("TEKIYOU"));
				meisai.setMemo(rs.getString("MEMO"));
				meisai.setKakuninSts(rs.getString("KAKUNINSTS"));

				meisaiList.add(meisai);
			}
			// commit
			conn.commit();
			return meisaiList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public int updateStatus(String kanribango, String status) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "UPDATE TBL00021 SET KAKUNINSTS = ? WHERE KANRIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, kanribango);
			int count = ps.executeUpdate();

			// commit
			conn.commit();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public int updateMemo(String kanribango, String memo) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "UPDATE TBL00021 SET MEMO = ? WHERE KANRIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, memo);
			ps.setString(2, kanribango);
			int count = ps.executeUpdate();

			// commit
			conn.commit();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public List<Meisai> getList(String logKey) throws Exception {
		TextPage textPage = null;
		WebResponse response = null;
		try {
			WebClient webClient = new WebClient();
			Utility.addLog("取り込み中。。。。。。。。。。", logKey);
			// 1.获取某个待测页面
			WebClientOptions options = webClient.getOptions();
			// options.setJavaScriptEnabled(false);
			options.setThrowExceptionOnScriptError(false);
			HtmlPage page = webClient
					.getPage("https://www.direct.gunmabank.co.jp/ib/index.do?PT=BS&CCT0080=0128");
			// 2.获取页面上的表单
			HtmlForm form = page.getForms().get(0);
			// 3.获取页面上的各个元素
			@SuppressWarnings("deprecation")
			HtmlButton button = form.getElementById("btn003");
			HtmlButton submit = null;
			HtmlTextInput textField = form.getElementById("txtBox001");
			HtmlPasswordInput pass1 = form.getElementById("pswd002");
			HtmlPasswordInput pass2 = form.getElementById("pswd001");
			// 4.给元素赋值
			textField.setValueAttribute("0902454082");
			pass1.setValueAttribute("621024");
			pass2.setValueAttribute("7411");
			// 5.提交
			page = (HtmlPage) button.click();
			form = page.getForms().get(0);
			if (page.asXml().contains("画面ID：BLI800")) {
				try {
					submit = form.getElementById("btn003");
					if ("forward_BSM0010".equals(submit.getNameAttribute())) {
						page = (HtmlPage) submit.click();
						form = page.getForms().get(0);
					}
				} catch (ElementNotFoundException e) {
				}
			}

			// 出入金明細照会
			Utility.addLog("入出金明細照会。。。。。。。。。。", logKey);
			button = form.getElementById("btn008-1");
			page = (HtmlPage) button.click();

			// 入出金明細照会（照会期間選択）
			Utility.addLog("入出金明細照会（照会期間選択）。。。。。。。。。。", logKey);
			form = page.getForms().get(57);
			HtmlRadioButtonInput radio = form.getElementById("radio001");
			radio.setValueAttribute("17");
			radio.click();
//			submit = form.getButtonByName("forward_BSM0010");
			// page = (HtmlPage) submit.click();
			page = (HtmlPage) form.click();
			// download
			Utility.addLog("データダウンロード中。。。。。。。。。。", logKey);
			form = page.getFormByName("_BDLForm");
			button = form.getElementById("btn009");
			textPage = button.click();
			response = textPage.getWebResponse();

		} catch (FailingHttpStatusCodeException e) {
			reload(logKey);
		}

		String date = response.getContentAsString("Windows-31J");

		String[] dateArr = date.split("\r\n");

		List<Meisai> newMeisaiList = new ArrayList<Meisai>();
		Meisai meisai = null;
		String[] meisaiArr = null;

		for (int i = 1; i < dateArr.length; i++) {
			meisai = new Meisai();
			newMeisaiList.add(meisai);

			dateArr[i] = dateArr[i].replaceFirst("\"", "");
			meisaiArr = dateArr[i].split("\",");
			if (!Utility.isEmptyString(meisaiArr[0])) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
				String data = sdf.format(sdf2.parse(meisaiArr[0]));
				meisai.setTorihikinengappi(data);
			}
			if (!Utility.isEmptyString(meisaiArr[1])) {
				meisai.setOshiharaikingaku(meisaiArr[1].replace("\"", "")
						.replace("\\", "").replace(",", ""));
			}
			if (!Utility.isEmptyString(meisaiArr[2])) {
				meisai.setOtukarikingaku(meisaiArr[2].replace("\"", "")
						.replace("\\", "").replace(",", ""));
			}
			if (!Utility.isEmptyString(meisaiArr[3])) {
				meisai.setTorihikikubun(meisaiArr[3].replace("\"", ""));
			}
			if (!Utility.isEmptyString(meisaiArr[4])) {
				meisai.setSashihikisanko(meisaiArr[4].replace("\"", "")
						.replace("\\", "").replace(",", ""));
			}
			if (!Utility.isEmptyString(meisaiArr[5])) {
				meisai.setTekiyou(Utility.hankakuKatakanaToZenkakuKatakana(
						meisaiArr[5].replace("\"", "")).replace("　", " "));
			}
			if (!Utility.isEmptyString(meisaiArr[6])) {
				meisai.setMemo(meisaiArr[6].replace("\"", ""));
			}

		}

		return newMeisaiList;
	}

	private void reload(String logKey) throws Exception {
		getList(logKey);
	}

	public void insertDB(List<Meisai> insertList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String kanribango = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "SELECT MAX(KANRIBANGO) KANRIBANGO FROM TBL00021";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				kanribango = rs.getString("KANRIBANGO");
			}
			if (Utility.isEmptyString(kanribango)) {
				kanribango = "100000000000";
			}
			for (int i = 0; i < insertList.size(); i++) {
				int j = 0;
				Meisai meisai = insertList.get(i);
				sql = "INSERT INTO `tbl00021`(`KANRIBANGO`,`TORIHIKINENGAPPI`,`OSHIHARAIKINGAKU`,`OTUKARIKINGAKU`,`TORIHIKIKUBUN`,`SASHIHIKISANKO`,`TEKIYOU`,`MEMO`,`KAKUNINSTS`)VALUES(?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(++j,
						String.valueOf(Long.valueOf(kanribango) + i + 1));
				ps.setString(++j, meisai.getTorihikinengappi());
				ps.setString(++j, meisai.getOshiharaikingaku());
				ps.setString(++j, meisai.getOtukarikingaku());
				ps.setString(++j, meisai.getTorihikikubun());
				ps.setString(++j, meisai.getSashihikisanko());
				ps.setString(++j, meisai.getTekiyou());
				ps.setString(++j, meisai.getMemo());
				ps.setString(++j, "0");
				ps.executeUpdate();
			}
			// commit
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}
}
