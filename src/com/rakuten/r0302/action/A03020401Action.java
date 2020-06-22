package com.rakuten.r0302.action;

import com.rakuten.common.action.BaseAction;

public class A03020401Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String commodityId = null;
	private String shop = null;

	protected void exec() throws Exception {

	}

	protected void init() {
		setTitle("");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

}
