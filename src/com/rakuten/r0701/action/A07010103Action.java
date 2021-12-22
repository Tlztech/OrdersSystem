package com.rakuten.r0701.action;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.CheckCommodityAp;
import com.rakuten.r0601.ap.GetCommodityPicUrlAp;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A07010103Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private File inputPath2 = null;
	private String successFlg = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		try {
			Map<String,Object> map =  ActionContext.getContext().getSession();
			int companyId;
			if (null == map.get("comp")) {
				companyId = -1;
			} else {
				companyId = (int)map.get("comp");
			}
			conn = JdbcConnection.getConnection();
			HSSFRow row = null;
			HSSFSheet sheet = null;
			HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
					inputPath2));
			sheet = workbook.getSheetAt(0);

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

			String shubetuId = Utility.getShoribango();
//			sql = "SELECT MAX(SHUBETU_ID) SHUBETU_ID FROM TBL00015";
//			ps = conn.prepareStatement(sql);
//			rs = ps.executeQuery();
//			while (rs.next()) {
//				shubetuId = String.valueOf(Long.valueOf(rs
//						.getString("SHUBETU_ID")) + 1);
//			}

			int i = 1;
			while (true) {
				row = sheet.getRow(i);
				if (row == null) {
					break;
				}
				if (Utility.isEmptyString(row.getCell(0).getStringCellValue())) {
					break;
				}
				String commodityId = row.getCell(0).getStringCellValue();

				String chineseName = row.getCell(3).getStringCellValue();

				String url = row.getCell(4).getStringCellValue();

				row.getCell(12).setCellType(HSSFCell.CELL_TYPE_NUMERIC);
				int kosu = 0;
				try {
					kosu = Double
							.valueOf(row.getCell(12).getNumericCellValue())
							.intValue();
				} catch (Exception e) {
					kosu = 0;
				}
				if (kosu > 0) {
					String commId = "";
					String detailNo = "";

					if (commodityId.indexOf("-") > 0) {
						commId = commodityId.substring(0,
								commodityId.indexOf("-"));
						detailNo = commodityId.substring(commodityId
								.indexOf("-"));
					} else {
						commId = commodityId;
						detailNo = "";
					}
					CheckCommodityAp checkCommodityAp = new CheckCommodityAp();
					if (companyId == 0 || companyId == 1) {
						if (!checkCommodityAp.execute(commId, detailNo)) {
							addError(null, "商品编号" + commodityId + "不存在！");
						}
					} else {
						if (!checkCommodityAp.execute(commId, detailNo, companyId)) {
							addError(null, "商品编号" + commodityId + "不存在！");
						}
					}

					sql = "UPDATE TBL00011 SET CHINESE_NAME = ? , COMMODITY_URL = ? WHERE COMMODITY_ID = ?";
					ps = conn.prepareStatement(sql);

					ps.setString(1, chineseName);
					ps.setString(2, url);
					ps.setString(3, commId);
					ps.executeUpdate();

					GetCommodityPicUrlAp getCommodityPicUrlAp = new GetCommodityPicUrlAp();

					String picurl = getCommodityPicUrlAp.execute(commId,
							detailNo);

					sql = "INSERT INTO TBL00015 VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);

					ps.setString(1, shubetuId);
					ps.setString(2, commodityId);
					ps.setString(3, "其他");
					ps.setString(4, chineseName);
					ps.setString(5, url);
					ps.setString(6, "");
					ps.setString(7, String.valueOf(kosu));
					ps.setString(8, "0");
					ps.setString(9, "0");
					ps.setString(10, "0");
					ps.setString(11, "");
					ps.setString(12, picurl);
					ps.setString(13, df.format(new Date()));
					ps.setString(14, "");
					ps.execute();
				}
				i++;
			}
			if (getActionErrors().isEmpty()) {
				// commit
				conn.commit();
				successFlg = "1";
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
		setTitle("V070101:进货单生成");

	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public File getInputPath2() {
		return inputPath2;
	}

	public void setInputPath2(File inputPath2) {
		this.inputPath2 = inputPath2;
	}

	public String getSuccessFlg() {
		return successFlg;
	}

	public void setSuccessFlg(String successFlg) {
		this.successFlg = successFlg;
	}

}
