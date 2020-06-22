package com.rakuten.r0001.action;

import com.opensymphony.xwork2.ActionSupport;
import com.rakuten.r0001.ap.CheckLoginAp;
import com.rakuten.r0001.bean.CheckLoginInput;
import com.rakuten.r0001.bean.CheckLoginOutput;

public class A000101Action extends ActionSupport {

	public String execute() throws Exception {

		CheckLoginAp checkLoginAp = new CheckLoginAp();
		CheckLoginInput input = new CheckLoginInput();
		input.setUserName(username);
		input.setPassword(password);
		CheckLoginOutput output = checkLoginAp.execute(input);
		if (!output.isSuccessFlg()) {
			this.addFieldError("username", "用户名或密码不正确");
			return INPUT;
		} else {
			return SUCCESS;
		}

	}

	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
