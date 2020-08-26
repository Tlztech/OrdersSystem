package com.rakuten.r1601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1601.form.F160101;
import com.rakuten.util.JdbcConnection;

public class A16010110Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F160101 f160101 = null;
	private String itemno = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		String commdityId =null;
		boolean flg = false;
		try {
			if(itemno.indexOf("-") == -1) {
				sql = "delete from tbl00012 where COMMODITY_ID = ?";
				commdityId = itemno;
			} else {
				commdityId = itemno.substring(0, itemno.indexOf("-"));
				sql = "delete from tbl00012 WHERE concat(commodity_id,detail_no) = ?";
			}
			conn = JdbcConnection.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, itemno);
			int count = ps.executeUpdate();
			ps.close();
			conn.commit();
			if (count > 0) {
				sql = "SELECT count(*) COUNT from tbl00012 where COMMODITY_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, commdityId);
				rs = ps.executeQuery();
				while (rs.next()) {
					if (rs.getInt("COUNT") == 0) {
						flg = true;
					}
				}
				rs.close();
				ps.close();
				if (flg) {
					sql = "delete from tbl00011 where COMMODITY_ID = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, commdityId);
					ps.executeUpdate();
					ps.close();
					conn.commit();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
			addError(null, "操作終わった");
		}
	}

	protected void init() {
		setTitle("V160101:小工具");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F160101 getF160101() {
		return f160101;
	}

	public void setF160101(F160101 f160101) {
		this.f160101 = f160101;
	}

	public String getItemno() {
		return itemno;
	}

	public void setItemno(String itemno) {
		this.itemno = itemno;
	}
}
