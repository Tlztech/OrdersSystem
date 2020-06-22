package com.rakuten.r0303.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0303.form.F030301;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A03030102Action extends BaseAction {

	private F030301 f030301 = null;
	private String shoriMode = null;
	private static final long serialVersionUID = 1L;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "";
		try {
			conn = JdbcConnection.getConnection();

			if ("0".equals(f030301.getKuwei())) {
				if ("0".equals(f030301.getJiajian())) {
					sql = "UPDATE TBL00012 SET STOCK_SH = STOCK_SH + ? , UPDATE_TIME = ? , UPDATE_USER = ? WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);
					ps.setDouble(1, Double.valueOf(f030301.getKosu()));
				} else {
					sql = "UPDATE TBL00012 SET STOCK_SH = STOCK_SH - ? , UPDATE_TIME = ? , UPDATE_USER = ? WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);
					ps.setDouble(1, Double.valueOf(f030301.getKosu()));
				}
			} else {
				if ("0".equals(f030301.getJiajian())) {
					sql = "UPDATE TBL00012 SET STOCK_JP = STOCK_JP + ? , UPDATE_TIME = ? , UPDATE_USER = ? WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);
					ps.setDouble(1, Double.valueOf(f030301.getKosu()));
				} else {
					sql = "UPDATE TBL00012 SET STOCK_JP = STOCK_JP - ? , UPDATE_TIME = ? , UPDATE_USER = ? WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
					ps = conn.prepareStatement(sql);
					ps.setDouble(1, Double.valueOf(f030301.getKosu()));
				}
			}

			ps.setString(2, Utility.getDateTime());
			ps.setString(3, Utility.getUser());
			ps.setString(4, Utility.getCommodityId(f030301.getShohinbango()));
			ps.setString(5, Utility.getDetailN0(f030301.getShohinbango()));
			int countupdate = ps.executeUpdate();

			sql = "INSERT INTO soneki (SHORIBANGO,SHOHINBANGO,TYPE,STOCK,KOSU,RIYU,CREATE_TIME,CREATE_USER) VALUES(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			int j = 0;
			ps.setString(++j, Utility.getShoribango());
			ps.setString(++j, f030301.getShohinbango());
			ps.setString(++j, f030301.getJiajian());
			ps.setString(++j, f030301.getKuwei());
			ps.setString(++j, f030301.getKosu());
			ps.setString(++j, f030301.getRiyu());
			ps.setString(++j, Utility.getDateTime());
			ps.setString(++j, Utility.getUser());
			int countinsert = ps.executeUpdate();

			if (countupdate == 0 || countinsert == 0) {
				addError(null, "操作失败");
			} else {
				// commit
				conn.commit();
				addActionError(f030301.getShohinbango() + " 操作成功");
				f030301 = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	protected void init() {
		setTitle("V030301:添加损益");
	}

	protected void isValidated() throws Exception {
		if (!Utility.isShohinExist(f030301.getShohinbango())) {
			addError(null, "商品编号不存在");
		}
		if ("1".equals(f030301.getJiajian())) {
			Connection conn = null;
			PreparedStatement ps = null;
			String sql = "";
			try {
				conn = JdbcConnection.getConnection();
				sql = "SELECT * FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1,
						Utility.getCommodityId(f030301.getShohinbango()));
				ps.setString(2, Utility.getDetailN0(f030301.getShohinbango()));
				ResultSet rs = ps.executeQuery();
				int count = 0;
				while (rs.next()) {
					if ("0".equals(f030301.getKuwei())) {
						count = rs.getInt("STOCK_SH")
								- Integer.valueOf(f030301.getKosu());
					} else {
						count = rs.getInt("STOCK_JP")
								- Integer.valueOf(f030301.getKosu());
					}
				}
				if (count < 0) {
					addError(null, f030301.getShohinbango() + "库存不足无法扣除");
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
		if (Utility.isEmptyString(f030301.getRiyu())) {
			addError(null, "请填写损益原因");
		}
		if (Utility.isEmptyString(f030301.getShohinbango())) {
			addError(null, "请填写商品编号");
		}
		if (Utility.isEmptyString(f030301.getKosu())) {
			addError(null, "请填写个数");
		}
		if (Utility.isEmptyString(f030301.getJiajian())) {
			addError(null, "请填写加减");
		}
		if (Utility.isEmptyString(f030301.getKosu())) {
			addError(null, "请填写库位");
		}

	}

	/**
	 * @return the shoriMode
	 */
	public String getShoriMode() {
		return shoriMode;
	}

	/**
	 * @param shoriMode
	 *            the shoriMode to set
	 */
	public void setShoriMode(String shoriMode) {
		this.shoriMode = shoriMode;
	}

	public F030301 getF030301() {
		return f030301;
	}

	public void setF030301(F030301 f030301) {
		this.f030301 = f030301;
	}

}
