package com.rakuten.r1701.form;

import java.io.Serializable;
import java.util.List;

public class F170102 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String chinese = null;
	private String japanese = null;
	List<DataList> dataList = null;

	/**
	 * @return the chinese
	 */
	public String getChinese() {
		return chinese;
	}

	/**
	 * @param chinese
	 *            the chinese to set
	 */
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

	/**
	 * @return the japanese
	 */
	public String getJapanese() {
		return japanese;
	}

	/**
	 * @param japanese
	 *            the japanese to set
	 */
	public void setJapanese(String japanese) {
		this.japanese = japanese;
	}

	/**
	 * @return the dataList
	 */
	public List<DataList> getDataList() {
		return dataList;
	}

	/**
	 * @param dataList
	 *            the dataList to set
	 */
	public void setDataList(List<DataList> dataList) {
		this.dataList = dataList;
	}



}
