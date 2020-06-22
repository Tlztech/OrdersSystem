package com.rakuten.r0502.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0502.ap.GetCommodityAp;
import com.rakuten.r0502.bean.GetCommodityApInput;
import com.rakuten.r0502.bean.GetCommodityApOutput;
import com.rakuten.r0502.form.CommodityList;
import com.rakuten.r0502.form.F050201;

public class A05020101Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F050201 f050201 = null;

	protected void exec() throws Exception {
		GetCommodityAp getCommodityAp = new GetCommodityAp();
		GetCommodityApInput input = new GetCommodityApInput();
		input.setStatus("10");
		List<GetCommodityApOutput> outputList = getCommodityAp.execute(input);

		f050201 = new F050201();
		f050201.setType("2");
		if (outputList != null) {
			f050201.setResultCount(String.valueOf(outputList.size()));
		} else {
			f050201.setResultCount("0");
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
			if ("10".equals(status)) {
				statusName = "未完成";
			} else if (Integer.valueOf(status) > 20) {
				statusName = "已完成";
			}
			commodity.setStatus(statusName);
			commodityList.add(commodity);
		}
		f050201.setCommodityList(commodityList);
	}

	protected void init() {
		setTitle("V050201:美工作图列表");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f050201
	 */
	public F050201 getF050201() {
		return f050201;
	}

	/**
	 * @param f050201
	 *            the f050201 to set
	 */
	public void setF050201(F050201 f050201) {
		this.f050201 = f050201;
	}

}
