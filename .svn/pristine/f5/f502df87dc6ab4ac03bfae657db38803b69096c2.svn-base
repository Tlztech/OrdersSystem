package com.rakuten.r0703.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;

public class A07030105Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String result = null;
	private String commodity_id = null;
	private String kosu = null;
	private String remark = null;
	private String shubetuId = null;

	protected void exec() throws Exception {

		updateCommodity(commodity_id, kosu, shubetuId, remark);

	}

	private void updateCommodity(String commodity_id, String kosu,
			String shubetuId, String remarks) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();

			sql = "UPDATE TBL00015 SET REMARKS = ? , RECIEVE_QUANTITY = ? WHERE SHUBETU_ID = ? AND COMMODITY_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, remarks);
			ps.setString(2, kosu);
			ps.setString(3, shubetuId);
			ps.setString(4, commodity_id);
			ps.executeUpdate();

			conn.commit();
			result = "true";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			result = "false";
			throw e;
		} finally {
			conn.close();
		}
	}

	protected void init() {
		setTitle("V070301:进货列表");

	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * @return the commodity_id
	 */
	public String getCommodity_id() {
		return commodity_id;
	}

	/**
	 * @param commodity_id
	 *            the commodity_id to set
	 */
	public void setCommodity_id(String commodity_id) {
		this.commodity_id = commodity_id;
	}

	/**
	 * @return the kosu
	 */
	public String getKosu() {
		return kosu;
	}

	/**
	 * @param kosu
	 *            the kosu to set
	 */
	public void setKosu(String kosu) {
		this.kosu = kosu;
	}

	public String getShubetuId() {
		return shubetuId;
	}

	public void setShubetuId(String shubetuId) {
		this.shubetuId = shubetuId;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 *            the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
