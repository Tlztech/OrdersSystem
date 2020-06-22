package com.rakuten.r1001.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.F100101;
import com.rakuten.r1001.form.OrderList;

public class A10010102Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100101 f100101 = null;
	A1001Common a1001Common = new A1001Common();
	private String type = null;

	protected void exec() throws Exception {
		List<OrderList> orderList = a1001Common.getOrderList(f100101, type);
		f100101.setResultCount(String.valueOf(orderList.size()));
		f100101.setOrderList(orderList);
	}

	protected void init() {
		setTitle("V100101:注文一覧");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F100101 getF100101() {
		return f100101;
	}

	public void setF100101(F100101 f100101) {
		this.f100101 = f100101;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
