package com.rakuten.r1601.form;

import java.io.Serializable;

public class F160101 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String site = null;
	private String shop = null;
	private String shohinbango = null;
	private String chumonbango = null;

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

	public String getChumonbango() {
		return chumonbango;
	}

	public void setChumonbango(String chumonbango) {
		this.chumonbango = chumonbango;
	}

}
