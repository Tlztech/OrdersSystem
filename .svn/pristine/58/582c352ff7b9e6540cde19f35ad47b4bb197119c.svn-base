package com.rakuten.r1001.action;

import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1001.form.F100104;
import com.rakuten.r1001.form.ShohinList;
import com.rakuten.util.Utility;

public class A10010404Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	F100104 f100104 = null;

	protected void exec() throws Exception {
		String shohinbango = f100104.getTuikashohinbango();
		f100104.setTuikashohinbango("");

		String shohinmei = Utility.getShohinmei(shohinbango);

		List<ShohinList> shohinList = f100104.getShohinList();
		if (Utility.isEmptyList(shohinList)) {
			shohinList = new ArrayList<ShohinList>();
			f100104.setShohinList(shohinList);
		}
		ShohinList shohin = new ShohinList();
		shohinList.add(shohin);

		shohin.setShohinbango(shohinbango);
		shohin.setShouhinmei(shohinmei + "(" + shohinbango + ")");
	}

	protected void init() {
		setTitle("V100104:新規");
	}

	protected void isValidated() throws Exception {
		String shohinbango = f100104.getTuikashohinbango();
		if (!Utility.isShohinExist(shohinbango)) {
			addError(null, "商品番号不正");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f100104
	 */
	public F100104 getF100104() {
		return f100104;
	}

	/**
	 * @param f100104
	 *            the f100104 to set
	 */
	public void setF100104(F100104 f100104) {
		this.f100104 = f100104;
	}

}
