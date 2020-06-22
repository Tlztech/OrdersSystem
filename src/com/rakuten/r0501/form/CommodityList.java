package com.rakuten.r0501.form;

import java.io.Serializable;

public class CommodityList implements Serializable {

	private static final long serialVersionUID = 1L;

	private String checkFlg = null;
	private String commodityId = null;
	private String status = null;
	private String categoryName = null;
	private String chineseName = null;
	private String commodityUrl = null;

	/**
	 * @return the checkFlg
	 */
	public String getCheckFlg() {
		return checkFlg;
	}

	/**
	 * @param checkFlg
	 *            the checkFlg to set
	 */
	public void setCheckFlg(String checkFlg) {
		this.checkFlg = checkFlg;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
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
	 * @return the commodityUrl
	 */
	public String getCommodityUrl() {
		return commodityUrl;
	}

	/**
	 * @param commodityUrl
	 *            the commodityUrl to set
	 */
	public void setCommodityUrl(String commodityUrl) {
		this.commodityUrl = commodityUrl;
	}

}
