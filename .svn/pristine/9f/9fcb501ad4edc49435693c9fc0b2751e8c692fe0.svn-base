package shohinmodel;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class PonpareQuantity {

	public static void main(String[] args) throws Exception {
		List<String[]> dataList = Utility.readCsvFile(new File(
				"D:\\quantity201704270937.csv"), true);
		List<String[]> shoriList = new ArrayList<String[]>();
		shoriList.add(new String[] { "code", "sub-code", "quantity",
				"allow-overdraft" });
		List<String> delList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT concat(commodity_id,detail_no) shohinbango from  tbl00012 where del_flg = 1";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				delList.add(rs.getString("shohinbango"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		for (String[] data : dataList) {
			data[2] = "300";
			for (String shohinbango : delList) {
				if (data[1].equals(shohinbango)) {
					data[2] = "0";
					break;
				}
			}
			shoriList.add(data);
		}
		
		Utility.writeCsvFile(shoriList, "D:\\yahoo_quantity.csv");

	}
}
