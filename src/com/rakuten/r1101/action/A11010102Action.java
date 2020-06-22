package com.rakuten.r1101.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1101.form.DataList;
import com.rakuten.r1101.form.F110102;
import com.rakuten.util.JdbcConnection;

public class A11010102Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F110102 f110102 = null;
	String mode = null;
	private String name = null;

	protected void exec() throws Exception {
		mode = getMode();
		mode = mode.replace(", ", "");
		List<DataList> dataList = getData(mode);
		f110102 = new F110102();
		f110102.setDataList(dataList);

		if ("1".equals(mode)) {
			name = "ËØ²Ä";
		} else if ("2".equals(mode)) {
			name = "ºá×ÝÖá£¨ÄÚÈÝ£©";
		} else if ("3".equals(mode)) {
			name = "ÏêÏ¸ÐÅÏ¢£¨Ãû³Æ£©";
		} else if ("4".equals(mode)) {
			name = "ÏêÏ¸ÐÅÏ¢£¨ÄÚÈÝ£©";
		} else if ("5".equals(mode)) {
			name = "ºá×ÝÖá(Ãû³Æ)";
		} else if ("7".equals(mode)) {
			name = "³ßÂë";
		}

	}

	public List<DataList> getData(String mode) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<DataList> dataList = new ArrayList<>();
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM dic_tbl WHERE id = ? ORDER BY s_id DESC";
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
			} else {
				return null;
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				DataList data = new DataList();
				dataList.add(data);
				data.setChinese(rs.getString("c_name"));
				data.setId(rs.getString("id"));
				data.setJapanese(rs.getString("j_name"));
				data.setS_id(rs.getString("s_id"));
				data.setS_coid(rs.getString("s_coid"));
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
		setTitle("V110101:ÎÄ°¸×Öµä");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
