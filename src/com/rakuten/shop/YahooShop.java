package com.rakuten.shop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.rakuten.common.MessageFromYahoo;
import com.rakuten.order.OrderShppingInfo;
import com.rakuten.order.YahooOrder;
import com.rakuten.util.GetTokenFromYahoo;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;
import com.rakuten.util.XmlUtils;

public class YahooShop {

	private final static String URL_GETORDERLIST = "https://circus.shopping.yahooapis.jp/ShoppingWebService/V1/orderList";
	private final static String URL_GETORDERINFO = "https://circus.shopping.yahooapis.jp/ShoppingWebService/V1/orderInfo";
	private final static String URL_ORDERCHANGE = "https://circus.shopping.yahooapis.jp/ShoppingWebService/V1/orderChange";
	private final static String URL_ORDERSHIPSTATUSCHANGE = "https://circus.shopping.yahooapis.jp/ShoppingWebService/V1/orderShipStatusChange";
	private final static String URL_SETSTOCK = "https://circus.shopping.yahooapis.jp/ShoppingWebService/V1/setStock";
	private final static int DATE_INTERVAL = -62;
	private final static int TOKEN_VALIDTIMEINTERVAL = -12;
	private final static int TOKEN_VALIDTIMEINTERVAL_ONEHOUR = -1;

	private String shopName;
	private String sellerId;
	private String clientId;
	private String accessToken;
	private String refreshToken;
	private Timestamp loginTime;
	private List<YahooOrder> orders = new ArrayList<YahooOrder>();
	private List<String> orderNoList;
	private List<MessageFromYahoo> messageFromYahooList_GetOrder = new ArrayList<MessageFromYahoo>();
	private List<MessageFromYahoo> messageFromYahooList_UpdateOrder = new ArrayList<MessageFromYahoo>();

	public YahooShop(String shopName) {
		this.shopName = shopName;
	}

	public List<YahooOrder> getOrders(int orderStatus, int payStatus, int shipStatus, boolean isSeen) throws Exception {

		messageFromYahooList_GetOrder.clear();
		orders.clear();
		searchOrder(orderStatus, payStatus, shipStatus, isSeen);
		if ((null == orderNoList) || (orderNoList.size() == 0)) {

		} else {
			getOrder();
		}
		return orders;
	}

	private void searchOrder(int orderStatus, int payStatus, int shipStatus, boolean isSeen) throws Exception {
		orderNoList = new ArrayList<String>();
		if (tokenIsValid(TOKEN_VALIDTIMEINTERVAL)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Calendar date = Calendar.getInstance();
			String endDay = sdf.format(date.getTime());
			date.add(Calendar.DATE, DATE_INTERVAL);
			String startDay = sdf.format(date.getTime());

			StringBuilder getXml = new StringBuilder();
			getXml.append("<Req>");
			getXml.append("<Search>");
			getXml.append("<Result>1000</Result>");
			getXml.append("<Condition>");
			getXml.append("<OrderTimeFrom>");
			getXml.append(startDay);
			getXml.append("</OrderTimeFrom>");
			getXml.append("<OrderTimeTo>");
			getXml.append(endDay);
			getXml.append("</OrderTimeTo>");
			getXml.append("<IsSeen>");
			getXml.append(isSeen);
			getXml.append("</IsSeen>");
			getXml.append("<OrderStatus>");
			getXml.append(orderStatus);
			getXml.append("</OrderStatus>");
			getXml.append("<PayStatus>");
			getXml.append(payStatus);
			getXml.append("</PayStatus>");
			getXml.append("<ShipStatus>");
			getXml.append(shipStatus);
			getXml.append("</ShipStatus>");
			getXml.append("</Condition>");
			getXml.append("<Field>OrderId</Field>");
			getXml.append("</Search>");
			getXml.append("<SellerId>");
			getXml.append(getSellerId());
			getXml.append("</SellerId>");
			getXml.append("</Req>");

			URL url = new URL(URL_GETORDERLIST);
			String xml_GetOrderResult = getXml(shopName, url, getXml.toString());
			Map<String, Object> xmlMap = XmlUtils.Dom2Map(xml_GetOrderResult);

			Map<String, Object> searchMap = (Map<String, Object>) xmlMap.get("Search");
			if (null == searchMap) {
				if (xmlMap.get("Message") != null && xmlMap.get("Code") != null) {
					MessageFromYahoo messageFromYahoo = new MessageFromYahoo();
					messageFromYahoo.setCode((String) xmlMap.get("Code"));
					messageFromYahoo.setMessage((String) xmlMap.get("Message"));
					messageFromYahooList_GetOrder.add(messageFromYahoo);
				}
			} else {
				List<Object> orderInfoList = (List<Object>) searchMap.get("OrderInfo");
				if (null == orderInfoList || 0 == orderInfoList.size()) {

				} else {
					for (Object orderInfo : orderInfoList) {
						orderNoList.add((String) ((Map<String, Object>) orderInfo).get("OrderId"));
					}
				}
			}
		} else {
			messageFromYahooList_GetOrder.clear();
			MessageFromYahoo messageFromYahoo = new MessageFromYahoo();
			messageFromYahoo.setCode("");
			messageFromYahoo.setMessage("YConnectServletに再度ログインして、Yahooアクセストークンを取得してください");
			messageFromYahooList_GetOrder.add(messageFromYahoo);
		}

	}

