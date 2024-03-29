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
	F180101 f180101;
	
	protected void exec() throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;

		if (0 == (f180101.getCompanyId())) {
			this.addFieldError("error", "请选择公司");
			return;
		}
		
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
				sql = "UPDATE SHOP SET SHOP_NAME = ?, SHOP_NO = ?, SHOP_TEL = ?, SHOP_FAX = ?, SHOP_URL = ?, SHOP_POST = ?, SHOP_ADDRESS = ?, SERVICE_KEY = ?, LICENSE_KEY = ?, UPDATE_TIME = ?, UPDATE_USER = ?, YAHOO_APP_ID = ?, AWS_ACCESS_KEY_ID = ?, AWS_SECRET_KEY = ?, AWS_ARN = ?, AWS_CLIENT_ID = ?, AWS_CLIENT_SECRET = ?, AWS_REFRESH_TOKEN = ?, AU_APIKEY = ?, COMPANY_ID = ? WHERE SITE = ? and SHOP_ID = ?";
				ps = conn.prepareStatement(sql);

				ps.setString(1, f180101.getShopName());
				ps.setString(2, f180101.getShopNumber());
				ps.setString(3, f180101.getPhoneNumber());
				ps.setString(4, f180101.getFax());
				ps.setString(5, f180101.getShopUrl());
				ps.setString(6, f180101.getShopPost());
				ps.setString(7, f180101.getShopAddress());
				ps.setString(8, f180101.getServiceKey());
				ps.setString(9, f180101.getLicenseKey());
				ps.setString(10, Utility.getDateTime());
				ps.setString(11, Utility.getUser());
				ps.setString(12, f180101.getApplicationID());
				ps.setString(13, f180101.getAwsAccessKeyId());
				ps.setString(14, f180101.getAwsSecretKey());
				ps.setString(15, f180101.getAwsARN());
				ps.setString(16, f180101.getAwsClientId());
				ps.setString(17, f180101.getAwsClientSecret());
				ps.setString(18, f180101.getAwsRefreshToken());
				ps.setString(19, f180101.getAuApiKey());
				ps.setInt(20, f180101.getCompanyId());
				ps.setString(21, f180101.getPlatform());
				ps.setString(22, f180101.getShopId());
				
				ps.execute();
				
				conn.commit();
				
				f180101.setPlatform(null);
				f180101.setShopId(null);
				f180101.setShopName(null);
				f180101.setShopNumber(null);;
				f180101.setPhoneNumber(null);;
				f180101.setFax(null);
				f180101.setShopUrl(null);
				f180101.setShopPost(null);
				f180101.setShopAddress(null);
				f180101.setServiceKey(null);
				f180101.setLicenseKey(null);
				f180101.setApplicationID(null);
				f180101.setAccessToken(null);
				f180101.setRefreshToken(null);
				f180101.setLoginTime(null);
				f180101.setAwsAccessKeyId(null);
				f180101.setAwsSecretKey(null);
				f180101.setAwsARN(null);
				f180101.setAwsClientId(null);
				f180101.setAwsClientSecret(null);
				f180101.setAwsRefreshToken(null);
				f180101.setAuApiKey(null);
				f180101.setCompanyId(0);
				
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
