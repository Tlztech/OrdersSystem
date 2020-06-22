package com.rakuten.r0602.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.rakuten.util.Utility;

public class CsvTool {
	public ArrayList<String[]> readCsv(String path) throws Exception {
		ArrayList<String[]> csvList = new ArrayList<String[]>();
		CsvReader reader = new CsvReader(new FileInputStream(path), ',', Charset.forName("SJIS")); // 一般用这编码读就可以了

		reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。

		while (reader.readRecord()) { // 逐行读入除表头的数据
			csvList.add(reader.getValues());
		}
		return csvList;
	}

	public List<String[]> removeIyahonjyaku(List<String[]> selList) {
		List<String[]> resultList1 = new ArrayList<>();
		List<String[]> resultList2 = new ArrayList<>();
		int i = 0;
		for (String[] data : selList) {
			i++;
			if (i == 1) {
				resultList1.add(data);
				continue;
			}
			if (data[3].contains("イヤホン")) {
				continue;
			}
			if ("i".equals(data[2])) {
				resultList1.add(data);
			} else {
				resultList2.add(data);
			}
		}
		resultList1.addAll(resultList2);
		return resultList1;
	}

	public void convert3ecatToOthercat(List<String[]> threeeshopcat, String shopname) {
		int i = 0;

		for (String[] data : threeeshopcat) {
			if (data[1].startsWith("sale")) {
				data[1] = data[1].substring(4);
			}
			i++;
			if (i == 0) {
				continue;
			}
			data[3] = "3eshop\\" + data[3];
		}

	}

	public void convert3eToOthershop(List<String[]> threeeshopCsvList, String shopname) {

		for (String[] data : threeeshopCsvList) {

			if (data[1].startsWith("sale")) {
				data[1] = data[1].substring(4);
			}

			data[24] = data[24]
					.replace("http://image.rakuten.co.jp/3eshop/cabinet/",
							"http://image.rakuten.co.jp/" + shopname + "/cabinet/3eshop/")
					.replace("http://www.rakuten.ne.jp/gold/3eshop/",
							"http://image.rakuten.co.jp/" + shopname + "/cabinet/3eshop/gold/");
			data[26] = data[26]
					.replace("http://image.rakuten.co.jp/3eshop/cabinet/",
							"http://image.rakuten.co.jp/" + shopname + "/cabinet/3eshop/")
					.replace("http://www.rakuten.ne.jp/gold/3eshop/",
							"http://image.rakuten.co.jp/" + shopname + "/cabinet/3eshop/gold/");
			data[27] = data[27]
					.replace("http://image.rakuten.co.jp/3eshop/cabinet/",
							"http://image.rakuten.co.jp/" + shopname + "/cabinet/3eshop/")
					.replace("http://www.rakuten.ne.jp/gold/3eshop/",
							"http://image.rakuten.co.jp/" + shopname + "/cabinet/3eshop/gold/")
					.replace("<img width=\"650px\" src=\"http://www.rakuten.ne.jp/gold/3eshop/images/review.jpg\">",
							"");
			data[28] = data[28]
					.replace("http://image.rakuten.co.jp/3eshop/cabinet/",
							"http://image.rakuten.co.jp/" + shopname + "/cabinet/3eshop/")
					.replace("http://www.rakuten.ne.jp/gold/3eshop/",
							"http://image.rakuten.co.jp/" + shopname + "/cabinet/3eshop/gold/");
		}

	}

	public void compare3eTo123mart(ArrayList<String[]> csvList_123mart, ArrayList<String[]> csvList_3eshop) {
		String shohinbango_123mart = "";
		String shohinbango_3eshop = "";
		boolean ariFlg = false;
		for (int i = 0; i < csvList_123mart.size(); i++) {
			shohinbango_123mart = csvList_123mart.get(i)[1];
			for (int j = 0; j < csvList_3eshop.size(); j++) {
				shohinbango_3eshop = csvList_3eshop.get(j)[1];
				if (shohinbango_3eshop.equals(shohinbango_123mart)) {
					ariFlg = true;
					break;
				}
			}
			if (!ariFlg) {
				System.out.println(shohinbango_123mart);
			}
			ariFlg = false;
		}
	}

