package com.rakuten.r0302.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0302.form.F030201;

public class A03020101Action extends BaseAction {

	private F030201 f030201 = null;
	private String shoriMode = null;
	private static final long serialVersionUID = 1L;

	protected void exec() throws Exception {
//		GetCommodityAllAp getCommodityAllAp = new GetCommodityAllAp();
//		List<GetCommodityApOutput> outputList = getCommodityAllAp.execute();
//		Integer count = outputList.size();
//		f030201 = new F030201();
//		f030201.setResultCount(count.toString());
//		if (!Utility.isEmptyList(outputList)) {
//
//			List<CommodityList> commodityList = new ArrayList<CommodityList>();
//			f030201.setCommodityList(commodityList);
//			CommodityList commodity = null;
//			for (GetCommodityApOutput output : outputList) {
//				commodity = new CommodityList();
//				commodityList.add(commodity);
//				commodity.setCategoryName(output.getCategoryName());
//				commodity.setChineseName(output.getChineseName());
//				commodity.setCommodityId(output.getCommodityId());
//				commodity.setJapaneseName(output.getJapaneseName());
//				commodity.setPicUrl(output.getPicUrl());
//				commodity.setStockSh(output.getStockSh());
//				commodity.setStockJp(output.getStockJp());
//				commodity.setStockHandup(output.getStockHandup());
//			}
//		}

	}

	protected void init() {
		setTitle("V030201:ø‚¥Ê¡–±Ì");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F030201 getF030201() {
		return f030201;
	}

	public void setF030201(F030201 f030201) {
		this.f030201 = f030201;
	}

	/**
	 * @return the shoriMode
	 */
	public String getShoriMode() {
		return shoriMode;
	}

	/**
	 * @param shoriMode
	 *            the shoriMode to set
	 */
	public void setShoriMode(String shoriMode) {
		this.shoriMode = shoriMode;
	}

}
