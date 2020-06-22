package com.rakuten.r0001.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.rakuten.common.action.BaseAction;

public class A000102Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String loginUrl = "";

	@Override
	protected void init() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {

		String strURL = "http://ip.chinaz.com/";
		URL url = new URL(strURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStreamReader input = new InputStreamReader(
				httpConn.getInputStream(), "utf-8");
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		String buf = contentBuf.toString();
		int beginIx = buf.indexOf("您的IP:[<strong class=\"red\">");
		int endIx = buf.indexOf("</strong>] 来自:<strong>");
		loginUrl = buf.substring(beginIx, endIx).replace("您的IP:[<strong class=\"red\">", "");
		loginUrl = "http://" + loginUrl + ":8080/OrdersSystem";

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getLoginUrl() {
		return loginUrl;
	}

	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

}
