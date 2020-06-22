package com.rakuten.r0302.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0302.ap.GetCommodityAp;
import com.rakuten.r0302.bean.GetCommodityApInput;
import com.rakuten.r0302.bean.GetCommodityApOutput;
import com.rakuten.r0302.form.CommodityList;
import com.rakuten.r0302.form.F030201;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A03020102Action extends BaseAction {

	private F030201 f030201 = null;
	private String shoriMode = null;
	private static final long serialVersionUID = 1L;
	private String page = null;
	private String goPage = null;
	private String nowPage = null;
	private String allPage = null;

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getGoPage() {
		return goPage;
	}

	public void setGoPage(String goPage) {
		this.goPage = goPage;
	}

	public String getNowPage() {
		return nowPage;
	}

	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}

	public String getAllPage() {
		return allPage;
	}

	public void setAllPage(String allPage) {
		this.allPage = allPage;
	}

	protected void exec() throws Exception {
		GetCommodityAp getCommodityAp = new GetCommodityAp();
		GetCommodityApInput input = new GetCommodityApInput();
		input.setCategoryId(f030201.getCategoryId());
		input.setChineseName(f030201.getChineseName());
		input.setCommodityId(f030201.getCommodityId());
		input.setJapaneseName(f030201.getJapaneseName());
		input.setStockJpEnd(f030201.getStockJpEnd());
		input.setStockShEnd(f030201.getStockShEnd());
		input.setStockJpStart(f030201.getStockJpStart());
		input.setStockShStart(f030201.getStockShStart());

		f030201.setHid_categoryId(f030201.getCategoryId());
		f030201.setHid_chineseName(f030201.getChineseName());
		f030201.setHid_commodityId(f030201.getCommodityId());
		f030201.setHid_japaneseName(f030201.getJapaneseName());
		f030201.setHid_stockJpEnd(f030201.getStockJpEnd());
		f030201.setHid_stockShEnd(f030201.getStockShEnd());
		f030201.setHid_stockJpStart(f030201.getStockJpStart());
		f030201.setHid_stockShStart(f030201.getStockShStart());

		List<GetCommodityApOutput> outputList = getCommodityAp.execute(input);
		Integer count = outputList.size();
		f030201.setResultCount(count.toString());
		if (count == 0) {
			addActionError("没有找到相应的结果！");
		}
		List<CommodityList> commodityList_temp = new ArrayList<CommodityList>();
		if (!Utility.isEmptyList(outputList)) {

			f030201.setCommodityList(commodityList_temp);
			CommodityList commodity = null;
			for (GetCommodityApOutput output : outputList) {
				commodity = new CommodityList();
				commodityList_temp.add(commodity);
				commodity.setCategoryName(output.getCategoryName());
				commodity.setChineseName(output.getChineseName());
				commodity.setCommodityId(output.getCommodityId());
				commodity.setJapaneseName(output.getJapaneseName());
				commodity.setPicUrl(Utility.getPicUrl(Utility
						.getCommodityId(commodity.getCommodityId())));
				commodity.setStockSh(output.getStockSh());
				commodity.setStockJp(output.getStockJp());

				String tochu = "0";
				String nyuka = "0";
				Connection conn = null;
				PreparedStatement ps = null;
				String sql = null;
				ResultSet rs = null;
				try {
					conn = JdbcConnection.getConnection();
					sql = "SELECT SUM(T1.QUANTITY) QUANTITY FROM TBL00014 T1 LEFT JOIN TBL00013 T2 ON T1.WAYBILL_NO = T2.WAYBILL_NO WHERE T1.COMMODITY_ID like ? AND T2.STATUS = '00'";
					ps = conn.prepareStatement(sql);
					ps.setString(1, commodity.getCommodityId() + "-%");
					rs = ps.executeQuery();
					while (rs.next()) {
						if (!Utility.isEmptyString(rs.getString("QUANTITY"))) {
							tochu = rs.getString("QUANTITY");
						}
					}

					sql = "SELECT SUM(QUANTITY) QUANTITY FROM TBL00015  WHERE COMMODITY_ID like ? AND COMMODITY_STATUS = '0'";
					ps = conn.prepareStatement(sql);
					ps.setString(1, commodity.getCommodityId() + "-%");
					rs = ps.executeQuery();
					while (rs.next()) {
						if (!Utility.isEmptyString(rs.getString("QUANTITY"))) {
							nyuka = rs.getString("QUANTITY");
						}
					}
					commodity.setNyuka(nyuka);
					commodity.setStockHandup(tochu);
				} catch (Exception e) {
					e.printStackTrace();
					conn.rollback();
					throw e;
				} finally {
					conn.close();
				}
			}
		} else {
			f030201.setCommodityList(null);
		}
		List<CommodityList> commodityList = null;

		setSessionAttribute("commodityList", commodityList_temp);
		if (commodityList_temp.size() > 10) {
			commodityList = commodityList_temp.subList(0, 10);
		} else {
			commodityList = commodityList_temp;
		}
		f030201.setCommodityList(commodityList);
		nowPage = "1";
		allPage = String
				.valueOf(((double) commodityList_temp.size() / (double) 10) > (commodityList_temp
						.size() / 10) ? commodityList_temp.size() / 10 + 1
						: commodityList_temp.size() / 10);
		goPage = "";
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

	public String getShoriMode() {
		return shoriMode;
	}

	public void setShoriMode(String shoriMode) {
		this.shoriMode = shoriMode;
	}
}
