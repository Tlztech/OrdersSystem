package com.rakuten.r0301.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0301.form.CategoryInfo;
import com.rakuten.r0301.form.F030101;
import com.rakuten.r0301.form.F030102;

public class A03010201Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F030101 f030101 = null;
	private F030102 f030102 = null;

	protected void exec() {
		String mode = getMode();
		String rowIndex = getRowIndex();
		if (!"0".equals(mode)) {
			List<CategoryInfo> categoryList = f030101.getCategoryList();
			CategoryInfo categoryInfo = categoryList.get(Integer
					.valueOf(rowIndex));
			String cateId = categoryInfo.getCateId();
			String cateName = categoryInfo.getCateName();
			String fatherCateName = categoryInfo.getFatherCateName();
			String nameJpn = categoryInfo.getNameJpn();
			String sozaiJpn = categoryInfo.getSozaiJpn();
			String sozaiChn = categoryInfo.getSozaiChn();
			String kakaku = categoryInfo.getKakaku();
			f030102 = new F030102();
			if (!"3".equals(mode)) {
				f030102.setCateId(cateId);
				f030102.setCateName(cateName);
				f030102.setFatherCateName(fatherCateName);
				f030102.setFatherCateId(categoryInfo.getFatherCateId());
				f030102.setNameJpn(nameJpn);
				f030102.setSozaiJpn(sozaiJpn);
				f030102.setSozaiChn(sozaiChn);
				f030102.setKakaku(kakaku);
			} else {
				f030102.setFatherCateName(cateName);
			}

		}
	}

	protected void init() {
		String mode = getMode();
		if ("0".equals(mode)) {
			setTitle("V030102:添加分类");
		} else if ("1".equals(mode)) {
			setTitle("V030102:修改分类");
		} else if ("2".equals(mode)) {
			setTitle("V030102:删除分类");
		} else if ("3".equals(mode)) {
			setTitle("V030102:添加子类");
		}
	}

	protected void isValidated() throws Exception {

	}

	public F030101 getF030101() {
		return f030101;
	}

	public void setF030101(F030101 f030101) {
		this.f030101 = f030101;
	}

	public F030102 getF030102() {
		return f030102;
	}

	public void setF030102(F030102 f030102) {
		this.f030102 = f030102;
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

}
