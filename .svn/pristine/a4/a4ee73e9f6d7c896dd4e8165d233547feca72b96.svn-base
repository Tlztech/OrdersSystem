package com.rakuten.r0302.action;

import java.io.File;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0302.form.F030303;
import com.rakuten.util.Utility;

public class A03020301Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F030303 f030303 = null;

	protected void exec() throws Exception {
		String imageFileName = null;
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 20; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		imageFileName = sb.toString() + ".jpg";

		if (f030303.getUploadFile() != null) {
			String realpath = ServletActionContext.getRequest()
					.getRealPath("/");
			File savefile = new File(new File(realpath + "/temp"),
					imageFileName);
			if (!savefile.getParentFile().exists())
				savefile.getParentFile().mkdirs();
			FileUtils.copyFile(f030303.getUploadFile(), savefile);
			f030303.setPicUrl("/temp/" + imageFileName);
		}

		setSessionAttribute("popupDate", f030303);
	}

	protected void init() {
		setTitle("V030203:商品明细");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		if (Utility.isEmptyString(f030303.getDetailNo())) {
			addError("f030303.detailNo",
					getMessage("M00000001E", new String[] { "明细编号" }));
		}
		if (Utility.isEmptyString(f030303.getDescribe())) {
			addError("f030303.describe",
					getMessage("M00000001E", new String[] { "商品描述" }));
		}

	}

	public F030303 getF030303() {
		return f030303;
	}

	public void setF030303(F030303 f030303) {
		this.f030303 = f030303;
	}
}
