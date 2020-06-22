package com.rakuten.r1001.form;

import java.io.Serializable;
import java.util.List;

public class HenpinSeteiZumiList implements Serializable {
	private static final long serialVersionUID = 1L;
	private boolean checkedFlg = false;
	private String henpinshoribango = null;
	private String henpinstsName = null;
	private String henpinsts = null;
	private String henpinhasonichiji = null;
	private String henpinhaisokaisha = null;
	private String henpinhaisouhoho = null;
	private String toiawasebango = null;
	private String henkinstsName = null;
	private String henkinsts = null;
	private String henkinkingaku = null;
	private String henpinbiko = null;

	private List<HenpinDetail> detailList = null;

	/**
	 * @return the henpinshoribango
	 */
	public String getHenpinshoribango() {
		return henpinshoribango;
	}

	/**
	 * @param henpinshoribango
	 *            the henpinshoribango to set
	 */
	public void setHenpinshoribango(String henpinshoribango) {
		this.henpinshoribango = henpinshoribango;
	}

	/**
	 * @return the henpinstsName
	 */
	public String getHenpinstsName() {
		return henpinstsName;
	}

	/**
	 * @param henpinstsName
	 *            the henpinstsName to set
	 */
	public void setHenpinstsName(String henpinstsName) {
		this.henpinstsName = henpinstsName;
	}

	/**
	 * @return the henpinhasonichiji
	 */
	public String getHenpinhasonichiji() {
		return henpinhasonichiji;
	}

	/**
	 * @param henpinhasonichiji
	 *            the henpinhasonichiji to set
	 */
	public void setHenpinhasonichiji(String henpinhasonichiji) {
		this.henpinhasonichiji = henpinhasonichiji;
	}

	/**
	 * @return the henpinhaisokaisha
	 */
	public String getHenpinhaisokaisha() {
		return henpinhaisokaisha;
	}

	/**
	 * @param henpinhaisokaisha
	 *            the henpinhaisokaisha to set
	 */
	public void setHenpinhaisokaisha(String henpinhaisokaisha) {
		this.henpinhaisokaisha = henpinhaisokaisha;
	}

	/**
	 * @return the henpinhaisouhoho
	 */
	public String getHenpinhaisouhoho() {
		return henpinhaisouhoho;
	}

	/**
	 * @param henpinhaisouhoho
	 *            the henpinhaisouhoho to set
	 */
	public void setHenpinhaisouhoho(String henpinhaisouhoho) {
		this.henpinhaisouhoho = henpinhaisouhoho;
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
	 * @return the henkinstsName
	 */
	public String getHenkinstsName() {
		return henkinstsName;
	}

	/**
	 * @param henkinstsName
	 *            the henkinstsName to set
	 */
	public void setHenkinstsName(String henkinstsName) {
		this.henkinstsName = henkinstsName;
	}

	/**
	 * @return the henkinsts
	 */
	public String getHenkinsts() {
		return henkinsts;
	}

	/**
	 * @param henkinsts
	 *            the henkinsts to set
	 */
	public void setHenkinsts(String henkinsts) {
		this.henkinsts = henkinsts;
	}

	/**
	 * @return the henkinkingaku
	 */
	public String getHenkinkingaku() {
		return henkinkingaku;
	}

	/**
	 * @param henkinkingaku
	 *            the henkinkingaku to set
	 */
	public void setHenkinkingaku(String henkinkingaku) {
		this.henkinkingaku = henkinkingaku;
	}

	/**
	 * @return the henpinbiko
	 */
	public String getHenpinbiko() {
		return henpinbiko;
	}

	/**
	 * @param henpinbiko
	 *            the henpinbiko to set
	 */
	public void setHenpinbiko(String henpinbiko) {
		this.henpinbiko = henpinbiko;
	}

	/**
	 * @return the detailList
	 */
	public List<HenpinDetail> getDetailList() {
		return detailList;
	}

	/**
	 * @param detailList
	 *            the detailList to set
	 */
	public void setDetailList(List<HenpinDetail> detailList) {
		this.detailList = detailList;
	}

	public String getHenpinsts() {
		return henpinsts;
	}

	public void setHenpinsts(String henpinsts) {
		this.henpinsts = henpinsts;
	}

	public boolean isCheckedFlg() {
		return checkedFlg;
	}

	public void setCheckedFlg(boolean checkedFlg) {
		this.checkedFlg = checkedFlg;
	}

}
