package com.rakuten.r1505.action;

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
import com.rakuten.r1505.common.A150501Common;
import com.rakuten.r1505.form.F150501;
import com.rakuten.r1505.form.MeisaiList;
import com.rakuten.util.Utility;

public class A15050105Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	F150501 f150501 = null;
	String mode = null;
	private String fileName = null;

	protected void exec() throws Exception {
		A150501Common common = new A150501Common();
		common.keisan(f150501);

		HSSFSheet sheet = null;
		HSSFSheet sheet1 = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		String basePath = request.getSession().getServletContext()
				.getRealPath("/");
		HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(basePath
				+ "/WEB-INF/classes/qingqiushumuban.xls"));
		sheet = wb.getSheetAt(0);
		sheet1 = wb.getSheetAt(1);
		outExl(wb, sheet);
		outExl(wb, sheet1);
		fileName = f150501.getStartDay().replace("-", "") + "~"
				+ f150501.getEndDay().replace("-", "");
		if ("トレンド最前線".equals(f150501.getHiseikyusha())) {
			fileName = fileName + "trend777.xls";
		} else if ("勝意有限会社".equals(f150501.getHiseikyusha())) {
			fileName = fileName + "coverforefront.xls";
		}
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

	protected void init() throws Exception {
		setTitle("V150501:请求书");
	}

	public F150501 getF150501() {
		return f150501;
	}

	public void setF150501(F150501 f150501) {
		this.f150501 = f150501;
	}

	public void outExl(HSSFWorkbook wb, HSSFSheet sheet) throws Exception {
		HSSFRow row = null;
		HSSFCell cell = null;
		row = sheet.getRow(1);
		cell = row.getCell(6);
		cell.setCellValue(Utility.formatData4(f150501.getCreateDay()));

		row = sheet.getRow(6);
		cell = row.getCell(0);
		cell.setCellValue(f150501.getHiseikyusha());

		row = sheet.getRow(12);
		cell = row.getCell(1);
		cell.setCellValue(Utility.numberFormat(f150501.getSeikyukingaku()));

		row = sheet.getRow(7);
		cell = row.getCell(5);
		cell.setCellValue(f150501.getSeikyusha1());

		row = sheet.getRow(8);
		cell = row.getCell(6);
		cell.setCellValue(f150501.getSeikyusha2());

		row = sheet.getRow(9);
		cell = row.getCell(5);
		cell.setCellValue(f150501.getSeikyusha3());

		row = sheet.getRow(10);
		cell = row.getCell(6);
		cell.setCellValue(f150501.getSeikyusha4());

		row = sheet.getRow(11);
		cell = row.getCell(6);
		cell.setCellValue(f150501.getSeikyusha5());

		row = sheet.getRow(12);
		cell = row.getCell(6);
		cell.setCellValue(f150501.getSeikyusha6());

		int k = 20;
		if (!Utility.isEmptyString(f150501.getMerubinkingaku())) {
			insertRow(wb, sheet, k, 1);
			row = sheet.getRow(k++);
			cell = row.getCell(1);
			cell.setCellValue("メール便代理発送料金");

			cell = row.getCell(2);
			cell.setCellValue(Utility.numberFormatNoen(f150501
					.getMerubintanka()));

			cell = row.getCell(3);
			cell.setCellValue(f150501.getMerubinkosu());

			cell = row.getCell(4);
			cell.setCellValue(Utility.numberFormatNoen(f150501
					.getMerubinkingaku()));
		}

		if (!Utility.isEmptyString(f150501.getTakyubinkingaku())) {
			insertRow(wb, sheet, k, 1);
			row = sheet.getRow(k++);
			cell = row.getCell(1);
			cell.setCellValue("宅急便代理発送料金");

			cell = row.getCell(2);
			cell.setCellValue(Utility.numberFormatNoen(f150501
					.getTakyubintanka()));

			cell = row.getCell(3);
			cell.setCellValue(f150501.getTakyubinkosu());

			cell = row.getCell(4);
			cell.setCellValue(Utility.numberFormatNoen(f150501
					.getTakyubinkingaku()));
		}
		if (!Utility.isEmptyString(f150501.getTesuryokingaku())) {
			insertRow(wb, sheet, k, 1);
			row = sheet.getRow(k++);
			cell = row.getCell(1);
			cell.setCellValue("促進販売手数料");

			cell = row.getCell(2);
			cell.setCellValue(Utility.numberFormatNoen(f150501
					.getTesuryotanka()));

			cell = row.getCell(3);
			cell.setCellValue(f150501.getTesuryokosu());

			cell = row.getCell(4);
			cell.setCellValue(Utility.numberFormatNoen(f150501
					.getTesuryokingaku()));
		}
		if (!Utility.isEmptyString(f150501.getDaibikikingaku())) {
			insertRow(wb, sheet, k, 1);
			row = sheet.getRow(k++);
			cell = row.getCell(1);
			cell.setCellValue("代金引換預かり金");

			cell = row.getCell(2);
			cell.setCellValue(Utility.numberFormatNoen(f150501
					.getDaibikitanka()));

			cell = row.getCell(3);
			cell.setCellValue(f150501.getDaibikikosu());

			cell = row.getCell(4);
			cell.setCellValue(Utility.numberFormatNoen(f150501
					.getDaibikikingaku()));
		}
		if (!Utility.isEmptyString(f150501.getHimoku1())) {
			insertRow(wb, sheet, k, 1);
			row = sheet.getRow(k++);
			cell = row.getCell(1);
			cell.setCellValue(f150501.getHimoku1());

			cell = row.getCell(2);
			cell.setCellValue(Utility.numberFormatNoen(f150501.getTanka1()));

			cell = row.getCell(3);
			cell.setCellValue(f150501.getKosu1());

			cell = row.getCell(4);
			cell.setCellValue(Utility.numberFormatNoen(f150501.getKingaku1()));
		}

		if (!Utility.isEmptyString(f150501.getHimoku2())) {
			insertRow(wb, sheet, k, 1);
			row = sheet.getRow(k++);
			cell = row.getCell(1);
			cell.setCellValue(f150501.getHimoku2());

			cell = row.getCell(2);
			cell.setCellValue(Utility.numberFormatNoen(f150501.getTanka2()));

			cell = row.getCell(3);
			cell.setCellValue(f150501.getKosu2());

			cell = row.getCell(4);
			cell.setCellValue(Utility.numberFormatNoen(f150501.getKingaku2()));
		}

		if (!Utility.isEmptyString(f150501.getHimoku3())) {
			insertRow(wb, sheet, k, 1);
			row = sheet.getRow(k++);
			cell = row.getCell(1);
			cell.setCellValue(f150501.getHimoku3());

			cell = row.getCell(2);
			cell.setCellValue(Utility.numberFormatNoen(f150501.getTanka3()));

			cell = row.getCell(3);
			cell.setCellValue(f150501.getKosu3());

			cell = row.getCell(4);
			cell.setCellValue(Utility.numberFormatNoen(f150501.getKingaku3()));
		}

		if (!Utility.isEmptyString(f150501.getHimoku4())) {
			insertRow(wb, sheet, k, 1);
			row = sheet.getRow(k++);
			cell = row.getCell(1);
			cell.setCellValue(f150501.getHimoku4());

			cell = row.getCell(2);
			cell.setCellValue(Utility.numberFormatNoen(f150501.getTanka4()));

			cell = row.getCell(3);
			cell.setCellValue(f150501.getKosu4());

			cell = row.getCell(4);
			cell.setCellValue(Utility.numberFormatNoen(f150501.getKingaku4()));
		}

		List<MeisaiList> meisaiList = f150501.getMeisaiList();
		int size = meisaiList.size();
		insertRow(wb, sheet, k, size - 1);
		int i = 0;
		for (MeisaiList meisai : meisaiList) {
			row = sheet.getRow(k + i);
			cell = row.getCell(0);
			cell.setCellValue(meisai.getJuchubango());

			cell = row.getCell(1);
			cell.setCellValue(meisai.getShohinbango());

			cell = row.getCell(2);
			cell.setCellValue(Utility.numberFormatNoen(meisai.getTanka()));

			cell = row.getCell(3);
			cell.setCellValue(meisai.getKosu());

			cell = row.getCell(4);
			cell.setCellValue(Utility.numberFormatNoen(meisai.getKingaku()));
			i++;
		}

		row = sheet.getRow(k + size);
		cell = row.getCell(4);
		cell.setCellValue(Utility.numberFormatNoen(f150501.getKokei()));

		row = sheet.getRow(k + size + 1);
		cell = row.getCell(4);
		cell.setCellValue("0");

		row = sheet.getRow(k + size + 2);
		cell = row.getCell(4);
		cell.setCellValue("0");

		row = sheet.getRow(k + size + 3);
		cell = row.getCell(4);
		cell.setCellValue(Utility.numberFormatNoen(f150501.getGokei()));
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
