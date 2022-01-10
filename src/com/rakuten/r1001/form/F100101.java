package com.rakuten.r1001.form;

import java.io.Serializable;
import java.util.List;

public class F100101 implements Serializable {

	private static final long serialVersionUID = 1L;

	List<OrderList> orderList = null;
	private String mondaiariCount = null;
	private String shutokushop = null;
	private String shutokuyahooshop = null;
	private String shutokuamazonshop = null;
	private String shutokuaushop = null;
	private String kikanStart = null;
	private String kikanEnd = null;
	private String keyword = null;
	private String kewordType = null;
	private boolean searchKeywordCondition = false;
	private String chumonbango = null;
	private String chumonEmail = null;
	private String tenpobetsu = null;
	private String denwabango = null;
	private String denwabangoType = null;
	private String nyukinSts = null;
	private boolean chumonStsSearch0 = false;
	private boolean chumonStsSearch1 = false;
	private boolean chumonStsSearch2 = false;
	private boolean chumonStsSearch3 = false;
	private boolean chumonStsSearch4 = false;
	private boolean chumonStsSearch5 = false;
	private boolean chumonStsSearch6 = false;
	private boolean chumonStsSearch7 = false;
	private boolean chumonStsSearch8 = false;
	private boolean chumonStsSearch9 = false;
	private String resultCount = null;
	private String oshiharaihoho = null;
	private String chumonshanamae = null;
	private String sofusakinamae = null;
	private String site = null;
	private String hasomachiCount = "0";
	private String tuikahasomachiCount = "0";
	private String bunnohasomachiCount = "0";
	private String hasomachishikyuCount = "0";
	private String tuikahasomachishikyuCount = "0";
	private String bunnohasomachishikyuCount = "0";
	private String hasomachihasokaCount = "0";
	private String tuikahasomachihasokaCount = "0";
	private String bunnohasomachihasokaCount = "0";
	private String hasomachishikyuhasokaCount = "0";
	private String tuikahasomachishikyuhasokaCount = "0";
	private String bunnohasomachishikyuhasokaCount = "0";
	private String henpinmachiCount = "0";
	private String henpinchuCount = "0";
	private String henkinmachiCount = "0";
	private String soshinmachiCount = "0";
	private String sonotachuijikoCount = "0";
	private String okureCount = "0";
	private String todokeseteimachiCount = "0";
	private String hasomaekakuninCount = "0";
	private String nyukamachiCount = "0";
	private String nyukachuCount = "0";
	private String unsochuCount = "0";
	private String todokeset = "0";

	private String nyukafukarenrakumachiCount = "0";
	private String nyukafukarenrakuzumiCount = "0";

	private String ijoCount = "0";
	
	private String charset = null;

	public String getNyukafukarenrakumachiCount() {
		return nyukafukarenrakumachiCount;
	}

	public void setNyukafukarenrakumachiCount(String nyukafukarenrakumachiCount) {
		this.nyukafukarenrakumachiCount = nyukafukarenrakumachiCount;
	}

	public String getNyukafukarenrakuzumiCount() {
		return nyukafukarenrakuzumiCount;
	}

	public void setNyukafukarenrakuzumiCount(String nyukafukarenrakuzumiCount) {
		this.nyukafukarenrakuzumiCount = nyukafukarenrakuzumiCount;
	}

	public String getNyukamachiCount() {
		return nyukamachiCount;
	}

	public void setNyukamachiCount(String nyukamachiCount) {
		this.nyukamachiCount = nyukamachiCount;
	}

	public String getNyukachuCount() {
		return nyukachuCount;
	}

	public void setNyukachuCount(String nyukachuCount) {
		this.nyukachuCount = nyukachuCount;
	}

	public String getUnsochuCount() {
		return unsochuCount;
	}

	public void setUnsochuCount(String unsochuCount) {
		this.unsochuCount = unsochuCount;
	}

	/**
	 * @return the todokeseteimachiCount
	 */
	public String getTodokeseteimachiCount() {
		return todokeseteimachiCount;
	}

	/**
	 * @param todokeseteimachiCount
	 *            the todokeseteimachiCount to set
	 */
	public void setTodokeseteimachiCount(String todokeseteimachiCount) {
		this.todokeseteimachiCount = todokeseteimachiCount;
	}

	/**
	 * @return the hasomaekakuninCount
	 */
	public String getHasomaekakuninCount() {
		return hasomaekakuninCount;
	}

	/**
	 * @param hasomaekakuninCount
	 *            the hasomaekakuninCount to set
	 */
	public void setHasomaekakuninCount(String hasomaekakuninCount) {
		this.hasomaekakuninCount = hasomaekakuninCount;
	}

