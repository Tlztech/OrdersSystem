package com.rakuten.r1002.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1002.form.F100201;

public class A10020101Action extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private F100201 f100201 = null;

	protected void exec() throws Exception {

	}

	protected void init() {
		setTitle("V100101:注文状態");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F100201 getF100201() {
		return f100201;
	}

	public void setF100201(F100201 f100201) {
		this.f100201 = f100201;
	}

}
