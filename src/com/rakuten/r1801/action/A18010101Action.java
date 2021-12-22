package com.rakuten.r1801.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1801.form.F180101;
import com.rakuten.r1801.form.F180101.Company;
import com.rakuten.util.JdbcConnection;

public class A18010101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F180101 f180101 = new F180101();
	
	protected void exec() throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = JdbcConnection.getConnection();
			
			sql = "select site from shop where DELETE_FLG is null group by site ";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<String> siteList = new ArrayList<String>();
			
			while (rs.next()) {
				siteList.add(rs.getString("site"));
			}

			f180101.setSiteList(siteList);
			
			sql = "select shop_id from shop where DELETE_FLG is null group by shop_id";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<String> shopList = new ArrayList<String>();
			
			while (rs.next()) {
				shopList.add(rs.getString("shop_id"));
			}
			
			f180101.setShopList(shopList);
			
			sql = "SELECT company_id, company_name FROM rakuten.tbl00001 where company_id <> 0 group by company_id";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			List<Company> companyList = new ArrayList<Company>();
			while (rs.next()) {
				Company comp = new Company();
				comp.setCompanyId(rs.getInt("company_id"));
				comp.setCompanyName(rs.getString("company_name"));
				companyList.add(comp);
			}
			f180101.setCompanyList(companyList);
			
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}

	}

	protected void init() {
		setTitle("V180101:店铺管理");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F180101 getF180101() {
		return f180101;
	}

	public void setF180101(F180101 f180101) {
		this.f180101 = f180101;
	}

}
