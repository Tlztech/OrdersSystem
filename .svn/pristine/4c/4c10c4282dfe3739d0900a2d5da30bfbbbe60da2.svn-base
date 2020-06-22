package com.rakuten.r1201.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1201.form.AlbumList;
import com.rakuten.r1201.form.F120101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A12010102Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F120101 f120101 = null;

	protected void exec() throws Exception {
		String cname = f120101.getAddName();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			conn = JdbcConnection.getConnection();
			sql = "INSERT INTO TBL00022 VALUES(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, Utility.getShoribango());
			ps.setString(2, cname);
			ps.setString(3, Utility.getDateTime());
			ps.setString(4, Utility.getUser());
			ps.setString(5, Utility.getDateTime());
			ps.setString(6, Utility.getUser());
			ps.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	protected void init() {
		setTitle("V120101:商品专辑列表");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F120101 getF120101() {
		return f120101;
	}

	public void setF120101(F120101 f120101) {
		this.f120101 = f120101;
	}
}
