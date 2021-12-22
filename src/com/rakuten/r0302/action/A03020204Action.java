package com.rakuten.r0302.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;

public class A03020204Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String result = "";
	private String data1 = "";
	private String data2 = "";

	protected void exec() throws Exception {
		Map<String, Object> map = ActionContext.getContext().getSession();
		int companyId;
		if (null == map.get("comp")) {
			companyId = -1;
		} else {
			companyId = (int) map.get("comp");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		if (companyId == 0 || companyId == 1) {
			try {
				conn = JdbcConnection.getConnection();
				String sql = "UPDATE tbl00012 SET DEL_FLG = ? WHERE concat(commodity_id,detail_no) = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, "true".equals(data2) ? "1" : "0");
				ps.setString(2, data1);
				ps.execute();

				conn.commit();
				result = "1";

			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}
		} else {
			result = "0";
		}
	}

	protected void init() throws Exception {
		setTitle("V130201:∞kÀÕÑI¿Ì“ª”E");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	public String getData1() {
		return data1;
	}

	public void setData1(String data1) {
		this.data1 = data1;
	}

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

}
