package com.rakuten.r1701.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1701.form.F170101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A17010102Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F170101 f170101 = null;

	protected void exec() throws Exception {
		String cname = f170101.getAddName();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			conn = JdbcConnection.getConnection();
			sql = "INSERT INTO TBL00032 VALUES(?,?,?,?,?,?)";
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
		setTitle("V170101:商品关键字分类");
	}

	public F170101 getF170101() {
		return f170101;
	}

	public void setF170101(F170101 f170101) {
		this.f170101 = f170101;
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

}