	private void getOrder() throws Exception {
		StringBuilder getXml;
		for (String orderNo : orderNoList) {
			if (tokenIsValid(TOKEN_VALIDTIMEINTERVAL)) {
				getXml = new StringBuilder();
				getXml.append("<Req>");
				getXml.append("<Target>");
				getXml.append("<OrderId>");
				getXml.append(orderNo);
				getXml.append("</OrderId>");
				getXml.append("<Field>");
				getXml.append(
						"OrderTime,PayMethodName,UsePoint,BillLastName,BillFirstName,BillLastNameKana,BillFirstNameKana,BillMailAddress,BillZipCode,BillPrefecture,BillCity,BillAddress1,BillAddress2,BillPhoneNumber,BuyerComments,ShipLastName,ShipFirstName,ShipLastNameKana,ShipFirstNameKana,ShipMethodName,ShipZipCode,ShipPhoneNumber,ShipPrefecture,ShipCity,ShipAddress1,ShipAddress2,SettleAmount,ItemTaxRatio,ShipCharge,PayCharge,TotalPrice,TotalCouponDiscount,Title,ItemId,SubCode,UnitPrice,Quantity");
				getXml.append("</Field>");
				getXml.append("</Target>");
				getXml.append("<SellerId>");
				getXml.append(getSellerId());
				getXml.append("</SellerId>");
				getXml.append("</Req>");

				URL url = new URL(URL_GETORDERINFO);
				String xml_GetOrderResult = getXml(shopName, url, getXml.toString());
				Map<String, Object> xmlMap = XmlUtils.Dom2Map(xml_GetOrderResult);

				Map<String, Object> resultMap = (Map<String, Object>) xmlMap.get("Result");
				if (null == resultMap) {
					if (xmlMap.get("Message") != null && xmlMap.get("Code") != null) {
						MessageFromYahoo messageFromYahoo = new MessageFromYahoo();
						messageFromYahoo.setCode((String) xmlMap.get("Code"));
						messageFromYahoo.setMessage((String) xmlMap.get("Message"));
						messageFromYahoo.setOrderId(orderNo);
						messageFromYahooList_GetOrder.add(messageFromYahoo);
					}
				} else {
					Map<String, Object> orderInfo = (Map<String, Object>) resultMap.get("OrderInfo");
					if (null == orderInfo) {

					} else {
						YahooOrder order = new YahooOrder();
						order.setOrderId(orderNo);
						order.setOrderTime((String) orderInfo.get("OrderTime"));
						YahooOrder.Pay pay = order.new Pay();
						Map<String, Object> payMap = (Map<String, Object>) orderInfo.get("Pay");
						pay.setPayMethodName((String) payMap.get("PayMethodName"));
						Map<String, Object> detailMap = (Map<String, Object>) orderInfo.get("Detail");
						YahooOrder.Detail detail = order.new Detail();
						detail.setUsePoint(Integer.parseInt((String) detailMap.get("UsePoint")));
						detail.setSettleAmount(Integer.parseInt((String) detailMap.get("SettleAmount")));
						detail.setShipCharge(Integer.parseInt((String) detailMap.get("ShipCharge")));
						detail.setPayCharge(Integer.parseInt((String) detailMap.get("PayCharge")));
						detail.setTotalPrice(Integer.parseInt((String) detailMap.get("TotalPrice")));
						order.setDetail(detail);
						pay.setBillLastName((String) payMap.get("BillLastName"));
						pay.setBillFirstName((String) payMap.get("BillFirstName"));
						pay.setBillLastNameKana((String) payMap.get("BillLastNameKana"));
						pay.setBillFirstNameKana((String) payMap.get("BillFirstNameKana"));
						pay.setBillMailAddress((String) payMap.get("BillMailAddress"));
//					String zipCode = (String)payMap.get("BillZipCode");
//					ordererModel.setZipCode1((zipCode == null || "".equals(zipCode))?"":zipCode.substring(0, zipCode.length()-4));
//					ordererModel.setZipCode2((zipCode == null || "".equals(zipCode))?"":zipCode.substring(zipCode.length()-4, zipCode.length()));
						pay.setBillZipCode((String) payMap.get("BillZipCode"));
						pay.setBillPrefecture((String) payMap.get("BillPrefecture"));
						pay.setBillCity((String) payMap.get("BillCity"));
//					ordererModel.setSubAddress((String)pay.get("BillAddress1")+(String)pay.get("BillAddress2"));
						pay.setBillAddress1((String) payMap.get("BillAddress1"));
						pay.setBillAddress2((String) payMap.get("BillAddress2"));
//					String phoneNumber = (String)pay.get("BillPhoneNumber");
//					phoneNumber = (phoneNumber == null || "".equals(phoneNumber))?"":phoneNumber.replace("-", "");
//					ordererModel.setPhoneNumber1("".equals(phoneNumber)?"":phoneNumber.substring(0,phoneNumber.length()-8));
//					ordererModel.setPhoneNumber2("".equals(phoneNumber)?"":phoneNumber.substring(phoneNumber.length()-8, phoneNumber.length()-4));
//					ordererModel.setPhoneNumber3("".equals(phoneNumber)?"":phoneNumber.substring(phoneNumber.length()-4, phoneNumber.length()));
						pay.setBillPhoneNumber((String) payMap.get("BillPhoneNumber"));
						order.setPay(pay);
						order.setBuyerComments((String) orderInfo.get("BuyerComments"));
						Map<String, Object> shipMap = (Map<String, Object>) orderInfo.get("Ship");
						YahooOrder.Ship ship = order.new Ship();
						ship.setShipLastName((String) shipMap.get("ShipLastName"));
						ship.setShipFirstName((String) shipMap.get("ShipFirstName"));
						ship.setShipLastNameKana((String) shipMap.get("ShipLastNameKana"));
						ship.setShipFirstNameKana((String) shipMap.get("ShipFirstNameKana"));
						ship.setShipMethodName((String) shipMap.get("ShipMethodName"));
						ship.setShipZipCode((String) shipMap.get("ShipZipCode"));
						ship.setShipPhoneNumber((String) shipMap.get("ShipPhoneNumber"));
						ship.setShipPrefecture((String) shipMap.get("ShipPrefecture"));
						ship.setShipCity((String) shipMap.get("ShipCity"));
						ship.setShipAddress1((String) shipMap.get("ShipAddress1"));
						ship.setShipAddress2((String) shipMap.get("ShipAddress2"));
						order.setShip(ship);
						if (null == orderInfo.get("Item")) {
							order.setItemList(new ArrayList<YahooOrder.Item>());
						} else {
							List<YahooOrder.Item> itemList = new ArrayList<YahooOrder.Item>();
							List<Map<String, Object>> itemMapList;
							if (orderInfo.get("Item") instanceof List) {
								itemMapList = (List<Map<String, Object>>) orderInfo.get("Item");
							} else {
								itemMapList = new ArrayList<Map<String, Object>>();
								itemMapList.add((Map<String, Object>) orderInfo.get("Item"));
							}
							String subCode;
							for (Map<String, Object> itemMap : itemMapList) {
								YahooOrder.Item item = order.new Item();
								item.setItemTaxRatio(Integer.parseInt((String) itemMap.get("ItemTaxRatio")));
								item.setTitle((String) itemMap.get("Title"));
								subCode = (String) itemMap.get("SubCode");
								item.setItemId((subCode == null || "".equals(subCode) ? (String) itemMap.get("ItemId") : subCode));
								item.setUnitPrice(Integer.parseInt((String) itemMap.get("UnitPrice")));
								item.setQuantity(Integer.parseInt((String) itemMap.get("Quantity")));
								itemList.add(item);
							}
							order.setItemList(itemList);
						}
						order.setTotalCouponDiscount(
								Integer.parseInt(((String) orderInfo.get("TotalCouponDiscount")).equals("") ? "0"
										: (String) orderInfo.get("TotalCouponDiscount")));
						orders.add(order);
					}
				}
			} else {
				messageFromYahooList_GetOrder.clear();
				MessageFromYahoo messageFromYahoo = new MessageFromYahoo();
				messageFromYahoo.setCode("");
				messageFromYahoo.setMessage("YConnectServletに再度ログインして、Yahooアクセストークンを取得してください");
				messageFromYahooList_GetOrder.add(messageFromYahoo);
				break;
			}
		}
	}

