package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.form.CommodityList;
import com.rakuten.util.JdbcConnection;

public class A06010106Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String waybillNo = null;
	private List<CommodityList> commodityList = null;

	protected void exec() throws Exception {
		@SuppressWarnings("unchecked")
		List<CommodityList> commodityList_temp = (List<CommodityList>) getSessionAttribute("commodityList");

		for (int i = 0; i < commodityList.size(); i++) {
			for (int j = 0; j < commodityList_temp.size(); j++) {
				if (commodityList.get(i).getCommodityId()
						.equals(commodityList_temp.get(j).getCommodityId())) {
					commodityList_temp.get(j).setGetSu(
							commodityList.get(i).getGetSu());
				}
			}
		}

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			String date = df.format(new Date());
			String sql = "UPDATE TBL00013 SET STATUS = ? , RECEIVE_DAY = ? WHERE WAYBILL_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "10");
			ps.setString(2, date);
			ps.setString(3, this.waybillNo);
			ps.executeUpdate();

			for (int i = 0; i < commodityList_temp.size(); i++) {
				CommodityList commodity = (CommodityList) commodityList_temp
						.get(i);
				String commodityId = commodity.getCommodityId();
				String getSu = commodity.getGetSu();
				String remarks = commodity.getRemarks();

				sql = "UPDATE TBL00014 SET REMARKS = ?, DEL_FLG = ? WHERE WAYBILL_NO = ? AND COMMODITY_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, remarks);
				ps.setString(2, "1");
				ps.setString(3, this.waybillNo);
				ps.setString(4, commodityId);
				ps.executeUpdate();

				String commodityId1 = "";

				String detailNo1 = "";

				if (commodityId.contains("-")) {
					commodityId1 = commodityId.substring(0,
							commodityId.indexOf("-"));
					detailNo1 = commodityId.substring(commodityId.indexOf("-"));
				} else {
					commodityId1 = commodityId;
				}

				sql = "UPDATE TBL00012 SET STOCK_JP = STOCK_JP + ?, UPDATEQUANTITY_FLG =TRUE WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, getSu);
				ps.setString(2, commodityId1);
				ps.setString(3, detailNo1);
				ps.executeUpdate();

				sql = "INSERT INTO TBL00017 VALUES(?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, this.waybillNo);
				ps.setString(2, commodityId);
				ps.setString(3, getSu);
				ps.executeUpdate();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	protected void init() {
		setTitle("V060104:签收");
	}

	protected void isValidated() throws Exception {
	}

	protected void fieldCheck() throws Exception {
	}

	public String getWaybillNo() {
		return this.waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	/**
	 * @return the commodityList
	 */
	public List<CommodityList> getCommodityList() {
		return commodityList;
	}

	/**
	 * @param commodityList
	 *            the commodityList to set
	 */
	public void setCommodityList(List<CommodityList> commodityList) {
		this.commodityList = commodityList;
	}

}