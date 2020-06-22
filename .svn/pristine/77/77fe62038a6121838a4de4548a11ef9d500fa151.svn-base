package com.rakuten.r1301.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1301.common.A130101Common;
import com.rakuten.r1301.form.F130101;
import com.rakuten.r1301.form.OrderList;

public class A13010201Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F130101 f130101 = null;

	protected void exec() throws Exception {
		f130101 = new F130101();
		A130101Common a130101Common = new A130101Common();
		a130101Common.setDisplay(f130101);

		List<OrderList> orderList_temp = f130101.getOrderList();
		List<OrderList> orderList = null;
		setSessionAttribute("orderList", orderList_temp);
		if (orderList_temp.size() > 50) {
			orderList = orderList_temp.subList(0, 50);
		} else {
			orderList = orderList_temp;
		}
		f130101.setOrderList(orderList);
		setNowPage("1");
		setAllPage(String
				.valueOf(((double) orderList_temp.size() / (double) 50) > (orderList_temp
						.size() / 50) ? orderList_temp.size() / 50 + 1
						: orderList_temp.size() / 50));
		setGoPage("");
	}

	protected void init() throws Exception {
		setTitle("V130101:∞kÀÕÑI¿Ì“ª”E");
	}

	protected void isValidated() throws Exception {

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
