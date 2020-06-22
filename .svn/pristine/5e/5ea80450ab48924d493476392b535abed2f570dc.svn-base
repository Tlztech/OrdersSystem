package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A06010112Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String waybillNo = null;
	private String customs = null;
	private String result = null;

	protected void exec() throws Exception {
		if (Utility.isEmptyString(customs)) {
			customs = "0";
		}
		int count = updateCustoms(waybillNo, customs);
		if (count > 0) {
			result = "true";
		} else {
			result = "false";
		}
	}

	public int updateCustoms(String waybillNo, String customs) throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "UPDATE TBL00013 SET CUSTOMS = ? WHERE WAYBILL_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, customs);
			ps.setString(2, waybillNo);
			int count = ps.executeUpdate();

			// commit
			conn.commit();
			return count;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			return 0;
		} finally {
			conn.close();
		}
	}

	protected void init() {
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the waybillNo
	 */
	public String getWaybillNo() {
		return waybillNo;
	}

	/**
	 * @param waybillNo
	 *            the waybillNo to set
	 */
	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	/**
	 * @return the customs
	 */
	public String getCustoms() {
		return customs;
	}

	/**
	 * @param customs
	 *            the customs to set
	 */
	public void setCustoms(String customs) {
		this.customs = customs;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
