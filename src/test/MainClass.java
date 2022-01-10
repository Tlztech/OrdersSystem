package test;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import shohinmodel.bean.Item;
import shohinmodel.common.Shohincommon;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class MainClass {

	public static void main(String[] args) throws Exception {
		List<String[]> dataList = Utility.readCsvFileJpn(new File(
				"D:\\data201601131512.csv"), true);
		List<String[]> shorilist = new ArrayList<String[]>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  shohinxml_tbl where itemurl = ?";
			ps = conn.prepareStatement(sql);
			Shohincommon common = new Shohincommon();
			for (String[] data : dataList) {
				String itemxml = "";
				ps.setString(1, data[2]);
				rs = ps.executeQuery();
				while (rs.next()) {
					itemxml = rs.getString("shohinxml");
				}
				Item item = common.getItemBean(itemxml);
				if (item.isIncludedPostage()) {
					data[5] = "1";
				} else {
					data[5] = "0";
				}
				shorilist.add(data);
			}
			Utility.writeCsvFile(shorilist, "D:\\123mart.csv");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
