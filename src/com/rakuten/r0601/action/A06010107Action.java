package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.CheckCommodityAp;
import com.rakuten.r0601.ap.GetCommodityPicUrlAp;
import com.rakuten.r0601.form.CommodityList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A06010107Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String waybillNo = null;
	private List<CommodityList> commodityList_temp = null;
	private List<CommodityList> commodityList = null;
	private String barcode = null;
	private String inputHid = null;
	private String count = null;
	private String sum = null;
	private String getSum = null;
	List<CommodityList> tempList = new ArrayList<CommodityList>();
	private String goPage = "1";
	private String page = null;
	private String nowPage = null;
	private String allPage = null;

	private String disabledFlg = "false";

	protected void exec() throws Exception {
		count = String.valueOf(commodityList_temp.size());
		sum = "0";
		getSum = "0";
		for (int i = 0; i < commodityList_temp.size(); i++) {
			sum = String.valueOf(Integer.valueOf(sum)
					+ Integer.valueOf(commodityList_temp.get(i).getQuantity()));
			getSum = String.valueOf(Integer.valueOf(getSum)
					+ Integer.valueOf(commodityList_temp.get(i).getGetSu()));
		}
		commodityList = commodityList_temp.subList(0, 7);
		nowPage = "1";
		setSessionAttribute("commodityList", commodityList_temp);
	}

	protected void init() {
		setTitle("V060104:签收");
	}

	@SuppressWarnings("unchecked")
	protected void isValidated() throws Exception {
		String commodityId = "";
		String detailNo = "";
		inputHid = "1";
		commodityList_temp = (List<CommodityList>) getSessionAttribute("commodityList");

		for (int i = 0; i < commodityList.size(); i++) {
			for (int j = 0; j < commodityList_temp.size(); j++) {
				if (commodityList.get(i).getCommodityId()
						.equals(commodityList_temp.get(j).getCommodityId())) {
					commodityList_temp.get(j).setGetSu(
							commodityList.get(i).getGetSu());
				}
			}
		}

		if (barcode.indexOf("-") > 0) {
			commodityId = barcode.substring(0, barcode.indexOf("-"));
			detailNo = barcode.substring(barcode.indexOf("-"));
		} else {
			commodityId = barcode;
			detailNo = "";
		}
		CheckCommodityAp checkCommodityAp = new CheckCommodityAp();
		if (!checkCommodityAp.execute(commodityId, detailNo)) {
			Connection conn = null;
			PreparedStatement ps = null;
			String sql = null;
			ResultSet rs = null;
			try {
				conn = JdbcConnection.getConnection();
				sql = "SELECT * FROM TBL00016 WHERE BARCODE = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, commodityId);
				rs = ps.executeQuery();
				String commId = "";
				while (rs.next()) {
					commId = rs.getString("COMMODITY_ID");
				}
				if (Utility.isEmptyString(commId)) {
					addError("f060102.commodityIdInput", "商品编号或条形码不存在！");
					return;
				} else {
					barcode = commId;
					if (barcode.indexOf("-") > 0) {
						commodityId = barcode
								.substring(0, barcode.indexOf("-"));
						detailNo = barcode.substring(barcode.indexOf("-"));
					} else {
						commodityId = barcode;
						detailNo = "";
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}
		}

		if (!Utility.isEmptyList(commodityList_temp)) {
			boolean ariFlg = false;
			for (int i = 0; i < commodityList_temp.size(); i++) {
				String id = ((CommodityList) commodityList_temp.get(i))
						.getCommodityId();
				if (id.equals(commodityId + detailNo)) {
					ariFlg = true;
					inputHid = String.valueOf(Integer.valueOf(
							((CommodityList) commodityList_temp.get(i))
									.getGetSu()).intValue()
							+ Integer.valueOf(inputHid));
					commodityList_temp.get(i).setGetSu(inputHid);
					tempList.add(commodityList_temp.get(i));
					commodityList_temp.remove(i);
					tempList.addAll(commodityList_temp);
					commodityList_temp.clear();
					commodityList_temp.addAll(tempList);
					tempList.clear();
					break;
				}
			}
			if (!ariFlg) {

				String picUrl = Utility.getPicUrl(commodityId);
				CommodityList commodity = new CommodityList();
				if (Utility.isEmptyString(detailNo))
					commodity.setCommodityId(commodityId);
				else {
					commodity.setCommodityId(commodityId + detailNo);
				}
				commodity.setPicUrl(picUrl);
				commodity.setGetSu("1");
				commodity.setCommodityId(barcode);
				commodity.setQuantity("0");

				tempList.add(commodity);
				commodityList_temp.remove(commodity);
				tempList.addAll(commodityList_temp);
				commodityList_temp.clear();
				commodityList_temp.addAll(tempList);
				tempList.clear();
			}
		}

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the waybillNo
	 */
	public String getWaybillNo() {
		return waybillNo;
	}

	/**
	 * @param waybillNo
	 *            the waybillNo to set
	 */
	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
	}

	public String getBarcode() {
		return barcode;
	}

	/**
	 * @return the commodityList
	 */
	public List<CommodityList> getCommodityList() {
		return commodityList;
	}

	/**
	 * @param commodityList
	 *            the commodityList to set
	 */
	public void setCommodityList(List<CommodityList> commodityList) {
		this.commodityList = commodityList;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	/**
	 * @return the inputHid
	 */
	public String getInputHid() {
		return inputHid;
	}

	/**
	 * @param inputHid
	 *            the inputHid to set
	 */
	public void setInputHid(String inputHid) {
		this.inputHid = inputHid;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getGetSum() {
		return getSum;
	}

	public void setGetSum(String getSum) {
		this.getSum = getSum;
	}

	public String getDisabledFlg() {
		return disabledFlg;
	}

	public void setDisabledFlg(String disabledFlg) {
		this.disabledFlg = disabledFlg;
	}

	/**
	 * @return the goPage
	 */
	public String getGoPage() {
		return goPage;
	}

	/**
	 * @param goPage
	 *            the goPage to set
	 */
	public void setGoPage(String goPage) {
		this.goPage = goPage;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the nowPage
	 */
	public String getNowPage() {
		return nowPage;
	}

	/**
	 * @param nowPage
	 *            the nowPage to set
	 */
	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}

	/**
	 * @return the allPage
	 */
	public String getAllPage() {
		return allPage;
	}

	/**
	 * @param allPage
	 *            the allPage to set
	 */
	public void setAllPage(String allPage) {
		this.allPage = allPage;
	}

}
