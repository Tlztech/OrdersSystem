package com.rakuten.common.bean;

import java.io.Serializable;
import java.util.List;

public class ShouhinStsBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// 商品番号
	private String shouhinbango = null;
	// 上海在庫数
	private String shanghaiStock = "0";
	// 日本個数
	private String nihonStock = "0";
	// 入荷中個数
	private String nyukachukosu = "0";
	// 運送途中個数
	private String unsochukosu = "0";
	// 残り個数(上海)
	private String nokorikosuSh = "0";
	// 残り個数(日本)
	private String nokorikosuJp = "0";
	// 残り個数(運送)
	private String nokorikosuUnso = "0";
	// 残り個数(入荷)
	private String nokorikosuNyuka = "0";
	// 商品ステータス詳細情報
	private List<ShohinStsInfoBean> shohinStsInfoBeanList = null;

	/**
	 * @return the shouhinbango
	 */
	public String getShouhinbango() {
		return shouhinbango;
	}

	/**
	 * @param shouhinbango
	 *            the shouhinbango to set
	 */
	public void setShouhinbango(String shouhinbango) {
		this.shouhinbango = shouhinbango;
	}

	/**
	 * @return the shanghaiStock
	 */
	public String getShanghaiStock() {
		return shanghaiStock;
	}

	/**
	 * @param shanghaiStock
	 *            the shanghaiStock to set
	 */
	public void setShanghaiStock(String shanghaiStock) {
		this.shanghaiStock = shanghaiStock;
	}

	/**
	 * @return the nihonStock
	 */
	public String getNihonStock() {
		return nihonStock;
	}

	/**
	 * @param nihonStock
	 *            the nihonStock to set
	 */
	public void setNihonStock(String nihonStock) {
		this.nihonStock = nihonStock;
	}

	/**
	 * @return the nyukachukosu
	 */
	public String getNyukachukosu() {
		return nyukachukosu;
	}

	/**
	 * @param nyukachukosu
	 *            the nyukachukosu to set
	 */
	public void setNyukachukosu(String nyukachukosu) {
		this.nyukachukosu = nyukachukosu;
	}

	/**
	 * @return the unsochukosu
	 */
	public String getUnsochukosu() {
		return unsochukosu;
	}

	/**
	 * @param unsochukosu
	 *            the unsochukosu to set
	 */
	public void setUnsochukosu(String unsochukosu) {
		this.unsochukosu = unsochukosu;
	}

	/**
	 * @return the shohinStsInfoBeanList
	 */
	public List<ShohinStsInfoBean> getShohinStsInfoBeanList() {
		return shohinStsInfoBeanList;
	}

	/**
	 * @param shohinStsInfoBeanList
	 *            the shohinStsInfoBeanList to set
	 */
	public void setShohinStsInfoBeanList(
			List<ShohinStsInfoBean> shohinStsInfoBeanList) {
		this.shohinStsInfoBeanList = shohinStsInfoBeanList;
	}

	/**
	 * @return the nokorikosuSh
	 */
	public String getNokorikosuSh() {
		return nokorikosuSh;
	}

	/**
	 * @param nokorikosuSh
	 *            the nokorikosuSh to set
	 */
	public void setNokorikosuSh(String nokorikosuSh) {
		this.nokorikosuSh = nokorikosuSh;
	}

	/**
	 * @return the nokorikosuJp
	 */
	public String getNokorikosuJp() {
		return nokorikosuJp;
	}

	/**
	 * @param nokorikosuJp
	 *            the nokorikosuJp to set
	 */
	public void setNokorikosuJp(String nokorikosuJp) {
		this.nokorikosuJp = nokorikosuJp;
	}

	/**
	 * @return the nokorikosuUnso
	 */
	public String getNokorikosuUnso() {
		return nokorikosuUnso;
	}

	/**
	 * @param nokorikosuUnso
	 *            the nokorikosuUnso to set
	 */
	public void setNokorikosuUnso(String nokorikosuUnso) {
		this.nokorikosuUnso = nokorikosuUnso;
	}

	/**
	 * @return the nokorikosuNyuka
	 */
	public String getNokorikosuNyuka() {
		return nokorikosuNyuka;
	}

	/**
	 * @param nokorikosuNyuka
	 *            the nokorikosuNyuka to set
	 */
	public void setNokorikosuNyuka(String nokorikosuNyuka) {
		this.nokorikosuNyuka = nokorikosuNyuka;
	}

}
