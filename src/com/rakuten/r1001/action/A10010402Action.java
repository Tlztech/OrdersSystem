package com.rakuten.r1001.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.F100104;
import com.rakuten.r1001.form.ShohinList;
import com.rakuten.util.Utility;

public class A10010402Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	OrderCommon orderCommon = new OrderCommon();
	A1001Common a1001Common = new A1001Common();
	F100104 f100104 = null;
	private String orderNo = null;

	protected void exec() throws Exception {
		String gokeishouhin = "0";
		String gokeizei = "0";
		String gokeisouryou = "0";
		String gokeidaibikiryou = "0";
		String seikyukingaku = "0";

		List<ShohinList> shohinList = f100104.getShohinList();
		boolean soryokomiAri = false;
		boolean daibikiryokomiAri = false;

		for (ShohinList shoin : shohinList) {
			gokeishouhin = String.valueOf(Long.valueOf(gokeishouhin)
					+ Long.valueOf(shoin.getTankaku())
					* Long.valueOf(shoin.getKosu()));

			shoin.setShoukei(String.valueOf(Long.valueOf(shoin.getTankaku())
					* Long.valueOf(shoin.getKosu())));

			String zeibetsu = shoin.getZei();
			String soryobetsu = shoin.getSourou();
			if ("別".equals(zeibetsu)) {
				gokeizei = String.valueOf(Long.valueOf(gokeizei)
						+ Utility.getZei(shoin.getShoukei()));
			}
			if ("込".equals(soryobetsu)) {
				soryokomiAri = true;
			}
			if ("代金引換".equals(f100104.getOshiharaihoho())
					&& "込".equals(shoin.getDaibiki())) {
				daibikiryokomiAri = true;
			}
		}
		if (!soryokomiAri
				&& (Long.valueOf(gokeishouhin) + Long.valueOf(gokeizei)) < Utility
						.getSorokomiKaku()) {
			gokeisouryou = Utility.getSoryo(f100104.getHaisohoho(),
					f100104.getSite(), f100104.getTenpo());
		}
		if (!daibikiryokomiAri && "代金引換".equals(f100104.getOshiharaihoho())) {
			gokeidaibikiryou = Utility.getDaibikiryo(String.valueOf(Long
					.valueOf(gokeishouhin)
					+ Long.valueOf(gokeizei)
					+ Long.valueOf(gokeisouryou)
					- Long.valueOf(f100104.getPointriyo())
					- Long.valueOf(f100104.getSonotariyogaku())));
		}

		seikyukingaku = String.valueOf(Long.valueOf(gokeishouhin)
				+ Long.valueOf(gokeizei) + Long.valueOf(gokeisouryou)
				+ Long.valueOf(gokeidaibikiryou)
				- Long.valueOf(f100104.getPointriyo())
				- Long.valueOf(f100104.getSonotariyogaku()));

		f100104.setGokeishouhin(gokeishouhin);
		f100104.setGokeizei(gokeizei);
		f100104.setGokeisouryou(gokeisouryou);
		f100104.setGokeidaibikiryou(gokeidaibikiryou);
		f100104.setSeikyukingaku(seikyukingaku);

		orderNo = f100104.getJuchubango();
		List<String> messageList = orderCommon.insertCommonOrderTbl(f100104,
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
		setTitle("V100104:新規");
	}

	protected void isValidated() throws Exception {
		List<ShohinList> shohinList = f100104.getShohinList();
		for (ShohinList shoin : shohinList) {
			if ("0".equals(shoin.getKosu())) {
				addError(null, "個数は0以上でなければらりません！");
			}
		}
		if (Utility.isEmptyList(shohinList)) {
			addError(null, "商品はありません");
		}
		if (Utility.isOrderExist(f100104.getJuchubango())) {
			addError(null, f100104.getJuchubango() + "はすでに存在します");
		}
		if (Utility.isEmptyString(f100104.getPointriyo())) {
			f100104.setPointriyo("0");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
		List<ShohinList> shohinList = f100104.getShohinList();
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
		if (Utility.isEmptyString(f100104.getJuchubango())) {
			addError(null, "受注番号は必須項目です");
		}
		if (Utility.isEmptyString(f100104.getChumonshanamae())) {
			addError(null, "注文者お名前は必須項目です");
		}
		if (Utility.isEmptyString(f100104.getChumonshameiji())) {
			addError(null, "注文者お名前は必須項目です");
		}
		if (Utility.isEmptyString(f100104.getSofusakinamae())) {
			addError(null, "送付先お名前は必須項目です");
		}
		if (Utility.isEmptyString(f100104.getSofusakimeiji())) {
			addError(null, "送付先お名前は必須項目です");
		}
		if (Utility.isEmptyString(f100104.getSofusakinamaefurigana())) {
			addError(null, "送付先お名前フリガナは必須項目です");
		}
		if (Utility.isEmptyString(f100104.getSofusakimeijifurigana())) {
			addError(null, "送付先お名前フリガナは必須項目です");
		}
		if (Utility.isEmptyString(f100104.getSofusakijushotodofuken())) {
			addError(null, "送付先住所都道府県は必須項目です");
		}
		if (Utility.isEmptyString(f100104.getSofusakijushotoshiku())) {
			addError(null, "送付先住所都市区は必須項目です");
		}
		// if (Utility.isEmptyString(f100104.getSofusakijushochoijo())) {
		// addError(null, "送付先住所丁以降は必須項目です");
		// }
		if (Utility.isEmptyString(f100104.getSofusakiyubinbango1())) {
			addError(null, "送付先住所郵便番号は必須項目です");
		}
		if (Utility.isEmptyString(f100104.getSofusakiyubinbango2())) {
			addError(null, "送付先住所郵便番号は必須項目です");
		}
		if (Utility.isEmptyString(f100104.getSofusakidenwabango1())) {
			addError(null, "送付先住所電話番号は必須項目です");
		}
		if (Utility.isEmptyString(f100104.getSofusakidenwabango2())) {
			addError(null, "送付先住所電話番号は必須項目です");
		}
		if (Utility.isEmptyString(f100104.getSofusakidenwabango3())) {
			addError(null, "送付先住所電話番号は必須項目です");
		}
		if (Utility.isEmptyList(f100104.getShohinList())) {
			addError(null, "商品リストはありません");
		}
	}

	/**
	 * @return the orderCommon
	 */
	public OrderCommon getOrderCommon() {
		return orderCommon;
	}

	/**
	 * @param orderCommon
	 *            the orderCommon to set
	 */
	public void setOrderCommon(OrderCommon orderCommon) {
		this.orderCommon = orderCommon;
	}

	/**
	 * @return the a1001Common
	 */
	public A1001Common getA1001Common() {
		return a1001Common;
	}

	/**
	 * @param a1001Common
	 *            the a1001Common to set
	 */
	public void setA1001Common(A1001Common a1001Common) {
		this.a1001Common = a1001Common;
	}

	/**
	 * @return the f100104
	 */
	public F100104 getF100104() {
		return f100104;
	}

	/**
	 * @param f100104
	 *            the f100104 to set
	 */
	public void setF100104(F100104 f100104) {
		this.f100104 = f100104;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
