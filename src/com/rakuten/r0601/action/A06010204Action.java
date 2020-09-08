package com.rakuten.r0601.action;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.CheckCommodityAp;
import com.rakuten.r0601.ap.GetCommodityPicUrlAp;
import com.rakuten.r0601.form.CommodityList;
import com.rakuten.r0601.form.F060102;
import com.rakuten.util.Utility;

public class A06010204Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	private F060102 f060102 = null;

	// file
	private File filepath = null;
	List<String> commodityIdList = new ArrayList<String>();
	List<String> kosuList = new ArrayList<String>();

	protected void exec() throws Exception {

		checkList();

		f060102 = new F060102();
		List<CommodityList> commodityList = new ArrayList<CommodityList>();
		for (int i = 0; i < commodityIdList.size(); i++) {
			String commId = "";
			String detailNo = "";
			if (commodityIdList.get(i).contains("-")) {
				commId = commodityIdList.get(i).substring(0,
						commodityIdList.get(i).indexOf("-"));
				detailNo = commodityIdList.get(i).substring(
						commodityIdList.get(i).indexOf("-"));
			} else {
				commId = commodityIdList.get(i);
				detailNo = "-0-0";
			}
			GetCommodityPicUrlAp getCommodityPicUrlAp = new GetCommodityPicUrlAp();
			String picUrl = getCommodityPicUrlAp.execute(commId, detailNo);

			CommodityList commodity = new CommodityList();
			if ("-0-0".equals(detailNo)) {
				commodity.setCommodityId(commId);
			} else {
				commodity.setCommodityId(commId + detailNo);
			}
			commodity.setPicUrl(picUrl);
			commodity.setQuantity(kosuList.get(i));
			commodityList.add(commodity);
		}
		f060102.setCommodityList(commodityList);
	}

	private void checkList() {
		for (int i = 0; i < commodityIdList.size(); i++) {
			String commodityId = commodityIdList.get(i);
			for (int j = i + 1; j < commodityIdList.size(); j++) {
				String tempCommodityId = commodityIdList.get(j);
				if (commodityId.equals(tempCommodityId)) {
					kosuList.set(
							i,
							String.valueOf(Integer.valueOf(kosuList.get(j))
									+ Integer.valueOf(kosuList.get(i))));
					kosuList.set(j, "0");
				}
			}
		}

		for (int i = 0; i < kosuList.size(); i++) {
			if ("0".equals(kosuList.get(i))) {
				commodityIdList.remove(i);
				kosuList.remove(i);
				i = i - 1;
			}
		}

	}

	protected void init() {
		setTitle("V060102:添加发货单");
	}

	protected void isValidated() throws Exception {
		HSSFRow row = null;
		HSSFSheet sheet = null;
		HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(filepath));
		sheet = workbook.getSheetAt(0);

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
			row.getCell(1).setCellType(HSSFCell.CELL_TYPE_STRING);
			String kosu = row.getCell(6).getStringCellValue();

			String commId = "";
			String detailNo = "";

			if (commodityId.indexOf("-") > 0) {
				commId = commodityId.substring(0, commodityId.indexOf("-"));
				detailNo = commodityId.substring(commodityId.indexOf("-"));
			} else {
				commId = commodityId;
				detailNo = "-0-0";
			}
			CheckCommodityAp checkCommodityAp = new CheckCommodityAp();
			if (!checkCommodityAp.execute(commId, detailNo)) {
				addError(null, "商品编号" + commodityId + "不存在！");
			}

			commodityIdList.add(commodityId);
			kosuList.add(kosu);

			i++;
		}

		for (int j = 0; j < kosuList.size(); j++) {
			if (Utility.isEmptyString(kosuList.get(j))) {
				addError(null, "商品编号" + commodityIdList.get(j) + "数量为空！");
			}
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the filepath
	 */
	public File getFilepath() {
		return filepath;
	}

	/**
	 * @param filepath
	 *            the filepath to set
	 */
	public void setFilepath(File filepath) {
		this.filepath = filepath;
	}

	/**
	 * @return the f060102
	 */
	public F060102 getF060102() {
		return f060102;
	}

	/**
	 * @param f060102
	 *            the f060102 to set
	 */
	public void setF060102(F060102 f060102) {
		this.f060102 = f060102;
	}

}
