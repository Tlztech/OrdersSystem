package com.rakuten.r0601.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.r0601.bean.GetWayBillApInput;
import com.rakuten.r0601.bean.GetWayBillApOutput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;
import com.rakuten.util.Utility;

public class GetWayBillAp {

	public List<GetWayBillApOutput> execute(GetWayBillApInput input)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();

			sql = SqlUtility.getSql("SQLR0001024");

			String waybillNo = input.getWaybillNo();
			String logistics = input.getLogistics();
			String status = input.getStatus();
			String deliverDay = input.getDelieverDay();
			String receiveDay = input.getReceiveDay();
			String customs = input.getCustoms();

			if (!Utility.isEmptyString(waybillNo)) {
				sql += " AND WAYBILL_NO = '" + waybillNo + "'";
			}
			if (!Utility.isEmptyString(logistics) && !"04".equals(logistics)) {
				sql += " AND LOGISTICS = '" + logistics + "'";
			}
			if ("2".equals(status)) {
				sql += " AND STATUS = '10'";
			} else if ("3".equals(status)) {
				sql += " AND STATUS = '00'";
			}
			if (!Utility.isEmptyString(deliverDay)) {
				sql += " AND DELIVER_DAY = '" + deliverDay + "'";
			}
			if (!Utility.isEmptyString(receiveDay)) {
				sql += " AND RECEIVE_DAY = '" + receiveDay + "'";
			}
			if ("1".equals(customs)) {
				sql += " AND CUSTOMS = 0";
			} else if ("2".equals(customs)) {
				sql += " AND CUSTOMS >0";
			}
			sql += " ORDER BY DELIVER_DAY DESC";

			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			List<GetWayBillApOutput> outputList = new ArrayList<GetWayBillApOutput>();
			GetWayBillApOutput output = null;
			while (rs.next()) {
				output = new GetWayBillApOutput();
				outputList.add(output);
				output.setWaybillNo(rs.getString("WAYBILL_NO"));
				output.setLogistics(rs.getString("LOGISTICS"));
				output.setStatus(rs.getString("STATUS"));
				output.setDeliverDay(rs.getString("DELIVER_DAY"));
				output.setReceiveDay(rs.getString("RECEIVE_DAY"));
				output.setWeight(rs.getString("WEIGHT"));
				output.setFreight(rs.getString("FREIGHT"));
				output.setCustoms(rs.getString("CUSTOMS"));
			}

			// commit
			conn.commit();
			return outputList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
