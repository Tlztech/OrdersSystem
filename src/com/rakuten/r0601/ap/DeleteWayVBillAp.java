package com.rakuten.r0601.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.util.JdbcConnection;

public class DeleteWayVBillAp {

	public int execute(String deliverDay) throws Exception  {
		int result = 0;
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		String sql1 = null;
		String sql2 = null;
		int rs = 0;
		int rs1 = 0;
		try {
			conn = JdbcConnection.getConnection();
			sql1 = "DELETE T1.* FROM tbl00017 AS T1 INNER JOIN tbl00013 AS T2 ON T1.WAYBILL_NO = T2.WAYBILL_NO WHERE (T2.DELIVER_DAY <='" + deliverDay + "' and T2.DELIVER_DAY != '')";
			ps1 = conn.prepareStatement(sql1);
			rs = ps1.executeUpdate(sql1);
			sql2 = "DELETE FROM tbl00013 where DELIVER_DAY <= '"+ deliverDay + "' and DELIVER_DAY != ''";
			ps2 = conn.prepareStatement(sql2);
			rs1 = ps1.executeUpdate(sql2);
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		if(rs == 0 && rs1 == 0) {
			result = 0;
		} else {
			result = 1;
		}
		return result;
	}

}
