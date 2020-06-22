package com.rakuten.common;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.bean.ShohinInfoBean;
import com.rakuten.common.bean.ShohinkategoriBean;
import com.rakuten.common.bean.ShohinsentakushiBean;
import com.rakuten.util.Utility;

public class ShohinCommon {
	public void writeRakutenItemCsv(List<ShohinInfoBean> shohinList, String path)
			throws Exception {
		String[] titleRecord = "コントロールカラム,商品管理番号（商品URL）,商品番号,全商品ディレクトリID,タグID,PC用キャッチコピー,モバイル用キャッチコピー,商品名,販売価格,表示価格,消費税,送料,個別送料,送料区分1,送料区分2,代引料,倉庫指定,商品情報レイアウト,注文ボタン,資料請求ボタン,商品問い合わせボタン,再入荷お知らせボタン,モバイル表示,のし対応,PC用商品説明文,モバイル用商品説明文,スマートフォン用商品説明文,PC用販売説明文,商品画像URL,商品画像名（ALT）,動画,販売期間指定,注文受付数,在庫タイプ,在庫数,在庫数表示,項目選択肢別在庫用横軸項目名,項目選択肢別在庫用縦軸項目名,項目選択肢別在庫用残り表示閾値,RAC番号,サーチ非表示,闇市パスワード,カタログID,在庫戻しフラグ,在庫切れ時の注文受付,在庫あり時納期管理番号,在庫切れ時納期管理番号,予約商品発売日,ポイント変倍率,ポイント変倍率適用期間,ヘッダー・フッター・レフトナビ,表示項目の並び順,共通説明文（小）,目玉商品,共通説明文（大）,レビュー本文表示,あす楽配送管理番号,海外配送管理番号,サイズ表リンク,医薬品説明文,医薬品注意事項,二重価格文言管理番号"
				.split(",");
		List<String[]> recordArr = new ArrayList<>();
		recordArr.add(titleRecord);
		for (ShohinInfoBean shohin : shohinList) {
			String[] record = new String[] {
					// コントロールカラム
					shohin.getKontororukaramu(),
					// 商品管理番号（商品URL）
					shohin.getShouhinkanribango(),
					// 商品番号
					shohin.getShouhinbango(),
					// 全商品ディレクトリID
					shohin.getZenshohindirekutoriId(),
					// タグID
					shohin.getTaguId(),
					// PC用キャッチコピー
					shohin.getPcyokyachikopi(),
					// モバイル用キャッチコピー
					shohin.getMobairuyokyachikopi(),
					// 商品名
					shohin.getShouhinmei(),
					// 販売価格
					shohin.getHanbaikakaku(),
					// 表示価格
					shohin.getHyojikakaku(),
					// 消費税
					shohin.getShouhizei(),
					// 送料
					shohin.getSouryou(),
					// 個別送料
					shohin.getKobetusouryou(),
					// 送料区分1
					shohin.getSouryoukubun1(),
					// 送料区分2
					shohin.getSouryoukubun2(),
					// 代引料
					shohin.getDaibikiryou(),
					// 倉庫指定
					shohin.getSokoshitei(),
					// 商品情報レイアウト
					shohin.getShouhinjouhoureiaouto(),
					// 注文ボタン
					shohin.getChumonbotan(),
					// 資料請求ボタン
					shohin.getShiryosekyubotan(),
					// 商品問い合わせボタン
					shohin.getShouhintoiawasebotan(),
					// 再入荷お知らせボタン
					shohin.getSainyukaoshirasebotan(),
					// モバイル表示
					shohin.getMobairuhyoji(),
					// のし対応
					shohin.getNoshitaiou(),
					// PC用商品説明文
					shohin.getPcyoushouhinsetumeibun(),
					// モバイル用商品説明文
					shohin.getMobairuyoushouhinsetumeibun(),
					// スマートフォン用商品説明文
					shohin.getSumatofonyoushouhinsetumeibun(),
					// PC用販売説明文
					shohin.getPcyouhanbaisetumeibun(),
					// 商品画像URL
					shohin.getShouhingazoUrl(),
					// 商品画像名（ALT）
					shohin.getShouhingazomeiAlt(),
					// 動画
					shohin.getDouga(),
					// 販売期間指定
					shohin.getHanbaikikanshitei(),
					// 注文受付数
					shohin.getChumonnuketukesu(),
					// 在庫タイプ
					shohin.getZaikotaipu(),
					// 在庫数
					shohin.getZaikusu(),
					// 在庫数表示
					shohin.getZaikosuhyouji(),
					// 項目選択肢別在庫用横軸項目名
					shohin.getKomokusentakushibetuzaikoyouyokojikukoumokumei(),
					// 項目選択肢別在庫用縦軸項目名
					shohin.getKomokusentakushibetuzaikoyoutatejikukomokumei(),
					// 項目選択肢別在庫用残り表示閾値
					shohin.getKoumokusentakushibetuzaikoyounokorihyoujiikichi(),
					// RAC番号
					shohin.getRacbango(),
					// サーチ非表示
					shohin.getSachihihyoji(),
					// 闇市パスワード
					shohin.getYamiichipasuwado(),
					// カタログID
					shohin.getKataroguId(),
					// 在庫戻しフラグ
					shohin.getZaikonodoshifuragu(),
					// 在庫切れ時の注文受付
					shohin.getZaikokiretokinochumonnuketuke(),
					// 在庫あり時納期管理番号
					shohin.getZaikoaritokinoukikanribango(),
					// 在庫切れ時納期管理番号
					shohin.getZaikokiretokinoukikanribango(),
					// 予約商品発売日
					shohin.getYoyakushouhinhanbaibi(),
					// ポイント変倍率
					shohin.getPointohenbairitu(),
					// ポイント変倍率適用期間
					shohin.getPointohenbaitekiyoukikan(),
					// ヘッダー・フッター・レフトナビ
					shohin.getHeddafuttarefutonabi(),
					// 表示項目の並び順
					shohin.getHyoujikomokunonarabijun(),
					// 共通説明文（小）
					shohin.getKyotusetumeibunsho(),
					// 目玉商品
					shohin.getMedamashouhin(),
					// 共通説明文（大）
					shohin.getKyoutusetumeibundai(),
					// レビュー本文表示
					shohin.getRebyuhonbunhyouji(),
					// あす楽配送管理番号
					shohin.getArurakuhaisoukanribango(),
					// 海外配送管理番号
					shohin.getKaigaihaisoukanribango(),
					// サイズ表リンク
					shohin.getSaizuhyourinku(),
					// 医薬品説明文
					shohin.getYypsmw(),
					// 医薬品注意事項
					shohin.getYypzysx(),
					// 二重価格文言管理番号
					shohin.getNichokakakubungankanribango() };
			recordArr.add(record);
		}
		Utility.writeCsvFile(recordArr, path);
	}

