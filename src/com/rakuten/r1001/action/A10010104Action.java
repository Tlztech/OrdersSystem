package com.rakuten.r1001.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.OrderApiBean;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.F100101;
import com.rakuten.util.Utility;

public class A10010104Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100101 f100101 = null;
	private String shop = null;
	private A1001Common a1001Common = new A1001Common();
	private String platform = null;

		protected void exec() throws Exception {
			// a1001Common.insertIntoRakutenOrderTbl(a1001Common
			// .getOrderListFromCsv(csvFile));
			OrderCommon common = new OrderCommon();
	
			if ("Rakuten".equals(platform)) {
				OrderApiBean orderapibean = common.getOrderListByApi(shop);
				if (!Utility.isEmptyList(orderapibean.getMessageList())) {
					for (String msg : orderapibean.getMessageList())
						addError(null, msg);
				}
				a1001Common
						.insertIntoRakutenOrderTbl(orderapibean.getRakutenBeanList());
				//common.setOrderListStatus(shop);
			} else if ("Yahoo".equals(platform)) {
				
	//			System.out.println(GetTokenFromYahoo.getToken());
	//			System.out.println(GetTokenFromYahoo.getTokenExpiration());
	//			System.out.println(platform);
				OrderApiBean orderapibean = common.getYahooOrderListByApi(shop);
				if (!Utility.isEmptyList(orderapibean.getMessageList())) {
					for (String msg : orderapibean.getMessageList())
						addError(null, msg);
				}
				a1001Common
						.insertIntoYahooOrderTbl(orderapibean.getRakutenBeanList());
			} else if ("AU".equals(platform)) {
				
				OrderApiBean orderapibean = common.getAUOrderListByApi(shop);
				if (!Utility.isEmptyList(orderapibean.getMessageList())) {
					for (String msg : orderapibean.getMessageList())
						addError(null, msg);
				}
				a1001Common
						.insertIntoAUOrderTbl(orderapibean.getRakutenBeanList(), shop);
			}
		}

	protected void init() {
		setTitle("V100101:注文一覧");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F100101 getF100101() {
		return f100101;
	}

	public void setF100101(F100101 f100101) {
		this.f100101 = f100101;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

}
