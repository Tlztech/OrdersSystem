package page.pagetools;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shohinmodel.bean.Inventories;
import shohinmodel.bean.Inventory;
import shohinmodel.bean.Item;
import shohinmodel.common.Shohincommon;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class Osusume1 {

	public void basicUpdate(String shop, String[] shohinbangoList)
			throws Exception {

		List<String> itemXmlList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  shohinxml_tbl where shop = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shop);
			rs = ps.executeQuery();
			while (rs.next()) {
				itemXmlList.add(rs.getString("shohinxml"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		Shohincommon common = new Shohincommon();
		List<Item> itemList = new ArrayList<Item>();
		for (String itemXml : itemXmlList) {
			itemList.add(common.getItemBean(itemXml));
		}

		List<Item> shoriItemList = new ArrayList<Item>();
		for (Item item : itemList) {
			if (item.isDepot()) {
				continue;
			}
			boolean ariFlg = false;
			List<Inventories> inventories = item.getItemInventory()
					.getInventories();
			for (Inventories inventorie : inventories) {
				if (ariFlg) {
					break;
				}
				List<Inventory> inventorys = inventorie.getInventory();
				for (Inventory inventory : inventorys) {
					if (inventory.isBackorder()) {
						ariFlg = true;
						break;
					}
				}
			}
			if (!ariFlg) {
				continue;
			}
			for (String shohinbango : shohinbangoList) {
				if (item.getItemUrl().equals(shohinbango)) {
					shoriItemList.add(item);
					break;
				}
			}
		}

		String commArr = "";
		String souryou = "";
		String commArrkakaku = "";
		String commArrurl = "";

		for (Item item : shoriItemList) {
			if (Utility.isEmptyString(commArr)) {
				commArr = (commArr + "\"" + item.getItemUrl() + "\"");
			} else {
				commArr = (commArr + ",\"" + item.getItemUrl() + "\"");
			}
			if (Utility.isEmptyString(souryou)) {
				souryou = (souryou + "\""
						+ (item.isIncludedPostage() ? "1" : "0") + "\"");
			} else {
				souryou = (souryou + ",\""
						+ (item.isIncludedPostage() ? "1" : "0") + "\"");
			}
			if (Utility.isEmptyString(commArrkakaku)) {
				commArrkakaku = (commArrkakaku
						+ "\""
						+ Utility.numberFormatNoen(String.valueOf(item
								.getItemPrice())) + "\"");
			} else {
				commArrkakaku = (commArrkakaku
						+ ",\""
						+ Utility.numberFormatNoen(String.valueOf(item
								.getItemPrice())) + "\"");
			}
			String url = item.getImages().getImage().get(0).getImageUrl();
			url = url.replace(
					("https://image.rakuten.co.jp/" + shop + "/cabinet/"), "");
			if (Utility.isEmptyString(commArrurl)) {
				commArrurl = (commArrurl + "\"" + url + "\"");
			} else {
				commArrurl = (commArrurl + ",\"" + url + "\"");
			}
		}

		File file = new File("C:\\pages\\osusume1.html");
		InputStream is = new FileInputStream(file);

		BufferedReader bf = new BufferedReader(new InputStreamReader(is,
				"shift-jis"));
		// 最好在将字节流转换为字符流的时候 进行转码
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = bf.readLine()) != null) {
			buffer.append(line + "\r\n");
		}
		bf.close();

		String shoriDiv = buffer.toString();
		shoriDiv = shoriDiv.replace("#{commArr}", commArr);

		shoriDiv = shoriDiv.replace("#{souryou}", souryou);
		shoriDiv = shoriDiv.replace("#{commArrkakaku}", commArrkakaku);
		shoriDiv = shoriDiv.replace("#{commArrurl}", commArrurl);
		shoriDiv = shoriDiv.replace("#{shop}", shop);
		try {
			File f = new File("C:\\temp\\osusume1_" + shop + ".html");
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(
					new FileOutputStream(f), "shift-jis");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(shoriDiv);
			writer.close();
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			e.printStackTrace();
		}

		String host = "ftp.rakuten.ne.jp:16910";

		String user = "";
		String pwd = "";
		String local = "";
		String remote = "";
		if ("trend777".equals(shop)) {
			FtpAccountBeanTrend777 accountTrend = new FtpAccountBeanTrend777();
			user = accountTrend.getUser();
			pwd = accountTrend.getPwd();
		} else if ("coverforefront".equals(shop)) {
			FtpAccountBeanCoverforefront accountTrend = new FtpAccountBeanCoverforefront();
			user = accountTrend.getUser();
			pwd = accountTrend.getPwd();
		}
		remote = "osusume1.html";
		local = ("C:\\temp\\osusume1_" + shop + ".html");
		String[] argggg = new String[] { "-s", host, user, pwd, remote, local };
		FtpUtils.ftpUtils(argggg);
	}
}
