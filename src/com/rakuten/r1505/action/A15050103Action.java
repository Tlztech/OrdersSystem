package com.rakuten.r1505.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1505.common.A150501Common;
import com.rakuten.r1505.form.F150501;
import com.rakuten.r1505.form.MeisaiList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A15050103Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	F150501 f150501 = null;
	String mode = null;

	@Override
	protected void exec() throws Exception {
		A150501Common common = new A150501Common();
		common.keisan(f150501);

	}

	protected void init() throws Exception {
		setTitle("V150501:请求书");
	}

	public F150501 getF150501() {
		return f150501;
	}

	public void setF150501(F150501 f150501) {
		this.f150501 = f150501;
	}

	protected void isValidated() throws Exception {
		if (Utility.isEmptyString(f150501.getEndDay())) {
			addError(null, "请求截止日不能为空");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
