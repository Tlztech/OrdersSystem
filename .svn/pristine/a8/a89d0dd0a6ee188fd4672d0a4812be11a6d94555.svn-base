package shohinmodel;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class YahooQuantity {

	public static void main(String[] args) throws Exception {
		List<String[]> dataList = Utility.readCsvFile(new File("D:\\option_20160721170605.csv"), true);
		List<String[]> shoriList = new ArrayList<String[]>();
		shoriList.add(new String[] { "コントロールカラム", "商品管理ID（商品URL）", "選択肢タイプ", "購入オプション名", "オプション項目名", "SKU横軸項目ID",
				"SKU横軸項目名", "SKU縦軸項目ID", "SKU縦軸項目名", "SKU在庫数" });
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
			data[0] = "u";
			data[9] = "300";
			for (String shohinbango : delList) {
				if ((data[1] + data[5] + data[7]).equals(shohinbango)) {
					data[9] = "0";
					break;
				}
			}
			shoriList.add(data);
		}

		Utility.writeCsvFile(shoriList, "D:\\ponpare_quantity.csv");

	}
}
