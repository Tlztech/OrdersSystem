package com.rakuten.common.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import org.apache.axis.encoding.Base64;

import com.rakuten.common.MessageFromRMS;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.CommonOrderDetailrBean;
import com.rakuten.common.bean.HasouBean;
import com.rakuten.common.bean.HenkinListBean;
import com.rakuten.common.bean.OrderApiBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShohinStsInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.order.Order;
import com.rakuten.r1001.bean.RakutenCsvBean;
import com.rakuten.r1001.bean.RakutenDetailCsvBean;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.F100102;
import com.rakuten.r1001.form.F100103;
import com.rakuten.r1001.form.F100104;
import com.rakuten.r1001.form.HenpinDetail;
import com.rakuten.r1001.form.HenpinSeteiZumiList;
import com.rakuten.r1001.form.HenpinshuseikaList;
import com.rakuten.r1001.form.ShohinList;
import com.rakuten.r1001.form.SoushinList;
import com.rakuten.r1001.form.TuikaDetail;
import com.rakuten.r1001.form.TuikaList;
import com.rakuten.shop.Shop;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

import jp.co.rakuten.rms.mall.order.api.client.GetOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.GetRequestIdResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.ItemModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService_Service;
import jp.co.rakuten.rms.mall.order.api.client.OrderModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderSearchModel;
import jp.co.rakuten.rms.mall.order.api.client.UnitErrorModel;
import jp.co.rakuten.rms.mall.order.api.client.UpdateOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.UserAuthModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import shohinmodel.common.Shohincommon;

public class OrderCommon {
	// 运送途中list
	List<String[]> unsochuList = null;
	// 进货中list
	List<String[]> nyukachuList = null;
	// 日本库存list
	List<String[]> stockJpList = null;
	// 上海库存list
	List<String[]> stockShList = null;

	public OrderInfoBean getOrderInfo() throws Exception {
		OrderInfoBean orderInfoBean = new OrderInfoBean();

		// 运送途中list
		unsochuList = getUnsochuList();
		// 进货中list
		nyukachuList = getNyukaChuList();
		// 日本库存list
		stockJpList = getJapanStockList();
		// 上海库存list
		stockShList = getShanghaiStockList();

		// 运送途中listStatic
		List<String[]> unsochuListStatic = unsochuList;
		// 进货中listStatic
		List<String[]> nyukachuListStatic = nyukachuList;
		// 日本库存listStatic
		List<String[]> stockJpListStatic = stockJpList;
		// 上海库存listStatic
		List<String[]> stockShListStatic = stockShList;

		List<CommonOrderBean> commonOrderBeanList = getCommonOrderBeanList();

		List<ShouhinStsBean> shouhinStsBeanList = new ArrayList<ShouhinStsBean>();
		List<String> priorityList = getPriority();

		// 分配货物
		for (String priority : priorityList) {
			distribute(unsochuListStatic, nyukachuListStatic, stockJpListStatic, stockShListStatic, commonOrderBeanList,
					shouhinStsBeanList, priority);
		}
		// for (ShouhinStsBean bean : shouhinStsBeanList) {
		// // 商品番号
		// System.out.print("商品番号:" + bean.getShouhinbango() + ",");
		// // 上海在庫数
		// System.out.print("上海在庫数:" + bean.getShanghaiStock() + ",");
		// // 日本個数
		// System.out.print("日本個数:" + bean.getNihonStock() + ",");
		// // 入荷中個数
		// System.out.print("入荷中個数:" + bean.getNyukachukosu() + ",");
		// // 運送途中個数
		// System.out.print("運送途中個数:" + bean.getUnsochukosu() + ",");
		// // 残り個数(上海)
		// System.out.print("残り個数(上海):" + bean.getNokorikosuSh() + ",");
		// // 残り個数(日本)
		// System.out.print("残り個数(日本):" + bean.getNokorikosuJp() + ",");
		// // 残り個数(運送)
		// System.out.print("残り個数(運送):" + bean.getNokorikosuUnso() + ",");
		// // 残り個数(入荷)
		// System.out.print("残り個数(入荷):" + bean.getNokorikosuNyuka() + ",");
		// System.out.println();
		// List<ShohinStsInfoBean> detailList = bean
		// .getShohinStsInfoBeanList();
		//
		// for (ShohinStsInfoBean detail : detailList) {
		//
		// System.out.print("受注番号:" + detail.getJuchubango());
		//
		// System.out.print("注文日時:" + detail.getChumonnichiji());
		//
		// System.out.print("ステータス最終設定日時:"
		// + detail.getStatussaishusetteibi());
		//
		// System.out.print("注文ステータス:" + detail.getChumonsts());
		//
		// System.out.print("保留個数(上海stock):" + detail.getHoryukosuSh());
		//
		// System.out.print("保留個数(日本stock):" + detail.getHoryukosuJp());
		//
		// System.out
		// .print("保留個数(運送途中stock):" + detail.getHoryukosuUnso());
		//
		// System.out
		// .print("保留個数(入荷中stock):" + detail.getHoryukosuNyuka());
		//
		// System.out.print("必要個数:" + detail.getHiyuyokosu());
		//
		// System.out.print("保留ステータス:" + detail.getHoryusts());
		//
		// System.out.println();
		//
		// }
		// }

		orderInfoBean.setCommonOrderBeanList(commonOrderBeanList);
		orderInfoBean.setShouhinStsBeanList(shouhinStsBeanList);
		return orderInfoBean;
	}

	/**
	 * 
	 * @param unsochuList         运送途中list
	 * @param nyukachuList        进货途中list
	 * @param stockJpList         日本库存
	 * @param stockShList         上海库存
	 * @param commonOrderBeanList 订单列表（需求）
	 * @param shouhinStsBean      商品分配信息
	 */

	private void distribute(List<String[]> unsochuListStatic, List<String[]> nyukachuListStatic,
			List<String[]> stockJpListStatic, List<String[]> stockShListStatic,
			List<CommonOrderBean> commonOrderBeanList, List<ShouhinStsBean> shouhinStsBeanList, String type) {

		List<CommonOrderBean> shoriList = new ArrayList<CommonOrderBean>();
		// 交换（加急）的单子
		if ("0".equals(type)) {
			for (int i = 0; i < commonOrderBeanList.size(); i++) {
				CommonOrderBean commonOrderBean = commonOrderBeanList.get(i);
				if (commonOrderBean.isKoukanFlg() && commonOrderBean.isShikyuFlg()) {
					// 将交换加急的bean放到list
					CommonOrderBean tempCommon = new CommonOrderBean();

					tempCommon.setJuchubango(commonOrderBean.getJuchubango());
					tempCommon.setShikyuFlg(commonOrderBean.isShikyuFlg());
					tempCommon.setShikyusetteibi(commonOrderBean.getShikyusetteibi());
					tempCommon.setHenpinFlg(commonOrderBean.isHenpinFlg());
					tempCommon.setHenpinsetteibi(commonOrderBean.getHenpinsetteibi());
					tempCommon.setKoukanFlg(commonOrderBean.isKoukanFlg());
					tempCommon.setKoukansetteibi(commonOrderBean.getKoukansetteibi());
					tempCommon.setBunnouFlg(commonOrderBean.isBunnouFlg());
					tempCommon.setBunnousetteibi(commonOrderBean.getBunnousetteibi());
					tempCommon.setYusenjun(commonOrderBean.getYusenjun());
					tempCommon.setChumonichiji(commonOrderBean.getChumonichiji());
					tempCommon.setTuikaFlg(commonOrderBean.isTuikaFlg());
					tempCommon.setTuikasetteibi(commonOrderBean.getTuikasetteibi());
					tempCommon.setCommonOrderDetailBeanList(new ArrayList<CommonOrderDetailrBean>());
					List<CommonOrderDetailrBean> commonOrderDetailList = commonOrderBean.getCommonOrderDetailBeanList();
					for (CommonOrderDetailrBean detail : commonOrderDetailList) {
						if ("3".equals(detail.getShubetsu())) {
							tempCommon.getCommonOrderDetailBeanList().add(detail);
						}
					}
					shoriList.add(tempCommon);
				}
			}
		}
		// 追加发送（加急）的单子
		if ("1".equals(type)) {
			for (int i = 0; i < commonOrderBeanList.size(); i++) {
				CommonOrderBean commonOrderBean = commonOrderBeanList.get(i);
				if (commonOrderBean.isTuikaFlg() && commonOrderBean.isShikyuFlg()) {
					CommonOrderBean tempCommon = new CommonOrderBean();

					tempCommon.setJuchubango(commonOrderBean.getJuchubango());
					tempCommon.setShikyuFlg(commonOrderBean.isShikyuFlg());
					tempCommon.setShikyusetteibi(commonOrderBean.getShikyusetteibi());
					tempCommon.setHenpinFlg(commonOrderBean.isHenpinFlg());
					tempCommon.setHenpinsetteibi(commonOrderBean.getHenpinsetteibi());
					tempCommon.setKoukanFlg(commonOrderBean.isKoukanFlg());
					tempCommon.setKoukansetteibi(commonOrderBean.getKoukansetteibi());
					tempCommon.setBunnouFlg(commonOrderBean.isBunnouFlg());
					tempCommon.setBunnousetteibi(commonOrderBean.getBunnousetteibi());
					tempCommon.setYusenjun(commonOrderBean.getYusenjun());
					tempCommon.setChumonichiji(commonOrderBean.getChumonichiji());
					tempCommon.setTuikaFlg(commonOrderBean.isTuikaFlg());
					tempCommon.setTuikasetteibi(commonOrderBean.getTuikasetteibi());
					tempCommon.setCommonOrderDetailBeanList(new ArrayList<CommonOrderDetailrBean>());
					List<CommonOrderDetailrBean> commonOrderDetailList = commonOrderBean.getCommonOrderDetailBeanList();
					for (CommonOrderDetailrBean detail : commonOrderDetailList) {
						if ("2".equals(detail.getShubetsu())) {
							tempCommon.getCommonOrderDetailBeanList().add(detail);
						}
					}
					// 将交换加急的bean放到list
					shoriList.add(tempCommon);
				}
			}
		}
		// 普通发送（加急）的单子
		if ("2".equals(type)) {
			for (int i = 0; i < commonOrderBeanList.size(); i++) {
				CommonOrderBean commonOrderBean = commonOrderBeanList.get(i);
				if (!commonOrderBean.isTuikaFlg() && !commonOrderBean.isBunnouFlg() && !commonOrderBean.isKoukanFlg()
						&& !commonOrderBean.isHenpinFlg() && commonOrderBean.isShikyuFlg()) {
					CommonOrderBean tempCommon = new CommonOrderBean();

					tempCommon.setJuchubango(commonOrderBean.getJuchubango());
					tempCommon.setShikyuFlg(commonOrderBean.isShikyuFlg());
					tempCommon.setShikyusetteibi(commonOrderBean.getShikyusetteibi());
					tempCommon.setHenpinFlg(commonOrderBean.isHenpinFlg());
					tempCommon.setHenpinsetteibi(commonOrderBean.getHenpinsetteibi());
					tempCommon.setKoukanFlg(commonOrderBean.isKoukanFlg());
					tempCommon.setKoukansetteibi(commonOrderBean.getKoukansetteibi());
					tempCommon.setBunnouFlg(commonOrderBean.isBunnouFlg());
					tempCommon.setBunnousetteibi(commonOrderBean.getBunnousetteibi());
					tempCommon.setYusenjun(commonOrderBean.getYusenjun());
					tempCommon.setChumonichiji(commonOrderBean.getChumonichiji());
					tempCommon.setTuikaFlg(commonOrderBean.isTuikaFlg());
					tempCommon.setTuikasetteibi(commonOrderBean.getTuikasetteibi());
					tempCommon.setCommonOrderDetailBeanList(new ArrayList<CommonOrderDetailrBean>());
					List<CommonOrderDetailrBean> commonOrderDetailList = commonOrderBean.getCommonOrderDetailBeanList();
					for (CommonOrderDetailrBean detail : commonOrderDetailList) {
						if ("0".equals(detail.getShubetsu())) {
							tempCommon.getCommonOrderDetailBeanList().add(detail);
						}
					}
					// 将交换加急的bean放到list
					shoriList.add(tempCommon);
				}
			}
		}
		// 交换的单子
		if ("3".equals(type)) {
			for (int i = 0; i < commonOrderBeanList.size(); i++) {
				CommonOrderBean commonOrderBean = commonOrderBeanList.get(i);
				if (commonOrderBean.isKoukanFlg() && !commonOrderBean.isShikyuFlg()) {
					// 将交换加急的bean放到list
					CommonOrderBean tempCommon = new CommonOrderBean();

					tempCommon.setJuchubango(commonOrderBean.getJuchubango());
					tempCommon.setShikyuFlg(commonOrderBean.isShikyuFlg());
					tempCommon.setShikyusetteibi(commonOrderBean.getShikyusetteibi());
					tempCommon.setHenpinFlg(commonOrderBean.isHenpinFlg());
					tempCommon.setHenpinsetteibi(commonOrderBean.getHenpinsetteibi());
					tempCommon.setKoukanFlg(commonOrderBean.isKoukanFlg());
					tempCommon.setKoukansetteibi(commonOrderBean.getKoukansetteibi());
					tempCommon.setBunnouFlg(commonOrderBean.isBunnouFlg());
					tempCommon.setBunnousetteibi(commonOrderBean.getBunnousetteibi());
					tempCommon.setYusenjun(commonOrderBean.getYusenjun());
					tempCommon.setChumonichiji(commonOrderBean.getChumonichiji());
					tempCommon.setTuikaFlg(commonOrderBean.isTuikaFlg());
					tempCommon.setTuikasetteibi(commonOrderBean.getTuikasetteibi());
					tempCommon.setCommonOrderDetailBeanList(new ArrayList<CommonOrderDetailrBean>());
					List<CommonOrderDetailrBean> commonOrderDetailList = commonOrderBean.getCommonOrderDetailBeanList();
					for (CommonOrderDetailrBean detail : commonOrderDetailList) {
						if ("3".equals(detail.getShubetsu())) {
							tempCommon.getCommonOrderDetailBeanList().add(detail);
						}
					}
					shoriList.add(tempCommon);
				}
			}
		}
		// 追加单子
		if ("4".equals(type)) {
			for (int i = 0; i < commonOrderBeanList.size(); i++) {
				CommonOrderBean commonOrderBean = commonOrderBeanList.get(i);
				if (commonOrderBean.isTuikaFlg() && !commonOrderBean.isShikyuFlg()) {
					// 将交换加急的bean放到list
					CommonOrderBean tempCommon = new CommonOrderBean();

					tempCommon.setJuchubango(commonOrderBean.getJuchubango());
					tempCommon.setShikyuFlg(commonOrderBean.isShikyuFlg());
					tempCommon.setShikyusetteibi(commonOrderBean.getShikyusetteibi());
					tempCommon.setHenpinFlg(commonOrderBean.isHenpinFlg());
					tempCommon.setHenpinsetteibi(commonOrderBean.getHenpinsetteibi());
					tempCommon.setKoukanFlg(commonOrderBean.isKoukanFlg());
					tempCommon.setKoukansetteibi(commonOrderBean.getKoukansetteibi());
					tempCommon.setBunnouFlg(commonOrderBean.isBunnouFlg());
					tempCommon.setBunnousetteibi(commonOrderBean.getBunnousetteibi());
					tempCommon.setYusenjun(commonOrderBean.getYusenjun());
					tempCommon.setChumonichiji(commonOrderBean.getChumonichiji());
					tempCommon.setTuikaFlg(commonOrderBean.isTuikaFlg());
					tempCommon.setTuikasetteibi(commonOrderBean.getTuikasetteibi());
					tempCommon.setCommonOrderDetailBeanList(new ArrayList<CommonOrderDetailrBean>());
					List<CommonOrderDetailrBean> commonOrderDetailList = commonOrderBean.getCommonOrderDetailBeanList();
					for (CommonOrderDetailrBean detail : commonOrderDetailList) {
						if ("2".equals(detail.getShubetsu())) {
							tempCommon.getCommonOrderDetailBeanList().add(detail);
						}
					}
					shoriList.add(tempCommon);
				}
			}
		}
		// 分纳发送（加急）
		if ("5".equals(type)) {
			for (int i = 0; i < commonOrderBeanList.size(); i++) {
				CommonOrderBean commonOrderBean = commonOrderBeanList.get(i);
				if (commonOrderBean.isBunnouFlg() && commonOrderBean.isShikyuFlg()) {
					// 将交换加急的bean放到list
					CommonOrderBean tempCommon = new CommonOrderBean();

					tempCommon.setJuchubango(commonOrderBean.getJuchubango());
					tempCommon.setShikyuFlg(commonOrderBean.isShikyuFlg());
					tempCommon.setShikyusetteibi(commonOrderBean.getShikyusetteibi());
					tempCommon.setHenpinFlg(commonOrderBean.isHenpinFlg());
					tempCommon.setHenpinsetteibi(commonOrderBean.getHenpinsetteibi());
					tempCommon.setKoukanFlg(commonOrderBean.isKoukanFlg());
					tempCommon.setKoukansetteibi(commonOrderBean.getKoukansetteibi());
					tempCommon.setBunnouFlg(commonOrderBean.isBunnouFlg());
					tempCommon.setBunnousetteibi(commonOrderBean.getBunnousetteibi());
					tempCommon.setYusenjun(commonOrderBean.getYusenjun());
					tempCommon.setChumonichiji(commonOrderBean.getChumonichiji());
					tempCommon.setTuikaFlg(commonOrderBean.isTuikaFlg());
					tempCommon.setTuikasetteibi(commonOrderBean.getTuikasetteibi());
					tempCommon.setCommonOrderDetailBeanList(new ArrayList<CommonOrderDetailrBean>());
					List<CommonOrderDetailrBean> commonOrderDetailList = commonOrderBean.getCommonOrderDetailBeanList();
					for (CommonOrderDetailrBean detail : commonOrderDetailList) {
						if ("1".equals(detail.getShubetsu())) {
							tempCommon.getCommonOrderDetailBeanList().add(detail);
						}
					}
					shoriList.add(tempCommon);
				}
			}
		}
		// 分纳发送
		if ("6".equals(type)) {
			for (int i = 0; i < commonOrderBeanList.size(); i++) {
				CommonOrderBean commonOrderBean = commonOrderBeanList.get(i);
				if (commonOrderBean.isBunnouFlg() && !commonOrderBean.isShikyuFlg()) {
					// 将交换加急的bean放到list
					CommonOrderBean tempCommon = new CommonOrderBean();

					tempCommon.setJuchubango(commonOrderBean.getJuchubango());
					tempCommon.setShikyuFlg(commonOrderBean.isShikyuFlg());
					tempCommon.setShikyusetteibi(commonOrderBean.getShikyusetteibi());
					tempCommon.setHenpinFlg(commonOrderBean.isHenpinFlg());
					tempCommon.setHenpinsetteibi(commonOrderBean.getHenpinsetteibi());
					tempCommon.setKoukanFlg(commonOrderBean.isKoukanFlg());
					tempCommon.setKoukansetteibi(commonOrderBean.getKoukansetteibi());
					tempCommon.setBunnouFlg(commonOrderBean.isBunnouFlg());
					tempCommon.setBunnousetteibi(commonOrderBean.getBunnousetteibi());
					tempCommon.setYusenjun(commonOrderBean.getYusenjun());
					tempCommon.setChumonichiji(commonOrderBean.getChumonichiji());
					tempCommon.setTuikaFlg(commonOrderBean.isTuikaFlg());
					tempCommon.setTuikasetteibi(commonOrderBean.getTuikasetteibi());
					tempCommon.setCommonOrderDetailBeanList(new ArrayList<CommonOrderDetailrBean>());
					List<CommonOrderDetailrBean> commonOrderDetailList = commonOrderBean.getCommonOrderDetailBeanList();
					for (CommonOrderDetailrBean detail : commonOrderDetailList) {
						if ("1".equals(detail.getShubetsu())) {
							tempCommon.getCommonOrderDetailBeanList().add(detail);
						}
					}
					shoriList.add(tempCommon);
				}
			}
		}
		// 普通发送
		if ("7".equals(type)) {
			for (int i = 0; i < commonOrderBeanList.size(); i++) {
				CommonOrderBean commonOrderBean = commonOrderBeanList.get(i);
				if (!commonOrderBean.isTuikaFlg() && !commonOrderBean.isBunnouFlg() && !commonOrderBean.isKoukanFlg()
						&& !commonOrderBean.isHenpinFlg() && !commonOrderBean.isShikyuFlg()) {
					// 将交换加急的bean放到list
					CommonOrderBean tempCommon = new CommonOrderBean();
					tempCommon.setJuchubango(commonOrderBean.getJuchubango());
					tempCommon.setShikyuFlg(commonOrderBean.isShikyuFlg());
					tempCommon.setShikyusetteibi(commonOrderBean.getShikyusetteibi());
					tempCommon.setHenpinFlg(commonOrderBean.isHenpinFlg());
					tempCommon.setHenpinsetteibi(commonOrderBean.getHenpinsetteibi());
					tempCommon.setKoukanFlg(commonOrderBean.isKoukanFlg());
					tempCommon.setKoukansetteibi(commonOrderBean.getKoukansetteibi());
					tempCommon.setBunnouFlg(commonOrderBean.isBunnouFlg());
					tempCommon.setBunnousetteibi(commonOrderBean.getBunnousetteibi());
					tempCommon.setYusenjun(commonOrderBean.getYusenjun());
					tempCommon.setChumonichiji(commonOrderBean.getChumonichiji());
					tempCommon.setTuikaFlg(commonOrderBean.isTuikaFlg());
					tempCommon.setTuikasetteibi(commonOrderBean.getTuikasetteibi());
					tempCommon.setCommonOrderDetailBeanList(new ArrayList<CommonOrderDetailrBean>());
					List<CommonOrderDetailrBean> commonOrderDetailList = commonOrderBean.getCommonOrderDetailBeanList();
					for (CommonOrderDetailrBean detail : commonOrderDetailList) {
						if ("0".equals(detail.getShubetsu())) {
							tempCommon.getCommonOrderDetailBeanList().add(detail);
						}
					}
					shoriList.add(tempCommon);
				}
			}
		}

		// 按照設定時間排序
		sortList(shoriList, type);
		for (int i = 0; i < shoriList.size(); i++) {
			CommonOrderBean shoribean = shoriList.get(i);
			List<CommonOrderDetailrBean> koukandetailList = shoribean.getCommonOrderDetailBeanList();
			for (int j = 0; j < koukandetailList.size(); j++) {
				CommonOrderDetailrBean koukanDetail = koukandetailList.get(j);
				koukanDetail.setShohinbango(Utility.strTrim(koukanDetail.getShohinbango()));
				String shouhinbango = koukanDetail.getShohinbango();

				// 需求个数
				int kosu = koukanDetail.getKosu();
				// 检查已有的商品bean，有的话利用，没得话new一个
				ShouhinStsBean shouhinStsBean = null;
				for (int k = 0; k < shouhinStsBeanList.size(); k++) {
					if (shouhinStsBeanList.get(k).getShouhinbango().equals(shouhinbango)) {
						shouhinStsBean = shouhinStsBeanList.get(k);
						break;
					}
				}
				// 不能存在 设置个新的 加入各种信息
				if (shouhinStsBean == null) {
					shouhinStsBean = new ShouhinStsBean();
					shouhinStsBean.setShouhinbango(shouhinbango);
					// 设置日本初始库存
					for (String[] japanStock : stockJpList) {
						if (japanStock[0].equals(shouhinbango)) {
							shouhinStsBean.setNihonStock(japanStock[1]);
						}
					}
					// 设置上海初始库存
					for (String[] shanghaiStock : stockShList) {
						if (shanghaiStock[0].equals(shouhinbango)) {
							shouhinStsBean.setShanghaiStock(shanghaiStock[1]);
						}
					}
					// 设置进货途中初始库存
					for (String[] nyukachu : nyukachuList) {
						if (nyukachu[0].equals(shouhinbango)) {
							shouhinStsBean.setNyukachukosu(String.valueOf(
									Integer.valueOf(shouhinStsBean.getNyukachukosu()) + Integer.valueOf(nyukachu[1])));
						}
					}
					// 设置运送途中初始库存
					for (String[] unsochu : unsochuList) {
						if ("xbx078-T-01".equals(unsochu[0])) {
							System.out.println(shouhinbango);
						}
						if (unsochu[0].equals(shouhinbango)) {
							shouhinStsBean.setUnsochukosu(String.valueOf(
									Integer.valueOf(shouhinStsBean.getUnsochukosu()) + Integer.valueOf(unsochu[1])));
						}
					}

					// 设置个新的详细listbean
					shouhinStsBean.setShohinStsInfoBeanList(new ArrayList<ShohinStsInfoBean>());
					shouhinStsBeanList.add(shouhinStsBean);
				}

				// 从日本库存检索是否有货
				ShohinStsInfoBean shohinStsInfoBean = new ShohinStsInfoBean();
				shouhinStsBean.getShohinStsInfoBeanList().add(shohinStsInfoBean);
				for (int k = 0; k < stockJpListStatic.size(); k++) {
					if (stockJpListStatic.get(k)[0].equals(shouhinbango)) {
						// 日本库存数
						int stockJp = Integer.valueOf(stockJpListStatic.get(k)[1]);
						// 日本有库存
						if (stockJp > kosu) {
							shohinStsInfoBean.setHoryukosuJp(String.valueOf(kosu));
							// 设置日本库存剩余个数
							shouhinStsBean.setNokorikosuJp(
									String.valueOf(Integer.valueOf(stockJpListStatic.get(k)[1]) - kosu));
							stockJpListStatic.get(k)[1] = shouhinStsBean.getNokorikosuJp();
							// 剩余需求个数
							kosu = 0;
						} else if (stockJp > 0) {
							// 有库存但是不够
							shohinStsInfoBean.setHoryukosuJp(String.valueOf(stockJp));
							// 设置日本库存剩余个数
							shouhinStsBean.setNokorikosuJp("0");
							stockJpListStatic.get(k)[1] = "0";
							// 剩余需求个数
							kosu = kosu - stockJp;
						}
					}
				}
				// 如果不够 从运送途中检索
				for (int k = 0; k < unsochuListStatic.size(); k++) {
					if (kosu > 0) {
						if (unsochuListStatic.get(k)[0].equals(shouhinbango)) {
							// 运送途中数
							int unsokosu = Integer.valueOf(unsochuListStatic.get(k)[1]);
							// 运送途中有
							if (unsokosu > kosu) {
								shohinStsInfoBean.setHoryukosuUnso(
										String.valueOf(Integer.valueOf(shohinStsInfoBean.getHoryukosuUnso()) + kosu));
								// 设置运送途中库存剩余个数
								shouhinStsBean.setNokorikosuUnso(
										String.valueOf(Integer.valueOf(unsochuListStatic.get(k)[1]) - kosu));
								unsochuListStatic.get(k)[1] = shouhinStsBean.getNokorikosuUnso();

								// 其他信息
								shohinStsInfoBean.getDenpyoubango().add(unsochuListStatic.get(k)[2]);
								shohinStsInfoBean.getHasoubi().add(unsochuListStatic.get(k)[3]);
								shohinStsInfoBean.getUnsohoryuList().add(String.valueOf(kosu));

								// 剩余需求个数
								kosu = 0;
							} else if (unsokosu > 0) {
								// 有库存但是不够
								shohinStsInfoBean.setHoryukosuUnso(String
										.valueOf(unsokosu + Integer.valueOf(shohinStsInfoBean.getHoryukosuUnso())));
								// 设置运送途中库存剩余个数
								shouhinStsBean.setNokorikosuUnso("0");
								unsochuListStatic.get(k)[1] = "0";
								// 剩余需求个数
								kosu = kosu - unsokosu;
								// 其他信息
								shohinStsInfoBean.getDenpyoubango().add(unsochuListStatic.get(k)[2]);
								shohinStsInfoBean.getHasoubi().add(unsochuListStatic.get(k)[3]);
								shohinStsInfoBean.getUnsohoryuList().add(String.valueOf(unsokosu));
							}
						}
					}
				}
				// 如果不够 从上海库存中检索

				for (int k = 0; k < stockShListStatic.size(); k++) {
					if (kosu > 0) {
						if (stockShListStatic.get(k)[0].equals(shouhinbango)) {
							// 上海库存中数
							int stocksh = Integer.valueOf(stockShListStatic.get(k)[1]);
							// 上海库存中有
							if (stocksh > kosu) {
								shohinStsInfoBean.setHoryukosuSh(String.valueOf(kosu));
								// 设置上海库存剩余个数
								shouhinStsBean.setNokorikosuSh(
										String.valueOf(Integer.valueOf(stockShListStatic.get(k)[1]) - kosu));
								stockShListStatic.get(k)[1] = shouhinStsBean.getNokorikosuSh();
								// 剩余需求个数
								kosu = 0;
							} else if (stocksh > 0) {
								// 有库存但是不够
								shohinStsInfoBean.setHoryukosuSh(String.valueOf(stocksh));
								// 设置上海库存剩余个数
								shouhinStsBean.setNokorikosuSh("0");
								stockShListStatic.get(k)[1] = "0";
								// 剩余需求个数
								kosu = kosu - stocksh;
							}
						}
					}
				}
				// 如果不够 从进货途中中检索

				for (int k = 0; k < nyukachuListStatic.size(); k++) {
					if (kosu > 0) {
						if (nyukachuListStatic.get(k)[0].equals(shouhinbango)) {
							// 进货途中数
							int nyukakosu = Integer.valueOf(nyukachuListStatic.get(k)[1]);
							// 进货途中有
							if (nyukakosu > kosu) {
								shohinStsInfoBean.setHoryukosuNyuka(
										String.valueOf(kosu + Integer.valueOf(shohinStsInfoBean.getHoryukosuNyuka())));
								// 设置进货剩余个数
								shouhinStsBean.setNokorikosuNyuka(
										String.valueOf(Integer.valueOf(nyukachuListStatic.get(k)[1]) - kosu));
								nyukachuListStatic.get(k)[1] = shouhinStsBean.getNokorikosuNyuka();

								shohinStsInfoBean.getNyukabi().add(nyukachuListStatic.get(k)[2]);
								shohinStsInfoBean.getNyukahoryuList().add(String.valueOf(kosu));

								// 剩余需求个数
								kosu = 0;
							} else if (nyukakosu > 0) {
								// 有库存但是不够
								shohinStsInfoBean.setHoryukosuNyuka(String
										.valueOf(nyukakosu + Integer.valueOf(shohinStsInfoBean.getHoryukosuNyuka())));
								// 设置进货剩余个数
								shouhinStsBean.setNokorikosuNyuka("0");
								nyukachuListStatic.get(k)[1] = "0";
								// 剩余需求个数
								kosu = kosu - nyukakosu;
								shohinStsInfoBean.getNyukabi().add(nyukachuListStatic.get(k)[2]);
								shohinStsInfoBean.getNyukahoryuList().add(String.valueOf(nyukakosu));
							}
						}
					}
				}
				// 设置需求个数
				shohinStsInfoBean.setHiyuyokosu(String.valueOf(kosu));
				// 受注番号
				shohinStsInfoBean.setJuchubango(shoribean.getJuchubango());
				// 注文日時
				shohinStsInfoBean.setChumonnichiji(shoribean.getChumonichiji());
				// ステータス最終設定日時
				shohinStsInfoBean.setStatussaishusetteibi(shoribean.getKoukansetteibi());
				// 保留ステータス
				shohinStsInfoBean.setHoryusts(type);

			}
		}

	}

