package com.rakuten.r1501.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1501.common.A1501Common;
import com.rakuten.r1501.form.Account;
import com.rakuten.r1501.form.F150104;

public class A15010402Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	F150104 f150104 = null;

	protected void exec() throws Exception {

	}

	protected void init() throws Exception {
		Account account = A1501Common.getAccountInfo(f150104.getAccount_id());
		setTitle("V150104:账目明细 " + account.getName() + " "
				+ account.getCardNo() + " " + account.getTypeName());

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F150104 getF150104() {
		return f150104;
	}

	public void setF150104(F150104 f150104) {
		this.f150104 = f150104;
	}

}
