package com.rakuten.r0801.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r0801.form.F080201;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A08010203Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F080201 f080201 = null;
	private String logKey = null;

	protected void exec() throws Exception {

		String mekabango = "";
		String rinku = "";
		String shumokubango = f080201.getShumokubango();
		String realpath = ServletActionContext.getRequest().getRealPath("/");
		String path = realpath + "/taobaoPic/" + shumokubango + "/";

		if ("2".equals(getMode())) {
			int index = Integer.valueOf(getRowIndex());
			mekabango = f080201.getShohinList().get(index).getMekabango();
			rinku = f080201.getShohinList().get(index).getRinku();
			downloadPic(rinku, path, mekabango);
			updateStatus(shumokubango, mekabango);
		} else if ("1".equals(getMode())) {
			List<String[]> rinkuList = getRinku(shumokubango);
			for (int i = 0; i < rinkuList.size(); i++) {
				Utility.addLog(
						"正在处理" + String.valueOf(i + 1) + "/"
								+ String.valueOf(rinkuList.size()) + " "
								+ rinkuList.get(i)[1], logKey);
				downloadPic(rinkuList.get(i)[0], path, rinkuList.get(i)[1]);
				updateStatus(shumokubango, rinkuList.get(i)[1]);
			}

		}

	}

	private void downloadPic(String rinku, String path, String mekabango)
			throws Exception {
		System.out.println("正在打开虚拟浏览器.............");
		Utility.addLog("正在打开虚拟浏览器.............", logKey);
		WebClient webClient = new WebClient();

		System.out.println("正在载入页面" + rinku);
		Utility.addLog("正在载入页面" + rinku, logKey);
		HtmlPage page = webClient.getPage(rinku);

		// 开始解析
		System.out.println("开始解析...........");
		Utility.addLog("开始解析...........", logKey);
		try {
			HtmlDivision division = page
					.getHtmlElementById("division-itemdetail");
			String divText = division.asXml().replace("<br/>", "");
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
			URL url = null;
			File outFile = null;
			String filePath = path + mekabango + "/";
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdirs();
			}
			OutputStream os = null;
			InputStream is = null;
			int i = 1;
			for (String pic : picList) {
				url = new URL(pic);
				outFile = new File(filePath + mekabango + "_"
						+ String.valueOf(i) + ".jpg");
				if (outFile.exists()) {
					outFile.delete();
				}
				System.out.println("正在下载：" + picUrl);
				Utility.addLog("正在下载：" + picUrl, logKey);
				os = new FileOutputStream(outFile);
				is = url.openStream();
				byte[] buff = new byte[1024];
				while (true) {
					int readed = is.read(buff);
					if (readed == -1) {
						break;
					}
					byte[] temp = new byte[readed];
					System.arraycopy(buff, 0, temp, 0, readed);
					os.write(temp);
				}
				is.close();
				os.close();

				i++;
			}
		} catch (ElementNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void updateStatus(String shumokubango, String mekabango)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		conn = JdbcConnection.getConnection();

		try {
			sql = "UPDATE TBL00020 SET GAZO = ? WHERE MEKA_BANGO = ? AND SHUMOKU_BANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "1");
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

	private List<String[]> getRinku(String shumokubango) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String[]> resultList = new ArrayList<String[]>();
		conn = JdbcConnection.getConnection();

		try {
			sql = "SELECT * FROM TBL00020 WHERE SHUMOKU_BANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shumokubango);
			rs = ps.executeQuery();
			while (rs.next()) {
				resultList.add(new String[] { rs.getString("RINKU"),
						rs.getString("MEKA_BANGO") });
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return resultList;
	}

	protected void init() {
		setTitle("V080201:日本网站商品清单-照会");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the logKey
	 */
	public String getLogKey() {
		return logKey;
	}

	/**
	 * @param logKey
	 *            the logKey to set
	 */
	public void setLogKey(String logKey) {
		this.logKey = logKey;
	}

	public F080201 getF080201() {
		return f080201;
	}

	public void setF080201(F080201 f080201) {
		this.f080201 = f080201;
	}

}
