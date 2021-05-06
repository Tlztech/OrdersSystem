package com.rakuten.r1901.form;

import java.io.Serializable;

public class F190101 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String newpassword = null;
	private String newpasswordconfirmation = null;
	/**
	 * @return the newpassword
	 */
	public String getNewpassword() {
		return newpassword;
	}
	/**
	 * @param newpassword the newpassword to set
	 */
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	/**
	 * @return the newpasswordconfirmation
	 */
	public String getNewpasswordconfirmation() {
		return newpasswordconfirmation;
	}
	/**
	 * @param newpasswordconfirmation the newpasswordconfirmation to set
	 */
	public void setNewpasswordconfirmation(String newpasswordconfirmation) {
		this.newpasswordconfirmation = newpasswordconfirmation;
	}
	
}
