package com.rakuten.r0601.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.common.A060105Common;
import com.rakuten.r0601.form.F060105;

public class A06010504Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String waybillNo = null;
	F060105 f060105 = null;

	protected void exec() throws Exception {

		A060105Common common = new A060105Common();
		f060105 = common.getDefaultValue(waybillNo);
		f060105.setHozozumiFlg("0");
	}

	protected void init() {
		setTitle("V060105:报关单");

	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public String getWaybillNo() {
		return waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public F060105 getF060105() {
		return f060105;
	}

	public void setF060105(F060105 f060105) {
		this.f060105 = f060105;
	}

}
