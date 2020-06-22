package com.rakuten.r1001.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.OrderApiBean;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.F100101;
import com.rakuten.util.Utility;

public class A10010112Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100101 f100101 = null;
	private String shop = null;
	private A1001Common a1001Common = new A1001Common();
	private File csvFile7 = null;

	protected void exec() throws Exception {

		OrderCommon common = new OrderCommon();

		a1001Common.insertIntoqoo10OrderTbl(a1001Common
				.getOrderListFromCsvQoo10(csvFile7));

	}

	protected void init() {
		setTitle("V100101:注文一覧");

	}

	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("c:/temp/" + csvFile7));
	}

	protected void isValidated() throws Exception {

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

	public File getCsvFile7() {
		return csvFile7;
	}

	public void setCsvFile7(File csvFile7) {
		this.csvFile7 = csvFile7;
	}

}
