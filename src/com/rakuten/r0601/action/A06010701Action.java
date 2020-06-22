package com.rakuten.r0601.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.GetWayBillDetailByIdAp;
import com.rakuten.r0601.bean.GetWayBillApDetailOutput;
import com.rakuten.r0601.form.F060101;

public class A06010701Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String fileName = null;
	private F060101 f060101 = null;

	protected void exec() throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet1 = wb.createSheet("发货单（上海至日本）");
		sheet1.setColumnWidth(0, 4000);
		sheet1.setColumnWidth(1, 7000);
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
		HSSFRow row = sheet1.createRow(0);
		row.setHeight((short) 400);

		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("商品编号");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("数量");

		GetWayBillDetailByIdAp getWayBillDetailByIdAp = new GetWayBillDetailByIdAp();
		String waybillNo = f060101.getWayBillList()
				.get(Integer.valueOf(getRowIndex())).getWaybillNo();
		List<GetWayBillApDetailOutput> detailList = getWayBillDetailByIdAp
				.execute(waybillNo);
		for (int i = 0; i < detailList.size(); i++) {
			row = sheet1.createRow(i + 1);
			row.setHeight((short) 400);

			cell = row.createCell(0);
			cell.setCellStyle(style1);
			cell.setCellValue(detailList.get(i).getCommodityId());

			cell = row.createCell(1);
			cell.setCellStyle(style1);
			cell.setCellValue(detailList.get(i).getQuantity());
		}
		fileName = "KAKUNIN_" + waybillNo + ".xls";
		FileOutputStream fileOut = new FileOutputStream("c:/temp/" + fileName);
		// 指定文件名
		wb.write(fileOut);
		// 输出到文件
		fileOut.close();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	protected void init() {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the f060101
	 */
	public F060101 getF060101() {
		return f060101;
	}

	/**
	 * @param f060101
	 *            the f060101 to set
	 */
	public void setF060101(F060101 f060101) {
		this.f060101 = f060101;
	}

	public InputStream getInputStream() throws FileNotFoundException {
		return new FileInputStream(new File("c:/temp/" + fileName));
	}
}
