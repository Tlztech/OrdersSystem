package com.rakuten.r0701.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.csvreader.CsvReader;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r0302.ap.GetCommodityByIdAp;
import com.rakuten.r0302.bean.CommodityDetail;
import com.rakuten.r0302.bean.GetCommodityByIdApOutput;
import com.rakuten.r0602.bean.DetailListBean;
import com.rakuten.r0602.bean.OrderBean;
import com.rakuten.r0701.bean.CommodityBean;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A07010102Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	// file
	private File inputPath1 = null;
	private File inputPath3 = null;
	private List<String> errmsg = new ArrayList<String>();
	private String fileName = null;

	protected void exec() throws Exception {

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

			download(orderList);

		}
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

	public void download(List<OrderBean> orderlist) throws Exception {
		List<CommodityBean> commodityList = new ArrayList<CommodityBean>();
		for (int i = 0; i < orderlist.size(); i++) {
			OrderBean order = orderlist.get(i);
			List<DetailListBean> detailList = order.getDetailList();
			for (int j = 0; j < detailList.size(); j++) {
				DetailListBean detail = detailList.get(j);
				boolean ariFlg = false;
				for (int k = 0; k < commodityList.size(); k++) {
					if (commodityList.get(k).getCommodityId()
							.equals(detail.getShouhinbango())) {
						ariFlg = true;
						commodityList.get(k).setKosu(
								String.valueOf(Integer.valueOf(commodityList
										.get(k).getKosu())
										+ Integer.valueOf(detail.getKosu())));
						if (detail.getFusokusu() == 0) {
							commodityList.get(k)
									.setLock(
											String.valueOf(Integer
													.valueOf(commodityList.get(
															k).getLock())
													+ Integer.valueOf(detail
															.getKosu())));
						} else if (detail.getFusokusu() < Integer
								.valueOf(detail.getKosu())) {
							commodityList
									.get(k)
									.setLock(
											String.valueOf(Integer
													.valueOf(commodityList.get(
															k).getLock())
													+ (Integer.valueOf(detail
															.getKosu()) - detail
															.getFusokusu())));
						}
					}
				}
				if (!ariFlg) {
					CommodityBean commodity = new CommodityBean();
					commodity.setCommodityId(detail.getShouhinbango());
					commodity.setKosu(detail.getKosu());
					commodity.setRakuten_url(detail.getRakuten_url());
					if (detail.getFusokusu() == 0) {
						commodity.setLock(detail.getKosu());
					} else if (detail.getFusokusu() < Integer.valueOf(detail
							.getKosu())) {
						commodity.setLock(String.valueOf(Integer.valueOf(detail
								.getKosu()) - detail.getFusokusu()));
					}

					commodityList.add(commodity);
				}
			}
		}
		for (int i = 0; i < commodityList.size(); i++) {
			CommodityBean commodity = commodityList.get(i);
			GetCommodityByIdAp ap = new GetCommodityByIdAp();
			GetCommodityByIdApOutput output = ap.execute(commodity
					.getCommodityId().substring(0,
							commodity.getCommodityId().indexOf("-")));
			String detailNo = commodity.getCommodityId().substring(
					commodity.getCommodityId().indexOf("-"));
			List<CommodityDetail> detailList = output.getCommodityDetailList();
			for (int j = 0; j < detailList.size(); j++) {
				if (detailList.get(j).getDetailNo().equals(detailNo)) {
					commodity.setStockjp(detailList.get(j).getStockJp());
					commodity.setStocksh(detailList.get(j).getStockSh());
				}
			}
			commodity.setUrl(output.getCommodityUrl());
			commodity.setChineseName(output.getChineseName());

			String tochu = "0";
			Connection conn = null;
			PreparedStatement ps = null;
			String sql = null;
			ResultSet rs = null;
			try {
				conn = JdbcConnection.getConnection();
				sql = "SELECT SUM(T1.QUANTITY) QUANTITY FROM TBL00014 T1 LEFT JOIN TBL00013 T2 ON T1.WAYBILL_NO = T2.WAYBILL_NO WHERE T1.COMMODITY_ID = ? AND T2.STATUS = '00'";
				ps = conn.prepareStatement(sql);
				ps.setString(1, commodity.getCommodityId());
				rs = ps.executeQuery();
				while (rs.next()) {
					if (!Utility.isEmptyString(rs.getString("QUANTITY"))) {
						tochu = rs.getString("QUANTITY");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}
			commodity.setTochu(tochu);

		}

		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet1 = wb.createSheet("进货单");

		sheet1.setDisplayGridlines(false);

		sheet1.setColumnWidth(0, 4000);
		sheet1.setColumnWidth(1, 6000);
		sheet1.setColumnWidth(2, 5000);
		sheet1.setColumnWidth(3, 6000);
		sheet1.setColumnWidth(4, 7000);
		sheet1.setColumnWidth(5, 2000);
		sheet1.setColumnWidth(6, 2000);
		sheet1.setColumnWidth(7, 2000);
		sheet1.setColumnWidth(8, 2000);
		sheet1.setColumnWidth(9, 2000);
		sheet1.setColumnWidth(10, 2000);
		sheet1.setColumnWidth(11, 2000);
		sheet1.setColumnWidth(12, 2000);

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

		HSSFRow row = sheet1.createRow(0);
		row.setHeight((short) 400);

		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("商品编号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("商品详细");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("乐天地址");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("中文名");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("进货地址");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("需求个数");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("上海库存");

		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("进货在途");

		cell = row.createCell(8);
		cell.setCellStyle(style);
		cell.setCellValue("日本库存");

		cell = row.createCell(9);
		cell.setCellStyle(style);
		cell.setCellValue("运输在途");

		cell = row.createCell(10);
		cell.setCellStyle(style);
		cell.setCellValue("需进货数");

		cell = row.createCell(11);
		cell.setCellStyle(style);
		cell.setCellValue("进货标识");

		cell = row.createCell(12);
		cell.setCellStyle(style);
		cell.setCellValue("实进货数");

		for (int i = 0; i < commodityList.size(); i++) {
			Connection conn = null;
			PreparedStatement ps = null;
			String sql = null;
			ResultSet rs = null;

			CommodityBean commodity = commodityList.get(i);

			row = sheet1.createRow(i + 1);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(commodity.getCommodityId());

			String describe = "";

			try {
				conn = JdbcConnection.getConnection();
				sql = "SELECT COMM_DESCRIBE FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
				ps = conn.prepareStatement(sql);
				if (commodity.getCommodityId().contains("-")) {
					ps.setString(
							1,
							commodity.getCommodityId().substring(0,
									commodity.getCommodityId().indexOf("-")));
					ps.setString(
							2,
							commodity.getCommodityId().substring(
									commodity.getCommodityId().indexOf("-")));
				} else {
					ps.setString(1, commodity.getCommodityId());
					ps.setString(2, "");
				}
				rs = ps.executeQuery();
				while (rs.next()) {
					if (!Utility.isEmptyString(rs.getString("COMM_DESCRIBE"))) {
						describe = rs.getString("COMM_DESCRIBE");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(describe);

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(commodity.getRakuten_url());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(commodity.getChineseName());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(commodity.getUrl());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(Integer.valueOf(commodity.getKosu()));

			cell = row.createCell(6);
			cell.setCellStyle(style1);
			cell.setCellValue(Integer.valueOf(commodity.getStocksh()));

			cell = row.createCell(7);
			cell.setCellStyle(style1);

			String quantity = "0";

			try {
				conn = JdbcConnection.getConnection();
				sql = "SELECT SUM(QUANTITY) QUANTITY FROM TBL00015 WHERE COMMODITY_ID = ? AND COMMODITY_STATUS = '0'";
				ps = conn.prepareStatement(sql);
				ps.setString(1, commodity.getCommodityId());
				rs = ps.executeQuery();
				while (rs.next()) {
					if (!Utility.isEmptyString(rs.getString("QUANTITY"))) {
						quantity = rs.getString("QUANTITY");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}

			cell.setCellValue(Integer.valueOf(quantity));

			cell = row.createCell(8);
			cell.setCellStyle(style1);
			cell.setCellValue(Integer.valueOf(commodity.getStockjp()));

			cell = row.createCell(9);
			cell.setCellStyle(style1);
			cell.setCellValue(Integer.valueOf(commodity.getTochu()));

			cell = row.createCell(10);
			cell.setCellStyle(style1);
			cell.setCellValue(Integer.valueOf(commodity.getKosu())
					- Integer.valueOf(commodity.getStockjp())
					- Integer.valueOf(commodity.getStocksh())
					- Integer.valueOf(commodity.getTochu())
					- Integer.valueOf(quantity) < 0 ? 0 : Integer
					.valueOf(commodity.getKosu())
					- Integer.valueOf(commodity.getStockjp())
					- Integer.valueOf(commodity.getStocksh())
					- Integer.valueOf(commodity.getTochu())
					- Integer.valueOf(quantity));

			cell = row.createCell(11);
			cell.setCellStyle(style1);
			if (Integer.valueOf(commodity.getKosu())
					- Integer.valueOf(commodity.getStockjp())
					- Integer.valueOf(commodity.getStocksh())
					- Integer.valueOf(commodity.getTochu())
					- Integer.valueOf(quantity) > 0) {
				cell.setCellValue("是");
			} else {
				cell.setCellValue("否");
			}

			cell = row.createCell(12);
			cell.setCellStyle(style1);
		}
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		fileName = "JINHUO_" + df.format(new Date()) + ".xls";
		FileOutputStream fileOut = new FileOutputStream("c:/temp/" + fileName);
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
						while (rs.next()) {
							stock = rs.getInt("STOCK_JP");
						}
						int kosu = Integer.valueOf(detail.getKosu());
						if (stock - kosu >= 0) {
							detail.setFusokusu(0);
							sql = "UPDATE TBL00012 SET STOCK_JP = ?, UPDATEQUANTITY_FLG =TRUE WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
							ps = conn.prepareStatement(sql);
							ps.setInt(1, stock - kosu);
							ps.setString(
									2,
									shouhinbango.substring(0,
											shouhinbango.indexOf("-")));
							ps.setString(3, shouhinbango.substring(shouhinbango
									.indexOf("-")));
							ps.executeUpdate();
						} else if (stock > 0) {
							detail.setFusokusu(kosu - stock);
							sql = "UPDATE TBL00012 SET STOCK_JP = ?, UPDATEQUANTITY_FLG =TRUE WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
							ps = conn.prepareStatement(sql);
							ps.setInt(1, 0);
							ps.setString(
									2,
									shouhinbango.substring(0,
											shouhinbango.indexOf("-")));
							ps.setString(3, shouhinbango.substring(shouhinbango
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
				String gokeikingaku = strArr[24];
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

				detailBean.setRakuten_url(strArr[103]);
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
					detailListBean.setRakuten_url(detailArr[103]);
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

}
