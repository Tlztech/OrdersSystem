package com.rakuten.p0101.form;

public class CategoryInfo {

	private String cateName = null;
	private String fatherCateName = null;
	private Long cateCount = null;
	private String cateId = null;
	private String fatherCateId = null;
	private String sozaiChn;
	private String sozaiJpn;
	private String nameJpn;
	private String kakaku;

	public String getSozaiChn() {
		return sozaiChn;
	}

	public void setSozaiChn(String sozaiChn) {
		this.sozaiChn = sozaiChn;
	}

	public String getSozaiJpn() {
		return sozaiJpn;
	}

	public void setSozaiJpn(String sozaiJpn) {
		this.sozaiJpn = sozaiJpn;
	}

	public String getNameJpn() {
		return nameJpn;
	}

	public void setNameJpn(String nameJpn) {
		this.nameJpn = nameJpn;
	}

	public String getKakaku() {
		return kakaku;
	}

	public void setKakaku(String kakaku) {
		this.kakaku = kakaku;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getFatherCateName() {
		return fatherCateName;
	}

	public void setFatherCateName(String fatherCateName) {
		this.fatherCateName = fatherCateName;
	}

	public Long getCateCount() {
		return cateCount;
	}

	public void setCateCount(Long cateCount) {
		this.cateCount = cateCount;
	}

	public String getCateId() {
		return cateId;
	}

	public void setCateId(String cateId) {
		this.cateId = cateId;
	}

	public String getFatherCateId() {
		return fatherCateId;
	}

	public void setFatherCateId(String fatherCateId) {
		this.fatherCateId = fatherCateId;
	}

}
