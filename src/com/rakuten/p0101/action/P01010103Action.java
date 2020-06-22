package com.rakuten.p0101.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import net.sf.json.JSONObject;

import com.rakuten.common.action.BaseAction;
import com.rakuten.p0101.form.CategoryInfo;
import com.rakuten.p0101.form.P010101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class P01010103Action extends BaseAction {

	private P010101 p010101 = null;
	private String shohinbango = null;
	private String searchMode = null;

	private static final long serialVersionUID = 1L;

	protected void exec() throws Exception {
		CategoryInfo categoryInfo = p010101.getCategoryList().get(
				Integer.valueOf(getRowIndex()));
		JSONObject jsonObject = JSONObject.fromObject(categoryInfo);
		setSessionAttribute("popupDate", jsonObject.toString());
		if (!Utility.isEmptyString(shohinbango)) {

			Connection conn = null;
			PreparedStatement ps = null;
			String sql = null;

			try {
				conn = JdbcConnection.getConnection();
				sql = "update tbl00011 set category_id = ? where commodity_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, categoryInfo.getCateId());
				ps.setString(2, shohinbango);
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
	}

	protected void init() {
		setTitle("P010101:商品分类检索");
	}

	protected void isValidated() throws Exception {
	}

	public String getSearchMode() {
		return searchMode;
	}

	public P010101 getP010101() {
		return p010101;
	}

	public void setP010101(P010101 p010101) {
		this.p010101 = p010101;
	}

	public void setSearchMode(String searchMode) {
		this.searchMode = searchMode;
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

}