	/**
	 * @return the hasomachiCount
	 */
	public String getHasomachiCount() {
		return hasomachiCount;
	}

	/**
	 * @param hasomachiCount
	 *            the hasomachiCount to set
	 */
	public void setHasomachiCount(String hasomachiCount) {
		this.hasomachiCount = hasomachiCount;
	}

	/**
	 * @return the tuikahasomachiCount
	 */
	public String getTuikahasomachiCount() {
		return tuikahasomachiCount;
	}

	/**
	 * @param tuikahasomachiCount
	 *            the tuikahasomachiCount to set
	 */
	public void setTuikahasomachiCount(String tuikahasomachiCount) {
		this.tuikahasomachiCount = tuikahasomachiCount;
	}

	/**
	 * @return the bunnohasomachiCount
	 */
	public String getBunnohasomachiCount() {
		return bunnohasomachiCount;
	}

	/**
	 * @param bunnohasomachiCount
	 *            the bunnohasomachiCount to set
	 */
	public void setBunnohasomachiCount(String bunnohasomachiCount) {
		this.bunnohasomachiCount = bunnohasomachiCount;
	}

	/**
	 * @return the hasomachishikyuCount
	 */
	public String getHasomachishikyuCount() {
		return hasomachishikyuCount;
	}

	/**
	 * @param hasomachishikyuCount
	 *            the hasomachishikyuCount to set
	 */
	public void setHasomachishikyuCount(String hasomachishikyuCount) {
		this.hasomachishikyuCount = hasomachishikyuCount;
	}

	/**
	 * @return the tuikahasomachishikyuCount
	 */
	public String getTuikahasomachishikyuCount() {
		return tuikahasomachishikyuCount;
	}

	/**
	 * @param tuikahasomachishikyuCount
	 *            the tuikahasomachishikyuCount to set
	 */
	public void setTuikahasomachishikyuCount(String tuikahasomachishikyuCount) {
		this.tuikahasomachishikyuCount = tuikahasomachishikyuCount;
	}

	/**
	 * @return the bunnohasomachishikyuCount
	 */
	public String getBunnohasomachishikyuCount() {
		return bunnohasomachishikyuCount;
	}

	/**
	 * @param bunnohasomachishikyuCount
	 *            the bunnohasomachishikyuCount to set
	 */
	public void setBunnohasomachishikyuCount(String bunnohasomachishikyuCount) {
		this.bunnohasomachishikyuCount = bunnohasomachishikyuCount;
	}

	/**
	 * @return the hasomachihasokaCount
	 */
	public String getHasomachihasokaCount() {
		return hasomachihasokaCount;
	}

