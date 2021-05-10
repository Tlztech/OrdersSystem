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

public class A10010114Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F100101 f100101 = null;
	private A1001Common a1001Common = new A1001Common();
	private File csvFile10 = null;
	private String shop = null;
	
	@Override
	protected void init() throws Exception {
		setTitle("V100101:注文一覧");
	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		OrderCommon common = new OrderCommon();
		
		OrderApiBean orderapibean = common.getAmazonOrderListByCSV(csvFile10,shop);
		a1001Common.insertIntoAmazonOrderTbl(orderapibean.getRakutenBeanList(), shop);
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}
	
	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("c:/temp/" + csvFile10));
	}

	/**
	 * @return the f100101
	 */
	public F100101 getF100101() {
		return f100101;
	}

	/**
	 * @param f100101 the f100101 to set
	 */
	public void setF100101(F100101 f100101) {
		this.f100101 = f100101;
	}

	/**
	 * @return the csvFile10
	 */
	public File getCsvFile10() {
		return csvFile10;
	}

	/**
	 * @param csvFile10 the csvFile10 to set
	 */
	public void setCsvFile10(File csvFile10) {
		this.csvFile10 = csvFile10;
	}

	/**
	 * @return the shop
	 */
	public String getShop() {
		return shop;
	}

	/**
	 * @param shop the shop to set
	 */
	public void setShop(String shop) {
		this.shop = shop;
	}

}