	public List<String[]> getPicUrl_123mart(List<String[]> csvList_123mart) {
		String shohinsetumeibun = "";
		String picUrl = "";
		boolean ariFlg = false;
		List<String[]> picList = new ArrayList<String[]>();
		for (int i = 0; i < csvList_123mart.size(); i++) {
			shohinsetumeibun = csvList_123mart.get(i)[25];
			while (shohinsetumeibun.contains(".jpg")) {
				shohinsetumeibun = shohinsetumeibun.substring(shohinsetumeibun.indexOf("http"));
				picUrl = shohinsetumeibun.substring(0, shohinsetumeibun.indexOf(".jpg") + 4);
				shohinsetumeibun = shohinsetumeibun.substring(shohinsetumeibun.indexOf(".jpg") + 4);
				for (int j = 0; j < picList.size(); j++) {
					if (picUrl.equals(picList.get(j))) {
						ariFlg = true;
						break;
					}
				}
				if (!ariFlg) {
					picList.add(new String[] { picUrl, csvList_123mart.get(i)[1] });
				}
				ariFlg = false;
			}
		}
		// for (int i = 0; i < csvList_123mart.size(); i++) {
		// shohinsetumeibun = csvList_123mart.get(i)[3];
		// while (shohinsetumeibun.contains(".jpg")) {
		// shohinsetumeibun = shohinsetumeibun.substring(shohinsetumeibun
		// .indexOf("http"));
		// picUrl = shohinsetumeibun.substring(0,
		// shohinsetumeibun.indexOf(".jpg") + 4);
		// shohinsetumeibun = shohinsetumeibun.substring(shohinsetumeibun
		// .indexOf(".jpg") + 4);
		// for (int j = 0; j < picList.size(); j++) {
		// if (picUrl.equals(picList.get(j))) {
		// ariFlg = true;
		// break;
		// }
		// }
		// if (!ariFlg) {
		// picList.add(new String[] { picUrl,
		// csvList_123mart.get(i)[1] });
		// }
		// ariFlg = false;
		// }
		// }
		return picList;
	}

	public void downloadPic(List<String[]> picList) {
		File file = null;
		String path = null;
		List<String> errList = new ArrayList<String>();
		for (int i = 0; i < picList.size(); i++) {
			String picUrl = picList.get(i)[0];
			try {
				path = picList.get(i)[0];
				path = path.replace("https://image.rakuten.co.jp/coverforefront/cabinet/", "");
				if (path.contains("https://www.rakuten.ne.jp/gold/coverforefront/")) {
					path = "gold/" + path.replace("https://www.rakuten.ne.jp/gold/coverforefront/", "");
				}

				if (path.lastIndexOf("/") > 0) {
					path = path.substring(0, path.lastIndexOf("/"));
				}

				file = new File("C://PIC_DOWNLOAD/coverforefront/" + path + "/");
				file.mkdirs();
				String kachoshi = picUrl.substring(picUrl.lastIndexOf("."));
				String picName = picUrl.substring(0, picUrl.lastIndexOf("."));
				picName = picName.substring(picUrl.lastIndexOf("/") + 1);
				picName = picName + kachoshi;
				download(picUrl, file.getPath() + "/" + picName);
			} catch (Exception e) {
				errList.add(picUrl + " 下载失败！");
			}
		}

		for (int i = 0; i < errList.size(); i++) {
			System.out.println(errList.get(i));
		}
	}

