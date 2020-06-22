package com.rakuten.r1001.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.r1001.form.BunnouhasokaList;
import com.rakuten.r1001.form.F100102;
import com.rakuten.util.Utility;

public class A10010205Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100102 f100102 = null;
	OrderCommon orderCommon = new OrderCommon();
	private String orderNo = null;

	protected void exec() throws Exception {
		orderNo = f100102.getJuchubango();
		List<BunnouhasokaList> bunnouhasokaList = f100102.getBunnouhasokaList();
		List<String[]> hasoList = new ArrayList<String[]>();
		for (BunnouhasokaList hasoka : bunnouhasokaList) {
			if (hasoka.isCheckdFlg()) {
				hasoList.add(new String[] { hasoka.getShohinbango(),
						hasoka.getKosu() });
			}
		}
		List<String> messageList = orderCommon.hassou(hasoList,
				f100102.getToiawasebango_bunnou(),
				f100102.getHaisokaisha_bunnou(), orderNo, "1",
				f100102.getHaisohoho_bunnou(),"");
		if (!Utility.isEmptyList(messageList)) {
			for (String msg : messageList) {
				addActionError(msg);
			}
		} else {
			orderCommon.checkIsShorizumi(orderNo);
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

}
