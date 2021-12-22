package com.rakuten.r0302.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.r0302.bean.GetCommodityApInput;
import com.rakuten.r0302.bean.GetCommodityApOutput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;
import com.rakuten.util.Utility;

public class GetCommodityAp {

	public List<GetCommodityApOutput> execute(GetCommodityApInput input)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Map<String,Object> map =  ActionContext.getContext().getSession();
			int companyId;
			if (null == map.get("comp")) {
				companyId = -1;
			} else {
				companyId = (int)map.get("comp");
			}
			List<GetCommodityApOutput> outputList = new ArrayList<GetCommodityApOutput>();
			GetCommodityApOutput output = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT T2.CATEGORY_NAME, T1.CHINESE_NAME,T1.COMMODITY_ID, " + 
					"              T1.JAPANESE_NAME, T1.PIC_URL, SUM(T3.STOCK_SH) STOCK_SH, " + 
					"              SUM(T3.STOCK_JP) STOCK_JP , SUM(T3.STOCK_HANDUP) STOCK_HANDUP " + 
					"              FROM TBL00011 T1 LEFT JOIN TBL00010 T2 ON T1.CATEGORY_ID = " + 
					"              T2.CATEGORY_ID LEFT JOIN TBL00012 T3 ON T1.COMMODITY_ID = " + 
					"              T3.COMMODITY_ID WHERE T1.DEL_FLG = '0'";

			String commodityId = input.getCommodityId();
			String categoryId = input.getCategoryId();
			String chineseName = input.getChineseName();
			String japaneseName = input.getJapaneseName();
			String stockShStart = input.getStockShStart();
			String stockJpStart = input.getStockJpStart();
			String stockShEnd = input.getStockShEnd();
			String stockJpEnd = input.getStockJpEnd();
			if (Utility.isEmptyString(stockShStart)) {
				stockShStart = "0";
			}
			if (Utility.isEmptyString(stockJpStart)) {
				stockJpStart = "0";
			}
			if (Utility.isEmptyString(stockShEnd)) {
				stockShEnd = "9999";
			}
			if (Utility.isEmptyString(stockJpEnd)) {
				stockJpEnd = "9999";
			}

			if (!Utility.isEmptyString(commodityId)) {
				sql += " AND T1.COMMODITY_ID IN (select commodity_id from company_commodity_tbl where commodity_id like '"+ commodityId + "%' AND (COMPANY_ID = "+companyId+" OR "+companyId+" = 0 OR "+companyId+" = 1))";
			} else {
				sql += " AND T1.COMMODITY_ID IN (select commodity_id from company_commodity_tbl where (COMPANY_ID = "+companyId+" OR "+companyId+" = 0 OR "+companyId+" = 1))";
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

//			sql += " AND STOCK_SH >= " + stockShStart;
//			sql += " AND STOCK_SH <= " + stockShEnd;
			sql += " AND STOCK_JP >= " + stockJpStart;
			sql += " AND STOCK_JP <= " + stockJpEnd;

			sql += " GROUP BY T1.COMMODITY_ID";

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
