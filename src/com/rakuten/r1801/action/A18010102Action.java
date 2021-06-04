package com.rakuten.r1801.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1801.form.F180101;
import com.rakuten.util.JdbcConnection;

public class A18010102Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F180101 f180101 = new F180101();

	protected void exec() throws Exception {
		
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM SHOP WHERE SITE = ? AND SHOP_ID = ? AND DELETE_FLG is null";
			ps = conn.prepareStatement(sql);
			ps.setString(1, f180101.getPlatformSelect());
			ps.setString(2, f180101.getShopSelect());
			
			if ("0".equals(f180101.getPlatformSelect())) {
				addError(null, "请选择平台");
			}else if("0".equals(f180101.getShopSelect())) {
				addError(null, "请选择店铺");
			}else {
				rs = ps.executeQuery();
				
				int rowCount = 0;
				while (rs.next()) {
					rowCount++;
					
					f180101.setPlatform(rs.getString("SITE"));
					f180101.setShopId(rs.getString("SHOP_ID"));
					f180101.setShopName(rs.getString("SHOP_NAME"));
					f180101.setShopNumber(rs.getString("SHOP_NO"));;
					f180101.setPhoneNumber(rs.getString("SHOP_TEL"));;
					f180101.setFax(rs.getString("SHOP_FAX"));
					f180101.setShopUrl(rs.getString("SHOP_URL"));
					f180101.setShopPost(rs.getString("SHOP_POST"));
					f180101.setShopAddress(rs.getString("SHOP_ADDRESS"));
					f180101.setServiceKey(rs.getString("SERVICE_KEY"));
					f180101.setLicenseKey(rs.getString("LICENSE_KEY"));
					f180101.setApplicationID(rs.getString("YAHOO_APP_ID"));
					f180101.setAccessToken(rs.getString("ACCESS_TOKEN"));
					f180101.setRefreshToken(rs.getString("REFRESH_TOKEN"));
					f180101.setLoginTime(rs.getString("LOGIN_TIME"));
					f180101.setAwsAccessKeyId(rs.getString("AWS_ACCESS_KEY_ID"));
					f180101.setAwsSecretKey(rs.getString("AWS_SECRET_KEY"));
					f180101.setAwsARN(rs.getString("AWS_ARN"));
					f180101.setAwsClientId(rs.getString("AWS_CLIENT_ID"));
					f180101.setAwsClientSecret(rs.getString("AWS_CLIENT_SECRET"));
					f180101.setAwsRefreshToken(rs.getString("AWS_REFRESH_TOKEN"));
					f180101.setAuApiKey(rs.getString("AU_APIKEY"));
					
				}
				if (rowCount == 0) {
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
					
					addError(null, "选择的平台和店铺的查找信息不存在！");
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
