package com.rakuten.r0601.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.UpdWayBillAp;
import com.rakuten.r0601.bean.AddWayBillApDetailInput;
import com.rakuten.r0601.bean.AddWayBillApInput;
import com.rakuten.r0601.form.CommodityList;
import com.rakuten.r0601.form.F060102;
import com.rakuten.util.Utility;

public class A06010402Action extends BaseAction {
	private static final long serialVersionUID = 1L;

	private F060102 f060102 = null;
	private String passFlg = null;

	protected void exec() throws Exception {
		List<CommodityList> commodityList = f060102.getCommodityList();
		UpdWayBillAp updWayBillAp = new UpdWayBillAp();
		AddWayBillApInput input = new AddWayBillApInput();
		input.setWaybillNo(f060102.getWaybillNo());
		input.setLogistics(f060102.getLogistics());
		input.setDelieverDay(f060102.getDeliverDay());
		if (Utility.isEmptyString(f060102.getWeight())) {
			input.setWeight("0");
		} else {
			input.setWeight(f060102.getWeight());
		}
		input.setFreight(f060102.getFreight());

		List<AddWayBillApDetailInput> inputList = new ArrayList<AddWayBillApDetailInput>();
		input.setDetailList(inputList);
		AddWayBillApDetailInput detail = null;
		for (int i = 0; i < commodityList.size(); i++) {
			detail = new AddWayBillApDetailInput();
			detail.setCommodityId(commodityList.get(i).getCommodityId());
			detail.setQuantity(commodityList.get(i).getQuantity());
			detail.setRemarks(commodityList.get(i).getRemarks());
			inputList.add(detail);
		}
		if (updWayBillAp.execute(input)) {
			setPassFlg("1");
		} else {
			addError(null, "系统错误");
		}
	}

	protected void init() {
		setTitle("V060103:修改发货单");
	}

	protected void isValidated() throws Exception {
		if (Utility.isEmptyString(f060102.getDeliverDay())) {
			addError("f060102.deliverDay", "请填写发货日！");
		}
		if (Utility.isEmptyString(f060102.getWaybillNo())) {
			addError("f060102.waybillNo", "请填写运单号！");
		}
		List<CommodityList> commodityList = f060102.getCommodityList();
		if (Utility.isEmptyList(commodityList)) {
			addError(null, "详细列表为空！");
		}

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F060102 getF060102() {
		return f060102;
	}

	public void setF060102(F060102 f060102) {
		this.f060102 = f060102;
	}

	public String getPassFlg() {
		return passFlg;
	}

	public void setPassFlg(String passFlg) {
		this.passFlg = passFlg;
	}

}
