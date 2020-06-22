package com.rakuten.r1501.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1501.common.A1501Common;
import com.rakuten.r1501.form.F150102;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A15010203Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F150102 f150102 = null;
	private String accountId = null;
	private String kachoshi1 = null;
	private String kachoshi2 = null;
	private String kachoshi3 = null;
	private String kachoshi4 = null;
	private String kachoshi5 = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String table = "";
		if ("0".equals(f150102.getAddArea())) {
			table = "cash_jp_tbl";
		} else {
			table = "cash_sh_tbl";
		}

		String balance = "";
		String bankcode = A1501Common.getBankCode(accountId);
		balance = A1501Common.getAccountBalance(accountId, bankcode);

		if ("0".equals(f150102.getAddKubun())) {
			balance = String.valueOf(Long.valueOf(balance)
					+ Long.valueOf(f150102.getAddJine()));
		} else {
			balance = String.valueOf(Long.valueOf(balance)
					- Long.valueOf(f150102.getAddJine()));
		}

		String kanribango = Utility.getShoribango();
		String filepath1 = "";
		String filepath2 = "";
		String filepath3 = "";
		String filepath4 = "";
		String filepath5 = "";

		File addedFile1 = f150102.getAddedFile1();
		File addedFile2 = f150102.getAddedFile2();
		File addedFile3 = f150102.getAddedFile3();
		File addedFile4 = f150102.getAddedFile4();
		File addedFile5 = f150102.getAddedFile5();
		HttpServletRequest request = ServletActionContext.getRequest();
		String basePath = request.getSession().getServletContext()
				.getRealPath("/");

		if (addedFile1 != null) {
			kachoshi1 = kachoshi1.substring(kachoshi1.lastIndexOf("."));
			filepath1 = "/temp/" + accountId + "/" + kanribango + "/addedFile1"
					+ kachoshi1;
			Utility.copyFile(addedFile1, basePath + "/temp/" + accountId + "/"
					+ kanribango + "/", "addedFile1" + kachoshi1);
			Utility.copyFile(addedFile1, "c:/temp/" + accountId + "/"
					+ kanribango + "/", "addedFile1" + kachoshi1);
		}
		if (addedFile2 != null) {
			kachoshi2 = kachoshi2.substring(kachoshi2.lastIndexOf("."));
			filepath2 = "/temp/" + accountId + "/" + kanribango + "/addedFile2"
					+ kachoshi2;
			Utility.copyFile(addedFile2, basePath + "/temp/" + accountId + "/"
					+ kanribango + "/", "addedFile2" + kachoshi2);
			Utility.copyFile(addedFile2, "c:/temp/" + accountId + "/"
					+ kanribango + "/", "addedFile2" + kachoshi2);
		}
		if (addedFile3 != null) {
			kachoshi3 = kachoshi3.substring(kachoshi3.lastIndexOf("."));
			filepath3 = "/temp/" + accountId + "/" + kanribango + "/addedFile3"
					+ kachoshi3;
			Utility.copyFile(addedFile3, basePath + "/temp/" + accountId + "/"
					+ kanribango + "/", "addedFile3" + kachoshi3);
			Utility.copyFile(addedFile3, "c:/temp/" + accountId + "/"
					+ kanribango + "/", "addedFile3" + kachoshi3);
		}
		if (addedFile4 != null) {
			kachoshi4 = kachoshi4.substring(kachoshi4.lastIndexOf("."));
			filepath4 = "/temp/" + accountId + "/" + kanribango + "/addedFile4"
					+ kachoshi4;
			Utility.copyFile(addedFile4, basePath + "/temp/" + accountId + "/"
					+ kanribango + "/", "addedFile4" + kachoshi4);
			Utility.copyFile(addedFile4, "c:/temp/" + accountId + "/"
					+ kanribango + "/", "addedFile4" + kachoshi4);
		}
		if (addedFile5 != null) {
			kachoshi5 = kachoshi5.substring(kachoshi5.lastIndexOf("."));
			filepath5 = "/temp/" + accountId + "/" + kanribango + "/addedFile5"
					+ kachoshi5;
			Utility.copyFile(addedFile5, basePath + "/temp/" + accountId + "/"
					+ kanribango + "/", "addedFile5" + kachoshi5);
			Utility.copyFile(addedFile5, "c:/temp/" + accountId + "/"
					+ kanribango + "/", "addedFile5" + kachoshi5);
		}

		try {
			conn = JdbcConnection.getConnection();
			String sql = "INSERT INTO " + table
					+ "  VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			int i = 0;
			ps.setString(++i, kanribango);
			ps.setString(++i, accountId);
			ps.setString(++i, f150102.getAddJiaoyiri());
			if ("0".equals(f150102.getAddKubun())) {
				ps.setString(++i, f150102.getAddJine());
				ps.setString(++i, "");
			} else if ("1".equals(f150102.getAddKubun())) {
				ps.setString(++i, "");
				ps.setString(++i, f150102.getAddJine());
			}

			ps.setString(++i, f150102.getAddKubun());
			ps.setString(++i, f150102.getAddBiko());
			ps.setString(++i, f150102.getAddJiaoyiKubun());
			ps.setString(++i, balance);
			ps.setString(++i, filepath1);
			ps.setString(++i, filepath2);
			ps.setString(++i, filepath3);
			ps.setString(++i, filepath4);
			ps.setString(++i, filepath5);
			ps.setString(++i, Utility.getDateTime());
			ps.setString(++i, Utility.getUser());
			ps.setString(++i, Utility.getDateTime());
			ps.setString(++i, Utility.getUser());
			ps.execute();

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	protected void init() throws Exception {
		setTitle("V150102:账目明细");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public F150102 getF150102() {
		return f150102;
	}

	public void setF150102(F150102 f150102) {
		this.f150102 = f150102;
	}

	public String getKachoshi1() {
		return kachoshi1;
	}

	public void setKachoshi1(String kachoshi1) {
		this.kachoshi1 = kachoshi1;
	}

	public String getKachoshi2() {
		return kachoshi2;
	}

	public void setKachoshi2(String kachoshi2) {
		this.kachoshi2 = kachoshi2;
	}

	public String getKachoshi3() {
		return kachoshi3;
	}

	public void setKachoshi3(String kachoshi3) {
		this.kachoshi3 = kachoshi3;
	}

	public String getKachoshi4() {
		return kachoshi4;
	}

	public void setKachoshi4(String kachoshi4) {
		this.kachoshi4 = kachoshi4;
	}

	public String getKachoshi5() {
		return kachoshi5;
	}

	public void setKachoshi5(String kachoshi5) {
		this.kachoshi5 = kachoshi5;
	}

}
