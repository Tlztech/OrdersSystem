package com.rakuten.r1001.common;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.r1001.bean.DenaCsvBean;
import com.rakuten.r1001.bean.DenaDetailCsvBean;
import com.rakuten.r1001.bean.OtherCsvBean;
import com.rakuten.r1001.bean.OtherDetailCsvBean;
import com.rakuten.r1001.bean.PonpareCsvBean;
import com.rakuten.r1001.bean.PonpareDetailCsvBean;
import com.rakuten.r1001.bean.Qoo10CsvBean;
import com.rakuten.r1001.bean.Qoo10DetailCsvBean;
import com.rakuten.r1001.bean.RakutenCsvBean;
import com.rakuten.r1001.bean.RakutenDetailCsvBean;
import com.rakuten.r1001.form.DokonList;
import com.rakuten.r1001.form.F100101;
import com.rakuten.r1001.form.F100102;
import com.rakuten.r1001.form.F100103;
import com.rakuten.r1001.form.OrderList;
import com.rakuten.r1001.form.ShohinList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;
import com.rakuten.util.Utility;

public class A1001Common {
	private String shop = "";

	public List<RakutenCsvBean> getOrderListFromCsv(File csvFile) throws Exception {
		// 从CSV文件获取订单信息
		List<String[]> csvList = Utility.readCsvFile(csvFile, true);
		// 返回的订单列表
		List<RakutenCsvBean> orderList = new ArrayList<RakutenCsvBean>();

		List<String[]> orderInfo = new ArrayList<String[]>();
		for (int i = 0; i < csvList.size(); i++) {
			if (i == 170) {
				System.out.print("");
			}
			String[] csvData = csvList.get(i);
			String[] csvDataNext = null;
			if (i + 1 < csvList.size()) {
				csvDataNext = csvList.get(i + 1);
			}
			String chumonbango = csvData[0];
			String chumonbangoNext = "";
			if (csvDataNext != null) {
				chumonbangoNext = csvDataNext[0];
			}
			orderInfo.add(csvData);
			if (!chumonbango.equals(chumonbangoNext) || "".equals(chumonbangoNext)) {
				AddtoOrderList(orderList, orderInfo);
				orderInfo = new ArrayList<String[]>();
			}
		}

		return orderList;

	}

	private void AddtoOrderList(List<RakutenCsvBean> orderList, List<String[]> orderInfo) {
		RakutenCsvBean rakutenCsvBean = new RakutenCsvBean();
		List<RakutenDetailCsvBean> shousaiList = new ArrayList<RakutenDetailCsvBean>();
		rakutenCsvBean.setShousaiList(shousaiList);
		orderList.add(rakutenCsvBean);

		RakutenDetailCsvBean detailCsvBean = null;
		for (int i = 0; i < orderInfo.size(); i++) {
			detailCsvBean = new RakutenDetailCsvBean();
			shousaiList.add(detailCsvBean);
			String[] order = orderInfo.get(i);
			int j = 99;
			// 商品ID
			detailCsvBean.setShouhinId(order[++j]);
			// 商品名
			detailCsvBean.setShouhinmei(order[++j]);
			// 商品番号
			detailCsvBean.setShouhinbango(order[++j]);
			// 商品URL
			detailCsvBean.setShouhinURL(order[++j]);
			// 単価
			detailCsvBean.setTanka(order[++j]);
			// 個数
			detailCsvBean.setKosu(order[++j]);
			// 送料込別
			detailCsvBean.setSouryoukomibetsu(order[++j]);
			// 税込別
			detailCsvBean.setZeikomibetsu(order[++j]);
			// 代引手数料込別
			detailCsvBean.setDaibikitesuryoukomibetsu(order[++j]);
			// 項目・選択肢
			detailCsvBean.setKomokusentakushi(order[++j]);
			// ポイント倍率
			detailCsvBean.setPointobairitsu(order[++j]);
			// ポイントタイプ
			detailCsvBean.setPointotaipu(order[++j]);
			// レコードナンバー
			detailCsvBean.setRekodananba(order[++j]);
			// 納期情報
			detailCsvBean.setNokijouho(order[++j]);
			// 在庫タイプ
			detailCsvBean.setZaikotaipu(order[++j]);
			// ラッピング種類(包装紙)
			detailCsvBean.setRapingushuruihousou(order[++j]);
			// ラッピング種類(リボン)
			detailCsvBean.setRapingushuruiribon(order[++j]);
		}
		int j = -1;
		// 受注番号
		rakutenCsvBean.setJuchubango(orderInfo.get(0)[++j]);
		// 受注ステータス
		rakutenCsvBean.setChumonsutetasu(orderInfo.get(0)[++j]);
		// カード決済ステータス
		rakutenCsvBean.setKadokesaisutetasu(orderInfo.get(0)[++j]);
		// 入金日
		rakutenCsvBean.setNyukinbi(orderInfo.get(0)[++j]);
		// 配送日
		rakutenCsvBean.setHaisoubi(orderInfo.get(0)[++j]);
		// お届け時間帯
		rakutenCsvBean.setOtodokejikantai(orderInfo.get(0)[++j]);
		// お届け日指定
		rakutenCsvBean.setOtodokebishitei(orderInfo.get(0)[++j]);
		// 担当者
		rakutenCsvBean.setTantousha(orderInfo.get(0)[++j]);
		// ひとことメモ
		rakutenCsvBean.setHitokotomemo(orderInfo.get(0)[++j]);
		// メール差込文(お客様へのメッセージ)
		rakutenCsvBean.setMerusashikomibun(orderInfo.get(0)[++j]);
		// 初期購入合計金額
		rakutenCsvBean.setShokikonyugokeikingaku(orderInfo.get(0)[++j]);
		// 利用端末
		rakutenCsvBean.setRiyoutanmatsu(orderInfo.get(0)[++j]);
		// メールキャリアコード
		rakutenCsvBean.setMerukyariakodo(orderInfo.get(0)[++j]);
		// ギフトチェック（0:なし/1:あり）
		rakutenCsvBean.setGifutocheku(orderInfo.get(0)[++j]);
		// コメント
		rakutenCsvBean.setKomento(orderInfo.get(0)[++j]);
		// 注文日時
		rakutenCsvBean.setChumonnichiji(orderInfo.get(0)[++j]);
		// 複数送付先フラグ
		rakutenCsvBean.setFukususofusakifuragu(orderInfo.get(0)[++j]);
		// 警告表示フラグ
		rakutenCsvBean.setKeikokuhyojifuragu(orderInfo.get(0)[++j]);
		// 楽天会員フラグ
		rakutenCsvBean.setRakutenkaiinfuragu(orderInfo.get(0)[++j]);
		// 合計
		rakutenCsvBean.setGokei(orderInfo.get(0)[++j]);
		// 消費税(-99999=無効値)
		rakutenCsvBean.setShohizei(orderInfo.get(0)[++j]);
		// 送料(-99999=無効値)
		rakutenCsvBean.setSoryou(orderInfo.get(0)[++j]);
		// 代引料(-99999=無効値)
		rakutenCsvBean.setDaibikiryou(orderInfo.get(0)[++j]);
		// 請求金額(-99999=無効値)
		rakutenCsvBean.setSeikyukingaku(orderInfo.get(0)[++j]);
		// 合計金額(-99999=無効値)
		rakutenCsvBean.setGokeikingaku(orderInfo.get(0)[++j]);
		// 同梱ID
		rakutenCsvBean.setDokonId(orderInfo.get(0)[++j]);
		// 同梱ステータス
		rakutenCsvBean.setDokonsutetasu(orderInfo.get(0)[++j]);
		// 同梱商品合計金額
		rakutenCsvBean.setDokonshouhingokeikingaku(orderInfo.get(0)[++j]);
		// 同梱送料合計
		rakutenCsvBean.setDokonsoryougokei(orderInfo.get(0)[++j]);
		// 同梱代引料合計
		rakutenCsvBean.setDokondaibikiryougokei(orderInfo.get(0)[++j]);
		// 同梱消費税合計
		rakutenCsvBean.setDokonshohizeigokei(orderInfo.get(0)[++j]);
		// 同梱請求金額
		rakutenCsvBean.setDokonseikyukingaku(orderInfo.get(0)[++j]);
		// 同梱合計金額
		rakutenCsvBean.setDokongokeikingaku(orderInfo.get(0)[++j]);
		// 同梱楽天バンク決済振替手数料
		rakutenCsvBean.setDokonrakutenbankukesaifurikaetesuryou(orderInfo.get(0)[++j]);
		// 同梱ポイント利用合計
		rakutenCsvBean.setDokonpointoriyougokei(orderInfo.get(0)[++j]);
		// メールフラグ
		rakutenCsvBean.setMerufuragu(orderInfo.get(0)[++j]);
		// 注文日
		rakutenCsvBean.setChumonbi(orderInfo.get(0)[++j]);
		// 注文時間
		rakutenCsvBean.setChumonjikan(orderInfo.get(0)[++j]);
		// モバイルキャリア決済番号
		rakutenCsvBean.setMobairukyariakesaibango(orderInfo.get(0)[++j]);
		// 購入履歴修正可否タイプ
		rakutenCsvBean.setKonyurirekishuseikahitaipu(orderInfo.get(0)[++j]);
		// 購入履歴修正アイコンフラグ
		rakutenCsvBean.setKonyurirekishuseiaikonfuragu(orderInfo.get(0)[++j]);
		// 購入履歴修正催促メールフラグ
		rakutenCsvBean.setKonyurirekishuseisaisokumerufuragu(orderInfo.get(0)[++j]);
		// 送付先一致フラグ
		rakutenCsvBean.setSofusakiichifuragu(orderInfo.get(0)[++j]);
		// ポイント利用有無
		rakutenCsvBean.setPointoriyouumu(orderInfo.get(0)[++j]);
		// 注文者郵便番号１
		rakutenCsvBean.setChumonshayubinbango1(orderInfo.get(0)[++j]);
		// 注文者郵便番号２
		rakutenCsvBean.setChumonshayubinbango2(orderInfo.get(0)[++j]);
		// 注文者住所：都道府県
		rakutenCsvBean.setChumonshajushotodofuken(orderInfo.get(0)[++j]);
		// 注文者住所：都市区
		rakutenCsvBean.setChumonshajushotoshiku(orderInfo.get(0)[++j]);
		// 注文者住所：町以降
		rakutenCsvBean.setChumonshajushochoijou(orderInfo.get(0)[++j]);
		// 注文者名字
		rakutenCsvBean.setChumonshameiji(orderInfo.get(0)[++j]);
		// 注文者名前
		rakutenCsvBean.setChumonshanamae(orderInfo.get(0)[++j]);
		// 注文者名字フリガナ
		rakutenCsvBean.setChumonshameijifurigana(orderInfo.get(0)[++j]);
		// 注文者名前フリガナ
		rakutenCsvBean.setChumonshanamaefurigana(orderInfo.get(0)[++j]);
		// 注文者電話番号１
		rakutenCsvBean.setChumonshadenwabango1(orderInfo.get(0)[++j]);
		// 注文者電話番号２
		rakutenCsvBean.setChumonshadenwabango2(orderInfo.get(0)[++j]);
		// 注文者電話番号３
		rakutenCsvBean.setChumonshadenwabango3(orderInfo.get(0)[++j]);
		// メールアドレス
		rakutenCsvBean.setMeruadoresu(orderInfo.get(0)[++j]);
		// 注文者性別
		rakutenCsvBean.setChumonshaseibetu(orderInfo.get(0)[++j]);
		// 注文者誕生日
		rakutenCsvBean.setChunonshatanjoubi(orderInfo.get(0)[++j]);
		// 決済方法
		rakutenCsvBean.setKesaihouhou(orderInfo.get(0)[++j]);
		// クレジットカード種類
		rakutenCsvBean.setKurejitokadoshurui(orderInfo.get(0)[++j]);
		// クレジットカード番号
		rakutenCsvBean.setKurejitokadobango(orderInfo.get(0)[++j]);
		// クレジットカード名義人
		rakutenCsvBean.setKurejitokadomeiginin(orderInfo.get(0)[++j]);
		// クレジットカード有効期限
		rakutenCsvBean.setGurejitokadoyukokigen(orderInfo.get(0)[++j]);
		// クレジットカード分割選択
		rakutenCsvBean.setKurejitokadobunwarisentaku(orderInfo.get(0)[++j]);
		// クレジットカード分割備考
		rakutenCsvBean.setKurejitokadobunraribiko(orderInfo.get(0)[++j]);
		// 配送方法
		rakutenCsvBean.setHaisouhoho(orderInfo.get(0)[++j]);
		// 配送区分
		rakutenCsvBean.setHaisokubun(orderInfo.get(0)[++j]);
		// ポイント利用額
		rakutenCsvBean.setPointoriyogaku(orderInfo.get(0)[++j]);
		// ポイント利用条件
		rakutenCsvBean.setPointoriyoujouken(orderInfo.get(0)[++j]);
		// ポイントステータス
		rakutenCsvBean.setPointosutetasu(orderInfo.get(0)[++j]);
		// 楽天バンク決済ステータス
		rakutenCsvBean.setRakutenbangkukesaisutetasu(orderInfo.get(0)[++j]);
		// 楽天バンク振替手数料負担区分
		rakutenCsvBean.setRakutenbankufurikomitesuryoufutankubun(orderInfo.get(0)[++j]);
		// 楽天バンク決済手数料
		rakutenCsvBean.setRakutenbankukesaitesuryou(orderInfo.get(0)[++j]);
		// ラッピングタイトル(包装紙)
		rakutenCsvBean.setRabingutaitoruhousou(orderInfo.get(0)[++j]);
		// ラッピング名(包装紙)
		rakutenCsvBean.setRapingumeihousou(orderInfo.get(0)[++j]);
		// ラッピング料金(包装紙)
		rakutenCsvBean.setRapinguryoukinhousou(orderInfo.get(0)[++j]);
		// 税込別(包装紙)
		rakutenCsvBean.setZeikomibetuhousou(orderInfo.get(0)[++j]);
		// ラッピングタイトル(リボン)
		rakutenCsvBean.setRapingutaitoruribon(orderInfo.get(0)[++j]);
		// ラッピング名(リボン)
		rakutenCsvBean.setRapingumeiribon(orderInfo.get(0)[++j]);
		// ラッピング料金(リボン)
		rakutenCsvBean.setRapinguryoukinribobn(orderInfo.get(0)[++j]);
		// 税込別(リボン)
		rakutenCsvBean.setZeikomibeturibon(orderInfo.get(0)[++j]);
		// 送付先送料
		rakutenCsvBean.setSoufusakisourou(orderInfo.get(0)[++j]);
		// 送付先代引料
		rakutenCsvBean.setSoufusakidaibikiryou(orderInfo.get(0)[++j]);
		// 送付先消費税
		rakutenCsvBean.setSoufusakishouhizei(orderInfo.get(0)[++j]);
		// お荷物伝票番号
		rakutenCsvBean.setOnimotudenpyoubango(orderInfo.get(0)[++j]);
		// 送付先商品合計金額
		rakutenCsvBean.setSoufusakishouhingokeikingaku(orderInfo.get(0)[++j]);
		// のし
		rakutenCsvBean.setNoshi(orderInfo.get(0)[++j]);
		// 送付先郵便番号１
		rakutenCsvBean.setSoufusakiyubinbango1(orderInfo.get(0)[++j]);
		// 送付先郵便番号２
		rakutenCsvBean.setSoufusakiyubinbango2(orderInfo.get(0)[++j]);
		// 送付先住所：都道府県
		rakutenCsvBean.setSoufusakijushotodofuken(orderInfo.get(0)[++j]);
		// 送付先住所：都市区
		rakutenCsvBean.setSoufusakijushotoshiku(orderInfo.get(0)[++j]);
		// 送付先住所：町以降
		rakutenCsvBean.setSoufusakijushochoijou(orderInfo.get(0)[++j]);
		// 送付先名字
		rakutenCsvBean.setSofusakimeiji(orderInfo.get(0)[++j]);
		// 送付先名前
		rakutenCsvBean.setSoufusakinamae(orderInfo.get(0)[++j]);
		// 送付先名字フリガナ
		rakutenCsvBean.setSoufusakimeijifurigana(orderInfo.get(0)[++j]);
		// 送付先名前フリガナ
		rakutenCsvBean.setSoufusakimeijinamaefurigana(orderInfo.get(0)[++j]);
		// 送付先電話番号１
		rakutenCsvBean.setSoufusakidenwabango1(orderInfo.get(0)[++j]);
		// 送付先電話番号２
		rakutenCsvBean.setSoufusakidenwabango2(orderInfo.get(0)[++j]);
		// 送付先電話番号３
		rakutenCsvBean.setSoufusakidenwabango3(orderInfo.get(0)[++j]);
		j = 116;
		// あす楽希望
		rakutenCsvBean.setAsurakukibou(orderInfo.get(0)[++j]);
		// クーポン利用額
		rakutenCsvBean.setKuponriyougaku(orderInfo.get(0)[++j]);
		// 店舗発行クーポン利用額
		rakutenCsvBean.setTenpohakoukuponriyougaku(orderInfo.get(0)[++j]);
		// 楽天発行クーポン利用額
		rakutenCsvBean.setRakutenhakoukuponriyougaku(orderInfo.get(0)[++j]);
		// 同梱注文クーポン利用額
		rakutenCsvBean.setDokonchumonkuponrigougaku(orderInfo.get(0)[++j]);
		// 配送会社
		rakutenCsvBean.setHaisoukaisha(orderInfo.get(0)[++j]);

	}

