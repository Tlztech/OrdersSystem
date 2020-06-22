package shohinmodel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shohinmodel.common.Shohincommon;

import com.rakuten.util.JdbcConnection;

public class test {

	public static void main(String[] args) throws Exception {
		// Connection conn = null;
		// PreparedStatement ps = null;
		// String sql = null;
		// ResultSet rs = null;
		// List<String> shohinbangoList = new ArrayList<String>();
		// try {
		// conn = JdbcConnection.getConnection();
		// sql = "SELECT commodity_id from tbl00011";
		// ps = conn.prepareStatement(sql);
		//
		// rs = ps.executeQuery();
		// while (rs.next()) {
		// shohinbangoList.add(rs.getString("commodity_id"));
		// }
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// conn.rollback();
		// throw e;
		// } finally {
		// conn.close();
		// }
		// Shohincommon common = new Shohincommon();
		// for (String shohinbango : shohinbangoList) {
		// System.out.println(shohinbango);
		// common.downloadItem("trend777", shohinbango, "1");
		// }

		dodownload();
		// sdfsdfwere.basicUpdate("coverforefront");
		// Shohincommon common = new Shohincommon();

		// Shohincommon common = new Shohincommon();
		// String itemxml = common.getItemXml("trend777", "nzwt198");
		// Item item = common.getItemBean(itemxml);
		// String updatexml = common.convertBeanToXml(item);
		// URL url = new
		// URL("https://api.rms.rakuten.co.jp/es/1.0/item/update");
		// String serviceSecret = "SP306685_TxVVl5qWQeMh6bPK";
		// String licenseKey = "SL306685_SgpPqimoNd8eJ7xd";
		// HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		// String author = "ESA "
		// + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		// // conn.setRequestProperty("contentType", "UTF-8");
		// conn.setRequestProperty("Authorization", author);
		// conn.setDoOutput(true);
		// conn.setRequestProperty("Pragma:", "no-cache");
		// conn.setRequestProperty("Cache-Control", "no-cache");
		// conn.setRequestProperty("Content-Type", "text/xml");
		//
		// // 得到请求的输出流对象
		// OutputStreamWriter out = new
		// OutputStreamWriter(conn.getOutputStream());
		// // 把数据写入请求的Body
		// out.write(new String(updatexml.getBytes("UTF-8")));
		// // out.write(("<request><itemUpdateRequest>"
		// // + new String(updatexml.getBytes("UTF-8")) +
		// // "</itemUpdateRequest>\r\n</request>")
		// // .replace("&lt;", "<")
		// // .replace("&gt;", ">")
		// // .replace(
		// //
		// "<request><itemUpdateRequest><?xml version=\"1.0\" encoding=\"UTF-8\"
		// standalone=\"yes\"?>",
		// //
		// "<?xml version=\"1.0\"
		// encoding=\"UTF-8\">\r\n<request>\r\n<itemUpdateRequest>"));
		// System.out.println(new String(updatexml.getBytes("UTF0-8")));
		// out.flush();
		// out.close();
		//
		// BufferedReader in = new BufferedReader(new InputStreamReader(
		// conn.getInputStream()));
		// String line = null;
		// StringBuffer sb1 = new StringBuffer();
		// while ((line = in.readLine()) != null) {
		// sb1.append(line);
		// }
		// in.close();
		// System.out.println(sb1.toString());

	}

	private static void dodownload() {
		Shohincommon common = new Shohincommon();
		try {
			common.downloadShohin("");
		} catch (Exception e) {
			dodownload();
		}

	}
}
