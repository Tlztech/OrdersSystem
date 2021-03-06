package com.rakuten.r0601.form;

import java.io.Serializable;
import java.util.List;

public class F060101 implements Serializable {

	private static final long serialVersionUID = 1L;

	private String deliverDay = null;
	private String receiveDay = null;
	private String waybillNo = null;
	private String customs = null;
	private String logistics = null;
	private String status = null;
	private String resultCount;
	private String passFlg = null;
	List<WayBillInfo> wayBillList = null;

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
	 * @return the wayBillList
	 */
	public List<WayBillInfo> getWayBillList() {
		return wayBillList;
	}

	/**
	 * @param wayBillList
	 *            the wayBillList to set
	 */
	public void setWayBillList(List<WayBillInfo> wayBillList) {
		this.wayBillList = wayBillList;
	}

	public String getPassFlg() {
		return passFlg;
	}

	public void setPassFlg(String passFlg) {
		this.passFlg = passFlg;
	}

}
