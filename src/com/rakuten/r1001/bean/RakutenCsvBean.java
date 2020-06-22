package com.rakuten.r1001.bean;

import java.io.Serializable;
import java.util.List;

public class RakutenCsvBean implements Serializable {

	private static final long serialVersionUID = 1L;
	// 詳細リスト
	private List<RakutenDetailCsvBean> shousaiList = null;
	// 受注番号
	private String juchubango = null;
	// 受注ステータス
	private String chumonsutetasu = null;
	// カード決済ステータス
	private String kadokesaisutetasu = null;
	// 入金日
	private String nyukinbi = null;
	// 配送日
	private String haisoubi = null;
	// お届け時間帯
	private String otodokejikantai = null;
	// お届け日指定
	private String otodokebishitei = null;
	// 担当者
	private String tantousha = null;
	// ひとことメモ
	private String hitokotomemo = null;
	// メール差込文(お客様へのメッセージ)
	private String merusashikomibun = null;
	// 初期購入合計金額
	private String shokikonyugokeikingaku = null;
	// 利用端末
	private String riyoutanmatsu = null;
	// メールキャリアコード
	private String merukyariakodo = null;
	// ギフトチェック（0:なし/1:あり）
	private String gifutocheku = null;
	// コメント
	private String komento = null;
	// 注文日時
	private String chumonnichiji = null;
	// 複数送付先フラグ
	private String fukususofusakifuragu = null;
	// 警告表示フラグ
	private String keikokuhyojifuragu = null;
	// 楽天会員フラグ
	private String rakutenkaiinfuragu = null;
	// 合計
	private String gokei = null;
	// 消費税(-99999=無効値)
	private String shohizei = null;
	// 送料(-99999=無効値)
	private String soryou = null;
	// 代引料(-99999=無効値)
	private String daibikiryou = null;
	// 請求金額(-99999=無効値)
	private String seikyukingaku = null;
	// 合計金額(-99999=無効値)
	private String gokeikingaku = null;
	// 同梱ID
	private String dokonId = null;
	// 同梱ステータス
	private String dokonsutetasu = null;
	// 同梱商品合計金額
	private String dokonshouhingokeikingaku = null;
	// 同梱送料合計
	private String dokonsoryougokei = null;
	// 同梱代引料合計
	private String dokondaibikiryougokei = null;
	// 同梱消費税合計
	private String dokonshohizeigokei = null;
	// 同梱請求金額
	private String dokonseikyukingaku = null;
	// 同梱合計金額
	private String dokongokeikingaku = null;
	// 同梱楽天バンク決済振替手数料
	private String dokonrakutenbankukesaifurikaetesuryou = null;
	// 同梱ポイント利用合計
	private String dokonpointoriyougokei = null;
	// メールフラグ
	private String merufuragu = null;
	// 注文日
	private String chumonbi = null;
	// 注文時間
	private String chumonjikan = null;
	// モバイルキャリア決済番号
	private String mobairukyariakesaibango = null;
	// 購入履歴修正可否タイプ
	private String konyurirekishuseikahitaipu = null;
	// 購入履歴修正アイコンフラグ
	private String konyurirekishuseiaikonfuragu = null;
	// 購入履歴修正催促メールフラグ
	private String konyurirekishuseisaisokumerufuragu = null;
	// 送付先一致フラグ
	private String sofusakiichifuragu = null;
	// ポイント利用有無
	private String pointoriyouumu = null;
	// 注文者郵便番号１
	private String chumonshayubinbango1 = null;
	// 注文者郵便番号２
	private String chumonshayubinbango2 = null;
	// 注文者住所：都道府県
	private String chumonshajushotodofuken = null;
	// 注文者住所：都市区
	private String chumonshajushotoshiku = null;
	// 注文者住所：町以降
	private String chumonshajushochoijou = null;
	// 注文者名字
	private String chumonshameiji = null;
	// 注文者名前
	private String chumonshanamae = null;
	// 注文者名字フリガナ
	private String chumonshameijifurigana = null;
	// 注文者名前フリガナ
	private String chumonshanamaefurigana = null;
	// 注文者電話番号１
	private String chumonshadenwabango1 = null;
	// 注文者電話番号２
	private String chumonshadenwabango2 = null;
	// 注文者電話番号３
	private String chumonshadenwabango3 = null;
	// メールアドレス
	private String meruadoresu = null;
	// 注文者性別
	private String chumonshaseibetu = null;
	// 注文者誕生日
	private String chunonshatanjoubi = null;
	// 決済方法
	private String kesaihouhou = null;
	// クレジットカード種類
	private String kurejitokadoshurui = null;
	// クレジットカード番号
	private String kurejitokadobango = null;
	// クレジットカード名義人
	private String kurejitokadomeiginin = null;
	// クレジットカード有効期限
	private String gurejitokadoyukokigen = null;
	// クレジットカード分割選択
	private String kurejitokadobunwarisentaku = null;
	// クレジットカード分割備考
	private String kurejitokadobunraribiko = null;
	// 配送方法
	private String haisouhoho = null;
	// 配送区分
	private String haisokubun = null;
	// ポイント利用額
	private String pointoriyogaku = null;
	// ポイント利用条件
	private String pointoriyoujouken = null;
	// ポイントステータス
	private String pointosutetasu = null;
	// 楽天バンク決済ステータス
	private String rakutenbangkukesaisutetasu = null;
	// 楽天バンク振替手数料負担区分
	private String rakutenbankufurikomitesuryoufutankubun = null;
	// 楽天バンク決済手数料
	private String rakutenbankukesaitesuryou = null;
	// ラッピングタイトル(包装紙)
	private String rabingutaitoruhousou = null;
	// ラッピング名(包装紙)
	private String rapingumeihousou = null;
	// ラッピング料金(包装紙)
	private String rapinguryoukinhousou = null;
	// 税込別(包装紙)
	private String zeikomibetuhousou = null;
	// ラッピングタイトル(リボン)
	private String rapingutaitoruribon = null;
	// ラッピング名(リボン)
	private String rapingumeiribon = null;
	// ラッピング料金(リボン)
	private String rapinguryoukinribobn = null;
	// 税込別(リボン)
	private String zeikomibeturibon = null;
	// 送付先送料
	private String soufusakisourou = null;
	// 送付先代引料
	private String soufusakidaibikiryou = null;
	// 送付先消費税
	private String soufusakishouhizei = null;
	// お荷物伝票番号
	private String onimotudenpyoubango = null;
	// 送付先商品合計金額
	private String soufusakishouhingokeikingaku = null;
	// のし
	private String noshi = null;
	// 送付先郵便番号１
	private String soufusakiyubinbango1 = null;
	// 送付先郵便番号２
	private String soufusakiyubinbango2 = null;
	// 送付先住所：都道府県
	private String soufusakijushotodofuken = null;
	// 送付先住所：都市区
	private String soufusakijushotoshiku = null;
	// 送付先住所：町以降
	private String soufusakijushochoijou = null;
	// 送付先名字
	private String sofusakimeiji = null;
	// 送付先名前
	private String soufusakinamae = null;
	// 送付先名字フリガナ
	private String soufusakimeijifurigana = null;
	// 送付先名前フリガナ
	private String soufusakimeijinamaefurigana = null;
	// 送付先電話番号１
	private String soufusakidenwabango1 = null;
	// 送付先電話番号２
	private String soufusakidenwabango2 = null;
	// 送付先電話番号３
	private String soufusakidenwabango3 = null;
	// あす楽希望
	private String asurakukibou = null;
	// クーポン利用額
	private String kuponriyougaku = null;
	// 店舗発行クーポン利用額
	private String tenpohakoukuponriyougaku = null;
	// 楽天発行クーポン利用額
	private String rakutenhakoukuponriyougaku = null;
	// 同梱注文クーポン利用額
	private String dokonchumonkuponrigougaku = null;
	// 配送会社
	private String haisoukaisha = null;
	// 代引手数料込別
	private String daibikitesuryokomibetsu = null;

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
	 * @return the chumonsutetasu
	 */
	public String getChumonsutetasu() {
		return chumonsutetasu;
	}

