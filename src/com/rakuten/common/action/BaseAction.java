package com.rakuten.common.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private String scrollx = null;
	private String scrolly = null;
	private List<String> messageList = null;
	private String goPage = "1";
	private String page = "0";
	private String nowPage = "0";
	private String allPage = "0";
	/**
	 * �������
	 */
	private String title = null;
	/**
	 * ����ģʽ
	 */
	private String mode = null;
	/**
	 * ѡ����
	 */
	private String rowIndex = null;
	/**
	 * ����ID
	 */
	private String viewId = null;

	/**
	 * ִ������
	 * 
	 * @throws Exception
	 */
	public String execute() throws Exception {
		// ����ǰ����ID
		setPreViewId();
		// ���ڻ�����
		init();
		// ����Ŀcheck
		fieldCheck();
		if (getActionErrors().isEmpty()) {
			// ��Ŀ���
			isValidated();
		}
		if (getActionErrors().isEmpty()) { // ��Ŀ���ͨ��
			// ��Ҫ����
			exec();
			if (getActionErrors().isEmpty()) {
				// ��ת
				return SUCCESS;
			} else {
				return INPUT;
			}
		} else {// ��Ŀ��鲻ͨ��
			// ���ص���ǰ����
			return INPUT;
		}
	}

	/**
	 * ���ڻ�����
	 * @throws Exception 
	 */
	protected abstract void init() throws Exception;

	/**
	 * ��Ŀ���
	 */
	protected abstract void isValidated() throws Exception;

	/**
	 * ��Ҫ����
	 */
	protected abstract void exec() throws Exception;

	/**
	 * ����Ŀcheck
	 */
	protected abstract void fieldCheck() throws Exception;

	/**
	 * ��Ӵ�����Ϣ
	 * 
	 * @param errfield
	 *            ������Ŀ��
	 * @param errMsg
	 *            ������Ϣ
	 */
	protected void addError(String errfield, String errMsg) {
		addFieldError(errfield, null);
		addActionError(errMsg);
		setScrollx("0");
		setScrolly("0");
	}

	/**
	 * ȡ�ñ�����session��ֵ
	 * 
	 * @param key
	 *            SessionKey
	 * @return
	 */
	protected Object getSessionAttribute(String key) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> sessionMap = actionContext.getSession();
		return sessionMap.get(key);
	}

	/**
	 * ��ֵ���õ�session
	 * 
	 * @param key
	 *            SessionKey
	 * @param value
	 *            ֵ
	 */
	protected void setSessionAttribute(String key, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> sessionMap = actionContext.getSession();
		sessionMap.put(key, value);
	}

	/**
	 * ɾ��session��ָ����ֵ
	 * 
	 * @param key
	 */
	protected void removeSessionAttribute(String key) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> sessionMap = actionContext.getSession();
		sessionMap.remove(key);
	}

	/**
	 * ȡ��message��Ϣ
	 * 
	 * @param msgId
	 *            messageId
	 * @param param
	 *            ����
	 * @return
	 */
	protected String getMessage(String msgId, String[] param) {
		String msg = getText(msgId);
		if (param != null) {
			for (int i = 0; i < param.length; i++) {
				msg = msg.replace("{" + String.valueOf(i) + "}", param[i]);
			}
		}
		return msg;
	}

	/**
	 * ����ǰ����ID
	 */
	private void setPreViewId() {
		setSessionAttribute("preViewId", getViewId());
	}

	/**
	 * ȡ��ǰ����ID
	 */
	protected String getPreViewId() {
		return (String) getSessionAttribute("preViewId");
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * @param mode
	 *            the mode to set
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * @return the rowIndex
	 */
	public String getRowIndex() {
		return rowIndex;
	}

	/**
	 * @param rowIndex
	 *            the rowIndex to set
	 */
	public void setRowIndex(String rowIndex) {
		this.rowIndex = rowIndex;
	}

	/**
	 * @return the viewId
	 */
	public String getViewId() {
		return viewId;
	}

	/**
	 * @param viewId
	 *            the viewId to set
	 */
	public void setViewId(String viewId) {
		this.viewId = viewId;
	}

	public String getScrollx() {
		return scrollx;
	}

	public void setScrollx(String scrollx) {
		this.scrollx = scrollx;
	}

	public String getScrolly() {
		return scrolly;
	}

	public void setScrolly(String scrolly) {
		this.scrolly = scrolly;
	}

	public List<String> getMessageList() {
		return messageList;
	}

	public void setMessageList(List<String> messageList) {
		this.messageList = messageList;
	}

	/**
	 * @return the goPage
	 */
	public String getGoPage() {
		return goPage;
	}

	/**
	 * @param goPage
	 *            the goPage to set
	 */
	public void setGoPage(String goPage) {
		this.goPage = goPage;
	}

	/**
	 * @return the page
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @param page
	 *            the page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}

	/**
	 * @return the nowPage
	 */
	public String getNowPage() {
		return nowPage;
	}

	/**
	 * @param nowPage
	 *            the nowPage to set
	 */
	public void setNowPage(String nowPage) {
		this.nowPage = nowPage;
	}

	/**
	 * @return the allPage
	 */
	public String getAllPage() {
		return allPage;
	}

	/**
	 * @param allPage
	 *            the allPage to set
	 */
	public void setAllPage(String allPage) {
		this.allPage = allPage;
	}

}