	public List<String> updateOrderShipping(OrderShppingInfo orderShppingInfo) throws Exception {

		messageFromYahooList_UpdateOrder.clear();
		List<String> updatedOrderNoList = new ArrayList<String>();
		List<String> orderNoList = orderShppingInfo.getOrderNoList();
		Map<String, String[]> orderShppingInfoMap = orderShppingInfo.getOrderShppingInfo();
		if ((null == orderNoList) || (orderNoList.size() == 0)) {

		} else {
			String[] strArr;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar date = Calendar.getInstance();
			String shippingDate = sdf.format(date.getTime());
			StringBuilder updateXml;
			for (String orderNo : orderNoList) {
				if (tokenIsValid(TOKEN_VALIDTIMEINTERVAL)) {
					strArr = orderShppingInfoMap.get(orderNo);
					updateXml = new StringBuilder();
					updateXml.append("<Req>");
					updateXml.append("<Target>");
					updateXml.append("<OrderId>");
					updateXml.append(orderNo);
					updateXml.append("</OrderId>");
					updateXml.append("<IsPointFix>");
					updateXml.append(true);
					updateXml.append("</IsPointFix>");
					updateXml.append("</Target>");
					updateXml.append("<Order>");
					updateXml.append("<Ship>");
					updateXml.append("<ShipStatus>");
					updateXml.append(3);
					updateXml.append("</ShipStatus>");
					updateXml.append("<ShipCompanyCode>");
					updateXml.append(strArr[1]);
					updateXml.append("</ShipCompanyCode>");
					updateXml.append("<ShipInvoiceNumber1>");
					updateXml.append(strArr[0]);
					updateXml.append("</ShipInvoiceNumber1>");
					updateXml.append("<ShipDate>");
					updateXml.append(shippingDate);
					updateXml.append("</ShipDate>");
					updateXml.append("</Ship>");
					updateXml.append("</Order>");
					updateXml.append("<SellerId>");
					updateXml.append(getSellerId());
					updateXml.append("</SellerId>");
					updateXml.append("</Req>");

					URL url = new URL(URL_ORDERSHIPSTATUSCHANGE);
					try {
						String xml_GetOrderResult = getXml(shopName, url, updateXml.toString());
						Map<String, Object> xmlMap = XmlUtils.Dom2Map(xml_GetOrderResult);

						Map<String, Object> resultMap = (Map<String, Object>) xmlMap.get("Result");
						if (null == resultMap) {
							if (xmlMap.get("Message") != null && xmlMap.get("Code") != null) {
								MessageFromYahoo messageFromYahoo = new MessageFromYahoo();
								messageFromYahoo.setCode((String) xmlMap.get("Code"));
								messageFromYahoo.setMessage((String) xmlMap.get("Message"));
								messageFromYahoo.setOrderId(orderNo);
								messageFromYahooList_UpdateOrder.add(messageFromYahoo);
							}
						} else {
							updatedOrderNoList.add(orderNo);
							updateXml = new StringBuilder();
							updateXml.append("<Req>");
							updateXml.append("<Target>");
							updateXml.append("<OrderId>");
							updateXml.append(orderNo);
							updateXml.append("</OrderId>");
							updateXml.append("</Target>");
							updateXml.append("<Order>");
							updateXml.append("<StoreStatus>");
							updateXml.append(5);
							updateXml.append("</StoreStatus>");
							updateXml.append("</Order>");
							updateXml.append("<SellerId>");
							updateXml.append(getSellerId());
							updateXml.append("</SellerId>");
							updateXml.append("</Req>");

							url = new URL(URL_ORDERCHANGE);
							getXml(shopName, url, updateXml.toString());

						}
					} catch (Exception e) {

					}
				} else {
					messageFromYahooList_UpdateOrder.clear();
					MessageFromYahoo messageFromYahoo = new MessageFromYahoo();
					messageFromYahoo.setCode("");
					messageFromYahoo.setMessage("YConnectServletに再度ログインして、Yahooアクセストークンを取得してください");
					messageFromYahooList_UpdateOrder.add(messageFromYahoo);
					break;
				}
			}
		}
		return updatedOrderNoList;
	}

