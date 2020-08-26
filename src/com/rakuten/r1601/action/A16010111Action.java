package com.rakuten.r1601.action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1601.form.F160101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A16010111Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private F160101 f160101 = null;
	private File inputCommodityFilePath = null;

	protected void exec() throws Exception {
		List<String[]> itemNoList = Utility.readCsvFile(inputCommodityFilePath, false);
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "";
		StringBuilder condition = new StringBuilder();
		StringBuilder conditionWithDetailNo = new StringBuilder();
		StringBuilder commdityId = new StringBuilder();
		Map<String, String> commdityIdMap = new HashMap<String, String>();
		try {
			
			for(String[] itemNo : itemNoList){
				if (itemNo[0].indexOf("-") == -1) {
					condition.append("'").append(itemNo[0]).append("'").append(",");
					commdityId.append("'").append(itemNo[0]).append("'").append(",");
					commdityIdMap.put(itemNo[0], itemNo[0]);
				} else {
					commdityId.append("'").append(itemNo[0].substring(0, itemNo[0].indexOf("-"))).append("'").append(",");
					conditionWithDetailNo.append("'").append(itemNo[0]).append("'").append(",");
					commdityIdMap.put(itemNo[0].substring(0, itemNo[0].indexOf("-")), itemNo[0].substring(0, itemNo[0].indexOf("-")));
				}
            }
			
			conn = JdbcConnection.getConnection();
			int count = 0;
			if (condition.length() > 0) {
				condition.setLength(condition.length() - 1);
				sql = "delete from tbl00012 where COMMODITY_ID IN (" + condition + ")";
				ps = conn.prepareStatement(sql);
				count = ps.executeUpdate();
				ps.close();
			}
			if (conditionWithDetailNo.length() > 0) {
				conditionWithDetailNo.setLength(conditionWithDetailNo.length() - 1);
				sql = "delete from tbl00012 WHERE concat(commodity_id,detail_no) IN (" + conditionWithDetailNo + ")";
				ps = conn.prepareStatement(sql);
				count+=ps.executeUpdate();
				ps.close();
			}
			conn.commit();
			
			if (count > 0) {
				if (commdityId.length() > 0) {
					commdityId.setLength(commdityId.length() - 1);
					sql = "SELECT count(*) COUNT, COMMODITY_ID from tbl00012 where COMMODITY_ID IN (" + commdityId + ") group by COMMODITY_ID";
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					while (rs.next()) {
						if(rs.getInt("COUNT") > 0) {
							commdityIdMap.remove(rs.getString("COMMODITY_ID"));
						}
					}
					rs.close();
					ps.close();
					commdityId = new StringBuilder();
					for(String cId : commdityIdMap.keySet()) {
						commdityId.append("'").append(cId).append("'").append(",");
					}
					
					if(commdityId.length() == 0) {
						
					} else {
						commdityId.setLength(commdityId.length() - 1);
						sql = "delete from tbl00011 where COMMODITY_ID IN (" + commdityId + ")";
						ps = conn.prepareStatement(sql);
						ps.executeUpdate();
						ps.close();
						conn.commit();
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
			addError(null, "操作終わった");
		}
	}

	protected void init() {
		setTitle("V160101:小工具");

	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F160101 getF160101() {
		return f160101;
	}

	public void setF160101(F160101 f160101) {
		this.f160101 = f160101;
	}

	public File getInputCommodityFilePath() {
		return inputCommodityFilePath;
	}

	public void setInputCommodityFilePath(File inputCommodityFilePath) {
		this.inputCommodityFilePath = inputCommodityFilePath;
	}

}
