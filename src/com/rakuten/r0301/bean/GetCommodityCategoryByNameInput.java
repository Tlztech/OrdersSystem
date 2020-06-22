package com.rakuten.r0301.bean;

import java.io.Serializable;

public class GetCommodityCategoryByNameInput implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
