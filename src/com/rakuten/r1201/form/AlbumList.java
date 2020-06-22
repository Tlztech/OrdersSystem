package com.rakuten.r1201.form;

import java.io.Serializable;

public class AlbumList implements Serializable {

	private static final long serialVersionUID = 1L;
	private String shumokuId = null;
	private String name = null;
	private String kosu = null;
	private String status = null;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the kosu
	 */
	public String getKosu() {
		return kosu;
	}

	/**
	 * @param kosu
	 *            the kosu to set
	 */
	public void setKosu(String kosu) {
		this.kosu = kosu;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	public String getShumokuId() {
		return shumokuId;
	}

	public void setShumokuId(String shumokuId) {
		this.shumokuId = shumokuId;
	}

}
