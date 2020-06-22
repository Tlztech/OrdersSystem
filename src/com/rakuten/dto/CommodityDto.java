package com.rakuten.dto;

import java.util.List;

public class CommodityDto {

	private TBL00011Dto tbl00011Dto = null;
	private List<TBL00012Dto> tbl00012Dto = null;

	/**
	 * @return the tbl00011Dto
	 */
	public TBL00011Dto getTbl00011Dto() {
		return tbl00011Dto;
	}

	/**
	 * @param tbl00011Dto
	 *            the tbl00011Dto to set
	 */
	public void setTbl00011Dto(TBL00011Dto tbl00011Dto) {
		this.tbl00011Dto = tbl00011Dto;
	}

	/**
	 * @return the tbl00012Dto
	 */
	public List<TBL00012Dto> getTbl00012Dto() {
		return tbl00012Dto;
	}

	/**
	 * @param tbl00012Dto
	 *            the tbl00012Dto to set
	 */
	public void setTbl00012Dto(List<TBL00012Dto> tbl00012Dto) {
		this.tbl00012Dto = tbl00012Dto;
	}

}
