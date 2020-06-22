package com.rakuten.r0601.ap;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.r0601.bean.AddWayBillApDetailInput;
import com.rakuten.r0601.bean.AddWayBillApInput;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;

public class UpdWayBillAp {

	public boolean execute(AddWayBillApInput input) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String sql = null;
		String date = format.format(new Date());
		try {
			conn = JdbcConnection.getConnection();

			List<AddWayBillApDetailInput> detailList = input.getDetailList();

			AddWayBillApInput sessionInput = (AddWayBillApInput) getSessionAttribute("hassou");
			List<AddWayBillApDetailInput> sessionDetailList = sessionInput
					.getDetailList();

			sql = SqlUtility.getSql("SQLR0001026");
			ps = conn.prepareStatement(sql);
			ps.setString(1, input.getDelieverDay());
			ps.setString(2, input.getLogistics());
			// todo
			ps.setString(3, input.getFreight());
			ps.setBigDecimal(4, new BigDecimal(input.getWeight()));

			ps.setString(5, input.getWaybillNo());
			ps.execute();

			for (int i = 0; i < detailList.size(); i++) {
				sql = "SELECT DEL_FLG FROM TBL00014 WHERE WAYBILL_NO = ? AND COMMODITY_ID = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, input.getWaybillNo());
				ps.setString(2, detailList.get(i).getCommodityId());

				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					detailList.get(i).setDelFlg(rs.getString("DEL_FLG"));
				}
			}

			for (int i = 0; i < sessionDetailList.size(); i++) {

				sql = SqlUtility.getSql("SQLR0001027");
				ps = conn.prepareStatement(sql);
				ps.setString(1, sessionInput.getWaybillNo());
				ps.setString(2, sessionDetailList.get(i).getCommodityId());
				ps.executeUpdate();
			}

			sql = SqlUtility.getSql("SQLR0001022");
			ps = conn.prepareStatement(sql);

			for (int i = 0; i < detailList.size(); i++) {
				AddWayBillApDetailInput detail = detailList.get(i);
				ps.setString(1, input.getWaybillNo());
				ps.setString(2, detail.getCommodityId());
				ps.setString(3, detail.getRemarks());
				ps.setString(4, detail.getQuantity());
				ps.setString(5, detail.getDelFlg());
				ps.setString(6, date);
				ps.setString(7, "123mart");
				ps.setString(8, date);
				ps.setString(9, "123mart");
				ps.execute();
			}

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

	private Object getSessionAttribute(String key) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> sessionMap = actionContext.getSession();
		return sessionMap.get(key);
	}
}
