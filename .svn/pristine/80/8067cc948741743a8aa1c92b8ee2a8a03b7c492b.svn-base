package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.form.F060101;
import com.rakuten.r0601.form.WayBillInfo;
import com.rakuten.util.JdbcConnection;

public class A06010110Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	F060101 f060101 = null;

	protected void exec() throws Exception {
		WayBillInfo info = f060101.getWayBillList().get(
				Integer.valueOf(getRowIndex()));
		deleteWaybill(info.getWaybillNo());

	}

	protected void init() {
		setTitle("V060101:发货管理");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f060101
	 */
	public F060101 getF060101() {
		return f060101;
	}

	/**
	 * @param f060101
	 *            the f060101 to set
	 */
	public void setF060101(F060101 f060101) {
		this.f060101 = f060101;
	}

	private void deleteWaybill(String waybillNo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "DELETE FROM TBL00013 WHERE WAYBILL_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, waybillNo);
			ps.execute();

			sql = "DELETE FROM TBL00014 WHERE WAYBILL_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, waybillNo);
			ps.execute();

			conn.commit();
			f060101.setPassFlg("1");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