	/**
	 * @param chumonsutetasu
	 *            the chumonsutetasu to set
	 */
	public void setChumonsutetasu(String chumonsutetasu) {
		this.chumonsutetasu = chumonsutetasu;
	}

	/**
	 * @return the kadokesaisutetasu
	 */
	public String getKadokesaisutetasu() {
		return kadokesaisutetasu;
	}

	/**
	 * @param kadokesaisutetasu
	 *            the kadokesaisutetasu to set
	 */
	public void setKadokesaisutetasu(String kadokesaisutetasu) {
		this.kadokesaisutetasu = kadokesaisutetasu;
	}

	/**
	 * @return the nyukinbi
	 */
	public String getNyukinbi() {
		return nyukinbi;
	}

	/**
	 * @param nyukinbi
	 *            the nyukinbi to set
	 */
	public void setNyukinbi(String nyukinbi) {
		this.nyukinbi = nyukinbi;
	}

	/**
	 * @return the haisoubi
	 */
	public String getHaisoubi() {
		return haisoubi;
	}

	/**
	 * @param haisoubi
	 *            the haisoubi to set
	 */
	public void setHaisoubi(String haisoubi) {
		this.haisoubi = haisoubi;
	}

	/**
	 * @return the otodokejikantai
	 */
	public String getOtodokejikantai() {
		return otodokejikantai;
	}

