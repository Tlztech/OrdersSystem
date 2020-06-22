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

public class Supersale_sp {

	public void basicUpdate(String shop) throws Exception {
		String[] List50 = new String[] { "nzctx646", "nzctx651", "nzctx665", "nzddk110", "nzddk172", "nzddk182",
				"nzsy021", "nzxdk042", "sjpj035", "sjpj138", "xbx002", "xbx032" };

		String[] List10 = new String[] {  };
		List<String> shoriList = new ArrayList<String>();
		for (String shohinbango : List50) {
			shoriList.add(shohinbango);
		}
		for (String shohinbango : List10) {
			shoriList.add(shohinbango);
		}
		List<String> itemXmlList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  shohinxml_tbl where shop = ? and (1<>1";
			for (String shohinbango : shoriList) {
				sql += " or itemurl = '" + shohinbango + "' ";
			}
			sql += ")";
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

		File file = new File("D:\\pages\\supersale.html");
		InputStream is = new FileInputStream(file);

		BufferedReader bf = new BufferedReader(new InputStreamReader(is, "euc-jp"));
		// 最好在将字节流转换为字符流的时候 进行转码
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = bf.readLine()) != null) {
			buffer.append(line + "\r\n");
		}
		bf.close();

		Shohincommon common = new Shohincommon();
		String a_xml = getAxml();
		String shori_xml = a_xml;
		String shoriDiv = buffer.toString();
		for (String shohinbango : shoriList) {

			for (String itemxml : itemXmlList) {

				if (itemxml.contains(shohinbango)) {
					System.out.println(shohinbango);
					Item item = common.getItemBean(itemxml);
					if (shori_xml.indexOf("ddpegitemurl") < 0) {
						shori_xml = shori_xml + a_xml;
					}

					String picurl = (item.getImages().getImage().get(0).getImageUrl().replace(
							"https://image.rakuten.co.jp/coverforefront/cabinet/",
							"https://thumbnail.image.rakuten.co.jp/@0_mall/coverforefront/cabinet/")
							+ "?_ex=220x220&s=2&r=1");
					String itemurl = ("https://item.rakuten.co.jp/coverforefront/" + item.getItemUrl());
					String name = (item.getItemName().substring(0, 30) + "...");
					String price1 = Utility.numberFormat(String.valueOf(item.getDisplayPrice()));
					String price2 = Utility.numberFormat(String.valueOf(item.getItemPrice()));

					shori_xml = shori_xml.replaceFirst("ddpegpicurl", picurl);
					shori_xml = shori_xml.replaceFirst("ddpegitemurl", itemurl);
					shori_xml = shori_xml.replaceFirst("ddpegname", name);
					shori_xml = shori_xml.replaceFirst("ddpegprice1", price1);
					shori_xml = shori_xml.replaceFirst("ddpegprice2", price2);
				}
			}
		}
		while (shori_xml.indexOf("ddpegitemurl") > 0) {
			shori_xml = shori_xml.replaceFirst("ddpegpicurl", "");
			shori_xml = shori_xml.replaceFirst("ddpegitemurl", "");
			shori_xml = shori_xml.replaceFirst("ddpegname", "");
			shori_xml = shori_xml.replaceFirst("ddpegprice1", "");
			shori_xml = shori_xml.replaceFirst("ddpegprice2", "");
		}
		shoriDiv = shoriDiv.replace("ddpegnaiyo", shori_xml);
		try {
			File f = new File("D:\\temp\\supersale_" + shop + ".html");
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

		// String host = "ftp.rakuten.ne.jp:16910";
		//
		// String user = "";
		// String pwd = "";
		// String local = "";
		// String remote = "";
		// if ("trend777".equals(shop)) {
		// FtpAccountBeanTrend777 accountTrend = new FtpAccountBeanTrend777();
		// user = accountTrend.getUser();
		// pwd = accountTrend.getPwd();
		// } else if ("coverforefront".equals(shop)) {
		// FtpAccountBeanCoverforefront accountTrend = new
		// FtpAccountBeanCoverforefront();
		// user = accountTrend.getUser();
		// pwd = accountTrend.getPwd();
		// }
		// remote = "osusume2.html";
		// local = ("D:\\temp\\osusume2_" + shop + ".html");
		// String[] argggg = new String[] { "-s", host, user, pwd, remote, local
		// };
		// FtpUtils.ftpUtils(argggg);
	}

	private String getAxml() {
		String a_xml = "";
		a_xml += "<table align=\"center\" cellspacing=\"1\" cellpadding=\"0\"  width=\"100%\">\n";
		a_xml += "    <tr>\n";
		a_xml += "        <td align=\"right\" width=\"33%\" style=\"background-image: url(ddpegpicurl); background-size:100%;background-repeat: no-repeat;background-position:center center\"><a href=\"ddpegitemurl;\"><img src=\"toumei.png\" border=\"0\" width=\"100%\"/></td>\n";
		a_xml += "        <td align=\"left\" width=\"33%\" style=\"background-image: url(ddpegpicurl) ;background-size:100%;background-repeat: no-repeat;background-position:center center\"><a href=\"ddpegitemurl;\"><img src=\"toumei.png\" border=\"0\" width=\"100%\"/></td>\n";
		a_xml += "        <td align=\"left\" width=\"33%\" style=\"background-image: url(ddpegpicurl) ;background-size:100%;background-repeat: no-repeat;background-position:center center\"><a href=\"ddpegitemurl;\"><img src=\"toumei.png\" border=\"0\" width=\"100%\"/></td>\n";
		a_xml += "    </tr>  \n";
		a_xml += "    <tr>\n";
		a_xml += "        <td style=\"border:1px solid ddpeg999\" height=\"40px\" align=\"center\" width=\"33%\" class=\"shm\"><img src=\"sale.png\" border=\"0\" width=\"10px\"/>ddpegname</td>\n";
		a_xml += "        <td style=\"border:1px solid ddpeg999\" align=\"center\" width=\"33%\" class=\"shm\"><img src=\"sale.png\" border=\"0\" width=\"10px\"/>ddpegname</td>\n";
		a_xml += "        <td style=\"border:1px solid ddpeg999\" align=\"center\" width=\"33%\" class=\"shm\"><img src=\"sale.png\" border=\"0\" width=\"10px\"/>ddpegname</td>\n";
		a_xml += "    </tr>\n";
		a_xml += "    <tr>\n";
		a_xml += "        <td  height=\"50px\" align=\"center\" width=\"33%\" class=\"price02\"><span class=\"price01\">当店通常価格:ddpegprice1</span><br />セール価格:<br/>ddpegprice2</td>\n";
		a_xml += "        <td  height=\"50px\" align=\"center\" width=\"33%\" class=\"price02\"><span class=\"price01\">当店通常価格:ddpegprice1</span><br />セール価格:<br/>ddpegprice2</td>\n";
		a_xml += "        <td  height=\"50px\" align=\"center\" width=\"33%\" class=\"price02\"><span class=\"price01\">当店通常価格:ddpegprice1</span><br />セール価格:<br/>ddpegprice2</td>\n";
		a_xml += "    </tr>\n";
		a_xml += "</table>\n";
		a_xml += "<br/>\n";
		return a_xml;
	}
}
