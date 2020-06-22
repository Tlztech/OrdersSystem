package com.rakuten.r1001.action;

import java.util.Collections;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;

public class A10010204Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	OrderCommon orderCommon = new OrderCommon();
	private String shoribango = null;
	private String result = null;

	protected void isValidated() throws Exception {
		setResult(orderCommon.setSoushinzumi(Collections
				.singletonList(shoribango)));
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the shoribango
	 */
	public String getShoribango() {
		return shoribango;
	}

	/**
	 * @param shoribango
	 *            the shoribango to set
	 */
	public void setShoribango(String shoribango) {
		this.shoribango = shoribango;
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
