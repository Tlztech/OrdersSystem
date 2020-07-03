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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.axis.encoding.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.common.MessageFromRMS;
import com.rakuten.order.Order;
import com.rakuten.order.Order.PackageModel.ItemModel;
import com.rakuten.order.OrderShppingInfo;

import shohinmodel.common.Shohincommon;

public class Shop {

	private final static String URL_SEARCHORDER = "https://api.rms.rakuten.co.jp/es/2.0/order/searchOrder/";
	private final static String URL_GETORDER = "https://api.rms.rakuten.co.jp/es/2.0/order/getOrder/";
	private final static String URL_UPDATEORDERSHIPPING = "https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderShipping/";
	private final static int DATE_INTERVAL = -62;
	private final static int DATETYPE_NOTEDAY = 1;
	private final static int MAX_GETORDER = 100;

	private String shopName;
	private List<Order> orders = new ArrayList<Order>();
	private List<MessageFromRMS> messageFromRMSList_GetOrder = new ArrayList<MessageFromRMS>();
	private List<MessageFromRMS> messageFromRMSList_UpdateOrder = new ArrayList<MessageFromRMS>();
	private List<String> orderNoList;
	
	public Shop(String shopName) {
		this.shopName = shopName;
	}
	
	public List<Order> getOrders(List<Integer> orderProgressList) throws Exception{
		searchOrder(orderProgressList);
		if((null == orderNoList) || (orderNoList.size() == 0)) {
			orders.clear();
		} else {
			getOrder();
		}
		return orders;
	}
	
	public List<Order> updateOrderShipping(OrderShppingInfo orderShppingInfo) throws Exception {
		List<Order> updatedOrderList = new ArrayList<Order>();
		List<String> orderNoList = orderShppingInfo.getOrderNoList();
		Map<String, String[]> orderShppingInfoMap =  orderShppingInfo.getOrderShppingInfo();
		if((null == orderNoList) || (orderNoList.size() == 0)) {

		} else {
			this.orderNoList = orderNoList;
			getOrder();
			if(orders.size() == 0) {

			} else {
				String[] strArr;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar date = Calendar.getInstance();
				String shippingDate = sdf.format(date.getTime());
				Map<String,?> map_UpdateOrderResult;
				ObjectMapper objectMapper_UpdateOrderResult = new ObjectMapper();
				List<Map<String,String>> messageModelList = new ArrayList<Map<String,String>>();
				List<Map<String,String>> messageModelSubList;
				for(Order order : orders) {
					strArr = orderShppingInfoMap.get(order.getOrderNumber());
					try {
						String json_UpdateOrderResult = updateOrderShipping(order.getOrderNumber(), order.getPackageModelList().get(0).getBasketId(), strArr[0], strArr[1], shippingDate);
						map_UpdateOrderResult = objectMapper_UpdateOrderResult.readValue(json_UpdateOrderResult, Map.class);
						messageModelSubList = (ArrayList<Map<String,String>>) map_UpdateOrderResult.get("MessageModelList");
						for(Map<String,String> messageModelMap : messageModelSubList) {
							messageModelMap.put("orderNumber", order.getOrderNumber());
						}
						messageModelList.addAll(messageModelSubList);
						updatedOrderList.add(order);
					} catch (Exception e) {
						
					}
				}
				convertUpdateOrderMessageModel(messageModelList);
			}
		}
		return updatedOrderList;
	}
	
	private String updateOrderShipping(String orderNumber, int basketId, String shippingNumber, String deliveryCompany, String shippingDate) throws Exception {
		ObjectMapper objectMapper_UpdateOrderResult = new ObjectMapper();
		Map<String, Object> requestMap = new HashMap<String, Object>();
		String updatexml;
		URL url = new URL(URL_UPDATEORDERSHIPPING);
		requestMap.put("orderNumber", orderNumber);
		List<Map<String, ?>> basketidModelList = new ArrayList<Map<String, ?>>();
		Map<String, Object> basketidModelMap = new HashMap<String, Object>();
		basketidModelMap.put("basketId", basketId);
		List<Map<String, ?>> shippingModelList = new ArrayList<Map<String, ?>>();
		Map<String, Object> shippingModelMap = new HashMap<String, Object>();
		shippingModelMap.put("deliveryCompany", deliveryCompany);
		shippingModelMap.put("shippingNumber", shippingNumber);
		shippingModelMap.put("shippingDate", shippingDate);
		shippingModelList.add(shippingModelMap);
		basketidModelMap.put("ShippingModelList", shippingModelList);
		basketidModelList.add(basketidModelMap);
		requestMap.put("BasketidModelList", basketidModelList);
		updatexml = objectMapper_UpdateOrderResult.writeValueAsString(requestMap);
		String json_UpdateOrderResult = getJson(shopName, url, updatexml);
		return json_UpdateOrderResult;
	}
	
