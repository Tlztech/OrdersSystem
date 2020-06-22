package com.rakuten.common.bean;

import java.io.Serializable;
import java.util.List;

public class ShohinBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private ShohinInfoBean shohinInfoBean = null;
	private List<ShohinkategoriBean> shohinkategoriBeanList = null;
	private List<ShohinsentakushiBean> shohinsentakushiBeanList = null;

	/**
	 * @return the shohinInfoBean
	 */
	public ShohinInfoBean getShohinInfoBean() {
		return shohinInfoBean;
	}

	/**
	 * @param shohinInfoBean
	 *            the shohinInfoBean to set
	 */
	public void setShohinInfoBean(ShohinInfoBean shohinInfoBean) {
		this.shohinInfoBean = shohinInfoBean;
	}

	/**
	 * @return the shohinkategoriBeanList
	 */
	public List<ShohinkategoriBean> getShohinkategoriBeanList() {
		return shohinkategoriBeanList;
	}

	/**
	 * @param shohinkategoriBeanList
	 *            the shohinkategoriBeanList to set
	 */
	public void setShohinkategoriBeanList(
			List<ShohinkategoriBean> shohinkategoriBeanList) {
		this.shohinkategoriBeanList = shohinkategoriBeanList;
	}

	/**
	 * @return the shohinsentakushiBeanList
	 */
	public List<ShohinsentakushiBean> getShohinsentakushiBeanList() {
		return shohinsentakushiBeanList;
	}

	/**
	 * @param shohinsentakushiBeanList
	 *            the shohinsentakushiBeanList to set
	 */
	public void setShohinsentakushiBeanList(
			List<ShohinsentakushiBean> shohinsentakushiBeanList) {
		this.shohinsentakushiBeanList = shohinsentakushiBeanList;
	}

}
