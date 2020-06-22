package com.rakuten.r1001.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimerTask;

import javax.xml.datatype.DatatypeFactory;

import org.apache.axis.encoding.Base64;

import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

import jp.co.rakuten.rms.mall.order.api.client.GetOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.GetRequestIdResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService_Service;
import jp.co.rakuten.rms.mall.order.api.client.OrderModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderSearchModel;
import jp.co.rakuten.rms.mall.order.api.client.UpdateOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.UserAuthModel;
import shohinmodel.common.Shohincommon;

public class GetOrderExpection extends TimerTask {

	/**
	 * 　ReceiveEmail类测试
	 */
	public void excute(String shop) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		ResultSet rs2 = null;
		List<String> exceptionOrderList = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();

			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			int i = 0;
			while (true) {
				Calendar date = Calendar.getInstance();
				date.add(Calendar.DATE, -62 * i);
				String endDay = sdf.format(date.getTime());
				date.add(Calendar.DATE, -62);
				String startDay = sdf.format(date.getTime());
				startDay = sdf.format(date.getTime());
				GetOrderResponseModel getResponse = getOrder(shop, startDay,
						endDay);
				if (!"正常終了".equals(getResponse.getMessage())) {
					break;
				}
				i++;
				for (OrderModel order : getResponse.getOrderModel()) {
					sql = "SELECT chumonsts1 from common_order_tbl where chumonbango = ? ";
					ps = conn.prepareStatement(sql);
					ps.setString(1, order.getOrderNumber());
					rs = ps.executeQuery();
					if (rs.next()) {
						String chumonsts1 = rs.getString("chumonsts1");
						if (!"2".equals(chumonsts1)) {
							sql = "SELECT haneists FROM rakuten.tbl00024 where chumonbango = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, order.getOrderNumber());
							rs2 = ps.executeQuery();
							if (rs2.next()) {
								if (!"0".equals(rs2.getString("haneists"))) {
									exceptionOrderList.add(order
											.getOrderNumber());
								}
							} else {
								exceptionOrderList.add(order.getOrderNumber());
							}
						}
					}
				}
			}
			List<String> shoriList = new ArrayList<String>();
			for (String orderNo : exceptionOrderList) {
				boolean ariFlg = false;
				for (String shoribango : shoriList) {
					if (shoribango.equals(orderNo)) {
						ariFlg = true;
					}
				}
				if (!ariFlg) {
					shoriList.add(orderNo);
				}
			}
			sql = "delete from tbl00031 where chumonbango like ?";
			ps = conn.prepareStatement(sql);
			if ("trend777".equals(shop)) {
				ps.setString(1, "306%");
			} else if ("coverforefront".equals(shop)) {
				ps.setString(1, "308%");
			}
			ps.execute();

