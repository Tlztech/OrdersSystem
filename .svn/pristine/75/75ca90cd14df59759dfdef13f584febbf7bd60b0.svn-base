package com.rakuten.r1202.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1202.common.A120201Common;
import com.rakuten.r1202.form.F120201;
import com.rakuten.r1202.form.F120201Detail;
import com.rakuten.r1202.form.ShohinList;
import com.rakuten.util.JdbcConnection;

public class A12020105Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F120201 f120201 = null;
	F120201Detail detail = null;
	A120201Common a120201Common = new A120201Common();

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		List<F120201Detail> detailList = (List<F120201Detail>) getSessionAttribute("f120201DetailList");
		try {
			conn = JdbcConnection.getConnection();
			sql = "UPDATE tbl00023 SET SHURUI = ? WHERE SHOHINBANGO = ?";

			ps = conn.prepareStatement(sql);
			ps.setString(1, "1");
			ps.setString(2, detail.getShohinbango());
			ps.execute();
			detail.setDetailDelFlg("1");
			for (int i = 0; i < detailList.size(); i++) {
				if (detailList.get(i).getShohinbango()
						.equals(detail.getShohinbango())) {
					detailList.set(i, detail);
				}
			}
			setSessionAttribute("f120201DetailList", detailList);
			List<ShohinList> shohinList = a120201Common.getshohinList(
					detailList, f120201);
			f120201.setShohinList(shohinList);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}

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

}
