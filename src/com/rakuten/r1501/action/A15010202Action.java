package com.rakuten.r1501.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1501.common.A1501Common;
import com.rakuten.r1501.form.Account;
import com.rakuten.r1501.form.F150102;

public class A15010202Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F150102 f150102 = null;
	private String accountId = null;

	protected void exec() throws Exception {
		Account account = A1501Common.getAccountInfo(accountId);
		setTitle("V150102:账目明细 " + account.getName() + " "
				+ account.getCardNo() + " " + account.getTypeName());
		f150102.setAddArea(account.getArea());

		f150102.setMeisaiList(A1501Common.getCashAccountInfo(accountId,
				f150102.getAddArea(), f150102.getDayStart(),
				f150102.getDayEnd(), f150102.getKubun(), f150102.getBiko(),
				f150102.getLeibie(), f150102.getOutStart(),
				f150102.getOutEnd(), f150102.getInStart(), f150102.getIntEnd()));
		f150102.setResultCount(String.valueOf(f150102.getMeisaiList().size()));
	}

	protected void init() throws Exception {
		setTitle("V150102:账目明细");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F150102 getF150102() {
		return f150102;
	}

	public void setF150102(F150102 f150102) {
		this.f150102 = f150102;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

}
