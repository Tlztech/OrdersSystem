package com.rakuten.common.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShohinStsInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// 受注番号
	private String juchubango = null;
	// 注文日時
	private String chumonnichiji = null;
	// ステータス最終設定日時
	private String statussaishusetteibi = null;
	// 注文ステータス
	private String chumonsts = null;
	// 保留個数(上海stock)
	private String horyukosuSh = "0";
	// 保留個数(日本stock)
	private String horyukosuJp = "0";
	// 保留個数(運送途中stock)
	private String horyukosuUnso = "0";
	// 保留個数(入荷中stock)
	private String horyukosuNyuka = "0";

	// 必要個数
	private String hiyuyokosu = "0";
	// 保留ステータス
	private String horyusts = null;
	// 入荷日
	private List<String> nyukabi = new ArrayList<String>();
	// 発送日（上海から）
	private List<String> hasoubi = new ArrayList<String>();
	// 伝票番号（上海から）
	private List<String> denpyoubango = new ArrayList<String>();
	// 入荷保留リスト
	private List<String> nyukahoryuList = new ArrayList<String>();
	// 運送保留リスト
	private List<String> unsohoryuList = new ArrayList<String>();

	/**
	 * @return the nyukahoryuList
	 */
	public List<String> getNyukahoryuList() {
		return nyukahoryuList;
	}

	/**
	 * @param nyukahoryuList
	 *            the nyukahoryuList to set
	 */
	public void setNyukahoryuList(List<String> nyukahoryuList) {
		this.nyukahoryuList = nyukahoryuList;
	}

	/**
	 * @return the unsohoryuList
	 */
	public List<String> getUnsohoryuList() {
		return unsohoryuList;
	}

	/**
	 * @param unsohoryuList
	 *            the unsohoryuList to set
	 */
	public void setUnsohoryuList(List<String> unsohoryuList) {
		this.unsohoryuList = unsohoryuList;
	}

	/**
	 * @return the nyukabi
	 */
	public List<String> getNyukabi() {
		return nyukabi;
	}

	/**
	 * @param nyukabi
	 *            the nyukabi to set
	 */
	public void setNyukabi(List<String> nyukabi) {
		this.nyukabi = nyukabi;
	}

	/**
	 * @return the hasoubi
	 */
	public List<String> getHasoubi() {
		return hasoubi;
	}

	/**
	 * @param hasoubi
	 *            the hasoubi to set
	 */
	public void setHasoubi(List<String> hasoubi) {
		this.hasoubi = hasoubi;
	}

	/**
	 * @return the denpyoubango
	 */
	public List<String> getDenpyoubango() {
		return denpyoubango;
	}

	/**
	 * @param denpyoubango
	 *            the denpyoubango to set
	 */
	public void setDenpyoubango(List<String> denpyoubango) {
		this.denpyoubango = denpyoubango;
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
	 * @return the chumonnichiji
	 */
	public String getChumonnichiji() {
		return chumonnichiji;
	}

	/**
	 * @param chumonnichiji
	 *            the chumonnichiji to set
	 */
	public void setChumonnichiji(String chumonnichiji) {
		this.chumonnichiji = chumonnichiji;
	}

	/**
	 * @return the statussaishusetteibi
	 */
	public String getStatussaishusetteibi() {
		return statussaishusetteibi;
	}

	/**
	 * @param statussaishusetteibi
	 *            the statussaishusetteibi to set
	 */
	public void setStatussaishusetteibi(String statussaishusetteibi) {
		this.statussaishusetteibi = statussaishusetteibi;
	}

	/**
	 * @return the chumonsts
	 */
	public String getChumonsts() {
		return chumonsts;
	}

	/**
	 * @param chumonsts
	 *            the chumonsts to set
	 */
	public void setChumonsts(String chumonsts) {
		this.chumonsts = chumonsts;
	}

	/**
	 * @return the horyukosuSh
	 */
	public String getHoryukosuSh() {
		return horyukosuSh;
	}

	/**
	 * @param horyukosuSh
	 *            the horyukosuSh to set
	 */
	public void setHoryukosuSh(String horyukosuSh) {
		this.horyukosuSh = horyukosuSh;
	}

	/**
	 * @return the horyukosuJp
	 */
	public String getHoryukosuJp() {
		return horyukosuJp;
	}

	/**
	 * @param horyukosuJp
	 *            the horyukosuJp to set
	 */
	public void setHoryukosuJp(String horyukosuJp) {
		this.horyukosuJp = horyukosuJp;
	}

	/**
	 * @return the horyukosuUnso
	 */
	public String getHoryukosuUnso() {
		return horyukosuUnso;
	}

	/**
	 * @param horyukosuUnso
	 *            the horyukosuUnso to set
	 */
	public void setHoryukosuUnso(String horyukosuUnso) {
		this.horyukosuUnso = horyukosuUnso;
	}

	/**
	 * @return the horyukosuNyuka
	 */
	public String getHoryukosuNyuka() {
		return horyukosuNyuka;
	}

	/**
	 * @param horyukosuNyuka
	 *            the horyukosuNyuka to set
	 */
	public void setHoryukosuNyuka(String horyukosuNyuka) {
		this.horyukosuNyuka = horyukosuNyuka;
	}

	/**
	 * @return the horyusts
	 */
	public String getHoryusts() {
		return horyusts;
	}

	/**
	 * @param horyusts
	 *            the horyusts to set
	 */
	public void setHoryusts(String horyusts) {
		this.horyusts = horyusts;
	}

	public String getHiyuyokosu() {
		return hiyuyokosu;
	}

	public void setHiyuyokosu(String hiyuyokosu) {
		this.hiyuyokosu = hiyuyokosu;
	}

}
