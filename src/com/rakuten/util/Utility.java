package com.rakuten.util;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;

import org.apache.commons.lang3.StringUtils;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.exceptions.NativeException;

import com.csvreader.CsvReader;
import com.rakuten.common.bean.DetailListBean;
import com.rakuten.common.bean.KingakuBean;
import com.rakuten.common.bean.OrderBean;
import com.rakuten.common.bean.ShohinBean;
import com.rakuten.common.bean.ShohinInfoBean;
import com.rakuten.common.bean.ShohinkategoriBean;
import com.rakuten.common.bean.ShohinsentakushiBean;
import com.rakuten.r1503.form.Type;

public class Utility {
	public static List<String[]> readCsvFile(File file, boolean jumpFlg) throws Exception {
		ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
		CsvReader reader = new CsvReader(new FileInputStream(file), ',', Charset.forName("Shift-JIS")); // 一般用这编码读就可以了
		if (jumpFlg) {
			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
		}
		while (reader.readRecord()) { // 逐行读入除表头的数据
			csvList.add(reader.getValues());
		}
		reader.close();

		return csvList;
	}

	public static String getJpstr(Connection conn, String cnstr) throws Exception {

		String jpstr = "";

		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;

		sql = "SELECT * FROM dic_tbl where c_name = ?";

		ps = conn.prepareStatement(sql);
		ps.setString(1, cnstr);
		rs = ps.executeQuery();
		while (rs.next()) {
			jpstr = rs.getString("j_name");
		}

		return jpstr;
	}

	public static List<String[]> readCsvFile3(File file, boolean jumpFlg) throws Exception {
		ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
		CsvReader reader = new CsvReader(new FileInputStream(file), ',', Charset.forName("UTF-8")); // 一般用这编码读就可以了
		if (jumpFlg) {
			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
		}
		while (reader.readRecord()) { // 逐行读入除表头的数据
			csvList.add(reader.getValues());
		}
		reader.close();

		return csvList;
	}	
	public static List<String[]> readCsvFile5(File file, boolean jumpFlg) throws Exception {
		ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
		CsvReader reader = new CsvReader(new FileInputStream(file), '\t', Charset.forName("UTF-16")); // 一般用这编码读就可以了
		if (jumpFlg) {
			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
		}
		while (reader.readRecord()) { // 逐行读入除表头的数据
			csvList.add(reader.getValues());
		}
		reader.close();

		return csvList;
	}

	public static List<String[]> readCsvFile2(File file, boolean jumpFlg) throws Exception {
		ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
		CsvReader reader = new CsvReader(new FileInputStream(file), ',', Charset.forName("GBK")); // 一般用这编码读就可以了
		if (jumpFlg) {
			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
		}
		while (reader.readRecord()) { // 逐行读入除表头的数据
			csvList.add(reader.getValues());
		}
		reader.close();

		return csvList;
	}

	public static List<String[]> readCsvFileUncode(File file, boolean jumpFlg, String code) throws Exception {
		ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
		CsvReader reader = new CsvReader(new FileInputStream(file), ',', Charset.forName(code)); // 一般用这编码读就可以了
		if (jumpFlg) {
			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。
		}
		while (reader.readRecord()) { // 逐行读入除表头的数据
			csvList.add(reader.getValues());
		}
		reader.close();

		return csvList;
	}

	public static int dayForWeek(String pTime) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

		Calendar c = Calendar.getInstance();

		c.setTime(format.parse(pTime));

		int dayForWeek = 0;

		if (c.get(Calendar.DAY_OF_WEEK) == 1) {

			dayForWeek = 7;

		} else {

			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;

		}

