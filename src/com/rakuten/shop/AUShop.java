package com.rakuten.shop;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.rakuten.common.MessageFromAU;
import com.rakuten.order.AUOrder;
import com.rakuten.order.OrderShppingInfo;
import com.rakuten.order.AUOrder.Item;
import com.rakuten.util.AU_XmlUtils;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class AUShop {

	private final static String URL_GETORDERNOLIST = "https://api.manager.wowma.jp/wmshopapi/searchTradeNoListProc?totalCount=1000&startDate=2021-05-10&endDate=";
	private final static String URL_GETORDERINFOLIST = "https://api.manager.wowma.jp/wmshopapi/searchTradeInfoListProc?totalCount=1000&startDate=2021-05-10&endDate=";
	private final static String URL_POSTSTOCKLIST = "https://api.manager.wowma.jp/wmshopapi/updateStock";
	private final static String URL_POSTTRADELIST = "https://api.manager.wowma.jp/wmshopapi/updateTradeInfoProc";
	private final static String URL_POSTSTATUSLIST = "https://api.manager.wowma.jp/wmshopapi/updateTradeStsProc";
	private List<MessageFromAU> messageFromAUList = new ArrayList<>();
	private String shopName;
	private String shopId;
	private String apiKey;
	
	public AUShop(String shopName) throws Exception {
		this.shopName = shopName;
		Connection conn = JdbcConnection.getConnection();
		String sql = "SELECT SHOP_NO, AU_APIKEY FROM rakuten.shop WHERE SITE = 'AU' AND SHOP_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, shopName);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			shopId = rs.getString("SHOP_NO");
			apiKey = rs.getString("AU_APIKEY");
		}
		conn.close();
	}
	
	public List<String> getOrderNo() throws Exception {
		List<String> orderList = new ArrayList<>();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf1.format(new Date());
		String result = getHttpRsp(URL_GETORDERNOLIST+date+"&shopId"+shopId+"&orderStatus="+URLEncoder.encode("発送待ち","utf-8"));
		if (result.isEmpty()) {
			
		} else {
			Map<String, Object> xmlMap = AU_XmlUtils.Dom2Map(result);
	        Map<String, Object> resultMap = (Map<String, Object>)xmlMap.get("result");
	        if (resultMap.get("status").equals("1")) {
	        	MessageFromAU e = new MessageFromAU();
	        	e.setCode((String)((Map<String, Object>)resultMap.get("error")).get("code"));
	        	e.setMessage((String)((Map<String, Object>)resultMap.get("error")).get("message"));
	        	messageFromAUList.add(e);
	        } else {
	        	List<Map<String, Object>> orderMapList;
	        	if (xmlMap.get("orderInfo") instanceof List) {
	        		orderMapList = (List<Map<String, Object>>)xmlMap.get("orderInfo");
	        	} else {
	        		orderMapList = new ArrayList<Map<String, Object>>();
	        		orderMapList.add((Map<String, Object>)xmlMap.get("orderInfo"));
	        	}
	        	for (int i = 0; i < orderMapList.size(); i++) {
	        		orderList.add((String)orderMapList.get(i).get("orderId"));
	        	}
	        }
		}
        return orderList;
	}
	
	public List<AUOrder> getOrders() throws Exception {
		List<AUOrder> orderList = new ArrayList<>();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		String date = sdf1.format(new Date());
		String result = getHttpRsp(URL_GETORDERINFOLIST+date+"&shopId="+shopId+"&orderStatus="+URLEncoder.encode("発送待ち","utf-8"));
		if (result.isEmpty()) {
			
		} else {
			Map<String, Object> xmlMap = AU_XmlUtils.Dom2Map(result);
			Map<String, Object> resultMap = (Map<String, Object>)xmlMap.get("result");
			if (resultMap.get("status").equals("1")) {
	        	MessageFromAU e = new MessageFromAU();
	        	e.setCode((String)((Map<String, Object>)resultMap.get("error")).get("code"));
	        	e.setMessage((String)((Map<String, Object>)resultMap.get("error")).get("message"));
	        	messageFromAUList.add(e);
	        } else {
	        	List<Map<String, Object>> orderMapList;
	        	if (xmlMap.get("orderInfo") instanceof List) {
	        		orderMapList = (List<Map<String, Object>>)xmlMap.get("orderInfo");
	        	} else {
	        		orderMapList = new ArrayList<Map<String, Object>>();
	        		orderMapList.add((Map<String, Object>)xmlMap.get("orderInfo"));
	        	}
	        	List<Map<String, Object>> itemMapList;
	        	for (int i = 0; i < orderMapList.size(); i++) {
	        		AUOrder auorder = new AUOrder();
	        		auorder.setOrderId((String)orderMapList.get(i).get("orderId"));
	        		auorder.setOrderDate((String)orderMapList.get(i).get("orderDate"));
	        		auorder.setMemo((String)orderMapList.get(i).get("memo"));
	        		auorder.setTotalSalePrice((String)orderMapList.get(i).get("totalSalePrice"));
	        		auorder.setPostagePrice((String)orderMapList.get(i).get("postagePrice"));
	        		auorder.setRequestPrice((String)orderMapList.get(i).get("requestPrice"));
	        		auorder.setOrdererZipCode((String)orderMapList.get(i).get("ordererZipCode"));
	        		auorder.setOrdererAddress((String)orderMapList.get(i).get("ordererAddress"));
	        		auorder.setOrdererName((String)orderMapList.get(i).get("ordererName"));
	        		auorder.setOrdererKana((String)orderMapList.get(i).get("ordererKana"));
	        		auorder.setOrdererPhoneNumber1((String)orderMapList.get(i).get("ordererPhoneNumber1"));
	        		auorder.setMailAddress((String)orderMapList.get(i).get("mailAddress"));
	        		auorder.setSettlementName((String)orderMapList.get(i).get("settlementName"));
	        		auorder.setDeliveryName((String)orderMapList.get(i).get("deliveryName"));
	        		auorder.setUsePoint((String)orderMapList.get(i).get("usePoint"));
	        		auorder.setSenderZipCode((String)orderMapList.get(i).get("senderZipCode"));
	        		auorder.setSenderAddress((String)orderMapList.get(i).get("senderAddress"));
	        		auorder.setSenderName((String)orderMapList.get(i).get("senderName"));
	        		auorder.setSenderKana((String)orderMapList.get(i).get("senderKana"));
	        		auorder.setSenderPhoneNumber1((String)orderMapList.get(i).get("senderPhoneNumber1"));
	        		auorder.setCouponTotalPrice((String)orderMapList.get(i).get("couponTotalPrice"));
	        		if (orderMapList.get(i).get("detail") instanceof List) {
	        			itemMapList = (List<Map<String, Object>>)orderMapList.get(i).get("detail");
		        	} else {
		        		itemMapList = new ArrayList<Map<String, Object>>();
		        		itemMapList.add((Map<String, Object>)orderMapList.get(i).get("detail"));
		        	}
	        		List<Item> itemList = new ArrayList<>();
	        		for (Map<String, Object> itemMap : itemMapList) {
	        			Item item = auorder.new Item();
	        			if (Utility.isEmptyString((String)itemMap.get("itemManagementId"))) {
	        				item.setItemCode(((String)itemMap.get("itemCode")).replaceAll("--", "-"));
	        			} else {
	        				item.setItemCode(((String)itemMap.get("itemCode")).replaceAll("--", "-")+((String)itemMap.get("itemManagementId")).replaceAll("--", "-"));
	        			}
	        			
	        			item.setItemName((String)itemMap.get("itemName"));
	        			item.setItemPrice((String)itemMap.get("itemPrice"));
	        			item.setUnit((String)itemMap.get("unit"));
	        			itemList.add(item);
	        		}
	        		auorder.setItemList(itemList);
	        		orderList.add(auorder);
	        	}
	        }
		}
		return orderList;
	}
	
	public void updateOrderStock(List<String> commodityIdList, List<String> detailNoList, List<String> quantityList) throws Exception {
		if (Utility.isEmptyList(commodityIdList) || Utility.isEmptyList(detailNoList) || Utility.isEmptyList(quantityList) || (commodityIdList.size() != detailNoList.size()) || (commodityIdList.size() != quantityList.size()) || (detailNoList.size() != quantityList.size())) {
			MessageFromAU e = new MessageFromAU();
        	e.setCode("");
        	e.setMessage("更新データ不正。商品ID個数：" + (Utility.isEmptyList(commodityIdList)?"0":commodityIdList.size()) + ",個別ID個数：" + (Utility.isEmptyList(commodityIdList)?"0":commodityIdList.size()) + ",在庫数量個数：" + (Utility.isEmptyList(commodityIdList)?"0":commodityIdList.size()));
        	messageFromAUList.add(e);
		} else {
			String result = postHttpRsp(URL_POSTSTOCKLIST, convertStockToXml(commodityIdList, detailNoList, quantityList));
			if (result.isEmpty()) {
				
			} else {
				Map<String, Object> xmlMap = AU_XmlUtils.Dom2Map(result);
				Map<String, Object> resultMap = (Map<String, Object>)xmlMap.get("result");
				if (resultMap.get("status").equals("1")) {
					List<Map<String, Object>> updateResultList;
		        	if (xmlMap.get("updateResult") instanceof List) {
		        		updateResultList = (List<Map<String, Object>>)xmlMap.get("updateResult");
		        	} else {
		        		updateResultList = new ArrayList<Map<String, Object>>();
		        		updateResultList.add((Map<String, Object>)xmlMap.get("updateResult"));
		        	}

		        	for (int i = 0; i < updateResultList.size(); i++) {
		        		if (null == (Map<String, Object>)updateResultList.get(i).get("error")) {
		        			
		        		} else {
		        			MessageFromAU e = new MessageFromAU();
				        	e.setCode((String)((Map<String, Object>)updateResultList.get(i).get("error")).get("code"));
				        	e.setMessage("itemCode:"+(String)(updateResultList.get(i).get("itemCode"))+","+(String)((Map<String, Object>)updateResultList.get(i).get("error")).get("message"));
				        	messageFromAUList.add(e);
		        		}
		        	}
		        	
		        }
				
			}
		}
		
	}
	
	public List<String> updateOrderShipping(OrderShppingInfo orderShppingInfo) throws Exception {
		List<String> updatedOrderNoList = new ArrayList<String>();
		List<String> orderNoList = orderShppingInfo.getOrderNoList();
		Map<String, String[]> orderShppingInfoMap = orderShppingInfo.getOrderShppingInfo();
		if ((null == orderNoList) || (orderNoList.size() == 0)) {

		} else {
			String[] strArr;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar date = Calendar.getInstance();
			String shippingDate = sdf.format(date.getTime());
			String updateXml;
			for (String orderNo : orderNoList) {
				strArr = orderShppingInfoMap.get(orderNo);
				updateXml = convertShipToXml(orderNo, shippingDate, strArr);
				
				String result = postHttpRsp(URL_POSTTRADELIST, updateXml);
				if (result.isEmpty()) {
					
				} else {
					Map<String, Object> xmlMap = AU_XmlUtils.Dom2Map(result);
					Map<String, Object> resultMap = (Map<String, Object>)xmlMap.get("result");
					if (resultMap.get("status").equals("1")) {
						MessageFromAU e = new MessageFromAU();
			        	e.setCode((String)((Map<String, Object>)resultMap.get("error")).get("code"));
			        	e.setMessage((String)((Map<String, Object>)resultMap.get("error")).get("message"));
			        	messageFromAUList.add(e);
					} else {
						result = postHttpRsp(URL_POSTSTATUSLIST, convertStatusToXml(orderNo));
						xmlMap = AU_XmlUtils.Dom2Map(result);
						resultMap = (Map<String, Object>)xmlMap.get("result");
						if (resultMap.get("status").equals("1")) {
							MessageFromAU e = new MessageFromAU();
				        	e.setCode((String)((Map<String, Object>)resultMap.get("error")).get("code"));
				        	e.setMessage((String)((Map<String, Object>)resultMap.get("error")).get("message"));
				        	messageFromAUList.add(e);
						} else {
							updatedOrderNoList.add(orderNo);
						}
					}
				}
			}
		}
		
		return updatedOrderNoList;
	}
	
	private String getHttpRsp(String urlStr) throws Exception {
		String result = "";
		BufferedReader in = null;
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization",
				"Bearer " + apiKey);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		conn.setRequestMethod("GET");
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.connect();// 得到请求的输出流对象
//		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
//		out.write(new String(getXml.getBytes("UTF-8")));
//		System.out.println(conn.getResponseCode());
		if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
	        // 定义 BufferedReader输入流来读取URL的响应
	        in = new BufferedReader(new InputStreamReader(
	        		conn.getInputStream()));
	        String line;
	        while ((line = in.readLine()) != null) {
	            result += line;
	        }
		} else {
			MessageFromAU e = new MessageFromAU();
			e.setCode(String.valueOf(conn.getResponseCode()));
			e.setMessage(conn.getResponseMessage() == null ? "HTTP ERROR":conn.getResponseMessage());
			messageFromAUList.add(e);
		}
		return result;
	}
	
	private String postHttpRsp(String urlStr, String req) throws Exception {
		String result = "";
		BufferedReader in = null;
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization",
				"Bearer " + apiKey);
		conn.setRequestProperty("Content-Type", "application/xml; charset=utf-8");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.connect();// 得到请求的输出流对象
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		out.write(new String(req.getBytes("UTF-8")));
		out.flush();
		out.close();
		try {
			if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		String line = null;
		StringBuffer sb1 = new StringBuffer();
		while ((line = in.readLine()) != null) {
			sb1.append(line);
		}
		in.close();
		result = sb1.toString();
		return result;
	}

	/**
	 * @return the messageFromAUList
	 */
	public List<MessageFromAU> getMessageFromAUList() {
		return messageFromAUList;
	}
	
	private String getApikey() throws Exception{
		String apikey= "";
		Connection conn = JdbcConnection.getConnection();
		String sql = "SELECT AU_APIKEY　FROM rakuten.shop WHERE SITE = 'AU' AND SHOP_ID = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		ps.setString(1, shopName);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			apikey = rs.getString("AU_APIKEY");
		}
		conn.close();
		
		return apikey;
	}
	
	private String convertStockToXml(List<String> commodityIdList, List<String> detailNoList, List<String> quantityList) {

		StringBuilder reqXml = new StringBuilder("<request><shopId>");
		reqXml.append(shopId).append("</shopId>");
		String detailNo;
		for (int i = 0; i < commodityIdList.size(); i++) {
			reqXml.append("<stockUpdateItem>");
			reqXml.append("<itemCode>");
			reqXml.append(commodityIdList.get(i));
			reqXml.append("</itemCode>");
			detailNo = detailNoList.get(i);
			if ("".equals(detailNo)) {
				reqXml.append("<stockSegment>");
				reqXml.append("1");
				reqXml.append("</stockSegment>");
				reqXml.append("<stockCount>");
				reqXml.append(quantityList.get(i));
				reqXml.append("</stockCount>");
			} else {
				reqXml.append("<stockSegment>");
				reqXml.append("2");
				reqXml.append("</stockSegment>");
				reqXml.append("<choicesStocks>");
				if (detailNo.indexOf("-") != detailNo.lastIndexOf("-")) {
					reqXml.append("<choicesStockHorizontalCode>");
					reqXml.append(detailNo.substring(0, detailNo.lastIndexOf("-")));
					reqXml.append("</choicesStockHorizontalCode>");
					reqXml.append("<choicesStockVerticalCode>");
					reqXml.append(detailNo.substring(detailNo.lastIndexOf("-")));
					reqXml.append("</choicesStockVerticalCode>");
				} else {
					reqXml.append("<choicesStockHorizontalCode>");
					reqXml.append(detailNo);
					reqXml.append("</choicesStockHorizontalCode>");
				}
				reqXml.append("<choicesStockCount>");
				reqXml.append(quantityList.get(i));
				reqXml.append("</choicesStockCount>");
				reqXml.append("</choicesStocks>");
			}
			reqXml.append("</stockUpdateItem>");
		}
		
		reqXml.append("</request>");
		return reqXml.toString();
	}
	
	private String convertShipToXml(String orderNo, String shippingDate, String[] info) {
		StringBuilder reqXml = new StringBuilder("<request><shopId>");
		reqXml.append(shopId).append("</shopId>");
		reqXml.append("<orderId>").append(orderNo).append("</orderId>");
		reqXml.append("<shippingDate>").append(shippingDate).append("</shippingDate>");
		reqXml.append("<shippingCarrier>").append(convertAUShippingCarrier(info[1])).append("</shippingCarrier>");
		reqXml.append("<shippingNumber>").append(info[0]).append("</shippingNumber>");
		reqXml.append("</request>");
		return reqXml.toString();
	}
	
	private String convertStatusToXml(String orderNo) {
		StringBuilder reqXml = new StringBuilder("<request><shopId>");
		reqXml.append(shopId).append("</shopId>");
		reqXml.append("<orderId>").append(orderNo).append("</orderId>");
		reqXml.append("<orderStatus>").append("完了").append("</orderStatus>");
		reqXml.append("</request>");
		return reqXml.toString();
	}
	
	private String convertAUShippingCarrier(String shippingCarrier) {
		String result;
		switch(shippingCarrier) {
		case "1001": result = "1";break;
		case "1002": result = "2";break;
		case "1003": result = "6";break;
		default : result = shippingCarrier;
		}
		return result;
	}
	
}
