package com.rakuten.r0703.form;

import java.io.Serializable;
import java.util.List;

public class F070301 implements Serializable {

	private static final long serialVersionUID = 1L;

	private String commodityId;
	private String nyukabiStart;
	private String nyukabiEnd;
	private String categoryId;
	private String chineseName;
	private String japaneseName;
	private String commodityStatus;
	private String printStatus;
	private String resultCount;

	private List<NyukaList> nyukaList = null;

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
	 * @return the nyukabiStart
	 */
	public String getNyukabiStart() {
		return nyukabiStart;
	}

	/**
	 * @param nyukabiStart
	 *            the nyukabiStart to set
	 */
	public void setNyukabiStart(String nyukabiStart) {
		this.nyukabiStart = nyukabiStart;
	}

	/**
	 * @return the nyukabiEnd
	 */
	public String getNyukabiEnd() {
		return nyukabiEnd;
	}

	/**
	 * @param nyukabiEnd
	 *            the nyukabiEnd to set
	 */
	public void setNyukabiEnd(String nyukabiEnd) {
		this.nyukabiEnd = nyukabiEnd;
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
	 * @return the commodityStatus
	 */
	public String getCommodityStatus() {
		return commodityStatus;
	}

	/**
	 * @param commodityStatus
	 *            the commodityStatus to set
	 */
	public void setCommodityStatus(String commodityStatus) {
		this.commodityStatus = commodityStatus;
	}

	/**
	 * @return the printStatus
	 */
	public String getPrintStatus() {
		return printStatus;
	}

	/**
	 * @param printStatus
	 *            the printStatus to set
	 */
	public void setPrintStatus(String printStatus) {
		this.printStatus = printStatus;
	}

	public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public List<NyukaList> getNyukaList() {
		return nyukaList;
	}

	public void setNyukaList(List<NyukaList> nyukaList) {
		this.nyukaList = nyukaList;
	}

}
