package com.rakuten.r0302.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0302.ap.AddCommodityAp;
import com.rakuten.r0302.bean.AddCommodityApInput;
import com.rakuten.r0302.bean.CommodityDetail;
import com.rakuten.r0302.form.F030202;
import com.rakuten.util.Utility;

public class A03020203Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String shoriMode = null;
	F030202 f030202 = null;

	protected void exec() throws Exception {
		AddCommodityApInput input = new AddCommodityApInput();
		input.setCommodityId(f030202.getCommodityId());
		input.setCategoryId(f030202.getCategoryId());
		input.setChineseName(f030202.getChineseName());
		input.setJapaneseName(f030202.getJapaneseName());
		input.setSource(f030202.getSource());
		input.setRespPerson(f030202.getRespPerson());
		input.setCommodityUrl(f030202.getCommodityUrl());
		input.setPicUrl(f030202.getPicUrl());
		input.setRemarks(f030202.getRemarks());
		input.setPriceIn(f030202.getPriceIn());
		input.setRePrice(f030202.getPriceOut());
		input.setShoriMode(shoriMode);

		List<CommodityDetail> commodityDetailList = new ArrayList<CommodityDetail>();
		input.setCommodityDetailList(commodityDetailList);
		CommodityDetail detail = null;
		if (!Utility.isEmptyList(f030202.getCommodityDetailList())) {
			for (com.rakuten.r0302.form.CommodityDetail commoditydetail : f030202
					.getCommodityDetailList()) {
				detail = new CommodityDetail();
				commodityDetailList.add(detail);
				detail.setDetailNo(commoditydetail.getDetailNo());
				detail.setDescribe(commoditydetail.getDescribe());
				detail.setPicUrl(commoditydetail.getPicUrl());
				detail.setBarCode(commoditydetail.getBarCode());
				detail.setStockSh(commoditydetail.getStockSh());
				detail.setStockJp(commoditydetail.getStockJp());
				detail.setStockHandup(commoditydetail.getStockHandup());
				detail.setRemarks(commoditydetail.getRemarks());
				detail.setDelFlg(commoditydetail.isDelFlg());
			}
		}
		AddCommodityAp addCommodityAp = new AddCommodityAp();
		addCommodityAp.execute(input);
	}

	protected void init() {
		if ("1".equals(shoriMode)) {
			setTitle("V030202:添加商品");
		} else if ("2".equals(shoriMode)) {
			setTitle("V030202:查看/修改商品");
		}
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		if (Utility.isEmptyString(f030202.getCommodityId())) {
			addError("f030202.commodityId",
					getMessage("M00000001E", new String[] { "商品编号" }));
		}
		if (Utility.isEmptyString(f030202.getCategoryId())) {
			addError("f030202.categoryId",
					getMessage("M00000001E", new String[] { "商品分类" }));
		}
		if (Utility.isEmptyString(f030202.getChineseName())) {
			addError("f030202.chineseName",
					getMessage("M00000001E", new String[] { "中文名" }));
		}
	}

	/**
	 * @return the f030202
	 */
	public F030202 getF030202() {
		return f030202;
	}

	/**
	 * @param f030202
	 *            the f030202 to set
	 */
	public void setF030202(F030202 f030202) {
		this.f030202 = f030202;
	}

	public String getShoriMode() {
		return shoriMode;
	}

	public void setShoriMode(String shoriMode) {
		this.shoriMode = shoriMode;
	}

}
