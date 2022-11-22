package com.rakuten.r0601.ap;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
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
			Map<String,Object> map =  ActionContext.getContext().getSession();
			int companyId;
			if (null == map.get("comp")) {
				companyId = -1;
			} else {
				companyId = (int)map.get("comp");
			}
			conn = JdbcConnection.getConnection();

			sql = SqlUtility.getSql("SQLR0001024");

			String waybillNo = input.getWaybillNo();
			String logistics = input.getLogistics();
			String status = input.getStatus();
			String deliverDay = input.getDelieverDay();
			String receiveDay = input.getReceiveDay();
			String customs = input.getCustoms();
			String commodityId = input.getCommodityId();
			boolean flag = false;
			if(!Utility.isEmptyString(commodityId)) {
				if(commodityId.contains("-")) {
				} else {
					commodityId = commodityId + "-0-0";
				}
			}
            
			if (!Utility.isEmptyString(waybillNo)) {
				sql += " AND WAYBILL_NO in (select waybill_no from company_waybill_tbl where waybill_no = '"+waybillNo+"' AND (COMPANY_ID = ? OR ? = 0 OR ? = 1))";	
			} else {
				if(!Utility.isEmptyString(commodityId)) {
					flag = true;
					sql += " AND WAYBILL_NO in (select waybill_no from tbl00017 where COMMODITY_ID = '"+commodityId+"') ";
				} else {
					sql += " AND WAYBILL_NO in (select waybill_no from company_waybill_tbl where (COMPANY_ID = ? OR ? = 0 OR ? = 1))";	
				}
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
			if(!flag) {
				ps.setInt(1, (companyId==0||companyId==1)&&(input.getCompanyId()>0)?input.getCompanyId():companyId);
				ps.setInt(2, (companyId==0||companyId==1)&&(input.getCompanyId()>0)?input.getCompanyId():companyId);
				ps.setInt(3, (companyId==0||companyId==1)&&(input.getCompanyId()>0)?input.getCompanyId():companyId);				
			}
			ResultSet rs = ps.executeQuery();
			
			flag = false;
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
				SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
				output.setCreateTime(ft.format(rs.getDate("CREATE_TIME")));
				output.setUpdateTime(ft.format(rs.getDate("UPDATE_TIME")));
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
