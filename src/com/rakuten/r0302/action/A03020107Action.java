package com.rakuten.r0302.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.bean.DetailListBean;
import com.rakuten.common.bean.OrderBean;
import com.rakuten.r0302.form.F030201;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A03020107Action extends BaseAction {

	private F030201 f030201 = null;
	private static final long serialVersionUID = 1L;

	private String noSellDays = null;
	private String page = null;
	private String goPage = null;
	private String nowPage = null;
	private String allPage = null;
	private String fileName = null;

	protected void exec() throws Exception {
		List<OrderBean> orderList = null;
		String startDay = getStartDay(Integer.valueOf(noSellDays));
		orderList = getOrderListFromDB(startDay);

		List<String[]> stockList = getStockList();

		List<String> comparedList = compareList(orderList, stockList,
				f030201.getPtype());

		List<Map<String, String>> downList = getCommodityInfo(comparedList);

		List<String[]> outputArr = new ArrayList<String[]>();
		for (int i = 0; i < downList.size(); i++) {
			Map<String, String> dataMap = downList.get(i);
			String[] arr = { dataMap.get("COMMODITY_ID"),
					dataMap.get("COMM_DESCRIBE"), dataMap.get("CATEGORY_NAME"),
					dataMap.get("JAPANESE_NAME"), dataMap.get("PRICE_IN"),
					dataMap.get("RE_PRICE"), dataMap.get("STOCK_SH"),
					dataMap.get("STOCK_JP"), dataMap.get("REMARKS") };
			outputArr.add(arr);
		}

		downCsv(outputArr);

	}

	private List<OrderBean> getOrderListFromDB(String startDay)
			throws Exception {
		List<OrderBean> orderList = new ArrayList<OrderBean>();

		Connection conn = null;
		PreparedStatement ps = null;
		try {

			OrderBean order = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM common_order_tbl T1 LEFT JOIN common_order_detail_tbl T2 ON T1.CHUMONBANGO = T2.JUCHUBANGO LEFT JOIN HENPIN_TBL T3 ON T1.CHUMONBANGO = T3.JUCHUBANGO LEFT JOIN TUIKA_HASOU_TBL T4 ON T1.CHUMONBANGO = T4.JUCHUBANGO WHERE CHUMONNICHIJI >= ? ";

			ps = conn.prepareStatement(sql);
			ps.setString(1, startDay + " 00:00:00");

			ResultSet rs = ps.executeQuery();
			String chumonbangoComp = "";
			List<DetailListBean> detailList = null;
			DetailListBean detail = null;
			while (rs.next()) {
				if (!chumonbangoComp.equals(rs.getString("T1.CHUMONBANGO"))) {
					order = new OrderBean();

					order.setJuchubango(rs.getString("T1.CHUMONBANGO"));
					detailList = new ArrayList<DetailListBean>();
					order.setDetailList(detailList);
					orderList.add(order);
				}
				detail = new DetailListBean();
				detailList.add(detail);
				detail.setShouhinbango(rs.getString("T2.SHOUHINBANGO"));

				chumonbangoComp = rs.getString("T1.CHUMONBANGO");

			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return orderList;
	}

	private List<Map<String, String>> getCommodityInfo(List<String> comparedList)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			List<Map<String, String>> ouputList = new ArrayList<Map<String, String>>();
			Map<String, String> output = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT T2.CATEGORY_NAME, T1.CHINESE_NAME,T1.COMMODITY_ID, T1.JAPANESE_NAME,T3.DETAIL_NO,T3.COMM_DESCRIBE,T3.PRICE_IN,T3.RE_PRICE,T3.STOCK_SH,T3.STOCK_JP,T3.REMARKS FROM TBL00011 T1 LEFT JOIN TBL00010 T2 ON T1.CATEGORY_ID = T2.CATEGORY_ID LEFT JOIN TBL00012 T3 ON T1.COMMODITY_ID = T3.COMMODITY_ID WHERE T1.DEL_FLG = '0' AND T3.COMMODITY_ID = ? AND T3.DETAIL_NO = ?";
			for (int i = 0; i < comparedList.size(); i++) {
				String commodityId = Utility
						.getCommodityId(comparedList.get(i));
				String detailNo = Utility.getDetailN0(comparedList.get(i));

				ps = conn.prepareStatement(sql);
				ps.setString(1, commodityId);
				ps.setString(2, detailNo);

				ResultSet rs = ps.executeQuery();

				while (rs.next()) {
					output = new HashMap<String, String>();
					output.put("COMMODITY_ID", rs.getString("COMMODITY_ID")
							+ rs.getString("DETAIL_NO").replace("-0-0", ""));
					output.put("CATEGORY_NAME", rs.getString("CATEGORY_NAME"));
					output.put("JAPANESE_NAME", rs.getString("JAPANESE_NAME"));
					output.put("COMM_DESCRIBE", rs.getString("COMM_DESCRIBE"));
					output.put("PRICE_IN", rs.getString("PRICE_IN"));
					output.put("STOCK_SH", rs.getString("STOCK_SH"));
					output.put("STOCK_JP", rs.getString("STOCK_JP"));
					output.put("RE_PRICE", rs.getString("RE_PRICE"));

					ouputList.add(output);
				}
			}
			// commit
			conn.commit();
			return ouputList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	private List<String> compareList(List<OrderBean> checkedOrderList,
			List<String[]> stockList, String pType) {
		List<String> outList = new ArrayList<String>();
		List<String> ariList = new ArrayList<String>();
		for (int i = 0; i < stockList.size(); i++) {
			String[] stockInfo = stockList.get(i);
			for (int j = 0; j < checkedOrderList.size(); j++) {
				OrderBean orderBean = checkedOrderList.get(j);
				for (int k = 0; k < orderBean.getDetailList().size(); k++) {
					DetailListBean detailListBean = orderBean.getDetailList()
							.get(k);
					if (!Utility
							.isEmptyString(detailListBean.getShouhinbango())) {

						if (detailListBean.getShouhinbango().equals(
								stockInfo[0])) {
							ariList.add(stockInfo[0]);
						}
					}

				}
			}
		}

		for (int i = 0; i < stockList.size(); i++) {
			boolean ariFlg = false;
			for (int j = 0; j < ariList.size(); j++) {
				if (stockList.get(i)[0].equals(ariList.get(j))) {
					ariFlg = true;
					continue;
				}
			}
			if (!ariFlg) {
				outList.add(stockList.get(i)[0]);
				ariFlg = false;
			}

		}
		List<String> removeList = new ArrayList<String>();
		List<String> tempList = new ArrayList<String>();
		if ("01".equals(pType)) {
			for (int i = 0; i < outList.size(); i++) {
				for (int j = 0; j < checkedOrderList.size(); j++) {
					OrderBean orderBean = checkedOrderList.get(j);
					for (int k = 0; k < orderBean.getDetailList().size(); k++) {
						DetailListBean detailListBean = orderBean
								.getDetailList().get(k);
						if (!Utility.isEmptyString(detailListBean
								.getShouhinbango())) {
							if (Utility.getCommodityId(
									detailListBean.getShouhinbango()).equals(
									Utility.getCommodityId(outList.get(i)))) {
								removeList.add(Utility
										.getCommodityId(detailListBean
												.getShouhinbango()));
							}
						}
					}
				}
			}

			boolean ariFlg = false;
			for (int i = 0; i < outList.size(); i++) {
				for (int j = 0; j < removeList.size(); j++) {
					if (outList.get(i).contains(removeList.get(j))) {
						ariFlg = true;
						continue;
					}
				}
				if (!ariFlg) {
					tempList.add(outList.get(i));
				}
				ariFlg = false;
			}
			outList = tempList;
		}
		return outList;
	}

	private void downCsv(List<String[]> outputArr) throws IOException {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet1 = wb.createSheet("検索結果");
			sheet1.setColumnWidth(0, 4000);
			sheet1.setColumnWidth(1, 7000);
			sheet1.setColumnWidth(2, 3000);
			sheet1.setColumnWidth(3, 23000);
			sheet1.setColumnWidth(4, 3000);
			sheet1.setColumnWidth(5, 3000);
			sheet1.setColumnWidth(6, 3000);
			sheet1.setColumnWidth(7, 3000);
			sheet1.setColumnWidth(8, 7000);

			HSSFCellStyle style = wb.createCellStyle();
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			// style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
			// style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);//
			// 前景填充色

			String[] record = { "商品番号", "商品詳細", "カテゴリ", "商品名", "仕入れ金額", "販売価格",
					"上海在庫数", "日本在庫数", "備考" };
			HSSFRow row = null;
			HSSFCell cell = null;
			row = sheet1.createRow(0);
			row.setHeight((short) 400);
			for (int i = 0; i < record.length; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style);
				cell.setCellValue(record[i]);
			}

			for (int i = 0; i < outputArr.size(); i++) {
				// 创建一行
				row = sheet1.createRow(i + 1);
				row.setHeight((short) 400);
				for (int j = 0; j < outputArr.get(i).length; j++) {
					cell = row.createCell(j);
					cell.setCellStyle(style);
					cell.setCellValue(outputArr.get(i)[j]);
				}
			}

			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			fileName = "kekka_" + df.format(new Date()) + ".xls";
			FileOutputStream fileOut = new FileOutputStream("c:/temp/"
					+ fileName);
			// 指定文件名
			wb.write(fileOut);
			// 输出到文件
			fileOut.close();
		} finally {
			// bufferedWrite.close();
		}
	}

	private List<String[]> getStockList() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		List<String[]> stockList = new ArrayList<String[]>();
		try {
			conn = JdbcConnection.getConnection();
			String sql = "SELECT * FROM TBL00012 WHERE STOCK_JP > 0";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String commodityId = rs.getString("COMMODITY_ID");
				String detailNo = rs.getString("DETAIL_NO").replace("-0-0", "");
				String stockJp = rs.getString("STOCK_JP");
				String[] stockInfo = { commodityId + detailNo, stockJp };
				stockList.add(stockInfo);
			}
			// commit
			conn.commit();
			return stockList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	private String getStartDay(int days) throws ParseException {

		Calendar date = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		date.add(Calendar.DATE, -days);
		String shiteibi = sdf.format(date.getTime());

		return shiteibi;
	}

	protected void init() {
		setTitle("V030201:库存列表");
	}

	public InputStream getInputStream() throws IOException {
		return new FileInputStream(new File("c:/temp/" + fileName));
	}

	/**
	 * @return the noSellDays
	 */
	public String getNoSellDays() {
		return noSellDays;
	}

	/**
	 * @param noSellDays
	 *            the noSellDays to set
	 */
	public void setNoSellDays(String noSellDays) {
		this.noSellDays = noSellDays;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the goPage
	 */
	public String getGoPage() {
		return goPage;
	}

	/**
	 * @param goPage
	 *            the goPage to set
	 */
	public void setGoPage(String goPage) {
		this.goPage = goPage;
	}

	/**
	 * @return the nowPage
	 */
	public String getNowPage() {
		return nowPage;
	}

	/**
	 * @param nowPage
	 *            the nowPage to set
	 */
	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}

	/**
	 * @return the allPage
	 */
	public String getAllPage() {
		return allPage;
	}

	/**
	 * @param allPage
	 *            the allPage to set
	 */
	public void setAllPage(String allPage) {
		this.allPage = allPage;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F030201 getF030201() {
		return f030201;
	}

	public void setF030201(F030201 f030201) {
		this.f030201 = f030201;
	}

}