	public void updateOrderStock(String item_code, String quantity, String allow_overdraft) throws Exception {
		messageFromYahooList_UpdateOrder.clear();
		if (Utility.isEmptyString(item_code) || Utility.isEmptyString(quantity)) {
			MessageFromYahoo messageFromYahoo = new MessageFromYahoo();
			messageFromYahoo.setCode("");
			messageFromYahoo.setMessage(Utility.isEmptyString(item_code) ? "商品コードをご 確認ください" : "在庫数をご 確認ください");
			messageFromYahooList_UpdateOrder.add(messageFromYahoo);
		} else {
			if (tokenIsValid(TOKEN_VALIDTIMEINTERVAL)) {
				StringBuilder updateXml;
				updateXml = new StringBuilder();
				updateXml.append("seller_id=");
				updateXml.append(getSellerId());
				updateXml.append("&");
				updateXml.append("item_code=");
				updateXml.append(item_code);
				updateXml.append("&");
				updateXml.append("quantity=");
				updateXml.append(quantity);
				updateXml.append("&");
				updateXml.append("allow_overdraft=");
				updateXml.append(allow_overdraft);

				URL url = new URL(URL_SETSTOCK);
				String xml_SetStockResult = getXml(shopName, url, updateXml.toString());
				Map<String, Object> xmlMap = XmlUtils.Dom2Map(xml_SetStockResult);

				Map<String, Object> resultMap = (Map<String, Object>) xmlMap.get("Result");
				if (null == resultMap) {
					if (xmlMap.get("Message") != null && xmlMap.get("Code") != null) {
						MessageFromYahoo messageFromYahoo = new MessageFromYahoo();
						messageFromYahoo.setCode((String) xmlMap.get("Code"));
						messageFromYahoo.setMessage((String) xmlMap.get("Message"));
						messageFromYahooList_UpdateOrder.add(messageFromYahoo);
					}
				}
			} else {
				MessageFromYahoo messageFromYahoo = new MessageFromYahoo();
				messageFromYahoo.setCode("");
				messageFromYahoo.setMessage("YConnectServletに再度ログインして、Yahooアクセストークンを取得してください");
				messageFromYahooList_UpdateOrder.add(messageFromYahoo);
			}
		}
	}

