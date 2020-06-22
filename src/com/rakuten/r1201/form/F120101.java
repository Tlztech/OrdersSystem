package com.rakuten.r1201.form;

import java.io.Serializable;
import java.util.List;

public class F120101 implements Serializable {
	private static final long serialVersionUID = 1L;

	private List<AlbumList> albumList = null;
	private String addName = null;

	/**
	 * @return the albumList
	 */
	public List<AlbumList> getAlbumList() {
		return albumList;
	}

	/**
	 * @param albumList
	 *            the albumList to set
	 */
	public void setAlbumList(List<AlbumList> albumList) {
		this.albumList = albumList;
	}

	/**
	 * @return the addName
	 */
	public String getAddName() {
		return addName;
	}

	/**
	 * @param addName
	 *            the addName to set
	 */
	public void setAddName(String addName) {
		this.addName = addName;
	}

}
