package com.rakuten.r0702.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0702.form.F070201;
import com.rakuten.r0702.form.NyukaList;

public class A07020108Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	List<NyukaList> nyukaList = null;
	F070201 f070201 = null;

	private String disabledFlg = "false";

	@SuppressWarnings("unchecked")
	protected void exec() throws Exception {
		List<NyukaList> tempList = (List<NyukaList>) getSessionAttribute("nyukaList");

		if ("first".equals(getPage())) {
			setGoPage("1");
		} else if ("end".equals(getPage())) {
			setGoPage(String.valueOf(getAllPage()));
		} else if ("next".equals(getPage())) {
			setGoPage(String.valueOf(Integer.valueOf(getNowPage()) + 1));
		} else if ("last".equals(getPage())) {
			setGoPage(String.valueOf(Integer.valueOf(getNowPage()) - 1));
		}

		setNowPage(getGoPage());
		if ((Integer.valueOf(getGoPage()) - 1) * 15 + 15 <= tempList.size()) {
			nyukaList = tempList.subList(
					(Integer.valueOf(getGoPage()) - 1) * 15,
					(Integer.valueOf(getGoPage()) - 1) * 15 + 15);
		} else {
			nyukaList = tempList.subList(
					(Integer.valueOf(getGoPage()) - 1) * 15, tempList.size());
		}
		f070201.setNyukaList(nyukaList);

	}

	protected void init() {
		setTitle("V070201:进货列表");
	}

	protected void isValidated() throws Exception {
		if ("0".equals(getNowPage())) {
			addError(null, "无法跳转！");
		} else {
			if ("go".equals(getPage())) {
				try {
					if (Integer.valueOf(getAllPage()) < Integer
							.valueOf(getGoPage())) {
						addError("goPage", "跳转页面设置有误！");
					}
				} catch (Exception e) {
					addError("goPage", "跳转页面设置有误！");
				}
			}

			else if ("last".equals(getPage())) {
				if (Integer.valueOf(getNowPage()) - 1 < 1) {
					addError(null, "没有上一页了");
				}
			} else if ("next".equals(getPage())) {
				if (Integer.valueOf(getNowPage()) + 1 > Integer
						.valueOf(getAllPage())) {
					addError(null, "没有下一页了");
				}
			}
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f070201
	 */
	public F070201 getF070201() {
		return f070201;
	}

	/**
	 * @param f070201
	 *            the f070201 to set
	 */
	public void setF070201(F070201 f070201) {
		this.f070201 = f070201;
	}

	public String getDisabledFlg() {
		return disabledFlg;
	}

	public void setDisabledFlg(String disabledFlg) {
		this.disabledFlg = disabledFlg;
	}

}
