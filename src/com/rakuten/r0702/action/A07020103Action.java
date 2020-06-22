package com.rakuten.r0702.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A07020103Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String result = null;
	private String commodity_id = null;
	private String kosu = null;
	private String shubetuId = null;

	protected void exec() throws Exception {

		String[] commodity_idArr = null;
		String[] kosuArr = null;
		String[] shubetuIdArr = null;
		if (commodity_id.contains("&")) {
			commodity_idArr = commodity_id.split("&");
			kosuArr = kosu.split("&");
			shubetuIdArr = shubetuId.split("&");

		} else {
			commodity_idArr = new String[] { commodity_id };
			kosuArr = new String[] { kosu };
			shubetuIdArr = new String[] { shubetuId };

		}
		updateCommodity(commodity_idArr, kosuArr, shubetuIdArr);

	}

	private void updateCommodity(String[] commodity_idArr, String[] kosuArr,
			String[] shubetuIdArr) throws Exception {
		String barcode = "";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();

			for (int i = 0; i < commodity_idArr.length; i++) {
				sql = "SELECT BARCODE FROM TBL00016 WHERE COMMODITY_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, commodity_idArr[i]);
				rs = ps.executeQuery();

				while (rs.next()) {
					barcode = rs.getString("BARCODE");
				}
				if (!Utility.isEmptyString(barcode)) {

					sql = "SELECT MAX(SEQ) SEQ FROM TBL00018";
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					String seq = "";
					while (rs.next()) {
						seq = rs.getString("SEQ");
					}
					if (!Utility.isEmptyString(seq)) {
						seq = String.valueOf(Integer.valueOf(seq) + 1);
					} else {
						seq = "10000";
					}

					sql = "INSERT INTO TBL00018 VALUES(?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, barcode);
					ps.setString(2, commodity_idArr[i]);
					ps.setString(3, kosuArr[i]);
					ps.setString(4, seq);
					ps.setString(5, "01");

					ps.execute();

					sql = "UPDATE TBL00015 SET PRINT_STATUS = ? WHERE SHUBETU_ID = ? AND COMMODITY_ID = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, "1");
					ps.setString(2, shubetuIdArr[i]);
					ps.setString(3, commodity_idArr[i]);
					ps.executeUpdate();
					conn.commit();
				} else {
					result = "false";
					break;
				}
			}
			result = "true";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			result = "false";
		} finally {
			conn.close();
		}
	}

	protected void init() {
		setTitle("V070201:进货列表");

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

}
