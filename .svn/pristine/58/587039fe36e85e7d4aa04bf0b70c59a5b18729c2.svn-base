package com.rakuten.p0101.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.p0101.ap.GetCategoryCountByFatherIdAP;
import com.rakuten.p0101.ap.GetCommodityCategoryByIdAp;
import com.rakuten.p0101.ap.GetCommodityCategoryByTopAp;
import com.rakuten.p0101.bean.GetCommodityCategoryByFatherIdInput;
import com.rakuten.p0101.bean.GetCommodityCategoryByIdInput;
import com.rakuten.p0101.bean.GetCommodityCategoryOutput;
import com.rakuten.p0101.form.CategoryInfo;
import com.rakuten.p0101.form.P010101;
import com.rakuten.util.Utility;

public class P01010101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private P010101 p010101 = null;
	private String shohinbango = null;

	protected void exec() throws Exception {
		GetCommodityCategoryByTopAp getCommodityCategoryByTopAp = new GetCommodityCategoryByTopAp();
		p010101 = new P010101();
		p010101.setShohinbango(shohinbango);
		List<GetCommodityCategoryOutput> getCommodityCategoryOutputList = null;
		getCommodityCategoryOutputList = getCommodityCategoryByTopAp.execute();
		List<CategoryInfo> categortInfoList = new ArrayList<CategoryInfo>();
		p010101.setCategoryList(categortInfoList);
		CategoryInfo categoryInfo = null;
		p010101.setResultCount(getCommodityCategoryOutputList.size());
		for (GetCommodityCategoryOutput output : getCommodityCategoryOutputList) {
			categoryInfo = new CategoryInfo();
			categoryInfo.setCateId(output.getCategoryId());
			categoryInfo.setCateName(output.getCategoryName());
			GetCommodityCategoryByIdAp getCommodityCategoryByIdAp = new GetCommodityCategoryByIdAp();
			GetCommodityCategoryByIdInput input = new GetCommodityCategoryByIdInput();
			input.setID(output.getFatherCategoryId());
			String fatherCateName = null;
			List<GetCommodityCategoryOutput> outputList = getCommodityCategoryByIdAp
					.execute(input);
			if (!Utility.isEmptyList(outputList)) {
				fatherCateName = outputList.get(0).getCategoryName();
			}
			categoryInfo.setFatherCateName(fatherCateName);
			GetCategoryCountByFatherIdAP getCategoryCountByFatherIdAP = new GetCategoryCountByFatherIdAP();
			GetCommodityCategoryByFatherIdInput input2 = new GetCommodityCategoryByFatherIdInput();
			input2.setID(output.getCategoryId());
			Long cateCount = getCategoryCountByFatherIdAP.execute(input2);
			categoryInfo.setCateCount(cateCount);
			categoryInfo.setFatherCateId(output.getFatherCategoryId());
			categortInfoList.add(categoryInfo);
		}

	}

	public P010101 getP010101() {
		return p010101;
	}

	public void setP010101(P010101 p010101) {
		this.p010101 = p010101;
	}

	protected void init() {
		setTitle("P010101:商品分类检索");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

}
