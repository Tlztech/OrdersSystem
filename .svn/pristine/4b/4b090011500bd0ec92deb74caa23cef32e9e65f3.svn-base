package com.rakuten.r0502.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.r0502.bean.GetCommodityApInput;
import com.rakuten.r0502.bean.GetCommodityApOutput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;
import com.rakuten.util.Utility;

public class GetCommodityAp {

	public List<GetCommodityApOutput> execute(GetCommodityApInput input)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			List<GetCommodityApOutput> outputList = new ArrayList<GetCommodityApOutput>();
			GetCommodityApOutput output = null;
			conn = JdbcConnection.getConnection();
			String sql = SqlUtility.getSql("SQLR0001018");

			String commodityId = input.getCommodityId();
			String categoryId = input.getCategoryId();
			String chineseName = input.getChineseName();
			String japaneseName = input.getJapaneseName();
			String status = input.getStatus();

			if (!Utility.isEmptyString(status)) {
				if ("1".equals(status)) {
					sql += " AND T1.STATUS > '20' OR T1.STATUS = '10' ";
				} else if ("3".equals(status)) {
					sql += " AND T1.STATUS > '20'";
				} else {
					sql += " AND T1.STATUS = '" + status + "'";
				}
			}
			if (!Utility.isEmptyString(commodityId)) {
				sql += " AND T1.COMMODITY_ID = '" + commodityId + "'";
			}
			if (!Utility.isEmptyString(categoryId)) {
				sql += " AND T1.CATEGORY_ID = '" + categoryId + "'";
			}
			if (!Utility.isEmptyString(chineseName)) {
				sql += " AND T1.CHINESE_NAME LIKE  '%" + chineseName + "%'";
			}
			if (!Utility.isEmptyString(japaneseName)) {
				sql += " AND T1.JAPANESE_NAME LIKE  '%" + japaneseName + "%'";
			}

			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				output = new GetCommodityApOutput();
				output.setCategoryName(rs.getString("CATEGORY_NAME"));
				output.setChineseName(rs.getString("CHINESE_NAME"));
				output.setCommodityId(rs.getString("COMMODITY_ID"));
				output.setStatus(rs.getString("STATUS"));
				output.setCommodityUrl(rs.getString("COMMODITY_URL"));
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