	public void downloadPicLocal(List<String[]> picList) {
		File file = null;
		String path = null;
		List<String> errList = new ArrayList<String>();
		for (int i = 0; i < picList.size(); i++) {
			String picUrl = picList.get(i)[0];
			try {
				path = picList.get(i)[0];
				path = path.replace("https://image.rakuten.co.jp/coverforefront/cabinet/", "");
				if (path.contains("https://www.rakuten.ne.jp/gold/coverforefront/")) {
					path = "gold/" + path.replace("https://www.rakuten.ne.jp/gold/coverforefront/", "");
				}

				if (path.lastIndexOf("/") > 0) {
					path = path.substring(0, path.lastIndexOf("/"));
				}

				file = new File("D://PIC_DOWNLOAD/coverforefront/" + path + "/");
				file.mkdirs();
				String kachoshi = picUrl.substring(picUrl.lastIndexOf("."));
				String picName = picUrl.substring(0, picUrl.lastIndexOf("."));
				picName = picName.substring(picUrl.lastIndexOf("/") + 1);
				picName = picName + kachoshi;
				downloadLoacl(picUrl, picName);
			} catch (Exception e) {
				errList.add(picUrl + " 下载失败！");
			}
		}

		for (int i = 0; i < errList.size(); i++) {
			System.out.println(errList.get(i));
		}
	}

	public void downloadPic2(List<String[]> picList) {
		File file = null;
		String path = null;
		List<String> errList = new ArrayList<String>();
		for (int i = 0; i < picList.size(); i++) {
			String picUrl = picList.get(i)[0];
			try {
				path = picList.get(i)[0];
				path = path.replace("http://image.rakuten.co.jp/trend777/cabinet/", "");
				if (path.contains("http://www.rakuten.ne.jp/gold/trend777/")) {
					path = "gold/" + path.replace("http://www.rakuten.ne.jp/gold/trend777/", "");
				}

				if (path.lastIndexOf("/") > 0) {
					path = path.substring(0, path.lastIndexOf("/"));
				}

				file = new File("D://PIC_DOWNLOAD/trend777/" + path + "/");
				file.mkdirs();
				String kachoshi = picUrl.substring(picUrl.lastIndexOf("."));
				String picName = picUrl.substring(0, picUrl.lastIndexOf("."));
				picName = picName.substring(picUrl.lastIndexOf("/") + 1);
				picName = picName + kachoshi;
				download(picUrl, file.getPath() + "/" + picName);
			} catch (Exception e) {
				errList.add(picUrl + " 下载失败！");
			}
		}

		for (int i = 0; i < errList.size(); i++) {
			System.out.println(errList.get(i));
		}
	}

	private void download(String picUrl, String path) throws Exception {

		URL url = new URL(picUrl);
		File outFile = new File(path);
		if (!outFile.exists()) {
			System.out.println("正在下载：" + picUrl);
			OutputStream os = new FileOutputStream(outFile);
			InputStream is = url.openStream();
			byte[] buff = new byte[1024];
			while (true) {
				int readed = is.read(buff);
				if (readed == -1) {
					break;
				}
				byte[] temp = new byte[readed];
				System.arraycopy(buff, 0, temp, 0, readed);
				os.write(temp);
			}
			is.close();
			os.close();
		}
	}

	private void downloadLoacl(String picUrl, String path) throws Exception {

		picUrl = picUrl.replace("https://image.rakuten.co.jp/coverforefront/cabinet/", "D://coverforefront2/");
		Utility.copyFile(picUrl, "D:/PIC_DOWNLOAD/", path);
	}

	public ArrayList<String[]> replace123martPic(ArrayList<String[]> csvList_123mart) {
		String[] record = null;
		for (int i = 0; i < csvList_123mart.size(); i++) {
			record = csvList_123mart.get(i);
			record[26] = replacePic(record[26]);
			record[27] = replacePic(record[27]);
			record[28] = replacePic(record[28]);
		}
		return csvList_123mart;

	}

