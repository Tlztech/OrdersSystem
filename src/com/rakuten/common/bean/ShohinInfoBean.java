package com.rakuten.common.bean;

import java.io.Serializable;

public class ShohinInfoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	// コントロールカラム
	private String kontororukaramu = null;
	// 商品管理番号（商品URL）
	private String shouhinkanribango = null;
	// 商品番号
	private String shouhinbango = null;
	// 全商品ディレクトリID
	private String zenshohindirekutoriId = null;
	// タグID
	private String taguId = null;
	// PC用キャッチコピー
	private String pcyokyachikopi = null;
	// モバイル用キャッチコピー
	private String mobairuyokyachikopi = null;
	// 商品名
	private String shouhinmei = null;
	// 販売価格
	private String hanbaikakaku = null;
	// 表示価格
	private String hyojikakaku = null;
	// 消費税
	private String shouhizei = null;
	// 送料
	private String souryou = null;
	// 個別送料
	private String kobetusouryou = null;
	// 送料区分1
	private String souryoukubun1 = null;
	// 送料区分2
	private String souryoukubun2 = null;
	// 代引料
	private String daibikiryou = null;
	// 倉庫指定
	private String sokoshitei = null;
	// 商品情報レイアウト
	private String shouhinjouhoureiaouto = null;
	// 注文ボタン
	private String chumonbotan = null;
	// 資料請求ボタン
	private String shiryosekyubotan = null;
	// 商品問い合わせボタン
	private String shouhintoiawasebotan = null;
	// 再入荷お知らせボタン
	private String sainyukaoshirasebotan = null;
	// モバイル表示
	private String mobairuhyoji = null;
	// のし対応
	private String noshitaiou = null;
	// PC用商品説明文
	private String pcyoushouhinsetumeibun = null;
	// モバイル用商品説明文
	private String mobairuyoushouhinsetumeibun = null;
	// スマートフォン用商品説明文
	private String sumatofonyoushouhinsetumeibun = null;
	// PC用販売説明文
	private String pcyouhanbaisetumeibun = null;
	// 商品画像URL
	private String shouhingazoUrl = null;
	// 商品画像名（ALT）
	private String shouhingazomeiAlt = null;
	// 動画
	private String douga = null;
	// 販売期間指定
	private String hanbaikikanshitei = null;
	// 注文受付数
	private String chumonnuketukesu = null;
	// 在庫タイプ
	private String zaikotaipu = null;
	// 在庫数
	private String zaikusu = null;
	// 在庫数表示
	private String zaikosuhyouji = null;
	// 項目選択肢別在庫用横軸項目名
	private String komokusentakushibetuzaikoyouyokojikukoumokumei = null;
	// 項目選択肢別在庫用縦軸項目名
	private String komokusentakushibetuzaikoyoutatejikukomokumei = null;
	// 項目選択肢別在庫用残り表示閾値
	private String koumokusentakushibetuzaikoyounokorihyoujiikichi = null;
	// RAC番号
	private String racbango = null;
	// サーチ非表示
	private String sachihihyoji = null;
	// 闇市パスワード
	private String yamiichipasuwado = null;
	// カタログID
	private String kataroguId = null;
	// 在庫戻しフラグ
	private String zaikonodoshifuragu = null;
	// 在庫切れ時の注文受付
	private String zaikokiretokinochumonnuketuke = null;
	// 在庫あり時納期管理番号
	private String zaikoaritokinoukikanribango = null;
	// 在庫切れ時納期管理番号
	private String zaikokiretokinoukikanribango = null;
	// 予約商品発売日
	private String yoyakushouhinhanbaibi = null;
	// ポイント変倍率
	private String pointohenbairitu = null;
	// ポイント変倍率適用期間
	private String pointohenbaitekiyoukikan = null;
	// ヘッダー・フッター・レフトナビ
	private String heddafuttarefutonabi = null;
	// 表示項目の並び順
	private String hyoujikomokunonarabijun = null;
	// 共通説明文（小）
	private String kyotusetumeibunsho = null;
	// 目玉商品
	private String medamashouhin = null;
	// 共通説明文（大）
	private String kyoutusetumeibundai = null;
	// レビュー本文表示
	private String rebyuhonbunhyouji = null;
	// あす楽配送管理番号
	private String arurakuhaisoukanribango = null;
	// 海外配送管理番号
	private String kaigaihaisoukanribango = null;
	// サイズ表リンク
	private String saizuhyourinku = null;
	// 医薬品説明文
	private String yypsmw = null;

	// 医薬品注意事項
	private String yypzysx = null;

	// 二重価格文言管理番号

	private String nichokakakubungankanribango = null;

	/**
	 * @return the kontororukaramu
	 */
	public String getKontororukaramu() {
		return kontororukaramu;
	}

	/**
	 * @param kontororukaramu
	 *            the kontororukaramu to set
	 */
	public void setKontororukaramu(String kontororukaramu) {
		this.kontororukaramu = kontororukaramu;
	}

	/**
	 * @return the yypsmw
	 */
	public String getYypsmw() {
		return yypsmw;
	}

	/**
	 * @param yypsmw
	 *            the yypsmw to set
	 */
	public void setYypsmw(String yypsmw) {
		this.yypsmw = yypsmw;
	}

	/**
	 * @return the yypzysx
	 */
	public String getYypzysx() {
		return yypzysx;
	}

	/**
	 * @param yypzysx
	 *            the yypzysx to set
	 */
	public void setYypzysx(String yypzysx) {
		this.yypzysx = yypzysx;
	}

	/**
	 * @return the nichokakakubungankanribango
	 */
	public String getNichokakakubungankanribango() {
		return nichokakakubungankanribango;
	}

	/**
	 * @param nichokakakubungankanribango
	 *            the nichokakakubungankanribango to set
	 */
	public void setNichokakakubungankanribango(
			String nichokakakubungankanribango) {
		this.nichokakakubungankanribango = nichokakakubungankanribango;
	}

	public String getShouhinkanribango() {
		return shouhinkanribango;
	}

	public void setShouhinkanribango(String shouhinkanribango) {
		this.shouhinkanribango = shouhinkanribango;
	}

	public String getShouhinbango() {
		return shouhinbango;
	}

	public void setShouhinbango(String shouhinbango) {
		this.shouhinbango = shouhinbango;
	}

	public String getZenshohindirekutoriId() {
		return zenshohindirekutoriId;
	}

	public void setZenshohindirekutoriId(String zenshohindirekutoriId) {
		this.zenshohindirekutoriId = zenshohindirekutoriId;
	}

	public String getTaguId() {
		return taguId;
	}

	public void setTaguId(String taguId) {
		this.taguId = taguId;
	}

	public String getPcyokyachikopi() {
		return pcyokyachikopi;
	}

	public void setPcyokyachikopi(String pcyokyachikopi) {
		this.pcyokyachikopi = pcyokyachikopi;
	}

	public String getMobairuyokyachikopi() {
		return mobairuyokyachikopi;
	}

	public void setMobairuyokyachikopi(String mobairuyokyachikopi) {
		this.mobairuyokyachikopi = mobairuyokyachikopi;
	}

	public String getShouhinmei() {
		return shouhinmei;
	}

	public void setShouhinmei(String shouhinmei) {
		this.shouhinmei = shouhinmei;
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

	public String getShouhizei() {
		return shouhizei;
	}

	public void setShouhizei(String shouhizei) {
		this.shouhizei = shouhizei;
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

	public String getDaibikiryou() {
		return daibikiryou;
	}

	public void setDaibikiryou(String daibikiryou) {
		this.daibikiryou = daibikiryou;
	}

	public String getSokoshitei() {
		return sokoshitei;
	}

	public void setSokoshitei(String sokoshitei) {
		this.sokoshitei = sokoshitei;
	}

	public String getShouhinjouhoureiaouto() {
		return shouhinjouhoureiaouto;
	}

	public void setShouhinjouhoureiaouto(String shouhinjouhoureiaouto) {
		this.shouhinjouhoureiaouto = shouhinjouhoureiaouto;
	}

	public String getChumonbotan() {
		return chumonbotan;
	}

	public void setChumonbotan(String chumonbotan) {
		this.chumonbotan = chumonbotan;
	}

	public String getShiryosekyubotan() {
		return shiryosekyubotan;
	}

	public void setShiryosekyubotan(String shiryosekyubotan) {
		this.shiryosekyubotan = shiryosekyubotan;
	}

	public String getShouhintoiawasebotan() {
		return shouhintoiawasebotan;
	}

	public void setShouhintoiawasebotan(String shouhintoiawasebotan) {
		this.shouhintoiawasebotan = shouhintoiawasebotan;
	}

	public String getSainyukaoshirasebotan() {
		return sainyukaoshirasebotan;
	}

	public void setSainyukaoshirasebotan(String sainyukaoshirasebotan) {
		this.sainyukaoshirasebotan = sainyukaoshirasebotan;
	}

	public String getMobairuhyoji() {
		return mobairuhyoji;
	}

	public void setMobairuhyoji(String mobairuhyoji) {
		this.mobairuhyoji = mobairuhyoji;
	}

	public String getNoshitaiou() {
		return noshitaiou;
	}

	public void setNoshitaiou(String noshitaiou) {
		this.noshitaiou = noshitaiou;
	}

	public String getPcyoushouhinsetumeibun() {
		return pcyoushouhinsetumeibun;
	}

	public void setPcyoushouhinsetumeibun(String pcyoushouhinsetumeibun) {
		this.pcyoushouhinsetumeibun = pcyoushouhinsetumeibun;
	}

	public String getMobairuyoushouhinsetumeibun() {
		return mobairuyoushouhinsetumeibun;
	}

	public void setMobairuyoushouhinsetumeibun(
			String mobairuyoushouhinsetumeibun) {
		this.mobairuyoushouhinsetumeibun = mobairuyoushouhinsetumeibun;
	}

	public String getSumatofonyoushouhinsetumeibun() {
		return sumatofonyoushouhinsetumeibun;
	}

	public void setSumatofonyoushouhinsetumeibun(
			String sumatofonyoushouhinsetumeibun) {
		this.sumatofonyoushouhinsetumeibun = sumatofonyoushouhinsetumeibun;
	}

	public String getPcyouhanbaisetumeibun() {
		return pcyouhanbaisetumeibun;
	}

	public void setPcyouhanbaisetumeibun(String pcyouhanbaisetumeibun) {
		this.pcyouhanbaisetumeibun = pcyouhanbaisetumeibun;
	}

	public String getShouhingazoUrl() {
		return shouhingazoUrl;
	}

	public void setShouhingazoUrl(String shouhingazoUrl) {
		this.shouhingazoUrl = shouhingazoUrl;
	}

	public String getShouhingazomeiAlt() {
		return shouhingazomeiAlt;
	}

	public void setShouhingazomeiAlt(String shouhingazomeiAlt) {
		this.shouhingazomeiAlt = shouhingazomeiAlt;
	}

	public String getDouga() {
		return douga;
	}

	public void setDouga(String douga) {
		this.douga = douga;
	}

	public String getHanbaikikanshitei() {
		return hanbaikikanshitei;
	}

	public void setHanbaikikanshitei(String hanbaikikanshitei) {
		this.hanbaikikanshitei = hanbaikikanshitei;
	}

	public String getChumonnuketukesu() {
		return chumonnuketukesu;
	}

	public void setChumonnuketukesu(String chumonnuketukesu) {
		this.chumonnuketukesu = chumonnuketukesu;
	}

	public String getZaikotaipu() {
		return zaikotaipu;
	}

	public void setZaikotaipu(String zaikotaipu) {
		this.zaikotaipu = zaikotaipu;
	}

	public String getZaikusu() {
		return zaikusu;
	}

	public void setZaikusu(String zaikusu) {
		this.zaikusu = zaikusu;
	}

	public String getZaikosuhyouji() {
		return zaikosuhyouji;
	}

	public void setZaikosuhyouji(String zaikosuhyouji) {
		this.zaikosuhyouji = zaikosuhyouji;
	}

	public String getKomokusentakushibetuzaikoyouyokojikukoumokumei() {
		return komokusentakushibetuzaikoyouyokojikukoumokumei;
	}

	public void setKomokusentakushibetuzaikoyouyokojikukoumokumei(
			String komokusentakushibetuzaikoyouyokojikukoumokumei) {
		this.komokusentakushibetuzaikoyouyokojikukoumokumei = komokusentakushibetuzaikoyouyokojikukoumokumei;
	}

	public String getKomokusentakushibetuzaikoyoutatejikukomokumei() {
		return komokusentakushibetuzaikoyoutatejikukomokumei;
	}

	public void setKomokusentakushibetuzaikoyoutatejikukomokumei(
			String komokusentakushibetuzaikoyoutatejikukomokumei) {
		this.komokusentakushibetuzaikoyoutatejikukomokumei = komokusentakushibetuzaikoyoutatejikukomokumei;
	}

	public String getKoumokusentakushibetuzaikoyounokorihyoujiikichi() {
		return koumokusentakushibetuzaikoyounokorihyoujiikichi;
	}

	public void setKoumokusentakushibetuzaikoyounokorihyoujiikichi(
			String koumokusentakushibetuzaikoyounokorihyoujiikichi) {
		this.koumokusentakushibetuzaikoyounokorihyoujiikichi = koumokusentakushibetuzaikoyounokorihyoujiikichi;
	}

	public String getRacbango() {
		return racbango;
	}

	public void setRacbango(String racbango) {
		this.racbango = racbango;
	}

	public String getSachihihyoji() {
		return sachihihyoji;
	}

	public void setSachihihyoji(String sachihihyoji) {
		this.sachihihyoji = sachihihyoji;
	}

	public String getYamiichipasuwado() {
		return yamiichipasuwado;
	}

	public void setYamiichipasuwado(String yamiichipasuwado) {
		this.yamiichipasuwado = yamiichipasuwado;
	}

	public String getKataroguId() {
		return kataroguId;
	}

	public void setKataroguId(String kataroguId) {
		this.kataroguId = kataroguId;
	}

	public String getZaikonodoshifuragu() {
		return zaikonodoshifuragu;
	}

	public void setZaikonodoshifuragu(String zaikonodoshifuragu) {
		this.zaikonodoshifuragu = zaikonodoshifuragu;
	}

	public String getZaikokiretokinochumonnuketuke() {
		return zaikokiretokinochumonnuketuke;
	}

	public void setZaikokiretokinochumonnuketuke(
			String zaikokiretokinochumonnuketuke) {
		this.zaikokiretokinochumonnuketuke = zaikokiretokinochumonnuketuke;
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

	public String getYoyakushouhinhanbaibi() {
		return yoyakushouhinhanbaibi;
	}

	public void setYoyakushouhinhanbaibi(String yoyakushouhinhanbaibi) {
		this.yoyakushouhinhanbaibi = yoyakushouhinhanbaibi;
	}

	public String getPointohenbairitu() {
		return pointohenbairitu;
	}

	public void setPointohenbairitu(String pointohenbairitu) {
		this.pointohenbairitu = pointohenbairitu;
	}

	public String getPointohenbaitekiyoukikan() {
		return pointohenbaitekiyoukikan;
	}

	public void setPointohenbaitekiyoukikan(String pointohenbaitekiyoukikan) {
		this.pointohenbaitekiyoukikan = pointohenbaitekiyoukikan;
	}

	public String getHeddafuttarefutonabi() {
		return heddafuttarefutonabi;
	}

	public void setHeddafuttarefutonabi(String heddafuttarefutonabi) {
		this.heddafuttarefutonabi = heddafuttarefutonabi;
	}

	public String getHyoujikomokunonarabijun() {
		return hyoujikomokunonarabijun;
	}

	public void setHyoujikomokunonarabijun(String hyoujikomokunonarabijun) {
		this.hyoujikomokunonarabijun = hyoujikomokunonarabijun;
	}

	public String getKyotusetumeibunsho() {
		return kyotusetumeibunsho;
	}

	public void setKyotusetumeibunsho(String kyotusetumeibunsho) {
		this.kyotusetumeibunsho = kyotusetumeibunsho;
	}

	public String getMedamashouhin() {
		return medamashouhin;
	}

	public void setMedamashouhin(String medamashouhin) {
		this.medamashouhin = medamashouhin;
	}

	public String getKyoutusetumeibundai() {
		return kyoutusetumeibundai;
	}

	public void setKyoutusetumeibundai(String kyoutusetumeibundai) {
		this.kyoutusetumeibundai = kyoutusetumeibundai;
	}

	public String getRebyuhonbunhyouji() {
		return rebyuhonbunhyouji;
	}

	public void setRebyuhonbunhyouji(String rebyuhonbunhyouji) {
		this.rebyuhonbunhyouji = rebyuhonbunhyouji;
	}

	public String getArurakuhaisoukanribango() {
		return arurakuhaisoukanribango;
	}

	public void setArurakuhaisoukanribango(String arurakuhaisoukanribango) {
		this.arurakuhaisoukanribango = arurakuhaisoukanribango;
	}

	public String getKaigaihaisoukanribango() {
		return kaigaihaisoukanribango;
	}

	public void setKaigaihaisoukanribango(String kaigaihaisoukanribango) {
		this.kaigaihaisoukanribango = kaigaihaisoukanribango;
	}

	public String getSaizuhyourinku() {
		return saizuhyourinku;
	}

	public void setSaizuhyourinku(String saizuhyourinku) {
		this.saizuhyourinku = saizuhyourinku;
	}

}
