package com.rakuten.r0302.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.r0302.bean.AddCommodityApInput;
import com.rakuten.r0302.bean.CommodityDetail;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;
import com.rakuten.util.Utility;

public class AddCommodityAp {

	public void execute(AddCommodityApInput input) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String shoriMode = input.getShoriMode();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = null;
		String date = format.format(new Date());
		List<CommodityDetail> commodityDetailList = input.getCommodityDetailList();
		try {
			Map<String, Object> map = ActionContext.getContext().getSession();
			int companyId;
			if (null == map.get("comp")) {
				companyId = -1;
			} else {
				companyId = (int) map.get("comp");
			}
			conn = JdbcConnection.getConnection();

			if ("2".equals(shoriMode)) {
				sql = SqlUtility.getSql("SQLR0001015");
				ps = conn.prepareStatement(sql);
				ps.setString(1, input.getCommodityId());
				ps.execute();

				sql = SqlUtility.getSql("SQLR0001016");
				ps = conn.prepareStatement(sql);
				ps.setString(1, input.getCommodityId());
				ps.execute();

			} else {
				sql = "INSERT INTO `rakuten`.`company_commodity_tbl` (`company_id`,`commodity_id`,`permission`) VALUES (?,?,?);";
				ps.setInt(1, companyId);
				ps.setString(2, input.getCommodityId());
				ps.setInt(3, 3);
				ps.executeUpdate();
			}

			sql = SqlUtility.getSql("SQLR0001011");
			ps = conn.prepareStatement(sql);
			ps.setString(1, input.getCommodityId());
			ps.setString(2, input.getCategoryId());
			ps.setString(3, input.getChineseName());
			ps.setString(4, input.getJapaneseName());
			ps.setString(5, input.getSource());
			ps.setString(6, input.getRespPerson());
			ps.setString(7, input.getCommodityUrl());
			ps.setString(8, input.getPicUrl());
			ps.setString(9, input.getRemarks());
			ps.setString(10, "0");
			ps.setString(11, date);
			ps.setString(12, Utility.getUser());
			ps.setString(13, date);
			ps.setString(14, Utility.getUser());
			ps.setString(15, "00");
			ps.execute();

			sql = SqlUtility.getSql("SQLR0001012");
			if (!Utility.isEmptyList(commodityDetailList)) {
				ps = conn.prepareStatement(sql);
				for (CommodityDetail detail : commodityDetailList) {
					ps.setString(1, input.getCommodityId());
					ps.setString(2, detail.getDetailNo());
					ps.setString(3, detail.getDescribe());
					ps.setString(4, detail.getPicUrl());
					ps.setString(5, "".equals(input.getPriceIn()) ? null : input.getPriceIn());
					ps.setString(6, "".equals(input.getRePrice()) ? null : input.getRePrice());
					ps.setString(7, detail.getStockSh());
					ps.setString(8, detail.getStockJp());
					ps.setString(9, detail.getStockHandup());
					ps.setString(10, detail.getRemarks());
					ps.setString(11, detail.getBarCode());
					ps.setString(12, "");
					ps.setString(13, detail.isDelFlg() ? "1" : "0");
					ps.setString(14, date);
					ps.setString(15, Utility.getUser());
					ps.setString(16, date);
					ps.setString(17, Utility.getUser());
					ps.setString(18, detail.getYokoname());
					ps.setString(19, detail.getShitaganame());
					ps.execute();
				}
			}

			ResultSet rs = null;
			String maxBarcode = null;
			if (!Utility.isEmptyList(commodityDetailList)) {
				for (CommodityDetail detail : commodityDetailList) {
					sql = "SELECT COUNT(*) COUNT FROM TBL00016 WHERE COMMODITY_ID = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, input.getCommodityId() + detail.getDetailNo());
					rs = ps.executeQuery();
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("COUNT");
					}
					if (count == 0) {
						sql = "SELECT MAX(BARCODE)+1 MAX_BARCODE FROM TBL00016";
						ps = conn.prepareStatement(sql);
						rs = ps.executeQuery();
						while (rs.next()) {
							maxBarcode = rs.getString("MAX_BARCODE");
						}

						sql = "INSERT INTO TBL00016 VALUES(?,?)";
						ps = conn.prepareStatement(sql);
						ps.setString(1, input.getCommodityId() + detail.getDetailNo());
						ps.setString(2, maxBarcode);
						ps.execute();
					}
				}

			}

			// commit
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
