package com.rakuten.r1001.action;

import java.io.File;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.F100101;

public class A10010110Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F100101 f100101 = null;
	private File csvFile5 = null;
	private A1001Common a1001Common = new A1001Common();

	protected void exec() throws Exception {
		a1001Common.insertIntoPonpareOrderTbl(a1001Common.getOrderListFromCsvPonpare(csvFile5));

	}

	protected void init() {
		setTitle("V100101:注文一覧");

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

	public File getCsvFile5() {
		return csvFile5;
	}

	public void setCsvFile5(File csvFile5) {
		this.csvFile5 = csvFile5;
	}

}
