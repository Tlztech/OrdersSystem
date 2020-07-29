package com.rakuten.r1301.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.r1301.form.F130101;
import com.rakuten.r1301.form.OrderList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A130101Common {
	public F130101 setDisplay(F130101 f130101) throws Exception {
		if (f130101 == null) {
			f130101 = new F130101();
		}

		OrderCommon orderCommon = new OrderCommon();
		OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean.getCommonOrderBeanList();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean.getShouhinStsBeanList();
		// 発送待ち-発送可
		List<String> hasomachihasokaList = orderCommon.getHasomachiHasokaList(commonOrderBeanList, shouhinStsBeanList);
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> sessionMap = actionContext.getSession();
		sessionMap.put("hasomachiList", hasomachihasokaList);

		// 追加発送待ち-発送可
		List<String> tuikahasomachihasokaList = orderCommon.getTuikahasomachiHasokaList(commonOrderBeanList,
				shouhinStsBeanList);

		// 分納発送待ち-発送可
		List<String> bunnohasomachihasokaList = orderCommon.getBunnohasomachiHasokaList(commonOrderBeanList,
				shouhinStsBeanList);

		// 発送待ち-至急-発送可
		List<String> hasomachishikyuhasokaList = orderCommon.getHasomachiHasokashikyuList(commonOrderBeanList,
				shouhinStsBeanList);

		// 追加発送待ち-至急-発送可
		List<String> tuikahasomachishikyuhasokaList = orderCommon.getTuikahasomachiHasokashikyuList(commonOrderBeanList,
				shouhinStsBeanList);

		// 分納発送待ち-至急-発送可
		List<String> bunnohasomachishikyuhasokaList = orderCommon.getBunnohasomachishikyuHasokaList(commonOrderBeanList,
				shouhinStsBeanList);

		String hasoshubetsu = f130101.getHasoshubetsu();

		List<String> juchubangoListHasomachihasoka = null;
		List<String> juchubangoListTuikahasomachihasoka = null;
		List<String> juchubangoListBunnohasomachihasoka = null;
		List<String> juchubangoListHasomachishikyuhasoka = null;
		List<String> juchubangoListTuikahasomachishikyuhasoka = null;
		List<String> juchubangoListBunnohasomachishikyuhasoka = null;

		// 発送待ち-発送可

		juchubangoListHasomachihasoka = hasomachihasokaList;

		// 追加発送待ち-発送可

		juchubangoListTuikahasomachihasoka = tuikahasomachihasokaList;

		// 分納発送待ち-発送可

		juchubangoListBunnohasomachihasoka = bunnohasomachihasokaList;

		// 発送待ち-至急-発送可

		juchubangoListHasomachishikyuhasoka = hasomachishikyuhasokaList;

		// 追加発送待ち-至急-発送可

		juchubangoListTuikahasomachishikyuhasoka = tuikahasomachishikyuhasokaList;

		// 分納発送待ち-至急-発送可

		juchubangoListBunnohasomachishikyuhasoka = bunnohasomachishikyuhasokaList;

		List<OrderList> orderListHasomachihasoka = getOrderListByBango(juchubangoListHasomachihasoka);
		List<OrderList> orderListTuikahasomachihasoka = getOrderListByBango(juchubangoListTuikahasomachihasoka);
		List<OrderList> orderListBunnohasomachihasoka = getOrderListByBango(juchubangoListBunnohasomachihasoka);
		List<OrderList> orderListHasomachishikyuhasoka = getOrderListByBango(juchubangoListHasomachishikyuhasoka);
		List<OrderList> orderListTuikahasomachishikyuhasoka = getOrderListByBango(
				juchubangoListTuikahasomachishikyuhasoka);
		List<OrderList> orderListBunnohasomachishikyuhasoka = getOrderListByBango(
				juchubangoListBunnohasomachishikyuhasoka);

		List<OrderList> orderList = new ArrayList<OrderList>();

		List<OrderList> orderListHasomachihasokaResult = searchOrder(orderListHasomachihasoka, f130101);
		List<OrderList> orderListTuikahasomachihasokaResult = searchOrder(orderListTuikahasomachihasoka, f130101);
		List<OrderList> orderListBunnohasomachihasokaResult = searchOrder(orderListBunnohasomachihasoka, f130101);
		List<OrderList> orderListHasomachishikyuhasokaResult = searchOrder(orderListHasomachishikyuhasoka, f130101);
		List<OrderList> orderListTuikahasomachishikyuhasokaResult = searchOrder(orderListTuikahasomachishikyuhasoka,
				f130101);
		List<OrderList> orderListBunnohasomachishikyuhasokaResult = searchOrder(orderListBunnohasomachishikyuhasoka,
				f130101);

		// 発送待ち-発送可
		if ("1".equals(hasoshubetsu)) {
			orderList.addAll(orderListHasomachihasokaResult);
		}
		// 追加発送待ち-発送可
		else if ("2".equals(hasoshubetsu)) {
			orderList.addAll(orderListTuikahasomachihasokaResult);
		}
		// 分納発送待ち-発送可
		else if ("3".equals(hasoshubetsu)) {
			orderList.addAll(orderListBunnohasomachihasokaResult);
		}
		// 発送待ち-至急-発送可
		else if ("4".equals(hasoshubetsu)) {
			orderList.addAll(orderListHasomachishikyuhasokaResult);
		}
		// 追加発送待ち-至急-発送可
		else if ("5".equals(hasoshubetsu)) {
			orderList.addAll(orderListTuikahasomachishikyuhasokaResult);
		}
		// 分納発送待ち-至急-発送可
		else if ("6".equals(hasoshubetsu)) {
			orderList.addAll(orderListBunnohasomachishikyuhasokaResult);
		}

		f130101.setHasomachihasokaCount(String.valueOf(orderListHasomachihasokaResult.size()));
		f130101.setTuikahasomachihasokaCount(String.valueOf(orderListTuikahasomachihasokaResult.size()));
		f130101.setBunnohasomachihasokaCount(String.valueOf(orderListBunnohasomachihasokaResult.size()));
		f130101.setHasomachishikyuhasokaCount(String.valueOf(orderListHasomachishikyuhasokaResult.size()));
		f130101.setTuikahasomachishikyuhasokaCount(String.valueOf(orderListTuikahasomachishikyuhasokaResult.size()));
		f130101.setBunnohasomachishikyuhasokaCount(String.valueOf(orderListBunnohasomachishikyuhasokaResult.size()));

		// if (orderListTuikahasomachihasokaResult.size() > 0 ||
		// orderListBunnohasomachihasokaResult.size() > 0
		// || orderListHasomachishikyuhasokaResult.size() > 0
		// || orderListTuikahasomachishikyuhasokaResult.size() > 0
		// || orderListBunnohasomachishikyuhasokaResult.size() > 0) {
		// if ("1".equals(hasoshubetsu)) {
		// orderList = new ArrayList<OrderList>();
		// }
		// }

		int rakutenCount = 0;
		int yahooShoppingCount = 0;
		int denaCount = 0;
		int yafuokuCount = 0;
		int qoo10Count = 0;
		int ponpareCount = 0;
		int otherCount = 0;

		String tenposhubetsu = f130101.getTenposhubetsu();

		List<OrderList> shoriList = new ArrayList<OrderList>();
		for (OrderList order : orderList) {
			if ("楽天".equals(order.getSite())) {
				rakutenCount++;
			} else if ("Yahoo Shopping".equals(order.getSite())) {
				yahooShoppingCount++;
			} else if ("DENA".equals(order.getSite())) {
				denaCount++;
			} else if ("ヤフオク".equals(order.getSite())) {
				yafuokuCount++;
			} else if ("ポンパレモール".equals(order.getSite())) {
				ponpareCount++;
			} else if ("qoo10".equals(order.getSite())) {
				qoo10Count++;
			}else {
				otherCount++;
			}

			if ("1".equals(tenposhubetsu)) {
				if ("楽天".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
			if ("2".equals(tenposhubetsu)) {
				if ("Yahoo Shopping".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
			if ("3".equals(tenposhubetsu)) {
				if ("DENA".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
			if ("4".equals(tenposhubetsu)) {
				if ("ヤフオク".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
			if ("5".equals(tenposhubetsu)) {
				if ("ポンパレモール".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
			if ("6".equals(tenposhubetsu)) {
				if ("qoo10".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
			if ("7".equals(tenposhubetsu)) {
				if (!"楽天".equals(order.getSite()) && !"Yahoo Shopping".equals(order.getSite()) 
						&& !"DENA".equals(order.getSite()) && !"ヤフオク".equals(order.getSite()) 
						&& !"ポンパレモール".equals(order.getSite()) && !"qoo10".equals(order.getSite())) {
					shoriList.add(order);
				}
			}
		}

		f130101.setRakutenCount(String.valueOf(rakutenCount));
		f130101.setYahooShoppingCount(String.valueOf(yahooShoppingCount));
		f130101.setDenaCount(String.valueOf(denaCount));
		f130101.setYafuokuCount(String.valueOf(yafuokuCount));
		f130101.setPonpareCount(String.valueOf(ponpareCount));
		f130101.setQoo10Count(String.valueOf(qoo10Count));
		f130101.setOtherCount(String.valueOf(otherCount));
		Comparator<OrderList> comparator = new Comparator<OrderList>() {
			@Override
			public int compare(OrderList o1, OrderList o2) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date o1Date = df.parse(o1.getChumonichiji());
					Date o2Date = df.parse(o2.getChumonichiji());
					return o1Date.compareTo(o2Date);

				} catch (ParseException e) {
					e.printStackTrace();
				}
				return -1;
			}
		};
		Collections.sort(shoriList, comparator);

		f130101.setOrderList(shoriList);
		return f130101;
	}

	private List<OrderList> searchOrder(List<OrderList> orderList, F130101 f130101) {
		List<OrderList> shoriList = new ArrayList<OrderList>();

		for (OrderList order : orderList) {
			// 检查店铺名
			if (!Utility.isEmptyString(f130101.getTenpo())) {
				if (!f130101.getTenpo().equals(order.getTenpo())) {
					continue;
				}
			}
			// 订单时间
			if (!Utility.isEmptyString(f130101.getKikanStart())) {
				if (Integer.valueOf(f130101.getKikanStart().replace("-", "")) > Integer
						.valueOf(order.getChumonichiji().substring(0, 10).replace("-", ""))) {
					continue;
				}
			}
			if (!Utility.isEmptyString(f130101.getKikanEnd())) {
				if (Integer.valueOf(f130101.getKikanEnd().replace("-", "")) < Integer
						.valueOf(order.getChumonichiji().substring(0, 10).replace("-", ""))) {
					continue;
				}
			}
			// 受注番号
			if (!Utility.isEmptyString(f130101.getChumonbango())) {
				if (!f130101.getChumonbango().trim().equals(order.getChumonbango())) {
					continue;
				}
			}
			// 送付先名前
			if (!Utility.isEmptyString(f130101.getSofusakinamae())) {
				if (!order.getSofusakinamae().replace("　", " ")
						.contains(f130101.getSofusakinamae().replace("　", " ").trim())) {
					continue;
				}
			}
			// 送付先電話番号
			if (!Utility.isEmptyString(f130101.getSofusakidenwabango())) {
				if (!f130101.getSofusakidenwabango().replace("-", "").trim()
						.equals(order.getSofusakidenwabango().replace("-", ""))) {
					continue;
				}
			}
			// お支払い方法
			if (!Utility.isEmptyString(f130101.getOshiharaihoho())) {
				if (!f130101.getOshiharaihoho().equals(order.getOshiharaihoho())) {
					continue;
				}
			}

			// 配送方法
			if (!Utility.isEmptyString(f130101.getHaisohoho())) {
				if (!f130101.getHaisohoho().equals(order.getHaisohoho())) {
					continue;
				}
			}
			// コメント注意
			if (!Utility.isEmptyString(f130101.getChuyi())) {
				if (Utility.isEmptyString((order.getComento().trim().replace("[配送日時指定:]", "")).replace("[備考欄:]", ""))) {
					continue;
				}
			}

			// 時間帯設定
			if ("0".equals(f130101.getJikantaiset())) {
				if (!Utility.isEmptyString(order.getOtodokeshiteibi())) {
					continue;
				}
			} else if ("1".equals(f130101.getJikantaiset())) {
				if (Utility.isEmptyString(order.getOtodokeshiteibi())) {
					continue;
				}
			}

			// 運送会社
			if (!Utility.isEmptyString(f130101.getUnyokaisha())) {
				if (!f130101.getUnyokaisha().equals(order.getUnsokaisha())) {
					continue;
				}
			}

			// 商品番号
			if (!Utility.isEmptyString(f130101.getShohinbango())) {
				boolean ariFlg = false;
				if (!Utility.isEmptyList(order.getShohinbangoList())) {
					for (String shohinbango : order.getShohinbangoList()) {
						if (shohinbango.startsWith(f130101.getShohinbango())) {
							ariFlg = true;
						}
					}
				}
				if (!ariFlg) {
					continue;
				}
			}

			shoriList.add(order);
		}

		return shoriList;
	}

	public List<OrderList> getOrderListByBango(List<String> juchubangoList) throws Exception {
		if (Utility.isEmptyList(juchubangoList)) {
			return new ArrayList<OrderList>();
		}
		Map<String, Integer> soryoMap = Utility.getSoryoMap();
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		try {
			List<OrderList> orderList = new ArrayList<OrderList>();
			OrderList Order = null;
			conn = JdbcConnection.getConnection();
			String sql = null;

			ResultSet rs = null;
			ResultSet rs2 = null;
			ResultSet rs3 = null;
			ResultSet rs4 = null;
			ResultSet rs5 = null;
			for (String juchubango : juchubangoList) {
				sql = "SELECT * FROM common_order_tbl WHERE CHUMONBANGO = ? ORDER BY str_to_date(CHUMONNICHIJI,'%Y-%m-%d %H:%i:%s') DESC";
				ps = conn.prepareStatement(sql);
				ps.setString(1, juchubango);
				rs = ps.executeQuery();

				while (rs.next()) {
					Order = new OrderList();
					orderList.add(Order);
					Order.setSite(rs.getString("SITE"));
					Order.setTenpo(rs.getString("SHOP"));
					Order.setChumonbango(rs.getString("CHUMONBANGO"));
					Order.setOshiharaihoho(rs.getString("OSHIHARAISTS"));
					Order.setGokeikingaku(Utility.numberFormat(rs.getString("GOKEISHOHIN")));
					Order.setSeikyujingaku(Utility.numberFormat(rs.getString("SEIKYUKINGAKU")));
					if ("後払い決済".equals(rs.getString("OSHIHARAISTS"))) {
						Order.setHaisohoho("宅配便");
					} else {
						Order.setHaisohoho(rs.getString("HAISOUHOHO"));
					}
					Order.setBiko(rs.getString("BIKO"));
					Order.setChumonichiji(rs.getString("CHUMONNICHIJI"));
					Order.setComento(rs.getString("KOMENTO"));
					Order.setSofusakinamae(rs.getString("SOFUSAKIMEIJI") + rs.getString("SOUFUSAKINAMAE") + "<br>["
							+ (Utility.isEmptyString(rs.getString("SOUFUSAKIMEIJIFURIGANA")) ? ""
									: rs.getString("SOUFUSAKIMEIJIFURIGANA"))
							+ " " + (Utility.isEmptyString(rs.getString("SOUFUSAKIMEIJINAMAEFURIGANA")) ? ""
									: rs.getString("SOUFUSAKIMEIJINAMAEFURIGANA"))
							+ "]");
					Order.setSofusakidenwabango(rs.getString("SOFUSAKIDENWABANGO1")
							+ rs.getString("SOFUSAKIDENWABANGO2") + rs.getString("SOFUSAKIDENWABANGO3"));
					String otodoke = "";
					if (!Utility.isEmptyString(rs.getString("OTODOKEBISHITEI"))) {
						otodoke += rs.getString("OTODOKEBISHITEI");
					}
					if (!Utility.isEmptyString(rs.getString("OTODOKEJIKANTAI1"))
							&& !"0".equals(rs.getString("OTODOKEJIKANTAI1"))) {

						if ("01".equals(rs.getString("OTODOKEJIKANTAI1"))) {
							otodoke += "　午前中";
						} else if ("12".equals(rs.getString("OTODOKEJIKANTAI1"))) {
							otodoke += "　12:00~14:00";
						} else if ("14".equals(rs.getString("OTODOKEJIKANTAI1"))) {
							otodoke += "　14:00~16:00";
						} else if ("16".equals(rs.getString("OTODOKEJIKANTAI1"))) {
							otodoke += "　16:00~18:00";
						} else if ("04".equals(rs.getString("OTODOKEJIKANTAI1"))) {
							otodoke += "　18:00~21:00";
						}
					}
					String address = rs.getString("SOUFUSAKIJUSHOTODOFUKEN") + rs.getString("SOUFUSAKIJUSHOTOSHIKU");

					Order.setOtodokeshiteibi(otodoke);

					boolean fukaariflg = false;
					sql = "select KOMOKUSENTAKUSHI from common_order_detail_tbl where juchubango = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, Order.getChumonbango());
					rs5 = ps.executeQuery();
					while (rs5.next()) {
						if (rs5.getString("KOMOKUSENTAKUSHI").contains("メール便対応不可")) {
							fukaariflg = true;
							break;
						}
					}

					if ("代金引換".equals(rs.getString("OSHIHARAISTS")) || fukaariflg) {
						Order.setUnsokaisha("1002");
						sql = "update common_order_tbl set haisouhoho = ? where chumonbango = ?";
						ps2 = conn.prepareStatement(sql);

						Order.setHaisohoho("宅配便");
						ps2.setString(1, "宅配便");

						ps2.setString(2, Order.getChumonbango());
						ps2.execute();

					} else {
						sql = "select * from kaisha_size_tbl where juchubango = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, rs.getString("CHUMONBANGO"));
						rs2 = ps.executeQuery();
						if (rs2.next()) {
							Order.setSize(rs2.getString("thissize"));
							Order.setUnsokaisha(rs2.getString("kaisha"));
							sql = "update common_order_tbl set haisouhoho = ?, UPDATE_TIME = ? where chumonbango = ?";
							ps2 = conn.prepareStatement(sql);
							if (Double.valueOf(rs2.getString("thissize")) < 0.3) {
								Order.setHaisohoho("DM便");
								ps2.setString(1, "DM便");
							}else if(Double.valueOf(rs2.getString("thissize")) >= 0.3 && Double.valueOf(rs2.getString("thissize")) < 1.0) {
								Order.setHaisohoho("メール便");
								ps2.setString(1, "メール便");
							}else {
								Order.setHaisohoho("宅配便");
								ps2.setString(1, "宅配便");
							}
							SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String date = format.format(new Date());
							ps2.setString(2, date);
							ps2.setString(3, Order.getChumonbango());
							ps2.execute();

						} else {
							sql = "select shouhinbango,kosu from common_order_detail_tbl where juchubango = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, rs.getString("CHUMONBANGO"));
							rs3 = ps.executeQuery();

							List<String[]> shohinbangoList = new ArrayList<String[]>();
							while (rs3.next()) {
								shohinbangoList
										.add(new String[] { rs3.getString("shouhinbango"), rs3.getString("kosu") });
							}

							sql = "select source from tbl00011 where commodity_id = ?";
							ps = conn.prepareStatement(sql);

							String size = "0";
							for (String[] shohinbango : shohinbangoList) {
								String bango = shohinbango[0];
								String kosu = shohinbango[1];
								ps.setString(1, Utility.getCommodityId(bango));
								rs3 = ps.executeQuery();

								if (rs3.next()) {
									String thisSize = rs3.getString("source");
									if (Utility.isEmptyString(thisSize)) {
										size = "";
										break;
									}

									size = String.valueOf(Double.parseDouble(size)
											+ Double.parseDouble(thisSize) * Double.parseDouble(kosu));

								} else {
									size = "";
									break;
								}
							}
							if (!Utility.isEmptyString(size) && Double.valueOf(size) > 2) {
								size = "2";
							}
							Order.setSize(size);

							if (!Utility.isEmptyString(size)) {
								String kaisha = Utility.getBestSoryo(rs.getString("SOUFUSAKIJUSHOTODOFUKEN"), size,
										soryoMap);
								sql = "update common_order_tbl set haisouhoho = ?, UPDATE_TIME = ? where chumonbango = ?";
								ps2 = conn.prepareStatement(sql);
								
								if (Double.valueOf(size) < 0.3) {
									Order.setHaisohoho("DM便");
									ps2.setString(1, "DM便");
								}else if(Double.valueOf(size) >= 0.3 && Double.valueOf(size) < 1.0) {
									Order.setHaisohoho("メール便");
									ps2.setString(1, "メール便");
								}else {
									Order.setHaisohoho("宅配便");
									ps2.setString(1, "宅配便");
									kaisha = "1002";
								}
								
								SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
								String date = format.format(new Date());
								ps2.setString(2, date);
								ps2.setString(3, Order.getChumonbango());
								ps2.execute();
								Order.setUnsokaisha(kaisha);
							}
						}
					}
					sql = "SELECT * FROM common_order_detail_tbl WHERE juchubango = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, juchubango);
					rs4 = ps.executeQuery();
					List<String> shohinbangoList = new ArrayList<String>();
					while (rs4.next()) {
						shohinbangoList.add(rs4.getString("shouhinbango"));
					}
					Order.setShohinbangoList(shohinbangoList);
					break;

				}

			}

			conn.commit();
			return orderList;
		} catch (

		Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
