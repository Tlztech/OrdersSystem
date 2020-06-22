package com.rakuten.r1302.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1302.common.A130201Common;
import com.rakuten.r1302.form.F130201;
import com.rakuten.r1302.form.OrderList;
import com.rakuten.util.JdbcConnection;

public class A13020103Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String result = "";
	private String shop = "";
	F130201 f130201 = null;

	protected void exec() throws Exception {
		A130201Common common = new A130201Common();
		List<String> bangoList = common.getHaneimachiList2();
		List<OrderList> heneimachiList = common.getOrderListByBango(bangoList);

		List<String> juchubangoList = new ArrayList<String>();

		for (OrderList order : heneimachiList) {
			if ("òSÃÏ".equals(order.getSite())) {
				if (order.getTenpo().equals(shop)) {
					juchubangoList.add(order.getChumonbango());
				}
			}
		}
		for (String bango : juchubangoList) {
			result = result + bango + "&";
		}
		List<String[]> dataList = getDenpyoAndKaisha(juchubangoList);
		result = result.substring(0, result.length() - 1) + "%%";
		for (String data[] : dataList) {
			result = result + data[0] + "&";
		}
		result = result.substring(0, result.length() - 1) + "%%";
		for (String data[] : dataList) {
			result = result + data[1] + "&";
		}
		result = result.substring(0, result.length() - 1).replace("&&", "&");

	}

	public List<String[]> getDenpyoAndKaisha(List<String> juchubangoList)
			throws Exception {
		List<String[]> dataList = new ArrayList<String[]>();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "SELECT Distinct TOIAWASEBANGO , UNSOKAISHA  FROM hassou_tbl WHERE JUCHUBANGO = ?";
			for (String juchubango : juchubangoList) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, juchubango);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					dataList.add(new String[] { rs.getString("TOIAWASEBANGO"),
							rs.getString("UNSOKAISHA") });
				}
			}
			return dataList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
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

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

}
