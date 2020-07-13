package com.rakuten.r0302.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.r0302.bean.CommodityDetail;
import com.rakuten.r0302.bean.GetCommodityByIdApOutput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;
import com.rakuten.util.Utility;

public class GetCommodityByIdAp {

	public GetCommodityByIdApOutput execute(String commodityId)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		GetCommodityByIdApOutput output = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = SqlUtility.getSql("SQLR0001014");
			ps = conn.prepareStatement(sql);
			ps.setString(1, commodityId);
			ResultSet rs = ps.executeQuery();
			output = new GetCommodityByIdApOutput();
			List<CommodityDetail> CommodityDetailList = new ArrayList<CommodityDetail>();
			output.setCommodityDetailList(CommodityDetailList);
			CommodityDetail detail = null;
			while (rs.next()) {
				output.setCommodityId(rs.getString("COMMODITY_ID"));
				output.setCategoryId(rs.getString("CATEGORY_ID"));
				output.setCategoryName(rs.getString("CATEGORY_NAME"));
				output.setChineseName(rs.getString("CHINESE_NAME"));
				output.setJapaneseName(rs.getString("JAPANESE_NAME"));
				output.setPicUrl(rs.getString("PIC_URL"));
				output.setRemarks(rs.getString("REMARKS"));
				output.setSource(rs.getString("SOURCE"));
				output.setRespPerson(rs.getString("RESP_PERSON"));
				output.setCommodityUrl(rs.getString("COMMODITY_URL"));

				if (!Utility.isEmptyString(rs.getString("DETAIL_NO"))) {
					detail = new CommodityDetail();
					CommodityDetailList.add(detail);
					detail.setDetailNo(rs.getString("DETAIL_NO").replace("-0-0", ""));
					detail.setDescribe(rs.getString("COMM_DESCRIBE"));
					detail.setPicUrl(rs.getString("PIC_URL_T2"));
					detail.setPriceIn(rs.getString("PRICE_IN"));
					detail.setRePrice(rs.getString("RE_PRICE"));
					detail.setBarCode(rs.getString("BAR_CODE"));
					detail.setStockSh(rs.getString("STOCK_SH"));
					detail.setStockJp(rs.getString("STOCK_JP"));
					detail.setStockHandup(rs.getString("STOCK_HANDUP"));
					detail.setRemarks(rs.getString("REMARKS"));
					detail.setDelFlg("0".equals(rs.getString("T3.DEL_FLG")) ? false
							: true);
				}
			}
			// commit
			conn.commit();
			return output;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
