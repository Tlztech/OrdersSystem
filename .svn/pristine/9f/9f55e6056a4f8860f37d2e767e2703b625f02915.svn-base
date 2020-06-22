package com.rakuten.r0301.action;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0301.form.F030101;

public class A03010101Action extends BaseAction {

	private F030101 f030101 = null;
	private static final long serialVersionUID = 1L;

	protected void exec() throws Exception {
		if ("V030102".equals(getPreViewId())) {
			f030101 = (F030101) getSessionAttribute("V030101SaveForm");
		} else if ("V030103".equals(getPreViewId())) {
			A03010102Action a03010102Action = new A03010102Action();
			a03010102Action.execute();
			f030101 = (F030101) getSessionAttribute("V030101SaveForm");
		} else {
			removeSessionAttribute("V030101SaveForm");
		}
	}

	protected void init() {
		setTitle("V030101:商品分类");
	}

	protected void isValidated() throws Exception {
	}

	public F030101 getF030101() {
		return f030101;
	}

	public void setF030101(F030101 f030101) {
		this.f030101 = f030101;
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub
		
	}
}
