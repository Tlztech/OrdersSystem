package com.rakuten.r0302.action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0302.ap.GetCommodityByIdAp;
import com.rakuten.r0302.bean.GetCommodityByIdApOutput;
import com.rakuten.r0302.form.CommodityDetail;
import com.rakuten.r0302.form.CommodityList;
import com.rakuten.r0302.form.F030201;
import com.rakuten.r0302.form.F030202;
import com.rakuten.r0302.form.F030303;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A03020201Action extends BaseAction {

	private F030202 f030202 = null;
	private F030201 f030201 = null;
	private String shoriMode = null;
	private static final long serialVersionUID = 1L;

	protected void exec() throws Exception {
		if ("99".equals(getMode())) {
			if (f030202 != null) {
				uploadFile(f030202.getUploadFile());
			}
		}
		if ("1".equals(getMode())) {
			F030303 f030303 = (F030303) getSessionAttribute("popupDate");
			CommodityDetail detail = new CommodityDetail();
			if (f030303 != null) {
				detail.setDetailNo(f030303.getDetailNo());
				detail.setDescribe(f030303.getDescribe());
				detail.setPicUrl(Utility.getPicUrl(Utility
						.getCommodityId(f030202.getCommodityId())));
				detail.setPriceIn(f030303.getPriceIn());
				detail.setRePrice(f030303.getRePrice());
				detail.setBarCode(f030303.getBarCode());
				detail.setStockSh(f030303.getStockSh());
				detail.setStockJp(f030303.getStockJp());
				detail.setRemarks(f030303.getRemarks());

				String tochu = "0";
				Connection conn = null;
				PreparedStatement ps = null;
				String sql = null;
				ResultSet rs = null;
				try {
					conn = JdbcConnection.getConnection();
					sql = "SELECT SUM(T1.QUANTITY) QUANTITY FROM TBL00014 T1 LEFT JOIN TBL00013 T2 ON T1.WAYBILL_NO = T2.WAYBILL_NO WHERE T1.COMMODITY_ID = ? AND T2.STATUS = '00'";
					ps = conn.prepareStatement(sql);
					ps.setString(1,
							f030202.getCommodityId() + detail.getDetailNo());
					rs = ps.executeQuery();
					while (rs.next()) {
						if (!Utility.isEmptyString(rs.getString("QUANTITY"))) {
							tochu = rs.getString("QUANTITY");
						}
					}
					detail.setStockHandup(tochu);
				} catch (Exception e) {
					e.printStackTrace();
					conn.rollback();
					throw e;
				} finally {
					conn.close();
				}
			}
			if (f030202 == null) {
				f030202 = new F030202();
			}
			if (Utility.isEmptyList(f030202.getCommodityDetailList())) {
				f030202.setCommodityDetailList(new ArrayList<CommodityDetail>());
			}
			f030202.getCommodityDetailList().add(detail);
			setMode("");
			removeSessionAttribute("popupDate");
		} else if ("3".equals(getMode())) {
			int rowindex = Integer.valueOf(getRowIndex());
			f030202.getCommodityDetailList().remove(rowindex);
			setMode("");
		} else if ("2".equals(getMode())
				|| "2".equals((String) getSessionAttribute("mode"))) {

			if (f030202 != null) {
				int rowindex = Integer.valueOf(getRowIndex());
				CommodityDetail detail = f030202.getCommodityDetailList().get(
						rowindex);
				setSessionAttribute("A03020201Date", detail);

				F030303 f030303 = (F030303) getSessionAttribute("popupDate");

				setSessionAttribute("mode", "2");
				if (f030303 != null) {
					detail = new CommodityDetail();
					detail.setDetailNo(f030303.getDetailNo());
					detail.setDescribe(f030303.getDescribe());
					detail.setPicUrl(f030303.getPicUrl());
					detail.setPriceIn(f030303.getPriceIn());
					detail.setRePrice(f030303.getRePrice());
					detail.setBarCode(f030303.getBarCode());
					detail.setStockSh(f030303.getStockSh());
					detail.setStockJp(f030303.getStockJp());
					detail.setStockHandup(f030303.getStockHandup());
					detail.setRemarks(f030303.getRemarks());
					detail.setPicUrl(Utility.getPicUrl(Utility
							.getCommodityId(f030202.getCommodityId())));
					f030202.getCommodityDetailList().set(rowindex, detail);

					setMode("");
					removeSessionAttribute("popupDate");
					removeSessionAttribute("mode");
					removeSessionAttribute("A03020201Date");
				}
			}
		}
		if ("2".equals(shoriMode)) {
			String rowIndex = getRowIndex();
			if (f030201 != null) {

				CommodityList commodity = f030201.getCommodityList().get(
						Integer.valueOf(rowIndex));
				String commId = commodity.getCommodityId();
				GetCommodityByIdAp getCommodityByIdAp = new GetCommodityByIdAp();
				GetCommodityByIdApOutput output = getCommodityByIdAp
						.execute(commId);
				f030202 = new F030202();
				f030202.setCommodityId(output.getCommodityId());
				f030202.setCategoryId(output.getCategoryId());
				f030202.setCategoryName(output.getCategoryName());
				f030202.setChineseName(output.getChineseName());
				f030202.setJapaneseName(output.getJapaneseName());
				f030202.setCommodityUrl(output.getCommodityUrl());
				f030202.setPicUrl(Utility.getPicUrl(output.getCommodityId()));
				f030202.setRemarks(output.getRemarks());
				f030202.setSource(output.getSource());
				f030202.setRespPerson(output.getRespPerson());

				List<CommodityDetail> commodityDetailList = new ArrayList<CommodityDetail>();
				CommodityDetail commodityDetail = null;
				f030202.setCommodityDetailList(commodityDetailList);
				for (com.rakuten.r0302.bean.CommodityDetail detail : output
						.getCommodityDetailList()) {
					f030202.setPriceIn(detail.getPriceIn());
					f030202.setPriceOut(detail.getRePrice());
					commodityDetail = new CommodityDetail();
					commodityDetailList.add(commodityDetail);
					commodityDetail.setDetailNo(detail.getDetailNo());
					commodityDetail.setDescribe(detail.getDescribe());
					commodityDetail.setPicUrl(Utility.getPicUrl(Utility
							.getCommodityId(f030201.getCommodityId())));
					commodityDetail.setPriceIn(f030202.getPriceIn());
					commodityDetail.setRePrice(f030202.getPriceOut());
					commodityDetail.setBarCode(detail.getBarCode());
					commodityDetail.setStockSh(detail.getStockSh());
					commodityDetail.setStockJp(detail.getStockJp());
					commodityDetail.setRemarks(detail.getRemarks());
					commodityDetail.setDelFlg(detail.isDelFlg());

					String tochu = "0";
					String nyuka = "0";
					Connection conn = null;
					PreparedStatement ps = null;
					String sql = null;
					ResultSet rs = null;
					try {
						conn = JdbcConnection.getConnection();
						sql = "SELECT SUM(T1.QUANTITY) QUANTITY FROM TBL00014 T1 LEFT JOIN TBL00013 T2 ON T1.WAYBILL_NO = T2.WAYBILL_NO WHERE T1.COMMODITY_ID = ? AND T2.STATUS = '00'";
						ps = conn.prepareStatement(sql);
						ps.setString(1,
								f030202.getCommodityId() + detail.getDetailNo());
						rs = ps.executeQuery();
						while (rs.next()) {
							if (!Utility
									.isEmptyString(rs.getString("QUANTITY"))) {
								tochu = rs.getString("QUANTITY");
							}
						}
						commodityDetail.setStockHandup(tochu);

						sql = "SELECT SUM(QUANTITY) QUANTITY FROM TBL00015 WHERE COMMODITY_ID = ? AND COMMODITY_STATUS = '0'";
						ps = conn.prepareStatement(sql);
						ps.setString(1,
								f030202.getCommodityId() + detail.getDetailNo());
						rs = ps.executeQuery();
						while (rs.next()) {
							if (!Utility
									.isEmptyString(rs.getString("QUANTITY"))) {
								nyuka = rs.getString("QUANTITY");
							}
						}
						commodityDetail.setNyuka(nyuka);
					} catch (Exception e) {
						e.printStackTrace();
						conn.rollback();
						throw e;
					} finally {
						conn.close();
					}
				}
			}
		}
	}

	private void uploadFile(File image) throws IOException {
		String imageFileName = null;
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 20; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		imageFileName = sb.toString() + ".jpg";

		if (image != null) {
			String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
			File savefile = new File(new File(realpath + "/temp"),
					imageFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(image, savefile);
			f030202.setPicUrl("/temp/" + imageFileName);
		}

	}

	protected void init() {
		if ("1".equals(shoriMode)) {
			setTitle("V030202:添加商品");
		} else if ("2".equals(shoriMode)) {
			setTitle("V030202:查看/修改商品");
		}
	}

	protected void isValidated() throws Exception {
	}

	/**
	 * @return the f030202
	 */
	public F030202 getF030202() {
		return f030202;
	}

	/**
	 * @param f030202
	 *            the f030202 to set
	 */
	public void setF030202(F030202 f030202) {
		this.f030202 = f030202;
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public String getShoriMode() {
		return shoriMode;
	}

	public void setShoriMode(String shoriMode) {
		this.shoriMode = shoriMode;
	}

	public F030201 getF030201() {
		return f030201;
	}

	public void setF030201(F030201 f030201) {
		this.f030201 = f030201;
	}
}
