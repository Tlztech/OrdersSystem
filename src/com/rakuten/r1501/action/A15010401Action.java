package com.rakuten.r1501.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1501.common.A1501Common;
import com.rakuten.r1501.form.Account;
import com.rakuten.r1501.form.AlipayMeisai;
import com.rakuten.r1501.form.F150104;

public class A15010401Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String accountId = null;
	F150104 f150104 = null;

	protected void exec() throws Exception {
		if (f150104 == null) {
			f150104 = new F150104();
		}
		f150104.setAccount_id(accountId);
		List<AlipayMeisai> meisaiList = A1501Common.getAlipayDataList(
				accountId, "50");
		f150104.setAlipayMeisaiList(meisaiList);
		f150104.setResultCount(String.valueOf(meisaiList.size()));
	}

	protected void init() throws Exception {
		Account account = A1501Common.getAccountInfo(accountId);
		setTitle("V150104:账目明细 " + account.getName() + " "
				+ account.getCardNo() + " " + account.getTypeName());

	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public F150104 getF150104() {
		return f150104;
	}

	public void setF150104(F150104 f150104) {
		this.f150104 = f150104;
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

}
