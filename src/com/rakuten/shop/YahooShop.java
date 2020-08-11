package com.rakuten.shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.rakuten.common.MessageFromYahoo;
import com.rakuten.order.YahooOrder;
import com.rakuten.util.GetTokenFromYahoo;
import com.rakuten.util.XmlUtils;

public class YahooShop {
	
	private final static String URL_GETORDERLIST = "https://circus.shopping.yahooapis.jp/ShoppingWebService/V1/orderList";
	private final static String URL_GETORDERINFO = "https://circus.shopping.yahooapis.jp/ShoppingWebService/V1/orderInfo";
	private final static int DATE_INTERVAL = -62;
	
	private String shopName;
	private List<YahooOrder> orders = new ArrayList<YahooOrder>();
	private List<String> orderNoList;
	private MessageFromYahoo messageFromYahoo;
	
	public YahooShop(String shopName) {
		this.shopName = shopName;
	}

	public List<YahooOrder> getOrders(int orderStatus, int payStatus, int shipStatus) throws Exception{
		
		searchOrder(orderStatus, payStatus, shipStatus);
		if((null == orderNoList) || (orderNoList.size() == 0)) {
			orders.clear();
		} else {
			getOrder();
		}
		return orders;
	}
	
	private void searchOrder(int orderStatus, int payStatus, int shipStatus) throws Exception{
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
		getXml.append("kirakiraichiba");
		getXml.append("</SellerId>");
		getXml.append("</Req>");
		
		URL url = new URL(URL_GETORDERLIST);
		String xml_GetOrderResult = getXml(shopName, url, getXml.toString());
		Map<String, Object> xmlMap = XmlUtils.Dom2Map(xml_GetOrderResult);
		
		Map<String, Object> searchMap = (Map<String, Object>)xmlMap.get("Search");
		if (null == searchMap) {
			if (xmlMap.get("Message") != null && xmlMap.get("Code") != null) {
				messageFromYahoo = new MessageFromYahoo();
				messageFromYahoo.setCode((String)xmlMap.get("Code"));
				messageFromYahoo.setMessage((String)xmlMap.get("Message"));
			}
		} else {
			messageFromYahoo = null;
			List<Object> orderInfoList = (List<Object>)searchMap.get("OrderInfo");
			if (null == orderInfoList || 0 == orderInfoList.size()) {
				
			} else {
				orderNoList = new ArrayList<String>();
				for (Object orderInfo : orderInfoList ) {
					orderNoList.add((String)((Map<String, Object>)orderInfo).get("OrderId"));
				}
			}
		}
		
	}
	
