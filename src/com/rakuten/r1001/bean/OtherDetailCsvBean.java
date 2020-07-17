package com.rakuten.r1001.bean;

import java.io.Serializable;
import java.util.List;

public class OtherDetailCsvBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 配送状態
	// 注文番号
	private String juchubango = null;
	// カート番号
	// 配送会社
	// 送り状番号
	// 発送日
	// 注文日
	// 入金日
	// お届け希望日
	// 発送予定日
	// 配送完了日
	// 配送方法
	// 商品番号
	private String shouhinbango = null;
	// 商品名
	private String shouhinmei = null;
	// 数量
	private String kosu = null;
	// オプション情報
	// オプションコード
	// おまけ
	// 受取人名
	// 受取人名(フリガナ)
	// 受取人電話番号
	// 受取人携帯電話番号
	// 住所
	// 郵便番号
	// 国家
	// 送料の決済
	// 決済サイト
	// 通貨
	// 購入者決済金額
	// 販売価格
	private String tanka = null;
	// 割引額
	// 注文金額の合計
	// 供給原価の合計
	// 購入者名
	// 購入者名(フリガナ)
	// 配送要請事項
	// 購入者電話番号
	// 購入者携帯電話番号
	// 販売者商品コード
	// JANコード
	// 規格番号
	// プレゼント贈り主
	// 外部広告
	// 素材
	private String souryoukomibetsu = null;
	private String zeikomibetsu = null;
	private String komokusentakushi = null;
	private String rekodananba = null;

	public String getShouhinmei() {
		return shouhinmei;
	}

	public void setShouhinmei(String shouhinmei) {
		this.shouhinmei = shouhinmei;
	}

	public String getShouhinbango() {
		return shouhinbango;
	}

	public void setShouhinbango(String shouhinbango) {
		this.shouhinbango = shouhinbango;
	}

	public String getTanka() {
		return tanka;
	}

	public void setTanka(String tanka) {
		this.tanka = tanka;
	}

	public String getKosu() {
		return kosu;
	}

	public void setKosu(String kosu) {
		this.kosu = kosu;
	}

	public String getSouryoukomibetsu() {
		return souryoukomibetsu;
	}

	public void setSouryoukomibetsu(String souryoukomibetsu) {
		this.souryoukomibetsu = souryoukomibetsu;
	}

	public String getZeikomibetsu() {
		return zeikomibetsu;
	}

	public void setZeikomibetsu(String zeikomibetsu) {
		this.zeikomibetsu = zeikomibetsu;
	}

	public String getKomokusentakushi() {
		return komokusentakushi;
	}

	public void setKomokusentakushi(String komokusentakushi) {
		this.komokusentakushi = komokusentakushi;
	}

	public String getRekodananba() {
		return rekodananba;
	}

	public void setRekodananba(String rekodananba) {
		this.rekodananba = rekodananba;
	}

	public String getJuchubango() {
		return juchubango;
	}

	public void setJuchubango(String juchubango) {
		this.juchubango = juchubango;
	}

}
