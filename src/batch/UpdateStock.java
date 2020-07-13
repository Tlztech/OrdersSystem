package batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.rpc.ServiceException;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
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

public class UpdateStock extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String shop = null;

	private List<StockBean> getStockFromDB() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<StockBean> stockList = new ArrayList<StockBean>();
		List<String[]> unsochuArr = new ArrayList<String[]>();
		StockBean stockbean = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "select commodity_id,detail_no,comm_describe,stock_jp,stock_sh,del_flg from tbl00012";

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
				stockbean.setNyukafukaFlg("0".equals(rs.getString("del_flg")) ? false : true);
				String desc[] = rs.getString("comm_describe").split("\r\n");
				if (desc != null && desc.length > 0) {
					stockbean.setDetail_name_yoko(desc[0]);
				}
				if (desc != null && desc.length > 1) {
					stockbean.setDetail_name_shitaga(desc[1]);
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

	private String getSaleItemURL(String shop, String itemURL) {
		String[] coverSaleItemArr = new String[] {};
		String[] trendSaleItemArr = new String[] { "salesjpj083"

		};

		for (String saleItem : coverSaleItemArr) {
			if (("sale" + itemURL).equals(saleItem)) {
				itemURL = "sale" + itemURL;
				break;
			}
		}

		return itemURL;
	}

	@Override
	protected void init() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		Inventoryapi locator = new InventoryapiLocator();
		InventoryapiPort port = null;

		try {
			port = locator.getinventoryapiPort();
		} catch (ServiceException e) {
			throw e;
		}
		List<StockBean> stockListDB = getStockFromDB();
		ExternalUserAuthModel auth = new ExternalUserAuthModel();
		auth.setAuthKey(Utility.getApiKey(shop));
		auth.setShopUrl(shop);
		auth.setUserName("dongtze");

		UpdateRequestExternalModel model = new UpdateRequestExternalModel();
		List<UpdateRequestExternalItem> updateList = new ArrayList<UpdateRequestExternalItem>();
		UpdateRequestExternalItem item = null;

		for (StockBean stockbean : stockListDB) {
			item = new UpdateRequestExternalItem();
			updateList.add(item);

			item.setHChoiceName(stockbean.getDetail_name_yoko());

			item.setVChoiceName(stockbean.getDetail_name_shitaga());

			int stock = 0;
			int nokiId = 0;
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
				nokiId = Utility.getNokiId(shop, 7);

			}
			item.setInventory(stock);

			item.setInventoryBackFlag(0);

			item.setInventoryType(3);

			item.setInventoryUpdateMode(1);

			String itemurl = stockbean.getCommodity_id();
			if ("trend777".equals(shop) || "coverforefront".equals(shop)) {
				itemurl = getSaleItemURL(shop, itemurl);
			}
			item.setItemUrl(itemurl);

			item.setLackDeliveryDeleteFlag(false);

			item.setLackDeliveryId(Utility.getNokiId(shop, 7));

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

			// item.setRestTypeFlag(1);
		}

		List<UpdateResponseExternalItem[]> messageList = new ArrayList<UpdateResponseExternalItem[]>();
		List<UpdateRequestExternalItem> shoriList = updateList;
		while (true) {
			if (shoriList.size() > 400) {
				shoriList = updateList.subList(400, updateList.size() - 1);
				updateList = updateList.subList(0, 400);
				UpdateRequestExternalItem[] updateArr = new UpdateRequestExternalItem[updateList.size()];
				model.setUpdateRequestExternalItem(updateList.toArray(updateArr));
				UpdateResponseExternalModel result = port.updateInventoryExternal(auth, model);
				messageList.addAll(Collections.singletonList(result.getUpdateResponseExternalItem()));
				updateList = shoriList;
			} else {
				UpdateRequestExternalItem[] updateArr = new UpdateRequestExternalItem[updateList.size()];
				model.setUpdateRequestExternalItem(updateList.toArray(updateArr));
				UpdateResponseExternalModel result = port.updateInventoryExternal(auth, model);
				messageList.addAll(Collections.singletonList(result.getUpdateResponseExternalItem()));
				break;

			}
		}
		List<String> errMsgList = new ArrayList<String>();
		for (UpdateResponseExternalItem[] messageArr : messageList) {
			for (UpdateResponseExternalItem message : messageArr) {
				errMsgList.add(message.getItemErrCode() + " " + message.getItemUrl() + " " + message.getHChoiceName()
						+ message.getVChoiceName() + " " + message.getItemErrMessage());
			}
		}
		for (String msg : errMsgList) {
			addActionError(msg);
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

}
