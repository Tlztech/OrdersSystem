package com.rakuten.r1302.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1302.form.F130201;
import com.rakuten.r1302.form.OrderList;
import com.rakuten.util.Utility;

public class A13020105Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F130201 f130201 = null;

	protected void exec() throws Exception {
		List<OrderList> tempList = (List<OrderList>) getSessionAttribute("orderList");
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

		List<OrderList> orderList = null;
		if ((Integer.valueOf(getGoPage()) - 1) * 15 + 15 <= tempList.size()) {
			orderList = tempList.subList(
					(Integer.valueOf(getGoPage()) - 1) * 15,
					(Integer.valueOf(getGoPage()) - 1) * 15 + 15);
		} else {
			orderList = tempList.subList(
					(Integer.valueOf(getGoPage()) - 1) * 15, tempList.size());
		}
		f130201.setOrderList(orderList);

	}

	protected void init() throws Exception {
		setTitle("V130201:発送処理一覧");
	}

	protected void isValidated() throws Exception {
		if (!Utility.isEmptyString(getNowPage())
				&& !Utility.isEmptyString(getAllPage())) {
			if ("go".equals(getPage())) {
				try {
					if (Integer.valueOf(getAllPage()) < Integer
							.valueOf(getGoPage())) {
						addError("goPage", "跳转页面设置有误！");
					}
				} catch (Exception e) {
					addError("goPage", "跳转页面设置有误！");
				}
			} else if ("last".equals(getPage())) {
				if (Integer.valueOf(getNowPage()) - 1 < 1) {
					addError(null, "没有上一页了");
				}
			} else if ("next".equals(getPage())) {
				if (Integer.valueOf(getNowPage()) + 1 > Integer
						.valueOf(getAllPage())) {
					addError(null, "没有下一页了");
				}
			}
		} else {
			addError(null, "没有数据！");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the f130201
	 */
	public F130201 getF130201() {
		return f130201;
	}

	/**
	 * @param f130201
	 *            the f130201 to set
	 */
	public void setF130201(F130201 f130201) {
		this.f130201 = f130201;
	}

}
