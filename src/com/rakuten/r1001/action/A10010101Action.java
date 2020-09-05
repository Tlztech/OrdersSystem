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
import com.rakuten.util.Utility;

public class A10010101Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100101 f100101 = null;
	A1001Common a1001Common = new A1001Common();
	OrderCommon orderCommon = new OrderCommon();
	private String type = null;
	private String successFlg = null;

	protected void exec() throws Exception {
		List<OrderList> orderList = new ArrayList<OrderList>();
		if (f100101 == null) {
			f100101 = new F100101();
			f100101.setKewordType("1");
			// orderList = a1001Common.getOrderList(f100101, type);
			// if (orderList.size() > 50) {
			// orderList = orderList.subList(0, 50);
			// }
		} else {
			orderList = a1001Common.getOrderList(f100101, type);
		}

		f100101.setResultCount(String.valueOf(orderList.size()));
		f100101.setOrderList(orderList);

		System.out.println("orderCommon.getOrderInfo()>>>"+ Utility.getDateTime());
		OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean.getCommonOrderBeanList();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean.getShouhinStsBeanList();
		// 発送待ち
		System.out.println("orderCommon.getHasomachiList>>>"+ Utility.getDateTime());
		List<String> hasomachiList = orderCommon.getHasomachiList(commonOrderBeanList);
		f100101.setHasomachiCount(String.valueOf(hasomachiList.size()));
		// 追加発送待ち
		System.out.println("orderCommon.getTuikaHasomachiList>>>"+ Utility.getDateTime());
		List<String> tuikaHasomachiList = orderCommon.getTuikaHasomachiList(commonOrderBeanList);
		f100101.setTuikahasomachiCount(String.valueOf(tuikaHasomachiList.size()));
		// 分納発送待ち
		System.out.println("orderCommon.getBunnoHasomachiList>>>"+ Utility.getDateTime());
		List<String> bunnoHasomachiList = orderCommon.getBunnoHasomachiList(commonOrderBeanList);
		f100101.setBunnohasomachiCount(String.valueOf(bunnoHasomachiList.size()));
		// 発送待ち-至急
		System.out.println("orderCommon.getHasomachishikyuList>>>"+ Utility.getDateTime());
		List<String> hasomachiShikyuList = orderCommon.getHasomachishikyuList(commonOrderBeanList);
		f100101.setHasomachishikyuCount(String.valueOf(hasomachiShikyuList.size()));
		// 追加発送待ち-至急
		System.out.println("orderCommon.getTuikaHasomachishikyuList>>>"+ Utility.getDateTime());
		List<String> tuikaHasomachiShikyuList = orderCommon.getTuikaHasomachishikyuList(commonOrderBeanList);
		f100101.setTuikahasomachishikyuCount(String.valueOf(tuikaHasomachiShikyuList.size()));
		// 分納発送待ち-至急
		System.out.println("orderCommon.getBunnoHasomachishikyuList>>>"+ Utility.getDateTime());
		List<String> bunnoHasomachiShikyuList = orderCommon.getBunnoHasomachishikyuList(commonOrderBeanList);
		f100101.setBunnohasomachishikyuCount(String.valueOf(bunnoHasomachiShikyuList.size()));
		// 発送待ち-発送可
		System.out.println("orderCommon.getHasomachiHasokaList>>>"+ Utility.getDateTime());
		List<String> hasomachihasokaList = orderCommon.getHasomachiHasokaList(commonOrderBeanList, shouhinStsBeanList);
		f100101.setHasomachihasokaCount(String.valueOf(hasomachihasokaList.size()));
		// 追加発送待ち-発送可
		System.out.println("orderCommon.getTuikahasomachiHasokaList>>>"+ Utility.getDateTime());
		List<String> tuikahasomachihasokaList = orderCommon.getTuikahasomachiHasokaList(commonOrderBeanList,
				shouhinStsBeanList);
		f100101.setTuikahasomachihasokaCount(String.valueOf(tuikahasomachihasokaList.size()));
		// 分納発送待ち-発送可
		System.out.println("orderCommon.getBunnohasomachiHasokaList>>>"+ Utility.getDateTime());
		List<String> bunnohasomachihasokaList = orderCommon.getBunnohasomachiHasokaList(commonOrderBeanList,
				shouhinStsBeanList);
		f100101.setBunnohasomachihasokaCount(String.valueOf(bunnohasomachihasokaList.size()));
		// 発送待ち-至急-発送可
		System.out.println("orderCommon.getHasomachiHasokashikyuList>>>"+ Utility.getDateTime());
		List<String> hasomachishikyuhasokaList = orderCommon.getHasomachiHasokashikyuList(commonOrderBeanList,
				shouhinStsBeanList);
		f100101.setHasomachishikyuhasokaCount(String.valueOf(hasomachishikyuhasokaList.size()));
		// 追加発送待ち-至急-発送可
		System.out.println("orderCommon.getTuikahasomachiHasokashikyuList>>>"+ Utility.getDateTime());
		List<String> tuikahasomachishikyuhasokaList = orderCommon.getTuikahasomachiHasokashikyuList(commonOrderBeanList,
				shouhinStsBeanList);
		f100101.setTuikahasomachishikyuhasokaCount(String.valueOf(tuikahasomachishikyuhasokaList.size()));
		// 分納発送待ち-至急-発送可
		System.out.println("orderCommon.getBunnohasomachishikyuHasokaList>>>"+ Utility.getDateTime());
		List<String> bunnohasomachishikyuhasokaList = orderCommon.getBunnohasomachishikyuHasokaList(commonOrderBeanList,
				shouhinStsBeanList);
		f100101.setBunnohasomachishikyuhasokaCount(String.valueOf(bunnohasomachishikyuhasokaList.size()));
		// 返品待ち
		System.out.println("getHenpinmachiList>>>"+ Utility.getDateTime());
		List<String> henpinmachiList = orderCommon.getHenpinmachiList();
		f100101.setHenpinmachiCount(String.valueOf(henpinmachiList.size()));
		// 返品中
		System.out.println("getHenpinchuList>>>"+ Utility.getDateTime());
		List<String> henpinchuList = orderCommon.getHenpinchuList();
		f100101.setHenpinchuCount(String.valueOf(henpinchuList.size()));
		// 返金待ち
		System.out.println("getHenkinmachiList>>>"+ Utility.getDateTime());
		List<String> henkinmachiList = orderCommon.getHenkinmachiList();
		f100101.setHenkinmachiCount(String.valueOf(henkinmachiList.size()));
		// 送信待ち
		System.out.println("getSoshinmachiList>>>"+ Utility.getDateTime());
		 List<String> soshinmachiList = orderCommon.getSoshinmachiList();
		 f100101.setSoshinmachiCount(String.valueOf(soshinmachiList.size()));
		// お届け時間帯設定待ち
		System.out.println("getTodokemachiList>>>"+ Utility.getDateTime());
//		List<String> todokemachiList = orderCommon.getTodokemachiList();
//		f100101.setTodokeseteimachiCount(String.valueOf(todokemachiList.size()));
		// 発送前確認
		System.out.println("getKakuninmachiList>>>"+ Utility.getDateTime());
		List<String> kakuninmachiList = orderCommon.getKakuninmachiList();
		f100101.setHasomaekakuninCount(String.valueOf(kakuninmachiList.size()));

		// 入荷不可 連絡待ち
		System.out.println("getNyukafukarenList>>>"+ Utility.getDateTime());
		List<String> nyukafukaList = orderCommon.getNyukafukarenList();
		List<CommonOrderBean> nyukamachiList = orderCommon.getNyukamachiList(commonOrderBeanList, shouhinStsBeanList);
		// 入荷不可 連絡待ち
		System.out.println("getNyukafukarenrakumachiList>>>"+ Utility.getDateTime());
		List<String> nyukafukarenrakumachi = orderCommon.getNyukafukarenrakumachiList(nyukamachiList, nyukafukaList);

		System.out.println("for (String orderno : nyukafukarenrakumachi)>>>"+ Utility.getDateTime());
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
		System.out.println("for (String orderno : nyukafukarenrakumachi)>>>"+ Utility.getDateTime());
		f100101.setNyukafukarenrakumachiCount(String.valueOf(shoriList.size()));
		// 入荷不可
		System.out.println("getNyukafukarenrakuzumiList>>>"+ Utility.getDateTime());
		List<String> nyukafukarenrakuzumi = orderCommon.getNyukafukarenrakuzumiList(nyukamachiList, nyukafukaList);
		f100101.setNyukafukarenrakuzumiCount(String.valueOf(nyukafukarenrakuzumi.size()));

		// 異常
		System.out.println("getijoList>>>"+ Utility.getDateTime());
		List<String> ijo = orderCommon.getijoList();
		f100101.setIjoCount(String.valueOf(ijo.size()));

		// 遅れ
		System.out.println("getOkureList>>>"+ Utility.getDateTime());
//		List<String> okure = orderCommon.getOkureList(orderInfoBean);
//		f100101.setOkureCount(String.valueOf(okure.size()));

		System.out.println("getHasomachiYoteibiariList>>>"+ Utility.getDateTime());
		List<String> bangoList = orderCommon.getHasomachiYoteibiariList();
		// 入荷待ち
		System.out.println("getNyukamachiList>>>"+ Utility.getDateTime());
//		List<String> nyukamachi = orderCommon.getNyukamachiList(orderInfoBean, bangoList);
//		f100101.setNyukamachiCount(String.valueOf(nyukamachi.size()));

		// 入荷中
		System.out.println("getNyukachuList>>>"+ Utility.getDateTime());
//		List<String> nyukachu = orderCommon.getNyukachuList(orderInfoBean, bangoList);
//		f100101.setNyukachuCount(String.valueOf(nyukachu.size()));

		// 運送中
		System.out.println("getUnsochuOrderList>>>"+ Utility.getDateTime());
//		List<String> unsochu = orderCommon.getUnsochuOrderList(orderInfoBean, bangoList);
//		f100101.setUnsochuCount(String.valueOf(unsochu.size()));
		// 問題あり
		System.out.println("111>>>"+ Utility.getDateTime());
		// List<String> mondaiari = orderCommon
		// .getMondaiariList(commonOrderBeanList);
		// f100101.setMondaiariCount(String.valueOf(mondaiari.size()));

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

	public String getSuccessFlg() {
		return successFlg;
	}

	public void setSuccessFlg(String successFlg) {
		this.successFlg = successFlg;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
