package com.rakuten.r1601.action;

import page.pagetools.Left2;
import page.pagetools.Osusume1;
import page.pagetools.Osusume2;
import page.pagetools.SpCategory;
import page.pagetools.SpCommonTxt2;
import page.pagetools.Timesale24PC;
import page.pagetools.Timesale24Sp;
import page.pagetools.Top;

import com.rakuten.common.action.BaseAction;
import com.rakuten.util.Utility;

public class A16010105Action extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String shop2 = null;
	private String logKey = null;

	@Override
	protected void init() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		Top top = new Top();
		// Utility.addLog("top.basicUpdate(\"trend777\");", logKey);
		// top.basicUpdate("trend777");
		Utility.addLog("top.basicUpdate(\"coverforefront\");", logKey);
		top.basicUpdate("coverforefront");
		SpCommonTxt2 commonTxt2 = new SpCommonTxt2();
		// commonTxt2.basicUpdate("trend777");
		// Utility.addLog("commonTxt2.basicUpdate(\"trend777\");", logKey);
		commonTxt2.basicUpdate("coverforefront");
		Utility.addLog("commonTxt2.basicUpdate(\"coverforefront\");", logKey);
		Left2 left2 = new Left2();
		// Utility.addLog("left2.basicUpdate(\"trend777\");", logKey);
		// left2.basicUpdate("trend777", new String[] { "秋", "冬" });
		Utility.addLog("left2.basicUpdate(\"coverforefront\");", logKey);
		left2.basicUpdate("coverforefront", new String[] { "秋" });
		Osusume1 osusume1 = new Osusume1();
		Osusume2 osusume2 = new Osusume2();

		String[] shohinbangolist1 = new String[] { "nzctx1189", "nzctx1190", "nzctx1191", "nzctx1192", "nzctx1193",
				"nzctx1194", "nzctx1195", "nzctx1196", "nzctx1197", "nzctx1198" };
		String[] shohinbangolist2 = new String[] { "nzks335", "nzks336", "nzks337", "nzks338", "nzks339", "nzks340",
				"nzks341", "nzks342", "nzks343", "nzks344", "nzks345", "nzmy606", "nzmy607", "nzmy608", "nzmy609",
				"nzmy610", "nzmy611", "nzmy612", "nzmy613" };
		Utility.addLog("osusume1", logKey);
		// osusume1.basicUpdate("trend777", shohinbangolist1);
		osusume1.basicUpdate("coverforefront", shohinbangolist1);
		Utility.addLog("osusume2", logKey);
		// osusume2.basicUpdate("trend777", shohinbangolist2);
		osusume2.basicUpdate("coverforefront", shohinbangolist2);

		// Timesale24Sp timesale24Sp = new Timesale24Sp();
		// Utility.addLog("timesale24Sptrend777", logKey);
		// timesale24Sp.basicUpdate("trend777");
		// Utility.addLog("timesale24Spcoverforefront", logKey);
		// timesale24Sp.basicUpdate("coverforefront");

		// Timesale24PC timesale24pc = new Timesale24PC();
		// timesale24pc.basicUpdate("trend777");
		// Utility.addLog("timesale24pctrend777", logKey);
		// timesale24pc.basicUpdate("coverforefront");
		// Utility.addLog("timesale24pccoverforefront", logKey);

		// SpCategory spCategory = new SpCategory();
		// spCategory.basicUpdate();

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getLogKey() {
		return logKey;
	}

	public void setLogKey(String logKey) {
		this.logKey = logKey;
	}

	public String getShop2() {
		return shop2;
	}

	public void setShop2(String shop2) {
		this.shop2 = shop2;
	}

}
