package com.rakuten.r1001.form;

import java.io.Serializable;

public class BunnouhasokaList implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean checkdFlg = false;
	private String shouhinmei = null;
	private String kosu = null;
	private String shohinbango = null;

	/**
	 * @return the checkdFlg
	 */
	public boolean isCheckdFlg() {
		return checkdFlg;
	}

	/**
	 * @param checkdFlg
	 *            the checkdFlg to set
	 */
	public void setCheckdFlg(boolean checkdFlg) {
		this.checkdFlg = checkdFlg;
	}

	/**
	 * @return the shouhinmei
	 */
	public String getShouhinmei() {
		return shouhinmei;
	}

	/**
	 * @param shouhinmei
	 *            the shouhinmei to set
	 */
	public void setShouhinmei(String shouhinmei) {
		this.shouhinmei = shouhinmei;
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

}
