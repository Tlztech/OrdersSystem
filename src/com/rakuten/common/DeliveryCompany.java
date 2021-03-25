package com.rakuten.common;

public enum DeliveryCompany {

	YAMATO("ヤマト運輸","v"),SAGAWA("佐川急便","s"),POST("郵便局","p");
	
	DeliveryCompany(String name, String tag) {
        this.name = name;  
        this.tag = tag;  
    }
	
	private String name;
    private String tag;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the tag
	 */
	public String getTag() {
		return tag;
	}
	/**
	 * @param tag the tag to set
	 */
	public void setTag(String tag) {
		this.tag = tag;
	}
    
	public static DeliveryCompany getDeliveryCompanyByName(String name) {
        for (DeliveryCompany deliveryCompany : values()) {
            if (deliveryCompany.getName().equals(name)) {
                return deliveryCompany;
            }
        }
        return null;
    }
}
