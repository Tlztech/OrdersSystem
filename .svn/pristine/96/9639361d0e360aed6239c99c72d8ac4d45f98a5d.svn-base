package com.rakuten.r0301.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0301.ap.AddCommodityCategoryAp;
import com.rakuten.r0301.ap.DelCommodityCategoryAp;
import com.rakuten.r0301.ap.GetCommodityCategoryByNameAp;
import com.rakuten.r0301.ap.GetCommodityCategoryMaxIdAp;
import com.rakuten.r0301.ap.UpdCommodityCategoryAp;
import com.rakuten.r0301.bean.AddCommodityCategoryInput;
import com.rakuten.r0301.bean.GetCommodityCategoryByNameInput;
import com.rakuten.r0301.bean.GetCommodityCategoryOutput;
import com.rakuten.r0301.bean.UpdCommodityCategoryInput;
import com.rakuten.r0301.form.F030102;
import com.rakuten.r0301.form.F030103;
import com.rakuten.util.Utility;

public class A03010202Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F030102 f030102 = null;
	private F030103 f030103 = null;

	@Override
	protected void exec() throws Exception {

		String mode = getMode();
		String cateId = null;
		if ("0".equals(mode) || "3".equals(mode)) {
			AddCommodityCategoryInput input = new AddCommodityCategoryInput();
			AddCommodityCategoryAp addCommodityCategory = new AddCommodityCategoryAp();
			GetCommodityCategoryMaxIdAp getCommodityCategoryMaxId = new GetCommodityCategoryMaxIdAp();
			cateId = getCommodityCategoryMaxId.execute();
			input.setCategoryId(cateId);
			input.setCategoryName(f030102.getCateName());
			input.setFatherCategoryId(f030102.getFatherCateId());
			input.setNameJpn(f030102.getNameJpn());
			input.setSozaiJpn(f030102.getSozaiJpn());
			input.setSozaiChn(f030102.getSozaiChn());
			input.setKakaku(f030102.getKakaku());
			addCommodityCategory.execute(input);

		} else if ("1".equals(mode)) {
			UpdCommodityCategoryInput input = new UpdCommodityCategoryInput();
			UpdCommodityCategoryAp updCommodityCategoryAp = new UpdCommodityCategoryAp();
			cateId = f030102.getCateId();
			input.setCategoryId(cateId);
			input.setCategoryName(f030102.getCateName());
			input.setFatherCategoryId(f030102.getFatherCateId());
			input.setNameJpn(f030102.getNameJpn());
			input.setSozaiJpn(f030102.getSozaiJpn());
			input.setSozaiChn(f030102.getSozaiChn());
			input.setKakaku(f030102.getKakaku());
			updCommodityCategoryAp.execute(input);
		} else if ("2".equals(mode)) {
			DelCommodityCategoryAp delCommodityCategoryAp = new DelCommodityCategoryAp();
			cateId = f030102.getCateId();
			delCommodityCategoryAp.execute(cateId);
		}
		f030103 = new F030103();
		f030103.setCateId(cateId);
		f030103.setCateName(f030102.getCateName());
		f030103.setFatherCateName(f030102.getFatherCateName());
		f030103.setNameJpn(f030102.getNameJpn());
		f030103.setSozaiJpn(f030102.getSozaiJpn());
		f030103.setSozaiChn(f030102.getSozaiChn());
		f030103.setKakaku(f030102.getKakaku());
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
		String mode = getMode();
		if ("0".equals(mode) || "3".equals(mode)) {
			GetCommodityCategoryByNameAp getCommodityCategoryByNameAp = new GetCommodityCategoryByNameAp();
			String fatherCateName = f030102.getFatherCateName();
			String cateName = f030102.getCateName();
			if (!Utility.isEmptyString(fatherCateName)) {
				GetCommodityCategoryByNameInput input = new GetCommodityCategoryByNameInput();
				input.setName(fatherCateName);
				List<GetCommodityCategoryOutput> commodityCategoryOutputList = getCommodityCategoryByNameAp
						.execute(input);
				String fatherCateId = null;
				if (!Utility.isEmptyList(commodityCategoryOutputList)) {
					fatherCateId = commodityCategoryOutputList.get(0)
							.getCategoryId();
				}
				if (Utility.isEmptyString(fatherCateId)) {
					addError("f030102.fatherCateName",
							getMessage("M03010201E", null));
					return;
				}
				f030102.setFatherCateId(fatherCateId);
			}
			GetCommodityCategoryByNameInput input = new GetCommodityCategoryByNameInput();
			input.setName(cateName);
			List<GetCommodityCategoryOutput> commodityCategoryOutputList = getCommodityCategoryByNameAp
					.execute(input);
			String checkId = null;
			if (!Utility.isEmptyList(commodityCategoryOutputList)) {
				checkId = commodityCategoryOutputList.get(0).getCategoryId();
			}
			if (!Utility.isEmptyString(checkId)) {
				addError("f030102.cateName", getMessage("M03010202E", null));
			}
		}
	}

	public F030102 getF030102() {
		return f030102;
	}

	public void setF030102(F030102 f030102) {
		this.f030102 = f030102;
	}

	public F030103 getF030103() {
		return f030103;
	}

	public void setF030103(F030103 f030103) {
		this.f030103 = f030103;
	}

	@Override
	protected void fieldCheck() throws Exception {
		if (Utility.isEmptyString(f030102.getCateName())) {
			addError("f030102.cateName",
					getMessage("M00000001E", new String[] { "分类名称" }));
		}
	}

}
