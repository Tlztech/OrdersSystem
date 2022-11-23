package com.rakuten.r1001.form;

import java.io.Serializable;
import java.util.List;

public class TuikaList implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tuikashoribango = null;
	private String tuikasts = null;
	private String tuikastsName = null;
	private String tuikahasonichiji = null;
	private String tuikahaisokaisha = null;
	private String tuikahaisouhoho = null;
	private String toiawasebango = null;
	private String tuikatantosya = null;
	private String seteihitsuke = null;
	private String tuikabiko = null;
	List<TuikaDetail> detailList = null;

	/**
	 * @return the seteihitsuke
	 */
	public String getSeteihitsuke() {
		return seteihitsuke;
	}
	/**
	 * @param seteihitsuke
	 *            the seteihitsuke to set
	 */
	public void setSeteihitsuke(String seteihitsuke) {
		this.seteihitsuke = seteihitsuke;
	}
	/**
	 * @return the tuikatantosya
	 */
	public String getTuikatantosya() {
		return tuikatantosya;
	}
	/**
	 * @param tuikatantosya
	 *            the tuikatantosya to set
	 */
	public void setTuikatantosya(String tuikatantosya) {
		this.tuikatantosya = tuikatantosya;
	}
	/**
	 * @return the tuikashoribango
	 */
	public String getTuikashoribango() {
		return tuikashoribango;
	}

	/**
	 * @param tuikashoribango
	 *            the tuikashoribango to set
	 */
	public void setTuikashoribango(String tuikashoribango) {
		this.tuikashoribango = tuikashoribango;
	}

	/**
	 * @return the tuikasts
	 */
	public String getTuikasts() {
		return tuikasts;
	}

	/**
	 * @param tuikasts
	 *            the tuikasts to set
	 */
	public void setTuikasts(String tuikasts) {
		this.tuikasts = tuikasts;
	}

	/**
	 * @return the tuikastsName
	 */
	public String getTuikastsName() {
		return tuikastsName;
	}

	/**
	 * @param tuikastsName
	 *            the tuikastsName to set
	 */
	public void setTuikastsName(String tuikastsName) {
		this.tuikastsName = tuikastsName;
	}

	/**
	 * @return the tuikahasonichiji
	 */
	public String getTuikahasonichiji() {
		return tuikahasonichiji;
	}

	/**
	 * @param tuikahasonichiji
	 *            the tuikahasonichiji to set
	 */
	public void setTuikahasonichiji(String tuikahasonichiji) {
		this.tuikahasonichiji = tuikahasonichiji;
	}

	/**
	 * @return the tuikahaisokaisha
	 */
	public String getTuikahaisokaisha() {
		return tuikahaisokaisha;
	}

	/**
	 * @param tuikahaisokaisha
	 *            the tuikahaisokaisha to set
	 */
	public void setTuikahaisokaisha(String tuikahaisokaisha) {
		this.tuikahaisokaisha = tuikahaisokaisha;
	}

	/**
	 * @return the tuikahaisouhoho
	 */
	public String getTuikahaisouhoho() {
		return tuikahaisouhoho;
	}

	/**
	 * @param tuikahaisouhoho
	 *            the tuikahaisouhoho to set
	 */
	public void setTuikahaisouhoho(String tuikahaisouhoho) {
		this.tuikahaisouhoho = tuikahaisouhoho;
	}

	/**
	 * @return the toiawasebango
	 */
	public String getToiawasebango() {
		return toiawasebango;
	}

	/**
	 * @param toiawasebango
	 *            the toiawasebango to set
	 */
	public void setToiawasebango(String toiawasebango) {
		this.toiawasebango = toiawasebango;
	}

	/**
	 * @return the detailList
	 */
	public List<TuikaDetail> getDetailList() {
		return detailList;
	}

	/**
	 * @param detailList
	 *            the detailList to set
	 */
	public void setDetailList(List<TuikaDetail> detailList) {
		this.detailList = detailList;
	}

	public String getTuikabiko() {
		return tuikabiko;
	}

	public void setTuikabiko(String tuikabiko) {
		this.tuikabiko = tuikabiko;
	}

}
