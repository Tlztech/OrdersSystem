package com.rakuten.common.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.bean.PicBean;
import com.rakuten.common.bean.ShohinBean;
import com.rakuten.common.bean.ShohinInfoBean;
import com.rakuten.common.bean.ShohinkategoriBean;
import com.rakuten.common.bean.ShohinsentakushiBean;
import com.rakuten.common.bean.YahooShohinCsvBean;
import com.rakuten.r0602.action.CsvTool;
import com.rakuten.util.Utility;

public class RakutenToYahooNew {

	public static void main(String[] args) throws Exception {

		String itemCsvPath = "d:\\item20190914205151.csv";
		String itemCatCsvPath = "d:\\item-cat20190914205157.csv";
		String selectCsvPath = "d:\\select20190914205154.csv";
		List<YahooShohinCsvBean> dataList = setYahooCsvBean(itemCsvPath, itemCatCsvPath, selectCsvPath);
		writeYahooCsv(dataList, "D:/yahoo.csv");
		shusei("D:/yahoo.csv");
		 getpic(itemCsvPath);
		 compressImg("D:\\yahoo\\firstimg\\");
		 compressImg("D:\\yahoo\\shosaiimg\\");
		 compressImg2("D:\\yahoo\\tuikaimg\\");
	}

	private static void getpic(String itemCsvPath) throws Exception {

		List<String[]> csvList = Utility.readCsvFile(new File(itemCsvPath), true);
		List<String[]> picList = getPicUrl(csvList);
		List<PicBean> picbeanList = new ArrayList<PicBean>();
		PicBean picbean = null;
		String shohinbango = "";
		String shohinbango_now = "";
		for (String[] pic : picList) {
			shohinbango = pic[1];
			if (Utility.isEmptyString(shohinbango_now) || !shohinbango.equals(shohinbango_now)) {
				picbean = new PicBean();
				picbeanList.add(picbean);
				picbean.setPicList(new ArrayList<String>());
				picbean.setShohinbango(shohinbango);
			}
			picbean.getPicList().add(pic[0]);
			shohinbango_now = pic[1];
		}
		File firstimg = new File("D:\\yahoo\\firstimg\\");
		if (!firstimg.exists()) {
			firstimg.mkdirs();
		}
		File shosaiimg = new File("D:\\yahoo\\shosaiimg\\");
		if (!shosaiimg.exists()) {
			shosaiimg.mkdirs();
		}
		File tuikaimg = new File("D:\\yahoo\\tuikaimg\\");
		if (!tuikaimg.exists()) {
			tuikaimg.mkdirs();
		}
		for (PicBean pic : picbeanList) {
			String shohinbnago = pic.getShohinbango();
			System.out.println(shohinbnago);
			List<String> picLurlist = pic.getPicList();
			Utility.copyFile(picLurlist.get(0), "D:\\yahoo\\firstimg\\", shohinbnago + ".jpg");

			int count = picLurlist.size();

			if (count > 5) {
				Utility.copyFile(picLurlist.get(count - 6), "D:\\yahoo\\shosaiimg\\", shohinbnago + "_1.jpg");
				Utility.copyFile(picLurlist.get(count - 5), "D:\\yahoo\\shosaiimg\\", shohinbnago + "_2.jpg");
				Utility.copyFile(picLurlist.get(count - 3), "D:\\yahoo\\shosaiimg\\", shohinbnago + "_3.jpg");
				Utility.copyFile(picLurlist.get(count - 2), "D:\\yahoo\\shosaiimg\\", shohinbnago + "_4.jpg");
				Utility.copyFile(picLurlist.get(count - 1), "D:\\yahoo\\shosaiimg\\", shohinbnago + "_5.jpg");
			} else {
				for (int i = picLurlist.size() - 1; i > 0; i--) {
					Utility.copyFile(picLurlist.get(i), "D:\\yahoo\\shosaiimg\\", shohinbnago + "_" + i + ".jpg");
				}
			}

		}

	}

