package com.rakuten.r1001.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.platform.commons.util.StringUtils;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.OrderApiBean;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.F100101;
import com.rakuten.util.Utility;

public class A10010113Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100101 f100101 = null;
	private String shop = null;
	private A1001Common a1001Common = new A1001Common();
	private File csvFile8 = null;

	protected void exec() throws Exception {

		OrderCommon common = new OrderCommon();

		if (!a1001Common.insertIntoOtherOrderTbl(a1001Common
				.getOrderListFromCsvOther(csvFile8, f100101.getCharset()))) {
			this.addError(null, "导入失败");
		}

	}

	protected void init() {
		setTitle("V100101:注文一覧");

	}

	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("c:/temp/" + csvFile8));
	}

	protected void isValidated() throws Exception {
		if(StringUtils.isBlank(f100101.getCharset())) {
			addError(null, "文件语言没选择");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F100101 getF100101() {
		return f100101;
	}

	public void setF100101(F100101 f100101) {
		this.f100101 = f100101;
	}

	public String getShop() {
		return shop;
	}

	public void setShop(String shop) {
		this.shop = shop;
	}

	public File getCsvFile8() {
		return csvFile8;
	}

	public void setCsvFile8(File csvFile8) {
		this.csvFile8 = csvFile8;
	}

}
