package com.rakuten.r1302.form;

import java.io.Serializable;

public class MidownloadrakutenList implements Serializable{

	private static final long serialVersionUID = 1L;
	private String shopName = null;
	private String shopNumber = null;

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

}