	public void writeRakutenSelCsv(
			List<ShohinsentakushiBean> shohinsentakushiList, String path)
			throws Exception {
		String[] titleRecord = "項目選択肢用コントロールカラム,商品管理番号（商品URL）,選択肢タイプ,Select/Checkbox用項目名,Select/Checkbox用選択肢,項目選択肢別在庫用横軸選択肢,項目選択肢別在庫用横軸選択肢子番号,項目選択肢別在庫用縦軸選択肢,項目選択肢別在庫用縦軸選択肢子番号,項目選択肢別在庫用取り寄せ可能表示,項目選択肢別在庫用在庫数,在庫戻しフラグ,在庫切れ時の注文受付,在庫あり時納期管理番号,在庫切れ時納期管理番号"
				.split(",");
		List<String[]> recordArr = new ArrayList<>();
		recordArr.add(titleRecord);
		for (ShohinsentakushiBean shohin : shohinsentakushiList) {
			String[] record = new String[] { // コントロールカラム
			shohin.getKontororukaramu(),
					// 商品管理番号（商品URL）
					shohin.getShohinkanribango(),
					// 選択肢タイプ
					shohin.getSentakutaipu(),
					// Select/Checkbox用項目名
					shohin.getSelectcheckboxyoukomokumei(),
					// Select/Checkbox用選択肢
					shohin.getSelectcheckboxyousentakushi(),
					// 項目選択肢別在庫用横軸選択肢
					shohin.getKomokusentakushibetuzaikoyouyokojikusentakushi(),
					// 項目選択肢別在庫用横軸選択肢子番号
					shohin.getKomokusentakushibetuzaikoyoyokojikusentakushishibango(),
					// 項目選択肢別在庫用縦軸選択肢
					shohin.getKomokusentakushizaikoyoutatejikusentakushi(),
					// 項目選択肢別在庫用縦軸選択肢子番号
					shohin.getKomokusentakushibetuzaikoyotatejikusentakushishibango(),
					// 項目選択肢別在庫用取り寄せ可能表示
					shohin.getKomokusentakushibetuzaikototoriyosekanohyouji(),
					// 項目選択肢別在庫用在庫数
					shohin.getKomokusentakushibetuzaikoyozaikosu(),
					// 在庫戻しフラグ
					shohin.getZaikonodoshifuragu(),
					// 在庫切れ時の注文受付
					shohin.getZaikokiretokinochumonuketuke(),
					// 在庫あり時納期管理番号
					shohin.getZaikoaritokinoukikanribango(),
					// 在庫切れ時納期管理番号
					shohin.getZaikokiretokinoukikanribango() };
			recordArr.add(record);
		}
		Utility.writeCsvFile(recordArr, path);
	}

	public void writeRakutenCatCsv(List<ShohinkategoriBean> shohinkategoriList,
			String path) throws Exception {
		String[] titleRecord = "コントロールカラム,商品管理番号（商品URL）,商品名,表示先カテゴリ,優先度,URL,1ページ複数形式"
				.split(",");
		List<String[]> recordArr = new ArrayList<>();
		recordArr.add(titleRecord);
		for (ShohinkategoriBean shohin : shohinkategoriList) {
			String[] record = new String[] {
					// コントロールカラム
					shohin.getKontororukaramu(),
					// 商品管理番号（商品URL）
					shohin.getShohinkanribango(),
					// 商品名
					shohin.getShohinmei(),
					// 表示先カテゴリ
					shohin.getHuojisakikategori(),
					// 優先度
					shohin.getYusendo(),
					// URL
					shohin.getUrl(),
					// 1ページ複数形式
					shohin.getIchipejifukusukeishiki() };
			recordArr.add(record);
		}
		Utility.writeCsvFile(recordArr, path);
	}
}
