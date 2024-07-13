package com.rakuten.r1301.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r1301.form.F130101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A13010104Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F130101 f130101 = null;
	private String successFlg = null;
	private List<String> errmsg = new ArrayList<String>();

	protected void exec() throws Exception {
		String juchubango = f130101.getInputJuchubango().trim();

		f130101.setInputJuchubango(juchubango);
		String denpyoubango = f130101.getInputDenpyobango().trim();

		sakujo(juchubango, denpyoubango);

	}

	protected void init() throws Exception {
		setTitle("V130101:発送処理一覧");
	}

	private String getjuchubango(String bango, String shubetsu) throws Exception {
		String juchubango = "";
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT chumonbango from  common_order_tbl where chumonbango like ? and chumonsts1 <> '3' and site =?";
			ps = conn.prepareStatement(sql);
			if ("O".equals(shubetsu)) {
				ps.setString(1, "%" + bango.replace("O", ""));
				ps.setString(2, "楽天");
			} else if ("P".equals(shubetsu)) {
				ps.setString(1, "%" + bango.replace("P", ""));
				ps.setString(2, "ポンパレモール");
			}
			rs = ps.executeQuery();
			while (rs.next()) {
				juchubango = rs.getString("chumonbango");
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return juchubango;
	}

	private void sakujo(String orderNo, String toiawasebango) throws Exception {
		boolean result = true;

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			Map<String,Object> map =  ActionContext.getContext().getSession();
			int companyId;
			if (null == map.get("comp")) {
				companyId = -1;
			} else {
				companyId = (int)map.get("comp");
			}
			
			conn = JdbcConnection.getConnection();

			String haisohoho = "";
			String chumonbango = null;

			List<String[]> hasoList = new ArrayList<String[]>();

			sql = "SELECT HAISOUHOHO, CHUMONBANGO FROM common_order_tbl WHERE chumonbango in (select order_id from company_order_tbl where order_id = ? AND (COMPANY_ID = ? OR ? = 0 OR ? = 1))";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orderNo);
			ps.setInt(2, companyId);
			ps.setInt(3, companyId);
			ps.setInt(4, companyId);
			rs = ps.executeQuery();
			while (rs.next()) {
				haisohoho = rs.getString("HAISOUHOHO");
				chumonbango = rs.getString("CHUMONBANGO");
			}
			if (chumonbango != null) {
				if ("メール便".equals(haisohoho)) {
					haisohoho = "2";
				} else if (haisohoho.contains("宅")) {
					haisohoho = "1";
				}

				sql = "SELECT * FROM common_order_detail_tbl WHERE JUCHUBANGO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, orderNo);
				rs = ps.executeQuery();
				while (rs.next()) {
					hasoList.add(new String[] { rs.getString("SHOUHINBANGO"), rs.getString("KOSU") });
				}

				for (int i = 0; i < hasoList.size(); i++) {
					String shoribango = Utility.getShoribango();
					String[] shohin = hasoList.get(i);
					String shouhinbango = shohin[0];
					String kosu = shohin[1];
					sql = "SELECT STOCK_JP, STOCK_HANDUP FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, Utility.getCommodityId(shouhinbango));
					ps.setString(2, Utility.getDetailN0(shouhinbango));

					rs = ps.executeQuery();
					String stockjp = "0";
					String stockhandup = "0";
					while (rs.next()) {
						stockjp = rs.getString("STOCK_JP");
						stockhandup = rs.getString("STOCK_HANDUP");

					}
					if (Integer.valueOf(stockjp) - Integer.valueOf(kosu) < 0) {
						errmsg.add(shouhinbango + "在庫不足！");
						result = false;
						continue;
					}

					sql = "UPDATE TBL00012 SET STOCK_JP = ?, STOCK_HANDUP = ?, UPDATE_TIME = ? , UPDATE_USER = ?, UPDATEQUANTITY_FLG =TRUE WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);

					ps.setString(1, String.valueOf(Integer.valueOf(stockjp) - Integer.valueOf(kosu)));
					ps.setString(2, String.valueOf((Integer.valueOf(stockhandup) - Integer.valueOf(kosu)) > 0 ? Integer.valueOf(stockhandup) - Integer.valueOf(kosu) : 0));
					ps.setString(3, Utility.getDateTime());
					ps.setString(4, Utility.getUser());
					ps.setString(5, Utility.getCommodityId(shouhinbango));
					ps.setString(6, Utility.getDetailN0(shouhinbango));

					ps.execute();

					int j = 1;
					sql = "INSERT INTO hassou_tbl (SHORIBANGO,JUCHUBANGO,SHOUHINBANGO,HASSOUKOSU,TOIAWASEBANGO,SHUBETSU,UNSOKAISHA,HAISOHOHO,CREATE_TIME,CREATE_USER,UPDATE_TIME,UPDATE_USER)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(j++, shoribango);
					ps.setString(j++, orderNo);
					ps.setString(j++, shouhinbango);
					ps.setString(j++, kosu);
					ps.setString(j++, toiawasebango);
					ps.setString(j++, "0");
					ps.setString(j++, Utility.getUnsokaisha(orderNo));
					ps.setString(j++, haisohoho);
					ps.setString(j++, Utility.getDateTime());
					ps.setString(j++, Utility.getUser());
					ps.setString(j++, Utility.getDateTime());
					ps.setString(j++, Utility.getUser());
					ps.execute();
				}

				sql = "UPDATE common_order_tbl SET CHUMONSTS1 = ? WHERE CHUMONBANGO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, "3");
				ps.setString(2, orderNo);
				ps.executeUpdate();

				sql = "insert into tbl00024 values(?,?,?,?,?,?)";

				ps = conn.prepareStatement(sql);
				ps.setString(1, orderNo);
				ps.setString(2, "0");
				ps.setString(3, Utility.getDateTime());
				ps.setString(4, Utility.getUser());
				ps.setString(5, Utility.getDateTime());
				ps.setString(6, Utility.getUser());

				ps.execute();

				if (result) {
					conn.commit();
					setSuccessFlg(orderNo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	protected void isValidated() throws Exception {
		String juchubango = f130101.getInputJuchubango().trim();

		f130101.setInputJuchubango(juchubango);
		List<String> hasomachihasokaList = (List<String>) getSessionAttribute("hasomachiList");

		boolean ariFlg = false;
		f130101.setInputJuchubango(f130101.getInputJuchubango().trim().replace("123MART", "123mart"));

		for (String orderNo : hasomachihasokaList) {
			if (juchubango.equals(orderNo)) {
				ariFlg = true;
				break;
			}
		}
		if (!ariFlg) {
			addError(null, juchubango + "は普通発送可の注文ではありません");
		} else {
			Connection conn = null;
			PreparedStatement ps = null;
			String sql = null;
			ResultSet rs = null;
			String sts = null;
			try {
				conn = JdbcConnection.getConnection();

				sql = "SELECT CHUMONSTS1 FROM common_order_tbl WHERE CHUMONBANGO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, juchubango);
				rs = ps.executeQuery();
				while (rs.next()) {
					sts = rs.getString("chumonsts1");
				}

				if (!"2".equals(sts)) {
					addError(null, juchubango + "は普通発送可の注文ではありません");
				}
				
				if (StringUtils.isBlank(Utility.getUnsokaisha(juchubango))) {
					addError(null, juchubango + "の運送会社が設定されません");
				}

			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}
		}

	}

	@Override
	protected void fieldCheck() throws Exception {
		if (Utility.isEmptyString(f130101.getInputJuchubango())) {
			addError(null, "受注番号を入力してください！");
		}
		if (Utility.isEmptyString(f130101.getInputDenpyobango())) {
			addError(null, "伝票番号を入力してください！");
		}
	}

	/**
	 * @return the f130101
	 */
	public F130101 getF130101() {
		return f130101;
	}

	/**
	 * @param f130101 the f130101 to set
	 */
	public void setF130101(F130101 f130101) {
		this.f130101 = f130101;
	}

	public String getSuccessFlg() {
		return successFlg;
	}

	public void setSuccessFlg(String successFlg) {
		this.successFlg = successFlg;
	}

}
