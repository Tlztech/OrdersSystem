package com.rakuten.r0302.bean;

import java.io.Serializable;

public class GetCommodityApInput implements Serializable {

	private static final long serialVersionUID = 1L;
	private String commodityId;
	private String categoryId;
	private String chineseName;
	private String japaneseName;
	private String stockShStart = null;
	private String stockJpStart = null;
	private String stockShEnd = null;
	private String stockJpEnd = null;
	private String updateTimeStart = null;
	private String updateTimeEnd = null;

	/**
	 * @return the stockShStart
	 */
	public String getStockShStart() {
		return stockShStart;
	}

	/**
	 * @param stockShStart
	 *            the stockShStart to set
	 */
	public void setStockShStart(String stockShStart) {
		this.stockShStart = stockShStart;
	}

	/**
	 * @return the stockJpStart
	 */
	public String getStockJpStart() {
		return stockJpStart;
	}

	/**
	 * @param stockJpStart
	 *            the stockJpStart to set
	 */
	public void setStockJpStart(String stockJpStart) {
		this.stockJpStart = stockJpStart;
	}

	/**
	 * @return the stockShEnd
	 */
	public String getStockShEnd() {
		return stockShEnd;
	}

	/**
	 * @param stockShEnd
	 *            the stockShEnd to set
	 */
	public void setStockShEnd(String stockShEnd) {
		this.stockShEnd = stockShEnd;
	}

	/**
	 * @return the stockJpEnd
	 */
	public String getStockJpEnd() {
		return stockJpEnd;
	}

	/**
	 * @param stockJpEnd
	 *            the stockJpEnd to set
	 */
	public void setStockJpEnd(String stockJpEnd) {
		this.stockJpEnd = stockJpEnd;
	}

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
	 * @return the categoryId
	 */
	public String getCategoryId() {
		return categoryId;
	}

	/**
	 * @param categoryId
	 *            the categoryId to set
	 */
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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
	 * @return the updateTimeStart
	 */
	public String getUpdateTimeStart() {
		return updateTimeStart;
	}

	/**
	 * @param updateTimeStart the updateTimeStart to set
	 */
	public void setUpdateTimeStart(String updateTimeStart) {
		this.updateTimeStart = updateTimeStart;
	}

	/**
	 * @return the updateTimeEnd
	 */
	public String getUpdateTimeEnd() {
		return updateTimeEnd;
	}

	/**
	 * @param updateTimeEnd the updateTimeEnd to set
	 */
	public void setUpdateTimeEnd(String updateTimeEnd) {
		this.updateTimeEnd = updateTimeEnd;
	}

}
