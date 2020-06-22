package com.rakuten.common.bean;

import java.io.Serializable;

public class ShohinkategoriBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// コントロールカラム
	private String kontororukaramu = null;
	// 商品管理番号（商品URL）
	private String shohinkanribango = null;
	// 商品名
	private String shohinmei = null;
	// 表示先カテゴリ
	private String huojisakikategori = null;
	// 優先度
	private String yusendo = null;
	// URL
	private String url = null;
	// 1ページ複数形式
	private String ichipejifukusukeishiki = null;

	public String getShohinkanribango() {
		return shohinkanribango;
	}

	public void setShohinkanribango(String shohinkanribango) {
		this.shohinkanribango = shohinkanribango;
	}

	public String getShohinmei() {
		return shohinmei;
	}

	public void setShohinmei(String shohinmei) {
		this.shohinmei = shohinmei;
	}

	public String getHuojisakikategori() {
		return huojisakikategori;
	}

	public void setHuojisakikategori(String huojisakikategori) {
		this.huojisakikategori = huojisakikategori;
	}

	public String getYusendo() {
		return yusendo;
	}

	public void setYusendo(String yusendo) {
		this.yusendo = yusendo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIchipejifukusukeishiki() {
		return ichipejifukusukeishiki;
	}

	public void setIchipejifukusukeishiki(String ichipejifukusukeishiki) {
		this.ichipejifukusukeishiki = ichipejifukusukeishiki;
	}

	public String getKontororukaramu() {
		return kontororukaramu;
	}

	public void setKontororukaramu(String kontororukaramu) {
		this.kontororukaramu = kontororukaramu;
	}

}
