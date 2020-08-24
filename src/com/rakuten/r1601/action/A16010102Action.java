package com.rakuten.r1601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.rakuten.common.MessageFromYahoo;
import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.action.UpdateStock;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.r1601.form.F160101;
import com.rakuten.shop.YahooShop;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

import batch.bean.StockBean;

public class A16010102Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F160101 f160101 = null;

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
	
	protected void exec() throws Exception {
		if ("楽天".equals(f160101.getSite())) {
			UpdateStock updatestock = new UpdateStock();
			List<String> msgList = updatestock.exec(f160101.getShop(),
					Collections.singletonList(f160101.getShohinbango()));
			if (Utility.isEmptyList(msgList)) {
				addError(null, "操作成功");
			} else {
				for (String msg : msgList) {
					addError(null, msg);
				}
			}
		} else if ("Yahoo".equals(f160101.getSite())) {
			List<StockBean> stockListDB = getStockFromDB(f160101.getShop());
			StringBuilder item_code = new StringBuilder();
			StringBuilder quantity = new StringBuilder();
			boolean ariFlg = false;
			for (StockBean stockbean : stockListDB) {
				if (f160101.getShohinbango().equals(stockbean.getCommodity_id())) {
					ariFlg = true;
					String itemurl = stockbean.getCommodity_id();
					int stock = 0;
					if (stockbean.getStock_jp_kano() > 0) {
						stock = stockbean.getStock_jp_kano();
					} else if (stockbean.getStock_unsochu_kano() > 0 || stockbean.getStock_sh_kano() > 0) {
						if (stockbean.getStock_unsochu_kano() > 0) {
							stock = stock + stockbean.getStock_unsochu_kano();
						}
						if (stockbean.getStock_sh_kano() > 0) {
							stock = stock + stockbean.getStock_sh_kano();
						}
					} else {
						stock = 0;
					}
					if (0 == item_code.length()) {
						item_code.append(itemurl);
						quantity.append(stock);
					} else {
						quantity.append(",").append(stock);
					}
				}
			}
			if (!ariFlg) {
				addError(null, f160101.getShohinbango() + "无效的商品编号");
			} else {
				YahooShop yahooShop = new YahooShop(f160101.getShop());
				yahooShop.updateOrderStock(item_code.toString(), quantity.toString());
				
				System.out.println("処理完了");
				List<String> errMsgList = new ArrayList<String>();
				List<MessageFromYahoo> messageList = yahooShop.getMessageFromYahooList_UpdateOrder();
				for (MessageFromYahoo message : messageList) {
					System.out.println(message.getCode() + " " + message.getMessage());
					errMsgList.add(message.getCode() + " " + message.getMessage());
				}
				for (String msg : errMsgList) {
					addActionError(msg);
				}
			}
		}
	}

	protected void init() {
		setTitle("V160101:小工具");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F160101 getF160101() {
		return f160101;
	}

	public void setF160101(F160101 f160101) {
		this.f160101 = f160101;
	}

}
