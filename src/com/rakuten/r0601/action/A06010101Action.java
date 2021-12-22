package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.form.F060101;
import com.rakuten.r0601.form.F060101.Company;
import com.rakuten.util.JdbcConnection;

public class A06010101Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	F060101 f060101 = null;

	protected void exec() throws Exception {
		removeSessionAttribute("hassou");
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			conn = JdbcConnection.getConnection();
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
			
			f060101.setCompanyList(companyList);
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	protected void init() {
		setTitle("V060101:发货管理");
		f060101 = new F060101();
		f060101.setStatus("1");
		f060101.setLogistics("04");
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

}
