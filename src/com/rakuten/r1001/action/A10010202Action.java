package com.rakuten.r1001.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;

public class A10010202Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String status = null;
	private String chumonbango = null;
	private String result = null;
	private String statusShubetsu = null;
	OrderCommon orderCommon = new OrderCommon();

	protected void exec() throws Exception {
		int count = orderCommon.setStatus(statusShubetsu, status, chumonbango);
		if (count > 0) {
			result = "true";
		} else {
			result = "false";
		}
	}

	protected void init() {
		setTitle("V100101:注文一覧");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the statusShubetsu
	 */
	public String getStatusShubetsu() {
		return statusShubetsu;
	}

	/**
	 * @param statusShubetsu
	 *            the statusShubetsu to set
	 */
	public void setStatusShubetsu(String statusShubetsu) {
		this.statusShubetsu = statusShubetsu;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChumonbango() {
		return chumonbango;
	}

	public void setChumonbango(String chumonbango) {
		this.chumonbango = chumonbango;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
