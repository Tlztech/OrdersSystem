package com.rakuten.r1505.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A15050102Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String result = null;
	private String hiseikyusha = "";

	protected void init() throws Exception {
		setTitle("V150501:请求书");
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT endDay  from  qingqiushu where hiseikyusha = ? order by seikyuid desc";
			ps = conn.prepareStatement(sql);
			ps.setString(1, hiseikyusha);
			rs = ps.executeQuery();
			if (rs.next()) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
				Calendar date = Calendar.getInstance();
				date.setTime(sdf.parse(rs.getString("endDay").replace("-", "")));
				date.add(Calendar.DATE, 1);
				result = sdf.format(date.getTime());

			} else if ("トレンド最前線".equals(hiseikyusha)) {
				result = "20150122";
			} else if ("勝意有限会社".equals(hiseikyusha)) {
				result = "20150310";
			}
			result = Utility.formatData2(result);
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	@Override
	protected void exec() throws Exception {
		// TODO Auto-generated method stub

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getHiseikyusha() {
		return hiseikyusha;
	}

	public void setHiseikyusha(String hiseikyusha) {
		this.hiseikyusha = hiseikyusha;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
