package com.rakuten.common.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;

public class PopDateAction extends BaseAction {

	private static final long serialVersionUID = -5541718078289452647L;
	private String result = null;

	protected void exec() throws Exception {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> sessionMap = actionContext.getSession();
		result = (String) sessionMap.get("popupDate");
		removeSessionAttribute("popupDate");
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
}
