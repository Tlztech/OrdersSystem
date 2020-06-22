package com.rakuten.r0801.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0801.common.A0801Common;
import com.rakuten.r0801.form.F080201;
import com.rakuten.r0801.form.ShohinList;

public class A08010204Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F080201 f080201 = null;
	private String fileName = null;
	private A0801Common a0801Common = new A0801Common();

	protected void exec() throws Exception {

		List<ShohinList> infoList = a0801Common.getShohinList(f080201
				.getShumokubango());
		downXls(infoList);

	}

	protected void init() {
		setTitle("V080201:日本网站商品清单-照会");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("d:/temp/" + fileName));
	}

	private void downXls(List<ShohinList> infoList) throws IOException {

		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet1 = wb.createSheet("商品");

		sheet1.setDisplayGridlines(false);

		sheet1.setColumnWidth(0, 5000);
		sheet1.setColumnWidth(1, 4000);
		sheet1.setColumnWidth(2, 5000);
		sheet1.setColumnWidth(3, 8000);
		sheet1.setColumnWidth(4, 5000);
		sheet1.setColumnWidth(5, 5000);
		sheet1.setColumnWidth(6, 5000);
		sheet1.setColumnWidth(7, 6000);
		sheet1.setColumnWidth(8, 8000);
		sheet1.setColumnWidth(9, 8000);
		sheet1.setColumnWidth(10, 8000);
		sheet1.setColumnWidth(11, 7000);
		sheet1.setColumnWidth(12, 7000);
		sheet1.setColumnWidth(13, 7000);

		HSSFCellStyle style = wb.createCellStyle();
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
		style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);// 前景填充色
		style.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		style.setWrapText(true);

		HSSFCellStyle style1 = wb.createCellStyle();
		style1.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style1.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style1.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		style1.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		style1.setWrapText(true);

		HSSFCellStyle style_shinki = wb.createCellStyle();
		style_shinki.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style_shinki.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style_shinki.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style_shinki.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style_shinki.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		style_shinki.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		style_shinki.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
		style_shinki.setFillForegroundColor(HSSFColor.AQUA.index);// 前景填充色
		style_shinki.setWrapText(true);

		HSSFCellStyle style_koushin = wb.createCellStyle();
		style_koushin.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style_koushin.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style_koushin.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style_koushin.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style_koushin.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		style_koushin.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		style_koushin.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
		style_koushin.setFillForegroundColor(HSSFColor.TAN.index);// 前景填充色
		style_koushin.setWrapText(true);

		HSSFCellStyle style_sakujo = wb.createCellStyle();
		style_sakujo.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style_sakujo.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style_sakujo.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style_sakujo.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style_sakujo.setAlignment(CellStyle.ALIGN_CENTER);// 水平居中
		style_sakujo.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直居中
		style_sakujo.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);// 设置前景填充样式
		style_sakujo.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);// 前景填充色
		style_sakujo.setWrapText(true);

		HSSFRow row = sheet1.createRow(0);
		row.setHeight((short) 400);

		HSSFCell cell = row.createCell(0);
		cell.setCellStyle(style);
		cell.setCellValue("画像");

		cell = row.createCell(1);
		cell.setCellStyle(style);
		cell.setCellValue("メーカー品番");

		cell = row.createCell(2);
		cell.setCellStyle(style);
		cell.setCellValue("ブランド");

		cell = row.createCell(3);
		cell.setCellStyle(style);
		cell.setCellValue("リンク");

		cell = row.createCell(4);
		cell.setCellStyle(style);
		cell.setCellValue("taobaoステータス");

		cell = row.createCell(5);
		cell.setCellStyle(style);
		cell.setCellValue("商品ステータス");

		cell = row.createCell(6);
		cell.setCellStyle(style);
		cell.setCellValue("価格");

		cell = row.createCell(7);
		cell.setCellStyle(style);
		cell.setCellValue("商品名");

		cell = row.createCell(8);
		cell.setCellStyle(style);
		cell.setCellValue("素材");

		cell = row.createCell(9);
		cell.setCellStyle(style);
		cell.setCellValue("カラー");

		cell = row.createCell(10);
		cell.setCellStyle(style);
		cell.setCellValue("サイズ");

		cell = row.createCell(11);
		cell.setCellStyle(style);
		cell.setCellValue("在庫");

		cell = row.createCell(12);
		cell.setCellStyle(style);
		cell.setCellValue("商品詳細");

		cell = row.createCell(13);
		cell.setCellStyle(style);
		cell.setCellValue("備考");

		for (int i = 0; i < infoList.size(); i++) {
			ShohinList info = infoList.get(i);
			String shohinStatus = info.getShouhinStatus();
			HSSFCellStyle style_Shosai = null;
			if ("00".equals(shohinStatus)) {
				style_Shosai = style1;
			} else if ("01".equals(shohinStatus)) {
				style_Shosai = style_shinki;
			} else if ("02".equals(shohinStatus)) {
				style_Shosai = style_koushin;
			} else if ("03".equals(shohinStatus)) {
				style_Shosai = style_sakujo;
			}

			row = sheet1.createRow(i + 1);
			row.setHeight((short) 3500);

			cell = row.createCell(0);
			cell.setCellStyle(style_Shosai);

			BufferedImage bufferImg = null;
			// 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
			try {
				bufferImg = ImageIO.read(new File("d:/temp/"
						+ f080201.getShumokubango() + "/" + info.getMekabango()
						+ "/", info.getMekabango() + "_1.jpg"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (bufferImg != null) {
				ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
				ImageIO.write(bufferImg, "jpg", byteArrayOut);
				HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();
				HSSFClientAnchor anchor = new HSSFClientAnchor(0, 10, 0, 235,
						(short) 0, i + 1, (short) 1, i + 1);

				// 插入图片
				patriarch.createPicture(anchor, wb.addPicture(
						byteArrayOut.toByteArray(),
						HSSFWorkbook.PICTURE_TYPE_JPEG));
			}

			cell = row.createCell(1);
			cell.setCellStyle(style_Shosai);
			cell.setCellValue(info.getMekabango());

			cell = row.createCell(2);
			cell.setCellStyle(style_Shosai);
			cell.setCellValue(info.getBurando());

			cell = row.createCell(3);
			cell.setCellStyle(style_Shosai);
			cell.setCellValue(info.getRinku());

			cell = row.createCell(4);
			cell.setCellStyle(style_Shosai);
			String taobaoStatus = info.getTaobaoStatus();
			if ("00".equals(taobaoStatus)) {
				cell.setCellValue("未上架");
			} else if ("01".equals(taobaoStatus)) {
				cell.setCellValue("已上架");
			}

			cell = row.createCell(5);
			cell.setCellStyle(style_Shosai);

			if ("00".equals(shohinStatus)) {
				cell.setCellValue("通常");
			} else if ("01".equals(shohinStatus)) {
				cell.setCellValue("新規");
			} else if ("02".equals(shohinStatus)) {
				cell.setCellValue("更新あり");
			} else if ("03".equals(shohinStatus)) {
				cell.setCellValue("削除");
			}

			cell = row.createCell(6);
			cell.setCellStyle(style_Shosai);
			cell.setCellValue(info.getKakaku());

			cell = row.createCell(7);
			cell.setCellStyle(style_Shosai);
			cell.setCellValue(info.getShouhinmei());

			cell = row.createCell(8);
			cell.setCellStyle(style_Shosai);
			cell.setCellValue(info.getSozai());

			cell = row.createCell(9);
			cell.setCellStyle(style_Shosai);
			cell.setCellValue(info.getKara());

			cell = row.createCell(10);
			cell.setCellStyle(style_Shosai);
			cell.setCellValue(info.getSaizu());

			cell = row.createCell(11);
			cell.setCellStyle(style_Shosai);
			cell.setCellValue(info.getZaiko());

			cell = row.createCell(12);
			cell.setCellStyle(style_Shosai);
			cell.setCellValue(info.getShouhinshousai());

			cell = row.createCell(13);
			cell.setCellStyle(style_Shosai);
			cell.setCellValue(info.getBiko());

		}
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		fileName = "VIVI" + df.format(new Date()) + ".xls";
		FileOutputStream fileOut = new FileOutputStream("d:/temp/" + fileName);
		// 指定文件名
		wb.write(fileOut);
		// 输出到文件
		fileOut.close();
	}

	public F080201 getF080201() {
		return f080201;
	}

	public void setF080201(F080201 f080201) {
		this.f080201 = f080201;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
