package com.rakuten.r1001.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.r1001.form.F100102;
import com.rakuten.util.Utility;

public class A10010213Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100102 f100102 = null;
	OrderCommon orderCommon = new OrderCommon();
	private String orderNo = null;
	private String shoribango = null;

	protected void exec() throws Exception {
		orderNo = f100102.getJuchubango();

		List<String[]> tuikashohinArry = new ArrayList<String[]>();
		String jsonArr = f100102.getJsonArr();
		JSONObject jsonObject = JSONObject.fromObject(jsonArr);
		JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("shohin");

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject object = (JSONObject) jsonArray.get(i);
			String shohinbango = String.valueOf(object.get("shohinbango"));
			String kosu = String.valueOf(object.get("kosu"));
			tuikashohinArry.add(new String[] { shohinbango, kosu });
		}
		shoribango = f100102.getTuikashoribango_shuseihaso();
		String[] tuikariyu_tuikashusei = f100102.getTuikariyu();
		String tuikariyuSonota_tuikashusei = f100102
				.getTuikariyusonota_tuikashusei();
		String tuikasoryofutan_tuikashusei = f100102.getTuikasoryofutan();
		String tuikabiko_tuikashusei = f100102.getTuikabiko();
		String tuikatantosya_tuikashusei = f100102.getTuikatantosya();

		List<String> messageList = orderCommon.updateTuika(tuikashohinArry,
				shoribango, tuikariyu_tuikashusei, tuikariyuSonota_tuikashusei,
				tuikasoryofutan_tuikashusei, tuikabiko_tuikashusei,tuikatantosya_tuikashusei, orderNo);

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
