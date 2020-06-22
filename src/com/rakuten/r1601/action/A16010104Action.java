package com.rakuten.r1601.action;

import shohinmodel.common.Shohincommon;

import com.rakuten.common.action.BaseAction;

public class A16010104Action extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String shop2 = null;
	private String logKey = null;

	@Override
	protected void init() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		Shohincommon common = new Shohincommon();
		common.downloadShohin(logKey);
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getLogKey() {
		return logKey;
	}

	public void setLogKey(String logKey) {
		this.logKey = logKey;
	}

	public String getShop2() {
		return shop2;
	}

	public void setShop2(String shop2) {
		this.shop2 = shop2;
	}

}
