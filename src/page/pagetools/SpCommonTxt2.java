package page.pagetools;

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

public class SpCommonTxt2 {
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
		try {
			conn = JdbcConnection.getConnection();
			sql = "select  substring_index(shouhinbango,'-',1) shohinbango,sum(kosu) count from common_order_detail_tbl t1 left join common_order_tbl t2 on t1.JUCHUBANGO = t2.CHUMONBANGO where t2.CHUMONNICHIJI >=? and substring_index(shouhinbango,'-',1)<> 'nvsw008'  group by shohinbango order by count desc";
			ps = conn.prepareStatement(sql);

			ps.setString(1, chumonnichiji);
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinbangoList.add(rs.getString("shohinbango"));
			}
			if (shohinbangoList.size() > 16) {
				shohinbangoList = shohinbangoList.subList(0, 16);
			}
			shohinbangoList = new ArrayList<String>();

			shohinbangoList.add("nzwy258");
			shohinbangoList.add("nzwy259");
			shohinbangoList.add("nzwy260");
			shohinbangoList.add("nzwy261");
			shohinbangoList.add("nzwy262");
			shohinbangoList.add("nzwy263");
			shohinbangoList.add("nzwy264");
			shohinbangoList.add("nzwy265");
			shohinbangoList.add("nzwy266");
			shohinbangoList.add("nzwy267");
			shohinbangoList.add("nzwy268");
			shohinbangoList.add("nzctx1177");
			shohinbangoList.add("nzctx1178");
			shohinbangoList.add("nzctx1189");
			shohinbangoList.add("nzctx1190");
			shohinbangoList.add("nzctx1191");
			shohinbangoList.add("nzctx1192");
			shohinbangoList.add("nzctx1193");
			shohinbangoList.add("nzctx1194");
			shohinbangoList.add("nzctx1195");
			shohinbangoList.add("nzctx1196");
			shohinbangoList.add("nzctx1197");
			shohinbangoList.add("nzctx1198");
			shohinbangoList.add("nzks343");
			shohinbangoList.add("nzks344");
			shohinbangoList.add("nzks345");
			shohinbangoList.add("nzmy606");
			shohinbangoList.add("nzmy607");
			shohinbangoList.add("nzmy608");
				
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
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		Shohincommon common = new Shohincommon();
		String html = "<table cellspacing=1 cellpadding=1 width=100%>";

		int i = 1;
		for (String itemXml : itemXmlList) {
			Item item = common.getItemBean(itemXml);
			String url = item.getImages().getImage().get(0).getImageUrl();
			url = url.replace(("https://image.rakuten.co.jp/" + shop + "/cabinet/"), "");
			if (i == 1) {
				html = html + "<tr>";
			}
			html += ("<td width=50%><a href=https://item.rakuten.co.jp/" + shop + "/" + item.getItemUrl()
					+ "><img src=https://thumbnail.image.rakuten.co.jp/@0_mall/" + shop + "/cabinet/" + url
					+ "?_ex=168x168&s=0&r=1 width=98%></a>"
					+ Utility.numberFormatNoen(String.valueOf(item.getItemPrice())) + "円(送料"
					+ (item.isIncludedPostage() ? "込)</td>" : "別)</td>"));
			if (i == 2) {
				html = html + "</tr>";
				i--;
			} else {
				i++;
			}
		}
		html += "</table>";
		System.out.println(html);
	}
}
