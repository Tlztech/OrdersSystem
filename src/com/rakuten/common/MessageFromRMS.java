package com.rakuten.common;

import java.util.Map;

public class MessageFromRMS {

	private String messageType;
	private String messageCode;
	private String message;
	private String orderNumber;
	private int dataNumber;
	private int shippingDetailId;
	private String code;
	private Map metadata;
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageCode() {
		return messageCode;
	}
	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public int getDataNumber() {
		return dataNumber;
	}
	public void setDataNumber(int dataNumber) {
		this.dataNumber = dataNumber;
	}
	public int getShippingDetailId() {
		return shippingDetailId;
	}
	public void setShippingDetailId(int shippingDetailId) {
		this.shippingDetailId = shippingDetailId;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the metadata
	 */
	public Map getMetadata() {
		return metadata;
	}
	/**
	 * @param metadata the metadata to set
	 */
	public void setMetadata(Map metadata) {
		this.metadata = metadata;
	}
	
}
