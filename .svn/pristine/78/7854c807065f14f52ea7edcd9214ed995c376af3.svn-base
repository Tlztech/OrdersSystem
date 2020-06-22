package com.rakuten.r0501.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0501.ap.GetCommodityAp;
import com.rakuten.r0501.bean.GetCommodityApInput;
import com.rakuten.r0501.bean.GetCommodityApOutput;
import com.rakuten.r0501.form.CommodityList;
import com.rakuten.r0501.form.F050101;

public class A05010101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F050101 f050101 = null;

	protected void exec() throws Exception {
		GetCommodityAp getCommodityAp = new GetCommodityAp();
		GetCommodityApInput input = new GetCommodityApInput();
		input.setStatus("00");
		List<GetCommodityApOutput> outputList = getCommodityAp.execute(input);

		f050101 = new F050101();
		f050101.setType("2");
		if (outputList != null) {
			f050101.setResultCount(String.valueOf(outputList.size()));
		} else {
			f050101.setResultCount("0");
		}
		List<CommodityList> commodityList = new ArrayList<CommodityList>();
		CommodityList commodity = null;
		for (GetCommodityApOutput output : outputList) {
			commodity = new CommodityList();
			commodity.setCategoryName(output.getCategoryName());
			commodity.setCommodityId(output.getCommodityId());
			commodity.setChineseName(output.getChineseName());
			commodity.setCommodityUrl(output.getCommodityUrl());
			String status = output.getStatus();
			String statusName = "";
			if ("00".equals(status)) {
				statusName = "未审核";
			} else if ("20".equals(status)) {
				statusName = "审核未通过";
			} else {
				statusName = "审核通过";
			}
			commodity.setStatus(statusName);
			commodityList.add(commodity);
		}
		f050101.setCommodityList(commodityList);
	}

	protected void init() {
		setTitle("V050101:新商品审核");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F050101 getF050101() {
		return f050101;
	}

	public void setF050101(F050101 f050101) {
		this.f050101 = f050101;
	}

}
