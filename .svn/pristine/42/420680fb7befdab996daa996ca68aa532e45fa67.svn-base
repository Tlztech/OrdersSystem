package com.rakuten.r1001.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.HasomachiList;
import com.rakuten.r1001.form.OrderList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class GetNoukiOkure extends TimerTask {

	/**
	 * 　ReceiveEmail类测试
	 */
	public void excute() throws Exception {
		List<String> bangoList = getHasomachiYoteibiariList();
		OrderCommon orderCommon = new OrderCommon();
		OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean
				.getShouhinStsBeanList();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean
				.getCommonOrderBeanList();
		List<String> okureList = new ArrayList<String>();

		for (String orderNo : bangoList) {
			CommonOrderBean thisOrder = null;
			for (CommonOrderBean order : commonOrderBeanList) {
				if (order.getJuchubango().equals(orderNo)) {
					thisOrder = order;
				}
			}
			// -10.入荷不可
			// -7.入荷待ち
			// -5.入荷中
			// -3.上海在庫
			// -1.運送中
			// 1.発送待ち
			List<Integer> nowStsList = new ArrayList<Integer>();

			List<String[]> hasomachiArr = orderCommon.getMachiListAll(
					thisOrder, shouhinStsBeanList, "2", "7");

			for (String[] hasomachi : hasomachiArr) {
				if ("入荷待ち".equals(hasomachi[2])
						&& !orderCommon.isNyukakano(hasomachi[0])) {
					nowStsList.add(-10);
				} else if ("入荷待ち".equals(hasomachi[2])) {
					nowStsList.add(-7);
				} else if ("入荷中".equals(hasomachi[2])) {
					nowStsList.add(-5);
				} else if ("上海在庫".equals(hasomachi[2])) {
					nowStsList.add(-3);
				} else if ("運送中".equals(hasomachi[2])) {
					nowStsList.add(-1);
				} else if ("発送待ち".equals(hasomachi[2])) {
					nowStsList.add(1);
				}
			}
			int nowSts = 2;
			for (Integer sts : nowStsList) {
				if (sts < nowSts) {
					nowSts = sts;
				}
			}
			if (-10 != nowSts) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				int betweenDays = Utility.daysBetween(new Date(),
						sdf.parse(thisOrder.getHasoyakusokubi()));
				if (betweenDays - nowSts <= 0) {
					okureList.add(orderNo);
				}
			}
		}

	}

	@Override
	public void run() {
		try {
			excute();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private List<String> getHasomachiYoteibiariList() throws Exception {
		List<String> bangoList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT t1.chumonbango bangot1 from common_order_tbl t1 left join tbl00027 t2 "
					+ "on t1.chumonbango = t2.chumonbango where HASOYAKUSOKUBI<> '' and t1.chumonsts1 = '2'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				bangoList.add(rs.getString("bangot1"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return bangoList;
	}

}
