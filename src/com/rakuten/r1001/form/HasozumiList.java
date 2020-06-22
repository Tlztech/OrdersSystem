package com.rakuten.r1001.form;

import java.io.Serializable;

public class HasozumiList implements Serializable {

	private static final long serialVersionUID = 1L;

	private String shohinbango = null;
	private String kosu = null;

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

	public String getKosu() {
		return kosu;
	}

	public void setKosu(String kosu) {
		this.kosu = kosu;
	}

}
