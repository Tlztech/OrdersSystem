package com.rakuten.r0601.bean;

import java.io.Serializable;

public class GetWayBillApInput implements Serializable {

	private static final long serialVersionUID = 1L;

	private String waybillNo;
	private String logistics;
	private String status;
	private String delieverDay;
	private String receiveDay;
	private String customs;
	private int companyId;
	private String commodityId;

	/**
	 * @return the waybillNo
	 */
	public String getWaybillNo() {
		return waybillNo;
	}

	/**
	 * @param waybillNo
	 *            the waybillNo to set
	 */
	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	/**
	 * @return the logistics
	 */
	public String getLogistics() {
		return logistics;
	}

	/**
	 * @param logistics
	 *            the logistics to set
	 */
	public void setLogistics(String logistics) {
		this.logistics = logistics;
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
	 * @return the delieverDay
	 */
	public String getDelieverDay() {
		return delieverDay;
	}

	/**
	 * @param delieverDay
	 *            the delieverDay to set
	 */
	public void setDelieverDay(String delieverDay) {
		this.delieverDay = delieverDay;
	}

	/**
	 * @return the receiveDay
	 */
	public String getReceiveDay() {
		return receiveDay;
	}

	/**
	 * @param receiveDay
	 *            the receiveDay to set
	 */
	public void setReceiveDay(String receiveDay) {
		this.receiveDay = receiveDay;
	}

	/**
	 * @return the customs
	 */
	public String getCustoms() {
		return customs;
	}

	/**
	 * @param customs
	 *            the customs to set
	 */
	public void setCustoms(String customs) {
		this.customs = customs;
	}

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}

}
