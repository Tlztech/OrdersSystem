package com.rakuten.r0602.action;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import com.csvreader.CsvReader;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r0602.bean.DetailListBean;
import com.rakuten.r0602.bean.OrderBean;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

@SuppressWarnings("deprecation")
public class A06020102Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	// file
	private File inputPath1 = null;
	private File inputPath3 = null;
	private List<String> errmsg = new ArrayList<String>();
	private String fileName = null;
	private String fileNameHassou = null;
	private String fileNameYamato = null;
	private String fileNameSagawa = null;
	private String dirName = null;

	protected void exec() throws Exception {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		dirName = df.format(new Date());
		File dir = new File("D://temp/" + dirName);
		dir.mkdirs();

		List<OrderBean> orderList = new ArrayList<OrderBean>();

		if (inputPath1 != null) {
			orderList.addAll((getOrders(inputPath1)));
		}
		if (inputPath3 != null) {
			orderList.addAll((getOrders(inputPath3)));
		}
		orderList = hassou(orderList);

		orderList = tongkuncheck(orderList);

		if (!Utility.isEmptyList(errmsg)) {
			for (int i = 0; i < errmsg.size(); i++) {
				addError(null, errmsg.get(i));
			}
		} else {

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

	private List<OrderBean> tongkuncheck(List<OrderBean> orderList) {
		String tongkunidstr = "";
		Map<String, List<Integer>> tempMap = new HashMap<String, List<Integer>>();
		for (int i = 0; i < orderList.size(); i++) {
			OrderBean order = orderList.get(i);
			String tongkunId = order.getTongkunID();
			if (!"0".equals(tongkunId)) {
				if (!tongkunidstr.contains(tongkunId)) {
					tongkunidstr = tongkunidstr + tongkunId + "&";
				}
				if (tempMap.get(tongkunId) == null) {
					List<Integer> ichilist = new ArrayList<Integer>();
					ichilist.add(i);
					tempMap.put(tongkunId, ichilist);
				} else {
					List<Integer> ichilist = tempMap.get(tongkunId);
					ichilist.add(i);
				}
			}
		}
		String[] strArr = tongkunidstr.split("&");
		for (int i = 0; i < strArr.length; i++) {
			String tonkunId = strArr[i];
			List<Integer> ichiList = tempMap.get(tonkunId);
			List<DetailListBean> detailList = new ArrayList<DetailListBean>();
			if (ichiList != null) {
				for (int j = 0; j < ichiList.size(); j++) {
					detailList.addAll(orderList.get(ichiList.get(j))
							.getDetailList());
				}
				for (int j = 0; j < ichiList.size(); j++) {
					if ("1".equals(orderList.get(ichiList.get(j))
							.getTongkunSts())) {
						orderList.get(ichiList.get(j))
								.setDetailList(detailList);
					}
				}
			}
		}
		List<OrderBean> tempList = new ArrayList<OrderBean>();
		for (int i = 0; i < orderList.size(); i++) {
			if ("1".equals(orderList.get(i).getTongkunSts())
					|| "0".equals(orderList.get(i).getTongkunSts())) {
				tempList.add(orderList.get(i));
			}
		}
		return tempList;
	}

	public void download(List<OrderBean> hassouList) throws IOException,
			SQLException {

		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet1 = wb.createSheet("発送可リスト");
		HSSFSheet sheet2 = wb.createSheet("発送待ちリスト");

		sheet1.setDisplayGridlines(false);
		sheet2.setDisplayGridlines(false);

		sheet1.setColumnWidth(0, 4000);
		sheet1.setColumnWidth(1, 7000);
		sheet1.setColumnWidth(2, 7000);
		sheet1.setColumnWidth(3, 7000);
		sheet1.setColumnWidth(4, 7000);
		sheet1.setColumnWidth(5, 7000);
		sheet1.setColumnWidth(7, 7000);

		sheet2.setColumnWidth(0, 4000);
		sheet2.setColumnWidth(1, 7000);
		sheet2.setColumnWidth(2, 7000);
		sheet2.setColumnWidth(3, 7000);
		sheet2.setColumnWidth(4, 7000);
		sheet2.setColumnWidth(5, 7000);
		sheet2.setColumnWidth(6, 4000);
		sheet2.setColumnWidth(7, 4000);
		sheet2.setColumnWidth(8, 4000);

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
		int l = 0;
		for (int i = 0; i < hassouList.size(); i++) {
			OrderBean orderBean = hassouList.get(i);
			List<DetailListBean> detailList = orderBean.getDetailList();
			boolean hassoFlg = true;
			for (int n = 0; n < detailList.size(); n++) {
				if (detailList.get(n).getFusokusu() > 0) {
					hassoFlg = false;
					break;
				}
			}

			if (hassoFlg) {
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
				if (orderBean.getKomento().replace("[配送日時指定:]", "").trim()
						.length() > 2) {
					HSSFCellStyle cellstyle = wb.createCellStyle();
					cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
					cellstyle
							.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);// 前景填充色
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
				cell.setCellValue("〒" + orderBean.getSofusakiyubinbango1()
						+ "-" + orderBean.getSofusakiyubinbango2());

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

					sheet1.addMergedRegion(new CellRangeAddress(k + 5 + j, k
							+ 5 + j, 1, 2));

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
						cellstyle
								.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
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

				String[] haisoukaisha = { "ヤマト運輸", "佐川急便", "郵便局" };
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
			} else {
				// 创建一行
				HSSFRow row = sheet2.createRow(l);
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

				sheet2.addMergedRegion(new CellRangeAddress(l, l, 5, 6));

				cell = row.createCell(6);
				cell.setCellStyle(style);

				cell = row.createCell(7);
				cell.setCellStyle(style);
				cell.setCellValue("コメント");

				row = sheet2.createRow(l + 1);
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

				sheet2.addMergedRegion(new CellRangeAddress(l + 1, l + 1, 5, 6));

				cell = row.createCell(6);
				cell.setCellStyle(style1);

				cell = row.createCell(7);
				cell.setCellValue(orderBean.getKomento());
				if (orderBean.getKomento().replace("[配送日時指定:]", "").trim()
						.length() > 2) {
					HSSFCellStyle cellstyle = wb.createCellStyle();
					cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
					cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
					cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
					cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
					cellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
					cellstyle
							.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);// 前景填充色
					cell.setCellStyle(cellstyle);
				} else {
					cell.setCellStyle(style1);
				}

				row = sheet2.createRow(l + 2);
				row.setHeight((short) 400);

				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("送付先郵便番号");

				cell = row.createCell(1);
				sheet2.addMergedRegion(new CellRangeAddress(l + 2, l + 2, 1, 2));
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

				row = sheet2.createRow(l + 3);
				row.setHeight((short) 400);

				cell = row.createCell(0);
				cell.setCellStyle(style1);
				cell.setCellValue("〒" + orderBean.getSofusakiyubinbango1()
						+ "-" + orderBean.getSofusakiyubinbango2());

				cell = row.createCell(1);
				sheet2.addMergedRegion(new CellRangeAddress(l + 3, l + 3, 1, 2));
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

				row = sheet2.createRow(l + 4);
				row.setHeight((short) 400);

				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("商品番号");

				cell = row.createCell(1);
				sheet2.addMergedRegion(new CellRangeAddress(l + 4, l + 4, 1, 2));
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

				cell = row.createCell(6);
				cell.setCellStyle(style);
				cell.setCellValue("不足数");

				cell = row.createCell(7);
				cell.setCellStyle(style);
				cell.setCellValue("保留数");

				for (int j = 0; j < orderBean.getDetailList().size(); j++) {
					row = sheet2.createRow(l + 5 + j);
					row.setHeight((short) 400);

					DetailListBean detail = orderBean.getDetailList().get(j);

					cell = row.createCell(0);
					cell.setCellStyle(style1);
					cell.setCellValue(detail.getShouhinbango());

					cell = row.createCell(1);
					cell.setCellStyle(style1);
					cell.setCellValue(detail.getShouhinmei());

					sheet2.addMergedRegion(new CellRangeAddress(l + 5 + j, l
							+ 5 + j, 1, 2));

					cell = row.createCell(2);
					cell.setCellStyle(style1);

					cell = row.createCell(3);
					cell.setCellStyle(style1);
					cell.setCellValue(detail.getTankaku());

					cell = row.createCell(4);
					cell.setCellStyle(style1);
					cell.setCellValue(Integer.valueOf(detail.getKosu()));

					cell = row.createCell(5);
					cell.setCellStyle(style1);
					cell.setCellValue(detail.getKomokusentakushi());

					cell = row.createCell(6);
					cell.setCellStyle(style1);
					cell.setCellValue(detail.getFusokusu());

					cell = row.createCell(7);
					cell.setCellStyle(style1);
					cell.setCellValue(Integer.valueOf(detail.getKosu())
							- Integer.valueOf(detail.getFusokusu()));

				}

				HSSFCellStyle cellstyle = wb.createCellStyle();
				cellstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
				cellstyle.setFillForegroundColor(HSSFColor.BLACK.index);// 前景填充色
				l = row.getRowNum() + 2;
				row = sheet2.createRow(l);
				for (int m = 0; m < 20; m++) {
					cell = row.createCell(m);
					cell.setCellStyle(cellstyle);
				}
				l = row.getRowNum() + 2;

			}

		}

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		fileNameHassou = "HASSOU_" + df.format(new Date()) + ".xls";
		FileOutputStream fileOut = new FileOutputStream("c:/temp/" + dirName
				+ "/" + fileNameHassou);
		// 指定文件名
		wb.write(fileOut);
		// 输出到文件
		fileOut.close();

	}

	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("c:/temp/" + fileName));
	}

	protected void init() {
		setTitle("V060201:发货单生成");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public File getInputPath1() {
		return inputPath1;
	}

	public void setInputPath1(File inputPath1) {
		this.inputPath1 = inputPath1;
	}

	private List<OrderBean> hassou(List<OrderBean> orderList)
			throws SQLException {
		Comparator<OrderBean> comparator = new Comparator<OrderBean>() {
			@Override
			public int compare(OrderBean o1, OrderBean o2) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Date o1Date = df.parse(o1.getChumonnichiji());
					Date o2Date = df.parse(o2.getChumonnichiji());
					return o1Date.compareTo(o2Date);

				} catch (ParseException e) {
					e.printStackTrace();
				}
				return -1;
			}
		};
		Collections.sort(orderList, comparator);
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			for (int i = 0; i < orderList.size(); i++) {
				OrderBean order = orderList.get(i);
				for (int j = 0; j < order.getDetailList().size(); j++) {
					DetailListBean detail = order.getDetailList().get(j);
					String shouhinbango = detail.getShouhinbango();

					sql = "SELECT COUNT(*) COUNT FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);
					if (shouhinbango.contains("-")) {
						ps.setString(
								1,
								shouhinbango.substring(0,
										shouhinbango.indexOf("-")));
						ps.setString(2, shouhinbango.substring(shouhinbango
								.indexOf("-")));
					} else {
						ps.setString(1, shouhinbango);
						ps.setString(2, "");
					}

					rs = ps.executeQuery();
					int count = 0;
					while (rs.next()) {
						count = rs.getInt("COUNT");
					}
					if (count > 0) {
						sql = "SELECT * FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
						ps = conn.prepareStatement(sql);
						if (shouhinbango.contains("-")) {
							ps.setString(
									1,
									shouhinbango.substring(0,
											shouhinbango.indexOf("-")));
							ps.setString(2, shouhinbango.substring(shouhinbango
									.indexOf("-")));
						} else {
							ps.setString(1, shouhinbango);
							ps.setString(2, "");
						}

						rs = ps.executeQuery();

						int stock = 0;
						int stockHandup =0;
						while (rs.next()) {
							stock = rs.getInt("STOCK_JP");
							stockHandup = rs.getInt("STOCK_HANDUP");
						}
						int kosu = Integer.valueOf(detail.getKosu());
						stockHandup = stockHandup - kosu >= 0 ? stockHandup - kosu : 0;
						if (stock - kosu >= 0) {
							detail.setFusokusu(0);
							sql = "UPDATE TBL00012 SET STOCK_JP = ?, STOCK_HANDUP = ?, UPDATE_TIME = ? , UPDATE_USER = ?, UPDATEQUANTITY_FLG =TRUE WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
							ps = conn.prepareStatement(sql);
							ps.setInt(1, stock - kosu);
							ps.setInt(2, stockHandup);
							ps.setString(3, Utility.getDateTime());
							ps.setString(4, Utility.getUser());
							ps.setString(
									5,
									shouhinbango.substring(0,
											shouhinbango.indexOf("-")));
							ps.setString(6, shouhinbango.substring(shouhinbango
									.indexOf("-")));
							ps.executeUpdate();
						} else if (stock > 0) {
							detail.setFusokusu(kosu - stock);
							sql = "UPDATE TBL00012 SET STOCK_JP = ?, STOCK_HANDUP = ?, UPDATE_TIME = ? , UPDATE_USER = ?, UPDATEQUANTITY_FLG =TRUE WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
							ps = conn.prepareStatement(sql);
							ps.setInt(1, 0);
							ps.setInt(2, 0);
							ps.setString(3, Utility.getDateTime());
							ps.setString(4, Utility.getUser());
							ps.setString(
									5,
									shouhinbango.substring(0,
											shouhinbango.indexOf("-")));
							ps.setString(6, shouhinbango.substring(shouhinbango
									.indexOf("-")));
							ps.executeUpdate();

						} else {
							detail.setFusokusu(kosu);
						}
					} else {
						String msg = order.getJuchubango() + "中的"
								+ shouhinbango + "不存在，请检查库存列表！";
						errmsg.add(msg);
					}

				}
			}
			// if (errmsg.size() == 0) {
			// conn.commit();
			// }
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
			return orderList;
		}

	}

	public static List<OrderBean> getOrders(File file) {
		List<OrderBean> orderList = new ArrayList<OrderBean>();
		try {

			ArrayList<String[]> csvList = new ArrayList<String[]>(); // 用来保存数据
			CsvReader reader = new CsvReader(new FileInputStream(file), ',',
					Charset.forName("SJIS")); // 一般用这编码读就可以了

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

				// 配送会社
				String haisokaisha = strArr[122];

				// 配送方法
				String haisohoho = strArr[66];

				// お荷物伝票番号
				String toiawasebango = strArr[85];

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
					detailListBean.setShouhinbango(detailArr[102].replaceAll(
							"−", "-"));
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
				orderBean.setToiawasebango(toiawasebango);
				orderBean.setHaisohoho(haisohoho);
				orderBean.setHaisokaisha(haisokaisha);
			}

		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			return orderList;
		}

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public File getInputPath3() {
		return inputPath3;
	}

	public void setInputPath3(File inputPath3) {
		this.inputPath3 = inputPath3;
	}

	public String getFileNameYamato() {
		return fileNameYamato;
	}

	public void setFileNameYamato(String fileNameYamato) {
		this.fileNameYamato = fileNameYamato;
	}

	public String getFileNameSagawa() {
		return fileNameSagawa;
	}

	public void setFileNameSagawa(String fileNameSagawa) {
		this.fileNameSagawa = fileNameSagawa;
	}

	public String getFileNameHassou() {
		return fileNameHassou;
	}

	public void setFileNameHassou(String fileNameHassou) {
		this.fileNameHassou = fileNameHassou;
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

	public String getDirName() {
		return dirName;
	}

	public void setDirName(String dirName) {
		this.dirName = dirName;
	}
}
