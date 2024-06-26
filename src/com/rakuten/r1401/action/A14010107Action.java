package com.rakuten.r1401.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.xml.rpc.ServiceException;

import org.apache.axis.encoding.Base64;

import com.amazonaws.util.StringUtils;
import com.rakuten.common.MessageFromAU;
import com.rakuten.common.MessageFromRMS;
import com.rakuten.common.MessageFromYahoo;
import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.shop.AUShop;
import com.rakuten.shop.Shop;
import com.rakuten.shop.YahooShop;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

import batch.bean.StockBean;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.ExternalUserAuthModel;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateRequestExternalItem;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateRequestExternalModel;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateResponseExternalItem;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateResponseExternalModel;
import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.Inventoryapi;
import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.InventoryapiLocator;
import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.InventoryapiPort;
import shohinmodel.common.EventCommon;
import shohinmodel.common.Shohincommon;

public class A14010107Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String logKey = null;
	private int type = 0;
	private String commodityId = null;
	private File commodityIdFile = null;
	private Map<String, String> itemNoMapForUpdateStock = new HashMap<String, String>();
	
	@Override
	protected void init() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isValidated() throws Exception {
		if (3 == type && !StringUtils.hasValue(commodityId)) {
			addError(null, "未指定同步商品ID");
		}
		if (4 == type && Objects.isNull(commodityIdFile)) {
			addError(null, "未指定同步商品IDファイル");
		}
	}

	@Override
	protected void exec() throws Exception {
	
		String site = "Yahoo";
		List<String> shopList = getShopsBySite(site);
		List<StockBean> stockBeanList = getStockFromDB(type);
		if (Objects.isNull(stockBeanList) || stockBeanList.isEmpty()) {
			addError(null, "同步商品がない");
			return;
		}
		stockBeanList.stream().forEach(n->{
			itemNoMapForUpdateStock.put(n.getCommodity_id(), n.getCommodity_id());
		});
		
		for (String shop : shopList) {
			updateYahooOrderStockByShop(stockBeanList, shop);
		}
	
		site = "楽天";
		shopList = getShopsBySite(site);
		for (String shop : shopList) {
//			updateLottoOrderStockByShop(stockBeanList, shop);
			
			updateLottoOrderStockByShopV2(stockBeanList, shop);
		}
		
		site = "AU";
		shopList = getShopsBySite(site);
		for (String shop : shopList) {
			updateAUOrderStockByShop(stockBeanList, shop);
		}
		
		updateQuantityFlg(new ArrayList<String>(itemNoMapForUpdateStock.keySet()));
		
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}
	
	private List<String> getShopsBySite(String site) throws Exception {
		
		List<String> shopList = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "SELECT distinct SHOP_ID FROM rakuten.shop where site = ? and DELETE_FLG  is null";
			ps = conn.prepareStatement(sql);
			ps.setString(1, site);
			
			rs = ps.executeQuery();
			while (rs.next()) {
				shopList.add(rs.getString("SHOP_ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		
		return shopList;
	}
	
//	private List<StockBean> getStockFromDB(String site, String shop) throws Exception {
	private List<StockBean> getStockFromDB(int type) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StockBean> stockList = new ArrayList<StockBean>();
		List<String[]> unsochuArr = new ArrayList<String[]>();
		StockBean stockbean = null;
		try {
//			Map<String,Object> map =  ActionContext.getContext().getSession();
//			int companyId;
//			if (null == map.get("comp")) {
//				companyId = -1;
//			} else {
//				companyId = (int)map.get("comp");
//			}
			conn = JdbcConnection.getConnection();
//			String sql = "select t1.commodity_id,t1.detail_no,t1.comm_describe,t1.stock_jp,t1.stock_sh,t1.del_flg,t2.resp_person from tbl00012 t1 left join tbl00011 t2 on t1.commodity_id = t2.commodity_id where t1.UPDATEQUANTITY_FLG = TRUE AND t1.SITE = ? AND t1.SHOP = ?";
			String sql;
			if(type == 2) {
				sql = "select t1.commodity_id,t1.detail_no,t1.comm_describe,t1.stock_jp,t1.stock_sh,t1.stock_handup,t1.del_flg,t2.resp_person from tbl00012 t1 left join tbl00011 t2 on t1.commodity_id = t2.commodity_id where t1.SITE IN ('Yahoo','楽天','AU')";
			} else if (type == 1){
				sql = "select t1.commodity_id,t1.detail_no,t1.comm_describe,t1.stock_jp,t1.stock_sh,t1.stock_handup,t1.del_flg,t2.resp_person from tbl00012 t1 left join tbl00011 t2 on t1.commodity_id = t2.commodity_id where t1.UPDATEQUANTITY_FLG = TRUE AND t1.SITE IN ('Yahoo','楽天','AU')";
			} else if (type == 3) {
				sql = String.format("select t1.commodity_id,t1.detail_no,t1.comm_describe,t1.stock_jp,t1.stock_sh,t1.stock_handup,t1.del_flg,t2.resp_person from tbl00012 t1 left join tbl00011 t2 on t1.commodity_id = t2.commodity_id where t1.COMMODITY_ID = '%s' AND t1.SITE IN ('Yahoo','楽天','AU')", commodityId);
			} else {
				List<String[]> commodityIdList = null;
				commodityIdList = Utility.readCsvFileJpn(commodityIdFile, true);
				String commodityIds = "";
				if (!commodityIdList.isEmpty()) {
					commodityIds = commodityIdList.stream().map(d->d[0]).reduce((a,b)->a+","+b).get();
				}
				sql = String.format("select t1.commodity_id,t1.detail_no,t1.comm_describe,t1.stock_jp,t1.stock_sh,t1.stock_handup,t1.del_flg,t2.resp_person from tbl00012 t1 left join tbl00011 t2 on t1.commodity_id = t2.commodity_id where concat(t.COMMODITY_ID, t.DETAIL_NO) IN ('%s') AND t1.SITE IN ('Yahoo','楽天','AU')", commodityIds);
			}

			ps = conn.prepareStatement(sql);
//			ps.setString(1, site);
//			ps.setString(2, shop);

			rs = ps.executeQuery();
			while (rs.next()) {

				stockbean = new StockBean();
				stockList.add(stockbean);
				stockbean.setCommodity_id(rs.getString("commodity_id"));
				stockbean.setDetail_no(rs.getString("detail_no").replace("-0-0", ""));
				stockbean.setStock_jp((rs.getInt("stock_jp") - rs.getInt("stock_handup")) > 0 ? rs.getInt("stock_jp") - rs.getInt("stock_handup") : 0);
				stockbean.setStock_jp_kano(rs.getInt("stock_jp"));
				stockbean.setStock_sh(rs.getInt("stock_sh"));
				stockbean.setStock_sh_kano(rs.getInt("stock_sh"));
				stockbean.setStock_handup(rs.getInt("stock_handup"));
				stockbean.setNyukafukaFlg("0".equals(rs.getString("del_flg")) ? false : true);
				stockbean.setJinhuoshang(rs.getString("resp_person"));
				if (!Utility.isEmptyString(rs.getString("comm_describe"))) {
					String desc[] = rs.getString("comm_describe").split("\r\n");
					if (desc != null && desc.length > 0) {
						stockbean.setDetail_name_yoko(desc[0]);
					}
					if (desc != null && desc.length > 1) {
						stockbean.setDetail_name_shitaga(desc[1]);
					}
				}
			}

			sql = "select t2.commodity_id, t2.quantity from tbl00014 t2 left join tbl00013 t1 on t1.waybill_no = t2.waybill_no where t1.status = '00'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				unsochuArr.add(new String[] { rs.getString("commodity_id"), rs.getString("t2.quantity") });
			}

			OrderCommon orderCommon = new OrderCommon();
			OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
			List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean.getShouhinStsBeanList();

			for (StockBean stock : stockList) {
				String shohinbango = (stock.getCommodity_id()
						+ (Utility.isEmptyString(stock.getDetail_no()) ? "" : stock.getDetail_no()));
				for (ShouhinStsBean shohin : shouhinStsBeanList) {
					if (shohin.getShouhinbango().equalsIgnoreCase(shohinbango)) {
						stock.setStock_jp_kano(Integer.valueOf(shohin.getNokorikosuJp()));
						stock.setStock_sh_kano(Integer.valueOf(shohin.getNokorikosuSh()));
						stock.setStock_nyukachu_kano(Integer.valueOf(shohin.getNokorikosuNyuka()));
					}
				}
				for (String[] nyuka : unsochuArr) {
					if (nyuka[0].equals(shohinbango)) {
						if (stock.getStock_nyukachu_kano() < 0) {
							stock.setStock_nyukachu_kano(Integer.valueOf(nyuka[1]));
						}
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
		return stockList;
	}
	
	private String getSaleItemURL(String shop, String itemURL) {
		String[] coverSaleItemArr = new String[] {};

		for (String saleItem : coverSaleItemArr) {
			if (("copy" + itemURL).equals(saleItem)) {
				itemURL = "copy" + itemURL;
				break;
			}

		}
		return itemURL;

	}
	
	private void updateQuantityFlg(List<String> itemNoList) {
		Connection conn = null;
		PreparedStatement ps = null;
		if(itemNoList.isEmpty()) {
			
		} else {
			try {
				conn = JdbcConnection.getConnection();
				String sql = "UPDATE `rakuten`.`tbl00012` SET `UPDATEQUANTITY_FLG`=FALSE WHERE `COMMODITY_ID`=?;";
				ps = conn.prepareStatement(sql);
				for (String itemNo : itemNoList) {
					ps.setString(1, itemNo);
					ps.addBatch();
				}
				ps.executeBatch();
				ps.close();
				conn.commit();
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	// InventoryAPI 2.0对应
	private void updateLottoOrderStockByShopV2(List<StockBean> stockListDB, String shop) throws Exception {
		Shop rakutenShop = new Shop(shop);
		rakutenShop.updateStock(stockListDB);
		System.out.println("処理完了");
		List<MessageFromRMS> errMsgList = rakutenShop.getMessageFromRMS_UpdateStock();
		if (errMsgList.size() != 0)
			itemNoMapForUpdateStock.clear();
		for (MessageFromRMS msg : errMsgList) {
			addActionError("SITE:楽天,SHOP:"+shop+" " +"code:"+msg.getCode()+",message:"+msg.getMessage()+",metadata:"+msg.getMetadata());
		}
	}
	
	// InventoryAPI 2.0对应
	private void updateLottoOrderStockByShop(List<StockBean> stockListDB, String shop) throws Exception {

//		Map<String, String> itemNoMapForUpdateStock = new HashMap<String, String>();
		
		Inventoryapi locator = new InventoryapiLocator();
		InventoryapiPort port = null;

		try {
			port = locator.getinventoryapiPort();
		} catch (ServiceException e) {
			throw e;
		}
		Shohincommon common = new Shohincommon();
		String serviceSecret = common.getServiceSecret(shop);
		String licenseKey = common.getLicenseKey(shop);
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());

		ExternalUserAuthModel auth = new ExternalUserAuthModel();
		auth.setAuthKey(author);
		auth.setShopUrl(shop);
		auth.setUserName("dongtze");

		UpdateRequestExternalModel model = new UpdateRequestExternalModel();
		List<UpdateRequestExternalItem> updateList = new ArrayList<UpdateRequestExternalItem>();
		UpdateRequestExternalItem item = null;
		EventCommon eventCommon = new EventCommon();
		List<String> exceptListTrend = eventCommon.getTodayList("trend777");
		exceptListTrend.addAll(eventCommon.getTommorowList("trend777"));

		List<String> exceptListCover = eventCommon.getTodayList("coverforefront");
		exceptListCover.addAll(eventCommon.getTommorowList("coverforefront"));

		for (StockBean stockbean : stockListDB) {
			String tongyici = "";
			boolean hanbaikano = false;

			String itemurl = stockbean.getCommodity_id();
//			itemNoMapForUpdateStock.put(itemurl, itemurl);

			item = new UpdateRequestExternalItem();
			updateList.add(item);

			item.setHChoiceName(stockbean.getDetail_name_yoko());

			item.setVChoiceName(stockbean.getDetail_name_shitaga());

			int stock = 0;
			int nokiId = 0;
			if (stockbean.getStock_jp_kano() > 0) {
				stock = stockbean.getStock_jp_kano();
				// nokiId = Utility.getNokiId(shop, 1);
				nokiId = 1;
//			} else if (stockbean.getStock_unsochu_kano() > 0 || stockbean.getStock_sh_kano() > 0) {
			} else if (stockbean.getStock_unsochu_kano() > 0) {
				if (stockbean.getStock_unsochu_kano() > 0) {
					stock = stock + stockbean.getStock_unsochu_kano();
				}
//				if (stockbean.getStock_sh_kano() > 0) {
//					stock = stock + stockbean.getStock_sh_kano();
//				}
				// nokiId = Utility.getNokiId(shop, 3);
				nokiId = 2;
			} else {
				stock = 0;
				// nokiId = Utility.getJinhuoshangNoki(stockbean.getJinhuoshang());
				nokiId = 3;

			}
			item.setInventory(stock);

			item.setInventoryBackFlag(0);

			// item.setInventoryType(3);
			if (stockbean.getDetail_no().contains("-")) {
				item.setInventoryType(3);
			} else {
				item.setInventoryType(2);
			}

			item.setInventoryUpdateMode(1);

			if ("trend777".equals(shop) || "coverforefront".equals(shop)) {
				itemurl = getSaleItemURL(shop, itemurl);
				if (itemurl.contains("copy")) {
					tongyici = itemurl;
				}
				itemurl = itemurl.replace("copy", "");
			}

			item.setItemUrl(itemurl);

			item.setLackDeliveryDeleteFlag(false);

			// item.setLackDeliveryId(Utility.getJinhuoshangNoki(stockbean.getJinhuoshang()));
			item.setLackDeliveryId(3);

			item.setNokoriThreshold(0);

			item.setNormalDeliveryDeleteFlag(false);

			item.setNormalDeliveryId(nokiId);

			item.setOrderFlag(0);

			if (stockbean.isNyukafukaFlg()) {
				item.setOrderSalesFlag(1);
//			item.setLackDeliveryDeleteFlag(true);
//			item.setLackDeliveryId(7);
			} else {
				item.setOrderSalesFlag(2);
				item.setLackDeliveryId(2);
			}
			if (!Utility.isEmptyString(tongyici)) {
				itemurl = tongyici;

				item = new UpdateRequestExternalItem();
				updateList.add(item);

				item.setHChoiceName(stockbean.getDetail_name_yoko());

				item.setVChoiceName(stockbean.getDetail_name_shitaga());

				stock = 0;
				nokiId = 0;
				if (stockbean.getStock_jp_kano() > 0) {
					stock = stockbean.getStock_jp_kano();
//					nokiId = Utility.getNokiId(shop, 1);
					// １～２営業日以内に発送
					nokiId = 1;
//				} else if (stockbean.getStock_unsochu_kano() > 0 || stockbean.getStock_sh_kano() > 0) {
				} else if (stockbean.getStock_unsochu_kano() > 0) {
					if (stockbean.getStock_unsochu_kano() > 0) {
						stock = stock + stockbean.getStock_unsochu_kano();
					}
//					if (stockbean.getStock_sh_kano() > 0) {
//						stock = stock + stockbean.getStock_sh_kano();
//					}
//					nokiId = Utility.getNokiId(shop, 3);
					// ７～１０営業日以内に発送
					nokiId = 2;
				} else {
					stock = 0;
//					nokiId = Utility.getJinhuoshangNoki(stockbean.getJinhuoshang());
					// 売り切れ
					nokiId = 3;

				}
				item.setInventory(stock);

				item.setInventoryBackFlag(0);

				item.setInventoryType(3);

				item.setInventoryUpdateMode(1);

				item.setItemUrl(itemurl);

				item.setLackDeliveryDeleteFlag(false);

				item.setLackDeliveryId(Utility.getJinhuoshangNoki(stockbean.getJinhuoshang()));

				item.setNokoriThreshold(0);

				item.setNormalDeliveryDeleteFlag(false);

				item.setNormalDeliveryId(nokiId);

				item.setOrderFlag(0);

				if (stockbean.isNyukafukaFlg()) {
					item.setOrderSalesFlag(1);
//				item.setLackDeliveryDeleteFlag(true);
					item.setLackDeliveryId(7);
				} else {
					item.setOrderSalesFlag(2);
				}
			}
			// item.setRestTypeFlag(1);
		}

		List<UpdateResponseExternalItem[]> messageList = new ArrayList<UpdateResponseExternalItem[]>();
		List<UpdateRequestExternalItem> shoriList = updateList;
		while (true) {
			if (shoriList.size() > 400) {
				System.out.println("残り" + updateList.size());
//				Utility.addLog("残り" + updateList.size(), logKey);
				shoriList = updateList.subList(400, updateList.size());
				updateList = updateList.subList(0, 400);
				UpdateRequestExternalItem[] updateArr = new UpdateRequestExternalItem[updateList.size()];
				model.setUpdateRequestExternalItem(updateList.toArray(updateArr));
				UpdateResponseExternalModel result = null;
				try {
					result = port.updateInventoryExternal(auth, model);
				} catch (Exception e) {
					try {
						result = port.updateInventoryExternal(auth, model);
					} catch (Exception e1) {
						result = port.updateInventoryExternal(auth, model);
					}
				}
				if (result.getUpdateResponseExternalItem() != null
						&& result.getUpdateResponseExternalItem().length > 0) {
					messageList.addAll(Collections.singletonList(result.getUpdateResponseExternalItem()));
					for (UpdateResponseExternalItem res : result.getUpdateResponseExternalItem()) {
						System.out.println(res.getItemErrCode() + " " + res.getItemUrl() + " "
								+ res.getHChoiceName() + res.getVChoiceName() + " " + res.getItemErrMessage());
						Utility.addLog(res.getItemErrCode() + " " + res.getItemUrl() + " " + res.getHChoiceName()
								+ res.getVChoiceName() + " " + res.getItemErrMessage(), logKey);
					}
				}

				updateList = shoriList;
			} else {
				System.out.println("残り" + updateList.size());
				Utility.addLog("残り" + updateList.size(), logKey);
				UpdateRequestExternalItem[] updateArr = new UpdateRequestExternalItem[updateList.size()];
				model.setUpdateRequestExternalItem(updateList.toArray(updateArr));
				UpdateResponseExternalModel result = port.updateInventoryExternal(auth, model);
				if (result.getUpdateResponseExternalItem() != null
						&& result.getUpdateResponseExternalItem().length > 0) {
					messageList.addAll(Collections.singletonList(result.getUpdateResponseExternalItem()));
					for (UpdateResponseExternalItem res : result.getUpdateResponseExternalItem()) {
						System.out.println(res.getItemErrCode() + " " + res.getItemUrl() + " "
								+ res.getHChoiceName() + res.getVChoiceName() + " " + res.getItemErrMessage());
						Utility.addLog(res.getItemErrCode() + " " + res.getItemUrl() + " " + res.getHChoiceName()
								+ res.getVChoiceName() + " " + res.getItemErrMessage(), logKey);
					}
				}
				break;
			}
		}

		System.out.println("処理完了");
		List<String> errMsgList = new ArrayList<String>();
		for (UpdateResponseExternalItem[] messageArr : messageList) {
			for (UpdateResponseExternalItem message : messageArr) {
				errMsgList.add(message.getItemErrCode() + " " + message.getItemUrl() + " "
						+ message.getHChoiceName() + message.getVChoiceName() + " " + message.getItemErrMessage());
				itemNoMapForUpdateStock.remove(message.getItemUrl());
			}
		}
		for (String msg : errMsgList) {
			addActionError("SITE:楽天,SHOP:"+shop+" " +msg);
		}
		
//		updateQuantityFlg(new ArrayList<String>(itemNoMapForUpdateStock.keySet()));

	
	}
	
	private void updateYahooOrderStockByShop(List<StockBean> stockListDB, String shop) throws Exception {

//		Map<String, String> itemNoMapForUpdateStock = new HashMap<String, String>();
		
		StringBuilder item_code = new StringBuilder();
		StringBuilder quantity = new StringBuilder();
		StringBuilder allow_overdraft = new StringBuilder();
		String subCode;
		List<StockBean> stockList = stockListDB.size() > 1000 ? stockListDB.subList(0, 1000) : stockListDB;
		
		List<String> errMsgList = new ArrayList<String>();
		List<MessageFromYahoo> messageList = new ArrayList<>();
		while(!stockList.isEmpty()) {
			
			for (StockBean stockbean : stockList) {
	
				String itemurl = stockbean.getCommodity_id();
				if (!Pattern.matches("^[A-Za-z0-9-]+$", itemurl)) {
					MessageFromYahoo e = new MessageFromYahoo();
					e.setMessage("COMMODITY_ID:"+itemurl+"不正");
					e.setCode("");
					e.setOrderId("");
					messageList.add(e);
					continue;
				}
	//			itemNoMapForUpdateStock.put(itemurl, itemurl);
				String detailNo = stockbean.getDetail_no();
				if (!(null == detailNo || "".equals(detailNo))) {
					if (!Pattern.matches("^[A-Za-z0-9-]+$", detailNo)) {
						MessageFromYahoo e = new MessageFromYahoo();
						e.setMessage("COMMODITY_ID:"+itemurl+",DETAIL_NO:"+detailNo+"不正");
						e.setCode("");
						e.setOrderId("");
						messageList.add(e);
						continue;
					}
				}
				int stock = 0;
				if (stockbean.getStock_jp_kano() > 0) {
					stock = (stockbean.getStock_jp_kano() - stockbean.getStock_handup()) > 0 ? stockbean.getStock_jp_kano() - stockbean.getStock_handup() : 0;
	//			} else if (stockbean.getStock_unsochu_kano() > 0 || stockbean.getStock_sh_kano() > 0) {
				} else if (stockbean.getStock_unsochu_kano() > 0) {
					if (stockbean.getStock_unsochu_kano() > 0) {
						stock = stock + stockbean.getStock_unsochu_kano();
					}
	//				if (stockbean.getStock_sh_kano() > 0) {
	//					stock = stock + stockbean.getStock_sh_kano();
	//				}
				} else {
					stock = 0;
				}
	
				subCode = ((null == detailNo || "".equals(detailNo)) ? "" : (":" + itemurl + detailNo));
				if (0 == item_code.length()) {
					item_code.append(itemurl).append(subCode);
					quantity.append(stock);
					allow_overdraft.append(stockbean.isNyukafukaFlg()?0:1);
				} else {
					item_code.append(",").append(itemurl).append(subCode);
					quantity.append(",").append(stock);
					allow_overdraft.append(",").append(stockbean.isNyukafukaFlg()?0:1);
				}
			}
			YahooShop yahooShop = new YahooShop(shop);
			yahooShop.updateOrderStock(item_code.toString(), quantity.toString(), allow_overdraft.toString());
			messageList.addAll(yahooShop.getMessageFromYahooList_UpdateOrder());
			item_code.setLength(0);
			quantity.setLength(0);
			allow_overdraft.setLength(0);
//			if (Utility.isEmptyList(messageList)) {
//				
//			} else {
//				break;
//			}
			if (stockListDB.size() > 1000) {
				stockListDB = stockListDB.subList(1000, stockListDB.size());
				stockList = stockListDB.size() > 1000 ? stockListDB.subList(0, 1000) : stockListDB;
			} else {
				break;
			}

		}
		System.out.println("処理完了");
		if (Utility.isEmptyList(messageList)) {
//			updateQuantityFlg(new ArrayList<String>(itemNoMapForUpdateStock.keySet()));
			addError(null, "SITE:Yahoo,SHOP:"+shop+" 操作成功");
		} else {
			itemNoMapForUpdateStock.clear();
			for (MessageFromYahoo message : messageList) {
				System.out.println(message.getCode() + " " + message.getMessage());
				errMsgList.add(message.getCode() + " " + message.getMessage());
			}
			for (String msg : errMsgList) {
				addActionError("SITE:Yahoo,SHOP:"+shop+" " +msg);
			}
		}
		
	
	}
	
	private void updateAUOrderStockByShop(List<StockBean> stockListDB, String shop) throws Exception {
		
		List<StockBean> stockList = stockListDB.size() > 200 ? stockListDB.subList(0, 200) : stockListDB;
		List<String> errMsgList = new ArrayList<String>();
		List<MessageFromAU> messageList = new ArrayList<>();
		List<String> commodityIdList = new ArrayList<>();
		List<String> detailNoList = new ArrayList<>();
		List<String> quantityList = new ArrayList<>();
		while(!stockList.isEmpty()) {
			for (StockBean stockbean : stockList) {
				String commodityId = stockbean.getCommodity_id();
				if (!Pattern.matches("^[A-Za-z0-9-]+$", commodityId)) {
					MessageFromAU e = new MessageFromAU();
					e.setMessage("COMMODITY_ID:"+commodityId+"不正");
					e.setCode("");
					e.setOrderId("");
					messageList.add(e);
					continue;
				}
				String detailNo = stockbean.getDetail_no();
				if (!(null == detailNo || "".equals(detailNo))) {
					if (!Pattern.matches("^[A-Za-z0-9-]+$", detailNo)) {
						MessageFromAU e = new MessageFromAU();
						e.setMessage("COMMODITY_ID:"+commodityId+",DETAIL_NO:"+detailNo+"不正");
						e.setCode("");
						e.setOrderId("");
						messageList.add(e);
						continue;
					}
				}
				int stock = 0;
				if (stockbean.getStock_jp_kano() > 0) {
					stock = (stockbean.getStock_jp_kano() - stockbean.getStock_handup()) > 0 ? stockbean.getStock_jp_kano() - stockbean.getStock_handup() : 0;
				} else if (stockbean.getStock_unsochu_kano() > 0) {
					if (stockbean.getStock_unsochu_kano() > 0) {
						stock = stock + stockbean.getStock_unsochu_kano();
					}
				} else {
					stock = 0;
				}
				commodityIdList.add(commodityId);
				detailNoList.add(detailNo);
				quantityList.add(String.valueOf(stock));
			}
			AUShop auShop = new AUShop(shop);
			auShop.updateOrderStock(commodityIdList, detailNoList, quantityList);
			messageList.addAll(auShop.getMessageFromAUList());
			commodityIdList.clear();
			detailNoList.clear();
			quantityList.clear();
			
			if (stockListDB.size() > 200) {
				stockListDB = stockListDB.subList(200, stockListDB.size());
				stockList = stockListDB.size() > 200 ? stockListDB.subList(0, 200) : stockListDB;
			} else {
				break;
			}
		}
		System.out.println("処理完了");
		if (Utility.isEmptyList(messageList)) {
			addError(null, "SITE:AU,SHOP:"+shop+" 操作成功");
		} else {
			itemNoMapForUpdateStock.clear();
			for (MessageFromAU message : messageList) {
				System.out.println(message.getCode() + " " + message.getMessage());
				errMsgList.add(message.getCode() + " " + message.getMessage());
			}
			for (String msg : errMsgList) {
				addActionError("SITE:AU,SHOP:"+shop+" " +msg);
			}
		}
	}
	
	/**
	 * @return the logKey
	 */
	public String getLogKey() {
		return logKey;
	}

	/**
	 * @param logKey the logKey to set
	 */
	public void setLogKey(String logKey) {
		this.logKey = logKey;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the commodityId
	 */
	public String getCommodityId() {
		return commodityId;
	}

	/**
	 * @param commodityId the commodityId to set
	 */
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	/**
	 * @return the commodityIdFile
	 */
	public File getCommodityIdFile() {
		return commodityIdFile;
	}

	/**
	 * @param commodityIdFile the commodityIdFile to set
	 */
	public void setCommodityIdFile(File commodityIdFile) {
		this.commodityIdFile = commodityIdFile;
	}

}
