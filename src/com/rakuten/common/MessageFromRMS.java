package com.rakuten.common;

public class MessageFromRMS {

	private String messageType;
	private String messageCode;
	private String message;
	private String orderNumber;
	private int dataNumber;
	private int shippingDetailId;
	
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
	
}
