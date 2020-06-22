package com.rakuten.r1001.form;

import java.io.Serializable;

public class DokonList implements Serializable {

	private static final long serialVersionUID = 1L;
	private String juchubango = null;
	private String type = null;

	/**
	 * @return the juchubango
	 */
	public String getJuchubango() {
		return juchubango;
	}

	/**
	 * @param juchubango
	 *            the juchubango to set
	 */
	public void setJuchubango(String juchubango) {
		this.juchubango = juchubango;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
