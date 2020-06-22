package com.rakuten.r0703.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0703.form.F070301;
import com.rakuten.r0703.form.NyukaList;

public class A07030108Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	List<NyukaList> nyukaList = null;
	F070301 f070301 = null;

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
		f070301.setNyukaList(nyukaList);

	}

	protected void init() {
		setTitle("V070301:进货列表");
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
	 * @return the f070301
	 */
	public F070301 getF070301() {
		return f070301;
	}

	/**
	 * @param f070301
	 *            the f070301 to set
	 */
	public void setF070301(F070301 f070301) {
		this.f070301 = f070301;
	}

	public String getDisabledFlg() {
		return disabledFlg;
	}

	public void setDisabledFlg(String disabledFlg) {
		this.disabledFlg = disabledFlg;
	}

}
