package com.rakuten.r1504.form;

import java.io.Serializable;

public class SeikyushoList implements Serializable {

	private static final long serialVersionUID = 1L;
	private String startDay =null;
	private String endDay =null;
	private String seikyusha  =null;
	private String hiseikyusha =null;
	private String seikyukingaku  =null;
	private String createDay  =null;
	private String seikyuid =null;
	public String getStartDay() {
		return startDay;
	}
	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}
	public String getEndDay() {
		return endDay;
	}
	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}
	public String getSeikyusha() {
		return seikyusha;
	}
	public void setSeikyusha(String seikyusha) {
		this.seikyusha = seikyusha;
	}
	public String getHiseikyusha() {
		return hiseikyusha;
	}
	public void setHiseikyusha(String hiseikyusha) {
		this.hiseikyusha = hiseikyusha;
	}
	public String getSeikyukingaku() {
		return seikyukingaku;
	}
	public void setSeikyukingaku(String seikyukingaku) {
		this.seikyukingaku = seikyukingaku;
	}
	public String getCreateDay() {
		return createDay;
	}
	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}
	public String getSeikyuid() {
		return seikyuid;
	}
	public void setSeikyuid(String seikyuid) {
		this.seikyuid = seikyuid;
	}
	
	
}
