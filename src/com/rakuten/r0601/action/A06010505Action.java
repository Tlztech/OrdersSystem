package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.form.F060105;
import com.rakuten.r0601.form.MeisaiList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A06010505Action extends BaseAction {
	private static final long serialVersionUID = 1L;

	F060105 f060105 = null;

	protected void exec() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			conn = JdbcConnection.getConnection();
			sql = "delete from baoguandan where waybillno = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, f060105.getWayBillNo());
			ps.execute();

			sql = "delete from baoguandandetail where waybillno = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, f060105.getWayBillNo());
			ps.execute();

			sql = "INSERT INTO baoguandan ( waybillno, weight, dimensions, dateofexportation, rcompanyname, raddress, rcity, rprovince, rcountry, rpostcode, rcontactname, rtelephone, scompanyname, saddress, scity, sprovince, scountry, spostcode, scontactname, stelephone, totalpcs, totalvalue, CREATE_TIME, CREATE_USER, UPDATE_TIME, UPDATE_USER ) VALUES ( ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			ps = conn.prepareStatement(sql);
			int i = 0;
			ps.setString(++i, f060105.getWayBillNo());
			ps.setString(++i, f060105.getWeight());
			ps.setString(++i, f060105.getDimensions());
			ps.setString(++i, f060105.getDateOfExportation());
			ps.setString(++i, f060105.getRcompanyName());
			ps.setString(++i, f060105.getRaddress());
			ps.setString(++i, f060105.getRcity());
			ps.setString(++i, f060105.getRprovince());
			ps.setString(++i, f060105.getRcountry());
			ps.setString(++i, f060105.getRpostcode());
			ps.setString(++i, f060105.getRcontactName());
			ps.setString(++i, f060105.getRtelephone());
			ps.setString(++i, f060105.getScompanyName());
			ps.setString(++i, f060105.getSaddress());
			ps.setString(++i, f060105.getScity());
			ps.setString(++i, f060105.getSprovince());
			ps.setString(++i, f060105.getScountry());
			ps.setString(++i, f060105.getSpostcode());
			ps.setString(++i, f060105.getScontactName());
			ps.setString(++i, f060105.getStelephone());
			ps.setString(++i, f060105.getTotalPcs());
			ps.setString(++i, f060105.getTotalValue());
			ps.setString(++i, Utility.getDateTime());
			ps.setString(++i, Utility.getUser());
			ps.setString(++i, Utility.getDateTime());
			ps.setString(++i, Utility.getUser());
			ps.execute();

			sql = "INSERT INTO baoguandandetail (waybillno, DESCRIPTION, PCS, ORIGIN, UNITVALUE, TOTALVALUE, CREATE_TIME, CREATE_USER, UPDATE_TIME, UPDATE_USER) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
			ps = conn.prepareStatement(sql);
			
			for (MeisaiList meisai : f060105.getMeisaiList()) {
				i = 0;
				ps.setString(++i, f060105.getWayBillNo());
				ps.setString(++i, meisai.getDescription());
				ps.setString(++i, meisai.getPcs());
				ps.setString(++i, meisai.getOrigin());
				ps.setString(++i, meisai.getUnitValue());
				ps.setString(++i, meisai.getTotalValue());
				ps.setString(++i, Utility.getDateTime());
				ps.setString(++i, Utility.getUser());
				ps.setString(++i, Utility.getDateTime());
				ps.setString(++i, Utility.getUser());
				ps.execute();
			}
			conn.commit();
			addError(null, "保存成功");
			f060105.setHozozumiFlg("1");
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	protected void init() {
		setTitle("V060105:报关单");

	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		List<MeisaiList> meisaiList = f060105.getMeisaiList();
		if (Utility.isEmptyList(meisaiList)) {
			addError(null, "没有明细");
		}
		List<MeisaiList> shoriList = new ArrayList<MeisaiList>();
		for (int i = 0; i < meisaiList.size(); i++) {
			MeisaiList meisai = meisaiList.get(i);
			if (!Utility.isEmptyString(meisai.getDescription())
					|| !Utility.isEmptyString(meisai.getOrigin())
					|| !Utility.isEmptyString(meisai.getPcs())
					|| !Utility.isEmptyString(meisai.getTotalValue())
					|| !Utility.isEmptyString(meisai.getUnitValue())
					|| !Utility.isEmptyString(meisai.getTotalValue())) {
				shoriList.add(meisai);
				if (Utility.isEmptyString(meisai.getDescription())) {
					addError("f060105.meisaiList[" + i + "].description",
							"Description是必须项目");
				}
				if (Utility.isEmptyString(meisai.getPcs())) {
					addError("f060105.meisaiList[" + i + "].pcs", "PCS是必须项目");
				}
				if (Utility.isEmptyString(meisai.getOrigin())) {
					addError("f060105.meisaiList[" + i + "].origin",
							"ORIGIN是必须项目");
				}
				if (Utility.isEmptyString(meisai.getUnitValue())) {
					addError("f060105.meisaiList[" + i + "].unitValue",
							"UNIT VALUE是必须项目");
				}if (Utility.isEmptyString(meisai.getTotalValue())) {
					addError("f060105.meisaiList[" + i + "].totalValue",
							"TOTAL VALUE是必须项目");
				}

			}

		}
		f060105.setMeisaiList(shoriList);

	}

	public F060105 getF060105() {
		return f060105;
	}

	public void setF060105(F060105 f060105) {
		this.f060105 = f060105;
	}

}
