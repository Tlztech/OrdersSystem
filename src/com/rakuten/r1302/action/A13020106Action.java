package com.rakuten.r1302.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1002.common.A1002Common;
import com.rakuten.r1302.common.A130201Common;
import com.rakuten.r1302.form.F130201;
import com.rakuten.r1302.form.OrderList;
import com.rakuten.util.Utility;

public class A13020106Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F130201 f130201 = null;
	private String successFlg = null;

	protected void exec() throws Exception {
		String tenpobetsu = f130201.getTenposhubetsu();
		String outtype = f130201.getOuttype();
		A130201Common a130201Common = new A130201Common();
		List<String[]> rakutenOrderList = new ArrayList<String[]>();
		List<String[]> yahooOrderList = new ArrayList<String[]>();
		List<String> shoriList = new ArrayList<String>();
		List<String[]> shoriList2 = new ArrayList<String[]>();
		List<String> msgList = null;
		if ("0".equals(outtype)) {
			for (OrderList order : f130201.getOrderList()) {
				if (order.isIschecked()) {
					if ("楽天".equals(order.getSite())) {
						rakutenOrderList.add(new String[] {
								order.getChumonbango(), order.getTenpo() });
					} else if ("Yahoo Shopping".equals(order.getSite())) {
						yahooOrderList.add(new String[] {
								order.getChumonbango(), order.getTenpo() });
					} else {
						shoriList.add(order.getChumonbango());
					}
					shoriList2.add(new String[] { order.getChumonbango(),
							order.getHaisokaisha(), order.getDenpyobango() });
				}

			}
			if (!Utility.isEmptyList(rakutenOrderList)) {
				msgList = a130201Common.setHaneizumiRakuten(rakutenOrderList);
				for (String msg : msgList) {
					addError(null, msg);
				}
			} else if (!Utility.isEmptyList(yahooOrderList)) {
				msgList = a130201Common.setHaneizumiYahoo(yahooOrderList);
				for (String msg : msgList) {
					addError(null, msg);
				}
			} else {
				a130201Common.setHaneizumi(shoriList);
			}
			setSuccessFlg("1");
		} else if ("1".equals(outtype)) {
			List<OrderList> orderList = (List<OrderList>) getSessionAttribute("heneimachiList");

//			List<String> yahooOrderList = new ArrayList<String>();
			List<String> denaOrderList = new ArrayList<String>();
			List<String> yahuoku = new ArrayList<String>();

			for (OrderList order : orderList) {
				if ("楽天".equals(order.getSite())) {
					rakutenOrderList.add(new String[] { order.getChumonbango(),
							order.getTenpo() });
				} else if ("Yahoo Shopping".equals(order.getSite())) {
					yahooOrderList.add(new String[] { order.getChumonbango(),
							order.getTenpo() });
				} else if ("DENA".equals(order.getSite())) {
					denaOrderList.add(order.getChumonbango());
				} else if ("ヤフオク".equals(order.getSite())) {
					yahuoku.add(order.getChumonbango());
				}
				shoriList2.add(new String[] { order.getChumonbango(),
						order.getHaisokaisha(), order.getDenpyobango() });
			}
			if ("1".equals(tenpobetsu)) {
				msgList = a130201Common.setHaneizumiRakuten(rakutenOrderList);
				for (String msg : msgList) {
					addError(null, msg);
				}
			} else if ("2".equals(tenpobetsu)) {
				msgList = a130201Common.setHaneizumiYahoo(rakutenOrderList);
				for (String msg : msgList) {
					addError(null, msg);
				}
			} else if ("3".equals(tenpobetsu)) {
				shoriList = denaOrderList;
				a130201Common.setHaneizumi(shoriList);
			} else if ("4".equals(tenpobetsu)) {
				shoriList = yahuoku;
				a130201Common.setHaneizumi(shoriList);
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
	 * @param f130201
	 *            the f130201 to set
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
