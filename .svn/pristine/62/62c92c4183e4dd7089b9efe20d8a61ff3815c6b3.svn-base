package com.rakuten.r0601.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.rakuten.r0601.bean.AddWayBillApDetailInput;
import com.rakuten.r0601.bean.AddWayBillApInput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class AddWayBillAp {

	public boolean execute(AddWayBillApInput input) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = null;
		String date = format.format(new Date());
		try {
			conn = JdbcConnection.getConnection();

			List<AddWayBillApDetailInput> detailList = input.getDetailList();

			sql = SqlUtility.getSql("SQLR0001022");
			ps = conn.prepareStatement(sql);

			for (int i = 0; i < detailList.size(); i++) {
				AddWayBillApDetailInput detail = detailList.get(i);
				ps.setString(1, input.getWaybillNo());
				ps.setString(2, detail.getCommodityId());
				ps.setString(3, detail.getRemarks());
				ps.setString(4, detail.getQuantity());
				ps.setString(5, "0");
				ps.setString(6, date);
				ps.setString(7, "123mart");
				ps.setString(8, date);
				ps.setString(9, "123mart");
				ps.execute();
			}

			sql = SqlUtility.getSql("SQLR0001023");
			ps = conn.prepareStatement(sql);
			ps.setString(1, input.getWaybillNo());
			ps.setString(2, input.getLogistics());
			ps.setString(3, input.getStatus());
			ps.setString(4, input.getDelieverDay());
			ps.setString(5, input.getReceiveDay());
			ps.setString(6, input.getWeight());
			ps.setString(7, input.getFreight());
			ps.setString(8, "0");
			ps.setString(9, "0");
			ps.setString(10, date);
			ps.setString(11, "123mart");
			ps.setString(12, date);
			ps.setString(13, "123mart");
			ps.execute();

			// commit
			conn.commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
