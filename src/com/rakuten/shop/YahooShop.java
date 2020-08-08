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

import com.rakuten.order.Order;
import com.rakuten.util.GetTokenFromYahoo;
import com.rakuten.util.XmlUtils;

public class YahooShop {
	
	private final static String URL_GETORDERLIST = "https://circus.shopping.yahooapis.jp/ShoppingWebService/V1/orderList";
	private final static String URL_GETORDERINFO = "https://circus.shopping.yahooapis.jp/ShoppingWebService/V1/orderInfo";
	private final static int DATE_INTERVAL = -62;
	
	private String shopName;
	private List<Order> orders = new ArrayList<Order>();
	private List<String> orderNoList;
	
	public YahooShop(String shopName) {
		this.shopName = shopName;
	}

	public List<Order> getOrders(int orderStatus, int payStatus, int shipStatus) throws Exception{
		
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
			
		} else {
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
			getXml.append("PayMethodName,BuyerComments");
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
				
			} else {
				Map<String, Object> orderInfo = (Map<String, Object>)resultMap.get("OrderInfo");
				if (null == orderInfo) {
					
				} else {
					
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
	
}
