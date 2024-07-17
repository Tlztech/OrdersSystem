package com.rakuten.r0302.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.platform.commons.util.StringUtils;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r0302.form.F030201;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A03020108Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	private F030201 f030201 = null;
	private File itemCsv = null;

	@Override
	protected void init() throws Exception {
		setTitle("V030201:库存列表");

	}

	@Override
	protected void isValidated() throws Exception {
		if(StringUtils.isBlank(f030201.getCharset())) {
			addError(null, "文件语言没选择");
		}
	}

	@Override
	protected void exec() throws Exception {
		if (itemCsv != null) {
			// 从CSV文件获取订单信息
			List<String[]> csvList = null;
			if("0".equals(f030201.getCharset())) {
				csvList = Utility.readCsvFileChn(itemCsv, true);
			} else if ("1".equals(f030201.getCharset())) {
				csvList = Utility.readCsvFileJpn(itemCsv, true);
			}

			Map<String, Object> map = ActionContext.getContext().getSession();
			int companyId;
			if (null == map.get("comp")) {
				companyId = -1;
			} else {
				companyId = (int) map.get("comp");
			}
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			String sql = null;
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = format.format(new Date());
			String commodityId;
			String detailNo;
			String site;
			String shop;
			int count = 0;

			if (csvList == null || csvList.isEmpty()) {
				addError(null, "没有商品数据");
				return;
			}

			if (companyId == 0 || companyId == 1) {
				if (StringUtils.isBlank(f030201.getSiteForGoods())) {
					addError(null, "请选择平台");
					return;
				} else if (StringUtils.isBlank(f030201.getShopForGoods())) {
					addError(null, "请选择店铺");
					return;
				} else {
					site = f030201.getSiteForGoods();
					shop = f030201.getShopForGoods();
				}
			} else {
				if (StringUtils.isBlank(f030201.getSiteForGoods())) {
					addError(null, "请选择平台");
					return;
				} else {
					site = f030201.getSiteForGoods();
					shop = "";
				}
			}

			if (csvList.get(0) == null || csvList.get(0).length < 1 || !csvList.get(0)[0].equals(site)) {
				addError(null, "选择平台与文件不一致");
				return;
			}

			try {
				conn = JdbcConnection.getConnection();
				for (String[] csvData : csvList) {
					try {
						if (csvData == null || csvData.length < 2 || StringUtils.isBlank(csvData[1])) {
							continue;
						} else {
							csvData[1] = csvData[1].replace(" ", "");
							commodityId = Utility.getCommodityId(csvData[1]);
							detailNo = Utility.getDetailN0(csvData[1]);
							count = 0;
							sql = "SELECT COUNT(*) COUNT FROM TBL00011 WHERE COMMODITY_ID = ? AND CATEGORY_ID = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, commodityId);
							ps.setString(2, "100001");
							rs = ps.executeQuery();
							while (rs.next()) {
								count = rs.getInt("COUNT");
							}
							if (count == 0) {
								sql = "INSERT INTO tbl00011(COMMODITY_ID,CATEGORY_ID,CHINESE_NAME,JAPANESE_NAME,SOURCE,RESP_PERSON,COMMODITY_URL,PIC_URL,REMARKS,DEL_FLG,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER,STATUS)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
								ps = conn.prepareStatement(sql);
								ps.setString(1, commodityId);
								ps.setString(2, "100001");
								ps.setString(3, StringUtils.isBlank(csvData[2]) ? "" : csvData[2]);
								ps.setString(4, StringUtils.isBlank(csvData[3]) ? "" : csvData[3]);
								ps.setString(5, "");
								ps.setString(6, "");
								ps.setString(7, "");
								ps.setString(8, "");
								ps.setString(9, "");
								ps.setString(10, "0");
								ps.setString(11, date);
								ps.setString(12, "kyo");
								ps.setString(13, date);
								ps.setString(14, "kyo");
								ps.setString(15, "00");
								ps.execute();

								sql = "INSERT INTO `rakuten`.`company_commodity_tbl` (`company_id`,`commodity_id`,`permission`) VALUES (?,?,?);";
								ps = conn.prepareStatement(sql);
								ps.setInt(1, companyId == 0 ? 1 : companyId);
								ps.setString(2, commodityId);
								ps.setInt(3, 3);
								ps.execute();
							}

							sql = "INSERT INTO TBL00012(COMMODITY_ID, DETAIL_NO, COMM_DESCRIBE, "
									+ "              PIC_URL, PRICE_IN, RE_PRICE, STOCK_SH, STOCK_JP, STOCK_HANDUP, "
									+ "              REMARKS, BAR_CODE, WEIGHT, DEL_FLG, CREATE_TIME, CREATE_USER, "
									+ "              UPDATE_TIME, UPDATE_USER, YOKONAME, "
									+ "              SHITAGANAME, SITE, SHOP)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
							ps = conn.prepareStatement(sql);
							ps.setString(1, commodityId);
							ps.setString(2, detailNo);
							ps.setString(3, "");
							ps.setString(4, "");
							ps.setString(5, null);
							ps.setString(6, "1");
							ps.setString(7, "0");
							ps.setString(8, "0");
							ps.setString(9, "0");
							ps.setString(10, null);
							ps.setString(11, null);
							ps.setString(12, "");
							ps.setString(13, "0");
							ps.setString(14, date);
							ps.setString(15, "A03020108");
							ps.setString(16, null);
							ps.setString(17, null);
							ps.setString(18, "");
							ps.setString(19, "");
							ps.setString(20, site);
							ps.setString(21, shop);
							ps.execute();

						}
					} catch (MySQLIntegrityConstraintViolationException e) {
						System.out.println(csvData[1] + "已存在，不再添加");
						continue;
					}
				}

				// commit
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				ps.close();
				conn.close();
			}
		} else {
			addError(null, "请选择csv文件");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F030201 getF030201() {
		return f030201;
	}

	public void setF030201(F030201 f030201) {
		this.f030201 = f030201;
	}

	/**
	 * @return the itemCsv
	 */
	public File getItemCsv() {
		return itemCsv;
	}

	/**
	 * @param itemCsv the itemCsv to set
	 */
	public void setItemCsv(File itemCsv) {
		this.itemCsv = itemCsv;
	}
}
