package com.rakuten.r0001.bean;

public class CheckLoginOutput {
	private boolean successFlg = false;
	private int companyId;

	/**
	 * @return the successFlg
	 */
	public boolean isSuccessFlg() {
		return successFlg;
	}

	/**
	 * @param successFlg
	 *            the successFlg to set
	 */
	public void setSuccessFlg(boolean successFlg) {
		this.successFlg = successFlg;
	}

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

}
