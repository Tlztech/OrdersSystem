package com.rakuten.r0503.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0503.ap.GetCommodityAp;
import com.rakuten.r0503.bean.GetCommodityApInput;
import com.rakuten.r0503.bean.GetCommodityApOutput;
import com.rakuten.r0503.form.CommodityList;
import com.rakuten.r0503.form.F050301;

public class A05030101Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F050301 f050301 = null;

	protected void exec() throws Exception {
		GetCommodityAp getCommodityAp = new GetCommodityAp();
		GetCommodityApInput input = new GetCommodityApInput();
		input.setStatus("30");
		List<GetCommodityApOutput> outputList = getCommodityAp.execute(input);

		f050301 = new F050301();
		f050301.setType("2");
		if (outputList != null) {
			f050301.setResultCount(String.valueOf(outputList.size()));
		} else {
			f050301.setResultCount("0");
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
			if ("30".equals(status)) {
				statusName = "未完成";
			} else if (Integer.valueOf(status) > 20) {
				statusName = "已完成";
			}
			commodity.setStatus(statusName);
			commodityList.add(commodity);
		}
		f050301.setCommodityList(commodityList);
	}

	protected void init() {
		setTitle("V050301:文案录入");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f050301
	 */
	public F050301 getF050301() {
		return f050301;
	}

	/**
	 * @param f050301
	 *            the f050301 to set
	 */
	public void setF050301(F050301 f050301) {
		this.f050301 = f050301;
	}

}
