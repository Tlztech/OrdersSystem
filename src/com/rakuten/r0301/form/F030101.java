package com.rakuten.r0301.form;

import java.util.List;

public class F030101 {
	private List<CategoryInfo> categoryList = null;

	private String cateName = null;

	private int resultCount = 0;

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public List<CategoryInfo> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<CategoryInfo> categoryList) {
		this.categoryList = categoryList;
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

}
