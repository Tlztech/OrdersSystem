package com.rakuten.common.action;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;

import com.rakuten.r1002.common.A1002Common;
import com.rakuten.r1002.form.JuchushousaiiList;
import com.rakuten.util.Utility;

public class GetOrderStsDetailAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String juchubango = null;
	String result = null;

	protected void exec() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.addHeader("Access-Control-Allow-Origin", "*");
		juchubango = Utility.strTrim(juchubango);
		juchubango = juchubango.replace("-", "");
		A1002Common common = new A1002Common();

		List<JuchushousaiiList> juchushousaiiList = common
				.getJuchushousaiiList(juchubango);

		JSONArray juchushousaiiList_json = JSONArray
				.fromObject(juchushousaiiList);
		if (juchushousaiiList_json != null) {
			result = juchushousaiiList_json.toString();
		}
	}

	protected void init() {

	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	protected void isValidated() throws Exception {
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
