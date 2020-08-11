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

public class A16010109Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private File inputPath = null;
	private String successFlg = null;
	private String fileName = null;

	protected void exec() throws Exception {
		List<String[]> shoriList = Utility.readCsvFile(inputPath, false);
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";
		String condition = "";
		try {
			
			for(int i=0; i<shoriList.size(); i++){
				condition+="'"+shoriList.get(i)[0]+"'";
                if(shoriList.size()-1 != i){
                	condition+=",";
                }
            }
			
			conn = JdbcConnection.getConnection();
			sql = "delete from tbl00027 where chumonbango in (" + condition + ")";
			ps = conn.prepareStatement(sql);
			int count1 = ps.executeUpdate();

			sql = "delete from common_order_tbl where chumonbango in (" + condition + ")";
			ps = conn.prepareStatement(sql);
			int count2 = ps.executeUpdate();

			sql = "delete from common_order_detail_tbl where juchubango in (" + condition + ")";
			ps = conn.prepareStatement(sql);
			int count3 = ps.executeUpdate();

			if (count1 > 0  && count2 > 0 && count3 > 0) {
				addError(null, "操作成功");
				conn.commit();
			} else {
				addError(null, "操作失败");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
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
