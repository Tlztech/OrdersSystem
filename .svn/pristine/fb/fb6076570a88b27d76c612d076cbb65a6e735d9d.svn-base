package com.rakuten.r1001.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.r1001.form.F100102;
import com.rakuten.r1001.form.HenpinList;
import com.rakuten.util.Utility;

public class A10010206Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100102 f100102 = null;
	OrderCommon orderCommon = new OrderCommon();
	private String orderNo = null;

	protected void exec() throws Exception {
		orderNo = f100102.getJuchubango();

		List<String[]> henpinList = new ArrayList<String[]>();
		List<HenpinList> list = f100102.getHenpinkaList();
		for (HenpinList henpin : list) {
			if (henpin.isCheckdFlg()) {
				henpinList.add(new String[] { henpin.getShohinbango(),
						henpin.getHenpinkosu(), henpin.getHassoushubetsu(),
						henpin.isSairiyokano() == true ? "1" : "0",
						henpin.isKakuninhituyo() == true ? "1" : "0" });
			}
		}
		String[] henpinriyuTenpo = f100102.getHenpinriyuTenpo();
		String[] henpinriyuOkyaku = f100102.getHenpinriyuOkyaku();
		String henpinriyuTenpoSonota = f100102.getHenpinriyuTenpoSonota();
		String henpinriyuOkyakuSonota = f100102.getHenpinriyuOkyakuSonota();
		String shokisoryofutan = f100102.getHenpinsoryofutanshokihasou();
		String uketorisoryofutan = f100102.getHenpinsoryofutanuketori();
		String henkinFlg = f100102.getHenpinhenkinhituyoFlg();
		String henkinkingaku = f100102.getHenpinhenkinkigaku();
		String biko = f100102.getHenpinbiko();
		List<String> messageList = orderCommon.addHenpin(henpinList,
				henpinriyuTenpo, henpinriyuOkyaku, henpinriyuTenpoSonota,
				henpinriyuOkyakuSonota, shokisoryofutan, uketorisoryofutan,
				henkinFlg, henkinkingaku, biko, orderNo);
		if (!Utility.isEmptyList(messageList)) {
			for (String msg : messageList) {
				addActionError(msg);
			}
		} else {
			orderCommon.checkIsShorizumi(orderNo);
			orderCommon.setStatus("1", "5", orderNo);
			orderCommon.setStatus("4", "1", orderNo);
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
