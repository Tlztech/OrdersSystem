package com.rakuten.r1001.form;

import java.io.Serializable;

public class BunnohasomachiList implements Serializable {

	private static final long serialVersionUID = 1L;

	private String shohinbango = null;
	private String kosu = null;
	private String sutetasu = null;

	/**
	 * @return the shohinbango
	 */
	public String getShohinbango() {
		return shohinbango;
	}

	/**
	 * @param shohinbango
	 *            the shohinbango to set
	 */
	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

	/**
	 * @return the kosu
	 */
	public String getKosu() {
		return kosu;
	}

	/**
	 * @param kosu
	 *            the kosu to set
	 */
	public void setKosu(String kosu) {
		this.kosu = kosu;
	}

	/**
	 * @return the sutetasu
	 */
	public String getSutetasu() {
		return sutetasu;
	}

	/**
	 * @param sutetasu
	 *            the sutetasu to set
	 */
	public void setSutetasu(String sutetasu) {
		this.sutetasu = sutetasu;
	}

}
