package com.rakuten.r0601.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.GetWayBillAp;
import com.rakuten.r0601.ap.GetWayBillDetailByIdAp;
import com.rakuten.r0601.bean.AddWayBillApDetailInput;
import com.rakuten.r0601.bean.AddWayBillApInput;
import com.rakuten.r0601.bean.GetWayBillApDetailOutput;
import com.rakuten.r0601.bean.GetWayBillApInput;
import com.rakuten.r0601.bean.GetWayBillApOutput;
import com.rakuten.r0601.form.CommodityList;
import com.rakuten.r0601.form.F060101;
import com.rakuten.r0601.form.F060102;
import com.rakuten.util.Utility;

public class A06010105Action extends BaseAction {
	private static final long serialVersionUID = 1L;

	private F060101 f060101 = null;
	private F060102 f060102 = null;

	protected void exec() throws Exception {
		int index = Integer.valueOf(getRowIndex());
		String waybillNo = f060101.getWayBillList().get(index).getWaybillNo();
		GetWayBillAp getWayBillAp = new GetWayBillAp();
		GetWayBillApInput input = new GetWayBillApInput();
		input.setWaybillNo(waybillNo);
		GetWayBillApOutput output = getWayBillAp.execute(input).get(0);
		f060102 = new F060102();
		f060102.setDeliverDay(output.getDeliverDay());
		f060102.setWaybillNo(output.getWaybillNo());
		f060102.setLogistics(output.getLogistics());
		f060102.setFreight(output.getFreight());
		f060102.setWeight(output.getWeight());
		f060102.setHid_waybillNo(output.getWaybillNo());

		GetWayBillDetailByIdAp getWayBillDetailByIdAp = new GetWayBillDetailByIdAp();
		List<GetWayBillApDetailOutput> outputList = getWayBillDetailByIdAp
				.execute(waybillNo);
		List<CommodityList> commodityList = new ArrayList<CommodityList>();
		CommodityList commodity = null;
		for (int i = 0; i < outputList.size(); i++) {
			commodity = new CommodityList();
			commodityList.add(commodity);
			commodity.setCommodityId(outputList.get(i).getCommodityId());
			commodity.setQuantity(outputList.get(i).getQuantity());
			commodity.setRemarks(outputList.get(i).getRemarks());
			String picurl = null;

			String commodityId = "";
			if (outputList.get(i).getCommodityId().indexOf("-") > 0) {
				commodityId = outputList
						.get(i)
						.getCommodityId()
						.substring(0,
								outputList.get(i).getCommodityId().indexOf("-"));
			} else {
				commodityId = outputList.get(i).getCommodityId();
			}

			picurl = Utility.getPicUrl(Utility.getCommodityId(commodityId));
			commodity.setPicUrl(picurl);
		}
		f060102.setCommodityList(commodityList);

		List<CommodityList> commodityList1 = f060102.getCommodityList();
		AddWayBillApInput input1 = new AddWayBillApInput();
		input1.setWaybillNo(f060102.getWaybillNo());
		input1.setLogistics(f060102.getLogistics());
		input1.setDelieverDay(f060102.getDeliverDay());
		if (Utility.isEmptyString(f060102.getWeight())) {
			input1.setWeight("0");
		} else {
			input1.setWeight(f060102.getWeight());
		}
		input1.setFreight(f060102.getFreight());

		List<AddWayBillApDetailInput> inputList = new ArrayList<AddWayBillApDetailInput>();
		input1.setDetailList(inputList);
		AddWayBillApDetailInput detail = null;
		for (int i = 0; i < commodityList1.size(); i++) {
			detail = new AddWayBillApDetailInput();
			detail.setCommodityId(commodityList1.get(i).getCommodityId());
			detail.setQuantity(commodityList1.get(i).getQuantity());
			detail.setRemarks(commodityList1.get(i).getRemarks());
			inputList.add(detail);
		}

		setSessionAttribute("hassou", input1);
	}

	protected void init() {
		setTitle("V060102:添加发货单");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F060101 getF060101() {
		return f060101;
	}

	public void setF060101(F060101 f060101) {
		this.f060101 = f060101;
	}

	/**
	 * @return the f060102
	 */
	public F060102 getF060102() {
		return f060102;
	}

	/**
	 * @param f060102
	 *            the f060102 to set
	 */
	public void setF060102(F060102 f060102) {
		this.f060102 = f060102;
	}

}
