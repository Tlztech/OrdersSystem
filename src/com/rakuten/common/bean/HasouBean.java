package com.rakuten.common.bean;

import java.io.Serializable;

public class HasouBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// 処理番号
	private String shoribango = null;
	// 受注番号
	private String juchubango = null;
	// 商品番号
	private String shohinbango = null;
	// 発送個数
	private String kosu = null;
	// お荷物問い合わせ番号
	private String toiawasebango = null;
	// 種別
	private String shubetsu = null;
	// 運送会社
	private String unsokaisha = null;
	// 発送日
	private String hasobi = null;
	// 配送方法
	private String haisohoho = null;

	/**
	 * @return the shoribango
	 */
	public String getShoribango() {
		return shoribango;
	}

	/**
	 * @param shoribango
	 *            the shoribango to set
	 */
	public void setShoribango(String shoribango) {
		this.shoribango = shoribango;
	}

	/**
	 * @return the juchubango
	 */
	public String getJuchubango() {
		return juchubango;
	}

	/**
	 * @param juchubango
	 *            the juchubango to set
	 */
	public void setJuchubango(String juchubango) {
		this.juchubango = juchubango;
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
	 * @return the toiawasebango
	 */
	public String getToiawasebango() {
		return toiawasebango;
	}

	/**
	 * @param toiawasebango
	 *            the toiawasebango to set
	 */
	public void setToiawasebango(String toiawasebango) {
		this.toiawasebango = toiawasebango;
	}

	/**
	 * @return the shubetsu
	 */
	public String getShubetsu() {
		return shubetsu;
	}

	/**
	 * @param shubetsu
	 *            the shubetsu to set
	 */
	public void setShubetsu(String shubetsu) {
		this.shubetsu = shubetsu;
	}

	/**
	 * @return the unsokaisha
	 */
	public String getUnsokaisha() {
		return unsokaisha;
	}

	/**
	 * @param unsokaisha
	 *            the unsokaisha to set
	 */
	public void setUnsokaisha(String unsokaisha) {
		this.unsokaisha = unsokaisha;
	}

	/**
	 * @return the hasobi
	 */
	public String getHasobi() {
		return hasobi;
	}

	/**
	 * @param hasobi
	 *            the hasobi to set
	 */
	public void setHasobi(String hasobi) {
		this.hasobi = hasobi;
	}

	public String getHaisohoho() {
		return haisohoho;
	}

	public void setHaisohoho(String haisohoho) {
		this.haisohoho = haisohoho;
	}

}
