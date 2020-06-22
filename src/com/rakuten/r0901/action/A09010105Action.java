package com.rakuten.r0901.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0901.common.A0901Common;
import com.rakuten.r0901.form.F090101;
import com.rakuten.r0901.form.Meisai;

public class A09010105Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F090101 f090101 = null;
	private A0901Common a0901Common = new A0901Common();
	private String logKey = null;

	protected void exec() throws Exception {
		System.out.println("GetbankInfoAction is starting...");
		getSaishin();
		List<Meisai> meisaiList = a0901Common.getMeisaiList(new F090101());
		f090101 = new F090101();
		f090101.setMeisaiList(meisaiList);
		f090101.setResultCount(String.valueOf(meisaiList.size()));
	}

	protected void init() {
		setTitle("V090101:群馬銀行入出金明細照会");

	}

	public void getSaishin() throws Exception {
		// 用虚拟浏览器获得最新银行情报
		List<Meisai> newMeisaiList = a0901Common.getList(logKey);
		List<Meisai> oldMeisaiList = getOldList();
		List<Meisai> insertList = getInsertList(newMeisaiList, oldMeisaiList);
		a0901Common.insertDB(insertList);

	}

	private List<Meisai> getInsertList(List<Meisai> newMeisaiList,
			List<Meisai> oldMeisaiList) {
		List<Meisai> insertList = new ArrayList<Meisai>();
		int oldSize = oldMeisaiList.size();
		int newSize = newMeisaiList.size();
		if (newSize > oldSize) {
			for (int i = oldSize; i < newSize; i++) {
				insertList.add(newMeisaiList.get(i));
			}
		}
		return insertList;
	}

	private List<Meisai> getOldList() throws Exception {
		TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
		TimeZone.setDefault(tz);

		Calendar date = Calendar.getInstance();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String endDay = sdf.format(date.getTime());
		date.add(Calendar.DATE, -6);
		String startDay = sdf.format(date.getTime());

		f090101 = new F090101();
		f090101.setTorihikinengappiStart(startDay);
		f090101.setTorihikinengappiEnd(endDay);

		List<Meisai> oldList = a0901Common.getMeisaiList(f090101);

		return oldList;
	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F090101 getF090101() {
		return f090101;
	}

	public void setF090101(F090101 f090101) {
		this.f090101 = f090101;
	}

	public String getLogKey() {
		return logKey;
	}

	public void setLogKey(String logKey) {
		this.logKey = logKey;
	}

}