	private void getOrder() throws Exception{
		StringBuilder getXml;
		for(String orderNo : orderNoList) {
			getXml = new StringBuilder();
			getXml.append("<Req>");
			getXml.append("<Target>");
			getXml.append("<OrderId>");
			getXml.append(orderNo);
			getXml.append("</OrderId>");
			getXml.append("<Field>");
			getXml.append("OrderTime,PayMethodName,UsePoint,BillLastName,BillFirstName,BillLastNameKana,BillFirstNameKana,BillMailAddress,BillZipCode,BillPrefecture,BillCity,BillAddress1,BillAddress2,BillPhoneNumber,BuyerComments,ShipLastName,ShipFirstName,ShipLastNameKana,ShipFirstNameKana,ShipMethodName,ShipZipCode,ShipPhoneNumber,ShipPrefecture,ShipCity,ShipAddress1,ShipAddress2,SettleAmount,ItemTaxRatio,ShipCharge,PayCharge,TotalPrice,TotalCouponDiscount,Title,ItemId,UnitPrice,Quantity");
			getXml.append("</Field>");
			getXml.append("</Target>");
			getXml.append("<SellerId>");
			getXml.append("kirakiraichiba");
			getXml.append("</SellerId>");
			getXml.append("</Req>");
			
			URL url = new URL(URL_GETORDERINFO);
			String xml_GetOrderResult = getXml(shopName, url, getXml.toString());
			Map<String, Object> xmlMap = XmlUtils.Dom2Map(xml_GetOrderResult);
			
			Map<String, Object> resultMap = (Map<String, Object>)xmlMap.get("Result");
			if (null == resultMap) {
				if (xmlMap.get("Message") != null && xmlMap.get("Code") != null) {
					messageFromYahoo = new MessageFromYahoo();
					messageFromYahoo.setCode((String)xmlMap.get("Code"));
					messageFromYahoo.setMessage((String)xmlMap.get("Message"));
				}
			} else {
				messageFromYahoo = null;
				Map<String, Object> orderInfo = (Map<String, Object>)resultMap.get("OrderInfo");
				if (null == orderInfo) {
					
				} else {
					YahooOrder order = new YahooOrder();
					order.setOrderId(orderNo);
					order.setOrderTime((String)orderInfo.get("OrderTime"));
					YahooOrder.Pay pay = order.new Pay();
					Map<String, Object> payMap = (Map<String, Object>)orderInfo.get("Pay");
					pay.setPayMethodName((String)payMap.get("PayMethodName"));
					Map<String, Object> detailMap = (Map<String, Object>)orderInfo.get("Detail");
					YahooOrder.Detail detail = order.new Detail();
					detail.setUsePoint(Integer.parseInt((String)detailMap.get("UsePoint")));
					detail.setSettleAmount(Integer.parseInt((String)detailMap.get("SettleAmount")));
					detail.setShipCharge(Integer.parseInt((String)detailMap.get("ShipCharge")));
					detail.setPayCharge(Integer.parseInt((String)detailMap.get("PayCharge")));
					detail.setTotalPrice(Integer.parseInt((String)detailMap.get("TotalPrice")));
					order.setDetail(detail);
					pay.setBillLastName((String)payMap.get("BillLastName"));
					pay.setBillFirstName((String)payMap.get("BillFirstName"));
					pay.setBillLastNameKana((String)payMap.get("BillLastNameKana"));
					pay.setBillFirstNameKana((String)payMap.get("BillFirstNameKana"));
					pay.setBillMailAddress((String)payMap.get("BillMailAddress"));
//					String zipCode = (String)payMap.get("BillZipCode");
//					ordererModel.setZipCode1((zipCode == null || "".equals(zipCode))?"":zipCode.substring(0, zipCode.length()-4));
//					ordererModel.setZipCode2((zipCode == null || "".equals(zipCode))?"":zipCode.substring(zipCode.length()-4, zipCode.length()));
					pay.setBillZipCode((String)payMap.get("BillZipCode"));
					pay.setBillPrefecture((String)payMap.get("BillPrefecture"));
					pay.setBillCity((String)payMap.get("BillCity"));
//					ordererModel.setSubAddress((String)pay.get("BillAddress1")+(String)pay.get("BillAddress2"));
					pay.setBillAddress1((String)payMap.get("BillAddress1"));
					pay.setBillAddress2((String)payMap.get("BillAddress2"));
//					String phoneNumber = (String)pay.get("BillPhoneNumber");
//					phoneNumber = (phoneNumber == null || "".equals(phoneNumber))?"":phoneNumber.replace("-", "");
//					ordererModel.setPhoneNumber1("".equals(phoneNumber)?"":phoneNumber.substring(0,phoneNumber.length()-8));
//					ordererModel.setPhoneNumber2("".equals(phoneNumber)?"":phoneNumber.substring(phoneNumber.length()-8, phoneNumber.length()-4));
//					ordererModel.setPhoneNumber3("".equals(phoneNumber)?"":phoneNumber.substring(phoneNumber.length()-4, phoneNumber.length()));
					pay.setBillPhoneNumber((String)payMap.get("BillPhoneNumber"));
					order.setPay(pay);
					order.setBuyerComments((String)orderInfo.get("BuyerComments"));
					Map<String, Object> shipMap = (Map<String, Object>)orderInfo.get("Ship");
					YahooOrder.Ship ship = order.new Ship();
					ship.setShipLastName((String)shipMap.get("ShipLastName"));
					ship.setShipFirstName((String)shipMap.get("ShipFirstName"));
					ship.setShipLastNameKana((String)shipMap.get("ShipLastNameKana"));
					ship.setShipFirstNameKana((String)shipMap.get("ShipFirstNameKana"));
					ship.setShipMethodName((String)shipMap.get("ShipMethodName"));
					ship.setShipZipCode((String)shipMap.get("ShipZipCode"));
					ship.setShipPhoneNumber((String)shipMap.get("ShipPhoneNumber"));
					ship.setShipPrefecture((String)shipMap.get("ShipPrefecture"));
					ship.setShipCity((String)shipMap.get("ShipCity"));
					ship.setShipAddress1((String)shipMap.get("ShipAddress1"));
					ship.setShipAddress2((String)shipMap.get("ShipAddress2"));
					order.setShip(ship);
					if (null == orderInfo.get("Item")) {
						order.setItemList(new ArrayList<YahooOrder.Item>());
					} else {
						List<YahooOrder.Item> itemList = new ArrayList<YahooOrder.Item>();
						List<Map<String, Object>> itemMapList;
						if (orderInfo.get("Item") instanceof List) {
							itemMapList = (List<Map<String, Object>>)orderInfo.get("Item");
						} else {
							itemMapList = new ArrayList<Map<String, Object>>();
							itemMapList.add((Map<String, Object>)orderInfo.get("Item"));
						}
						for (Map<String, Object> itemMap : itemMapList) {
							YahooOrder.Item item = order.new Item();
							item.setItemTaxRatio(Integer.parseInt((String)itemMap.get("ItemTaxRatio")));
							item.setTitle((String)itemMap.get("Title"));
							item.setItemId((String)itemMap.get("ItemId"));
							item.setUnitPrice(Integer.parseInt((String)itemMap.get("UnitPrice")));
							item.setQuantity(Integer.parseInt((String)itemMap.get("Quantity")));
							itemList.add(item);
						}
						order.setItemList(itemList);
					}
					order.setTotalCouponDiscount(Integer.parseInt(((String)orderInfo.get("TotalCouponDiscount")).equals("")?"0":(String)orderInfo.get("TotalCouponDiscount")));
					orders.add(order);
				}
			}
			
		}
	}
	
	private String getXml(String shopName, URL url, String getXml) throws IOException {
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization", "Bearer " + GetTokenFromYahoo.getToken());
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

	public MessageFromYahoo getMessageFromYahoo() {
		return messageFromYahoo;
	}
	
}
