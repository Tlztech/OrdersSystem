package com.rakuten.common.action;

import java.util.List;

import com.rakuten.r1202.form.F120201Detail;

public class getLastNameAction extends BaseAction {

	private static final long serialVersionUID = -5541718078289452647L;
	private String result = null;
	private String shohinbango = null;
	private String shohinbango_sel = null;

	protected void exec() throws Exception {
		result = "";

		List<F120201Detail> detailList = (List<F120201Detail>) getSessionAttribute("f120201DetailList");

		int i = 0;
		for (F120201Detail detailInfo : detailList) {
			if (detailInfo.getShohinbango().equals(shohinbango_sel)) {
				break;
			}
			i++;
		}
		if (i > 0) {
			result = detailList.get(i-1).getShohinmei_jp();
		}
	}

	protected void init() {
	}

	protected void isValidated() throws Exception {
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

	public String getShohinbango_sel() {
		return shohinbango_sel;
	}

	public void setShohinbango_sel(String shohinbango_sel) {
		this.shohinbango_sel = shohinbango_sel;
	}
}
