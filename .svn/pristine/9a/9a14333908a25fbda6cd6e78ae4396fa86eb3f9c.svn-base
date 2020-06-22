package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.GetWayBillDetailByIdAp;
import com.rakuten.r0601.bean.GetWayBillApDetailOutput;
import com.rakuten.r0601.form.CommodityList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A06010104Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String waybillNo = null;
	List<CommodityList> commodityList = null;
	private String count = null;
	private String sum = "0";
	private String getSum = "0";
	private String disabledFlg = "false";

	protected void exec() throws Exception {
		GetWayBillDetailByIdAp getWayBillDetailByIdAp = new GetWayBillDetailByIdAp();
		List<GetWayBillApDetailOutput> detailOutputs = getWayBillDetailByIdAp
				.execute(this.waybillNo);
		List<CommodityList> commodityList_temp = new ArrayList<CommodityList>();
		for (int i = 0; i < detailOutputs.size(); i++) {
			GetWayBillApDetailOutput detail = (GetWayBillApDetailOutput) detailOutputs
					.get(i);
			CommodityList commodity = new CommodityList();
			commodityList_temp.add(commodity);
			commodity.setCommodityId(detail.getCommodityId());
			commodity.setQuantity(detail.getQuantity());
			commodity.setRemarks(detail.getRemarks());
			commodity.setDelFlg(detail.getDelFlg());
			String commodityId = "";

			String detailNo = "";

			if (detail.getCommodityId().indexOf("-") > 0) {
				commodityId = detail.getCommodityId().substring(0,
						detail.getCommodityId().indexOf("-"));
				detailNo = detail.getCommodityId().substring(
						detail.getCommodityId().indexOf("-"));
			} else {
				commodityId = detail.getCommodityId();
			}

			String picUrl = Utility.getPicUrl(commodityId);
			commodity.setPicUrl(picUrl);

			Connection conn = null;
			PreparedStatement ps = null;
			String sql = null;
			try {
				conn = JdbcConnection.getConnection();

				sql = "SELECT * FROM TBL00017 WHERE WAYBILL_NO = ? AND COMMODITY_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, this.waybillNo);
				ps.setString(2, detail.getCommodityId());
				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					commodity.setGetSu(rs.getString("QUANTITY"));
				}

				sql = "SELECT STATUS FROM TBL00013 WHERE WAYBILL_NO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, this.waybillNo);

				rs = ps.executeQuery();

				while (rs.next()) {
					if ("10".equals(rs.getString("STATUS")))
						setDisabledFlg("true");
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}
		}

		setCount(String.valueOf(commodityList_temp.size()));
		for (int i = 0; i < commodityList_temp.size(); i++) {
			this.sum = String.valueOf(Integer.valueOf(this.sum).intValue()
					+ Integer.valueOf(
							((CommodityList) commodityList_temp.get(i))
									.getQuantity()).intValue());
			this.getSum = String.valueOf(Integer.valueOf(this.getSum)
					.intValue()
					+ Integer.valueOf((commodityList_temp.get(i)).getGetSu())
							.intValue());
		}

		setSessionAttribute("commodityList", commodityList_temp);
		if (commodityList_temp.size() > 7) {
			commodityList = commodityList_temp.subList(0, 7);
		} else {
			commodityList = commodityList_temp;
		}
		setNowPage("1");
		setAllPage(String
				.valueOf(((double) commodityList_temp.size() / (double) 7) > (commodityList_temp
						.size() / 7) ? commodityList_temp.size() / 7 + 1
						: commodityList_temp.size() / 7));

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

	public List<CommodityList> getCommodityList() {
		return this.commodityList;
	}

	public void setCommodityList(List<CommodityList> commodityList) {
		this.commodityList = commodityList;
	}

	public String getCount() {
		return this.count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSum() {
		return this.sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getGetSum() {
		return this.getSum;
	}

	public void setGetSum(String getSum) {
		this.getSum = getSum;
	}

	public String getDisabledFlg() {
		return this.disabledFlg;
	}

	public void setDisabledFlg(String disabledFlg) {
		this.disabledFlg = disabledFlg;
	}

}