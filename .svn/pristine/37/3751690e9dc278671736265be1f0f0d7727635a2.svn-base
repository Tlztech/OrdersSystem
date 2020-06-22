package com.rakuten.r1601.action;

import java.io.File;
import java.io.FileInputStream;
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
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.CheckCommodityAp;
import com.rakuten.r0601.ap.GetCommodityPicUrlAp;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A16010108Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private File inputPath = null;
	private String successFlg = null;
	private String fileName = null;

	protected void exec() throws Exception {
		List<String[]> shoriList = new ArrayList<String[]>();
		shoriList.add(new String[] { "Item Code", "Seller Code", "Option Code", "Price", "Qty", "Option Kind" });
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT commodity_id,detail_no,del_flg from tbl00012";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				String sellerCode = rs.getString("commodity_id");
				String optionCode = rs.getString("detail_no");
				String qty = "1".equals(rs.getString("del_flg")) ? "0" : "300";
				String optionKind = "i";
				shoriList.add(new String[] { "", sellerCode, optionCode, "", qty, optionKind });
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

		Utility.writeCsvFile(shoriList, "c:\\temp\\qoo10_quantity.csv");
		fileName = "qoo10_quantity.csv";

	}

	protected void init() {
		setTitle("V160101:小工具");

	}

	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("c:/temp/" + fileName));
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public String getSuccessFlg() {
		return successFlg;
	}

	public void setSuccessFlg(String successFlg) {
		this.successFlg = successFlg;
	}

	public File getInputPath() {
		return inputPath;
	}

	public void setInputPath(File inputPath) {
		this.inputPath = inputPath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
