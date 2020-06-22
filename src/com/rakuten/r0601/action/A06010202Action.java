package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.AddWayBillAp;
import com.rakuten.r0601.bean.AddWayBillApDetailInput;
import com.rakuten.r0601.bean.AddWayBillApInput;
import com.rakuten.r0601.form.CommodityList;
import com.rakuten.r0601.form.F060102;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;
import com.rakuten.util.Utility;

public class A06010202Action extends BaseAction {
	private static final long serialVersionUID = 1L;

	private F060102 f060102 = null;
	private String passFlg = null;

	protected void exec() throws Exception {
		List<CommodityList> commodityList = f060102.getCommodityList();
		AddWayBillAp addWayBillAp = new AddWayBillAp();
		AddWayBillApInput input = new AddWayBillApInput();
		input.setWaybillNo(f060102.getWaybillNo());
		input.setLogistics(f060102.getLogistics());
		input.setDelieverDay(f060102.getDeliverDay());
		input.setReceiveDay("");
		input.setStatus("00");
		if (Utility.isEmptyString(f060102.getWeight())) {
			input.setWeight("0");
		} else {
			input.setWeight(f060102.getWeight());
		}
		input.setFreight(f060102.getFreight());

		List<AddWayBillApDetailInput> inputList = new ArrayList<AddWayBillApDetailInput>();
		input.setDetailList(inputList);

		if (Utility.isEmptyString(f060102.getHid_waybillNo())) {
			f060102.setHid_waybillNo(f060102.getWaybillNo());
		}
		deleteComm2();

		if (getSessionAttribute("hassou") != null) {
			AddWayBillApInput input1 = (AddWayBillApInput) getSessionAttribute("hassou");
			List<AddWayBillApDetailInput> detailList = input1.getDetailList();
			for (int i = 0; i < detailList.size(); i++) {
				deleteComm1(detailList.get(i).getCommodityId());
			}
		}
		if (f060102.getCommodityList() != null) {

			List<CommodityList> commodityList2 = f060102.getCommodityList();
			for (int i = 0; i < commodityList2.size(); i++) {
				deleteComm1(commodityList2.get(i).getCommodityId());
			}
		}

		AddWayBillApDetailInput detail = null;
		for (int i = 0; i < commodityList.size(); i++) {
			detail = new AddWayBillApDetailInput();
			detail.setCommodityId(commodityList.get(i).getCommodityId());
			detail.setQuantity(commodityList.get(i).getQuantity());
			detail.setRemarks(commodityList.get(i).getRemarks());
			inputList.add(detail);
		}

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();

			for (int i = 0; i < commodityList.size(); i++) {
				String commId = "";
				String detailNo = "";
				if (commodityList.get(i).getCommodityId().contains("-")) {
					commId = commodityList
							.get(i)
							.getCommodityId()
							.substring(
									0,
									commodityList.get(i).getCommodityId()
											.indexOf("-"));
					detailNo = commodityList
							.get(i)
							.getCommodityId()
							.substring(
									commodityList.get(i).getCommodityId()
											.indexOf("-"));
				} else {
					commId = commodityList.get(i).getCommodityId();
				}

				sql = "UPDATE TBL00012 SET STOCK_SH = STOCK_SH - ? WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, commodityList.get(i).getQuantity());
				ps.setString(2, commId);
				ps.setString(3, detailNo);
				ps.execute();
			}

			if (addWayBillAp.execute(input)) {
				setPassFlg("1");
			} else {
				addError(null, "系统错误");
				return;
			}
			// commit
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
		setTitle("V060102:添加发货单");
	}

	protected void isValidated() throws Exception {
		if (Utility.isEmptyString(f060102.getDeliverDay())) {
			addError("f060102.deliverDay", "请填写发货日！");
		}
		if (Utility.isEmptyString(f060102.getWaybillNo())) {
			addError("f060102.waybillNo", "请填写运单号！");
		}
		List<CommodityList> commodityList = f060102.getCommodityList();
		if (Utility.isEmptyList(commodityList)) {
			addError(null, "详细列表为空！");
		}

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();

			for (int i = 0; i < commodityList.size(); i++) {
				String commId = "";
				String detailNo = "";
				int stocksh = 0;
				if (commodityList.get(i).getCommodityId().contains("-")) {
					commId = commodityList
							.get(i)
							.getCommodityId()
							.substring(
									0,
									commodityList.get(i).getCommodityId()
											.indexOf("-"));
					detailNo = commodityList
							.get(i)
							.getCommodityId()
							.substring(
									commodityList.get(i).getCommodityId()
											.indexOf("-"));
				} else {
					commId = commodityList.get(i).getCommodityId();
				}

				sql = "SELECT STOCK_SH FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, commId);
				ps.setString(2, detailNo);
				rs = ps.executeQuery();

				while (rs.next()) {
					stocksh = rs.getInt("STOCK_SH");
				}
				int kosu = Integer.valueOf(commodityList.get(i).getQuantity());
				if (stocksh - kosu < 0) {
					addError(null, commodityList.get(i).getCommodityId()
							+ "上海库存不足，无法发货");
				}

				// commit
				// conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public F060102 getF060102() {
		return f060102;
	}

	public void setF060102(F060102 f060102) {
		this.f060102 = f060102;
	}

	public String getPassFlg() {
		return passFlg;
	}

	public void setPassFlg(String passFlg) {
		this.passFlg = passFlg;
	}

	private boolean deleteComm1(String commodityId) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = SqlUtility.getSql("SQLR0001027");
			ps = conn.prepareStatement(sql);

			ps.setString(1, f060102.getHid_waybillNo());
			ps.setString(2, commodityId);
			ps.executeUpdate();

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

	private boolean deleteComm2() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "DELETE FROM TBL00013 WHERE WAYBILL_NO = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, f060102.getHid_waybillNo());
			ps.executeUpdate();

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
