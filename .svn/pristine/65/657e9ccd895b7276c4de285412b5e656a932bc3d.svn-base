package com.rakuten.r1202.action;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1202.common.A120201Common;
import com.rakuten.r1202.form.F120201;
import com.rakuten.r1202.form.F120201Detail;
import com.rakuten.r1202.form.PicList;
import com.rakuten.util.Utility;

import de.innosystec.unrar.Archive;
import de.innosystec.unrar.rarfile.FileHeader;

public class A12020112Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F120201 f120201 = null;
	F120201Detail detail = null;
	String destDirName = null;
	File picfile = null;
	A120201Common a120201Common = new A120201Common();

	protected void exec() throws Exception {
		try {
			String shohinbango = detail.getShohinbango();
			unrar(picfile, "D:\\temp\\");
			if (!(destDirName.substring(destDirName.lastIndexOf("\\") + 1))
					.equals(shohinbango)) {
				addError(null, "图片压缩包与商品编号名称不相符");
				return;
			}

			File dir = new File(destDirName);
			if (!dir.exists()) {
				addError(null, "图片压缩包有误");
				return;
			}
			String[] fileArr = dir.list();
			for (String afile : fileArr) {
				if (!afile.contains(".jpg")) {
					addError(null, "图片非jpg格式");
					return;
				}
			}

			List<String> msgList = checkImg(fileArr);
			if (!Utility.isEmptyList(msgList)) {
				for (String msg : msgList) {
					addError(null, msg);
				}
				return;
			}

			String basepath = ServletActionContext.getServletContext()
					.getRealPath("\\");

			File filedir = new File(basepath + "temp/" + shohinbango + "\\");
			if (filedir.exists()) {
				Utility.delFolder(basepath + "temp/" + shohinbango + "\\");
			}

			File filedirLocal = new File("D:/picDir/" + shohinbango + "\\");
			if (filedirLocal.exists()) {
				Utility.delFolder("D:/picDir/" + shohinbango + "\\");
			}
			List<PicList> picList = new ArrayList<PicList>();
			for (String file : fileArr) {
				Utility.copyFile(destDirName + "\\" + file, basepath + "temp/"
						+ shohinbango + "\\", file);
				Utility.copyFile(destDirName + "\\" + file, "D:/picDir/"
						+ shohinbango + "\\", file);
				PicList pic = new PicList();
				picList.add(pic);

				pic.setPicurl("temp/" + shohinbango + "/" + file);
			}
			detail.setPicList(picList);
			String picdir = detail.getPicdir();
			if (Utility.isEmptyString(picdir)) {
				String shohinbangotmp = shohinbango;
				while (shohinbangotmp.length() > 0) {
					if (Utility.isNum(String.valueOf(shohinbangotmp
							.charAt(shohinbangotmp.length() - 1)))) {
						shohinbangotmp = shohinbangotmp.substring(0,
								shohinbangotmp.length() - 1);
					} else {
						break;
					}
				}
				detail.setPicdir(shohinbangotmp);
			}
			detail.setHozonFlg("0");
			List<F120201Detail> detailList = (List<F120201Detail>) getSessionAttribute("f120201DetailList");
			for (F120201Detail detailInfo : detailList) {
				if (shohinbango.equals(detailInfo.getShohinbango())) {
					detailInfo = detail;
				}
			}

		} finally {
			Utility.delFolder(destDirName);
		}
	}

	private List<String> checkImg(String[] fileArr) {
		return null;

	}

	private void unrar(File sourceRar, String destDir) throws Exception {
		Archive a = null;
		FileOutputStream fos = null;
		try {
			a = new Archive(sourceRar);
			FileHeader fh = a.nextFileHeader();
			while (fh != null) {
				if (!fh.isDirectory()) {
					// 1 根据不同的操作系统拿到相应的 destDirName 和 destFileName
					String compressFileName = fh.getFileNameString().trim();
					String destFileName = "";
					destDirName = "";
					// 非windows系统
					if (File.separator.equals("/")) {
						destFileName = destDir
								+ compressFileName.replaceAll("\\\\", "/");
						destDirName = destFileName.substring(0,
								destFileName.lastIndexOf("/"));
						// windows系统
					} else {
						destFileName = destDir
								+ compressFileName.replaceAll("/", "\\\\");
						destDirName = destFileName.substring(0,
								destFileName.lastIndexOf("\\"));
					}
					// 2创建文件夹
					File dir = new File(destDirName);
					if (!dir.exists() || !dir.isDirectory()) {
						dir.mkdirs();
					}
					// 3解压缩文件
					fos = new FileOutputStream(new File(destFileName));
					a.extractFile(fh, fos);
					fos.close();
					fos = null;
				}
				fh = a.nextFileHeader();
			}
			a.close();
			a = null;
		} catch (Exception e) {
			throw e;
		} finally {
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (a != null) {
				try {
					a.close();
					a = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void init() throws Exception {
		setTitle("V120201:文案编辑:  "
				+ a120201Common.getShumokuname(f120201.getShumokuid()));
	}

	protected void isValidated() throws Exception {
		if (picfile == null) {
			addError(null, "请选择上传压缩包");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @return the picfile
	 */
	public File getPicfile() {
		return picfile;
	}

	/**
	 * @param picfile
	 *            the picfile to set
	 */
	public void setPicfile(File picfile) {
		this.picfile = picfile;
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

}
