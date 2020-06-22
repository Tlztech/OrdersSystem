package com.rakuten.r1002.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import batch.bean.OrderStsDetailBean;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.r1002.form.F100201;
import com.rakuten.r1002.form.JuchujoutaiList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A10020104Action extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private F100201 f100201 = null;

	protected void exec() throws Exception {

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<JuchujoutaiList> juchujoutaiList = f100201.getJuchujoutaiList();
		String bango = f100201.getJuchubango_hid();
		try {
			conn = JdbcConnection.getConnection();
			String ststxtid = "";
			for (JuchujoutaiList juchujoutai : juchujoutaiList) {
				if (Utility.isEmptyString(juchujoutai.getJoutai())) {
					continue;
				}
				if (Utility.isEmptyString(ststxtid)) {
					ststxtid += (juchujoutai.getStsid() + "%" + juchujoutai
							.getStsdata());
				} else {
					ststxtid += ("&" + juchujoutai.getStsid() + "%" + juchujoutai
							.getStsdata());
				}

				sql = "select count(*) count from order_sts_text_tbl where shoribango = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, juchujoutai.getStsid());
				rs = ps.executeQuery();
				while (rs.next()) {
					if (rs.getInt("count") == 0) {
						sql = "insert into order_sts_text_tbl values(?,?,?)";
						ps = conn.prepareStatement(sql);
						ps.setString(1, juchujoutai.getStsid());
						ps.setString(2, juchujoutai.getJoutai());
						ps.setString(3, "1");
						ps.execute();
					}
				}

			}

			sql = "update order_sts_tbl set order_sts = ? ,update_time = ?, update_user = ? where juchubango = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, ststxtid);
			ps.setString(2, Utility.getDateTime());
			ps.setString(3, Utility.getUser());
			ps.setString(4, bango);
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

	protected void init() {
		setTitle("V100101:注文状態");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F100201 getF100201() {
		return f100201;
	}

	public void setF100201(F100201 f100201) {
		this.f100201 = f100201;
	}
}
