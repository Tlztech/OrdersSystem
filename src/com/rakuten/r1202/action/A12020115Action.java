package com.rakuten.r1202.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1202.common.A120201Common;
import com.rakuten.r1202.form.F120201;
import com.rakuten.r1202.form.F120201Detail;

public class A12020115Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F120201 f120201 = null;
	F120201Detail detail = null;
	A120201Common a120201Common = new A120201Common();
	private String fileName = null;

	protected void exec() throws Exception {
		List<F120201Detail> detailList = (List<F120201Detail>) getSessionAttribute("f120201DetailList");
		if ("0".equals(detail.getDetailDelFlg())) {
			for (int i = 0; i < detailList.size(); i++) {
				if (detailList.get(i).getShohinbango()
						.equals(detail.getShohinbango())) {
					detailList.set(i, detail);
				}
			}
			setSessionAttribute("f120201DetailList", detailList);
		}

		List<String[]> sozaiList = new ArrayList<String[]>();
		List<String[]> shosaimeishoList = new ArrayList<String[]>();
		List<String[]> shosainaiyoList = new ArrayList<String[]>();
		List<String[]> axismeiList = new ArrayList<String[]>();
		List<String[]> axisnaiyoList = new ArrayList<String[]>();
		List<String[]> sizeList = new ArrayList<String[]>();

		a120201Common.getHonyakuList(detailList, sozaiList, shosaimeishoList,
				shosainaiyoList, axismeiList, axisnaiyoList, sizeList);

		sozaiList = chofukuSakujo(sozaiList);
		shosaimeishoList = chofukuSakujo(shosaimeishoList);
		shosainaiyoList = chofukuSakujo(shosainaiyoList);
		axismeiList = chofukuSakujo(axismeiList);
		axisnaiyoList = chofukuSakujo(axisnaiyoList);
		sizeList = chofukuSakujo(sizeList);

		writeExcel(sozaiList, shosaimeishoList, shosainaiyoList, axismeiList,
				axisnaiyoList, sizeList);

	}

	private List<String[]> chofukuSakujo(List<String[]> shoriList) {
		List<String[]> resultList = new ArrayList<String[]>();

		for (String[] shoriStr : shoriList) {
			boolean ariFlg = false;
			for (String[] resultStr : resultList) {
				if (resultStr[0].equals(shoriStr[0])) {
					ariFlg = true;
					resultStr[1] += ("," + shoriStr[1]);
				}
			}
			if (!ariFlg) {
				resultList.add(new String[] { shoriStr[0], shoriStr[1] });
			}

		}

		return resultList;
	}

	private void writeExcel(List<String[]> sozaiList,
			List<String[]> shosaimeishoList, List<String[]> shosainaiyoList,
			List<String[]> axismeiList, List<String[]> axisnaiyoList,
			List<String[]> sizeList) throws Exception {
		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			HSSFSheet sheet1 = wb.createSheet("�ı�����");
			sheet1.setColumnWidth(0, 4000);
			sheet1.setColumnWidth(1, 7000);
			sheet1.setColumnWidth(2, 10000);
			sheet1.setColumnWidth(3, 4000);
			sheet1.setColumnWidth(4, 7000);

			HSSFCellStyle style = wb.createCellStyle();
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			// style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// ����ǰ�������ʽ
			// style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);//
			// ǰ�����ɫ

			HSSFCellStyle style1 = wb.createCellStyle();
			style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// ����ǰ�������ʽ
			style1.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// ǰ�����ɫ

			HSSFCellStyle style2 = wb.createCellStyle();
			style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// ����ǰ�������ʽ
			style2.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);// ǰ�����ɫ

			String[] record = { "����", "����", "����", "�ӻ���", "������Ʒ���" };
			HSSFRow row = null;
			HSSFCell cell = null;
			row = sheet1.createRow(0);
			row.setHeight((short) 400);
			for (int i = 0; i < record.length; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(style1);
				cell.setCellValue(record[i]);
			}

			// ����һ��
			int i = 1;
			row = sheet1.createRow(i++);
			row.setHeight((short) 400);
			for (int j = 0; j < sozaiList.size(); j++) {
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("�ز�");

				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(sozaiList.get(j)[0]);

				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue("");

				cell = row.createCell(3);
				cell.setCellStyle(style2);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue("");

				cell = row.createCell(4);
				cell.setCellStyle(style2);
				cell.setCellValue(sozaiList.get(j)[1]);

				row = sheet1.createRow(i++);
				row.setHeight((short) 400);
			}

			for (int j = 0; j < shosaimeishoList.size(); j++) {
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("��ϸ��Ϣ�����ƣ�");

				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(shosaimeishoList.get(j)[0]);

				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue("");

				cell = row.createCell(3);
				cell.setCellStyle(style2);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue("");

				cell = row.createCell(4);
				cell.setCellStyle(style2);
				cell.setCellValue(shosaimeishoList.get(j)[1]);

				row = sheet1.createRow(i++);
				row.setHeight((short) 400);
			}

			for (int j = 0; j < shosainaiyoList.size(); j++) {
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("��ϸ��Ϣ�����ݣ�");

				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(shosainaiyoList.get(j)[0]);

				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue("");

				cell = row.createCell(3);
				cell.setCellStyle(style2);
				cell.setCellValue("");

				cell = row.createCell(4);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(shosainaiyoList.get(j)[1]);

				row = sheet1.createRow(i++);
				row.setHeight((short) 400);
			}

			for (int j = 0; j < axismeiList.size(); j++) {
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("�����ᣨ���ƣ�");

				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(axismeiList.get(j)[0]);

				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue("");

				cell = row.createCell(3);
				cell.setCellStyle(style2);
				cell.setCellValue("");

				cell = row.createCell(4);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(axismeiList.get(j)[1]);

				row = sheet1.createRow(i++);
				row.setHeight((short) 400);
			}

			for (int j = 0; j < axisnaiyoList.size(); j++) {
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("�����ᣨ���ݣ�");

				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(axisnaiyoList.get(j)[0]);

				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue("");

				cell = row.createCell(3);
				cell.setCellStyle(style);
				cell.setCellValue("");

				cell = row.createCell(4);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellStyle(style2);
				cell.setCellValue(axisnaiyoList.get(j)[1]);

				row = sheet1.createRow(i++);
				row.setHeight((short) 400);
			}

			for (int j = 0; j < sizeList.size(); j++) {
				cell = row.createCell(0);
				cell.setCellStyle(style);
				cell.setCellValue("����");

				cell = row.createCell(1);
				cell.setCellStyle(style);
				cell.setCellValue(sizeList.get(j)[0]);

				cell = row.createCell(2);
				cell.setCellStyle(style);
				cell.setCellValue("");

				cell = row.createCell(3);
				cell.setCellStyle(style2);
				cell.setCellValue("");

				cell = row.createCell(4);
				cell.setCellStyle(style2);
				cell.setCellType(HSSFCell.CELL_TYPE_STRING);
				cell.setCellValue(sizeList.get(j)[1]);

				row = sheet1.createRow(i++);
				row.setHeight((short) 400);
			}

			DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
			fileName = "translate_" + df.format(new Date()) + ".xls";
			FileOutputStream fileOut = new FileOutputStream("c:/temp/"
					+ fileName);
			// ָ���ļ���
			wb.write(fileOut);
			// ������ļ�
			fileOut.close();
		} finally {
			// bufferedWrite.close();
		}

	}

	public InputStream getInputStream() throws FileNotFoundException {
		return new FileInputStream(new File("c:/temp/" + fileName));
	}

	protected void init() throws Exception {
		setTitle("V120201:�İ��༭:  "
				+ a120201Common.getShumokuname(f120201.getShumokuid()));
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the f120201
	 */
	public F120201 getF120201() {
		return f120201;
	}

	/**
	 * @param f120201
	 *            the f120201 to set
	 */
	public void setF120201(F120201 f120201) {
		this.f120201 = f120201;
	}

	/**
	 * @return the detail
	 */
	public F120201Detail getDetail() {
		return detail;
	}

	/**
	 * @param detail
	 *            the detail to set
	 */
	public void setDetail(F120201Detail detail) {
		this.detail = detail;
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

}
