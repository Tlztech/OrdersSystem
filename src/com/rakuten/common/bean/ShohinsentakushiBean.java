package com.rakuten.common.bean;

import java.io.Serializable;

public class ShohinsentakushiBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// コントロールカラム
	private String kontororukaramu = null;
	// 商品管理番号（商品URL）
	private String shohinkanribango = null;
	// 選択肢タイプ
	private String sentakutaipu = null;
	// Select/Checkbox用項目名
	private String selectcheckboxyoukomokumei = null;
	// Select/Checkbox用選択肢
	private String selectcheckboxyousentakushi = null;
	// 項目選択肢別在庫用横軸選択肢
	private String komokusentakushibetuzaikoyouyokojikusentakushi = null;
	// 項目選択肢別在庫用横軸選択肢子番号
	private String komokusentakushibetuzaikoyoyokojikusentakushishibango = null;
	// 項目選択肢別在庫用縦軸選択肢
	private String komokusentakushizaikoyoutatejikusentakushi = null;
	// 項目選択肢別在庫用縦軸選択肢子番号
	private String komokusentakushibetuzaikoyotatejikusentakushishibango = null;
	// 項目選択肢別在庫用取り寄せ可能表示
	private String komokusentakushibetuzaikototoriyosekanohyouji = null;
	// 項目選択肢別在庫用在庫数
	private String komokusentakushibetuzaikoyozaikosu = null;
	// 在庫戻しフラグ
	private String zaikonodoshifuragu = null;
	// 在庫切れ時の注文受付
	private String zaikokiretokinochumonuketuke = null;
	// 在庫あり時納期管理番号
	private String zaikoaritokinoukikanribango = null;
	// 在庫切れ時納期管理番号
	private String zaikokiretokinoukikanribango = null;
	// 販売価格
	private String hanbaikakaku = null;
	// 表示価格
	private String hyojikakaku = null;
	// 送料
	private String souryou = null;
	// 個別送料
	private String kobetusouryou = null;
	// 送料区分1
	private String souryoukubun1 = null;
	// 送料区分2
	private String souryoukubun2 = null;


	public String getShohinkanribango() {
		return shohinkanribango;
	}

	public void setShohinkanribango(String shohinkanribango) {
		this.shohinkanribango = shohinkanribango;
	}

	public String getSentakutaipu() {
		return sentakutaipu;
	}

	public void setSentakutaipu(String sentakutaipu) {
		this.sentakutaipu = sentakutaipu;
	}

	public String getSelectcheckboxyoukomokumei() {
		return selectcheckboxyoukomokumei;
	}

	public void setSelectcheckboxyoukomokumei(String selectcheckboxyoukomokumei) {
		this.selectcheckboxyoukomokumei = selectcheckboxyoukomokumei;
	}

	public String getSelectcheckboxyousentakushi() {
		return selectcheckboxyousentakushi;
	}

	public void setSelectcheckboxyousentakushi(
			String selectcheckboxyousentakushi) {
		this.selectcheckboxyousentakushi = selectcheckboxyousentakushi;
	}

	public String getKomokusentakushibetuzaikoyouyokojikusentakushi() {
		return komokusentakushibetuzaikoyouyokojikusentakushi;
	}

	public void setKomokusentakushibetuzaikoyouyokojikusentakushi(
			String komokusentakushibetuzaikoyouyokojikusentakushi) {
		this.komokusentakushibetuzaikoyouyokojikusentakushi = komokusentakushibetuzaikoyouyokojikusentakushi;
	}

	public String getKomokusentakushibetuzaikoyoyokojikusentakushishibango() {
		return komokusentakushibetuzaikoyoyokojikusentakushishibango;
	}

	public void setKomokusentakushibetuzaikoyoyokojikusentakushishibango(
			String komokusentakushibetuzaikoyoyokojikusentakushishibango) {
		this.komokusentakushibetuzaikoyoyokojikusentakushishibango = komokusentakushibetuzaikoyoyokojikusentakushishibango;
	}

	public String getKomokusentakushizaikoyoutatejikusentakushi() {
		return komokusentakushizaikoyoutatejikusentakushi;
	}

	public void setKomokusentakushizaikoyoutatejikusentakushi(
			String komokusentakushizaikoyoutatejikusentakushi) {
		this.komokusentakushizaikoyoutatejikusentakushi = komokusentakushizaikoyoutatejikusentakushi;
	}

	public String getKomokusentakushibetuzaikoyotatejikusentakushishibango() {
		return komokusentakushibetuzaikoyotatejikusentakushishibango;
	}

	public void setKomokusentakushibetuzaikoyotatejikusentakushishibango(
			String komokusentakushibetuzaikoyotatejikusentakushishibango) {
		this.komokusentakushibetuzaikoyotatejikusentakushishibango = komokusentakushibetuzaikoyotatejikusentakushishibango;
	}

	public String getKomokusentakushibetuzaikototoriyosekanohyouji() {
		return komokusentakushibetuzaikototoriyosekanohyouji;
	}

	public void setKomokusentakushibetuzaikototoriyosekanohyouji(
			String komokusentakushibetuzaikototoriyosekanohyouji) {
		this.komokusentakushibetuzaikototoriyosekanohyouji = komokusentakushibetuzaikototoriyosekanohyouji;
	}

	public String getKomokusentakushibetuzaikoyozaikosu() {
		return komokusentakushibetuzaikoyozaikosu;
	}

	public void setKomokusentakushibetuzaikoyozaikosu(
			String komokusentakushibetuzaikoyozaikosu) {
		this.komokusentakushibetuzaikoyozaikosu = komokusentakushibetuzaikoyozaikosu;
	}

	public String getZaikonodoshifuragu() {
		return zaikonodoshifuragu;
	}

	public void setZaikonodoshifuragu(String zaikonodoshifuragu) {
		this.zaikonodoshifuragu = zaikonodoshifuragu;
	}

	public String getZaikokiretokinochumonuketuke() {
		return zaikokiretokinochumonuketuke;
	}

	public void setZaikokiretokinochumonuketuke(
			String zaikokiretokinochumonuketuke) {
		this.zaikokiretokinochumonuketuke = zaikokiretokinochumonuketuke;
	}

	public String getZaikoaritokinoukikanribango() {
		return zaikoaritokinoukikanribango;
	}

	public void setZaikoaritokinoukikanribango(
			String zaikoaritokinoukikanribango) {
		this.zaikoaritokinoukikanribango = zaikoaritokinoukikanribango;
	}

	public String getZaikokiretokinoukikanribango() {
		return zaikokiretokinoukikanribango;
	}

	public void setZaikokiretokinoukikanribango(
			String zaikokiretokinoukikanribango) {
		this.zaikokiretokinoukikanribango = zaikokiretokinoukikanribango;
	}

	public String getKontororukaramu() {
		return kontororukaramu;
	}

	public void setKontororukaramu(String kontororukaramu) {
		this.kontororukaramu = kontororukaramu;
	}
	
	public String getHanbaikakaku() {
		return hanbaikakaku;
	}

	public void setHanbaikakaku(String hanbaikakaku) {
		this.hanbaikakaku = hanbaikakaku;
	}

	public String getHyojikakaku() {
		return hyojikakaku;
	}

	public void setHyojikakaku(String hyojikakaku) {
		this.hyojikakaku = hyojikakaku;
	}
	
	public String getSouryou() {
		return souryou;
	}

	public void setSouryou(String souryou) {
		this.souryou = souryou;
	}

	public String getKobetusouryou() {
		return kobetusouryou;
	}

	public void setKobetusouryou(String kobetusouryou) {
		this.kobetusouryou = kobetusouryou;
	}

	public String getSouryoukubun1() {
		return souryoukubun1;
	}

	public void setSouryoukubun1(String souryoukubun1) {
		this.souryoukubun1 = souryoukubun1;
	}

	public String getSouryoukubun2() {
		return souryoukubun2;
	}

	public void setSouryoukubun2(String souryoukubun2) {
		this.souryoukubun2 = souryoukubun2;
	}


}
