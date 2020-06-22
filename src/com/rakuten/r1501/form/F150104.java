package com.rakuten.r1501.form;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class F150104 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String dayStart = null;
	private String dayEnd = null;
	private String kubun = null;
	private String biko = null;
	private String leibie = null;
	private String outStart = null;
	private String outEnd = null;
	private String inStart = null;
	private String intEnd = null;
	private String resultCount = null;
	private String account_id = null;

	private File alipayCsvFile = null;

	private List<AlipayMeisai> alipayMeisaiList = null;

	public String getDayStart() {
		return dayStart;
	}

	public void setDayStart(String dayStart) {
		this.dayStart = dayStart;
	}

	public String getDayEnd() {
		return dayEnd;
	}

	public void setDayEnd(String dayEnd) {
		this.dayEnd = dayEnd;
	}

	public String getKubun() {
		return kubun;
	}

	public void setKubun(String kubun) {
		this.kubun = kubun;
	}

	public String getBiko() {
		return biko;
	}

	public void setBiko(String biko) {
		this.biko = biko;
	}

	public String getLeibie() {
		return leibie;
	}

	public void setLeibie(String leibie) {
		this.leibie = leibie;
	}

	public String getOutStart() {
		return outStart;
	}

	public void setOutStart(String outStart) {
		this.outStart = outStart;
	}

	public String getOutEnd() {
		return outEnd;
	}

	public void setOutEnd(String outEnd) {
		this.outEnd = outEnd;
	}

	public String getInStart() {
		return inStart;
	}

	public void setInStart(String inStart) {
		this.inStart = inStart;
	}

	public String getIntEnd() {
		return intEnd;
	}

	public void setIntEnd(String intEnd) {
		this.intEnd = intEnd;
	}

	public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public File getAlipayCsvFile() {
		return alipayCsvFile;
	}

	public void setAlipayCsvFile(File alipayCsvFile) {
		this.alipayCsvFile = alipayCsvFile;
	}

	public List<AlipayMeisai> getAlipayMeisaiList() {
		return alipayMeisaiList;
	}

	public void setAlipayMeisaiList(List<AlipayMeisai> alipayMeisaiList) {
		this.alipayMeisaiList = alipayMeisaiList;
	}

	public String getAccount_id() {
		return account_id;
	}

	public void setAccount_id(String account_id) {
		this.account_id = account_id;
	}

}
