package batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import batch.bean.OrderStsDetailBean;

import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class UpdateOrderStatus extends TimerTask {
	/**
	 * 　ReceiveEmail类测试
	 */

	@Override
	public void run() {
		try {
			Connection conn = null;
			PreparedStatement ps = null;
			String sql = null;
			ResultSet rs = null;
			List<String> bangoList = new ArrayList<String>();
			try {
				conn = JdbcConnection.getConnection();
				sql = "SELECT chumonbango from common_order_tbl where chumonsts1 = '2'";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					bangoList.add(rs.getString("chumonbango"));
				}

				OrderCommon orderCommon = new OrderCommon();

				OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
				List<CommonOrderBean> commonOrderBeanList = orderInfoBean
						.getCommonOrderBeanList();
				List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean
						.getShouhinStsBeanList();

				// 入荷中
				List<String> nyukachu = orderCommon.getNyukachuList2(
						orderInfoBean, bangoList);
				// 運送中
				List<String> unsochu = orderCommon.getUnsochuOrderList2(
						orderInfoBean, bangoList);
				// 発送待ち-発送可
				List<String> hasomachihasokaList = orderCommon
						.getHasomachiHasokaList(commonOrderBeanList,
								shouhinStsBeanList);

				for (String bango : nyukachu) {

					String ststxtid = "";
					String[] ststxtidarr = null;
					List<String> shoriarr = new ArrayList<String>();
					sql = "select * from order_sts_tbl where juchubango = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, bango);
					rs = ps.executeQuery();
					if (rs.next()) {
						ststxtid = rs.getString("order_sts");
						ststxtidarr = ststxtid.split("&");
						for (String idarr : ststxtidarr) {
							String id = idarr.split("%")[0];
							if ("1".equals(id) || "2".equals(id)
									|| "3".equals(id) || "4".equals(id)) {
								shoriarr.add(id);
							}
						}
						if (!"2".equals(shoriarr.get(shoriarr.size() - 1))) {
							ststxtid = (ststxtid + "&2%" + Utility
									.getDateTime());
						}
						sql = "update order_sts_tbl set order_sts = ? ,update_time = ?, update_user = ? where juchubango = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, ststxtid);
						ps.setString(2, Utility.getDateTime());
						ps.setString(3, Utility.getUser());
						ps.setString(4, bango);
						ps.execute();
					} else {
						sql = "insert into order_sts_tbl values(?,?,?,?,?,?)";
						ps = conn.prepareStatement(sql);
						int j = 0;
						ps.setString(++j, bango);
						ps.setString(++j, "1%" + Utility.getDateTime() + "&2%"
								+ Utility.getDateTime());
						ps.setString(++j, Utility.getDateTime());
						ps.setString(++j, Utility.getUser());
						ps.setString(++j, Utility.getDateTime());
						ps.setString(++j, Utility.getUser());
						ps.execute();

					}
					conn.commit();
				}

				for (String bango : unsochu) {
					String ststxtid = "";
					String[] ststxtidarr = null;
					List<String> shoriarr = new ArrayList<String>();
					sql = "select * from order_sts_tbl where juchubango = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, bango);
					rs = ps.executeQuery();
					if (rs.next()) {
						ststxtid = rs.getString("order_sts");
						ststxtidarr = ststxtid.split("&");
						for (String idarr : ststxtidarr) {
							String id = idarr.split("%")[0];
							if ("1".equals(id) || "2".equals(id)
									|| "3".equals(id) || "4".equals(id)) {
								shoriarr.add(id);
							}
						}
						if (!"3".equals(shoriarr.get(shoriarr.size() - 1))) {
							ststxtid = (ststxtid + "&3%" + Utility
									.getDateTime());
						}
						sql = "update order_sts_tbl set order_sts = ? , update_time = ? ,update_user = ? where juchubango = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, ststxtid);
						ps.setString(2, Utility.getDateTime());
						ps.setString(3, Utility.getUser());
						ps.setString(4, bango);
						ps.execute();
					} else {
						sql = "insert into order_sts_tbl values(?,?,?,?,?,?)";
						ps = conn.prepareStatement(sql);
						int j = 0;
						ps.setString(++j, bango);

						ps.setString(
								++j,
								"1%" + Utility.getDateTime() + "&2%"
										+ Utility.getDateTime() + "&3%"
										+ Utility.getDateTime());
						ps.setString(++j, Utility.getDateTime());
						ps.setString(++j, Utility.getUser());
						ps.setString(++j, Utility.getDateTime());
						ps.setString(++j, Utility.getUser());
						ps.execute();

					}
					conn.commit();
				}

				for (String bango : hasomachihasokaList) {
					String ststxtid = "";
					String[] ststxtidarr = null;
					List<String> shoriarr = new ArrayList<String>();
					sql = "select * from order_sts_tbl where juchubango = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, bango);
					rs = ps.executeQuery();
					if (rs.next()) {
						ststxtid = rs.getString("order_sts");
						ststxtidarr = ststxtid.split("&");
						for (String idarr : ststxtidarr) {
							String id = idarr.split("%")[0];
							if ("1".equals(id) || "2".equals(id)
									|| "3".equals(id) || "4".equals(id)) {
								shoriarr.add(id);
							}
						}
						if (!"4".equals(shoriarr.get(shoriarr.size() - 1))) {
							ststxtid = (ststxtid + "&4%" + Utility
									.getDateTime());
						}
						sql = "update order_sts_tbl set order_sts = ?,  update_time = ? ,update_user = ? where juchubango = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, ststxtid);
						ps.setString(2, Utility.getDateTime());
						ps.setString(3, Utility.getUser());
						ps.setString(4, bango);
						ps.execute();
					} else {
						sql = "insert into order_sts_tbl values(?,?,?,?,?,?)";
						ps = conn.prepareStatement(sql);
						int j = 0;
						ps.setString(++j, bango);
						ps.setString(++j, "1%" + Utility.getDateTime() + "&4%"
								+ Utility.getDateTime());
						ps.setString(++j, Utility.getDateTime());
						ps.setString(++j, Utility.getUser());
						ps.setString(++j, Utility.getDateTime());
						ps.setString(++j, Utility.getUser());
						ps.execute();

					}
					conn.commit();
				}

				OrderStsDetailBean orderStsDetailBean = null;

				for (String orderNo : bangoList) {

					sql = "delete from order_sts_detail_tbl where juchubango = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, orderNo);
					ps.execute();
					conn.commit();

					CommonOrderBean thisOrder = null;
					for (CommonOrderBean order : commonOrderBeanList) {
						if (order.getJuchubango().equals(orderNo)) {
							thisOrder = order;
						}
					}
					if (thisOrder == null) {
						thisOrder = new CommonOrderBean();
					}

					List<String[]> hasomachiArr = orderCommon.getMachiListAll(
							thisOrder, shouhinStsBeanList, "2", "7");

					for (String[] hasomachi : hasomachiArr) {
						orderStsDetailBean = new OrderStsDetailBean();

						orderStsDetailBean.setShohinbango(hasomachi[0]);
						orderStsDetailBean.setKosu(hasomachi[1]);

						if ("入荷待ち".equals(hasomachi[2])
								&& !orderCommon.isNyukakano(hasomachi[0])) {
							orderStsDetailBean.setOrdersts("入荷不可");
						} else if ("入荷待ち".equals(hasomachi[2])
								|| "入荷中".equals(hasomachi[2])) {
							orderStsDetailBean.setOrdersts("入荷中");
						} else if ("運送中".equals(hasomachi[2])
								|| "上海在庫".equals(hasomachi[2])) {
							orderStsDetailBean.setOrdersts("倉庫へ運送中");
						} else if ("発送待ち".equals(hasomachi[2])) {
							orderStsDetailBean.setOrdersts("発送準備中");
						} else {
							orderStsDetailBean.setOrdersts("-----");
						}

						sql = "insert into order_sts_detail_tbl values(?,?,?,?,?,?,?,?)";
						ps = conn.prepareStatement(sql);
						int j = 0;
						ps.setString(++j, orderNo);
						ps.setString(++j, orderStsDetailBean.getShohinbango());
						ps.setString(++j, orderStsDetailBean.getKosu());
						ps.setString(++j, orderStsDetailBean.getOrdersts());
						ps.setString(++j, Utility.getDateTime());
						ps.setString(++j, Utility.getUser());
						ps.setString(++j, Utility.getDateTime());
						ps.setString(++j, Utility.getUser());
						ps.execute();
						conn.commit();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
