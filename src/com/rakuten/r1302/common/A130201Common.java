package com.rakuten.r1302.common;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.axis.encoding.Base64;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.MessageFromRMS;
import com.rakuten.order.Order;
import com.rakuten.order.OrderShppingInfo;
import com.rakuten.r1302.form.F130201;
import com.rakuten.r1302.form.OrderList;
import com.rakuten.shop.Shop;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

import jp.co.rakuten.rms.mall.order.api.client.AsyncReceiptModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.GetRequestIdResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService_Service;
import jp.co.rakuten.rms.mall.order.api.client.OrderModel;
import jp.co.rakuten.rms.mall.order.api.client.PackageModel;
import jp.co.rakuten.rms.mall.order.api.client.UnitErrorModel;
import jp.co.rakuten.rms.mall.order.api.client.UpdateOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.UserAuthModel;
import shohinmodel.common.Shohincommon;

public class A130201Common {
	public F130201 setDisplay(F130201 f130201) throws Exception {
		if (f130201 == null) {
			f130201 = new F130201();
		}

		List<String> juchubangoList = getHaneimachiList();
		List<OrderList> heneimachiList = getOrderListByBango(juchubangoList);
		List<OrderList> orderList = searchOrder(heneimachiList, f130201);

		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> sessionMap = actionContext.getSession();
		sessionMap.put("heneimachiList", heneimachiList);

		int rakutenCount = 0;
		int yahooShoppingCount = 0;
		int denaCount = 0;
		int yafuokuCount = 0;
		int ponpareCount = 0;
		int qoo10Count = 0;
		String tenposhubetsu = f130201.getTenposhubetsu();

		List<OrderList> shoriList = new ArrayList<OrderList>();
		for (OrderList order : orderList) {
			if ("楽天".equals(order.getSite())) {
				rakutenCount++;
			} else if ("Yahoo Shopping".equals(order.getSite())) {
				yahooShoppingCount++;
			} else if ("DENA".equals(order.getSite())) {
				denaCount++;
			} else if ("ヤフオク".equals(order.getSite())) {
				yafuokuCount++;
			} else if ("ポンパレモール".equals(order.getSite())) {
				ponpareCount++;
			}else if ("qoo10".equals(order.getSite())) {
				qoo10Count++;
			}

			if ("1".equals(tenposhubetsu)) {
				if ("楽天".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
			if ("2".equals(tenposhubetsu)) {
				if ("Yahoo Shopping".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
			if ("3".equals(tenposhubetsu)) {
				if ("DENA".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
			if ("4".equals(tenposhubetsu)) {
				if ("ヤフオク".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
			if ("5".equals(tenposhubetsu)) {
				if ("ポンパレモール".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
			if ("6".equals(tenposhubetsu)) {
				if ("qoo10".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
		}

		f130201.setRakutenCount(String.valueOf(rakutenCount));
		f130201.setYahooShoppingCount(String.valueOf(yahooShoppingCount));
		f130201.setDenaCount(String.valueOf(denaCount));
		f130201.setYafuokuCount(String.valueOf(yafuokuCount));
		f130201.setPonpareCount(String.valueOf(ponpareCount));
		f130201.setQoo10Count(String.valueOf(qoo10Count));
		f130201.setOrderList(shoriList);
		return f130201;
	}

	public List<String> setHaneizumiRakuten_SourceVersion(List<String[]> orderNoList1) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		List<String> messageList = new ArrayList<String>();
		try {

			String result = "";

			conn = JdbcConnection.getConnection();

			if (Utility.isEmptyList(orderNoList1)) {
				messageList.add("没有单号需要反映");
				return messageList;
			}
			for (String[] bango : orderNoList1) {
				result = result + bango[0] + "&";
			}
			List<String[]> dataList = getDenpyoAndKaishaFast(orderNoList1, conn);
			result = result.substring(0, result.length() - 1) + "%%";
			for (String data[] : dataList) {
				result = result + data[0] + "&";
			}
			result = result.substring(0, result.length() - 1) + "%%";
			for (String data[] : dataList) {
				result = result + data[1] + "&";
			}
			result = result.substring(0, result.length() - 1) + "%%";
			for (String data[] : dataList) {
				result = result + data[2] + "&";
			}
			result = result.substring(0, result.length() - 1).replace("&&", "&");

			String[] orderNoList = result.split("%%")[0].split("&");
			String[] expNoList = result.split("%%")[1].split("&");
			String[] kaisha = result.split("%%")[2].split("&");
			String[] shop = result.split("%%")[3].split("&");

			if (orderNoList == null || orderNoList.length == 0) {
				messageList.add("没有单号需要反映");
				return messageList;
			}
			List<String> shopNameList = new ArrayList<String>();
			for (String shopname : shop) {
				boolean ariFlg = false;
				for (String shopnameListname : shopNameList) {
					if (shopname.equals(shopnameListname)) {
						ariFlg = true;
						break;
					}
				}
				if (!ariFlg) {
					shopNameList.add(shopname);
				}
			}

			List<List<String[]>> shopbetsuOrderInfoList = new ArrayList<List<String[]>>();
			for (int i = 0; i < shopNameList.size(); i++) {
				shopbetsuOrderInfoList.add(new ArrayList<String[]>());
				String shopname = shopNameList.get(i);
				for (int j = 0; j < shop.length; j++) {
					if (shopname.equals(shop[j])) {
						shopbetsuOrderInfoList.get(i)
								.add(new String[] { orderNoList[j], expNoList[j], kaisha[j], shop[j] });
					}
				}

			}

			OrderApiService_Service api = new OrderApiService_Service();
			Shohincommon common = new Shohincommon();
			for (List<String[]> shopbetsuOrderInfo : shopbetsuOrderInfoList) {

				String serviceSecret = common.getServiceSecret(shop[0]);
				String licenseKey = common.getLicenseKey(shop[0]);
				String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());

				String shopname = shopbetsuOrderInfo.get(0)[3];
				// ポートを生成します
				OrderApiService port = api.getOrderApiServicePort();
				// 認証モデルを生成します
				UserAuthModel auth = new UserAuthModel();
				// 認証キーをセットします
				auth.setAuthKey(author);
				// 店舗URL をセットします
				auth.setShopUrl(shopname);
				// ユーザー名をセットします
				auth.setUserName("dongtze");
				// レスポンスモデルを生成します
				// UpdateOrderResponse
				// getRequestId を呼出します
				GetOrderRequestModel getmode = new GetOrderRequestModel();

				for (String[] orderinfo : shopbetsuOrderInfo) {
					getmode.getOrderNumber().add(orderinfo[0]);
				}
				List<String> nukuList = new ArrayList<String>();
				GetOrderResponseModel getResponse = port.getOrder(auth, getmode);
				GetRequestIdResponseModel res = port.getRequestId(auth);
				if (!Utility.isEmptyList(res.getUnitError())) {
					List<UnitErrorModel> unitList = res.getUnitError();
					for (UnitErrorModel unit : unitList) {
						messageList.add(unit.getErrorCode() + unit.getMessage() + unit.getOrderKey());
						nukuList.add(unit.getOrderKey());
					}
				}

				int reqId = res.getRequestId();

				UpdateOrderRequestModel updatemodel = new UpdateOrderRequestModel();
				updatemodel.setRequestId(reqId);

				for (OrderModel order : getResponse.getOrderModel()) {
					PackageModel packageModel = order.getPackageModel().get(0);

					for (int i = 0; i < shopbetsuOrderInfo.size(); i++) {
						String orderno = shopbetsuOrderInfo.get(i)[0];
						if (orderno.equals(order.getOrderNumber())) {
							packageModel.setShippingNumber(shopbetsuOrderInfo.get(i)[1]);
							packageModel.setDeliveryCompanyId(shopbetsuOrderInfo.get(i)[2]);
							order.setStatus("発送２");
							GregorianCalendar cal = new GregorianCalendar();
							cal.setTime(new Date());
							order.setShippingDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal));
							updatemodel.getOrderModel().add(order);
							break;
						}
					}

				}

				AsyncReceiptModel response = port.updateOrder(auth, updatemodel);

				// 実行結果を判定します
				if (response.getErrorCode() != null && response.getErrorCode().equals("N00-000")) {

				} else if (response.getErrorCode().equals("W00-000")) {
					List<UnitErrorModel> unitList = response.getUnitError();
					for (UnitErrorModel unit : unitList) {
						messageList.add(unit.getErrorCode() + unit.getMessage() + unit.getOrderKey());
						nukuList.add(unit.getOrderKey());
					}
				} else {
					messageList.add(response.getErrorCode() + response.getMessage());
					return messageList;
				}

				List<String> shorilist = new ArrayList<String>();

				for (String[] orderinfo : shopbetsuOrderInfo) {
					boolean ariflg = false;
					for (String nukuorder : nukuList) {
						if (nukuorder.equals(orderinfo[0])) {
							ariflg = true;
							break;
						}
					}
					if (!ariflg) {
						shorilist.add(orderinfo[0]);
					}
				}
				if (!Utility.isEmptyList(shorilist)) {
					String sql = "UPDATE tbl00024 SET HANEISTS = '2' WHERE CHUMONBANGO = ?";
					for (String orderNo : orderNoList) {
						ps = conn.prepareStatement(sql);
						ps.setString(1, orderNo);
						ps.execute();
					}
				}

			}
			if (Utility.isEmptyList(messageList)) {
				messageList.add("正常终了");
			}
			conn.commit();
			return messageList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();

		}

	}

	public List<String> setHaneizumiRakuten(List<String[]> orderNoList1) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		List<String> messageList = new ArrayList<String>();
		try {

			String result = "";

			conn = JdbcConnection.getConnection();

			if (Utility.isEmptyList(orderNoList1)) {
				messageList.add("没有单号需要反映");
				return messageList;
			}
			for (String[] bango : orderNoList1) {
				result = result + bango[0] + "&";
			}
			List<String[]> dataList = getDenpyoAndKaishaFast(orderNoList1, conn);
			result = result.substring(0, result.length() - 1) + "%%";
			for (String data[] : dataList) {
				result = result + data[0] + "&";
			}
			result = result.substring(0, result.length() - 1) + "%%";
			for (String data[] : dataList) {
				result = result + data[1] + "&";
			}
			result = result.substring(0, result.length() - 1) + "%%";
			for (String data[] : dataList) {
				result = result + data[2] + "&";
			}
			result = result.substring(0, result.length() - 1).replace("&&", "&");

			String[] orderNoList = result.split("%%")[0].split("&");
			String[] expNoList = result.split("%%")[1].split("&");
			String[] kaisha = result.split("%%")[2].split("&");
			String[] shop = result.split("%%")[3].split("&");

			if (orderNoList == null || orderNoList.length == 0) {
				messageList.add("没有单号需要反映");
				return messageList;
			}
			List<String> shopNameList = new ArrayList<String>();
			for (String shopname : shop) {
				boolean ariFlg = false;
				for (String shopnameListname : shopNameList) {
					if (shopname.equals(shopnameListname)) {
						ariFlg = true;
						break;
					}
				}
				if (!ariFlg) {
					shopNameList.add(shopname);
				}
			}

			List<List<String[]>> shopbetsuOrderInfoList = new ArrayList<List<String[]>>();
			for (int i = 0; i < shopNameList.size(); i++) {
				shopbetsuOrderInfoList.add(new ArrayList<String[]>());
				String shopname = shopNameList.get(i);
				for (int j = 0; j < shop.length; j++) {
					if (shopname.equals(shop[j])) {
						shopbetsuOrderInfoList.get(i)
								.add(new String[] { orderNoList[j], expNoList[j], kaisha[j], shop[j] });
					}
				}

			}

			for (List<String[]> shopbetsuOrderInfo : shopbetsuOrderInfoList) {

				String shopname = shopbetsuOrderInfo.get(0)[3];				
				Shop shop_UpdateOrderShipping = new Shop(shopname);
				OrderShppingInfo orderShppingInfo = OrderShppingInfo.convertOrderShppingInfoFromPage(shopbetsuOrderInfo);
				shop_UpdateOrderShipping.updateOrderShipping(orderShppingInfo);
				List<MessageFromRMS> messageFromRMSList = shop_UpdateOrderShipping.getMessageFromRMS_GetOrder();
				List<String> shorilist = new ArrayList<String>();
				Set<String> updatedOrderNoSet = new HashSet<String>();
				for (MessageFromRMS messageFromRMS : messageFromRMSList) {
					if("INFO".equals(messageFromRMS.getMessageType())) {
						
					} else  {
						messageList.add(messageFromRMS.getMessageCode() + messageFromRMS.getMessage() + messageFromRMS.getOrderNumber() == null ? "" : messageFromRMS.getOrderNumber());
					}
				}
				messageFromRMSList = shop_UpdateOrderShipping.getMessageFromRMS_UpdateOrder();
				for (MessageFromRMS messageFromRMS : messageFromRMSList) {
					if("INFO".equals(messageFromRMS.getMessageType())) {
						updatedOrderNoSet.add(messageFromRMS.getOrderNumber());
					} else  {
						messageList.add(messageFromRMS.getMessageCode() + messageFromRMS.getMessage() + messageFromRMS.getOrderNumber());
					}
				}
				shorilist.addAll(updatedOrderNoSet);

				if (!Utility.isEmptyList(shorilist)) {
					String sql = "UPDATE tbl00024 SET HANEISTS = '2' WHERE CHUMONBANGO = ?";
					for (String orderNo : shorilist) {
						ps = conn.prepareStatement(sql);
						ps.setString(1, orderNo);
						ps.execute();
					}
				}

			}
			if (Utility.isEmptyList(messageList)) {
				messageList.add("正常终了");
			}
			conn.commit();
			return messageList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();

		}

	}

	public static List<String[]> getDenpyoAndKaishaFast(List<String[]> juchubangoList, Connection conn)
			throws Exception {
		List<String[]> dataList = new ArrayList<String[]>();

		PreparedStatement ps = null;

		String sql = "SELECT Distinct TOIAWASEBANGO , UNSOKAISHA  FROM hassou_tbl WHERE JUCHUBANGO = ?";
		for (String juchubango[] : juchubangoList) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, juchubango[0]);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dataList.add(new String[] { rs.getString("TOIAWASEBANGO"), rs.getString("UNSOKAISHA"), juchubango[1] });
			}
		}
		return dataList;

	}

	public void setHaneizumi(List<String> orderNoList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "UPDATE tbl00024 SET HANEISTS = '2' WHERE CHUMONBANGO = ?";

			for (String orderNo : orderNoList) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, orderNo);
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
	}

	public List<String> getHaneimachiList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			List<String> haneimachiList = new ArrayList<String>();
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM tbl00024 WHERE HANEISTS = '0'";

			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				haneimachiList.add(rs.getString("CHUMONBANGO"));
			}

			return haneimachiList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public List<String> getHaneimachiList2() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			List<String> haneimachiList = new ArrayList<String>();
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM tbl00024 WHERE HANEISTS = '2'";

			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				haneimachiList.add(rs.getString("CHUMONBANGO"));
			}

			return haneimachiList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public List<String> getHaneimachiList3() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			List<String> haneimachiList = new ArrayList<String>();
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM tbl00024 WHERE HANEISTS = '2' and chumonbango not like '1%'";

			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				haneimachiList.add(rs.getString("CHUMONBANGO"));
			}

