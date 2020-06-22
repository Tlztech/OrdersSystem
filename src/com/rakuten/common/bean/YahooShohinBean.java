package com.rakuten.common.bean;

import java.io.Serializable;
import java.util.List;

public class YahooShohinBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<YahooShohinCsvBean> yahooShohinCsvBeanList = null;
	private List<YahooShohinQuatityCsvBean> yahooShohinQuatityCsvBeanList = null;
	/**
	 * @return the yahooShohinCsvBeanList
	 */
	public List<YahooShohinCsvBean> getYahooShohinCsvBeanList() {
		return yahooShohinCsvBeanList;
	}
	/**
	 * @param yahooShohinCsvBeanList the yahooShohinCsvBeanList to set
	 */
	public void setYahooShohinCsvBeanList(
			List<YahooShohinCsvBean> yahooShohinCsvBeanList) {
		this.yahooShohinCsvBeanList = yahooShohinCsvBeanList;
	}
	/**
	 * @return the yahooShohinQuatityCsvBeanList
	 */
	public List<YahooShohinQuatityCsvBean> getYahooShohinQuatityCsvBeanList() {
		return yahooShohinQuatityCsvBeanList;
	}
	/**
	 * @param yahooShohinQuatityCsvBeanList the yahooShohinQuatityCsvBeanList to set
	 */
	public void setYahooShohinQuatityCsvBeanList(
			List<YahooShohinQuatityCsvBean> yahooShohinQuatityCsvBeanList) {
		this.yahooShohinQuatityCsvBeanList = yahooShohinQuatityCsvBeanList;
	}

}
