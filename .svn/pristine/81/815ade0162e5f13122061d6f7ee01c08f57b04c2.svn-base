package com.rakuten.p0101.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.p0101.ap.GetCategoryCountByFatherIdAP;
import com.rakuten.p0101.ap.GetCommodityCategoryByFatherIdAp;
import com.rakuten.p0101.ap.GetCommodityCategoryByIdAp;
import com.rakuten.p0101.ap.GetCommodityCategoryByNameAp;
import com.rakuten.p0101.bean.GetCommodityCategoryByFatherIdInput;
import com.rakuten.p0101.bean.GetCommodityCategoryByIdInput;
import com.rakuten.p0101.bean.GetCommodityCategoryByNameInput;
import com.rakuten.p0101.bean.GetCommodityCategoryOutput;
import com.rakuten.p0101.form.CategoryInfo;
import com.rakuten.p0101.form.P010101;
import com.rakuten.util.Utility;

public class P01010102Action extends BaseAction {

	private P010101 p010101 = null;
	private String shohinbango = null;
	private String searchMode = null;

	private static final long serialVersionUID = 1L;

	protected void exec() throws Exception {
		String cateName = p010101.getCateName();

		List<GetCommodityCategoryOutput> outputList = null;
		if ("1".equals(searchMode)) {
			GetCommodityCategoryByFatherIdAp getCommodityCategoryByFatherIdAp = new GetCommodityCategoryByFatherIdAp();
			GetCommodityCategoryByFatherIdInput input = new GetCommodityCategoryByFatherIdInput();
			input.setID(p010101.getCategoryList()
					.get(Integer.valueOf(getRowIndex())).getCateId());
			outputList = getCommodityCategoryByFatherIdAp.execute(input);
		} else if ("2".equals(searchMode)) {
			GetCommodityCategoryByIdAp getCommodityCategoryByIdAp = new GetCommodityCategoryByIdAp();
			GetCommodityCategoryByIdInput input = new GetCommodityCategoryByIdInput();
			input.setID(p010101.getCategoryList()
					.get(Integer.valueOf(getRowIndex())).getFatherCateId());
			outputList = getCommodityCategoryByIdAp.execute(input);
		} else if ("0".equals(searchMode)) {
			GetCommodityCategoryByNameAp categoryByNameAp = new GetCommodityCategoryByNameAp();
			GetCommodityCategoryByNameInput input = new GetCommodityCategoryByNameInput();
			input.setName(cateName);
			outputList = categoryByNameAp.execute(input);
		}
		p010101.setCategoryList(null);
		if (Utility.isEmptyList(outputList)) {
			addError(null, getMessage("P01010101E", null));
			return;
		}
		List<CategoryInfo> categortInfoList = new ArrayList<CategoryInfo>();
		p010101.setCategoryList(categortInfoList);
		CategoryInfo categoryInfo = null;
		p010101.setResultCount(outputList.size());
		for (GetCommodityCategoryOutput output : outputList) {
			categoryInfo = new CategoryInfo();
			categoryInfo.setCateId(output.getCategoryId());
			categoryInfo.setCateName(output.getCategoryName());

			GetCommodityCategoryByIdAp getCommodityCategoryByIdAp = new GetCommodityCategoryByIdAp();
			GetCommodityCategoryByIdInput input = new GetCommodityCategoryByIdInput();
			input.setID(output.getFatherCategoryId());
			List<GetCommodityCategoryOutput> outputList2 = getCommodityCategoryByIdAp
					.execute(input);
			String fatherCateName = null;
			if (!Utility.isEmptyList(outputList2)) {
				fatherCateName = outputList2.get(0).getCategoryName();
			}
			categoryInfo.setFatherCateName(fatherCateName);

			GetCategoryCountByFatherIdAP getCategoryCountByFatherIdAP = new GetCategoryCountByFatherIdAP();
			GetCommodityCategoryByFatherIdInput input2 = new GetCommodityCategoryByFatherIdInput();
			input2.setID(output.getCategoryId());
			Long cateCount = getCategoryCountByFatherIdAP.execute(input2);

			categoryInfo.setCateCount(cateCount);
			categoryInfo.setFatherCateId(output.getFatherCategoryId());
			categoryInfo.setSozaiChn(output.getSozaiChn());
			categoryInfo.setSozaiJpn(output.getSozaiJpn());
			categoryInfo.setNameJpn(output.getNameJpn());
			categoryInfo.setKakaku(output.getKakaku());
			categortInfoList.add(categoryInfo);
		}
	}

	protected void init() {
		setTitle("P010101:商品分类检索");
	}

	protected void isValidated() throws Exception {
	}

	public String getSearchMode() {
		return searchMode;
	}

	public P010101 getP010101() {
		return p010101;
	}

	public void setP010101(P010101 p010101) {
		this.p010101 = p010101;
	}

	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
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
