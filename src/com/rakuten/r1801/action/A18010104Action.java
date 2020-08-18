package com.rakuten.r1801.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1801.form.F180101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A18010104Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F180101 f180101 = new F180101();
	
	protected void exec() throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;

		try {
			conn = JdbcConnection.getConnection();

			int count = 0;
			sql = "SELECT COUNT(*) COUNT FROM SHOP WHERE SITE = ? and SHOP_ID = ? AND DELETE_FLG is null";
			ps = conn.prepareStatement(sql);
			ps.setString(1, f180101.getPlatform());
			ps.setString(2, f180101.getShopId());
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("COUNT");
			}
			if (count > 0) {
				sql = "UPDATE SHOP SET SHOP_NAME = ?, SHOP_NO = ?, SHOP_TEL = ?, SHOP_FAX = ?, SHOP_URL = ?, SHOP_ADDRESS = ?, SERVICE_KEY = ?, LICENSE_KEY = ?, UPDATE_TIME = ?, UPDATE_USER = ? WHERE SITE = ? and SHOP_ID = ?";
				ps = conn.prepareStatement(sql);

				ps.setString(1, f180101.getShopName());
				ps.setString(2, f180101.getShopNumber());
				ps.setString(3, f180101.getPhoneNumber());
				ps.setString(4, f180101.getFax());
				ps.setString(5, f180101.getShopUrl());
				ps.setString(6, f180101.getShopAddress());
				ps.setString(7, f180101.getServiceKey());
				ps.setString(8, f180101.getLicenseKey());
				ps.setString(9, Utility.getDateTime());
				ps.setString(10, Utility.getUser());
				ps.setString(11, f180101.getPlatform());
				ps.setString(12, f180101.getShopId());
				
				ps.execute();
				
				conn.commit();
				
				f180101.setPlatform(null);
				f180101.setShopId(null);
				f180101.setShopName(null);
				f180101.setShopNumber(null);;
				f180101.setPhoneNumber(null);;
				f180101.setFax(null);
				f180101.setShopUrl(null);
				f180101.setShopAddress(null);
				f180101.setServiceKey(null);
				f180101.setLicenseKey(null);
				f180101.setApplicationID(null);
				f180101.setAccessToken(null);
				f180101.setRefreshToken(null);
				f180101.setLoginTime(null);
				
				addError(null, "更新成功");
				
			} else {
				
				addError(null, "更新的平台和店铺不存在，请确认！");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	protected void init() {
		setTitle("V180101:店铺管理");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F180101 getF180101() {
		return f180101;
	}

	public void setF180101(F180101 f180101) {
		this.f180101 = f180101;
	}

}
