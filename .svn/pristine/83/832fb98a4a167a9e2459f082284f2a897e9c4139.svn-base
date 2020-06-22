package com.rakuten.r1001.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.F100103;
import com.rakuten.r1001.form.ShohinList;
import com.rakuten.util.Utility;

public class A10010302Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	OrderCommon orderCommon = new OrderCommon();
	A1001Common a1001Common = new A1001Common();
	F100103 f100103 = null;
	private String orderNo = null;

	protected void exec() throws Exception {

		List<String> messageList = orderCommon.updateCommonOrderTbl(f100103,
				orderNo);
		if (!Utility.isEmptyList(messageList)) {
			for (String msg : messageList) {
				addActionError(msg);
			}
		} else {
			orderCommon.checkIsShorizumi(orderNo);
		}
	}

	protected void init() {
		setTitle("V100103:" + orderNo + " の修正");
	}

	protected void isValidated() throws Exception {
		List<ShohinList> shohinList = f100103.getShohinList();
		for (ShohinList shoin : shohinList) {
			if ("0".equals(shoin.getKosu())) {
				addError(null, "個数は0以上でなければらりません！");
			}
		}
		if (Utility.isEmptyList(shohinList)) {
			addError(null, "商品はありません");
		}
		if (Utility.isEmptyString(f100103.getPointriyo())) {
			f100103.setPointriyo("0");
		}
		if (Utility.isEmptyString(f100103.getSonotariyogaku())) {
			f100103.setSonotariyogaku("0");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
		List<ShohinList> shohinList = f100103.getShohinList();
		if (!Utility.isEmptyList(shohinList)) {
			for (ShohinList shoin : shohinList) {
				if (Utility.isEmptyString(shoin.getTankaku())) {
					addError(null, "単価は必須項目です");
				}
				if (Utility.isEmptyString(shoin.getKosu())) {
					addError(null, "個数は必須項目です");
				}
			}
		}
		if (Utility.isEmptyString(f100103.getChumonshanamae())) {
			addError(null, "注文者お名前は必須項目です");
		}
		if (Utility.isEmptyString(f100103.getChumonshameiji())) {
			addError(null, "注文者お名前は必須項目です");
		}
		if (Utility.isEmptyString(f100103.getSofusakinamae())) {
			addError(null, "送付先お名前は必須項目です");
		}
		if (Utility.isEmptyString(f100103.getSofusakimeiji())) {
			addError(null, "送付先お名前は必須項目です");
		}
		if (Utility.isEmptyString(f100103.getSofusakinamaefurigana())) {
			addError(null, "送付先お名前フリガナは必須項目です");
		}
		if (Utility.isEmptyString(f100103.getSofusakimeijifurigana())) {
			addError(null, "送付先お名前フリガナは必須項目です");
		}
		if (Utility.isEmptyString(f100103.getSofusakijushotodofuken())) {
			addError(null, "送付先住所都道府県は必須項目です");
		}
		if (Utility.isEmptyString(f100103.getSofusakijushotoshiku())) {
			addError(null, "送付先住所都市区は必須項目です");
		}
		// if (Utility.isEmptyString(f100103.getSofusakijushochoijo())) {
		// addError(null, "送付先住所丁以降は必須項目です");
		// }
		if (Utility.isEmptyString(f100103.getSofusakiyubinbango1())) {
			addError(null, "送付先住所郵便番号は必須項目です");
		}
		if (Utility.isEmptyString(f100103.getSofusakiyubinbango2())) {
			addError(null, "送付先住所郵便番号は必須項目です");
		}
		if (Utility.isEmptyString(f100103.getSofusakidenwabango1())) {
			addError(null, "送付先住所電話番号は必須項目です");
		}
		if (Utility.isEmptyString(f100103.getSofusakidenwabango2())) {
			addError(null, "送付先住所電話番号は必須項目です");
		}
		if (Utility.isEmptyString(f100103.getSofusakidenwabango3())) {
			addError(null, "送付先住所電話番号は必須項目です");
		}
		if (Utility.isEmptyList(f100103.getShohinList())) {
			addError(null, "商品リストはありません");
		}
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
