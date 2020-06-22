package com.rakuten.r1701.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1701.form.F170102;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A17010202Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F170102 f170102 = null;
	String shumokuid = null;

	protected void exec() throws Exception {
		String chinese = f170102.getChinese();
		String japanese = f170102.getJapanese();

		addData(chinese, japanese);

	}

	private void addData(String chinese, String japanese) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			conn = JdbcConnection.getConnection();
			sql = "INSERT INTO tbl00033(shumokuid,id,c_name,j_name) VALUES (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shumokuid);
			ps.setString(2, Utility.getShoribango());
			ps.setString(3, chinese);
			ps.setString(4, japanese);
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
		if (f170102 != null) {
			String chinese = f170102.getChinese();

			Connection conn = null;
			PreparedStatement ps = null;
			String sql = null;
			ResultSet rs = null;

			try {
				conn = JdbcConnection.getConnection();
				sql = "SELECT COUNT(*) COUNT FROM tbl00033 WHERE shumokuid = ? AND c_name = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shumokuid);
				ps.setString(2, chinese);
				rs = ps.executeQuery();
				while (rs.next()) {
					int count = rs.getInt("COUNT");
					if (count > 0) {
						addError(null, "已存在，不能重复添加");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}
		}
		if (f170102 == null || Utility.isEmptyString(f170102.getChinese())
				|| Utility.isEmptyString(f170102.getJapanese())) {
			addError(null, "请输入必要信息");
		}

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

}
