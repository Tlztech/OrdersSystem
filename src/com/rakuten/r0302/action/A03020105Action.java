package com.rakuten.r0302.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.bean.ShohinBean;
import com.rakuten.common.bean.ShohinInfoBean;
import com.rakuten.common.bean.ShohinsentakushiBean;
import com.rakuten.r0302.ap.GetCommodityAllAp;
import com.rakuten.r0302.bean.CommodityDetail;
import com.rakuten.r0302.bean.GetCommodityApOutput;
import com.rakuten.r0302.form.F030201;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;
import com.rakuten.util.Utility;

public class A03020105Action extends BaseAction {

	private F030201 f030201 = null;
	private File itemCsv = null;
	private File itemCatCsv = null;
	private File selectCsv = null;
	private static final long serialVersionUID = 1L;

	protected void exec() throws Exception {
		
		List<ShohinBean> shohinList = new ArrayList<ShohinBean>();
		if("楽天".equals(f030201.getSite())) {
			shohinList = Utility.getShohinFromCsv(itemCsv,
					itemCatCsv, selectCsv);
		}else if("Yahoo".equals(f030201.getSite())){
			shohinList = Utility.getShohinFromYahooCsv(itemCsv,
					itemCatCsv, selectCsv);
		}else {
			shohinList = Utility.getShohinFromCsv(itemCsv,
					itemCatCsv, selectCsv);
		}
		GetCommodityAllAp getCommodityAllAp = new GetCommodityAllAp();
		List<GetCommodityApOutput> commodityList = getCommodityAllAp.execute();
		List<ShohinBean> insertList = getInsertList(shohinList, commodityList);
		List<ShohinBean> updateList = getUpdateList(shohinList, insertList);

		insertDB(insertList);
		updateDB(updateList);

	}

