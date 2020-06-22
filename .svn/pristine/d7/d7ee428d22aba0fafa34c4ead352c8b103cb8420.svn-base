package com.rakuten.r1701.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1701.form.DataList;
import com.rakuten.r1701.form.F170102;
import com.rakuten.util.JdbcConnection;

public class A17010201Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F170102 f170102 = null;
	String shumokuid = null;
	private String name = null;

	protected void exec() throws Exception {

		List<DataList> dataList = getData(shumokuid);
		f170102 = new F170102();
		f170102.setDataList(dataList);

	}

	public List<DataList> getData(String mode) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<DataList> dataList = new ArrayList<>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM tbl00033 WHERE shumokuid = ? ORDER BY id DESC";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shumokuid);
			rs = ps.executeQuery();
			while (rs.next()) {
				DataList data = new DataList();
				dataList.add(data);
				data.setChinese(rs.getString("c_name"));
				data.setId(rs.getString("id"));
				data.setJapanese(rs.getString("j_name"));

			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return dataList;

	}

	/**
	 * @return the f170102
	 */
	public F170102 getF170102() {
		return f170102;
	}

	/**
	 * @param f170102
	 *            the f170102 to set
	 */
	public void setF170102(F170102 f170102) {
		this.f170102 = f170102;
	}

	protected void init() {
		setTitle("V170102:商品分类详细");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getShumokuid() {
		return shumokuid;
	}

	public void setShumokuid(String shumokuid) {
		this.shumokuid = shumokuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
