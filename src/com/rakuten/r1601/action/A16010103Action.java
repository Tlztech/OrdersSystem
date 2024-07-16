package com.rakuten.r1601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r1601.form.F160101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A16010103Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	F160101 f160101 = null;

	protected void exec() throws Exception {
		String chumonbango = f160101.getChumonbango();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String shouhinbango = "";
		String kosu = "0";
		
		try {
			Map<String,Object> map =  ActionContext.getContext().getSession();
			int companyId;
			if (null == map.get("comp")) {
				companyId = -1;
			} else {
				companyId = (int)map.get("comp");
			}
			
			conn = JdbcConnection.getConnection();
			
			sql = "select t2.SHOUHINBANGO, t2.KOSU  from common_order_tbl t1 join common_order_detail_tbl t2 on t1.CHUMONBANGO = t2.JUCHUBANGO  where t1.CHUMONBANGO = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, chumonbango);
			rs = ps.executeQuery();
			while (rs.next()) {
				if (!Utility.isEmptyString(rs.getString("SHOUHINBANGO"))) {
					shouhinbango = rs.getString("SHOUHINBANGO");
					kosu = rs.getString("KOSU");
					
					if (!shouhinbango.contains("-")) {
						shouhinbango = shouhinbango + "-0-0";
					}
						
					String sql2 = null;
					ResultSet rs2 = null;
					PreparedStatement ps2 = null;
					sql2 = "select STOCK_HANDUP from TBL00012 where concat(COMMODITY_ID, DETAIL_NO) = ?";
					ps2 = conn.prepareStatement(sql2);
					ps2.setString(1, shouhinbango);
					rs2 = ps2.executeQuery();
					int handupamount = 0;
					
					while (rs2.next()) {
						if (!Utility.isEmptyString(rs2.getString("STOCK_HANDUP"))) {
							handupamount = Integer.valueOf(rs2.getString("STOCK_HANDUP"))-Integer.valueOf(kosu)>0 ? Integer.valueOf(rs2.getString("STOCK_HANDUP"))-Integer.valueOf(kosu) : 0;
						}
						String sql3 = null;
						ResultSet rs3 = null;
						PreparedStatement ps3 = null;
						
						sql3 = "update TBL00012 SET STOCK_HANDUP=?, update_time = ?, update_user = ? where concat(COMMODITY_ID, DETAIL_NO) = ?";
						ps3 = conn.prepareStatement(sql3);
						ps3.setInt(1, handupamount);
						ps3.setString(2, Utility.getDateTime());
						ps3.setString(3, "deleteOne");
						ps3.setString(4, shouhinbango);
						ps3.executeUpdate();
					}
				}
			}

			sql = "delete from tbl00027 where chumonbango in (select order_id from company_order_tbl where order_id = ? AND (COMPANY_ID = ? OR ? = 0 OR ? = 1))";
			ps = conn.prepareStatement(sql);
			ps.setString(1, chumonbango);
			ps.setInt(2, companyId);
			ps.setInt(3, companyId);
			ps.setInt(4, companyId);
			ps.executeUpdate();
			sql = "delete from common_order_tbl where chumonbango in (select order_id from company_order_tbl where order_id = ? AND (COMPANY_ID = ? OR ? = 0 OR ? = 1))";
			ps = conn.prepareStatement(sql);
			ps.setString(1, chumonbango);
			ps.setInt(2, companyId);
			ps.setInt(3, companyId);
			ps.setInt(4, companyId);
			int count1 = ps.executeUpdate();
			sql = "delete from common_order_detail_tbl where juchubango in (select order_id from company_order_tbl where order_id = ? AND (COMPANY_ID = ? OR ? = 0 OR ? = 1))";
			ps = conn.prepareStatement(sql);
			ps.setString(1, chumonbango);
			ps.setInt(2, companyId);
			ps.setInt(3, companyId);
			ps.setInt(4, companyId);
			ps.executeUpdate();
			sql = "delete from hassou_tbl where juchubango in (select order_id from company_order_tbl where order_id = ? AND (COMPANY_ID = ? OR ? = 0 OR ? = 1))";
			ps = conn.prepareStatement(sql);
			ps.setString(1, chumonbango);
			ps.setInt(2, companyId);
			ps.setInt(3, companyId);
			ps.setInt(4, companyId);
			ps.executeUpdate();
			sql = "delete from company_order_tbl where order_id = ? AND (COMPANY_ID = ? OR ? = 0 OR ? = 1) ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, chumonbango);
			ps.setInt(2, companyId);
			ps.setInt(3, companyId);
			ps.setInt(4, companyId);
			ps.executeUpdate();
			
			if (count1 == 1) {
				addError(null, "操作成功");
				conn.commit();
			} else {
				addError(null, "操作失败");
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
		setTitle("V160101:小工具");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public F160101 getF160101() {
		return f160101;
	}

	public void setF160101(F160101 f160101) {
		this.f160101 = f160101;
	}

}
