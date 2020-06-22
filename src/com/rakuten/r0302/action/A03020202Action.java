package com.rakuten.r0302.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0302.form.CommodityDetail;
import com.rakuten.r0302.form.F030303;

public class A03020202Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String shoriMode = null;
	F030303 f030303 = null;

	protected void exec() throws Exception {
		CommodityDetail detail = (CommodityDetail) getSessionAttribute("A03020201Date");
		if (detail != null) {
			f030303 = new F030303();
			f030303.setDetailNo(detail.getDetailNo());
			f030303.setDescribe(detail.getDescribe());
			f030303.setPicUrl(detail.getPicUrl());
			f030303.setPriceIn(detail.getPriceIn());
			f030303.setRePrice(detail.getRePrice());
			f030303.setBarCode(detail.getBarCode());
			f030303.setStockSh(detail.getStockSh());
			f030303.setStockJp(detail.getStockJp());
			f030303.setStockHandup(detail.getStockHandup());
			f030303.setRemarks(detail.getRemarks());
			removeSessionAttribute("A03020201Date");
		}else{
			f030303 = new F030303();
			f030303.setStockSh("0");
			f030303.setStockJp("0");
			f030303.setStockHandup("0");
		}
	}

	protected void init() {
		setTitle("V030203:ÉÌÆ·Ã÷Ï¸");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f030303
	 */
	public F030303 getF030303() {
		return f030303;
	}

	/**
	 * @param f030303
	 *            the f030303 to set
	 */
	public void setF030303(F030303 f030303) {
		this.f030303 = f030303;
	}

	public String getShoriMode() {
		return shoriMode;
	}

	public void setShoriMode(String shoriMode) {
		this.shoriMode = shoriMode;
	}
}
