package com.rakuten.r1001.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.r1001.form.F100102;
import com.rakuten.r1001.form.HasomachiList;
import com.rakuten.util.Utility;

public class A10010203Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100102 f100102 = null;
	OrderCommon orderCommon = new OrderCommon();
	private String orderNo = null;

	protected void exec() throws Exception {
		orderNo = f100102.getJuchubango();
		List<HasomachiList> hasomachiList = f100102.getHasomachiList();
		List<String[]> hasoList = new ArrayList<String[]>();
		for (HasomachiList hasomachi : hasomachiList) {
			boolean ariflg = false;
			for (int i = 0; i < hasoList.size(); i++) {
				if (hasomachi.getShohinbango().equals(hasoList.get(i)[0])) {
					hasoList.get(i)[1] = String.valueOf(Integer
							.valueOf(hasoList.get(i)[1])
							+ Integer.valueOf(hasomachi.getKosu()));
					ariflg = true;
					break;
				}
			}
			if (!ariflg) {
				hasoList.add(new String[] { hasomachi.getShohinbango(),
						hasomachi.getKosu() });
			}
		}

		List<String> messageList = orderCommon.hassou(hasoList,
				f100102.getToiawasebango_futsuhasou(),
				f100102.getHaisokaisha_futsuhasou(), orderNo, "0",
				f100102.getHaisohoho_futsuhasou(), "");
		if (!Utility.isEmptyList(messageList)) {
			for (String msg : messageList) {
				addActionError(msg);
			}
		} else {
			orderCommon.setStatus("1", "3", orderNo);
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
