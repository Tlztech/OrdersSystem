package com.rakuten.r1001.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.HasouBean;
import com.rakuten.common.bean.HenkinListBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.BunnohasomachiList;
import com.rakuten.r1001.form.BunnohasozumiList;
import com.rakuten.r1001.form.BunnouhasokaList;
import com.rakuten.r1001.form.F100102;
import com.rakuten.r1001.form.HasomachiList;
import com.rakuten.r1001.form.HasozumiList;
import com.rakuten.r1001.form.HenpinList;
import com.rakuten.r1001.form.HenpinSeteiZumiList;
import com.rakuten.r1001.form.HenpinshuseikaList;
import com.rakuten.r1001.form.TuikaDetail;
import com.rakuten.r1001.form.TuikaList;
import com.rakuten.util.Utility;

public class A10010201Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String orderNo = null;
	private F100102 f100102 = null;
	A1001Common a1001Common = new A1001Common();
	OrderCommon orderCommon = new OrderCommon();
	private String messageInfo = null;

	protected void exec() throws Exception {

		f100102 = a1001Common.getOrderInfo(orderNo);
		f100102.setResultcount(String.valueOf(f100102.getShohinList().size()));

		OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();

		List<CommonOrderBean> commonOrderBeanList = orderInfoBean
				.getCommonOrderBeanList();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean
				.getShouhinStsBeanList();

		String orderNo = f100102.getJuchubango();
		CommonOrderBean thisOrder = null;
		for (CommonOrderBean order : commonOrderBeanList) {
			if (order.getJuchubango().equals(orderNo)) {
				thisOrder = order;
			}
		}
		if (thisOrder == null) {
			thisOrder = new CommonOrderBean();
		}

		String[] shiteibiInfo = orderCommon.getOrderShiteibiInfo(orderNo);
		if (shiteibiInfo != null) {
			f100102.setOtodokebishitei(shiteibiInfo[0]);
			f100102.setOtodokejikantai1(shiteibiInfo[1]);
			f100102.setOtodokejikantai2(shiteibiInfo[2]);
			f100102.setOtodokejikantai3(shiteibiInfo[3]);
			f100102.setBiko(shiteibiInfo[4]);
		}

		// 送信リスト取得
		f100102.setSoshinList(orderCommon.getSoushinList(orderNo));
		// 発送済みリスト取得
		List<HasozumiList> hasozumiList = null;
		List<HasouBean> hasoList = orderCommon.getHasoList(orderNo, "0");
		if (!Utility.isEmptyList(hasoList)) {
			// 设定发送完毕list显示flg位1
			f100102.setHasouzumiFlg("1");

			hasozumiList = new ArrayList<HasozumiList>();
			f100102.setHasozumiList(hasozumiList);
			for (HasouBean hasobean : hasoList) {
				HasozumiList hasozumibean = new HasozumiList();
				hasozumiList.add(hasozumibean);

				hasozumibean.setKosu(hasobean.getKosu());
				hasozumibean.setShohinbango(hasobean.getShohinbango());

				f100102.setHasonichiji_hasozumi(hasobean.getHasobi());
				if ("0".equals(hasobean.getHaisohoho())) {
					f100102.setHaisohoho_hasozumi("その他");
				} else if ("1".equals(hasobean.getHaisohoho())) {
					f100102.setHaisohoho_hasozumi("宅急便");
				} else if ("2".equals(hasobean.getHaisohoho())) {
					f100102.setHaisohoho_hasozumi("メール便");
				}

				if ("1000".equals(hasobean.getUnsokaisha())) {
					f100102.setHaisokaisha_hasozumi("その他");
				} else if ("1001".equals(hasobean.getUnsokaisha())) {
					f100102.setHaisokaisha_hasozumi("ヤマト運輸");
				} else if ("1002".equals(hasobean.getUnsokaisha())) {
					f100102.setHaisokaisha_hasozumi("佐川急便");
				} else if ("1003".equals(hasobean.getUnsokaisha())) {
					f100102.setHaisokaisha_hasozumi("郵便局");
				}
				f100102.setToiawasebango_hasozumi(hasobean.getToiawasebango());
			}
		}

		// 普通発送の場合
		if (!thisOrder.isBunnouFlg() && !thisOrder.isHenpinFlg()
				&& !thisOrder.isKoukanFlg() && !thisOrder.isTuikaFlg()
				&& "2".equals(f100102.getChumonsts1())) {
			List<HasomachiList> hasomachiList = new ArrayList<HasomachiList>();
			HasomachiList hasomachiBean = null;
			List<String[]> hasomachiArr = orderCommon.getMachiListAll(
					thisOrder, shouhinStsBeanList, "2", "7");
			String latestData = "";
			boolean blankFlg = false;
			for (String[] hasomachi : hasomachiArr) {
				hasomachiBean = new HasomachiList();
				hasomachiList.add(hasomachiBean);

				hasomachiBean.setShohinbango(hasomachi[0]);
				hasomachiBean.setKosu(hasomachi[1]);

				if ("入荷待ち".equals(hasomachi[2])) {

					if (!orderCommon.isNyukakano(hasomachi[0])) {
						hasomachi[2] = hasomachi[2] + "(入荷不可)";
						if (!Utility.isEmptyString(f100102
								.getNyukafukaBangoArr())) {
							f100102.setNyukafukaBangoArr(f100102
									.getNyukafukaBangoArr()
									+ "&"
									+ hasomachi[0]);
						} else {
							f100102.setNyukafukaBangoArr(hasomachi[0]);
						}
					}
				}

				hasomachiBean.setSutetasu(hasomachi[2]);

				if ("0".equals(hasomachi[3])) {
					f100102.setFutuhasokanoFlg("0");
				}

				if (!Utility.isEmptyString(hasomachi[4])) {
					if (Utility.isEmptyString(latestData)) {
						latestData = hasomachi[4];
					} else {
						if (Long.valueOf(hasomachi[4]) > Long
								.valueOf(latestData)) {
							latestData = hasomachi[4];
						}
					}
				} else {
					blankFlg = true;
				}
			}
			if (blankFlg) {
				latestData = "";
			}
			f100102.setHasoyoteibi(latestData);
			f100102.setHasomachiList(hasomachiList);

		}
		if (thisOrder.isBunnouFlg() || "1".equals(f100102.getChumonsts3())) {
			// 分納の場合
			// 取得已经发送的list
			List<HasouBean> hasozumuList_bunno = orderCommon.getHasoList(
					orderNo, "1");
			List<BunnohasozumiList> bunnohasozumiList = new ArrayList<BunnohasozumiList>();
			f100102.setBunnohasozumiList(bunnohasozumiList);
			BunnohasozumiList bunnohasozumi = null;
			for (int i = 0; i < hasozumuList_bunno.size(); i++) {
				HasouBean hasozumi = hasozumuList_bunno.get(i);
				bunnohasozumi = new BunnohasozumiList();
				bunnohasozumiList.add(bunnohasozumi);

				bunnohasozumi.setShohinbango(hasozumi.getShohinbango());
				bunnohasozumi.setKosu(hasozumi.getKosu());
				bunnohasozumi.setHasonichiji(hasozumi.getHasobi());
				bunnohasozumi.setHaisokaisha(Utility
						.getHaisokaishaName(hasozumi.getUnsokaisha()));
				bunnohasozumi.setHaisohoho(Utility.getHaisohohoName(hasozumi
						.getHaisohoho()));
				bunnohasozumi.setToiawasebango(hasozumi.getToiawasebango());
			}

			// 如果已经发送 不可取消
			if (!Utility.isEmptyList(bunnohasozumiList)) {
				f100102.setBunnotorikeshikaFlg("0");
			}

			// 获得需要发货list
			List<BunnohasomachiList> bunnohasomachiList = new ArrayList<BunnohasomachiList>();
			BunnohasomachiList bunnohasomachi = null;

			List<String[]> hasomachiArr = orderCommon.getMachiListAll(
					thisOrder, shouhinStsBeanList, "5", "6");
			// 発送可リスto
			List<String[]> hasokaList = new ArrayList<String[]>();
			for (String[] hasomachi : hasomachiArr) {
				bunnohasomachi = new BunnohasomachiList();
				bunnohasomachiList.add(bunnohasomachi);

				bunnohasomachi.setShohinbango(hasomachi[0]);
				bunnohasomachi.setKosu(hasomachi[1]);

				// TODO 添加如何不可CHECK

				if ("入荷待ち".equals(hasomachi[2])) {
					// TODO 添加如何不可CHECK
					if (!orderCommon.isNyukakano(hasomachi[0])) {
						hasomachi[2] = hasomachi[2] + "(入荷不可)";
						if (!Utility.isEmptyString(f100102
								.getNyukafukaBangoArr())) {
							f100102.setNyukafukaBangoArr(f100102
									.getNyukafukaBangoArr()
									+ "&"
									+ hasomachi[0]);

						} else {
							f100102.setNyukafukaBangoArr(hasomachi[0]);
						}
					}
				}
				bunnohasomachi.setSutetasu(hasomachi[2]);

				if ("発送待ち".equals(hasomachi[2])) {
					hasokaList.add(hasomachi);
				}
			}
			f100102.setBunnohasomachiList(bunnohasomachiList);

			// 设定可发货列表到画面上
			List<BunnouhasokaList> bunnouhasoukaList = new ArrayList<BunnouhasokaList>();
			BunnouhasokaList bunnohasoka = null;
			if (!Utility.isEmptyList(hasokaList)) {
				f100102.setBunnohasoukaFlg("1");
				for (String[] hasoka : hasokaList) {
					bunnohasoka = new BunnouhasokaList();
					bunnouhasoukaList.add(bunnohasoka);
					bunnohasoka.setShouhinmei(Utility.getShohinmei(hasoka[0]));
					bunnohasoka.setKosu(hasoka[1]);
					bunnohasoka.setShohinbango(hasoka[0]);
				}
			}
			f100102.setBunnouhasokaList(bunnouhasoukaList);
		}

		// 返品可能リスト設定
		List<String[]> henpinkaList = orderCommon.getHenpinListAll(orderNo);
		if (!Utility.isEmptyList(henpinkaList)) {
			List<HenpinList> henpinList = new ArrayList<HenpinList>();
			HenpinList henpin = null;
			for (String[] result : henpinkaList) {
				henpin = new HenpinList();
				henpinList.add(henpin);

				henpin.setShouhinmei(Utility.getShohinmei(result[0]) + "("
						+ result[0] + ")");
				henpin.setHassoushubetsu(orderCommon
						.getHassouShubetsuName(result[2]));
				henpin.setHasokosu(result[1]);
				henpin.setShohinbango(result[0]);
				henpin.setHasounichiji(result[3]);
			}

			f100102.setHenpinkaList(henpinList);
			// 修正可能リスト
			f100102.setHenpinshuseikaShohinList(henpinList);
		}

		// 返品ありの場合
		List<HenpinSeteiZumiList> henpinList = orderCommon
				.getHenpinList(orderNo);
		if (!Utility.isEmptyList(henpinList)) {
			f100102.setHenpinariFlg("1");
			f100102.setHenpinList(henpinList);
			f100102.setHenpinListzumi(henpinList);
		}

		List<HenpinshuseikaList> henpinshuseikaList = orderCommon
				.getHenpinshuseikaList(orderNo);
		f100102.setHenpinshuseikaList(henpinshuseikaList);

		// 追加ありの場合
		List<TuikaList> tuikaList = orderCommon.getTuikaList(orderNo);
		if (!Utility.isEmptyList(tuikaList)) {
			f100102.setTuikaariFlg("1");
			f100102.setTuikaList(tuikaList);
		}
		List<String[]> tuikahasomachiArr = orderCommon.getMachiListAll(
				thisOrder, shouhinStsBeanList, "1", "4");

		for (TuikaList tuika : tuikaList) {
			if (tuika.getTuikasts().equals("0")) {
				f100102.setTuikahasokaFlg("0");
				for (TuikaDetail detail : tuika.getDetailList()) {
					String shouhinbango = Utility.strTrim(detail
							.getShohinbango());
					for (String[] tuikahasomachi : tuikahasomachiArr) {
						if (shouhinbango.equals(tuikahasomachi[0])) {
							if ("入荷待ち".equals(tuikahasomachi[2])) {
								// TODO 添加如何不可CHECK
								if (!orderCommon.isNyukakano(shouhinbango)) {
									tuikahasomachi[2] = tuikahasomachi[2]
											+ "(入荷不可)";
									if (!Utility.isEmptyString(f100102
											.getNyukafukaBangoArr())) {
										f100102.setNyukafukaBangoArr(f100102
												.getNyukafukaBangoArr()
												+ "&"
												+ tuikahasomachi[0]);
									} else {
										f100102.setNyukafukaBangoArr(tuikahasomachi[0]);
									}
								}
							}
							detail.setTuikastatus(tuikahasomachi[2]);
						}
					}
				}
			} else {
				for (TuikaDetail detail : tuika.getDetailList()) {
					detail.setTuikastatus("発送済み");
				}
			}
		}

		String[] tuikashuseika = orderCommon.getTuikahasomachi(orderNo);
		if (tuikashuseika != null) {
			f100102.setTuikashoribango_tuikashusei(tuikashuseika[0]);
			f100102.setTuikariyu_tuikashusei(tuikashuseika[1]);
			f100102.setTuikariyusonota_tuikashusei(tuikashuseika[2]);
			f100102.setHasosoryofutan_tuikashusei(tuikashuseika[3]);
			f100102.setBiko_tuikashusei(tuikashuseika[4]);
			f100102.setJsonArr(tuikashuseika[5]);
			f100102.setJsonArr_moto(tuikashuseika[5]);
		}

		// 设置返金列表
		List<HenkinListBean> henkinList = orderCommon.getHenkinList(orderNo);

		if (!Utility.isEmptyList(henkinList)) {
			f100102.setHenkinariFlg("1");
			f100102.setHenkinList(henkinList);
		}

		// 设置发货时默认的运送方式和公司 信封位黑猫 宅急便为佐川
		String haisohoho = f100102.getHaisohoho();
		if ("メール便".equals(haisohoho)) {
			f100102.setHaisohoho_bunnou("2");
			f100102.setHaisohoho_futsuhasou("2");

			f100102.setHaisokaisha_bunnou("1001");
			f100102.setHaisokaisha_futsuhasou("1001");

		} else if ("宅配便".equals(haisohoho)) {
			f100102.setHaisohoho_bunnou("1");
			f100102.setHaisohoho_futsuhasou("1");

			f100102.setHaisokaisha_bunnou("1002");
			f100102.setHaisokaisha_futsuhasou("1002");
		}

		// 获取发送约定日
		String yakusokubi = orderCommon.getHasoyakusokubi(orderNo);
		f100102.setHasoyakusokubi(yakusokubi);

	}

	protected void init() {
		setTitle("V100102:" + orderNo + "の詳細情報");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public F100102 getF100102() {
		return f100102;
	}

	public void setF100102(F100102 f100102) {
		this.f100102 = f100102;
	}

	public String getMessageInfo() {
		return messageInfo;
	}

	public void setMessageInfo(String messageInfo) {
		this.messageInfo = messageInfo;
	}

}
