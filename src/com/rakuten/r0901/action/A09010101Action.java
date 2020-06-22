package com.rakuten.r0901.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0901.common.A0901Common;
import com.rakuten.r0901.form.F090101;
import com.rakuten.r0901.form.Meisai;

public class A09010101Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F090101 f090101 = null;
	private A0901Common a0901Common = new A0901Common();

	protected void exec() throws Exception {
		List<Meisai> meisaiList = a0901Common.getMeisaiList(new F090101());
		f090101 = new F090101();
		f090101.setMeisaiList(meisaiList);
		f090101.setResultCount(String.valueOf(meisaiList.size()));
	}

	protected void init() {
		setTitle("V090101:群馬銀行入出金明細照会");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F090101 getF090101() {
		return f090101;
	}

	public void setF090101(F090101 f090101) {
		this.f090101 = f090101;
	}

}
