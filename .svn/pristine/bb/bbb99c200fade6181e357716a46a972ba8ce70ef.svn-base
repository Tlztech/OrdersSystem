package com.rakuten.r0503.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0503.ap.UpdCommodityByIdAp;
import com.rakuten.r0503.form.CommodityList;
import com.rakuten.r0503.form.F050301;

public class A05030104Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String alertFlg = null;
	private F050301 f050301 = null;

	protected void exec() throws Exception {
		List<CommodityList> commodityLists = f050301.getCommodityList();
		for (int i = 0; i < commodityLists.size(); i++) {

			if ("false".equals(commodityLists.get(i).getCheckFlg())) {
				continue;
			}
			CommodityList commodity = f050301.getCommodityList().get(i);
			String commodityId = commodity.getCommodityId();
			UpdCommodityByIdAp updCommodityByIdAp = new UpdCommodityByIdAp();
			updCommodityByIdAp.execute(commodityId, "40");
		}
		setAlertFlg("0");
	}

	protected void init() {
		setTitle("V050301:文案录入");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F050301 getF050301() {
		return f050301;
	}

	public void setF050301(F050301 f050301) {
		this.f050301 = f050301;
	}

	public String getAlertFlg() {
		return alertFlg;
	}

	public void setAlertFlg(String alertFlg) {
		this.alertFlg = alertFlg;
	}

}
