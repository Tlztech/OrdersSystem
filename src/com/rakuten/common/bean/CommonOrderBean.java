package com.rakuten.common.bean;

import java.io.Serializable;
import java.util.List;

public class CommonOrderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 受注番号
	private String juchubango = null;
	// お支払い方法
	private String oshiharaihoho = null;
	// 至急フラグ
	private boolean shikyuFlg = false;
	// 至急設定日
	private String shikyusetteibi = "";
	// 返品フラグ
	private boolean henpinFlg = false;
	// 返品設定日
	private String henpinsetteibi = "";
	// 交換フラグ
	private boolean koukanFlg = false;
	// 交換設定日
	private String koukansetteibi = "";
	// 分納フラグ
	private boolean bunnouFlg = false;
	// 分納設定日
	private String bunnousetteibi = "";
	// 発送約束日
	private String hasoyakusokubi = "";
	// 優先順
	private long yusenjun = 999999999;
	// 注文日時
	private String chumonichiji = null;
	// 追加フラグ
	private boolean tuikaFlg = false;
	// 追加設定日
	private String tuikasetteibi = "";
	// 詳細BEAN
	private List<CommonOrderDetailrBean> commonOrderDetailBeanList = null;

	/**
	 * @return the tuikaFlg
	 */
	public boolean isTuikaFlg() {
		return tuikaFlg;
	}

	/**
	 * @param tuikaFlg
	 *            the tuikaFlg to set
	 */
	public void setTuikaFlg(boolean tuikaFlg) {
		this.tuikaFlg = tuikaFlg;
	}

	/**
	 * @return the tuikasetteibi
	 */
	public String getTuikasetteibi() {
		return tuikasetteibi;
	}

	/**
	 * @param tuikasetteibi
	 *            the tuikasetteibi to set
	 */
	public void setTuikasetteibi(String tuikasetteibi) {
		this.tuikasetteibi = tuikasetteibi;
	}

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

	/**
	 * @return the shikyuFlg
	 */
	public boolean isShikyuFlg() {
		return shikyuFlg;
	}

	/**
	 * @param shikyuFlg
	 *            the shikyuFlg to set
	 */
	public void setShikyuFlg(boolean shikyuFlg) {
		this.shikyuFlg = shikyuFlg;
	}

	/**
	 * @return the shikyusetteibi
	 */
	public String getShikyusetteibi() {
		return shikyusetteibi;
	}

	/**
	 * @param shikyusetteibi
	 *            the shikyusetteibi to set
	 */
	public void setShikyusetteibi(String shikyusetteibi) {
		this.shikyusetteibi = shikyusetteibi;
	}

	/**
	 * @return the henpinFlg
	 */
	public boolean isHenpinFlg() {
		return henpinFlg;
	}

	/**
	 * @param henpinFlg
	 *            the henpinFlg to set
	 */
	public void setHenpinFlg(boolean henpinFlg) {
		this.henpinFlg = henpinFlg;
	}

	/**
	 * @return the henpinsetteibi
	 */
	public String getHenpinsetteibi() {
		return henpinsetteibi;
	}

	/**
	 * @param henpinsetteibi
	 *            the henpinsetteibi to set
	 */
	public void setHenpinsetteibi(String henpinsetteibi) {
		this.henpinsetteibi = henpinsetteibi;
	}

	/**
	 * @return the koukanFlg
	 */
	public boolean isKoukanFlg() {
		return koukanFlg;
	}

	/**
	 * @param koukanFlg
	 *            the koukanFlg to set
	 */
	public void setKoukanFlg(boolean koukanFlg) {
		this.koukanFlg = koukanFlg;
	}

	/**
	 * @return the koukansetteibi
	 */
	public String getKoukansetteibi() {
		return koukansetteibi;
	}

	/**
	 * @param koukansetteibi
	 *            the koukansetteibi to set
	 */
	public void setKoukansetteibi(String koukansetteibi) {
		this.koukansetteibi = koukansetteibi;
	}

	/**
	 * @return the bunnouFlg
	 */
	public boolean isBunnouFlg() {
		return bunnouFlg;
	}

	/**
	 * @param bunnouFlg
	 *            the bunnouFlg to set
	 */
	public void setBunnouFlg(boolean bunnouFlg) {
		this.bunnouFlg = bunnouFlg;
	}

	/**
	 * @return the bunnousetteibi
	 */
	public String getBunnousetteibi() {
		return bunnousetteibi;
	}

	/**
	 * @param bunnousetteibi
	 *            the bunnousetteibi to set
	 */
	public void setBunnousetteibi(String bunnousetteibi) {
		this.bunnousetteibi = bunnousetteibi;
	}

	/**
	 * @return the yusenjun
	 */
	public long getYusenjun() {
		return yusenjun;
	}

	/**
	 * @param yusenjun
	 *            the yusenjun to set
	 */
	public void setYusenjun(long yusenjun) {
		this.yusenjun = yusenjun;
	}

	/**
	 * @return the chumonichiji
	 */
	public String getChumonichiji() {
		return chumonichiji;
	}

	/**
	 * @param chumonichiji
	 *            the chumonichiji to set
	 */
	public void setChumonichiji(String chumonichiji) {
		this.chumonichiji = chumonichiji;
	}

	/**
	 * @return the commonOrderDetailBeanList
	 */
	public List<CommonOrderDetailrBean> getCommonOrderDetailBeanList() {
		return commonOrderDetailBeanList;
	}

	/**
	 * @param commonOrderDetailBeanList
	 *            the commonOrderDetailBeanList to set
	 */
	public void setCommonOrderDetailBeanList(
			List<CommonOrderDetailrBean> commonOrderDetailBeanList) {
		this.commonOrderDetailBeanList = commonOrderDetailBeanList;
	}

	public String getOshiharaihoho() {
		return oshiharaihoho;
	}

	public void setOshiharaihoho(String oshiharaihoho) {
		this.oshiharaihoho = oshiharaihoho;
	}

	public String getHasoyakusokubi() {
		return hasoyakusokubi;
	}

	public void setHasoyakusokubi(String hasoyakusokubi) {
		this.hasoyakusokubi = hasoyakusokubi;
	}

}
