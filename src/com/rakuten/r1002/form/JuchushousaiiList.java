package com.rakuten.r1002.form;

import java.io.Serializable;

public class JuchushousaiiList implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String picurl = null;
	private String shohinbango = null;
	private String shohinmei = null;
	private String shohinjoutai = null;
	private String kosu = null;

	public String getPicurl() {
		return picurl;
	}

	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

	public String getShohinmei() {
		return shohinmei;
	}

	public void setShohinmei(String shohinmei) {
		this.shohinmei = shohinmei;
	}

	public String getShohinjoutai() {
		return shohinjoutai;
	}

	public void setShohinjoutai(String shohinjoutai) {
		this.shohinjoutai = shohinjoutai;
	}

	public String getKosu() {
		return kosu;
	}

	public void setKosu(String kosu) {
		this.kosu = kosu;
	}

}
