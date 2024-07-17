package com.rakuten.r0303.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0303.form.F030301;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A03030103Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F030301 f030301 = null;
	private File csvFile = null;
	
	@Override
	protected void init() throws Exception {
		setTitle("V030301:添加损益");
	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";
		List<String[]> stockList = Utility.readCsvFileJpn(csvFile,false);
		try {
			conn = JdbcConnection.getConnection();
			sql = "UPDATE TBL00012 SET STOCK_JP = STOCK_JP + ? , UPDATE_TIME = ? , UPDATE_USER = ? , UPDATEQUANTITY_FLG =TRUE WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
			ps = conn.prepareStatement(sql);
			for(int i = 0;i<stockList.size();i++) {
				ps.setDouble(1,Double.valueOf(stockList.get(i)[1]));
				ps.setString(2, Utility.getDateTime());
				ps.setString(3, "A03030103");
				ps.setString(4, Utility.getCommodityId(stockList.get(i)[0]));
				ps.setString(5, Utility.getDetailN0(stockList.get(i)[0]));
				ps.addBatch();
			}
			ps.executeBatch();
			conn.commit();
			addError(null, "在庫更新しました");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			addError(null, "在庫ファイルにエラーがあり、在庫数量の更新に失敗しました");
//			throw e;
		} finally {
			conn.close();
		}

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}
	
	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("c:/temp/" + csvFile));
	}

	/**
	 * @return the f030301
	 */
	public F030301 getF030301() {
		return f030301;
	}

	/**
	 * @param f030301 the f030301 to set
	 */
	public void setF030301(F030301 f030301) {
		this.f030301 = f030301;
	}

	/**
	 * @return the csvFile
	 */
	public File getCsvFile() {
		return csvFile;
	}

	/**
	 * @param csvFile the csvFile to set
	 */
	public void setCsvFile(File csvFile) {
		this.csvFile = csvFile;
	}

}
