package com.rakuten.r1501.action;

import java.sql.Connection;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1501.common.A1501Common;
import com.rakuten.r1501.form.Account;
import com.rakuten.r1501.form.F150101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A15010101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F150101 f150101 = null;
	private String area = null;

	protected void exec() throws Exception {

		Connection conn = null;

		try {
			conn = JdbcConnection.getConnection();
			List<Account> accountList = A1501Common.getAccountList(conn, area);
			f150101 = new F150101();
			f150101.setAccountList(accountList);

			String accountCount = "0";
			String totalBalance = "0";
			String totalUndidCount = "0";
			for (Account account : accountList) {
				totalBalance = String.valueOf(Double.valueOf(totalBalance)
						+ Double.valueOf(account.getBalance()));

				totalUndidCount = String.valueOf(Integer
						.valueOf(totalUndidCount)
						+ Integer.valueOf(account.getUndidCount()));
			}
			accountCount = String.valueOf(accountList.size());

			f150101.setAccountCount(accountCount);
			f150101.setTotalBalance(totalBalance);
			f150101.setTotalUndidCount(totalUndidCount);
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	protected void init() throws Exception {
		setTitle("V150101:账目查询（" + Utility.getAreaName(area) + "）");

	}

	public F150101 getF150101() {
		return f150101;
	}

	public void setF150101(F150101 f150101) {
		this.f150101 = f150101;
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
