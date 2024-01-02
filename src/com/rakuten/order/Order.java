package com.rakuten.order;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private String orderNumber;
	private String orderDatetime;
	private SettlementModel settlementModel;
	private OrdererModel ordererModel;
	private DeliveryModel deliveryModel;
	private List<PackageModel> packageModelList = new ArrayList<PackageModel>();
	private PointModel pointModel;
	private int asurakuFlag;
	private String remarks;
	private String mailPlugSentence;
	private int goodsPrice;
	private int goodsTax;
	private int postagePrice;
	private int deliveryPrice;
	private int requestPrice;
	private int couponAllTotalPrice;
	
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getOrderDatetime() {
		return orderDatetime;
	}

	public void setOrderDatetime(String orderDatetime) {
		this.orderDatetime = orderDatetime;
	}

	public SettlementModel getSettlementModel() {
		return settlementModel;
	}

	public void setSettlementModel(SettlementModel settlementModel) {
		this.settlementModel = settlementModel;
	}

	public OrdererModel getOrdererModel() {
		return ordererModel;
	}

	public void setOrdererModel(OrdererModel ordererModel) {
		this.ordererModel = ordererModel;
	}

	public DeliveryModel getDeliveryModel() {
		return deliveryModel;
	}

	public void setDeliveryModel(DeliveryModel deliveryModel) {
		this.deliveryModel = deliveryModel;
	}

	public List<PackageModel> getPackageModelList() {
		return packageModelList;
	}

	public void setPackageModelList(List<PackageModel> packageModelList) {
		this.packageModelList = packageModelList;
	}

	public PointModel getPointModel() {
		return pointModel;
	}

	public void setPointModel(PointModel pointModel) {
		this.pointModel = pointModel;
	}

	public int getAsurakuFlag() {
		return asurakuFlag;
	}

	public void setAsurakuFlag(int asurakuFlag) {
		this.asurakuFlag = asurakuFlag;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMailPlugSentence() {
		return mailPlugSentence;
	}

	public void setMailPlugSentence(String mailPlugSentence) {
		this.mailPlugSentence = mailPlugSentence;
	}

	public int getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(int goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public int getGoodsTax() {
		return goodsTax;
	}

	public void setGoodsTax(int goodsTax) {
		this.goodsTax = goodsTax;
	}

	public int getPostagePrice() {
		return postagePrice;
	}

	public void setPostagePrice(int postagePrice) {
		this.postagePrice = postagePrice;
	}

	public int getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(int deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public int getRequestPrice() {
		return requestPrice;
	}

	public void setRequestPrice(int requestPrice) {
		this.requestPrice = requestPrice;
	}

	public int getCouponAllTotalPrice() {
		return couponAllTotalPrice;
	}

	public void setCouponAllTotalPrice(int couponAllTotalPrice) {
		this.couponAllTotalPrice = couponAllTotalPrice;
	}

	public class SettlementModel {
		private String settlementMethod;

		public String getSettlementMethod() {
			return settlementMethod;
		}

		public void setSettlementMethod(String settlementMethod) {
			this.settlementMethod = settlementMethod;
		}
		
	}
	
	public class OrdererModel {
		private String zipCode1;
		private String zipCode2;
		private String prefecture;
		private String city;
		private String subAddress;
		private String familyName;
		private String firstName;
		private String familyNameKana;
		private String firstNameKana;
		private String phoneNumber1;
		private String phoneNumber2;
		private String phoneNumber3;
		private String emailAddress;
		private int birthYear;
		private int birthMonth;
		private int birthDay;

		public String getZipCode1() {
			return zipCode1;
		}

		public void setZipCode1(String zipCode1) {
			this.zipCode1 = zipCode1;
		}

		public String getZipCode2() {
			return zipCode2;
		}

		public void setZipCode2(String zipCode2) {
			this.zipCode2 = zipCode2;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getSubAddress() {
			return subAddress;
		}

		public void setSubAddress(String subAddress) {
			this.subAddress = subAddress;
		}

		public String getPrefecture() {
			return prefecture;
		}

		public void setPrefecture(String prefecture) {
			this.prefecture = prefecture;
		}

		public String getFamilyName() {
			return familyName;
		}

		public void setFamilyName(String familyName) {
			this.familyName = familyName;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getFamilyNameKana() {
			return familyNameKana;
		}

		public void setFamilyNameKana(String familyNameKana) {
			this.familyNameKana = familyNameKana;
		}

		public String getFirstNameKana() {
			return firstNameKana;
		}

		public void setFirstNameKana(String firstNameKana) {
			this.firstNameKana = firstNameKana;
		}

		public String getPhoneNumber1() {
			return phoneNumber1;
		}

		public void setPhoneNumber1(String phoneNumber1) {
			this.phoneNumber1 = phoneNumber1;
		}

		public String getPhoneNumber2() {
			return phoneNumber2;
		}

		public void setPhoneNumber2(String phoneNumber2) {
			this.phoneNumber2 = phoneNumber2;
		}

		public String getPhoneNumber3() {
			return phoneNumber3;
		}

		public void setPhoneNumber3(String phoneNumber3) {
			this.phoneNumber3 = phoneNumber3;
		}

		public String getEmailAddress() {
			return emailAddress;
		}

		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}

		public int getBirthYear() {
			return birthYear;
		}

		public void setBirthYear(int birthYear) {
			this.birthYear = birthYear;
		}

		public int getBirthMonth() {
			return birthMonth;
		}

		public void setBirthMonth(int birthMonth) {
			this.birthMonth = birthMonth;
		}

		public int getBirthDay() {
			return birthDay;
		}

		public void setBirthDay(int birthDay) {
			this.birthDay = birthDay;
		}
		
	}
	
	public class DeliveryModel {
		private String deliveryName;

		public String getDeliveryName() {
			return deliveryName;
		}

		public void setDeliveryName(String deliveryName) {
			this.deliveryName = deliveryName;
		}
		
	}
	
	public class PackageModel {
		private long basketId;
		private SenderModel senderModel;
		private List<ItemModel> itemModelList = new ArrayList<ItemModel>();
		private int postagePrice;
		
		public long getBasketId() {
			return basketId;
		}

		public void setBasketId(long basketId) {
			this.basketId = basketId;
		}

		public SenderModel getSenderModel() {
			return senderModel;
		}

		public void setSenderModel(SenderModel senderModel) {
			this.senderModel = senderModel;
		}

		public List<ItemModel> getItemModelList() {
			return itemModelList;
		}

		public void setItemModelList(List<ItemModel> itemModelList) {
			this.itemModelList = itemModelList;
		}

		public int getPostagePrice() {
			return postagePrice;
		}

		public void setPostagePrice(int postagePrice) {
			this.postagePrice = postagePrice;
		}

		public class SenderModel {
			private String zipCode1;
			private String zipCode2;
			private String prefecture;
			private String city;
			private String subAddress;
			private String familyName;
			private String firstName;
			private String familyNameKana;
			private String firstNameKana;
			private String phoneNumber1;
			private String phoneNumber2;
			private String phoneNumber3;
			
			public String getZipCode1() {
				return zipCode1;
			}
			public void setZipCode1(String zipCode1) {
				this.zipCode1 = zipCode1;
			}
			public String getZipCode2() {
				return zipCode2;
			}
			public void setZipCode2(String zipCode2) {
				this.zipCode2 = zipCode2;
			}
			public String getPrefecture() {
				return prefecture;
			}
			public void setPrefecture(String prefecture) {
				this.prefecture = prefecture;
			}
			public String getCity() {
				return city;
			}
			public void setCity(String city) {
				this.city = city;
			}
			public String getSubAddress() {
				return subAddress;
			}
			public void setSubAddress(String subAddress) {
				this.subAddress = subAddress;
			}
			public String getFamilyName() {
				return familyName;
			}
			public void setFamilyName(String familyName) {
				this.familyName = familyName;
			}
			public String getFirstName() {
				return firstName;
			}
			public void setFirstName(String firstName) {
				this.firstName = firstName;
			}
			public String getFamilyNameKana() {
				return familyNameKana;
			}
			public void setFamilyNameKana(String familyNameKana) {
				this.familyNameKana = familyNameKana;
			}
			public String getFirstNameKana() {
				return firstNameKana;
			}
			public void setFirstNameKana(String firstNameKana) {
				this.firstNameKana = firstNameKana;
			}
			public String getPhoneNumber1() {
				return phoneNumber1;
			}
			public void setPhoneNumber1(String phoneNumber1) {
				this.phoneNumber1 = phoneNumber1;
			}
			public String getPhoneNumber2() {
				return phoneNumber2;
			}
			public void setPhoneNumber2(String phoneNumber2) {
				this.phoneNumber2 = phoneNumber2;
			}
			public String getPhoneNumber3() {
				return phoneNumber3;
			}
			public void setPhoneNumber3(String phoneNumber3) {
				this.phoneNumber3 = phoneNumber3;
			}
			
		}

		public class ItemModel{
			private String itemName;
			private String itemNumber;
			private String manageNumber;
			private int price;
			private int units;
			private int includePostageFlag;
			private int includeTaxFlag;
			private int includeCashOnDeliveryPostageFlag;
			private String selectedChoice;
			private int pointRate;
			private String delvdateInfo;

			public String getItemName() {
				return itemName;
			}

			public void setItemName(String itemName) {
				this.itemName = itemName;
			}

			public String getItemNumber() {
				return itemNumber;
			}

			public void setItemNumber(String itemNumber) {
				this.itemNumber = itemNumber;
			}

			public String getManageNumber() {
				return manageNumber;
			}

			public void setManageNumber(String manageNumber) {
				this.manageNumber = manageNumber;
			}

			public int getPrice() {
				return price;
			}

			public void setPrice(int price) {
				this.price = price;
			}

			public int getUnits() {
				return units;
			}

			public void setUnits(int units) {
				this.units = units;
			}

			public int getIncludePostageFlag() {
				return includePostageFlag;
			}

			public void setIncludePostageFlag(int includePostageFlag) {
				this.includePostageFlag = includePostageFlag;
			}

			public int getIncludeTaxFlag() {
				return includeTaxFlag;
			}

			public void setIncludeTaxFlag(int includeTaxFlag) {
				this.includeTaxFlag = includeTaxFlag;
			}

			public int getIncludeCashOnDeliveryPostageFlag() {
				return includeCashOnDeliveryPostageFlag;
			}

			public void setIncludeCashOnDeliveryPostageFlag(int includeCashOnDeliveryPostageFlag) {
				this.includeCashOnDeliveryPostageFlag = includeCashOnDeliveryPostageFlag;
			}

			public String getSelectedChoice() {
				return selectedChoice;
			}

			public void setSelectedChoice(String selectedChoice) {
				this.selectedChoice = selectedChoice;
			}

			public int getPointRate() {
				return pointRate;
			}

			public void setPointRate(int pointRate) {
				this.pointRate = pointRate;
			}

			public String getDelvdateInfo() {
				return delvdateInfo;
			}

			public void setDelvdateInfo(String delvdateInfo) {
				this.delvdateInfo = delvdateInfo;
			}
			
		}
	}
	
	public class PointModel {
		private int usedPoint;

		public int getUsedPoint() {
			return usedPoint;
		}

		public void setUsedPoint(int usedPoint) {
			this.usedPoint = usedPoint;
		}
		
	}
}