	private void searchOrder(List<Integer> orderProgressList) throws Exception{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		String endDay = sdf.format(date.getTime()) + "T23:59:59+0900";
		date.add(Calendar.DATE, DATE_INTERVAL);
		String startDay = sdf.format(date.getTime()) + "T00:00:00+0900";
		StringBuilder orderProgressStr = new StringBuilder("");
		if (null != orderProgressList && orderProgressList.size() > 0) {
			for (Integer orderProgress:orderProgressList) {
				orderProgressStr.append("\"").append(orderProgress).append("\"").append(",");
			}
			orderProgressStr.deleteCharAt(orderProgressStr.length() - 1);
		}
		String searchxml = "{"+(orderProgressStr.length()>0?"\"orderProgressList\":["+orderProgressStr.toString()+"],":"")+"\"dateType\":"+DATETYPE_NOTEDAY+",\"startDatetime\":\"" + startDay
				+ "\",\"endDatetime\":\"" + endDay
				+ "\",\"PaginationRequestModel\":{\"requestRecordsAmount\":1000,\"requestPage\":1,\"SortModelList\":[{\"sortColumn\":1,\"sortDirection\":1}]}}";
		
		URL url = new URL(URL_SEARCHORDER);
		String json_SearchOrderResult = getJson(shopName, url, searchxml);
		
		ObjectMapper objectMapper_SearchOrderResult = new ObjectMapper();
		Map<String,?> map_SearchOrderResult = objectMapper_SearchOrderResult.readValue(json_SearchOrderResult, Map.class);
		List<Map<String,String>> messageModelList = (ArrayList<Map<String,String>>) map_SearchOrderResult.get("MessageModelList");
		convertGetOrderMessageModel(messageModelList);
		
		orderNoList = (ArrayList<String>) map_SearchOrderResult.get("orderNumberList");
	}

	private void getOrder() throws Exception{
		Map<String, List<String>> requestMap = new HashMap<String, List<String>>();
		ObjectMapper objectMapper_GetOrderResult = new ObjectMapper();
		String getxml;
		int orderNoAmount = orderNoList.size();
		List<String> orderNoSubList;
		int startPos, endPos;
		List<Map<String, ?>> orderModelList = new ArrayList<Map<String, ?>>();
		List<Map<String, ?>> orderModelSubList;
		List<Map<String,String>> messageModelList = new ArrayList<Map<String,String>>();
		List<Map<String,String>> messageModelSubList;
		startPos = 0;
		endPos = MAX_GETORDER<orderNoAmount?MAX_GETORDER: orderNoAmount;
		while (endPos > startPos) {
			orderNoSubList = orderNoList.subList(startPos, endPos);
			requestMap.put("orderNumberList", orderNoSubList);
			getxml = objectMapper_GetOrderResult.writeValueAsString(requestMap);
			URL url = new URL(URL_GETORDER);
			String json_GetOrderResult = getJson(shopName, url, getxml);
			Map<String,?> map_GetOrderResult = objectMapper_GetOrderResult.readValue(json_GetOrderResult, Map.class);
			messageModelSubList = (ArrayList<Map<String,String>>) map_GetOrderResult.get("MessageModelList");
			if (null == messageModelSubList || messageModelSubList.size() == 0) {
				
			} else {
				messageModelList.addAll(messageModelSubList);
			}
			orderModelSubList = (List<Map<String, ?>>) map_GetOrderResult.get("OrderModelList");
			if (null == orderModelSubList || orderModelSubList.size() == 0) {
				
			} else {
				orderModelList.addAll(orderModelSubList);
			}
			startPos=endPos;
			endPos = (endPos+MAX_GETORDER)<orderNoAmount?endPos + MAX_GETORDER: orderNoAmount;
		}
		convertGetOrderMessageModel(messageModelList);
		convertOrderModel(orderModelList);
	}
	
