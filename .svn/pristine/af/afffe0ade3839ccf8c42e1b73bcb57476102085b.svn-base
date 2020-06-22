package com.rakuten.r0302.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0302.form.CommodityList;
import com.rakuten.r0302.form.F030201;
import com.rakuten.util.Utility;

public class A03020106Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String waybillNo = null;
	private List<CommodityList> commodityList = null;
	private String barcode = null;
	private String inputHid = null;
	private String count = null;
	List<CommodityList> tempList = new ArrayList<CommodityList>();
	private String page = null;
	private String goPage = null;
	private String nowPage = null;
	private String allPage = null;
	private F030201 f030201 = null;

	private String disabledFlg = "false";

	@SuppressWarnings("unchecked")
	protected void exec() throws Exception {
		tempList = (List<CommodityList>) getSessionAttribute("commodityList");
		if ("first".equals(page)) {
			goPage = "1";
		} else if ("end".equals(page)) {
			goPage = String.valueOf(allPage);
		} else if ("next".equals(page)) {
			goPage = String.valueOf(Integer.valueOf(nowPage) + 1);
		} else if ("last".equals(page)) {
			goPage = String.valueOf(Integer.valueOf(nowPage) - 1);
		}

		nowPage = goPage;
		if ((Integer.valueOf(goPage) - 1) * 10 + 10 <= tempList.size()) {
			commodityList = tempList.subList((Integer.valueOf(goPage) - 1) * 10,
					(Integer.valueOf(goPage) - 1) * 10 + 10);
		} else {
			commodityList = tempList.subList((Integer.valueOf(goPage) - 1) * 10,
					tempList.size());
		}
		f030201.setCommodityList(commodityList);

	}

	protected void init() {
		setTitle("V030201:库存列表");
	}

	protected void isValidated() throws Exception {
		if (!Utility.isEmptyString(nowPage) && !Utility.isEmptyString(allPage)) {
			if ("go".equals(page)) {
				try {
					if (Integer.valueOf(allPage) < Integer.valueOf(goPage)) {
						addError("goPage", "跳转页面设置有误！");
					}
				} catch (Exception e) {
					addError("goPage", "跳转页面设置有误！");
				}
			} else if ("last".equals(page)) {
				if (Integer.valueOf(nowPage) - 1 < 1) {
					addError(null, "没有上一页了");
				}
			} else if ("next".equals(page)) {
				if (Integer.valueOf(nowPage) + 1 > Integer.valueOf(allPage)) {
					addError(null, "没有下一页了");
				}
			}
		} else {
			addError(null, "没有数据！");
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
	 * @return the tempList
	 */
	public List<CommodityList> getTempList() {
		return tempList;
	}

	/**
	 * @param tempList
	 *            the tempList to set
	 */
	public void setTempList(List<CommodityList> tempList) {
		this.tempList = tempList;
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

	public F030201 getF030201() {
		return f030201;
	}

	public void setF030201(F030201 f030201) {
		this.f030201 = f030201;
	}

}
