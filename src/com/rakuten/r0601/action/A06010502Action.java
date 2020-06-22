package com.rakuten.r0601.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.form.F060105;
import com.rakuten.r0601.form.MeisaiList;

public class A06010502Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String waybillNo = null;
	F060105 f060105 = null;

	protected void exec() throws Exception {
		if (f060105 == null) {
			f060105 = new F060105();
		}
		List<MeisaiList> meisaiList = f060105.getMeisaiList();
		if (meisaiList == null) {
			meisaiList = new ArrayList<MeisaiList>();
		}
		meisaiList.add(new MeisaiList());
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