	private static List<String[]> getPicUrl(List<String[]> csvList) {
		String shohinsetumeibun = "";
		String picUrl = "";
		boolean ariFlg = false;
		List<String[]> picList = new ArrayList<String[]>();
		for (int i = 0; i < csvList.size(); i++) {
			shohinsetumeibun = csvList.get(i)[27];
			while (shohinsetumeibun.contains(".jpg")) {
				shohinsetumeibun = shohinsetumeibun.substring(shohinsetumeibun.indexOf("http"));
				picUrl = shohinsetumeibun.substring(0, shohinsetumeibun.indexOf(".jpg") + 4);
				shohinsetumeibun = shohinsetumeibun.substring(shohinsetumeibun.indexOf(".jpg") + 4);
				picUrl = picUrl.replace("https://image.rakuten.co.jp/coverforefront/cabinet/", "");
				if (picUrl.contains("https://www.rakuten.ne.jp/gold/coverforefront/")) {
					picUrl = picUrl.replace("https://www.rakuten.ne.jp/gold/coverforefront/", "/gold/");
				}
				picUrl = "D:\\coverforefront\\" + picUrl;
				for (int j = 0; j < picList.size(); j++) {
					if (picUrl.equals(picList.get(j))) {
						ariFlg = true;
						break;
					}
				}
				if (!ariFlg) {
					picList.add(new String[] { picUrl, csvList.get(i)[1] });
				}
				ariFlg = false;
			}
		}
		return picList;
	}

