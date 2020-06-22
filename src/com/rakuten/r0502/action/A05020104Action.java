package com.rakuten.r0502.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0502.ap.UpdCommodityByIdAp;
import com.rakuten.r0502.form.CommodityList;
import com.rakuten.r0502.form.F050201;

public class A05020104Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String alertFlg = null;
	private F050201 f050201 = null;

	protected void exec() throws Exception {
		List<CommodityList> commodityLists = f050201.getCommodityList();
		for (int i = 0; i < commodityLists.size(); i++) {

			if ("false".equals(commodityLists.get(i).getCheckFlg())) {
				continue;
			}
			CommodityList commodity = f050201.getCommodityList().get(i);
			String commodityId = commodity.getCommodityId();
			UpdCommodityByIdAp updCommodityByIdAp = new UpdCommodityByIdAp();
			updCommodityByIdAp.execute(commodityId, "30");
		}
		setAlertFlg("0");
	}

	protected void init() {
		setTitle("V050201:美工作图列表");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F050201 getF050201() {
		return f050201;
	}

	public void setF050201(F050201 f050201) {
		this.f050201 = f050201;
	}

	public String getAlertFlg() {
		return alertFlg;
	}

	public void setAlertFlg(String alertFlg) {
		this.alertFlg = alertFlg;
	}

}
