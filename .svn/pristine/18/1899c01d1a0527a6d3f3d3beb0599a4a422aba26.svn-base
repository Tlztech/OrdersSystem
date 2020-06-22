package com.rakuten.r1001.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.r1001.form.F100102;
import com.rakuten.util.Utility;

public class A10010221Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100102 f100102 = null;
	OrderCommon orderCommon = new OrderCommon();
	private String orderNo = null;
	private String messageInfo = null;

	protected void exec() throws Exception {
		orderNo = f100102.getJuchubango();
		String tuikaOrderNo = f100102.getJuchubango_dokon().trim();
		messageInfo = "";

		List<String> messageList = orderCommon.dokonOrder(orderNo,
				tuikaOrderNo, f100102.getSite(), f100102.getTenpobetsu());
		if (!Utility.isEmptyList(messageList)) {
			for (String msg : messageList) {
				addActionError(msg);
			}
		} else {
			setMessageInfo("update successfully");
		}
	}

	protected void init() {
		setTitle("V100102:" + f100102.getJuchubango() + "の詳細情報");
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F100102 getF100102() {
		return f100102;
	}

	public void setF100102(F100102 f100102) {
		this.f100102 = f100102;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}

}
