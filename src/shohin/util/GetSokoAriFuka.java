package shohin.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.JdbcConnection;

public class GetSokoAriFuka {

	public static void main(String[] args) throws Exception {

		List<String> shoriList = new ArrayList<String>();
		List<String> shangjiaList = new ArrayList<String>();
		// 获得有库存但是已下架且如何不可

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  tbl00012 where  stock_jp > 0 and del_flg = '1'";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				shoriList.add(rs.getString("commodity_id"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		for (String shohinbango : shoriList) {

			boolean ariFlg2 = false;
			for (String shangjia : shangjiaList) {
				if (shangjia.equals(shohinbango)) {
					ariFlg2 = true;
				}
			}
			if (!ariFlg2) {
				shangjiaList.add(shohinbango);
			}

		}
		for (String shangjia : shangjiaList) {
			System.out.println(shangjia);
		}
	}

}