			return haneimachiList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	private List<OrderList> searchOrder(List<OrderList> orderList, F130201 f130201) {
		List<OrderList> shoriList = new ArrayList<OrderList>();

		for (OrderList order : orderList) {
			// 检查店铺名
			if (!Utility.isEmptyString(f130201.getTenpo())) {
				if (!f130201.getTenpo().equals(order.getTenpo())) {
					continue;
				}
			}
			// 订单时间
			if (!Utility.isEmptyString(f130201.getKikanStart())) {
				if (Integer.valueOf(f130201.getKikanStart().replace("-", "")) > Integer
						.valueOf(order.getChumonichiji().substring(0, 10).replace("-", ""))) {
					continue;
				}
			}
			if (!Utility.isEmptyString(f130201.getKikanEnd())) {
				if (Integer.valueOf(f130201.getKikanEnd().replace("-", "")) < Integer
						.valueOf(order.getChumonichiji().substring(0, 10).replace("-", ""))) {
					continue;
				}
			}
			// 受注番号
			if (!Utility.isEmptyString(f130201.getChumonbango())) {
				if (!f130201.getChumonbango().trim().equals(order.getChumonbango())) {
					continue;
				}
			}
			// 送付先名前
			if (!Utility.isEmptyString(f130201.getSofusakinamae())) {
				if (!order.getSofusakinamae().replace("　", " ")
						.contains(f130201.getSofusakinamae().replace("　", " ").trim())) {
					continue;
				}
			}
			// 送付先電話番号
			if (!Utility.isEmptyString(f130201.getSofusakidenwabango())) {
				if (!f130201.getSofusakidenwabango().replace("-", "").trim()
						.equals(order.getSofusakidenwabango().replace("-", ""))) {
					continue;
				}
			}
			// お支払い方法
			if (!Utility.isEmptyString(f130201.getOshiharaihoho())) {
				if (!f130201.getOshiharaihoho().equals(order.getOshiharaihoho())) {
					continue;
				}
			}

			// 配送方法
			if (!Utility.isEmptyString(f130201.getHaisohoho())) {
				if (!f130201.getHaisohoho().equals(order.getHaisohoho())) {
					continue;
				}
			}

			shoriList.add(order);
		}

		return shoriList;
	}

