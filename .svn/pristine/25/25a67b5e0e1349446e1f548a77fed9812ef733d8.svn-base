package com.rakuten.r0502.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0502.ap.GetCommodityAp;
import com.rakuten.r0502.bean.GetCommodityApInput;
import com.rakuten.r0502.bean.GetCommodityApOutput;
import com.rakuten.r0502.form.CommodityList;
import com.rakuten.r0502.form.F050201;

public class A05020102Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F050201 f050201 = null;

	protected void exec() throws Exception {
		GetCommodityAp getCommodityAp = new GetCommodityAp();
		GetCommodityApInput input = new GetCommodityApInput();
		input.setCategoryId(f050201.getCategoryId());
		input.setCommodityId(f050201.getCommodityId());
		input.setChineseName(f050201.getChineseName());
		input.setJapaneseName(f050201.getJapaneseName());
		String type = f050201.getType();
		if ("1".equals(type)) {
			input.setStatus("1");
		} else if ("2".equals(type)) {
			input.setStatus("10");
		} else if ("3".equals(type)) {
			input.setStatus("3");
		}

		List<GetCommodityApOutput> outputList = getCommodityAp.execute(input);

		if (outputList != null) {
			int count = outputList.size();
			if (count == 0) {
				addActionError("没有找到相应的结果！");
			}
			f050201.setResultCount(String.valueOf(count));
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

	public F050201 getF050201() {
		return f050201;
	}

	public void setF050201(F050201 f050201) {
		this.f050201 = f050201;
	}

}
