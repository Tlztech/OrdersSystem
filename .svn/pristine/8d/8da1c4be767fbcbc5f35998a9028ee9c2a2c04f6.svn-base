package com.rakuten.r1101.action;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A11010106Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private File uploadFile = null;

	protected void exec() throws Exception {
		HSSFRow row = null;
		HSSFSheet sheet = null;
		HSSFWorkbook workbook = new HSSFWorkbook(
				new FileInputStream(uploadFile));
		sheet = workbook.getSheetAt(0);
		List<String[]> dataList = new ArrayList<String[]>();
		int i = 1;
		while (true) {
			row = sheet.getRow(i);
			if (row == null) {
				break;
			}
			if (row.getCell(0) == null) {
				break;
			}
			if (Utility.isEmptyString(row.getCell(0).getStringCellValue())) {
				break;
			}

			row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
			row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
			row.getCell(2).setCellType(HSSFCell.CELL_TYPE_STRING);
			row.getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);

			String shurui = row.getCell(0).getStringCellValue();
			String cnStr = row.getCell(1).getStringCellValue();
			String jpStr = row.getCell(2).getStringCellValue();
			String subid = row.getCell(3).getStringCellValue();

			String[] data = new String[] { shurui, cnStr, jpStr, subid };
			if (!Utility.isEmptyString(jpStr)) {
				dataList.add(data);
			}

			i++;
		}

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			conn = JdbcConnection.getConnection();

			for (String[] data : dataList) {
				sql = "SELECT COUNT(*) COUNT FROM dic_tbl WHERE ID= ? AND C_NAME=?";
				ps = conn.prepareStatement(sql);
				if ("ËØ²Ä".equals(data[0])) {
					ps.setString(1, "101");
				} else if ("ºá×ÝÖá£¨ÄÚÈÝ£©".equals(data[0])) {
					ps.setString(1, "102");
				} else if ("ÏêÏ¸ÐÅÏ¢£¨Ãû³Æ£©".equals(data[0])) {
					ps.setString(1, "103");
				} else if ("ÏêÏ¸ÐÅÏ¢£¨ÄÚÈÝ£©".equals(data[0])) {
					ps.setString(1, "104");
				} else if ("ºá×ÝÖá£¨Ãû³Æ£©".equals(data[0])) {
					ps.setString(1, "105");
				} else if ("³ßÂë".equals(data[0])) {
					ps.setString(1, "107");
				} else {
					throw new Exception("ÓÐ´íÎó" + data[0] + data[1] + data[2]
							+ data[3]);
				}
				ps.setString(2, data[1]);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					if (rs.getInt("COUNT") > 0) {
						addError(null, data[1] + "ÖØ¸´Ìí¼Ó");
						return;
					}
				}

				sql = "INSERT INTO dic_tbl VALUES(?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				if ("ËØ²Ä".equals(data[0])) {
					ps.setString(1, "101");
				} else if ("ºá×ÝÖá£¨ÄÚÈÝ£©".equals(data[0])) {
					ps.setString(1, "102");
				} else if ("ÏêÏ¸ÐÅÏ¢£¨Ãû³Æ£©".equals(data[0])) {
					ps.setString(1, "103");
				} else if ("ÏêÏ¸ÐÅÏ¢£¨ÄÚÈÝ£©".equals(data[0])) {
					ps.setString(1, "104");
				} else if ("ºá×ÝÖá£¨Ãû³Æ£©".equals(data[0])) {
					ps.setString(1, "105");
				} else if ("³ßÂë".equals(data[0])) {
					ps.setString(1, "107");
				} else {
					throw new Exception("ÓÐ´íÎó" + data[0] + data[1] + data[2]
							+ data[3]);
				}
				ps.setString(2, Utility.getShoribango());
				ps.setString(3, data[1]);
				ps.setString(4, data[2]);
				ps.setString(5, data[3]);

				ps.execute();
			}

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	protected void init() {
		setTitle("V110101:ÎÄ°¸×Öµä");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public File getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(File uploadFile) {
		this.uploadFile = uploadFile;
	}
}
