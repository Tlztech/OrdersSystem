package com.rakuten.r1302.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A13020109Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String result = "";
	private String orderNo = "";

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Map<String,Object> map =  ActionContext.getContext().getSession();
			int companyId;
			if (null == map.get("comp")) {
				companyId = -1;
			} else {
				companyId = (int)map.get("comp");
			}
			conn = JdbcConnection.getConnection();
			String sql = "select hassoushahenokomento from common_order_tbl where CHUMONBANGO in (select order_id from company_order_tbl where order_id = ? AND (COMPANY_ID = ? OR ? = 0 OR ? = 1))";
			ps = conn.prepareStatement(sql);

			ps.setString(1, orderNo);
			ps.setInt(2, companyId);
			ps.setInt(3, companyId);
			ps.setInt(4, companyId);

			rs = ps.executeQuery();
			while (rs.next()) {
				if (!Utility.isEmptyString(rs.getString("hassoushahenokomento"))) {
					result = rs.getString("hassoushahenokomento");
				}
			}

			sql = "select * from common_order_detail_tbl left join tbl00016 on SHOUHINBANGO = commodity_id where juchubango in (select order_id from company_order_tbl where order_id = ? AND (COMPANY_ID = ?))";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			ps.setInt(2, companyId);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = result + "&&";
				result += rs.getString("SHOUHINBANGO") + "&&";
				result += rs.getString("kosu") + "&&";
				result += rs.getString("barcode");
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	private String getjuchubango(String bango, String shubetsu) throws Exception {
		String juchubango = "";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT chumonbango from  common_order_tbl where chumonbango like ? and chumonsts1 <> '3' and site =?";
			ps = conn.prepareStatement(sql);
			if ("O".equals(shubetsu)) {
				ps.setString(1, "%" + bango.replace("O", ""));
				ps.setString(2, "òSÃÏ");
			} else if ("P".equals(shubetsu)) {
				ps.setString(1, "%" + bango.replace("P", ""));
				ps.setString(2, "•›•Û•—•Ï•‚©`•Î");
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				juchubango = rs.getString("chumonbango");
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return juchubango;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}
