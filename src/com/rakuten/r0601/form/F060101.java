package com.rakuten.r0601.form;

import java.io.Serializable;
import java.util.List;

import com.rakuten.r1801.form.F180101.Company;

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
	private static List<Company> companyList = null;
	private int companyId;
	private String commodityId;
	private String createTime = null;
	private String updateTime = null;
	private int queryResult;


	public int getQueryResult() {
		return queryResult;
	}

	public void setQueryResult(int queryResult) {
		this.queryResult = queryResult;
	}

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

	/**
	 * @return the companyList
	 */
	public List<Company> getCompanyList() {
		return companyList;
	}

	/**
	 * @param companyList the companyList to set
	 */
	public void setCompanyList(List<Company> companyList) {
		F060101.companyList = companyList;
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

	public static class Company {
		private int companyId;
		private String companyName;
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
		/**
		 * @return the companyName
		 */
		public String getCompanyName() {
			return companyName;
		}
		/**
		 * @param companyName the companyName to set
		 */
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		
	}

	public String getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(String commodityId) {
		this.commodityId = commodityId;
	}
}
