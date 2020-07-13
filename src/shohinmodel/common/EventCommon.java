package shohinmodel.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.xml.rpc.ServiceException;

import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.Inventoryapi;
import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.InventoryapiLocator;
import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.InventoryapiPort;

import org.apache.axis.encoding.Base64;

import shohinmodel.bean.Item;
import shohinmodel.bean.Option;
import shohinmodel.bean.OptionValue;
import shohinmodel.bean.OptionValues;
import shohinmodel.bean.Options;
import batch.bean.StockBean;

import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.DetailListBean;
import com.rakuten.common.bean.OrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.ExternalUserAuthModel;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateRequestExternalItem;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateRequestExternalModel;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateResponseExternalItem;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateResponseExternalModel;

public class EventCommon {

	public List<String> getNoSaleShohinList(int noSellDays) throws Exception {
		List<String> noSaleList = new ArrayList<String>();
		List<OrderBean> orderList = null;
		String startDay = getStartDay(noSellDays);
		orderList = getOrderListFromDB(startDay);

		List<String[]> stockList = getStockList();

		noSaleList = compareList(orderList, stockList, "01");
		return noSaleList;
	}

	public List<String> getExceptList2() throws Exception {
		List<String> exceptList2 = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar date = Calendar.getInstance();

		String startDay1 = sdf.format(date.getTime());
		date.add(Calendar.DATE, 1);
		String startDay2 = sdf.format(date.getTime());
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  event_tbl where startdata = ? or startdata = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, startDay1);
			ps.setString(2, startDay2);
			rs = ps.executeQuery();
			while (rs.next()) {
				exceptList2.add(rs.getString("shohinbango"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return exceptList2;
	}

	public void addEventItemToDB(List<Item> itemList, String data, String shop)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();

			for (Item item : itemList) {
				boolean continueflg = false;
				int i = 0;
				sql = "select count(*) count from event_Tbl where shop = ? and startdata = ? and shohinbango = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(++i, shop);
				ps.setString(++i, data);
				ps.setString(++i, item.getItemUrl());
				rs = ps.executeQuery();
				while (rs.next()) {
					if (rs.getInt("COUNT") > 0) {
						continueflg = true;
					}
				}
				if (continueflg) {
					continue;
				}
				sql = "INSERT INTO event_tbl(shoribango,shop,startdata,shohinbango,motokakaku,hanbaikakaku,status,CREATE_TIME,CREATE_USER)VALUES(?,?,?,?,?,?,?,?,?);";
				ps = conn.prepareStatement(sql);

				i = 0;
				ps.setString(++i, Utility.getShoribango());
				ps.setString(++i, shop);
				ps.setString(++i, data);
				ps.setString(++i, item.getItemUrl());
				ps.setInt(++i, item.getItemPrice());
				ps.setInt(++i, item.getItemPrice() / 2);
				ps.setString(++i, "0");
				ps.setString(++i, Utility.getDateTime());
				ps.setString(++i, Utility.getUser());
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

	public void deleteTesterdayEventItem(String shop) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			conn = JdbcConnection.getConnection();
			sql = "update event_tbl set status = '1' where shop = ? and startdata < ? and status = '0'";
			ps = conn.prepareStatement(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

			Calendar date = Calendar.getInstance();
			date.add(Calendar.DATE, -1);
			String startdata = sdf.format(date.getTime());

			ps.setString(1, shop);
			ps.setString(2, startdata);

			ps.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
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
				stockbean
						.setNyukafukaFlg("0".equals(rs.getString("del_flg")) ? false
								: true);
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
				unsochuArr.add(new String[] { rs.getString("commodity_id"),
						rs.getString("t2.quantity") });
			}

			OrderCommon orderCommon = new OrderCommon();
			OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
			List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean
					.getShouhinStsBeanList();

			for (StockBean stock : stockList) {
				String shohinbango = (stock.getCommodity_id() + (Utility
						.isEmptyString(stock.getDetail_no()) ? "" : stock
						.getDetail_no()));
				for (ShouhinStsBean shohin : shouhinStsBeanList) {
					if (shohin.getShouhinbango().equals(shohinbango)) {
						stock.setStock_jp_kano(Integer.valueOf(shohin
								.getNokorikosuJp()));
						stock.setStock_sh_kano(Integer.valueOf(shohin
								.getNokorikosuSh()));
						stock.setStock_nyukachu_kano(Integer.valueOf(shohin
								.getNokorikosuNyuka()));
					}
				}
				for (String[] nyuka : unsochuArr) {
					if (nyuka[0].equals(shohinbango)) {
						if (stock.getStock_nyukachu_kano() < 0) {
							stock.setStock_nyukachu_kano(Integer
									.valueOf(nyuka[1]));
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

	public void setStock(String shop, List<String> shoribangoList,
			boolean kanoflg) throws Exception {

		List<String> errMsgList = new ArrayList<String>();
		Inventoryapi locator = new InventoryapiLocator();
		InventoryapiPort port = null;

		try {
			port = locator.getinventoryapiPort();
		} catch (ServiceException e) {
			throw e;
		}
		List<StockBean> stockListDB = getStockFromDB(shop);
		ExternalUserAuthModel auth = new ExternalUserAuthModel();
		auth.setAuthKey(Utility.getApiKey(shop));
		auth.setShopUrl(shop);
		auth.setUserName("dongtze");

		UpdateRequestExternalModel model = new UpdateRequestExternalModel();
		List<UpdateRequestExternalItem> updateList = new ArrayList<UpdateRequestExternalItem>();
		UpdateRequestExternalItem item = null;

		for (String shohinbango : shoribangoList) {
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
						nokiId = Utility.getNokiId(shop, 1);
					} else if (stockbean.getStock_unsochu_kano() > 0
							|| stockbean.getStock_sh_kano() > 0) {
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

					if ("trend777".equals(shop)
							|| "coverforefront".equals(shop)) {
						itemurl = getSaleItemURL(shop, itemurl);
					}

					item.setItemUrl(itemurl);

					item.setLackDeliveryDeleteFlag(false);

					item.setLackDeliveryId(Utility.getNokiId(shop, 7));

					item.setNokoriThreshold(0);

					item.setNormalDeliveryDeleteFlag(false);

					item.setNormalDeliveryId(nokiId);

					item.setOrderFlag(0);

					if (kanoflg) {
						if (stockbean.isNyukafukaFlg()) {
							item.setOrderSalesFlag(1);
							item.setLackDeliveryDeleteFlag(true);
						} else {
							item.setOrderSalesFlag(2);
						}
					} else {

						item.setOrderSalesFlag(1);
						item.setLackDeliveryDeleteFlag(true);
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
				UpdateRequestExternalItem[] updateArr = new UpdateRequestExternalItem[updateList
						.size()];
				model.setUpdateRequestExternalItem(updateList
						.toArray(updateArr));
				UpdateResponseExternalModel result = port
						.updateInventoryExternal(auth, model);
				if (result.getUpdateResponseExternalItem() != null
						&& result.getUpdateResponseExternalItem().length > 0) {
					messageList.addAll(Collections.singletonList(result
							.getUpdateResponseExternalItem()));
					for (UpdateResponseExternalItem res : result
							.getUpdateResponseExternalItem()) {
						System.out.println(res.getItemErrCode() + " "
								+ res.getItemUrl() + " " + res.getHChoiceName()
								+ res.getVChoiceName() + " "
								+ res.getItemErrMessage());

					}
				}

				updateList = shoriList;
			} else {
				System.out.println("残り" + updateList.size());

				UpdateRequestExternalItem[] updateArr = new UpdateRequestExternalItem[updateList
						.size()];
				model.setUpdateRequestExternalItem(updateList
						.toArray(updateArr));
				UpdateResponseExternalModel result = port
						.updateInventoryExternal(auth, model);
				if (result.getUpdateResponseExternalItem() != null
						&& result.getUpdateResponseExternalItem().length > 0) {
					messageList.addAll(Collections.singletonList(result
							.getUpdateResponseExternalItem()));
					for (UpdateResponseExternalItem res : result
							.getUpdateResponseExternalItem()) {
						System.out.println(res.getItemErrCode() + " "
								+ res.getItemUrl() + " " + res.getHChoiceName()
								+ res.getVChoiceName() + " "
								+ res.getItemErrMessage());

					}
				}
				break;
			}
		}
		System.out.println("処理完了");

		for (UpdateResponseExternalItem[] messageArr : messageList) {
			for (UpdateResponseExternalItem message : messageArr) {
				errMsgList.add(message.getItemErrCode() + " "
						+ message.getItemUrl() + " " + message.getHChoiceName()
						+ message.getVChoiceName() + " "
						+ message.getItemErrMessage());
			}
		}

	}

	private String getSaleItemURL(String shop, String itemURL) {
		String[] coverSaleItemArr = new String[] {};
		String[] trendSaleItemArr = new String[] { "salesjpj083" };

		if ("trend777".equals(shop)) {
			for (String saleItem : trendSaleItemArr) {
				if (("sale" + itemURL).equals(saleItem)) {
					itemURL = "sale" + itemURL;
					break;
				}
			}
		} else {
			for (String saleItem : coverSaleItemArr) {
				if (("sale" + itemURL).equals(saleItem)) {
					itemURL = "sale" + itemURL;
					break;
				}
			}
		}
		return itemURL;
	}

	public List<String> getYesterdayItemList(String shop) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> itemList = new ArrayList<String>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "select * from event_tbl where  shop = ? and startdata <= ? and status ='0'";
			ps = conn.prepareStatement(sql);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar date = Calendar.getInstance();
			date.add(Calendar.DATE, -1);
			String startdata = sdf.format(date.getTime());

			ps.setString(1, shop);
			ps.setString(2, startdata);
			rs = ps.executeQuery();
			while (rs.next()) {
				itemList.add(rs.getString("shohinbango"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return itemList;
	}

	public void updateEventItem(String shop, String itemurl,
			int displayPriceId, String startDataTime, String endDataTime)
			throws Exception {
		Shohincommon common = new Shohincommon();
		String itemxml = common.getShohinXmlFromDB(shop, itemurl);
		Item item = common.getItemBean(itemxml);
		// Select/Checkbox用項目情報リスト
		String updateXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		updateXml += "<request>";
		updateXml += "  <itemUpdateRequest>";
		updateXml += "    <item>";
		updateXml += ("      <itemUrl>" + item.getItemUrl() + "</itemUrl>");
		if (displayPriceId == 0) {
			updateXml += ("     <itemPrice>" + item.getItemPrice() + "</itemPrice>");
		} else if (displayPriceId == 1) {
			updateXml += ("     <itemPrice>" + item.getItemPrice() / 2 + "</itemPrice>");
		}

		Options options = item.getOptions();
		if (options != null) {
			updateXml += "<options>";

			// Select/Checkbox用項目情報
			List<Option> option_s = options.getOption();
			for (Option option : option_s) {
				updateXml += "<option>";

				// Select/Checkbox用項目名
				String optionName = option.getOptionName();
				if (!Utility.isEmptyString(optionName)) {
					updateXml += ("<optionName>" + optionName + "</optionName>");
				}
				// 選択肢スタイル
				int optionStyle = option.getOptionStyle();
				if (optionStyle != 0) {
					updateXml += ("<optionStyle>" + optionStyle + "</optionStyle>");
				}
				// Select/Checkbox用選択肢情報リスト
				OptionValues optionValues = option.getOptionValues();
				if (optionValues != null) {
					updateXml += "<optionValues>";
					// Select/Checkbox用選択肢情報
					List<OptionValue> optionValue_s = optionValues
							.getOptionValue();
					for (OptionValue optionValue : optionValue_s) {
						updateXml += "<optionValue>";
						// Select/Checkbox用選択肢
						String value = optionValue.getValue();
						if (!Utility.isEmptyString(value)) {
							updateXml += ("<value>" + value + "</value>");
						}
						updateXml += "</optionValue>";
					}

					updateXml += "</optionValues>";
				}

				updateXml += "</option>";
			}
			if (displayPriceId == 0) {
				updateXml = updateXml
						.replace(
								"<option><optionName>初期不良以外の場合は返品交換不可</optionName><optionStyle>1</optionStyle><optionValues><optionValue><value>了承済み</value></optionValue></optionValues></option>",
								"");
			} else if (displayPriceId == 1) {
				updateXml += "<option><optionName>初期不良以外の場合は返品交換不可</optionName><optionStyle>1</optionStyle><optionValues><optionValue><value>了承済み</value></optionValue></optionValues></option>";
			}

			updateXml += "</options>";
		}

		if (displayPriceId == 0) {
			// updateXml += ("<displayPrice></displayPrice>");
			updateXml += ("<isTimeSale>false</isTimeSale>");
		} else if (displayPriceId == 1) {
			updateXml += ("<displayPrice>" + item.getDisplayPrice() + "</displayPrice>");
			updateXml += ("<isTimeSale>true</isTimeSale>");
			updateXml += ("<timeSaleStartDateTime>" + startDataTime + "</timeSaleStartDateTime>");
			updateXml += ("<timeSaleEndDateTime>" + endDataTime + "</timeSaleEndDateTime>");
		}

		updateXml += ("<displayPriceId>" + displayPriceId + "</displayPriceId>");

		updateXml += "   </item>";
		updateXml += "   </itemUpdateRequest>";
		updateXml += "  </request>";

		URL url = new URL("https://api.rms.rakuten.co.jp/es/1.0/item/update");
		String serviceSecret = common.getServiceSecret(shop);
		String licenseKey = common.getLicenseKey(shop);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String author = "ESA "
				+ Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		// conn.setRequestProperty("contentType", "UTF-8");
		conn.setRequestProperty("Authorization", author);
		conn.setDoOutput(true);
		conn.setRequestProperty("Pragma:", "no-cache");
		conn.setRequestProperty("Cache-Control", "no-cache");
		conn.setRequestProperty("Content-Type", "text/xml");

		// 得到请求的输出流对象
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		// 把数据写入请求的Body
		out.write(new String(updateXml.getBytes("UTF-8")));

		System.out.println(new String(updateXml.getBytes("UTF-8")));
		out.flush();
		out.close();

		BufferedReader in = new BufferedReader(new InputStreamReader(
				conn.getInputStream()));
		String line = null;
		StringBuffer sb1 = new StringBuffer();
		while ((line = in.readLine()) != null) {
			sb1.append(line);
		}
		in.close();
		System.out.println(sb1.toString());

	}

	public List<String> getTodayList(String shop) throws Exception {
		List<String> todayList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar date = Calendar.getInstance();

		String startDay1 = sdf.format(date.getTime());

		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  event_tbl where startdata = ? and shop=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, startDay1);
			ps.setString(2, shop);
			rs = ps.executeQuery();
			while (rs.next()) {
				todayList.add(rs.getString("shohinbango"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return todayList;
	}

	public List<String> getTommorowList(String shop) throws Exception {
		List<String> todayList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar date = Calendar.getInstance();

		date.add(Calendar.DATE, 1);
		String startDay2 = sdf.format(date.getTime());
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  event_tbl where startdata = ? and shop = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, startDay2);
			ps.setString(2, shop);
			rs = ps.executeQuery();
			while (rs.next()) {
				todayList.add(rs.getString("shohinbango"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return todayList;
	}

	private List<String> compareList(List<OrderBean> checkedOrderList,
			List<String[]> stockList, String pType) {
		List<String> outList = new ArrayList<String>();
		List<String> ariList = new ArrayList<String>();
		for (int i = 0; i < stockList.size(); i++) {
			String[] stockInfo = stockList.get(i);
			for (int j = 0; j < checkedOrderList.size(); j++) {
				OrderBean orderBean = checkedOrderList.get(j);
				for (int k = 0; k < orderBean.getDetailList().size(); k++) {
					DetailListBean detailListBean = orderBean.getDetailList()
							.get(k);
					if (!Utility
							.isEmptyString(detailListBean.getShouhinbango())) {

						if (detailListBean.getShouhinbango().equals(
								stockInfo[0])) {
							ariList.add(stockInfo[0]);
						}
					}

				}
			}
		}

		for (int i = 0; i < stockList.size(); i++) {
			boolean ariFlg = false;
			for (int j = 0; j < ariList.size(); j++) {
				if (stockList.get(i)[0].equals(ariList.get(j))) {
					ariFlg = true;
					continue;
				}
			}
			if (!ariFlg) {
				outList.add(stockList.get(i)[0]);
				ariFlg = false;
			}

		}
		List<String> removeList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		if ("01".equals(pType)) {
			for (int i = 0; i < outList.size(); i++) {
				for (int j = 0; j < checkedOrderList.size(); j++) {
					OrderBean orderBean = checkedOrderList.get(j);
					for (int k = 0; k < orderBean.getDetailList().size(); k++) {
						DetailListBean detailListBean = orderBean
								.getDetailList().get(k);
						if (!Utility.isEmptyString(detailListBean
								.getShouhinbango())) {
							if (Utility.getCommodityId(
									detailListBean.getShouhinbango()).equals(
									Utility.getCommodityId(outList.get(i)))) {
								removeList.add(Utility
										.getCommodityId(detailListBean
												.getShouhinbango()));
							}
						}
					}
				}
			}

			boolean ariFlg = false;
			for (int i = 0; i < outList.size(); i++) {
				for (int j = 0; j < removeList.size(); j++) {
					if (outList.get(i).contains(removeList.get(j))) {
						ariFlg = true;
						continue;
					}
				}
				if (!ariFlg) {
					tempList.add(outList.get(i));
				}
				ariFlg = false;
			}
			outList = tempList;
		}
		return outList;
	}

	private String getStartDay(int days) throws ParseException {

		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date.add(Calendar.DATE, -days);
		String shiteibi = sdf.format(date.getTime());

		return shiteibi;
	}

	private List<String[]> getStockList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		List<String[]> stockList = new ArrayList<String[]>();
		try {
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM TBL00012 WHERE STOCK_JP > 0";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String commodityId = rs.getString("COMMODITY_ID");
				String detailNo = rs.getString("DETAIL_NO").replace("-0-0", "");
				String stockJp = rs.getString("STOCK_JP");
				String[] stockInfo = { commodityId + detailNo, stockJp };
				stockList.add(stockInfo);
			}
			// commit
			conn.commit();
			return stockList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	private List<OrderBean> getOrderListFromDB(String startDay)
			throws Exception {
		List<OrderBean> orderList = new ArrayList<OrderBean>();

		Connection conn = null;
		PreparedStatement ps = null;
		try {

			OrderBean order = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM common_order_tbl T1 LEFT JOIN common_order_detail_tbl T2 ON T1.CHUMONBANGO = T2.JUCHUBANGO LEFT JOIN HENPIN_TBL T3 ON T1.CHUMONBANGO = T3.JUCHUBANGO LEFT JOIN TUIKA_HASOU_TBL T4 ON T1.CHUMONBANGO = T4.JUCHUBANGO WHERE CHUMONNICHIJI >= ? ";

			ps = conn.prepareStatement(sql);
			ps.setString(1, startDay + " 00:00:00");

			ResultSet rs = ps.executeQuery();
			String chumonbangoComp = "";
			List<DetailListBean> detailList = null;
			DetailListBean detail = null;
			while (rs.next()) {
				if (!chumonbangoComp.equals(rs.getString("T1.CHUMONBANGO"))) {
					order = new OrderBean();

					order.setJuchubango(rs.getString("T1.CHUMONBANGO"));
					detailList = new ArrayList<DetailListBean>();
					order.setDetailList(detailList);
					orderList.add(order);
				}
				detail = new DetailListBean();
				detailList.add(detail);
				detail.setShouhinbango(rs.getString("T2.SHOUHINBANGO"));

				chumonbangoComp = rs.getString("T1.CHUMONBANGO");

			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return orderList;
	}
}