	private void AddtoOrderListDena(List<DenaCsvBean> orderList, List<String[]> orderInfo) {
		DenaCsvBean denaCsvBean = new DenaCsvBean();
		List<DenaDetailCsvBean> shousaiList = new ArrayList<DenaDetailCsvBean>();
		denaCsvBean.setShousaiList(shousaiList);
		orderList.add(denaCsvBean);

		DenaDetailCsvBean detailCsvBean = null;
		for (int i = 0; i < orderInfo.size(); i++) {
			detailCsvBean = new DenaDetailCsvBean();
			shousaiList.add(detailCsvBean);
			String[] order = orderInfo.get(i);
			// 商品名
			detailCsvBean.setShouhinmei(order[3]);
			// 商品番号
			if (order[1].startsWith("cpctx001-")) {
				order[1] = order[1].replace("-T", "");
				order[1] = order[1].replace("-S-M", "-S");
				order[1] = order[1].replace("-M-M", "-M");
				order[1] = order[1].replace("-L-M", "-L");
				order[1] = order[1].replace("-XL-M", "-XL");
			}
			if (order[1].startsWith("salecpctx001-")) {
				order[1] = order[1].substring(0, order[1].length() - 2);
			}
			if (order[1].startsWith("sale")) {
				detailCsvBean.setShouhinbango(order[1].replace("sale", ""));
			} else {
				detailCsvBean.setShouhinbango(order[1]);
			}
			// 単価
			detailCsvBean.setTanka(order[4]);
			// 個数
			detailCsvBean.setKosu(order[5]);
			String soryo = order[30];
			if ("0".equals(soryo)) {
				// 送料込別
				detailCsvBean.setSouryoukomibetsu("込");
			} else {
				// 送料込別
				detailCsvBean.setSouryoukomibetsu("別");
			}
			String zei = order[28];
			if ("0".equals(zei)) {
				// 税込別
				detailCsvBean.setZeikomibetsu("込");
			} else {
				// 税込別
				detailCsvBean.setZeikomibetsu("別");
			}

			String tesuryo = order[29];
			if ("0".equals(tesuryo)) {
				// 代引手数料込別
				detailCsvBean.setDaibikitesuryoukomibetsu("込");
			} else {
				// 代引手数料込別
				detailCsvBean.setDaibikitesuryoukomibetsu("別");
			}
			// 項目・選択肢
			detailCsvBean.setKomokusentakushi(order[45]);
			// ポイント倍率
			detailCsvBean.setPointobairitsu("1");
			// レコードナンバー
			detailCsvBean.setRekodananba(order[2]);
		}
		// 受注番号
		denaCsvBean.setJuchubango(orderInfo.get(0)[0]);

		// コメント
		denaCsvBean.setKomento(orderInfo.get(0)[16]);
		// 注文日時
		denaCsvBean.setChumonnichiji(orderInfo.get(0)[6].replace("/", "-") + ":00");

		// 合計
		denaCsvBean.setGokei(orderInfo.get(0)[41]);
		// 消費税(-99999=無効値)
		denaCsvBean.setShohizei(orderInfo.get(0)[28]);
		// 送料(-99999=無効値)
		denaCsvBean.setSoryou(orderInfo.get(0)[30]);
		// 代引料(-99999=無効値)
		denaCsvBean.setDaibikiryou(orderInfo.get(0)[29]);
		// 請求金額(-99999=無効値)
		denaCsvBean.setSeikyukingaku(orderInfo.get(0)[31]);

		String chumonshajusho = orderInfo.get(0)[13];
		String[] chumonshajushoArr = chumonshajusho.split(" ");
		String chumonshayubinbango = chumonshajushoArr[0];
		String chumonshayubinbango1 = "";
		String chumonshayubinbango2 = "";
		if (chumonshayubinbango.contains("-")) {
			chumonshayubinbango1 = chumonshayubinbango.split("-")[0];
			chumonshayubinbango2 = chumonshayubinbango.split("-")[1];
		} else {
			chumonshayubinbango1 = chumonshayubinbango;
		}
		// 注文者郵便番号１
		denaCsvBean.setChumonshayubinbango1(chumonshayubinbango1);
		// 注文者郵便番号２
		denaCsvBean.setChumonshayubinbango2(chumonshayubinbango2);

		// 注文者住所：都道府県
		denaCsvBean.setChumonshajushotodofuken(chumonshajushoArr[1]);
		// 注文者住所：都市区
		String jusho = "";
		for (int i = 2; i < chumonshajushoArr.length; i++) {
			jusho = jusho + chumonshajushoArr[i] + " ";
		}
		denaCsvBean.setChumonshajushotoshiku(jusho);
		String chumonsha = orderInfo.get(0)[12];
		String chumonshameiji = "";
		String chumonshanamae = "";
		if (chumonsha.contains(" ")) {
			chumonshameiji = chumonsha.split(" ")[0];
			chumonshanamae = chumonsha.split(" ")[1];
		} else {
			chumonshameiji = chumonsha;
		}
		// 注文者名字
		denaCsvBean.setChumonshameiji(chumonshameiji);
		// 注文者名前
		denaCsvBean.setChumonshanamae(chumonshanamae);

		String chumonshakana = orderInfo.get(0)[36];
		String chumonshameijikana = "";
		String chumonshanamaekana = "";
		if (chumonsha.contains(" ")) {
			chumonshameijikana = chumonshakana.split(" ")[0];
			chumonshanamaekana = chumonshakana.split(" ")[1];
		} else {
			chumonshameijikana = chumonshakana;
		}
		// 注文者名字フリガナ
		denaCsvBean.setChumonshameijifurigana(chumonshameijikana);
		// 注文者名前フリガナ
		denaCsvBean.setChumonshanamaefurigana(chumonshanamaekana);

		String chumonshadenwabango = orderInfo.get(0)[14];
		String chumonshadenwabango1 = "";
		String chumonshadenwabango2 = "";
		String chumonshadenwabango3 = "";
		if (chumonshadenwabango.contains("-")) {
			chumonshadenwabango1 = chumonshadenwabango.split("-")[0];
			chumonshadenwabango2 = chumonshadenwabango.split("-")[1];
			if (chumonshadenwabango.split("-").length > 2) {
				chumonshadenwabango3 = chumonshadenwabango.split("-")[2];
			}

		}
		// 注文者電話番号１
		denaCsvBean.setChumonshadenwabango1(chumonshadenwabango1);
		// 注文者電話番号２
		denaCsvBean.setChumonshadenwabango2(chumonshadenwabango2);
		// 注文者電話番号３
		denaCsvBean.setChumonshadenwabango3(chumonshadenwabango3);
		// メールアドレス
		denaCsvBean.setMeruadoresu(orderInfo.get(0)[8]);

		// 決済方法
		denaCsvBean.setKesaihouhou(orderInfo.get(0)[15]);

		// ポイント利用額
		String point = String.valueOf(Integer.valueOf(orderInfo.get(0)[57]) + Integer.valueOf(orderInfo.get(0)[72]));
		denaCsvBean.setPointoriyogaku(point);

		String sofusakijusho = orderInfo.get(0)[34];
		String[] sofusakijushoArr = sofusakijusho.split(" ");
		String sofusakiyubinbango = sofusakijushoArr[0];
		String sofusakiyubinbango1 = "";
		String sofusakiyubinbango2 = "";
		if (sofusakiyubinbango.contains("-")) {
			sofusakiyubinbango1 = sofusakiyubinbango.split("-")[0];
			sofusakiyubinbango2 = sofusakiyubinbango.split("-")[1];
		} else {
			sofusakiyubinbango1 = sofusakiyubinbango;
		}

		// 送付先郵便番号１
		denaCsvBean.setSoufusakiyubinbango1(sofusakiyubinbango1);
		// 送付先郵便番号２
		denaCsvBean.setSoufusakiyubinbango2(sofusakiyubinbango2);
		// 送付先住所：都道府県
		denaCsvBean.setSoufusakijushotodofuken(sofusakijushoArr[1]);
		// 送付先住所：都市区
		String jusho2 = "";
		for (int i = 2; i < sofusakijushoArr.length; i++) {
			jusho2 = jusho2 + sofusakijushoArr[i] + " ";
		}
		denaCsvBean.setSoufusakijushotoshiku(jusho2);

		String sofusaki = orderInfo.get(0)[33];
		String sofusakimeiji = "";
		String sofusakinamae = "";
		if (sofusaki.contains(" ")) {
			sofusakimeiji = sofusaki.split(" ")[0];
			sofusakinamae = sofusaki.split(" ")[1];
		} else {
			sofusakimeiji = sofusaki;
		}
		// 送付先名字
		denaCsvBean.setSofusakimeiji(sofusakimeiji);
		// 送付先名前
		denaCsvBean.setSoufusakinamae(sofusakinamae);

		String sofusakikana = orderInfo.get(0)[39];
		String sofusakimeijikana = "";
		String sofusakinamaekana = "";
		if (sofusaki.contains(" ")) {
			sofusakimeijikana = sofusakikana.split(" ")[0];
			sofusakinamaekana = sofusakikana.split(" ")[1];
		} else {
			sofusakimeijikana = sofusakikana;
		}

		// 送付先名字フリガナ
		denaCsvBean.setSoufusakimeijifurigana(sofusakimeijikana);
		// 送付先名前フリガナ
		denaCsvBean.setSoufusakimeijinamaefurigana(sofusakinamaekana);

		String sofusakidenwabango = orderInfo.get(0)[35];
		String sofusakidenwabango1 = "";
		String sofusakidenwabango2 = "";
		String sofusakidenwabango3 = "";
		if (sofusakidenwabango.contains("-")) {
			sofusakidenwabango1 = sofusakidenwabango.split("-")[0];
			sofusakidenwabango2 = sofusakidenwabango.split("-")[1];
			if (sofusakidenwabango.split("-").length > 2) {
				sofusakidenwabango3 = sofusakidenwabango.split("-")[2];
			}

		}
		// 送付先電話番号１
		denaCsvBean.setSoufusakidenwabango1(sofusakidenwabango1);
		// 送付先電話番号２
		denaCsvBean.setSoufusakidenwabango2(sofusakidenwabango2);
		// 送付先電話番号３
		denaCsvBean.setSoufusakidenwabango3(sofusakidenwabango3);

	}

	private void AddtoOrderListQoo10(List<Qoo10CsvBean> orderList, List<String[]> orderInfo) {
		Qoo10CsvBean qoo10CsvBean = new Qoo10CsvBean();
		List<Qoo10DetailCsvBean> shousaiList = new ArrayList<Qoo10DetailCsvBean>();
		qoo10CsvBean.setShousaiList(shousaiList);
		orderList.add(qoo10CsvBean);

		Qoo10DetailCsvBean detailCsvBean = null;
		Long gokei = 0L;
		for (int i = 0; i < orderInfo.size(); i++) {
			detailCsvBean = new Qoo10DetailCsvBean();

			shousaiList.add(detailCsvBean);

			String[] order = orderInfo.get(i);
			// 商品名
			detailCsvBean.setShouhinmei(order[13]);
			// 商品番号
			detailCsvBean.setShouhinbango((order[38] + order[16]).replace("'", ""));
			// 単価
			detailCsvBean.setTanka(order[29].replace(",", ""));
			// 個数
			detailCsvBean.setKosu(order[14]);
			if (Integer.valueOf(31) - Integer.valueOf(29) == Integer.valueOf(29) * Integer.valueOf(14)) {
				// 送料込別
				detailCsvBean.setSouryoukomibetsu("込");
			} else {
				// 送料込別
				detailCsvBean.setSouryoukomibetsu("別");
			}

			gokei += Long.valueOf(order[31].replace(",", ""));
			detailCsvBean.setZeikomibetsu("別");

			// 項目・選択肢
			detailCsvBean.setKomokusentakushi(order[15]);
			// レコードナンバー
			detailCsvBean.setRekodananba(order[1]);
		}
		// 受注番号
		qoo10CsvBean.setJuchubango(orderInfo.get(0)[2]);
		qoo10CsvBean.setHasoyakusokubi(orderInfo.get(0)[9].replace("/", "-").replace("  ", " "));
		// コメント
		qoo10CsvBean.setKomento(orderInfo.get(0)[35]);
		// 注文日時
		qoo10CsvBean.setChumonnichiji(orderInfo.get(0)[6].replace("/", "-").replace("  ", " "));

		// 合計

		qoo10CsvBean.setGokei(String.valueOf(gokei));
		// 消費税(-99999=無効値)
		qoo10CsvBean.setShohizei("0");
		// 送料(-99999=無効値)
		qoo10CsvBean.setSoryou("0");
		// 代引料(-99999=無効値)
		qoo10CsvBean.setDaibikiryou("0");
		// 請求金額(-99999=無効値)
		qoo10CsvBean.setSeikyukingaku(String.valueOf(gokei));

		String chumonshajusho = "";
//		String[] chumonshajushoArr = chumonshajusho.split(" ");
//		String chumonshayubinbango = chumonshajushoArr[0];
//		String chumonshayubinbango1 = "";
//		String chumonshayubinbango2 = "";
//		if (chumonshayubinbango.contains("-")) {
//			chumonshayubinbango1 = chumonshayubinbango.split("-")[0];
//			chumonshayubinbango2 = chumonshayubinbango.split("-")[1];
//		} else {
//			chumonshayubinbango1 = chumonshayubinbango;
//		}
		// 注文者郵便番号１
		qoo10CsvBean.setChumonshayubinbango1(chumonshajusho);
		// 注文者郵便番号２
		qoo10CsvBean.setChumonshayubinbango2(chumonshajusho);

		// 注文者住所：都道府県
		qoo10CsvBean.setChumonshajushotodofuken(chumonshajusho);
		// 注文者住所：都市区
		String jusho = "";
		qoo10CsvBean.setChumonshajushotoshiku(jusho);
		String chumonsha = orderInfo.get(0)[33];
		String chumonshameiji = "";
		String chumonshanamae = "";
		if (chumonsha.contains(" ")) {
			chumonshameiji = chumonsha.split(" ")[0];
			chumonshanamae = chumonsha.split(" ")[1];
		} else {
			chumonshameiji = chumonsha;
		}
		// 注文者名字
		qoo10CsvBean.setChumonshameiji(chumonshameiji);
		// 注文者名前
		qoo10CsvBean.setChumonshanamae(chumonshanamae);

		String chumonshakana = orderInfo.get(0)[34];
		String chumonshameijikana = "";
		String chumonshanamaekana = "";
		if (!Utility.isEmptyString(chumonshakana) && chumonshakana.contains(" ")) {
			chumonshameijikana = chumonshakana.split(" ")[0];
			chumonshanamaekana = chumonshakana.split(" ")[1];
		} else {
			chumonshameijikana = chumonshakana;
		}
		// 注文者名字フリガナ
		qoo10CsvBean.setChumonshameijifurigana(chumonshameijikana);
		// 注文者名前フリガナ
		qoo10CsvBean.setChumonshanamaefurigana(chumonshanamaekana);
		String chumonshadenwabango = "";
		if (!"-".equals(orderInfo.get(0)[36])) {
			chumonshadenwabango = orderInfo.get(0)[36];
		} else {
			chumonshadenwabango = orderInfo.get(0)[37];
		}

		String chumonshadenwabango1 = "";
		String chumonshadenwabango2 = "";
		String chumonshadenwabango3 = "";
		if (chumonshadenwabango.contains("-") && chumonshadenwabango.length() > 2) {
			chumonshadenwabango1 = chumonshadenwabango.split("-")[0];
			chumonshadenwabango2 = chumonshadenwabango.split("-")[1];
			if (chumonshadenwabango.split("-").length > 2) {
				chumonshadenwabango3 = chumonshadenwabango.split("-")[2];
			}

		} else {
			chumonshadenwabango1 = chumonshadenwabango;
		}
		// 注文者電話番号１
		qoo10CsvBean.setChumonshadenwabango1(chumonshadenwabango1);
		// 注文者電話番号２
		qoo10CsvBean.setChumonshadenwabango2(chumonshadenwabango2);
		// 注文者電話番号３
		qoo10CsvBean.setChumonshadenwabango3(chumonshadenwabango3);
		// メールアドレス
		qoo10CsvBean.setMeruadoresu("");

		// 決済方法
		qoo10CsvBean.setKesaihouhou("");

		// ポイント利用額

		qoo10CsvBean.setPointoriyogaku(orderInfo.get(0)[30].replace(",", ""));

		String sofusakijusho = Utility.strTrim(orderInfo.get(0)[22]);
		String todofuken = "";
		String tokushi = "";
		String jusho3 = "";
		String sofusakiyubinbango = orderInfo.get(0)[23].replace("'", "");
		String sofusakiyubinbango1 = "";
		String sofusakiyubinbango2 = "";
		if (sofusakiyubinbango.contains("-")) {
			sofusakiyubinbango1 = sofusakiyubinbango.split("-")[0];
			sofusakiyubinbango2 = sofusakiyubinbango.split("-")[1];
		} else {
			sofusakiyubinbango1 = sofusakiyubinbango;
		}
		if (sofusakijusho.contains("県")) {
			todofuken = sofusakijusho.split("県")[0] + "県";
			if (sofusakijusho.split("県")[1].contains("市")) {
				tokushi = sofusakijusho.split("県")[1].split("市")[0] + "市";
				jusho3 = sofusakijusho.split("県")[1].split("市")[1];
			} else {
				todofuken = sofusakijusho;
			}
		}

		else if (sofusakijusho.contains("京都府")) {
			todofuken = sofusakijusho.split("京都府")[0] + "京都府";
			if (sofusakijusho.split("京都府")[1].contains("市")) {
				tokushi = sofusakijusho.split("京都府")[1].split("市")[0] + "市";
				jusho3 = sofusakijusho.split("京都府")[1].split("市")[1];
			} else {
				todofuken = sofusakijusho;
			}
		} else if (sofusakijusho.contains("北海道")) {
			todofuken = sofusakijusho.split("北海道")[0] + "北海道";
			if (sofusakijusho.split("北海道")[1].contains("市")) {
				tokushi = sofusakijusho.split("北海道")[1].split("市")[0] + "市";
				jusho3 = sofusakijusho.split("北海道")[1].split("市")[1];
			} else {
				todofuken = sofusakijusho;
			}
		} else if (sofusakijusho.contains("大阪府")) {
			todofuken = sofusakijusho.split("大阪府")[0] + "大阪府";
			if (sofusakijusho.split("大阪府")[1].contains("市")) {
				tokushi = sofusakijusho.split("大阪府")[1].split("市")[0] + "市";
				jusho3 = sofusakijusho.split("大阪府")[1].split("市")[1];
			} else {
				todofuken = sofusakijusho;
			}
		} else {
			todofuken = sofusakijusho;
		}
		// 送付先郵便番号１
		qoo10CsvBean.setSoufusakiyubinbango1(sofusakiyubinbango1);
		// 送付先郵便番号２
		qoo10CsvBean.setSoufusakiyubinbango2(sofusakiyubinbango2);
		// 送付先住所：都道府県
		qoo10CsvBean.setSoufusakijushotodofuken(todofuken);
		// 送付先住所：都市区
		qoo10CsvBean.setSoufusakijushotoshiku(tokushi);
		qoo10CsvBean.setSoufusakijusho3(jusho3);

		String sofusaki = orderInfo.get(0)[18];
		String sofusakimeiji = "";
		String sofusakinamae = "";
		if (sofusaki.contains(" ")) {
			sofusakimeiji = sofusaki.split(" ")[0];
			sofusakinamae = sofusaki.split(" ")[1];
		} else {
			sofusakimeiji = sofusaki;
		}
		// 送付先名字
		qoo10CsvBean.setSofusakimeiji(sofusakimeiji);
		// 送付先名前
		qoo10CsvBean.setSoufusakinamae(sofusakinamae);

		String sofusakikana = orderInfo.get(0)[19];
		String sofusakimeijikana = "";
		String sofusakinamaekana = "";
		if (!Utility.isEmptyString(sofusakikana) && sofusakikana.contains(" ")) {
			sofusakimeijikana = sofusakikana.split(" ")[0];
			sofusakinamaekana = sofusakikana.split(" ")[1];
		} else {
			sofusakimeijikana = sofusakikana;
		}

		// 送付先名字フリガナ
		qoo10CsvBean.setSoufusakimeijifurigana(sofusakimeijikana);
		// 送付先名前フリガナ
		qoo10CsvBean.setSoufusakimeijinamaefurigana(sofusakinamaekana);
		String soufusadenwabango = "";
		if (!"-".equals(orderInfo.get(0)[20])) {
			soufusadenwabango = orderInfo.get(0)[20];
		} else if (!"-".equals(orderInfo.get(0)[21])) {
			soufusadenwabango = orderInfo.get(0)[21];
		} else if (!"-".equals(orderInfo.get(0)[37])) {
			soufusadenwabango = orderInfo.get(0)[37];
		} else {
			soufusadenwabango = orderInfo.get(0)[36];
		}
		String soufusadenwabango1 = "";
		String soufusadenwabango2 = "";
		String soufusadenwabango3 = "";
		if (soufusadenwabango.contains("-") && soufusadenwabango.length() > 2) {
			soufusadenwabango1 = soufusadenwabango.split("-")[0];
			soufusadenwabango2 = soufusadenwabango.split("-")[1];
			if (soufusadenwabango.split("-").length > 2) {
				soufusadenwabango3 = soufusadenwabango.split("-")[2];
			}
		} else {
			soufusadenwabango1 = soufusadenwabango;
		}
		// 送付先電話番号１
		qoo10CsvBean.setSoufusakidenwabango1(soufusadenwabango1);
		// 送付先電話番号２
		qoo10CsvBean.setSoufusakidenwabango2(soufusadenwabango2);
		// 送付先電話番号３
		qoo10CsvBean.setSoufusakidenwabango3(soufusadenwabango3);

	}

	private void AddtoOrderListPonpare(List<PonpareCsvBean> orderList, List<String[]> orderInfo) throws Exception {
		PonpareCsvBean ponpareCsvBean = new PonpareCsvBean();
		List<PonpareDetailCsvBean> shousaiList = new ArrayList<PonpareDetailCsvBean>();
		ponpareCsvBean.setShousaiList(shousaiList);
		orderList.add(ponpareCsvBean);

		PonpareDetailCsvBean detailCsvBean = null;
		for (int i = 0; i < orderInfo.size(); i++) {
			detailCsvBean = new PonpareDetailCsvBean();
			shousaiList.add(detailCsvBean);
			String[] order = orderInfo.get(i);
			// 商品名
			detailCsvBean.setShouhinmei(Utility.getShohinmei(order[25]));
			// 商品番号
			detailCsvBean.setShouhinbango(order[26].replace("--", "-"));
			// 単価
			detailCsvBean.setTanka(order[30]);
			// 個数
			detailCsvBean.setKosu(order[29]);
			String soryo = order[32];
			if ("別".equals(soryo)) {
				// 送料込別
				detailCsvBean.setSouryoukomibetsu("別");
			} else {
				// 送料込別
				detailCsvBean.setSouryoukomibetsu("込");
			}
			String zei = order[36];
			if ("込".equals(zei)) {
				// 税込別
				detailCsvBean.setZeikomibetsu("込");
			} else {
				// 税込別
				detailCsvBean.setZeikomibetsu("別");
			}

			String tesuryo = order[33];
			if ("別".equals(tesuryo)) {
				// 代引手数料込別
				detailCsvBean.setDaibikitesuryoukomibetsu("別");
			} else {
				// 代引手数料込別
				detailCsvBean.setDaibikitesuryoukomibetsu("込");
			}
			// 項目・選択肢
			detailCsvBean.setKomokusentakushi(order[31]);
			// ポイント倍率
			detailCsvBean.setPointobairitsu(order[79]);
			// レコードナンバー
			// detailCsvBean.setRekodananba(order[2]);
		}
		// 受注番号
		ponpareCsvBean.setJuchubango(orderInfo.get(0)[0]);

		// コメント
		// ponpareCsvBean.setKomento(orderInfo.get(0)[16]);
		// 注文日時
		ponpareCsvBean.setChumonnichiji(orderInfo.get(0)[1].replace("/", "-"));

		// 合計
		ponpareCsvBean.setGokei(orderInfo.get(0)[48]);
		// 消費税(-99999=無効値)
		ponpareCsvBean.setShohizei(orderInfo.get(0)[46]);
		// 送料(-99999=無効値)
		ponpareCsvBean.setSoryou(orderInfo.get(0)[45]);
		// 代引料(-99999=無効値)
		ponpareCsvBean.setDaibikiryou(orderInfo.get(0)[47]);
		// 請求金額(-99999=無効値)
		ponpareCsvBean.setSeikyukingaku(orderInfo.get(0)[51]);

		// 注文者郵便番号１
		ponpareCsvBean.setChumonshayubinbango1(orderInfo.get(0)[7]);
		// 注文者郵便番号２
		ponpareCsvBean.setChumonshayubinbango2(orderInfo.get(0)[8]);

		// 注文者住所：都道府県
		ponpareCsvBean.setChumonshajushotodofuken(orderInfo.get(0)[9]);
		// 注文者住所：都市区
		ponpareCsvBean.setChumonshajushotoshiku(orderInfo.get(0)[10]);

		// 注文者名字
		ponpareCsvBean.setChumonshameiji(orderInfo.get(0)[3]);
		// 注文者名前
		ponpareCsvBean.setChumonshanamae(orderInfo.get(0)[4]);
		// 注文者名字フリガナ
		ponpareCsvBean.setChumonshameijifurigana(orderInfo.get(0)[5]);
		// 注文者名前フリガナ
		ponpareCsvBean.setChumonshanamaefurigana(orderInfo.get(0)[6]);

		// 注文者電話番号１
		ponpareCsvBean.setChumonshadenwabango1(orderInfo.get(0)[11]);
		// 注文者電話番号２
		ponpareCsvBean.setChumonshadenwabango2("");
		// 注文者電話番号３
		ponpareCsvBean.setChumonshadenwabango3("");
		// メールアドレス
		ponpareCsvBean.setMeruadoresu("");

		// 決済方法
		ponpareCsvBean.setKesaihouhou(orderInfo.get(0)[53]);

		// ポイント利用額

		ponpareCsvBean.setPointoriyogaku(orderInfo.get(0)[50]);

		// 送付先郵便番号１
		ponpareCsvBean.setSoufusakiyubinbango1(orderInfo.get(0)[19]);
		// 送付先郵便番号２
		ponpareCsvBean.setSoufusakiyubinbango2(orderInfo.get(0)[20]);
		// 送付先住所：都道府県
		ponpareCsvBean.setSoufusakijushotodofuken(orderInfo.get(0)[21]);
		// 送付先住所：都市区
		ponpareCsvBean.setSoufusakijushotoshiku(orderInfo.get(0)[22]);

		// 送付先名字
		ponpareCsvBean.setSofusakimeiji(orderInfo.get(0)[15]);
		// 送付先名前
		ponpareCsvBean.setSoufusakinamae(orderInfo.get(0)[16]);

		// 送付先名字フリガナ
		ponpareCsvBean.setSoufusakimeijifurigana(orderInfo.get(0)[17]);
		// 送付先名前フリガナ
		ponpareCsvBean.setSoufusakimeijinamaefurigana(orderInfo.get(0)[18]);

		// 送付先電話番号１
		ponpareCsvBean.setSoufusakidenwabango1(orderInfo.get(0)[23]);
		// 送付先電話番号２
		ponpareCsvBean.setSoufusakidenwabango2("");
		// 送付先電話番号３
		ponpareCsvBean.setSoufusakidenwabango3("");

	}

