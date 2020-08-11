package com.rakuten.order;

import java.util.List;

public class YahooOrder {

	private String orderId;
	private String orderTime;
	private Pay pay;
	private Detail detail;
	private String buyerComments;
	private Ship ship;
	private List<Item> itemList;
	private int totalCouponDiscount;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public Pay getPay() {
		return pay;
	}

	public void setPay(Pay pay) {
		this.pay = pay;
	}

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}

	public String getBuyerComments() {
		return buyerComments;
	}

	public void setBuyerComments(String buyerComments) {
		this.buyerComments = buyerComments;
	}

	public Ship getShip() {
		return ship;
	}

	public void setShip(Ship ship) {
		this.ship = ship;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public int getTotalCouponDiscount() {
		return totalCouponDiscount;
	}

	public void setTotalCouponDiscount(int totalCouponDiscount) {
		this.totalCouponDiscount = totalCouponDiscount;
	}

	public class Pay {
		private String payMethodName;
		private String billLastName;
		private String billFirstName;
		private String billLastNameKana;
		private String billFirstNameKana;
		private String billMailAddress;
		private String billZipCode;
		private String billPrefecture;
		private String billCity;
		private String billAddress1;
		private String billAddress2;
		private String billPhoneNumber;
		public String getPayMethodName() {
			return payMethodName;
		}
		public void setPayMethodName(String payMethodName) {
			this.payMethodName = payMethodName;
		}
		public String getBillLastName() {
			return billLastName;
		}
		public void setBillLastName(String billLastName) {
			this.billLastName = billLastName;
		}
		public String getBillFirstName() {
			return billFirstName;
		}
		public void setBillFirstName(String billFirstName) {
			this.billFirstName = billFirstName;
		}
		public String getBillLastNameKana() {
			return billLastNameKana;
		}
		public void setBillLastNameKana(String billLastNameKana) {
			this.billLastNameKana = billLastNameKana;
		}
		public String getBillFirstNameKana() {
			return billFirstNameKana;
		}
		public void setBillFirstNameKana(String billFirstNameKana) {
			this.billFirstNameKana = billFirstNameKana;
		}
		public String getBillMailAddress() {
			return billMailAddress;
		}
		public void setBillMailAddress(String billMailAddress) {
			this.billMailAddress = billMailAddress;
		}
		public String getBillZipCode() {
			return billZipCode;
		}
		public void setBillZipCode(String billZipCode) {
			this.billZipCode = billZipCode;
		}
		public String getBillPrefecture() {
			return billPrefecture;
		}
		public void setBillPrefecture(String billPrefecture) {
			this.billPrefecture = billPrefecture;
		}
		public String getBillCity() {
			return billCity;
		}
		public void setBillCity(String billCity) {
			this.billCity = billCity;
		}
		public String getBillAddress1() {
			return billAddress1;
		}
		public void setBillAddress1(String billAddress1) {
			this.billAddress1 = billAddress1;
		}
		public String getBillAddress2() {
			return billAddress2;
		}
		public void setBillAddress2(String billAddress2) {
			this.billAddress2 = billAddress2;
		}
		public String getBillPhoneNumber() {
			return billPhoneNumber;
		}
		public void setBillPhoneNumber(String billPhoneNumber) {
			this.billPhoneNumber = billPhoneNumber;
		}
		
	}
	
	public class Detail {
		private int usePoint;
		private int settleAmount;
		private int shipCharge;
		private int payCharge;
		private int totalPrice;
		public int getUsePoint() {
			return usePoint;
		}
		public void setUsePoint(int usePoint) {
			this.usePoint = usePoint;
		}
		public int getSettleAmount() {
			return settleAmount;
		}
		public void setSettleAmount(int settleAmount) {
			this.settleAmount = settleAmount;
		}
		public int getShipCharge() {
			return shipCharge;
		}
		public void setShipCharge(int shipCharge) {
			this.shipCharge = shipCharge;
		}
		public int getPayCharge() {
			return payCharge;
		}
		public void setPayCharge(int payCharge) {
			this.payCharge = payCharge;
		}
		public int getTotalPrice() {
			return totalPrice;
		}
		public void setTotalPrice(int totalPrice) {
			this.totalPrice = totalPrice;
		}
		
	}
	
	public class Ship {
		private String shipLastName;
		private String shipFirstName;
		private String shipLastNameKana;
		private String shipFirstNameKana;
		private String shipMethodName;
		private String shipZipCode;
		private String shipPhoneNumber;
		private String shipPrefecture;
		private String shipCity;
		private String shipAddress1;
		private String shipAddress2;
		public String getShipLastName() {
			return shipLastName;
		}
		public void setShipLastName(String shipLastName) {
			this.shipLastName = shipLastName;
		}
		public String getShipFirstName() {
			return shipFirstName;
		}
		public void setShipFirstName(String shipFirstName) {
			this.shipFirstName = shipFirstName;
		}
		public String getShipLastNameKana() {
			return shipLastNameKana;
		}
		public void setShipLastNameKana(String shipLastNameKana) {
			this.shipLastNameKana = shipLastNameKana;
		}
		public String getShipFirstNameKana() {
			return shipFirstNameKana;
		}
		public void setShipFirstNameKana(String shipFirstNameKana) {
			this.shipFirstNameKana = shipFirstNameKana;
		}
		public String getShipMethodName() {
			return shipMethodName;
		}
		public void setShipMethodName(String shipMethodName) {
			this.shipMethodName = shipMethodName;
		}
		public String getShipZipCode() {
			return shipZipCode;
		}
		public void setShipZipCode(String shipZipCode) {
			this.shipZipCode = shipZipCode;
		}
		public String getShipPhoneNumber() {
			return shipPhoneNumber;
		}
		public void setShipPhoneNumber(String shipPhoneNumber) {
			this.shipPhoneNumber = shipPhoneNumber;
		}
		public String getShipPrefecture() {
			return shipPrefecture;
		}
		public void setShipPrefecture(String shipPrefecture) {
			this.shipPrefecture = shipPrefecture;
		}
		public String getShipCity() {
			return shipCity;
		}
		public void setShipCity(String shipCity) {
			this.shipCity = shipCity;
		}
		public String getShipAddress1() {
			return shipAddress1;
		}
		public void setShipAddress1(String shipAddress1) {
			this.shipAddress1 = shipAddress1;
		}
		public String getShipAddress2() {
			return shipAddress2;
		}
		public void setShipAddress2(String shipAddress2) {
			this.shipAddress2 = shipAddress2;
		}
		
	}
	
	public class Item {
		private int itemTaxRatio;
		private String title;
		private String itemId;
		private int unitPrice;
		private int quantity;
		public int getItemTaxRatio() {
			return itemTaxRatio;
		}
		public void setItemTaxRatio(int itemTaxRatio) {
			this.itemTaxRatio = itemTaxRatio;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getItemId() {
			return itemId;
		}
		public void setItemId(String itemId) {
			this.itemId = itemId;
		}
		public int getUnitPrice() {
			return unitPrice;
		}
		public void setUnitPrice(int unitPrice) {
			this.unitPrice = unitPrice;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
	}
}
