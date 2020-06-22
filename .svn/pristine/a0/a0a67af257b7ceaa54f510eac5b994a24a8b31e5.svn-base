package com.rakuten.r0302.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0302.ap.DelCommodityByIdAp;
import com.rakuten.r0302.form.F030201;

public class A03020103Action extends BaseAction {

	private F030201 f030201 = null;
	private String shoriMode = null;
	private static final long serialVersionUID = 1L;

	protected void exec() throws Exception {
		DelCommodityByIdAp delCommodityByIdAp = new DelCommodityByIdAp();
		String rowIndex = getRowIndex();
		String commId = f030201.getCommodityList()
				.get(Integer.valueOf(rowIndex)).getCommodityId();
		delCommodityByIdAp.execute(commId);
	}

	protected void init() {
		setTitle("V030201:ø‚¥Ê¡–±Ì");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F030201 getF030201() {
		return f030201;
	}

	public void setF030201(F030201 f030201) {
		this.f030201 = f030201;
	}

	/**
	 * @return the shoriMode
	 */
	public String getShoriMode() {
		return shoriMode;
	}

	/**
	 * @param shoriMode
	 *            the shoriMode to set
	 */
	public void setShoriMode(String shoriMode) {
		this.shoriMode = shoriMode;
	}

}
