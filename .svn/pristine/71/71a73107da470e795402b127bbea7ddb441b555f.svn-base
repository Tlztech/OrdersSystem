package shohinmodel;

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

public class sdfsdfwere {
	public static void basicUpdate(String shop) throws Exception {

		List<String> todayList = new ArrayList<String>();
		todayList.add("nzmy086");
		todayList.add("nzmy088");
		todayList.add("nzmy089");
		todayList.add("nzmy090");
		todayList.add("nzmy091");
		todayList.add("nzmy092");
		todayList.add("nzwb011");
		todayList.add("nzwb012");
		todayList.add("nzwb020");
		todayList.add("nzwb021");
		todayList.add("nzwb022");
		todayList.add("nzwt235");
		todayList.add("nzwt234");
		todayList.add("nzwt233");
		todayList.add("nzwt232");
		todayList.add("nzwt227");
		todayList.add("nzwt226");
		todayList.add("nzwt225");
		todayList.add("nzwt220");
		todayList.add("nzddk121");
		todayList.add("nzddk128");
		todayList.add("nzctx170");
		todayList.add("nzctx171");
		todayList.add("nzctx172");
		todayList.add("nzddk132");
		todayList.add("nzddk133");
		todayList.add("nzddk134");
		todayList.add("nzddk136");
		todayList.add("nzwb023");
		todayList.add("sjpj083");
		todayList.add("nzctx066");
		todayList.add("sjpj138");
		todayList.add("nzk062");
		todayList.add("nzwt338");
		todayList.add("nzwt340");
		todayList.add("nzwt341");
		todayList.add("nzwt351");
		todayList.add("nzwt354");
		todayList.add("nzwt357");
		todayList.add("nzwt367");
		todayList.add("nzwt368");
		todayList.add("nzwt379");
		todayList.add("nzwt380");
		todayList.add("nzwy026");
		todayList.add("nzwy027");
		todayList.add("nzwy032");
		todayList.add("nzwy033");
		todayList.add("nzwy034");
		todayList.add("nzwy035");
		todayList.add("nzwy036");
		todayList.add("nzwy037");
		todayList.add("nzwy038");
		todayList.add("nzwy039");
		todayList.add("nzwy040");
		todayList.add("nzwy048");
		todayList.add("nzwy049");
		todayList.add("nzwy050");
		todayList.add("nzctx175");
		todayList.add("nzctx176");
		todayList.add("nzctx193");
		todayList.add("nzctx194");
		todayList.add("nzctx195");
		todayList.add("nzctx196");
		todayList.add("nzctx197");
		todayList.add("nzctx198");
		todayList.add("nzctx199");
		List<Item> todayItemList = new ArrayList<Item>();

		Shohincommon common = new Shohincommon();
		for (String shohinbango : todayList) {
			String shohinxml = common.getShohinXmlFromDB(shop, shohinbango);
			if (!Utility.isEmptyString(shohinxml)) {
				todayItemList.add(common.getItemBean(shohinxml));
			}
		}

		File file = null;
		if ("trend777".equals(shop)) {
			file = new File("D:\\pages\\24timesale_trend.html");
		} else if ("coverforefront".equals(shop)) {
			file = new File("D:\\pages\\24timesale_cover.html");
		}
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

		for (Item item : todayItemList) {
			String url = item.getImages().getImage().get(0).getImageUrl();
			String shohinname = item.getItemName();
			if (item.getItemName().getBytes().length > 20) {
				shohinname = (shohinname.substring(0, 20) + "...");
			}
			url = (url.replace("http://image.rakuten.co.jp/",
					"http://thumbnail.image.rakuten.co.jp/@0_mall/") + "?_ex=220x220&s=2&r=1");
			shoriDiv = shoriDiv.replaceFirst("&\\{itemurl}", item.getItemUrl());
			shoriDiv = shoriDiv.replaceFirst("&\\{tujokakaku}", Utility
					.numberFormatNoen(String.valueOf(item.getDisplayPrice())));
			shoriDiv = shoriDiv.replaceFirst("&\\{hanbaikakaku}", Utility
					.numberFormatNoen(String.valueOf(item.getItemPrice())));
			shoriDiv = shoriDiv.replaceFirst("&\\{img}", url);
			shoriDiv = shoriDiv.replaceFirst("&\\{shohinname}", shohinname);
		}

		try {
			File f = new File("D:\\temp\\24timesale_" + shop + ".html");
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

	}

}