	private String getJson(String shopName, URL url, String searchxml) throws IOException{
		Shohincommon common = new Shohincommon();
		String serviceSecret = common.getServiceSecret(shopName);
		String licenseKey = common.getLicenseKey(shopName);
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization", author);
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.connect();// 得到请求的输出流对象
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		out.write(new String(searchxml.getBytes("UTF-8")));

		System.out.println(new String(searchxml.getBytes("UTF-8")));
		out.flush();
		out.close();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (Exception e) {
			e.toString();
		}
		String line = null;
		StringBuffer sb1 = new StringBuffer();
		while ((line = in.readLine()) != null) {
			sb1.append(line);
		}
		in.close();
		return sb1.toString();
	}
	
	private void convertGetOrderMessageModel(List<Map<String,String>> messageModelList) {
		ObjectMapper objectMapper = new ObjectMapper();
		messageFromRMSList_GetOrder.clear();
		for(Map<String,String> messageModel:messageModelList)
			messageFromRMSList_GetOrder.add(objectMapper.convertValue(messageModel, MessageFromRMS.class));
	}
	
	private void convertUpdateOrderMessageModel(List<Map<String,String>> messageModelList) {
		ObjectMapper objectMapper = new ObjectMapper();
		messageFromRMSList_UpdateOrder.clear();
		for(Map<String,String> messageModel:messageModelList)
			messageFromRMSList_UpdateOrder.add(objectMapper.convertValue(messageModel, MessageFromRMS.class));
	}
	
