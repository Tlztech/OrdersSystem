package com.rakuten.r0601.form;

import java.io.Serializable;
import java.util.List;

public class F060102 implements Serializable {

	private static final long serialVersionUID = 1L;

	private String deliverDay = null;
	private String waybillNo = null;
	private String logistics = null;
	private String commodityIdInput = null;
	private String quantityInput = null;
	private String remarksInput = null;
	private String freight = null;
	private String weight = null;
	private String hid_waybillNo = null;
	List<CommodityList> commodityList = null;

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
	 * @return the hid_waybillNo
	 */
	public String getHid_waybillNo() {
		return hid_waybillNo;
	}

	/**
	 * @param hid_waybillNo
	 *            the hid_waybillNo to set
	 */
	public void setHid_waybillNo(String hid_waybillNo) {
		this.hid_waybillNo = hid_waybillNo;
	}

	/**
	 * @param logistics
	 *            the logistics to set
	 */
	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

	/**
	 * @return the commodityIdInput
	 */
	public String getCommodityIdInput() {
		return commodityIdInput;
	}

	/**
	 * @param commodityIdInput
	 *            the commodityIdInput to set
	 */
	public void setCommodityIdInput(String commodityIdInput) {
		this.commodityIdInput = commodityIdInput;
	}

	/**
	 * @return the quantityInput
	 */
	public String getQuantityInput() {
		return quantityInput;
	}

	/**
	 * @param quantityInput
	 *            the quantityInput to set
	 */
	public void setQuantityInput(String quantityInput) {
		this.quantityInput = quantityInput;
	}

	/**
	 * @return the remarksInput
	 */
	public String getRemarksInput() {
		return remarksInput;
	}

	/**
	 * @param remarksInput
	 *            the remarksInput to set
	 */
	public void setRemarksInput(String remarksInput) {
		this.remarksInput = remarksInput;
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

	public String getFreight() {
		return freight;
	}

	public void setFreight(String freight) {
		this.freight = freight;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

}
