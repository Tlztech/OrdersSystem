package com.rakuten.r0601.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.form.CommodityList;
import com.rakuten.r0601.form.F060102;

public class A06010203Action extends BaseAction {
	private static final long serialVersionUID = 1L;

	private F060102 f060102 = null;

	protected void exec() throws Exception {
		List<CommodityList> commodityList = f060102.getCommodityList();
		commodityList.remove(commodityList.get(Integer.valueOf(getRowIndex())));
	}

	protected void init() {
		setTitle("V060102:添加发货单");
	}

	protected void isValidated() throws Exception {
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
