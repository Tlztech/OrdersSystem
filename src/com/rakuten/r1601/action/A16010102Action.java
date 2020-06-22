package com.rakuten.r1601.action;

import java.util.Collections;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.UpdateStock;
import com.rakuten.r1601.form.F160101;
import com.rakuten.util.Utility;

public class A16010102Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F160101 f160101 = null;

	protected void exec() throws Exception {
		UpdateStock updatestock = new UpdateStock();
		List<String> msgList = updatestock.exec(f160101.getShop(),
				Collections.singletonList(f160101.getShohinbango()));
		if (Utility.isEmptyList(msgList)) {
			addError(null, "操作成功");
		} else {
			for (String msg : msgList) {
				addError(null, msg);
			}
		}
	}

	protected void init() {
		setTitle("V160101:小工具");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F160101 getF160101() {
		return f160101;
	}

	public void setF160101(F160101 f160101) {
		this.f160101 = f160101;
	}

}