	private void convertOrderModel(List<Map<String, ?>> orderModelList) {
		orders.clear();
		for (Map<String, ?> orderModelMap : orderModelList) {
			Order order = new Order();
			order.setOrderNumber((String)orderModelMap.get("orderNumber"));
			
			order.setOrderDatetime((String)orderModelMap.get("orderDatetime"));
			
			Map<String, ?> settlementModelMap = (Map<String, ?>) orderModelMap.get("SettlementModel");
			Order.SettlementModel settlementModel = order.new SettlementModel();
			settlementModel.setSettlementMethod((String)settlementModelMap.get("settlementMethod"));
			order.setSettlementModel(settlementModel);
			
			Map<String, ?> ordererModelMap = (Map<String, ?>) orderModelMap.get("OrdererModel");
			Order.OrdererModel ordererModel = order.new OrdererModel();
			ordererModel.setZipCode1((String)ordererModelMap.get("zipCode1"));
			ordererModel.setZipCode2((String)ordererModelMap.get("zipCode2"));
			ordererModel.setPrefecture((String)ordererModelMap.get("prefecture"));
			ordererModel.setCity((String)ordererModelMap.get("city"));
			ordererModel.setSubAddress((String)ordererModelMap.get("subAddress"));
			ordererModel.setFamilyName((String)ordererModelMap.get("familyName"));
			ordererModel.setFirstName((String)ordererModelMap.get("firstName"));
			ordererModel.setFamilyNameKana((String)ordererModelMap.get("familyNameKana"));
			ordererModel.setFirstNameKana((String)ordererModelMap.get("firstNameKana"));
			ordererModel.setPhoneNumber1((String)ordererModelMap.get("phoneNumber1"));
			ordererModel.setPhoneNumber2((String)ordererModelMap.get("phoneNumber2"));
			ordererModel.setPhoneNumber3((String)ordererModelMap.get("phoneNumber3"));
			ordererModel.setEmailAddress((String)ordererModelMap.get("emailAddress"));
			ordererModel.setBirthYear((int)(ordererModelMap.get("birthYear") == null ? 1900 : ordererModelMap.get("birthYear")));
			ordererModel.setBirthMonth((int)(ordererModelMap.get("birthMonth") == null ? 1 : ordererModelMap.get("birthMonth")));
			ordererModel.setBirthDay((int)(ordererModelMap.get("birthDay") == null ? 1 : ordererModelMap.get("birthDay")));
			order.setOrdererModel(ordererModel);
			
			Map<String, ?> deliveryModelMap = (Map<String, ?>) orderModelMap.get("DeliveryModel");
			Order.DeliveryModel deliveryModel = order.new DeliveryModel();
			deliveryModel.setDeliveryName((String)deliveryModelMap.get("deliveryName"));
			order.setDeliveryModel(deliveryModel);
			
			List<Map<String, ?>> packageModelMapList = (List<Map<String, ?>>) orderModelMap.get("PackageModelList");
			List<Order.PackageModel> packageModelList = new ArrayList<Order.PackageModel>();
			for(Map<String, ?> packageModelMap : packageModelMapList) {
				Order.PackageModel packageModel = order.new PackageModel();
				packageModel.setBasketId((int)packageModelMap.get("basketId"));
				Order.PackageModel.SenderModel senderModel = packageModel.new SenderModel();
				Map<String, ?> senderModelMap = (Map<String, ?>)packageModelMap.get("SenderModel");
				senderModel.setZipCode1((String)senderModelMap.get("zipCode1"));
				senderModel.setZipCode2((String)senderModelMap.get("zipCode2"));
				senderModel.setPrefecture((String)senderModelMap.get("prefecture"));
				senderModel.setCity((String)senderModelMap.get("city"));
				senderModel.setSubAddress((String)senderModelMap.get("subAddress"));
				senderModel.setFamilyName((String)senderModelMap.get("familyName"));
				senderModel.setFirstName((String)senderModelMap.get("firstName"));
				senderModel.setFamilyNameKana((String)senderModelMap.get("familyNameKana"));
				senderModel.setFirstNameKana((String)senderModelMap.get("firstNameKana"));
				senderModel.setPhoneNumber1((String)senderModelMap.get("phoneNumber1"));
				senderModel.setPhoneNumber2((String)senderModelMap.get("phoneNumber2"));
				senderModel.setPhoneNumber3((String)senderModelMap.get("phoneNumber3"));
				packageModel.setSenderModel(senderModel);
				List<Map<String, ?>> itemModelMapList = (List<Map<String, ?>>) packageModelMap.get("ItemModelList");
				List<ItemModel> itemModelList = new ArrayList<ItemModel>();
				for(Map<String, ?> itemModelMap : itemModelMapList) {
					Order.PackageModel.ItemModel itemModel = packageModel. new ItemModel();
					itemModel.setItemName((String)itemModelMap.get("itemName"));
					itemModel.setItemNumber((String)itemModelMap.get("itemNumber"));
					itemModel.setManageNumber((String)itemModelMap.get("manageNumber"));
					itemModel.setPrice((int)itemModelMap.get("price"));
					itemModel.setUnits((int)itemModelMap.get("units"));
					itemModel.setIncludePostageFlag((int)itemModelMap.get("includePostageFlag"));
					itemModel.setIncludeTaxFlag((int)itemModelMap.get("includeTaxFlag"));
					itemModel.setIncludeCashOnDeliveryPostageFlag((int)itemModelMap.get("includeCashOnDeliveryPostageFlag"));
					itemModel.setSelectedChoice((String)itemModelMap.get("selectedChoice"));
					itemModel.setPointRate((int)itemModelMap.get("pointRate"));
					itemModel.setDelvdateInfo((String)itemModelMap.get("delvdateInfo"));
					itemModelList.add(itemModel);
				}
				packageModel.setItemModelList(itemModelList);
				packageModel.setPostagePrice((int)packageModelMap.get("postagePrice"));
				packageModelList.add(packageModel);
			}
			order.setPackageModelList(packageModelList);
			
			Map<String, ?> pointModelMap = (Map<String, ?>) orderModelMap.get("PointModel");
			Order.PointModel pointModel = order.new PointModel();
			pointModel.setUsedPoint((int)pointModelMap.get("usedPoint"));
			order.setPointModel(pointModel);
			
			order.setAsurakuFlag((int)orderModelMap.get("asurakuFlag"));
			
			order.setRemarks((String)orderModelMap.get("remarks"));
			
			order.setMailPlugSentence((String)orderModelMap.get("mailPlugSentence"));
			
			order.setGoodsPrice((int)orderModelMap.get("goodsPrice"));
			
			order.setGoodsTax((int)orderModelMap.get("goodsTax"));
			
			order.setPostagePrice((int)orderModelMap.get("postagePrice"));
			
			order.setDeliveryPrice((int)orderModelMap.get("deliveryPrice"));
			
			order.setRequestPrice((int)orderModelMap.get("requestPrice"));
			
			order.setCouponAllTotalPrice((int)orderModelMap.get("couponAllTotalPrice"));
			
			orders.add(order);
		}
	}
	
	public List<MessageFromRMS> getMessageFromRMS_GetOrder() {
		return messageFromRMSList_GetOrder;
	}
	
	public List<MessageFromRMS> getMessageFromRMS_UpdateOrder() {
		return messageFromRMSList_UpdateOrder;
	}
}