	/**
	 * @param otodokejikantai
	 *            the otodokejikantai to set
	 */
	public void setOtodokejikantai(String otodokejikantai) {
		this.otodokejikantai = otodokejikantai;
	}

	/**
	 * @return the otodokebishitei
	 */
	public String getOtodokebishitei() {
		return otodokebishitei;
	}

	/**
	 * @param otodokebishitei
	 *            the otodokebishitei to set
	 */
	public void setOtodokebishitei(String otodokebishitei) {
		this.otodokebishitei = otodokebishitei;
	}

	/**
	 * @return the tantousha
	 */
	public String getTantousha() {
		return tantousha;
	}

	/**
	 * @param tantousha
	 *            the tantousha to set
	 */
	public void setTantousha(String tantousha) {
		this.tantousha = tantousha;
	}

	/**
	 * @return the hitokotomemo
	 */
	public String getHitokotomemo() {
		return hitokotomemo;
	}

	/**
	 * @param hitokotomemo
	 *            the hitokotomemo to set
	 */
	public void setHitokotomemo(String hitokotomemo) {
		this.hitokotomemo = hitokotomemo;
	}

	/**
	 * @return the merusashikomibun
	 */
	public String getMerusashikomibun() {
		return merusashikomibun;
	}

	/**
	 * @param merusashikomibun
	 *            the merusashikomibun to set
	 */
	public void setMerusashikomibun(String merusashikomibun) {
		this.merusashikomibun = merusashikomibun;
	}

	/**
	 * @return the shokikonyugokeikingaku
	 */
	public String getShokikonyugokeikingaku() {
		return shokikonyugokeikingaku;
	}

	/**
	 * @param shokikonyugokeikingaku
	 *            the shokikonyugokeikingaku to set
	 */
	public void setShokikonyugokeikingaku(String shokikonyugokeikingaku) {
		this.shokikonyugokeikingaku = shokikonyugokeikingaku;
	}

	/**
	 * @return the riyoutanmatsu
	 */
	public String getRiyoutanmatsu() {
		return riyoutanmatsu;
	}

	/**
	 * @param riyoutanmatsu
	 *            the riyoutanmatsu to set
	 */
	public void setRiyoutanmatsu(String riyoutanmatsu) {
		this.riyoutanmatsu = riyoutanmatsu;
	}

	/**
	 * @return the merukyariakodo
	 */
	public String getMerukyariakodo() {
		return merukyariakodo;
	}

	/**
	 * @param merukyariakodo
	 *            the merukyariakodo to set
	 */
	public void setMerukyariakodo(String merukyariakodo) {
		this.merukyariakodo = merukyariakodo;
	}

	/**
	 * @return the gifutocheku
	 */
	public String getGifutocheku() {
		return gifutocheku;
	}