	/**
	 * @param hasomachihasokaCount
	 *            the hasomachihasokaCount to set
	 */
	public void setHasomachihasokaCount(String hasomachihasokaCount) {
		this.hasomachihasokaCount = hasomachihasokaCount;
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
	public void setTuikahasomachishikyuhasokaCount(
			String tuikahasomachishikyuhasokaCount) {
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
	public void setBunnohasomachishikyuhasokaCount(
			String bunnohasomachishikyuhasokaCount) {
		this.bunnohasomachishikyuhasokaCount = bunnohasomachishikyuhasokaCount;
	}

	/**
	 * @return the henpinmachiCount
	 */
	public String getHenpinmachiCount() {
		return henpinmachiCount;
	}

	/**
	 * @param henpinmachiCount
	 *            the henpinmachiCount to set
	 */
	public void setHenpinmachiCount(String henpinmachiCount) {
		this.henpinmachiCount = henpinmachiCount;
	}

	/**
	 * @return the henpinchuCount
	 */
	public String getHenpinchuCount() {
		return henpinchuCount;
	}

	/**
	 * @param henpinchuCount
	 *            the henpinchuCount to set
	 */
	public void setHenpinchuCount(String henpinchuCount) {
		this.henpinchuCount = henpinchuCount;
	}

	/**
	 * @return the henkinmachiCount
	 */
	public String getHenkinmachiCount() {
		return henkinmachiCount;
	}

	/**
	 * @param henkinmachiCount
	 *            the henkinmachiCount to set
	 */
	public void setHenkinmachiCount(String henkinmachiCount) {
		this.henkinmachiCount = henkinmachiCount;
	}

	/**
	 * @return the soshinmachiCount
	 */
	public String getSoshinmachiCount() {
		return soshinmachiCount;
	}

	/**
	 * @param soshinmachiCount
	 *            the soshinmachiCount to set
	 */
	public void setSoshinmachiCount(String soshinmachiCount) {
		this.soshinmachiCount = soshinmachiCount;
	}

	/**
	 * @return the sonotachuijikoCount
	 */
	public String getSonotachuijikoCount() {
		return sonotachuijikoCount;
	}

	/**
	 * @param sonotachuijikoCount
	 *            the sonotachuijikoCount to set
	 */
	public void setSonotachuijikoCount(String sonotachuijikoCount) {
		this.sonotachuijikoCount = sonotachuijikoCount;
	}

	/**
	 * @return the chumonshanamae
	 */
	public String getChumonshanamae() {
		return chumonshanamae;
	}

	/**
	 * @param chumonshanamae
	 *            the chumonshanamae to set
	 */
	public void setChumonshanamae(String chumonshanamae) {
		this.chumonshanamae = chumonshanamae;
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
	 * @return the keyword
	 */
	public String getKeyword() {
		return keyword;
	}

	/**
	 * @param keyword
	 *            the keyword to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the kewordType
	 */
	public String getKewordType() {
		return kewordType;
	}

	/**
	 * @param kewordType
	 *            the kewordType to set
	 */
	public void setKewordType(String kewordType) {
		this.kewordType = kewordType;
	}

	public boolean isSearchKeywordCondition() {
		return searchKeywordCondition;
	}

	public void setSearchKeywordCondition(boolean searchKeywordCondition) {
		this.searchKeywordCondition = searchKeywordCondition;
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
	 * @return the chumonEmail
	 */
	public String getChumonEmail() {
		return chumonEmail;
	}

	/**
	 * @param chumonEmail
	 *            the chumonEmail to set
	 */
	public void setChumonEmail(String chumonEmail) {
		this.chumonEmail = chumonEmail;
	}

	/**
	 * @return the tenpobetsu
	 */
	public String getTenpobetsu() {
		return tenpobetsu;
	}

	/**
	 * @return the chumonStsSearch0
	 */
	public boolean isChumonStsSearch0() {
		return chumonStsSearch0;
	}

	/**
	 * @param chumonStsSearch0
	 *            the chumonStsSearch0 to set
	 */
	public void setChumonStsSearch0(boolean chumonStsSearch0) {
		this.chumonStsSearch0 = chumonStsSearch0;
	}

	/**
	 * @return the chumonStsSearch1
	 */
	public boolean isChumonStsSearch1() {
		return chumonStsSearch1;
	}

	/**
	 * @param chumonStsSearch1
	 *            the chumonStsSearch1 to set
	 */
	public void setChumonStsSearch1(boolean chumonStsSearch1) {
		this.chumonStsSearch1 = chumonStsSearch1;
	}

	/**
	 * @return the chumonStsSearch2
	 */
	public boolean isChumonStsSearch2() {
		return chumonStsSearch2;
	}

	/**
	 * @param chumonStsSearch2
	 *            the chumonStsSearch2 to set
	 */
	public void setChumonStsSearch2(boolean chumonStsSearch2) {
		this.chumonStsSearch2 = chumonStsSearch2;
	}

	/**
	 * @return the chumonStsSearch3
	 */
	public boolean isChumonStsSearch3() {
		return chumonStsSearch3;
	}

	/**
	 * @param chumonStsSearch3
	 *            the chumonStsSearch3 to set
	 */
	public void setChumonStsSearch3(boolean chumonStsSearch3) {
		this.chumonStsSearch3 = chumonStsSearch3;
	}

	/**
	 * @return the chumonStsSearch4
	 */
	public boolean isChumonStsSearch4() {
		return chumonStsSearch4;
	}

	/**
	 * @param chumonStsSearch4
	 *            the chumonStsSearch4 to set
	 */
	public void setChumonStsSearch4(boolean chumonStsSearch4) {
		this.chumonStsSearch4 = chumonStsSearch4;
	}

	/**
	 * @return the chumonStsSearch5
	 */
	public boolean isChumonStsSearch5() {
		return chumonStsSearch5;
	}

	/**
	 * @param chumonStsSearch5
	 *            the chumonStsSearch5 to set
	 */
	public void setChumonStsSearch5(boolean chumonStsSearch5) {
		this.chumonStsSearch5 = chumonStsSearch5;
	}

	/**
	 * @return the chumonStsSearch6
	 */
	public boolean isChumonStsSearch6() {
		return chumonStsSearch6;
	}

	/**
	 * @param chumonStsSearch6
	 *            the chumonStsSearch6 to set
	 */
	public void setChumonStsSearch6(boolean chumonStsSearch6) {
		this.chumonStsSearch6 = chumonStsSearch6;
	}

	/**
	 * @return the chumonStsSearch7
	 */
	public boolean isChumonStsSearch7() {
		return chumonStsSearch7;
	}

	/**
	 * @param chumonStsSearch7
	 *            the chumonStsSearch7 to set
	 */
	public void setChumonStsSearch7(boolean chumonStsSearch7) {
		this.chumonStsSearch7 = chumonStsSearch7;
	}

	/**
	 * @return the chumonStsSearch8
	 */
	public boolean isChumonStsSearch8() {
		return chumonStsSearch8;
	}

	/**
	 * @param chumonStsSearch8
	 *            the chumonStsSearch8 to set
	 */
	public void setChumonStsSearch8(boolean chumonStsSearch8) {
		this.chumonStsSearch8 = chumonStsSearch8;
	}

	/**
	 * @return the chumonStsSearch9
	 */
	public boolean isChumonStsSearch9() {
		return chumonStsSearch9;
	}

	/**
	 * @param chumonStsSearch9
	 *            the chumonStsSearch9 to set
	 */
	public void setChumonStsSearch9(boolean chumonStsSearch9) {
		this.chumonStsSearch9 = chumonStsSearch9;
	}

	/**
	 * @param tenpobetsu
	 *            the tenpobetsu to set
	 */
	public void setTenpobetsu(String tenpobetsu) {
		this.tenpobetsu = tenpobetsu;
	}

	/**
	 * @return the denwabango
	 */
	public String getDenwabango() {
		return denwabango;
	}

	/**
	 * @param denwabango
	 *            the denwabango to set
	 */
	public void setDenwabango(String denwabango) {
		this.denwabango = denwabango;
	}

	/**
	 * @return the denwabangoType
	 */
	public String getDenwabangoType() {
		return denwabangoType;
	}

	/**
	 * @param denwabangoType
	 *            the denwabangoType to set
	 */
	public void setDenwabangoType(String denwabangoType) {
		this.denwabangoType = denwabangoType;
	}

	/**
	 * @return the nyukinSts
	 */
	public String getNyukinSts() {
		return nyukinSts;
	}

	/**
	 * @param nyukinSts
	 *            the nyukinSts to set
	 */
	public void setNyukinSts(String nyukinSts) {
		this.nyukinSts = nyukinSts;
	}

	/**
	 * @return the resultCount
	 */
	public String getResultCount() {
		return resultCount;
	}

	/**
	 * @param resultCount
	 *            the resultCount to set
	 */
	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public String getOshiharaihoho() {
		return oshiharaihoho;
	}

	public void setOshiharaihoho(String oshiharaihoho) {
		this.oshiharaihoho = oshiharaihoho;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getTodokeset() {
		return todokeset;
	}

	public void setTodokeset(String todokeset) {
		this.todokeset = todokeset;
	}

	public String getShutokushop() {
		return shutokushop;
	}

	public void setShutokushop(String shutokushop) {
		this.shutokushop = shutokushop;
	}

	public String getShutokuyahooshop() {
		return shutokuyahooshop;
	}

	public void setShutokuyahooshop(String shutokuyahooshop) {
		this.shutokuyahooshop = shutokuyahooshop;
	}

	/**
	 * @return the shutokuamazonshop
	 */
	public String getShutokuamazonshop() {
		return shutokuamazonshop;
	}

	/**
	 * @param shutokuamazonshop the shutokuamazonshop to set
	 */
	public void setShutokuamazonshop(String shutokuamazonshop) {
		this.shutokuamazonshop = shutokuamazonshop;
	}

	/**
	 * @return the shutokuaushop
	 */
	public String getShutokuaushop() {
		return shutokuaushop;
	}

	/**
	 * @param shutokuaushop the shutokuaushop to set
	 */
	public void setShutokuaushop(String shutokuaushop) {
		this.shutokuaushop = shutokuaushop;
	}

	public String getIjoCount() {
		return ijoCount;
	}

	public void setIjoCount(String ijoCount) {
		this.ijoCount = ijoCount;
	}

	public String getOkureCount() {
		return okureCount;
	}

	public void setOkureCount(String okureCount) {
		this.okureCount = okureCount;
	}

	public String getMondaiariCount() {
		return mondaiariCount;
	}

	public void setMondaiariCount(String mondaiariCount) {
		this.mondaiariCount = mondaiariCount;
	}

	/**
	 * @return the charset
	 */
	public String getCharset() {
		return charset;
	}

	/**
	 * @param charset the charset to set
	 */
	public void setCharset(String charset) {
		this.charset = charset;
	}

}
