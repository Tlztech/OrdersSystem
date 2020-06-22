package com.rakuten.common.bean;

import java.io.Serializable;
import java.util.List;

import com.rakuten.r1001.bean.RakutenCsvBean;

public class OrderApiBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	List<RakutenCsvBean> rakutenBeanList = null;
	List<String> messageList = null;

	public List<RakutenCsvBean> getRakutenBeanList() {
		return rakutenBeanList;
	}

	public void setRakutenBeanList(List<RakutenCsvBean> rakutenBeanList) {
		this.rakutenBeanList = rakutenBeanList;
	}

	public List<String> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}

}
