package com.rakuten.common.bean;

import java.io.Serializable;

public class CommonOrderDetailrBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 処理番号
	private String shoribango = null;
	// 商品番号
	private String shohinbango = null;
	// 個数
	private int kosu = 0;
	// 上海STOCK
	private int shanghaiStock = 0;
	// 日本STOCK
	private int nihonStock = 0;
	// 順番情報
	private String[] junbanJoho = null;
	// 運送中情報
	private String[] unsochuJoho = null;
	// 入荷中情報
	private String[] nyukachuJoho = null;
	// ステータス
	private String[] status = null;
	// その他情報
	private String[] sonotaJoho = null;
	// 種別
	private String shubetsu = null;

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
	public int getKosu() {
		return kosu;
	}

	/**
	 * @param kosu
	 *            the kosu to set
	 */
	public void setKosu(int kosu) {
		this.kosu = kosu;
	}

	/**
	 * @return the shanghaiStock
	 */
	public int getShanghaiStock() {
		return shanghaiStock;
	}

	/**
	 * @param shanghaiStock
	 *            the shanghaiStock to set
	 */
	public void setShanghaiStock(int shanghaiStock) {
		this.shanghaiStock = shanghaiStock;
	}

	/**
	 * @return the nihonStock
	 */
	public int getNihonStock() {
		return nihonStock;
	}

	/**
	 * @param nihonStock
	 *            the nihonStock to set
	 */
	public void setNihonStock(int nihonStock) {
		this.nihonStock = nihonStock;
	}

	/**
	 * @return the junbanJoho
	 */
	public String[] getJunbanJoho() {
		return junbanJoho;
	}

	/**
	 * @param junbanJoho
	 *            the junbanJoho to set
	 */
	public void setJunbanJoho(String[] junbanJoho) {
		this.junbanJoho = junbanJoho;
	}

	/**
	 * @return the unsochuJoho
	 */
	public String[] getUnsochuJoho() {
		return unsochuJoho;
	}

	/**
	 * @param unsochuJoho
	 *            the unsochuJoho to set
	 */
	public void setUnsochuJoho(String[] unsochuJoho) {
		this.unsochuJoho = unsochuJoho;
	}

	/**
	 * @return the nyukachuJoho
	 */
	public String[] getNyukachuJoho() {
		return nyukachuJoho;
	}

	/**
	 * @param nyukachuJoho
	 *            the nyukachuJoho to set
	 */
	public void setNyukachuJoho(String[] nyukachuJoho) {
		this.nyukachuJoho = nyukachuJoho;
	}

	/**
	 * @return the status
	 */
	public String[] getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String[] status) {
		this.status = status;
	}

	/**
	 * @return the sonotaJoho
	 */
	public String[] getSonotaJoho() {
		return sonotaJoho;
	}

	/**
	 * @param sonotaJoho
	 *            the sonotaJoho to set
	 */
	public void setSonotaJoho(String[] sonotaJoho) {
		this.sonotaJoho = sonotaJoho;
	}

	public String getShoribango() {
		return shoribango;
	}

	public void setShoribango(String shoribango) {
		this.shoribango = shoribango;
	}

	public String getShubetsu() {
		return shubetsu;
	}

	public void setShubetsu(String shubetsu) {
		this.shubetsu = shubetsu;
	}

}