	public List<OrderList> getOrderListByBango(List<String> juchubangoList) throws Exception {
		if (Utility.isEmptyList(juchubangoList)) {
			return new ArrayList<OrderList>();
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			List<OrderList> orderList = new ArrayList<OrderList>();
			OrderList Order = null;
			conn = JdbcConnection.getConnection();
			String sql = "";
			for (String juchubango : juchubangoList) {
				sql = "SELECT * FROM common_order_tbl WHERE CHUMONBANGO = ? ORDER BY str_to_date(CHUMONNICHIJI,'%Y-%m-%d %H:%i:%s') DESC";
				ps = conn.prepareStatement(sql);
				ps.setString(1, juchubango);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Order = new OrderList();
					orderList.add(Order);
					Order.setSite(rs.getString("SITE"));
					Order.setTenpo(rs.getString("SHOP"));
					Order.setChumonbango(rs.getString("CHUMONBANGO"));
					Order.setOshiharaihoho(rs.getString("OSHIHARAISTS"));
					Order.setGokeikingaku(Utility.numberFormat(rs.getString("GOKEISHOHIN")));
					Order.setSeikyujingaku(Utility.numberFormat(rs.getString("SEIKYUKINGAKU")));
					Order.setHaisohoho(rs.getString("HAISOUHOHO"));
					Order.setBiko(rs.getString("BIKO"));
					Order.setChumonichiji(rs.getString("CHUMONNICHIJI"));
					Order.setComento(rs.getString("KOMENTO"));
					Order.setSofusakinamae(rs.getString("SOFUSAKIMEIJI") + rs.getString("SOUFUSAKINAMAE") + "<br>["
							+ (Utility.isEmptyString(rs.getString("SOUFUSAKIMEIJIFURIGANA")) ? ""
									: rs.getString("SOUFUSAKIMEIJIFURIGANA"))
							+ " " + (Utility.isEmptyString(rs.getString("SOUFUSAKIMEIJINAMAEFURIGANA")) ? ""
									: rs.getString("SOUFUSAKIMEIJINAMAEFURIGANA"))
							+ "]");
					Order.setSofusakidenwabango(rs.getString("SOFUSAKIDENWABANGO1")
							+ rs.getString("SOFUSAKIDENWABANGO2") + rs.getString("SOFUSAKIDENWABANGO3"));

					sql = "SELECT * FROM hassou_tbl WHERE JUCHUBANGO = ?";

					ps = conn.prepareStatement(sql);
					ps.setString(1, juchubango);
					ResultSet rs2 = ps.executeQuery();
					while (rs2.next()) {
						Order.setDenpyobango(rs2.getString("TOIAWASEBANGO"));
						Order.setHaisokaisha(rs2.getString("UNSOKAISHA"));
					}

					break;
				}
			}
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public List<OrderList> getOrderListByBangoFast(List<String> juchubangoList, Connection conn) throws Exception {
		if (Utility.isEmptyList(juchubangoList)) {
			return new ArrayList<OrderList>();
		}

		PreparedStatement ps = null;

		List<OrderList> orderList = new ArrayList<OrderList>();
		OrderList Order = null;

		String sql = "";
		for (String juchubango : juchubangoList) {
			sql = "SELECT * FROM common_order_tbl WHERE CHUMONBANGO = ? ORDER BY str_to_date(CHUMONNICHIJI,'%Y-%m-%d %H:%i:%s') DESC";
			ps = conn.prepareStatement(sql);
			ps.setString(1, juchubango);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Order = new OrderList();
				orderList.add(Order);
				Order.setSite(rs.getString("SITE"));
				Order.setTenpo(rs.getString("SHOP"));
				Order.setChumonbango(rs.getString("CHUMONBANGO"));
				Order.setOshiharaihoho(rs.getString("OSHIHARAISTS"));
				Order.setGokeikingaku(Utility.numberFormat(rs.getString("GOKEISHOHIN")));
				Order.setSeikyujingaku(Utility.numberFormat(rs.getString("SEIKYUKINGAKU")));
				Order.setHaisohoho(rs.getString("HAISOUHOHO"));
				Order.setBiko(rs.getString("BIKO"));
				Order.setChumonichiji(rs.getString("CHUMONNICHIJI"));
				Order.setComento(rs.getString("KOMENTO"));
				Order.setSofusakinamae(rs.getString("SOFUSAKIMEIJI") + rs.getString("SOUFUSAKINAMAE") + "<br>["
						+ (Utility.isEmptyString(rs.getString("SOUFUSAKIMEIJIFURIGANA")) ? ""
								: rs.getString("SOUFUSAKIMEIJIFURIGANA"))
						+ " " + (Utility.isEmptyString(rs.getString("SOUFUSAKIMEIJINAMAEFURIGANA")) ? ""
								: rs.getString("SOUFUSAKIMEIJINAMAEFURIGANA"))
						+ "]");
				Order.setSofusakidenwabango(rs.getString("SOFUSAKIDENWABANGO1") + rs.getString("SOFUSAKIDENWABANGO2")
						+ rs.getString("SOFUSAKIDENWABANGO3"));

				sql = "SELECT * FROM hassou_tbl WHERE JUCHUBANGO = ?";

				ps = conn.prepareStatement(sql);
				ps.setString(1, juchubango);
				ResultSet rs2 = ps.executeQuery();
				while (rs2.next()) {
					Order.setDenpyobango(rs2.getString("TOIAWASEBANGO"));
					Order.setHaisokaisha(rs2.getString("UNSOKAISHA"));
				}

				break;
			}
		}
		return orderList;

	}

