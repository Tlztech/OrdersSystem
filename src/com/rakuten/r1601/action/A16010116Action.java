package com.rakuten.r1601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;

public class A16010116Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String result = "";
	private String sizeId = "";
	private String hasouHoho = "";
	private String unsouKaisha = "";

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String unsouKaishaName = "";
		try {
			conn = JdbcConnection.getConnection();
			
			String sql = "SELECT UNSOUKAISHANAME FROM unsoukaisha_tbl WHERE unsoukaishacode = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, unsouKaisha);
			rs = ps.executeQuery();
			while (rs.next()) {
				unsouKaishaName = rs.getString("UNSOUKAISHANAME");
			}
			
			sql = "UPDATE hassouhoho_unsoukaisha_tbl SET hasouhoho = ? , unsoukaisha = ? , unsoukaishaname = ?  WHERE sizeid = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, hasouHoho);
			ps.setString(2, unsouKaisha);
			ps.setString(3, unsouKaishaName);
			ps.setString(4, sizeId);
			int count = ps.executeUpdate();
			
			if (count > 0) {
				conn.commit();
				result = "true";
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	protected void init() throws Exception {
		setTitle("V160101:Ð¡¹¤¾ß");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the hasouhoho
	 */
	public String getHasouHoho() {
		return hasouHoho;
	}

	/**
	 * @param hasouhoho
	 *            the hasouhoho to set
	 */
	public void setHasouHoho(String hasouHoho) {
		this.hasouHoho = hasouHoho;
	}

	/**
	 * @return the unsouKaisha
	 */
	public String getUnsouKaisha() {
		return unsouKaisha;
	}

	/**
	 * @param unsouKaisha
	 *            the unsouKaisha to set
	 */
	public void setUnsouKaisha(String unsouKaisha) {
		this.unsouKaisha = unsouKaisha;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	public String getSizeId() {
		return sizeId;
	}

	public void setSizeId(String sizeId) {
		this.sizeId = sizeId;
	}

}
