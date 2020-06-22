package com.rakuten.r1001.bean;

import java.io.Serializable;
import java.util.List;

public class DenaCsvBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 取引No.
	private String juchubango = null;
	// 管理No.
	// ロットNo.
	// タイトル
	// 落札価格
	// 個数
	// 落札日
	private String chumonnichiji = null;
	// ニックネーム
	// Eメールアドレス
	// 【取引管理】名前
	private String chumonshameiji = null;
	private String chumonshanamae = null;
	// 【取引管理】住所
	// 【取引管理】電話番号
	// 【取引ナビ】名前
	// 【取引ナビ】住所
	private String chumonshayubinbango1 = null;
	private String chumonshayubinbango2 = null;
	private String chumonshajushotodofuken = null;
	private String chumonshajushotoshiku = null;
	// 【取引ナビ】電話番号
	private String chumonshadenwabango1 = null;
	private String chumonshadenwabango2 = null;
	private String chumonshadenwabango3 = null;
	// 【取引ナビ】希望取引方法
	private String kesaihouhou = null;
	// 【取引ナビ】コメント
	private String komento = null;
	// 【出品時設定】希望取引方法
	// 【取引管理】実際の取引方法
	// 連絡済み
	// 連絡日
	// 入金確認済み
	// 入金確認日
	// 発送済み
	// 発送日
	// 販売単価
	// 販売個数
	// 小計
	// 消費税
	private String shohizei = null;
	// 手数料
	private String daibikiryou = null;
	// 送料
	private String soryou = null;
	// 請求金額
	private String seikyukingaku = null;
	// 取引メモ
	// 【取引ナビ】送付先氏名
	private String sofusakimeiji = null;
	private String soufusakinamae = null;
	private String soufusakimeijifurigana = null;
	private String soufusakimeijinamaefurigana = null;
	// 【取引ナビ】送付先住所
	private String soufusakiyubinbango1 = null;
	private String soufusakiyubinbango2 = null;
	private String soufusakijushotodofuken = null;
	private String soufusakijushotoshiku = null;
	// 【取引ナビ】送付先電話番号
	private String soufusakidenwabango1 = null;
	private String soufusakidenwabango2 = null;
	private String soufusakidenwabango3 = null;
	// 【取引ナビ】落札者カナ
	private String chumonshameijifurigana = null;
	private String chumonshanamaefurigana = null;
	// 【取引ナビ】落札者日中連絡先
	// 【取引ナビ】落札者メールアドレス
	private String meruadoresu = null;
	// 【取引ナビ】送付先カナ
	// 【取引ナビ】送付先日中連絡先
	// 販売総額
	private String gokei = null;
	// 販売総数
	// 消費税区分
	// キャンセル
	// アイテムオプション
	// (旧)取引No.
	// カード種類
	// カード番号
	// 有効期限・年
	// 有効期限・月
	// カード名義人
	// 名義人生年月日
	// オークションタイプ ホームサイト
	// 商品コード
	// 総合計
	// ポイント利用分
	private String pointoriyogaku = null;

	// 利用キャンセル状況
	// 付与ポイント数
	// CB原資付与ポイント数
	// 付与ポイント確定(予定)日
	// 付与ポイント状況
	// 取引オプション
	// クレジットカードオプション
	// キャリア決済ステータス
	// 結果コード
	// 結果事由コード
	// サービス提供者側注文ID
	// キャリア決済請求額
	// 割引前単価
	// 割引額
	// auポイント利用分（金額）
	// 利用auポイント
	// 利用auポイントキャンセル状況

	List<DenaDetailCsvBean> shousaiList = null;

	/**
	 * @return the shousaiList
	 */
	public List<DenaDetailCsvBean> getShousaiList() {
		return shousaiList;
	}

	/**
	 * @param shousaiList
	 *            the shousaiList to set
	 */
	public void setShousaiList(List<DenaDetailCsvBean> shousaiList) {
		this.shousaiList = shousaiList;
	}

	public String getJuchubango() {
		return juchubango;
	}

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

	public String getKesaihouhou() {
		return kesaihouhou;
	}

	public void setKesaihouhou(String kesaihouhou) {
		this.kesaihouhou = kesaihouhou;
	}

	public String getPointoriyogaku() {
		return pointoriyogaku;
	}

	public void setPointoriyogaku(String pointoriyogaku) {
		this.pointoriyogaku = pointoriyogaku;
	}

	/**
	 * @return the chumonshameiji
	 */
	public String getChumonshameiji() {
		return chumonshameiji;
	}

	/**
	 * @param chumonshameiji
	 *            the chumonshameiji to set
	 */
	public void setChumonshameiji(String chumonshameiji) {
		this.chumonshameiji = chumonshameiji;
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
	 * @return the chumonshayubinbango1
	 */
	public String getChumonshayubinbango1() {
		return chumonshayubinbango1;
	}

	/**
	 * @param chumonshayubinbango1
	 *            the chumonshayubinbango1 to set
	 */
	public void setChumonshayubinbango1(String chumonshayubinbango1) {
		this.chumonshayubinbango1 = chumonshayubinbango1;
	}

	/**
	 * @return the chumonshayubinbango2
	 */
	public String getChumonshayubinbango2() {
		return chumonshayubinbango2;
	}

	/**
	 * @param chumonshayubinbango2
	 *            the chumonshayubinbango2 to set
	 */
	public void setChumonshayubinbango2(String chumonshayubinbango2) {
		this.chumonshayubinbango2 = chumonshayubinbango2;
	}

	/**
	 * @return the chumonshajushotodofuken
	 */
	public String getChumonshajushotodofuken() {
		return chumonshajushotodofuken;
	}

	/**
	 * @param chumonshajushotodofuken
	 *            the chumonshajushotodofuken to set
	 */
	public void setChumonshajushotodofuken(String chumonshajushotodofuken) {
		this.chumonshajushotodofuken = chumonshajushotodofuken;
	}

	/**
	 * @return the chumonshajushotoshiku
	 */
	public String getChumonshajushotoshiku() {
		return chumonshajushotoshiku;
	}

	/**
	 * @param chumonshajushotoshiku
	 *            the chumonshajushotoshiku to set
	 */
	public void setChumonshajushotoshiku(String chumonshajushotoshiku) {
		this.chumonshajushotoshiku = chumonshajushotoshiku;
	}

	/**
	 * @return the chumonshadenwabango1
	 */
	public String getChumonshadenwabango1() {
		return chumonshadenwabango1;
	}

	/**
	 * @param chumonshadenwabango1
	 *            the chumonshadenwabango1 to set
	 */
	public void setChumonshadenwabango1(String chumonshadenwabango1) {
		this.chumonshadenwabango1 = chumonshadenwabango1;
	}

	/**
	 * @return the chumonshadenwabango2
	 */
	public String getChumonshadenwabango2() {
		return chumonshadenwabango2;
	}

	/**
	 * @param chumonshadenwabango2
	 *            the chumonshadenwabango2 to set
	 */
	public void setChumonshadenwabango2(String chumonshadenwabango2) {
		this.chumonshadenwabango2 = chumonshadenwabango2;
	}

	/**
	 * @return the chumonshadenwabango3
	 */
	public String getChumonshadenwabango3() {
		return chumonshadenwabango3;
	}

	/**
	 * @param chumonshadenwabango3
	 *            the chumonshadenwabango3 to set
	 */
	public void setChumonshadenwabango3(String chumonshadenwabango3) {
		this.chumonshadenwabango3 = chumonshadenwabango3;
	}

	/**
	 * @return the komento
	 */
	public String getKomento() {
		return komento;
	}

	/**
	 * @param komento
	 *            the komento to set
	 */
	public void setKomento(String komento) {
		this.komento = komento;
	}

	/**
	 * @return the sofusakimeiji
	 */
	public String getSofusakimeiji() {
		return sofusakimeiji;
	}

	/**
	 * @param sofusakimeiji
	 *            the sofusakimeiji to set
	 */
	public void setSofusakimeiji(String sofusakimeiji) {
		this.sofusakimeiji = sofusakimeiji;
	}

	/**
	 * @return the soufusakinamae
	 */
	public String getSoufusakinamae() {
		return soufusakinamae;
	}

	/**
	 * @param soufusakinamae
	 *            the soufusakinamae to set
	 */
	public void setSoufusakinamae(String soufusakinamae) {
		this.soufusakinamae = soufusakinamae;
	}

	/**
	 * @return the soufusakimeijifurigana
	 */
	public String getSoufusakimeijifurigana() {
		return soufusakimeijifurigana;
	}

	/**
	 * @param soufusakimeijifurigana
	 *            the soufusakimeijifurigana to set
	 */
	public void setSoufusakimeijifurigana(String soufusakimeijifurigana) {
		this.soufusakimeijifurigana = soufusakimeijifurigana;
	}

	/**
	 * @return the soufusakimeijinamaefurigana
	 */
	public String getSoufusakimeijinamaefurigana() {
		return soufusakimeijinamaefurigana;
	}

	/**
	 * @param soufusakimeijinamaefurigana
	 *            the soufusakimeijinamaefurigana to set
	 */
	public void setSoufusakimeijinamaefurigana(
			String soufusakimeijinamaefurigana) {
		this.soufusakimeijinamaefurigana = soufusakimeijinamaefurigana;
	}

	/**
	 * @return the soufusakiyubinbango1
	 */
	public String getSoufusakiyubinbango1() {
		return soufusakiyubinbango1;
	}

	/**
	 * @param soufusakiyubinbango1
	 *            the soufusakiyubinbango1 to set
	 */
	public void setSoufusakiyubinbango1(String soufusakiyubinbango1) {
		this.soufusakiyubinbango1 = soufusakiyubinbango1;
	}

	/**
	 * @return the soufusakiyubinbango2
	 */
	public String getSoufusakiyubinbango2() {
		return soufusakiyubinbango2;
	}

	/**
	 * @param soufusakiyubinbango2
	 *            the soufusakiyubinbango2 to set
	 */
	public void setSoufusakiyubinbango2(String soufusakiyubinbango2) {
		this.soufusakiyubinbango2 = soufusakiyubinbango2;
	}

	/**
	 * @return the soufusakijushotodofuken
	 */
	public String getSoufusakijushotodofuken() {
		return soufusakijushotodofuken;
	}

	/**
	 * @param soufusakijushotodofuken
	 *            the soufusakijushotodofuken to set
	 */
	public void setSoufusakijushotodofuken(String soufusakijushotodofuken) {
		this.soufusakijushotodofuken = soufusakijushotodofuken;
	}

	/**
	 * @return the soufusakijushotoshiku
	 */
	public String getSoufusakijushotoshiku() {
		return soufusakijushotoshiku;
	}

	/**
	 * @param soufusakijushotoshiku
	 *            the soufusakijushotoshiku to set
	 */
	public void setSoufusakijushotoshiku(String soufusakijushotoshiku) {
		this.soufusakijushotoshiku = soufusakijushotoshiku;
	}

	/**
	 * @return the soufusakidenwabango1
	 */
	public String getSoufusakidenwabango1() {
		return soufusakidenwabango1;
	}

	/**
	 * @param soufusakidenwabango1
	 *            the soufusakidenwabango1 to set
	 */
	public void setSoufusakidenwabango1(String soufusakidenwabango1) {
		this.soufusakidenwabango1 = soufusakidenwabango1;
	}

	/**
	 * @return the soufusakidenwabango2
	 */
	public String getSoufusakidenwabango2() {
		return soufusakidenwabango2;
	}

	/**
	 * @param soufusakidenwabango2
	 *            the soufusakidenwabango2 to set
	 */
	public void setSoufusakidenwabango2(String soufusakidenwabango2) {
		this.soufusakidenwabango2 = soufusakidenwabango2;
	}

	/**
	 * @return the soufusakidenwabango3
	 */
	public String getSoufusakidenwabango3() {
		return soufusakidenwabango3;
	}

	/**
	 * @param soufusakidenwabango3
	 *            the soufusakidenwabango3 to set
	 */
	public void setSoufusakidenwabango3(String soufusakidenwabango3) {
		this.soufusakidenwabango3 = soufusakidenwabango3;
	}

	/**
	 * @return the chumonshameijifurigana
	 */
	public String getChumonshameijifurigana() {
		return chumonshameijifurigana;
	}

	/**
	 * @param chumonshameijifurigana
	 *            the chumonshameijifurigana to set
	 */
	public void setChumonshameijifurigana(String chumonshameijifurigana) {
		this.chumonshameijifurigana = chumonshameijifurigana;
	}

	/**
	 * @return the chumonshanamaefurigana
	 */
	public String getChumonshanamaefurigana() {
		return chumonshanamaefurigana;
	}

	/**
	 * @param chumonshanamaefurigana
	 *            the chumonshanamaefurigana to set
	 */
	public void setChumonshanamaefurigana(String chumonshanamaefurigana) {
		this.chumonshanamaefurigana = chumonshanamaefurigana;
	}

	/**
	 * @return the meruadoresu
	 */
	public String getMeruadoresu() {
		return meruadoresu;
	}

	/**
	 * @param meruadoresu
	 *            the meruadoresu to set
	 */
	public void setMeruadoresu(String meruadoresu) {
		this.meruadoresu = meruadoresu;
	}

	/**
	 * @return the shohizei
	 */
	public String getShohizei() {
		return shohizei;
	}

	/**
	 * @param shohizei
	 *            the shohizei to set
	 */
	public void setShohizei(String shohizei) {
		this.shohizei = shohizei;
	}

	/**
	 * @return the daibikiryou
	 */
	public String getDaibikiryou() {
		return daibikiryou;
	}

	/**
	 * @param daibikiryou
	 *            the daibikiryou to set
	 */
	public void setDaibikiryou(String daibikiryou) {
		this.daibikiryou = daibikiryou;
	}

	/**
	 * @return the soryou
	 */
	public String getSoryou() {
		return soryou;
	}

	/**
	 * @param soryou
	 *            the soryou to set
	 */
	public void setSoryou(String soryou) {
		this.soryou = soryou;
	}

	/**
	 * @return the gokei
	 */
	public String getGokei() {
		return gokei;
	}

	/**
	 * @param gokei
	 *            the gokei to set
	 */
	public void setGokei(String gokei) {
		this.gokei = gokei;
	}

	public String getSeikyukingaku() {
		return seikyukingaku;
	}

	public void setSeikyukingaku(String seikyukingaku) {
		this.seikyukingaku = seikyukingaku;
	}

}