	private void AddtoOrderListOther(List<OtherCsvBean> orderList, List<String[]> orderInfo) {
		OtherCsvBean otherCsvBean = new OtherCsvBean();
		List<OtherDetailCsvBean> shousaiList = new ArrayList<OtherDetailCsvBean>();
		otherCsvBean.setShousaiList(shousaiList);
		orderList.add(otherCsvBean);

		OtherDetailCsvBean detailCsvBean = null;
		Long gokei = 1L;
		for (int i = 0; i < orderInfo.size(); i++) {
			detailCsvBean = new OtherDetailCsvBean();

			shousaiList.add(detailCsvBean);

			String[] order = orderInfo.get(i);
			// 商品名
			detailCsvBean.setShouhinmei(order[5]);
			// 商品番号
			detailCsvBean.setShouhinbango(order[4]);
			// 単価
			detailCsvBean.setTanka("1");
			// 個数
			detailCsvBean.setKosu(order[6]);
			// 送料込別
			detailCsvBean.setSouryoukomibetsu("込");
			
			detailCsvBean.setZeikomibetsu("別");

			// 項目・選択肢
			detailCsvBean.setKomokusentakushi("");
			// レコードナンバー
			detailCsvBean.setRekodananba(order[3]);
		}
		// 受注番号
		otherCsvBean.setJuchubango(orderInfo.get(0)[3]);
		
		otherCsvBean.setPlatform(orderInfo.get(0)[1]);
		
		otherCsvBean.setShopname(orderInfo.get(0)[2]);
		
		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		otherCsvBean.setHasoyakusokubi(sdf.format(now));
		// コメント
		otherCsvBean.setKomento(orderInfo.get(0)[15]);
		
		// 注文日時
		SimpleDateFormat sdft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		otherCsvBean.setChumonnichiji(sdft.format(now));

		otherCsvBean.setGokei(String.valueOf(gokei));
		// 消費税(-99999=無効値)
		otherCsvBean.setShohizei("0");
		// 送料(-99999=無効値)
		otherCsvBean.setSoryou("0");
		// 代引料(-99999=無効値)
		otherCsvBean.setDaibikiryou("0");
		// 請求金額(-99999=無効値)
		otherCsvBean.setSeikyukingaku(String.valueOf(gokei));

//		String chumonshajusho = "";
//		String[] chumonshajushoArr = chumonshajusho.split(" ");
//		String chumonshayubinbango = chumonshajushoArr[0];
//		String chumonshayubinbango1 = "";
//		String chumonshayubinbango2 = "";
//		if (chumonshayubinbango.contains("-")) {
//			chumonshayubinbango1 = chumonshayubinbango.split("-")[0];
//			chumonshayubinbango2 = chumonshayubinbango.split("-")[1];
//		} else {
//			chumonshayubinbango1 = chumonshayubinbango;
//		}
		String chumonshayubinbango1 = orderInfo.get(0)[8].substring(0, orderInfo.get(0)[8].indexOf("-"));
		String chumonshayubinbango2 = orderInfo.get(0)[8].substring(orderInfo.get(0)[8].indexOf("-") + 1);
		// 注文者郵便番号１
		otherCsvBean.setChumonshayubinbango1(chumonshayubinbango1);
		// 注文者郵便番号２
		otherCsvBean.setChumonshayubinbango2(chumonshayubinbango2);

		// 注文者住所：都道府県
		otherCsvBean.setChumonshajushotodofuken(orderInfo.get(0)[9]);
		// 注文者住所：都市区
		otherCsvBean.setChumonshajushotoshiku(orderInfo.get(0)[10]);
		
		String chumonsha = orderInfo.get(0)[7];
		String chumonshameiji = "";
		String chumonshanamae = "";
		if (chumonsha.contains(" ")) {
			chumonshameiji = chumonsha.split(" ")[0];
			chumonshanamae = chumonsha.split(" ")[1];
		} else {
			chumonshameiji = chumonsha;
		}
		// 注文者名字
		otherCsvBean.setChumonshameiji(chumonshameiji);
		// 注文者名前
		otherCsvBean.setChumonshanamae(chumonshanamae);

//		String chumonshakana = orderInfo.get(0)[34];
		String chumonshameijikana = "";
		String chumonshanamaekana = "";
//		if (!Utility.isEmptyString(chumonshakana) && chumonshakana.contains(" ")) {
//			chumonshameijikana = chumonshakana.split(" ")[0];
//			chumonshanamaekana = chumonshakana.split(" ")[1];
//		} else {
//			chumonshameijikana = chumonshakana;
//		}
		// 注文者名字フリガナ
		otherCsvBean.setChumonshameijifurigana(chumonshameijikana);
		// 注文者名前フリガナ
		otherCsvBean.setChumonshanamaefurigana(chumonshanamaekana);
		
		otherCsvBean.setMeruadoresu(orderInfo.get(0)[3] + "@abc.co.jp");
		otherCsvBean.setChumonshatanjoubi("1900年1月1日");
		
		String chumonshadenwabango = orderInfo.get(0)[11];
		
		String chumonshadenwabango1 = "";
		String chumonshadenwabango2 = "";
		String chumonshadenwabango3 = "";
		if (chumonshadenwabango.contains("-") && chumonshadenwabango.length() > 2) {
			chumonshadenwabango1 = chumonshadenwabango.split("-")[0];
			chumonshadenwabango2 = chumonshadenwabango.split("-")[1];
			if (chumonshadenwabango.split("-").length > 2) {
				chumonshadenwabango3 = chumonshadenwabango.split("-")[2];
			}

		} else {
			chumonshadenwabango1 = chumonshadenwabango;
		}
		// 注文者電話番号１
		otherCsvBean.setChumonshadenwabango1(chumonshadenwabango1);
		// 注文者電話番号２
		otherCsvBean.setChumonshadenwabango2(chumonshadenwabango2);
		// 注文者電話番号３
		otherCsvBean.setChumonshadenwabango3(chumonshadenwabango3);

		// 決済方法
		otherCsvBean.setKesaihouhou("その他");

		// ポイント利用額

		otherCsvBean.setPointoriyogaku("0");

//		String sofusakijusho = Utility.strTrim(orderInfo.get(0)[22]);
//		String todofuken = "";
//		String tokushi = "";
//		String jusho3 = "";
//		String sofusakiyubinbango = orderInfo.get(0)[23].replace("'", "");
//		String sofusakiyubinbango1 = "";
//		String sofusakiyubinbango2 = "";
//		if (sofusakiyubinbango.contains("-")) {
//			sofusakiyubinbango1 = sofusakiyubinbango.split("-")[0];
//			sofusakiyubinbango2 = sofusakiyubinbango.split("-")[1];
//		} else {
//			sofusakiyubinbango1 = sofusakiyubinbango;
//		}
//		if (sofusakijusho.contains("県")) {
//			todofuken = sofusakijusho.split("県")[0] + "県";
//			if (sofusakijusho.split("県")[1].contains("市")) {
//				tokushi = sofusakijusho.split("県")[1].split("市")[0] + "市";
//				jusho3 = sofusakijusho.split("県")[1].split("市")[1];
//			} else {
//				todofuken = sofusakijusho;
//			}
//		}
//
//		else if (sofusakijusho.contains("京都府")) {
//			todofuken = sofusakijusho.split("京都府")[0] + "京都府";
//			if (sofusakijusho.split("京都府")[1].contains("市")) {
//				tokushi = sofusakijusho.split("京都府")[1].split("市")[0] + "市";
//				jusho3 = sofusakijusho.split("京都府")[1].split("市")[1];
//			} else {
//				todofuken = sofusakijusho;
//			}
//		} else if (sofusakijusho.contains("北海道")) {
//			todofuken = sofusakijusho.split("北海道")[0] + "北海道";
//			if (sofusakijusho.split("北海道")[1].contains("市")) {
//				tokushi = sofusakijusho.split("北海道")[1].split("市")[0] + "市";
//				jusho3 = sofusakijusho.split("北海道")[1].split("市")[1];
//			} else {
//				todofuken = sofusakijusho;
//			}
//		} else if (sofusakijusho.contains("大阪府")) {
//			todofuken = sofusakijusho.split("大阪府")[0] + "大阪府";
//			if (sofusakijusho.split("大阪府")[1].contains("市")) {
//				tokushi = sofusakijusho.split("大阪府")[1].split("市")[0] + "市";
//				jusho3 = sofusakijusho.split("大阪府")[1].split("市")[1];
//			} else {
//				todofuken = sofusakijusho;
//			}
//		} else {
//			todofuken = sofusakijusho;
//		}
		// 送付先郵便番号１
		otherCsvBean.setSoufusakiyubinbango1(chumonshayubinbango1);
		// 送付先郵便番号２
		otherCsvBean.setSoufusakiyubinbango2(chumonshayubinbango2);
		// 送付先住所：都道府県
		otherCsvBean.setSoufusakijushotodofuken(orderInfo.get(0)[9]);
		// 送付先住所：都市区
		otherCsvBean.setSoufusakijushotoshiku(orderInfo.get(0)[10]);
		otherCsvBean.setSoufusakijusho3("");

//		String sofusaki = orderInfo.get(0)[18];
//		String sofusakimeiji = "";
//		String sofusakinamae = "";
//		if (sofusaki.contains(" ")) {
//			sofusakimeiji = sofusaki.split(" ")[0];
//			sofusakinamae = sofusaki.split(" ")[1];
//		} else {
//			sofusakimeiji = sofusaki;
//		}
		// 送付先名字
		otherCsvBean.setSofusakimeiji(chumonshameiji);
		// 送付先名前
		otherCsvBean.setSoufusakinamae(chumonshanamae);

//		String sofusakikana = orderInfo.get(0)[19];
//		String sofusakimeijikana = "";
//		String sofusakinamaekana = "";
//		if (!Utility.isEmptyString(sofusakikana) && sofusakikana.contains(" ")) {
//			sofusakimeijikana = sofusakikana.split(" ")[0];
//			sofusakinamaekana = sofusakikana.split(" ")[1];
//		} else {
//			sofusakimeijikana = sofusakikana;
//		}

		// 送付先名字フリガナ
		otherCsvBean.setSoufusakimeijifurigana(chumonshameijikana);
		// 送付先名前フリガナ
		otherCsvBean.setSoufusakimeijinamaefurigana(chumonshanamaekana);
//		String soufusadenwabango = "";
//		if (!"-".equals(orderInfo.get(0)[20])) {
//			soufusadenwabango = orderInfo.get(0)[20];
//		} else if (!"-".equals(orderInfo.get(0)[21])) {
//			soufusadenwabango = orderInfo.get(0)[21];
//		} else if (!"-".equals(orderInfo.get(0)[37])) {
//			soufusadenwabango = orderInfo.get(0)[37];
//		} else {
//			soufusadenwabango = orderInfo.get(0)[36];
//		}
//		String soufusadenwabango1 = "";
//		String soufusadenwabango2 = "";
//		String soufusadenwabango3 = "";
//		if (soufusadenwabango.contains("-") && soufusadenwabango.length() > 2) {
//			soufusadenwabango1 = soufusadenwabango.split("-")[0];
//			soufusadenwabango2 = soufusadenwabango.split("-")[1];
//			if (soufusadenwabango.split("-").length > 2) {
//				soufusadenwabango3 = soufusadenwabango.split("-")[2];
//			}
//		} else {
//			soufusadenwabango1 = soufusadenwabango;
//		}
		// 送付先電話番号１
		otherCsvBean.setSoufusakidenwabango1(chumonshadenwabango1);
		// 送付先電話番号２
		otherCsvBean.setSoufusakidenwabango2(chumonshadenwabango2);
		// 送付先電話番号３
		otherCsvBean.setSoufusakidenwabango3(chumonshadenwabango3);

	}

	
	public void insertIntoRakutenOrderTbl(List<RakutenCsvBean> orderList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT SHOP_ID, SHOP_NO FROM rakuten.shop";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Map<String, String> shopMap = new HashMap<String, String>();
			while (rs.next()) {
				shopMap.put(rs.getString("SHOP_NO"), rs.getString("SHOP_ID"));
			}
			rs.close();
			ps.close();
			
			for (int i = 0; i < orderList.size(); i++) {

				int j = 0;
				boolean donkonFlg = false;
				boolean donkonOyaFlg = false;
				try {
					if (!"0".equals(orderList.get(i).getDokonId())) {
						donkonFlg = true;
						if ("1".equals(orderList.get(i).getDokonsutetasu())) {
							donkonOyaFlg = true;
						}
					}

					j = 0;
					sql = "INSERT INTO common_order_tbl VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(++j, orderList.get(i).getJuchubango());
					ps.setString(++j, orderList.get(i).getChumonnichiji());
					ps.setString(++j, "0");// 未入金
					if (donkonFlg && !donkonOyaFlg) {
						ps.setString(++j, "7");
					} else {
						ps.setString(++j, "2");
					}
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "楽天");
					shop = shopMap.get(orderList.get(i).getJuchubango().substring(0, 6));
					if (shop != null) {
						
					} else {
//					if (orderList.get(i).getJuchubango().startsWith("308759")) {
//						shop = "coverforefront";
//					} else if (orderList.get(i).getJuchubango().startsWith("373860")) {
//						shop = "herz";
//					} else if (orderList.get(i).getJuchubango().startsWith("384100")) {
//						shop = "epintl";
//					} else {
						shop = orderList.get(i).getShousaiList().get(0).getShouhinURL();
						shop = shop.replace("//", "");
						shop = shop.substring(shop.indexOf("/") + 1);
						shop = shop.substring(0, shop.indexOf("/"));
					}
					if (!Utility.isValidShop(shop)) {
						throw new Exception("not valid");
					}
					ps.setString(++j, shop);
					ps.setString(++j, "");
					if ("代金引換".equals(orderList.get(i).getHaisouhoho())) {
						if (Long.valueOf(
								orderList.get(i).getPointoriyogaku()) >= (Long.valueOf(orderList.get(i).getGokei()))) {
							ps.setString(++j, "銀行振込");
						}else {
							ps.setString(++j, orderList.get(i).getKesaihouhou());
						}
					} else {
						ps.setString(++j, orderList.get(i).getKesaihouhou());
					}
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, Utility.isEmptyString(orderList.get(i).getPointoriyogaku()) ? "0"
							: orderList.get(i).getPointoriyogaku());

					// あす楽希望
					ps.setString(++j, orderList.get(i).getAsurakukibou());
					// 注文者名字
					ps.setString(++j, orderList.get(i).getChumonshameiji());
					// 注文者名前
					ps.setString(++j, orderList.get(i).getChumonshanamae());
					// 注文者名字フリガナ
					ps.setString(++j, orderList.get(i).getChumonshameijifurigana());
					// 注文者名前フリガナ
					ps.setString(++j, orderList.get(i).getChumonshanamaefurigana());
					// メールアドレス
					ps.setString(++j, orderList.get(i).getMeruadoresu());
					// 注文者誕生日
					ps.setString(++j, orderList.get(i).getChunonshatanjoubi());
					// 注文者郵便番号１
					ps.setString(++j, orderList.get(i).getChumonshayubinbango1());
					// 注文者郵便番号２
					ps.setString(++j, orderList.get(i).getChumonshayubinbango2());
					// 注文者住所：都道府県
					ps.setString(++j, orderList.get(i).getChumonshajushotodofuken());
					// 注文者住所：都市区
					ps.setString(++j, orderList.get(i).getChumonshajushotoshiku());
					// 注文者住所：町以降
					ps.setString(++j, orderList.get(i).getChumonshajushochoijou());
					// 注文者電話番号１
					ps.setString(++j, orderList.get(i).getChumonshadenwabango1());
					// 注文者電話番号２
					ps.setString(++j, orderList.get(i).getChumonshadenwabango2());
					// 注文者電話番号３
					ps.setString(++j, orderList.get(i).getChumonshadenwabango3());
					// コメント
					ps.setString(++j, orderList.get(i).getKomento());
					// メール差込文(お客様へのメッセージ)
					ps.setString(++j, orderList.get(i).getMerusashikomibun());

					// 送付先名字
					ps.setString(++j, orderList.get(i).getSofusakimeiji());
					// 送付先名前
					ps.setString(++j, orderList.get(i).getSoufusakinamae());
					// 送付先名字フリガナ
					ps.setString(++j, orderList.get(i).getSoufusakimeijifurigana());
					// 送付先名前フリガナ
					ps.setString(++j, orderList.get(i).getSoufusakimeijinamaefurigana());
					// 配送方法
					ps.setString(++j, orderList.get(i).getHaisouhoho());
					// 送付先郵便番号１
					ps.setString(++j, orderList.get(i).getSoufusakiyubinbango1());
					// 送付先郵便番号２
					ps.setString(++j, orderList.get(i).getSoufusakiyubinbango2());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango1());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango2());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango3());
					// 送付先住所：都道府県
					ps.setString(++j, orderList.get(i).getSoufusakijushotodofuken());
					// 送付先住所：都市区
					ps.setString(++j, orderList.get(i).getSoufusakijushotoshiku());
					// 送付先住所：町以降
					ps.setString(++j, orderList.get(i).getSoufusakijushochoijou());

					String gokeishouhin = "0";
					String gokeizei = "0";
					String gokeisouryou = "0";
					String gokeidaibikiryou = "0";
					String seikyukingaku = "0";

					if (!donkonFlg) {
						gokeishouhin = orderList.get(i).getGokei();
						gokeizei = Utility.isEmptyString(orderList.get(i).getShohizei()) ? "0"
								: orderList.get(i).getShohizei();

						gokeisouryou = Utility.isEmptyString(orderList.get(i).getSoryou()) ? "0"
								: orderList.get(i).getSoryou();

						gokeidaibikiryou = Utility.isEmptyString(orderList.get(i).getDaibikiryou()) ? "0"
								: orderList.get(i).getDaibikiryou();

						seikyukingaku = orderList.get(i).getSeikyukingaku();
					} else {
						if (donkonOyaFlg) {
							String donkonId = orderList.get(i).getDokonId();
							for (RakutenCsvBean bean : orderList) {
								if (donkonId.equals(bean.getDokonId())) {
									if (!Utility.isEmptyString(bean.getDokongokeikingaku())) {
										gokeishouhin = String.valueOf(Integer.valueOf(bean.getDokongokeikingaku())
												+ Integer.valueOf(gokeishouhin));
									}
								}
							}
						}
						gokeizei = Utility.isEmptyString(orderList.get(i).getDokonshohizeigokei()) ? "0"
								: orderList.get(i).getDokonshohizeigokei();

						gokeisouryou = Utility.isEmptyString(orderList.get(i).getDokonsoryougokei()) ? "0"
								: orderList.get(i).getDokonsoryougokei();

						gokeidaibikiryou = Utility.isEmptyString(orderList.get(i).getDokondaibikiryougokei()) ? "0"
								: orderList.get(i).getDokondaibikiryougokei();

						seikyukingaku = Utility.isEmptyString(orderList.get(i).getDokonseikyukingaku()) ? "0"
								: orderList.get(i).getDokonseikyukingaku();
					}
					if ("代金引換".equals(orderList.get(i).getHaisouhoho())) {
						if (Long.valueOf(
								orderList.get(i).getPointoriyogaku()) >= (Long.valueOf(orderList.get(i).getGokei()))) {
							gokeisouryou = "0";
							gokeidaibikiryou = "0";
						}
					}
					ps.setString(++j, gokeishouhin);
					ps.setString(++j, gokeizei);
					ps.setString(++j, gokeisouryou);
					ps.setString(++j, gokeidaibikiryou);
					ps.setString(++j, seikyukingaku);

					// 同梱ID
					ps.setString(++j, orderList.get(i).getDokonId());
					// 同梱親FLG
					ps.setString(++j, donkonOyaFlg ? "1" : "0");

					ps.setString(++j, "0");
					ps.setString(++j, date);
					ps.setString(++j, "kyo");
					ps.setString(++j, date);
					ps.setString(++j, "kyo");

					// 発送者へのコメント
					ps.setString(++j, "");
					// その他利用額
					ps.setString(++j, orderList.get(i).getKuponriyougaku());

					ps.executeUpdate();
					ps.close();

				} catch (MySQLIntegrityConstraintViolationException e) {
					System.out.println(orderList.get(i).getJuchubango() + "已存在，不再添加");
					continue;
				} finally {
					if (ps != null && !ps.isClosed()) {
						ps.close();
					}
				}

				// for (int k = 0; k < orderList.get(i).getShousaiList().size();
				// k++) {
				// RakutenDetailCsvBean shousai = orderList.get(i)
				// .getShousaiList().get(k);
				// j = 0;
				// sql =
				// "INSERT INTO rakuten_order_detail_tbl
				// VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
				// ps = conn.prepareStatement(sql);
				//
				// ps.setString(++j, orderList.get(i).getJuchubango());
				// // 商品ID
				// ps.setString(++j, shousai.getShouhinId());
				// // 商品名
				// ps.setString(++j, shousai.getShouhinmei());
				// // 商品番号
				// ps.setString(++j, shousai.getShouhinbango());
				// // 商品URL
				// ps.setString(++j, shousai.getShouhinURL());
				// // 単価
				// ps.setString(++j, shousai.getTanka());
				// // 個数
				// ps.setString(++j, shousai.getKosu());
				// // 送料込別
				// ps.setString(++j, shousai.getSouryoukomibetsu());
				// // 税込別
				// ps.setString(++j, shousai.getZeikomibetsu());
				// // 代引手数料込別
				// ps.setString(++j, shousai.getDaibikitesuryoukomibetsu());
				// // 項目・選択肢
				// ps.setString(++j, shousai.getKomokusentakushi());
				// // ポイント倍率
				// ps.setString(++j, shousai.getPointobairitsu());
				// // ポイントタイプ
				// ps.setString(++j, shousai.getPointotaipu());
				// // レコードナンバー
				// ps.setString(++j, shousai.getRekodananba());
				// // 納期情報
				// ps.setString(++j, shousai.getNokijouho());
				// // 在庫タイプ
				// ps.setString(++j, shousai.getZaikotaipu());
				// // ラッピング種類(包装紙)
				// ps.setString(++j, shousai.getRapingushuruihousou());
				// // ラッピング種類(リボン)
				// ps.setString(++j, shousai.getRapingushuruiribon());
				//
				// ps.executeUpdate();
				// }
				int noukiday = 2;
				if (!donkonFlg) {

					for (int k = 0; k < orderList.get(i).getShousaiList().size(); k++) {

						RakutenDetailCsvBean shousai = orderList.get(i).getShousaiList().get(k);

						shousai.setShouhinbango(shousai.getShouhinbango().replace("10000001", "xbx008"));

						shousai.setShouhinbango(shousai.getShouhinbango().replace("lu-001", "xbx030"));

						j = 0;
						sql = "INSERT INTO common_order_detail_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
						ps = conn.prepareStatement(sql);

						ps.setString(++j, Utility.getShoribango());

						ps.setString(++j, orderList.get(i).getJuchubango());
						// 商品名
						ps.setString(++j, shousai.getShouhinmei());
						String bango = shousai.getShouhinbango();
						if (bango.startsWith("sale")) {
							bango = bango.replace("sale", "");
						}

						// 商品番号
						ps.setString(++j, bango);
						// 商品URL
						ps.setString(++j, shousai.getShouhinURL());
						// 単価
						ps.setString(++j, shousai.getTanka());
						// 個数
						ps.setString(++j, shousai.getKosu());
						// 送料込別
						ps.setString(++j, shousai.getSouryoukomibetsu());
						// 税込別
						ps.setString(++j, shousai.getZeikomibetsu());
						// 代引手数料込別
						ps.setString(++j, shousai.getDaibikitesuryoukomibetsu());
						// 項目選択肢
						ps.setString(++j, shousai.getKomokusentakushi());

						// ponint bairitu
						ps.setString(++j, shousai.getPointobairitsu());
						// nouki
						ps.setString(++j, shousai.getNokijouho());

						ps.executeUpdate();
						
						ps.close();

						if (Utility.getNoukiDay(shousai.getNokijouho()) > noukiday) {
							noukiday = Utility.getNoukiDay(shousai.getNokijouho());
						}
						
						String commodityId;
						String detailNo;
						if (-1 == bango.indexOf("-")) {
							commodityId = bango;
							detailNo = "-0-0";
						} else {
							commodityId = bango.substring(0, bango.indexOf("-"));
							detailNo = bango.substring(bango.indexOf("-") + 1);
						}
						sql = "SELECT count(*) COUNT FROM rakuten.tbl00012 WHERE `COMMODITY_ID`=? and`DETAIL_NO`=?;";
						ps = conn.prepareStatement(sql);
						ps.setString(1, commodityId);
						ps.setString(2, detailNo);
						rs = ps.executeQuery();
						int count = 0;
						while (rs.next()) {
							count = rs.getInt("COUNT");
						}
						rs.close();
						ps.close();
						if (0 == count) {
							sql = "INSERT INTO `rakuten`.`tbl00012` (`COMMODITY_ID`, `DETAIL_NO`, `UPDATEQUANTITY_FLG`, RE_PRICE, STOCK_SH, STOCK_JP, STOCK_HANDUP, DEL_FLG) VALUES (?, ?, TRUE, ?, 0, 0, 0, 0);";
							
						} else {
							sql = "UPDATE `rakuten`.`tbl00012` SET `UPDATEQUANTITY_FLG`=TRUE WHERE `COMMODITY_ID`=? and`DETAIL_NO`=?;";
						}
						ps = conn.prepareStatement(sql);
						ps.setString(1, commodityId);
						ps.setString(2, detailNo);
						if (0 == count) {
							ps.setString(3, shousai.getTanka());
						}
						ps.executeUpdate();
						ps.close();
						if (0 == count) {
							sql = "INSERT INTO tbl00011(COMMODITY_ID,CATEGORY_ID,CHINESE_NAME,JAPANESE_NAME,SOURCE,RESP_PERSON,COMMODITY_URL,PIC_URL,REMARKS,DEL_FLG,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER,STATUS)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							ps = conn.prepareStatement(sql);
							ps.setString(1, commodityId);
							ps.setString(2, "100001");
							ps.setString(3, shousai.getShouhinmei());
							ps.setString(4, shousai.getShouhinmei());
							ps.setString(5, "");
							ps.setString(6, "");
							ps.setString(7, "");
							String pirurl = "";
							ps.setString(8, pirurl);
							ps.setString(9, "");
							ps.setString(10, "0");
							ps.setString(11, date);
							ps.setString(12, "kyo");
							ps.setString(13, date);
							ps.setString(14, "kyo");
							ps.setString(15, "00");
							ps.execute();
							ps.close();
							
							String maxBarcode = null;
							sql = "SELECT COUNT(*) COUNT FROM TBL00016 WHERE COMMODITY_ID = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, bango);
							rs = ps.executeQuery();
							while (rs.next()) {
								count = rs.getInt("COUNT");
							}
							if (count == 0) {
								sql = "SELECT MAX(BARCODE)+1 MAX_BARCODE FROM TBL00016";
								ps = conn.prepareStatement(sql);
								rs = ps.executeQuery();
								while (rs.next()) {
									maxBarcode = rs.getString("MAX_BARCODE");
								}

								sql = "INSERT INTO TBL00016 VALUES(?,?)";
								ps = conn.prepareStatement(sql);
								ps.setString(1,
										bango);
								ps.setString(2, maxBarcode);
								ps.execute();
								ps.close();
							}
						}
					}
				} else if (donkonFlg && donkonOyaFlg) {
					List<RakutenDetailCsvBean> shousaiDonkonList = new ArrayList<RakutenDetailCsvBean>();
					String donkonId = orderList.get(i).getDokonId();
					for (int m = 0; m < orderList.size(); m++) {
						if (donkonId.equals(orderList.get(m).getDokonId())) {
							shousaiDonkonList.addAll(orderList.get(m).getShousaiList());
						}
					}

					for (int k = 0; k < shousaiDonkonList.size(); k++) {
						RakutenDetailCsvBean shousai = shousaiDonkonList.get(k);
						j = 0;
						sql = "INSERT INTO common_order_detail_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
						ps = conn.prepareStatement(sql);
						ps.setString(++j, Utility.getShoribango());

						ps.setString(++j, orderList.get(i).getJuchubango());
						// 商品名
						ps.setString(++j, shousai.getShouhinmei());
						// 商品番号
						ps.setString(++j, shousai.getShouhinbango());
						// 商品URL
						ps.setString(++j, shousai.getShouhinURL());
						// 単価
						ps.setString(++j, shousai.getTanka());
						// 個数
						ps.setString(++j, shousai.getKosu());
						// 送料込別
						ps.setString(++j, shousai.getSouryoukomibetsu());
						// 税込別
						ps.setString(++j, shousai.getZeikomibetsu());
						// 代引手数料込別
						ps.setString(++j, shousai.getDaibikitesuryoukomibetsu());
						// 項目選択肢
						ps.setString(++j, shousai.getKomokusentakushi());

						// ponint bairitu
						ps.setString(++j, shousai.getPointobairitsu());
						// nouki
						ps.setString(++j, shousai.getNokijouho());

						if (Utility.getNoukiDay(shousai.getNokijouho()) > noukiday) {
							noukiday = Utility.getNoukiDay(shousai.getNokijouho());
						}

						ps.executeUpdate();
						
						String bango = shousai.getShouhinbango();
						String commodityId;
						String detailNo;
						if (-1 == bango.indexOf("-")) {
							commodityId = bango;
							detailNo = "-0-0";
						} else {
							commodityId = bango.substring(0, bango.indexOf("-"));
							detailNo = bango.substring(bango.indexOf("-") + 1);
						}
						sql = "SELECT count(*) COUNT FROM rakuten.tbl00012 WHERE `COMMODITY_ID`=? and`DETAIL_NO`=?;";
						ps = conn.prepareStatement(sql);
						ps.setString(1, commodityId);
						ps.setString(2, detailNo);
						rs = ps.executeQuery();
						int count = 0;
						while (rs.next()) {
							count = rs.getInt("COUNT");
						}
						rs.close();
						ps.close();
						if (0 == count) {
							sql = "INSERT INTO `rakuten`.`tbl00012` (`COMMODITY_ID`, `DETAIL_NO`, `UPDATEQUANTITY_FLG`) VALUES (?, ?, TRUE);";
							
						} else {
							sql = "UPDATE `rakuten`.`tbl00012` SET `UPDATEQUANTITY_FLG`=TRUE WHERE `COMMODITY_ID`=? and`DETAIL_NO`=?;";
						}
						ps = conn.prepareStatement(sql);
						ps.setString(1, commodityId);
						ps.setString(2, detailNo);
						ps.executeUpdate();
						ps.close();
					}
				}
				String dateStr = "";
				if (noukiday != 20) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, noukiday);
					dateStr = sdf.format(calendar.getTime());
				} else {
					dateStr = "2019-02-21";
				}
				sql = "INSERT INTO TBL00027 VALUES(?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, orderList.get(i).getJuchubango());
				ps.setString(2, dateStr);
				ps.setString(3, Utility.getDateTime());
				ps.setString(4, Utility.getUser());
				ps.setString(5, Utility.getDateTime());
				ps.setString(6, Utility.getUser());
				ps.execute();
			}

			// commit
			conn.commit();

		} catch (

		Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public void insertIntoYahooOrderTbl(List<RakutenCsvBean> orderList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		try {
			conn = JdbcConnection.getConnection();
			for (int i = 0; i < orderList.size(); i++) {

				int j = 0;
				boolean donkonFlg = false;
				boolean donkonOyaFlg = false;
				try {
					if (!"0".equals(orderList.get(i).getDokonId())) {
						donkonFlg = true;
						if ("1".equals(orderList.get(i).getDokonsutetasu())) {
							donkonOyaFlg = true;
						}
					}

					j = 0;
					sql = "INSERT INTO common_order_tbl VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(++j, orderList.get(i).getJuchubango());
					ps.setString(++j, orderList.get(i).getChumonnichiji());
					ps.setString(++j, "0");// 未入金
					if (donkonFlg && !donkonOyaFlg) {
						ps.setString(++j, "7");
					} else {
						ps.setString(++j, "2");
					}
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "Yahoo");
//					if (orderList.get(i).getJuchubango().startsWith("kirakiraichiba")) {
//						shop = "kirakiraichiba";
//					} else {
//						shop = "";
//					}
					shop = orderList.get(i).getJuchubango().substring(0, orderList.get(i).getJuchubango().indexOf("-"));
					
					ps.setString(++j, shop);
					ps.setString(++j, "");
					if ("代金引換".equals(orderList.get(i).getHaisouhoho())) {
						if (Long.valueOf(
								orderList.get(i).getPointoriyogaku()) >= (Long.valueOf(orderList.get(i).getGokei()))) {
							ps.setString(++j, "銀行振込");
						}else {
							ps.setString(++j, orderList.get(i).getKesaihouhou());
						}
					} else {
						ps.setString(++j, orderList.get(i).getKesaihouhou());
					}
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, Utility.isEmptyString(orderList.get(i).getPointoriyogaku()) ? "0"
							: orderList.get(i).getPointoriyogaku());

					// あす楽希望
					ps.setString(++j, orderList.get(i).getAsurakukibou());
					// 注文者名字
					ps.setString(++j, orderList.get(i).getChumonshameiji());
					// 注文者名前
					ps.setString(++j, orderList.get(i).getChumonshanamae());
					// 注文者名字フリガナ
					ps.setString(++j, orderList.get(i).getChumonshameijifurigana());
					// 注文者名前フリガナ
					ps.setString(++j, orderList.get(i).getChumonshanamaefurigana());
					// メールアドレス
					ps.setString(++j, orderList.get(i).getMeruadoresu());
					// 注文者誕生日
					ps.setString(++j, orderList.get(i).getChunonshatanjoubi());
					// 注文者郵便番号１
					ps.setString(++j, orderList.get(i).getChumonshayubinbango1());
					// 注文者郵便番号２
					ps.setString(++j, orderList.get(i).getChumonshayubinbango2());
					// 注文者住所：都道府県
					ps.setString(++j, orderList.get(i).getChumonshajushotodofuken());
					// 注文者住所：都市区
					ps.setString(++j, orderList.get(i).getChumonshajushotoshiku());
					// 注文者住所：町以降
					ps.setString(++j, orderList.get(i).getChumonshajushochoijou());
					// 注文者電話番号１
					ps.setString(++j, orderList.get(i).getChumonshadenwabango1());
					// 注文者電話番号２
					ps.setString(++j, orderList.get(i).getChumonshadenwabango2());
					// 注文者電話番号３
					ps.setString(++j, orderList.get(i).getChumonshadenwabango3());
					// コメント
					ps.setString(++j, orderList.get(i).getKomento());
					// メール差込文(お客様へのメッセージ)
					ps.setString(++j, orderList.get(i).getMerusashikomibun());

					// 送付先名字
					ps.setString(++j, orderList.get(i).getSofusakimeiji());
					// 送付先名前
					ps.setString(++j, orderList.get(i).getSoufusakinamae());
					// 送付先名字フリガナ
					ps.setString(++j, orderList.get(i).getSoufusakimeijifurigana());
					// 送付先名前フリガナ
					ps.setString(++j, orderList.get(i).getSoufusakimeijinamaefurigana());
					// 配送方法
					ps.setString(++j, orderList.get(i).getHaisouhoho());
					// 送付先郵便番号１
					ps.setString(++j, orderList.get(i).getSoufusakiyubinbango1());
					// 送付先郵便番号２
					ps.setString(++j, orderList.get(i).getSoufusakiyubinbango2());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango1());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango2());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango3());
					// 送付先住所：都道府県
					ps.setString(++j, orderList.get(i).getSoufusakijushotodofuken());
					// 送付先住所：都市区
					ps.setString(++j, orderList.get(i).getSoufusakijushotoshiku());
					// 送付先住所：町以降
					ps.setString(++j, orderList.get(i).getSoufusakijushochoijou());

					String gokeishouhin = "0";
					String gokeizei = "0";
					String gokeisouryou = "0";
					String gokeidaibikiryou = "0";
					String seikyukingaku = "0";

					if (!donkonFlg) {
						gokeishouhin = orderList.get(i).getGokei();
						gokeizei = Utility.isEmptyString(orderList.get(i).getShohizei()) ? "0"
								: orderList.get(i).getShohizei();

						gokeisouryou = Utility.isEmptyString(orderList.get(i).getSoryou()) ? "0"
								: orderList.get(i).getSoryou();

						gokeidaibikiryou = Utility.isEmptyString(orderList.get(i).getDaibikiryou()) ? "0"
								: orderList.get(i).getDaibikiryou();

						seikyukingaku = orderList.get(i).getSeikyukingaku();
					} else {
						if (donkonOyaFlg) {
							String donkonId = orderList.get(i).getDokonId();
							for (RakutenCsvBean bean : orderList) {
								if (donkonId.equals(bean.getDokonId())) {
									if (!Utility.isEmptyString(bean.getDokongokeikingaku())) {
										gokeishouhin = String.valueOf(Integer.valueOf(bean.getDokongokeikingaku())
												+ Integer.valueOf(gokeishouhin));
									}
								}
							}
						}
						gokeizei = Utility.isEmptyString(orderList.get(i).getDokonshohizeigokei()) ? "0"
								: orderList.get(i).getDokonshohizeigokei();

						gokeisouryou = Utility.isEmptyString(orderList.get(i).getDokonsoryougokei()) ? "0"
								: orderList.get(i).getDokonsoryougokei();

						gokeidaibikiryou = Utility.isEmptyString(orderList.get(i).getDokondaibikiryougokei()) ? "0"
								: orderList.get(i).getDokondaibikiryougokei();

						seikyukingaku = Utility.isEmptyString(orderList.get(i).getDokonseikyukingaku()) ? "0"
								: orderList.get(i).getDokonseikyukingaku();
					}
					if ("代金引換".equals(orderList.get(i).getHaisouhoho())) {
						if (Long.valueOf(
								orderList.get(i).getPointoriyogaku()) >= (Long.valueOf(orderList.get(i).getGokei()))) {
							gokeisouryou = "0";
							gokeidaibikiryou = "0";
						}
					}
					ps.setString(++j, gokeishouhin);
					ps.setString(++j, gokeizei);
					ps.setString(++j, gokeisouryou);
					ps.setString(++j, gokeidaibikiryou);
					ps.setString(++j, seikyukingaku);

					// 同梱ID
					ps.setString(++j, orderList.get(i).getDokonId());
					// 同梱親FLG
					ps.setString(++j, donkonOyaFlg ? "1" : "0");

					ps.setString(++j, "0");
					ps.setString(++j, date);
					ps.setString(++j, "kyo");
					ps.setString(++j, date);
					ps.setString(++j, "kyo");

					// 発送者へのコメント
					ps.setString(++j, "");
					// その他利用額
					ps.setString(++j, orderList.get(i).getKuponriyougaku());

					ps.executeUpdate();

				} catch (MySQLIntegrityConstraintViolationException e) {
					System.out.println(orderList.get(i).getJuchubango() + "已存在，不再添加");
					continue;
				}

				// for (int k = 0; k < orderList.get(i).getShousaiList().size();
				// k++) {
				// RakutenDetailCsvBean shousai = orderList.get(i)
				// .getShousaiList().get(k);
				// j = 0;
				// sql =
				// "INSERT INTO rakuten_order_detail_tbl
				// VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
				// ps = conn.prepareStatement(sql);
				//
				// ps.setString(++j, orderList.get(i).getJuchubango());
				// // 商品ID
				// ps.setString(++j, shousai.getShouhinId());
				// // 商品名
				// ps.setString(++j, shousai.getShouhinmei());
				// // 商品番号
				// ps.setString(++j, shousai.getShouhinbango());
				// // 商品URL
				// ps.setString(++j, shousai.getShouhinURL());
				// // 単価
				// ps.setString(++j, shousai.getTanka());
				// // 個数
				// ps.setString(++j, shousai.getKosu());
				// // 送料込別
				// ps.setString(++j, shousai.getSouryoukomibetsu());
				// // 税込別
				// ps.setString(++j, shousai.getZeikomibetsu());
				// // 代引手数料込別
				// ps.setString(++j, shousai.getDaibikitesuryoukomibetsu());
				// // 項目・選択肢
				// ps.setString(++j, shousai.getKomokusentakushi());
				// // ポイント倍率
				// ps.setString(++j, shousai.getPointobairitsu());
				// // ポイントタイプ
				// ps.setString(++j, shousai.getPointotaipu());
				// // レコードナンバー
				// ps.setString(++j, shousai.getRekodananba());
				// // 納期情報
				// ps.setString(++j, shousai.getNokijouho());
				// // 在庫タイプ
				// ps.setString(++j, shousai.getZaikotaipu());
				// // ラッピング種類(包装紙)
				// ps.setString(++j, shousai.getRapingushuruihousou());
				// // ラッピング種類(リボン)
				// ps.setString(++j, shousai.getRapingushuruiribon());
				//
				// ps.executeUpdate();
				// }
				int noukiday = 2;
				if (!donkonFlg) {

					for (int k = 0; k < orderList.get(i).getShousaiList().size(); k++) {

						RakutenDetailCsvBean shousai = orderList.get(i).getShousaiList().get(k);

						shousai.setShouhinbango(shousai.getShouhinbango().replace("10000001", "xbx008"));

						shousai.setShouhinbango(shousai.getShouhinbango().replace("lu-001", "xbx030"));

						j = 0;
						sql = "INSERT INTO common_order_detail_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
						ps = conn.prepareStatement(sql);

						ps.setString(++j, Utility.getShoribango());

						ps.setString(++j, orderList.get(i).getJuchubango());
						// 商品名
						ps.setString(++j, shousai.getShouhinmei());
						String bango = shousai.getShouhinbango();
						if (bango.startsWith("sale")) {
							bango = bango.replace("sale", "");
						}

						// 商品番号
						ps.setString(++j, bango);
						// 商品URL
						ps.setString(++j, shousai.getShouhinURL());
						// 単価
						ps.setString(++j, shousai.getTanka());
						// 個数
						ps.setString(++j, shousai.getKosu());
						// 送料込別
						ps.setString(++j, shousai.getSouryoukomibetsu());
						// 税込別
						ps.setString(++j, shousai.getZeikomibetsu());
						// 代引手数料込別
						ps.setString(++j, shousai.getDaibikitesuryoukomibetsu());
						// 項目選択肢
						ps.setString(++j, shousai.getKomokusentakushi());

						// ponint bairitu
						ps.setString(++j, shousai.getPointobairitsu());
						// nouki
						ps.setString(++j, shousai.getNokijouho());

						ps.executeUpdate();

						if (Utility.getNoukiDay(shousai.getNokijouho()) > noukiday) {
							noukiday = Utility.getNoukiDay(shousai.getNokijouho());
						}
						
						String commodityId;
						String detailNo;
						if (-1 == bango.indexOf("-")) {
							commodityId = bango;
							detailNo = "-0-0";
						} else {
							commodityId = bango.substring(0, bango.indexOf("-"));
							detailNo = bango.substring(bango.indexOf("-") + 1);
						}
						sql = "SELECT count(*) COUNT FROM rakuten.tbl00012 WHERE `COMMODITY_ID`=? and`DETAIL_NO`=?;";
						ps = conn.prepareStatement(sql);
						ps.setString(1, commodityId);
						ps.setString(2, detailNo);
						rs = ps.executeQuery();
						int count = 0;
						while (rs.next()) {
							count = rs.getInt("COUNT");
						}
						rs.close();
						ps.close();
						if (0 == count) {
							sql = "INSERT INTO `rakuten`.`tbl00012` (`COMMODITY_ID`, `DETAIL_NO`, `UPDATEQUANTITY_FLG`) VALUES (?, ?, TRUE);";
							
						} else {
							sql = "UPDATE `rakuten`.`tbl00012` SET `UPDATEQUANTITY_FLG`=TRUE WHERE `COMMODITY_ID`=? and`DETAIL_NO`=?;";
						}
						ps = conn.prepareStatement(sql);
						ps.setString(1, commodityId);
						ps.setString(2, detailNo);
						ps.executeUpdate();
						ps.close();
						
						if (0 == count) {
							sql = "INSERT INTO tbl00011(COMMODITY_ID,CATEGORY_ID,CHINESE_NAME,JAPANESE_NAME,SOURCE,RESP_PERSON,COMMODITY_URL,PIC_URL,REMARKS,DEL_FLG,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER,STATUS)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
							ps = conn.prepareStatement(sql);
							ps.setString(1, commodityId);
							ps.setString(2, "100001");
							ps.setString(3, shousai.getShouhinmei());
							ps.setString(4, shousai.getShouhinmei());
							ps.setString(5, "");
							ps.setString(6, "");
							ps.setString(7, "");
							String pirurl = "";
							ps.setString(8, pirurl);
							ps.setString(9, "");
							ps.setString(10, "0");
							ps.setString(11, date);
							ps.setString(12, "kyo");
							ps.setString(13, date);
							ps.setString(14, "kyo");
							ps.setString(15, "00");
							ps.execute();
							ps.close();
							
							String maxBarcode = null;
							sql = "SELECT COUNT(*) COUNT FROM TBL00016 WHERE COMMODITY_ID = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, bango);
							rs = ps.executeQuery();
							while (rs.next()) {
								count = rs.getInt("COUNT");
							}
							if (count == 0) {
								sql = "SELECT MAX(BARCODE)+1 MAX_BARCODE FROM TBL00016";
								ps = conn.prepareStatement(sql);
								rs = ps.executeQuery();
								while (rs.next()) {
									maxBarcode = rs.getString("MAX_BARCODE");
								}

								sql = "INSERT INTO TBL00016 VALUES(?,?)";
								ps = conn.prepareStatement(sql);
								ps.setString(1,
										bango);
								ps.setString(2, maxBarcode);
								ps.execute();
								ps.close();
							}
						}
					}
				} else if (donkonFlg && donkonOyaFlg) {
					List<RakutenDetailCsvBean> shousaiDonkonList = new ArrayList<RakutenDetailCsvBean>();
					String donkonId = orderList.get(i).getDokonId();
					for (int m = 0; m < orderList.size(); m++) {
						if (donkonId.equals(orderList.get(m).getDokonId())) {
							shousaiDonkonList.addAll(orderList.get(m).getShousaiList());
						}
					}

					for (int k = 0; k < shousaiDonkonList.size(); k++) {
						RakutenDetailCsvBean shousai = shousaiDonkonList.get(k);
						j = 0;
						sql = "INSERT INTO common_order_detail_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
						ps = conn.prepareStatement(sql);
						ps.setString(++j, Utility.getShoribango());

						ps.setString(++j, orderList.get(i).getJuchubango());
						// 商品名
						ps.setString(++j, shousai.getShouhinmei());
						// 商品番号
						ps.setString(++j, shousai.getShouhinbango());
						// 商品URL
						ps.setString(++j, shousai.getShouhinURL());
						// 単価
						ps.setString(++j, shousai.getTanka());
						// 個数
						ps.setString(++j, shousai.getKosu());
						// 送料込別
						ps.setString(++j, shousai.getSouryoukomibetsu());
						// 税込別
						ps.setString(++j, shousai.getZeikomibetsu());
						// 代引手数料込別
						ps.setString(++j, shousai.getDaibikitesuryoukomibetsu());
						// 項目選択肢
						ps.setString(++j, shousai.getKomokusentakushi());

						// ponint bairitu
						ps.setString(++j, shousai.getPointobairitsu());
						// nouki
						ps.setString(++j, shousai.getNokijouho());

						if (Utility.getNoukiDay(shousai.getNokijouho()) > noukiday) {
							noukiday = Utility.getNoukiDay(shousai.getNokijouho());
						}

						ps.executeUpdate();
						
						String bango = shousai.getShouhinbango();
						String commodityId;
						String detailNo;
						if (-1 == bango.indexOf("-")) {
							commodityId = bango;
							detailNo = "-0-0";
						} else {
							commodityId = bango.substring(0, bango.indexOf("-"));
							detailNo = bango.substring(bango.indexOf("-") + 1);
						}
						sql = "SELECT count(*) COUNT FROM rakuten.tbl00012 WHERE `COMMODITY_ID`=? and`DETAIL_NO`=?;";
						ps = conn.prepareStatement(sql);
						ps.setString(1, commodityId);
						ps.setString(2, detailNo);
						rs = ps.executeQuery();
						int count = 0;
						while (rs.next()) {
							count = rs.getInt("COUNT");
						}
						rs.close();
						ps.close();
						if (0 == count) {
							sql = "INSERT INTO `rakuten`.`tbl00012` (`COMMODITY_ID`, `DETAIL_NO`, `UPDATEQUANTITY_FLG`) VALUES (?, ?, TRUE);";
							
						} else {
							sql = "UPDATE `rakuten`.`tbl00012` SET `UPDATEQUANTITY_FLG`=TRUE WHERE `COMMODITY_ID`=? and`DETAIL_NO`=?;";
						}
						ps = conn.prepareStatement(sql);
						ps.setString(1, commodityId);
						ps.setString(2, detailNo);
						ps.executeUpdate();
						ps.close();
					}
				}
				String dateStr = "";
				if (noukiday != 20) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar calendar = Calendar.getInstance();
					calendar.add(Calendar.DATE, noukiday);
					dateStr = sdf.format(calendar.getTime());
				} else {
					dateStr = "2019-02-21";
				}
				sql = "INSERT INTO TBL00027 VALUES(?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, orderList.get(i).getJuchubango());
				ps.setString(2, dateStr);
				ps.setString(3, Utility.getDateTime());
				ps.setString(4, Utility.getUser());
				ps.setString(5, Utility.getDateTime());
				ps.setString(6, Utility.getUser());
				ps.execute();
			}

			// commit
			conn.commit();

		} catch (

		Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
	
	public void insertIntoPonpareOrderTbl(List<PonpareCsvBean> orderList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		try {
			conn = JdbcConnection.getConnection();
			for (int i = 0; i < orderList.size(); i++) {
				int j = 0;

				try {

					j = 0;
					sql = "INSERT INTO common_order_tbl VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(++j, orderList.get(i).getJuchubango());
					ps.setString(++j, orderList.get(i).getChumonnichiji());
					ps.setString(++j, "0");// 未入金

					ps.setString(++j, "2");
					// }
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "ポンパレモール");
					ps.setString(++j, "WhiteSweet");
					ps.setString(++j, "");
					String kesaihoho = orderList.get(i).getKesaihouhou();
					if (kesaihoho.contains("銀行振込")) {
						ps.setString(++j, "銀行振込");
					} else if (kesaihoho.contains("代金引換")) {
						ps.setString(++j, "代金引換");
					} else if (kesaihoho.contains("クレジットカード")) {
						ps.setString(++j, "クレジットカード");
					} else {
						ps.setString(++j, kesaihoho);
					}
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, Utility.isEmptyString(orderList.get(i).getPointoriyogaku()) ? "0"
							: orderList.get(i).getPointoriyogaku());

					// あす楽希望
					ps.setString(++j, "");
					// 注文者名字
					ps.setString(++j, orderList.get(i).getChumonshameiji());
					// 注文者名前
					ps.setString(++j, orderList.get(i).getChumonshanamae());
					// 注文者名字フリガナ
					ps.setString(++j, orderList.get(i).getChumonshameijifurigana());
					// 注文者名前フリガナ
					ps.setString(++j, orderList.get(i).getChumonshanamaefurigana());
					// メールアドレス
					ps.setString(++j, orderList.get(i).getMeruadoresu());
					// 注文者誕生日
					ps.setString(++j, "");
					// 注文者郵便番号１
					ps.setString(++j, orderList.get(i).getChumonshayubinbango1());
					// 注文者郵便番号２
					ps.setString(++j, orderList.get(i).getChumonshayubinbango2());
					// 注文者住所：都道府県
					ps.setString(++j, orderList.get(i).getChumonshajushotodofuken());
					// 注文者住所：都市区
					ps.setString(++j, orderList.get(i).getChumonshajushotoshiku());
					// 注文者住所：町以降
					ps.setString(++j, "");
					// 注文者電話番号１
					ps.setString(++j, orderList.get(i).getChumonshadenwabango1());
					// 注文者電話番号２
					ps.setString(++j, orderList.get(i).getChumonshadenwabango2());
					// 注文者電話番号３
					ps.setString(++j, orderList.get(i).getChumonshadenwabango3());
					// コメント
					ps.setString(++j, orderList.get(i).getKomento());
					// メール差込文(お客様へのメッセージ)
					ps.setString(++j, "");

					// 送付先名字
					ps.setString(++j, orderList.get(i).getSofusakimeiji());
					// 送付先名前
					ps.setString(++j, orderList.get(i).getSoufusakinamae());
					// 送付先名字フリガナ
					ps.setString(++j, orderList.get(i).getSoufusakimeijifurigana());
					// 送付先名前フリガナ
					ps.setString(++j, orderList.get(i).getSoufusakimeijinamaefurigana());
					// 配送方法
					int soryo = 0;
					if (!Utility.isEmptyString(orderList.get(i).getSoryou())) {
						soryo = Integer.valueOf(orderList.get(i).getSoryou());
					}
					if (soryo > 180 || orderList.get(i).getKesaihouhou().contains("代")
							|| Integer.valueOf(orderList.get(i).getGokei()) > 5480) {
						ps.setString(++j, "宅配便");
					} else {
						ps.setString(++j, "メール便");
					}
					// 送付先郵便番号１
					ps.setString(++j, orderList.get(i).getSoufusakiyubinbango1());
					// 送付先郵便番号２
					ps.setString(++j, orderList.get(i).getSoufusakiyubinbango2());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango1());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango2());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango3());
					// 送付先住所：都道府県
					ps.setString(++j, orderList.get(i).getSoufusakijushotodofuken());
					// 送付先住所：都市区
					ps.setString(++j, orderList.get(i).getSoufusakijushotoshiku());
					// 送付先住所：町以降
					ps.setString(++j, "");

					String gokeishouhin = "0";
					String gokeizei = "0";
					String gokeisouryou = "0";
					String gokeidaibikiryou = "0";
					String seikyukingaku = "0";

					// if (!donkonFlg) {
					gokeishouhin = orderList.get(i).getGokei();
					gokeizei = Utility.isEmptyString(orderList.get(i).getShohizei()) ? "0"
							: orderList.get(i).getShohizei();

					gokeisouryou = Utility.isEmptyString(orderList.get(i).getSoryou()) ? "0"
							: orderList.get(i).getSoryou();

					gokeidaibikiryou = Utility.isEmptyString(orderList.get(i).getDaibikiryou()) ? "0"
							: orderList.get(i).getDaibikiryou();

					seikyukingaku = orderList.get(i).getSeikyukingaku();

					ps.setString(++j, gokeishouhin);
					ps.setString(++j, gokeizei);
					ps.setString(++j, gokeisouryou);
					ps.setString(++j, gokeidaibikiryou);
					ps.setString(++j, seikyukingaku);

					// 同梱ID
					ps.setString(++j, "0");
					// 同梱親FLG
					ps.setString(++j, "0");

					ps.setString(++j, "0");
					ps.setString(++j, date);
					ps.setString(++j, "kyo");
					ps.setString(++j, date);
					ps.setString(++j, "kyo");
					ps.setString(++j, "");
					ps.setString(++j, "0");
					ps.executeUpdate();

				} catch (MySQLIntegrityConstraintViolationException e) {
					System.out.println(orderList.get(i).getJuchubango() + "已存在，不再添加");
					continue;
				}

				for (int k = 0; k < orderList.get(i).getShousaiList().size(); k++) {
					PonpareDetailCsvBean shousai = orderList.get(i).getShousaiList().get(k);

					String shouhinbango = shousai.getShouhinbango();
					shouhinbango = shouhinbango.replace("--", "-");
//					sql = "SELECT COUNT(COMMODITY_ID) COUNT FROM TBL00012 WHERE CONCAT(COMMODITY_ID,DETAIL_NO) = ?";
//					ps = conn.prepareStatement(sql);
//					ps.setString(1, shouhinbango.substring(0, shouhinbango.length() - 2));
//					ResultSet rs999 = ps.executeQuery();
//					while (rs999.next()) {
//						if (rs999.getInt("count") > 0) {
//							shouhinbango = shouhinbango.substring(0, shouhinbango.length() - 2);
//						}
//					}

					sql = "INSERT INTO common_order_detail_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(sql);

					j = 0;
					ps.setString(++j, Utility.getShoribango());

					ps.setString(++j, orderList.get(i).getJuchubango());
					// 商品名
					ps.setString(++j, shousai.getShouhinmei());
					// 商品番号
					ps.setString(++j, shouhinbango);
					// 商品URL
					ps.setString(++j, "");
					// 単価
					ps.setString(++j,
							String.valueOf(Integer.valueOf(shousai.getTanka()) / Integer.valueOf(shousai.getKosu())));
					// 個数
					ps.setString(++j, shousai.getKosu());
					// 送料込別
					ps.setString(++j, shousai.getSouryoukomibetsu());
					// 税込別
					ps.setString(++j, shousai.getZeikomibetsu());
					// 代引手数料込別
					ps.setString(++j, shousai.getDaibikitesuryoukomibetsu());
					// 項目選択肢
					ps.setString(++j, shousai.getKomokusentakushi());

					// ponint bairitu
					ps.setString(++j, shousai.getPointobairitsu());

					// nouki
					ps.setString(++j, shousai.getNouki());

					ps.executeUpdate();
				}

			}
			// commit
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public void insertIntoDenaOrderTbl(List<DenaCsvBean> orderList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		try {
			conn = JdbcConnection.getConnection();
			for (int i = 0; i < orderList.size(); i++) {
				int j = 0;
				// boolean donkonFlg = false;
				// boolean donkonOyaFlg = false;
				try {

					// if (!"0".equals(orderList.get(i).getDokonId())) {
					// donkonFlg = true;
					// if ("1".equals(orderList.get(i).getDokonsutetasu())) {
					// donkonOyaFlg = true;
					// }
					// }

					j = 0;
					sql = "INSERT INTO common_order_tbl VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(++j, orderList.get(i).getJuchubango());
					ps.setString(++j, orderList.get(i).getChumonnichiji());
					ps.setString(++j, "0");// 未入金
					// if (donkonFlg && !donkonOyaFlg) {
					// ps.setString(++j, "7");
					// } else {
					ps.setString(++j, "2");
					// }
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "DENA");
					ps.setString(++j, "123mart");
					ps.setString(++j, "");
					String kesaihoho = orderList.get(i).getKesaihouhou();
					if (kesaihoho.contains("銀行振込")) {
						ps.setString(++j, "銀行振込");
					} else if (kesaihoho.contains("代金引換")) {
						ps.setString(++j, "代金引換");
					} else if (kesaihoho.contains("クレジットカード")) {
						ps.setString(++j, "クレジットカード");
					} else {
						ps.setString(++j, kesaihoho);
					}
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, Utility.isEmptyString(orderList.get(i).getPointoriyogaku()) ? "0"
							: orderList.get(i).getPointoriyogaku());

					// あす楽希望
					ps.setString(++j, "");
					// 注文者名字
					ps.setString(++j, orderList.get(i).getChumonshameiji());
					// 注文者名前
					ps.setString(++j, orderList.get(i).getChumonshanamae());
					// 注文者名字フリガナ
					ps.setString(++j, orderList.get(i).getChumonshameijifurigana());
					// 注文者名前フリガナ
					ps.setString(++j, orderList.get(i).getChumonshanamaefurigana());
					// メールアドレス
					ps.setString(++j, orderList.get(i).getMeruadoresu());
					// 注文者誕生日
					ps.setString(++j, "");
					// 注文者郵便番号１
					ps.setString(++j, orderList.get(i).getChumonshayubinbango1());
					// 注文者郵便番号２
					ps.setString(++j, orderList.get(i).getChumonshayubinbango2());
					// 注文者住所：都道府県
					ps.setString(++j, orderList.get(i).getChumonshajushotodofuken());
					// 注文者住所：都市区
					ps.setString(++j, orderList.get(i).getChumonshajushotoshiku());
					// 注文者住所：町以降
					ps.setString(++j, "");
					// 注文者電話番号１
					ps.setString(++j, orderList.get(i).getChumonshadenwabango1());
					// 注文者電話番号２
					ps.setString(++j, orderList.get(i).getChumonshadenwabango2());
					// 注文者電話番号３
					ps.setString(++j, orderList.get(i).getChumonshadenwabango3());
					// コメント
					ps.setString(++j, orderList.get(i).getKomento());
					// メール差込文(お客様へのメッセージ)
					ps.setString(++j, "");

					// 送付先名字
					ps.setString(++j, orderList.get(i).getSofusakimeiji());
					// 送付先名前
					ps.setString(++j, orderList.get(i).getSoufusakinamae());
					// 送付先名字フリガナ
					ps.setString(++j, orderList.get(i).getSoufusakimeijifurigana());
					// 送付先名前フリガナ
					ps.setString(++j, orderList.get(i).getSoufusakimeijinamaefurigana());
					// 配送方法
					int soryo = 0;
					if (!Utility.isEmptyString(orderList.get(i).getSoryou())) {
						soryo = Integer.valueOf(orderList.get(i).getSoryou());
					}
					if (soryo > 180 || orderList.get(i).getKesaihouhou().contains("代")
							|| Integer.valueOf(orderList.get(i).getGokei()) > 5480) {
						ps.setString(++j, "宅配便");
					} else {
						ps.setString(++j, "メール便");
					}
					// 送付先郵便番号１
					ps.setString(++j, orderList.get(i).getSoufusakiyubinbango1());
					// 送付先郵便番号２
					ps.setString(++j, orderList.get(i).getSoufusakiyubinbango2());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango1());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango2());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango3());
					// 送付先住所：都道府県
					ps.setString(++j, orderList.get(i).getSoufusakijushotodofuken());
					// 送付先住所：都市区
					ps.setString(++j, orderList.get(i).getSoufusakijushotoshiku());
					// 送付先住所：町以降
					ps.setString(++j, "");

					String gokeishouhin = "0";
					String gokeizei = "0";
					String gokeisouryou = "0";
					String gokeidaibikiryou = "0";
					String seikyukingaku = "0";

					// if (!donkonFlg) {
					gokeishouhin = orderList.get(i).getGokei();
					gokeizei = Utility.isEmptyString(orderList.get(i).getShohizei()) ? "0"
							: orderList.get(i).getShohizei();

					gokeisouryou = Utility.isEmptyString(orderList.get(i).getSoryou()) ? "0"
							: orderList.get(i).getSoryou();

					gokeidaibikiryou = Utility.isEmptyString(orderList.get(i).getDaibikiryou()) ? "0"
							: orderList.get(i).getDaibikiryou();

					seikyukingaku = orderList.get(i).getSeikyukingaku();
					// } else {
					// if (donkonOyaFlg) {
					// String donkonId = orderList.get(i).getDokonId();
					// for (RakutenCsvBean bean : orderList) {
					// if (donkonId.equals(bean.getDokonId())) {
					// gokeishouhin = String.valueOf(Integer
					// .valueOf(bean
					// .getDokongokeikingaku())
					// + Integer.valueOf(gokeishouhin));
					// }
					// }
					// }
					// gokeizei = Utility.isEmptyString(orderList.get(i)
					// .getDokonshohizeigokei()) ? "0" : orderList
					// .get(i).getDokonshohizeigokei();
					//
					// gokeisouryou = Utility.isEmptyString(orderList.get(i)
					// .getDokonsoryougokei()) ? "0" : orderList
					// .get(i).getDokonsoryougokei();
					//
					// gokeidaibikiryou = Utility.isEmptyString(orderList.get(
					// i).getDokondaibikiryougokei()) ? "0"
					// : orderList.get(i).getDokondaibikiryougokei();
					//
					// seikyukingaku = Utility.isEmptyString(orderList.get(i)
					// .getDokonseikyukingaku()) ? "0" : orderList
					// .get(i).getDokonseikyukingaku();
					// }

					ps.setString(++j, gokeishouhin);
					ps.setString(++j, gokeizei);
					ps.setString(++j, gokeisouryou);
					ps.setString(++j, gokeidaibikiryou);
					ps.setString(++j, seikyukingaku);

					// 同梱ID
					ps.setString(++j, "");
					// 同梱親FLG
					ps.setString(++j, "");

					ps.setString(++j, "0");
					ps.setString(++j, date);
					ps.setString(++j, "kyo");
					ps.setString(++j, date);
					ps.setString(++j, "kyo");
					ps.setString(++j, "");
					ps.setString(++j, "0");
					ps.executeUpdate();

				} catch (MySQLIntegrityConstraintViolationException e) {
					System.out.println(orderList.get(i).getJuchubango() + "已存在，不再添加");
					continue;
				}

				// if (!donkonFlg) {
				for (int k = 0; k < orderList.get(i).getShousaiList().size(); k++) {
					DenaDetailCsvBean shousai = orderList.get(i).getShousaiList().get(k);

					String shouhinbango = shousai.getShouhinbango();
					sql = "SELECT COUNT(COMMODITY_ID) COUNT FROM TBL00012 WHERE CONCAT(COMMODITY_ID,DETAIL_NO) = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, shouhinbango.substring(0, shouhinbango.length() - 2));
					ResultSet rs999 = ps.executeQuery();
					while (rs999.next()) {
						if (rs999.getInt("count") > 0) {
							shouhinbango = shouhinbango.substring(0, shouhinbango.length() - 2);
						}
					}

					sql = "INSERT INTO common_order_detail_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(sql);

					j = 0;
					ps.setString(++j, Utility.getShoribango());

					ps.setString(++j, orderList.get(i).getJuchubango());
					// 商品名
					ps.setString(++j, shousai.getShouhinmei());
					// 商品番号
					ps.setString(++j, shouhinbango);
					// 商品URL
					ps.setString(++j, "");
					// 単価
					ps.setString(++j,
							String.valueOf(Integer.valueOf(shousai.getTanka()) / Integer.valueOf(shousai.getKosu())));
					// 個数
					ps.setString(++j, shousai.getKosu());
					// 送料込別
					ps.setString(++j, shousai.getSouryoukomibetsu());
					// 税込別
					ps.setString(++j, shousai.getZeikomibetsu());
					// 代引手数料込別
					ps.setString(++j, shousai.getDaibikitesuryoukomibetsu());
					// 項目選択肢
					ps.setString(++j, shousai.getKomokusentakushi());

					// ponint bairitu
					ps.setString(++j, shousai.getPointobairitsu());

					// nouki
					ps.setString(++j, shousai.getNouki());

					ps.executeUpdate();
				}
				// } else if (donkonFlg && donkonOyaFlg) {
				// List<RakutenDetailCsvBean> shousaiDonkonList = new
				// ArrayList<RakutenDetailCsvBean>();
				// String donkonId = orderList.get(i).getDokonId();
				// for (int m = 0; m < orderList.size(); m++) {
				// if (donkonId.equals(orderList.get(m).getDokonId())) {
				// shousaiDonkonList.addAll(orderList.get(m)
				// .getShousaiList());
				// }
				// }
				// for (int k = 0; k < shousaiDonkonList.size(); k++) {
				// RakutenDetailCsvBean shousai = shousaiDonkonList.get(k);
				// j = 0;
				// sql =
				// "INSERT INTO common_order_detail_tbl
				// VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
				// ps = conn.prepareStatement(sql);
				// ps.setString(++j, Utility.getShoribango());
				//
				// ps.setString(++j, orderList.get(i).getJuchubango());
				// // 商品名
				// ps.setString(++j, shousai.getShouhinmei());
				// // 商品番号
				// ps.setString(++j, shousai.getShouhinbango());
				// // 商品URL
				// ps.setString(++j, shousai.getShouhinURL());
				// // 単価
				// ps.setString(++j, shousai.getTanka());
				// // 個数
				// ps.setString(++j, shousai.getKosu());
				// // 送料込別
				// ps.setString(++j, shousai.getSouryoukomibetsu());
				// // 税込別
				// ps.setString(++j, shousai.getZeikomibetsu());
				// // 代引手数料込別
				// ps.setString(++j, shousai.getDaibikitesuryoukomibetsu());
				// // 項目選択肢
				// ps.setString(++j, shousai.getKomokusentakushi());
				//
				// // ponint bairitu
				// ps.setString(++j, shousai.getPointobairitsu());
				//
				// ps.executeUpdate();
				// }
				// }
			}
			// commit
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public void insertIntoqoo10OrderTbl(List<Qoo10CsvBean> orderList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		try {
			conn = JdbcConnection.getConnection();
			for (int i = 0; i < orderList.size(); i++) {
				int j = 0;
				try {

					j = 0;
					sql = "INSERT INTO common_order_tbl VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(++j, orderList.get(i).getJuchubango());
					ps.setString(++j, orderList.get(i).getChumonnichiji());
					ps.setString(++j, "0");// 未入金
					ps.setString(++j, "2");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "0");
					ps.setString(++j, "qoo10");
					ps.setString(++j, "xandw");
					ps.setString(++j, "");
					String kesaihoho = orderList.get(i).getKesaihouhou();
					ps.setString(++j, kesaihoho);
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, "");
					ps.setString(++j, Utility.isEmptyString(orderList.get(i).getPointoriyogaku()) ? "0"
							: orderList.get(i).getPointoriyogaku());

					// あす楽希望
					ps.setString(++j, "");
					// 注文者名字
					ps.setString(++j, orderList.get(i).getChumonshameiji());
					// 注文者名前
					ps.setString(++j, orderList.get(i).getChumonshanamae());
					// 注文者名字フリガナ
					ps.setString(++j, orderList.get(i).getChumonshameijifurigana());
					// 注文者名前フリガナ
					ps.setString(++j, orderList.get(i).getChumonshanamaefurigana());
					// メールアドレス
					ps.setString(++j, orderList.get(i).getMeruadoresu());
					// 注文者誕生日
					ps.setString(++j, "");
					// 注文者郵便番号１
					ps.setString(++j, orderList.get(i).getChumonshayubinbango1());
					// 注文者郵便番号２
					ps.setString(++j, orderList.get(i).getChumonshayubinbango2());
					// 注文者住所：都道府県
					ps.setString(++j, orderList.get(i).getChumonshajushotodofuken());
					// 注文者住所：都市区
					ps.setString(++j, orderList.get(i).getChumonshajushotoshiku());
					// 注文者住所：町以降
					ps.setString(++j, "");
					// 注文者電話番号１
					ps.setString(++j, orderList.get(i).getChumonshadenwabango1());
					// 注文者電話番号２
					ps.setString(++j, orderList.get(i).getChumonshadenwabango2());
					// 注文者電話番号３
					ps.setString(++j, orderList.get(i).getChumonshadenwabango3());
					// コメント
					ps.setString(++j, orderList.get(i).getKomento());
					// メール差込文(お客様へのメッセージ)
					ps.setString(++j, "");

					// 送付先名字
					ps.setString(++j, orderList.get(i).getSofusakimeiji());
					// 送付先名前
					ps.setString(++j, orderList.get(i).getSoufusakinamae());
					// 送付先名字フリガナ
					ps.setString(++j, orderList.get(i).getSoufusakimeijifurigana());
					// 送付先名前フリガナ
					ps.setString(++j, orderList.get(i).getSoufusakimeijinamaefurigana());
					// 配送方法
					int soryo = 0;
					if (!Utility.isEmptyString(orderList.get(i).getSoryou())) {
						soryo = Integer.valueOf(orderList.get(i).getSoryou());
					}
					if (soryo > 180 || orderList.get(i).getKesaihouhou().contains("代")
							|| Integer.valueOf(orderList.get(i).getGokei()) > 5480) {
						ps.setString(++j, "宅配便");
					} else {
						ps.setString(++j, "メール便");
					}
					// 送付先郵便番号１
					ps.setString(++j, orderList.get(i).getSoufusakiyubinbango1());
					// 送付先郵便番号２
					ps.setString(++j, orderList.get(i).getSoufusakiyubinbango2());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango1());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango2());
					ps.setString(++j, orderList.get(i).getSoufusakidenwabango3());
					// 送付先住所：都道府県
					ps.setString(++j, orderList.get(i).getSoufusakijushotodofuken());
					// 送付先住所：都市区
					ps.setString(++j, orderList.get(i).getSoufusakijushotoshiku());
					// 送付先住所：町以降
					ps.setString(++j, orderList.get(i).getSoufusakijusho3());

					String gokeishouhin = "0";
					String gokeizei = "0";
					String gokeisouryou = "0";
					String gokeidaibikiryou = "0";
					String seikyukingaku = "0";

					gokeishouhin = orderList.get(i).getGokei();
					gokeizei = Utility.isEmptyString(orderList.get(i).getShohizei()) ? "0"
							: orderList.get(i).getShohizei();

					gokeisouryou = Utility.isEmptyString(orderList.get(i).getSoryou()) ? "0"
							: orderList.get(i).getSoryou();

					gokeidaibikiryou = Utility.isEmptyString(orderList.get(i).getDaibikiryou()) ? "0"
							: orderList.get(i).getDaibikiryou();

					seikyukingaku = orderList.get(i).getSeikyukingaku();
					ps.setString(++j, gokeishouhin);
					ps.setString(++j, gokeizei);
					ps.setString(++j, gokeisouryou);
					ps.setString(++j, gokeidaibikiryou);
					ps.setString(++j, seikyukingaku);

					// 同梱ID
					ps.setString(++j, "0");
					// 同梱親FLG
					ps.setString(++j, "");

					ps.setString(++j, "0");
					ps.setString(++j, date);
					ps.setString(++j, "kyo");
					ps.setString(++j, date);
					ps.setString(++j, "kyo");
					ps.setString(++j, "");
					ps.setString(++j, "0");
					ps.executeUpdate();

				} catch (MySQLIntegrityConstraintViolationException e) {
					System.out.println(orderList.get(i).getJuchubango() + "已存在，不再添加");
					continue;
				}

				for (int k = 0; k < orderList.get(i).getShousaiList().size(); k++) {
					Qoo10DetailCsvBean shousai = orderList.get(i).getShousaiList().get(k);

					String shouhinbango = shousai.getShouhinbango();
					sql = "SELECT COUNT(COMMODITY_ID) COUNT FROM TBL00012 WHERE CONCAT(COMMODITY_ID,DETAIL_NO) = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, shouhinbango.substring(0, shouhinbango.length() - 2));
					ResultSet rs999 = ps.executeQuery();
					while (rs999.next()) {
						if (rs999.getInt("count") > 0) {
							shouhinbango = shouhinbango.substring(0, shouhinbango.length() - 2);
						}
					}

					sql = "INSERT INTO common_order_detail_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
					ps = conn.prepareStatement(sql);

					j = 0;
					ps.setString(++j, Utility.getShoribango());

					ps.setString(++j, orderList.get(i).getJuchubango());
					// 商品名
					ps.setString(++j, shousai.getShouhinmei());
					// 商品番号
					ps.setString(++j, shouhinbango);
					// 商品URL
					ps.setString(++j, "");
					// 単価
					ps.setString(++j,
							String.valueOf(Integer.valueOf(shousai.getTanka()) / Integer.valueOf(shousai.getKosu())));
					// 個数
					ps.setString(++j, shousai.getKosu());
					// 送料込別
					ps.setString(++j, shousai.getSouryoukomibetsu());
					// 税込別
					ps.setString(++j, shousai.getZeikomibetsu());
					// 代引手数料込別
					ps.setString(++j, "0");
					// 項目選択肢
					ps.setString(++j, shousai.getKomokusentakushi());

					// ponint bairitu
					ps.setString(++j, "0");

					// nouki
					ps.setString(++j, "");

					ps.executeUpdate();
				}

				sql = "INSERT INTO TBL00027 VALUES(?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, orderList.get(i).getJuchubango());
				ps.setString(2, orderList.get(i).getHasoyakusokubi().split(" ")[0].replace("/", "-"));
				ps.setString(3, Utility.getDateTime());
				ps.setString(4, Utility.getUser());
				ps.setString(5, Utility.getDateTime());
				ps.setString(6, Utility.getUser());
				ps.execute();
			}

			// commit
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public void insertIntoOtherOrderTbl(List<OtherCsvBean> orderList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		try {
			conn = JdbcConnection.getConnection();
			for (int i = 0; i < orderList.size(); i++) {
				int j = 0;
				try {
					
					int count = 0;
					sql = "SELECT COUNT(*) COUNT FROM common_order_tbl WHERE CHUMONBANGO = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, orderList.get(i).getJuchubango());
					rs = ps.executeQuery();
					while (rs.next()) {
						count = rs.getInt("COUNT");
					}
					if (count > 0) {
						sql = "UPDATE common_order_tbl SET BIKO = ?, UPDATE_TIME = ? , UPDATE_USER = ? WHERE CHUMONBANGO = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1,orderList.get(i).getKomento());
						ps.setString(2, date);
						ps.setString(3, "updater");
						ps.setString(4, orderList.get(i).getJuchubango());
						ps.executeUpdate();
					} else {
						j = 0;
						sql = "INSERT INTO common_order_tbl VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
						ps = conn.prepareStatement(sql);
						ps.setString(++j, orderList.get(i).getJuchubango());
						ps.setString(++j, orderList.get(i).getChumonnichiji());
						ps.setString(++j, "0");// 未入金
						ps.setString(++j, "2");
						ps.setString(++j, "0");
						ps.setString(++j, "0");
						ps.setString(++j, "0");
						ps.setString(++j, "0");
						ps.setString(++j, "0");
						ps.setString(++j, orderList.get(i).getPlatform());
						ps.setString(++j, orderList.get(i).getShopname());
						ps.setString(++j, orderList.get(i).getKomento());
						String kesaihoho = orderList.get(i).getKesaihouhou();
						ps.setString(++j, kesaihoho);
						ps.setString(++j, "");
						ps.setString(++j, "");
						ps.setString(++j, "");
						ps.setString(++j, "");
						ps.setString(++j, "");
						ps.setString(++j, Utility.isEmptyString(orderList.get(i).getPointoriyogaku()) ? "0"
								: orderList.get(i).getPointoriyogaku());

						// あす楽希望
						ps.setString(++j, "0");
						// 注文者名字
						ps.setString(++j, orderList.get(i).getChumonshameiji());
						// 注文者名前
						ps.setString(++j, orderList.get(i).getChumonshanamae());
						// 注文者名字フリガナ
						ps.setString(++j, orderList.get(i).getChumonshameijifurigana());
						// 注文者名前フリガナ
						ps.setString(++j, orderList.get(i).getChumonshanamaefurigana());
						// メールアドレス
						ps.setString(++j, orderList.get(i).getMeruadoresu());
						// 注文者誕生日
						ps.setString(++j, orderList.get(i).getChumonshatanjoubi());
						// 注文者郵便番号１
						ps.setString(++j, orderList.get(i).getChumonshayubinbango1());
						// 注文者郵便番号２
						ps.setString(++j, orderList.get(i).getChumonshayubinbango2());
						// 注文者住所：都道府県
						ps.setString(++j, orderList.get(i).getChumonshajushotodofuken());
						// 注文者住所：都市区
						ps.setString(++j, orderList.get(i).getChumonshajushotoshiku());
						// 注文者住所：町以降
						ps.setString(++j, "");
						// 注文者電話番号１
						ps.setString(++j, orderList.get(i).getChumonshadenwabango1());
						// 注文者電話番号２
						ps.setString(++j, orderList.get(i).getChumonshadenwabango2());
						// 注文者電話番号３
						ps.setString(++j, orderList.get(i).getChumonshadenwabango3());
						// コメント
						ps.setString(++j, orderList.get(i).getKomento());
						// メール差込文(お客様へのメッセージ)
						ps.setString(++j, "");

						// 送付先名字
						ps.setString(++j, orderList.get(i).getSofusakimeiji());
						// 送付先名前
						ps.setString(++j, orderList.get(i).getSoufusakinamae());
						// 送付先名字フリガナ
						ps.setString(++j, orderList.get(i).getSoufusakimeijifurigana());
						// 送付先名前フリガナ
						ps.setString(++j, orderList.get(i).getSoufusakimeijinamaefurigana());
						// 配送方法
						int soryo = 0;
						if (!Utility.isEmptyString(orderList.get(i).getSoryou())) {
							soryo = Integer.valueOf(orderList.get(i).getSoryou());
						}
						if (soryo > 180 || orderList.get(i).getKesaihouhou().contains("代")
								|| Integer.valueOf(orderList.get(i).getGokei()) > 5480) {
							ps.setString(++j, "宅配便");
						} else {
							ps.setString(++j, "メール便");
						}
						// 送付先郵便番号１
						ps.setString(++j, orderList.get(i).getSoufusakiyubinbango1());
						// 送付先郵便番号２
						ps.setString(++j, orderList.get(i).getSoufusakiyubinbango2());
						ps.setString(++j, orderList.get(i).getSoufusakidenwabango1());
						ps.setString(++j, orderList.get(i).getSoufusakidenwabango2());
						ps.setString(++j, orderList.get(i).getSoufusakidenwabango3());
						// 送付先住所：都道府県
						ps.setString(++j, orderList.get(i).getSoufusakijushotodofuken());
						// 送付先住所：都市区
						ps.setString(++j, orderList.get(i).getSoufusakijushotoshiku());
						// 送付先住所：町以降
						ps.setString(++j, orderList.get(i).getSoufusakijusho3());

						String gokeishouhin = "0";
						String gokeizei = "0";
						String gokeisouryou = "0";
						String gokeidaibikiryou = "0";
						String seikyukingaku = "0";

						gokeishouhin = orderList.get(i).getGokei();
						gokeizei = Utility.isEmptyString(orderList.get(i).getShohizei()) ? "0"
								: orderList.get(i).getShohizei();

						gokeisouryou = Utility.isEmptyString(orderList.get(i).getSoryou()) ? "0"
								: orderList.get(i).getSoryou();

						gokeidaibikiryou = Utility.isEmptyString(orderList.get(i).getDaibikiryou()) ? "0"
								: orderList.get(i).getDaibikiryou();

						seikyukingaku = orderList.get(i).getSeikyukingaku();
						ps.setString(++j, gokeishouhin);
						ps.setString(++j, gokeizei);
						ps.setString(++j, gokeisouryou);
						ps.setString(++j, gokeidaibikiryou);
						ps.setString(++j, seikyukingaku);

						// 同梱ID
						ps.setString(++j, "0");
						// 同梱親FLG
						ps.setString(++j, "0");

						ps.setString(++j, "0");
						ps.setString(++j, date);
						ps.setString(++j, "kyo");
						ps.setString(++j, date);
						ps.setString(++j, "kyo");
						ps.setString(++j, "");
						ps.setString(++j, "0");
						ps.executeUpdate();
						
						for (int k = 0; k < orderList.get(i).getShousaiList().size(); k++) {
							OtherDetailCsvBean shousai = orderList.get(i).getShousaiList().get(k);

							String shouhinbango = shousai.getShouhinbango();
//							sql = "SELECT COUNT(COMMODITY_ID) COUNT FROM TBL00012 WHERE CONCAT(COMMODITY_ID,DETAIL_NO) = ?";
//							ps = conn.prepareStatement(sql);
//							ps.setString(1, shouhinbango.substring(0, shouhinbango.length() - 2));
//							ResultSet rs999 = ps.executeQuery();
//							while (rs999.next()) {
//								if (rs999.getInt("count") > 0) {
//									shouhinbango = shouhinbango.substring(0, shouhinbango.length() - 2);
//								}
//							}
					
							sql = "INSERT INTO common_order_detail_tbl VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
							ps = conn.prepareStatement(sql);

							j = 0;
							ps.setString(++j, Utility.getShoribango());

							ps.setString(++j, orderList.get(i).getJuchubango());
							// 商品名
							ps.setString(++j, shousai.getShouhinmei());
							// 商品番号
							ps.setString(++j, shouhinbango);
							// 商品URL
							ps.setString(++j, "");
							// 単価
							ps.setString(++j, "1");
							// 個数
							ps.setString(++j, shousai.getKosu());
							// 送料込別
							ps.setString(++j, shousai.getSouryoukomibetsu());
							// 税込別
							ps.setString(++j, shousai.getZeikomibetsu());
							// 代引手数料込別
							ps.setString(++j, "0");
							// 項目選択肢
							ps.setString(++j, shousai.getKomokusentakushi());

							// ponint bairitu
							ps.setString(++j, "0");

							// nouki
							ps.setString(++j, "");

							ps.executeUpdate();
							
							if (shouhinbango.indexOf("-") != -1) {
								shouhinbango = shouhinbango.substring(0, shouhinbango.indexOf("-"));
							}

							count = 0;
							sql = "SELECT COUNT(*) COUNT FROM TBL00011 WHERE COMMODITY_ID = ? AND CATEGORY_ID = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, shouhinbango);
							ps.setString(2, "100001");
							rs = ps.executeQuery();
							while (rs.next()) {
								count = rs.getInt("COUNT");
							}
							if (count > 0) {
								sql = "UPDATE TBL00011 SET UPDATE_TIME = ? , UPDATE_USER = ? WHERE COMMODITY_ID = ? AND CATEGORY_ID = ?";
								ps = conn.prepareStatement(sql);
								ps.setString(1, date);
								ps.setString(2, "updater");
								ps.setString(3,shouhinbango);
								ps.setString(4, "100001");
								
								ps.executeUpdate();
							} else {
								sql = "INSERT INTO tbl00011(COMMODITY_ID,CATEGORY_ID,CHINESE_NAME,JAPANESE_NAME,SOURCE,RESP_PERSON,COMMODITY_URL,PIC_URL,REMARKS,DEL_FLG,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER,STATUS)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								ps = conn.prepareStatement(sql);
								ps.setString(1, shouhinbango);
								//
								ps.setString(2, "100001");
								ps.setString(3, shousai.getShouhinmei());
								ps.setString(4, shousai.getShouhinmei());
								ps.setString(5, "");
								ps.setString(6, "");
								ps.setString(7, "");
								ps.setString(8, "");
								ps.setString(9, "");
								ps.setString(10, "0");
								ps.setString(11, date);
								ps.setString(12, "kyo");
								ps.setString(13, date);
								ps.setString(14, "kyo");
								ps.setString(15, "00");
								ps.execute();
							}
							
							count = 0;
							sql = "SELECT COUNT(*) COUNT FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, shouhinbango);
							String detailNo = "-0-0";
							if (shousai.getShouhinbango().indexOf("-") != -1) {
								detailNo = shousai.getShouhinbango().substring(shousai.getShouhinbango().indexOf("-"));
							}
							ps.setString(2, detailNo);
							rs = ps.executeQuery();
							while (rs.next()) {
								count = rs.getInt("COUNT");
							}
							if (count > 0) {
								sql = "UPDATE TBL00012 SET UPDATE_TIME = ? , UPDATE_USER = ? WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
								ps = conn.prepareStatement(sql);
								ps.setString(1, date);
								ps.setString(2, "updater");
								ps.setString(3,shouhinbango);
								ps.setString(4, detailNo);
								
								ps.executeUpdate();
							} else {
								sql = SqlUtility.getSql("SQLR0001012");
								ps = conn.prepareStatement(sql);
								ps.setString(1,	shouhinbango);
								ps.setString(2, detailNo);
								ps.setString(3, "");
								ps.setString(4, "");
								ps.setString(5, null);
								ps.setString(6, "1");
								ps.setString(7, "0");
								ps.setString(8, "0");
								ps.setString(9, "0");
								ps.setString(10, null);
								ps.setString(11, null);
								ps.setString(12, "");
								ps.setString(13, "0");
								ps.setString(14, date);
								ps.setString(15, "kyo");
								ps.setString(16, null);
								ps.setString(17, null);
								ps.setString(18, "");
								ps.setString(19, "");
								ps.execute();
							}
							
							
							String maxBarcode = null;
							sql = "SELECT COUNT(*) COUNT FROM TBL00016 WHERE COMMODITY_ID = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, shouhinbango + detailNo);
							rs = ps.executeQuery();
							
							count = 0;
							while (rs.next()) {
								count = rs.getInt("COUNT");
							}
							if (count == 0) {
								sql = "SELECT MAX(BARCODE)+1 MAX_BARCODE FROM TBL00016";
								ps = conn.prepareStatement(sql);
								rs = ps.executeQuery();
								while (rs.next()) {
									maxBarcode = rs.getString("MAX_BARCODE");
								}

								sql = "INSERT INTO TBL00016 VALUES(?,?)";
								ps = conn.prepareStatement(sql);
								ps.setString(1, shouhinbango + detailNo);
								ps.setString(2, maxBarcode);
								ps.execute();
							}
						}

						int count027 = 0;
						sql = "SELECT COUNT(*) COUNT FROM TBL00027 WHERE CHUMONBANGO = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, orderList.get(i).getJuchubango());
						rs = ps.executeQuery();
						while (rs.next()) {
							count027 = rs.getInt("COUNT");
						}
						if (count027 > 0) {
							sql = "UPDATE TBL00027 SET UPDATE_TIME = ? , UPDATE_USER = ? WHERE CHUMONBANGO = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, date);
							ps.setString(2, "updater");
							ps.setString(3,orderList.get(i).getJuchubango());
							ps.executeUpdate();
						} else {
							sql = "INSERT INTO TBL00027 VALUES(?,?,?,?,?,?)";
							ps = conn.prepareStatement(sql);
							ps.setString(1, orderList.get(i).getJuchubango());
							ps.setString(2, orderList.get(i).getHasoyakusokubi().split(" ")[0].replace("/", "-"));
							ps.setString(3, Utility.getDateTime());
							ps.setString(4, Utility.getUser());
							ps.setString(5, Utility.getDateTime());
							ps.setString(6, Utility.getUser());
							ps.execute();
						}
					}

				} catch (MySQLIntegrityConstraintViolationException e) {
					System.out.println(orderList.get(i).getJuchubango() + "已存在，不再添加");
					continue;
				}
				
			}

			// commit
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			ps.close();
			conn.close();
		}
	}
	
	public List<OrderList> getOrderListByBango(List<String> juchubangoList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			List<OrderList> orderList = new ArrayList<OrderList>();
			OrderList Order = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM common_order_tbl WHERE CHUMONBANGO = ? ORDER BY str_to_date(CHUMONNICHIJI,'%Y-%m-%d %H:%i:%s') DESC";
			for (String juchubango : juchubangoList) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, juchubango);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					Order = new OrderList();
					orderList.add(Order);
					Order.setSite(rs.getString("SITE"));
					Order.setTenpo(rs.getString("SHOP"));
					Order.setChumonbango(rs.getString("CHUMONBANGO"));
					Order.setChumonsha(rs.getString("CHUMONSHAMEIJI") + rs.getString("CHUMONSHANAMAE") + "("
							+ (Utility.isEmptyString(rs.getString("CHUMONSHAMEIJIFURIGANA")) ? ""
									: rs.getString("CHUMONSHAMEIJIFURIGANA"))
							+ " " + (Utility.isEmptyString(rs.getString("CHUMONSHANAMAEFURIGANA")) ? ""
									: rs.getString("CHUMONSHANAMAEFURIGANA"))
							+ ")");
					Order.setDokonFlg(rs.getString("DONKONID"));
					Order.setMobileMailFlg(rs.getString("MERUADORESU").contains("@pc.") ? "0" : "1");
					Order.setOshiharaihoho(rs.getString("OSHIHARAISTS"));
					Order.setGokeikingaku(Utility.numberFormat(rs.getString("GOKEISHOHIN")));
					Order.setSeikyujingaku(Utility.numberFormat(rs.getString("SEIKYUKINGAKU")));
					Order.setHaisohoho(rs.getString("HAISOUHOHO"));
					Order.setBiko(rs.getString("BIKO"));
					Order.setChumonichiji(rs.getString("CHUMONNICHIJI"));
					Order.setComento(rs.getString("KOMENTO"));
					Order.setHasoshahenokomento(rs.getString("HASSOUSHAHENOKOMENTO"));

					String chumonsts1 = OrderCommon.getStatus1Name(rs.getString("CHUMONSTS1"));
					String chumonsts2 = OrderCommon.getStatus2Name("2", rs.getString("CHUMONSTS2"));
					String chumonsts3 = OrderCommon.getStatus2Name("3", rs.getString("CHUMONSTS3"));
					String chumonsts4 = OrderCommon.getStatus2Name("4", rs.getString("CHUMONSTS4"));
					String chumonsts5 = OrderCommon.getStatus2Name("5", rs.getString("CHUMONSTS5"));
					String chumonsts6 = OrderCommon.getStatus2Name("6", rs.getString("CHUMONSTS6"));

					Order.setChumonsts1(chumonsts1);
					Order.setChumonsts2(chumonsts2);
					Order.setChumonsts3(chumonsts3);
					Order.setChumonsts4(chumonsts4);
					Order.setChumonsts5(chumonsts5);
					Order.setChumonsts6(chumonsts6);

					break;
				}
			}
			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public List<OrderList> getOrderList(F100101 f100101, String type) throws Exception {

		String kikanStart = null;
		String kikanEnd = null;
		String keyword = null;
		String kewordType = null;
		String chumonbango = null;
		String chumonEmail = null;
		String chumonshanamae = null;
		String sofusakinamae = null;
		String tenpobetsu = null;
		String site = null;
		String denwabango = null;
		String denwabangoType = null;
		String oshiharaihoho = null;
		boolean chumonStsSearch0 = false;
		boolean chumonStsSearch1 = false;
		boolean chumonStsSearch2 = false;
		boolean chumonStsSearch3 = false;
		boolean chumonStsSearch4 = false;
		boolean chumonStsSearch5 = false;
		boolean chumonStsSearch6 = false;
		boolean chumonStsSearch7 = false;
		boolean chumonStsSearch8 = false;
		boolean chumonStsSearch9 = false;
		boolean searchKeywordCondition = false;

		if (f100101 != null) {
			kikanStart = Utility.strTrim(f100101.getKikanStart());
			kikanEnd = Utility.strTrim(f100101.getKikanEnd());
			keyword = Utility.strTrim(f100101.getKeyword());
			kewordType = Utility.strTrim(f100101.getKewordType());
			chumonbango = Utility.strTrim(f100101.getChumonbango());
			chumonEmail = Utility.strTrim(f100101.getChumonEmail());
			chumonshanamae = Utility.strTrim(f100101.getChumonshanamae());
			sofusakinamae = Utility.strTrim(f100101.getSofusakinamae());
			tenpobetsu = Utility.strTrim(f100101.getTenpobetsu());
			denwabango = Utility.strTrim(f100101.getDenwabango());
			denwabangoType = Utility.strTrim(f100101.getDenwabangoType());
			chumonStsSearch0 = f100101.isChumonStsSearch0();
			chumonStsSearch1 = f100101.isChumonStsSearch1();
			chumonStsSearch2 = f100101.isChumonStsSearch2();
			chumonStsSearch3 = f100101.isChumonStsSearch3();
			chumonStsSearch4 = f100101.isChumonStsSearch4();
			chumonStsSearch5 = f100101.isChumonStsSearch5();
			chumonStsSearch6 = f100101.isChumonStsSearch6();
			chumonStsSearch7 = f100101.isChumonStsSearch7();
			chumonStsSearch8 = f100101.isChumonStsSearch8();
			chumonStsSearch9 = f100101.isChumonStsSearch9();
			oshiharaihoho = f100101.getOshiharaihoho();
			site = f100101.getSite();
			searchKeywordCondition = f100101.isSearchKeywordCondition();
		}

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			List<OrderList> orderList = new ArrayList<OrderList>();
			OrderList Order = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM common_order_tbl T1 LEFT JOIN common_order_detail_tbl T2 ON T1.CHUMONBANGO = T2.JUCHUBANGO LEFT JOIN HENPIN_TBL T3 ON T1.CHUMONBANGO = T3.JUCHUBANGO LEFT JOIN TUIKA_HASOU_TBL T4 ON T1.CHUMONBANGO = T4.JUCHUBANGO WHERE 1=1 ";

			if (Utility.isEmptyString(kikanStart)) {
				kikanStart = "0";
			}
			if (Utility.isEmptyString(kikanEnd)) {
				kikanEnd = "999999999";
			}
			if (!Utility.isEmptyString(chumonshanamae)) {
				sql += " AND (CONCAT(T1.CHUMONSHAMEIJI,T1.CHUMONSHANAMAE) LIKE '%"
						+ chumonshanamae.replace("　", "").replace(" ", "").trim()
						+ "%' OR CONCAT(T1.CHUMONSHAMEIJIFURIGANA,T1.CHUMONSHANAMAEFURIGANA) LIKE '%"
						+ chumonshanamae.replace("　", "").replace(" ", "").trim() + "%')";
			}
			if (!Utility.isEmptyString(sofusakinamae)) {
				sql += " AND (CONCAT(T1.SOFUSAKIMEIJI,T1.SOUFUSAKINAMAE) LIKE '%"
						+ sofusakinamae.replace("　", "").replace(" ", "").trim()
						+ "%' OR CONCAT(T1.SOUFUSAKIMEIJIFURIGANA,T1.SOUFUSAKIMEIJINAMAEFURIGANA) LIKE '%"
						+ sofusakinamae.replace("　", "").replace(" ", "").trim() + "%')";
			}
			if (!Utility.isEmptyString(keyword)) {
				if ("0".equals(kewordType)) {
					sql += " AND T2.SHOUHINMEI LIKE '%" + keyword + "%'";
				} else if ("1".equals(kewordType)) {
					sql += " AND T2.SHOUHINBANGO LIKE '%" + keyword + "%'";
				} else if ("2".equals(kewordType)) {
					sql += " AND T1.BIKO LIKE  '%" + keyword + "%'";
				}
			}
			if (!Utility.isEmptyString(chumonbango)) {
				sql += " AND T1.CHUMONBANGO = '" + chumonbango + "'";
			}
			if (!Utility.isEmptyString(chumonEmail)) {
				sql += " AND T1.MERUADORESU = '" + chumonEmail + "'";
			}
			if (!Utility.isEmptyString(tenpobetsu)) {
				sql += " AND T1.SHOP = '" + tenpobetsu + "'";
			}
			if (!Utility.isEmptyString(site)) {
				sql += " AND T1.SITE = '" + site + "'";
			}
			if (!Utility.isEmptyString(denwabango)) {
				if ("0".equals(denwabangoType)) {
					sql += " AND CONCAT(T1.CHUMONSHADENWABANGO1,T1.CHUMONSHADENWABANGO2,T1.CHUMONSHADENWABANGO3) = '"
							+ denwabango.replace("-", "") + "'";
				} else if ("1".equals(denwabangoType)) {
					sql += " AND CONCAT(T1.SOFUSAKIDENWABANGO1,T1.SOFUSAKIDENWABANGO2,T1.SOFUSAKIDENWABANGO3) = '"
							+ denwabango.replace("-", "") + "'";
				}
			}
			if (!Utility.isEmptyString(oshiharaihoho)) {
				sql += " AND T1.OSHIHARAISTS = '" + oshiharaihoho + "'";
			}

			if (chumonStsSearch0) {
				sql += " AND T1.CHUMONSTS1 = '" + "1" + "'";
			}
			if (chumonStsSearch1) {
				sql += " AND T1.CHUMONSTS1 = '" + "2" + "'";
			}
			if (chumonStsSearch2) {
				sql += " AND T1.CHUMONSTS1 = '" + "3" + "'";
			}
			if (chumonStsSearch7) {
				sql += " AND T1.CHUMONSTS1 = '" + "4" + "'";
			}
			if (chumonStsSearch8) {
				sql += " AND T1.CHUMONSTS1 = '" + "6" + "'";
			}
			if (chumonStsSearch9) {
				sql += " AND T1.CHUMONSTS1 = '" + "5" + "'";
			}
			if (chumonStsSearch3) {
				sql += " AND T1.CHUMONSTS2 = '" + "1" + "'";
			}
			if (chumonStsSearch4) {
				sql += " AND T1.CHUMONSTS3 = '" + "1" + "'";
			}
			if (chumonStsSearch5) {
				sql += " AND (T3.HENPINSTATUS = '0' OR T3.HENPINSTATUS = '1' OR T3.HENPINSTATUS = '2')";
			}
			if (chumonStsSearch6) {
				sql += " AND (T4.STATUS = '0' OR T4.STATUS = '1')";
			}

			if (!"search".equals(type)) {
				sql += " AND T1.CHUMONSTS1 = '" + "2" + "'";
			}

			sql += " AND left(REPLACE(CHUMONNICHIJI,'-',''),8) >= " + kikanStart.replace("-", "");
			sql += " AND left(REPLACE(CHUMONNICHIJI,'-',''),8)<= " + kikanEnd.replace("-", "");

			sql += " ORDER BY str_to_date(T1.CHUMONNICHIJI,'%Y-%m-%d %H:%i:%s')";
			
			if (searchKeywordCondition) {
				sql = sql.replace("common_order_detail_tbl", "(select * from common_order_detail_tbl group by JUCHUBANGO having count(*) =1)");
			}

			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			String chumonbangoComp = "";
			while (rs.next()) {
				if (chumonbangoComp.equals(rs.getString("T1.CHUMONBANGO"))) {
					continue;
				}
				chumonbangoComp = rs.getString("T1.CHUMONBANGO");
				Order = new OrderList();
				Order.setSite(rs.getString("T1.SITE"));
				Order.setTenpo(rs.getString("T1.SHOP"));
				Order.setChumonbango(rs.getString("T1.CHUMONBANGO"));
				Order.setChumonsha(rs.getString("T1.CHUMONSHAMEIJI") + rs.getString("T1.CHUMONSHANAMAE") + "("
						+ (Utility.isEmptyString(rs.getString("T1.CHUMONSHAMEIJIFURIGANA")) ? ""
								: rs.getString("T1.CHUMONSHAMEIJIFURIGANA"))
						+ " " + (Utility.isEmptyString(rs.getString("T1.CHUMONSHANAMAEFURIGANA")) ? ""
								: rs.getString("T1.CHUMONSHANAMAEFURIGANA"))
						+ ")");
				Order.setDokonFlg(rs.getString("T1.DONKONID"));
				Order.setMobileMailFlg(rs.getString("T1.MERUADORESU").contains("@pc.") ? "0" : "1");
				Order.setOshiharaihoho(rs.getString("T1.OSHIHARAISTS"));
				Order.setGokeikingaku(Utility.numberFormat(rs.getString("T1.GOKEISHOHIN")));
				Order.setSeikyujingaku(Utility.numberFormat(rs.getString("T1.SEIKYUKINGAKU")));
				Order.setHaisohoho(rs.getString("T1.HAISOUHOHO"));
				Order.setBiko(rs.getString("T1.BIKO"));
				Order.setChumonichiji(rs.getString("T1.CHUMONNICHIJI"));

				Order.setComento(rs.getString("T1.KOMENTO"));

				String chumonsts1 = OrderCommon.getStatus1Name(rs.getString("T1.CHUMONSTS1"));
				String chumonsts2 = OrderCommon.getStatus2Name("2", rs.getString("T1.CHUMONSTS2"));
				String chumonsts3 = OrderCommon.getStatus2Name("3", rs.getString("T1.CHUMONSTS3"));
				String chumonsts4 = OrderCommon.getStatus2Name("4", rs.getString("T1.CHUMONSTS4"));
				String chumonsts5 = OrderCommon.getStatus2Name("5", rs.getString("T1.CHUMONSTS5"));
				String chumonsts6 = OrderCommon.getStatus2Name("6", rs.getString("T1.CHUMONSTS6"));

				Order.setChumonsts1(chumonsts1);
				Order.setChumonsts2(chumonsts2);
				Order.setChumonsts3(chumonsts3);
				Order.setChumonsts4(chumonsts4);
				Order.setChumonsts5(chumonsts5);
				Order.setChumonsts6(chumonsts6);

				orderList.add(Order);
			}

			return orderList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public F100102 getOrderInfo(String orderNo) throws Exception {
		F100102 f100102 = new F100102();
		List<ShohinList> shohinList = new ArrayList<ShohinList>();
		f100102.setShohinList(shohinList);

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ShohinList shohin = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM common_order_tbl T1 LEFT JOIN common_order_detail_tbl T3 ON T1.CHUMONBANGO = T3.JUCHUBANGO WHERE T1.CHUMONBANGO = ? ";

			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				f100102.setChumonsts1(rs.getString("T1.CHUMONSTS1"));
				f100102.setChumonsts2(rs.getString("T1.CHUMONSTS2"));
				f100102.setChumonsts3(rs.getString("T1.CHUMONSTS3"));
				f100102.setChumonsts4(rs.getString("T1.CHUMONSTS4"));
				f100102.setChumonsts5(rs.getString("T1.CHUMONSTS5"));
				f100102.setChumonsts6(rs.getString("T1.CHUMONSTS6"));
				// f100102.setDokonFlg(rs.getString("T2.DOKONSUTETASU"));
				f100102.setOkurisakuchuiFlg((rs.getString("T1.CHUMONSHAJUSHOTODOFUKEN")
						+ rs.getString("T1.CHUMONSHAJUSHOTOSHIKU") + rs.getString("T1.CHUMONSHAJUSHOCHOIJOU")).equals(
								rs.getString("T1.SOUFUSAKIJUSHOTODOFUKEN") + rs.getString("T1.SOUFUSAKIJUSHOTOSHIKU")
										+ rs.getString("T1.SOUFUSAKIJUSHOCHOIJOU")) ? "1" : "0");

				f100102.setMobileMailFlg(rs.getString("T1.MERUADORESU").contains("@pc.") ? "0" : "1");

				f100102.setAsurakuFlg(rs.getString("T1.ASURAKUKIBOU"));

				f100102.setJuchubango(rs.getString("T1.CHUMONBANGO"));
				f100102.setChumonnichiji(rs.getString("T1.CHUMONNICHIJI"));
				f100102.setDingdandaoruri(rs.getString("T1.CREATE_TIME").substring(0, 19));
				f100102.setChumonshanamae(rs.getString("T1.CHUMONSHAMEIJI") + rs.getString("T1.CHUMONSHANAMAE") + "["
						+ (Utility.isEmptyString(rs.getString("T1.CHUMONSHAMEIJIFURIGANA")) ? ""
								: rs.getString("T1.CHUMONSHAMEIJIFURIGANA"))
						+ " " + (Utility.isEmptyString(rs.getString("T1.CHUMONSHANAMAEFURIGANA")) ? ""
								: rs.getString("T1.CHUMONSHANAMAEFURIGANA"))
						+ "]");
				f100102.setChumonshameruadoresu(rs.getString("T1.MERUADORESU"));
				f100102.setChumonshatanjoubi(rs.getString("T1.CHUNONSHATANJOUBI"));
				f100102.setChumonshajusho("〒 " + rs.getString("T1.CHUMONSHAYUBINBANGO1") + "-"
						+ rs.getString("T1.CHUMONSHAYUBINBANGO2") + " " + rs.getString("T1.CHUMONSHAJUSHOTODOFUKEN")
						+ rs.getString("T1.CHUMONSHAJUSHOTOSHIKU") + rs.getString("T1.CHUMONSHAJUSHOCHOIJOU"));
				f100102.setChumonshadenwabango(rs.getString("T1.CHUMONSHADENWABANGO1") + "-"
						+ rs.getString("T1.CHUMONSHADENWABANGO2") + "-" + rs.getString("T1.CHUMONSHADENWABANGO3"));
				f100102.setOshiharaihoho(rs.getString("T1.OSHIHARAISTS"));
				f100102.setHaisohoho(rs.getString("T1.HAISOUHOHO"));
				f100102.setHassobi(rs.getString("T1.HASSOUBI"));
				f100102.setOtodokebishitei(rs.getString("T1.OTODOKEBISHITEI"));
				f100102.setOtodokejikantai1(rs.getString("T1.OTODOKEJIKANTAI1"));
				f100102.setOtodokejikantai2(rs.getString("T1.OTODOKEJIKANTAI2"));
				f100102.setOtodokejikantai3(rs.getString("T1.OTODOKEJIKANTAI3"));
				f100102.setBiko(rs.getString("T1.BIKO"));
				f100102.setBikorakuten(rs.getString("T1.KOMENTO"));
				f100102.setHasoshahe(rs.getString("T1.HASSOUSHAHENOKOMENTO"));
				f100102.setOkyakusamahenomeseji(rs.getString("T1.MERUSASHIKOMIBUN"));
				f100102.setSofusakijoho(rs.getString("T1.SOFUSAKIMEIJI") + rs.getString("T1.SOUFUSAKINAMAE") + "["
						+ (Utility.isEmptyString(rs.getString("T1.SOUFUSAKIMEIJIFURIGANA")) ? ""
								: rs.getString("T1.SOUFUSAKIMEIJIFURIGANA"))
						+ " "
						+ (Utility.isEmptyString(rs.getString("T1.SOUFUSAKIMEIJINAMAEFURIGANA")) ? ""
								: rs.getString("T1.SOUFUSAKIMEIJINAMAEFURIGANA"))
						+ "]" + "<br>" + "〒 " + rs.getString("T1.SOUFUSAKIYUBINBANGO1") + "-"
						+ rs.getString("T1.SOUFUSAKIYUBINBANGO2") + "<br>" + rs.getString("T1.SOUFUSAKIJUSHOTODOFUKEN")
						+ rs.getString("T1.SOUFUSAKIJUSHOTOSHIKU") + rs.getString("T1.SOUFUSAKIJUSHOCHOIJOU") + "<br>"
						+ rs.getString("T1.SOFUSAKIDENWABANGO1") + "-" + rs.getString("T1.SOFUSAKIDENWABANGO2") + "-"
						+ rs.getString("T1.SOFUSAKIDENWABANGO3"));
				// f100102.setOnimotsudenpyobango(rs
				// .getString("T1.ONIMOTUDENPYOUBANGO"));
				// f100102.setHaisokaisha(rs.getString("T1.HAISOUKAISHA"));
				// f100102.setNoshi(rs.getString("T1.NOSHI"));

				f100102.setPointriyou(
						Utility.isEmptyString(rs.getString("T1.POINTRIYO")) ? "0" : rs.getString("T1.POINTRIYO"));
				f100102.setSonotariyogaku(Utility.isEmptyString(rs.getString("T1.SONOTARIYOGAKU")) ? "0"
						: rs.getString("T1.SONOTARIYOGAKU"));
				f100102.setTenpobetsu(rs.getString("T1.SHOP"));
				f100102.setSite(rs.getString("T1.SITE"));
				f100102.setGokeishouhin(rs.getString("T1.GOKEISHOHIN"));
				f100102.setGokeizei(rs.getString("T1.GOKEIZEI"));
				f100102.setGokeisouryou(rs.getString("T1.GOKEISOURYOU"));
				f100102.setGokeidaibikiryou(rs.getString("T1.GOKEIDAIBIKIRYOU"));
				f100102.setSeikyukingaku(rs.getString("T1.SEIKYUKINGAKU"));

				f100102.setOyaFlg(rs.getString("T1.DONKONOYAFLG"));
				if (!"0".equals(rs.getString("T1.DONKONID"))) {
					f100102.setDokonFlg("1");
					List<DokonList> dokonList = new ArrayList<DokonList>();
					f100102.setDokonList(dokonList);
					DokonList donkon = null;

					sql = "SELECT * FROM common_order_tbl WHERE DONKONID = ? ";

					ps = conn.prepareStatement(sql);
					ps.setString(1, rs.getString("DONKONID"));

					ResultSet rs2 = ps.executeQuery();
					while (rs2.next()) {
						donkon = new DokonList();
						dokonList.add(donkon);
						donkon.setJuchubango(rs2.getString("CHUMONBANGO"));
						donkon.setType("1".equals(rs2.getString("DONKONOYAFLG")) ? "親" : "子");
					}

				}

				if (!Utility.isEmptyString(rs.getString("T3.SHOUHINBANGO"))) {
					shohin = new ShohinList();
					shohinList.add(shohin);

					shohin.setShohinbango(rs.getString("T3.SHOUHINBANGO"));
					shohin.setShouhinmei(Utility.getShohinmei(rs.getString("T3.SHOUHINBANGO")) + "("
							+ rs.getString("T3.SHOUHINBANGO") + ")");
					shohin.setKomokusentakushi(rs.getString("T3.KOMOKUSENTAKUSHI").replace("\n", "<br>"));
					shohin.setTankaku(rs.getString("T3.TANKA"));
					shohin.setKosu(rs.getString("T3.KOSU"));
					shohin.setPointobairitsu(rs.getString("T3.POINTOBAIRITSU"));
					// // shohin.setotodokenomeyasu = null;
					shohin.setShoukei(String.valueOf(rs.getLong("T3.TANKA") * rs.getLong("T3.KOSU")));
					shohin.setZei(rs.getString("T3.ZEIKOMIBETSU"));
					shohin.setSourou(rs.getString("T3.SOURYOUKOMIBETSU"));
					shohin.setDaibiki(rs.getString("T3.DAIBIKITESURYOUKOMIBETSU"));
					shohin.setShohinURL(rs.getString("T3.SHOUHINURL"));
					shohin.setNouki(rs.getString("T3.nouki"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return f100102;

	}

	public List<F100102> getOrderInfo() throws Exception {
		F100102 f100102 = null;
		List<F100102> f100102List = new ArrayList<F100102>();

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ShohinList shohin = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT T3.TANKA,T1.CHUMONBANGO,T1.OSHIHARAISTS,T1.HAISOUHOHO,T1.SOFUSAKIMEIJI,T1.SOUFUSAKINAMAE,T1.SOUFUSAKIMEIJIFURIGANA,T1.SOUFUSAKIMEIJIFURIGANA,T1.SOUFUSAKIMEIJINAMAEFURIGANA,T1.SOUFUSAKIMEIJINAMAEFURIGANA,T1.SOUFUSAKIYUBINBANGO1,T1.SOUFUSAKIYUBINBANGO2,T1.SOUFUSAKIJUSHOTODOFUKEN,T1.SOUFUSAKIJUSHOTOSHIKU,T1.SOUFUSAKIJUSHOCHOIJOU,T1.SOFUSAKIDENWABANGO1,T1.SOFUSAKIDENWABANGO2,T1.SOFUSAKIDENWABANGO3,T1.GOKEISHOHIN,T1.GOKEISOURYOU,T3.SHOUHINBANGO,T3.KOMOKUSENTAKUSHI"
					+ " FROM common_order_tbl T1 LEFT JOIN common_order_detail_tbl T3 ON T1.CHUMONBANGO = T3.JUCHUBANGO WHERE T1.CHUMONSTS1 = '2' and (DONKONOYAFLG = '0' or DONKONOYAFLG = '') ";

			ps = conn.prepareStatement(sql);
			String orderNo = "";
			ResultSet rs = ps.executeQuery();
			List<ShohinList> shohinList = new ArrayList<ShohinList>();
			while (rs.next()) {
				if (Utility.isEmptyString(orderNo) || !orderNo.equals(rs.getString("T1.CHUMONBANGO"))) {
					orderNo = rs.getString("T1.CHUMONBANGO");
					f100102 = new F100102();
					shohinList = new ArrayList<ShohinList>();
					f100102.setShohinList(shohinList);
					f100102List.add(f100102);
					// f100102.setChumonsts1(rs.getString("T1.CHUMONSTS1"));
					// f100102.setChumonsts2(rs.getString("T1.CHUMONSTS2"));
					// f100102.setChumonsts3(rs.getString("T1.CHUMONSTS3"));
					// f100102.setChumonsts4(rs.getString("T1.CHUMONSTS4"));
					// f100102.setChumonsts5(rs.getString("T1.CHUMONSTS5"));
					// f100102.setChumonsts6(rs.getString("T1.CHUMONSTS6"));
					// f100102.setDokonFlg(rs.getString("T2.DOKONSUTETASU"));
					// f100102.setOkurisakuchuiFlg((rs
					// .getString("T1.CHUMONSHAJUSHOTODOFUKEN")
					// + rs.getString("T1.CHUMONSHAJUSHOTOSHIKU") + rs
					// .getString("T1.CHUMONSHAJUSHOCHOIJOU")).equals(rs
					// .getString("T1.SOUFUSAKIJUSHOTODOFUKEN")
					// + rs.getString("T1.SOUFUSAKIJUSHOTOSHIKU")
					// + rs.getString("T1.SOUFUSAKIJUSHOCHOIJOU")) ? "1" : "0");

					// f100102.setMobileMailFlg(rs.getString("T1.MERUADORESU")
					// .contains("@pc.") ? "0" : "1");
					//
					// f100102.setAsurakuFlg(rs.getString("T1.ASURAKUKIBOU"));

					f100102.setJuchubango(rs.getString("T1.CHUMONBANGO"));
					// f100102.setChumonnichiji(rs.getString("T1.CHUMONNICHIJI"));
					// f100102.setDingdandaoruri(rs.getString("T1.CREATE_TIME")
					// .substring(0, 19));
					// f100102.setChumonshanamae(rs.getString("T1.CHUMONSHAMEIJI")
					// + rs.getString("T1.CHUMONSHANAMAE")
					// + "["
					// + (Utility.isEmptyString(rs
					// .getString("T1.CHUMONSHAMEIJIFURIGANA")) ? ""
					// : rs.getString("T1.CHUMONSHAMEIJIFURIGANA"))
					// + " "
					// + (Utility.isEmptyString(rs
					// .getString("T1.CHUMONSHANAMAEFURIGANA")) ? ""
					// : rs.getString("T1.CHUMONSHANAMAEFURIGANA"))
					// + "]");
					// f100102.setChumonshameruadoresu(rs.getString("T1.MERUADORESU"));
					// f100102.setChumonshatanjoubi(rs
					// .getString("T1.CHUNONSHATANJOUBI"));
					// f100102.setChumonshajusho("〒 "
					// + rs.getString("T1.CHUMONSHAYUBINBANGO1") + "-"
					// + rs.getString("T1.CHUMONSHAYUBINBANGO2") + " "
					// + rs.getString("T1.CHUMONSHAJUSHOTODOFUKEN")
					// + rs.getString("T1.CHUMONSHAJUSHOTOSHIKU")
					// + rs.getString("T1.CHUMONSHAJUSHOCHOIJOU"));
					// f100102.setChumonshadenwabango(rs
					// .getString("T1.CHUMONSHADENWABANGO1")
					// + "-"
					// + rs.getString("T1.CHUMONSHADENWABANGO2")
					// + "-"
					// + rs.getString("T1.CHUMONSHADENWABANGO3"));
					f100102.setOshiharaihoho(rs.getString("T1.OSHIHARAISTS"));
					f100102.setHaisohoho(rs.getString("T1.HAISOUHOHO"));
					// f100102.setHassobi(rs.getString("T1.HASSOUBI"));
					// f100102.setOtodokebishitei(rs.getString("T1.OTODOKEBISHITEI"));
					// f100102.setOtodokejikantai1(rs.getString("T1.OTODOKEJIKANTAI1"));
					// f100102.setOtodokejikantai2(rs.getString("T1.OTODOKEJIKANTAI2"));
					// f100102.setOtodokejikantai3(rs.getString("T1.OTODOKEJIKANTAI3"));
					// f100102.setBiko(rs.getString("T1.BIKO"));
					// f100102.setBikorakuten(rs.getString("T1.KOMENTO"));
					// f100102.setHasoshahe(rs.getString("T1.HASSOUSHAHENOKOMENTO"));
					// f100102.setOkyakusamahenomeseji(rs
					// .getString("T1.MERUSASHIKOMIBUN"));
					f100102.setSofusakijoho(rs.getString("T1.SOFUSAKIMEIJI") + rs.getString("T1.SOUFUSAKINAMAE") + "["
							+ (Utility.isEmptyString(rs.getString("T1.SOUFUSAKIMEIJIFURIGANA")) ? ""
									: rs.getString("T1.SOUFUSAKIMEIJIFURIGANA"))
							+ " "
							+ (Utility.isEmptyString(rs.getString("T1.SOUFUSAKIMEIJINAMAEFURIGANA")) ? ""
									: rs.getString("T1.SOUFUSAKIMEIJINAMAEFURIGANA"))
							+ "]" + "<br>" + "〒 " + rs.getString("T1.SOUFUSAKIYUBINBANGO1") + "-"
							+ rs.getString("T1.SOUFUSAKIYUBINBANGO2") + "<br>"
							+ rs.getString("T1.SOUFUSAKIJUSHOTODOFUKEN") + rs.getString("T1.SOUFUSAKIJUSHOTOSHIKU")
							+ rs.getString("T1.SOUFUSAKIJUSHOCHOIJOU") + "<br>" + rs.getString("T1.SOFUSAKIDENWABANGO1")
							+ "-" + rs.getString("T1.SOFUSAKIDENWABANGO2") + "-"
							+ rs.getString("T1.SOFUSAKIDENWABANGO3"));
					// f100102.setOnimotsudenpyobango(rs
					// .getString("T1.ONIMOTUDENPYOUBANGO"));
					// f100102.setHaisokaisha(rs.getString("T1.HAISOUKAISHA"));
					// f100102.setNoshi(rs.getString("T1.NOSHI"));

					// f100102.setPointriyou(Utility.isEmptyString(rs
					// .getString("T1.POINTRIYO")) ? "0" : rs
					// .getString("T1.POINTRIYO"));
					// f100102.setSonotariyogaku(Utility.isEmptyString(rs
					// .getString("T1.SONOTARIYOGAKU")) ? "0" : rs
					// .getString("T1.SONOTARIYOGAKU"));
					// f100102.setTenpobetsu(rs.getString("T1.SHOP"));
					// f100102.setSite(rs.getString("T1.SITE"));
					f100102.setGokeishouhin(rs.getString("T1.GOKEISHOHIN"));
					// f100102.setGokeizei(rs.getString("T1.GOKEIZEI"));
					f100102.setGokeisouryou(rs.getString("T1.GOKEISOURYOU"));
					// f100102.setGokeidaibikiryou(rs.getString("T1.GOKEIDAIBIKIRYOU"));
					// f100102.setSeikyukingaku(rs.getString("T1.SEIKYUKINGAKU"));

					// f100102.setOyaFlg(rs.getString("T1.DONKONOYAFLG"));
					// if (!"0".equals(rs.getString("T1.DONKONID"))) {
					// f100102.setDokonFlg("1");
					// List<DokonList> dokonList = new ArrayList<DokonList>();
					// f100102.setDokonList(dokonList);
					// DokonList donkon = null;
					//
					// sql =
					// "SELECT * FROM common_order_tbl WHERE DONKONID = ? ";
					//
					// ps = conn.prepareStatement(sql);
					// ps.setString(1, rs.getString("DONKONID"));
					//
					// ResultSet rs2 = ps.executeQuery();
					// while (rs2.next()) {
					// donkon = new DokonList();
					// dokonList.add(donkon);
					// donkon.setJuchubango(rs2.getString("CHUMONBANGO"));
					// donkon.setType("1".equals(rs2.getString("DONKONOYAFLG"))
					// ?
					// "親"
					// : "子");
					// }
					//
					// }
				}
				if (!Utility.isEmptyString(rs.getString("T3.SHOUHINBANGO"))) {
					shohin = new ShohinList();
					shohinList.add(shohin);

					// shohin.setShohinbango(rs.getString("T3.SHOUHINBANGO"));
					shohin.setShouhinmei(Utility.getShohinmei(rs.getString("T3.SHOUHINBANGO")) + "("
							+ rs.getString("T3.SHOUHINBANGO") + ")");
					shohin.setKomokusentakushi(rs.getString("T3.KOMOKUSENTAKUSHI").replace("\n", "<br>"));
					shohin.setTankaku(rs.getString("T3.TANKA"));
					// shohin.setKosu(rs.getString("T3.KOSU"));
					// shohin.setPointobairitsu(rs.getString("T3.POINTOBAIRITSU"));
					// // // shohin.setotodokenomeyasu = null;
					// shohin.setShoukei(String.valueOf(rs.getLong("T3.TANKA")
					// * rs.getLong("T3.KOSU")));
					// shohin.setZei(rs.getString("T3.ZEIKOMIBETSU"));
					// shohin.setSourou(rs.getString("T3.SOURYOUKOMIBETSU"));
					// shohin.setDaibiki(rs
					// .getString("T3.DAIBIKITESURYOUKOMIBETSU"));
					// shohin.setShohinURL(rs.getString("T3.SHOUHINURL"));
					// shohin.setNouki(rs.getString("T3.nouki"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return f100102List;

	}

	public F100103 getOrderInfoShusei(String orderNo) throws Exception {
		F100103 f100103 = new F100103();
		List<ShohinList> shohinList = new ArrayList<ShohinList>();
		f100103.setShohinList(shohinList);

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			ShohinList shohin = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM common_order_tbl T1 LEFT JOIN common_order_detail_tbl T3 ON T1.CHUMONBANGO = T3.JUCHUBANGO WHERE T1.CHUMONBANGO = ? ";

			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				f100103.setChumonshameiji(rs.getString("T1.CHUMONSHAMEIJI"));
				f100103.setChumonshanamae(rs.getString("T1.CHUMONSHANAMAE"));
				f100103.setChumonshanamaefurigana(rs.getString("T1.CHUMONSHANAMAEFURIGANA"));
				f100103.setChumonshameijifurigana(rs.getString("T1.CHUMONSHAMEIJIFURIGANA"));
				f100103.setChumonshayubinbango1(rs.getString("T1.CHUMONSHAYUBINBANGO1"));
				f100103.setChumonshayubinbango2(rs.getString("T1.CHUMONSHAYUBINBANGO2"));
				f100103.setChumonshajushotodofuken(rs.getString("T1.CHUMONSHAJUSHOTODOFUKEN"));
				f100103.setChumonshajushotoshiku(rs.getString("T1.CHUMONSHAJUSHOTOSHIKU"));
				f100103.setChumonshajushochojijo(rs.getString("T1.CHUMONSHAJUSHOCHOIJOU"));
				f100103.setChumonshadenwabango1(rs.getString("T1.CHUMONSHADENWABANGO1"));
				f100103.setChumonshadenwabango2(rs.getString("T1.CHUMONSHADENWABANGO2"));
				f100103.setChumonshadenwabango3(rs.getString("T1.CHUMONSHADENWABANGO3"));
				f100103.setMeruadoresu(rs.getString("T1.MERUADORESU"));
				f100103.setOshiharaihoho(rs.getString("T1.OSHIHARAISTS"));
				f100103.setHaisohoho(rs.getString("T1.HAISOUHOHO"));
				f100103.setSofusakimeiji(rs.getString("T1.SOFUSAKIMEIJI"));
				f100103.setSofusakinamae(rs.getString("T1.SOUFUSAKINAMAE"));
				f100103.setSofusakimeijifurigana(rs.getString("T1.SOUFUSAKIMEIJIFURIGANA"));
				f100103.setSofusakinamaefurigana(rs.getString("T1.SOUFUSAKIMEIJINAMAEFURIGANA"));
				f100103.setSofusakiyubinbango1(rs.getString("SOUFUSAKIYUBINBANGO1"));
				f100103.setSofusakiyubinbango2(rs.getString("SOUFUSAKIYUBINBANGO2"));
				f100103.setSofusakijushotodofuken(rs.getString("T1.SOUFUSAKIJUSHOTODOFUKEN"));
				f100103.setSofusakijushotoshiku(rs.getString("T1.SOUFUSAKIJUSHOTOSHIKU"));
				f100103.setSofusakijushochoijo(rs.getString("T1.SOUFUSAKIJUSHOCHOIJOU"));
				f100103.setSofusakidenwabango1(rs.getString("T1.SOFUSAKIDENWABANGO1"));
				f100103.setSofusakidenwabango2(rs.getString("T1.SOFUSAKIDENWABANGO2"));
				f100103.setSofusakidenwabango3(rs.getString("T1.SOFUSAKIDENWABANGO3"));
				f100103.setGokeishouhin(rs.getString("T1.GOKEISHOHIN"));
				f100103.setGokeizei(rs.getString("T1.GOKEIZEI"));
				f100103.setGokeisouryou(rs.getString("T1.GOKEISOURYOU"));
				f100103.setGokeidaibikiryou(rs.getString("T1.GOKEIDAIBIKIRYOU"));
				f100103.setSeikyukingaku(rs.getString("T1.SEIKYUKINGAKU"));
				f100103.setPointriyo(rs.getString("T1.POINTRIYO"));
				f100103.setSonotariyogaku(rs.getString("T1.SONOTARIYOGAKU"));

				if (!Utility.isEmptyString(rs.getString("T3.SHOUHINBANGO"))) {
					shohin = new ShohinList();
					shohinList.add(shohin);

					shohin.setShohinbango(rs.getString("T3.SHOUHINBANGO"));
					shohin.setShouhinmei(Utility.getShohinmei(rs.getString("T3.SHOUHINBANGO")) + "("
							+ rs.getString("T3.SHOUHINBANGO") + ")");
					shohin.setKomokusentakushi(rs.getString("T3.KOMOKUSENTAKUSHI"));
					shohin.setTankaku(rs.getString("T3.TANKA"));
					shohin.setKosu(rs.getString("T3.KOSU"));
					shohin.setPointobairitsu(rs.getString("T3.POINTOBAIRITSU"));
					// // shohin.setotodokenomeyasu = null;
					shohin.setShoukei(String.valueOf(rs.getLong("T3.TANKA") * rs.getLong("T3.KOSU")));
					shohin.setZei(rs.getString("T3.ZEIKOMIBETSU"));
					shohin.setSourou(rs.getString("T3.SOURYOUKOMIBETSU"));
					shohin.setDaibiki(rs.getString("T3.DAIBIKITESURYOUKOMIBETSU"));
					shohin.setShohinURL(rs.getString("T3.SHOUHINURL"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return f100103;

	}

	public List<DenaCsvBean> getOrderListFromCsvDena(File csvFile) throws Exception {
		// 从CSV文件获取订单信息
		List<String[]> csvList = Utility.readCsvFile(csvFile, true);
		// 返回的订单列表
		List<DenaCsvBean> orderList = new ArrayList<DenaCsvBean>();

		List<String[]> orderInfo = new ArrayList<String[]>();
		for (int i = 0; i < csvList.size(); i++) {
			String[] csvData = csvList.get(i);
			String[] csvDataNext = null;
			if (i + 1 < csvList.size()) {
				csvDataNext = csvList.get(i + 1);
			}
			String chumonbango = csvData[0];
			String chumonbangoNext = "";
			if (csvDataNext != null) {
				chumonbangoNext = csvDataNext[0];
			}
			orderInfo.add(csvData);
			if (!chumonbango.equals(chumonbangoNext) || "".equals(chumonbangoNext)) {
				AddtoOrderListDena(orderList, orderInfo);
				orderInfo = new ArrayList<String[]>();
			}
		}

		return orderList;
	}

	public List<Qoo10CsvBean> getOrderListFromCsvQoo10(File csvFile) throws Exception {
		// 从CSV文件获取订单信息
		List<String[]> csvList = Utility.readCsvFile(csvFile, true);
		// 返回的订单列表
		List<Qoo10CsvBean> orderList = new ArrayList<Qoo10CsvBean>();

		List<String[]> orderInfo = new ArrayList<String[]>();
		for (int i = 0; i < csvList.size(); i++) {
			String[] csvData = csvList.get(i);
			String[] csvDataNext = null;
			if (i + 1 < csvList.size()) {
				csvDataNext = csvList.get(i + 1);
			}
			String chumonbango = csvData[2];
			String chumonbangoNext = "";
			if (csvDataNext != null) {
				chumonbangoNext = csvDataNext[2];
			}
			orderInfo.add(csvData);
			if (!chumonbango.equals(chumonbangoNext) || "".equals(chumonbangoNext)) {
				AddtoOrderListQoo10(orderList, orderInfo);
				orderInfo = new ArrayList<String[]>();
			}
		}

		return orderList;
	}

	public List<PonpareCsvBean> getOrderListFromCsvPonpare(File csvFile) throws Exception {
		// 从CSV文件获取订单信息
		List<String[]> csvList = Utility.readCsvFile(csvFile, true);
		// 返回的订单列表
		List<PonpareCsvBean> orderList = new ArrayList<PonpareCsvBean>();

		List<String[]> orderInfo = new ArrayList<String[]>();
		for (int i = 0; i < csvList.size(); i++) {
			String[] csvData = csvList.get(i);
			String[] csvDataNext = null;
			if (i + 1 < csvList.size()) {
				csvDataNext = csvList.get(i + 1);
			}
			String chumonbango = csvData[0];
			String chumonbangoNext = "";
			if (csvDataNext != null) {
				chumonbangoNext = csvDataNext[0];
			}
			orderInfo.add(csvData);
			if (!chumonbango.equals(chumonbangoNext) || "".equals(chumonbangoNext)) {
				AddtoOrderListPonpare(orderList, orderInfo);
				orderInfo = new ArrayList<String[]>();
			}
		}

		return orderList;
	}
	
	public List<OtherCsvBean> getOrderListFromCsvOther(File csvFile) throws Exception {
		// 从CSV文件获取订单信息
		List<String[]> csvList = Utility.readCsvFile(csvFile, true);
		// 返回的订单列表
		List<OtherCsvBean> orderList = new ArrayList<OtherCsvBean>();

		List<String[]> orderInfo = new ArrayList<String[]>();
		for (int i = 0; i < csvList.size(); i++) {
			String[] csvData = csvList.get(i);
			String[] csvDataNext = null;
			if (i + 1 < csvList.size()) {
				csvDataNext = csvList.get(i + 1);
			}
			String chumonbango = csvData[3];
			String chumonbangoNext = "";
			if (csvDataNext != null) {
				chumonbangoNext = csvDataNext[3];
			}
			orderInfo.add(csvData);
			if (!chumonbango.equals(chumonbangoNext) || "".equals(chumonbangoNext)) {
				AddtoOrderListOther(orderList, orderInfo);
				orderInfo = new ArrayList<String[]>();
			}
		}

		return orderList;
	}

}
