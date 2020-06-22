package com.rakuten.r0801.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.struts2.ServletActionContext;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0801.form.F080101;
import com.rakuten.r0801.form.ShumokuList;
import com.rakuten.util.JdbcConnection;

public class A08010103Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private F080101 f080101 = null;

	protected void exec() throws Exception {
		ShumokuList info = f080101.getShumokuList().get(
				Integer.valueOf(getRowIndex()));
		deleteShumokuList(info.getShumokubango());
		deletePic(info.getShumokubango());
	}

	private void deletePic(String shumokubango) {
		String realpath = ServletActionContext.getRequest().getRealPath("/");
		String path = realpath + "/taobaoPic/" + shumokubango + "/";
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}

	}

	protected void init() {
		setTitle("V080101:日本网站商品清单");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F080101 getF080101() {
		return f080101;
	}

	public void setF080101(F080101 f080101) {
		this.f080101 = f080101;
	}

	private void deleteShumokuList(String shumokuBango) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "DELETE FROM TBL00019 WHERE SHUMOKU_BANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shumokuBango);
			ps.execute();

			sql = "DELETE FROM TBL00020 WHERE SHUMOKU_BANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shumokuBango);
			ps.execute();

			conn.commit();
			f080101.setPassFlg("1");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

}
