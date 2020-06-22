package com.rakuten.r0501.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0501.ap.UpdCommodityByIdAp;
import com.rakuten.r0501.form.CommodityList;
import com.rakuten.r0501.form.F050101;

public class A05010103Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String passFlg = null;
	private String alertFlg = null;
	private F050101 f050101 = null;

	protected void exec() throws Exception {
		int index = Integer.valueOf(getRowIndex());
		CommodityList commodity = f050101.getCommodityList().get(index);
		String commodityId = commodity.getCommodityId();
		UpdCommodityByIdAp updCommodityByIdAp = new UpdCommodityByIdAp();
		String status = "";
		if ("0".equals(passFlg)) {
			status = "10";
		} else {
			status = "20";
		}
		updCommodityByIdAp.execute(commodityId, status);
		setAlertFlg("0");
	}

	protected void init() {
		setTitle("V050101:新商品审核");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F050101 getF050101() {
		return f050101;
	}

	public void setF050101(F050101 f050101) {
		this.f050101 = f050101;
	}

	public String getPassFlg() {
		return passFlg;
	}

	public void setPassFlg(String passFlg) {
		this.passFlg = passFlg;
	}

	public String getAlertFlg() {
		return alertFlg;
	}

	public void setAlertFlg(String alertFlg) {
		this.alertFlg = alertFlg;
	}

}
