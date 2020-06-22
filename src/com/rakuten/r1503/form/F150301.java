package com.rakuten.r1503.form;

import java.io.Serializable;
import java.util.List;

public class F150301 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String accountName;
	private String cardNo;
	private String biko;
	private String type;
	private String area;
	private String fenleiName;
	private String area2;
	private String type2;
	private String biko2;
	private List<Account> accountList = null;
	private List<Fenlei> fenleiList = null;

	public String getFenleiName() {
		return fenleiName;
	}

	public void setFenleiName(String fenleiName) {
		this.fenleiName = fenleiName;
	}

	public String getArea2() {
		return area2;
	}

	public void setArea2(String area2) {
		this.area2 = area2;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getBiko2() {
		return biko2;
	}

	public void setBiko2(String biko2) {
		this.biko2 = biko2;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBiko() {
		return biko;
	}

	public void setBiko(String biko) {
		this.biko = biko;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public List<Fenlei> getFenleiList() {
		return fenleiList;
	}

	public void setFenleiList(List<Fenlei> fenleiList) {
		this.fenleiList = fenleiList;
	}

}
