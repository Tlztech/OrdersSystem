package com.rakuten.r1001.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1001.form.F100104;

public class A10010401Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100104 f100104 = null;

	protected void exec() throws Exception {

	}

	protected void init() {
		setTitle("V100104:新規");
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F100104 getF100104() {
		return f100104;
	}

	public void setF100104(F100104 f100104) {
		this.f100104 = f100104;
	}

}
