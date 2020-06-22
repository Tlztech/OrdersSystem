package com.rakuten.r1001.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.F100103;
import com.rakuten.r1001.form.ShohinList;
import com.rakuten.util.Utility;

public class A10010303Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	OrderCommon orderCommon = new OrderCommon();
	A1001Common a1001Common = new A1001Common();
	F100103 f100103 = null;
	private String orderNo = null;

	protected void exec() throws Exception {

		String gokeishouhin = "0";
		String gokeizei = "0";
		String gokeisouryou = "0";
		String gokeidaibikiryou = "0";
		String seikyukingaku = "0";

		List<ShohinList> shohinList = f100103.getShohinList();
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
			if ("代金引換".equals(f100103.getOshiharaihoho())
					&& "込".equals(shoin.getDaibiki())) {
				daibikiryokomiAri = true;
			}
		}

		if (!soryokomiAri
				&& (Long.valueOf(gokeishouhin) + Long.valueOf(gokeizei)) < Utility
						.getSorokomiKaku()) {
			String info[] = Utility.getSiteAndTenpo(orderNo);
			gokeisouryou = Utility.getSoryo(f100103.getHaisohoho(), info[0],
					info[1]);
		}
		if (!daibikiryokomiAri && "代金引換".equals(f100103.getOshiharaihoho())) {
			gokeidaibikiryou = Utility.getDaibikiryo(String.valueOf(Long
					.valueOf(gokeishouhin)
					+ Long.valueOf(gokeizei)
					+ Long.valueOf(gokeisouryou)
					- Long.valueOf(f100103.getPointriyo())
					- Long.valueOf(f100103.getSonotariyogaku())));
		}

		seikyukingaku = String.valueOf(Long.valueOf(gokeishouhin)
				+ Long.valueOf(gokeizei) + Long.valueOf(gokeisouryou)
				+ Long.valueOf(gokeidaibikiryou)
				- Long.valueOf(f100103.getPointriyo())
				- Long.valueOf(f100103.getSonotariyogaku()));

		f100103.setGokeishouhin(gokeishouhin);
		f100103.setGokeizei(gokeizei);
		f100103.setGokeisouryou(gokeisouryou);
		f100103.setGokeidaibikiryou(gokeidaibikiryou);
		f100103.setSeikyukingaku(seikyukingaku);

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
		for (ShohinList shoin : shohinList) {
			if (Utility.isEmptyString(shoin.getTankaku())) {
				addError(null, "単価は必須項目です");
			}
			if (Utility.isEmptyString(shoin.getKosu())) {
				addError(null, "個数は必須項目です");
			}
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
