package com.rakuten.order;

import java.util.List;

public class AUOrder {

	private String orderId;
	private String orderDate;
	private String memo;
	private String totalSalePrice;
	private String postagePrice;
	private String requestPrice;
	private String ordererZipCode;
	private String ordererAddress;
	private String ordererName;
	private String ordererKana;
	private String ordererPhoneNumber1;
	private String mailAddress;
	private String settlementName;
	private String deliveryName;
	private String usePoint;
	private String senderZipCode;
	private String senderAddress;
	private String senderName;
	private String senderKana;
	private String senderPhoneNumber1;
	private String couponTotalPrice;
	private List<Item> itemList;
	

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the memo
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo the memo to set
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @return the totalSalePrice
	 */
	public String getTotalSalePrice() {
		return totalSalePrice;
	}

	/**
	 * @param totalSalePrice the totalSalePrice to set
	 */
	public void setTotalSalePrice(String totalSalePrice) {
		this.totalSalePrice = totalSalePrice;
	}

	/**
	 * @return the postagePrice
	 */
	public String getPostagePrice() {
		return postagePrice;
	}

	/**
	 * @param postagePrice the postagePrice to set
	 */
	public void setPostagePrice(String postagePrice) {
		this.postagePrice = postagePrice;
	}

	/**
	 * @return the requestPrice
	 */
	public String getRequestPrice() {
		return requestPrice;
	}

	/**
	 * @param requestPrice the requestPrice to set
	 */
	public void setRequestPrice(String requestPrice) {
		this.requestPrice = requestPrice;
	}

	/**
	 * @return the ordererZipCode
	 */
	public String getOrdererZipCode() {
		return ordererZipCode;
	}

	/**
	 * @param ordererZipCode the ordererZipCode to set
	 */
	public void setOrdererZipCode(String ordererZipCode) {
		this.ordererZipCode = ordererZipCode;
	}

	/**
	 * @return the ordererAddress
	 */
	public String getOrdererAddress() {
		return ordererAddress;
	}

	/**
	 * @param ordererAddress the ordererAddress to set
	 */
	public void setOrdererAddress(String ordererAddress) {
		this.ordererAddress = ordererAddress;
	}

	/**
	 * @return the ordererName
	 */
	public String getOrdererName() {
		return ordererName;
	}

	/**
	 * @param ordererName the ordererName to set
	 */
	public void setOrdererName(String ordererName) {
		this.ordererName = ordererName;
	}

	/**
	 * @return the ordererKana
	 */
	public String getOrdererKana() {
		return ordererKana;
	}

	/**
	 * @param ordererKana the ordererKana to set
	 */
	public void setOrdererKana(String ordererKana) {
		this.ordererKana = ordererKana;
	}

	/**
	 * @return the ordererPhoneNumber1
	 */
	public String getOrdererPhoneNumber1() {
		return ordererPhoneNumber1;
	}

	/**
	 * @param ordererPhoneNumber1 the ordererPhoneNumber1 to set
	 */
	public void setOrdererPhoneNumber1(String ordererPhoneNumber1) {
		this.ordererPhoneNumber1 = ordererPhoneNumber1;
	}

	/**
	 * @return the mailAddress
	 */
	public String getMailAddress() {
		return mailAddress;
	}

	/**
	 * @param mailAddress the mailAddress to set
	 */
	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	/**
	 * @return the settlementName
	 */
	public String getSettlementName() {
		return settlementName;
	}

	/**
	 * @param settlementName the settlementName to set
	 */
	public void setSettlementName(String settlementName) {
		this.settlementName = settlementName;
	}

	/**
	 * @return the deliveryName
	 */
	public String getDeliveryName() {
		return deliveryName;
	}

	/**
	 * @param deliveryName the deliveryName to set
	 */
	public void setDeliveryName(String deliveryName) {
		this.deliveryName = deliveryName;
	}

	/**
	 * @return the usePoint
	 */
	public String getUsePoint() {
		return usePoint;
	}

	/**
	 * @param usePoint the usePoint to set
	 */
	public void setUsePoint(String usePoint) {
		this.usePoint = usePoint;
	}

	/**
	 * @return the senderZipCode
	 */
	public String getSenderZipCode() {
		return senderZipCode;
	}

	/**
	 * @param senderZipCode the senderZipCode to set
	 */
	public void setSenderZipCode(String senderZipCode) {
		this.senderZipCode = senderZipCode;
	}

	/**
	 * @return the senderAddress
	 */
	public String getSenderAddress() {
		return senderAddress;
	}

	/**
	 * @param senderAddress the senderAddress to set
	 */
	public void setSenderAddress(String senderAddress) {
		this.senderAddress = senderAddress;
	}

	/**
	 * @return the senderName
	 */
	public String getSenderName() {
		return senderName;
	}

	/**
	 * @param senderName the senderName to set
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	/**
	 * @return the senderKana
	 */
	public String getSenderKana() {
		return senderKana;
	}

	/**
	 * @param senderKana the senderKana to set
	 */
	public void setSenderKana(String senderKana) {
		this.senderKana = senderKana;
	}

	/**
	 * @return the senderPhoneNumber1
	 */
	public String getSenderPhoneNumber1() {
		return senderPhoneNumber1;
	}

	/**
	 * @param senderPhoneNumber1 the senderPhoneNumber1 to set
	 */
	public void setSenderPhoneNumber1(String senderPhoneNumber1) {
		this.senderPhoneNumber1 = senderPhoneNumber1;
	}

	/**
	 * @return the couponTotalPrice
	 */
	public String getCouponTotalPrice() {
		return couponTotalPrice;
	}

	/**
	 * @param couponTotalPrice the couponTotalPrice to set
	 */
	public void setCouponTotalPrice(String couponTotalPrice) {
		this.couponTotalPrice = couponTotalPrice;
	}

	/**
	 * @return the itemList
	 */
	public List<Item> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public class Item {
		private String itemName;
		private String itemCode;
		private String itemPrice;
		private String unit;
		/**
		 * @return the itemName
		 */
		public String getItemName() {
			return itemName;
		}
		/**
		 * @param itemName the itemName to set
		 */
		public void setItemName(String itemName) {
			this.itemName = itemName;
		}
		/**
		 * @return the itemCode
		 */
		public String getItemCode() {
			return itemCode;
		}
		/**
		 * @param itemCode the itemCode to set
		 */
		public void setItemCode(String itemCode) {
			this.itemCode = itemCode;
		}
		/**
		 * @return the itemPrice
		 */
		public String getItemPrice() {
			return itemPrice;
		}
		/**
		 * @param itemPrice the itemPrice to set
		 */
		public void setItemPrice(String itemPrice) {
			this.itemPrice = itemPrice;
		}
		/**
		 * @return the unit
		 */
		public String getUnit() {
			return unit;
		}
		/**
		 * @param unit the unit to set
		 */
		public void setUnit(String unit) {
			this.unit = unit;
		}
		
	}
	
}