	private String replacePic(String str) {
		str = str.replaceAll("http://image.rakuten.co.jp/123mart/cabinet/",
				"http://image.rakuten.co.jp/3eshop/cabinet/123mart/");
		str = str.replaceAll("http://www.rakuten.ne.jp/gold/123mart/images/heartskyYY/",
				"http://image.rakuten.co.jp/3eshop/cabinet/123mart123/hyy/");
		str = str.replaceAll("http://www.rakuten.ne.jp/gold/123mart/",
				"http://image.rakuten.co.jp/3eshop/cabinet/123mart123/");
		str = str.replaceAll("http://image.rakuten.co.jp/3eshop/cabinet/123mart123/images/firstImg/",
				"http://image.rakuten.co.jp/3eshop/cabinet/123mart123/fi/");
		str = str.replaceAll("http://image.rakuten.co.jp/heartsky/cabinet/",
				"http://image.rakuten.co.jp/3eshop/cabinet/heartsky/");
		str = str.replaceAll("03367724", "iphone");
		String temp = str;
		while (temp.contains("img")) {
			if (isNum(temp.substring(temp.indexOf("img") + 3, temp.indexOf("img") + 4))) {
				System.out.println(temp);
				break;
			} else {
				temp = temp.replace("img", "");
			}
		}

		return str;
	}

	public void rewriteCsv(String path, List<String[]> csvList) throws Exception {
		try {
			File file = new File(path);
			file.createNewFile();
			CsvWriter writer = new CsvWriter(path, ',', Charset.forName("SJIS"));
		} finally {
			// bufferedWrite.close();
		}
	}

	public void downCsv(String path, List<String[]> csvList_123mart) throws Exception {
		// BufferedWriter bufferedWrite = null;
		// String tempStr = "";

		try {
			File file = new File(path);
			file.createNewFile();
			CsvWriter writer = new CsvWriter(path, ',', Charset.forName("SJIS"));
			// String title =
			// "コントロールカラム,商品管理番号（商品URL）,商品番号,全商品ディレクトリID,タグID,PC用キャッチコピー,モバイル用キャッチコピー,商品名,販売価格,表示価格,消費税,送料,個別送料,送料区分1,送料区分2,代引料,倉庫指定,商品情報レイアウト,注文ボタン,資料請求ボタン,商品問い合わせボタン,再入荷お知らせボタン,モバイル表示,のし対応,PC用商品説明文,モバイル用商品説明文,スマートフォン用商品説明文,PC用販売説明文,商品画像URL,商品画像名（ALT）,動画,販売期間指定,注文受付数,在庫タイプ,在庫数,在庫数表示,項目選択肢別在庫用横軸項目名,項目選択肢別在庫用縦軸項目名,項目選択肢別在庫用残り表示閾値,RAC番号,サーチ非表示,闇市パスワード,カタログID,在庫戻しフラグ,在庫切れ時の注文受付,在庫あり時納期管理番号,在庫切れ時納期管理番号,予約商品発売日,ポイント変倍率,ポイント変倍率適用期間,ヘッダー・フッター・レフトナビ,表示項目の並び順,共通説明文（小）,目玉商品,共通説明文（大）,レビュー本文表示,あす楽配送管理番号,海外配送管理番号,サイズ表リンク";
			// String[] titlearr = title.split(",");
			// writer.writeRecord(titlearr);
			// bufferedWrite
			// .write("\"コントロールカラム\",\"商品管理番号（商品URL）\",\"商品番号\",\"全商品ディレクトリID\",\"タグID\",\"PC用キャッチコピー\",\"モバイル用キャッチコピー\",\"商品名\",\"販売価格\",\"表示価格\",\"消費税\",\"送料\",\"個別送料\",\"送料区分1\",\"送料区分2\",\"代引料\",\"倉庫指定\",\"商品情報レイアウト\",\"注文ボタン\",\"資料請求ボタン\",\"商品問い合わせボタン\",\"再入荷お知らせボタン\",\"モバイル表示\",\"のし対応\",\"PC用商品説明文\",\"モバイル用商品説明文\",\"スマートフォン用商品説明文\",\"PC用販売説明文\",\"商品画像URL\",\"商品画像名（ALT）\",\"動画\",\"販売期間指定\",\"注文受付数\",\"在庫タイプ\",\"在庫数\",\"在庫数表示\",\"項目選択肢別在庫用横軸項目名\",\"項目選択肢別在庫用縦軸項目名\",\"項目選択肢別在庫用残り表示閾値\",\"RAC番号\",\"サーチ非表示\",\"闇市パスワード\",\"カタログID\",\"在庫戻しフラグ\",\"在庫切れ時の注文受付\",\"在庫あり時納期管理番号\",\"在庫切れ時納期管理番号\",\"予約商品発売日\",\"ポイント変倍率\",\"ポイント変倍率適用期間\",\"ヘッダー・フッター・レフトナビ\",\"表示項目の並び順\",\"共通説明文（小）\",\"目玉商品\",\"共通説明文（大）\",\"レビュー本文表示\",\"あす楽配送管理番号\",\"海外配送管理番号\",\"サイズ表リンク\"");
			// bufferedWrite.newLine();
			// for (int i = 0; i < csvList_123mart.size(); i++) {
			// for (int j = 0; j < csvList_123mart.get(i).length; j++) {
			// tempStr += (csvList_123mart.get(i)[j] + ",");
			// }
			// tempStr = tempStr.substring(0, tempStr.length() - 1);
			// tempStr = tempStr.replace("\n", "");
			// bufferedWrite.write(tempStr);
			// tempStr = "";
			// bufferedWrite.newLine();
			// }
			//
			// bufferedWrite.flush();
			for (int i = 0; i < csvList_123mart.size(); i++) {
				String[] record = csvList_123mart.get(i);
				writer.writeRecord(record);
			}
		} finally {
			// bufferedWrite.close();
		}
	}

