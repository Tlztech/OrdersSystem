package com.rakuten.common.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.apache.axis.encoding.Base64;

import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.Inventoryapi;
import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.InventoryapiLocator;
import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.InventoryapiPort;
import shohinmodel.common.Shohincommon;
import batch.bean.StockBean;

import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.ExternalUserAuthModel;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateRequestExternalItem;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateRequestExternalModel;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateResponseExternalItem;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateResponseExternalModel;

public class UpdateStock {

	public List<String> exec(String shop, List<String> shoribangoList) throws Exception {
		List<String> errMsgList = new ArrayList<String>();
		Inventoryapi locator = new InventoryapiLocator();
		InventoryapiPort port = null;

		try {
			port = locator.getinventoryapiPort();
		} catch (ServiceException e) {
			throw e;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String[]> tongyiciList = new ArrayList<String[]>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  tongbu_tongyici_tbl ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				tongyiciList.add(new String[] { rs.getString("shohinbango"), rs.getString("shohinbango_co"),
						rs.getString("hanbaikano") });
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		List<StockBean> stockListDB = getStockFromDB(shop);
		ExternalUserAuthModel auth = new ExternalUserAuthModel();
		Shohincommon common = new Shohincommon();
		String serviceSecret = common.getServiceSecret(shop);
		String licenseKey = common.getLicenseKey(shop);
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());

		auth.setAuthKey(author);
		auth.setShopUrl(shop);
		auth.setUserName("dongtze");

		UpdateRequestExternalModel model = new UpdateRequestExternalModel();
		List<UpdateRequestExternalItem> updateList = new ArrayList<UpdateRequestExternalItem>();
		UpdateRequestExternalItem item = null;

		for (String shohinbango : shoribangoList) {

			String tongyici = "";
			boolean hanbaikano = false;
			for (String[] shohinbango_coArr : tongyiciList) {
				String shohinbango_co = shohinbango_coArr[0];
				if (shohinbango_co.equals(shohinbango)) {
					tongyici = shohinbango_coArr[1];
					if ("1".equals(shohinbango_coArr[2])) {
						hanbaikano = true;
					}
				}
			}
			boolean ariFlg = false;
			for (StockBean stockbean : stockListDB) {
				if (shohinbango.equals(stockbean.getCommodity_id())) {
					ariFlg = true;
					String itemurl = stockbean.getCommodity_id();

					item = new UpdateRequestExternalItem();
					updateList.add(item);

					item.setHChoiceName(stockbean.getDetail_name_yoko());

					item.setVChoiceName(stockbean.getDetail_name_shitaga());

					int stock = 0;
					int nokiId = 0;
					if (stockbean.getStock_jp_kano() > 0) {
						stock = stockbean.getStock_jp_kano();
//						nokiId = Utility.getNokiId(shop, 1);
						nokiId = 1;
					} else if (stockbean.getStock_unsochu_kano() > 0 || stockbean.getStock_sh_kano() > 0) {
						if (stockbean.getStock_unsochu_kano() > 0) {
							stock = stock + stockbean.getStock_unsochu_kano();
						}
						if (stockbean.getStock_sh_kano() > 0) {
							stock = stock + stockbean.getStock_sh_kano();
						}
//						nokiId = Utility.getNokiId(shop, 3);
						nokiId = 2;
					} else {
						stock = 0;
//						nokiId = Utility.getJinhuoshangNoki(stockbean.getJinhuoshang());
						nokiId = 3;
					}
					item.setInventory(stock);

					item.setInventoryBackFlag(0);

//					item.setInventoryType(3);
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

//					item.setLackDeliveryId(Utility.getJinhuoshangNoki(stockbean.getJinhuoshang()));
					item.setLackDeliveryId(3);

					item.setNokoriThreshold(0);

					item.setNormalDeliveryDeleteFlag(false);

					item.setNormalDeliveryId(nokiId);

					item.setOrderFlag(0);

					if (stockbean.isNyukafukaFlg()) {
						item.setOrderSalesFlag(1);
//						item.setLackDeliveryDeleteFlag(true);
//						item.setLackDeliveryId(7);
					} else {
						item.setOrderSalesFlag(2);
					}

					// item.setRestTypeFlag(1);
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
							nokiId = Utility.getNokiId(shop, 1);
						} else if (stockbean.getStock_unsochu_kano() > 0 || stockbean.getStock_sh_kano() > 0) {
							if (stockbean.getStock_unsochu_kano() > 0) {
								stock = stock + stockbean.getStock_unsochu_kano();
							}
							if (stockbean.getStock_sh_kano() > 0) {
								stock = stock + stockbean.getStock_sh_kano();
							}
							nokiId = Utility.getNokiId(shop, 3);
						} else {
							stock = 0;
							nokiId = Utility.getJinhuoshangNoki(stockbean.getJinhuoshang());

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
//							item.setLackDeliveryDeleteFlag(true);
							item.setLackDeliveryId(7);
						} else {
							item.setOrderSalesFlag(2);
						}
					}
				}

			}
			if (!ariFlg) {
				errMsgList.add(shohinbango + "无效的商品编号");
			}
		}
		List<UpdateResponseExternalItem[]> messageList = new ArrayList<UpdateResponseExternalItem[]>();
		List<UpdateRequestExternalItem> shoriList = updateList;

		while (true) {
			if (shoriList.size() > 400) {
				System.out.println("残り" + updateList.size());
				shoriList = updateList.subList(400, updateList.size());
				updateList = updateList.subList(0, 400);
				UpdateRequestExternalItem[] updateArr = new UpdateRequestExternalItem[updateList.size()];
				model.setUpdateRequestExternalItem(updateList.toArray(updateArr));
				UpdateResponseExternalModel result = port.updateInventoryExternal(auth, model);
				if (result.getUpdateResponseExternalItem() != null
						&& result.getUpdateResponseExternalItem().length > 0) {
					messageList.addAll(Collections.singletonList(result.getUpdateResponseExternalItem()));
					for (UpdateResponseExternalItem res : result.getUpdateResponseExternalItem()) {
						System.out.println(res.getItemErrCode() + " " + res.getItemUrl() + " " + res.getHChoiceName()
								+ res.getVChoiceName() + " " + res.getItemErrMessage());

					}
				}

				updateList = shoriList;
			} else {
				System.out.println("残り" + updateList.size());

				UpdateRequestExternalItem[] updateArr = new UpdateRequestExternalItem[updateList.size()];
				model.setUpdateRequestExternalItem(updateList.toArray(updateArr));
				UpdateResponseExternalModel result = port.updateInventoryExternal(auth, model);
				if (result.getUpdateResponseExternalItem() != null
						&& result.getUpdateResponseExternalItem().length > 0) {
					messageList.addAll(Collections.singletonList(result.getUpdateResponseExternalItem()));
					for (UpdateResponseExternalItem res : result.getUpdateResponseExternalItem()) {
						System.out.println(res.getItemErrCode() + " " + res.getItemUrl() + " " + res.getHChoiceName()
								+ res.getVChoiceName() + " " + res.getItemErrMessage());

					}
				}
				break;
			}
		}
		System.out.println("処理完了");

		for (UpdateResponseExternalItem[] messageArr : messageList) {
			for (UpdateResponseExternalItem message : messageArr) {
				errMsgList.add(message.getItemErrCode() + " " + message.getItemUrl() + " " + message.getHChoiceName()
						+ message.getVChoiceName() + " " + message.getItemErrMessage());
			}
		}

		return errMsgList;
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

	private List<StockBean> getStockFromDB(String shop) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StockBean> stockList = new ArrayList<StockBean>();
		List<String[]> unsochuArr = new ArrayList<String[]>();
		StockBean stockbean = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "select t1.commodity_id,t1.detail_no,t1.comm_describe,t1.stock_jp,t1.stock_sh,t1.del_flg,t2.resp_person from tbl00012 t1 left join tbl00011 t2 on t1.commodity_id = t2.commodity_id";

			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				stockbean = new StockBean();
				stockList.add(stockbean);
				stockbean.setCommodity_id(rs.getString("commodity_id"));
				stockbean.setDetail_no(rs.getString("detail_no").replace("-0-0", ""));
				stockbean.setStock_jp(rs.getInt("stock_jp"));
				stockbean.setStock_jp_kano(rs.getInt("stock_jp"));
				stockbean.setStock_sh(rs.getInt("stock_sh"));
				stockbean.setStock_sh_kano(rs.getInt("stock_sh"));
				stockbean.setJinhuoshang(rs.getString("resp_person"));
				stockbean.setNyukafukaFlg("0".equals(rs.getString("del_flg")) ? false : true);
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
					if (shohin.getShouhinbango().equals(shohinbango)) {
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
}
