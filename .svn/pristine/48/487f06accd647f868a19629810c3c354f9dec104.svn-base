package com.rakuten.common.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class getBangoAction extends BaseAction {

	private static final long serialVersionUID = -5541718078289452647L;
	private String result = null;
	private String first = null;

	protected void exec() throws Exception {
		result = "";

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = null;

			sql = "SELECT shohinbango FROM tbl00023 where shohinbango like ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, first + "%");
			rs = ps.executeQuery();
			List<String> bangoList = new ArrayList<String>();
			while (rs.next()) {
				bangoList.add(rs.getString("shohinbango"));
			}

			if (Utility.isEmptyList(bangoList)) {
				result = first + "001";
			} else {
				List<String> shoriBangoList = new ArrayList<String>();
				for (String bango : bangoList) {
					if (Utility.isNum(bango.replace(first, ""))) {
						shoriBangoList.add(bango.replace(first, ""));
					}
				}
				if (Utility.isEmptyList(shoriBangoList)) {
					result = first + "001";
				} else {
					int max = Integer.valueOf(shoriBangoList.get(0));
					for (int i = 0; i < shoriBangoList.size(); i++) {
						if (Integer.valueOf(shoriBangoList.get(i)) > max) {
							max = Integer.valueOf(shoriBangoList.get(i));
						}
					}
					String resultbango = "";
					if (max + 1 < 10) {
						resultbango = "00" + (max + 1);
					} else if (max + 1 < 100) {
						resultbango = "0" + (max + 1);
					} else {
						resultbango = String.valueOf(max + 1);
					}
					result = first + resultbango;
				}
			}

		} catch (

		Exception e)

		{
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally

		{
			conn.close();
		}

	}

	protected void init() {
	}

	protected void isValidated() throws Exception {
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}
}
