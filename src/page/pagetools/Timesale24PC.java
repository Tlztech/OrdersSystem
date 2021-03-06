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

import shohinmodel.bean.Item;
import shohinmodel.common.Shohincommon;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class Timesale24PC {
	public void basicUpdate(String shop) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();

		String today = sdf.format(date.getTime());
		String startdatatoday = (sdf2.format(date.getTime()) + " 00:00:00 ~ "
				+ sdf2.format(date.getTime()) + " 23:59:59");

		date.add(Calendar.DATE, 1);

		String tomorrow = sdf.format(date.getTime());
		String startdatatomorrow = (sdf2.format(date.getTime())
				+ " 00:00:00 ~ " + sdf2.format(date.getTime()) + " 23:59:59");

		List<String> todayList = getShohinList(shop, today);
		List<String> tomorrowList = getShohinList(shop, tomorrow);

		List<Item> todayItemList = new ArrayList<Item>();
		List<Item> tomorrowItemList = new ArrayList<Item>();
		Shohincommon common = new Shohincommon();
		for (String shohinbango : todayList) {
			String shohinxml = common.getShohinXmlFromDB(shop, shohinbango);
			if (!Utility.isEmptyString(shohinxml)) {
				todayItemList.add(common.getItemBean(shohinxml));
			}
		}
		for (String shohinbango : tomorrowList) {
			String shohinxml = common.getShohinXmlFromDB(shop, shohinbango);
			if (!Utility.isEmptyString(shohinxml)) {
				tomorrowItemList.add(common.getItemBean(shohinxml));
			}
		}

		File file = new File("C:\\pages\\24timesale_pc.html");

		InputStream is = new FileInputStream(file);

		BufferedReader bf = new BufferedReader(new InputStreamReader(is,
				"euc-jp"));
		// 最好在将字节流转换为字符流的时候 进行转码
		StringBuffer buffer = new StringBuffer();
		String line = "";
		while ((line = bf.readLine()) != null) {
			buffer.append(line + "\r\n");
		}
		bf.close();

		String shoriDiv = buffer.toString();
		shoriDiv = shoriDiv.replace("&{shop}", shop);
		shoriDiv = shoriDiv.replace("&{startdatatoday}", startdatatoday);
		shoriDiv = shoriDiv.replace("&{startdatatomorrow}", startdatatomorrow);

		for (Item item : todayItemList) {
			String url = item.getImages().getImage().get(0).getImageUrl();
			String shohinname = item.getItemName();
			if (item.getItemName().getBytes().length > 20) {
				shohinname = (shohinname.substring(0, 20) + "...");
			}
			url = (url.replace("https://image.rakuten.co.jp/",
					"https://thumbnail.image.rakuten.co.jp/@0_mall/") + "?_ex=220x220&s=2&r=1");
			shoriDiv = shoriDiv.replaceFirst("&\\{itemurl}", item.getItemUrl());
			shoriDiv = shoriDiv.replaceFirst("&\\{tujokakaku}", Utility
					.numberFormatNoen(String.valueOf(item.getItemPrice())));
			shoriDiv = shoriDiv.replaceFirst("&\\{hanbaikakaku}", Utility
					.numberFormatNoen(String.valueOf(item.getItemPrice() / 2)));
			shoriDiv = shoriDiv.replaceFirst("&\\{img}", url);
			shoriDiv = shoriDiv.replaceFirst("&\\{shohinname}", shohinname);
		}

		for (Item item : tomorrowItemList) {
			String url = item.getImages().getImage().get(0).getImageUrl();
			String shohinname = item.getItemName();
			if (item.getItemName().getBytes().length > 20) {
				shohinname = (shohinname.substring(0, 20) + "...");
			}
			url = (url.replace("https://image.rakuten.co.jp/",
					"https://thumbnail.image.rakuten.co.jp/@0_mall/") + "?_ex=220x220&s=2&r=1");
			shoriDiv = shoriDiv.replaceFirst("&\\{to_itemurl}",
					item.getItemUrl());
			shoriDiv = shoriDiv.replaceFirst("&\\{to_tujokakaku}", Utility
					.numberFormatNoen(String.valueOf(item.getItemPrice())));
			shoriDiv = shoriDiv.replaceFirst("&\\{to_hanbaikakaku}", Utility
					.numberFormatNoen(String.valueOf(item.getItemPrice() / 2)));
			shoriDiv = shoriDiv.replaceFirst("&\\{to_img}", url);
			shoriDiv = shoriDiv.replaceFirst("&\\{to_shohinname}", shohinname);
		}

		try {
			File f = new File("C:\\temp\\24timesale_" + shop + "_pc.html");
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(
					new FileOutputStream(f), "euc-jp");
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
		remote = "24timesale_pc.html";
		local = ("C:\\temp\\24timesale_" + shop + "_pc.html");
		String[] argggg = new String[] { "-s", host, user, pwd, remote, local };
		FtpUtils.ftpUtils(argggg);
	}

	private List<String> getShohinList(String shop, String startdata)
			throws Exception {
		List<String> shohinList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM event_tbl where shop = ? and startdata = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shop);
			ps.setString(2, startdata);
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinList.add(rs.getString("shohinbango"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return shohinList;
	}

}
