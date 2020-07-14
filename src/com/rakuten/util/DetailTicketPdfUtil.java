package com.rakuten.util;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class DetailTicketPdfUtil {

	private final List<PdfReader> page = new ArrayList<PdfReader>();
	private final BaseFont bfJpn;
	
	public DetailTicketPdfUtil() throws Exception {
		bfJpn = BaseFont.createFont("HeiseiKakuGo-W5", "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED);
	}
	
	public void generatePDF(String targetPdfPath) throws Exception {
		Document document = new Document(PageSize.A5);
        PdfCopy copy = new PdfCopy(document, new FileOutputStream(targetPdfPath));
        document.open();
        for (int k = 0; k < page.size(); k++) {
            PdfReader pdfReader = page.get(k);
            document.newPage();
            copy.addDocument(pdfReader);
        }
        copy.close();
	}
	
	public void generateOnePageWithTemplate(String tempPdfPath, Map<String, Object> templateDataMap)
			throws Exception {
		PdfReader pdfReader = new PdfReader(tempPdfPath);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		PdfStamper stamper = new PdfStamper(pdfReader, bos);
		AcroFields form = stamper.getAcroFields();
		//BaseFont bfJpn = BaseFont.createFont("HeiseiKakuGo-W5", "UniJIS-UCS2-H", BaseFont.NOT_EMBEDDED);
		// form.addSubstitutionFont(bfJpn);
		Iterator<String> iterator = form.getFields().keySet().iterator();
		while (iterator.hasNext()) {
			String fieldName = iterator.next();
			if ("title".equals(fieldName)) {
				setTitle(form, fieldName, (String) templateDataMap.get(fieldName));
			} else if (("buyeraddr".equals(fieldName))) {
				setText(form, fieldName, (String) templateDataMap.get(fieldName));
			} else if (("buyername".equals(fieldName))) {
				setText(form, fieldName, (String) templateDataMap.get(fieldName));
			} else if (("saleeraddr".equals(fieldName))) {
				setText(form, fieldName, (String) templateDataMap.get(fieldName));
			} else if (("content".equals(fieldName))) {
				setText(form, fieldName, (String) templateDataMap.get(fieldName));
			} else if (("receiveaddr".equals(fieldName))) {
				setText(form, fieldName, (String) templateDataMap.get(fieldName));
			} else if (("orderinfo".equals(fieldName))) {
				setText(form, fieldName, (String) templateDataMap.get(fieldName));
			} else if (("payway".equals(fieldName))) {
				setText(form, fieldName, (String) templateDataMap.get(fieldName));
			} else if ("detailtable".equals(fieldName)) {
				int pageNo = form.getFieldPositions(fieldName).get(0).page;
				PdfContentByte pcb = stamper.getOverContent(pageNo);
				List<List<String>> lists = (List<List<String>>) templateDataMap.get(fieldName);
				setDetailTable(form, pcb, fieldName, lists);
			} else if ("sumtable".equals(fieldName)) {
				int pageNo = form.getFieldPositions(fieldName).get(0).page;
				PdfContentByte pcb = stamper.getOverContent(pageNo);
				List<List<String>> lists = (List<List<String>>) templateDataMap.get(fieldName);
				setSumTable(form, pcb, fieldName, lists);
			} else if ("barcode".equals(fieldName)) {
				int pageNo = form.getFieldPositions(fieldName).get(0).page;
				PdfContentByte pcb = stamper.getOverContent(pageNo);
				setBarcode(form, pcb, fieldName, (String) templateDataMap.get(fieldName));
			}
		}
		stamper.setFormFlattening(true);
		stamper.close();
		page.add(new PdfReader(bos.toByteArray()));
	}
	
	private void setTitle(AcroFields form, String key, String value) throws Exception {
		form.setField(key, value);
	}
	
	private void setText(AcroFields form, String key, String value) throws Exception {
		form.setField(key, value);
	}
	
	private void setDetailTable(AcroFields form, PdfContentByte pcb, String key, List<List<String>> lists) throws Exception {
		Rectangle signRect = form.getFieldPositions(key).get(0).position;
		int column = lists.get(0).size();
		PdfPTable table = new PdfPTable(column);
		float totalWidth = signRect.getRight() - signRect.getLeft() - 1;
		int size = lists.get(0).size();
		float width[] = new float[size];
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				width[i] = 375f;
			} else if (i == 1) {
				width[i] = 35f;
			} else {
				width[i] = (totalWidth - 410) / (size - 2);
			}
		}
		table.setTotalWidth(width);
		table.setLockedWidth(true);
		table.setKeepTogether(true);
		table.setSplitLate(false);
		table.setSplitRows(true);
		Font fontBody = new Font(bfJpn, 8, 0);
		Font fontHeader = new Font(bfJpn, 8, Font.BOLD);
		boolean isHeader = true;
		boolean isBodyFirstContent = true;
		for (List<String> list : lists) {
			for (String tableEl : list) {
				if (isHeader) {
					Paragraph paragraph = new Paragraph(tableEl, fontHeader);
					table.addCell(getHeaderCell(paragraph));
				} else {
					if (isBodyFirstContent) {
						Paragraph paragraph = new Paragraph(tableEl, fontBody);
						table.addCell(getBodyCell(paragraph, Element.ALIGN_LEFT));
						isBodyFirstContent = false;
					} else {
						Paragraph paragraph = new Paragraph(tableEl, fontBody);
						table.addCell(getBodyCell(paragraph, Element.ALIGN_RIGHT));
					}
				}
			}
			isBodyFirstContent = true;
			isHeader = false;
		}
		table.writeSelectedRows(0, -1, signRect.getLeft(), signRect.getTop(), pcb);
	}
	
	private void setSumTable(AcroFields form, PdfContentByte pcb, String key, List<List<String>> lists) throws Exception {
		Rectangle signRect = form.getFieldPositions(key).get(0).position;
		int column = lists.get(0).size();
		PdfPTable table = new PdfPTable(column);
		float totalWidth = signRect.getRight() - signRect.getLeft() - 1;
		int size = lists.get(0).size();
		float width[] = new float[size];
		for (int i = 0; i < size; i++) {
			if (i == 0) {
				width[i] = 110f;
			} else if (i == 1) {
				width[i] = totalWidth - 110f;
			}
		}
		table.setTotalWidth(width);
		table.setLockedWidth(true);
		table.setKeepTogether(true);
		table.setSplitLate(false);
		table.setSplitRows(true);
		Font fontBody = new Font(bfJpn, 8, 0);
		boolean isFirstContent = true;
		for (List<String> list : lists) {
			for (String tableEl : list) {
				if (isFirstContent) {
					Paragraph paragraph = new Paragraph(tableEl, fontBody);
					table.addCell(getSumCell(paragraph, Element.ALIGN_LEFT, new BaseColor(175, 175, 175)));
					isFirstContent = false;
				} else {
					Paragraph paragraph = new Paragraph(tableEl, fontBody);
					table.addCell(getSumCell(paragraph, Element.ALIGN_RIGHT, BaseColor.WHITE));
				}
			}
			isFirstContent = true;
		}
		table.writeSelectedRows(0, -1, signRect.getLeft(), signRect.getTop(), pcb);
	}
	
	private void setBarcode(AcroFields form, PdfContentByte pcb, String key, String value) throws Exception {
		Rectangle signRect = form.getFieldPositions(key).get(0).position;
		Image image = getImageOfBarcode(pcb, value);
		image.scaleToFit(signRect.getWidth(), signRect.getHeight());
		float x = signRect.getLeft();
		float y = signRect.getBottom();
		image.setAbsolutePosition(x, y);
		pcb.addImage(image);
	}
	
	private PdfPCell getHeaderCell(Paragraph paragraph) {
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorderWidth(1);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(Element.ALIGN_LEFT);
		cell.setLeading(0, (float) 1.4);
		cell.setBackgroundColor(new BaseColor(175, 175, 175));
		return cell;
	}

	private PdfPCell getBodyCell(Paragraph paragraph, int hAlignment) {
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorderWidth(1);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(hAlignment);
		cell.setLeading(0, (float) 1.4);
		return cell;
	}

	private PdfPCell getSumCell(Paragraph paragraph, int hAlignment, BaseColor bgColor) {
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorderWidth(1);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		cell.setHorizontalAlignment(hAlignment);
		cell.setLeading(0, (float) 1.4);
		cell.setBackgroundColor(bgColor);
		return cell;
	}
	
	private Image getImageOfBarcode(PdfContentByte pcb, String value) {
		Barcode128 barcode128 = new Barcode128();
		barcode128.setCode(value);
		barcode128.setBarHeight(35);
		barcode128.setBaseline(10);
		barcode128.setStartStopText(false);
		barcode128.setExtended(true);
		barcode128.setSize(10);
		barcode128.setCodeType(Barcode128.CODE128);
		Image image = barcode128.createImageWithBarcode(pcb, null, null);
		return image;
	}
}