	private String getXml(String shopName, URL url, String getXml) throws Exception {

		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization",
				"Bearer " + (tokenIsValid(TOKEN_VALIDTIMEINTERVAL_ONEHOUR)?getAccessToken():GetTokenFromYahoo.getToken(getClientId(), getSellerId(), getRefreshToken())));
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.connect();// 得到请求的输出流对象
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		out.write(new String(getXml.getBytes("UTF-8")));

		System.out.println(new String(getXml.getBytes("UTF-8")));
		out.flush();
		out.close();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		String line = null;
		StringBuffer sb1 = new StringBuffer();
		while ((line = in.readLine()) != null) {
			sb1.append(line);
		}
		in.close();
		return sb1.toString();
	}

	public List<MessageFromYahoo> getMessageFromYahooList_GetOrder() {
		return messageFromYahooList_GetOrder;
	}

	public List<MessageFromYahoo> getMessageFromYahooList_UpdateOrder() {
		return messageFromYahooList_UpdateOrder;
	}

	private String getSellerId() throws Exception {
		if (null == sellerId) {
			Connection conn = JdbcConnection.getConnection();
			String sql = "SELECT YAHOO_APP_ID, ACCESS_TOKEN, REFRESH_TOKEN, LOGIN_TIME FROM rakuten.shop WHERE SITE = 'Yahoo' AND SHOP_ID = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, shopName);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				clientId = rs.getString("YAHOO_APP_ID");
				accessToken = rs.getString("ACCESS_TOKEN");
				refreshToken = rs.getString("REFRESH_TOKEN");
				loginTime = rs.getTimestamp("LOGIN_TIME");
			}
			conn.close();

			sellerId = shopName;
		}
		return sellerId;
	}

	private String getClientId() throws Exception {
		if (null == clientId) {
			getSellerId();
		}
		return clientId;
	}

	private String getRefreshToken() throws Exception {
		if (null == refreshToken) {
			getSellerId();
		}
		return refreshToken;
	}

	private Timestamp getUpdateTime() throws Exception {
		if (null == loginTime) {
			getSellerId();
		}
		return loginTime;
	}

	private boolean tokenIsValid(int interval) throws Exception {
		Calendar date = Calendar.getInstance();
		date.add(Calendar.HOUR, interval);
		Calendar dateDB = Calendar.getInstance();
		getUpdateTime();
		if(null == loginTime) {
			return false;
		} else {
			dateDB.setTime(getUpdateTime());
			return date.compareTo(dateDB) < 0;
		}
	}

	private String getAccessToken() throws Exception {
		if (null == accessToken) {
			getSellerId();
		}
		return accessToken;
	}
	
}
