package com.rakuten.r0601.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.GetWayBillAp;
import com.rakuten.r0601.bean.GetWayBillApInput;
import com.rakuten.r0601.bean.GetWayBillApOutput;
import com.rakuten.r0601.form.F060101;
import com.rakuten.r0601.form.WayBillInfo;

public class A06010103Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	F060101 f060101 = null;

	protected void exec() throws Exception {
		GetWayBillApInput input = new GetWayBillApInput();
		input.setDelieverDay(f060101.getDeliverDay().replaceAll("-", ""));
		input.setReceiveDay(f060101.getReceiveDay().replaceAll("-", ""));
		input.setWaybillNo(f060101.getWaybillNo());
		input.setCustoms(f060101.getCustoms());
		input.setLogistics(f060101.getLogistics());
		input.setStatus(f060101.getStatus());
		GetWayBillAp getWayBillAp = new GetWayBillAp();
		List<GetWayBillApOutput> outputList = getWayBillAp.execute(input);

		List<WayBillInfo> wayBillList = new ArrayList<WayBillInfo>();
		WayBillInfo wayBillInfo = null;
		for (int i = 0; i < outputList.size(); i++) {
			wayBillInfo = new WayBillInfo();
			wayBillList.add(wayBillInfo);
			wayBillInfo.setWaybillNo(outputList.get(i).getWaybillNo());
			if ("01".equals(outputList.get(i).getLogistics())) {
				wayBillInfo.setLogistics("ZCE");
			} else if ("02".equals(outputList.get(i).getLogistics())) {
				wayBillInfo.setLogistics("EMS");
			} else if ("03".equals(outputList.get(i).getLogistics())) {
				wayBillInfo.setLogistics("其他");
			}
			if ("00".equals(outputList.get(i).getStatus())) {
				wayBillInfo.setStatus("未签收");
			} else if ("10".equals(outputList.get(i).getStatus())) {
				wayBillInfo.setStatus("已签收");
			} else if ("20".equals(outputList.get(i).getStatus())) {
				wayBillInfo.setStatus("一时保存");
			}
			wayBillInfo.setDeliverDay(outputList.get(i).getDeliverDay());
			wayBillInfo.setReceiveDay(outputList.get(i).getReceiveDay());
			wayBillInfo.setWeight(outputList.get(i).getWeight());
			wayBillInfo.setFreight(outputList.get(i).getFreight());
			wayBillInfo.setCustoms(outputList.get(i).getCustoms());

		}
		f060101.setWayBillList(wayBillList);
		f060101.setResultCount(String.valueOf(outputList.size()));
		if (outputList.size() == 0) {
			f060101.setWayBillList(null);
			addError(null, "没有指定的检索结果");
		}

	}

	protected void init() {
		setTitle("V060101:发货管理");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f060101
	 */
	public F060101 getF060101() {
		return f060101;
	}

	/**
	 * @param f060101
	 *            the f060101 to set
	 */
	public void setF060101(F060101 f060101) {
		this.f060101 = f060101;
	}

}
