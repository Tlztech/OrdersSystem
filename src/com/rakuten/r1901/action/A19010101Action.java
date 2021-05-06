package com.rakuten.r1901.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1901.form.F190101;

public class A19010101Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F190101 f190101 = new F190101();

	@Override
	protected void init() throws Exception {
		setTitle("V190101:パスワード変更");

	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the f190101
	 */
	public F190101 getF190101() {
		return f190101;
	}

	/**
	 * @param f190101 the f190101 to set
	 */
	public void setF190101(F190101 f190101) {
		this.f190101 = f190101;
	}

}
