package com.rakuten.common.bean;

import java.io.Serializable;

public class YahooShohinQuatityCsvBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String code = null;
	private String sub_code = null;
	private String quantity = null;
	private String mode = null;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the sub_code
	 */
	public String getSub_code() {
		return sub_code;
	}

	/**
	 * @param sub_code
	 *            the sub_code to set
	 */
	public void setSub_code(String sub_code) {
		this.sub_code = sub_code;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

}
