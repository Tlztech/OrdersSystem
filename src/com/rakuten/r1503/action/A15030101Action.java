package com.rakuten.r1503.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1503.form.Account;
import com.rakuten.r1503.form.F150301;
import com.rakuten.r1503.form.Fenlei;
import com.rakuten.r1503.form.Type;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A15030101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F150301 f150301 = null;
	private List<Type> typeList = null;

	protected void exec() throws Exception {
		init();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<Account> accountList = new ArrayList<Account>();
		List<Fenlei> fenleiList = new ArrayList<Fenlei>();
		Account account = null;
		Fenlei fenlei = null;
		f150301.setAccountList(accountList);
		f150301.setFenleiList(fenleiList);
		try {
			conn = JdbcConnection.getConnection();
			sql = "select * from accountinfo";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				account = new Account();
				accountList.add(account);
				account.setName(rs.getString("account_name"));
				account.setCardno(rs.getString("card_no"));
				account.setType(Utility.getBankName(rs.getString("bank_code")));
				account.setArea(Utility.getAreaName(rs.getString("area")));
				account.setBiko(rs.getString("comment"));
			}

			sql = "select * from fenleiinfo";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				fenlei = new Fenlei();
				fenleiList.add(fenlei);
				fenlei.setName(rs.getString("fenlei_name"));
				fenlei.setType(Utility.getTypeNameJP(rs.getString("kubun")));
				fenlei.setArea(Utility.getAreaName(rs.getString("area")));
				fenlei.setBiko(rs.getString("comment"));
			}

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
		f150301 = new F150301();
		setTypeList(Utility.getBankList());

	}

	public F150301 getF150301() {
		return f150301;
	}

	public void setF150301(F150301 f150301) {
		this.f150301 = f150301;
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public List<Type> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<Type> typeList) {
		this.typeList = typeList;
	}

}
