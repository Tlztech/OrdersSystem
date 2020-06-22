package com.rakuten.r1501.form;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class F150101 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String totalBalance;
	private String accountCount;
	private String totalUndidCount;
	private List<Account> accountList = null;
	private File csvFile = null;

	public String getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}

	public String getAccountCount() {
		return accountCount;
	}

	public void setAccountCount(String accountCount) {
		this.accountCount = accountCount;
	}

	public String getTotalUndidCount() {
		return totalUndidCount;
	}

	public void setTotalUndidCount(String totalUndidCount) {
		this.totalUndidCount = totalUndidCount;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	public File getCsvFile() {
		return csvFile;
	}

	public void setCsvFile(File csvFile) {
		this.csvFile = csvFile;
	}
	
}
