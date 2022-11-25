package com.rakuten.r0302.form;

import java.io.Serializable;

public class CommodityList implements Serializable {

	private static final long serialVersionUID = 1L;

	private String commodityId = null;
	private String categoryName = null;
	private String chineseName = null;
	private String japaneseName = null;
	private String picUrl = null;
	private String stockSh = null;
	private String stockJp = null;
	private String stockHandup = null;
	private String nyuka = null;
	private String shop = null;
	private String createTime;

	/**
	 * @return the commodityId
	 */
	public String getCommodityId() {
		return commodityId;
	}

	/**
	 * @param commodityId
	 *            the commodityId to set
	 */
	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the chineseName
	 */
	public String getChineseName() {
		return chineseName;
	}

	/**
	 * @param chineseName
	 *            the chineseName to set
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	/**
	 * @return the japaneseName
	 */
	public String getJapaneseName() {
		return japaneseName;
	}

	/**
	 * @param japaneseName
	 *            the japaneseName to set
	 */
	public void setJapaneseName(String japaneseName) {
		this.japaneseName = japaneseName;
	}

	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl
	 *            the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * @return the stockSh
	 */
	public String getStockSh() {
		return stockSh;
	}

	/**
	 * @param stockSh
	 *            the stockSh to set
	 */
	public void setStockSh(String stockSh) {
		this.stockSh = stockSh;
	}

	/**
	 * @return the stockJp
	 */
	public String getStockJp() {
		return stockJp;
	}

	/**
	 * @param stockJp
	 *            the stockJp to set
	 */
	public void setStockJp(String stockJp) {
		this.stockJp = stockJp;
	}

	/**
	 * @return the stockHandup
	 */
	public String getStockHandup() {
		return stockHandup;
	}

	/**
	 * @param stockHandup
	 *            the stockHandup to set
	 */
	public void setStockHandup(String stockHandup) {
		this.stockHandup = stockHandup;
	}

	public String getNyuka() {
		return nyuka;
	}

	public void setNyuka(String nyuka) {
		this.nyuka = nyuka;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
