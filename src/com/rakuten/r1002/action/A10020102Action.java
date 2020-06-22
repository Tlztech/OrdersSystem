package com.rakuten.r1002.action;

import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1002.common.A1002Common;
import com.rakuten.r1002.form.F100201;
import com.rakuten.r1002.form.JuchujoutaiList;
import com.rakuten.util.Utility;

public class A10020102Action extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private F100201 f100201 = null;

	protected void exec() throws Exception {
		A1002Common common = new A1002Common();
		List<JuchujoutaiList> juchujoutaiList = common
				.getJuchujoutaiList(f100201.getJuchubango());
		if (!Utility.isEmptyList(juchujoutaiList)) {
			f100201.setJuchujoutaiList(juchujoutaiList);
			f100201.setJuchubango_hid(f100201.getJuchubango());
		} else {
			addError(null, "該当なし");
			f100201.setJuchujoutaiList(null);
			f100201.setJuchushousaiiList(null);
			f100201.setJuchubango_hid(null);
			return;
		}
		f100201.setJuchushousaiiList(common.getJuchushousaiiList(f100201
				.getJuchubango()));
	}

	protected void init() {
		setTitle("V100101:注文状態");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		if (Utility.isEmptyString(f100201.getJuchubango())) {
			addError(null, "受注番号を入力してください");
		}
	}

	public F100201 getF100201() {
		return f100201;
	}

	public void setF100201(F100201 f100201) {
		this.f100201 = f100201;
	}
}
