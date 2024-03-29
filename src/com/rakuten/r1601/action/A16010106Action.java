package com.rakuten.r1601.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.datatype.DatatypeFactory;

import org.apache.axis.encoding.Base64;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rakuten.common.action.BaseAction;
import com.rakuten.util.Utility;

import jp.co.rakuten.rms.mall.order.api.client.AsyncReceiptModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.GetRequestIdResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.ItemModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService_Service;
import jp.co.rakuten.rms.mall.order.api.client.OrderModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderSearchModel;
import jp.co.rakuten.rms.mall.order.api.client.PackageModel;
import jp.co.rakuten.rms.mall.order.api.client.PersonModel;
import jp.co.rakuten.rms.mall.order.api.client.UnitErrorModel;
import jp.co.rakuten.rms.mall.order.api.client.UpdateOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.UserAuthModel;
import shohinmodel.common.Shohincommon;

public class A16010106Action extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String shop2 = null;
	private String logKey = null;

	@Override
	protected void init() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected void exec() throws Exception {
		String shop = "coverforefront";

		List<String> messageList = new ArrayList<String>();
		URL url = new URL("https://api.rms.rakuten.co.jp/es/2.0/order/searchOrder/");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		String endDay = sdf.format(date.getTime()) + "T23:59:59+0900";
		date.add(Calendar.DATE, -62);
		String startDay = sdf.format(date.getTime()) + "T00:00:00+0900";

		String searchxml = "{\"orderProgressList\":[\"100\"],\"dateType\":1,\"startDatetime\":\"" + startDay
				+ "\",\"endDatetime\":\"" + endDay
				+ "\",\"PaginationRequestModel\":{\"requestRecordsAmount\":1000,\"requestPage\":1,\"SortModelList\":[{\"sortColumn\":1,\"sortDirection\":1}]}}";

		ObjectMapper mapper = new ObjectMapper();
		String jsonStr1 = getJason(shop, url, searchxml);
		Map dataMap = mapper.readValue(jsonStr1, Map.class);
		List<Map> messageModelList = (ArrayList<Map>) dataMap.get("MessageModelList");

		if (!"INFO".equals(messageModelList.get(0).get("messageType"))) {
			messageList.add((String) messageModelList.get(0).get("message"));
		} else {
			List<String> orderNoList = (ArrayList<String>) dataMap.get("orderNumberList");

			Map<String, List<String>> requestMap = new HashMap<String, List<String>>();
			if (orderNoList.size() > 100) {
				orderNoList = orderNoList.subList(0, 30);
			}
			requestMap.put("orderNumberList", orderNoList);
			searchxml = mapper.writeValueAsString(requestMap);
			URL url2 = new URL("https://api.rms.rakuten.co.jp/es/2.0/order/getOrder/");
			String jsonStr2 = getJason(shop, url2, searchxml);
			System.out.println(jsonStr2);
			Map orderDataMap = mapper.readValue(jsonStr2, Map.class);
			List<String> cfmList = new ArrayList<String>();
			List<Map> orderModelList = (List<Map>) orderDataMap.get("OrderModelList");
			List<String> errorList = new ArrayList<String>();
			for (Map order : orderModelList) {
				String orderNo = (String) order.get("orderNumber");
				// 判断包邮类型
				// 1:宅急便包邮
				// 2:信封包邮
				// 3:不包邮(可以信封）
				// 4:不包邮（不能信封）
				int type = checkBaoyouNew(order);
				// 支払方法名
				Map settlementModel = (Map) order.get("SettlementModel");
				String settlement = (String) settlementModel.get("settlementMethod");
				// とど府県

				Map ordererModel = (Map) order.get("OrdererModel");
				String prefecture = (String) ordererModel.get("prefecture");
				Map deliveryModel = (Map) order.get("DeliveryModel");
				List<Map> packageModelList = (List<Map>) order.get("PackageModelList");
				List<Map> itemModelList = (List<Map>) packageModelList.get(0).get("ItemModelList");
				boolean shoudongFlg = false;
				if (1 == type) {
					if (prefecture.contains("沖縄")) {
						shoudongFlg = true;
						deliveryModel.put("deliveryName", "宅配便");
						packageModelList.get(0).put("postagePrice", 1000);
						for (Map item : itemModelList) {
							item.put("includePostageFlag", 0);
						}
					} else {
						deliveryModel.put("deliveryName", "宅配便");
						packageModelList.get(0).put("postagePrice", 0);
					}
				} else if (2 == type) {
					if (!settlement.equals("代金引換") && !settlement.equals("後払い決済")) {
						deliveryModel.put("deliveryName", "メール便");
						packageModelList.get(0).put("postagePrice", 0);

					} else {
						shoudongFlg = true;
						deliveryModel.put("deliveryName", "宅配便");

						for (Map item : itemModelList) {
							item.put("includeCashOnDeliveryPostageFlag", 0);
							item.put("includePostageFlag", 0);
						}
						if (prefecture.contains("沖縄")) {
							packageModelList.get(0).put("postagePrice", 1540);
						} else {
							packageModelList.get(0).put("postagePrice", 540);
						}

					}
				} else if (3 == type) {
					for (Map item : itemModelList) {
						item.put("includePostageFlag", 0);
					}
					if (!settlement.equals("代金引換") && !settlement.equals("後払い決済")) {
						deliveryModel.put("deliveryName", "メール便");
						packageModelList.get(0).put("postagePrice", 378);
					} else {
						shoudongFlg = true;
						for (Map item : itemModelList) {
							item.put("includeCashOnDeliveryPostageFlag", 0);
						}
						deliveryModel.put("deliveryName", "宅配便");
						if (prefecture.contains("沖縄")) {
							packageModelList.get(0).put("postagePrice", 1540);
						} else {
							packageModelList.get(0).put("postagePrice", 540);
						}
					}
				} else if (4 == type) {
					deliveryModel.put("deliveryName", "宅配便");
					if (prefecture.contains("沖縄")) {
						shoudongFlg = true;
						packageModelList.get(0).put("postagePrice", 1540);
					} else {
						packageModelList.get(0).put("postagePrice", 540);
					}
					for (Map item : itemModelList) {
						item.put("includePostageFlag", 0);
					}
				}
				for (Map item : itemModelList) {
					String shohinbango = (String) item.get("itemNumber");
					if (shohinbango.contains("nzfd")) {
						if (shohinbango.contains("nzfd-1000")) {
							item.put("price", 1000);
						} else if (shohinbango.contains("nzfd-200")) {
							item.put("price", 200);
						} else if (shohinbango.contains("nzfd-300")) {
							item.put("price", 300);
						} else if (shohinbango.contains("nzfd-4000")) {
							item.put("price", 4000);
						} else if (shohinbango.contains("nzfd-800")) {
							item.put("price", 800);
						}
					}
				}

				String updateOrderDeliveryResult = updateOrderDelivery(shop, order);
				System.out.println(updateOrderDeliveryResult);
				String updateOrderSenderResult = "INFO";
				if (shoudongFlg) {
					updateOrderSenderResult = updateOrderSender(shop, order);
					System.out.println(updateOrderSenderResult);
				}

				if (updateOrderDeliveryResult.contains("INFO") && updateOrderSenderResult.contains("INFO")) {
					cfmList.add(orderNo);
					Utility.addLog(orderNo, logKey);
				} else {
					messageList.add(orderNo + "处理失败");
					Utility.addLog(orderNo + "处理失败", logKey);
				}

			}
			if (!Utility.isEmptyList(cfmList)) {
				String result = confirmOrder(shop, cfmList);
			}
		}

//		AsyncReceiptModel response = port.updateOrder(auth, updatemodel);
		if (!Utility.isEmptyList(messageList)) {
			for (String message : messageList) {
				addError(null, message);
			}
		} else {
			addError(null, "success");
		}
	}

	private String updateOrderSender(String shop, Map order) throws IOException {
		URL url = new URL("https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderSender/");
		ObjectMapper mapper = new ObjectMapper();
		Shohincommon common = new Shohincommon();
		String serviceSecret = common.getServiceSecret(shop);
		String licenseKey = common.getLicenseKey(shop);
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization", author);
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.connect();// 得到请求的输出流对象
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		Map updateMap = new HashMap();

		String orderNo = (String) order.get("orderNumber");
		List<Map> packageModelList = (List<Map>) order.get("PackageModelList");
		updateMap.put("orderNumber", orderNo);
		updateMap.put("PackageModelList", packageModelList);

		out.write(new String(mapper.writeValueAsString(updateMap)));
		out.flush();
		out.close();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (Exception e) {
			e.toString();
		}
		if (in != null) {
			String line = null;
			StringBuffer sb1 = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb1.append(line);
			}
			in.close();
			return sb1.toString();
		} else
			return "error";
	}

	private String updateOrderDelivery(String shop, Map order) throws IOException {
		URL url = new URL("https://api.rms.rakuten.co.jp/es/2.0/order/updateOrderDelivery/");
		ObjectMapper mapper = new ObjectMapper();
		Shohincommon common = new Shohincommon();
		String serviceSecret = common.getServiceSecret(shop);
		String licenseKey = common.getLicenseKey(shop);
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization", author);
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.connect();// 得到请求的输出流对象
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		Map updateMap = new HashMap();

		String orderNo = (String) order.get("orderNumber");
		Map deliveryModel = (Map) order.get("DeliveryModel");
		String deliveryName = (String) deliveryModel.get("deliveryName");
		updateMap.put("orderNumber", orderNo);
		updateMap.put("deliveryName", deliveryName);

		out.write(new String(mapper.writeValueAsString(updateMap)));
		out.flush();
		out.close();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (Exception e) {
			e.toString();
		}
		if (in != null) {
			String line = null;
			StringBuffer sb1 = new StringBuffer();
			while ((line = in.readLine()) != null) {
				sb1.append(line);
			}
			in.close();
			return sb1.toString();
		} else
			return "error";
	}

	private String confirmOrder(String shop, List<String> orderNumberList) throws IOException {
		URL url = new URL("https://api.rms.rakuten.co.jp/es/2.0/order/confirmOrder/");
		ObjectMapper mapper = new ObjectMapper();
		Shohincommon common = new Shohincommon();
		String serviceSecret = common.getServiceSecret(shop);
		String licenseKey = common.getLicenseKey(shop);
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization", author);
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.connect();// 得到请求的输出流对象
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		Map updateMap = new HashMap();
		updateMap.put("orderNumberList", orderNumberList);
		out.write(new String(mapper.writeValueAsString(updateMap)));
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

	private String getJason(String shop, URL url, String xml) throws IOException {
		Shohincommon common = new Shohincommon();
		String serviceSecret = common.getServiceSecret(shop);
		String licenseKey = common.getLicenseKey(shop);
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestProperty("Authorization", author);
		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.connect();// 得到请求的输出流对象
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		out.write(new String(xml.getBytes("UTF-8")));

		System.out.println(new String(xml.getBytes("UTF-8")));
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

	private int checkBaoyou(OrderModel order) {
		// 取得全部商品
		List<ItemModel> itemList = order.getPackageModel().get(0).getItemModel();
		int takyubinmuryou = 0;
		int merubinmuryou = 0;
		int merubinfuka = 0;
		int itemcount = itemList.size();
		Long price = order.getGoodsPrice();
		for (ItemModel item : itemList) {
			String name = item.getItemName();
			String choice = item.getSelectedChoice();
			String shohinbango = item.getItemNumber();
			if (shohinbango.contains("nzfd")) {
				if (shohinbango.contains("nzfd-1000")) {
					merubinfuka++;
					item.setPrice(1000);
				} else if (shohinbango.contains("nzfd-200")) {
					item.setPrice(200);
				} else if (shohinbango.contains("nzfd-300")) {
					item.setPrice(300);
				} else if (shohinbango.contains("nzfd-4000")) {
					merubinfuka++;
					item.setPrice(4000);
				} else if (shohinbango.contains("nzfd-800")) {
					item.setPrice(800);
				}
			} else {
//				if ((name.contains("送料無料") && !name.contains("メール便送料無料")) || price >= 5480L) {
//					takyubinmuryou++;
//					continue;
//				} else if (name.contains("メール便送料無料")) {
//					merubinmuryou++;
//					continue;
//				} else if (choice.contains("メール便対応不可")) {
//					merubinfuka++;
//				}
			}
		}
		// 有一个宅急便包邮就整体包邮
		if (takyubinmuryou > 0) {
			return 1;
		} else if (merubinfuka == 0 && Double.valueOf(merubinmuryou) / Double.valueOf(itemcount) > 0.66) {
			return 2;
		} else if (merubinfuka == 0) {
			return 3;
		} else {
			return 4;
		}
	}

	private int checkBaoyouNew(Map order) {
		// 取得全部商品
		List<Map> packageModelList = (List<Map>) order.get("PackageModelList");
		Map packageModel = packageModelList.get(0);
		List<Map> itemList = (List<Map>) packageModel.get("ItemModelList");
		int takyubinmuryou = 0;
		int merubinmuryou = 0;
		int merubinfuka = 0;
		int itemcount = itemList.size();
		Integer price = (Integer) order.get("goodsPrice");
		for (Map item : itemList) {
			String name = (String) item.get("itemName");
			String choice = (String) item.get("selectedChoice");
			String shohinbango = (String) item.get("itemNumber");
//			if (shohinbango.contains("nzfd")) {
//				if (shohinbango.contains("nzfd-1000")) {
//					merubinfuka++;
//					item.setPrice(1000);
//				} else if (shohinbango.contains("nzfd-200")) {
//					item.setPrice(200);
//				} else if (shohinbango.contains("nzfd-300")) {
//					item.setPrice(300);
//				} else if (shohinbango.contains("nzfd-4000")) {
//					merubinfuka++;
//					item.setPrice(4000);
//				} else if (shohinbango.contains("nzfd-800")) {
//					item.setPrice(800);
//				}

//			if (shohinbango.contains("nzfd-1000")) {
//				merubinfuka++;
//			} else {
//				if ((name.contains("送料無料") && !name.contains("メール便送料無料")) || price >= 5480L) {
//					takyubinmuryou++;
//					continue;
//				} else if (name.contains("メール便送料無料")) {
//					merubinmuryou++;
//					continue;
//				} else if (choice.contains("メール便対応不可")) {
//					merubinfuka++;
//				}
//			}
		}
		// 有一个宅急便包邮就整体包邮
		if (takyubinmuryou > 0)

		{
			return 1;
		} else if (merubinfuka == 0 && Double.valueOf(merubinmuryou) / Double.valueOf(itemcount) > 0.66) {
			return 2;
		} else if (merubinfuka == 0) {
			return 3;
		} else {
			return 4;
		}
	}

	private List<OrderModel> checkDonkon(List<OrderModel> orderList) {
		List<OrderModel> shoriList = new ArrayList<OrderModel>();

		for (OrderModel order : orderList) {
			List<PackageModel> packageList = order.getPackageModel();
			String orderNo = order.getOrderNumber();
			PackageModel packageModel = packageList.get(0);
			PersonModel person = packageModel.getSenderModel();
			String name = person.getFamilyName() + person.getFirstName();
			String address = person.getPrefecture() + person.getCity() + person.getSubAddress();
			for (OrderModel order2 : orderList) {
				List<PackageModel> packageList2 = order2.getPackageModel();
				String orderNo2 = order2.getOrderNumber();
				PackageModel packageModel2 = packageList2.get(0);
				PersonModel person2 = packageModel2.getSenderModel();
				String name2 = person2.getFamilyName() + person2.getFirstName();
				String address2 = person2.getPrefecture() + person2.getCity() + person2.getSubAddress();

				if (!orderNo.equals(orderNo2) && name.equals(name2) && address.equals(address2)) {
					shoriList.add(order);
					break;
				}
			}

		}

		return shoriList;
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getLogKey() {
		return logKey;
	}

	public void setLogKey(String logKey) {
		this.logKey = logKey;
	}

	public String getShop2() {
		return shop2;
	}

	public void setShop2(String shop2) {
		this.shop2 = shop2;
	}

}
