package com.rakuten.r1505.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1505.common.A150501Common;
import com.rakuten.r1505.form.F150501;

public class A15050101Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	F150501 f150501 = null;
	String mode = null;

	protected void exec() throws Exception {
		if ("shinki".equals(mode)) {
			f150501 = new F150501();
		} else {
			A150501Common common = new A150501Common();
			f150501 = common.getSeikyusho(mode);
		}
	}

	protected void init() throws Exception {
		setTitle("V150501:请求书");
	}

	public F150501 getF150501() {
		return f150501;
	}

	public void setF150501(F150501 f150501) {
		this.f150501 = f150501;
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