	public List<String> setRakutenHaneizumi(List<String> shoriList, String username1, String username2,
			String password1, String password2, String password3, String logKey) throws Exception {
		List<String> msgList = new ArrayList<String>();

		Utility.addLog("ログイン中。。。。。。。。。。", logKey);
		WebClient webClient = new WebClient(BrowserVersion.INTERNET_EXPLORER_10);

		// 1.Login
		WebClientOptions options = webClient.getOptions();
		// options.setJavaScriptEnabled(false);
		webClient.setAjaxController(new NicelyResynchronizingAjaxController());
		webClient.getOptions().setJavaScriptEnabled(true);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setTimeout(35000);
		webClient.getOptions().setThrowExceptionOnScriptError(false);

		options.setJavaScriptEnabled(true);
		options.setCssEnabled(true);
		HtmlPage page = webClient.getPage("https://glogin.rms.rakuten.co.jp/?sp_id=1");
		// 2.获取页面上的表单
		HtmlForm form = page.getForms().get(0);
		// 3.获取页面上的各个元素
		HtmlTextInput login_id = form.getInputByName("login_id");
		HtmlPasswordInput login_pass = form.getInputByName("passwd");
		// 4.给元素赋值
		login_id.setValueAttribute(username1);
		login_pass.setValueAttribute(password1);
		// 5.提交
		HtmlSubmitInput submintbtn = (HtmlSubmitInput) form.getElementsByTagName("input").get(5);
		page = (HtmlPage) submintbtn.click();
		if (page.asXml().contains("R-Loginパスワードに誤りがあるか、このR-Login IDは登録されていません。")) {

			form = page.getForms().get(0);
			login_id = form.getInputByName("login_id");
			login_pass = form.getInputByName("passwd");
			login_id.setValueAttribute(username1);
			login_pass.setValueAttribute(password2);

			submintbtn = (HtmlSubmitInput) form.getElementsByTagName("input").get(5);
			page = (HtmlPage) submintbtn.click();
		}

		form = page.getForms().get(0);
		login_id = form.getInputByName("user_id");
		login_pass = form.getInputByName("user_passwd");
		login_id.setValueAttribute(username2);
		login_pass.setValueAttribute(password3);

		submintbtn = (HtmlSubmitInput) form.getElementsByTagName("input").get(7);
		page = (HtmlPage) submintbtn.click();

		form = page.getForms().get(0);
		if (form.asXml().contains("以下の内容を確認したら、画面下部の「次へ」を押してください。")) {
			submintbtn = (HtmlSubmitInput) form.getElementsByTagName("input").get(3);
			page = (HtmlPage) submintbtn.click();
		} else {
			msgList.add("ログイン失敗しました!");
			throw new Exception();
		}

		form = page.getForms().get(0);
		if (form.asXml().contains("上記を遵守していることを確認の上、RMSを利用します")) {
			submintbtn = (HtmlSubmitInput) form.getElementsByTagName("input").get(0);
			page = (HtmlPage) submintbtn.click();
		} else {
			msgList.add("ログイン失敗しました!");
			throw new Exception();
		}

		page = webClient.getPage("https://mainmenu.rms.rakuten.co.jp/?menu_type=0");

		Utility.addLog("注文一覧を開く。。。。。", logKey);

		for (int i = 0; i < shoriList.size(); i++) {
			String orderNo = shoriList.get(i);
			Utility.addLog((i + 1) + "/" + shoriList.size(), logKey);
			Utility.addLog(orderNo, logKey);
			try {

				page = webClient.getPage(
						"https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
								+ orderNo);
				System.out.println(page.asText());
				HtmlSelect stsSelect = (HtmlSelect) page.getElementsByName("status_id_" + orderNo).get(0);
				HtmlOption stsOption = stsSelect.getOptionByText("発送１");
				stsSelect.setSelectedAttribute(stsOption, true);

				HtmlSubmitInput haneisubmit = (HtmlSubmitInput) page.getElementsByName("B016").get(0);
				page = haneisubmit.click();
				if (page.asXml().contains("受注ステータス変更、ポイント承認、メモ入力などの入力内容を反映しました。")) {
					setHaneizumi(Collections.singletonList(orderNo));
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				msgList.add(orderNo + "処理し失敗しました!");
			}
		}

		return msgList;

	}

	public static void write(String filePathAndName, String fileContent) {
		try {
			File f = new File(filePathAndName);
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			e.printStackTrace();
		}
	}
}
