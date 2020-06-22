package com.rakuten.r1503.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1503.form.F150301;
import com.rakuten.r1503.form.Type;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A15030102Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F150301 f150301 = null;
	private List<Type> typeList = null;

	protected void exec() throws Exception {
		init();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			conn = JdbcConnection.getConnection();
			sql = "INSERT INTO accountinfo (account_id,account_name,card_no,bank_code,comment,area,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?);";
			ps = conn.prepareStatement(sql);
			int i = 0;
			ps.setString(++i, Utility.getShoribango());
			ps.setString(++i, f150301.getAccountName());
			ps.setString(++i, f150301.getCardNo());
			ps.setString(++i, f150301.getType());
			ps.setString(++i, f150301.getBiko());
			ps.setString(++i, f150301.getArea());
			ps.setString(++i, Utility.getDateTime());
			ps.setString(++i, Utility.getUser());
			ps.setString(++i, Utility.getDateTime());
			ps.setString(++i, Utility.getUser());
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

	protected void init() throws Exception {
		setTitle("V150301:账本设置");
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F150301 getF150301() {
		return f150301;
	}

	public void setF150301(F150301 f150301) {
		this.f150301 = f150301;
	}

	public List<Type> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

}
