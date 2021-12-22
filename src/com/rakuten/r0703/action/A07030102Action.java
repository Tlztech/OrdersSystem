package com.rakuten.r0703.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r0703.form.F070301;
import com.rakuten.r0703.form.NyukaList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A07030102Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	F070301 f070301 = null;
	List<NyukaList> nyukaList = null;
	private boolean printFlg;

	protected void exec() throws Exception {
		if ("3".equals(f070301.getCommodityStatus())) {
			getNyukaList();
		} else {
			getNyukaList2();
		}
		f070301.setResultCount(String.valueOf(f070301.getNyukaList().size()));
		if (0 == f070301.getNyukaList().size()) {
			addError(null, "未找到相应的检索结果");
		}
		// else {
		// List<NyukaList> nyukaList_temp = f070301.getNyukaList();
		// setSessionAttribute("nyukaList", nyukaList_temp);
		// if (nyukaList_temp.size() > 15) {
		// nyukaList = nyukaList_temp.subList(0, 15);
		// } else {
		// nyukaList = nyukaList_temp;
		// }
		// f070301.setNyukaList(nyukaList);
		// setNowPage("1");
		// setAllPage(String
		// .valueOf(((double) nyukaList_temp.size() / (double) 15) >
		// (nyukaList_temp
		// .size() / 15) ? nyukaList_temp.size() / 15 + 1
		// : nyukaList_temp.size() / 15));
		// }
	}

	protected void init() {
		setTitle("V070301:进货列表");

	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f070301
	 */
	public F070301 getF070301() {
		return f070301;
	}

	/**
	 * @param f070301
	 *            the f070301 to set
	 */
	public void setF070301(F070301 f070301) {
		this.f070301 = f070301;
	}

	private void getNyukaList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			Map<String,Object> map =  ActionContext.getContext().getSession();
			int companyId;
			if (null == map.get("comp")) {
				companyId = -1;
			} else {
				companyId = (int)map.get("comp");
			}
			conn = JdbcConnection.getConnection();

			sql = "SELECT * FROM TBL00013 t1 left join tbl00014 t2 on t1.waybill_no = t2.waybill_no WHERE t1.status = '00' and t1.waybill_no like 'zyt%'";

			String commodityId = f070301.getCommodityId();
			String nyukabiStart = f070301.getNyukabiStart();
			String nyukabiEnd = f070301.getNyukabiEnd();

			if (!Utility.isEmptyString(commodityId)) {
				sql += " AND SUBSTRING_INDEX(t2.COMMODITY_ID, '-', 1) in (select commodity_id from company_commodity_tbl where commodity_id like '%" + Utility.getCommodityId(commodityId) + "%' AND (? = 0 OR ? = 1))";
			} else {
				sql += " AND SUBSTRING_INDEX(t2.COMMODITY_ID, '-', 1) in (select commodity_id from company_commodity_tbl where (? = 0 OR ? = 1))";
			}
			if (!Utility.isEmptyString(nyukabiStart)) {
				sql += " AND t1.deliver_day >= '" + nyukabiStart.replace("-", "") + "'";
			}
			if (!Utility.isEmptyString(nyukabiEnd)) {
				sql += " AND t1.deliver_day <= '" + nyukabiEnd.replace("-", "") + "'";
			}

			sql += " ORDER BY t1.deliver_day DESC";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, companyId);
			ps.setInt(2, companyId);
			ResultSet rs = ps.executeQuery();

			if (f070301 == null) {
				f070301 = new F070301();
			}
			List<NyukaList> nyukaList = new ArrayList<NyukaList>();
			f070301.setNyukaList(nyukaList);
			NyukaList nyuka = null;
			int i = 0;
			while (rs.next()) {
				nyuka = new NyukaList();
				nyukaList.add(nyuka);

				nyuka.setShubetuId(rs.getString("t1.waybill_no"));
				nyuka.setCommodityId(rs.getString("t2.COMMODITY_ID"));
				nyuka.setPicUrl(Utility.getPicUrl(Utility.getCommodityId(rs.getString("t2.COMMODITY_ID"))));
				// nyuka.setChineseName(Utility.getShohinmei(Utility.getCommodityId(rs.getString("t2.COMMODITY_ID"))));
				nyuka.setCommodityStatus("0");
				nyuka.setPrintStatus("0");
				nyuka.setNyukasu(rs.getString("t2.QUANTITY"));
				nyuka.setGetsu("0");
				nyuka.setNyukabi(rs.getString("t1.deliver_day"));
				// nyuka.setRemarks(rs.getString("t2.REMARKS"));
				nyuka.setDescribe(getDescribe(rs.getString("t2.COMMODITY_ID")));

				if ("0".equals(nyuka.getCommodityStatus())) {
					nyuka.setCommodityStatusName("未签收");
				} else if ("1".equals(nyuka.getCommodityStatus())) {
					nyuka.setCommodityStatusName("已签收");
				} else if ("2".equals(nyuka.getCommodityStatus())) {
					nyuka.setCommodityStatusName("问题件");
				} else if ("3".equals(nyuka.getCommodityStatus())) {
					nyuka.setCommodityStatusName("已解決");
				}

				if ("0".equals(nyuka.getPrintStatus())) {
					nyuka.setPrintStatusName("未打印");
				} else if ("1".equals(nyuka.getPrintStatus())) {
					nyuka.setPrintStatusName("已打印");
				}
				i++;
				System.out.println(i);
			}

			// commit
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	private void getNyukaList2() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			Map<String,Object> map =  ActionContext.getContext().getSession();
			int companyId;
			if (null == map.get("comp")) {
				companyId = -1;
			} else {
				companyId = (int)map.get("comp");
			}
			conn = JdbcConnection.getConnection();

			sql = "SELECT * FROM TBL00015 WHERE SHUBETU_ID like 'zyt%' ";

			String commodityId = f070301.getCommodityId();
			String nyukabiStart = f070301.getNyukabiStart();
			String nyukabiEnd = f070301.getNyukabiEnd();
			String chineseName = f070301.getChineseName();
			String commodityStatus = f070301.getCommodityStatus();
			String printStatus = f070301.getPrintStatus();

			if (!Utility.isEmptyString(commodityId)) {
				sql += " AND SUBSTRING_INDEX(COMMODITY_ID, '-', 1) in (select commodity_id from company_commodity_tbl where commodity_id like '%" + Utility.getCommodityId(commodityId) + "%' AND (? = 0 OR ? = 1))";
			} else {
				sql += " AND SUBSTRING_INDEX(COMMODITY_ID, '-', 1) in (select commodity_id from company_commodity_tbl where (? = 0 OR ? = 1))";
			}
			if (!Utility.isEmptyString(nyukabiStart)) {
				sql += " AND CREATE_DATE >= '" + nyukabiStart.replace("-", "") + "'";
			}
			if (!Utility.isEmptyString(nyukabiEnd)) {
				sql += " AND CREATE_DATE <= '" + nyukabiEnd.replace("-", "") + "'";
			}

			if (!Utility.isEmptyString(chineseName)) {
				sql += " AND CHINESE_NAME like '%" + chineseName + "%'";
			}
			if (!Utility.isEmptyString(commodityStatus) && "2".equals(commodityStatus)) {
				sql += " AND COMMODITY_STATUS = '" + "1" + "'";
			} else if (!Utility.isEmptyString(commodityStatus) && "3".equals(commodityStatus)) {
				sql += " AND COMMODITY_STATUS = '" + "0" + "'";
			} else if (!Utility.isEmptyString(commodityStatus) && "4".equals(commodityStatus)) {
				sql += " AND COMMODITY_STATUS = '" + "2" + "'";
			} else if (!Utility.isEmptyString(commodityStatus) && "5".equals(commodityStatus)) {
				sql += " AND COMMODITY_STATUS = '" + "3" + "'";
			}

			if (!Utility.isEmptyString(printStatus) && "2".equals(printStatus)) {
				sql += " AND PRINT_STATUS = '" + "1" + "'";
			} else if (!Utility.isEmptyString(printStatus) && "3".equals(printStatus)) {
				sql += " AND PRINT_STATUS = '" + "0" + "'";
			}

			sql += " ORDER BY CREATE_DATE DESC";

			ps = conn.prepareStatement(sql);
			ps.setInt(1, companyId);
			ps.setInt(2, companyId);
			ResultSet rs = ps.executeQuery();

			if (f070301 == null) {
				f070301 = new F070301();
			}
			List<NyukaList> nyukaList = new ArrayList<NyukaList>();
			f070301.setNyukaList(nyukaList);
			NyukaList nyuka = null;
			while (rs.next()) {
				nyuka = new NyukaList();
				nyukaList.add(nyuka);

				nyuka.setShubetuId(rs.getString("SHUBETU_ID"));
				nyuka.setCommodityId(rs.getString("COMMODITY_ID"));
				nyuka.setPicUrl(Utility.getPicUrl(Utility.getCommodityId(rs.getString("COMMODITY_ID"))));
				nyuka.setChineseName(rs.getString("CHINESE_NAME"));
				nyuka.setCommodityStatus(rs.getString("COMMODITY_STATUS"));
				nyuka.setPrintStatus(rs.getString("PRINT_STATUS"));
				nyuka.setNyukasu(rs.getString("QUANTITY"));
				nyuka.setGetsu(rs.getString("RECIEVE_QUANTITY"));
				nyuka.setNyukabi(rs.getString("CREATE_DATE"));
				nyuka.setRemarks(rs.getString("REMARKS"));
				nyuka.setDescribe(getDescribe(rs.getString("COMMODITY_ID")));

				if ("0".equals(nyuka.getCommodityStatus())) {
					nyuka.setCommodityStatusName("未签收");
				} else if ("1".equals(nyuka.getCommodityStatus())) {
					nyuka.setCommodityStatusName("已签收");
				} else if ("2".equals(nyuka.getCommodityStatus())) {
					nyuka.setCommodityStatusName("问题件");
				} else if ("3".equals(nyuka.getCommodityStatus())) {
					nyuka.setCommodityStatusName("已解決");
				}

				if ("0".equals(nyuka.getPrintStatus())) {
					nyuka.setPrintStatusName("未打印");
				} else if ("1".equals(nyuka.getPrintStatus())) {
					nyuka.setPrintStatusName("已打印");
				}

			}

			// commit
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	private String getDescribe(String shohinbango) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		String describe = "";
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();

			sql = "SELECT * FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, Utility.getCommodityId(shohinbango));
			ps.setString(2, Utility.getDetailN0(shohinbango));
			rs = ps.executeQuery();
			while (rs.next()) {
				describe = rs.getString("COMM_DESCRIBE");
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return describe;
	}

	public boolean isPrintFlg() {
		return printFlg;
	}

	public void setPrintFlg(boolean printFlg) {
		this.printFlg = printFlg;
	}
}
