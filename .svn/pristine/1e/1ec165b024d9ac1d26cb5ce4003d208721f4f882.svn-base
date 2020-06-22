package com.rakuten.r1202.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipOutputStream;

import com.rakuten.common.ShohinCommon;
import com.rakuten.common.action.BaseAction;
import com.rakuten.common.bean.ShohinInfoBean;
import com.rakuten.common.bean.ShohinkategoriBean;
import com.rakuten.common.bean.ShohinsentakushiBean;
import com.rakuten.r1202.common.A120201Common;
import com.rakuten.r1202.form.F120201;
import com.rakuten.r1202.form.F120201Detail;
import com.rakuten.util.Utility;

public class A12020111Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F120201 f120201 = null;
	F120201Detail detail = null;
	A120201Common a120201Common = new A120201Common();
	ShohinCommon shohinCommon = new ShohinCommon();
	private String fileName = null;

	protected void exec() throws Exception {
		List<ShohinInfoBean> itemList = a120201Common.converDetailToShohinBean(
				Collections.singletonList(detail), f120201.getShop());
		List<ShohinsentakushiBean> selList = a120201Common
				.converSelToSelBean(Collections.singletonList(detail));
		List<ShohinkategoriBean> catList = a120201Common
				.converCatToCatBean(Collections.singletonList(detail));

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		String dirName = df.format(new Date());
		File dir = new File("D://temp/" + dirName);
		dir.mkdirs();
		shohinCommon.writeRakutenItemCsv(itemList, "c:/temp/" + dirName
				+ "/item.csv");
		shohinCommon.writeRakutenSelCsv(selList, "c:/temp/" + dirName
				+ "/sel.csv");
		shohinCommon.writeRakutenCatCsv(catList, "c:/temp/" + dirName
				+ "/cat.csv");

		fileName = "out_" + dirName + ".zip";
		FileOutputStream fileOutputStream = new FileOutputStream(new File(
				"c:/temp/" + fileName));
		CheckedOutputStream cos = new CheckedOutputStream(fileOutputStream,
				new CRC32());
		ZipOutputStream out = new ZipOutputStream(cos);
		String basedir = "";
		Utility.compress(new File("c:/temp/" + dirName), out, basedir);
		out.close();
	}

	public InputStream getInputStream() throws IOException {
		return new FileInputStream(new File("c:/temp/" + fileName));
	}

	protected void init() throws Exception {
		setTitle("V120201:ÎÄ°¸±à¼­:  "
				+ a120201Common.getShumokuname(f120201.getShumokuid()));
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the f120201
	 */
	public F120201 getF120201() {
		return f120201;
	}

	/**
	 * @param f120201
	 *            the f120201 to set
	 */
	public void setF120201(F120201 f120201) {
		this.f120201 = f120201;
	}

	/**
	 * @return the detail
	 */
	public F120201Detail getDetail() {
		return detail;
	}

	/**
	 * @param detail
	 *            the detail to set
	 */
	public void setDetail(F120201Detail detail) {
		this.detail = detail;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
