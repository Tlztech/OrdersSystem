package com.rakuten.r1002.form;

import java.io.Serializable;
import java.util.List;

public class F100201 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String juchubango = null;
	private String juchubango_hid = null;
	private List<JuchujoutaiList> juchujoutaiList = null;
	private List<JuchushousaiiList> juchushousaiiList = null;

	public String getJuchubango() {
		return juchubango;
	}

	public void setJuchubango(String juchubango) {
		this.juchubango = juchubango;
	}

	public List<JuchujoutaiList> getJuchujoutaiList() {
		return juchujoutaiList;
	}

	public void setJuchujoutaiList(List<JuchujoutaiList> juchujoutaiList) {
		this.juchujoutaiList = juchujoutaiList;
	}

	public List<JuchushousaiiList> getJuchushousaiiList() {
		return juchushousaiiList;
	}

	public void setJuchushousaiiList(List<JuchushousaiiList> juchushousaiiList) {
		this.juchushousaiiList = juchushousaiiList;
	}

	public String getJuchubango_hid() {
		return juchubango_hid;
	}

	public void setJuchubango_hid(String juchubango_hid) {
		this.juchubango_hid = juchubango_hid;
	}

}
