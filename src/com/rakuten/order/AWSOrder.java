package com.rakuten.order;

import java.util.List;

public class AWSOrder {

	private String orderId;
	private Address address;
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
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
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

	public class Address {
		private String shipPrefecture;
		private String shipPostalCode;
		
		/**
		 * @return the shipPrefecture
		 */
		public String getShipPrefecture() {
			return shipPrefecture;
		}
		/**
		 * @param shipPrefecture the shipPrefecture to set
		 */
		public void setShipPrefecture(String shipPrefecture) {
			this.shipPrefecture = shipPrefecture;
		}
		/**
		 * @return the shipPostalCode
		 */
		public String getShipPostalCode() {
			return shipPostalCode;
		}
		/**
		 * @param shipPostalCode the shipPostalCode to set
		 */
		public void setShipPostalCode(String shipPostalCode) {
			this.shipPostalCode = shipPostalCode;
		}
	}
	
	public class Item {
		private String price;

		/**
		 * @return the price
		 */
		public String getPrice() {
			return price;
		}

		/**
		 * @param price the price to set
		 */
		public void setPrice(String price) {
			this.price = price;
		}
		
	}
}
