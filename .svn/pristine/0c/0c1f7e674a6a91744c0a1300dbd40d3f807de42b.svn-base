package com.rakuten.r1001.action;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.poi.hssf.usermodel.DVConstraint;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataValidation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.r0602.bean.DetailListBean;
import com.rakuten.r0602.bean.OrderBean;
import com.rakuten.r1001.form.F100101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

@SuppressWarnings("deprecation")
public class A10010105Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100101 f100101 = null;
	OrderCommon orderCommon = new OrderCommon();
	private String type = null;
	private String fileName = null;
	private String fileNameHassou = null;
	private String fileNameYamato = null;
	private String fileNameSagawa = null;
	private String dirName = null;

	protected void exec() throws Exception {

		OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean
				.getCommonOrderBeanList();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean
				.getShouhinStsBeanList();

		// 発送待ち-発送可
		List<String> hasomachihasokaList = orderCommon.getHasomachiHasokaList(
				commonOrderBeanList, shouhinStsBeanList);

		List<OrderBean> orderList = getOrderList(hasomachihasokaList);

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		dirName = df.format(new Date());
		File dir = new File("D://temp/" + dirName);
		dir.mkdirs();

		outCsv(orderList);

		download(orderList);

		fileName = "out_" + dirName + ".zip";
		FileOutputStream fileOutputStream = new FileOutputStream(new File(
				"c:/temp/" + fileName));
		CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,
				new CRC32());
		ZipOutputStream out = new ZipOutputStream(cos);
		String basedir = "";
		compress(new File("c:/temp/" + dirName), out, basedir);
		out.close();
	}

	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("c:/temp/" + fileName));
	}

	private List<OrderBean> getOrderList(List<String> hasomachihasokaList)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			List<OrderBean> orderList = new ArrayList<OrderBean>();
			DetailListBean detail = null;
			List<DetailListBean> detailList = null;
			OrderBean Order = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM common_order_tbl T1 LEFT JOIN common_order_detail_tbl T2 ON T1.CHUMONBANGO = T2.JUCHUBANGO WHERE T1.CHUMONBANGO = ? ";
			for (String juchubango : hasomachihasokaList) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, juchubango);
				ResultSet rs = ps.executeQuery();
				String juchubangocomp = "";
				while (rs.next()) {
					String juchubangotemp = rs.getString("T1.CHUMONBANGO");
					if (!juchubangotemp.equals(juchubangocomp)) {
						juchubangocomp = juchubangotemp;
						detailList = new ArrayList<DetailListBean>();
						Order = new OrderBean();
						Order.setDetailList(detailList);
						orderList.add(Order);

						// 受注番号
						Order.setJuchubango(rs.getString("T1.CHUMONBANGO"));
						// お届け時間帯
						Order.setOtodokejikandai(rs
								.getString("T1.OTODOKEJIKANTAI1")
								+ rs.getString("T1.OTODOKEJIKANTAI2")
								+ rs.getString("T1.OTODOKEJIKANTAI3"));
						// お届け日指定
						Order.setOtodokebishitei(rs
								.getString("OTODOKEBISHITEI"));
						// ひとことメモ
						// Order.setHitokotomemo(rs.getString(""));
						// 注文日時
						Order.setChumonnichiji(rs.getString("T1.CHUMONNICHIJI"));
						// 合計金額(-99999=無効値)
						Order.setGokeikingaku(rs.getString("T1.SEIKYUKINGAKU"));
						// 決済方法
						Order.setKesaihoho(rs.getString("T1.OSHIHARAISTS"));
						// 配送方法
						Order.setHaisouhoho(rs.getString("T1.HAISOUHOHO"));
						// 送付先郵便番号１
						Order.setSofusakiyubinbango1(rs
								.getString("T1.SOUFUSAKIYUBINBANGO1"));
						// 送付先郵便番号２
						Order.setSofusakiyubinbango2(rs
								.getString("T1.SOUFUSAKIYUBINBANGO2"));
						// 送付先住所：都道府県
						Order.setSofusakijusho1(rs
								.getString("T1.SOUFUSAKIJUSHOTODOFUKEN"));
						// 送付先住所：都市区
						Order.setSofusakijusho2(rs
								.getString("T1.SOUFUSAKIJUSHOTOSHIKU"));
						// 送付先住所：町以降
						Order.setSofusakijusho3(rs
								.getString("T1.SOUFUSAKIJUSHOCHOIJOU"));
						// 送付先名字
						Order.setSofusakimeiji(rs.getString("T1.SOFUSAKIMEIJI"));
						// 送付先名前
						Order.setSofusakinamae(rs
								.getString("T1.SOUFUSAKINAMAE"));
						// 送付先名字フリガナ
						Order.setSofusakimeijifurigana(rs
								.getString("T1.SOUFUSAKIMEIJIFURIGANA"));
						// 送付先名前フリガナ
						Order.setSofusakinamaefurigana(rs
								.getString("T1.SOUFUSAKIMEIJINAMAEFURIGANA"));
						// 送付先電話番号１
						Order.setSofusakidenwabango1(rs
								.getString("T1.SOFUSAKIDENWABANGO1"));
						// 送付先電話番号２
						Order.setSofusakidenwabango2(rs
								.getString("T1.SOFUSAKIDENWABANGO2"));
						// 送付先電話番号３
						Order.setSofusakidenwabango3(rs
								.getString("T1.SOFUSAKIDENWABANGO3"));
						// コメント
						Order.setKomento(rs.getString("T1.KOMENTO"));

						// 店舗別
						Order.setTenpobetsu(rs.getString("T1.SHOP"));

						Order.setSite(rs.getString("T1.SITE"));
					}
					// 商品詳細リスト
					detail = new DetailListBean();
					detailList.add(detail);

					// 商品名
					detail.setShouhinmei(rs.getString("T2.SHOUHINMEI"));
					// 商品番号
					detail.setShouhinbango(rs.getString("T2.SHOUHINBANGO"));
					// 単価
					detail.setTankaku(rs.getString("T2.TANKA"));
					// 個数
					detail.setKosu(rs.getString("T2.KOSU"));
					// 項目・選択肢
					detail.setKomokusentakushi(rs
							.getString("T2.KOMOKUSENTAKUSHI"));
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

	/**
	 * @return the orderCommon
	 */
	public OrderCommon getOrderCommon() {
		return orderCommon;
	}

	/**
	 * @param orderCommon
	 *            the orderCommon to set
	 */
	public void setOrderCommon(OrderCommon orderCommon) {
		this.orderCommon = orderCommon;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName
	 *            the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the fileNameHassou
	 */
	public String getFileNameHassou() {
		return fileNameHassou;
	}

	/**
	 * @param fileNameHassou
	 *            the fileNameHassou to set
	 */
	public void setFileNameHassou(String fileNameHassou) {
		this.fileNameHassou = fileNameHassou;
	}

	/**
	 * @return the fileNameYamato
	 */
	public String getFileNameYamato() {
		return fileNameYamato;
	}

	/**
	 * @param fileNameYamato
	 *            the fileNameYamato to set
	 */
	public void setFileNameYamato(String fileNameYamato) {
		this.fileNameYamato = fileNameYamato;
	}

	/**
	 * @return the fileNameSagawa
	 */
	public String getFileNameSagawa() {
		return fileNameSagawa;
	}

	/**
	 * @param fileNameSagawa
	 *            the fileNameSagawa to set
	 */
	public void setFileNameSagawa(String fileNameSagawa) {
		this.fileNameSagawa = fileNameSagawa;
	}

	/**
	 * @return the dirName
	 */
	public String getDirName() {
		return dirName;
	}

	/**
	 * @param dirName
	 *            the dirName to set
	 */
	public void setDirName(String dirName) {
		this.dirName = dirName;
	}

	private void compress(File file, ZipOutputStream out, String basedir) {
		/* 判断是目录还是文件 */
		if (file.isDirectory()) {
			System.out.println("压缩：" + basedir + file.getName());
			this.compressDirectory(file, out, basedir);
		} else {
			System.out.println("压缩：" + basedir + file.getName());
			this.compressFile(file, out, basedir);
		}
	}

	/** 压缩一个目录 */
	private void compressDirectory(File dir, ZipOutputStream out, String basedir) {
		if (!dir.exists())
			return;

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			/* 递归 */
			compress(files[i], out, basedir + dir.getName() + "/");
		}
	}

	/** 压缩一个文件 */
	private void compressFile(File file, ZipOutputStream out, String basedir) {
		if (!file.exists()) {
			return;
		}
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(file));
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

	public void download(List<OrderBean> hassouList) throws IOException,
			SQLException {

		HSSFWorkbook wb = new HSSFWorkbook();

		List<OrderBean> rakuten3eshopHasouList = new ArrayList<OrderBean>();
		List<OrderBean> rakutencitycatHasouList = new ArrayList<OrderBean>();
		List<OrderBean> yahooshoppingHasouList = new ArrayList<OrderBean>();
		List<OrderBean> denaHasouList = new ArrayList<OrderBean>();
		List<OrderBean> yafuokuHasouList = new ArrayList<OrderBean>();

		for (OrderBean order : hassouList) {
			String site = order.getSite();
			String shop = order.getTenpobetsu();
			if ("楽天".equals(site) && "3eshop".equals(shop)) {
				rakuten3eshopHasouList.add(order);
			} else if ("楽天".equals(site) && "citycat".equals(shop)) {
				if (!Utility.isEmptyString(order.getDetailList().get(0)
						.getShouhinbango())) {
					rakutencitycatHasouList.add(order);
				}
			} else if ("Yahoo Shopping".equals(site)) {
				yahooshoppingHasouList.add(order);
			} else if ("ヤフオク".equals(site)) {
				yafuokuHasouList.add(order);
			} else if ("DENA".equals(site)) {
				denaHasouList.add(order);
			}
		}

		HSSFSheet sheet1 = wb.createSheet("楽天3eshop");
		HSSFSheet sheet2 = wb.createSheet("楽天citycat");
		HSSFSheet sheet3 = wb.createSheet("Yahoo ショッピング");
		HSSFSheet sheet4 = wb.createSheet("DENA");
		HSSFSheet sheet5 = wb.createSheet("ヤフオク");

		writeXls(wb, sheet1, rakuten3eshopHasouList);
		writeXls(wb, sheet2, rakutencitycatHasouList);
		writeXls(wb, sheet3, yahooshoppingHasouList);
		writeXls(wb, sheet4, denaHasouList);
		writeXls(wb, sheet5, yafuokuHasouList);

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		fileNameHassou = "HASSOU_" + df.format(new Date()) + ".xls";
		FileOutputStream fileOut = new FileOutputStream("c:/temp/" + dirName
				+ "/" + fileNameHassou);
		// 指定文件名
		wb.write(fileOut);
		// 输出到文件
		fileOut.close();
	}

	public void writeXls(HSSFWorkbook wb, HSSFSheet sheet1,
			List<OrderBean> hassouList) {
		sheet1.setDisplayGridlines(false);

		sheet1.setColumnWidth(0, 4000);
		sheet1.setColumnWidth(1, 7000);
		sheet1.setColumnWidth(2, 7000);
		sheet1.setColumnWidth(3, 7000);
		sheet1.setColumnWidth(4, 7000);
		sheet1.setColumnWidth(5, 7000);
		sheet1.setColumnWidth(7, 7000);

		HSSFCellStyle style = wb.createCellStyle();
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
		style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// 前景填充色

		HSSFCellStyle style1 = wb.createCellStyle();
		style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);

		int k = 0;
		for (int i = 0; i < hassouList.size(); i++) {
			OrderBean orderBean = hassouList.get(i);

			// 创建一行
			HSSFRow row = sheet1.createRow(k);
			row.setHeight((short) 400);

			HSSFCell cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue("店舗別");

			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue("受注番号");

			cell = row.createCell(2);
			cell.setCellStyle(style);
			cell.setCellValue("決済方法");

			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("配送方法");

			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue("合計金額");

			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("ひとことメモ");

			sheet1.addMergedRegion(new CellRangeAddress(k, k, 5, 6));

			cell = row.createCell(6);
			cell.setCellStyle(style);

			cell = row.createCell(7);
			cell.setCellStyle(style);
			cell.setCellValue("コメント");

			row = sheet1.createRow(k + 1);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(orderBean.getTenpobetsu());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(orderBean.getJuchubango());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(orderBean.getKesaihoho());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(orderBean.getHaisouhoho());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(orderBean.getGokeikingaku());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(orderBean.getHitokotomemo());

			sheet1.addMergedRegion(new CellRangeAddress(k + 1, k + 1, 5, 6));

			cell = row.createCell(6);
			cell.setCellStyle(style1);

			cell = row.createCell(7);
			cell.setCellValue(orderBean.getKomento());
			if (orderBean.getKomento().replace("[配送日時指定:]", "").trim().length() > 2) {
				HSSFCellStyle cellstyle = wb.createCellStyle();
				cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
				cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
				cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				cellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
				cellstyle.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);// 前景填充色
				cell.setCellStyle(cellstyle);
			} else {
				cell.setCellStyle(style1);
			}

			row = sheet1.createRow(k + 2);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue("送付先郵便番号");

			cell = row.createCell(1);
			sheet1.addMergedRegion(new CellRangeAddress(k + 2, k + 2, 1, 2));
			cell.setCellStyle(style);
			cell.setCellValue("送付先住所");

			cell = row.createCell(2);
			cell.setCellStyle(style);

			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("送付先名前");

			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue("送付先名前フリガナ");

			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("送付先電話");

			row = sheet1.createRow(k + 3);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue("〒" + orderBean.getSofusakiyubinbango1() + "-"
					+ orderBean.getSofusakiyubinbango2());

			cell = row.createCell(1);
			sheet1.addMergedRegion(new CellRangeAddress(k + 3, k + 3, 1, 2));
			cell.setCellStyle(style1);
			cell.setCellValue(orderBean.getSofusakijusho1()
					+ orderBean.getSofusakijusho2()
					+ orderBean.getSofusakijusho3());

			cell = row.createCell(2);
			cell.setCellStyle(style1);

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(orderBean.getSofusakimeiji() + " "
					+ orderBean.getSofusakinamae());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(orderBean.getSofusakimeijifurigana() + " "
					+ orderBean.getSofusakinamaefurigana());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(orderBean.getSofusakidenwabango1() + "-"
					+ orderBean.getSofusakidenwabango2() + "-"
					+ orderBean.getSofusakidenwabango3());

			row = sheet1.createRow(k + 4);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue("商品番号");

			cell = row.createCell(1);
			sheet1.addMergedRegion(new CellRangeAddress(k + 4, k + 4, 1, 2));
			cell.setCellStyle(style);
			cell.setCellValue("商品名");

			cell = row.createCell(2);
			cell.setCellStyle(style);

			cell = row.createCell(3);
			cell.setCellStyle(style);
			cell.setCellValue("単価");

			cell = row.createCell(4);
			cell.setCellStyle(style);
			cell.setCellValue("個数");

			cell = row.createCell(5);
			cell.setCellStyle(style);
			cell.setCellValue("項目・選択肢");

			for (int j = 0; j < orderBean.getDetailList().size(); j++) {
				row = sheet1.createRow(k + 5 + j);
				row.setHeight((short) 400);

				DetailListBean detail = orderBean.getDetailList().get(j);

				cell = row.createCell(0);
				cell.setCellStyle(style1);
				cell.setCellValue(detail.getShouhinbango());

				cell = row.createCell(1);
				cell.setCellStyle(style1);
				cell.setCellValue(detail.getShouhinmei());

				sheet1.addMergedRegion(new CellRangeAddress(k + 5 + j, k + 5
						+ j, 1, 2));

				cell = row.createCell(2);
				cell.setCellStyle(style1);

				cell = row.createCell(3);
				cell.setCellStyle(style1);
				cell.setCellValue(detail.getTankaku());

				cell = row.createCell(4);
				cell.setCellStyle(style1);
				cell.setCellValue(Integer.valueOf(detail.getKosu()));

				if (Integer.valueOf(detail.getKosu()) > 1) {
					HSSFCellStyle cellstyle = wb.createCellStyle();
					cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
					cellstyle
							.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);// 前景填充色
					cell.setCellStyle(cellstyle);

				}

				cell = row.createCell(5);
				cell.setCellStyle(style1);
				cell.setCellValue(detail.getKomokusentakushi());

			}
			k = row.getRowNum() + 1;
			row = sheet1.createRow(k);
			cell = row.createCell(0);
			cell.setCellStyle(style);
			cell.setCellValue("配送会社");

			cell = row.createCell(1);
			cell.setCellStyle(style);
			cell.setCellValue("お荷物伝票番号");

			String[] haisoukaisha = { "ヤマト運輸", "佐川急便" };
			// 加载下拉列表内容
			DVConstraint constraint = DVConstraint
					.createExplicitListConstraint(haisoukaisha);
			// 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
			CellRangeAddressList regions = new CellRangeAddressList(k + 1,
					k + 1, 0, 0);
			// 数据有效性对象
			HSSFDataValidation data_validation_list = new HSSFDataValidation(
					regions, constraint);
			sheet1.addValidationData(data_validation_list);

			k = row.getRowNum() + 1;
			row = sheet1.createRow(k);
			cell = row.createCell(0);
			cell.setCellStyle(style1);

			cell = row.createCell(1);
			cell.setCellStyle(style1);

			HSSFCellStyle cellstyle = wb.createCellStyle();
			cellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
			cellstyle.setFillForegroundColor(HSSFColor.BLACK.index);// 前景填充色
			k = row.getRowNum() + 2;
			row = sheet1.createRow(k);
			for (int m = 0; m < 20; m++) {
				cell = row.createCell(m);
				cell.setCellStyle(cellstyle);
			}
			k = row.getRowNum() + 2;
		}
	}

	private void outCsv(List<OrderBean> orderList) throws IOException {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		DateFormat df2 = new SimpleDateFormat("yyyy/MM/dd");
		fileNameYamato = "YAMATO_" + df.format(new Date()) + ".csv";
		fileNameSagawa = "SAGAWA_" + df.format(new Date()) + ".csv";

		File file1 = new File("D://temp/" + dirName + "/" + fileNameYamato);
		file1.createNewFile();
		File file2 = new File("D://temp/" + dirName + "/" + fileNameSagawa);
		file2.createNewFile();
		BufferedWriter bufferedWriterYamato = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(file1), "shift-jis"));

		bufferedWriterYamato
				.write("お客様管理番号,送り状種別,クール区分,伝票番号,出荷予定日,お届け予定（指定）日,配達時間帯,お届け先コード,お届け先電話番号,お届け先電話番号枝番,お届け先郵便番号,お届け先住所,お届け先住所（アパートマンション名）,お届け先会社・部門名１,お届け先会社・部門名２,お届け先名,お届け先名略称カナ,敬称,ご依頼主コード,ご依頼主電話番号,ご依頼主電話番号枝番,ご依頼主郵便番号,ご依頼主住所,ご依頼主住所（アパートマンション名）,ご依頼主名,ご依頼主略称カナ,品名コード１,品名１,品名コード２,品名２,荷扱い１,荷扱い２,記事,コレクト代金引換額（税込）,コレクト内消費税額等,営業所止置き,営業所コード,発行枚数,個数口枠の印字,ご請求先顧客コード,ご請求先分類コード,運賃管理番号,注文時カード払いデータ登録,注文時カード払い加盟店番号,注文時カード払い申込受付番号１,注文時カード払い申込受付番号２,注文時カード払い申込受付番号３,お届け予定ｅメール利用区分,お届け予定ｅメールe-mailアドレス,入力機種,お届け予定eメールメッセージ,お届け完了ｅメール利用区分,お届け完了ｅメールe-mailアドレス,お届け完了eメールメッセージ,クロネコ収納代行利用区分,収納代行決済ＱＲコード印刷,収納代行請求金額(税込),収納代行内消費税額等,収納代行請求先郵便番号,収納代行請求先住所,収納代行請求先住所（アパートマンション名）,収納代行請求先会社・部門名１,収納代行請求先会社・部門名２,収納代行請求先名(漢字),収納代行請求先名(カナ),収納代行問合せ先名(漢字),収納代行問合せ先郵便番号,収納代行問合せ先住所,収納代行問合せ先住所（アパートマンション名）,収納代行問合せ先電話番号,収納代行管理番号,収納代行品名,収納代行備考");

		BufferedWriter bufferedWriterSagawa = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(file2), "shift-jis"));

		// bufferedWriterSagawa
		// .write("住所録コード,お届け先電話番号,お届け先郵便番号,お届け先住所１,お届け先住所２,お届け先住所３,お届け先名称１,お届け先名称２,お客様管理ナンバー,お客様コード,部署・担当者（荷送人）,荷送人電話番号,ご依頼主電話番号,ご依頼主郵便番号,ご依頼主住所１,ご依頼主住所２,ご依頼主名称１,ご依頼主名称２,荷姿コード,品名１,品名２,品名３,品名４,品名５,出荷個数,便種（スピードで選択）,便種（商品）,配達日,配達指定時間帯,配達指定時間（時分）,代引金額,消費税,保険金額,指定シール①,指定シール②,指定シール③,営業店止め,営業店コード,元着区分");

		for (int i = 0; i < orderList.size(); i++) {
			OrderBean orderBean = orderList.get(i);
			List<DetailListBean> detailList = orderBean.getDetailList();
			boolean hassoFlg = true;
			for (int n = 0; n < detailList.size(); n++) {
				if (detailList.get(n).getFusokusu() > 0) {
					hassoFlg = false;
					break;
				}
			}

			if (hassoFlg) {
				if ("宅配便".equals(orderBean.getHaisouhoho())) {
					// 出荷予定日
					// String yotebi = df2.format(new Date());
					// お届け先電話番号
					String denwabango = orderBean.getSofusakidenwabango1()
							+ "-" + orderBean.getSofusakidenwabango2() + "-"
							+ orderBean.getSofusakidenwabango3();
					// お届け先郵便番号
					String yunbinbango = orderBean.getSofusakiyubinbango1()
							+ "-" + orderBean.getSofusakiyubinbango2();
					// お届け先住所
					String jusho1 = "";
					// お届け先住所（アパートマンション名）
					String jusho2 = "";

					jusho1 = orderBean.getSofusakijusho1()
							+ orderBean.getSofusakijusho2();

					jusho2 = orderBean.getSofusakijusho3();

					// お届け先名
					String name = orderBean.getSofusakimeiji()
							+ orderBean.getSofusakinamae() + "　様";

					String peidari = "";
					String zhidingrishi = "";
					String daiyingjinge = "";
					if ("代金引換".equals(orderBean.getKesaihoho())) {
						daiyingjinge = orderBean.getGokeikingaku();
					}
					if (i != 0) {
						bufferedWriterSagawa.newLine();
					}
					bufferedWriterSagawa.write("," + denwabango + ","
							+ yunbinbango + "," + jusho1 + "," + jusho2 + ",,"
							+ name + ",,,,,," + "050-37043022" + ",,,,,,,,,,,,"
							+ "1," + "0," + "0," + peidari + "," + zhidingrishi
							+ ",," + daiyingjinge + ",,,,,,,,");
				} else {

					// 出荷予定日
					String yotebi = df2.format(new Date());
					// お届け先電話番号
					String denwabango = orderBean.getSofusakidenwabango1()
							+ "-" + orderBean.getSofusakidenwabango2() + "-"
							+ orderBean.getSofusakidenwabango3();
					// お届け先郵便番号
					String yunbinbango = orderBean.getSofusakiyubinbango1()
							+ "-" + orderBean.getSofusakiyubinbango2();
					// お届け先住所
					String jusho1 = "";
					// お届け先住所（アパートマンション名）
					String jusho2 = "";

					String sofusakijusho = orderBean.getSofusakijusho1()
							+ orderBean.getSofusakijusho2()
							+ orderBean.getSofusakijusho3();
					jusho1 = sofusakijusho;

					// お届け先名
					String name = orderBean.getSofusakimeiji()
							+ orderBean.getSofusakinamae() + "　様";
					bufferedWriterYamato.newLine();
					bufferedWriterYamato
							.write(",3,,,"
									+ yotebi
									+ ",,,,"
									+ denwabango
									+ ",,"
									+ yunbinbango
									+ ","
									+ jusho1
									+ ","
									+ jusho2
									+ ",,,"
									+ name
									+ ",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

				}
			}
		}
		bufferedWriterYamato.flush();
		bufferedWriterYamato.close();
		bufferedWriterSagawa.flush();
		bufferedWriterSagawa.close();

	}

	protected void init() {
		setTitle("V100101:注文一覧");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F100101 getF100101() {
		return f100101;
	}

	public void setF100101(F100101 f100101) {
		this.f100101 = f100101;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
