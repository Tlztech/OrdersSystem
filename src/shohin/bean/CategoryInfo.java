package shohin.bean;

import java.io.Serializable;

public class CategoryInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// カテゴリセット管理番号
	private String categorySetManageNumber;
	// カテゴリID
	private int categoryId;
	// 複数表示形式
	private boolean isPluralItemPage;

	public String getCategorySetManageNumber() {
		return categorySetManageNumber;
	}

	public void setCategorySetManageNumber(String categorySetManageNumber) {
		this.categorySetManageNumber = categorySetManageNumber;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public boolean isPluralItemPage() {
		return isPluralItemPage;
	}

	public void setPluralItemPage(boolean isPluralItemPage) {
		this.isPluralItemPage = isPluralItemPage;
	}

}
