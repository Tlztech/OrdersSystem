package com.rakuten.r0702.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0702.form.F070201;
import com.rakuten.r0702.form.NyukaList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A07020102Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	F070201 f070201 = null;
	List<NyukaList> nyukaList = null;
	private boolean printFlg;

	protected void exec() throws Exception {
		getNyukaList();
		f070201.setResultCount(String.valueOf(f070201.getNyukaList().size()));
		if (0 == f070201.getNyukaList().size()) {
			addError(null, "未找到相应的检索结果");
		} 
		// else {
		// List<NyukaList> nyukaList_temp = f070201.getNyukaList();
		// setSessionAttribute("nyukaList", nyukaList_temp);
		// if (nyukaList_temp.size() > 15) {
		// nyukaList = nyukaList_temp.subList(0, 15);
		// } else {
		// nyukaList = nyukaList_temp;
		// }
		// f070201.setNyukaList(nyukaList);
		// setNowPage("1");
		// setAllPage(String
		// .valueOf(((double) nyukaList_temp.size() / (double) 15) >
		// (nyukaList_temp
		// .size() / 15) ? nyukaList_temp.size() / 15 + 1
		// : nyukaList_temp.size() / 15));
		// }
	}

	protected void init() {
		setTitle("V070201:进货列表");

	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f070201
	 */
	public F070201 getF070201() {
		return f070201;
	}

	/**
	 * @param f070201
	 *            the f070201 to set
	 */
	public void setF070201(F070201 f070201) {
		this.f070201 = f070201;
	}

	private void getNyukaList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();

			sql = "SELECT * FROM TBL00015 WHERE 1=1";

			String commodityId = f070201.getCommodityId();
			String nyukabiStart = f070201.getNyukabiStart();
			String nyukabiEnd = f070201.getNyukabiEnd();
			String chineseName = f070201.getChineseName();
			String commodityStatus = f070201.getCommodityStatus();
			String printStatus = f070201.getPrintStatus();

			if (!Utility.isEmptyString(commodityId)) {
				sql += " AND COMMODITY_ID like '%" + commodityId + "%'";
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
			if (!Utility.isEmptyString(commodityStatus)
					&& "2".equals(commodityStatus)) {
				sql += " AND COMMODITY_STATUS = '" + "1" + "'";
			} else if (!Utility.isEmptyString(commodityStatus)
					&& "3".equals(commodityStatus)) {
				sql += " AND COMMODITY_STATUS = '" + "0" + "'";
			} else if (!Utility.isEmptyString(commodityStatus)
					&& "4".equals(commodityStatus)) {
				sql += " AND COMMODITY_STATUS = '" + "2" + "'";
			} else if (!Utility.isEmptyString(commodityStatus)
					&& "5".equals(commodityStatus)) {
				sql += " AND COMMODITY_STATUS = '" + "3" + "'";
			}

			if (!Utility.isEmptyString(printStatus) && "2".equals(printStatus)) {
				sql += " AND PRINT_STATUS = '" + "1" + "'";
			} else if (!Utility.isEmptyString(printStatus)
					&& "3".equals(printStatus)) {
				sql += " AND PRINT_STATUS = '" + "0" + "'";
			}

			sql += " ORDER BY CREATE_DATE DESC";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (f070201 == null) {
				f070201 = new F070201();
			}
			List<NyukaList> nyukaList = new ArrayList<NyukaList>();
			f070201.setNyukaList(nyukaList);
			NyukaList nyuka = null;
			while (rs.next()) {
				nyuka = new NyukaList();
				nyukaList.add(nyuka);

				nyuka.setShubetuId(rs.getString("SHUBETU_ID"));
				nyuka.setCommodityId(rs.getString("COMMODITY_ID"));
				nyuka.setPicUrl(Utility.getPicUrl(Utility.getCommodityId(rs
						.getString("COMMODITY_ID"))));
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
