package com.rakuten.r1001.form;

import java.io.Serializable;

public class HenpinList implements Serializable {
	private static final long serialVersionUID = 1L;

	private boolean checkdFlg = false;
	private String shouhinmei = null;
	private String hassoushubetsu = null;
	private String hasokosu = null;
	private String shohinbango = null;
	private String hasounichiji = null;
	private String henpinkosu = null;
	private boolean sairiyokano = false;
	private boolean kakuninhituyo = false;

	/**
	 * @return the sairiyokano
	 */
	public boolean isSairiyokano() {
		return sairiyokano;
	}

	/**
	 * @param sairiyokano
	 *            the sairiyokano to set
	 */
	public void setSairiyokano(boolean sairiyokano) {
		this.sairiyokano = sairiyokano;
	}

	/**
	 * @return the kakuninhituyo
	 */
	public boolean isKakuninhituyo() {
		return kakuninhituyo;
	}

	/**
	 * @param kakuninhituyo
	 *            the kakuninhituyo to set
	 */
	public void setKakuninhituyo(boolean kakuninhituyo) {
		this.kakuninhituyo = kakuninhituyo;
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
	 * @return the hassoushubetsu
	 */
	public String getHassoushubetsu() {
		return hassoushubetsu;
	}

	/**
	 * @param hassoushubetsu
	 *            the hassoushubetsu to set
	 */
	public void setHassoushubetsu(String hassoushubetsu) {
		this.hassoushubetsu = hassoushubetsu;
	}

	/**
	 * @return the hasokosu
	 */
	public String getHasokosu() {
		return hasokosu;
	}

	/**
	 * @param hasokosu
	 *            the hasokosu to set
	 */
	public void setHasokosu(String hasokosu) {
		this.hasokosu = hasokosu;
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

	/**
	 * @return the hasounichiji
	 */
	public String getHasounichiji() {
		return hasounichiji;
	}

	/**
	 * @param hasounichiji
	 *            the hasounichiji to set
	 */
	public void setHasounichiji(String hasounichiji) {
		this.hasounichiji = hasounichiji;
	}

	/**
	 * @return the henpinkosu
	 */
	public String getHenpinkosu() {
		return henpinkosu;
	}

	/**
	 * @param henpinkosu
	 *            the henpinkosu to set
	 */
	public void setHenpinkosu(String henpinkosu) {
		this.henpinkosu = henpinkosu;
	}

	public boolean isCheckdFlg() {
		return checkdFlg;
	}

	public void setCheckdFlg(boolean checkdFlg) {
		this.checkdFlg = checkdFlg;
	}

}