	/**
	 * @param gifutocheku
	 *            the gifutocheku to set
	 */
	public void setGifutocheku(String gifutocheku) {
		this.gifutocheku = gifutocheku;
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
	 * @return the fukususofusakifuragu
	 */
	public String getFukususofusakifuragu() {
		return fukususofusakifuragu;
	}

	/**
	 * @param fukususofusakifuragu
	 *            the fukususofusakifuragu to set
	 */
	public void setFukususofusakifuragu(String fukususofusakifuragu) {
		this.fukususofusakifuragu = fukususofusakifuragu;
	}

	/**
	 * @return the keikokuhyojifuragu
	 */
	public String getKeikokuhyojifuragu() {
		return keikokuhyojifuragu;
	}

	/**
	 * @param keikokuhyojifuragu
	 *            the keikokuhyojifuragu to set
	 */
	public void setKeikokuhyojifuragu(String keikokuhyojifuragu) {
		this.keikokuhyojifuragu = keikokuhyojifuragu;
	}

	/**
	 * @return the rakutenkaiinfuragu
	 */
	public String getRakutenkaiinfuragu() {
		return rakutenkaiinfuragu;
	}

	/**
	 * @param rakutenkaiinfuragu
	 *            the rakutenkaiinfuragu to set
	 */
	public void setRakutenkaiinfuragu(String rakutenkaiinfuragu) {
		this.rakutenkaiinfuragu = rakutenkaiinfuragu;
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
	 * @return the seikyukingaku
	 */
	public String getSeikyukingaku() {
		return seikyukingaku;
	}

	/**
	 * @param seikyukingaku
	 *            the seikyukingaku to set
	 */
	public void setSeikyukingaku(String seikyukingaku) {
		this.seikyukingaku = seikyukingaku;
	}

	/**
	 * @return the gokeikingaku
	 */
	public String getGokeikingaku() {
		return gokeikingaku;
	}

	/**
	 * @param gokeikingaku
	 *            the gokeikingaku to set
	 */
	public void setGokeikingaku(String gokeikingaku) {
		this.gokeikingaku = gokeikingaku;
	}

	/**
	 * @return the dokonId
	 */
	public String getDokonId() {
		return dokonId;
	}

	/**
	 * @param dokonId
	 *            the dokonId to set
	 */
	public void setDokonId(String dokonId) {
		this.dokonId = dokonId;
	}

	/**
	 * @return the dokonsutetasu
	 */
	public String getDokonsutetasu() {
		return dokonsutetasu;
	}

	/**
	 * @param dokonsutetasu
	 *            the dokonsutetasu to set
	 */
	public void setDokonsutetasu(String dokonsutetasu) {
		this.dokonsutetasu = dokonsutetasu;
	}

	/**
	 * @return the dokonshouhingokeikingaku
	 */
	public String getDokonshouhingokeikingaku() {
		return dokonshouhingokeikingaku;
	}

	/**
	 * @param dokonshouhingokeikingaku
	 *            the dokonshouhingokeikingaku to set
	 */
	public void setDokonshouhingokeikingaku(String dokonshouhingokeikingaku) {
		this.dokonshouhingokeikingaku = dokonshouhingokeikingaku;
	}

	/**
	 * @return the dokonsoryougokei
	 */
	public String getDokonsoryougokei() {
		return dokonsoryougokei;
	}

	/**
	 * @param dokonsoryougokei
	 *            the dokonsoryougokei to set
	 */
	public void setDokonsoryougokei(String dokonsoryougokei) {
		this.dokonsoryougokei = dokonsoryougokei;
	}

	/**
	 * @return the dokondaibikiryougokei
	 */
	public String getDokondaibikiryougokei() {
		return dokondaibikiryougokei;
	}

	/**
	 * @param dokondaibikiryougokei
	 *            the dokondaibikiryougokei to set
	 */
	public void setDokondaibikiryougokei(String dokondaibikiryougokei) {
		this.dokondaibikiryougokei = dokondaibikiryougokei;
	}

	/**
	 * @return the dokonshohizeigokei
	 */
	public String getDokonshohizeigokei() {
		return dokonshohizeigokei;
	}

	/**
	 * @param dokonshohizeigokei
	 *            the dokonshohizeigokei to set
	 */
	public void setDokonshohizeigokei(String dokonshohizeigokei) {
		this.dokonshohizeigokei = dokonshohizeigokei;
	}

	/**
	 * @return the dokonseikyukingaku
	 */
	public String getDokonseikyukingaku() {
		return dokonseikyukingaku;
	}

	/**
	 * @param dokonseikyukingaku
	 *            the dokonseikyukingaku to set
	 */
	public void setDokonseikyukingaku(String dokonseikyukingaku) {
		this.dokonseikyukingaku = dokonseikyukingaku;
	}

	/**
	 * @return the dokongokeikingaku
	 */
	public String getDokongokeikingaku() {
		return dokongokeikingaku;
	}

	/**
	 * @param dokongokeikingaku
	 *            the dokongokeikingaku to set
	 */
	public void setDokongokeikingaku(String dokongokeikingaku) {
		this.dokongokeikingaku = dokongokeikingaku;
	}

	/**
	 * @return the dokonrakutenbankukesaifurikaetesuryou
	 */
	public String getDokonrakutenbankukesaifurikaetesuryou() {
		return dokonrakutenbankukesaifurikaetesuryou;
	}

	/**
	 * @param dokonrakutenbankukesaifurikaetesuryou
	 *            the dokonrakutenbankukesaifurikaetesuryou to set
	 */
	public void setDokonrakutenbankukesaifurikaetesuryou(
			String dokonrakutenbankukesaifurikaetesuryou) {
		this.dokonrakutenbankukesaifurikaetesuryou = dokonrakutenbankukesaifurikaetesuryou;
	}

	/**
	 * @return the dokonpointoriyougokei
	 */
	public String getDokonpointoriyougokei() {
		return dokonpointoriyougokei;
	}

	/**
	 * @param dokonpointoriyougokei
	 *            the dokonpointoriyougokei to set
	 */
	public void setDokonpointoriyougokei(String dokonpointoriyougokei) {
		this.dokonpointoriyougokei = dokonpointoriyougokei;
	}

	/**
	 * @return the merufuragu
	 */
	public String getMerufuragu() {
		return merufuragu;
	}

	/**
	 * @param merufuragu
	 *            the merufuragu to set
	 */
	public void setMerufuragu(String merufuragu) {
		this.merufuragu = merufuragu;
	}

	/**
	 * @return the chumonbi
	 */
	public String getChumonbi() {
		return chumonbi;
	}

	/**
	 * @param chumonbi
	 *            the chumonbi to set
	 */
	public void setChumonbi(String chumonbi) {
		this.chumonbi = chumonbi;
	}

	/**
	 * @return the chumonjikan
	 */
	public String getChumonjikan() {
		return chumonjikan;
	}

	/**
	 * @param chumonjikan
	 *            the chumonjikan to set
	 */
	public void setChumonjikan(String chumonjikan) {
		this.chumonjikan = chumonjikan;
	}

	/**
	 * @return the mobairukyariakesaibango
	 */
	public String getMobairukyariakesaibango() {
		return mobairukyariakesaibango;
	}

	/**
	 * @param mobairukyariakesaibango
	 *            the mobairukyariakesaibango to set
	 */
	public void setMobairukyariakesaibango(String mobairukyariakesaibango) {
		this.mobairukyariakesaibango = mobairukyariakesaibango;
	}

	/**
	 * @return the konyurirekishuseikahitaipu
	 */
	public String getKonyurirekishuseikahitaipu() {
		return konyurirekishuseikahitaipu;
	}

	/**
	 * @param konyurirekishuseikahitaipu
	 *            the konyurirekishuseikahitaipu to set
	 */
	public void setKonyurirekishuseikahitaipu(String konyurirekishuseikahitaipu) {
		this.konyurirekishuseikahitaipu = konyurirekishuseikahitaipu;
	}

	/**
	 * @return the konyurirekishuseiaikonfuragu
	 */
	public String getKonyurirekishuseiaikonfuragu() {
		return konyurirekishuseiaikonfuragu;
	}

	/**
	 * @param konyurirekishuseiaikonfuragu
	 *            the konyurirekishuseiaikonfuragu to set
	 */
	public void setKonyurirekishuseiaikonfuragu(
			String konyurirekishuseiaikonfuragu) {
		this.konyurirekishuseiaikonfuragu = konyurirekishuseiaikonfuragu;
	}

	/**
	 * @return the konyurirekishuseisaisokumerufuragu
	 */
	public String getKonyurirekishuseisaisokumerufuragu() {
		return konyurirekishuseisaisokumerufuragu;
	}

	/**
	 * @param konyurirekishuseisaisokumerufuragu
	 *            the konyurirekishuseisaisokumerufuragu to set
	 */
	public void setKonyurirekishuseisaisokumerufuragu(
			String konyurirekishuseisaisokumerufuragu) {
		this.konyurirekishuseisaisokumerufuragu = konyurirekishuseisaisokumerufuragu;
	}

	/**
	 * @return the sofusakiichifuragu
	 */
	public String getSofusakiichifuragu() {
		return sofusakiichifuragu;
	}

	/**
	 * @param sofusakiichifuragu
	 *            the sofusakiichifuragu to set
	 */
	public void setSofusakiichifuragu(String sofusakiichifuragu) {
		this.sofusakiichifuragu = sofusakiichifuragu;
	}

	/**
	 * @return the pointoriyouumu
	 */
	public String getPointoriyouumu() {
		return pointoriyouumu;
	}

	/**
	 * @param pointoriyouumu
	 *            the pointoriyouumu to set
	 */
	public void setPointoriyouumu(String pointoriyouumu) {
		this.pointoriyouumu = pointoriyouumu;
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
	 * @return the chumonshajushochoijou
	 */
	public String getChumonshajushochoijou() {
		return chumonshajushochoijou;
	}

	/**
	 * @param chumonshajushochoijou
	 *            the chumonshajushochoijou to set
	 */
	public void setChumonshajushochoijou(String chumonshajushochoijou) {
		this.chumonshajushochoijou = chumonshajushochoijou;
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
	 * @return the chumonshaseibetu
	 */
	public String getChumonshaseibetu() {
		return chumonshaseibetu;
	}

	/**
	 * @param chumonshaseibetu
	 *            the chumonshaseibetu to set
	 */
	public void setChumonshaseibetu(String chumonshaseibetu) {
		this.chumonshaseibetu = chumonshaseibetu;
	}

	/**
	 * @return the chunonshatanjoubi
	 */
	public String getChunonshatanjoubi() {
		return chunonshatanjoubi;
	}

	/**
	 * @param chunonshatanjoubi
	 *            the chunonshatanjoubi to set
	 */
	public void setChunonshatanjoubi(String chunonshatanjoubi) {
		this.chunonshatanjoubi = chunonshatanjoubi;
	}

	/**
	 * @return the kesaihouhou
	 */
	public String getKesaihouhou() {
		return kesaihouhou;
	}

	/**
	 * @param kesaihouhou
	 *            the kesaihouhou to set
	 */
	public void setKesaihouhou(String kesaihouhou) {
		this.kesaihouhou = kesaihouhou;
	}

	/**
	 * @return the kurejitokadoshurui
	 */
	public String getKurejitokadoshurui() {
		return kurejitokadoshurui;
	}

	/**
	 * @param kurejitokadoshurui
	 *            the kurejitokadoshurui to set
	 */
	public void setKurejitokadoshurui(String kurejitokadoshurui) {
		this.kurejitokadoshurui = kurejitokadoshurui;
	}

	/**
	 * @return the kurejitokadobango
	 */
	public String getKurejitokadobango() {
		return kurejitokadobango;
	}

	/**
	 * @param kurejitokadobango
	 *            the kurejitokadobango to set
	 */
	public void setKurejitokadobango(String kurejitokadobango) {
		this.kurejitokadobango = kurejitokadobango;
	}

	/**
	 * @return the kurejitokadomeiginin
	 */
	public String getKurejitokadomeiginin() {
		return kurejitokadomeiginin;
	}

	/**
	 * @param kurejitokadomeiginin
	 *            the kurejitokadomeiginin to set
	 */
	public void setKurejitokadomeiginin(String kurejitokadomeiginin) {
		this.kurejitokadomeiginin = kurejitokadomeiginin;
	}

	/**
	 * @return the gurejitokadoyukokigen
	 */
	public String getGurejitokadoyukokigen() {
		return gurejitokadoyukokigen;
	}

	/**
	 * @param gurejitokadoyukokigen
	 *            the gurejitokadoyukokigen to set
	 */
	public void setGurejitokadoyukokigen(String gurejitokadoyukokigen) {
		this.gurejitokadoyukokigen = gurejitokadoyukokigen;
	}

	/**
	 * @return the kurejitokadobunwarisentaku
	 */
	public String getKurejitokadobunwarisentaku() {
		return kurejitokadobunwarisentaku;
	}

	/**
	 * @param kurejitokadobunwarisentaku
	 *            the kurejitokadobunwarisentaku to set
	 */
	public void setKurejitokadobunwarisentaku(String kurejitokadobunwarisentaku) {
		this.kurejitokadobunwarisentaku = kurejitokadobunwarisentaku;
	}

	/**
	 * @return the kurejitokadobunraribiko
	 */
	public String getKurejitokadobunraribiko() {
		return kurejitokadobunraribiko;
	}

	/**
	 * @param kurejitokadobunraribiko
	 *            the kurejitokadobunraribiko to set
	 */
	public void setKurejitokadobunraribiko(String kurejitokadobunraribiko) {
		this.kurejitokadobunraribiko = kurejitokadobunraribiko;
	}

	/**
	 * @return the haisouhoho
	 */
	public String getHaisouhoho() {
		return haisouhoho;
	}

	/**
	 * @param haisouhoho
	 *            the haisouhoho to set
	 */
	public void setHaisouhoho(String haisouhoho) {
		this.haisouhoho = haisouhoho;
	}

	/**
	 * @return the haisokubun
	 */
	public String getHaisokubun() {
		return haisokubun;
	}

	/**
	 * @param haisokubun
	 *            the haisokubun to set
	 */
	public void setHaisokubun(String haisokubun) {
		this.haisokubun = haisokubun;
	}

	/**
	 * @return the pointoriyogaku
	 */
	public String getPointoriyogaku() {
		return pointoriyogaku;
	}

	/**
	 * @param pointoriyogaku
	 *            the pointoriyogaku to set
	 */
	public void setPointoriyogaku(String pointoriyogaku) {
		this.pointoriyogaku = pointoriyogaku;
	}

	/**
	 * @return the pointoriyoujouken
	 */
	public String getPointoriyoujouken() {
		return pointoriyoujouken;
	}

	/**
	 * @param pointoriyoujouken
	 *            the pointoriyoujouken to set
	 */
	public void setPointoriyoujouken(String pointoriyoujouken) {
		this.pointoriyoujouken = pointoriyoujouken;
	}

	/**
	 * @return the pointosutetasu
	 */
	public String getPointosutetasu() {
		return pointosutetasu;
	}

	/**
	 * @param pointosutetasu
	 *            the pointosutetasu to set
	 */
	public void setPointosutetasu(String pointosutetasu) {
		this.pointosutetasu = pointosutetasu;
	}

	/**
	 * @return the rakutenbangkukesaisutetasu
	 */
	public String getRakutenbangkukesaisutetasu() {
		return rakutenbangkukesaisutetasu;
	}

	/**
	 * @param rakutenbangkukesaisutetasu
	 *            the rakutenbangkukesaisutetasu to set
	 */
	public void setRakutenbangkukesaisutetasu(String rakutenbangkukesaisutetasu) {
		this.rakutenbangkukesaisutetasu = rakutenbangkukesaisutetasu;
	}

	/**
	 * @return the rakutenbankufurikomitesuryoufutankubun
	 */
	public String getRakutenbankufurikomitesuryoufutankubun() {
		return rakutenbankufurikomitesuryoufutankubun;
	}

	/**
	 * @param rakutenbankufurikomitesuryoufutankubun
	 *            the rakutenbankufurikomitesuryoufutankubun to set
	 */
	public void setRakutenbankufurikomitesuryoufutankubun(
			String rakutenbankufurikomitesuryoufutankubun) {
		this.rakutenbankufurikomitesuryoufutankubun = rakutenbankufurikomitesuryoufutankubun;
	}

	/**
	 * @return the rakutenbankukesaitesuryou
	 */
	public String getRakutenbankukesaitesuryou() {
		return rakutenbankukesaitesuryou;
	}

	/**
	 * @param rakutenbankukesaitesuryou
	 *            the rakutenbankukesaitesuryou to set
	 */
	public void setRakutenbankukesaitesuryou(String rakutenbankukesaitesuryou) {
		this.rakutenbankukesaitesuryou = rakutenbankukesaitesuryou;
	}

	/**
	 * @return the rabingutaitoruhousou
	 */
	public String getRabingutaitoruhousou() {
		return rabingutaitoruhousou;
	}

	/**
	 * @param rabingutaitoruhousou
	 *            the rabingutaitoruhousou to set
	 */
	public void setRabingutaitoruhousou(String rabingutaitoruhousou) {
		this.rabingutaitoruhousou = rabingutaitoruhousou;
	}

	/**
	 * @return the rapingumeihousou
	 */
	public String getRapingumeihousou() {
		return rapingumeihousou;
	}

	/**
	 * @param rapingumeihousou
	 *            the rapingumeihousou to set
	 */
	public void setRapingumeihousou(String rapingumeihousou) {
		this.rapingumeihousou = rapingumeihousou;
	}

	/**
	 * @return the rapinguryoukinhousou
	 */
	public String getRapinguryoukinhousou() {
		return rapinguryoukinhousou;
	}

	/**
	 * @param rapinguryoukinhousou
	 *            the rapinguryoukinhousou to set
	 */
	public void setRapinguryoukinhousou(String rapinguryoukinhousou) {
		this.rapinguryoukinhousou = rapinguryoukinhousou;
	}

	/**
	 * @return the zeikomibetuhousou
	 */
	public String getZeikomibetuhousou() {
		return zeikomibetuhousou;
	}

	/**
	 * @param zeikomibetuhousou
	 *            the zeikomibetuhousou to set
	 */
	public void setZeikomibetuhousou(String zeikomibetuhousou) {
		this.zeikomibetuhousou = zeikomibetuhousou;
	}

	/**
	 * @return the rapingutaitoruribon
	 */
	public String getRapingutaitoruribon() {
		return rapingutaitoruribon;
	}

	/**
	 * @param rapingutaitoruribon
	 *            the rapingutaitoruribon to set
	 */
	public void setRapingutaitoruribon(String rapingutaitoruribon) {
		this.rapingutaitoruribon = rapingutaitoruribon;
	}

	/**
	 * @return the rapingumeiribon
	 */
	public String getRapingumeiribon() {
		return rapingumeiribon;
	}

	/**
	 * @param rapingumeiribon
	 *            the rapingumeiribon to set
	 */
	public void setRapingumeiribon(String rapingumeiribon) {
		this.rapingumeiribon = rapingumeiribon;
	}

	/**
	 * @return the rapinguryoukinribobn
	 */
	public String getRapinguryoukinribobn() {
		return rapinguryoukinribobn;
	}

	/**
	 * @param rapinguryoukinribobn
	 *            the rapinguryoukinribobn to set
	 */
	public void setRapinguryoukinribobn(String rapinguryoukinribobn) {
		this.rapinguryoukinribobn = rapinguryoukinribobn;
	}

	/**
	 * @return the zeikomibeturibon
	 */
	public String getZeikomibeturibon() {
		return zeikomibeturibon;
	}

	/**
	 * @param zeikomibeturibon
	 *            the zeikomibeturibon to set
	 */
	public void setZeikomibeturibon(String zeikomibeturibon) {
		this.zeikomibeturibon = zeikomibeturibon;
	}

	/**
	 * @return the soufusakisourou
	 */
	public String getSoufusakisourou() {
		return soufusakisourou;
	}

	/**
	 * @param soufusakisourou
	 *            the soufusakisourou to set
	 */
	public void setSoufusakisourou(String soufusakisourou) {
		this.soufusakisourou = soufusakisourou;
	}

	/**
	 * @return the soufusakidaibikiryou
	 */
	public String getSoufusakidaibikiryou() {
		return soufusakidaibikiryou;
	}

	/**
	 * @param soufusakidaibikiryou
	 *            the soufusakidaibikiryou to set
	 */
	public void setSoufusakidaibikiryou(String soufusakidaibikiryou) {
		this.soufusakidaibikiryou = soufusakidaibikiryou;
	}

	/**
	 * @return the soufusakishouhizei
	 */
	public String getSoufusakishouhizei() {
		return soufusakishouhizei;
	}

	/**
	 * @param soufusakishouhizei
	 *            the soufusakishouhizei to set
	 */
	public void setSoufusakishouhizei(String soufusakishouhizei) {
		this.soufusakishouhizei = soufusakishouhizei;
	}

	/**
	 * @return the onimotudenpyoubango
	 */
	public String getOnimotudenpyoubango() {
		return onimotudenpyoubango;
	}

	/**
	 * @param onimotudenpyoubango
	 *            the onimotudenpyoubango to set
	 */
	public void setOnimotudenpyoubango(String onimotudenpyoubango) {
		this.onimotudenpyoubango = onimotudenpyoubango;
	}

	/**
	 * @return the soufusakishouhingokeikingaku
	 */
	public String getSoufusakishouhingokeikingaku() {
		return soufusakishouhingokeikingaku;
	}

	/**
	 * @param soufusakishouhingokeikingaku
	 *            the soufusakishouhingokeikingaku to set
	 */
	public void setSoufusakishouhingokeikingaku(
			String soufusakishouhingokeikingaku) {
		this.soufusakishouhingokeikingaku = soufusakishouhingokeikingaku;
	}

	/**
	 * @return the noshi
	 */
	public String getNoshi() {
		return noshi;
	}

	/**
	 * @param noshi
	 *            the noshi to set
	 */
	public void setNoshi(String noshi) {
		this.noshi = noshi;
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
	 * @return the soufusakijushochoijou
	 */
	public String getSoufusakijushochoijou() {
		return soufusakijushochoijou;
	}

	/**
	 * @param soufusakijushochoijou
	 *            the soufusakijushochoijou to set
	 */
	public void setSoufusakijushochoijou(String soufusakijushochoijou) {
		this.soufusakijushochoijou = soufusakijushochoijou;
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
	 * @return the asurakukibou
	 */
	public String getAsurakukibou() {
		return asurakukibou;
	}

	/**
	 * @param asurakukibou
	 *            the asurakukibou to set
	 */
	public void setAsurakukibou(String asurakukibou) {
		this.asurakukibou = asurakukibou;
	}

	/**
	 * @return the kuponriyougaku
	 */
	public String getKuponriyougaku() {
		return kuponriyougaku;
	}

	/**
	 * @param kuponriyougaku
	 *            the kuponriyougaku to set
	 */
	public void setKuponriyougaku(String kuponriyougaku) {
		this.kuponriyougaku = kuponriyougaku;
	}

	/**
	 * @return the tenpohakoukuponriyougaku
	 */
	public String getTenpohakoukuponriyougaku() {
		return tenpohakoukuponriyougaku;
	}

	/**
	 * @param tenpohakoukuponriyougaku
	 *            the tenpohakoukuponriyougaku to set
	 */
	public void setTenpohakoukuponriyougaku(String tenpohakoukuponriyougaku) {
		this.tenpohakoukuponriyougaku = tenpohakoukuponriyougaku;
	}

	/**
	 * @return the rakutenhakoukuponriyougaku
	 */
	public String getRakutenhakoukuponriyougaku() {
		return rakutenhakoukuponriyougaku;
	}

	/**
	 * @param rakutenhakoukuponriyougaku
	 *            the rakutenhakoukuponriyougaku to set
	 */
	public void setRakutenhakoukuponriyougaku(String rakutenhakoukuponriyougaku) {
		this.rakutenhakoukuponriyougaku = rakutenhakoukuponriyougaku;
	}

	/**
	 * @return the dokonchumonkuponrigougaku
	 */
	public String getDokonchumonkuponrigougaku() {
		return dokonchumonkuponrigougaku;
	}

	/**
	 * @param dokonchumonkuponrigougaku
	 *            the dokonchumonkuponrigougaku to set
	 */
	public void setDokonchumonkuponrigougaku(String dokonchumonkuponrigougaku) {
		this.dokonchumonkuponrigougaku = dokonchumonkuponrigougaku;
	}

	/**
	 * @return the haisoukaisha
	 */
	public String getHaisoukaisha() {
		return haisoukaisha;
	}

	/**
	 * @param haisoukaisha
	 *            the haisoukaisha to set
	 */
	public void setHaisoukaisha(String haisoukaisha) {
		this.haisoukaisha = haisoukaisha;
	}

	public List<RakutenDetailCsvBean> getShousaiList() {
		return shousaiList;
	}

	public void setShousaiList(List<RakutenDetailCsvBean> shousaiList) {
		this.shousaiList = shousaiList;
	}

	public String getDaibikitesuryokomibetsu() {
		return daibikitesuryokomibetsu;
	}

	public void setDaibikitesuryokomibetsu(String daibikitesuryokomibetsu) {
		this.daibikitesuryokomibetsu = daibikitesuryokomibetsu;
	}

}
