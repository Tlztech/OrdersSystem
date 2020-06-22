package com.rakuten.r1001.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.F100101;
import com.rakuten.r1001.form.OrderList;

public class A10010103Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100101 f100101 = null;
	A1001Common a1001Common = new A1001Common();
	OrderCommon orderCommon = new OrderCommon();
	private String type = null;

	protected void exec() throws Exception {
		List<String> juchubangoList = null;

		OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean.getCommonOrderBeanList();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean.getShouhinStsBeanList();

		// 発送待ち
		if ("1".equals(type)) {
			List<String> hasomachiList = orderCommon.getHasomachiList(commonOrderBeanList);
			f100101.setHasomachiCount(String.valueOf(hasomachiList.size()));
			juchubangoList = hasomachiList;
		} else if ("2".equals(type)) {
			// 追加発送待ち
			List<String> tuikaHasomachiList = orderCommon.getTuikaHasomachiList(commonOrderBeanList);
			f100101.setTuikahasomachiCount(String.valueOf(tuikaHasomachiList.size()));
			juchubangoList = tuikaHasomachiList;
		} else if ("3".equals(type)) {
			// 分納発送待ち
			List<String> bunnoHasomachiList = orderCommon.getBunnoHasomachiList(commonOrderBeanList);
			f100101.setBunnohasomachiCount(String.valueOf(bunnoHasomachiList.size()));
			juchubangoList = bunnoHasomachiList;
		} else if ("4".equals(type)) {
			// 発送待ち-至急
			List<String> hasomachiShikyuList = orderCommon.getHasomachishikyuList(commonOrderBeanList);
			f100101.setHasomachishikyuCount(String.valueOf(hasomachiShikyuList.size()));
			juchubangoList = hasomachiShikyuList;
		} else if ("5".equals(type)) {
			// 追加発送待ち-至急
			List<String> tuikaHasomachiShikyuList = orderCommon.getTuikaHasomachishikyuList(commonOrderBeanList);
			f100101.setTuikahasomachishikyuCount(String.valueOf(tuikaHasomachiShikyuList.size()));
			juchubangoList = tuikaHasomachiShikyuList;
		} else if ("6".equals(type)) {
			// 分納発送待ち-至急
			List<String> bunnoHasomachiShikyuList = orderCommon.getBunnoHasomachishikyuList(commonOrderBeanList);
			f100101.setBunnohasomachishikyuCount(String.valueOf(bunnoHasomachiShikyuList.size()));
			juchubangoList = bunnoHasomachiShikyuList;
		} else if ("7".equals(type)) {
			// 発送待ち-発送可
			List<String> hasomachihasokaList = orderCommon.getHasomachiHasokaList(commonOrderBeanList,
					shouhinStsBeanList);
			f100101.setHasomachihasokaCount(String.valueOf(hasomachihasokaList.size()));
			juchubangoList = hasomachihasokaList;
		} else if ("8".equals(type)) {
			// 追加発送待ち-発送可
			List<String> tuikahasomachihasokaList = orderCommon.getTuikahasomachiHasokaList(commonOrderBeanList,
					shouhinStsBeanList);
			f100101.setTuikahasomachihasokaCount(String.valueOf(tuikahasomachihasokaList.size()));
			juchubangoList = tuikahasomachihasokaList;
		} else if ("9".equals(type)) {
			// 分納発送待ち-発送可
			List<String> bunnohasomachihasokaList = orderCommon.getBunnohasomachiHasokaList(commonOrderBeanList,
					shouhinStsBeanList);
			f100101.setBunnohasomachihasokaCount(String.valueOf(bunnohasomachihasokaList.size()));
			juchubangoList = bunnohasomachihasokaList;
		} else if ("10".equals(type)) {
			// 発送待ち-至急-発送可
			List<String> hasomachishikyuhasokaList = orderCommon.getHasomachiHasokashikyuList(commonOrderBeanList,
					shouhinStsBeanList);
			f100101.setHasomachishikyuhasokaCount(String.valueOf(hasomachishikyuhasokaList.size()));
			juchubangoList = hasomachishikyuhasokaList;
		} else if ("11".equals(type)) {
			// 追加発送待ち-至急-発送可
			List<String> tuikahasomachishikyuhasokaList = orderCommon
					.getTuikahasomachiHasokashikyuList(commonOrderBeanList, shouhinStsBeanList);
			f100101.setTuikahasomachishikyuhasokaCount(String.valueOf(tuikahasomachishikyuhasokaList.size()));
			juchubangoList = tuikahasomachishikyuhasokaList;
		} else if ("12".equals(type)) {
			// 分納発送待ち-至急-発送可
			List<String> bunnohasomachishikyuhasokaList = orderCommon
					.getBunnohasomachishikyuHasokaList(commonOrderBeanList, shouhinStsBeanList);
			f100101.setBunnohasomachishikyuhasokaCount(String.valueOf(bunnohasomachishikyuhasokaList.size()));
			juchubangoList = bunnohasomachishikyuhasokaList;
		} else if ("13".equals(type)) {
			// 返品待ち
			List<String> henpinmachiList = orderCommon.getHenpinmachiList();
			f100101.setHenpinmachiCount(String.valueOf(henpinmachiList.size()));
			juchubangoList = henpinmachiList;
		} else if ("14".equals(type)) {
			// 返品中
			List<String> henpinchuList = orderCommon.getHenpinchuList();
			f100101.setHenpinchuCount(String.valueOf(henpinchuList.size()));
			juchubangoList = henpinchuList;
		} else if ("15".equals(type)) {
			// 返金待ち
			List<String> henkinmachiList = orderCommon.getHenkinmachiList();
			f100101.setHenkinmachiCount(String.valueOf(henkinmachiList.size()));
			juchubangoList = henkinmachiList;
		} else if ("16".equals(type)) {
			// 送信待ち
			List<String> soshinmachiList = orderCommon.getSoshinmachiList();
			f100101.setSoshinmachiCount(String.valueOf(soshinmachiList.size()));
			juchubangoList = soshinmachiList;
		} else if ("17".equals(type)) {
			// お届け時間帯設定待ち
			List<String> todokemachiList = orderCommon.getTodokemachiList();
			f100101.setTodokeseteimachiCount(String.valueOf(todokemachiList.size()));
			List<String> shoriList = new ArrayList<String>();
			for (int i = todokemachiList.size() - 1; i >= 0; i--) {
				shoriList.add(todokemachiList.get(i));
			}
			juchubangoList = shoriList;
			f100101.setTodokeset("1");
		} else if ("18".equals(type)) {
			// 発送前確認
			List<String> kakuninmachiList = orderCommon.getKakuninmachiList();
			f100101.setHasomaekakuninCount(String.valueOf(kakuninmachiList.size()));
			juchubangoList = kakuninmachiList;
		}

		List<String> nyukafukaList = orderCommon.getNyukafukarenList();
		List<CommonOrderBean> nyukamachiList = orderCommon.getNyukamachiList(commonOrderBeanList, shouhinStsBeanList);
		if ("19".equals(type)) {
			// 入荷不可 連絡待ち
			List<String> nyukafukarenrakumachi = orderCommon.getNyukafukarenrakumachiList(nyukamachiList,
					nyukafukaList);
			List<String> shoriList = new ArrayList<String>();
			for (String orderno : nyukafukarenrakumachi) {
				boolean ariFlg = false;
				for (String ordernono : shoriList) {
					if (orderno.equals(ordernono)) {
						ariFlg = true;
					}
				}
				if (!ariFlg) {
					shoriList.add(orderno);
				}
			}
			f100101.setNyukafukarenrakumachiCount(String.valueOf(shoriList.size()));
			juchubangoList = shoriList;
		} else if ("20".equals(type)) {
			// 入荷不可
			List<String> nyukafukarenrakuzumi = orderCommon.getNyukafukarenrakuzumiList(nyukamachiList, nyukafukaList);
			f100101.setNyukafukarenrakuzumiCount(String.valueOf(nyukafukarenrakuzumi.size()));
			juchubangoList = nyukafukarenrakuzumi;
		} else if ("21".equals(type)) {
			// 異常
			List<String> ijo = orderCommon.getijoList();
			f100101.setIjoCount(String.valueOf(ijo.size()));
			juchubangoList = ijo;
		} else if ("22".equals(type)) {
			// 遅れ
			List<String> okure = orderCommon.getOkureList(orderInfoBean);
			f100101.setOkureCount(String.valueOf(okure.size()));
			juchubangoList = okure;
		} else if ("23".equals(type)) {
			// 入荷待ち
			List<String> bangoList = orderCommon.getHasomachiYoteibiariList();
			List<String> nyukamachi = orderCommon.getNyukamachiList(orderInfoBean, bangoList);
			f100101.setNyukamachiCount(String.valueOf(nyukamachi.size()));
			juchubangoList = nyukamachi;
		} else if ("24".equals(type)) {
			// 入荷中
			List<String> bangoList = orderCommon.getHasomachiYoteibiariList();
			List<String> nyukachu = orderCommon.getNyukachuList(orderInfoBean, bangoList);
			f100101.setNyukachuCount(String.valueOf(nyukachu.size()));
			juchubangoList = nyukachu;
		} else if ("25".equals(type)) {
			// 運送中
			List<String> bangoList = orderCommon.getHasomachiYoteibiariList();
			List<String> unsochu = orderCommon.getUnsochuOrderList(orderInfoBean, bangoList);
			f100101.setUnsochuCount(String.valueOf(unsochu.size()));
			juchubangoList = unsochu;
		} else if ("26".equals(type)) {

			List<String> mondaiariList = orderCommon.getMondaiariList(commonOrderBeanList);
			f100101.setMondaiariCount(String.valueOf(mondaiariList.size()));
			juchubangoList = mondaiariList;
		}

		List<OrderList> orderList = a1001Common.getOrderListByBango(juchubangoList);
		f100101.setResultCount(String.valueOf(orderList.size()));
		f100101.setOrderList(orderList);
	}

	protected void init() {
		setTitle("V100101:注文一覧");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F100101 getF100101() {
		return f100101;
	}

	public void setF100101(F100101 f100101) {
		this.f100101 = f100101;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
