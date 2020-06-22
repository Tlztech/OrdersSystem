package com.rakuten.r1401.action;

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

public class A14010103Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	File xlsFile = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			conn = JdbcConnection.getConnection();
			List<String[]> haneiList = new ArrayList<String[]>();
			HSSFRow row = null;
			HSSFSheet sheet = null;
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
					xlsFile));
			for (int j = 0; j < 7; j++) {

				sheet = workbook.getSheetAt(j);
				System.out.println("sheet" + j);
				int i = 1;
				while (true) {
					row = sheet.getRow(i);
					System.out.println("row" + i);
					if (row == null) {
						break;
					}
					if (Utility.isEmptyString(row.getCell(0)
							.getStringCellValue())) {
						break;
					}
					String data1 = "";
					String data2 = "";
					String data3 = "";
					if (row.getCell(0) != null) {
						row.getCell(0).setCellType(HSSFCell.CELL_TYPE_STRING);
						data1 = row.getCell(0).getStringCellValue();
					}
					if (row.getCell(3) != null) {
						row.getCell(3).setCellType(HSSFCell.CELL_TYPE_STRING);
						data2 = row.getCell(3).getStringCellValue();
					}
					if (row.getCell(5) != null) {
						row.getCell(5).setCellType(HSSFCell.CELL_TYPE_STRING);
						data3 = row.getCell(5).getStringCellValue();
					}
					haneiList.add(new String[] { data1, data2, data3 });
					i++;
				}
			}

			sql = "UPDATE common_order_tbl SET biko = ?  WHERE chumonbango = ?";
			ps = conn.prepareStatement(sql);
			String sql2 = "select count(*) COUNT from tbl00027 where chumonbango = ?";
			String sql3 = "update tbl00027 set hasoyakusokubi = ?,update_time = ?,update_user = ? where chumonbango = ? ";
			String sql4 = "insert into tbl00027 values(?,?,?,?,?,?)";
			PreparedStatement ps2 = null;
			ps2 = conn.prepareStatement(sql2);
			PreparedStatement ps3 = null;
			ps3 = conn.prepareStatement(sql3);
			PreparedStatement ps4 = null;
			ps4 = conn.prepareStatement(sql4);
			for (String[] data : haneiList) {
				System.out.println(data[0]);
				ps.setString(1, data[2]);
				ps.setString(2, data[0]);

				ps.executeUpdate();
				int count = 0;

				ps2.setString(1, data[0]);
				rs = ps2.executeQuery();
				while (rs.next()) {
					count = rs.getInt("COUNT");
				}

				if (count > 0) {
					ps3.setString(1, data[1]);
					ps3.setString(2, Utility.getDateTime());
					ps3.setString(3, Utility.getUser());
					ps3.setString(4, data[0]);

					ps3.execute();
				} else {
					ps4.setString(1, data[0]);
					ps4.setString(2, data[1]);
					ps4.setString(3, Utility.getDateTime());
					ps4.setString(4, Utility.getUser());
					ps4.setString(5, Utility.getDateTime());
					ps4.setString(6, Utility.getUser());

					ps4.execute();
				}

			}

			// commit
			conn.commit();
			addError(null, "success");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public File getXlsFile() {
		return xlsFile;
	}

	public void setXlsFile(File xlsFile) {
		this.xlsFile = xlsFile;
	}

	protected void init() throws Exception {
		setTitle("V140101:发送预警");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

}
