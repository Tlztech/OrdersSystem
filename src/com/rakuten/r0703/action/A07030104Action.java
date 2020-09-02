package com.rakuten.r0703.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A07030104Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String result = null;
	private String commodity_id = null;
	private String kosu = null;
	private boolean printFlg;
	private String shubetuId = null;
	private String remark = null;
	private String date = null;

	protected void exec() throws Exception {

		String[] commodity_idArr = null;
		String[] kosuArr = null;
		String[] shubetuIdArr = null;
		String[] remarksArr = null;
		String[] dateArr = null;

		if (commodity_id.contains("&")) {
			commodity_idArr = commodity_id.split("&");
			kosuArr = kosu.split("&");
			shubetuIdArr = shubetuId.split("&");
			remarksArr = remark.split("&");
			dateArr = date.split("&");
		} else {
			commodity_idArr = new String[] { commodity_id };
			kosuArr = new String[] { kosu };
			shubetuIdArr = new String[] { shubetuId };
			remarksArr = new String[] { remark };
			dateArr = new String[] { date };

		}
		updateCommodity(commodity_idArr, kosuArr, shubetuIdArr, remarksArr, dateArr);

	}

	private void updateCommodity(String[] commodity_idArr, String[] kosuArr, String[] shubetuIdArr, String[] remarksArr,
			String[] dateArr) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();

			for (int i = 0; i < commodity_idArr.length; i++) {
				sql = "SELECT COMMODITY_STATUS FROM TBL00015 WHERE COMMODITY_ID = ? AND SHUBETU_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, commodity_idArr[i]);
				ps.setString(2, shubetuIdArr[i]);
				rs = ps.executeQuery();
				while (rs.next()) {
					if ("1".equals(rs.getString("COMMODITY_STATUS"))) {
						throw new Exception("不能重复签收");
					}
				}

				// sql = "UPDATE TBL00015 SET COMMODITY_STATUS = ? ,
				// RECIEVE_QUANTITY = ? , REMARKS = ? WHERE COMMODITY_ID = ? AND
				// SHUBETU_ID = ?";
				// ps = conn.prepareStatement(sql);
				// ps.setString(1, "1");
				// ps.setString(2, kosuArr[i]);
				// ps.setString(3, remarksArr[i]);
				// ps.setString(4, commodity_idArr[i]);
				// ps.setString(5, shubetuIdArr[i]);
				// ps.executeUpdate();

				sql = "insert into tbl00015 values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				int k = 0;
				ps.setString(++k, shubetuIdArr[i]);
				ps.setString(++k, commodity_idArr[i]);
				ps.setString(++k, "");
				ps.setString(++k, "");
				ps.setString(++k, "");
				ps.setString(++k, "");
				ps.setString(++k, kosuArr[i]);
				ps.setString(++k, kosuArr[i]);
				ps.setString(++k, "1");
				ps.setString(++k, "1");
				ps.setString(++k, "");
				ps.setString(++k, "");
				ps.setString(++k, dateArr[i]);
				ps.setString(++k, "");
				ps.execute();

				sql = "delete from tbl00014 where waybill_no = ? and commodity_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shubetuIdArr[i]);
				ps.setString(2, commodity_idArr[i]);
				ps.execute();

				sql = "UPDATE TBL00012 SET STOCK_JP = STOCK_JP + ?, UPDATEQUANTITY_FLG =TRUE  WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, kosuArr[i]);
				ps.setString(2, Utility.getCommodityId(commodity_idArr[i]));
				ps.setString(3, Utility.getDetailN0(commodity_idArr[i]));
				ps.executeUpdate();
			}
			conn.commit();

			// if (printFlg) {
			// for (int i = 0; i < commodity_idArr.length; i++) {
			// sql = "SELECT BARCODE FROM TBL00016 WHERE COMMODITY_ID = ?";
			// ps = conn.prepareStatement(sql);
			// ps.setString(1, commodity_idArr[i]);
			// rs = ps.executeQuery();
			//
			// while (rs.next()) {
			// barcode = rs.getString("BARCODE");
			// }
			// if (!Utility.isEmptyString(barcode)) {
			// // result = Utility.barcode_print(barcode,
			// // commodity_idArr[i], kosuArr[i]);
			//
			// sql = "SELECT MAX(SEQ) SEQ FROM TBL00018";
			// ps = conn.prepareStatement(sql);
			// rs = ps.executeQuery();
			// String seq = "";
			// while (rs.next()) {
			// seq = rs.getString("SEQ");
			// }
			// if (!Utility.isEmptyString(seq)) {
			// seq = String.valueOf(Integer.valueOf(seq) + 1);
			// } else {
			// seq = "10000";
			// }
			//
			// sql = "INSERT INTO TBL00018 VALUES(?,?,?,?,?)";
			// ps = conn.prepareStatement(sql);
			// ps.setString(1, barcode);
			// ps.setString(2, commodity_idArr[i]);
			// ps.setString(3, kosuArr[i]);
			// ps.setString(4, seq);
			// ps.setString(5, "01");
			// ps.execute();
			//
			// sql = "UPDATE TBL00015 SET PRINT_STATUS = ? WHERE SHUBETU_ID = ?
			// AND COMMODITY_ID = ?";
			// ps = conn.prepareStatement(sql);
			// ps.setString(1, "1");
			// ps.setString(2, shubetuIdArr[i]);
			// ps.setString(3, commodity_idArr[i]);
			// ps.executeUpdate();
			// conn.commit();
			// }
			// }
			// }
			result = "true";

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			result = "false";
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

	/**
	 * @return the printFlg
	 */
	public boolean isPrintFlg() {
		return printFlg;
	}

	/**
	 * @param printFlg
	 *            the printFlg to set
	 */
	public void setPrintFlg(boolean printFlg) {
		this.printFlg = printFlg;
	}

	public String getShubetuId() {
		return shubetuId;
	}

	public void setShubetuId(String shubetuId) {
		this.shubetuId = shubetuId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
