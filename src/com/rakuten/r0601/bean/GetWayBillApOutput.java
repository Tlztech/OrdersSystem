package com.rakuten.r0601.bean;

import java.io.Serializable;
import java.util.Date;

public class GetWayBillApOutput implements Serializable {

	private static final long serialVersionUID = 1L;
	private String waybillNo = null;
	private String logistics = null;
	private String status = null;
	private String deliverDay = null;
	private String receiveDay = null;
	private String weight = null;
	private String freight = null;
	private String customs = null;
	private String createTime = null;
	private String updateTime = null;

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

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
	 * @return the deliverDay
	 */
	public String getDeliverDay() {
		return deliverDay;
	}

	/**
	 * @param deliverDay
	 *            the deliverDay to set
	 */
	public void setDeliverDay(String deliverDay) {
		this.deliverDay = deliverDay;
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
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}

	/**
	 * @return the freight
	 */
	public String getFreight() {
		return freight;
	}

	/**
	 * @param freight
	 *            the freight to set
	 */
	public void setFreight(String freight) {
		this.freight = freight;
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
}
