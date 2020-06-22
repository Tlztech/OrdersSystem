package com.rakuten.r0601.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.GetCommodityPicUrlAp;
import com.rakuten.r0601.form.CommodityList;
import com.rakuten.r0601.form.F060102;
import com.rakuten.util.Utility;

public class A06010401Action extends BaseAction {
	private static final long serialVersionUID = 1L;

	private F060102 f060102 = null;

	protected void exec() throws Exception {
		String commodityId = "";
		String detailNo = "";
		if (f060102.getCommodityIdInput().contains("-")) {
			commodityId = f060102.getCommodityIdInput().substring(0,
					f060102.getCommodityIdInput().indexOf("-"));
			detailNo = f060102.getCommodityIdInput().substring(
					f060102.getCommodityIdInput().indexOf("-"));
		} else {
			commodityId = f060102.getCommodityIdInput();
		}
		GetCommodityPicUrlAp getCommodityPicUrlAp = new GetCommodityPicUrlAp();
		String picUrl = getCommodityPicUrlAp.execute(commodityId, detailNo);
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
		f060102.setCommodityList(commodityList);
		f060102.setCommodityIdInput("");
		f060102.setQuantityInput("");
		f060102.setRemarksInput("");
	}

	protected void init() {
		setTitle("V060103:修改发货单");
	}

	protected void isValidated() throws Exception {
		String commodityId = "";
		String detailNo = "";
		if (f060102.getCommodityIdInput().indexOf("-") > 0) {
			commodityId = f060102.getCommodityIdInput().substring(0,
					f060102.getCommodityIdInput().indexOf("-"));
			detailNo = f060102.getCommodityIdInput().substring(
					f060102.getCommodityIdInput().indexOf("-"));
		}
		List<CommodityList> commodityList = f060102.getCommodityList();
		if (!Utility.isEmptyList(commodityList)) {
			for (int i = 0; i < commodityList.size(); i++) {
				String id = commodityList.get(i).getCommodityId();
				if (id.equals(commodityId + detailNo)) {
					addError("f060102.commodityIdInput", "此商品已添加到详细列表！");
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
