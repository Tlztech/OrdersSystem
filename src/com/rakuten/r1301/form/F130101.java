package com.rakuten.r1301.form;

import java.io.Serializable;
import java.util.List;

public class F130101 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String kikanStart = null;
	private String kikanEnd = null;
	private String chumonbango = null;
	private String sofusakinamae = null;
	private String sofusakidenwabango = null;
	private String oshiharaihoho = null;
	private String haisohoho = null;
	private String hasomachihasokaCount = "0";
	private String tuikahasomachihasokaCount = "0";
	private String bunnohasomachihasokaCount = "0";
	private String hasomachishikyuhasokaCount = "0";
	private String tuikahasomachishikyuhasokaCount = "0";
	private String bunnohasomachishikyuhasokaCount = "0";
	private String unyokaisha = null;
	private boolean checkFlg = true;
	private String rakutenCount = "0";
	private String yahooShoppingCount = "0";
	private String denaCount = "0";
	private String yafuokuCount = "0";
	private String pageHeight = "1146";
	private List<OrderList> orderList = null;
	private String userId = null;
	private String hasoshubetsu = "1";
	private String tenposhubetsu = "1";
	private String outtype = null;
	private boolean checkhitiyo = false;
	private String checkPass = null;
	private String tenpo = "";

	private String shohinbango = "";

	private String inputJuchubango = null;
	private String inputDenpyobango = null;

	private String chuyi = null;
	private String jikantaiset = null;

	private String takuhaibinkaisha = null;
	private String merubinkaisha = null;
	private String ponpareCount = null;
	private String qoo10Count = null;
	private String otherCount = null;

	/**
	 * @return the inputJuchubango
	 */
	public String getInputJuchubango() {
		return inputJuchubango;
	}

	/**
	 * @param inputJuchubango
	 *            the inputJuchubango to set
	 */
	public void setInputJuchubango(String inputJuchubango) {
		this.inputJuchubango = inputJuchubango;
	}

	/**
	 * @return the inputDenpyobango
	 */
	public String getInputDenpyobango() {
		return inputDenpyobango;
	}

	/**
	 * @param inputDenpyobango
	 *            the inputDenpyobango to set
	 */
	public void setInputDenpyobango(String inputDenpyobango) {
		this.inputDenpyobango = inputDenpyobango;
	}

	/**
	 * @return the rakutenCount
	 */
	public String getRakutenCount() {
		return rakutenCount;
	}

	/**
	 * @param rakutenCount
	 *            the rakutenCount to set
	 */
	public void setRakutenCount(String rakutenCount) {
		this.rakutenCount = rakutenCount;
	}

	/**
	 * @return the yahooShoppingCount
	 */
	public String getYahooShoppingCount() {
		return yahooShoppingCount;
	}

	/**
	 * @param yahooShoppingCount
	 *            the yahooShoppingCount to set
	 */
	public void setYahooShoppingCount(String yahooShoppingCount) {
		this.yahooShoppingCount = yahooShoppingCount;
	}

	/**
	 * @return the denaCount
	 */
	public String getDenaCount() {
		return denaCount;
	}

	/**
	 * @param denaCount
	 *            the denaCount to set
	 */
	public void setDenaCount(String denaCount) {
		this.denaCount = denaCount;
	}

	/**
	 * @return the yafuokuCount
	 */
	public String getYafuokuCount() {
		return yafuokuCount;
	}

	/**
	 * @param yafuokuCount
	 *            the yafuokuCount to set
	 */
	public void setYafuokuCount(String yafuokuCount) {
		this.yafuokuCount = yafuokuCount;
	}

	/**
	 * @return the tuikahasomachihasokaCount
	 */
	public String getTuikahasomachihasokaCount() {
		return tuikahasomachihasokaCount;
	}

	/**
	 * @param tuikahasomachihasokaCount
	 *            the tuikahasomachihasokaCount to set
	 */
	public void setTuikahasomachihasokaCount(String tuikahasomachihasokaCount) {
		this.tuikahasomachihasokaCount = tuikahasomachihasokaCount;
	}

	/**
	 * @return the bunnohasomachihasokaCount
	 */
	public String getBunnohasomachihasokaCount() {
		return bunnohasomachihasokaCount;
	}

	/**
	 * @param bunnohasomachihasokaCount
	 *            the bunnohasomachihasokaCount to set
	 */
	public void setBunnohasomachihasokaCount(String bunnohasomachihasokaCount) {
		this.bunnohasomachihasokaCount = bunnohasomachihasokaCount;
	}

	/**
	 * @return the hasomachishikyuhasokaCount
	 */
	public String getHasomachishikyuhasokaCount() {
		return hasomachishikyuhasokaCount;
	}

	/**
	 * @param hasomachishikyuhasokaCount
	 *            the hasomachishikyuhasokaCount to set
	 */
	public void setHasomachishikyuhasokaCount(String hasomachishikyuhasokaCount) {
		this.hasomachishikyuhasokaCount = hasomachishikyuhasokaCount;
	}

	/**
	 * @return the tuikahasomachishikyuhasokaCount
	 */
	public String getTuikahasomachishikyuhasokaCount() {
		return tuikahasomachishikyuhasokaCount;
	}

	/**
	 * @param tuikahasomachishikyuhasokaCount
	 *            the tuikahasomachishikyuhasokaCount to set
	 */
	public void setTuikahasomachishikyuhasokaCount(String tuikahasomachishikyuhasokaCount) {
		this.tuikahasomachishikyuhasokaCount = tuikahasomachishikyuhasokaCount;
	}

	/**
	 * @return the bunnohasomachishikyuhasokaCount
	 */
	public String getBunnohasomachishikyuhasokaCount() {
		return bunnohasomachishikyuhasokaCount;
	}

	/**
	 * @param bunnohasomachishikyuhasokaCount
	 *            the bunnohasomachishikyuhasokaCount to set
	 */
	public void setBunnohasomachishikyuhasokaCount(String bunnohasomachishikyuhasokaCount) {
		this.bunnohasomachishikyuhasokaCount = bunnohasomachishikyuhasokaCount;
	}

	/**
	 * @return the kikanStart
	 */
	public String getKikanStart() {
		return kikanStart;
	}

	/**
	 * @param kikanStart
	 *            the kikanStart to set
	 */
	public void setKikanStart(String kikanStart) {
		this.kikanStart = kikanStart;
	}

	/**
	 * @return the kikanEnd
	 */
	public String getKikanEnd() {
		return kikanEnd;
	}

	/**
	 * @param kikanEnd
	 *            the kikanEnd to set
	 */
	public void setKikanEnd(String kikanEnd) {
		this.kikanEnd = kikanEnd;
	}

	/**
	 * @return the chumonbango
	 */
	public String getChumonbango() {
		return chumonbango;
	}

	/**
	 * @param chumonbango
	 *            the chumonbango to set
	 */
	public void setChumonbango(String chumonbango) {
		this.chumonbango = chumonbango;
	}

	/**
	 * @return the sofusakinamae
	 */
	public String getSofusakinamae() {
		return sofusakinamae;
	}

	/**
	 * @param sofusakinamae
	 *            the sofusakinamae to set
	 */
	public void setSofusakinamae(String sofusakinamae) {
		this.sofusakinamae = sofusakinamae;
	}

	/**
	 * @return the sofusakidenwabango
	 */
	public String getSofusakidenwabango() {
		return sofusakidenwabango;
	}

	/**
	 * @param sofusakidenwabango
	 *            the sofusakidenwabango to set
	 */
	public void setSofusakidenwabango(String sofusakidenwabango) {
		this.sofusakidenwabango = sofusakidenwabango;
	}

	/**
	 * @return the oshiharaihoho
	 */
	public String getOshiharaihoho() {
		return oshiharaihoho;
	}

	/**
	 * @param oshiharaihoho
	 *            the oshiharaihoho to set
	 */
	public void setOshiharaihoho(String oshiharaihoho) {
		this.oshiharaihoho = oshiharaihoho;
	}

	/**
	 * @return the haisohoho
	 */
	public String getHaisohoho() {
		return haisohoho;
	}

	/**
	 * @param haisohoho
	 *            the haisohoho to set
	 */
	public void setHaisohoho(String haisohoho) {
		this.haisohoho = haisohoho;
	}

	/**
	 * @return the orderList
	 */
	public List<OrderList> getOrderList() {
		return orderList;
	}

	/**
	 * @param orderList
	 *            the orderList to set
	 */
	public void setOrderList(List<OrderList> orderList) {
		this.orderList = orderList;
	}

	public String getHasomachihasokaCount() {
		return hasomachihasokaCount;
	}

	public void setHasomachihasokaCount(String hasomachihasokaCount) {
		this.hasomachihasokaCount = hasomachihasokaCount;
	}

	public String getHasoshubetsu() {
		return hasoshubetsu;
	}

	public void setHasoshubetsu(String hasoshubetsu) {
		this.hasoshubetsu = hasoshubetsu;
	}

	public String getTenposhubetsu() {
		return tenposhubetsu;
	}

	public void setTenposhubetsu(String tenposhubetsu) {
		this.tenposhubetsu = tenposhubetsu;
	}

	public String getTenpo() {
		return tenpo;
	}

	public void setTenpo(String tenpo) {
		this.tenpo = tenpo;
	}

	public String getOuttype() {
		return outtype;
	}

	public void setOuttype(String outtype) {
		this.outtype = outtype;
	}

	public String getChuyi() {
		return chuyi;
	}

	public void setChuyi(String chuyi) {
		this.chuyi = chuyi;
	}

	public String getJikantaiset() {
		return jikantaiset;
	}

	public void setJikantaiset(String jikantaiset) {
		this.jikantaiset = jikantaiset;
	}

	public boolean isCheckFlg() {
		return checkFlg;
	}

	public void setCheckFlg(boolean checkFlg) {
		this.checkFlg = checkFlg;
	}

	public String getPageHeight() {
		return pageHeight;
	}

	public void setPageHeight(String pageHeight) {
		this.pageHeight = pageHeight;
	}

	public String getTakuhaibinkaisha() {
		return takuhaibinkaisha;
	}

	public void setTakuhaibinkaisha(String takuhaibinkaisha) {
		this.takuhaibinkaisha = takuhaibinkaisha;
	}

	public String getMerubinkaisha() {
		return merubinkaisha;
	}

	public void setMerubinkaisha(String merubinkaisha) {
		this.merubinkaisha = merubinkaisha;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isCheckhitiyo() {
		return checkhitiyo;
	}

	public void setCheckhitiyo(boolean checkhitiyo) {
		this.checkhitiyo = checkhitiyo;
	}

	public String getCheckPass() {
		return checkPass;
	}

	public void setCheckPass(String checkPass) {
		this.checkPass = checkPass;
	}

	public String getUnyokaisha() {
		return unyokaisha;
	}

	public void setUnyokaisha(String unyokaisha) {
		this.unyokaisha = unyokaisha;
	}

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

	public String getPonpareCount() {
		return ponpareCount;
	}

	public void setPonpareCount(String ponpareCount) {
		this.ponpareCount = ponpareCount;
	}

	public String getQoo10Count() {
		return qoo10Count;
	}

	public void setQoo10Count(String qoo10Count) {
		this.qoo10Count = qoo10Count;
	}

	public String getOtherCount() {
		return otherCount;
	}

	public void setOtherCount(String otherCount) {
		this.otherCount = otherCount;
	}
	
}
