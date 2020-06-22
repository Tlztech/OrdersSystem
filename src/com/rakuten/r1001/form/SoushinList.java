package com.rakuten.r1001.form;

import java.io.Serializable;

public class SoushinList implements Serializable {

	private static final long serialVersionUID = 1L;

	private String shoribango = null;
	private String soushintype = null;
	private String soushinzumiflg = null;
	private String setteibi = null;

	/**
	 * @return the soushintype
	 */
	public String getSoushintype() {
		return soushintype;
	}

	/**
	 * @param soushintype
	 *            the soushintype to set
	 */
	public void setSoushintype(String soushintype) {
		this.soushintype = soushintype;
	}

	/**
	 * @return the soushinzumiflg
	 */
	public String getSoushinzumiflg() {
		return soushinzumiflg;
	}

	/**
	 * @param soushinzumiflg
	 *            the soushinzumiflg to set
	 */
	public void setSoushinzumiflg(String soushinzumiflg) {
		this.soushinzumiflg = soushinzumiflg;
	}

	public String getSetteibi() {
		return setteibi;
	}

	public void setSetteibi(String setteibi) {
		this.setteibi = setteibi;
	}

	public String getShoribango() {
		return shoribango;
	}

	public void setShoribango(String shoribango) {
		this.shoribango = shoribango;
	}

}