		return dayForWeek;

	}

	public static String getUnsokaisha(String orderNo) throws Exception {
		String unsokaisha = "";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT kaisha from  kaisha_size_tbl where juchubango = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			while (rs.next()) {
				unsokaisha = rs.getString("kaisha");
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return unsokaisha;
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

	// 删除指定文件夹下所有文件
	// param path 文件夹完整绝对路径
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

	public static boolean isNum(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void copyFile(String source1, String target1Dir, String fileName) throws IOException {
		File source = new File(source1);
		File filedir = new File(target1Dir);
		File target = new File(target1Dir + fileName);
		System.out.println(target);
		FileChannel in = null;
		FileChannel out = null;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			if (!filedir.exists()) {
				filedir.mkdirs();
			}
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

	public static void copyFile(File source, String target1Dir, String fileName) throws IOException {

		File filedir = new File(target1Dir);
		File target = new File(target1Dir + fileName);
		System.out.println(target);
		FileChannel in = null;
		FileChannel out = null;
		FileInputStream inStream = null;
		FileOutputStream outStream = null;
		try {
			if (!filedir.exists()) {
				filedir.mkdirs();
			}
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

	public static void writeCsvFile(List<String[]> csvList, String path) throws Exception {
		ICsvListWriter writer = null;
		try {
			OutputStreamWriter fwriter = new OutputStreamWriter(new FileOutputStream(new File(path)),
					Charset.forName("SJIS"));

			writer = new CsvListWriter(fwriter, CsvPreference.EXCEL_PREFERENCE);
			for (int i = 0; i < csvList.size(); i++) {
				String[] record = csvList.get(i);
				writer.write(record);
			}
		} finally {
			writer.close();
		}
	}

	public static void writeCsvFile2(List<String[]> csvList, String path) throws Exception {
		ICsvListWriter writer = null;
		try {
			OutputStreamWriter fwriter = new OutputStreamWriter(new FileOutputStream(new File(path)),
					Charset.forName("gb2312"));

			writer = new CsvListWriter(fwriter, CsvPreference.EXCEL_PREFERENCE);
			for (int i = 0; i < csvList.size(); i++) {
				String[] record = csvList.get(i);
				writer.write(record);
			}
		} finally {
			writer.close();
		}
	}

	public static void writeCsvFile3(List<String[]> csvList, String path) throws Exception {
		ICsvListWriter writer = null;
		try {
			OutputStreamWriter fwriter = new OutputStreamWriter(new FileOutputStream(new File(path)),
					Charset.forName("utf-8"));

			writer = new CsvListWriter(fwriter, CsvPreference.EXCEL_PREFERENCE);
			for (int i = 0; i < csvList.size(); i++) {
				String[] record = csvList.get(i);
				writer.write(record);
			}
		} finally {
			writer.close();
		}
	}

	public static void writeCsvFileTemp(List<String[]> csvList, String path) throws Exception {
		ICsvListWriter writer = null;
		try {
			OutputStreamWriter fwriter = new OutputStreamWriter(new FileOutputStream(new File(path)),
					Charset.forName("SJIS"));

			writer = new CsvListWriter(fwriter, CsvPreference.STANDARD_PREFERENCE);
			for (int i = 0; i < csvList.size(); i++) {
				String[] record = csvList.get(i);
				String[] writeRecod = new String[record.length];
				for (int j = 0; j < record.length; j++) {
					String str = record[j];
					if (Utility.isEmptyString(str)) {
						str = "\"\"";
					} else {
						str = "\"" + str + "\"";
					}
					writeRecod[j] = str;
				}
				writer.write(writeRecod);
			}
		} finally {
			writer.close();
		}
	}

	@SuppressWarnings("finally")
	public static List<OrderBean> getOrders(File file) {
		List<OrderBean> orderList = new ArrayList<OrderBean>();
		try {

			ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
			CsvReader reader = new CsvReader(new FileInputStream(file), ',', Charset.forName("SJIS")); // 一般用这编码读就可以了

			reader.readHeaders(); // 跳过表头 如果需要表头的话，不要写这句。

			while (reader.readRecord()) { // 逐行读入除表头的数据
				csvList.add(reader.getValues());
			}
			reader.close();

			for (int row = 0; row < csvList.size(); row++) {
				OrderBean orderBean = new OrderBean();
				orderList.add(orderBean);
				String[] strArr = csvList.get(row);
				// 受注番号
				String juchubango = strArr[0];
				// 受注ステータス
				String juchusts = strArr[1];
				// お届け時間帯
				String otodokejikandai = strArr[5];
				// お届け日指定
				String otodokebishitei = strArr[6];
				// ひとことメモ
				String hitokotomemo = strArr[8];
				// メール差込文(お客様へのメッセージ)
				String merusashikomibun = strArr[9];
				// 注文日時
				String chumonnichiji = strArr[15];
				// 合計金額(-99999=無効値)
				String gokeikingaku = strArr[23];
				// 同梱ID
				String tongkunID = strArr[25];
				// 同梱ステータス
				String tongkunSts = strArr[26];
				// 同梱商品合計金額
				String tongkunkingaku = strArr[27];
				// 同梱送料合計
				// 同梱代引料合計
				// 同梱消費税合計
				// 同梱請求金額
				String tokunkinkyukingaku = strArr[31];
				if (!Utility.isEmptyString(tokunkinkyukingaku)) {
					gokeikingaku = tokunkinkyukingaku;
				}
				// 決済方法
				String kesaihoho = strArr[59];
				// 配送方法
				String haisouhoho = strArr[66];
				// 送付先郵便番号１
				String sofusakiyubinbango1 = strArr[88];
				// 送付先郵便番号２
				String sofusakiyubinbango2 = strArr[89];
				// 送付先住所：都道府県
				String sofusakijusho1 = strArr[90];
				// 送付先住所：都市区
				String sofusakijusho2 = strArr[91];
				// 送付先住所：町以降
				String sofusakijusho3 = strArr[92];
				// 送付先名字
				String sofusakimeiji = strArr[93];
				// 送付先名前
				String sofusakinamae = strArr[94];
				// 送付先名字フリガナ
				String sofusakimeijifurigana = strArr[95];
				// 送付先名前フリガナ
				String sofusakinamaefurigana = strArr[96];
				// 送付先電話番号１
				String sofusakidenwabango1 = strArr[97];
				// 送付先電話番号２
				String sofusakidenwabango2 = strArr[98];
				// 送付先電話番号３
				String sofusakidenwabango3 = strArr[99];

				// コメント
				String komento = strArr[14];

				// 店舗別
				String tenpobetsu = "";

				if (strArr[103].contains("123mart")) {
					tenpobetsu = "123mart";
				} else {
					tenpobetsu = "3e shop";
				}
				List<DetailListBean> detailList = new ArrayList<DetailListBean>();

				DetailListBean detailBean = new DetailListBean();

				// 商品番号
				detailBean.setShouhinbango(strArr[102].replaceAll("−", "-"));
				// 商品名
				detailBean.setShouhinmei(strArr[101]);
				// 単価
				detailBean.setTankaku(strArr[104]);
				// 個数
				detailBean.setKosu(strArr[105]);
				// 項目・選択肢
				String komokusentakushi = strArr[109];
				detailBean.setKomokusentakushi(komokusentakushi);

				detailList.add(detailBean);
				int i = row;
				for (i = row + 1; i < csvList.size(); i++) {
					String[] detailArr = csvList.get(i);
					if (!juchubango.equals(csvList.get(i)[0])) {
						break;
					}
					DetailListBean detailListBean = new DetailListBean();

					// 商品番号
					detailListBean.setShouhinbango(detailArr[102].replaceAll("−", "-"));
					// 商品名
					detailListBean.setShouhinmei(detailArr[101]);
					// 単価
					detailListBean.setTankaku(detailArr[104]);
					// 個数
					detailListBean.setKosu(detailArr[105]);
					detailListBean.setKomokusentakushi(detailArr[109]);
					detailList.add(detailListBean);

				}
				row = i - 1;

				orderBean.setDetailList(detailList);

				// 受注番号
				orderBean.setJuchubango(juchubango);
				// 受注ステータス
				orderBean.setJuchusts(juchusts);
				// お届け時間帯
				orderBean.setOtodokejikandai(otodokejikandai);
				// お届け日指定
				orderBean.setOtodokebishitei(otodokebishitei);
				// ひとことメモ
				orderBean.setHitokotomemo(hitokotomemo);
				// メール差込文(お客様へのメッセージ)
				orderBean.setMerusashikomibun(merusashikomibun);
				// 注文日時
				orderBean.setChumonnichiji(chumonnichiji);
				// 合計金額(-99999=無効値)
				orderBean.setGokeikingaku(gokeikingaku);
				// 同梱ID
				orderBean.setTongkunID(tongkunID);
				// 同梱ステータス
				orderBean.setTongkunSts(tongkunSts);
				// 同梱商品合計金額
				orderBean.setTongkunkingaku(tongkunkingaku);
				// 同梱送料合計
				// 同梱代引料合計
				// 同梱消費税合計
				// 同梱請求金額
				// 決済方法
				orderBean.setKesaihoho(kesaihoho);
				// 配送方法
				orderBean.setHaisouhoho(haisouhoho);
				// 送付先郵便番号１
				orderBean.setSofusakiyubinbango1(sofusakiyubinbango1);
				// 送付先郵便番号２
				orderBean.setSofusakiyubinbango2(sofusakiyubinbango2);
				// 送付先住所：都道府県
				orderBean.setSofusakijusho1(sofusakijusho1);
				// 送付先住所：都市区
				orderBean.setSofusakijusho2(sofusakijusho2);
				// 送付先住所：町以降
				orderBean.setSofusakijusho3(sofusakijusho3);
				// 送付先名字
				orderBean.setSofusakimeiji(sofusakimeiji);
				// 送付先名前
				orderBean.setSofusakinamae(sofusakinamae);
				// 送付先名字フリガナ
				orderBean.setSofusakimeijifurigana(sofusakimeijifurigana);
				// 送付先名前フリガナ
				orderBean.setSofusakinamaefurigana(sofusakinamaefurigana);
				// 送付先電話番号１
				orderBean.setSofusakidenwabango1(sofusakidenwabango1);
				// 送付先電話番号２
				orderBean.setSofusakidenwabango2(sofusakidenwabango2);
				// 送付先電話番号３
				orderBean.setSofusakidenwabango3(sofusakidenwabango3);
				// コメント
				orderBean.setKomento(komento);

				orderBean.setTenpobetsu(tenpobetsu);
			}

		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			return orderList;
		}
	}

	public static boolean isEmptyString(String str) {
		if (str == null || "".equals(str)) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("rawtypes")
	public static boolean isEmptyList(List list) {
		if (list == null || list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

	public static String replaceNullToBlank(String str) {
		return str == null ? "" : str;
	}

	public static String getCommodityId(String str) {
		if (str.contains("-")) {
			return str.substring(0, str.indexOf("-"));

		} else {
			return str;

		}
	}

	public static String getDetailN0(String str) {
		if (str.contains("-")) {
			return str.substring(str.indexOf("-"));
		} else {
			return "-0-0";
		}

	}

	public static String barcode_print(String barcode_str, String commodityId, String kosu)
			throws NativeException, IllegalAccessException {
		try {

			// TODO Auto-generated method stub
			// 系统加载dll文件有两种写法1.loadLibrary方法:把dll文件拷贝到c:\windows\system32目录下,引用时只需写dll名字2.load方法:写dll文件的完整路径
			System.loadLibrary("TSCLIB");// TSCLIB是TES打印机的dll文件

			// 参数说明InterfaceFun dll名,AddZhiYe函数名
			JNative openport = new JNative("TSCLIB", "openport");
			// openport.setRetVal(Type.STRING);
			openport.setParameter(0, "TSC TDP-245");
			// 函数执行
			openport.invoke();

			int i = 0;
			/*
			 * setup(a,b,c,d,e,f,g) 说明: 设定卷标的宽度、高度、打印速度、打印浓度、感应器类别、gap/black mark
			 * 垂直间距、gap/black mark 偏移距离) 参数: a: 字符串型别，设定卷标宽度，单位 mm b: 字符串型别，设定卷标高度，单位 mm c:
			 * 字符串型别，设定打印速度，(打印速度随机型不同而有不同的选项) 1.0: 每秒1.0吋打印速度 1.5: 每秒1.5吋打印速度 2.0:
			 * 每秒2.0吋打印速度 3.0: 每秒3.0吋打印速度 4.0: 每秒4.0吋打印速度 5.0: 每秒5.0吋打印速度 6.0: 每秒6.0吋打印速度 d:
			 * 字符串型别，设定打印浓度， 0~15，数字愈大打印结果愈黑 e: 字符串型别，设定使用感应器类别 0 表示使用垂直间距传感器(gap sensor) 1
			 * 表示使用黑标传感器(black mark sensor) f: 字符串型别，设定gap/black mark 垂直间距高度，单位: mm g:
			 * 字符串型别，设定gap/black mark 偏移距离，单位: mm，此参数若使用一般卷标时均设为0
			 */
			JNative setup = new JNative("TSCLIB", "setup");
			setup.setParameter(i++, "40");
			setup.setParameter(i++, "32.5");
			setup.setParameter(i++, "3");
			setup.setParameter(i++, "5");
			setup.setParameter(i++, "5");
			setup.setParameter(i++, "0");
			setup.setParameter(i++, "5");
			setup.setParameter(i++, "0");
			setup.invoke();

			/*
			 * 使用条形码机内建条形码打印 5. barcode(a,b,c,d,e,f,g,h,I) 说明: 使用条形码机内建条形码打印 参数: a:
			 * 字符串型别，条形码X方向起始点，以点(point)表示。 (200 DPI，1点=1/8 mm, 300 DPI，1点=1/12 mm) b:
			 * 字符串型别，条形码Y方向起始点，以点(point)表示。 (200 DPI，1点=1/8 mm, 300 DPI，1点=1/12 mm) c:
			 * 字符串型别， 128 Code 128, switching code subset A, B, C automatically 128M Code
			 * 128, switching code subset A, B, C manually. EAN128 Code 128, switching code
			 * subset A, B, C automatically 25 Interleaved 2 of 5 25C Interleaved 2 of 5
			 * with check digits 39 Code 39 39C Code 39 with check digits 93 Code 93 EAN13
			 * EAN 13 EAN13+2 EAN 13 with 2 digits add-on EAN13+5 EAN 13 with 5 digits
			 * add-on EAN8 EAN 8 EAN8+2 EAN 8 with 2 digits add-on EAN8+5 EAN 8 with 5
			 * digits add-on CODA Codabar POST Postnet UPCA UPC-A UPCA+2 UPC-A with 2 digits
			 * add-on UPCA+5 UPC-A with 5 digits add-on UPCE UPC-E UPCE+2 UPC-E with 2
			 * digits add-on UPCE+5 UPC-E with 5 digits add-on
			 * 
			 * d: 字符串型别，设定条形码高度，高度以点来表示 e: 字符串型别，设定是否打印条形码码文 0: 不打印码文 1: 打印码文 f:
			 * 字符串型别，设定条形码旋转角度 0: 旋转0度 90: 旋转90度 180: 旋转180度 270: 旋转270度 g: 字符串型别，设定条形码窄bar
			 * 比例因子，请参考TSPL使用手册 h: 字符串型别，设定条形码窄bar 比例因子，请参考TSPL使用手册 I: 字符串型别，条形码内容
			 */
			i = 0;
			JNative barcode = new JNative("TSCLIB", "barcode");
			barcode.setParameter(i++, "40");
			barcode.setParameter(i++, "40");
			barcode.setParameter(i++, "128");
			barcode.setParameter(i++, "120");
			barcode.setParameter(i++, "0");
			barcode.setParameter(i++, "0");
			barcode.setParameter(i++, "2");
			barcode.setParameter(i++, "4");
			barcode.setParameter(i++, barcode_str);
			barcode.invoke();

			i = 0;
			JNative printerfont = new JNative("TSCLIB", "printerfont");
			printerfont.setParameter(i++, "40");
			printerfont.setParameter(i++, "170");
			printerfont.setParameter(i++, "3");
			printerfont.setParameter(i++, "0");
			printerfont.setParameter(i++, "1");
			printerfont.setParameter(i++, "1");
			printerfont.setParameter(i++, commodityId);
			printerfont.invoke();

			/*
			 * 7. sendcommand(command) 说明: 送内建指令到条形码打印机 参数: 详细指令请参考TSPL
			 */
			JNative sendcommand = new JNative("TSCLIB", "sendcommand");
			sendcommand.setParameter(0, "BAR 400,200,300,100");
			sendcommand.invoke();
			/*
			 * 8. printlabel(a,b) 说明: 打印卷标内容 参数: a: 字符串型别，设定打印卷标式数(set) b:
			 * 字符串型别，设定打印卷标份数(copy)
			 */
			JNative printlabel = new JNative("TSCLIB", "printlabel");
			printlabel.setParameter(0, "1");
			printlabel.setParameter(1, kosu);
			printlabel.invoke();
			return "true";
		} finally {
			// 清除
			JNative clearbuffer = new JNative("TSCLIB", "clearbuffer");
			clearbuffer.invoke();
			// 关闭
			JNative closeport = new JNative("TSCLIB", "closeport");
			closeport.invoke();
		}
	}

	public static void addLog(String log, String logKey) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		String seq = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT MAX(SEQ) SEQ FROM LOG_TBL";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				seq = rs.getString("SEQ");
			}
			if (isEmptyString(seq)) {
				seq = "1000000000";
			} else {
				seq = String.valueOf(Long.valueOf(seq) + 1);
			}

			sql = "INSERT INTO LOG_TBL VALUES (?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, seq);
			ps.setString(2, log);
			ps.setString(3, logKey);
			ps.setString(4, "0");
			ps.execute();

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

	public static String getLog(String logKey) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		String log = null;
		ResultSet rs = null;
		String seq = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM LOG_TBL WHERE READED = ? AND LOG_KEY = ? ORDER BY SEQ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "0");
			ps.setString(2, logKey);
			rs = ps.executeQuery();
			while (rs.next()) {
				log = rs.getString("LOG_INFO");
				seq = rs.getString("SEQ");
				break;
			}

			sql = "UPDATE LOG_TBL SET READED = ? WHERE SEQ = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "1");
			ps.setString(2, seq);
			ps.execute();

			// commit
			conn.commit();
			return log;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public static boolean compressPic(String srcFilePath, String descFilePath) {
		File file = null;
		BufferedImage src = null;
		FileOutputStream out = null;
		ImageWriter imgWrier;
		ImageWriteParam imgWriteParams;

		// 指定写图片的方式为 jpg
		imgWrier = ImageIO.getImageWritersByFormatName("jpg").next();
		imgWriteParams = new javax.imageio.plugins.jpeg.JPEGImageWriteParam(null);
		// 要使用压缩，必须指定压缩方式为MODE_EXPLICIT
		imgWriteParams.setCompressionMode(imgWriteParams.MODE_EXPLICIT);
		// 这里指定压缩的程度，参数qality是取值0~1范围内，
		imgWriteParams.setCompressionQuality((float) 0.1);
		imgWriteParams.setProgressiveMode(imgWriteParams.MODE_DISABLED);
		ColorModel colorModel = ColorModel.getRGBdefault();
		// 指定压缩时使用的色彩模式
		imgWriteParams.setDestinationType(
				new javax.imageio.ImageTypeSpecifier(colorModel, colorModel.createCompatibleSampleModel(16, 16)));

		try {
			if (StringUtils.isBlank(srcFilePath)) {
				return false;
			} else {
				file = new File(srcFilePath);
				src = ImageIO.read(file);
				out = new FileOutputStream(descFilePath);

				imgWrier.reset();
				// 必须先指定 out值，才能调用write方法, ImageOutputStream可以通过任何
				// OutputStream构造
				imgWrier.setOutput(ImageIO.createImageOutputStream(out));
				// 调用write方法，就可以向输入流写图片
				imgWrier.write(null, new IIOImage(src, null, null), imgWriteParams);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private static final char[] HANKAKU_KATAKANA = { '｡', '｢', '｣', '､', '･', 'ｦ', 'ｧ', 'ｨ', 'ｩ', 'ｪ', 'ｫ', 'ｬ', 'ｭ',
			'ｮ', 'ｯ', 'ｰ', 'ｱ', 'ｲ', 'ｳ', 'ｴ', 'ｵ', 'ｶ', 'ｷ', 'ｸ', 'ｹ', 'ｺ', 'ｻ', 'ｼ', 'ｽ', 'ｾ', 'ｿ', 'ﾀ', 'ﾁ', 'ﾂ',
			'ﾃ', 'ﾄ', 'ﾅ', 'ﾆ', 'ﾇ', 'ﾈ', 'ﾉ', 'ﾊ', 'ﾋ', 'ﾌ', 'ﾍ', 'ﾎ', 'ﾏ', 'ﾐ', 'ﾑ', 'ﾒ', 'ﾓ', 'ﾔ', 'ﾕ', 'ﾖ', 'ﾗ',
			'ﾘ', 'ﾙ', 'ﾚ', 'ﾛ', 'ﾜ', 'ﾝ', 'ﾞ', 'ﾟ' };

	private static final char[] ZENKAKU_KATAKANA = { '。', '「', '」', '、', '・', 'ヲ', 'ァ', 'ィ', 'ゥ', 'ェ', 'ォ', 'ャ', 'ュ',
			'ョ', 'ッ', 'ー', 'ア', 'イ', 'ウ', 'エ', 'オ', 'カ', 'キ', 'ク', 'ケ', 'コ', 'サ', 'シ', 'ス', 'セ', 'ソ', 'タ', 'チ', 'ツ',
			'テ', 'ト', 'ナ', 'ニ', 'ヌ', 'ネ', 'ノ', 'ハ', 'ヒ', 'フ', 'ヘ', 'ホ', 'マ', 'ミ', 'ム', 'メ', 'モ', 'ヤ', 'ユ', 'ヨ', 'ラ',
			'リ', 'ル', 'レ', 'ロ', 'ワ', 'ン', '゛', '゜' };

	private static final char HANKAKU_KATAKANA_FIRST_CHAR = HANKAKU_KATAKANA[0];

	private static final char HANKAKU_KATAKANA_LAST_CHAR = HANKAKU_KATAKANA[HANKAKU_KATAKANA.length - 1];

	/**
	 * 半角カタカナから全角カタカナへ変換します。
	 * 
	 * @param c 変換前の文字
	 * @return 変換後の文字
	 */
	public static char hankakuKatakanaToZenkakuKatakana(char c) {
		if (c >= HANKAKU_KATAKANA_FIRST_CHAR && c <= HANKAKU_KATAKANA_LAST_CHAR) {
			return ZENKAKU_KATAKANA[c - HANKAKU_KATAKANA_FIRST_CHAR];
		} else {
			return c;
		}
	}

	/**
	 * 2文字目が濁点・半濁点で、1文字目に加えることができる場合は、合成した文字を返します。 合成ができないときは、c1を返します。
	 * 
	 * @param c1 変換前の1文字目
	 * @param c2 変換前の2文字目
	 * @return 変換後の文字
	 */
	public static char mergeChar(char c1, char c2) {
		if (c2 == 'ﾞ') {
			if ("ｶｷｸｹｺｻｼｽｾｿﾀﾁﾂﾃﾄﾊﾋﾌﾍﾎ".indexOf(c1) > 0) {
				switch (c1) {
				case 'ｶ':
					return 'ガ';
				case 'ｷ':
					return 'ギ';
				case 'ｸ':
					return 'グ';
				case 'ｹ':
					return 'ゲ';
				case 'ｺ':
					return 'ゴ';
				case 'ｻ':
					return 'ザ';
				case 'ｼ':
					return 'ジ';
				case 'ｽ':
					return 'ズ';
				case 'ｾ':
					return 'ゼ';
				case 'ｿ':
					return 'ゾ';
				case 'ﾀ':
					return 'ダ';
				case 'ﾁ':
					return 'ヂ';
				case 'ﾂ':
					return 'ヅ';
				case 'ﾃ':
					return 'デ';
				case 'ﾄ':
					return 'ド';
				case 'ﾊ':
					return 'バ';
				case 'ﾋ':
					return 'ビ';
				case 'ﾌ':
					return 'ブ';
				case 'ﾍ':
					return 'ベ';
				case 'ﾎ':
					return 'ボ';
				}
			}
		} else if (c2 == 'ﾟ') {
			if ("ﾊﾋﾌﾍﾎ".indexOf(c1) > 0) {
				switch (c1) {
				case 'ﾊ':
					return 'パ';
				case 'ﾋ':
					return 'ピ';
				case 'ﾌ':
					return 'プ';
				case 'ﾍ':
					return 'ペ';
				case 'ﾎ':
					return 'ポ';
				}
			}
		}
		return c1;
	}

	/**
	 * 
	 * @param QJstr 変換前の文字目
	 * @return 変換後の文字
	 */
	private static String B2Q(String QJstr) {// 半角-->全角
		String outStr = "";
		String Tstr = "";
		byte[] b = null;

		for (int i = 0; i < QJstr.length(); i++) {
			try {
				Tstr = QJstr.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if (b[3] == 0) {
				b[2] = (byte) (b[2] - 32);
				b[3] = -1;
				try {
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			} else
				outStr = outStr + Tstr;
		}

		return outStr;
	}

	/**
	 * 文字列中の半角カタカナを全角カタカナに変換します。
	 * 
	 * @param s 変換前文字列
	 * @return 変換後文字列
	 */
	public static String hankakuKatakanaToZenkakuKatakana(String s) {
		if (s.length() == 0) {
			return s;
		} else {
			s = B2Q(s);
			if (s.length() == 1) {
				return hankakuKatakanaToZenkakuKatakana(s.charAt(0)) + "";
			} else {

				StringBuffer sb = new StringBuffer(s);
				int i = 0;
				for (i = 0; i < sb.length() - 1; i++) {
					char originalChar1 = sb.charAt(i);
					char originalChar2 = sb.charAt(i + 1);
					char margedChar = mergeChar(originalChar1, originalChar2);
					if (margedChar != originalChar1) {
						sb.setCharAt(i, margedChar);
						sb.deleteCharAt(i + 1);
					} else {
						char convertedChar = hankakuKatakanaToZenkakuKatakana(originalChar1);
						if (convertedChar != originalChar1) {
							sb.setCharAt(i, convertedChar);
						}
					}
				}
				if (i < sb.length()) {
					char originalChar1 = sb.charAt(i);
					char convertedChar = hankakuKatakanaToZenkakuKatakana(originalChar1);
					if (convertedChar != originalChar1) {
						sb.setCharAt(i, convertedChar);
					}
				}
				return sb.toString();
			}
		}
	}

	public static String strTrim(String str) {
		return str == null ? "" : str.trim();
	}

	public static String numberFormat(String str) {
		String newstr = "";
		if (!isEmptyString(str)) {
			DecimalFormat myformat = new DecimalFormat();
			myformat.applyPattern("##,###");
			newstr = myformat.format(Long.valueOf(str));
			newstr = "¥" + newstr;
		}
		return newstr;
	}

	public static String numberFormatNoen(String str) {
		String newstr = "";
		if (!isEmptyString(str)) {
			DecimalFormat myformat = new DecimalFormat();
			myformat.applyPattern("##,###");
			newstr = myformat.format(Long.valueOf(str));
		}
		return newstr;
	}

	public static String formatData(String data) throws ParseException {
		if (!isEmptyString(data)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			return sdf.format(sdf2.parse(data));
		} else
			return "";
	}

	public static String formatData4(String data) throws ParseException {
		if (!isEmptyString(data)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.format(sdf2.parse(data));
		} else
			return "";
	}

	public static String formatData3(String data) throws ParseException {
		if (!isEmptyString(data)) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM.dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			return sdf.format(sdf2.parse(data));
		} else
			return "";
	}

	public static String formatData2(String data) throws ParseException {
		if (!isEmptyString(data)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
			return sdf.format(sdf2.parse(data));
		} else
			return "";
	}

	public static List<ShohinBean> getShohinFromCsv(File itemCsv, File itemCatCsv, File selectCsv) throws Exception {
		List<String[]> itemList = readCsvFile(itemCsv, true);
		List<String[]> itemCatList = null;
		if (itemCatCsv != null) {
			itemCatList = readCsvFile(itemCatCsv, true);
		}
//		List<String[]> selectList = readCsvFile(selectCsv, true);
		List<String[]> selectList = null;
		if (selectCsv != null) {
			selectList = readCsvFile(selectCsv, true);
		}

		// 结果
		List<ShohinBean> shohinList = new ArrayList<ShohinBean>();
		ShohinBean shohinBean = null;

		// 每件商品的情报
		ShohinInfoBean shohinInfoBean = null;
		List<ShohinkategoriBean> shohinkategoriBeanList = null;
		List<ShohinsentakushiBean> shohinsentakushiBeanList = null;

		for (int i = 0; i < itemList.size(); i++) {
			// 从itemCsv获取商品基本信息
			String[] itemInfo = itemList.get(i);
			int j = 0;
			shohinInfoBean = new ShohinInfoBean();
			// 商品管理番号（商品URL）
			shohinInfoBean.setShouhinkanribango(itemInfo[++j]);
			// 商品番号
			shohinInfoBean.setShouhinbango(itemInfo[++j]);
			// 全商品ディレクトリID
			shohinInfoBean.setZenshohindirekutoriId(itemInfo[++j]);
			// タグID
			shohinInfoBean.setTaguId(itemInfo[++j]);
			// PC用キャッチコピー
			shohinInfoBean.setPcyokyachikopi(itemInfo[++j]);
			// モバイル用キャッチコピー
			shohinInfoBean.setMobairuyokyachikopi(itemInfo[++j]);
			// 商品名
			shohinInfoBean.setShouhinmei(itemInfo[++j]);
			// 販売価格
			shohinInfoBean.setHanbaikakaku(itemInfo[++j]);
			// 表示価格
			shohinInfoBean.setHyojikakaku(itemInfo[++j]);
			// 消費税
			shohinInfoBean.setShouhizei(itemInfo[++j]);
			// 送料
			shohinInfoBean.setSouryou(itemInfo[++j]);
			// 個別送料
			shohinInfoBean.setKobetusouryou(itemInfo[++j]);
			// 送料区分1
			shohinInfoBean.setSouryoukubun1(itemInfo[++j]);
			// 送料区分2
			shohinInfoBean.setSouryoukubun2(itemInfo[++j]);
			// 代引料
			shohinInfoBean.setDaibikiryou(itemInfo[++j]);
			// 倉庫指定
			shohinInfoBean.setSokoshitei(itemInfo[++j]);
			// 商品情報レイアウト
			shohinInfoBean.setShouhinjouhoureiaouto(itemInfo[++j]);
			// 注文ボタン
			shohinInfoBean.setChumonbotan(itemInfo[++j]);
			// 資料請求ボタン
			shohinInfoBean.setShiryosekyubotan(itemInfo[++j]);
			// 商品問い合わせボタン
			shohinInfoBean.setShouhintoiawasebotan(itemInfo[++j]);
			// 再入荷お知らせボタン
			shohinInfoBean.setSainyukaoshirasebotan(itemInfo[++j]);
			// モバイル表示
			// shohinInfoBean.setMobairuhyoji(itemInfo[++j]);
			// のし対応
			shohinInfoBean.setNoshitaiou(itemInfo[++j]);
			// PC用商品説明文
			shohinInfoBean.setPcyoushouhinsetumeibun(itemInfo[++j]);
			// モバイル用商品説明文
			shohinInfoBean.setMobairuyoushouhinsetumeibun(itemInfo[++j]);
			// スマートフォン用商品説明文
			shohinInfoBean.setSumatofonyoushouhinsetumeibun(itemInfo[++j]);
			// PC用販売説明文
			shohinInfoBean.setPcyouhanbaisetumeibun(itemInfo[++j]);
			// 商品画像URL
			shohinInfoBean.setShouhingazoUrl(itemInfo[++j]);
			// 商品画像名（ALT）
			shohinInfoBean.setShouhingazomeiAlt(itemInfo[++j]);
			// 動画
			shohinInfoBean.setDouga(itemInfo[++j]);
			// 販売期間指定
			shohinInfoBean.setHanbaikikanshitei(itemInfo[++j]);
			// 注文受付数
			shohinInfoBean.setChumonnuketukesu(itemInfo[++j]);
			// 在庫タイプ
			shohinInfoBean.setZaikotaipu(itemInfo[++j]);
			// 在庫数
			shohinInfoBean.setZaikusu(itemInfo[++j]);
			// 在庫数表示
			shohinInfoBean.setZaikosuhyouji(itemInfo[++j]);
			// 項目選択肢別在庫用横軸項目名
			shohinInfoBean.setKomokusentakushibetuzaikoyouyokojikukoumokumei(itemInfo[++j]);
			// 項目選択肢別在庫用縦軸項目名
			shohinInfoBean.setKomokusentakushibetuzaikoyoutatejikukomokumei(itemInfo[++j]);
			// 項目選択肢別在庫用残り表示閾値
			shohinInfoBean.setKoumokusentakushibetuzaikoyounokorihyoujiikichi(itemInfo[++j]);
			// RAC番号
			shohinInfoBean.setRacbango(itemInfo[++j]);
			// サーチ非表示
			shohinInfoBean.setSachihihyoji(itemInfo[++j]);
			// 闇市パスワード
			shohinInfoBean.setYamiichipasuwado(itemInfo[++j]);
			// カタログID
			shohinInfoBean.setKataroguId(itemInfo[++j]);
			// 在庫戻しフラグ
			shohinInfoBean.setZaikonodoshifuragu(itemInfo[++j]);
			// 在庫切れ時の注文受付
			shohinInfoBean.setZaikokiretokinochumonnuketuke(itemInfo[++j]);
			// 在庫あり時納期管理番号
			shohinInfoBean.setZaikoaritokinoukikanribango(itemInfo[++j]);
			// 在庫切れ時納期管理番号
			shohinInfoBean.setZaikokiretokinoukikanribango(itemInfo[++j]);
			// 予約商品発売日
			shohinInfoBean.setYoyakushouhinhanbaibi(itemInfo[++j]);
			// ポイント変倍率
			shohinInfoBean.setPointohenbairitu(itemInfo[++j]);
			// ポイント変倍率適用期間
			shohinInfoBean.setPointohenbaitekiyoukikan(itemInfo[++j]);
			// ヘッダー・フッター・レフトナビ
			shohinInfoBean.setHeddafuttarefutonabi(itemInfo[++j]);
			// 表示項目の並び順
			shohinInfoBean.setHyoujikomokunonarabijun(itemInfo[++j]);
			// 共通説明文（小）
			shohinInfoBean.setKyotusetumeibunsho(itemInfo[++j]);
			// 目玉商品
			shohinInfoBean.setMedamashouhin(itemInfo[++j]);
			// 共通説明文（大）
			shohinInfoBean.setKyoutusetumeibundai(itemInfo[++j]);
			// レビュー本文表示
			shohinInfoBean.setRebyuhonbunhyouji(itemInfo[++j]);
			// あす楽配送管理番号
			shohinInfoBean.setArurakuhaisoukanribango(itemInfo[++j]);
			// 海外配送管理番号
			shohinInfoBean.setKaigaihaisoukanribango(itemInfo[++j]);
			// サイズ表リンク
			shohinInfoBean.setSaizuhyourinku(itemInfo[++j]);

			// 从selectCsv获取商品基本信息
			shohinsentakushiBeanList = new ArrayList<ShohinsentakushiBean>();
			ShohinsentakushiBean shohinsentakushiBean = null;
			
			if (selectCsv != null) {
				for (int k = 0; k < selectList.size(); k++) {
					j = 0;
					String shouhinkanribango = shohinInfoBean.getShouhinkanribango();
					if (shouhinkanribango.equals(selectList.get(k)[1])) {
						shohinsentakushiBean = new ShohinsentakushiBean();
						shohinsentakushiBeanList.add(shohinsentakushiBean);
						// 商品管理番号（商品URL）
						shohinsentakushiBean.setShohinkanribango(selectList.get(k)[++j]);
						// 選択肢タイプ
						shohinsentakushiBean.setSentakutaipu(selectList.get(k)[++j]);
						// Select/Checkbox用項目名
						shohinsentakushiBean.setSelectcheckboxyoukomokumei(selectList.get(k)[++j]);
						// Select/Checkbox用選択肢
						shohinsentakushiBean.setSelectcheckboxyousentakushi(selectList.get(k)[++j]);
						// 項目選択肢別在庫用横軸選択肢
						shohinsentakushiBean.setKomokusentakushibetuzaikoyouyokojikusentakushi(selectList.get(k)[++j]);
						// 項目選択肢別在庫用横軸選択肢子番号
						String strXAxisNo = selectList.get(k)[++j];
						if (strXAxisNo.length() > 0 && (strXAxisNo.replace("－", "-").indexOf("-") == -1)) {
							strXAxisNo = "-" + strXAxisNo;
						}
						
						// 項目選択肢別在庫用縦軸選択肢
						shohinsentakushiBean.setKomokusentakushizaikoyoutatejikusentakushi(selectList.get(k)[++j]);
						// 項目選択肢別在庫用縦軸選択肢子番号
						String strYAxisNo = selectList.get(k)[++j];
						if (strYAxisNo.length() > 0 && (strYAxisNo.replace("－", "-").indexOf("-") == -1)) {
							strYAxisNo = "-" + strYAxisNo;
						}
						
						if (strXAxisNo.length() == 0 && strYAxisNo.length() == 0) {
							strXAxisNo = "-0";
							strYAxisNo = "-0";
						}
						
						shohinsentakushiBean.setKomokusentakushibetuzaikoyoyokojikusentakushishibango(strXAxisNo);
						shohinsentakushiBean.setKomokusentakushibetuzaikoyotatejikusentakushishibango(strYAxisNo);
						
						// 項目選択肢別在庫用取り寄せ可能表示
						shohinsentakushiBean.setKomokusentakushibetuzaikototoriyosekanohyouji(selectList.get(k)[++j]);
						// 項目選択肢別在庫用在庫数
						shohinsentakushiBean.setKomokusentakushibetuzaikoyozaikosu(selectList.get(k)[++j]);
						// 在庫戻しフラグ
						shohinsentakushiBean.setZaikonodoshifuragu(selectList.get(k)[++j]);
						// 在庫切れ時の注文受付
						shohinsentakushiBean.setZaikokiretokinochumonuketuke(selectList.get(k)[++j]);
						// 在庫あり時納期管理番号
						shohinsentakushiBean.setZaikoaritokinoukikanribango(selectList.get(k)[++j]);
						// 在庫切れ時納期管理番号
						shohinsentakushiBean.setZaikokiretokinoukikanribango(selectList.get(k)[++j]);
					}
				}
			}else {
				String shouhinkanribango = shohinInfoBean.getShouhinkanribango();

				shohinsentakushiBean = new ShohinsentakushiBean();
				shohinsentakushiBeanList.add(shohinsentakushiBean);
				// 商品管理番号（商品URL）
				shohinsentakushiBean.setShohinkanribango(shouhinkanribango);
				// 選択肢タイプ
				shohinsentakushiBean.setSentakutaipu("i");
				// Select/Checkbox用項目名
				shohinsentakushiBean.setSelectcheckboxyoukomokumei("");
				// Select/Checkbox用選択肢
				shohinsentakushiBean.setSelectcheckboxyousentakushi("");
				// 項目選択肢別在庫用横軸選択肢
				shohinsentakushiBean.setKomokusentakushibetuzaikoyouyokojikusentakushi("仮横軸名称");
				// 項目選択肢別在庫用横軸選択肢子番号
				shohinsentakushiBean.setKomokusentakushibetuzaikoyoyokojikusentakushishibango("-0");
				// 項目選択肢別在庫用縦軸選択肢
				shohinsentakushiBean.setKomokusentakushizaikoyoutatejikusentakushi("仮縦軸名称");
				// 項目選択肢別在庫用縦軸選択肢子番号
				shohinsentakushiBean.setKomokusentakushibetuzaikoyotatejikusentakushishibango("-0");
				// 項目選択肢別在庫用取り寄せ可能表示
				shohinsentakushiBean.setKomokusentakushibetuzaikototoriyosekanohyouji("");
				// 項目選択肢別在庫用在庫数
				shohinsentakushiBean.setKomokusentakushibetuzaikoyozaikosu("0");
				// 在庫戻しフラグ
				shohinsentakushiBean.setZaikonodoshifuragu("0");
				// 在庫切れ時の注文受付
				shohinsentakushiBean.setZaikokiretokinochumonuketuke("1");
				// 在庫あり時納期管理番号
				shohinsentakushiBean.setZaikoaritokinoukikanribango("1");
				// 在庫切れ時納期管理番号
				shohinsentakushiBean.setZaikokiretokinoukikanribango("1");
			}

			if (itemCatCsv != null) {
				// 从selectCsv获取商品基本信息
				shohinkategoriBeanList = new ArrayList<ShohinkategoriBean>();
				ShohinkategoriBean shohinkategoriBean = null;
				for (int k = 0; k < itemCatList.size(); k++) {
					j = 0;
					String shouhinkanribango = shohinInfoBean.getShouhinkanribango();
					if (shouhinkanribango.equals(itemCatList.get(k)[1])) {
						shohinkategoriBean = new ShohinkategoriBean();
						shohinkategoriBeanList.add(shohinkategoriBean);

						// 商品管理番号（商品URL）
						shohinkategoriBean.setShohinkanribango(itemCatList.get(k)[++j]);
						// 商品名
						shohinkategoriBean.setShohinmei(itemCatList.get(k)[++j]);
						// 表示先カテゴリ
						shohinkategoriBean.setHuojisakikategori(itemCatList.get(k)[++j]);
						// 優先度
						shohinkategoriBean.setYusendo(itemCatList.get(k)[++j]);
						// URL
						shohinkategoriBean.setUrl(itemCatList.get(k)[++j]);
						// 1ページ複数形式
						shohinkategoriBean.setIchipejifukusukeishiki(itemCatList.get(k)[++j]);

					}

				}
			}
			shohinBean = new ShohinBean();
			shohinBean.setShohinInfoBean(shohinInfoBean);
			shohinBean.setShohinkategoriBeanList(shohinkategoriBeanList);
			shohinBean.setShohinsentakushiBeanList(shohinsentakushiBeanList);
			shohinList.add(shohinBean);

		}
		return shohinList;

	}

	public static String getDateTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		return date;
	}

	public static String getUser() {
		return "kyo";
	}

	public static String getHaisohohoName(String code) {
		String name = "";
		if ("0".equals(code)) {
			name = "その他";
		} else if ("1".equals(code)) {
			name = "宅急便";
		} else if ("2".equals(code)) {
			name = "メール便";
		}
		return name;
	}

	public static String getHaisokaishaName(String code) {
		String name = "";
		if ("1000".equals(code)) {
			name = "その他";
		} else if ("1001".equals(code)) {
			name = "ヤマト運輸";
		} else if ("1002".equals(code)) {
			name = "佐川急便";
		}
		return name;
	}

	public static String getShohinmei(String shohinbango) throws Exception {
		String shohinmei = "";

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT JAPANESE_NAME FROM TBL00011 WHERE COMMODITY_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, getCommodityId(shohinbango));
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinmei = rs.getString("JAPANESE_NAME");
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return shohinmei;
	}

	public static boolean isShohinExist(String shohinbango) throws Exception {
		int count = 0;

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT COUNT(*) COUNT FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, getCommodityId(shohinbango));
			ps.setString(2, getDetailN0(shohinbango));
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("COUNT");
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return count > 0 ? true : false;
	}

	public static boolean isOrderExist(String orderNo) throws Exception {
		int count = 0;

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT COUNT(*) COUNT FROM common_order_tbl WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, getCommodityId(orderNo));
			rs = ps.executeQuery();
			while (rs.next()) {
				count = rs.getInt("COUNT");
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return count > 0 ? true : false;
	}

	public static String getShoribango() throws Exception {
		String shoribango_new = "1100000001";

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT MAX(SHORIBANGO) SHORIBANGO FROM SHORIBANGO_TBL";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (!isEmptyString(rs.getString("SHORIBANGO"))) {
					shoribango_new = String.valueOf(Long.valueOf(rs.getString("SHORIBANGO")) + 1);
				}
			}

			if (!"1100000001".equals(shoribango_new)) {
				sql = "UPDATE SHORIBANGO_TBL SET SHORIBANGO = ? WHERE SHORIBANGO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shoribango_new);
				ps.setString(2, String.valueOf(Long.valueOf(shoribango_new) - 1));
				int count = ps.executeUpdate();
				if (count == 0) {
					throw new Exception();
				}

			} else {
				sql = "INSERT INTO SHORIBANGO_TBL VALUES(?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shoribango_new);
				int count = ps.executeUpdate();
				if (count == 0) {
					throw new Exception();
				}
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return shoribango_new;

	}

	public static Long getZei(String value) {
		BigDecimal tanka = new BigDecimal(value);
		BigDecimal rate = new BigDecimal("0.08");
		BigDecimal kekka = tanka.multiply(rate).setScale(BigDecimal.ROUND_HALF_UP);
		return kekka.longValue();
	}

	public static String[] getSiteAndTenpo(String orderNo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String[] info = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM common_order_tbl WHERE CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			rs = ps.executeQuery();
			String site = "";
			String tenpo = "";
			while (rs.next()) {
				site = rs.getString("SITE");
				tenpo = rs.getString("SHOP");
			}
			info = new String[] { site, tenpo };

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return info;
	}

	public static String getSoryo(String shubetsu, String site, String tenpo) {
		String soryo = "0";
		if ("宅配便".equals(shubetsu) || "宅急便".equals(shubetsu)) {
			if ("楽天".equals(site)) {
				soryo = "540";
			} else if ("DENA".equals(site)) {
				soryo = "540";
			} else if ("Yahoo Shopping".equals(site)) {
				soryo = "540";
			}
		}
		if ("メール便".equals(shubetsu)) {
			soryo = "378";
		}
		return soryo;
	}

	public static String getDaibikiryo(String value) {
		Long kakaku = Long.valueOf(value);
		if (kakaku < 10001l) {
			kakaku += 324l;
		} else if (kakaku < 30000l) {
			kakaku += 432l;
		} else {
			kakaku += 669l;
		}
		Long daibikiryo = 0l;
		if (kakaku < 10001l) {
			daibikiryo = 324l;
		} else if (kakaku < 30000l) {
			daibikiryo = 432l;
		} else {
			daibikiryo = 669l;
		}
		return String.valueOf(daibikiryo);
	}

	public static String getDaibikiryoDena(String value) {
		Long kakaku = Long.valueOf(value);
		if (kakaku < 10001l) {
			kakaku += 324l;
		} else if (kakaku < 30000l) {
			kakaku += 432l;
		} else {
			kakaku += 660l;
		}
		Long daibikiryo = 0l;
		if (kakaku < 10001l) {
			daibikiryo = 324l;
		} else if (kakaku < 30000l) {
			daibikiryo = 432l;
		} else {
			daibikiryo = 660l;
		}
		return String.valueOf(daibikiryo);
	}

	public static Long getSorokomiKaku() {
		return 5480l;
	}

	public static String getShohinUrl(String shohinbango, String site, String tenpo) {
		String url = "";
		if ("楽天".equals(site)) {
			url = "http://item.rakuten.co.jp/" + tenpo + "/" + getCommodityId(shohinbango) + "/";
		}
		return url;
	}

	public static String getShop(String shouhinbango) {
		// TODO Auto-generated method stub
		return "3eshop";
	}

	public static String getShopNameById(String id) {
		String name = "";
		if ("306685".equals(id)) {
			name = "trend777";
		} else if ("308759".equals(id)) {
			name = "coverforefront";
		}else if ("385894".equals(id)) {
			name = "xandw";
		} else if ("373860".equals(id)) {
			name = "herz";
		}
		return name;
	}

	public static String getPicUrl(String commodityId) throws Exception {
		String picurl = "";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT PIC_URL FROM TBL00011 WHERE COMMODITY_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, commodityId);
			rs = ps.executeQuery();
			while (rs.next()) {
				picurl = rs.getString("PIC_URL");
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return picurl;
	}

	public static String getShohinbango(String shohinmei) throws Exception {
		String shohinbango = "";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT shohinbango FROM TBL00023 WHERE REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(REPLACE(shoinmeicn,'-',''),' ',''),'+',''),'#',''),'（',''),'）',''),'/',''),'.','') = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shohinmei);
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinbango = rs.getString("shohinbango");
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return shohinbango;
	}

	public static String getShohinbango2(String shohinmei) throws Exception {
		String shohinbango = "";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT shohinbango FROM TBL00023 WHERE shiireurl like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + shohinmei + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinbango = rs.getString("shohinbango");
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return shohinbango;
	}

	public static void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			System.out.println("压缩：" + basedir + file.getName());
			compressDirectory(file, out, basedir);
		} else {
			System.out.println("压缩：" + basedir + file.getName());
			compressFile(file, out, basedir);
		}
	}

	/** 压缩一个目录 */
	private static void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())
			return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	/** 压缩一个文件 */
	private static void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
			ZipEntry entry = new ZipEntry(basedir + file.getName());
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[8192];
			while ((count = bis.read(data, 0, 8192)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);

		}
	}

	/**
	 * 功能:压缩多个文件成一个zip文件
	 * <p>
	 * 作者 陈亚标 Jul 16, 2010 10:59:40 AM
	 * 
	 * @param srcfile ：源文件列表
	 * @param zipfile ：压缩后的文件
	 */
	public static void zipFiles(File[] srcfile, File zipfile) {
		byte[] buf = new byte[1024];
		try {
			// ZipOutputStream类：完成文件或文件夹的压缩
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipfile));
			for (int i = 0; i < srcfile.length; i++) {
				FileInputStream in = new FileInputStream(srcfile[i]);
				out.putNextEntry(new ZipEntry(srcfile[i].getName()));
				int len;
				while ((len = in.read(buf)) > 0) {
					out.write(buf, 0, len);
				}
				out.closeEntry();
				in.close();
			}
			out.close();
			System.out.println("压缩完成.");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 功能:解压缩
	 * <p>
	 * 作者 陈亚标 Jul 16, 2010 12:42:20 PM
	 * 
	 * @param zipfile ：需要解压缩的文件
	 * @param descDir ：解压后的目标目录
	 */
	public void unZipFiles(File zipfile, String descDir) {
		try {
			ZipFile zf = new ZipFile(zipfile);
			for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String zipEntryName = entry.getName();
				InputStream in = zf.getInputStream(entry);
				OutputStream out = new FileOutputStream(descDir + zipEntryName);
				byte[] buf1 = new byte[1024];
				int len;
				while ((len = in.read(buf1)) > 0) {
					out.write(buf1, 0, len);
				}
				in.close();
				out.close();
				System.out.println("解压缩完成.");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static KingakuBean kingakukeisan(boolean soryokomiAri, boolean daibikiryokomiAri, String gokeishouhin,
			String gokeizei, String sonotatesuryo, String sonotawaribikigaku, String haisohoho, String oshiharaihoho,
			String pointoriyo, String site, String tenpo) {
		KingakuBean kingakuBean = new KingakuBean();
		String gokeisouryou = "0";
		String gokeidaibikiryou = "0";
		String seikyukingaku = "0";
		if (!soryokomiAri && (Long.valueOf(gokeishouhin) + Long.valueOf(gokeizei)) < Utility.getSorokomiKaku()) {
			gokeisouryou = Utility.getSoryo(haisohoho, site, tenpo);
		}
		if (!daibikiryokomiAri && "代金引換".equals(oshiharaihoho)) {
			gokeidaibikiryou = Utility.getDaibikiryo(String.valueOf(Long.valueOf(gokeishouhin) + Long.valueOf(gokeizei)
					+ Long.valueOf(gokeisouryou) - Long.valueOf(pointoriyo)));
		}

		seikyukingaku = String.valueOf(Long.valueOf(gokeishouhin) + Long.valueOf(gokeizei) + Long.valueOf(gokeisouryou)
				+ Long.valueOf(gokeidaibikiryou) - Long.valueOf(pointoriyo) + Long.valueOf(sonotatesuryo)
				- Long.valueOf(sonotawaribikigaku));

		kingakuBean.setGokeishouhin(gokeishouhin);
		kingakuBean.setGokeizei(gokeizei);
		kingakuBean.setGokeisouryou(gokeisouryou);
		kingakuBean.setGokeidaibikiryou(gokeidaibikiryou);
		kingakuBean.setSeikyukingaku(seikyukingaku);

		return kingakuBean;
	}

	public static int getLength(String str) {
		int length = 0;

		for (char c : str.toCharArray()) {
			String tempC = String.valueOf(c);
			if (tempC.length() == tempC.getBytes().length) {
				length++;
			} else {
				length = length + 2;
			}
		}
		return length;

	}

	public static String getShopUrl(String shop, String site) {
		String shopUrl = "";
		if ("楽天".equals(site)) {
			if ("3eshop".equals(shop)) {
				shopUrl = "http://www.rakuten.co.jp/3eshop";
			} else if ("citycat".equals(shop)) {
				shopUrl = "http://www.rakuten.co.jp/citycat";
			} else if ("trend777".equals(shop)) {
				shopUrl = "http://www.rakuten.co.jp/trend777";
			} else if ("coverforefront".equals(shop)) {
				shopUrl = "http://www.rakuten.co.jp/coverforefront";
			}
		} else if ("DENA".equals(site)) {
			if ("123mart".equals(shop)) {
				shopUrl = "http://www.dena-ec.com/user/31948291";
			}
		} else if ("Yahoo Shopping".equals(site)) {
			if ("WhiteSweet".equals(shop)) {
				shopUrl = "http://store.shopping.yahoo.co.jp/coverforefront/";
			}
		} else if ("qoo10".equals(site)) {
			if ("xandw".equals(shop)) {
				shopUrl = "https://www.qoo10.jp/shop/xandw";
			}
		}

		return shopUrl;

	}

	public static String getShopPost(String shop, String site) {
		String shopPost = "349-0114";

		return shopPost;

	}

	public static String getShopAddr(String shop, String site) {
		String shopPost = "埼玉県蓮田市馬込2-132エルディムセブン 1-203";

		return shopPost;

	}

	public static String getShopTel(String shop, String site) {
		String shopPost = "";
		if ("3eshop".equals(shop)) {
			shopPost = "050-37043022";
		} else if ("citycat".equals(shop)) {
			shopPost = "050-37043022";
		} else if ("trend777".equals(shop)) {
			shopPost = "050-36962972";
		} else if ("coverforefront".equals(shop)) {
			shopPost = "050-35675168";
		} else if ("xandw".equals(shop)) {
			shopPost = "03-6821-1372";
		} else if ("herz".equals(shop)) {
			shopPost = "050-33902972";
		} else if ("epintl".equals(shop)) {
			shopPost = "050-35587157";
		} else {
			shopPost = "050-35675168";
		}

		return shopPost;

	}

	public static String getShopFax(String shop, String site) {
		String shopPost = "";
		if ("3eshop".equals(shop)) {
			shopPost = "050-37043022 ";
		} else if ("citycat".equals(shop)) {
			shopPost = "050-37043022";
		} else if ("trend777".equals(shop)) {
			shopPost = "050-36962972";
		} else if ("coverforefront".equals(shop)) {
			shopPost = "050-35675168";
		} else if ("xandw".equals(shop)) {
			shopPost = "03-6821-1372";
		} else if ("herz".equals(shop)) {
			shopPost = "050-33902972";
		} else if ("epintl".equals(shop)) {
			shopPost = "050-35587157";
		} else {
			shopPost = "050-35675168";
		}

		return shopPost;

	}

	public static String[] getShopLoginInfo(String shop) {
		if ("3eshop".equals(shop)) {
			return new String[] { "threeeshop", "threeeshop", "3theYYshop", "shopYYth3e", "123mart" };
		} else if ("citycat".equals(shop)) {
			return new String[] { "citycat198426", "citycat198426", "jinyan624891", "624891jinyan", "jinyan624891" };
		} else {
			return null;
		}
	}

	public static List<Type> getBankList() throws Exception {
		List<Type> typeList = new ArrayList<Type>();
		Type type = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM bankinfo";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				type = new Type();
				typeList.add(type);
				type.setBankCode(rs.getString("bankcode"));
				type.setBankName(rs.getString("bankname"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return typeList;
	}

	public static String getBankName(String code) throws Exception {
		String name = "";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT bankname FROM bankinfo where bankcode = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("bankname");
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return name;
	}

	public static String getFenleiName(String code) throws Exception {
		String name = "";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT fenlei_name FROM fenleiinfo where kanribango = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			while (rs.next()) {
				name = rs.getString("fenlei_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return name;
	}

	public static String getAreaName(String code) {
		if ("0".equals(code)) {
			return "日本";
		} else if ("1".equals(code)) {
			return "中国";
		} else {
			return "";
		}
	}

	public static String getTblName(String code) {
		String tblName = "";
		if ("001".equals(code)) {
			tblName = "bank_cn_tbl";
		} else if ("002".equals(code)) {
			tblName = "alipay_tbl";
		} else if ("003".equals(code)) {
			tblName = "cash_sh_tbl";
		} else if ("004".equals(code)) {
			tblName = "bank_jp_tbl";
		} else if ("005".equals(code)) {
			tblName = "bank_jp_tbl";
		} else if ("006".equals(code)) {
			tblName = "bank_jp_tbl";
		} else if ("007".equals(code)) {
			tblName = "bank_jp_tbl";
		} else if ("008".equals(code)) {
			tblName = "cash_jp_tbl";
		}
		return tblName;

	}

	public static String getTypeNameCN(String kubun) {
		if ("0".equals(kubun)) {
			return "进账";
		} else {
			return "出账";
		}
	}

	public static String getTypeNameJP(String kubun) {
		if ("0".equals(kubun)) {
			return "入金";
		} else {
			return "出金";
		}
	}

	public static boolean isValidShop(String shop) {
		if ("123mart".equals(shop) || "trend777".equals(shop) || "coverforefront".equals(shop) || "herz".equals(shop) || "epintl".equals(shop)) {
			return true;
		} else {
			return false;
		}
	}

	public static String getFenleiNameConn(String code, Connection conn) throws Exception {
		String name = "";

		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		sql = "SELECT fenlei_name FROM fenleiinfo where kanribango = ?";
		ps = conn.prepareStatement(sql);
		ps.setString(1, code);
		rs = ps.executeQuery();
		while (rs.next()) {
			name = rs.getString("fenlei_name");
		}

		return name;
	}

	public static String getApiKey(String shop) {
		String key = "";
		if ("trend777".equals(shop)) {
			return "03747f837876270a24737cf3d3dc9f2c";
		} else if ("coverforefront".equals(shop)) {
			return "975dde27fa85695b2eb04bc9144f1719";
		}
		return key;

	}

	public static int getNokiId(String shop, int type) {
		int id = 0;
		if ("trend777".equals(shop)) {
			if (1 == type) {
				id = 3;
			} else if (3 == type) {
				id = 2;
			} else if (7 == type) {
				id = 1;
			}
		} else if ("coverforefront".equals(shop)) {
			if (1 == type) {
				id = 3;
			} else if (3 == type) {
				id = 2;
			} else if (7 == type) {
				id = 1;
			}
		}else if ("xandw".equals(shop)) {
			if (1 == type) {
				id = 3;
			} else if (3 == type) {
				id = 2;
			} else if (7 == type) {
				id = 1;
			}
		}
		return id;
	}

	public static Map<String, Integer> getSoryoMap() throws Exception {
		Map<String, Integer> soryoMap = new HashMap<String, Integer>();

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * FROM soryo";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				soryoMap.put(rs.getString("id"), rs.getInt("kakaku"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		return soryoMap;

	}

	public static String getChikuId(String address) {
		if (address.startsWith("北海道")) {
			return "01";
		} else if (address.startsWith("青森")) {
			return "02";
		} else if (address.startsWith("岩手")) {
			return "03";
		} else if (address.startsWith("宮城")) {
			return "04";
		} else if (address.startsWith("秋田")) {
			return "05";
		} else if (address.startsWith("山形")) {
			return "06";
		} else if (address.startsWith("福島")) {
			return "07";
		} else if (address.startsWith("茨城")) {
			return "08";
		} else if (address.startsWith("栃木")) {
			return "09";
		} else if (address.startsWith("群馬")) {
			return "10";
		} else if (address.startsWith("埼玉")) {
			return "11";
		} else if (address.startsWith("千葉")) {
			return "12";
		} else if (address.startsWith("東京都")) {
			return "13";
		} else if (address.startsWith("神奈川")) {
			return "14";
		} else if (address.startsWith("新潟")) {
			return "15";
		} else if (address.startsWith("富山")) {
			return "16";
		} else if (address.startsWith("石川")) {
			return "17";
		} else if (address.startsWith("福井")) {
			return "18";
		} else if (address.startsWith("山梨")) {
			return "19";
		} else if (address.startsWith("長野")) {
			return "20";
		} else if (address.startsWith("岐阜")) {
			return "21";
		} else if (address.startsWith("静岡")) {
			return "22";
		} else if (address.startsWith("愛知")) {
			return "23";
		} else if (address.startsWith("三重")) {
			return "24";
		} else if (address.startsWith("滋賀")) {
			return "25";
		} else if (address.startsWith("京都府")) {
			return "26";
		} else if (address.startsWith("大阪府")) {
			return "27";
		} else if (address.startsWith("兵庫")) {
			return "28";
		} else if (address.startsWith("奈良")) {
			return "29";
		} else if (address.startsWith("和歌山")) {
			return "30";
		} else if (address.startsWith("鳥取")) {
			return "31";
		} else if (address.startsWith("島根")) {
			return "32";
		} else if (address.startsWith("岡山")) {
			return "33";
		} else if (address.startsWith("広島")) {
			return "34";
		} else if (address.startsWith("山口")) {
			return "35";
		} else if (address.startsWith("徳島")) {
			return "36";
		} else if (address.startsWith("香川")) {
			return "37";
		} else if (address.startsWith("愛媛")) {
			return "38";
		} else if (address.startsWith("高知")) {
			return "39";
		} else if (address.startsWith("福岡")) {
			return "40";
		} else if (address.startsWith("佐賀")) {
			return "41";
		} else if (address.startsWith("長崎")) {
			return "42";
		} else if (address.startsWith("熊本")) {
			return "43";
		} else if (address.startsWith("大分")) {
			return "44";
		} else if (address.startsWith("宮崎")) {
			return "45";
		} else if (address.startsWith("鹿児島")) {
			return "46";
		} else if (address.startsWith("沖縄")) {
			return "47";
		} else {
			return "48";
		}

	}

//	public static String getBestSoryo(String address, String size, Map<String, Integer> soryoMap) throws Exception {
//
//		String chikuId = getChikuId(address);
//
//		BigDecimal b = new BigDecimal(String.valueOf(size));
//		b = b.divide(BigDecimal.ONE, 0, BigDecimal.ROUND_CEILING);
//
//		int sagawaSoryo = soryoMap.get("s" + b.toString() + chikuId);
//		int yamatoSoryo = soryoMap.get("y" + b.toString() + chikuId);
//		if (sagawaSoryo + 10 >= yamatoSoryo) {
//			return "1001";
//		} else {
//			return "1002";
//		}
//
//	}

	public static String getBestSoryo(String address, String size, Map<String, Integer> soryoMap) throws Exception {

		String chikuId = getChikuId(address);

		BigDecimal b = new BigDecimal(String.valueOf(size));
		b = b.divide(BigDecimal.ONE, 0, BigDecimal.ROUND_CEILING);

		int sagawaSoryo = soryoMap.get("s" + 1 + chikuId);
		int yamatoSoryo = Integer.valueOf(b.toString()) * 240;
		if (sagawaSoryo + 10 >= yamatoSoryo) {
			return "1001";
		} else {
			return "1002";
		}

	}

	public static String strStringValue(Object str) {
		if (str == null) {
			return "";
		} else {
			return String.valueOf(str);
		}
	}

	public static int getNoukiDay(String nouki) {
		int noukiDay = 2;
		if (isEmptyString(nouki)) {
			return noukiDay;
		}
		switch (nouki) {
		case "7～10営業日以内に発送":
			noukiDay = 10;
			break;
		case "7〜10営業日以内に発送":
			noukiDay = 10;
			break;
		case "8～11営業日以内に発送":
			noukiDay = 11;
			break;
		case "9～12営業日以内に発送":
			noukiDay = 12;
			break;
		case "10～13営業日以内に発送":
			noukiDay = 13;
			break;
		case "10〜13営業日以内に発送":
			noukiDay = 20;
			break;
		case "18～23営業日以内に発送":
			noukiDay = 23;
			break;
		case "18〜23営業日以内に発送":
			noukiDay = 23;
			break;
		case "6～9営業日以内に発送":
			noukiDay = 9;
			break;
		case "6～8営業日以内に発送":
			noukiDay = 8;
			break;
		case "6〜8営業日以内に発送":
			noukiDay = 8;
			break;
		case "3～4営業日以内に発送":
			noukiDay = 4;
			break;
		case "4～5営業日以内に発送":
			noukiDay = 5;
			break;
		case "5～7営業日以内に発送":
			noukiDay = 7;
			break;
		case "3月1日前後発送する予定":
			noukiDay = 30;
			break;
		case "即納":
			noukiDay = 2;
			break;
		case "11～12営業日以内に発送":
			noukiDay = 12;
			break;
		case "10～11営業日以内に発送":
			noukiDay = 11;
			break;
		case "9～10営業日以内に発送":
			noukiDay = 10;
			break;
		case "8～9営業日以内に発送":
			noukiDay = 9;
			break;
		case "7～8営業日以内に発送":
			noukiDay = 8;
			break;
		case "6～7営業日以内に発送":
			noukiDay = 7;
			break;
		case "5〜6営業日以内に発送":
			noukiDay = 6;
			break;
		case "3月上旬発送する予定":
			noukiDay = 20;
			break;
		default:
			noukiDay = 2;
		}
		return noukiDay;
	}

	/**
	 * 计算两个日期之间相差的天数
	 * 
	 * @param smdate 较小的时间
	 * @param bdate  较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date smdate, Date bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String smdate, String bdate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(smdate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(bdate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	public static String getSearchUrl(String kaisha) {
		if ("1001".equals(kaisha)) {
			return "http://link.kuronekoyamato.co.jp/link/send/receive/lneko";
		} else if ("1002".equals(kaisha)) {
			return "http://k2k.sagawa-exp.co.jp/p/sagawa/web/okurijoinput.jsp";
		} else {
			return "";
		}
	}

	public static int getJinhuoshangNoki(String jinhuoshang) {
		int nokiid = 8;
		if ("刘雨露".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("子菲瑜".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("俏甜".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("芊黛".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("卡迪派".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("汉麻缘".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("朱飞燕".equals(jinhuoshang)) {
			nokiid = 4;
		}
		if ("萝卜大叔".equals(jinhuoshang)) {
			nokiid = 4;
		}
		if ("曦之洞".equals(jinhuoshang)) {
			nokiid = 4;
		}
		if ("珍衣奇饰".equals(jinhuoshang)) {
			nokiid = 2;
		}
		if ("刘肖俊".equals(jinhuoshang)) {
			nokiid = 2;
		}
		if ("其他（6-8）".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("百素".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("美淘".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("自由婷".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("其他（7-10）".equals(jinhuoshang)) {
			nokiid = 8;
		}
		if ("其他（4-5）".equals(jinhuoshang)) {
			nokiid = 4;
		}
		if ("玛雅".equals(jinhuoshang)) {
			nokiid = 1;
		}

		if ("钒飐".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("鹤本".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("翠盈".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("张富军".equals(jinhuoshang)) {
			nokiid = 4;
		}
		if ("刘雨露(fx)".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("奈久留美".equals(jinhuoshang)) {
			nokiid = 2;
		}
		if ("织里涵泽".equals(jinhuoshang)) {
			nokiid = 2;
		}
		if ("zhicongxi".equals(jinhuoshang)) {
			nokiid = 2;
		}
		if ("兴城美诗奇泳装源头厂家".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("高飞服装".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("夏葵泳装".equals(jinhuoshang)) {
			nokiid = 1;
		}
		if ("媚动力".equals(jinhuoshang)) {
			nokiid = 4;
		}
		if ("PATTKIN".equals(jinhuoshang)) {
			nokiid = 4;
		}
		if ("彩戴妃".equals(jinhuoshang)) {
			nokiid = 4;
		}
		if ("手表".equals(jinhuoshang)) {
			nokiid = 9;
		}

		return nokiid;

	}
}
