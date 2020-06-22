package com.rakuten.r0801.form;

import java.io.Serializable;
import java.util.List;

public class F080201 implements Serializable {

	private static final long serialVersionUID = 1L;

	private String mekabango = null;
	private String shouhinmei = null;
	private String shumokumei = null;
	private String taobaoStatus = null;
	private String shouhinStatus = null;
	private String burando = null;
	private String resultCount = null;
	private String shumokubango = null;
	List<ShohinList> shohinList = null;

	public String getMekabango() {
		return mekabango;
	}

	public void setMekabango(String mekabango) {
		this.mekabango = mekabango;
	}

	public String getShouhinmei() {
		return shouhinmei;
	}

	public void setShouhinmei(String shouhinmei) {
		this.shouhinmei = shouhinmei;
	}

	public String getTaobaoStatus() {
		return taobaoStatus;
	}

	public void setTaobaoStatus(String taobaoStatus) {
		this.taobaoStatus = taobaoStatus;
	}

	public String getShouhinStatus() {
		return shouhinStatus;
	}

	public void setShouhinStatus(String shouhinStatus) {
		this.shouhinStatus = shouhinStatus;
	}

	public String getBurando() {
		return burando;
	}

	public void setBurando(String burando) {
		this.burando = burando;
	}

	public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public List<ShohinList> getShohinList() {
		return shohinList;
	}

	public void setShohinList(List<ShohinList> shohinList) {
		this.shohinList = shohinList;
	}

	public String getShumokumei() {
		return shumokumei;
	}

	public void setShumokumei(String shumokumei) {
		this.shumokumei = shumokumei;
	}

	public String getShumokubango() {
		return shumokubango;
	}

	public void setShumokubango(String shumokubango) {
		this.shumokubango = shumokubango;
	}

}