	public static void compressImg(String rootPath) throws IOException {

		File imgFile = new File(rootPath);
		String[] imgFileList = imgFile.list();
		int count = 0;
		if (0 == imgFileList.length % 500) {
			count = imgFileList.length / 500;
		} else {
			count = imgFileList.length / 500 + 1;
		}
		for (int i = 0; i < count; i++) {
			File dirFile = new File(rootPath + "/zip/" + "img/image" + i + "/");
			dirFile.mkdirs();
			if (i * 500 + 500 <= imgFileList.length) {
				for (int j = i * 500; j < i * 500 + 500; j++) {
					copyFile(rootPath + imgFileList[j], rootPath + "/zip/" + "img/image" + i + "/" + imgFileList[j]);
				}
			} else {
				for (int j = i * 500; j < imgFileList.length; j++) {
					copyFile(rootPath + imgFileList[j], rootPath + "/zip/" + "img/image" + i + "/" + imgFileList[j]);
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

	}

	public static void compressImg2(String rootPath) throws IOException {

		File imgFile = new File(rootPath);
		String[] imgFileList = imgFile.list();
		int count = 0;
		if (0 == imgFileList.length % 200) {
			count = imgFileList.length / 200;
		} else {
			count = imgFileList.length / 200 + 1;
		}
		for (int i = 0; i < count; i++) {
			File dirFile = new File(rootPath + "/zip/" + "img/image" + i + "/");
			dirFile.mkdirs();
			if (i * 200 + 200 <= imgFileList.length) {
				for (int j = i * 200; j < i * 200 + 200; j++) {
					copyFile(rootPath + imgFileList[j], rootPath + "/zip/" + "img/image" + i + "/" + imgFileList[j]);
				}
			} else {
				for (int j = i * 200; j < imgFileList.length; j++) {
					copyFile(rootPath + imgFileList[j], rootPath + "/zip/" + "img/image" + i + "/" + imgFileList[j]);
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

	}

	private static void copyFile(String source1, String target1) throws IOException {
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

	public static void delFolder(String folderPath) {
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

	public static boolean delAllFile(String path) {
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

	private static void shusei(String path) throws Exception {
		File file = new File(path);
		List<String[]> dataList = Utility.readCsvFile(file, true);
		List<String[]> shoriList = new ArrayList<String[]>();
		String[] title = new String[] { "path", "name", "code", "sub-code", "original-price", "price", "sale-price",
				"options", "headline", "caption", "abstract", "explanation", "additional1", "additional2",
				"additional3", "relevant-links", "ship-weight", "taxable", "release-date", "temporary-point-term",
				"point-code", "meta-key", "meta-desc", "template", "sale-period-start", "sale-period-end", "sale-limit",
				"sp-code", "brand-code", "person-code", "yahoo-product-code", "product-code", "jan", "isbn", "delivery",
				"astk-code", "condition", "taojapan", "product-category", "spec1", "spec2", "spec3", "spec4", "spec5",
				"display", "sort", "sp-additional", "sort_priority" };
		shoriList.add(title);
		for (String[] data : dataList) {
			String spdata = data[46];
			spdata = spdata.replace("<table align=\"center\">", "<table width=\"100%\" align=\"center\">");
			spdata = spdata.replace("<td><img ", "<td><img width=\"98%\" ");
			spdata = spdata.replace("<table width=\"600px\"", "<table width=\"100%\"");
			spdata = spdata.replace("<tr height=\"35px\">", "<tr>");
			spdata = spdata.replace("<td width=\"120px\"", "<td ");
			spdata = spdata.replace("<table width=\"550px\"", "<table width=\"100%\"");
			spdata = spdata.replace("<td width=\"50px\"", "<td ");
			spdata = spdata.replace("<td width=\"93px\"", "<td ");
			spdata = spdata.replace("<td><img width=\"650px\"", "<td><img width=\"98%\" ");
			spdata = spdata.replace(" width=\"650\"></img></td>", "></img></td>");
			spdata = spdata.replace("<td><img width=\"98%\" width=\"650px\" ", "<td><img width=\"98%\" ");
			spdata = spdata.replace("<table width=\"650px\" style=", "<table width=\"100%\" style=");
			spdata = spdata.replace("<td width=\"20%\" style", "<td style");
			spdata = spdata.replace(
					"<table style=\"font-size:12px\" width=\"550px\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">",
					"<table style=\"font-size:12px\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">");
			spdata = spdata.replace("<table width=\"780px\">", "<table width=\"100%\">");
			spdata = spdata.replace("<table width=\"650px\" align=\"center\">",
					"<table width=\"100%\" align=\"center\">");
			spdata = spdata.replace(".STYLE3 {font-family: \"MS PGothic\"; font-size: 14px; }", "");

			data[46] = spdata;

			String[] cateList = data[0].split("\n");
			List<String> cateAll = new ArrayList<String>();
			for (String cate : cateList) {
				String[] cateArr = cate.split(":");

				for (int i = 0; i < cateArr.length; i++) {
					String inputCate = "";
					for (int j = 0; j < i; j++) {
						inputCate = inputCate + cateArr[j] + ":";
					}
					inputCate = inputCate + cateArr[i];
					cateAll.add(inputCate);
				}

			}

			List<String> shoriCateList = new ArrayList<String>();
			for (String cate : cateAll) {
				boolean ariFlg = false;
				for (String acate : shoriCateList) {
					if (acate.equals(cate)) {
						ariFlg = true;
					}
				}
				if (!ariFlg) {
					shoriCateList.add(cate);
				}
			}

			String joinCate = "";
			for (String cate : shoriCateList) {
				joinCate = joinCate + cate + "\n";
			}
			joinCate = joinCate.substring(0, joinCate.length() - 1);
			data[0] = joinCate;

			shoriList.add(data);
		}

		Utility.writeCsvFile(shoriList, "D:\\yahoo_shusei.csv");

	}

	private static String subStr4Byte(String str, int len) {
		String strRet = str;
		if (str != null) {
			byte[] bt = str.getBytes();
			if (bt.length > len) {
				// 半个漢字时、byte値<0
				if (bt[len - 1] < 0) {
					strRet = new String(bt, 0, len - 1);
				} else {
					strRet = new String(bt, 0, len);
				}
			}
		}
		return strRet;
	}

	private static List<YahooShohinCsvBean> setYahooCsvBean(String itemCsvPath, String itemCatCsvPath,
			String selectCsvPath) throws Exception {

		List<YahooShohinCsvBean> yahooBeanList = new ArrayList<YahooShohinCsvBean>();
		YahooShohinCsvBean yahooBean = null;

		List<ShohinBean> shohinList = Utility.getShohinFromCsv(new File(itemCsvPath), new File(itemCatCsvPath),
				new File(selectCsvPath));

		for (ShohinBean shohin : shohinList) {

			yahooBean = new YahooShohinCsvBean();
			yahooBeanList.add(yahooBean);

			ShohinInfoBean info = shohin.getShohinInfoBean();
			List<ShohinsentakushiBean> shohinsentakushiBean = shohin.getShohinsentakushiBeanList();

			// path
			String path = "";
			for (ShohinkategoriBean catBean : shohin.getShohinkategoriBeanList()) {
				if (Utility.isEmptyString(path)) {
					path = catBean.getHuojisakikategori().replace("\\", ":");
				} else {
					path += "\r\n" + catBean.getHuojisakikategori().replace("\\", ":");
				}
			}
			yahooBean.setPath(path);
			// name
			String name = info.getShouhinmei();
			if (name.getBytes().length > 150) {
				name = subStr4Byte(name, 150);
			}
			yahooBean.setName(name);
			// code
			yahooBean.setCode(info.getShouhinbango());
			// sub_code
			String subcode = "";
			for (ShohinsentakushiBean sentakubean : shohinsentakushiBean) {
				if ("i".equals(sentakubean.getSentakutaipu())) {
					if (!Utility.isEmptyString(subcode)) {
						subcode = subcode + "&";
					}
					if (!Utility.isEmptyString(info.getKomokusentakushibetuzaikoyouyokojikukoumokumei())) {
						subcode += (info.getKomokusentakushibetuzaikoyouyokojikukoumokumei().replace(" ", "　")
								.replace("|", "｜").replace(";", "；").replace(":", "：").replace("&", "＆")
								.replace("=", "＝").replace("#", "＃").replace("\\", "￥").replace("\"", "”")
								+ ":"
								+ sentakubean.getKomokusentakushibetuzaikoyouyokojikusentakushi().replace(" ", "　")
										.replace("|", "｜").replace(";", "；").replace(":", "：").replace("&", "＆")
										.replace("=", "＝").replace("#", "＃").replace("\\", "￥").replace("\"", "”"));
					}
					if (!Utility.isEmptyString(info.getKomokusentakushibetuzaikoyoutatejikukomokumei())) {
						subcode += ("#"
								+ info.getKomokusentakushibetuzaikoyoutatejikukomokumei().replace(" ", "　")
										.replace("|", "｜").replace(";", "；").replace(":", "：").replace("&", "＆")
										.replace("=", "＝").replace("#", "＃").replace("\\", "￥").replace("\"", "”")
								+ ":"
								+ sentakubean.getKomokusentakushizaikoyoutatejikusentakushi().replace(" ", "　")
										.replace("|", "｜").replace(";", "；").replace(":", "：").replace("&", "＆")
										.replace("=", "＝").replace("#", "＃").replace("\\", "￥").replace("\"", "”"));
					}
					subcode += "=";
					subcode += info.getShouhinbango();
					if (!Utility.isEmptyString(info.getKomokusentakushibetuzaikoyouyokojikukoumokumei())) {
						subcode += sentakubean.getKomokusentakushibetuzaikoyoyokojikusentakushishibango()
								.replace(" ", "　").replace("|", "｜").replace(";", "；").replace(":", "：")
								.replace("&", "＆").replace("=", "＝").replace("#", "＃").replace("\\", "￥")
								.replace("\"", "”");
					}
					if (!Utility.isEmptyString(info.getKomokusentakushibetuzaikoyoutatejikukomokumei())) {
						subcode += sentakubean.getKomokusentakushibetuzaikoyotatejikusentakushishibango()
								.replace(" ", "　").replace("|", "｜").replace(";", "；").replace(":", "：")
								.replace("&", "＆").replace("=", "＝").replace("#", "＃").replace("\\", "￥")
								.replace("\"", "”");
					}

				}
			}
			yahooBean.setSub_code(subcode);
			// original_price
			yahooBean.setOriginal_price(info.getHyojikakaku());
			// price
			yahooBean.setPrice(info.getHanbaikakaku());
			// sale_price
			// options
			String options = "";
			if (!Utility.isEmptyString(info.getKomokusentakushibetuzaikoyouyokojikukoumokumei())) {
				options = info.getKomokusentakushibetuzaikoyouyokojikukoumokumei();
				String yokosentakushi = "";
				for (ShohinsentakushiBean sentaku : shohinsentakushiBean) {
					if ("i".equals(sentaku.getSentakutaipu())
							&& !sentaku.getKomokusentakushibetuzaikoyouyokojikusentakushi().equals(yokosentakushi)) {
						options = (options + " "
								+ sentaku.getKomokusentakushibetuzaikoyouyokojikusentakushi().replace(" ", "　")
										.replace("|", "｜").replace(";", "；").replace(":", "：").replace("&", "＆")
										.replace("=", "＝").replace("#", "＃").replace("\\", "￥").replace("\"", "”"));
						yokosentakushi = sentaku.getKomokusentakushibetuzaikoyouyokojikusentakushi();
					}

				}

			}
			if (!Utility.isEmptyString(info.getKomokusentakushibetuzaikoyoutatejikukomokumei())) {
				if (!Utility.isEmptyString(options)) {
					options = options + "\n\n";
				}
				options += info.getKomokusentakushibetuzaikoyoutatejikukomokumei();
				String yokosentakushi = shohinsentakushiBean.get(0).getKomokusentakushibetuzaikoyouyokojikusentakushi();
				for (ShohinsentakushiBean sentaku : shohinsentakushiBean) {

					if (!yokosentakushi.equals(sentaku.getKomokusentakushibetuzaikoyouyokojikusentakushi())) {
						break;
					}
					if ("i".equals(sentaku.getSentakutaipu())) {
						options = (options + " "
								+ sentaku.getKomokusentakushizaikoyoutatejikusentakushi().replace(" ", "　")
										.replace("|", "｜").replace(";", "；").replace(":", "：").replace("&", "＆")
										.replace("=", "＝").replace("#", "＃").replace("\\", "￥").replace("\"", "”"));
					}
					yokosentakushi = sentaku.getKomokusentakushibetuzaikoyouyokojikusentakushi();

				}
			}
			String sbungen = "";
			for (ShohinsentakushiBean sentaku : shohinsentakushiBean) {

				if ("s".equals(sentaku.getSentakutaipu())) {

					if (!sbungen.equals(sentaku.getSelectcheckboxyoukomokumei())) {
						if (!Utility.isEmptyString(options)) {
							options = options + "\n\n";
						}
						options = (options
								+ sentaku.getSelectcheckboxyoukomokumei().replace(" ", "　").replace("|", "｜")
										.replace(";", "；").replace(":", "：").replace("&", "＆").replace("=", "＝")
										.replace("#", "＃").replace("\\", "￥").replace("\"", "”")
								+ " "
								+ sentaku.getSelectcheckboxyousentakushi().replace(" ", "　").replace("|", "｜")
										.replace(";", "；").replace(":", "：").replace("&", "＆").replace("=", "＝")
										.replace("#", "＃").replace("\\", "￥").replace("\"", "”"));
					} else {
						options = (options + " "
								+ sentaku.getSelectcheckboxyousentakushi().replace(" ", "　").replace("|", "｜")
										.replace(";", "；").replace(":", "：").replace("&", "＆").replace("=", "＝")
										.replace("#", "＃").replace("\\", "￥").replace("\"", "”"));
					}

					sbungen = sentaku.getSelectcheckboxyoukomokumei();
				}
			}
			yahooBean.setOptions(options);
			// headline
			String headline = info.getMobairuyokyachikopi();
			if (headline.getBytes().length > 30) {
				headline = subStr4Byte(headline, 30);
			}
			yahooBean.setHeadline(headline);
			// caption
			// abstract_y
			// explanation
			yahooBean.setExplanation(info.getShouhinmei());
			// additional1

			// additional2
			yahooBean.setAdditional2(replaceAdditional1(info.getPcyouhanbaisetumeibun()));
			// additional3
			// relevant_links
			// ship_weight
			String weight = "";
			if (name.contains("【送料無料】")) {
//				weight = "10000";
			} else if (name.contains("【メール便送料無料】")) {
//				weight = "1";
			} else if (options.contains("メール便対応不可")) {
				weight = "1001";
			} else {
				weight = "60";
			}
			yahooBean.setShip_weight(weight);
			// taxable
			// release_date
			// temporary_point_term
			// point_code
			// meta_key
			String meta_key = info.getShouhinmei();
			if (meta_key.getBytes().length > 80) {
				meta_key = subStr4Byte(meta_key, 80);
			}
			yahooBean.setMeta_key(meta_key);
			// meta_desc
			String meta_desc = info.getPcyokyachikopi();
			if (meta_desc.getBytes().length > 80) {
				meta_desc = subStr4Byte(meta_desc, 80);
			}
			yahooBean.setMeta_desc(meta_desc);
			// template
			// sale_period_start
			// sale_period_end
			// sale_limit
			// sp_code
			// brand_code
			// person_code
			// yahoo_product_code
			// product_code
			// jan
			// isbn
			// delivery

			yahooBean.setDelivery(info.getSouryou());
			// astk_code
			yahooBean.setAstk_code("0");
			// condition
			yahooBean.setCondition("0");
			// taojapan
			yahooBean.setTaojapan("1");
			// product_category
			// spec1
			// spec2
			// spec3
			// spec4
			// spec5
			// display
			yahooBean.setDisplay("1");
			// sort
			// sp_additional
			yahooBean.setSp_additional(replaceAdditional1(info.getPcyouhanbaisetumeibun()));
			String product_code = "";
			String rakutendirectid = info.getZenshohindirekutoriId();
			List<String[]> directidList = Utility.readCsvFile(new File("D:\\directid.csv"), false);
			for (String[] directid : directidList) {
				if (directid[0].equals(rakutendirectid)) {
					product_code = directid[1];
					break;
				}
			}
			yahooBean.setProduct_category(product_code);
			yahooBean.setSort_priority(shohin.getShohinkategoriBeanList().get(0).getYusendo());
		}

		return yahooBeanList;

	}

	private static String replaceAdditional1(String pcyouhanbaisetumeibun) {
		pcyouhanbaisetumeibun = pcyouhanbaisetumeibun
				.replace("<style type=\"text/css\"><!--.STYLE1 {font-family: \"MS PGothic\"}--> </style>", "")
				.replace("<br/>", "<br>").replace("<style type=\"text/css\">", "")
				.replace("<style type=\"\"text/css\"\"><!--.STYLE1 {font-family: \"\"MS PGothic\"\"}--></style>", "")
				.replace("<!--", "").replace("-->", "").replace("</style>", "")
				.replace(".STYLE1 {font-family: \"\"MS PGothic\"\"}", "")
				.replace("<!--.STYLE1 {font-family: \"\"MS PGothic\"\"}--></style>", "")
				.replace(".STYLE1 {font-family: \"MS PGothic\"}", "");
		String pcsetumeibuntemp = pcyouhanbaisetumeibun;
		String picUrl = "";
		String baseUrl = "http://shopping.c.yimg.jp/lib/coverforefront/";
		List<String> picList = new ArrayList<String>();
		while (pcsetumeibuntemp.contains(".jpg")) {
			pcsetumeibuntemp = pcsetumeibuntemp.substring(pcsetumeibuntemp.indexOf("http"));
			picUrl = pcsetumeibuntemp.substring(0, pcsetumeibuntemp.indexOf(".jpg") + 4);
			pcsetumeibuntemp = pcsetumeibuntemp.substring(pcsetumeibuntemp.indexOf(".jpg") + 4);
			picList.add(picUrl);
		}

		for (String pic : picList) {
			String kachoshi = pic.substring(pic.lastIndexOf("."));
			String picName = pic.substring(0, pic.lastIndexOf("."));
			picName = picName.substring(pic.lastIndexOf("/") + 1);
			if ("common2".equals(picName) || "model_common1".equals(picName) || "review".equals(picName)
					|| "size-1".equals(picName) || "xfpy_common".equals(picName)) {
				pcyouhanbaisetumeibun = pcyouhanbaisetumeibun.replace(pic, baseUrl + picName + kachoshi);
			} else {
				pcyouhanbaisetumeibun = pcyouhanbaisetumeibun.replace(pic, baseUrl + picName + kachoshi);
			}
		}

		return pcyouhanbaisetumeibun;
	}

	private static void writeYahooCsv(List<YahooShohinCsvBean> yahooShohinCsvBeanList, String path) throws Exception {
		List<String[]> csvList = new ArrayList<String[]>();
		String[] title = new String[] { "path", "name", "code", "sub-code", "original-price", "price", "sale-price",
				"options", "headline", "caption", "abstract", "explanation", "additional1", "additional2",
				"additional3", "relevant-links", "ship-weight", "taxable", "release-date", "temporary-point-term",
				"point-code", "meta-key", "meta-desc", "template", "sale-period-start", "sale-period-end", "sale-limit",
				"sp-code", "brand-code", "person-code", "yahoo-product-code", "product-code", "jan", "isbn", "delivery",
				"astk-code", "condition", "taojapan", "product-category", "spec1", "spec2", "spec3", "spec4", "spec5",
				"display", "sort", "sp-additional", "sort_priority" };
		csvList.add(title);
		for (YahooShohinCsvBean yahooShohinCsvBean : yahooShohinCsvBeanList) {
			String[] shohin = new String[48];
			csvList.add(shohin);
			int i = 0;
			shohin[i++] = yahooShohinCsvBean.getPath();
			shohin[i++] = yahooShohinCsvBean.getName();
			shohin[i++] = yahooShohinCsvBean.getCode();
			shohin[i++] = yahooShohinCsvBean.getSub_code();
			shohin[i++] = yahooShohinCsvBean.getOriginal_price();
			shohin[i++] = yahooShohinCsvBean.getPrice();
			shohin[i++] = yahooShohinCsvBean.getSale_price();

			if (!yahooShohinCsvBean.getOptions().contains("納期について 7〜10営業日以内に発送となります")) {
				yahooShohinCsvBean.setOptions(yahooShohinCsvBean.getOptions() + "\r\n\r\n納期について 7〜10営業日以内に発送となります");
			}
			shohin[i++] = yahooShohinCsvBean.getOptions();
			shohin[i++] = yahooShohinCsvBean.getHeadline();
			shohin[i++] = yahooShohinCsvBean.getCaption();
			shohin[i++] = yahooShohinCsvBean.getAbstract_y();
			shohin[i++] = yahooShohinCsvBean.getExplanation();
			shohin[i++] = yahooShohinCsvBean.getAdditional1();
			shohin[i++] = yahooShohinCsvBean.getAdditional2();
			shohin[i++] = yahooShohinCsvBean.getAdditional3();
			shohin[i++] = yahooShohinCsvBean.getRelevant_links();
			shohin[i++] = yahooShohinCsvBean.getShip_weight();
			shohin[i++] = yahooShohinCsvBean.getTaxable();
			shohin[i++] = yahooShohinCsvBean.getRelease_date();
			shohin[i++] = yahooShohinCsvBean.getTemporary_point_term();
			shohin[i++] = yahooShohinCsvBean.getPoint_code();
			shohin[i++] = yahooShohinCsvBean.getMeta_key();
			shohin[i++] = yahooShohinCsvBean.getMeta_desc();
			shohin[i++] = yahooShohinCsvBean.getTemplate();
			shohin[i++] = yahooShohinCsvBean.getSale_period_start();
			shohin[i++] = yahooShohinCsvBean.getSale_period_end();
			shohin[i++] = yahooShohinCsvBean.getSale_limit();
			shohin[i++] = yahooShohinCsvBean.getSp_code();
			shohin[i++] = yahooShohinCsvBean.getBrand_code();
			shohin[i++] = yahooShohinCsvBean.getPerson_code();
			shohin[i++] = yahooShohinCsvBean.getYahoo_product_code();
			shohin[i++] = yahooShohinCsvBean.getProduct_code();
			shohin[i++] = yahooShohinCsvBean.getJan();
			shohin[i++] = yahooShohinCsvBean.getIsbn();
			shohin[i++] = yahooShohinCsvBean.getDelivery();
			shohin[i++] = yahooShohinCsvBean.getAstk_code();
			shohin[i++] = yahooShohinCsvBean.getCondition();
			shohin[i++] = yahooShohinCsvBean.getTaojapan();
			shohin[i++] = yahooShohinCsvBean.getProduct_category();
			shohin[i++] = yahooShohinCsvBean.getSpec1();
			shohin[i++] = yahooShohinCsvBean.getSpec2();
			shohin[i++] = yahooShohinCsvBean.getSpec3();
			shohin[i++] = yahooShohinCsvBean.getSpec4();
			shohin[i++] = yahooShohinCsvBean.getSpec5();
			shohin[i++] = yahooShohinCsvBean.getDisplay();
			shohin[i++] = yahooShohinCsvBean.getSort();
			shohin[i++] = yahooShohinCsvBean.getSp_additional();
			shohin[i++] = yahooShohinCsvBean.getSort_priority();
		}
		Utility.writeCsvFile(csvList, path);
	}
}