	public void sortList(List<CommonOrderBean> beanList, final String type) {
		Comparator<CommonOrderBean> comparator = new Comparator<CommonOrderBean>() {
			@Override
			public int compare(CommonOrderBean o1, CommonOrderBean o2) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date o1Date = null;
					Date o2Date = null;
					if ("3".equals(type)) {
						o1Date = df.parse(o1.getKoukansetteibi());
						o2Date = df.parse(o2.getKoukansetteibi());
					} else if ("4".equals(type)) {
						o1Date = df.parse(o1.getTuikasetteibi());
						o2Date = df.parse(o2.getTuikasetteibi());
					} else if ("7".equals(type)) {
						o1Date = df.parse(o1.getChumonichiji());
						o2Date = df.parse(o2.getChumonichiji());
					} else if ("6".equals(type)) {
						o1Date = df.parse(o1.getBunnousetteibi());
						o2Date = df.parse(o2.getBunnousetteibi());
					} else if ("0".equals(type) || "1".equals(type) || "2".equals(type) || "5".equals(type)) {
						o1Date = df.parse(o1.getShikyusetteibi());
						o2Date = df.parse(o2.getShikyusetteibi());
					}
					return o1Date.compareTo(o2Date);

				} catch (ParseException e) {
					e.printStackTrace();
				}
				return -1;
			}
		};
		Collections.sort(beanList, comparator);
	}

	public List<CommonOrderBean> getCommonOrderBeanList() throws Exception {
		List<CommonOrderBean> commonOrderBeanList = new ArrayList<CommonOrderBean>();

		CommonOrderBean commonOrderBean = null;
		CommonOrderDetailrBean detailBean = null;
		List<CommonOrderDetailrBean> commonOrderDetailBeanList = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();

			String sql = "SELECT * FROM common_order_tbl t1 left join tbl00027 t2 on t1.chumonbango = t2.chumonbango"
					+ " WHERE CHUMONSTS1 = ? OR CHUMONSTS1 = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "2");
			ps.setString(2, "5");
			rs = ps.executeQuery();

			String chumonbango = null;
			String chumonnichiji = null;
			String chumonsts1 = null;
			String chumonsts2 = null;
			String chumonsts3 = null;
			String chumonsts4 = null;
			String chumonsts5 = null;
			String chumonsts6 = null;
			String hasoyakusokubi = null;

			while (rs.next()) {
				chumonbango = rs.getString("t1.CHUMONBANGO");

				chumonnichiji = rs.getString("CHUMONNICHIJI");
				chumonsts1 = rs.getString("CHUMONSTS1");
				chumonsts2 = rs.getString("CHUMONSTS2");
				chumonsts3 = rs.getString("CHUMONSTS3");
				chumonsts4 = rs.getString("CHUMONSTS4");
				chumonsts5 = rs.getString("CHUMONSTS5");
				chumonsts6 = rs.getString("CHUMONSTS6");
				hasoyakusokubi = rs.getString("t2.hasoyakusokubi");

				commonOrderBean = new CommonOrderBean();
				commonOrderBeanList.add(commonOrderBean);

				// 受注番号
				commonOrderBean.setJuchubango(chumonbango);
				commonOrderBean.setHasoyakusokubi(hasoyakusokubi);
				// 注文日時
				commonOrderBean.setChumonichiji(chumonnichiji);
				// お支払い方法
				commonOrderBean.setOshiharaihoho(rs.getString("OSHIHARAISTS"));
				String oshiharaihoho = rs.getString("OSHIHARAISTS");
				if ("銀行振込".equals(oshiharaihoho) || "Edy決済".equals(oshiharaihoho) || "ATM決済".equals(oshiharaihoho)
						|| "セブンイレブン前払".equals(oshiharaihoho) || "ローソン前払".equals(oshiharaihoho)) {
					commonOrderBean.setChumonichiji(rs.getString("T1.CREATE_TIME"));

				}

				// 至急フラグ
				if ("1".equals(chumonsts2)) {
					commonOrderBean.setShikyuFlg(true);
					// 至急設定日
					String dayTime = getStatusSetDay(chumonbango, "2", "1", "1");
					commonOrderBean.setShikyusetteibi(dayTime);
				}

				// 普通发送
				if ("2".equals(chumonsts1)) {

					// 詳細BEAN
					commonOrderDetailBeanList = new ArrayList<CommonOrderDetailrBean>();
					commonOrderBean.setCommonOrderDetailBeanList(commonOrderDetailBeanList);

					sql = "SELECT * FROM common_order_detail_tbl WHERE JUCHUBANGO = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, chumonbango);
					ResultSet rs2 = ps.executeQuery();
					while (rs2.next()) {

						String shohinbango = rs2.getString("SHOUHINBANGO");
						boolean ariflg = false;
						for (CommonOrderDetailrBean detail : commonOrderDetailBeanList) {
							if (shohinbango.equals(detail.getShohinbango())) {
								detail.setKosu(rs2.getInt("KOSU") + detail.getKosu());
								ariflg = true;
							}
						}
						if (!ariflg) {
							detailBean = new CommonOrderDetailrBean();
							commonOrderDetailBeanList.add(detailBean);

							// 商品番号
							detailBean.setShohinbango(rs2.getString("SHOUHINBANGO"));
							// 個数
							detailBean.setKosu(rs2.getInt("KOSU"));
							// 種別
							detailBean.setShubetsu("0");
						}
					}
				} else {
					// 分納
					if ("1".equals(chumonsts3)) {
						commonOrderBean.setBunnouFlg(true);

						// 分納設定日
						String dayTime = getStatusSetDay(chumonbango, "3", "1", "1");
						commonOrderBean.setBunnousetteibi(dayTime);

						List<String[]> hasozumiList = new ArrayList<String[]>();
						List<String[]> hituyoList = new ArrayList<String[]>();
						commonOrderDetailBeanList = new ArrayList<CommonOrderDetailrBean>();

						// 詳細BEAN
						commonOrderBean.setCommonOrderDetailBeanList(commonOrderDetailBeanList);

						sql = "SELECT * FROM hassou_tbl WHERE JUCHUBANGO = ? AND SHUBETSU = '1'";
						ps = conn.prepareStatement(sql);
						ps.setString(1, chumonbango);
						ResultSet rs2 = ps.executeQuery();
						while (rs2.next()) {
							String shohinbango = rs2.getString("SHOUHINBANGO");
							boolean ariflg = false;
							for (String[] hasozumi : hasozumiList) {
								if (shohinbango.equals(hasozumi[0])) {
									hasozumi[1] = String
											.valueOf(rs2.getInt("HASSOUKOSU") + Integer.valueOf(hasozumi[1]));
									ariflg = true;
								}
							}
							if (!ariflg) {
								hasozumiList.add(
										new String[] { rs2.getString("SHOUHINBANGO"), rs2.getString("HASSOUKOSU") });
							}
						}

						sql = "SELECT * FROM common_order_detail_tbl WHERE JUCHUBANGO = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, chumonbango);
						rs2 = ps.executeQuery();

						while (rs2.next()) {
							String shohinbango = rs2.getString("SHOUHINBANGO");
							boolean ariflg = false;
							for (String[] hituyo : hituyoList) {
								if (shohinbango.equals(hituyo[0])) {
									hituyo[1] = String.valueOf(rs2.getInt("KOSU") + Integer.valueOf(hituyo[1]));
									ariflg = true;
								}
							}
							if (!ariflg) {
								hituyoList.add(new String[] { rs2.getString("SHOUHINBANGO"), rs2.getString("KOSU") });
							}
						}

						for (int i = 0; i < hituyoList.size(); i++) {
							boolean ariFlg = false;
							for (int j = 0; j < hasozumiList.size(); j++) {
								if (hituyoList.get(i)[0].equals(hasozumiList.get(j)[0])) {
									ariFlg = true;
									if (Integer.valueOf(hituyoList.get(i)[1])
											- Integer.valueOf(hasozumiList.get(j)[1]) > 0) {
										detailBean = new CommonOrderDetailrBean();
										commonOrderDetailBeanList.add(detailBean);
										// 商品番号
										detailBean.setShohinbango(hituyoList.get(i)[0]);
										// 個数
										detailBean.setKosu(Integer.valueOf(hituyoList.get(i)[1])
												- Integer.valueOf(hasozumiList.get(j)[1]));
										// 種別
										detailBean.setShubetsu("1");
									}
									continue;
								}
							}
							if (!ariFlg) {
								detailBean = new CommonOrderDetailrBean();
								commonOrderDetailBeanList.add(detailBean);
								// 商品番号
								detailBean.setShohinbango(hituyoList.get(i)[0]);
								// 個数
								detailBean.setKosu(Integer.valueOf(hituyoList.get(i)[1]));
								// 種別
								detailBean.setShubetsu("1");
							}
						}

					}
					// 交換
					if ("1".equals(chumonsts5)) {
						commonOrderBean.setKoukanFlg(true);
						// 分納設定日
						String dayTime = getStatusSetDay(chumonbango, "5", "1", "1");
						commonOrderBean.setKoukansetteibi(dayTime);
						commonOrderDetailBeanList = new ArrayList<CommonOrderDetailrBean>();
						// 詳細BEAN
						commonOrderBean.setCommonOrderDetailBeanList(commonOrderDetailBeanList);

						sql = "SELECT * FROM koukan_hasou_tbl WHERE JUCHUBANGO = ? AND KOUKANSTATUS = '3'";
						ps = conn.prepareStatement(sql);
						ps.setString(1, chumonbango);
						ResultSet rs2 = ps.executeQuery();
						while (rs2.next()) {
							detailBean = new CommonOrderDetailrBean();
							commonOrderDetailBeanList.add(detailBean);

							// 商品番号
							detailBean.setShohinbango(rs2.getString("SHOUHINBANGO"));
							// 個数
							detailBean.setKosu(rs2.getInt("KOSU"));
							// 種別
							detailBean.setShubetsu("3");
						}
					}
					// 追加
					if ("1".equals(chumonsts6)) {
						commonOrderBean.setTuikaFlg(true);
						// 追加設定日
						String dayTime = getStatusSetDay(chumonbango, "6", "1", "1");
						commonOrderBean.setTuikasetteibi(dayTime);
						commonOrderDetailBeanList = new ArrayList<CommonOrderDetailrBean>();
						// 詳細BEAN
						commonOrderBean.setCommonOrderDetailBeanList(commonOrderDetailBeanList);

						sql = "SELECT * FROM tuika_hasou_tbl WHERE JUCHUBANGO = ? AND STATUS = '0'";
						ps = conn.prepareStatement(sql);
						ps.setString(1, chumonbango);
						ResultSet rs2 = ps.executeQuery();
						while (rs2.next()) {
							detailBean = new CommonOrderDetailrBean();
							commonOrderDetailBeanList.add(detailBean);

							// 商品番号
							detailBean.setShohinbango(rs2.getString("SHOHINBANGO"));
							// 個数
							detailBean.setKosu(rs2.getInt("KOSU"));
							// 種別
							detailBean.setShubetsu("2");
						}

					}
					if ("1".equals(chumonsts4)) {
						commonOrderBean.setHenpinFlg(true);
						// 返品設定日
						String dayTime = getStatusSetDay(chumonbango, "4", "1", "1");
						commonOrderBean.setHenpinsetteibi(dayTime);
					}

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return commonOrderBeanList;
	}

	private List<String> getPriority() throws Exception {
		List<String> priorityList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM PRIORITY_TBL ORDER BY PRIORITY";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				priorityList.add(rs.getString("CODE"));
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return priorityList;
	}

	public List<String[]> getJapanStockList() throws Exception {
		Connection conn = null;
		List<String[]> stockList = new ArrayList<String[]>();
		try {
			conn = JdbcConnection.getConnection();

			PreparedStatement ps = null;

			String sql = "SELECT * FROM TBL00012 WHERE STOCK_JP > 0";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				stockList.add(new String[] { rs.getString("COMMODITY_ID") + rs.getString("DETAIL_NO").replace("-0-0", ""),
						rs.getString("STOCK_JP") });
			}
		} finally {
			conn.close();
		}
		return stockList;
	}

	public List<String[]> getShanghaiStockList() throws Exception {
		Connection conn = null;
		List<String[]> stockList = new ArrayList<String[]>();
		try {
			conn = JdbcConnection.getConnection();

			PreparedStatement ps = null;

			String sql = "SELECT * FROM TBL00012 WHERE STOCK_SH > 0";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				stockList.add(new String[] { rs.getString("COMMODITY_ID") + rs.getString("DETAIL_NO").replace("-0-0", ""),
						rs.getString("STOCK_SH") });
			}
		} finally {
			conn.close();
		}
		return stockList;
	}

	public List<String[]> getUnsochuList() throws Exception {
		Connection conn = null;
		List<String[]> unsochuList = new ArrayList<String[]>();
		try {
			conn = JdbcConnection.getConnection();
			PreparedStatement ps = null;

			String sql = "SELECT * FROM TBL00013 T1 LEFT JOIN TBL00014 T2 ON T1.WAYBILL_NO = T2.WAYBILL_NO WHERE T1.STATUS = '00'";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				unsochuList.add(new String[] { rs.getString("T2.COMMODITY_ID"), rs.getString("QUANTITY"),
						rs.getString("WAYBILL_NO"), rs.getString("DELIVER_DAY") });
			}
		} finally {
			conn.close();
		}
		return unsochuList;
	}

	public List<String[]> getNyukaChuList() throws Exception {
		Connection conn = null;
		List<String[]> nyukachuList = new ArrayList<String[]>();
		try {
			conn = JdbcConnection.getConnection();
			PreparedStatement ps = null;
			String sql = "SELECT * FROM TBL00015 WHERE COMMODITY_STATUS = '0'";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nyukachuList.add(new String[] { rs.getString("COMMODITY_ID"), rs.getString("QUANTITY"),
						rs.getString("CREATE_DATE") });
			}
		} finally {
			conn.close();
		}
		return nyukachuList;
	}

	private String getStatusSetDay(String juchubango, String shubetsu, String code, String yukou) throws Exception {
		String dayTime = "";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM status_history_tbl WHERE JUCHUBANGO = ? AND STATUSSHUBETSU = ? AND STATUSCODE = ? AND YUKOU = ? ORDER BY CREATE_TIME ASC";
			ps = conn.prepareStatement(sql);
			ps.setString(1, juchubango);
			ps.setString(2, shubetsu);
			ps.setString(3, code);
			ps.setString(4, yukou);
			rs = ps.executeQuery();
			while (rs.next()) {
				dayTime = rs.getString("CREATE_TIME");
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return dayTime;

	}

	public int setStatus(String statusShubetsuArr, String statusArr, String chumonbango) throws Exception {
		String[] statusShubetsu = statusShubetsuArr.split("&");
		String[] status = statusArr.split("&");

		Connection conn = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			conn = JdbcConnection.getConnection();
			String sql = null;

			for (int i = 0; i < statusShubetsu.length; i++) {

				if ("1".equals(statusShubetsu[i])) {
					sql = "UPDATE common_order_tbl SET CHUMONSTS1 = ? WHERE CHUMONBANGO = ?";
				} else if ("2".equals(statusShubetsu[i])) {
					sql = "UPDATE common_order_tbl SET CHUMONSTS2 = ? WHERE CHUMONBANGO = ?";
				} else if ("3".equals(statusShubetsu[i])) {
					sql = "UPDATE common_order_tbl SET CHUMONSTS3 = ? WHERE CHUMONBANGO = ?";
				} else if ("4".equals(statusShubetsu[i])) {
					sql = "UPDATE common_order_tbl SET CHUMONSTS4 = ? WHERE CHUMONBANGO = ?";
				} else if ("5".equals(statusShubetsu[i])) {
					sql = "UPDATE common_order_tbl SET CHUMONSTS5 = ? WHERE CHUMONBANGO = ?";
				} else if ("6".equals(statusShubetsu[i])) {
					sql = "UPDATE common_order_tbl SET CHUMONSTS6 = ? WHERE CHUMONBANGO = ?";
				}
				ps = conn.prepareStatement(sql);
				ps.setString(1, status[i]);
				ps.setString(2, chumonbango);

				count = ps.executeUpdate();

				sql = "SELECT MAX(SHORIBANGO) SHORIBANGO FROM status_history_tbl";
				ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				String shoribango = "1000000000";
				while (rs.next()) {
					if (!Utility.isEmptyString(rs.getString("SHORIBANGO"))) {
						shoribango = String.valueOf(Long.valueOf(rs.getString("SHORIBANGO")) + 1);
					}
				}
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = format.format(new Date());

				sql = "INSERT INTO status_history_tbl(SHORIBANGO,JUCHUBANGO,STATUSSHUBETSU,STATUSCODE,YUKOU,CREATE_TIME)VALUES(?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shoribango);
				ps.setString(2, chumonbango);
				ps.setString(3, statusShubetsu[i]);
				ps.setString(4, status[i]);
				ps.setString(5, "1");
				ps.setString(6, date);
				ps.execute();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return count;

	}

	public List<HasouBean> getHasoList(String juchubango, String shubetsu) throws SQLException {
		List<HasouBean> hasoList = new ArrayList<HasouBean>();
		HasouBean hasoubean = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM hassou_tbl WHERE JUCHUBANGO = ? AND SHUBETSU = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, juchubango);
			ps.setString(2, shubetsu);
			rs = ps.executeQuery();
			while (rs.next()) {
				hasoubean = new HasouBean();
				hasoList.add(hasoubean);

				// 処理番号
				hasoubean.setShoribango(rs.getString("SHORIBANGO"));
				// 受注番号
				hasoubean.setJuchubango(rs.getString("JUCHUBANGO"));
				// 商品番号
				hasoubean.setShohinbango(rs.getString("SHOUHINBANGO"));
				// 発送個数
				hasoubean.setKosu(rs.getString("HASSOUKOSU"));
				// お荷物問い合わせ番号
				hasoubean.setToiawasebango(rs.getString("TOIAWASEBANGO"));
				// 種別
				hasoubean.setShubetsu(rs.getString("SHUBETSU"));
				// 運送会社
				hasoubean.setUnsokaisha(rs.getString("UNSOKAISHA"));
				// 発送日
				hasoubean.setHasobi(rs.getString("CREATE_TIME"));
				// 配送方法
				hasoubean.setHaisohoho(rs.getString("HAISOHOHO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return hasoList;
	}

	public List<SoushinList> getSoushinList(String orderNo) throws SQLException {
		List<SoushinList> soushinList = new ArrayList<SoushinList>();

		SoushinList shouhin = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM soushin_tbl WHERE JUCHUBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				shouhin = new SoushinList();
				shouhin.setSetteibi(rs.getString("UPDATE_TIME"));
				shouhin.setShoribango(rs.getString("SHORIBANGO"));
				shouhin.setSoushintype(rs.getString("SOUSHINTAIPU"));
				shouhin.setSoushinzumiflg(rs.getString("SOUSHINZUMIFLG"));
				soushinList.add(shouhin);
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return soushinList;

	}

	public List<String> hassou(List<String[]> hasoList, String toiawasebango, String unsokaisha, String orderNo,
			String shubetsu, String haisohoho, String shoribango) throws SQLException {
		boolean result = true;

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> errmsg = new ArrayList<String>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		try {
			conn = JdbcConnection.getConnection();
			if (Utility.isEmptyString(shoribango)) {
				shoribango = Utility.getShoribango();
			}

			String shoribango_soshin = "100000000";
			sql = "SELECT MAX(SHORIBANGO) SHORIBANGO FROM soushin_tbl";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (!Utility.isEmptyString(rs.getString("SHORIBANGO"))) {
					shoribango_soshin = rs.getString("SHORIBANGO");
				}
			}
			for (int i = 0; i < hasoList.size(); i++) {
				String[] shohin = hasoList.get(i);
				String shouhinbango = shohin[0];
				String kosu = shohin[1];
				sql = "SELECT STOCK_JP FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, Utility.getCommodityId(shouhinbango));
				ps.setString(2, Utility.getDetailN0(shouhinbango));

				rs = ps.executeQuery();
				String stockjp = "0";
				while (rs.next()) {
					stockjp = rs.getString("STOCK_JP");

				}
				if (Integer.valueOf(stockjp) - Integer.valueOf(kosu) < 0) {
					errmsg.add(shouhinbango + "在庫不足！");
					result = false;
					continue;
				}

				sql = "UPDATE TBL00012 SET STOCK_JP = ? WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
				ps = conn.prepareStatement(sql);

				ps.setString(1, String.valueOf(Integer.valueOf(stockjp) - Integer.valueOf(kosu)));

				ps.setString(2, Utility.getCommodityId(shouhinbango));
				ps.setString(3, Utility.getDetailN0(shouhinbango));

				ps.execute();

				int j = 1;
				sql = "INSERT INTO hassou_tbl (SHORIBANGO,JUCHUBANGO,SHOUHINBANGO,HASSOUKOSU,TOIAWASEBANGO,SHUBETSU,UNSOKAISHA,HAISOHOHO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(j++, shoribango);
				ps.setString(j++, orderNo);
				ps.setString(j++, shouhinbango);
				ps.setString(j++, kosu);
				ps.setString(j++, toiawasebango);
				ps.setString(j++, shubetsu);
				ps.setString(j++, unsokaisha);
				ps.setString(j++, haisohoho);
				ps.setString(j++, date);
				ps.setString(j++, Utility.getUser());
				ps.setString(j++, date);
				ps.setString(j++, Utility.getUser());
				ps.execute();
			}
			if ("1".equals(shubetsu) || "2".equals(shubetsu)) {
				int j = 1;
				shoribango_soshin = String.valueOf(Long.valueOf(shoribango_soshin) + 1);
				sql = "INSERT INTO soushin_tbl (SHORIBANGO,JUCHUBANGO,SOUSHINTAIPU,SOUSHINZUMIFLG,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(j++, shoribango_soshin);
				ps.setString(j++, orderNo);
				ps.setString(j++, shubetsu);
				ps.setString(j++, "0");
				ps.setString(j++, date);
				ps.setString(j++, Utility.getUser());
				ps.setString(j++, date);
				ps.setString(j++, Utility.getUser());
				ps.execute();
			}
			if (result) {
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			result = false;
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public String setSoushinzumi(List<String> shoribangoList) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String result = "true";
		try {
			conn = JdbcConnection.getConnection();
			String sql = null;

			sql = "UPDATE soushin_tbl SET SOUSHINZUMIFLG = ? , UPDATE_TIME = ? WHERE SHORIBANGO = ?";

			ps = conn.prepareStatement(sql);

			for (String shoribango : shoribangoList) {
				ps.setString(1, "1");
				ps.setString(2, Utility.getDateTime());
				ps.setString(3, shoribango);
				ps.execute();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			result = "false";
		} finally {
			conn.close();
		}
		return result;
	}

	public List<String[]> getMachiListAll(CommonOrderBean thisOrder, List<ShouhinStsBean> shouhinStsBeanList,
			String type1, String type2) throws NumberFormatException, Exception {
		List<String[]> hasomachiList = new ArrayList<String[]>();
		List<CommonOrderDetailrBean> commonOrderDetailrBeanList = thisOrder.getCommonOrderDetailBeanList();
		String orderNo = thisOrder.getJuchubango();
		if (!Utility.isEmptyList(commonOrderDetailrBeanList)) {
			for (int i = 0; i < commonOrderDetailrBeanList.size(); i++) {
				CommonOrderDetailrBean detailBean = commonOrderDetailrBeanList.get(i);
				// 商品番号
				String shohinbango = detailBean.getShohinbango();
				// 从商品bean里面检索这件商品的发送状况
				ShouhinStsBean thisShohin = null;
				for (int j = 0; j < shouhinStsBeanList.size(); j++) {
					ShouhinStsBean shohinsts = shouhinStsBeanList.get(j);
					if (shohinsts.getShouhinbango().equals(shohinbango)) {
						thisShohin = shohinsts;
					}
				}
				// 检查这个商品编号里面有没有对应的这单的信息
				if (thisShohin != null) {
					List<ShohinStsInfoBean> shohinStsInfoBeanList = thisShohin.getShohinStsInfoBeanList();
					String[] hasomachi = null;
					for (int j = 0; j < shohinStsInfoBeanList.size(); j++) {
						ShohinStsInfoBean infoBean = shohinStsInfoBeanList.get(j);
						if (infoBean.getJuchubango().equals(orderNo)
								&& (type1.equals(infoBean.getHoryusts()) || type2.equals(infoBean.getHoryusts()))) {

							// 入荷
							if (!"0".equals(infoBean.getHoryukosuNyuka())) {
								hasomachi = new String[5];
								hasomachiList.add(hasomachi);
								// 进货中。。 运送途中之类情报设置在画面上
								hasomachi[0] = thisShohin.getShouhinbango();
								hasomachi[2] = "入荷中";
								hasomachi[3] = "0";
								hasomachi[1] = infoBean.getHoryukosuNyuka();
								hasomachi[4] = "";
							}
							if (!"0".equals(infoBean.getHoryukosuSh())) {
								hasomachi = new String[5];
								hasomachiList.add(hasomachi);
								// 进货中。。 运送途中之类情报设置在画面上
								hasomachi[0] = thisShohin.getShouhinbango();
								hasomachi[2] = "上海在庫";
								hasomachi[3] = "0";
								hasomachi[1] = infoBean.getHoryukosuSh();

								Calendar date = Calendar.getInstance();
								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
								String latestData = sdf.format(date.getTime());
								if ("7".equals(Utility.dayForWeek(latestData))) {
									date.add(Calendar.DATE, 1);
								}

								date.add(Calendar.DATE, 3);
								if ("7".equals(Utility.dayForWeek(latestData))) {
									date.add(Calendar.DATE, 1);
								}
								latestData = sdf.format(date.getTime());

								hasomachi[4] = latestData;

							}
							if (!"0".equals(infoBean.getHoryukosuUnso())) {
								hasomachi = new String[5];
								hasomachiList.add(hasomachi);
								// 进货中。。 运送途中之类情报设置在画面上
								hasomachi[0] = thisShohin.getShouhinbango();
								hasomachi[2] = "運送中";
								hasomachi[3] = "0";
								hasomachi[1] = infoBean.getHoryukosuUnso();

								String latestData = "";
								if (!Utility.isEmptyList(infoBean.getHasoubi())) {
									for (String data : infoBean.getHasoubi()) {
										if (Utility.isEmptyString(latestData)) {
											latestData = data;
										} else {
											if (Long.valueOf(data) > Long.valueOf(latestData)) {
												latestData = data;
											}
										}
									}
								}

								Calendar date = Calendar.getInstance();

								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
								date.setTime(sdf.parse(latestData));
								date.add(Calendar.DATE, 3);
								if ("7".equals(Utility.dayForWeek(latestData))) {
									date.add(Calendar.DATE, 1);
								}
								latestData = sdf.format(date.getTime());

								hasomachi[4] = latestData;

							}
							if (!"0".equals(infoBean.getHoryukosuJp())) {
								hasomachi = new String[5];
								hasomachiList.add(hasomachi);
								// 进货中。。 运送途中之类情报设置在画面上
								hasomachi[0] = thisShohin.getShouhinbango();
								hasomachi[2] = "発送待ち";
								hasomachi[1] = infoBean.getHoryukosuJp();

								Calendar date = Calendar.getInstance();

								SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
								String latestData = sdf.format(date.getTime());
								if ("7".equals(Utility.dayForWeek(latestData))) {
									date.add(Calendar.DATE, 1);
								}
								latestData = sdf.format(date.getTime());

								hasomachi[4] = latestData;

							}
							int nyukakosu = detailBean.getKosu() - Integer.valueOf(infoBean.getHoryukosuJp())
									- Integer.valueOf(infoBean.getHoryukosuUnso())
									- Integer.valueOf(infoBean.getHoryukosuSh())
									- Integer.valueOf(infoBean.getHoryukosuNyuka());
							if (nyukakosu > 0) {
								hasomachi = new String[5];
								hasomachiList.add(hasomachi);
								// 进货中。。 运送途中之类情报设置在画面上
								hasomachi[0] = thisShohin.getShouhinbango();
								hasomachi[2] = "入荷待ち";
								hasomachi[3] = "0";
								hasomachi[1] = String.valueOf(nyukakosu);
								hasomachi[4] = "";
							}
						}
					}
				}

			}
		}
		if ("5".equals(type1) && "6".equals(type2)) {
			List<String[]> tempList = new ArrayList<String[]>();
			for (String[] strArr : hasomachiList) {
				if ("発送待ち".equals(strArr[2])) {
					tempList.add(strArr);
				}
			}
			for (String[] strArr : hasomachiList) {
				if ("運送中".equals(strArr[2])) {
					tempList.add(strArr);
				}
			}
			for (String[] strArr : hasomachiList) {
				if ("上海在庫".equals(strArr[2])) {
					tempList.add(strArr);
				}
			}
			for (String[] strArr : hasomachiList) {
				if ("入荷中".equals(strArr[2])) {
					tempList.add(strArr);
				}
			}
			for (String[] strArr : hasomachiList) {
				if ("入荷待ち".equals(strArr[2])) {
					tempList.add(strArr);
				}
			}
			hasomachiList = tempList;
		}
		return hasomachiList;
	}

	public void checkIsShorizumi(String orderNo) throws Exception {
		List<CommonOrderBean> commonOrderBeanList = getCommonOrderBeanList();
		boolean shorizumi = false;
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (orderNo.equals(commonOrderBean.getJuchubango())) {
				if (Utility.isEmptyList(commonOrderBean.getCommonOrderDetailBeanList())) {
					// 查看是否有退货途中的
					int koukanCount = 0;
					int henpinCount = 0;
					int henkincount = 0;
					Connection conn = null;
					PreparedStatement ps = null;
					ResultSet rs = null;
					try {
						conn = JdbcConnection.getConnection();
						String sql = null;

						sql = "SELECT COUNT(*) COUNT FROM koukan_hasou_tbl WHERE JUCHUBANGO = ? AND (KOUKANSTATUS = '0' OR KOUKANSTATUS = '1' OR KOUKANSTATUS = '3')";

						ps = conn.prepareStatement(sql);
						ps.setString(1, orderNo);
						rs = ps.executeQuery();
						while (rs.next()) {
							koukanCount = rs.getInt("COUNT");
						}

						sql = "SELECT COUNT(*) COUNT FROM henpin_tbl WHERE JUCHUBANGO = ? AND (HENPINSTATUS = '0' OR HENPINSTATUS = '1')";

						ps = conn.prepareStatement(sql);
						ps.setString(1, orderNo);
						rs = ps.executeQuery();
						while (rs.next()) {
							henpinCount = rs.getInt("COUNT");
						}

						sql = "SELECT COUNT(*) COUNT FROM henkin_tbl WHERE JUCHUBANGO = ? AND HENKINZUMIFLG = '0'";

						ps = conn.prepareStatement(sql);
						ps.setString(1, orderNo);
						rs = ps.executeQuery();
						while (rs.next()) {
							henkincount = rs.getInt("COUNT");
						}

					} catch (Exception e) {
						e.printStackTrace();
						conn.rollback();
						throw e;
					} finally {
						conn.close();
					}

					if (koukanCount == 0 && henpinCount == 0 && henkincount == 0) {
						shorizumi = true;
					}
				}
			}
		}

		if (shorizumi) {
			setStatus("1", "3", orderNo);
		}
	}

	public List<String[]> getHenpinListAll(String orderNo) throws Exception {
		List<String[]> henpinList = new ArrayList<String[]>();
		String[] henpin = null;

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = null;

			sql = "SELECT * FROM hassou_tbl WHERE JUCHUBANGO = ? AND (SHUBETSU = '0' OR SHUBETSU = '1' OR SHUBETSU = '2' OR SHUBETSU = '3')";

			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				henpin = new String[4];
				henpinList.add(henpin);

				// 商品番号
				henpin[0] = rs.getString("SHOUHINBANGO");
				// 発送個数
				henpin[1] = rs.getString("HASSOUKOSU");
				// 種別
				henpin[2] = rs.getString("SHUBETSU");
				// 発送日時
				henpin[3] = rs.getString("CREATE_TIME");
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return henpinList;
	}

	public String getHassouShubetsuName(String code) {
		switch (code) {
		case "0":
			return "初期発送";
		case "1":
			return "分納発送";
		case "2":
			return "追加発送";
		case "3":
			return "交換発送";
		case "4":
			return "返品受付";
		case "5":
			return "交換受付";
		default:
			return "";
		}
	}

	public static String getStatus2Name(String code, String value) {
		if ("1".equals(value)) {
			switch (code) {
			case "2":
				return "至急";
			case "3":
				return "分納";
			case "4":
				return "返品";
			case "5":
				return "交換";
			case "6":
				return "追加";
			default:
				return "";
			}
		}
		return "";
	}

	public static String getStatus1Name(String code) {
		switch (code) {
		case "0":
			return "新規";
		case "1":
			return "発送前確認";
		case "2":
			return "発送待ち";
		case "3":
			return "処理済";
		case "4":
			return "キャンセル";
		case "5":
			return "保留";
		case "6":
			return "その他";
		default:
			return "";
		}

	}

	public List<String> addHenpin(List<String[]> henpinList, String[] henpinriyuTenpo, String[] henpinriyuOkyaku,
			String henpinriyuTenpoSonota, String henpinriyuOkyakuSonota, String shokisoryofutan,
			String uketorisoryofutan, String henkinFlg, String henkinkingaku, String biko, String orderNo)
			throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			String shoribango = Utility.getShoribango();

			int count = 0;
			for (int i = 0; i < henpinList.size(); i++) {
				String[] shohin = henpinList.get(i);
				String shouhinbango = shohin[0];
				String kosu = shohin[1];

				sql = "INSERT INTO henpin_tbl(SHORIBANGO,JUCHUBANGO,SHOHINBANGO,HENPINKOSU,HENPINSHUBETSU,HENPINSTATUS,SAIRIYOUKANOUFLG,KAKUNINHITUYO,HENPINRIYUTENPO,HENPINRIYUSONOTA,HENPINRIYUOKYAKU,HENPINRIYUOKYAKUSONOTA,SHOKIHASOUSORYOFUTAN,UKETORISORYOFUTAN,HENKINHITUYO,HENKINKINGAKU,BIKO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				int j = 1;
				ps.setString(j++, shoribango);
				ps.setString(j++, orderNo);
				ps.setString(j++, shouhinbango);
				ps.setString(j++, kosu);
				ps.setString(j++, shohin[2]);
				ps.setString(j++, "0");// 返品待ち
				ps.setString(j++, shohin[3]);
				ps.setString(j++, shohin[4]);
				String riyotenpo = "";
				if (henpinriyuTenpo != null) {
					for (String riyu : henpinriyuTenpo) {
						if (Utility.isEmptyString(riyotenpo)) {
							riyotenpo = riyu;
						} else {
							riyotenpo = riyotenpo + "&" + riyu;
						}
					}
				}
				ps.setString(j++, riyotenpo);
				ps.setString(j++, henpinriyuTenpoSonota);
				String riyookyaku = "";
				if (henpinriyuOkyaku != null) {
					for (String riyu : henpinriyuOkyaku) {
						if (Utility.isEmptyString(riyookyaku)) {
							riyookyaku = riyu;
						} else {
							riyookyaku = riyookyaku + "&" + riyu;
						}
					}
				}
				ps.setString(j++, riyookyaku);
				ps.setString(j++, henpinriyuOkyakuSonota);
				ps.setString(j++, shokisoryofutan);
				ps.setString(j++, uketorisoryofutan);
				ps.setString(j++, "true".equals(henkinFlg) ? "1" : "0");
				if ("true".equals(henkinFlg)) {
					ps.setString(j++, henkinkingaku);
				} else {
					ps.setString(j++, "");
				}
				ps.setString(j++, biko);
				ps.setString(j++, Utility.getDateTime());
				ps.setString(j++, Utility.getUser());
				ps.setString(j++, Utility.getDateTime());
				ps.setString(j++, Utility.getUser());

				count += ps.executeUpdate();
			}
			if ("true".equals(henkinFlg)) {
				sql = "INSERT INTO henkin_tbl(SHORIBANGO,JUCHUBANGO,HENKINKINGAKU,HENKINSHUBETSU,HENKINZUMIFLG,HENKINBI,BIKO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				int j = 1;
				ps.setString(j++, shoribango);
				ps.setString(j++, orderNo);
				ps.setString(j++, henkinkingaku);
				ps.setString(j++, "1");
				ps.setString(j++, "0");
				ps.setString(j++, "");
				ps.setString(j++, "");
				ps.setString(j++, Utility.getDateTime());
				ps.setString(j++, Utility.getUser());
				ps.setString(j++, Utility.getDateTime());
				ps.setString(j++, Utility.getUser());
				ps.executeUpdate();
			}

			if (count > 0) {
				conn.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<HenpinSeteiZumiList> getHenpinList(String orderNo) throws Exception {
		List<HenpinSeteiZumiList> henpinList = new ArrayList<HenpinSeteiZumiList>();
		HenpinSeteiZumiList henpin = null;
		List<HenpinDetail> detailList = null;
		HenpinDetail detail = null;

		String shoribango = "";

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM henpin_tbl T1 LEFT JOIN hassou_tbl T2 ON T1.SHORIBANGO = T2.SHORIBANGO AND T1.JUCHUBANGO = T2.JUCHUBANGO AND T1.SHOHINBANGO = T2.SHOUHINBANGO WHERE T1.JUCHUBANGO = ? ORDER BY T1.SHORIBANGO";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (Utility.isEmptyString(shoribango) || !shoribango.equals(rs.getString("T1.SHORIBANGO"))) {
					shoribango = rs.getString("T1.SHORIBANGO");
					henpin = new HenpinSeteiZumiList();
					henpinList.add(henpin);
					detailList = new ArrayList<HenpinDetail>();
					henpin.setDetailList(detailList);

					henpin.setHenpinshoribango(rs.getString("T1.SHORIBANGO"));
					String henpinsts = rs.getString("T1.HENPINSTATUS");
					henpin.setHenpinsts(henpinsts);
					if ("0".equals(henpinsts)) {
						henpin.setHenpinstsName("返品待ち");
					} else if ("1".equals(henpinsts)) {
						henpin.setHenpinstsName("返品中");
					} else if ("2".equals(henpinsts)) {
						henpin.setHenpinstsName("返品済み");
					}
					henpin.setHenpinhasonichiji(rs.getString("T2.CREATE_TIME"));
					henpin.setHenpinhaisokaisha(Utility.getHaisokaishaName(rs.getString("T2.UNSOKAISHA")));
					henpin.setHenpinhaisouhoho(Utility.getHaisohohoName(rs.getString("T2.HAISOHOHO")));
					henpin.setToiawasebango(rs.getString("T2.TOIAWASEBANGO"));
					// henpin.setHenkinstsName(rs.getString("T2.TOIAWASEBANGO"));
					// henpin.setHenkinsts(rs.getString("T1.HENKINHITUYO"));
					// henpin.setHenkinkingaku(rs.getString("T1.HENKINKINGAKU"));
					henpin.setHenpinbiko(rs.getString("T1.BIKO"));

				}

				detail = new HenpinDetail();
				detailList.add(detail);
				detail.setShohinbango(rs.getString("T1.SHOHINBANGO"));
				detail.setKosu(rs.getString("T1.HENPINKOSU"));
				detail.setShubetsu(rs.getString("T1.HENPINSHUBETSU"));
				detail.setSairiyokanoflg(rs.getString("T1.SAIRIYOUKANOUFLG"));
				detail.setKakuninflg(rs.getString("T1.KAKUNINHITUYO"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return henpinList;
	}

	public List<HenpinshuseikaList> getHenpinshuseikaList(String orderNo) throws Exception {
		List<HenpinshuseikaList> henpinList = new ArrayList<HenpinshuseikaList>();
		HenpinshuseikaList henpin = null;

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String shoribango = "";
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM henpin_tbl WHERE JUCHUBANGO = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (Utility.isEmptyString(shoribango) || !shoribango.equals(rs.getString("SHORIBANGO"))) {
					shoribango = rs.getString("SHORIBANGO");
					henpin = new HenpinshuseikaList();
					henpinList.add(henpin);

					henpin.setShoribango(rs.getString("SHORIBANGO"));
					henpin.setHenpinriyutenpogawa(rs.getString("HENPINRIYUTENPO"));
					henpin.setHenpinriyutenpogawasonota(rs.getString("HENPINRIYUSONOTA"));
					henpin.setHenpinriyuokyakugawa(rs.getString("HENPINRIYUOKYAKU"));
					henpin.setHenpinriyuokyakugawasonota(rs.getString("HENPINRIYUOKYAKUSONOTA"));
					henpin.setShokihasosoryofutan(rs.getString("SHOKIHASOUSORYOFUTAN"));
					henpin.setUketorisoryofutan(rs.getString("UKETORISORYOFUTAN"));
					henpin.setHenkinhistsuyoflg(rs.getString("HENKINHITUYO"));
					henpin.setHenkinkagaku(rs.getString("HENKINKINGAKU"));
					henpin.setSairiyokanoflg(rs.getString("SAIRIYOUKANOUFLG"));
					henpin.setKakuninhituyo(rs.getString("KAKUNINHITUYO"));
					henpin.setBiko(rs.getString("BIKO"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return henpinList;
	}

	public List<HenkinListBean> getHenkinList(String orderNo) throws SQLException {
		List<HenkinListBean> henkinList = new ArrayList<HenkinListBean>();
		HenkinListBean henkin = null;

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM henkin_tbl WHERE JUCHUBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				henkin = new HenkinListBean();
				henkinList.add(henkin);

				// 処理番号
				henkin.setShoribango(rs.getString("SHORIBANGO"));
				// 受注番号
				henkin.setJuchubango(rs.getString("JUCHUBANGO"));
				// 返金金額
				henkin.setHenkinkingaku(rs.getString("HENKINKINGAKU"));
				// 返金種別
				henkin.setHenkinshubetsu(rs.getString("HENKINSHUBETSU"));
				// 返金済みフラグ
				henkin.setHenkinzumiflg(rs.getString("HENKINZUMIFLG"));
				// 返金日
				henkin.setHenkinbi(rs.getString("HENKINBI"));
				// 備考
				henkin.setBiko(rs.getString("BIKO"));
				henkin.setSettibi(rs.getString("CREATE_TIME"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return henkinList;

	}

	public List<String> updateHenpin(List<String[]> henpinshohinArry, String shoribango_henpinshusei,
			String[] henpinriyuTenpo_henpinshusei, String henpinriyuTenpoSonota_henpinshusei,
			String[] henpinriyuOkyaku_henpinshusei, String henpinriyuOkyakuSonota_henpinshusei,
			String henpinsoryofutanshokihasou_henpinshusei, String henpinsoryofutanuketori_henpinshusei,
			String henpinhenkinhituyoFlg_henpinshusei, String henpinhenkinkigaku_henpinshusei,
			String henpinbiko_henpinshusei, String orderNo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			String shoribango = shoribango_henpinshusei;

			String createTime = "";
			String createUser = "";
			String henpinsts = "";

			String henkinzumiflg = "";
			String henkinbi = "";
			String biko = "";

			sql = "SELECT CREATE_TIME,CREATE_USER, HENPINSTATUS FROM henpin_tbl WHERE SHORIBANGO =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			rs = ps.executeQuery();
			while (rs.next()) {
				createTime = rs.getString("CREATE_TIME");
				createUser = rs.getString("CREATE_USER");
				henpinsts = rs.getString("HENPINSTATUS");
			}

			sql = "SELECT HENKINZUMIFLG,HENKINBI, BIKO FROM henkin_tbl WHERE SHORIBANGO =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			rs = ps.executeQuery();
			while (rs.next()) {
				henkinzumiflg = rs.getString("HENKINZUMIFLG");
				henkinbi = rs.getString("HENKINBI");
				biko = rs.getString("BIKO");
			}

			sql = "DELETE FROM henpin_tbl WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			ps.execute();

			if (!"1".equals(henkinzumiflg)) {
				sql = "DELETE FROM henkin_tbl WHERE SHORIBANGO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shoribango);
				ps.execute();
			}
			int count = 0;
			for (int i = 0; i < henpinshohinArry.size(); i++) {
				String[] shohin = henpinshohinArry.get(i);
				String shouhinbango = shohin[0];
				String kosu = shohin[1];

				sql = "INSERT INTO henpin_tbl(SHORIBANGO,JUCHUBANGO,SHOHINBANGO,HENPINKOSU,HENPINSHUBETSU,HENPINSTATUS,SAIRIYOUKANOUFLG,KAKUNINHITUYO,HENPINRIYUTENPO,HENPINRIYUSONOTA,HENPINRIYUOKYAKU,HENPINRIYUOKYAKUSONOTA,SHOKIHASOUSORYOFUTAN,UKETORISORYOFUTAN,HENKINHITUYO,HENKINKINGAKU,BIKO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				int j = 1;
				ps.setString(j++, shoribango);
				ps.setString(j++, orderNo);
				ps.setString(j++, shouhinbango);
				ps.setString(j++, kosu);
				ps.setString(j++, shohin[2]);
				ps.setString(j++, henpinsts);// 返品待ち
				ps.setString(j++, shohin[3]);
				ps.setString(j++, shohin[4]);
				String riyotenpo = "";
				if (henpinriyuTenpo_henpinshusei != null) {
					for (String riyu : henpinriyuTenpo_henpinshusei) {
						if (Utility.isEmptyString(riyotenpo)) {
							riyotenpo = riyu;
						} else {
							riyotenpo = riyotenpo + "&" + riyu;
						}
					}
				}
				ps.setString(j++, riyotenpo);
				ps.setString(j++, henpinriyuTenpoSonota_henpinshusei);
				String riyookyaku = "";
				if (henpinriyuOkyaku_henpinshusei != null) {
					for (String riyu : henpinriyuOkyaku_henpinshusei) {
						if (Utility.isEmptyString(riyookyaku)) {
							riyookyaku = riyu;
						} else {
							riyookyaku = riyookyaku + "&" + riyu;
						}
					}
				}
				ps.setString(j++, riyookyaku);
				ps.setString(j++, henpinriyuOkyakuSonota_henpinshusei);
				ps.setString(j++, henpinsoryofutanshokihasou_henpinshusei);
				ps.setString(j++, henpinsoryofutanuketori_henpinshusei);
				ps.setString(j++, "true".equals(henpinhenkinhituyoFlg_henpinshusei) ? "1" : "0");
				if ("true".equals(henpinhenkinhituyoFlg_henpinshusei)) {
					ps.setString(j++, henpinhenkinkigaku_henpinshusei);
				} else {
					ps.setString(j++, "");
				}
				ps.setString(j++, henpinbiko_henpinshusei);
				ps.setString(j++, createTime);
				ps.setString(j++, createUser);
				ps.setString(j++, Utility.getDateTime());
				ps.setString(j++, Utility.getUser());

				count += ps.executeUpdate();
			}

			if ("true".equals(henpinhenkinhituyoFlg_henpinshusei) && !"1".equals(henkinzumiflg)) {
				sql = "INSERT INTO henkin_tbl(SHORIBANGO,JUCHUBANGO,HENKINKINGAKU,HENKINSHUBETSU,HENKINZUMIFLG,HENKINBI,BIKO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				int j = 1;
				ps.setString(j++, shoribango);
				ps.setString(j++, orderNo);
				ps.setString(j++, henpinhenkinkigaku_henpinshusei);
				ps.setString(j++, "1");
				ps.setString(j++, henkinzumiflg);
				ps.setString(j++, henkinbi);
				ps.setString(j++, biko);
				ps.setString(j++, createTime);
				ps.setString(j++, createUser);
				ps.setString(j++, Utility.getDateTime());
				ps.setString(j++, Utility.getUser());
				ps.executeUpdate();
			}

			if (count > 0) {
				conn.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> deleteHenpin(String orderNo, String shoribango) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			int count = 0;

			sql = "DELETE FROM henpin_tbl WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			count += ps.executeUpdate();

			sql = "DELETE FROM henkin_tbl WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			count += ps.executeUpdate();

			if (count > 0) {
				conn.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> henpinhaso(String shoribango, String toiawasebango, String haisokaisha, String haisohoho)
			throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			List<String[]> hasouList = new ArrayList<String[]>();

			String orderNo = "";
			String shohinbango = "";
			String hasokosu = "";
			String shubetsu = "4";

			sql = "SELECT * FROM henpin_tbl WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			rs = ps.executeQuery();
			while (rs.next()) {
				orderNo = rs.getString("JUCHUBANGO");
				shohinbango = rs.getString("SHOHINBANGO");
				hasokosu = rs.getString("HENPINKOSU");
				hasouList.add(new String[] { shohinbango, hasokosu });

			}
			for (int i = 0; i < hasouList.size(); i++) {
				String[] shohin = hasouList.get(i);
				String shouhinbango = shohin[0];
				String kosu = shohin[1];

				int j = 1;
				sql = "INSERT INTO hassou_tbl (SHORIBANGO,JUCHUBANGO,SHOUHINBANGO,HASSOUKOSU,TOIAWASEBANGO,SHUBETSU,UNSOKAISHA,HAISOHOHO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(j++, shoribango);
				ps.setString(j++, orderNo);
				ps.setString(j++, shouhinbango);
				ps.setString(j++, kosu);
				ps.setString(j++, toiawasebango);
				ps.setString(j++, shubetsu);
				ps.setString(j++, haisokaisha);
				ps.setString(j++, haisohoho);
				ps.setString(j++, Utility.getDateTime());
				ps.setString(j++, Utility.getUser());
				ps.setString(j++, Utility.getDateTime());
				ps.setString(j++, Utility.getUser());
				ps.execute();
			}
			updateHenpinSts(shoribango, "1");
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> updateHenpinSts(String shoribango, String status) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "UPDATE henpin_tbl SET HENPINSTATUS = ? WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, status);
			ps.setString(2, shoribango);
			ps.executeUpdate();

			if ("0".equals(status)) {
				sql = "DELETE FROM hassou_tbl WHERE SHORIBANGO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shoribango);
				ps.execute();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> setHenpinzumi(String shoribango) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		List<String[]> henpinList = new ArrayList<String[]>();
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM henpin_tbl WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			rs = ps.executeQuery();
			while (rs.next()) {
				if ("1".equals(rs.getString("SAIRIYOUKANOUFLG"))) {
					henpinList.add(new String[] { rs.getString("SHOHINBANGO"), rs.getString("HENPINKOSU") });
				}
			}
			sql = "UPDATE tbl00012 SET STOCK_JP = STOCK_JP + ?  ,UPDATE_TIME = ? ,UPDATE_USER = ? WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
			for (String[] detail : henpinList) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, detail[1]);
				ps.setString(2, Utility.getDateTime());
				ps.setString(3, Utility.getUser());
				ps.setString(4, Utility.getCommodityId(detail[0]));
				ps.setString(5, Utility.getDetailN0(detail[0]));

				ps.execute();
			}

			sql = "UPDATE henpin_tbl SET HENPINSTATUS = ?  ,UPDATE_TIME = ? ,UPDATE_USER = ? WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "2");
			ps.setString(2, Utility.getDateTime());
			ps.setString(3, Utility.getUser());
			ps.setString(4, shoribango);

			ps.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> addTuika(List<String[]> tuikaList, String[] tuikariyu, String tuikasoryofutan,
			String tuikariyuSonota, String tuikabiko, String orderNo) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			String shoribango = Utility.getShoribango();

			int count = 0;
			for (int i = 0; i < tuikaList.size(); i++) {
				String[] shohin = tuikaList.get(i);
				String shouhinbango = shohin[0];
				String kosu = shohin[1];

				sql = "INSERT INTO tuika_hasou_tbl(SHORIBANGO,JUCHUBANGO,SHOHINBANGO,KOSU,STATUS,HASORIYU,HASORIYUSONOTA,SOURYOUFUTAN,BIKO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				int j = 1;
				ps.setString(j++, shoribango);
				ps.setString(j++, orderNo);
				ps.setString(j++, shouhinbango);
				ps.setString(j++, kosu);
				ps.setString(j++, "0");// 発送待ち
				String hasoriyu = "";
				if (tuikariyu != null) {
					for (String riyu : tuikariyu) {
						if (Utility.isEmptyString(hasoriyu)) {
							hasoriyu = riyu;
						} else {
							hasoriyu = hasoriyu + "&" + riyu;
						}
					}
				}
				ps.setString(j++, hasoriyu);
				ps.setString(j++, tuikariyuSonota);
				ps.setString(j++, tuikasoryofutan);
				ps.setString(j++, tuikabiko);
				ps.setString(j++, Utility.getDateTime());
				ps.setString(j++, Utility.getUser());
				ps.setString(j++, Utility.getDateTime());
				ps.setString(j++, Utility.getUser());

				count += ps.executeUpdate();
			}

			if (count > 0) {
				conn.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<TuikaList> getTuikaList(String orderNo) throws Exception {
		List<TuikaList> tuikaList = new ArrayList<TuikaList>();
		TuikaList tuika = null;
		List<TuikaDetail> detailList = null;
		TuikaDetail detail = null;

		String shoribango = "";

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM tuika_hasou_tbl T1 LEFT JOIN hassou_tbl T2 ON T1.SHORIBANGO = T2.SHORIBANGO AND T1.JUCHUBANGO = T2.JUCHUBANGO AND T1.SHOHINBANGO = T2.SHOUHINBANGO WHERE T1.JUCHUBANGO = ? ORDER BY T1.SHORIBANGO";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (Utility.isEmptyString(shoribango) || !shoribango.equals(rs.getString("T1.SHORIBANGO"))) {
					shoribango = rs.getString("T1.SHORIBANGO");
					tuika = new TuikaList();
					tuikaList.add(tuika);
					detailList = new ArrayList<TuikaDetail>();
					tuika.setDetailList(detailList);

					tuika.setTuikashoribango(rs.getString("T1.SHORIBANGO"));
					String Tuikasts = rs.getString("T1.STATUS");
					tuika.setTuikasts(Tuikasts);
					if ("0".equals(Tuikasts)) {
						tuika.setTuikastsName("発送待ち");
					} else if ("1".equals(Tuikasts)) {
						tuika.setTuikastsName("発送済み");
					}
					tuika.setTuikahasonichiji(rs.getString("T2.CREATE_TIME"));
					tuika.setTuikahaisokaisha(Utility.getHaisokaishaName(rs.getString("T2.UNSOKAISHA")));
					tuika.setTuikahaisouhoho(Utility.getHaisohohoName(rs.getString("T2.HAISOHOHO")));
					tuika.setToiawasebango(rs.getString("T2.TOIAWASEBANGO"));

					tuika.setTuikabiko(rs.getString("T1.BIKO"));

				}
				detail = new TuikaDetail();
				detailList.add(detail);
				detail.setShohinbango(rs.getString("T1.SHOHINBANGO"));
				detail.setKosu(rs.getString("T1.KOSU"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return tuikaList;
	}

	public List<String> updateTuika(List<String[]> tuikashohinArry, String shoribango_tuikashusei,
			String[] tuikariyu_tuikashusei, String tuikariyuSonota_tuikashusei, String tuikasoryofutan_tuikashusei,
			String tuikabiko_tuikashusei, String orderNo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			String shoribango = shoribango_tuikashusei;

			String createTime = "";
			String createUser = "";
			String tuikasts = "";

			sql = "SELECT CREATE_TIME,CREATE_USER, STATUS FROM tuika_hasou_tbl WHERE SHORIBANGO =?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			rs = ps.executeQuery();
			while (rs.next()) {
				createTime = rs.getString("CREATE_TIME");
				createUser = rs.getString("CREATE_USER");
				tuikasts = rs.getString("STATUS");
			}

			sql = "DELETE FROM tuika_hasou_tbl WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			ps.execute();

			sql = "DELETE FROM tuika_hasou_tbl WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			ps.execute();

			int count = 0;
			for (int i = 0; i < tuikashohinArry.size(); i++) {
				String[] shohin = tuikashohinArry.get(i);
				String shouhinbango = shohin[0];
				String kosu = shohin[1];

				sql = "INSERT INTO tuika_hasou_tbl(SHORIBANGO,JUCHUBANGO,SHOHINBANGO,KOSU,STATUS,HASORIYU,HASORIYUSONOTA,SOURYOUFUTAN,BIKO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				int j = 1;
				ps.setString(j++, shoribango);
				ps.setString(j++, orderNo);
				ps.setString(j++, shouhinbango);
				ps.setString(j++, kosu);
				ps.setString(j++, tuikasts);
				String hasoriyu = "";
				if (tuikariyu_tuikashusei != null) {
					for (String riyu : tuikariyu_tuikashusei) {
						if (Utility.isEmptyString(hasoriyu)) {
							hasoriyu = riyu;
						} else {
							hasoriyu = hasoriyu + "&" + riyu;
						}
					}
				}
				ps.setString(j++, hasoriyu);
				ps.setString(j++, tuikariyuSonota_tuikashusei);
				ps.setString(j++, tuikasoryofutan_tuikashusei);
				ps.setString(j++, tuikabiko_tuikashusei);
				ps.setString(j++, createTime);
				ps.setString(j++, createUser);
				ps.setString(j++, Utility.getDateTime());
				ps.setString(j++, Utility.getUser());

				count += ps.executeUpdate();
			}

			if (count > 0) {
				conn.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> deleteTuika(String orderNo, String shoribango) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			int count = 0;

			sql = "DELETE FROM tuika_hasou_tbl WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			count += ps.executeUpdate();

			sql = "DELETE FROM tuika_hasou_tbl WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			count += ps.executeUpdate();

			if (count > 0) {
				conn.commit();
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> tuikahaso(String shoribango, String toiawasebango, String haisokaisha, String haisohoho)
			throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			List<String[]> hasouList = new ArrayList<String[]>();

			String orderNo = "";
			String shohinbango = "";
			String hasokosu = "";
			String shubetsu = "2";

			sql = "SELECT * FROM tuika_hasou_tbl WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			rs = ps.executeQuery();
			while (rs.next()) {
				orderNo = rs.getString("JUCHUBANGO");
				shohinbango = rs.getString("SHOHINBANGO");
				hasokosu = rs.getString("KOSU");
				hasouList.add(new String[] { shohinbango, hasokosu });

			}

			errmsg = hassou(hasouList, toiawasebango, haisokaisha, orderNo, shubetsu, haisohoho, shoribango);

			// for (int i = 0; i < hasouList.size(); i++) {
			// String[] shohin = hasouList.get(i);
			// String shouhinbango = shohin[0];
			// String kosu = shohin[1];
			//
			// int j = 1;
			// sql =
			// "INSERT INTO hassou_tbl
			// (SHORIBANGO,JUCHUBANGO,SHOUHINBANGO,HASSOUKOSU,TOIAWASEBANGO,SHUBETSU,UNSOKAISHA,HAISOHOHO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
			// ps = conn.prepareStatement(sql);
			// ps.setString(j++, shoribango);
			// ps.setString(j++, orderNo);
			// ps.setString(j++, shouhinbango);
			// ps.setString(j++, kosu);
			// ps.setString(j++, toiawasebango);
			// ps.setString(j++, shubetsu);
			// ps.setString(j++, haisokaisha);
			// ps.setString(j++, haisohoho);
			// ps.setString(j++, Utility.getDateTime());
			// ps.setString(j++, Utility.getUser());
			// ps.setString(j++, Utility.getDateTime());
			// ps.setString(j++, Utility.getUser());
			// ps.execute();
			// }
			//
			// String shoribango_soshin = "100000000";
			// sql = "SELECT MAX(SHORIBANGO) SHORIBANGO FROM soushin_tbl";
			// ps = conn.prepareStatement(sql);
			// rs = ps.executeQuery();
			// while (rs.next()) {
			// if (!Utility.isEmptyString(rs.getString("SHORIBANGO"))) {
			// shoribango_soshin = rs.getString("SHORIBANGO");
			// }
			// }
			//
			// int j = 1;
			// shoribango_soshin = String
			// .valueOf(Long.valueOf(shoribango_soshin) + 1);
			// sql =
			// "INSERT INTO soushin_tbl
			// (SHORIBANGO,JUCHUBANGO,SOUSHINTAIPU,SOUSHINZUMIFLG,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?)";
			// ps = conn.prepareStatement(sql);
			// ps.setString(j++, shoribango_soshin);
			// ps.setString(j++, orderNo);
			// ps.setString(j++, shubetsu);
			// ps.setString(j++, "0");
			// ps.setString(j++, Utility.getDateTime());
			// ps.setString(j++, Utility.getUser());
			// ps.setString(j++, Utility.getDateTime());
			// ps.setString(j++, Utility.getUser());
			// ps.execute();
			//
			if (Utility.isEmptyList(errmsg)) {
				sql = "UPDATE tuika_hasou_tbl SET STATUS = ? WHERE SHORIBANGO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, "1");
				ps.setString(2, shoribango);
				ps.execute();

				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public String[] getTuikahasomachi(String orderNo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String[] hasomachi = null;

		try {
			conn = JdbcConnection.getConnection();

			sql = "SELECT * FROM tuika_hasou_tbl WHERE JUCHUBANGO = ? AND STATUS = '0'";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			int count = 0;
			JSONObject jsonObj = new JSONObject();
			JSONArray shohinArray = new JSONArray();
			JSONObject shohin = null;
			while (rs.next()) {
				if (count == 0) {
					hasomachi = new String[6];
					hasomachi[0] = rs.getString("SHORIBANGO");
					hasomachi[1] = rs.getString("HASORIYU");
					hasomachi[2] = rs.getString("HASORIYUSONOTA");
					hasomachi[3] = rs.getString("SOURYOUFUTAN");
					hasomachi[4] = rs.getString("BIKO");

					count++;
				}

				shohin = new JSONObject();
				shohin.accumulate("shohinbango", rs.getString("SHOHINBANGO"));
				shohin.accumulate("kosu", rs.getString("KOSU"));
				shohinArray.add(shohin);
			}
			jsonObj.accumulate("shohin", shohinArray);
			if (jsonObj != null && hasomachi != null) {
				hasomachi[5] = jsonObj.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return hasomachi;
	}

	public List<String> addHenkin(String orderNo, String henkintype, String henkinkakaku, String biko)
			throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();

			sql = "INSERT INTO henkin_tbl(SHORIBANGO,JUCHUBANGO,HENKINKINGAKU,HENKINSHUBETSU,HENKINZUMIFLG,HENKINBI,BIKO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?,?);";
			ps = conn.prepareStatement(sql);
			int j = 1;
			ps.setString(j++, Utility.getShoribango());
			ps.setString(j++, orderNo);
			ps.setString(j++, henkinkakaku);
			ps.setString(j++, henkintype);
			ps.setString(j++, "0");
			ps.setString(j++, "");
			ps.setString(j++, biko);
			ps.setString(j++, Utility.getDateTime());
			ps.setString(j++, Utility.getUser());
			ps.setString(j++, Utility.getDateTime());
			ps.setString(j++, Utility.getUser());

			ps.execute();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> updateHenkin(String shoribango, String henkinkakaku, String biko) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();

			sql = "UPDATE henkin_tbl SET HENKINKINGAKU = ? , BIKO = ? ,UPDATE_TIME = ? , UPDATE_USER = ? WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			int j = 1;

			ps.setString(j++, henkinkakaku);
			ps.setString(j++, biko);
			ps.setString(j++, Utility.getDateTime());
			ps.setString(j++, Utility.getUser());
			ps.setString(j++, shoribango);
			ps.execute();

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> deleteHenkin(String shoribango) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();

			sql = "DELETE FROM henkin_tbl WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shoribango);
			ps.execute();

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> setHenkinzumi(String shoribango) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();

			sql = "UPDATE henkin_tbl SET HENKINZUMIFLG = '1' , HENKINBI = ?  WHERE SHORIBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, Utility.getDateTime());
			ps.setString(2, shoribango);
			ps.execute();

			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public String[] getOrderShiteibiInfo(String orderNo) throws Exception {
		String[] shiteibiInfo = null;

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		String otodokebishitei = null;
		String otodokejikantai1 = null;
		String otodokejikantai2 = null;
		String otodokejikantai3 = null;
		String biko = null;

		try {
			conn = JdbcConnection.getConnection();

			sql = "SELECT * FROM common_order_tbl WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();

			while (rs.next()) {
				otodokebishitei = rs.getString("OTODOKEBISHITEI");
				otodokejikantai1 = rs.getString("OTODOKEJIKANTAI1");
				otodokejikantai2 = rs.getString("OTODOKEJIKANTAI2");
				otodokejikantai3 = rs.getString("OTODOKEJIKANTAI3");
				biko = rs.getString("BIKO");
				shiteibiInfo = new String[] { otodokebishitei, otodokejikantai1, otodokejikantai2, otodokejikantai3,
						biko };
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return shiteibiInfo;
	}

	public List<String> updateOrder(String orderNo, String otodokebishitei, String otodokejikantai1,
			String otodokejikantai2, String otodokejikantai3, String biko, String yakusokubi, String hasoshahe)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();

			sql = "UPDATE common_order_tbl SET OTODOKEBISHITEI = ?, OTODOKEJIKANTAI1 = ?,OTODOKEJIKANTAI2 = ?,OTODOKEJIKANTAI3 = ?, BIKO = ? ,HASSOUSHAHENOKOMENTO = ? ,UPDATE_TIME = ? , UPDATE_USER = ? WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, otodokebishitei);
			ps.setString(2, otodokejikantai1);
			ps.setString(3, otodokejikantai2);
			ps.setString(4, otodokejikantai3);
			ps.setString(5, biko);
			ps.setString(6, hasoshahe);
			ps.setString(7, Utility.getDateTime());
			ps.setString(8, Utility.getUser());
			ps.setString(9, orderNo);

			ps.execute();

			int count = 0;
			sql = "SELECT COUNT(*) COUNT FROM TBL00027 WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("COUNT");
			}
			if (count > 0) {
				sql = "UPDATE TBL00027 SET HASOYAKUSOKUBI = ?,UPDATE_TIME = ? ,UPDATE_USER = ? WHERE CHUMONBANGO = ? ";
				ps = conn.prepareStatement(sql);
				ps.setString(1, yakusokubi);
				ps.setString(2, Utility.getDateTime());
				ps.setString(3, Utility.getUser());
				ps.setString(4, orderNo);
				ps.execute();
			} else {
				sql = "INSERT INTO TBL00027 VALUES(?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, orderNo);
				ps.setString(2, yakusokubi);
				ps.setString(3, Utility.getDateTime());
				ps.setString(4, Utility.getUser());
				ps.setString(5, Utility.getDateTime());
				ps.setString(6, Utility.getUser());
				ps.execute();
			}

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> dokonOrder(String orderNo, String tuikaOrderNo, String site, String tenpo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();

			int count = 0;

			if (orderNo.equals(tuikaOrderNo)) {
				errmsg.add("受注番号重複!");
				return errmsg;
			}

			sql = "SELECT COUNT(*) COUNT FROM common_order_tbl WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tuikaOrderNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("COUNT");
			}
			if (count == 0) {
				errmsg.add("該当受注番号は存在してません!");
				return errmsg;
			}

			sql = "SELECT * FROM common_order_tbl WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, tuikaOrderNo);
			rs = ps.executeQuery();
			String donkonId_tuika = null;
			String address_tuika = null;
			String pointriyo_tuika = null;
			String sonota_tuika = null;
			while (rs.next()) {
				donkonId_tuika = rs.getString("DONKONID");
				address_tuika = rs.getString("SOUFUSAKIYUBINBANGO1") + rs.getString("SOUFUSAKIYUBINBANGO2")
						+ rs.getString("SOUFUSAKIJUSHOTODOFUKEN") + rs.getString("SOUFUSAKIJUSHOTOSHIKU")
						+ rs.getString("SOUFUSAKIJUSHOCHOIJOU");
				pointriyo_tuika = Utility.isEmptyString(rs.getString("POINTRIYO")) ? "0" : rs.getString("POINTRIYO");

				sonota_tuika = Utility.isEmptyString(rs.getString("SONOTARIYOGAKU")) ? "0"
						: rs.getString("SONOTARIYOGAKU");
			}

			if (!"0".equals(donkonId_tuika) && !Utility.isEmptyString(donkonId_tuika)) {
				errmsg.add("同梱された注文は再同梱できません!");
				return errmsg;
			}

			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			String kesaihoho = null;
			String haisohoho = null;
			String pointriyo_moto = null;
			String sonota_moto = null;
			while (rs.next()) {
				kesaihoho = rs.getString("OSHIHARAISTS");
				haisohoho = rs.getString("HAISOUHOHO");
				pointriyo_moto = Utility.isEmptyString(rs.getString("POINTRIYO")) ? "0" : rs.getString("POINTRIYO");
				sonota_moto = Utility.isEmptyString(rs.getString("SONOTARIYOGAKU")) ? "0"
						: rs.getString("SONOTARIYOGAKU");
			}

			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			String address_moto = null;
			String donkonId_moto = null;
			while (rs.next()) {
				donkonId_moto = rs.getString("DONKONID");
				address_moto = rs.getString("SOUFUSAKIYUBINBANGO1") + rs.getString("SOUFUSAKIYUBINBANGO2")
						+ rs.getString("SOUFUSAKIJUSHOTODOFUKEN") + rs.getString("SOUFUSAKIJUSHOTOSHIKU")
						+ rs.getString("SOUFUSAKIJUSHOCHOIJOU");
			}

			if ("0".equals(donkonId_moto) || Utility.isEmptyString(donkonId_moto)) {
				donkonId_moto = Utility.getShoribango();
			}

			if (!address_moto.equals(address_tuika)) {
				errmsg.add("送付先は異なっています!");
				return errmsg;
			}

			sql = "UPDATE common_order_tbl SET DONKONID = ? ,DONKONOYAFLG = ?, UPDATE_TIME = ? , UPDATE_USER = ? WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, donkonId_moto);
			ps.setString(2, "1");
			ps.setString(3, Utility.getDateTime());
			ps.setString(4, Utility.getUser());
			ps.setString(5, orderNo);
			ps.execute();

			sql = "UPDATE common_order_tbl SET DONKONID = ? ,DONKONOYAFLG = ? ,CHUMONSTS1 = ? ,UPDATE_TIME = ? , UPDATE_USER = ? WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, donkonId_moto);
			ps.setString(2, "0");
			ps.setString(3, "7");
			ps.setString(4, Utility.getDateTime());
			ps.setString(5, Utility.getUser());
			ps.setString(6, tuikaOrderNo);
			ps.execute();

			sql = "UPDATE common_order_detail_tbl SET JUCHUBANGO = ?  WHERE JUCHUBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			ps.setString(2, tuikaOrderNo);
			ps.execute();

			sql = "SELECT * FROM common_order_detail_tbl WHERE JUCHUBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();

			String gokeishouhin = "0";
			String gokeizei = "0";
			String gokeisouryou = "0";
			String gokeidaibikiryou = "0";
			String seikyukingaku = "0";

			sql = "UPDATE common_order_tbl SET GOKEISHOHIN = ? ,GOKEIZEI = ? ,GOKEISOURYOU = ?, GOKEIDAIBIKIRYOU = ?, SEIKYUKINGAKU = ?,UPDATE_TIME = ? , UPDATE_USER = ?  WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, gokeishouhin);
			ps.setString(2, gokeizei);
			ps.setString(3, gokeisouryou);
			ps.setString(4, gokeidaibikiryou);
			ps.setString(5, seikyukingaku);
			ps.setString(6, Utility.getDateTime());
			ps.setString(7, Utility.getUser());
			ps.setString(8, tuikaOrderNo);
			ps.execute();
			boolean soryokomiAri = false;
			boolean daibikiryokomiAri = false;
			while (rs.next()) {
				gokeishouhin = String.valueOf(Long.valueOf(gokeishouhin) + rs.getLong("TANKA") * rs.getLong("KOSU"));

				String zeibetsu = rs.getString("ZEIKOMIBETSU");
				String soryobetsu = rs.getString("SOURYOUKOMIBETSU");
				if ("別".equals(zeibetsu)) {
					gokeizei = String.valueOf(Long.valueOf(gokeizei)
							+ Utility.getZei(String.valueOf(rs.getLong("TANKA") * rs.getLong("KOSU"))));
				}
				if ("込".equals(soryobetsu)) {
					soryokomiAri = true;
				}
				if ("代金引換".equals(kesaihoho) && "込".equals(rs.getString("DAIBIKITESURYOUKOMIBETSU"))) {
					daibikiryokomiAri = true;
				}
			}
			if (!soryokomiAri && Long.valueOf(gokeishouhin) < Utility.getSorokomiKaku()) {
				gokeisouryou = Utility.getSoryo(haisohoho, site, tenpo);
			}

			String pointriyo = String.valueOf(Long.valueOf(pointriyo_moto) + Long.valueOf(pointriyo_tuika));
			String sonota = String.valueOf(Long.valueOf(sonota_moto) + Long.valueOf(sonota_tuika));
			if (!daibikiryokomiAri && "代金引換".equals(kesaihoho)) {
				gokeidaibikiryou = Utility.getDaibikiryo(String.valueOf(Long.valueOf(gokeishouhin)
						+ Long.valueOf(gokeizei) + Long.valueOf(gokeisouryou) - Long.valueOf(pointriyo)));
			}
			seikyukingaku = String
					.valueOf(Long.valueOf(gokeishouhin) + Long.valueOf(gokeizei) + Long.valueOf(gokeisouryou)
							+ Long.valueOf(gokeidaibikiryou) - Long.valueOf(pointriyo) - Long.valueOf(sonota));

			sql = "UPDATE common_order_tbl SET GOKEISHOHIN = ? ,GOKEIZEI = ? ,GOKEISOURYOU = ?, GOKEIDAIBIKIRYOU = ?, SEIKYUKINGAKU = ?,POINTRIYO = ?,SONOTARIYOGAKU = ?,UPDATE_TIME = ? , UPDATE_USER = ?  WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, gokeishouhin);
			ps.setString(2, gokeizei);
			ps.setString(3, gokeisouryou);
			ps.setString(4, gokeidaibikiryou);
			ps.setString(5, seikyukingaku);
			ps.setString(6, pointriyo);
			ps.setString(7, sonota);
			ps.setString(8, Utility.getDateTime());
			ps.setString(9, Utility.getUser());
			ps.setString(10, orderNo);
			ps.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> updateCommonOrderTbl(F100103 f100103, String orderNo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			String gokeishouhin = "0";
			String gokeizei = "0";
			String gokeisouryou = "0";
			String gokeidaibikiryou = "0";
			String seikyukingaku = "0";

			List<ShohinList> shohinList = f100103.getShohinList();
			boolean soryokomiAri = false;
			boolean daibikiryokomiAri = false;

			for (ShohinList shoin : shohinList) {
				gokeishouhin = String.valueOf(
						Long.valueOf(gokeishouhin) + Long.valueOf(shoin.getTankaku()) * Long.valueOf(shoin.getKosu()));

				shoin.setShoukei(String.valueOf(Long.valueOf(shoin.getTankaku()) * Long.valueOf(shoin.getKosu())));

				String zeibetsu = shoin.getZei();
				String soryobetsu = shoin.getSourou();
				if ("別".equals(zeibetsu)) {
					gokeizei = String.valueOf(Long.valueOf(gokeizei) + Utility.getZei(shoin.getShoukei()));
				}
				if ("込".equals(soryobetsu)) {
					soryokomiAri = true;
				}
				if ("代金引換".equals(f100103.getOshiharaihoho()) && "込".equals(shoin.getDaibiki())) {
					daibikiryokomiAri = true;
				}
			}
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM common_order_tbl T1 LEFT JOIN common_order_detail_tbl T2 ON T1.CHUMONBANGO = T2.JUCHUBANGO WHERE T1.CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			ResultSet rs = ps.executeQuery();
			String site = "";
			String tenpo = "";
			String pointbairitu = "";
			while (rs.next()) {
				site = rs.getString("T1.SITE");
				tenpo = rs.getString("T1.SHOP");
				pointbairitu = rs.getString("T2.POINTOBAIRITSU");
			}
			if (!soryokomiAri && (Long.valueOf(gokeishouhin) + Long.valueOf(gokeizei)) < Utility.getSorokomiKaku()) {
				gokeisouryou = Utility.getSoryo(f100103.getHaisohoho(), site, tenpo);
			}
			if (!daibikiryokomiAri && "代金引換".equals(f100103.getOshiharaihoho())) {
				gokeidaibikiryou = Utility.getDaibikiryo(String.valueOf(Long.valueOf(gokeishouhin)
						+ Long.valueOf(gokeizei) + Long.valueOf(gokeisouryou) - Long.valueOf(f100103.getPointriyo())));
			}

			seikyukingaku = String.valueOf(Long.valueOf(gokeishouhin) + Long.valueOf(gokeizei)
					+ Long.valueOf(gokeisouryou) + Long.valueOf(gokeidaibikiryou) - Long.valueOf(f100103.getPointriyo())
					- Long.valueOf(f100103.getSonotariyogaku()));

			f100103.setGokeishouhin(gokeishouhin);
			f100103.setGokeizei(gokeizei);
			f100103.setGokeisouryou(gokeisouryou);
			f100103.setGokeidaibikiryou(gokeidaibikiryou);
			f100103.setSeikyukingaku(seikyukingaku);

			int j = 1;
			sql = "UPDATE common_order_tbl SET POINTRIYO=?,SONOTARIYOGAKU=?, OSHIHARAISTS = ?,CHUMONSHAMEIJI = ?,CHUMONSHANAMAE = ?,CHUMONSHAMEIJIFURIGANA = ?,CHUMONSHANAMAEFURIGANA = ?,MERUADORESU = ?,CHUMONSHAYUBINBANGO1 = ?,CHUMONSHAYUBINBANGO2 = ?,CHUMONSHAJUSHOTODOFUKEN = ?,CHUMONSHAJUSHOTOSHIKU = ?,CHUMONSHAJUSHOCHOIJOU = ?,CHUMONSHADENWABANGO1 = ?,CHUMONSHADENWABANGO2 = ?,CHUMONSHADENWABANGO3 =?,SOFUSAKIMEIJI = ?,SOUFUSAKINAMAE = ?,SOUFUSAKIMEIJIFURIGANA = ?,SOUFUSAKIMEIJINAMAEFURIGANA = ?,HAISOUHOHO = ?,SOUFUSAKIYUBINBANGO1 = ?,SOUFUSAKIYUBINBANGO2 = ?,SOFUSAKIDENWABANGO1 = ?,SOFUSAKIDENWABANGO2 = ?,SOFUSAKIDENWABANGO3 = ?,SOUFUSAKIJUSHOTODOFUKEN = ?,SOUFUSAKIJUSHOTOSHIKU = ?,SOUFUSAKIJUSHOCHOIJOU = ?,GOKEISHOHIN = ?,GOKEIZEI = ?,GOKEISOURYOU =?,GOKEIDAIBIKIRYOU = ?,SEIKYUKINGAKU = ?,UPDATE_TIME = ?,UPDATE_USER = ?WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(j++, f100103.getPointriyo());
			ps.setString(j++, f100103.getSonotariyogaku());
			ps.setString(j++, f100103.getOshiharaihoho());
			ps.setString(j++, f100103.getChumonshameiji());
			ps.setString(j++, f100103.getChumonshanamae());
			ps.setString(j++, f100103.getChumonshameijifurigana());
			ps.setString(j++, f100103.getChumonshanamaefurigana());
			ps.setString(j++, f100103.getMeruadoresu());
			ps.setString(j++, f100103.getChumonshayubinbango1());
			ps.setString(j++, f100103.getChumonshayubinbango2());
			ps.setString(j++, f100103.getChumonshajushotodofuken());
			ps.setString(j++, f100103.getChumonshajushotoshiku());
			ps.setString(j++, f100103.getChumonshajushochojijo());
			ps.setString(j++, f100103.getChumonshadenwabango1());
			ps.setString(j++, f100103.getChumonshadenwabango2());
			ps.setString(j++, f100103.getChumonshadenwabango3());
			ps.setString(j++, f100103.getSofusakimeiji());
			ps.setString(j++, f100103.getSofusakinamae());
			ps.setString(j++, f100103.getSofusakimeijifurigana());
			ps.setString(j++, f100103.getSofusakinamaefurigana());
			ps.setString(j++, f100103.getHaisohoho());
			ps.setString(j++, f100103.getSofusakiyubinbango1());
			ps.setString(j++, f100103.getSofusakiyubinbango2());
			ps.setString(j++, f100103.getSofusakidenwabango1());
			ps.setString(j++, f100103.getSofusakidenwabango2());
			ps.setString(j++, f100103.getSofusakidenwabango3());
			ps.setString(j++, f100103.getSofusakijushotodofuken());
			ps.setString(j++, f100103.getSofusakijushotoshiku());
			ps.setString(j++, f100103.getSofusakijushochoijo());
			ps.setString(j++, f100103.getGokeishouhin());
			ps.setString(j++, f100103.getGokeizei());
			ps.setString(j++, f100103.getGokeisouryou());
			ps.setString(j++, f100103.getGokeidaibikiryou());
			ps.setString(j++, f100103.getSeikyukingaku());
			ps.setString(j++, Utility.getDateTime());
			ps.setString(j++, Utility.getUser());
			ps.setString(j++, orderNo);

			int updateCount = ps.executeUpdate();

			sql = "DELETE FROM common_order_detail_tbl WHERE JUCHUBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			int deleteCount = ps.executeUpdate();

			int insertCount = 0;
			sql = "INSERT INTO common_order_detail_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
			for (ShohinList shohin : f100103.getShohinList()) {
				ps = conn.prepareStatement(sql);
				j = 0;
				ps.setString(++j, Utility.getShoribango());

				ps.setString(++j, orderNo);
				// 商品名
				ps.setString(++j, shohin.getShouhinmei().replace(shohin.getShohinbango(), "").replace("()", ""));
				// 商品番号
				ps.setString(++j, shohin.getShohinbango());
				// 商品URL
				ps.setString(++j, Utility.getShohinUrl(shohin.getShohinbango(), site, tenpo));
				// 単価
				ps.setString(++j, shohin.getTankaku());
				// 個数
				ps.setString(++j, shohin.getKosu());
				// 送料込別
				ps.setString(++j, shohin.getSourou());
				// 税込別
				ps.setString(++j, shohin.getZei());
				// 代引手数料込別
				ps.setString(++j, shohin.getDaibiki());
				// 項目選択肢
				ps.setString(++j, shohin.getKomokusentakushi());
				// ponint bairitu
				ps.setString(++j, pointbairitu);
				// nouki
				ps.setString(++j, shohin.getNouki());

				insertCount += ps.executeUpdate();
			}
			if (updateCount > 0 && deleteCount > 0 && insertCount > 0) {
				conn.commit();
			} else {
				throw new Exception("システムエラー");
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
		} finally {
			conn.close();
		}
		return errmsg;
	}

	public List<String> getHasomachiList(List<CommonOrderBean> commonOrderBeanList) {
		List<String> orderNoList = new ArrayList<String>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (!commonOrderBean.isBunnouFlg() && !commonOrderBean.isHenpinFlg() && !commonOrderBean.isKoukanFlg()
					&& !commonOrderBean.isTuikaFlg()) {
				orderNoList.add(commonOrderBean.getJuchubango());
			}
		}
		return orderNoList;
	}

	public List<String> getTuikaHasomachiList(List<CommonOrderBean> commonOrderBeanList) {
		List<String> orderNoList = new ArrayList<String>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (commonOrderBean.isTuikaFlg()) {
				List<CommonOrderDetailrBean> detailList = commonOrderBean.getCommonOrderDetailBeanList();
				for (CommonOrderDetailrBean detail : detailList) {
					if ("2".equals(detail.getShubetsu())) {
						orderNoList.add(commonOrderBean.getJuchubango());
						break;
					}
				}
			}
		}
		return orderNoList;
	}

	public List<String> getBunnoHasomachiList(List<CommonOrderBean> commonOrderBeanList) {
		List<String> orderNoList = new ArrayList<String>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (commonOrderBean.isBunnouFlg()) {
				List<CommonOrderDetailrBean> detailList = commonOrderBean.getCommonOrderDetailBeanList();
				for (CommonOrderDetailrBean detail : detailList) {
					if ("1".equals(detail.getShubetsu())) {
						orderNoList.add(commonOrderBean.getJuchubango());
						break;
					}
				}
			}
		}
		return orderNoList;
	}

	public List<String> getHasomachishikyuList(List<CommonOrderBean> commonOrderBeanList) {
		List<String> orderNoList = new ArrayList<String>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (!commonOrderBean.isBunnouFlg() && !commonOrderBean.isHenpinFlg() && !commonOrderBean.isKoukanFlg()
					&& !commonOrderBean.isTuikaFlg() && commonOrderBean.isShikyuFlg()) {
				orderNoList.add(commonOrderBean.getJuchubango());
			}
		}
		return orderNoList;
	}

	public List<String> getTuikaHasomachishikyuList(List<CommonOrderBean> commonOrderBeanList) {
		List<String> orderNoList = new ArrayList<String>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (commonOrderBean.isTuikaFlg() && commonOrderBean.isShikyuFlg()) {
				List<CommonOrderDetailrBean> detailList = commonOrderBean.getCommonOrderDetailBeanList();
				for (CommonOrderDetailrBean detail : detailList) {
					if ("2".equals(detail.getShubetsu())) {
						orderNoList.add(commonOrderBean.getJuchubango());
						break;
					}
				}
			}
		}
		return orderNoList;
	}

	public List<String> getBunnoHasomachishikyuList(List<CommonOrderBean> commonOrderBeanList) {
		List<String> orderNoList = new ArrayList<String>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (commonOrderBean.isBunnouFlg() && commonOrderBean.isShikyuFlg()) {
				List<CommonOrderDetailrBean> detailList = commonOrderBean.getCommonOrderDetailBeanList();
				for (CommonOrderDetailrBean detail : detailList) {
					if ("1".equals(detail.getShubetsu())) {
						orderNoList.add(commonOrderBean.getJuchubango());
						break;
					}
				}
			}
		}
		return orderNoList;
	}

	public List<String> getHenpinmachiList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> orderNoList = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT DISTINCT JUCHUBANGO FROM henpin_tbl WHERE HENPINSTATUS = '0'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				orderNoList.add(rs.getString("JUCHUBANGO"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return orderNoList;
	}

	public List<String> getHenpinchuList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> orderNoList = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT DISTINCT JUCHUBANGO FROM henpin_tbl WHERE HENPINSTATUS = '1'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			String juchubango = null;

			while (rs.next()) {
				juchubango = rs.getString("JUCHUBANGO");
				boolean ariFlg = false;
				for (int i = 0; i < orderNoList.size(); i++) {
					if (orderNoList.get(i).equals(juchubango)) {
						ariFlg = true;
						break;
					}
				}
				if (!ariFlg) {
					orderNoList.add(juchubango);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return orderNoList;
	}

	public List<String> getHenkinmachiList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> orderNoList = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM henkin_tbl WHERE HENKINZUMIFLG = '0'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			String juchubango = null;

			while (rs.next()) {
				juchubango = rs.getString("JUCHUBANGO");
				boolean ariFlg = false;
				for (int i = 0; i < orderNoList.size(); i++) {
					if (orderNoList.get(i).equals(juchubango)) {
						ariFlg = true;
						break;
					}
				}
				if (!ariFlg) {
					orderNoList.add(juchubango);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return orderNoList;
	}

	public List<String> getSoshinmachiList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> orderNoList = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT DISTINCT JUCHUBANGO FROM soushin_tbl WHERE SOUSHINZUMIFLG = '0'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String juchubango = rs.getString("JUCHUBANGO");
				orderNoList.add(juchubango);
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return orderNoList;
	}

	public List<String> getTodokemachiList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> orderNoList = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM common_order_tbl WHERE CHUMONSTS1 = '2'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (!Utility.isEmptyString(rs.getString("KOMENTO"))) {
					if (!Utility.isEmptyString(
							rs.getString("KOMENTO").trim().replace("[配送日時指定:]", "").replace("[備考欄:]", ""))
							&& (Utility.isEmptyString(rs.getString("OTODOKEBISHITEI"))
									&& Utility.isEmptyString(rs.getString("OTODOKEJIKANTAI1")))) {
						String juchubango = rs.getString("CHUMONBANGO");
						orderNoList.add(juchubango);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return orderNoList;
	}

	public List<String> getKakuninmachiList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> orderNoList = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM common_order_tbl WHERE CHUMONSTS1 = '1'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String juchubango = rs.getString("CHUMONBANGO");
				orderNoList.add(juchubango);
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return orderNoList;
	}

	public List<String> getHasomachiHasokaList(List<CommonOrderBean> commonOrderBeanList,
			List<ShouhinStsBean> shouhinStsBeanList) {
		List<String> orderNoList = new ArrayList<String>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (!commonOrderBean.isBunnouFlg() && !commonOrderBean.isHenpinFlg() && !commonOrderBean.isKoukanFlg()
					&& !commonOrderBean.isTuikaFlg()) {
				String juchubango = commonOrderBean.getJuchubango();
				boolean hasokanoFlg = true;
				for (CommonOrderDetailrBean detail : commonOrderBean.getCommonOrderDetailBeanList()) {
					if ("0".equals(detail.getShubetsu())) {
						// 已经取得的商品编号
						String shohinbango = detail.getShohinbango();
						// 从商品stsbean里面搜索状态
						for (ShouhinStsBean shohin : shouhinStsBeanList) {
							if (shohin.getShouhinbango().equals(shohinbango)) {
								for (ShohinStsInfoBean order : shohin.getShohinStsInfoBeanList()) {
									if (order.getJuchubango().equals(juchubango)) {
										if (!order.getHoryukosuJp().equals(String.valueOf(detail.getKosu()))) {
											hasokanoFlg = false;
										}
									}
								}
							}

						}
					}
				}
				if (hasokanoFlg) {
					orderNoList.add(juchubango);
				}
			}
		}
		return orderNoList;
	}

	public List<String> getHasomachiHasokashikyuList(List<CommonOrderBean> commonOrderBeanList,
			List<ShouhinStsBean> shouhinStsBeanList) {
		List<String> orderNoList = new ArrayList<String>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (!commonOrderBean.isBunnouFlg() && !commonOrderBean.isHenpinFlg() && !commonOrderBean.isKoukanFlg()
					&& !commonOrderBean.isTuikaFlg() && commonOrderBean.isShikyuFlg()) {
				String juchubango = commonOrderBean.getJuchubango();
				boolean hasokanoFlg = true;
				for (CommonOrderDetailrBean detail : commonOrderBean.getCommonOrderDetailBeanList()) {
					if ("0".equals(detail.getShubetsu())) {
						// 已经取得的商品编号
						String shohinbango = detail.getShohinbango();
						// 从商品stsbean里面搜索状态
						for (ShouhinStsBean shohin : shouhinStsBeanList) {
							if (shohin.getShouhinbango().equals(shohinbango)) {
								for (ShohinStsInfoBean order : shohin.getShohinStsInfoBeanList()) {
									if (order.getJuchubango().equals(juchubango)) {
										if (!order.getHoryukosuJp().equals(String.valueOf(detail.getKosu()))) {
											hasokanoFlg = false;
										}
									}
								}
							}

						}
					}
				}
				if (hasokanoFlg) {
					orderNoList.add(juchubango);
				}
			}
		}
		return orderNoList;
	}

	public List<String> getTuikahasomachiHasokashikyuList(List<CommonOrderBean> commonOrderBeanList,
			List<ShouhinStsBean> shouhinStsBeanList) {
		List<String> orderNoList = new ArrayList<String>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (commonOrderBean.isTuikaFlg() && commonOrderBean.isShikyuFlg()) {
				String juchubango = commonOrderBean.getJuchubango();
				boolean hasokanoFlg = true;
				for (CommonOrderDetailrBean detail : commonOrderBean.getCommonOrderDetailBeanList()) {
					if ("2".equals(detail.getShubetsu())) {
						// 已经取得的商品编号
						String shohinbango = detail.getShohinbango();
						// 从商品stsbean里面搜索状态
						for (ShouhinStsBean shohin : shouhinStsBeanList) {
							if (shohin.getShouhinbango().equals(shohinbango)) {
								for (ShohinStsInfoBean order : shohin.getShohinStsInfoBeanList()) {
									if (order.getJuchubango().equals(juchubango)) {
										if (!order.getHoryukosuJp().equals(String.valueOf(detail.getKosu()))) {
											hasokanoFlg = false;
										}
									}
								}
							}

						}
					}
				}
				if (hasokanoFlg) {
					orderNoList.add(juchubango);
				}
			}
		}
		return orderNoList;
	}

	public List<String> getTuikahasomachiHasokaList(List<CommonOrderBean> commonOrderBeanList,
			List<ShouhinStsBean> shouhinStsBeanList) {
		List<String> orderNoList = new ArrayList<String>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (commonOrderBean.isTuikaFlg()) {
				String juchubango = commonOrderBean.getJuchubango();
				if (!Utility.isEmptyList(commonOrderBean.getCommonOrderDetailBeanList())) {
					boolean hasokanoFlg = true;
					for (CommonOrderDetailrBean detail : commonOrderBean.getCommonOrderDetailBeanList()) {
						if ("2".equals(detail.getShubetsu())) {
							// 已经取得的商品编号
							String shohinbango = detail.getShohinbango();
							// 从商品stsbean里面搜索状态
							for (ShouhinStsBean shohin : shouhinStsBeanList) {
								if (shohin.getShouhinbango().equals(shohinbango)) {
									for (ShohinStsInfoBean order : shohin.getShohinStsInfoBeanList()) {
										if (order.getJuchubango().equals(juchubango)) {
											if (!order.getHoryukosuJp().equals(String.valueOf(detail.getKosu()))) {
												hasokanoFlg = false;
											}
										}
									}
								}

							}
						}
					}
					if (hasokanoFlg) {
						orderNoList.add(juchubango);
					}
				}
			}
		}
		return orderNoList;
	}

	public List<String> getBunnohasomachiHasokaList(List<CommonOrderBean> commonOrderBeanList,
			List<ShouhinStsBean> shouhinStsBeanList) {
		List<String> orderNoList = new ArrayList<String>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (commonOrderBean.isBunnouFlg()) {
				String juchubango = commonOrderBean.getJuchubango();
				boolean hasokanoFlg = false;
				for (CommonOrderDetailrBean detail : commonOrderBean.getCommonOrderDetailBeanList()) {
					if ("1".equals(detail.getShubetsu())) {
						// 已经取得的商品编号
						String shohinbango = detail.getShohinbango();
						// 从商品stsbean里面搜索状态
						for (ShouhinStsBean shohin : shouhinStsBeanList) {
							if (shohin.getShouhinbango().equals(shohinbango)) {
								for (ShohinStsInfoBean order : shohin.getShohinStsInfoBeanList()) {
									if (order.getJuchubango().equals(juchubango)) {
										if (!"0".equals(order.getHoryukosuJp())) {
											hasokanoFlg = true;
											break;
										}
									}
								}
							}
						}
					}
				}
				if (hasokanoFlg) {
					orderNoList.add(juchubango);
				}
			}
		}
		return orderNoList;
	}

	public List<String> getBunnohasomachishikyuHasokaList(List<CommonOrderBean> commonOrderBeanList,
			List<ShouhinStsBean> shouhinStsBeanList) {
		List<String> orderNoList = new ArrayList<String>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			if (commonOrderBean.isBunnouFlg() && commonOrderBean.isShikyuFlg()) {
				String juchubango = commonOrderBean.getJuchubango();
				boolean hasokanoFlg = false;
				for (CommonOrderDetailrBean detail : commonOrderBean.getCommonOrderDetailBeanList()) {
					if ("1".equals(detail.getShubetsu())) {
						// 已经取得的商品编号
						String shohinbango = detail.getShohinbango();
						// 从商品stsbean里面搜索状态
						for (ShouhinStsBean shohin : shouhinStsBeanList) {
							if (shohin.getShouhinbango().equals(shohinbango)) {
								for (ShohinStsInfoBean order : shohin.getShohinStsInfoBeanList()) {
									if (order.getJuchubango().equals(juchubango)) {
										if (!"0".equals(order.getHoryukosuJp())) {
											hasokanoFlg = true;
											break;
										}
									}
								}
							}
						}
					}
				}
				if (hasokanoFlg) {
					orderNoList.add(juchubango);
				}
			}
		}
		return orderNoList;
	}

	public List<String> insertCommonOrderTbl(F100104 f100104, String orderNo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<String> errmsg = new ArrayList<String>();
		try {
			int j = 0;
			conn = JdbcConnection.getConnection();
			sql = "INSERT INTO common_order_tbl VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(++j, Utility.strTrim(f100104.getJuchubango()));
			ps.setString(++j, Utility.getDateTime());
			ps.setString(++j, "0");// 未入金
			ps.setString(++j, "2");
			ps.setString(++j, "0");
			ps.setString(++j, "0");
			ps.setString(++j, "0");
			ps.setString(++j, "0");
			ps.setString(++j, "0");
			ps.setString(++j, f100104.getSite());
			ps.setString(++j, f100104.getTenpo());
			ps.setString(++j, "");
			ps.setString(++j, f100104.getOshiharaihoho());
			ps.setString(++j, "");
			ps.setString(++j, "");
			ps.setString(++j, "");
			ps.setString(++j, "");
			ps.setString(++j, "");
			ps.setString(++j, f100104.getPointriyo());

			// あす楽希望
			ps.setString(++j, "0");
			// 注文者名字
			ps.setString(++j, Utility.strTrim(f100104.getChumonshameiji()));
			// 注文者名前
			ps.setString(++j, Utility.strTrim(f100104.getChumonshanamae()));
			// 注文者名字フリガナ
			ps.setString(++j, Utility.strTrim(f100104.getChumonshameijifurigana()));
			// 注文者名前フリガナ
			ps.setString(++j, Utility.strTrim(f100104.getChumonshanamaefurigana()));
			// メールアドレス
			ps.setString(++j, Utility.strTrim(f100104.getMeruadoresu()));
			// 注文者誕生日
			ps.setString(++j, "");
			// 注文者郵便番号１
			ps.setString(++j, Utility.strTrim(f100104.getChumonshayubinbango1()));
			// 注文者郵便番号２
			ps.setString(++j, Utility.strTrim(f100104.getChumonshayubinbango2()));
			// 注文者住所：都道府県
			ps.setString(++j, Utility.strTrim(f100104.getChumonshajushotodofuken()));
			// 注文者住所：都市区
			ps.setString(++j, Utility.strTrim(f100104.getChumonshajushotoshiku()));
			// 注文者住所：町以降
			ps.setString(++j, Utility.strTrim(f100104.getChumonshajushochojijo()));
			// 注文者電話番号１
			ps.setString(++j, Utility.strTrim(f100104.getChumonshadenwabango1()));
			// 注文者電話番号２
			ps.setString(++j, Utility.strTrim(f100104.getChumonshadenwabango2()));
			// 注文者電話番号３
			ps.setString(++j, Utility.strTrim(f100104.getChumonshadenwabango3()));
			// コメント
			ps.setString(++j, "");
			// メール差込文(お客様へのメッセージ)
			ps.setString(++j, "");

			// 送付先名字
			ps.setString(++j, Utility.strTrim(f100104.getSofusakimeiji()));
			// 送付先名前
			ps.setString(++j, Utility.strTrim(f100104.getSofusakinamae()));
			// 送付先名字フリガナ
			ps.setString(++j, Utility.strTrim(f100104.getSofusakimeijifurigana()));
			// 送付先名前フリガナ
			ps.setString(++j, Utility.strTrim(f100104.getSofusakinamaefurigana()));
			// 配送方法
			ps.setString(++j, Utility.strTrim(f100104.getHaisohoho()));
			// 送付先郵便番号１
			ps.setString(++j, Utility.strTrim(f100104.getSofusakiyubinbango1()));
			// 送付先郵便番号２
			ps.setString(++j, Utility.strTrim(f100104.getSofusakiyubinbango2()));
			ps.setString(++j, Utility.strTrim(f100104.getSofusakidenwabango1()));
			ps.setString(++j, Utility.strTrim(f100104.getSofusakidenwabango2()));
			ps.setString(++j, Utility.strTrim(f100104.getSofusakidenwabango3()));
			// 送付先住所：都道府県
			ps.setString(++j, Utility.strTrim(f100104.getSofusakijushotodofuken()));
			// 送付先住所：都市区
			ps.setString(++j, Utility.strTrim(f100104.getSofusakijushotoshiku()));
			// 送付先住所：町以降
			ps.setString(++j, Utility.strTrim(f100104.getSofusakijushochoijo()));
			ps.setString(++j, Utility.strTrim(f100104.getGokeishouhin()));
			ps.setString(++j, Utility.strTrim(f100104.getGokeizei()));
			ps.setString(++j, Utility.strTrim(f100104.getGokeisouryou()));
			ps.setString(++j, Utility.strTrim(f100104.getGokeidaibikiryou()));
			ps.setString(++j, Utility.strTrim(f100104.getSeikyukingaku()));
			// 同梱ID
			ps.setString(++j, "0");
			// 同梱親FLG
			ps.setString(++j, "0");

			ps.setString(++j, "0");
			ps.setString(++j, Utility.getDateTime());
			ps.setString(++j, Utility.getUser());
			ps.setString(++j, Utility.getDateTime());
			ps.setString(++j, Utility.getUser());
			ps.setString(++j, "");
			ps.setString(++j, Utility.strTrim(f100104.getSonotariyogaku()));
			ps.executeUpdate();

			for (int k = 0; k < f100104.getShohinList().size(); k++) {
				ShohinList shousai = f100104.getShohinList().get(k);
				j = 0;
				sql = "INSERT INTO common_order_detail_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
				ps = conn.prepareStatement(sql);

				ps.setString(++j, Utility.getShoribango());

				ps.setString(++j, Utility.strTrim(f100104.getJuchubango()));
				// 商品名
				ps.setString(++j, Utility.strTrim(shousai.getShouhinmei()));
				// 商品番号
				ps.setString(++j, Utility.strTrim(shousai.getShohinbango()));
				// 商品URL
				ps.setString(++j, "");
				// 単価
				ps.setString(++j, Utility.strTrim(shousai.getTankaku()));
				// 個数
				ps.setString(++j, Utility.strTrim(shousai.getKosu()));
				// 送料込別
				ps.setString(++j, Utility.strTrim(shousai.getSourou()));
				// 税込別
				ps.setString(++j, Utility.strTrim(shousai.getZei()));
				// 代引手数料込別
				ps.setString(++j, Utility.strTrim(shousai.getDaibiki()));
				// 項目選択肢
				ps.setString(++j, Utility.strTrim(shousai.getKomokusentakushi()));
				// ponint bairitu
				ps.setString(++j, Utility.strTrim(shousai.getPointobairitsu()));
				// nouki
				ps.setString(++j, Utility.strTrim(shousai.getNouki()));

				ps.executeUpdate();
			}
			// commit
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			errmsg.add("システムエラー");
			throw e;
		} finally {
			conn.close();
		}

		return errmsg;
	}

	public List<String> getNyukafukarenrakumachiList(List<CommonOrderBean> commonOrderBeanList,
			List<String> nyukafukaList) throws Exception {
		Connection conn = null;
		List<String> orderNoList = new ArrayList<String>();
		try {
			String shouhinbango = "";

			conn = JdbcConnection.getConnection();
			for (CommonOrderBean order : commonOrderBeanList) {
				List<CommonOrderDetailrBean> commonOrderDetailBeanList = order.getCommonOrderDetailBeanList();
				if (!Utility.isEmptyList(commonOrderDetailBeanList)) {
					for (CommonOrderDetailrBean detail : commonOrderDetailBeanList) {
						shouhinbango = detail.getShohinbango();
						for (String fukabango : nyukafukaList) {
							if (fukabango.equals(shouhinbango)) {
								if (!isRenrakuzumi(order.getJuchubango(), shouhinbango, conn)) {
									orderNoList.add(order.getJuchubango());
									break;
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return orderNoList;

	}

	public List<CommonOrderBean> getNyukamachiList(List<CommonOrderBean> commonOrderBeanList,
			List<ShouhinStsBean> shouhinStsBeanList) {
		List<CommonOrderBean> orderNoList = new ArrayList<CommonOrderBean>();
		for (CommonOrderBean commonOrderBean : commonOrderBeanList) {
			boolean nyukamachiFlg = false;
			String juchubango = commonOrderBean.getJuchubango();
			if (!Utility.isEmptyList(commonOrderBean.getCommonOrderDetailBeanList())) {

				for (CommonOrderDetailrBean detail : commonOrderBean.getCommonOrderDetailBeanList()) {

					// 已经取得的商品编号
					String shohinbango = detail.getShohinbango();
					// 从商品stsbean里面搜索状态
					for (ShouhinStsBean shohin : shouhinStsBeanList) {
						if (shohin.getShouhinbango().equals(shohinbango)) {
							for (ShohinStsInfoBean order : shohin.getShohinStsInfoBeanList()) {
								if (order.getJuchubango().equals(juchubango)) {
									if ("0".equals(order.getHoryukosuJp()) && "0".equals(order.getHoryukosuSh())
											&& "0".equals(order.getHoryukosuNyuka())
											&& "0".equals(order.getHoryukosuUnso())) {
										nyukamachiFlg = true;
										break;
									}
								}
							}
						}

					}

				}
			}
			if (nyukamachiFlg) {
				orderNoList.add(commonOrderBean);
			}
		}
		return orderNoList;

	}

	public List<String> getNyukafukarenList() throws Exception {
		Connection conn = null;
		List<String> shohinbangoList = new ArrayList<String>();

		try {
			PreparedStatement ps = null;
			String sql = null;
			ResultSet rs = null;
			conn = JdbcConnection.getConnection();
			sql = "SELECT concat(commodity_id,detail_no) juchubango FROM tbl00012 WHERE del_flg = '1'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinbangoList.add(rs.getString("juchubango"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return shohinbangoList;
	}

	public boolean isNyukakano(String shouhinbango) throws Exception {
		Connection conn = null;
		boolean result = true;
		try {
			PreparedStatement ps = null;
			String sql = null;
			ResultSet rs = null;
			conn = JdbcConnection.getConnection();
			sql = "SELECT DEL_FLG juchubango FROM tbl00012 WHERE concat(commodity_id,detail_no) = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shouhinbango);
			rs = ps.executeQuery();
			while (rs.next()) {
				if ("1".equals(rs.getString("juchubango"))) {
					result = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return result;
	}

	private boolean isRenrakuzumi(String chumonbango, String shouhinbango, Connection conn) throws Exception {
		boolean result = false;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		sql = "SELECT count(*) COUNT FROM tbl00026 WHERE CHUMONBANGO = ? and SHOUHINBANGO = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, chumonbango);
		ps.setString(2, shouhinbango);
		rs = ps.executeQuery();
		while (rs.next()) {
			Long count = rs.getLong("COUNT");
			if (count > 0) {
				result = true;
			}
		}

		return result;
	}

	public List<String> getNyukafukarenrakuzumiList(List<CommonOrderBean> commonOrderBeanList,
			List<String> nyukafukaList) throws Exception {
		Connection conn = null;
		List<String> orderNoList = new ArrayList<String>();
		try {
			String shouhinbango = "";

			conn = JdbcConnection.getConnection();
			for (CommonOrderBean order : commonOrderBeanList) {
				boolean ariFlg = true;
				boolean fukaari = false;
				List<CommonOrderDetailrBean> commonOrderDetailBeanList = order.getCommonOrderDetailBeanList();
				if (!Utility.isEmptyList(commonOrderDetailBeanList)) {
					for (CommonOrderDetailrBean detail : commonOrderDetailBeanList) {
						shouhinbango = detail.getShohinbango();
						for (String fukabango : nyukafukaList) {
							if (fukabango.equals(shouhinbango)) {
								fukaari = true;
								if (!isRenrakuzumi(order.getJuchubango(), shouhinbango, conn)) {
									ariFlg = false;
									break;
								}
							}
						}
					}
					if (fukaari && ariFlg) {
						orderNoList.add(order.getJuchubango());
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		return orderNoList;
	}

	public String getHasoyakusokubi(String juchubango) throws Exception {
		String yakusokubi = "";
		Connection conn = null;
		try {
			PreparedStatement ps = null;
			String sql = null;
			ResultSet rs = null;
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM tbl00027 WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, juchubango);
			rs = ps.executeQuery();
			while (rs.next()) {
				yakusokubi = rs.getString("HASOYAKUSOKUBI");
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

		return yakusokubi;
	}

	public String getHasoyakusokubiFast(String juchubango, Connection conn) throws Exception {
		String yakusokubi = "";

		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		sql = "SELECT * FROM tbl00027 WHERE CHUMONBANGO = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, juchubango);
		rs = ps.executeQuery();
		while (rs.next()) {
			yakusokubi = rs.getString("HASOYAKUSOKUBI");
		}

		return yakusokubi;
	}

	public String[] getOrderKomentoAndBiko(String juchubango, Connection conn) throws Exception {
		String komento = "";
		String biko = "";
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		sql = "SELECT * FROM common_order_tbl WHERE CHUMONBANGO = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, juchubango);
		rs = ps.executeQuery();
		while (rs.next()) {
			komento = rs.getString("KOMENTO");
			biko = rs.getString("BIKO");
		}

		return new String[] { komento, biko };
	}

	public void setOrderListStatus(String shop) throws Exception, Exception {
		OrderApiService_Service api = new OrderApiService_Service();
		// ポートを生成します
		OrderApiService port = api.getOrderApiServicePort();
		// 認証モデルを生成します
		UserAuthModel auth = new UserAuthModel();
		// 認証キーをセットします
		Shohincommon common = new Shohincommon();
		String serviceSecret = common.getServiceSecret(shop);
		String licenseKey = common.getLicenseKey(shop);
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		auth.setAuthKey(author);
		// 店舗URL をセットします
		auth.setShopUrl(shop);
		// ユーザー名をセットします
		auth.setUserName("dongtze");
		// レスポンスモデルを生成します
		// UpdateOrderResponse
		// getRequestId を呼出します
		GetOrderRequestModel getmode = new GetOrderRequestModel();
		OrderSearchModel orderSearchModel = new OrderSearchModel();
		getmode.setOrderSearchModel(orderSearchModel);
		orderSearchModel.setDateType(1);
		if ("trend777".equals(shop)) {
			orderSearchModel.getStatus().add("新規２");
		} else {
			orderSearchModel.getStatus().add("新規2");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar date = Calendar.getInstance();
		String endDay = sdf.format(date.getTime());
		date.add(Calendar.DATE, -62);
		String startDay = sdf.format(date.getTime());
		getmode.getOrderSearchModel()
				.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(
						Integer.valueOf(startDay.substring(0, 4)), Integer.valueOf(startDay.substring(4, 6)),
						Integer.valueOf(startDay.substring(6, 8)), 0, 0, 0, 000, 0));
		getmode.getOrderSearchModel()
				.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(
						Integer.valueOf(endDay.substring(0, 4)), Integer.valueOf(endDay.substring(4, 6)),
						Integer.valueOf(endDay.substring(6, 8)), 23, 59, 59, 999, 0));
		GetOrderResponseModel getResponse = port.getOrder(auth, getmode);
		GetRequestIdResponseModel res = port.getRequestId(auth);

		int reqId = res.getRequestId();

		UpdateOrderRequestModel updatemodel = new UpdateOrderRequestModel();
		updatemodel.setRequestId(reqId);

		for (OrderModel order : getResponse.getOrderModel()) {
			order.setStatus("発送待ち");
			updatemodel.getOrderModel().add(order);
		}
		port.updateOrder(auth, updatemodel);

	}

	public List<String> getHasomachiYoteibiariList() throws Exception {
		List<String> bangoList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT t1.chumonbango bangot1 from common_order_tbl t1 left join tbl00027 t2 "
					+ "on t1.chumonbango = t2.chumonbango where t1.chumonsts1 = '2'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				bangoList.add(rs.getString("bangot1"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return bangoList;
	}

	public List<String> getUnsochuOrderList(OrderInfoBean orderInfoBean, List<String> bangoList) throws Exception {

		OrderCommon orderCommon = new OrderCommon();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean.getShouhinStsBeanList();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean.getCommonOrderBeanList();
		List<String> shoriList = new ArrayList<String>();
		for (String orderNo : bangoList) {
			CommonOrderBean thisOrder = null;
			for (CommonOrderBean order : commonOrderBeanList) {
				if (order.getJuchubango().equals(orderNo)) {
					thisOrder = order;
				}
			}
			// -10.入荷不可
			// -7.入荷待ち
			// -5.入荷中
			// -3.上海在庫
			// -1.運送中
			// 1.発送待ち
			List<Integer> nowStsList = new ArrayList<Integer>();

			List<String[]> hasomachiArr = orderCommon.getMachiListAll(thisOrder, shouhinStsBeanList, "2", "7");

			for (String[] hasomachi : hasomachiArr) {
				if ("入荷待ち".equals(hasomachi[2]) && !orderCommon.isNyukakano(hasomachi[0])) {
					nowStsList.add(-10);
				} else if ("入荷待ち".equals(hasomachi[2])) {
					nowStsList.add(-7);
				} else if ("入荷中".equals(hasomachi[2])) {
					nowStsList.add(-5);
				} else if ("上海在庫".equals(hasomachi[2])) {
					nowStsList.add(-3);
				} else if ("運送中".equals(hasomachi[2])) {
					nowStsList.add(-1);
				} else if ("発送待ち".equals(hasomachi[2])) {
					nowStsList.add(1);
				}
			}
			int nowSts = 2;
			for (Integer sts : nowStsList) {
				if (sts < nowSts) {
					nowSts = sts;
				}
			}

			// if (-10 == nowSts) {
			// shoriList.add(orderNo);
			// } else if (-7 == nowSts) {
			// shoriList.add(orderNo);
			// }
			if (-1 == nowSts) {
				shoriList.add(orderNo);
			}
			// } else if (-1 == nowSts) {
			// stsName = "運送中";
			// } else if (1 == nowSts) {
			// stsName = "発送待ち";
			// }

		}
		List<String> shoriList1 = new ArrayList<String>();
		for (String orderNo : shoriList) {
			boolean ariFlg = false;
			for (String shoribango : shoriList1) {
				if (shoribango.equals(orderNo)) {
					ariFlg = true;
				}
			}
			if (!ariFlg) {
				shoriList1.add(orderNo);
			}
		}
		return shoriList1;
	}

	public List<String> getUnsochuOrderList2(OrderInfoBean orderInfoBean, List<String> bangoList) throws Exception {

		OrderCommon orderCommon = new OrderCommon();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean.getShouhinStsBeanList();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean.getCommonOrderBeanList();
		List<String> shoriList = new ArrayList<String>();
		for (String orderNo : bangoList) {
			CommonOrderBean thisOrder = null;
			for (CommonOrderBean order : commonOrderBeanList) {
				if (order.getJuchubango().equals(orderNo)) {
					thisOrder = order;
				}
			}
			// -10.入荷不可
			// -7.入荷待ち
			// -5.入荷中
			// -3.上海在庫
			// -1.運送中
			// 1.発送待ち
			List<Integer> nowStsList = new ArrayList<Integer>();

			List<String[]> hasomachiArr = orderCommon.getMachiListAll(thisOrder, shouhinStsBeanList, "2", "7");

			for (String[] hasomachi : hasomachiArr) {
				if ("入荷待ち".equals(hasomachi[2]) && !orderCommon.isNyukakano(hasomachi[0])) {
					nowStsList.add(-10);
				} else if ("入荷待ち".equals(hasomachi[2])) {
					nowStsList.add(-7);
				} else if ("入荷中".equals(hasomachi[2])) {
					nowStsList.add(-5);
				} else if ("上海在庫".equals(hasomachi[2])) {
					nowStsList.add(-3);
				} else if ("運送中".equals(hasomachi[2])) {
					nowStsList.add(-1);
				} else if ("発送待ち".equals(hasomachi[2])) {
					nowStsList.add(1);
				}
			}
			int nowSts = 2;
			for (Integer sts : nowStsList) {
				if (sts < nowSts) {
					nowSts = sts;
				}
			}

			// if (-10 == nowSts) {
			// shoriList.add(orderNo);
			// } else if (-7 == nowSts) {
			// shoriList.add(orderNo);
			// }
			if (-1 == nowSts) {
				shoriList.add(orderNo);
			} else if (-3 == nowSts) {
				shoriList.add(orderNo);
			}
			// } else if (-1 == nowSts) {
			// stsName = "運送中";
			// } else if (1 == nowSts) {
			// stsName = "発送待ち";
			// }

		}
		List<String> shoriList1 = new ArrayList<String>();
		for (String orderNo : shoriList) {
			boolean ariFlg = false;
			for (String shoribango : shoriList1) {
				if (shoribango.equals(orderNo)) {
					ariFlg = true;
				}
			}
			if (!ariFlg) {
				shoriList1.add(orderNo);
			}
		}
		return shoriList1;
	}

	public List<String> getNyukachuList(OrderInfoBean orderInfoBean, List<String> bangoList) throws Exception {

		OrderCommon orderCommon = new OrderCommon();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean.getShouhinStsBeanList();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean.getCommonOrderBeanList();
		List<String> shoriList = new ArrayList<String>();
		for (String orderNo : bangoList) {
			CommonOrderBean thisOrder = null;
			for (CommonOrderBean order : commonOrderBeanList) {
				if (order.getJuchubango().equals(orderNo)) {
					thisOrder = order;
				}
			}
			// -10.入荷不可
			// -7.入荷待ち
			// -5.入荷中
			// -3.上海在庫
			// -1.運送中
			// 1.発送待ち
			List<Integer> nowStsList = new ArrayList<Integer>();

			List<String[]> hasomachiArr = orderCommon.getMachiListAll(thisOrder, shouhinStsBeanList, "2", "7");

			for (String[] hasomachi : hasomachiArr) {
				if ("入荷待ち".equals(hasomachi[2]) && !orderCommon.isNyukakano(hasomachi[0])) {
					nowStsList.add(-10);
				} else if ("入荷待ち".equals(hasomachi[2])) {
					nowStsList.add(-7);
				} else if ("入荷中".equals(hasomachi[2])) {
					nowStsList.add(-5);
				} else if ("上海在庫".equals(hasomachi[2])) {
					nowStsList.add(-3);
				} else if ("運送中".equals(hasomachi[2])) {
					nowStsList.add(-1);
				} else if ("発送待ち".equals(hasomachi[2])) {
					nowStsList.add(1);
				}
			}
			int nowSts = 2;
			for (Integer sts : nowStsList) {
				if (sts < nowSts) {
					nowSts = sts;
				}
			}

			// if (-10 == nowSts) {
			// shoriList.add(orderNo);
			// } else if (-7 == nowSts) {
			// shoriList.add(orderNo);
			// }
			if (-5 == nowSts) {
				shoriList.add(orderNo);
			} else if (-3 == nowSts) {
				shoriList.add(orderNo);
			}
			// } else if (-1 == nowSts) {
			// stsName = "運送中";
			// } else if (1 == nowSts) {
			// stsName = "発送待ち";
			// }

		}
		List<String> shoriList1 = new ArrayList<String>();
		for (String orderNo : shoriList) {
			boolean ariFlg = false;
			for (String shoribango : shoriList1) {
				if (shoribango.equals(orderNo)) {
					ariFlg = true;
				}
			}
			if (!ariFlg) {
				shoriList1.add(orderNo);
			}
		}
		return shoriList1;
	}

	public List<String> getNyukachuList2(OrderInfoBean orderInfoBean, List<String> bangoList) throws Exception {

		OrderCommon orderCommon = new OrderCommon();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean.getShouhinStsBeanList();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean.getCommonOrderBeanList();
		List<String> shoriList = new ArrayList<String>();
		for (String orderNo : bangoList) {
			CommonOrderBean thisOrder = null;
			for (CommonOrderBean order : commonOrderBeanList) {
				if (order.getJuchubango().equals(orderNo)) {
					thisOrder = order;
				}
			}
			// -10.入荷不可
			// -7.入荷待ち
			// -5.入荷中
			// -3.上海在庫
			// -1.運送中
			// 1.発送待ち
			List<Integer> nowStsList = new ArrayList<Integer>();

			List<String[]> hasomachiArr = orderCommon.getMachiListAll(thisOrder, shouhinStsBeanList, "2", "7");

			for (String[] hasomachi : hasomachiArr) {
				if ("入荷待ち".equals(hasomachi[2]) && !orderCommon.isNyukakano(hasomachi[0])) {
					nowStsList.add(-10);
				} else if ("入荷待ち".equals(hasomachi[2])) {
					nowStsList.add(-7);
				} else if ("入荷中".equals(hasomachi[2])) {
					nowStsList.add(-5);
				} else if ("上海在庫".equals(hasomachi[2])) {
					nowStsList.add(-3);
				} else if ("運送中".equals(hasomachi[2])) {
					nowStsList.add(-1);
				} else if ("発送待ち".equals(hasomachi[2])) {
					nowStsList.add(1);
				}
			}
			int nowSts = 2;
			for (Integer sts : nowStsList) {
				if (sts < nowSts) {
					nowSts = sts;
				}
			}

			// if (-10 == nowSts) {
			// shoriList.add(orderNo);
			// } else
			if (-7 == nowSts) {
				shoriList.add(orderNo);
			} else if (-5 == nowSts) {
				shoriList.add(orderNo);
			}
			// } else if (-3 == nowSts) {
			// shoriList.add(orderNo);
			// }
			// } else if (-1 == nowSts) {
			// stsName = "運送中";
			// } else if (1 == nowSts) {
			// stsName = "発送待ち";
			// }

		}
		List<String> shoriList1 = new ArrayList<String>();
		for (String orderNo : shoriList) {
			boolean ariFlg = false;
			for (String shoribango : shoriList1) {
				if (shoribango.equals(orderNo)) {
					ariFlg = true;
				}
			}
			if (!ariFlg) {
				shoriList1.add(orderNo);
			}
		}
		return shoriList1;
	}

	public List<String> getNyukamachiList(OrderInfoBean orderInfoBean, List<String> bangoList) throws Exception {

		OrderCommon orderCommon = new OrderCommon();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean.getShouhinStsBeanList();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean.getCommonOrderBeanList();
		List<String> shoriList = new ArrayList<String>();
		for (String orderNo : bangoList) {
			CommonOrderBean thisOrder = null;
			for (CommonOrderBean order : commonOrderBeanList) {
				if (order.getJuchubango().equals(orderNo)) {
					thisOrder = order;
				}
			}
			// -10.入荷不可
			// -7.入荷待ち
			// -5.入荷中
			// -3.上海在庫
			// -1.運送中
			// 1.発送待ち
			List<Integer> nowStsList = new ArrayList<Integer>();

			List<String[]> hasomachiArr = orderCommon.getMachiListAll(thisOrder, shouhinStsBeanList, "2", "7");

			for (String[] hasomachi : hasomachiArr) {
				if ("入荷待ち".equals(hasomachi[2]) && !orderCommon.isNyukakano(hasomachi[0])) {
					nowStsList.add(-10);
				} else if ("入荷待ち".equals(hasomachi[2])) {
					nowStsList.add(-7);
				} else if ("入荷中".equals(hasomachi[2])) {
					nowStsList.add(-5);
				} else if ("上海在庫".equals(hasomachi[2])) {
					nowStsList.add(-3);
				} else if ("運送中".equals(hasomachi[2])) {
					nowStsList.add(-1);
				} else if ("発送待ち".equals(hasomachi[2])) {
					nowStsList.add(1);
				}
			}
			int nowSts = 2;
			for (Integer sts : nowStsList) {
				if (sts < nowSts) {
					nowSts = sts;
				}
			}

			if (-10 == nowSts) {
				shoriList.add(orderNo);
			} else if (-7 == nowSts) {
				shoriList.add(orderNo);
			}
			// else if (-5 == nowSts) {
			// stsName = "入荷中";
			// } else if (-3 == nowSts) {
			// stsName = "上海在庫";
			// } else if (-1 == nowSts) {
			// stsName = "運送中";
			// } else if (1 == nowSts) {
			// stsName = "発送待ち";
			// }

		}
		List<String> shoriList1 = new ArrayList<String>();
		for (String orderNo : shoriList) {
			boolean ariFlg = false;
			for (String shoribango : shoriList1) {
				if (shoribango.equals(orderNo)) {
					ariFlg = true;
				}
			}
			if (!ariFlg) {
				shoriList1.add(orderNo);
			}
		}
		return shoriList1;
	}

	public List<String> getOkureList(OrderInfoBean orderInfoBean) throws Exception {
		List<String> bangoList = getHasomachiYoteibiariList();
		OrderCommon orderCommon = new OrderCommon();

		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean.getShouhinStsBeanList();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean.getCommonOrderBeanList();
		List<String> okureList = new ArrayList<String>();

		for (String orderNo : bangoList) {
			CommonOrderBean thisOrder = null;
			for (CommonOrderBean order : commonOrderBeanList) {
				if (order.getJuchubango().equals(orderNo)) {
					thisOrder = order;
				}
			}
			// -10.入荷不可
			// -7.入荷待ち
			// -5.入荷中
			// -3.上海在庫
			// -1.運送中
			// 1.発送待ち
			List<Integer> nowStsList = new ArrayList<Integer>();

			List<String[]> hasomachiArr = orderCommon.getMachiListAll(thisOrder, shouhinStsBeanList, "2", "7");

			for (String[] hasomachi : hasomachiArr) {
				if ("入荷待ち".equals(hasomachi[2]) && !orderCommon.isNyukakano(hasomachi[0])) {
					nowStsList.add(-10);
				} else if ("入荷待ち".equals(hasomachi[2])) {
					nowStsList.add(-3);
				} else if ("入荷中".equals(hasomachi[2])) {
					nowStsList.add(-3);
				} else if ("上海在庫".equals(hasomachi[2])) {
					nowStsList.add(-3);
				} else if ("運送中".equals(hasomachi[2])) {
					nowStsList.add(-1);
				} else if ("発送待ち".equals(hasomachi[2])) {
					nowStsList.add(1);
				}
			}
			int nowSts = 2;
			for (Integer sts : nowStsList) {
				if (sts < nowSts) {
					nowSts = sts;
				}
			}
			if (!Utility.isEmptyString(thisOrder.getHasoyakusokubi()) && -10 != nowSts) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				int betweenDays = Utility.daysBetween(new Date(), sdf.parse(thisOrder.getHasoyakusokubi()));
				if (betweenDays + nowSts <= 0) {
					okureList.add(orderNo);
				}
			}
		}
		return okureList;
	}

	public OrderApiBean getOrderListByApi_SourceVersion(String shop) throws Exception {
		List<RakutenCsvBean> rakutenBeanList = new ArrayList<RakutenCsvBean>();
		List<String> messageList = new ArrayList<String>();

		OrderApiService_Service api = new OrderApiService_Service();
		// ポートを生成します
		OrderApiService port = api.getOrderApiServicePort();
		// 認証モデルを生成します
		UserAuthModel auth = new UserAuthModel();
		// 認証キーをセットします
		Shohincommon common = new Shohincommon();
		String serviceSecret = common.getServiceSecret(shop);
		String licenseKey = common.getLicenseKey(shop);
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		auth.setAuthKey(author);
		// 店舗URL をセットします
		auth.setShopUrl(shop);
		// ユーザー名をセットします
		auth.setUserName("dongtze");
		// レスポンスモデルを生成します
		// UpdateOrderResponse
		// getRequestId を呼出します
		GetOrderRequestModel getmode = new GetOrderRequestModel();
		OrderSearchModel orderSearchModel = new OrderSearchModel();
		getmode.setOrderSearchModel(orderSearchModel);
		orderSearchModel.setDateType(1);
		if ("trend777".equals(shop)) {
			orderSearchModel.getStatus().add("新規２");
		} else {
			orderSearchModel.getStatus().add("新規2");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar date = Calendar.getInstance();
		String endDay = sdf.format(date.getTime());
		date.add(Calendar.DATE, -62);
		String startDay = sdf.format(date.getTime());
		getmode.getOrderSearchModel()
				.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(
						Integer.valueOf(startDay.substring(0, 4)), Integer.valueOf(startDay.substring(4, 6)),
						Integer.valueOf(startDay.substring(6, 8)), 0, 0, 0, 000, 0));
		getmode.getOrderSearchModel()
				.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(
						Integer.valueOf(endDay.substring(0, 4)), Integer.valueOf(endDay.substring(4, 6)),
						Integer.valueOf(endDay.substring(6, 8)), 23, 59, 59, 999, 0));
		GetOrderResponseModel getResponse = port.getOrder(auth, getmode);
		GetRequestIdResponseModel res = port.getRequestId(auth);
		if (!Utility.isEmptyList(res.getUnitError())) {
			List<UnitErrorModel> unitList = res.getUnitError();
			for (UnitErrorModel unit : unitList) {
				messageList.add(unit.getErrorCode() + unit.getMessage() + unit.getOrderKey());

			}
		}

		for (OrderModel order : getResponse.getOrderModel()) {

			rakutenBeanList.add(setOrder(order));
			if (!Utility.isEmptyList(order.getChildOrderModel())) {
				for (OrderModel childOrder : order.getChildOrderModel()) {
					rakutenBeanList.add(setOrder(childOrder));
				}
			}

		}

		OrderApiBean orderApiBean = new OrderApiBean();
		orderApiBean.setRakutenBeanList(rakutenBeanList);
		orderApiBean.setMessageList(messageList);

		return orderApiBean;
	}
	
	public OrderApiBean getOrderListByApi(String shopName) throws Exception {
		List<RakutenCsvBean> rakutenBeanList = new ArrayList<RakutenCsvBean>();
		List<String> messageList = new ArrayList<String>();
		
		Shop shop = new Shop(shopName);
		List<Integer> orderProgressList = new ArrayList<Integer>();
		orderProgressList.add(300);
		List<Order> orders = shop.getOrders(orderProgressList);
		
		for(MessageFromRMS message : shop.getMessageFromRMS_GetOrder()) {
			if ("INFO".equals(message.getMessageType())) {
				
			} else {
				messageList.add(message.getMessageCode() + message.getMessage() + message.getOrderNumber() == null ? "" : message.getOrderNumber());
			}
		}
		
		for (Order order : orders) {

			rakutenBeanList.add(setOrder(order));
		}
		
		OrderApiBean orderApiBean = new OrderApiBean();
		orderApiBean.setRakutenBeanList(rakutenBeanList);
		orderApiBean.setMessageList(messageList);

		return orderApiBean;
	}

	public OrderApiBean getOrderListByCSV(File file,String shop) throws Exception {
		List<RakutenCsvBean> rakutenBeanList = new ArrayList<RakutenCsvBean>();
		List<String> messageList = new ArrayList<String>();
		List<String[]> orderList = Utility.readCsvFile(file, true);

		rakutenBeanList = setOrderByCsv(file,orderList);

		OrderApiBean orderApiBean = new OrderApiBean();
		orderApiBean.setRakutenBeanList(rakutenBeanList);
		orderApiBean.setMessageList(messageList);

		return orderApiBean;
	}

	public List<String> getijoList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> ijoList = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  tbl00031 where 1 = 1";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ijoList.add(rs.getString("chumonbango"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return ijoList;
	}

	private RakutenCsvBean setOrder(OrderModel order) {
		RakutenCsvBean bean = new RakutenCsvBean();

		// 受注番号
		bean.setJuchubango(order.getOrderNumber());
		// 受注ステータス
		bean.setChumonsutetasu(order.getStatus());
		// カード決済ステータス
		// bean.setKadokesaisutetasu(order.get);
		// 入金日
		// bean.setNyukinbi();
		// 配送日
		// bean.setHaisoubi(order.getShippingDate());
		// お届け時間帯
		bean.setOtodokejikantai(order.getOption());
		// お届け日指定
		// bean.setOtodokebishitei(order.get);
		// 担当者
		// bean.setTantousha(order.get);
		// ひとことメモ
		bean.setHitokotomemo(order.getMemo());
		// メール差込文(お客様へのメッセージ)
		bean.setMerusashikomibun(order.getMailPlugSentence());
		// 初期購入合計金額
		bean.setShokikonyugokeikingaku(Utility.strStringValue(order.getFirstAmount()));
		// 利用端末
		bean.setRiyoutanmatsu(Utility.strStringValue(order.getCarrierCode()));
		// メールキャリアコード
		bean.setMerukyariakodo(Utility.strStringValue(order.getEmailCarrierCode()));
		// ギフトチェック（0:なし/1:あり）
		bean.setGifutocheku(order.isIsGiftCheck() ? "1" : "0");
		// コメント
		bean.setKomento(order.getOption());
		// 注文日時
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		bean.setChumonnichiji(sdf1.format(order.getOrderDate().toGregorianCalendar().getTime()));
		// 複数送付先フラグ
		// bean.setFukususofusakifuragu(order.get);
		// 警告表示フラグ
		// bean.setKeikokuhyojifuragu(order.get);
		// 楽天会員フラグ
		// bean.setRakutenkaiinfuragu(order.get);
		// 合計
		bean.setGokei(Utility.strStringValue(order.getGoodsPrice()));
		// 消費税(-99999=無効値)
		bean.setShohizei(Utility.strStringValue(order.getGoodsTax()));
		// 送料(-99999=無効値)
		bean.setSoryou(Utility.strStringValue(order.getPostagePrice()));
		// 代引料(-99999=無効値)
		bean.setDaibikiryou(Utility.strStringValue(order.getDeliveryPrice()));
		// 請求金額(-99999=無効値)
		bean.setSeikyukingaku(Utility.strStringValue(order.getRequestPrice()));
		// 合計金額(-99999=無効値)
		bean.setGokeikingaku(Utility.strStringValue(order.getTotalPrice()));
		// 同梱ID
		bean.setDokonId(Utility.strStringValue(order.getEnclosureId()));
		// 同梱ステータス
		bean.setDokonsutetasu(Utility.strStringValue(order.getEnclosureStatus()));
		// 同梱商品合計金額
		bean.setDokonshouhingokeikingaku(Utility.strStringValue(order.getEnclosureGoodsPrice()));
		// 同梱送料合計
		bean.setDokonsoryougokei(Utility.strStringValue(order.getEnclosurePostagePrice()));
		// 同梱代引料合計
		bean.setDokondaibikiryougokei(Utility.strStringValue(order.getEnclosureDeliveryPrice()));
		// 同梱消費税合計
		bean.setDokonshohizeigokei(Utility.strStringValue(order.getEnclosureGoodsTax()));
		// 同梱請求金額
		bean.setDokonseikyukingaku(Utility.strStringValue(order.getEnclosureRequestPrice()));
		// 同梱合計金額
		bean.setDokongokeikingaku(Utility.strStringValue(order.getEnclosureTotalPrice()));
		// 同梱楽天バンク決済振替手数料
		bean.setDokonrakutenbankukesaifurikaetesuryou(
				Utility.strStringValue(order.getEnclosureRBankTransferCommission()));
		// 同梱ポイント利用合計
		bean.setDokonpointoriyougokei(Utility.strStringValue(order.getEnclosurePointPrice()));
		// メールフラグ
		// bean.setMerufuragu(order.get);
		// 注文日
		// bean.setChumonbi(order.get);
		// 注文時間
		// bean.setChumonjikan(order.get);
		// モバイルキャリア決済番号
		// bean.setMobairukyariakesaibango(order.get);
		// 購入履歴修正可否タイプ
		// bean.setKonyurirekishuseikahitaipu(order.get);
		// 購入履歴修正アイコンフラグ
		// bean.setKonyurirekishuseiaikonfuragu(order.get);
		// 購入履歴修正催促メールフラグ
		// bean.setKonyurirekishuseisaisokumerufuragu(order.get);
		// 送付先一致フラグ
		// bean.setSofusakiichifuragu(order.get);
		// ポイント利用有無
		// bean.setPointoriyouumu(order.get);
		// 注文者郵便番号１
		bean.setChumonshayubinbango1(order.getOrdererModel().getZipCode1());
		// 注文者郵便番号２
		bean.setChumonshayubinbango2(order.getOrdererModel().getZipCode2());
		// 注文者住所：都道府県
		bean.setChumonshajushotodofuken(order.getOrdererModel().getPrefecture());
		// 注文者住所：都市区
		bean.setChumonshajushotoshiku(order.getOrdererModel().getCity());
		// 注文者住所：町以降
		bean.setChumonshajushochoijou(order.getOrdererModel().getSubAddress());
		// 注文者名字
		bean.setChumonshameiji(order.getOrdererModel().getFamilyName());
		// 注文者名前
		bean.setChumonshanamae(order.getOrdererModel().getFirstName());
		// 注文者名字フリガナ
		bean.setChumonshameijifurigana(order.getOrdererModel().getFamilyNameKana());
		// 注文者名前フリガナ
		bean.setChumonshanamaefurigana(order.getOrdererModel().getFirstNameKana());
		// 注文者電話番号１
		bean.setChumonshadenwabango1(order.getOrdererModel().getPhoneNumber1());
		// 注文者電話番号２
		bean.setChumonshadenwabango2(order.getOrdererModel().getPhoneNumber2());
		// 注文者電話番号３
		bean.setChumonshadenwabango3(order.getOrdererModel().getPhoneNumber3());
		// メールアドレス
		bean.setMeruadoresu(order.getOrdererModel().getEmailAddress());
		// 注文者性別
		bean.setChumonshaseibetu(order.getOrdererModel().getSex());
		// 注文者誕生日
		bean.setChunonshatanjoubi(order.getOrdererModel().getBirthYear() + "年" + order.getOrdererModel().getBirthMonth()
				+ "月" + order.getOrdererModel().getBirthDay() + "日");
		// 決済方法
		bean.setKesaihouhou(order.getSettlementModel().getSettlementName());
		if (order.getSettlementModel() != null) {
			if (order.getSettlementModel().getCardModel() != null) {
				// クレジットカード種類
				bean.setKurejitokadoshurui(order.getSettlementModel().getCardModel().getBrandName());
				// クレジットカード番号
				bean.setKurejitokadobango(order.getSettlementModel().getCardModel().getCardNo());
				// クレジットカード名義人
				bean.setKurejitokadomeiginin(order.getSettlementModel().getCardModel().getOwnerName());
				// クレジットカード有効期限
				bean.setGurejitokadoyukokigen(order.getSettlementModel().getCardModel().getExpYM());
				// クレジットカード分割選択
				bean.setKurejitokadobunwarisentaku(
						Utility.strStringValue(order.getSettlementModel().getCardModel().getPayType()));
				// クレジットカード分割備考
				bean.setKurejitokadobunraribiko(order.getSettlementModel().getCardModel().getInstallmentDesc());
			}
		}
		if (order.getDeliveryModel() != null) {
			// 配送方法
			bean.setHaisouhoho(order.getDeliveryModel().getDeliveryName());
			// 配送区分
			bean.setHaisokubun(Utility.strStringValue(order.getDeliveryModel().getDeliveryClass()));
		}
		if (order.getPointModel() != null) {
			// ポイント利用額
			bean.setPointoriyogaku(Utility.strStringValue(order.getPointModel().getUsedPoint()));
			// ポイント利用条件
			bean.setPointoriyoujouken(Utility.strStringValue(order.getPointModel().getPointUsage()));
			// ポイントステータス
			bean.setPointosutetasu(Utility.strStringValue(order.getPointModel().getStatus()));
		}
		// 楽天バンク決済ステータス
		if (order.getRBankModel() != null) {
			bean.setRakutenbangkukesaisutetasu(Utility.strStringValue(order.getRBankModel().getRbankStatus()));
			// 楽天バンク振替手数料負担区分
			bean.setRakutenbankufurikomitesuryoufutankubun(
					Utility.strStringValue(order.getRBankModel().getRbCommissionPayer()));
			// 楽天バンク決済手数料
			bean.setRakutenbankukesaitesuryou(Utility.strStringValue(order.getRBankModel().getTransferCommission()));
		}
		if (order.getWrappingModel1() != null) {
			// ラッピングタイトル(包装紙)
			bean.setRabingutaitoruhousou(order.getWrappingModel1().getTitle());
			// ラッピング名(包装紙)
			bean.setRapingumeihousou(order.getWrappingModel1().getName());
			// ラッピング料金(包装紙)
			bean.setRapinguryoukinhousou(Utility.strStringValue(order.getWrappingModel1().getPrice()));
			// 税込別(包装紙)
			bean.setZeikomibetuhousou(order.getWrappingModel1().isIsIncludedTax() ? "1" : "0");
		}
		if (order.getWrappingModel2() != null) {
			// ラッピングタイトル(リボン)
			bean.setRapingutaitoruribon(order.getWrappingModel2().getName());
			// ラッピング名(リボン)
			bean.setRapingumeiribon(order.getWrappingModel2().getName());
			// ラッピング料金(リボン)
			bean.setRapinguryoukinribobn(Utility.strStringValue(order.getWrappingModel2().getPrice()));
			// 税込別(リボン)
			bean.setZeikomibeturibon(order.getWrappingModel2().isIsIncludedTax() ? "1" : "0");
		}
		// 送付先送料
		bean.setSoufusakisourou(Utility.strStringValue(order.getPackageModel().get(0).getPostagePrice()));
		// 送付先代引料
		bean.setSoufusakidaibikiryou(Utility.strStringValue(order.getPackageModel().get(0).getDeliveryPrice()));
		// 送付先消費税
		bean.setSoufusakishouhizei(Utility.strStringValue(order.getPackageModel().get(0).getGoodsTax()));
		// お荷物伝票番号
		bean.setOnimotudenpyoubango(order.getPackageModel().get(0).getShippingNumber());
		// 送付先商品合計金額
		bean.setSoufusakishouhingokeikingaku(Utility.strStringValue(order.getPackageModel().get(0).getGoodsPrice()));
		// のし
		bean.setNoshi(order.getPackageModel().get(0).getNoshi());
		// 送付先郵便番号１
		bean.setSoufusakiyubinbango1(order.getPackageModel().get(0).getSenderModel().getZipCode1());
		// 送付先郵便番号２
		bean.setSoufusakiyubinbango2(order.getPackageModel().get(0).getSenderModel().getZipCode2());
		// 送付先住所：都道府県
		bean.setSoufusakijushotodofuken(order.getPackageModel().get(0).getSenderModel().getPrefecture());
		// 送付先住所：都市区
		bean.setSoufusakijushotoshiku(order.getPackageModel().get(0).getSenderModel().getCity());
		// 送付先住所：町以降
		bean.setSoufusakijushochoijou(order.getPackageModel().get(0).getSenderModel().getSubAddress());
		// 送付先名字
		bean.setSofusakimeiji(order.getPackageModel().get(0).getSenderModel().getFamilyName());
		// 送付先名前
		bean.setSoufusakinamae(order.getPackageModel().get(0).getSenderModel().getFirstName());
		// 送付先名字フリガナ
		bean.setSoufusakimeijifurigana(order.getPackageModel().get(0).getSenderModel().getFamilyNameKana());
		// 送付先名前フリガナ
		bean.setSoufusakimeijinamaefurigana(order.getPackageModel().get(0).getSenderModel().getFirstNameKana());
		// 送付先電話番号１
		bean.setSoufusakidenwabango1(order.getPackageModel().get(0).getSenderModel().getPhoneNumber1());
		// 送付先電話番号２
		bean.setSoufusakidenwabango2(order.getPackageModel().get(0).getSenderModel().getPhoneNumber2());
		// 送付先電話番号３
		bean.setSoufusakidenwabango3(order.getPackageModel().get(0).getSenderModel().getPhoneNumber3());
		// あす楽希望
		bean.setAsurakukibou(order.getAsurakuFlg());
		// クーポン利用額
		String kupon = "0";
		if (order.getCouponAllTotalPrice() != null) {
			kupon = Utility.strStringValue(order.getCouponAllTotalPrice());
		}
		bean.setKuponriyougaku(kupon);
		// 店舗発行クーポン利用額
		bean.setTenpohakoukuponriyougaku(Utility.strStringValue(order.getCouponShopPrice()));
		// 楽天発行クーポン利用額
		bean.setRakutenhakoukuponriyougaku(Utility.strStringValue(order.getCouponOtherPrice()));
		// 同梱注文クーポン利用額
		bean.setDokonchumonkuponrigougaku(Utility.strStringValue(order.getEnclosureCouponPrice()));
		// 配送会社
		bean.setHaisoukaisha(order.getPackageModel().get(0).getDeliveryCompanyId());
		// 代引手数料込別
		bean.setDaibikitesuryokomibetsu(
				order.getPackageModel().get(0).getItemModel().get(0).isIsIncludedCashOnDeliveryPostage() ? "込" : "別");
		RakutenDetailCsvBean detail = null;
		List<RakutenDetailCsvBean> detailList = new ArrayList<RakutenDetailCsvBean>();
		bean.setShousaiList(detailList);
		for (ItemModel item : order.getPackageModel().get(0).getItemModel()) {
			detail = new RakutenDetailCsvBean();
			detailList.add(detail);
			// 商品ID
			detail.setShouhinId(Utility.strStringValue(item.getItemId()));
			// 商品名
			detail.setShouhinmei(item.getItemName());
			// 商品番号
			detail.setShouhinbango(item.getItemNumber());
			// 商品URL
			detail.setShouhinURL(Utility.strStringValue(item.getPageURL()));
			// 単価
			detail.setTanka(Utility.strStringValue(item.getPrice()));
			// 個数
			detail.setKosu(Utility.strStringValue(item.getUnits()));
			// 送料込別
			detail.setSouryoukomibetsu(item.isIsIncludedPostage() ? "込" : "別");
			// 税込別
			detail.setZeikomibetsu(item.isIsIncludedTax() ? "込" : "別");
			// 代引手数料込別
			detail.setDaibikitesuryoukomibetsu(item.isIsIncludedCashOnDeliveryPostage() ? "込" : "別");
			// 項目・選択肢
			detail.setKomokusentakushi(Utility.strStringValue(item.getSelectedChoice()));
			// ポイント倍率
			detail.setPointobairitsu(Utility.strStringValue(item.getPointRate()));
			// ポイントタイプ
			detail.setPointotaipu(Utility.strStringValue(item.getPointType()));
			// レコードナンバー
			// detail.setRekodananba(item.getre);
			// 納期情報
			detail.setNokijouho(item.getNormalItemModel().getDelvdateInfo());
			// 在庫タイプ
			detail.setZaikotaipu(item.getNormalItemModel().getInventoryType());
			// ラッピング種類(包装紙)
			// detail.setRapingushuruihousou(item.get);
			// ラッピング種類(リボン)
			// detail.setRapingushuruiribon(item.get);
		}
		return bean;
	}
	
	private RakutenCsvBean setOrder(Order order) throws Exception {
		RakutenCsvBean bean = new RakutenCsvBean();
		
		// 受注番号
		bean.setJuchubango(order.getOrderNumber());
		
		// 注文日時
		SimpleDateFormat sdf_OrderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf_OrderDateWithZone = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		bean.setChumonnichiji(sdf_OrderDate.format(sdf_OrderDateWithZone.parse(order.getOrderDatetime())));
		
		// 決済方法
		bean.setKesaihouhou(order.getSettlementModel().getSettlementMethod());
		
		// ポイント利用額
		bean.setPointoriyogaku(Utility.strStringValue(order.getPointModel().getUsedPoint()));
		
		// あす楽希望フラグ 
		bean.setAsurakukibou(Utility.strStringValue(order.getAsurakuFlag()));
		
		// 注文者名字
		bean.setChumonshameiji(order.getOrdererModel().getFamilyName());
		
		// 注文者名称
		bean.setChumonshanamae(order.getOrdererModel().getFirstName());
		
		// 注文者名字カナ
		bean.setChumonshameijifurigana(order.getOrdererModel().getFamilyNameKana());
		
		// 注文者名称カナ
		bean.setChumonshanamaefurigana(order.getOrdererModel().getFirstNameKana());
		
		// メールアドレス
		bean.setMeruadoresu(order.getOrdererModel().getEmailAddress());
		
		// 注文者誕生日
		bean.setChunonshatanjoubi(Utility.strStringValue(order.getOrdererModel().getBirthYear()) + "年" + Utility.strStringValue(order.getOrdererModel().getBirthMonth()) + "月" + Utility.strStringValue(order.getOrdererModel().getBirthDay()) + "日");
		
		// 注文者郵便番号１
		bean.setChumonshayubinbango1(order.getOrdererModel().getZipCode1());
		
		// 注文者郵便番号２
		bean.setChumonshayubinbango2(order.getOrdererModel().getZipCode2());
		
		// 注文者住所：都道府県
		bean.setChumonshajushotodofuken(order.getOrdererModel().getPrefecture());
		
		// 注文者住所：都市区
		bean.setChumonshajushotoshiku(order.getOrdererModel().getCity());
		
		// 注文者住所：町以降
		bean.setChumonshajushochoijou(order.getOrdererModel().getSubAddress());
		
		// 注文者電話番号１
		bean.setChumonshadenwabango1(order.getOrdererModel().getPhoneNumber1());
		
		// 注文者電話番号２
		bean.setChumonshadenwabango2(order.getOrdererModel().getPhoneNumber2());
		
		// 注文者電話番号３
		bean.setChumonshadenwabango3(order.getOrdererModel().getPhoneNumber3());
		
		// コメント
		bean.setKomento(order.getRemarks());
		
		// メール差込文(お客様へのメッセージ)
		bean.setMerusashikomibun(order.getMailPlugSentence());
		
		// 送付先名字
		bean.setSofusakimeiji(order.getPackageModelList().get(0).getSenderModel().getFamilyName());
		
		// 送付先名前
		bean.setSoufusakinamae(order.getPackageModelList().get(0).getSenderModel().getFirstName());
		
		// 送付先名字フリガナ
		bean.setSoufusakimeijifurigana(order.getPackageModelList().get(0).getSenderModel().getFamilyNameKana());
		
		// 送付先名前フリガナ
		bean.setSoufusakimeijinamaefurigana(order.getPackageModelList().get(0).getSenderModel().getFamilyNameKana());
		
		// 配送方法
		bean.setHaisouhoho(order.getDeliveryModel().getDeliveryName());
		
		// 送付先郵便番号１
		bean.setSoufusakiyubinbango1(order.getPackageModelList().get(0).getSenderModel().getZipCode1());
		
		// 送付先郵便番号２
		bean.setSoufusakiyubinbango2(order.getPackageModelList().get(0).getSenderModel().getZipCode2());
		
		// 送付先電話番号１
		bean.setSoufusakidenwabango1(order.getPackageModelList().get(0).getSenderModel().getPhoneNumber1());
		
		// 送付先電話番号２
		bean.setSoufusakidenwabango2(order.getPackageModelList().get(0).getSenderModel().getPhoneNumber2());
		
		// 送付先電話番号３
		bean.setSoufusakidenwabango3(order.getPackageModelList().get(0).getSenderModel().getPhoneNumber3());
		
		// 送付先住所：都道府県
		bean.setSoufusakijushotodofuken(order.getPackageModelList().get(0).getSenderModel().getPrefecture());
		
		// 送付先住所：都市区
		bean.setSoufusakijushotoshiku(order.getPackageModelList().get(0).getSenderModel().getCity());
		
		// 送付先住所：町以降
		bean.setSoufusakijushochoijou(order.getPackageModelList().get(0).getSenderModel().getSubAddress());
		
		// 合計商品
		bean.setGokei(Utility.strStringValue(order.getGoodsPrice()));
		
		// 合計税
		bean.setShohizei(Utility.strStringValue(order.getGoodsTax()));
		
		// 合計送料
		bean.setSoryou(Utility.strStringValue(order.getPostagePrice()));
		
		// 合計代引き料
		bean.setDaibikiryou(Utility.strStringValue(order.getDeliveryPrice()));
		
		// 請求金額
		bean.setSeikyukingaku(Utility.strStringValue(order.getRequestPrice()));
		
		// クーポン利用額
		bean.setKuponriyougaku(Utility.strStringValue(order.getCouponAllTotalPrice()));
		
		List<RakutenDetailCsvBean> detailList = new ArrayList<RakutenDetailCsvBean>();
		RakutenDetailCsvBean detail = null;
		for (Order.PackageModel.ItemModel item : order.getPackageModelList().get(0).getItemModelList()) {
			detail = new RakutenDetailCsvBean();
			// 商品名
			detail.setShouhinmei(item.getItemName());
			
			// 商品番号
			if (item.getItemNumber() != null) {
				detail.setShouhinbango(item.getManageNumber() + item.getItemNumber());
			}else {
				detail.setShouhinbango(item.getManageNumber());
			}
			
			// 商品URL
			detail.setShouhinURL("");
			
			// 単価
			detail.setTanka(Utility.strStringValue(item.getPrice()));
			
			// 個数
			detail.setKosu(Utility.strStringValue(item.getUnits()));
			
			// 送料込別
			detail.setSouryoukomibetsu(item.getIncludePostageFlag() == 1 ? "込" : "別");
			
			// 税込別
			detail.setZeikomibetsu(item.getIncludeTaxFlag() == 1 ? "込" : "別");
			
			// 代引手数料込別
			detail.setDaibikitesuryoukomibetsu(item.getIncludeCashOnDeliveryPostageFlag() == 1 ? "込" : "別");
			
			// 項目・選択肢
			detail.setKomokusentakushi(Utility.strStringValue(item.getSelectedChoice()));
			
			// ポイント倍率
			detail.setPointobairitsu(Utility.strStringValue(item.getPointRate()));
			
			// 納期情報
			detail.setNokijouho(item.getDelvdateInfo());
			
			detailList.add(detail);
		}
		// 詳細リスト
		bean.setShousaiList(detailList);
		
		// 同梱ステータス
		bean.setDokonsutetasu("0");
		
		// 同梱ID
		bean.setDokonId("0");
		
		return bean;
	}

	private List<RakutenCsvBean> setOrderByCsv(File file, List<String[]> orderList) {
		List<RakutenCsvBean> rakutenCsvBeanList = new ArrayList<RakutenCsvBean>();
		RakutenCsvBean bean = null;
		String orderNo = "";

		for (String[] order : orderList) {
			if (bean == null || !order[0].equals(orderNo)) {
				orderNo = order[0];
				bean = new RakutenCsvBean();
				rakutenCsvBeanList.add(bean);
				// 受注番号
				bean.setJuchubango(order[0]);
				// 受注ステータス
				bean.setChumonsutetasu(order[1]);
				// カード決済ステータス
				// bean.setKadokesaisutetasu(order.get);
				// 入金日
				// bean.setNyukinbi();
				// 配送日
				// bean.setHaisoubi(order.getShippingDate());
				// お届け時間帯
				bean.setOtodokejikantai(order[94]);
				// お届け日指定
				// bean.setOtodokebishitei(order.get);
				// 担当者
				// bean.setTantousha(order.get);
				// ひとことメモ
				bean.setHitokotomemo(order[97]);
				// メール差込文(お客様へのメッセージ)
				bean.setMerusashikomibun(order[98]);
				// 初期購入合計金額
//				bean.setShokikonyugokeikingaku(Utility.strStringValue(order.getFirstAmount()));
				// 利用端末
				bean.setRiyoutanmatsu(Utility.strStringValue(order[101]));
				// メールキャリアコード
				bean.setMerukyariakodo(Utility.strStringValue(order[102]));
				// ギフトチェック（0:なし/1:あり）
				bean.setGifutocheku(order[99]);
				// コメント
				bean.setKomento(order[100]);
				// 注文日時
				bean.setChumonnichiji(order[4]);
				// 複数送付先フラグ
				// bean.setFukususofusakifuragu(order.get);
				// 警告表示フラグ
				// bean.setKeikokuhyojifuragu(order.get);
				// 楽天会員フラグ
				// bean.setRakutenkaiinfuragu(order.get);
				// 合計
				bean.setGokei(Utility.strStringValue(order[25]));
				// 消費税(-99999=無効値)
				bean.setShohizei(Utility.strStringValue(order[26]));
				// 送料(-99999=無効値)
				bean.setSoryou(Utility.strStringValue(order[27]));
				// 代引料(-99999=無効値)
				bean.setDaibikiryou(Utility.strStringValue(order[28]));
				// 請求金額(-99999=無効値)
				bean.setSeikyukingaku(Utility.strStringValue(order[29]));
				// 合計金額(-99999=無効値)
				bean.setGokeikingaku(Utility.strStringValue(order[30]));
//				// 同梱ID
				bean.setDokonId("0");
//				// 同梱ステータス
//				bean.setDokonsutetasu(Utility.strStringValue(order.getEnclosureStatus()));
//				// 同梱商品合計金額
//				bean.setDokonshouhingokeikingaku(Utility.strStringValue(order.getEnclosureGoodsPrice()));
//				// 同梱送料合計
//				bean.setDokonsoryougokei(Utility.strStringValue(order.getEnclosurePostagePrice()));
//				// 同梱代引料合計
//				bean.setDokondaibikiryougokei(Utility.strStringValue(order.getEnclosureDeliveryPrice()));
//				// 同梱消費税合計
//				bean.setDokonshohizeigokei(Utility.strStringValue(order.getEnclosureGoodsTax()));
//				// 同梱請求金額
//				bean.setDokonseikyukingaku(Utility.strStringValue(order.getEnclosureRequestPrice()));
//				// 同梱合計金額
//				bean.setDokongokeikingaku(Utility.strStringValue(order.getEnclosureTotalPrice()));
//				// 同梱楽天バンク決済振替手数料
//				bean.setDokonrakutenbankukesaifurikaetesuryou(
//						Utility.strStringValue(order.getEnclosureRBankTransferCommission()));
//				// 同梱ポイント利用合計
//				bean.setDokonpointoriyougokei(Utility.strStringValue(order.getEnclosurePointPrice()));
				// メールフラグ
				// bean.setMerufuragu(order.get);
				// 注文日
				// bean.setChumonbi(order.get);
				// 注文時間
				// bean.setChumonjikan(order.get);
				// モバイルキャリア決済番号
				// bean.setMobairukyariakesaibango(order.get);
				// 購入履歴修正可否タイプ
				// bean.setKonyurirekishuseikahitaipu(order.get);
				// 購入履歴修正アイコンフラグ
				// bean.setKonyurirekishuseiaikonfuragu(order.get);
				// 購入履歴修正催促メールフラグ
				// bean.setKonyurirekishuseisaisokumerufuragu(order.get);
				// 送付先一致フラグ
				// bean.setSofusakiichifuragu(order.get);
				// ポイント利用有無
				// bean.setPointoriyouumu(order.get);
				// 注文者郵便番号１
				bean.setChumonshayubinbango1(order[35]);
				// 注文者郵便番号２
				bean.setChumonshayubinbango2(order[36]);
				// 注文者住所：都道府県
				bean.setChumonshajushotodofuken(order[37]);
				// 注文者住所：都市区
				bean.setChumonshajushotoshiku(order[38]);
				// 注文者住所：町以降
				bean.setChumonshajushochoijou(order[39]);
				// 注文者名字
				bean.setChumonshameiji(order[40]);
				// 注文者名前
				bean.setChumonshanamae(order[41]);
				// 注文者名字フリガナ
				bean.setChumonshameijifurigana(order[42]);
				// 注文者名前フリガナ
				bean.setChumonshanamaefurigana(order[43]);
				// 注文者電話番号１
				bean.setChumonshadenwabango1(order[44]);
				// 注文者電話番号２
				bean.setChumonshadenwabango2(order[45]);
				// 注文者電話番号３
				bean.setChumonshadenwabango3(order[46]);
				// メールアドレス
				bean.setMeruadoresu(order[47]);
				// 注文者性別
				bean.setChumonshaseibetu(order[48]);
				// 注文者誕生日
//				bean.setChunonshatanjoubi(order.getOrdererModel().getBirthYear() + "年"
//						+ order.getOrdererModel().getBirthMonth() + "月" + order.getOrdererModel().getBirthDay() + "日");
				// 決済方法
				bean.setKesaihouhou(order[12]);
//				if (order.getSettlementModel() != null) {
//					if (order.getSettlementModel().getCardModel() != null) {
//						// クレジットカード種類
//						bean.setKurejitokadoshurui(order.getSettlementModel().getCardModel().getBrandName());
//						// クレジットカード番号
//						bean.setKurejitokadobango(order.getSettlementModel().getCardModel().getCardNo());
//						// クレジットカード名義人
//						bean.setKurejitokadomeiginin(order.getSettlementModel().getCardModel().getOwnerName());
//						// クレジットカード有効期限
//						bean.setGurejitokadoyukokigen(order.getSettlementModel().getCardModel().getExpYM());
//						// クレジットカード分割選択
//						bean.setKurejitokadobunwarisentaku(
//								Utility.strStringValue(order.getSettlementModel().getCardModel().getPayType()));
//						// クレジットカード分割備考
//						bean.setKurejitokadobunraribiko(order.getSettlementModel().getCardModel().getInstallmentDesc());
//					}
//				}

				// 配送方法
				bean.setHaisouhoho(order[15]);
				// 配送区分
				bean.setHaisokubun(order[16]);

				// ポイント利用額
				bean.setPointoriyogaku(order[31]);
				// ポイント利用条件
//					bean.setPointoriyoujouken(Utility.strStringValue(order.getPointModel().getPointUsage()));
//					// ポイントステータス
//					bean.setPointosutetasu(Utility.strStringValue(order.getPointModel().getStatus()));

				// 楽天バンク決済ステータス
//				if (order.getRBankModel() != null) {
//					bean.setRakutenbangkukesaisutetasu(Utility.strStringValue(order.getRBankModel().getRbankStatus()));
//					// 楽天バンク振替手数料負担区分
//					bean.setRakutenbankufurikomitesuryoufutankubun(
//							Utility.strStringValue(order.getRBankModel().getRbCommissionPayer()));
//					// 楽天バンク決済手数料
//					bean.setRakutenbankukesaitesuryou(
//							Utility.strStringValue(order.getRBankModel().getTransferCommission()));
//				}
//				if (order.getWrappingModel1() != null) {
//					// ラッピングタイトル(包装紙)
//					bean.setRabingutaitoruhousou(order.getWrappingModel1().getTitle());
//					// ラッピング名(包装紙)
//					bean.setRapingumeihousou(order.getWrappingModel1().getName());
//					// ラッピング料金(包装紙)
//					bean.setRapinguryoukinhousou(Utility.strStringValue(order.getWrappingModel1().getPrice()));
//					// 税込別(包装紙)
//					bean.setZeikomibetuhousou(order.getWrappingModel1().isIsIncludedTax() ? "1" : "0");
//				}
//				if (order.getWrappingModel2() != null) {
//					// ラッピングタイトル(リボン)
//					bean.setRapingutaitoruribon(order.getWrappingModel2().getName());
//					// ラッピング名(リボン)
//					bean.setRapingumeiribon(order.getWrappingModel2().getName());
//					// ラッピング料金(リボン)
//					bean.setRapinguryoukinribobn(Utility.strStringValue(order.getWrappingModel2().getPrice()));
//					// 税込別(リボン)
//					bean.setZeikomibeturibon(order.getWrappingModel2().isIsIncludedTax() ? "1" : "0");
//				}
				// 送付先送料
				bean.setSoufusakisourou(order[52]);
				// 送付先代引料
				bean.setSoufusakidaibikiryou(order[53]);
				// 送付先消費税
				bean.setSoufusakishouhizei(order[54]);
				// お荷物伝票番号
//				bean.setOnimotudenpyoubango(order.getPackageModel().get(0).getShippingNumber());
				// 送付先商品合計金額
				bean.setSoufusakishouhingokeikingaku(order[55]);
				// のし
				bean.setNoshi(order[57]);
				// 送付先郵便番号１
				bean.setSoufusakiyubinbango1(order[58]);
				// 送付先郵便番号２
				bean.setSoufusakiyubinbango2(order[59]);
				// 送付先住所：都道府県
				bean.setSoufusakijushotodofuken(order[60]);
				// 送付先住所：都市区
				bean.setSoufusakijushotoshiku(order[61]);
				// 送付先住所：町以降
				bean.setSoufusakijushochoijou(order[62]);
				// 送付先名字
				bean.setSofusakimeiji(order[63]);
				// 送付先名前
				bean.setSoufusakinamae(order[64]);
				// 送付先名字フリガナ
				bean.setSoufusakimeijifurigana(order[65]);
				// 送付先名前フリガナ
				bean.setSoufusakimeijinamaefurigana(order[66]);
				// 送付先電話番号１
				bean.setSoufusakidenwabango1(order[67]);
				// 送付先電話番号２
				bean.setSoufusakidenwabango2(order[68]);
				// 送付先電話番号３
				bean.setSoufusakidenwabango3(order[69]);
				// あす楽希望
				bean.setAsurakukibou(order[103]);
				// クーポン利用額
				bean.setKuponriyougaku(order[32]);
				// 店舗発行クーポン利用額
				bean.setTenpohakoukuponriyougaku(order[33]);
				// 楽天発行クーポン利用額
				bean.setRakutenhakoukuponriyougaku(order[34]);
//				// 同梱注文クーポン利用額
//				bean.setDokonchumonkuponrigougaku(Utility.strStringValue(order.getEnclosureCouponPrice()));
				// 配送会社
//				bean.setHaisoukaisha(order.getPackageModel().get(0).getDeliveryCompanyId());
				// 代引手数料込別
				bean.setDaibikitesuryokomibetsu(order[79]);
			}

			if (Utility.isEmptyList(bean.getShousaiList())) {
				bean.setShousaiList(new ArrayList<RakutenDetailCsvBean>());
			}
			RakutenDetailCsvBean detail = new RakutenDetailCsvBean();
			bean.getShousaiList().add(detail);

			// 商品ID
			detail.setShouhinId(order[71]);
			// 商品名
			detail.setShouhinmei(order[72]);
			// 商品番号
			detail.setShouhinbango(order[73]);
			// 商品URL
//			detail.setShouhinURL(item.getPageURL());
			// 単価
			detail.setTanka(order[75]);
			// 個数
			detail.setKosu(order[76]);
			// 送料込別
			detail.setSouryoukomibetsu(order[77]);
			// 税込別
			detail.setZeikomibetsu(order[78]);
			// 代引手数料込別
			detail.setDaibikitesuryoukomibetsu(order[79]);
			// 項目・選択肢
			detail.setKomokusentakushi(order[80]);
			// ポイント倍率
			detail.setPointobairitsu(order[81]);
			// ポイントタイプ
//			detail.setPointotaipu(Utility.strStringValue(item.getPointType()));
			// レコードナンバー
			// detail.setRekodananba(item.getre);
			// 納期情報
			detail.setNokijouho(order[82]);
			// 在庫タイプ
			detail.setZaikotaipu(order[83]);

		}
		return rakutenCsvBeanList;
	}

	public List<String> getMondaiariList(List<CommonOrderBean> commonOrderBeanList) throws Exception {

		List<String> mondaiariList = new ArrayList<String>();
		A1001Common a1001Common = new A1001Common();
		List<F100102> orderList = a1001Common.getOrderInfo();
		for (F100102 order : orderList) {
			if (order.getJuchubango().startsWith("1")) {
				continue;
			}
			boolean ariFlg = false;
			boolean merubinsoryomuryoFlg = false;
			boolean takyubinsoryomuryoFlg = false;
			String keisaihoho = order.getOshiharaihoho();
			String haisohoho = order.getHaisohoho();
			String address = order.getSofusakijoho();
			Long soryou = 0L;
			Long gokeishohin = 0L;
			if (!Utility.isEmptyString(order.getGokeisouryou())) {
				soryou = Long.valueOf(order.getGokeisouryou());
			}
			if (!Utility.isEmptyString(order.getGokeishouhin())) {
				gokeishohin = Long.valueOf(order.getGokeishouhin());
			}

			for (ShohinList shohin : order.getShohinList()) {
				if (shohin.getShouhinmei().contains("メール便送料無料") && shohin.getKomokusentakushi().contains("メール便送料無料")) {
					merubinsoryomuryoFlg = true;
				} else if (shohin.getShouhinmei().contains("送料無料") && shohin.getKomokusentakushi().contains("送料無料")) {
					takyubinsoryomuryoFlg = true;
					merubinsoryomuryoFlg = true;
				}

				if (shohin.getShouhinmei().contains("nzfd-300")) {
					if (!"300".equals(shohin.getTankaku())) {
						ariFlg = true;
					}
				} else if (shohin.getShouhinmei().contains("nzfd-200")) {
					if (!"200".equals(shohin.getTankaku())) {
						ariFlg = true;
					}
				} else if (shohin.getShouhinmei().contains("nzfd-800")) {
					if (!"800".equals(shohin.getTankaku())) {
						ariFlg = true;
					}
				} else if (shohin.getShouhinmei().contains("nzfd-4000")) {
					if (!"4000".equals(shohin.getTankaku())) {
						ariFlg = true;
					}
				}
			}
			if (gokeishohin >= 5480) {
				merubinsoryomuryoFlg = true;
				takyubinsoryomuryoFlg = true;
			}
			// 包邮check
			if ("宅配便".equals(haisohoho)) {
				if (!takyubinsoryomuryoFlg) {
					if (address.contains("沖縄")) {
						if (soryou < 1500) {
							ariFlg = true;
						}
					} else {
						if (soryou < 540) {
							ariFlg = true;
						}
					}
				}
			} else {
				if (!merubinsoryomuryoFlg) {
					if (soryou < 180) {
						ariFlg = true;
					}
				}
			}
			// 代金merubincheck
			if (keisaihoho.contains("代")) {
				if ("メール便".equals(haisohoho)) {
					ariFlg = true;
				}
				if (!takyubinsoryomuryoFlg) {
					if (address.contains("沖縄")) {
						if (soryou < 1500) {
							ariFlg = true;
						}
					} else {
						if (soryou < 540) {
							ariFlg = true;
						}
					}
				}
			}
			if ("306685-20150404-0277783329".equals(order.getJuchubango())
					|| "306685-20150405-0281067135".equals(order.getJuchubango())
					|| "306685-20150405-0292331226".equals(order.getJuchubango())
					|| "306685-20150409-0299371119".equals(order.getJuchubango())
					|| "306685-20150409-0300447123".equals(order.getJuchubango())
					|| "308759-20150405-0290826319".equals(order.getJuchubango())
					|| "308759-20150408-0305520334".equals(order.getJuchubango())
					|| "308759-20150408-0307973317".equals(order.getJuchubango())
					|| "306685-20150402-0263325308".equals(order.getJuchubango())
					|| "306685-20150405-0275196127".equals(order.getJuchubango())
					|| "306685-20150405-0293566222".equals(order.getJuchubango())
					|| "306685-20150409-0307507207".equals(order.getJuchubango())
					|| "306685-20150409-0308508217".equals(order.getJuchubango())
					|| "306685-20150409-0309023331".equals(order.getJuchubango())
					|| "306685-20150411-0308062127".equals(order.getJuchubango())
					|| "306685-20150411-0311310109".equals(order.getJuchubango())
					|| "306685-20150411-0317556217".equals(order.getJuchubango())
					|| "306685-20150411-0319864241".equals(order.getJuchubango())
					|| "306685-20150412-0320429139".equals(order.getJuchubango())
					|| "306685-20150412-0322744111".equals(order.getJuchubango())
					|| "306685-20150412-0326692322".equals(order.getJuchubango())
					|| "306685-20150413-0190224210".equals(order.getJuchubango())
					|| "306685-20150413-0291041202".equals(order.getJuchubango())
					|| "306685-20150413-0324384126".equals(order.getJuchubango())
					|| "306685-20150413-0328310139".equals(order.getJuchubango())
					|| "306685-20150413-0341837337".equals(order.getJuchubango())
					|| "306685-20150413-0342655317".equals(order.getJuchubango())
					|| "306685-20150413-0343884336".equals(order.getJuchubango())
					|| "306685-20150415-0356192314".equals(order.getJuchubango())
					|| "308759-20150409-0306432201".equals(order.getJuchubango())
					|| "308759-20150409-0310249232".equals(order.getJuchubango())
					|| "308759-20150410-0312526316".equals(order.getJuchubango())
					|| "308759-20150410-0316966320".equals(order.getJuchubango())
					|| "308759-20150411-0153086110".equals(order.getJuchubango())
					|| "308759-20150411-0164355310".equals(order.getJuchubango())
					|| "308759-20150411-0317859341".equals(order.getJuchubango())
					|| "308759-20150411-0320201234".equals(order.getJuchubango())
					|| "308759-20150411-0324887218".equals(order.getJuchubango())
					|| "308759-20150412-0316437141".equals(order.getJuchubango())
					|| "308759-20150412-0327162122".equals(order.getJuchubango())
					|| "308759-20150412-0329403337".equals(order.getJuchubango())
					|| "308759-20150412-0330823311".equals(order.getJuchubango())
					|| "308759-20150412-0332625220".equals(order.getJuchubango())
					|| "308759-20150412-0335337215".equals(order.getJuchubango())
					|| "308759-20150412-0336124231".equals(order.getJuchubango())
					|| "308759-20150412-0338498216".equals(order.getJuchubango())
					|| "308759-20150413-0330296135".equals(order.getJuchubango())
					|| "308759-20150413-0331594117".equals(order.getJuchubango())
					|| "308759-20150413-0336691227".equals(order.getJuchubango())
					|| "308759-20150413-0340842334".equals(order.getJuchubango())
					|| "308759-20150414-0337055127".equals(order.getJuchubango())
					|| "308759-20150414-0345372235".equals(order.getJuchubango())
					|| "308759-20150414-0347771228".equals(order.getJuchubango())
					|| "308759-20150415-0354454330".equals(order.getJuchubango())
					|| "306685-20150412-0332204319".equals(order.getJuchubango())
					|| "306685-20150413-0331245139".equals(order.getJuchubango())
					|| "306685-20150413-0332135120".equals(order.getJuchubango())
					|| "306685-20150413-0334776120".equals(order.getJuchubango())
					|| "306685-20150413-0335451227".equals(order.getJuchubango())
					|| "306685-20150414-0333641113".equals(order.getJuchubango())
					|| "306685-20150416-0351750106".equals(order.getJuchubango())
					|| "306685-20150416-0351826139".equals(order.getJuchubango())
					|| "308759-20150410-0308130318".equals(order.getJuchubango())) {
				ariFlg = false;
			}
			if (ariFlg) {
				mondaiariList.add(order.getJuchubango());
			}
		}

		return mondaiariList;

	}
}
