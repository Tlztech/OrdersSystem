package com.rakuten.r1001.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.r1001.form.F100102;
import com.rakuten.r1001.form.HenpinList;
import com.rakuten.util.Utility;

public class A10010207Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100102 f100102 = null;
	OrderCommon orderCommon = new OrderCommon();
	private String orderNo = null;

	protected void exec() throws Exception {
		orderNo = f100102.getJuchubango();

		List<String[]> henpinshohinArry = new ArrayList<String[]>();
		List<HenpinList> henpinshohinList = f100102
				.getHenpinshuseikaShohinList();
		for (HenpinList henpin : henpinshohinList) {
			if (henpin.isCheckdFlg()) {
				henpinshohinArry.add(new String[] { henpin.getShohinbango(),
						henpin.getHenpinkosu(), henpin.getHassoushubetsu(),
						henpin.isSairiyokano() == true ? "1" : "0",
						henpin.isKakuninhituyo() == true ? "1" : "0" });
			}
		}

		String shoribango_henpinshusei = f100102.getShoribango_henpinshusei();
		String[] henpinriyuTenpo_henpinshusei = f100102
				.getHenpinriyuTenpo_henpinshusei();
		String henpinriyuTenpoSonota_henpinshusei = f100102
				.getHenpinriyuTenpoSonota_henpinshusei();
		String[] henpinriyuOkyaku_henpinshusei = f100102
				.getHenpinriyuOkyaku_henpinshusei();
		String henpinriyuOkyakuSonota_henpinshusei = f100102
				.getHenpinriyuOkyakuSonota_henpinshusei();
		String henpinsoryofutanshokihasou_henpinshusei = f100102
				.getHenpinsoryofutanshokihasou_henpinshusei();
		String henpinsoryofutanuketori_henpinshusei = f100102
				.getHenpinsoryofutanuketori_henpinshusei();
		String henpinhenkinhituyoFlg_henpinshusei = f100102
				.getHenpinhenkinhituyoFlg_henpinshusei();
		String henpinhenkinkigaku_henpinshusei = f100102
				.getHenpinhenkinkigaku_henpinshusei();
		String henpinbiko_henpinshusei = f100102.getHenpinbiko_henpinshusei();

		List<String> messageList = orderCommon.updateHenpin(henpinshohinArry,
				shoribango_henpinshusei, henpinriyuTenpo_henpinshusei,
				henpinriyuTenpoSonota_henpinshusei,
				henpinriyuOkyaku_henpinshusei,
				henpinriyuOkyakuSonota_henpinshusei,
				henpinsoryofutanshokihasou_henpinshusei,
				henpinsoryofutanuketori_henpinshusei,
				henpinhenkinhituyoFlg_henpinshusei,
				henpinhenkinkigaku_henpinshusei, henpinbiko_henpinshusei,
				orderNo);
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
