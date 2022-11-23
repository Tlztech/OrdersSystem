package com.rakuten.r1302.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1001.form.TuikaList;
import com.rakuten.r1302.form.OrderList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.r1302.common.A130201Common;
import com.rakuten.r1302.form.F130201;
import com.rakuten.r1302.form.MidownloadrakutenList;

public class A13020101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F130201 f130201 = null;

	protected void exec() throws Exception {
		f130201 = new F130201();
		A130201Common a130201Common = new A130201Common();
		a130201Common.setDisplay(f130201);
		List<MidownloadrakutenList> downList = new ArrayList<MidownloadrakutenList>();
		MidownloadrakutenList rakutenList = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		List<OrderList> orderList_temp = f130201.getOrderList();
		List<OrderList> orderList = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT SHOP_NAME,SHOP_NO FROM shop where SITE = 'òSÃÏ'  and DELETE_FLG is null order by SHOP_NAME ASC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				rakutenList = new MidownloadrakutenList();
				downList.add(rakutenList);
				rakutenList.setShopName(rs.getString("SHOP_NAME"));
				rakutenList.setShopNumber(rs.getString("SHOP_NO"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		f130201.setMidownloadList(downList);
		setSessionAttribute("orderList", orderList_temp);
		if (orderList_temp.size() > 15) {
			orderList = orderList_temp.subList(0, 15);
		} else {
			orderList = orderList_temp;
		}
		
		f130201.setOrderList(orderList);
		setNowPage("1");
		setAllPage(String
				.valueOf(((double) orderList_temp.size() / (double) 15) > (orderList_temp
						.size() / 15) ? orderList_temp.size() / 15 + 1
						: orderList_temp.size() / 15));
		setGoPage("");
	}

	protected void init() throws Exception {
		setTitle("V130201:∞kÀÕÑI¿Ì“ª”E");
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the f130201
	 */
	public F130201 getF130201() {
		return f130201;
	}

	/**
	 * @param f130201
	 *            the f130201 to set
	 */
	public void setF130201(F130201 f130201) {
		this.f130201 = f130201;
	}

}
