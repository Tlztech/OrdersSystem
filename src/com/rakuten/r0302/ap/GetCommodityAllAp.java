package com.rakuten.r0302.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.r0302.bean.GetCommodityApOutput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class GetCommodityAllAp {

	public List<GetCommodityApOutput> execute() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			List<GetCommodityApOutput> outputList = new ArrayList<GetCommodityApOutput>();
			GetCommodityApOutput output = null;
			conn = JdbcConnection.getConnection();
			String sql = SqlUtility.getSql("SQLR0001013");
			sql+=" GROUP BY T1.COMMODITY_ID";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				output = new GetCommodityApOutput();
				output.setCategoryName(rs.getString("CATEGORY_NAME"));
				output.setChineseName(rs.getString("CHINESE_NAME"));
				output.setCommodityId(rs.getString("COMMODITY_ID"));
				output.setJapaneseName(rs.getString("JAPANESE_NAME"));
				output.setPicUrl(rs.getString("PIC_URL"));
				output.setStockSh(rs.getString("STOCK_SH"));
				output.setStockJp(rs.getString("STOCK_JP"));
				output.setStockHandup(rs.getString("STOCK_HANDUP"));
				outputList.add(output);
			}
			// commit
			conn.commit();
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
