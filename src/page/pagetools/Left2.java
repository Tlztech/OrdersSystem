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

public class Left2 {

	public void basicUpdate(String shop, String[] keywords) throws Exception {

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
			List<Inventories> inventories = item.getItemInventory().getInventories();
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
			for (String keyword : keywords) {
				if (item.getItemName().contains(keyword)) {
					shoriItemList.add(item);
					break;
				}
			}
		}

		String commArr = "";
		String souryou = "";
		String commArrkakaku = "";
		String commArrurl = "";
		String searchShopId = "";
		String categoryList = "";

		for (Item item : shoriItemList) {
			if (Utility.isEmptyString(commArr)) {
				commArr = (commArr + "\"" + item.getItemUrl() + "\"");
			} else {
				commArr = (commArr + ",\"" + item.getItemUrl() + "\"");
			}
			if (Utility.isEmptyString(souryou)) {
				souryou = (souryou + "\"" + (item.isIncludedPostage() ? "1" : "0") + "\"");
			} else {
				souryou = (souryou + ",\"" + (item.isIncludedPostage() ? "1" : "0") + "\"");
			}
			if (Utility.isEmptyString(commArrkakaku)) {
				commArrkakaku = (commArrkakaku + "\"" + Utility.numberFormatNoen(String.valueOf(item.getItemPrice()))
						+ "\"");
			} else {
				commArrkakaku = (commArrkakaku + ",\"" + Utility.numberFormatNoen(String.valueOf(item.getItemPrice()))
						+ "\"");
			}
			String url = item.getImages().getImage().get(0).getImageUrl();
			url = url.replace(("https://image.rakuten.co.jp/" + shop + "/cabinet/"), "");
			if (Utility.isEmptyString(commArrurl)) {
				commArrurl = (commArrurl + "\"" + url + "\"");
			} else {
				commArrurl = (commArrurl + ",\"" + url + "\"");
			}
		}

		String shopid = "";
		if ("coverforefront".equals(shop)) {
			shopid = "308759";
		}
		searchShopId = "<INPUT name=\"sid\" value=\"" + shopid + "\" type=\"hidden\">";

		if ("coverforefront".equals(shop)) {
			categoryList = categoryList + "<table id=\"skplncategory\">\r\n";
			categoryList = categoryList + "    <tr>\r\n";
			categoryList = categoryList + "      <th><div><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/category_title.gif\"/></div></th>\r\n";
			categoryList = categoryList + "    </tr>\r\n";
			categoryList = categoryList + "    <tr>\r\n";
			categoryList = categoryList + "      <td><table>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td\r\n";
			categoryList = categoryList + "class=\"skplncateparent\"><img src=\"https://image.rakuten.co.jp/com/img/skp/0001/parent_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000125/\">レディースファッション</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000142/\">ドレス</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000143/\">ワンピース</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000144/\">トップス</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000158/\">タンクトップ</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000159/\">ブラウス</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000160/\">カーディガン・ボレロ</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000161/\">カットソー・Tシャツ</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000162/\">ニット・セーター</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000190/\">パーカ</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000257/\">チュニック</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000258/\">シャツ</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000259/\">カットソー</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000260/\">アンサンブル</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000261/\">Tシャツ</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000145/\">ボトムス</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000163/\">パンツ</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000164/\">スパッツ・レギンス</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000165/\">スカート</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000146/\">アウター</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000171/\">デニムジャケット</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000170/\">コート</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000199/\">ポンチョ</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000343/\">毛皮</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000344/\">ジャンパー・ブルゾン</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncate3\">・<a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000172/\">ジャケット・ブレザー</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000147/\">ストッキング</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000148/\">水着</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000149/\">帽子</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000150/\">上下セット</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000151/\">オールインワン</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000189/\">つけ襟・アクセサリー</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000380/\">スーツ</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncatechild\"><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/child_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000204/\">レインウエア</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncateparent\"><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/parent_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000192/\">バッグ・小物・ブランド雑貨</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td\r\n";
			categoryList = categoryList + "class=\"skplncateparent\"><img src=\"https://image.rakuten.co.jp/com/img/skp/0001/parent_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000206/\">靴</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td class=\"skplncateparent\"><img\r\n";
			categoryList = categoryList + "src=\"https://image.rakuten.co.jp/com/img/skp/0001/parent_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000212/\">インナー・下着・ナイトウエア</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "          <tr>\r\n";
			categoryList = categoryList + "            <td\r\n";
			categoryList = categoryList + "class=\"skplncateparent\"><img src=\"https://image.rakuten.co.jp/com/img/skp/0001/parent_category.gif\"/><a\r\n";
			categoryList = categoryList + "target=_\"top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000213/\">ジュエリー・アクセサリー</a></td>\r\n";
			categoryList = categoryList + "          </tr>\r\n";
			categoryList = categoryList + "        </table></td>\r\n";
			categoryList = categoryList + "    </tr>\r\n";
			categoryList = categoryList + "  </table>\r\n";
		}

		File file = new File("C:\\pages\\left2.html");
		InputStream is = new FileInputStream(file);

		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "euc-jp"));
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
		shoriDiv = shoriDiv.replace("#{searchShopId}", searchShopId);
		shoriDiv = shoriDiv.replace("#{categoryList}", categoryList);
		shoriDiv = shoriDiv.replace("#{shop}", shop);
		try {
			File f = new File("C:\\temp\\left2_" + shop + ".html");
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "euc-jp");
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
		remote = "left2.html";
		local = ("C:\\temp\\left2_" + shop + ".html");
		String[] argggg = new String[] { "-s", host, user, pwd, remote, local };
		FtpUtils.ftpUtils(argggg);
	}
}
