package com.rakuten.r0302.form;

import java.io.Serializable;

public class CommodityDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	private String detailNo;
	private String describe;
	private String picUrl;
	private String priceIn;
	private String rePrice;
	private String barCode;
	private String stockSh;
	private String stockJp;
	private String stockHandup;
	private String remarks;
	private String nyuka;
	private boolean delFlg;
	private String createTime;
	private String updateTime;

	/**
	 * @return the detailNo
	 */
	public String getDetailNo() {
		return detailNo;
	}

	/**
	 * @param detailNo
	 *            the detailNo to set
	 */
	public void setDetailNo(String detailNo) {
		this.detailNo = detailNo;
	}

	/**
	 * @return the describe
	 */
	public String getDescribe() {
		return describe;
	}

	/**
	 * @param describe
	 *            the describe to set
	 */
	public void setDescribe(String describe) {
		this.describe = describe;
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
	 * @return the priceIn
	 */
	public String getPriceIn() {
		return priceIn;
	}

	/**
	 * @param priceIn
	 *            the priceIn to set
	 */
	public void setPriceIn(String priceIn) {
		this.priceIn = priceIn;
	}

	/**
	 * @return the rePrice
	 */
	public String getRePrice() {
		return rePrice;
	}

	/**
	 * @param rePrice
	 *            the rePrice to set
	 */
	public void setRePrice(String rePrice) {
		this.rePrice = rePrice;
	}

	/**
	 * @return the barCode
	 */
	public String getBarCode() {
		return barCode;
	}

	/**
	 * @param barCode
	 *            the barCode to set
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
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

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks
	 *            the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getNyuka() {
		return nyuka;
	}

	public void setNyuka(String nyuka) {
		this.nyuka = nyuka;
	}

	public boolean isDelFlg() {
		return delFlg;
	}

	public void setDelFlg(boolean delFlg) {
		this.delFlg = delFlg;
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

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