	@SuppressWarnings("resource")
	private void updateDB(List<ShohinBean> updateList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = null;
		String date = format.format(new Date());
		try {
			conn = JdbcConnection.getConnection();
			for (ShohinBean shohinBean : updateList) {
				ShohinInfoBean shohinInfoBean = shohinBean.getShohinInfoBean();
				List<ShohinsentakushiBean> shohinsentakushiBeanList = shohinBean
						.getShohinsentakushiBeanList();
				List<CommodityDetail> commodityDetailList = getDetailList(shohinsentakushiBeanList);

				sql = "UPDATE TBL00011 SET JAPANESE_NAME =? , PIC_URL = ? ,UPDATE_TIME = ? , UPDATE_USER = ? WHERE COMMODITY_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shohinInfoBean.getShouhinmei());
				String pirurl = "";
				try {
					if (shohinInfoBean.getShouhingazoUrl().indexOf("https://image.rakuten.co.jp/") != -1) {
						if (shohinInfoBean.getShouhingazoUrl().indexOf(" ") != -1) {
							pirurl = ("https://thumbnail.image.rakuten.co.jp/@0_mall/"
									+ shohinInfoBean.getShouhingazoUrl().substring(
											shohinInfoBean.getShouhingazoUrl().indexOf(
													"https://image.rakuten.co.jp/"),
											shohinInfoBean.getShouhingazoUrl().indexOf(
													" ")) + "?_ex=200x200&s=2&r=1")
									.replace("https://image.rakuten.co.jp/", "");
						}else {
							pirurl = ("https://thumbnail.image.rakuten.co.jp/@0_mall/"
									+ shohinInfoBean.getShouhingazoUrl() + "?_ex=200x200&s=2&r=1")
									.replace("https://image.rakuten.co.jp/", "");
						}
					}else if (shohinInfoBean.getShouhingazoUrl().indexOf("https://www.rakuten.ne.jp/") != -1) {
						if (shohinInfoBean.getShouhingazoUrl().indexOf(" ") != -1) {
							pirurl = ("https://thumbnail.image.rakuten.co.jp/@0_gold/"
									+ shohinInfoBean.getShouhingazoUrl().substring(
											shohinInfoBean.getShouhingazoUrl().indexOf(
													"https://www.rakuten.ne.jp/gold/"),
											shohinInfoBean.getShouhingazoUrl().indexOf(
													" ")) + "?_ex=200x200&s=2&r=1")
									.replace("https://www.rakuten.ne.jp/gold/", "");
						}else {
							pirurl = ("https://thumbnail.image.rakuten.co.jp/@0_gold/"
									+ shohinInfoBean.getShouhingazoUrl() + "?_ex=200x200&s=2&r=1")
									.replace("https://www.rakuten.ne.jp/gold/", "");
						}
					}else {
						pirurl = "";
					}
				} catch (Exception e) {
					System.out.println(shohinInfoBean.getShouhingazoUrl());
				}
				ps.setString(2, pirurl);
				ps.setString(3, date);

				ps.setString(4, "kyo");
				ps.setString(5, shohinInfoBean.getShouhinkanribango());
				ps.execute();

				if (!Utility.isEmptyList(commodityDetailList)) {

					for (CommodityDetail detail : commodityDetailList) {
						int count = 0;
						sql = "SELECT COUNT(*) COUNT FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, shohinInfoBean.getShouhinkanribango());
						ps.setString(2, detail.getDetailNo());
						rs = ps.executeQuery();
						while (rs.next()) {
							count = rs.getInt("COUNT");
						}
						if (count > 0) {
							sql = "UPDATE TBL00012 SET COMM_DESCRIBE = ?,RE_PRICE = ?,UPDATE_TIME = ? , YOKONAME = ?, SHITAGANAME = ?, UPDATE_USER = ? WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, detail.getDescribe());
							if (shohinInfoBean.getShouhinkanribango().equals(
									"sjpj138")) {
								detail.getDescribe();
							}
							ps.setString(2, shohinInfoBean.getHanbaikakaku());
							ps.setString(3, date);
							ps.setString(4, detail.getYokoname());
							ps.setString(5, detail.getShitaganame());
							ps.setString(6, "kyo");
							ps.setString(7,
									shohinInfoBean.getShouhinkanribango());
							ps.setString(8, detail.getDetailNo());
							
							ps.executeUpdate();
						} else {
							sql = SqlUtility.getSql("SQLR0001012");
							ps = conn.prepareStatement(sql);
							ps.setString(1,
									shohinInfoBean.getShouhinkanribango());
							ps.setString(2, detail.getDetailNo());
							ps.setString(3, detail.getDescribe());
							ps.setString(4, detail.getPicUrl());
							ps.setString(5, Utility.isEmptyString(detail
									.getPriceIn()) ? null : detail.getPriceIn());
							ps.setString(6, Utility
									.isEmptyString(shohinInfoBean
											.getHanbaikakaku()) ? null
									: shohinInfoBean.getHanbaikakaku());
							ps.setString(7, "0");
							ps.setString(8, "0");
							ps.setString(9, "0");
							ps.setString(10, detail.getRemarks());
							ps.setString(11, detail.getBarCode());
							ps.setString(12, "");
							ps.setString(13, "0");
							ps.setString(14, date);
							ps.setString(15, "kyo");
							ps.setString(16, date);
							ps.setString(17, "kyo");
							ps.setString(18, detail.getYokoname());
							ps.setString(19, detail.getShitaganame());
							ps.setString(20, f030201.getSite());
							ps.setString(21, f030201.getShop());
							ps.execute();
						}

					}
				}

				String maxBarcode = null;
				if (!Utility.isEmptyList(commodityDetailList)) {
					for (CommodityDetail detail : commodityDetailList) {
						sql = "SELECT COUNT(*) COUNT FROM TBL00016 WHERE COMMODITY_ID = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, shohinInfoBean.getShouhinkanribango()
								+ detail.getDetailNo());
						rs = ps.executeQuery();
						int count = 0;
						while (rs.next()) {
							count = rs.getInt("COUNT");
						}
						if (count == 0) {
							sql = "SELECT MAX(BARCODE)+1 MAX_BARCODE FROM TBL00016";
							ps = conn.prepareStatement(sql);
							rs = ps.executeQuery();
							while (rs.next()) {
								maxBarcode = rs.getString("MAX_BARCODE");
							}

							sql = "INSERT INTO TBL00016 VALUES(?,?)";
							ps = conn.prepareStatement(sql);
							ps.setString(1,
									shohinInfoBean.getShouhinkanribango()
											+ detail.getDetailNo());
							ps.setString(2, maxBarcode);
							ps.execute();
						}
					}

				}
			}
			// commit
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	private void insertDB(List<ShohinBean> insertList) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = null;
		String date = format.format(new Date());
		try {
			conn = JdbcConnection.getConnection();
			for (ShohinBean shohinBean : insertList) {
				ShohinInfoBean shohinInfoBean = shohinBean.getShohinInfoBean();
				List<ShohinsentakushiBean> shohinsentakushiBeanList = shohinBean
						.getShohinsentakushiBeanList();
				List<CommodityDetail> commodityDetailList = getDetailList(shohinsentakushiBeanList);

				sql = "INSERT INTO tbl00011(COMMODITY_ID,CATEGORY_ID,CHINESE_NAME,JAPANESE_NAME,SOURCE,RESP_PERSON,COMMODITY_URL,PIC_URL,REMARKS,DEL_FLG,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER,STATUS)VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shohinInfoBean.getShouhinkanribango());
				// TODO
				ps.setString(2, "100001");
				ps.setString(3, shohinInfoBean.getShouhinmei());
				ps.setString(4, shohinInfoBean.getShouhinmei());
				ps.setString(5, "");
				ps.setString(6, "");
				String pirurl = "";
				try {
					if (shohinInfoBean.getShouhingazoUrl().indexOf("https://image.rakuten.co.jp/") != -1) {
						if (shohinInfoBean.getShouhingazoUrl().indexOf(" ") != -1) {
							pirurl = ("https://thumbnail.image.rakuten.co.jp/@0_mall/"
									+ shohinInfoBean.getShouhingazoUrl().substring(
											shohinInfoBean.getShouhingazoUrl().indexOf(
													"https://image.rakuten.co.jp/"),
											shohinInfoBean.getShouhingazoUrl().indexOf(
													" ")) + "?_ex=200x200&s=2&r=1")
									.replace("https://image.rakuten.co.jp/", "");
						}else {
							pirurl = ("https://thumbnail.image.rakuten.co.jp/@0_mall/"
									+ shohinInfoBean.getShouhingazoUrl() + "?_ex=200x200&s=2&r=1")
									.replace("https://image.rakuten.co.jp/", "");
						}
					}else if (shohinInfoBean.getShouhingazoUrl().indexOf("https://www.rakuten.ne.jp/") != -1) {
						if (shohinInfoBean.getShouhingazoUrl().indexOf(" ") != -1) {
							pirurl = ("https://thumbnail.image.rakuten.co.jp/@0_gold/"
									+ shohinInfoBean.getShouhingazoUrl().substring(
											shohinInfoBean.getShouhingazoUrl().indexOf(
													"https://www.rakuten.ne.jp/gold/"),
											shohinInfoBean.getShouhingazoUrl().indexOf(
													" ")) + "?_ex=200x200&s=2&r=1")
									.replace("https://www.rakuten.ne.jp/gold/", "");
						}else {
							pirurl = ("https://thumbnail.image.rakuten.co.jp/@0_gold/"
									+ shohinInfoBean.getShouhingazoUrl() + "?_ex=200x200&s=2&r=1")
									.replace("https://www.rakuten.ne.jp/gold/", "");
						}
					}else {
						pirurl = "";
					}
				} catch (Exception e) {
					System.out.println(shohinInfoBean.getShouhingazoUrl());
				}
				ps.setString(7, "");
				ps.setString(8, pirurl);
				ps.setString(9, "");
				ps.setString(10, "0");
				ps.setString(11, date);
				ps.setString(12, "kyo");
				ps.setString(13, date);
				ps.setString(14, "kyo");
				ps.setString(15, "00");
				ps.execute();

				sql = SqlUtility.getSql("SQLR0001012");
				if (!Utility.isEmptyList(commodityDetailList)) {
					ps = conn.prepareStatement(sql);
					for (CommodityDetail detail : commodityDetailList) {
						ps.setString(1, shohinInfoBean.getShouhinkanribango());
						ps.setString(2, detail.getDetailNo());
						ps.setString(3, detail.getDescribe());
						ps.setString(4, detail.getPicUrl());
						ps.setString(5, Utility.isEmptyString(detail
								.getPriceIn()) ? null : detail.getPriceIn());
						ps.setString(6,
								Utility.isEmptyString(shohinInfoBean
										.getHanbaikakaku()) ? null
										: shohinInfoBean.getHanbaikakaku());
						ps.setString(7, "0");
						ps.setString(8, "0");
						ps.setString(9, "0");
						ps.setString(10, detail.getRemarks());
						ps.setString(11, detail.getBarCode());
						ps.setString(12, "");
						ps.setString(13, "0");
						ps.setString(14, date);
						ps.setString(15, "kyo");
						ps.setString(16, date);
						ps.setString(17, "kyo");
						ps.setString(18, detail.getYokoname());
						ps.setString(19, detail.getShitaganame());
						ps.setString(20, f030201.getSite());
						ps.setString(21, f030201.getShop());
						ps.execute();
					}
				}

				ResultSet rs = null;
				String maxBarcode = null;
				if (!Utility.isEmptyList(commodityDetailList)) {
					for (CommodityDetail detail : commodityDetailList) {
						sql = "SELECT COUNT(*) COUNT FROM TBL00016 WHERE COMMODITY_ID = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, shohinInfoBean.getShouhinkanribango()
								+ detail.getDetailNo());
						rs = ps.executeQuery();
						int count = 0;
						while (rs.next()) {
							count = rs.getInt("COUNT");
						}
						if (count == 0) {
							sql = "SELECT MAX(BARCODE)+1 MAX_BARCODE FROM TBL00016";
							ps = conn.prepareStatement(sql);
							rs = ps.executeQuery();
							while (rs.next()) {
								maxBarcode = rs.getString("MAX_BARCODE");
							}

							sql = "INSERT INTO TBL00016 VALUES(?,?)";
							ps = conn.prepareStatement(sql);
							ps.setString(1,
									shohinInfoBean.getShouhinkanribango()
											+ detail.getDetailNo());
							ps.setString(2, maxBarcode);
							ps.execute();
						}
					}

				}
			}
			// commit
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	private List<CommodityDetail> getDetailList(
			List<ShohinsentakushiBean> shohinsentakushiBeanList) {
		CommodityDetail detail = null;
		List<CommodityDetail> detailList = new ArrayList<CommodityDetail>();
		for (int i = 0; i < shohinsentakushiBeanList.size(); i++) {
			ShohinsentakushiBean shohinsentakushiBean = shohinsentakushiBeanList
					.get(i);
			if ("i".equals(shohinsentakushiBean.getSentakutaipu())) {
				detail = new CommodityDetail();
				detailList.add(detail);
				detail.setDetailNo(shohinsentakushiBean
						.getKomokusentakushibetuzaikoyoyokojikusentakushishibango()
						+ shohinsentakushiBean
								.getKomokusentakushibetuzaikoyotatejikusentakushishibango());
				detail.setDescribe(shohinsentakushiBean
						.getKomokusentakushibetuzaikoyouyokojikusentakushi()
						+ "\r\n"
						+ shohinsentakushiBean
								.getKomokusentakushizaikoyoutatejikusentakushi());
				detail.setYokoname(shohinsentakushiBean
						.getKomokusentakushibetuzaikoyouyokojikusentakushi());
				detail.setShitaganame(shohinsentakushiBean
						.getKomokusentakushizaikoyoutatejikusentakushi());
			}
		}
		return detailList;
	}

	private List<ShohinBean> getUpdateList(List<ShohinBean> shohinList,
			List<ShohinBean> insertList) {
		List<ShohinBean> updateList = new ArrayList<ShohinBean>();
		ShohinBean shohinBean = null;
		String shohinbango = null;
		for (int i = 0; i < shohinList.size(); i++) {
			boolean ariFlg = false;
			shohinBean = shohinList.get(i);
			shohinbango = shohinBean.getShohinInfoBean().getShouhinkanribango();
			if (shohinbango.startsWith("sale")) {
				shohinbango = shohinbango.replace("sale", "");
			}
			for (int j = 0; j < insertList.size(); j++) {
				if (insertList.get(j).getShohinInfoBean()
						.getShouhinkanribango().startsWith("sale")) {
					insertList
							.get(j)
							.getShohinInfoBean()
							.setShouhinkanribango(
									insertList.get(j).getShohinInfoBean()
											.getShouhinkanribango()
											.replace("sale", ""));
				}
				if (shohinbango.equals(insertList.get(j).getShohinInfoBean()
						.getShouhinkanribango())) {
					ariFlg = true;
					break;
				}
			}
			if (!ariFlg) {
				updateList.add(shohinBean);
			}

		}
		return updateList;
	}

	private List<ShohinBean> getInsertList(List<ShohinBean> shohinList,
			List<GetCommodityApOutput> commodityList) {
		List<ShohinBean> insertList = new ArrayList<ShohinBean>();
		ShohinBean shohinBean = null;
		String shohinbango = null;
		for (int i = 0; i < shohinList.size(); i++) {
			boolean ariFlg = false;
			shohinBean = shohinList.get(i);
			shohinbango = shohinBean.getShohinInfoBean().getShouhinkanribango();
			if (shohinbango.startsWith("sale")) {
				shohinbango = shohinbango.replace("sale", "");
			}
			for (int j = 0; j < commodityList.size(); j++) {
				if (shohinbango.toUpperCase().equals(commodityList.get(j).getCommodityId().toUpperCase())) {
					ariFlg = true;
					break;
				}
			}
			if (!ariFlg) {
				insertList.add(shohinBean);
			}

		}

		return insertList;
	}

	protected void init() {
		setTitle("V030201:库存列表");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
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
	 * @param itemCsv
	 *            the itemCsv to set
	 */
	public void setItemCsv(File itemCsv) {
		this.itemCsv = itemCsv;
	}

	/**
	 * @return the itemCatCsv
	 */
	public File getItemCatCsv() {
		return itemCatCsv;
	}

	/**
	 * @param itemCatCsv
	 *            the itemCatCsv to set
	 */
	public void setItemCatCsv(File itemCatCsv) {
		this.itemCatCsv = itemCatCsv;
	}

	/**
	 * @return the selectCsv
	 */
	public File getSelectCsv() {
		return selectCsv;
	}

	/**
	 * @param selectCsv
	 *            the selectCsv to set
	 */
	public void setSelectCsv(File selectCsv) {
		this.selectCsv = selectCsv;
	}

}
