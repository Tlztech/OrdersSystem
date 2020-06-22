package com.rakuten.r0503.form;

import java.io.Serializable;
import java.util.List;

public class F050301 implements Serializable {

	private static final long serialVersionUID = 1L;

	private String commodityId = null;
	private String categoryId = null;
	private String categoryName = null;
	private String chineseName = null;
	private String japaneseName = null;
	private String resultCount = null;
	private String type = null;
	List<CommodityList> commodityList = null;

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
	 * @return the resultCount
	 */
	public String getResultCount() {
		return resultCount;
	}

	/**
	 * @param resultCount
	 *            the resultCount to set
	 */
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the commodityList
	 */
	public List<CommodityList> getCommodityList() {
		return commodityList;
	}

	/**
	 * @param commodityList
	 *            the commodityList to set
	 */
	public void setCommodityList(List<CommodityList> commodityList) {
		this.commodityList = commodityList;
	}

}
