package com.rakuten.r1801.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1801.form.F180101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A18010103Action extends BaseAction {

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
				addError(null, "平台和店铺已经存在，请做更新处理");
			} else {
				sql = "INSERT INTO `rakuten`.`shop`(`SITE`,`SHOP_ID`,`SHOP_NAME`,`SHOP_NO`,`SHOP_TEL`,`SHOP_FAX`,`SHOP_URL`,`SHOP_POST`,`SHOP_ADDRESS`,`SERVICE_KEY`,`LICENSE_KEY`,`YAHOO_APP_ID`,`ACCESS_TOKEN`,`REFRESH_TOKEN`,`LOGIN_TIME`,`CREATE_TIME`,`CREATE_USER`,`UPDATE_TIME`,`UPDATE_USER`,`DELETE_FLG`,`AWS_ACCESS_KEY_ID`,`AWS_SECRET_KEY`,`AWS_ARN`,`AWS_CLIENT_ID`,`AWS_CLIENT_SECRET`,`AWS_REFRESH_TOKEN`, `AU_APIKEY`)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, f180101.getPlatform());
				ps.setString(2, f180101.getShopId());
				ps.setString(3, f180101.getShopName());
				ps.setString(4, f180101.getShopNumber());
				ps.setString(5, f180101.getPhoneNumber());
				ps.setString(6, f180101.getFax());
				ps.setString(7, f180101.getShopUrl());
				ps.setString(8, f180101.getShopPost());
				ps.setString(9, f180101.getShopAddress());
				ps.setString(10, f180101.getServiceKey());
				ps.setString(11, f180101.getLicenseKey());
				ps.setString(12, f180101.getApplicationID());
				ps.setString(13, null);
				ps.setString(14, null);
				ps.setString(15, null);
				ps.setString(16, Utility.getDateTime());
				ps.setString(17, Utility.getUser());
				ps.setString(18, null);
				ps.setString(19, null);
				ps.setString(20, null);
				ps.setString(21, f180101.getAwsAccessKeyId());
				ps.setString(22, f180101.getAwsSecretKey());
				ps.setString(23, f180101.getAwsARN());
				ps.setString(24, f180101.getAwsClientId());
				ps.setString(25, f180101.getAwsClientSecret());
				ps.setString(26, f180101.getAwsRefreshToken());
				ps.setString(27, f180101.getAuApiKey());
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
				f180101.setAwsAccessKeyId(null);
				f180101.setAwsSecretKey(null);
				f180101.setAwsARN(null);
				f180101.setAwsClientId(null);
				f180101.setAwsClientSecret(null);
				f180101.setAwsRefreshToken(null);
				f180101.setAuApiKey(null);
				
				addError(null, "添加成功");
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
