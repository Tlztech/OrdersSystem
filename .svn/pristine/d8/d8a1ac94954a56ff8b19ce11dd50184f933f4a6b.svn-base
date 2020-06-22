package com.rakuten.common.action;

import com.rakuten.util.Utility;

public class GetLogAction extends BaseAction {

	private static final long serialVersionUID = -5541718078289452647L;
	private String result = null;
	private String logKey = null;

	protected void exec() throws Exception {
		result = Utility.getLog(logKey);
	}

	protected void init() {
	}

	public String getLogKey() {
		return logKey;
	}

	public void setLogKey(String logKey) {
		this.logKey = logKey;
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
