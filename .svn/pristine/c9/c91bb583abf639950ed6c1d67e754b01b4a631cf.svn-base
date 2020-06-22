package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.form.F060101;
import com.rakuten.r0601.form.WayBillInfo;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A06010111Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	F060101 f060101 = null;

	protected void exec() throws Exception {
		WayBillInfo info = f060101.getWayBillList().get(
				Integer.valueOf(getRowIndex()));
		deleteWaybill(info.getWaybillNo());

	}

	protected void init() {
		setTitle("V060101:发货管理");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f060101
	 */
	public F060101 getF060101() {
		return f060101;
	}

	/**
	 * @param f060101
	 *            the f060101 to set
	 */
	public void setF060101(F060101 f060101) {
		this.f060101 = f060101;
	}

	private void deleteWaybill(String waybillNo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String[]> resumeList = new ArrayList<String[]>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "UPDATE TBL00013 SET STATUS = '20' WHERE WAYBILL_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, waybillNo);
			ps.execute();

			sql = "SELECT COMMODITY_ID, QUANTITY FROM TBL00014 WHERE WAYBILL_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, waybillNo);
			rs = ps.executeQuery();

			String commodityId = null;
			String quantity = null;
			while (rs.next()) {
				commodityId = rs.getString("COMMODITY_ID");
				quantity = rs.getString("QUANTITY");
				resumeList.add(new String[] { commodityId, quantity });
			}

			sql = "UPDATE TBL00012 SET STOCK_SH = STOCK_SH + ? WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
			for (int i = 0; i < resumeList.size(); i++) {
				ps = conn.prepareStatement(sql);
				ps.setInt(1, Integer.valueOf(resumeList.get(i)[1]));
				ps.setString(2, Utility.getCommodityId(resumeList.get(i)[0]));
				ps.setString(3, Utility.getDetailN0(resumeList.get(i)[0]));
				ps.executeUpdate();
			}

			conn.commit();
			f060101.setPassFlg("2");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
