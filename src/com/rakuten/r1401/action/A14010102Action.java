package com.rakuten.r1401.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.r1001.form.HasomachiList;
import com.rakuten.r1401.bean.Hasobean;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A14010102Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String fileNameHassou = null;
	private String shop = null;

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public String getFileNameHassou() {
		return fileNameHassou;
	}

	public void setFileNameHassou(String fileNameHassou) {
		this.fileNameHassou = fileNameHassou;
	}

	protected void exec() throws Exception {
		init();

	}

	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("c:/temp/" + fileNameHassou));
	}

	protected void init() throws Exception {
		setTitle("V140101:发送预警");

		List<Hasobean> hasobeanList = new ArrayList<Hasobean>();
		Hasobean hasobean = null;
		OrderCommon orderCommon = new OrderCommon();
		OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean
				.getCommonOrderBeanList();
		List<ShouhinStsBean> shouhinStsBeanList = orderInfoBean
				.getShouhinStsBeanList();
		// 発送待ち
		List<String> hasomachiList222 = orderCommon
				.getHasomachiList(commonOrderBeanList);
		List<String> shoriList = new ArrayList<String>();
		for (String bango : hasomachiList222) {
			if (bango.startsWith(shop)) {
				shoriList.add(bango);
			}
		}
		Connection conn = null;
		try {
			conn = JdbcConnection.getConnection();
			for (String orderNo : shoriList) {
				hasobean = new Hasobean();
				hasobeanList.add(hasobean);
				// 获取发送约定日
				String yakusokubi = orderCommon.getHasoyakusokubiFast(orderNo,
						conn);
				// 发送预订日
				String latestData = "";
				CommonOrderBean thisOrder = null;
				for (CommonOrderBean order : commonOrderBeanList) {
					if (order.getJuchubango().equals(orderNo)) {
						thisOrder = order;
					}
				}
				if (thisOrder == null) {
					thisOrder = new CommonOrderBean();
				}
				List<HasomachiList> hasomachiList = new ArrayList<HasomachiList>();
				HasomachiList hasomachiBean = null;
				List<String[]> hasomachiArr = orderCommon.getMachiListAll(
						thisOrder, shouhinStsBeanList, "2", "7");

				boolean blankFlg = false;
				for (String[] hasomachi : hasomachiArr) {
					hasomachiBean = new HasomachiList();
					hasomachiList.add(hasomachiBean);

					hasomachiBean.setShohinbango(hasomachi[0]);
					hasomachiBean.setKosu(hasomachi[1]);

					if (!Utility.isEmptyString(hasomachi[4])) {
						if (Utility.isEmptyString(latestData)) {
							latestData = hasomachi[4];
						} else {
							if (Long.valueOf(hasomachi[4]) > Long
									.valueOf(latestData)) {
								latestData = hasomachi[4];
							}
						}
					} else {
						blankFlg = true;
					}
				}
				if (blankFlg) {
					latestData = "";
				}

				hasobean.setChumonbango(orderNo);
				hasobean.setChumonnichiji(thisOrder.getChumonichiji()
						.substring(0, 10).replace("-", ""));
				hasobean.setHasoyoteibi(latestData.replace("-", ""));
				hasobean.setHasoyakusokubi(yakusokubi.replace("-", ""));
				String[] komantoBiko = orderCommon.getOrderKomentoAndBiko(
						orderNo, conn);
				hasobean.setKomento(komantoBiko[0].replace("[配送日時指定:]", "")
						.trim());
				hasobean.setBiko(komantoBiko[1]);

			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		// 2天内未出预定日未约定
		List<Hasobean> weichuweiyueding2 = new ArrayList<Hasobean>();
		// 2天内未出预定日已约定
		List<Hasobean> weichuyiyueding2 = new ArrayList<Hasobean>();
		// 2天内已出预定日未约定
		List<Hasobean> yichuweiyueding2 = new ArrayList<Hasobean>();
		// 2天内已出预定日已约定
		List<Hasobean> yichuyiyueding2 = new ArrayList<Hasobean>();
		// 2天内未按约定日发货
		List<Hasobean> weianyuedingfahuo2 = new ArrayList<Hasobean>();
		// 3天未出预定日未约定
		List<Hasobean> weichuweiyueding3 = new ArrayList<Hasobean>();
		// 3天未出预定日已约定
		List<Hasobean> weichuyiyueding3 = new ArrayList<Hasobean>();
		// 3天已出预定日未约定
		List<Hasobean> yichuweiyueding3 = new ArrayList<Hasobean>();
		// 3天已出预定日已约定
		List<Hasobean> yichuyiyueding3 = new ArrayList<Hasobean>();
		// 3天未按约定日发货
		List<Hasobean> weianyuedingfahuo3 = new ArrayList<Hasobean>();
		// 4天内未出预定日未约定
		List<Hasobean> weichuweiyueding4 = new ArrayList<Hasobean>();
		// 4天未出预定日已约定
		List<Hasobean> weichuyiyueding4 = new ArrayList<Hasobean>();
		// 4天已出预定日未约定
		List<Hasobean> yichuweiyueding4 = new ArrayList<Hasobean>();
		// 4天已出预定日已约定
		List<Hasobean> yichuyiyueding4 = new ArrayList<Hasobean>();
		// 4天未按约定日发货
		List<Hasobean> weianyuedingfahuo4 = new ArrayList<Hasobean>();
		// 5天未出预定日未约定
		List<Hasobean> weichuweiyueding5 = new ArrayList<Hasobean>();
		// 5天未出预定日已约定
		List<Hasobean> weichuyiyueding5 = new ArrayList<Hasobean>();
		// 5天已出预定日未约定
		List<Hasobean> yichuweiyueding5 = new ArrayList<Hasobean>();
		// 5天已出预定日已约定
		List<Hasobean> yichuyiyueding5 = new ArrayList<Hasobean>();
		// 5天未按约定日发货
		List<Hasobean> weianyuedingfahuo5 = new ArrayList<Hasobean>();
		// 6天未出预定日未约定
		List<Hasobean> weichuweiyueding6 = new ArrayList<Hasobean>();
		// 6天未出预定日已约定
		List<Hasobean> weichuyiyueding6 = new ArrayList<Hasobean>();
		// 6天已出预定日未约定
		List<Hasobean> yichuweiyueding6 = new ArrayList<Hasobean>();
		// 6天已出预定日已约定
		List<Hasobean> yichuyiyueding6 = new ArrayList<Hasobean>();
		// 6天未按约定日发货
		List<Hasobean> weianyuedingfahuo6 = new ArrayList<Hasobean>();
		// 7天未出预定日未约定
		List<Hasobean> weichuweiyueding7 = new ArrayList<Hasobean>();
		// 7天未出预定日已约定
		List<Hasobean> weichuyiyueding7 = new ArrayList<Hasobean>();
		// 7天已出预定日未约定
		List<Hasobean> yichuweiyueding7 = new ArrayList<Hasobean>();
		// 7天已出预定日已约定
		List<Hasobean> yichuyiyueding7 = new ArrayList<Hasobean>();
		// 7天未按约定日发货
		List<Hasobean> weianyuedingfahuo7 = new ArrayList<Hasobean>();
		// 7天以上未出预定日未约定
		List<Hasobean> weichuweiyueding8 = new ArrayList<Hasobean>();
		// 7天以上未出预定日已约定
		List<Hasobean> weichuyiyueding8 = new ArrayList<Hasobean>();
		// 7天以上已出预定日未约定
		List<Hasobean> yichuweiyueding8 = new ArrayList<Hasobean>();
		// 7天以上已出预定日已约定
		List<Hasobean> yichuyiyueding8 = new ArrayList<Hasobean>();
		// 7天以上未按约定日发货
		List<Hasobean> weianyuedingfahuo8 = new ArrayList<Hasobean>();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		String today = sdf.format(new Date());

		for (Hasobean bean : hasobeanList) {
			String chumonnichiji = bean.getChumonnichiji();

			int days = countDays(chumonnichiji, today);

			if (!Utility.isEmptyString(bean.getHasoyakusokubi())) {
				if (Long.valueOf(bean.getHasoyakusokubi())
						- Long.valueOf(today) < 0) {
					// 未按约定日发货
					if (days <= 2) {
						weianyuedingfahuo2.add(bean);
					} else if (days == 3) {
						weianyuedingfahuo3.add(bean);
					} else if (days == 4) {
						weianyuedingfahuo4.add(bean);
					} else if (days == 5) {
						weianyuedingfahuo5.add(bean);
					} else if (days == 6) {
						weianyuedingfahuo6.add(bean);
					} else if (days == 7) {
						weianyuedingfahuo7.add(bean);
					} else {
						weianyuedingfahuo8.add(bean);
					}
					continue;
				}
				// 已出预定日已约定
				if (days <= 2) {
					yichuyiyueding2.add(bean);
				} else if (days == 3) {
					yichuyiyueding3.add(bean);
				} else if (days == 4) {
					yichuyiyueding4.add(bean);
				} else if (days == 5) {
					yichuyiyueding5.add(bean);
				} else if (days == 6) {
					yichuyiyueding6.add(bean);
				} else if (days == 7) {
					yichuyiyueding7.add(bean);
				} else {
					yichuyiyueding8.add(bean);
				}
				continue;
			}

			if (Utility.isEmptyString(bean.getHasoyakusokubi())
					&& !Utility.isEmptyString(bean.getHasoyoteibi())) {
				// 已出预定日未约定
				if (days <= 2) {
					yichuweiyueding2.add(bean);
				} else if (days == 3) {
					yichuweiyueding3.add(bean);
				} else if (days == 4) {
					yichuweiyueding4.add(bean);
				} else if (days == 5) {
					yichuweiyueding5.add(bean);
				} else if (days == 6) {
					yichuweiyueding6.add(bean);
				} else if (days == 7) {
					yichuweiyueding7.add(bean);
				} else {
					yichuweiyueding8.add(bean);
				}
				continue;

			}

			if (!Utility.isEmptyString(bean.getHasoyakusokubi())
					&& Utility.isEmptyString(bean.getHasoyoteibi())) {
				// 未出预定日已约定
				if (days <= 2) {
					weichuyiyueding2.add(bean);
				} else if (days == 3) {
					weichuyiyueding3.add(bean);
				} else if (days == 4) {
					weichuyiyueding4.add(bean);
				} else if (days == 5) {
					weichuyiyueding5.add(bean);
				} else if (days == 6) {
					weichuyiyueding6.add(bean);
				} else if (days == 7) {
					weichuyiyueding7.add(bean);
				} else {
					weichuyiyueding8.add(bean);
				}
				continue;
			}

			if (Utility.isEmptyString(bean.getHasoyakusokubi())
					&& Utility.isEmptyString(bean.getHasoyoteibi())) {
				// 未出预定未未约定
				if (days <= 2) {
					weichuweiyueding2.add(bean);
				} else if (days == 3) {
					weichuweiyueding3.add(bean);
				} else if (days == 4) {
					weichuweiyueding4.add(bean);
				} else if (days == 5) {
					weichuweiyueding5.add(bean);
				} else if (days == 6) {
					weichuweiyueding6.add(bean);
				} else if (days == 7) {
					weichuweiyueding7.add(bean);
				} else {
					weichuweiyueding8.add(bean);
				}
			}
		}

		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet1 = wb.createSheet("2天内的单子");
		HSSFSheet sheet2 = wb.createSheet("3天的单子");
		HSSFSheet sheet3 = wb.createSheet("4天的单子");
		HSSFSheet sheet4 = wb.createSheet("5天的单子");
		HSSFSheet sheet5 = wb.createSheet("6天的单子");
		HSSFSheet sheet6 = wb.createSheet("7天的单子");
		HSSFSheet sheet7 = wb.createSheet("7天以上的单子");

		sheet1.setDisplayGridlines(false);
		sheet2.setDisplayGridlines(false);
		sheet3.setDisplayGridlines(false);
		sheet4.setDisplayGridlines(false);
		sheet5.setDisplayGridlines(false);
		sheet6.setDisplayGridlines(false);
		sheet7.setDisplayGridlines(false);

		sheet1.setColumnWidth(0, 8000);
		sheet1.setColumnWidth(1, 4000);
		sheet1.setColumnWidth(2, 4000);
		sheet1.setColumnWidth(3, 4000);
		sheet1.setColumnWidth(4, 7000);
		sheet1.setColumnWidth(5, 7000);

		sheet2.setColumnWidth(0, 8000);
		sheet2.setColumnWidth(1, 4000);
		sheet2.setColumnWidth(2, 4000);
		sheet2.setColumnWidth(3, 4000);
		sheet2.setColumnWidth(4, 7000);
		sheet2.setColumnWidth(5, 7000);

		sheet3.setColumnWidth(0, 8000);
		sheet3.setColumnWidth(1, 4000);
		sheet3.setColumnWidth(2, 4000);
		sheet3.setColumnWidth(3, 4000);
		sheet3.setColumnWidth(4, 7000);
		sheet3.setColumnWidth(5, 7000);

		sheet4.setColumnWidth(0, 8000);
		sheet4.setColumnWidth(1, 4000);
		sheet4.setColumnWidth(2, 4000);
		sheet4.setColumnWidth(3, 4000);
		sheet4.setColumnWidth(4, 7000);
		sheet4.setColumnWidth(5, 7000);

		sheet5.setColumnWidth(0, 8000);
		sheet5.setColumnWidth(1, 4000);
		sheet5.setColumnWidth(2, 4000);
		sheet5.setColumnWidth(3, 4000);
		sheet5.setColumnWidth(4, 7000);
		sheet5.setColumnWidth(5, 7000);

		sheet6.setColumnWidth(0, 8000);
		sheet6.setColumnWidth(1, 4000);
		sheet6.setColumnWidth(2, 4000);
		sheet6.setColumnWidth(3, 4000);
		sheet6.setColumnWidth(4, 7000);
		sheet6.setColumnWidth(5, 7000);

		sheet7.setColumnWidth(0, 8000);
		sheet7.setColumnWidth(1, 4000);
		sheet7.setColumnWidth(2, 4000);
		sheet7.setColumnWidth(3, 4000);
		sheet7.setColumnWidth(4, 7000);
		sheet7.setColumnWidth(5, 7000);
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

		// 创建一行
		int k = 0;
		HSSFRow row = sheet1.createRow(k++);
		row.setHeight((short) 400);

		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日未约定");

		row = sheet1.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuweiyueding2) {
			row = sheet1.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/
		row = sheet1.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日已约定");

		row = sheet1.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuyiyueding2) {
			row = sheet1.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet1.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日未约定");

		row = sheet1.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuweiyueding2) {
			row = sheet1.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet1.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日已约定");

		row = sheet1.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuyiyueding2) {
			row = sheet1.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet1.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未按约定日发货");

		row = sheet1.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weianyuedingfahuo2) {
			row = sheet1.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		k = 0;
		row = sheet2.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日未约定");

		row = sheet2.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuweiyueding3) {
			row = sheet2.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/
		row = sheet2.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日已约定");

		row = sheet2.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuyiyueding3) {
			row = sheet2.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet2.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日未约定");

		row = sheet2.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuweiyueding3) {
			row = sheet2.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet2.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日已约定");

		row = sheet2.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuyiyueding3) {
			row = sheet2.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet2.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未按约定日发货");

		row = sheet2.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weianyuedingfahuo3) {
			row = sheet2.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		k = 0;
		row = sheet3.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日未约定");

		row = sheet3.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuweiyueding4) {
			row = sheet3.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/
		row = sheet3.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日已约定");

		row = sheet3.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuyiyueding4) {
			row = sheet3.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet3.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日未约定");

		row = sheet3.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuweiyueding4) {
			row = sheet3.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet3.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日已约定");

		row = sheet3.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuyiyueding4) {
			row = sheet3.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet3.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未按约定日发货");

		row = sheet3.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weianyuedingfahuo4) {
			row = sheet3.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		k = 0;
		row = sheet4.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日未约定");

		row = sheet4.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuweiyueding5) {
			row = sheet4.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/
		row = sheet4.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日已约定");

		row = sheet4.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuyiyueding5) {
			row = sheet4.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet4.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日未约定");

		row = sheet4.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuweiyueding5) {
			row = sheet4.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet4.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日已约定");

		row = sheet4.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuyiyueding5) {
			row = sheet4.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet4.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未按约定日发货");

		row = sheet4.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weianyuedingfahuo5) {
			row = sheet4.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		k = 0;
		row = sheet5.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日未约定");

		row = sheet5.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuweiyueding6) {
			row = sheet5.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/
		row = sheet5.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日已约定");

		row = sheet5.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuyiyueding6) {
			row = sheet5.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet5.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日未约定");

		row = sheet5.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuweiyueding6) {
			row = sheet5.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet5.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日已约定");

		row = sheet5.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuyiyueding6) {
			row = sheet5.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet5.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未按约定日发货");

		row = sheet5.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weianyuedingfahuo6) {
			row = sheet5.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		k = 0;
		row = sheet6.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日未约定");

		row = sheet6.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuweiyueding7) {
			row = sheet6.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/
		row = sheet6.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日已约定");

		row = sheet6.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuyiyueding7) {
			row = sheet6.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet6.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日未约定");

		row = sheet6.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuweiyueding7) {
			row = sheet6.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet6.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日已约定");

		row = sheet6.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuyiyueding7) {
			row = sheet6.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet6.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未按约定日发货");

		row = sheet6.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weianyuedingfahuo7) {
			row = sheet6.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		k = 0;
		row = sheet7.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日未约定");

		row = sheet7.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuweiyueding8) {
			row = sheet7.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/
		row = sheet7.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日已约定");

		row = sheet7.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuyiyueding8) {
			row = sheet7.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet7.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("出预定日未约定");

		row = sheet7.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : yichuweiyueding8) {
			row = sheet7.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet7.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未出预定日已约定");

		row = sheet7.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weichuyiyueding8) {
			row = sheet7.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/

		/*****************************************/
		row = sheet7.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("未按约定日发货");

		row = sheet7.createRow(k++);
		row.setHeight((short) 400);

		cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("受注番号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("注文日時");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("発送予定日");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("発送約束日");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("コメント");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("URL");
		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("URL2");

		for (Hasobean bean : weianyuedingfahuo8) {
			row = sheet7.createRow(k++);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonbango());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getChumonnichiji());

			cell = row.createCell(2);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyoteibi());

			cell = row.createCell(3);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getHasoyakusokubi());

			cell = row.createCell(4);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getKomento());

			cell = row.createCell(5);
			cell.setCellStyle(style1);
			cell.setCellValue(bean.getBiko());
			cell = row.createCell(6);
			cell = row.createCell(6);
			cell.setCellValue("http://dongtze123.xicp.net:29494/OrdersSystem/A10010201?OrderNo="
					+ bean.getChumonbango());
			cell = row.createCell(7);
			cell.setCellValue("https://order.rms.rakuten.co.jp/rms/mall/order/rb/vc?__event=BO02_001_013&order_number="
					+ bean.getChumonbango());

		}
		/*****************************************/
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		fileNameHassou = df.format(new Date()) + ".xls";
		FileOutputStream fileOut = new FileOutputStream("c:/temp/"
				+ fileNameHassou);
		// 指定文件名
		wb.write(fileOut);
		// 输出到文件
		fileOut.close();

	}

	private int countDays(String begin, String end) throws Exception {
		int days = 0;
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		Calendar c_b = Calendar.getInstance();
		Calendar c_e = Calendar.getInstance();

		c_b.setTime(df.parse(begin));
		c_e.setTime(df.parse(end));
		while (c_b.before(c_e)) {
			days++;
			c_b.add(Calendar.DAY_OF_YEAR, 1);
		}

		return days;
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

}
