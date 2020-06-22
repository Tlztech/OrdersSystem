package com.rakuten.r1001.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.F100103;
import com.rakuten.r1001.form.ShohinList;

public class A10010305Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	OrderCommon orderCommon = new OrderCommon();
	A1001Common a1001Common = new A1001Common();
	F100103 f100103 = null;
	private String orderNo = null;

	protected void exec() throws Exception {

		List<ShohinList> shohinList = f100103.getShohinList();
		int index = Integer.valueOf(getRowIndex());
		shohinList.remove(index);
		
	}

	protected void init() {
		setTitle("V100103:" + orderNo + " の修正");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f100103
	 */
	public F100103 getF100103() {
		return f100103;
	}

	/**
	 * @param f100103
	 *            the f100103 to set
	 */
	public void setF100103(F100103 f100103) {
		this.f100103 = f100103;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
