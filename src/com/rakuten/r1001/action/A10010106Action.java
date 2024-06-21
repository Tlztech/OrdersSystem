package com.rakuten.r1001.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.r0602.action.A06020102Action;
import com.rakuten.r0602.bean.OrderBean;
import com.rakuten.r1001.form.F100101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A10010106Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100101 f100101 = null;
	private File csvFile = null;
	private String successFlg = null;
	private List<String> errmsg = new ArrayList<String>();

	protected void exec() throws Exception {
		List<OrderBean> orderList = new ArrayList<OrderBean>();
		if (csvFile != null) {
			orderList.addAll(A06020102Action.getOrders(csvFile));
			checkCsv(orderList);
			if (Utility.isEmptyList(errmsg)) {
				sakujo(orderList);
			} else {
				for (String msg : errmsg) {
					addError(null, msg);
				}
			}
		}

	}

	private void checkCsv(List<OrderBean> orderList) throws Exception {
		OrderCommon orderCommon = new OrderCommon();
		OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean
				.getCommonOrderBeanList();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean
				.getShouhinStsBeanList();
		List<String> hasomachihasokaList = orderCommon.getHasomachiHasokaList(
				commonOrderBeanList, shouhinStsBeanList);

		for (OrderBean order : orderList) {
			boolean ariFlg = false;
			String juchubango = order.getJuchubango();
			for (String orderNo : hasomachihasokaList) {
				if (juchubango.equals(orderNo)) {
					ariFlg = true;
					break;
				}
			}
			if (!ariFlg) {
				errmsg.add(juchubango + "は普通発送可の注文ではありません");
			}
		}

	}

	private void sakujo(List<OrderBean> orderList) throws Exception {
		boolean result = true;

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			for (OrderBean order : orderList) {

				List<String[]> hasoList = new ArrayList<String[]>();
				String orderNo = order.getJuchubango();
				String toiawasebango = order.getToiawasebango();
				String unsokaisha = order.getHaisokaisha();
				String haisohoho = order.getHaisohoho();

				sql = "SELECT * FROM common_order_detail_tbl WHERE JUCHUBANGO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, orderNo);
				rs = ps.executeQuery();
				while (rs.next()) {
					hasoList.add(new String[] { rs.getString("SHOUHINBANGO"),
							rs.getString("KOSU") });
				}

				for (int i = 0; i < hasoList.size(); i++) {
					String shoribango = Utility.getShoribango();
					String[] shohin = hasoList.get(i);
					String shouhinbango = shohin[0];
					String kosu = shohin[1];
					sql = "SELECT STOCK_JP, STOCK_HANDUP FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, Utility.getCommodityId(shouhinbango));
					ps.setString(2, Utility.getDetailN0(shouhinbango));

					rs = ps.executeQuery();
					String stockjp = "0";
					String stockhandup = "0";
					while (rs.next()) {
						stockjp = rs.getString("STOCK_JP");
						stockhandup = rs.getString("STOCK_HANDUP");

					}
					if (Integer.valueOf(stockjp) - Integer.valueOf(kosu) < 0) {
						errmsg.add(shouhinbango + "在庫不足！");
						result = false;
						continue;
					}

					sql = "UPDATE TBL00012 SET STOCK_JP = ?, STOCK_HANDUP = ?, UPDATE_TIME = ? , UPDATE_USER = ?, UPDATEQUANTITY_FLG =TRUE WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);

					ps.setString(1, String.valueOf(Integer.valueOf(stockjp)	- Integer.valueOf(kosu)));
					ps.setString(2, String.valueOf(Integer.valueOf(stockhandup)	- Integer.valueOf(kosu)));
					ps.setString(3, Utility.getDateTime());
					ps.setString(4, Utility.getUser());
					ps.setString(5, Utility.getCommodityId(shouhinbango));
					ps.setString(6, Utility.getDetailN0(shouhinbango));

					ps.execute();

					int j = 1;
					sql = "INSERT INTO hassou_tbl (SHORIBANGO,JUCHUBANGO,SHOUHINBANGO,HASSOUKOSU,TOIAWASEBANGO,SHUBETSU,UNSOKAISHA,HAISOHOHO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(j++, shoribango);
					ps.setString(j++, orderNo);
					ps.setString(j++, shouhinbango);
					ps.setString(j++, kosu);
					ps.setString(j++, toiawasebango);
					ps.setString(j++, "0");
					ps.setString(j++, unsokaisha);
					ps.setString(j++, haisohoho);
					ps.setString(j++, Utility.getDateTime());
					ps.setString(j++, Utility.getUser());
					ps.setString(j++, Utility.getDateTime());
					ps.setString(j++, Utility.getUser());
					ps.execute();
				}

				sql = "UPDATE common_order_tbl SET CHUMONSTS1 = ? WHERE CHUMONBANGO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, "3");
				ps.setString(2, orderNo);
				ps.executeUpdate();
			}
			if (result) {
				conn.commit();
				successFlg = "1";
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	protected void init() {
		setTitle("V100101:注文一覧");

	}

	protected void isValidated() throws Exception {
		if (csvFile == null) {
			addError(null, "CSVファイルを選択してください");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F100101 getF100101() {
		return f100101;
	}

	public void setF100101(F100101 f100101) {
		this.f100101 = f100101;
	}

	/**
	 * @return the csvFile
	 */
	public File getCsvFile() {
		return csvFile;
	}

	/**
	 * @param csvFile
	 *            the csvFile to set
	 */
	public void setCsvFile(File csvFile) {
		this.csvFile = csvFile;
	}

	public String getSuccessFlg() {
		return successFlg;
	}

	public void setSuccessFlg(String successFlg) {
		this.successFlg = successFlg;
	}

}