	public ArrayList<String[]> add3eLink(ArrayList<String[]> csvList_123mart) {
		String shouhinbango = "";
		String setumeibun = "";
		String[] record = null;
		for (int i = 0; i < csvList_123mart.size(); i++) {
			record = csvList_123mart.get(i);
			shouhinbango = record[1];
			setumeibun = "<style type=\"text/css\"><!--a:hover img{filter: alpha(opacity=70);-moz-opacity:0.7;opacity:0.7;}--></style>"
					+ record[27];
			setumeibun += "<table align=\"center\"><tr><td><a href=\"";
			setumeibun += "http://item.rakuten.co.jp/3eshop/";
			setumeibun += shouhinbango;
			setumeibun += "\" target=\"_top\"><img border=\"0\" src=\"http://www.rakuten.ne.jp/gold/123mart/images/3e_link.jpg\"></img></a></td></tr></table>";
			record[27] = setumeibun;

			setumeibun = record[24];
			setumeibun += "<table align=\"center\"><tr><td><a href=\"";
			setumeibun += "http://item.rakuten.co.jp/3eshop/";
			setumeibun += shouhinbango;
			setumeibun += "\" target=\"_top\"><img width=\"500px\" border=\"0\" src=\"http://www.rakuten.ne.jp/gold/123mart/images/3e_link.jpg\"></img></a></td></tr></table>";
			record[24] = setumeibun;

			setumeibun = record[26];
			setumeibun += "<br/><br/><a href=\"http://item.rakuten.co.jp/3eshop/" + shouhinbango
					+ "\" target=\"_top\"><font size=\"+1\" color=\"black\"><b>同商品姉妹店舗3Eショップでご購入の場合</b></font><br><font size=\"+3\" color=\"red\">10%OFF＋10倍ポイント獲得</font><br><font size=\"+2\" color=\"blue\">マジで？！</font><br><font size=\"+1\">下記のリンクをクリックして3Eショップで買おう (ﾟoﾟ;;<br>"
					+ "http://item.rakuten.co.jp/3eshop/" + shouhinbango + "</font></a>";
			record[26] = setumeibun;
		}

		return csvList_123mart;

	}

