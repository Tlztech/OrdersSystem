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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import shohinmodel.bean.Inventories;
import shohinmodel.bean.Inventory;
import shohinmodel.bean.Item;
import shohinmodel.common.Shohincommon;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class Top {
	public void basicUpdate(String shop) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, -7);
		String chumonnichiji = sdf.format(date.getTime());
		List<String> shohinbangoList = new ArrayList<String>();
		List<String> itemXmlList = new ArrayList<String>();
		List<String> newItemXmlList = new ArrayList<String>();
		String[] newItems = new String[] { "nzwy258", "nzwy259", "nzwy260", "nzwy261", "nzwy262", "nzwy263", "nzwy264",
				"nzwy265", "nzwy266", "nzwy267", "nzwy268", "nzctx1179", "xbx227", "xbx228", "xbx229", "xbx230",
				"xbx224", "xbx225", "xbx226", "nzck273", "nztz289", "nzctx1175", "nzctx1176", "nzctx1177",
				"nzctx1178" };
		try {
			conn = JdbcConnection.getConnection();
			sql = "select  substring_index(shouhinbango,'-',1) shohinbango,sum(kosu) count from common_order_detail_tbl t1 left join common_order_tbl t2 on t1.JUCHUBANGO = t2.CHUMONBANGO where t2.CHUMONNICHIJI >=? and substring_index(shouhinbango,'-',1)<> 'nvsw008'  group by shohinbango order by count desc";
			ps = conn.prepareStatement(sql);

			ps.setString(1, chumonnichiji);
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinbangoList.add(rs.getString("shohinbango"));
			}
			if (shohinbangoList.size() > 30) {
				shohinbangoList = shohinbangoList.subList(0, 30);
			}
			for (String shohinbango : shohinbangoList) {
				if ("trend777".equals(shop) && "sjpj083".equals(shohinbango)) {
					shohinbango = "salesjpj083";
				}
				sql = "SELECT * from  shohinxml_tbl where shop = ? and itemurl = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shop);
				ps.setString(2, shohinbango);
				rs = ps.executeQuery();
				while (rs.next()) {
					itemXmlList.add(rs.getString("shohinxml"));
				}
			}

			for (String newItem : newItems) {
				ps.setString(1, shop);
				ps.setString(2, newItem);
				rs = ps.executeQuery();
				while (rs.next()) {
					newItemXmlList.add(rs.getString("shohinxml"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		Shohincommon common = new Shohincommon();
		List<Item> shoriItemList = new ArrayList<Item>();
		List<Item> newItemList = new ArrayList<Item>();
		for (String itemXml : itemXmlList) {
			shoriItemList.add(common.getItemBean(itemXml));
		}
		for (String newItemXml : newItemXmlList) {
			Item item = common.getItemBean(newItemXml);
			// if (item.getItemUrl().equals("nzck005")) {
			// System.out.print(true);
			// }
			// if (item.isDepot()) {
			// continue;
			// }
			// boolean ariFlg = false;
			// List<Inventories> inventories =
			// item.getItemInventory().getInventories();
			// for (Inventories inventorie : inventories) {
			// if (ariFlg) {
			// break;
			// }
			// List<Inventory> inventorys = inventorie.getInventory();
			// for (Inventory inventory : inventorys) {
			// if (inventory.isBackorder()) {
			// ariFlg = true;
			// break;
			// }
			// }
			// }
			// if (!ariFlg) {
			// continue;
			// }

			newItemList.add(item);
		}

		String commArr = "";
		String souryou = "";
		String commArrkakaku = "";
		String commArrurl = "";

		String new_commArr = "";
		String new_souryou = "";
		String new_commArrkakaku = "";
		String new_commArrurl = "";

		String categoryList = "";
		if ("coverforefront".equals(shop)) {
			categoryList = categoryList
					+ "<table align=\"center\" width=\"1050px\" cellpadding=\"0\" cellspacing=\"0\">";
			categoryList = categoryList + "        <tr>";
			categoryList = categoryList
					+ "            <td align=\"left\"><A target=\"_top\" href=\"https://www.rakuten.ne.jp/gold/coverforefront/index.html\"><img alt=\"coverforefront Logo\" src=\"images/logo.png\" width=\"350px\" ></a></td>";
			categoryList = categoryList
					+ "            <td align=\"right\"><img src=\"images/top2.jpg\" alt=\"お支払い方法と5480以上送料無料\" width=\"650px\" height=\"200px\"></td>";
			categoryList = categoryList + "        </tr>";
			categoryList = categoryList + "        <tr height=\"30px\">";
			categoryList = categoryList + "            <td align=\"right\" colspan=\"2\">";
			categoryList = categoryList
					+ "            ｜<A target=\"_top\" href=\"https://www.rakuten.ne.jp/gold/coverforefront\">トップページ</A>";
			categoryList = categoryList
					+ "            ｜<A target=\"_top\" href=\"https://www.rakuten.co.jp/coverforefront/info.html\">会社概要</A> ";
			categoryList = categoryList
					+ "            ｜<A target=\"_top\" href=\"https://www.rakuten.co.jp/coverforefront/info2.html\">お支払い・送料・返品</A>                ";
			categoryList = categoryList
					+ "            ｜<A target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c\">商品一覧</A>";
			categoryList = categoryList
					+ "            ｜<A target=\"_top\" href=\"https://basket.step.rakuten.co.jp/rms/mall/bs/cartempty/\">買い物かご</A>";
			categoryList = categoryList
					+ "            ｜<A href=\"mailto:coverforefront@shop.rakuten.co.jp\">お問い合わせ</A>";
			categoryList = categoryList
					+ "            ｜<A target=\"_blank\" href=\"https://www.rakuten.co.jp/coverforefront/news.html\">メルマガ</A>";
			categoryList = categoryList
					+ "            ｜<A target=\"_blank\" href=\"https://www.rakuten.co.jp/\">楽天市場</A>｜";
			categoryList = categoryList + "            </td>";
			categoryList = categoryList + "        </tr>";
			categoryList = categoryList + "    </table>";
			categoryList = categoryList + "    <ul id=\"navigation\" style=\"width:1050px;margin:0 auto;\">";
			categoryList = categoryList
					+ "    <li onMouseOver=\"displaySubMenu(this)\" onMouseOut=\"hideSubMenu(this)\"> ";
			categoryList = categoryList
					+ "        <a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000476/\">NEW</a> ";
			categoryList = categoryList + "        <!--<ul> ";
			categoryList = categoryList + "            ";
			categoryList = categoryList
					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000129/\">ワンピース</a></li> ";
			categoryList = categoryList
					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000130/\">レギンス</a></li>";
			categoryList = categoryList
					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000131/\">ジャケット</a></li>";
			categoryList = categoryList
					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000132/\">デニムジャケット</a></li>";
			categoryList = categoryList
					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000133/\">スカート</a></li>";
			categoryList = categoryList
					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000134/\">Tシャツ</a></li>";
			categoryList = categoryList
					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000135/\">タンクトップ</a></li>";
			categoryList = categoryList
					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000136/\">アクセサリー</a></li>";
			categoryList = categoryList + "        </ul>-->";
			categoryList = categoryList + "    </li> ";
			categoryList = categoryList
					+ "    <li onMouseOver=\"displaySubMenu(this)\" onMouseOut=\"hideSubMenu(this)\"> ";
			categoryList = categoryList
					+ "        <a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000143/\">ONE PIECE</a> ";
//			categoryList = categoryList + "        <ul> ";
//			categoryList = categoryList
//					+ "        　　<li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000155/\">春ワンピ</a></li>";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000156/\">夏ワンピ</a></li> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000157/\">プリンセス系</a></li>";
//			categoryList = categoryList + "        </ul> ";
			categoryList = categoryList + "    </li> ";
			categoryList = categoryList
					+ "    <li onMouseOver=\"displaySubMenu(this)\" onMouseOut=\"hideSubMenu(this)\"> ";
			categoryList = categoryList
					+ "        <a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000144/\">TOPS</a> ";
//			categoryList = categoryList + "        <ul> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000158/\">タンクトップ</a></li> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000159/\">ブラウス</a></li> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000160/\">カーディガン</a></li> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000161/\">Tシャツ</a></li> ";
//			categoryList = categoryList + "        </ul> ";
			categoryList = categoryList + "    </li> ";
			categoryList = categoryList
					+ "     <li onMouseOver=\"displaySubMenu(this)\" onMouseOut=\"hideSubMenu(this)\"> ";
			categoryList = categoryList
					+ "        <a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000145/\">BOTTOM</a> ";
//			categoryList = categoryList + "        <ul> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000163/\">パンツ</a></li> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000164/\">レギンス</a></li> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000165/\">スカート</a></li> ";
//			categoryList = categoryList + "        </ul> ";
			categoryList = categoryList + "    </li>";
			categoryList = categoryList
					+ "    <li onMouseOver=\"displaySubMenu(this)\" onMouseOut=\"hideSubMenu(this)\"> ";
			categoryList = categoryList
					+ "        <a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000142/\">DRESS</a> ";
//			categoryList = categoryList + "        <ul> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000152/\">パーティードレス</a></li> ";
//			categoryList = categoryList + "        </ul> ";
			categoryList = categoryList + "    </li>";
			categoryList = categoryList
					+ "    <li onMouseOver=\"displaySubMenu(this)\" onMouseOut=\"hideSubMenu(this)\"> ";
			categoryList = categoryList
					+ "        <a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000148/\">SWIMWEAR</a> ";
			categoryList = categoryList + "    </li>";
			categoryList = categoryList
					+ "    <li onMouseOver=\"displaySubMenu(this)\" onMouseOut=\"hideSubMenu(this)\"> ";
			categoryList = categoryList
					+ "        <a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000123/\">IPHONE</a> ";
//			categoryList = categoryList + "        <ul> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000111/\">iPhone6ケース</a></li> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000112/\">iPhone5S/5ケース</a></li> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000113/\">iPhone4S/4ケース</a></li> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000114/\">iPhone5Cケース</a></li> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000100/\">GALAXYケース</a></li> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000101/\">iPadケース</a></li> ";
//			categoryList = categoryList
//					+ "            <li><a target=\"_top\" href=\"https://item.rakuten.co.jp/coverforefront/c/0000000102/\">アクセサリー</a></li>";
//			categoryList = categoryList + "        </ul> ";
			categoryList = categoryList + "    </li>";
			categoryList = categoryList + "    </ul>";
		}
		for (Item item : shoriItemList) {
			if (Utility.isEmptyString(commArr)) {
				if ("trend777".equals(shop) && "sjpj083".equals(item.getItemUrl())) {
					item.setItemUrl("salesjpj083");
				}
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

		for (Item newitem : newItemList) {
			if (Utility.isEmptyString(new_commArr)) {
				new_commArr = (new_commArr + "\"" + newitem.getItemUrl() + "\"");
			} else {
				new_commArr = (new_commArr + ",\"" + newitem.getItemUrl() + "\"");
			}
			if (Utility.isEmptyString(new_souryou)) {
				new_souryou = (new_souryou + "\"" + (newitem.isIncludedPostage() ? "1" : "0") + "\"");
			} else {
				new_souryou = (new_souryou + ",\"" + (newitem.isIncludedPostage() ? "1" : "0") + "\"");
			}
			if (Utility.isEmptyString(new_commArrkakaku)) {
				new_commArrkakaku = (new_commArrkakaku + "\""
						+ Utility.numberFormatNoen(String.valueOf(newitem.getItemPrice())) + "\"");
			} else {
				new_commArrkakaku = (new_commArrkakaku + ",\""
						+ Utility.numberFormatNoen(String.valueOf(newitem.getItemPrice())) + "\"");
			}
			String url = newitem.getImages().getImage().get(0).getImageUrl();
			url = url.replace(("https://image.rakuten.co.jp/" + shop + "/cabinet/"), "");
			if (Utility.isEmptyString(new_commArrurl)) {
				new_commArrurl = (new_commArrurl + "\"" + url + "\"");
			} else {
				new_commArrurl = (new_commArrurl + ",\"" + url + "\"");
			}
		}

		File file = new File("C:\\pages\\top.html");
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

		shoriDiv = shoriDiv.replace("#{new_commArr}", new_commArr);
		shoriDiv = shoriDiv.replace("#{new_souryou}", new_souryou);
		shoriDiv = shoriDiv.replace("#{new_commArrkakaku}", new_commArrkakaku);
		shoriDiv = shoriDiv.replace("#{new_commArrurl}", new_commArrurl);

		shoriDiv = shoriDiv.replace("#{categoryList}", categoryList);
		shoriDiv = shoriDiv.replace("#{shop}", shop);

		try {
			File f = new File("C:\\temp\\top_" + shop + ".html");
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
		remote = "top.html";
		local = ("C:\\temp\\top_" + shop + ".html");
		String[] argggg = new String[] { "-s", host, user, pwd, remote, local };
//		FtpUtils.ftpUtils(argggg);
	}
}
