package com.rakuten.common.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.rakuten.r1002.common.A1002Common;
import com.rakuten.r1002.form.JuchujoutaiList;
import com.rakuten.util.Utility;

public class GetOrderStsAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String juchubango = null;
	String result = null;

	protected void exec() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		juchubango = Utility.strTrim(juchubango);
		juchubango = juchubango.replace("-", "");
		response.addHeader("Access-Control-Allow-Origin", "*");
		A1002Common common = new A1002Common();
		List<JuchujoutaiList> juchujoutaiList = common
				.getJuchujoutaiList(juchubango);

		JSONArray juchujoutaiList_json = JSONArray.fromObject(juchujoutaiList);
		if (juchujoutaiList_json != null) {
			result = juchujoutaiList_json.toString();
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

	public String getJuchubango() {
		return juchubango;
	}

	public void setJuchubango(String juchubango) {
		this.juchubango = juchubango;
	}

}
