package shohinmodel.bean;

import java.io.Serializable;

public class Category implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// カテゴリID
	private int categoryId;
	// カテゴリレベル
	private int categoryLevel;
	// カテゴリ名
	private String name;
	// カテゴリステータス
	private int status;
	// 優先度
	private int categoryWeight;
	// 子カテゴリ情報リスト
	private ChildCategories childCategories;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(int categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCategoryWeight() {
		return categoryWeight;
	}

	public void setCategoryWeight(int categoryWeight) {
		this.categoryWeight = categoryWeight;
	}

	public ChildCategories getChildCategories() {
		return childCategories;
	}

	public void setChildCategories(ChildCategories childCategories) {
		this.childCategories = childCategories;
	}

}
