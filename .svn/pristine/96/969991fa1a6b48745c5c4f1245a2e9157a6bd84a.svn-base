package com.rakuten.r1301.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1301.form.F130101;
import com.rakuten.r1301.form.OrderList;
import com.rakuten.util.Utility;

public class A13010107Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F130101 f130101 = null;

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
		f130101.setOrderList(orderList);

	}

	protected void init() throws Exception {
		setTitle("V130101:発送処理一覧");
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
	 * @return the f130101
	 */
	public F130101 getF130101() {
		return f130101;
	}

	/**
	 * @param f130101
	 *            the f130101 to set
	 */
	public void setF130101(F130101 f130101) {
		this.f130101 = f130101;
	}

}
