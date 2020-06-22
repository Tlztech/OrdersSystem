package com.rakuten.r1101.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1101.form.F110102;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A11010104Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F110102 f110102 = null;
	String mode = null;

	protected void exec() throws Exception {
		String chinese = f110102.getChinese();
		String japanese = f110102.getJapanese();
		String s_coid = f110102.getS_coid();
		addData(chinese, japanese, s_coid);

	}

	private void addData(String chinese, String japanese, String s_coid)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			conn = JdbcConnection.getConnection();
			sql = "INSERT INTO dic_tbl(id,s_id,c_name,j_name,s_coid) VALUES (?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			if ("1".equals(mode)) {
				ps.setString(1, "101");
			} else if ("2".equals(mode)) {
				ps.setString(1, "102");
			} else if ("3".equals(mode)) {
				ps.setString(1, "103");
			} else if ("4".equals(mode)) {
				ps.setString(1, "104");
			} else if ("5".equals(mode)) {
				ps.setString(1, "105");
			} else if ("6".equals(mode)) {
				ps.setString(1, "106");
			} else if ("7".equals(mode)) {
				ps.setString(1, "107");
			}
			ps.setString(2, Utility.getShoribango());
			ps.setString(3, chinese);
			ps.setString(4, japanese);
			ps.setString(5, s_coid);
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
	 * @return the f110102
	 */
	public F110102 getF110102() {
		return f110102;
	}

	/**
	 * @param f110102
	 *            the f110102 to set
	 */
	public void setF110102(F110102 f110102) {
		this.f110102 = f110102;
	}

	protected void init() {
		setTitle("V110101:文案字典");
	}

	protected void isValidated() throws Exception {
		if (f110102 != null) {
			String chinese = f110102.getChinese();

			Connection conn = null;
			PreparedStatement ps = null;
			String sql = null;
			ResultSet rs = null;

			try {
				conn = JdbcConnection.getConnection();
				sql = "SELECT COUNT(*) COUNT FROM dic_tbl WHERE id = ? AND c_name = ?";
				ps = conn.prepareStatement(sql);
				if ("1".equals(mode)) {
					ps.setString(1, "101");
				} else if ("2".equals(mode)) {
					ps.setString(1, "102");
				} else if ("3".equals(mode)) {
					ps.setString(1, "103");
				} else if ("4".equals(mode)) {
					ps.setString(1, "104");
				} else if ("5".equals(mode)) {
					ps.setString(1, "105");
				} else if ("6".equals(mode)) {
					ps.setString(1, "106");
				} else if ("7".equals(mode)) {
					ps.setString(1, "107");
				}
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
		if (f110102 == null || Utility.isEmptyString(f110102.getChinese())
				|| Utility.isEmptyString(f110102.getJapanese())) {
			addError(null, "请输入必要信息");
		}
		if (f110102 != null && "2".equals(mode)
				&& Utility.isEmptyString(f110102.getS_coid())) {
			addError(null, "请输入子货号");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

}
