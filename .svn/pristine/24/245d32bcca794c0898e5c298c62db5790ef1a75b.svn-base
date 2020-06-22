package com.rakuten.r0601.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.form.F060105;
import com.rakuten.r0601.form.MeisaiList;
import com.rakuten.util.Utility;

public class A06010506Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String fileName = null;
	F060105 f060105 = null;

	protected void exec() throws Exception {
		HSSFRow row = null;
		HSSFSheet sheet = null;
		HSSFCell cell = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String basePath = request.getSession().getServletContext()
				.getRealPath("/");
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(basePath
				+ "/WEB-INF/classes/baoguandanmuban.xls"));
		sheet = wb.getSheetAt(0);

		row = sheet.getRow(0);
		cell = row.getCell(0);
		cell.setCellValue(f060105.getWayBillNo());

		row = sheet.getRow(2);
		cell = row.getCell(1);
		cell.setCellValue(f060105.getWeight());

		row = sheet.getRow(3);
		cell = row.getCell(1);
		cell.setCellValue(f060105.getDimensions());

		row = sheet.getRow(4);
		cell = row.getCell(1);
		cell.setCellValue(Utility.formatData4(f060105.getDateOfExportation()));

		row = sheet.getRow(7);
		cell = row.getCell(0);
		cell.setCellValue(f060105.getRcompanyName());

		row = sheet.getRow(9);
		cell = row.getCell(0);
		cell.setCellValue(f060105.getRaddress());

		row = sheet.getRow(10);
		cell = row.getCell(0);
		cell.setCellValue("CITY:" + f060105.getRcity());

		row = sheet.getRow(11);
		cell = row.getCell(0);
		cell.setCellValue("PROVINCE:" + f060105.getRprovince());

		row = sheet.getRow(12);
		cell = row.getCell(0);
		cell.setCellValue("COUNTRY:" + f060105.getRcountry());

		row = sheet.getRow(13);
		cell = row.getCell(0);
		cell.setCellValue("POSTCODE:" + f060105.getRpostcode());

		row = sheet.getRow(14);
		cell = row.getCell(0);
		cell.setCellValue("CONTACT NAME:" + f060105.getRcontactName());

		row = sheet.getRow(15);
		cell = row.getCell(0);
		cell.setCellValue("TELEPHONE:" + f060105.getRtelephone());

		row = sheet.getRow(7);
		cell = row.getCell(2);
		cell.setCellValue(f060105.getScompanyName());

		row = sheet.getRow(9);
		cell = row.getCell(2);
		cell.setCellValue(f060105.getSaddress());

		row = sheet.getRow(10);
		cell = row.getCell(2);
		cell.setCellValue("CITY:" + f060105.getScity());

		row = sheet.getRow(11);
		cell = row.getCell(2);
		cell.setCellValue("PROVINCE:" + f060105.getSprovince());

		row = sheet.getRow(12);
		cell = row.getCell(2);
		cell.setCellValue("COUNTRY:" + f060105.getScountry());

		row = sheet.getRow(13);
		cell = row.getCell(2);
		cell.setCellValue("POSTCODE:" + f060105.getSpostcode());

		row = sheet.getRow(14);
		cell = row.getCell(2);
		cell.setCellValue("CONTACT NAME:" + f060105.getScontactName());

		row = sheet.getRow(15);
		cell = row.getCell(2);
		cell.setCellValue("TELEPHONE:" + f060105.getStelephone());

		List<MeisaiList> meisaiList = f060105.getMeisaiList();
		int size = meisaiList.size();
		insertRow(wb, sheet, 18, size - 1);
		int i = 0;
		for (MeisaiList meisai : meisaiList) {
			row = sheet.getRow(18 + i);
			cell = row.getCell(0);
			cell.setCellValue(meisai.getDescription());

			cell = row.getCell(1);
			cell.setCellValue(meisai.getPcs());

			cell = row.getCell(2);
			cell.setCellValue(meisai.getOrigin());

			cell = row.getCell(3);
			cell.setCellValue("US$" + meisai.getUnitValue());

			cell = row.getCell(4);
			cell.setCellValue("US$" + meisai.getTotalValue());
			i++;
		}

		row = sheet.getRow(18 + size);
		cell = row.getCell(1);
		cell.setCellValue(f060105.getTotalPcs());

		cell = row.getCell(4);
		cell.setCellValue(f060105.getTotalValue());
		fileName = f060105.getWayBillNo()
				+ "Date"
				+ Utility.formatData3(f060105.getDateOfExportation().replace(
						"-", "")) + ".xls";
		FileOutputStream fileOut = new FileOutputStream("c:/temp/" + fileName);
		// 指定文件名
		wb.write(fileOut);
		// 输出到文件
		fileOut.close();
	}

	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("c:/temp/" + fileName));
	}

	public static void insertRow(HSSFWorkbook wb, HSSFSheet sheet, int starRow,
			int rows) {

		sheet.shiftRows(starRow + 1, sheet.getLastRowNum(), rows, true, false);
		// Parameters:
		// startRow - the row to start shifting
		// endRow - the row to end shifting
		// n - the number of rows to shift
		// copyRowHeight - whether to copy the row height during the shift
		// resetOriginalRowHeight - whether to set the original row's height to
		// the default

		starRow = starRow - 1;

		for (int i = 0; i < rows; i++) {

			HSSFRow sourceRow = null;
			HSSFRow targetRow = null;
			HSSFCell sourceCell = null;
			HSSFCell targetCell = null;
			short m;

			starRow = starRow + 1;
			sourceRow = sheet.getRow(starRow);
			targetRow = sheet.createRow(starRow + 1);
			targetRow.setHeight(sourceRow.getHeight());

			for (m = sourceRow.getFirstCellNum(); m < sourceRow
					.getLastCellNum(); m++) {

				sourceCell = sourceRow.getCell(m);
				targetCell = targetRow.createCell(m);

				targetCell.setCellStyle(sourceCell.getCellStyle());
				targetCell.setCellType(sourceCell.getCellType());

			}
		}

	}

	protected void init() {
		setTitle("V060105:报关单");

	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {

	}

	public F060105 getF060105() {
		return f060105;
	}

	public void setF060105(F060105 f060105) {
		this.f060105 = f060105;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