			if (!Utility.isEmptyList(shoriList)) {
				sql = "insert into tbl00031 values(?)";
				ps = conn.prepareStatement(sql);
				for (String orderNo : shoriList) {
					ps.setString(1, orderNo);
					ps.execute();
				}
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	public void setOrderStatus(String shop) throws Exception {
		List<String> bangoList = getHasomachiYoteibiariList(shop);
		OrderCommon orderCommon = new OrderCommon();
		OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean
				.getShouhinStsBeanList();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean
				.getCommonOrderBeanList();
		List<String[]> shoriList = new ArrayList<String[]>();
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

			List<String[]> hasomachiArr = orderCommon.getMachiListAll(
					thisOrder, shouhinStsBeanList, "2", "7");

			for (String[] hasomachi : hasomachiArr) {
				if ("入荷待ち".equals(hasomachi[2])
						&& !orderCommon.isNyukakano(hasomachi[0])) {
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
			String stsName = "";
			if (-10 == nowSts) {
				stsName = "入荷不可";
			} else if (-7 == nowSts) {
				stsName = "入荷待ち";
			} else if (-5 == nowSts) {
				stsName = "入荷中";
			} else if (-3 == nowSts) {
				stsName = "上海在庫";
			} else if (-1 == nowSts) {
				stsName = "運送中";
			} else if (1 == nowSts) {
				stsName = "発送待ち";
			}
			shoriList.add(new String[] { orderNo, stsName });
		}
		OrderApiService_Service api = new OrderApiService_Service();
		// ポートを生成します
		OrderApiService port = api.getOrderApiServicePort();
		// 認証モデルを生成します
		UserAuthModel auth = new UserAuthModel();
		// 認証キーをセットします
		auth.setAuthKey(Utility.getApiKey(shop));
		// 店舗URL をセットします
		auth.setShopUrl(shop);
		// ユーザー名をセットします
		auth.setUserName("dongtze");
		// レスポンスモデルを生成します
		// UpdateOrderResponse
		// getRequestId を呼出します
		GetOrderRequestModel getmode = new GetOrderRequestModel();
		for (String[] bango : shoriList) {
			getmode.getOrderNumber().add(bango[0]);
		}

		GetOrderResponseModel getResponse = port.getOrder(auth, getmode);
		GetRequestIdResponseModel res = port.getRequestId(auth);

		int reqId = res.getRequestId();

		UpdateOrderRequestModel updatemodel = new UpdateOrderRequestModel();
		updatemodel.setRequestId(reqId);

		for (OrderModel order : getResponse.getOrderModel()) {
			for (String[] bango : shoriList) {
				if (bango[0].equals(order.getOrderNumber())) {

					if ("入荷不可".equals(bango[1]) || "入荷待ち".equals(bango[1])) {
						if ("入荷待ち".equals(order.getStatus())) {
							continue;
						} else {
							order.setStatus("入荷待ち");
							updatemodel.getOrderModel().add(order);
						}
					} else if ("入荷中".equals(bango[1])) {
						if ("入荷中".equals(order.getStatus())
								|| "入荷中連絡済 ".equals(order.getStatus())) {
							continue;
						} else {
							order.setStatus("入荷中");
							updatemodel.getOrderModel().add(order);
						}
					} else if ("運送中".equals(bango[1])) {
						if ("運送中".equals(order.getStatus())
								|| "運送中連絡済 ".equals(order.getStatus())) {
							continue;
						} else {
							order.setStatus("運送中");
							updatemodel.getOrderModel().add(order);
						}
					} else if ("発送待ち".equals(bango[1])) {
						if ("発送可".equals(order.getStatus())) {
							continue;
						} else {
							order.setStatus("発送可");
							updatemodel.getOrderModel().add(order);
						}
					}
				}
			}

		}
		port.updateOrder(auth, updatemodel);

	}

	public List<String> getHasomachiYoteibiariList(String shop)
			throws Exception {
		List<String> bangoList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT t1.chumonbango bangot1 from common_order_tbl t1 left join tbl00027 t2 "
					+ "on t1.chumonbango = t2.chumonbango where HASOYAKUSOKUBI<> '' and t1.chumonsts1 = '2' and t1.shop = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shop);
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

	@Override
	public void run() {
		try {
			excute("trend777");
			excute("coverforefront");
			// setOrderStatus("trend777");
			// setOrderStatus("coverforefront");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private GetOrderResponseModel getOrder(String shop, String startDay,
			String endDay) throws Exception {
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

		getmode.getOrderSearchModel().setStartDate(
				DatatypeFactory.newInstance().newXMLGregorianCalendar(
						Integer.valueOf(startDay.substring(0, 4)),
						Integer.valueOf(startDay.substring(4, 6)),
						Integer.valueOf(startDay.substring(6, 8)), 0, 0, 0,
						000, 0));
		getmode.getOrderSearchModel().setEndDate(
				DatatypeFactory.newInstance().newXMLGregorianCalendar(
						Integer.valueOf(endDay.substring(0, 4)),
						Integer.valueOf(endDay.substring(4, 6)),
						Integer.valueOf(endDay.substring(6, 8)), 23, 59, 59,
						999, 0));
		orderSearchModel.getStatus().add("発送待ち");
		GetOrderResponseModel getResponse = port.getOrder(auth, getmode);

		return getResponse;
	}
}
