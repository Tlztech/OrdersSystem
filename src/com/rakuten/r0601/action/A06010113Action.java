package com.rakuten.r0601.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.form.CommodityList;

public class A06010113Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String waybillNo = null;
	private List<CommodityList> commodityList = null;
	private String barcode = null;
	private String inputHid = null;
	private String count = null;
	private String sum = null;
	private String getSum = null;

	private String page = null;
	private String goPage = null;
	private String nowPage = null;
	private String allPage = null;

	private String disabledFlg = "false";

	protected void exec() throws Exception {
		@SuppressWarnings("unchecked")
		List<CommodityList> commodityList_temp = (List<CommodityList>) getSessionAttribute("commodityList");
		for (int i = 0; i < commodityList_temp.size(); i++) {
			for (int j = 0; j < commodityList.size(); j++) {
				if (commodityList.get(j).getCommodityId()
						.equals(commodityList_temp.get(i).getCommodityId())) {
					commodityList.get(j).setGetSu(
							commodityList_temp.get(i).getQuantity());
				}
			}
			commodityList_temp.get(i).setGetSu(
					commodityList_temp.get(i).getQuantity());
		}
		setSessionAttribute("commodityList", commodityList_temp);
		for (int i = 0; i < commodityList.size(); i++) {
			for (int j = 0; j < commodityList_temp.size(); j++) {
				if (commodityList.get(i).getCommodityId()
						.equals(commodityList_temp.get(j).getCommodityId())) {
					commodityList_temp.get(j).setGetSu(
							commodityList.get(i).getGetSu());
				}
			}
		}
		for (int i = 0; i < commodityList_temp.size(); i++) {
			this.getSum = String.valueOf(Integer.valueOf(this.getSum)
					.intValue()
					+ Integer.valueOf((commodityList_temp.get(i)).getGetSu())
							.intValue());
		}

	}

	protected void init() {
		setTitle("V060104:签收");
	}

	protected void isValidated() throws Exception {
	}

	protected void fieldCheck() throws Exception {
	}

	public String getWaybillNo() {
		return this.waybillNo;
	}

	public void setWaybillNo(String waybillNo) {
		this.waybillNo = waybillNo;
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

	public String getBarcode() {
		return barcode;
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

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
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