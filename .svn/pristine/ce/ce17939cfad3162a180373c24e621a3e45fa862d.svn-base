package com.rakuten.r0301.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0301.ap.GetCommodityCategoryByFatherIdAp;
import com.rakuten.r0301.ap.GetCommodityCategoryByIdAp;
import com.rakuten.r0301.ap.GetCommodityCategoryByNameAp;
import com.rakuten.r0301.bean.GetCommodityCategoryByFatherIdInput;
import com.rakuten.r0301.bean.GetCommodityCategoryByIdInput;
import com.rakuten.r0301.bean.GetCommodityCategoryByNameInput;
import com.rakuten.r0301.bean.GetCommodityCategoryOutput;
import com.rakuten.r0301.form.CategoryInfo;
import com.rakuten.r0301.form.F030101;
import com.rakuten.util.Utility;

public class A03010102Action extends BaseAction {

	private F030101 f030101 = null;
	private String searchMode = null;
	private static final long serialVersionUID = 1L;

	protected void exec() throws Exception {
		if (f030101 == null) {
			if (getSessionAttribute("V030101SearchForm") != null) {
				f030101 = (F030101) getSessionAttribute("V030101SearchForm");
			} else {
				f030101 = new F030101();
			}
		}
		GetCommodityCategoryByFatherIdAp getCommodityCategoryByFatherIdAp = new GetCommodityCategoryByFatherIdAp();
		GetCommodityCategoryByIdAp getCommodityCategoryByIdAp = new GetCommodityCategoryByIdAp();
		GetCommodityCategoryByNameAp getCommodityCategoryByNameAp = new GetCommodityCategoryByNameAp();
		String cateName = f030101.getCateName();

		List<GetCommodityCategoryOutput> outPutList = null;
		if ("1".equals(searchMode)) {
			GetCommodityCategoryByFatherIdInput input = new GetCommodityCategoryByFatherIdInput();
			input.setFatherID(f030101.getCategoryList()
					.get(Integer.valueOf(getRowIndex())).getCateId());
			outPutList = getCommodityCategoryByFatherIdAp.execute(input);
		} else if ("2".equals(searchMode)) {
			GetCommodityCategoryByIdInput input = new GetCommodityCategoryByIdInput();
			input.setID(f030101.getCategoryList()
					.get(Integer.valueOf(getRowIndex())).getFatherCateId());
			outPutList = getCommodityCategoryByIdAp.execute(input);
		} else {
			GetCommodityCategoryByNameInput input = new GetCommodityCategoryByNameInput();
			input.setName(cateName);
			outPutList = getCommodityCategoryByNameAp.execute(input);
		}
		f030101.setCategoryList(null);
		if (Utility.isEmptyList(outPutList)) {
			addError(null, getMessage("M03010101E", null));
			return;
		}
		List<CategoryInfo> categortInfoList = new ArrayList<CategoryInfo>();
		f030101.setCategoryList(categortInfoList);
		CategoryInfo categoryInfo = null;
		f030101.setResultCount(outPutList.size());
		for (GetCommodityCategoryOutput outPut : outPutList) {
			categoryInfo = new CategoryInfo();
			categoryInfo.setCateId(outPut.getCategoryId());
			categoryInfo.setCateName(outPut.getCategoryName());
			GetCommodityCategoryByIdInput input1 = new GetCommodityCategoryByIdInput();
			input1.setID(outPut.getFatherCategoryId());
			List<GetCommodityCategoryOutput> outputList = getCommodityCategoryByIdAp
					.execute(input1);
			
			if (!Utility.isEmptyList(outputList)) {
				categoryInfo.setFatherCateName(outputList.get(0).getCategoryName());
			}
			GetCommodityCategoryByFatherIdInput input2 = new GetCommodityCategoryByFatherIdInput();
			input2.setFatherID(outPut.getCategoryId());
			Long cateCount = (long) getCommodityCategoryByFatherIdAp.execute(
					input2).size();
			categoryInfo.setCateCount(cateCount);
			categoryInfo.setFatherCateId(outPut.getFatherCategoryId());
			categoryInfo.setNameJpn(outPut.getNameJpn());
			categoryInfo.setSozaiJpn(outPut.getSozaiJpn());
			categoryInfo.setSozaiChn(outPut.getSozaiChn());
			categoryInfo.setKakaku(outPut.getKakaku());
			categortInfoList.add(categoryInfo);
		}
		setSessionAttribute("V030101SaveForm", f030101);
		F030101 searchForm = new F030101();
		searchForm.setCateName(f030101.getCateName());
		setSessionAttribute("V030101SearchForm", searchForm);
	}

	protected void init() {
		setTitle("V030101:商品分类");
	}

	protected void isValidated() throws Exception {
	}

	public F030101 getF030101() {
		return f030101;
	}

	public void setF030101(F030101 f030101) {
		this.f030101 = f030101;
	}

	public String getSearchMode() {
		return searchMode;
	}

	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

}
