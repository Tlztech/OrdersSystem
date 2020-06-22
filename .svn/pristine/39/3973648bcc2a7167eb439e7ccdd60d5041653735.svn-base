package shohinmodel;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class kiquality {

	public static void main(String[] args) throws Exception {
		List<String[]> dataList = Utility.readCsvFile(new File("d:\\select20160220193343.csv"), true);
		List<String[]> resultList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  tbl00012 where concat(commodity_id,detail_no) = ?";
			ps = conn.prepareStatement(sql);

			for (String[] data : dataList) {
				ps.setString(1, data[1].replace("ki", "") + data[6] + data[8]);
				rs = ps.executeQuery();
				System.out.println(data[1] + data[6] + data[8]);
				if (rs.next()) {
					data[10] = rs.getString("stock_jp");
					// resultList.add(new String[] { data[1], data[6], data[8],
					// rs.getString("stock_jp") });
					resultList.add(data);
				} else {
					data[10] = "0";
					// resultList.add(new String[] { data[1], data[6], data[8],
					// rs.getString("stock_jp") });
					resultList.add(data);
				}

			}

			Utility.writeCsvFile(resultList, "d:\\kiresult.csv");

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

}
