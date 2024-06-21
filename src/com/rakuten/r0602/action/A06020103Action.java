package com.rakuten.r0602.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0602.bean.DetailListBean;
import com.rakuten.r0602.bean.OrderBean;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A06020103Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private File inputPath2 = null;
	private File inputPath4 = null;
	private String successFlg = null;
	private List<String> errmsg = new ArrayList<String>();

	protected void exec() throws Exception {
		List<OrderBean> orderList = new ArrayList<OrderBean>();

		if (inputPath2 != null) {
			orderList.addAll((A06020102Action.getOrders(inputPath2)));
		}
		if (inputPath4 != null) {
			orderList.addAll((A06020102Action.getOrders(inputPath4)));
		}
		sakujo(orderList);
	}

	private void sakujo(List<OrderBean> orderList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			for (int i = 0; i < orderList.size(); i++) {
				OrderBean order = orderList.get(i);
				for (int j = 0; j < orderList.get(i).getDetailList().size(); j++) {
					DetailListBean detail = order.getDetailList().get(j);
					String shouhinbango = detail.getShouhinbango();
					String kosu = detail.getKosu();
					sql = "SELECT STOCK_JP, STOCK_HANDUP FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);
					if (shouhinbango.contains("-")) {
						ps.setString(
								1,
								shouhinbango.substring(0,
										shouhinbango.indexOf("-")));
						ps.setString(2, shouhinbango.substring(shouhinbango
								.indexOf("-")));
					} else {
						ps.setString(1, shouhinbango);
						ps.setString(2, "");
					}
					rs = ps.executeQuery();
					String stockjp = "0";
					String stockhandup = "0";
					while (rs.next()) {
						stockjp = rs.getString("STOCK_JP");
						stockhandup = rs.getString("STOCK_HANDUP");

					}
					if (Integer.valueOf(stockjp) - Integer.valueOf(kosu) < 0) {
						errmsg.add(order.getJuchubango() + "中的"
								+ detail.getShouhinbango()
								+ "的库存不足！どういうことですかね！これ！");
						continue;
					}

					sql = "UPDATE TBL00012 SET STOCK_JP = ?, STOCK_HANDUP = ?, UPDATE_TIME = ? , UPDATE_USER = ?, UPDATEQUANTITY_FLG =TRUE WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);

					ps.setString(1, String.valueOf(Integer.valueOf(stockjp)	- Integer.valueOf(kosu)));
					ps.setString(2, String.valueOf(Integer.valueOf(stockhandup)	- Integer.valueOf(kosu)));
					ps.setString(3, Utility.getDateTime());
					ps.setString(4, Utility.getUser());
					if (shouhinbango.contains("-")) {
						ps.setString(5, shouhinbango.substring(0, shouhinbango.indexOf("-")));
						ps.setString(6, shouhinbango.substring(shouhinbango.indexOf("-")));
					} else {
						ps.setString(5, shouhinbango);
						ps.setString(6, "");
					}
					ps.execute();
				}

			}
			if (!Utility.isEmptyList(errmsg)) {
				for (int i = 0; i < errmsg.size(); i++) {
					addError(null, errmsg.get(i));
				}
			} else {
				successFlg = "1";
				conn.commit();
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
		setTitle("V060201:发货单生成");

	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the inputPath2
	 */
	public File getInputPath2() {
		return inputPath2;
	}

	/**
	 * @param inputPath2
	 *            the inputPath2 to set
	 */
	public void setInputPath2(File inputPath2) {
		this.inputPath2 = inputPath2;
	}

	/**
	 * @return the errmsg
	 */
	public List<String> getErrmsg() {
		return errmsg;
	}

	/**
	 * @param errmsg
	 *            the errmsg to set
	 */
	public void setErrmsg(List<String> errmsg) {
		this.errmsg = errmsg;
	}

	public File getInputPath4() {
		return inputPath4;
	}

	public void setInputPath4(File inputPath4) {
		this.inputPath4 = inputPath4;
	}

	public String getSuccessFlg() {
		return successFlg;
	}

	public void setSuccessFlg(String successFlg) {
		this.successFlg = successFlg;
	}

}