	private boolean isNum(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public List<String[]> getOrders(File file) {
		ArrayList<String[]> csvList2 = new ArrayList<String[]>();
		try {
			ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
			CsvReader reader = new CsvReader(new FileInputStream(file), ',', Charset.forName("SJIS")); // 一般用这编码读就可以了

			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。

			while (reader.readRecord()) { // 逐行读入除表头的数据
				csvList.add(reader.getValues());
			}
			reader.close();

			for (int row = 0; row < csvList.size(); row++) {
				String[] strArr = csvList.get(row);
				// 受注番号
				String juchubango = strArr[0];
				boolean ariFlg = false;
				for (int j = row + 1; j < csvList.size(); j++) {
					String[] strArr2 = csvList.get(j);
					// 受注番号
					String juchubango2 = strArr2[0];
					if (juchubango.equals(juchubango2)) {
						ariFlg = true;
						break;
					}

				}
				if (!ariFlg) {
					csvList2.add(csvList.get(row));
				}
			}

		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			return csvList2;
		}
	}

	public void converPicToYahooFormat(String rootPath) throws IOException {
		File rootDir = new File(rootPath + "/coverforefront");
		String[] dirList = rootDir.list();
		for (String shohinbango : dirList) {
			File dir = new File(rootPath + "/coverforefront" + "/" + shohinbango);
			String[] picList = dir.list();
			int count = picList.length;
			if (count > 5) {
				copyFile(rootPath + "/coverforefront" + "/" + shohinbango + "/" + picList[picList.length - 6],
						rootPath + "\\img\\" + shohinbango.replace(".jpg", "") + "_1" + ".jpg");
				copyFile(rootPath + "/coverforefront" + "/" + shohinbango + "/" + picList[picList.length - 5],
						rootPath + "\\img\\" + shohinbango.replace(".jpg", "") + "_1" + ".jpg");
				copyFile(rootPath + "/coverforefront" + "/" + shohinbango + "/" + picList[picList.length - 4],
						rootPath + "\\img\\" + shohinbango.replace(".jpg", "") + "_2" + ".jpg");
				copyFile(rootPath + "/coverforefront" + "/" + shohinbango + "/" + picList[picList.length - 3],
						rootPath + "\\img\\" + shohinbango.replace(".jpg", "") + "_3" + ".jpg");
				copyFile(rootPath + "/coverforefront" + "/" + shohinbango + "/" + picList[picList.length - 2],
						rootPath + "\\img\\" + shohinbango.replace(".jpg", "") + "_4" + ".jpg");
				copyFile(rootPath + "/coverforefront" + "/" + shohinbango + "/" + picList[picList.length - 1],
						rootPath + "\\img\\" + shohinbango.replace(".jpg", "") + "_5" + ".jpg");

			} else {
				for (int i = picList.length - 1; i > 0; i--) {
					copyFile(rootPath + "coverforefront" + "/" + shohinbango + "/" + picList[i],
							rootPath + "\\img\\" + shohinbango.replace(".jpg", "") + "_" + i + ".jpg");
				}
			}
			copyFile(rootPath + "coverforefront" + "/" + shohinbango + "/" + picList[0],
					rootPath + "\\img\\" + shohinbango + ".jpg");

			for (String picName : picList) {
				copyFile(rootPath + "coverforefront" + "/" + shohinbango + "/" + picName,
						rootPath + "/" + "lib/" + shohinbango + "_" + picName);
			}
		}
	}

	private void copyFile(String source1, String target1) throws IOException {
		File source = new File(source1);
		File target = new File(target1);
		System.out.println(target);
		FileChannel in = null;
		FileChannel out = null;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			inStream = new FileInputStream(source);
			outStream = new FileOutputStream(target);
			in = inStream.getChannel();
			out = outStream.getChannel();
			in.transferTo(0, in.size(), out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			inStream.close();
			in.close();
			outStream.close();
			out.close();
		}
	}

	public void compressImg(String rootPath) throws IOException {

		File imgFile = new File(rootPath + "/img");
		String[] imgFileList = imgFile.list();
		int count = 0;
		if (0 == imgFileList.length % 100) {
			count = imgFileList.length / 100;
		} else {
			count = imgFileList.length / 100 + 1;
		}
		for (int i = 0; i < count; i++) {
			File dirFile = new File(rootPath + "/zip/" + "img/image" + i + "/");
			dirFile.mkdirs();
			if (i * 100 + 100 <= imgFileList.length) {
				for (int j = i * 100; j < i * 100 + 100; j++) {
					copyFile(rootPath + "/img/" + imgFileList[j],
							rootPath + "/zip/" + "img/image" + i + "/" + imgFileList[j]);
				}
			} else {
				for (int j = i * 100; j < imgFileList.length; j++) {
					copyFile(rootPath + "/img/" + imgFileList[j],
							rootPath + "/zip/" + "img/image" + i + "/" + imgFileList[j]);
				}
			}
			File zipdir = new File(rootPath + "/zip/" + "img/image" + i + "/");
			String[] fileArr = zipdir.list();
			List<File> fileList = new ArrayList<File>();
			for (String filename : fileArr) {
				fileList.add(new File(rootPath + "/zip/" + "img/image" + i + "/" + filename));
			}

			File[] srcfile = new File[] {};
			srcfile = fileList.toArray(srcfile);
			// 压缩后的文件
			File zipfile = new File(rootPath + "/zip/" + "img/image" + i + ".zip");
			Utility.zipFiles(srcfile, zipfile);

			delFolder(rootPath + "/zip/" + "img/image" + i);
		}

		File libFile = new File(rootPath + "/lib");
		String[] libFileList = libFile.list();
		count = 0;
		if (0 == libFileList.length % 100) {
			count = libFileList.length / 100;
		} else {
			count = libFileList.length / 100 + 1;
		}
		for (int i = 0; i < count; i++) {
			File dirFile = new File(rootPath + "/zip/" + "lib/image" + i + "/");
			dirFile.mkdirs();
			if (i * 100 + 100 <= libFileList.length) {
				for (int j = i * 100; j < i * 100 + 100; j++) {
					copyFile(rootPath + "/lib/" + libFileList[j],
							rootPath + "/zip/" + "lib/image" + i + "/" + libFileList[j]);
				}

			} else {
				for (int j = i * 100; j < libFileList.length; j++) {
					copyFile(rootPath + "/lib/" + libFileList[j],
							rootPath + "/zip/" + "lib/image" + i + "/" + libFileList[j]);
				}
			}
			File zipdir = new File(rootPath + "/zip/" + "lib/image" + i + "/");
			String[] fileArr = zipdir.list();
			List<File> fileList = new ArrayList<File>();
			for (String filename : fileArr) {
				fileList.add(new File(rootPath + "/zip/" + "lib/image" + i + "/" + filename));
			}

			File[] srcfile = new File[] {};
			srcfile = fileList.toArray(srcfile);
			// 压缩后的文件
			File zipfile = new File(rootPath + "/zip/" + "lib/image" + i + ".zip");
			Utility.zipFiles(srcfile, zipfile);

			delFolder(rootPath + "/zip/" + "lib/image" + i);
		}

	}

	public void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			java.io.File myFilePath = new java.io.File(filePath);
			myFilePath.delete(); // 删除空文件夹
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
	public boolean delAllFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if (!file.exists()) {
			return flag;
		}
		if (!file.isDirectory()) {
			return flag;
		}
		String[] tempList = file.list();
		File temp = null;
		for (int i = 0; i < tempList.length; i++) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + tempList[i]);
			} else {
				temp = new File(path + File.separator + tempList[i]);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
				delFolder(path + "/" + tempList[i]);// 再删除空文件夹
				flag = true;
			}
		}
		return flag;
	}

	public List<String[]> removeSale(List<String[]> csvList_3eshop_sel) {
		for (String[] arr : csvList_3eshop_sel) {
			if (arr[1].startsWith("sale")) {
				arr[1] = arr[1].substring(4);
			}
		}
		return csvList_3eshop_sel;
	}

}
