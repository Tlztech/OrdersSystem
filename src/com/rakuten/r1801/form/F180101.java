package com.rakuten.r1801.form;

import java.io.Serializable;
import java.util.List;

public class F180101 implements Serializable {
	private static final long serialVersionUID = 1L;
	private String platform = null;
	private String shopId = null;
	private String shopName = null;
	private String shopNumber = null;
	private String phoneNumber = null;
	private String fax = null;
	private String shopUrl = null;
	private String shopPost = null;
	private String shopAddress = null;
	private String serviceKey = null;
	private String licenseKey = null;
	private String applicationID = null;
	private String accessToken = null;
	private String refreshToken = null;
	private String loginTime = null;
	private static List<String> siteList = null;
	private static List<String> shopList = null;
	private String platformSelect = null;
	private String shopSelect = null;
	

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}
	
	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	
	public String getShopNumber() {
		return shopNumber;
	}

	public void setShopNumber(String shopNumber) {
		this.shopNumber = shopNumber;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	public String getShopUrl() {
		return shopUrl;
	}

	public void setShopUrl(String shopUrl) {
		this.shopUrl = shopUrl;
	}
	
	public String getShopPost() {
		return shopPost;
	}

	public void setShopPost(String shopPost) {
		this.shopPost = shopPost;
	}
	
	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	
	public String getServiceKey() {
		return serviceKey;
	}

	public void setServiceKey(String serviceKey) {
		this.serviceKey = serviceKey;
	}
	
	public String getLicenseKey() {
		return licenseKey;
	}

	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	
	public String getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	
	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	
	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	
	public List<String> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<String> siteListDB) {
		siteList = siteListDB;
	}
	
	public List<String> getShopList() {
		return shopList;
	}

	public void setShopList(List<String> shopListDB) {
		shopList = shopListDB;
	}

	public String getPlatformSelect() {
		return platformSelect;
	}

	public void setPlatformSelect(String platformSelect) {
		this.platformSelect = platformSelect;
	}
	
	public String getShopSelect() {
		return shopSelect;
	}

	public void setShopSelect(String shopSelect) {
		this.shopSelect = shopSelect;
	}
}
