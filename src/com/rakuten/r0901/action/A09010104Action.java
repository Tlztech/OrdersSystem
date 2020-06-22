package com.rakuten.r0901.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0901.common.A0901Common;

public class A09010104Action extends BaseAction {
	private static final long serialVersionUID = 1L;

	private A0901Common a0901Common = new A0901Common();
	private String kanribango = null;
	private String memo = null;
	private String result = null;

	protected void exec() throws Exception {
		int count = a0901Common.updateMemo(kanribango, memo);
		if (count > 0) {
			result = "true";
		} else {
			result = "false";
		}
	}

	protected void init() {
		setTitle("V090101:群馬銀行入出金明細照会");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public A0901Common getA0901Common() {
		return a0901Common;
	}

	public void setA0901Common(A0901Common a0901Common) {
		this.a0901Common = a0901Common;
	}

	public String getKanribango() {
		return kanribango;
	}

	public void setKanribango(String kanribango) {
		this.kanribango = kanribango;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
