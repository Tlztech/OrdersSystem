package com.rakuten.r0601.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.r0601.bean.GetWayBillApDetailOutput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class GetWayBillDetailByIdAp {

	public List<GetWayBillApDetailOutput> execute(String waybillNo)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();

			sql = SqlUtility.getSql("SQLR0001025");
			ps = conn.prepareStatement(sql);
			ps.setString(1, waybillNo);
			ResultSet rs = ps.executeQuery();

			List<GetWayBillApDetailOutput> outputList = new ArrayList<GetWayBillApDetailOutput>();
			GetWayBillApDetailOutput output = null;
			while (rs.next()) {
				output = new GetWayBillApDetailOutput();
				outputList.add(output);
				output.setCommodityId(rs.getString("COMMODITY_ID"));
				output.setQuantity(rs.getString("QUANTITY"));
				output.setRemarks(rs.getString("REMARKS"));
				output.setDelFlg(rs.getString("DEL_FLG"));
			}

			// commit
//			conn.commit();
			return outputList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
