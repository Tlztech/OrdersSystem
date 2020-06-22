package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.CheckCommodityAp;
import com.rakuten.r0601.form.CommodityList;
import com.rakuten.r0601.form.F060102;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A06010201Action extends BaseAction {
	private static final long serialVersionUID = 1L;

	private F060102 f060102 = null;

	protected void exec() throws Exception {
		String commodityId = "";
		String detailNo = "";
		if (f060102.getCommodityIdInput().indexOf("-") > 0) {
			commodityId = f060102.getCommodityIdInput().substring(0,
					f060102.getCommodityIdInput().indexOf("-"));
			detailNo = f060102.getCommodityIdInput().substring(
					f060102.getCommodityIdInput().indexOf("-"));
		} else {
			commodityId = f060102.getCommodityIdInput();
		}
	
		String picUrl = Utility.getPicUrl(commodityId);
		List<CommodityList> commodityList = f060102.getCommodityList();
		if (Utility.isEmptyList(commodityList)) {
			commodityList = new ArrayList<CommodityList>();
		}
		CommodityList commodity = new CommodityList();
		if (Utility.isEmptyString(detailNo)) {
			commodity.setCommodityId(commodityId);
		} else {
			commodity.setCommodityId(commodityId + detailNo);
		}
		commodity.setPicUrl(picUrl);
		commodity.setQuantity(f060102.getQuantityInput());
		commodity.setRemarks(f060102.getRemarksInput());
		commodityList.add(commodity);
		Collections.reverse(commodityList);
		f060102.setCommodityList(commodityList);
		f060102.setCommodityIdInput("");
		f060102.setQuantityInput("");
		f060102.setRemarksInput("");
	}

	protected void init() {
		setTitle("V060102:添加发货单");
	}

	protected void isValidated() throws Exception {
		String commodityId = "";
		String detailNo = "";
		if (Utility.isEmptyString(f060102.getQuantityInput())) {
			f060102.setQuantityInput("1");
		}

		if (f060102.getCommodityIdInput().indexOf("-") > 0) {
			commodityId = f060102.getCommodityIdInput().substring(0,
					f060102.getCommodityIdInput().indexOf("-"));
			detailNo = f060102.getCommodityIdInput().substring(
					f060102.getCommodityIdInput().indexOf("-"));
		} else {
			commodityId = f060102.getCommodityIdInput();
			detailNo = "";
		}
		CheckCommodityAp checkCommodityAp = new CheckCommodityAp();
		if (!checkCommodityAp.execute(commodityId, detailNo)) {
			Connection conn = null;
			PreparedStatement ps = null;
			String sql = null;
			ResultSet rs = null;
			try {
				conn = JdbcConnection.getConnection();
				sql = "SELECT * FROM TBL00016 WHERE BARCODE = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, commodityId);
				rs = ps.executeQuery();
				String comm = "";
				while (rs.next()) {
					comm = rs.getString("COMMODITY_ID");
				}
				if (Utility.isEmptyString(comm)) {
					addError("f060102.commodityIdInput", "商品编号或条形码不存在！");
				} else {
					f060102.setCommodityIdInput(comm);
					if (f060102.getCommodityIdInput().indexOf("-") > 0) {
						commodityId = f060102.getCommodityIdInput().substring(
								0, f060102.getCommodityIdInput().indexOf("-"));
						detailNo = f060102.getCommodityIdInput().substring(
								f060102.getCommodityIdInput().indexOf("-"));
					} else {
						commodityId = f060102.getCommodityIdInput();
						detailNo = "";
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}

		}

		List<CommodityList> commodityList = f060102.getCommodityList();
		if (!Utility.isEmptyList(commodityList)) {
			for (int i = 0; i < commodityList.size(); i++) {
				String id = commodityList.get(i).getCommodityId();
				if (id.equals(commodityId + detailNo)) {
					f060102.setQuantityInput((String.valueOf(Integer
							.valueOf(commodityList.get(i).getQuantity())
							+ Integer.valueOf(f060102.getQuantityInput()))));
					commodityList.remove(i);
					break;
				}
			}
		}

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F060102 getF060102() {
		return f060102;
	}

	public void setF060102(F060102 f060102) {
		this.f060102 = f060102;
	}

}
