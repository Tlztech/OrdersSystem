package com.rakuten.r0601.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.form.F060101;

public class A06010101Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	F060101 f060101 = null;

	protected void exec() throws Exception {
		removeSessionAttribute("hassou");
	}

	protected void init() {
		setTitle("V060101:发货管理");
		f060101 = new F060101();
		f060101.setStatus("1");
		f060101.setLogistics("04");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f060101
	 */
	public F060101 getF060101() {
		return f060101;
	}

	/**
	 * @param f060101
	 *            the f060101 to set
	 */
	public void setF060101(F060101 f060101) {
		this.f060101 = f060101;
	}

}
