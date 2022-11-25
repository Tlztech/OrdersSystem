package com.rakuten.r0302.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r0302.form.F030201;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A03020104Action extends BaseAction {

	private F030201 f030201 = null;
	private String shoriMode = null;
	private static final long serialVersionUID = 1L;
	private String fileName = null;

	protected void exec() throws Exception {

		List<Map<String, String>> outputList = getComm(f030201);
		List<String[]> outputArr = new ArrayList<String[]>();
		for (int i = 0; i < outputList.size(); i++) {
			Map<String, String> dataMap = outputList.get(i);
			String[] arr = { dataMap.get("COMMODITY_ID"),
					dataMap.get("COMM_DESCRIBE"), dataMap.get("CATEGORY_NAME"),
					dataMap.get("JAPANESE_NAME"), dataMap.get("PRICE_IN"),
					dataMap.get("RE_PRICE"), dataMap.get("STOCK_SH"),
					dataMap.get("STOCK_JP"), dataMap.get("REMARKS"), dataMap.get("CREATE_TIME"), dataMap.get("UPDATE_TIME")  };
			outputArr.add(arr);
		}

		downCsv(outputArr);
	}

	public InputStream getInputStream() throws FileNotFoundException {
		return new FileInputStream(new File("c:/temp/" + fileName));
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
			sheet1.setColumnWidth(9, 4000);
			sheet1.setColumnWidth(10, 4000);

			HSSFCellStyle style = wb.createCellStyle();
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//			style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
//			style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// 前景填充色

			String[] record = { "商品番号", "商品詳細", "カテゴリ", "商品名", "仕入れ金額", "販売価格",
					"上海在庫数", "日本在庫数", "備考", "作成時間", "更新時間" };
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
			fileName = "kensakukekka_" + df.format(new Date()) + ".xls";
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

	protected void init() {
		setTitle("V030201:库存列表");
	}

	protected void isValidated() throws Exception {
		if (f030201 == null || Utility.isEmptyList(f030201.getCommodityList())) {
			addError(null, "没有查询结果！");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F030201 getF030201() {
		return f030201;
	}

	public void setF030201(F030201 f030201) {
		this.f030201 = f030201;
	}

	/**
	 * @return the shoriMode
	 */
	public String getShoriMode() {
		return shoriMode;
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
	 * @param shoriMode
	 *            the shoriMode to set
	 */
	public void setShoriMode(String shoriMode) {
		this.shoriMode = shoriMode;
	}

	private List<Map<String, String>> getComm(F030201 f030201) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Map<String,Object> map =  ActionContext.getContext().getSession();
			int companyId;
			if (null == map.get("comp")) {
				companyId = -1;
			} else {
				companyId = (int)map.get("comp");
			}
			List<Map<String, String>> ouputList = new ArrayList<Map<String, String>>();
			Map<String, String> output = null;
			conn = JdbcConnection.getConnection();
			String sql = "SELECT T2.CATEGORY_NAME, T1.CHINESE_NAME,T1.COMMODITY_ID, T1.JAPANESE_NAME,T3.DETAIL_NO,T3.COMM_DESCRIBE,T3.PRICE_IN,T3.RE_PRICE,T3.STOCK_SH,T3.STOCK_JP,T3.REMARKS, T3.CREATE_TIME, T3.UPDATE_TIME FROM TBL00011 T1 LEFT JOIN TBL00010 T2 ON T1.CATEGORY_ID = T2.CATEGORY_ID LEFT JOIN TBL00012 T3 ON T1.COMMODITY_ID = T3.COMMODITY_ID WHERE T1.DEL_FLG = '0'";

			String commodityId = f030201.getHid_commodityId();
			String categoryId = f030201.getHid_categoryId();
			String chineseName = f030201.getHid_chineseName();
			String japaneseName = f030201.getHid_japaneseName();
			String stockShStart = f030201.getHid_stockShStart();
			String stockJpStart = f030201.getHid_stockJpStart();
			String stockShEnd = f030201.getHid_stockShEnd();
			String stockJpEnd = f030201.getHid_stockJpEnd();
			String updateTimeStart = f030201.getHid_updateTimeStart();
			String updateTimeEnd = f030201.getHid_updateTimeEnd();

			if (Utility.isEmptyString(stockShStart)) {
				stockShStart = "0";
			}
			if (Utility.isEmptyString(stockJpStart)) {
				stockJpStart = "0";
			}
			if (Utility.isEmptyString(stockShEnd)) {
				stockShEnd = "9999";
			}
			if (Utility.isEmptyString(stockJpEnd)) {
				stockJpEnd = "9999";
			}

			if (!Utility.isEmptyString(commodityId)) {
				sql += " AND T1.COMMODITY_ID in (select commodity_id from company_commodity_tbl where COMMODITY_ID LIKE '" + commodityId + "%' AND (COMPANY_ID = "+companyId+" OR "+ companyId + " = 0 OR "+ companyId + " = 1))";
			} else {
				sql += " AND T1.COMMODITY_ID in (select commodity_id from company_commodity_tbl where (COMPANY_ID = "+ companyId+ " OR " + companyId + " = 0 OR " + companyId +" = 1))";
			}
			if (!Utility.isEmptyString(categoryId)) {
				sql += " AND T1.CATEGORY_ID = '" + categoryId + "'";
			}
			if (!Utility.isEmptyString(chineseName)) {
				sql += " AND T1.CHINESE_NAME LIKE  '%" + chineseName + "%'";
			}
			if (!Utility.isEmptyString(japaneseName)) {
				sql += " AND T1.JAPANESE_NAME LIKE  '%" + japaneseName + "%'";
			}
			if (!Utility.isEmptyString(updateTimeStart)) {
				sql += " AND T3.UPDATE_TIME >= '" + updateTimeStart + "'";
			}
			if (!Utility.isEmptyString(updateTimeEnd)) {
				sql += " AND T3.UPDATE_TIME <= '" + updateTimeEnd + "'";
			}

//			sql += " AND STOCK_SH >= " + stockShStart;
//			sql += " AND STOCK_SH <= " + stockShEnd;
			sql += " AND STOCK_JP >= " + stockJpStart;
			sql += " AND STOCK_JP <= " + stockJpEnd;

			sql += " ORDER BY T1.COMMODITY_ID";

			ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				output = new HashMap<String, String>();
				output.put(
						"COMMODITY_ID",
						rs.getString("COMMODITY_ID")
								+ rs.getString("DETAIL_NO"));
				output.put("CATEGORY_NAME", rs.getString("CATEGORY_NAME"));
				output.put("JAPANESE_NAME", rs.getString("JAPANESE_NAME"));
				output.put("COMM_DESCRIBE", rs.getString("COMM_DESCRIBE"));
				output.put("PRICE_IN", rs.getString("PRICE_IN"));
				output.put("STOCK_SH", rs.getString("STOCK_SH"));
				output.put("STOCK_JP", rs.getString("STOCK_JP"));
				output.put("RE_PRICE", rs.getString("RE_PRICE"));
				output.put("CREATE_TIME", rs.getString("CREATE_TIME"));
				output.put("UPDATE_TIME", rs.getString("UPDATE_TIME"));

				ouputList.add(output);
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

}
