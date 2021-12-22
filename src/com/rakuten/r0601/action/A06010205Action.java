package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.AddWayBillAp;
import com.rakuten.r0601.bean.AddWayBillApDetailInput;
import com.rakuten.r0601.bean.AddWayBillApInput;
import com.rakuten.r0601.form.CommodityList;
import com.rakuten.r0601.form.F060102;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;
import com.rakuten.util.Utility;

public class A06010205Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	F060102 f060102 = null;
	private String passFlg = null;

	protected void exec() throws Exception {

		List<CommodityList> commodityList = f060102.getCommodityList();
		AddWayBillAp addWayBillAp = new AddWayBillAp();
		AddWayBillApInput input = new AddWayBillApInput();
		input.setWaybillNo(f060102.getWaybillNo());
		input.setLogistics(f060102.getLogistics());
		input.setDelieverDay(f060102.getDeliverDay());
		input.setReceiveDay("");
		input.setStatus("20");
		if (Utility.isEmptyString(f060102.getWeight())) {
			input.setWeight("0");
		} else {
			input.setWeight(f060102.getWeight());
		}
		input.setFreight(f060102.getFreight());

		Map<String, Object> map = ActionContext.getContext().getSession();
		int companyId;
		if (null == map.get("comp")) {
			companyId = -1;
		} else {
			companyId = (int) map.get("comp");
		}
		
		List<AddWayBillApDetailInput> inputList = new ArrayList<AddWayBillApDetailInput>();
		input.setDetailList(inputList);
		AddWayBillApDetailInput detail = null;
		for (int i = 0; i < commodityList.size(); i++) {
			detail = new AddWayBillApDetailInput();
			detail.setCommodityId(commodityList.get(i).getCommodityId());
			detail.setQuantity(commodityList.get(i).getQuantity());
			detail.setRemarks(commodityList.get(i).getRemarks());
			inputList.add(detail);
		}

		if (Utility.isEmptyString(f060102.getHid_waybillNo())) {
			f060102.setHid_waybillNo(f060102.getWaybillNo());
			createCompanyWaybillRelation(companyId==0?1:companyId, f060102.getWaybillNo());
		} else {
			input.setWaybillNo(f060102.getHid_waybillNo());
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
		}

		if (addWayBillAp.execute(input)) {
			setPassFlg("2");
		} else {
			addError(null, "系统错误");
			return;
		}
	}

	protected void init() {
		setTitle("V060102:添加发货单 ");
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

		if (Utility.isEmptyString(f060102.getHid_waybillNo()) && existWaybillNo(f060102.getWaybillNo())) {
			addError(null, "运单" + f060102.getWaybillNo() + "已存在");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	/**
	 * @return the f060102
	 */
	public F060102 getF060102() {
		return f060102;
	}

	/**
	 * @param f060102 the f060102 to set
	 */
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
	
	private boolean existWaybillNo(String waybillNo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		boolean exist = true;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT COUNT(*) COUNT FROM TBL00013 WHERE WAYBILL_NO = ?";
			ps = conn.prepareStatement(sql);

			ps.setString(1, waybillNo);
			rs = ps.executeQuery();
			Long count = 0l;
			while (rs.next()) {
				count = rs.getLong("COUNT");
			}

			if (count == 0) {
				exist = false;
			}

			// commit
			conn.commit();
			return exist;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	private void createCompanyWaybillRelation(int companyId, String waybillNo) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();
			sql ="INSERT INTO company_waybill_tbl (`company_id`,`waybill_no`,`permission`) VALUES(?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, companyId);
			ps.setString(2, waybillNo);
			ps.setInt(3, 3);
			ps.execute();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}
}
