package com.rakuten.r1302.action;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1002.common.A1002Common;
import com.rakuten.r1302.common.A130201Common;
import com.rakuten.r1302.form.F130201;
import com.rakuten.r1302.form.OrderList;
import com.rakuten.util.Utility;

public class A13020114Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F130201 f130201 = null;
	private String successFlg = null;

	protected void exec() throws Exception {
		String tenpobetsu = f130201.getTenposhubetsu();
		String outtype = f130201.getOuttype();
		A130201Common a130201Common = new A130201Common();
		List<String> rakutenOrderList = new ArrayList<String>();
		List<String> shoriList = new ArrayList<String>();
		List<String[]> shoriList2 = new ArrayList<String[]>();
		List<String> msgList = null;
		List<String> dList = a130201Common.getHaneimachiList("3");
		if ("0".equals(outtype)) {
			List<OrderList> oList = f130201.getOrderList().stream().filter(n->dList.contains(n.getChumonbango())).collect(Collectors.toList());
			if (oList.size() == f130201.getOrderList().size()) {
				
			} else {
				addError(null, "注文の一部を「设置反应完毕」に設定できません、反映状態で検索して確認ください");
			}
			for (OrderList order : oList) {
				if (order.isIschecked()) {

					shoriList.add(order.getChumonbango());

					shoriList2.add(
							new String[] { order.getChumonbango(), order.getHaisokaisha(), order.getDenpyobango() });
				}

			}

			a130201Common.setHaneiToComplete(shoriList);

			setSuccessFlg("1");
		} else if ("1".equals(outtype)) {
			List<OrderList> orderList = (List<OrderList>) getSessionAttribute("heneimachiList");

			List<OrderList> oList = orderList.stream().filter(n->dList.contains(n.getChumonbango())).collect(Collectors.toList());
			if (oList.size() == orderList.size()) {
				
			} else {
				addError(null, "注文の一部を「设置反应完毕」に設定できません、反映状態で検索して確認ください");
			}
			
			List<String> yahooOrderList = new ArrayList<String>();
			List<String> denaOrderList = new ArrayList<String>();
			List<String> yahuoku = new ArrayList<String>();
			List<String> ponpaOrderList = new ArrayList<String>();
			List<String> qoo10OrderList = new ArrayList<String>();
			List<String> otherOrderList = new ArrayList<String>();

			for (OrderList order : oList) {
				if ("楽天".equals(order.getSite())) {
					rakutenOrderList.add(order.getChumonbango());
				} else if ("Yahoo".equals(order.getSite())) {
					yahooOrderList.add(order.getChumonbango());
				} else if ("DENA".equals(order.getSite())) {
					denaOrderList.add(order.getChumonbango());
				} else if ("ヤフオク".equals(order.getSite())) {
					yahuoku.add(order.getChumonbango());
				}else if ("ポンパレモール".equals(order.getSite())) {
					ponpaOrderList.add(order.getChumonbango());
				}else if("qoo10".equals(order.getSite())) {
					qoo10OrderList.add(order.getChumonbango());
				}else {
					otherOrderList.add(order.getChumonbango());
				}
				shoriList2.add(new String[] { order.getChumonbango(), order.getHaisokaisha(), order.getDenpyobango() });
			}
			if ("1".equals(tenpobetsu)) {
				shoriList = rakutenOrderList;
				a130201Common.setHaneiToComplete(shoriList);
			} else if ("2".equals(tenpobetsu)) {
				shoriList = yahooOrderList;
				a130201Common.setHaneiToComplete(shoriList);
			} else if ("3".equals(tenpobetsu)) {
				shoriList = denaOrderList;
				a130201Common.setHaneiToComplete(shoriList);
			} else if ("4".equals(tenpobetsu)) {
				shoriList = yahuoku;
				a130201Common.setHaneiToComplete(shoriList);
			}else if ("5".equals(tenpobetsu)) {
				shoriList = ponpaOrderList;
				a130201Common.setHaneiToComplete(shoriList);
			}else if ("6".equals(tenpobetsu)) {
				shoriList = qoo10OrderList;
				a130201Common.setHaneiToComplete(shoriList);
			}else if ("7".equals(tenpobetsu)) {
				shoriList = otherOrderList;
				a130201Common.setHaneiToComplete(shoriList);
			}

			setSuccessFlg("1");
		}
		A1002Common a1002Common = new A1002Common();
		a1002Common.setHasozumiSts(shoriList2);
	}

	protected void init() throws Exception {
		setTitle("V130201:発送処理一覧");
	}

	protected void isValidated() throws Exception {
		String type = f130201.getOuttype();
		if (Utility.isEmptyList(f130201.getOrderList())) {
			addError("", "反映できる注文はありません！");
		} else if ("0".equals(type)) {
			boolean result = false;
			for (OrderList order : f130201.getOrderList()) {
				if (order.isIschecked()) {
					result = true;
				}
			}
			if (!result) {
				addError("", "注文を選択してください！");
			}
		}

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the f130201
	 */
	public F130201 getF130201() {
		return f130201;
	}

	/**
	 * @param f130201 the f130201 to set
	 */
	public void setF130201(F130201 f130201) {
		this.f130201 = f130201;
	}

	public String getSuccessFlg() {
		return successFlg;
	}

	public void setSuccessFlg(String successFlg) {
		this.successFlg = successFlg;
	}

}
