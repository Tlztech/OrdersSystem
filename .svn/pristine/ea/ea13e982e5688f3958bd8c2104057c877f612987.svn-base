package com.rakuten.r1002.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1002.form.F100201;
import com.rakuten.r1002.form.JuchujoutaiList;
import com.rakuten.util.Utility;

public class A10020103Action extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private F100201 f100201 = null;

	protected void exec() throws Exception {
		if (f100201 != null) {
			List<JuchujoutaiList> juchujoutaiList = f100201
					.getJuchujoutaiList();
			JuchujoutaiList juchujoutai = new JuchujoutaiList();
			juchujoutai.setStsid(Utility.getShoribango());
			juchujoutai.setEditable(true);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			juchujoutai.setStsdata(sdf.format(new Date()));
			juchujoutaiList.add(juchujoutai);
		}
	}

	protected void init() {
		setTitle("V100101:注文状態");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F100201 getF100201() {
		return f100201;
	}

	public void setF100201(F100201 f100201) {
		this.f100201 = f100201;
	}
}
