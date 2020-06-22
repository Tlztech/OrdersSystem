package com.rakuten.r1505.action;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1505.common.A150501Common;
import com.rakuten.r1505.form.F150501;
import com.rakuten.r1505.form.MeisaiList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A15050104Action extends BaseAction {

	private static final long serialVersionUID = 1L;

	F150501 f150501 = null;
	String mode = null;

	protected void exec() throws Exception {
		A150501Common common = new A150501Common();
		common.keisan(f150501);
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;

		try {
			String seikyuid = f150501.getStartDay().replace("-", "");
			conn = JdbcConnection.getConnection();
			sql = "delete from qingqiushu where seikyuid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, seikyuid);
			ps.execute();

			sql = "delete from  qingqiushudetail where seikyuid = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, seikyuid);
			ps.execute();

			sql = "INSERT INTO qingqiushu"
					+ "(seikyuid,"
					+ "startDay,"
					+ "endDay,"
					+ "hiseikyusha,"
					+ "seikyukingaku,"
					+ "seikyusha1,"
					+ "seikyusha2,"
					+ "seikyusha3,"
					+ "seikyusha4,"
					+ "seikyusha5,"
					+ "seikyusha6,"
					+ "merubintanka,"
					+ "merubinkosu,"
					+ "merubinkingaku,"
					+ "takyubintanka,"
					+ "takyubinkosu,"
					+ "takyubinkingaku,"
					+ "tesuryotanka,"
					+ "tesuryokosu,"
					+ "tesuryokingaku,"
					+ "daibikitanka,"
					+ "daibikikingaku,"
					+ "daibikikosu,"
					+ "himoku1,"
					+ "tanka1,"
					+ "kosu1,"
					+ "kingaku1,"
					+ "himoku2,"
					+ "tanka2,"
					+ "kosu2,"
					+ "kingaku2,"
					+ "himoku3,"
					+ "tanka3,"
					+ "kosu3,"
					+ "kingaku3,"
					+ "himoku4,"
					+ "tanka4,"
					+ "kosu4,"
					+ "kingaku4,"
					+ "kokei,"
					+ "gokei,"
					+ "hozozumiFlg,"
					+ "jishu,"
					+ "CREATE_TIME,"
					+ "CREATE_USER,"
					+ "UPDATE_TIME,"
					+ "UPDATE_USER)"
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			ps = conn.prepareStatement(sql);
			int i = 0;
			ps.setString(++i, seikyuid);
			ps.setString(++i, f150501.getStartDay());
			ps.setString(++i, f150501.getEndDay());
			ps.setString(++i, f150501.getHiseikyusha());
			ps.setString(++i, f150501.getSeikyukingaku());
			ps.setString(++i, f150501.getSeikyusha1());
			ps.setString(++i, f150501.getSeikyusha2());
			ps.setString(++i, f150501.getSeikyusha3());
			ps.setString(++i, f150501.getSeikyusha4());
			ps.setString(++i, f150501.getSeikyusha5());
			ps.setString(++i, f150501.getSeikyusha6());
			ps.setString(++i, f150501.getMerubintanka());
			ps.setString(++i, f150501.getMerubinkosu());
			ps.setString(++i, f150501.getMerubinkingaku());
			ps.setString(++i, f150501.getTakyubintanka());
			ps.setString(++i, f150501.getTakyubinkosu());
			ps.setString(++i, f150501.getTakyubinkingaku());
			ps.setString(++i, f150501.getTesuryotanka());
			ps.setString(++i, f150501.getTesuryokosu());
			ps.setString(++i, f150501.getTesuryokingaku());
			ps.setString(++i, f150501.getDaibikitanka());
			ps.setString(++i, f150501.getDaibikikingaku());
			ps.setString(++i, f150501.getDaibikikosu());
			ps.setString(++i, f150501.getHimoku1());
			ps.setString(++i, f150501.getTanka1());
			ps.setString(++i, f150501.getKosu1());
			ps.setString(++i, f150501.getKingaku1());
			ps.setString(++i, f150501.getHimoku2());
			ps.setString(++i, f150501.getTanka2());
			ps.setString(++i, f150501.getKosu2());
			ps.setString(++i, f150501.getKingaku2());
			ps.setString(++i, f150501.getHimoku3());
			ps.setString(++i, f150501.getTanka3());
			ps.setString(++i, f150501.getKosu3());
			ps.setString(++i, f150501.getKingaku3());
			ps.setString(++i, f150501.getHimoku4());
			ps.setString(++i, f150501.getTanka4());
			ps.setString(++i, f150501.getKosu4());
			ps.setString(++i, f150501.getKingaku4());
			ps.setString(++i, f150501.getKokei());
			ps.setString(++i, f150501.getGokei());
			ps.setString(++i, f150501.getHozozumiFlg());
			ps.setString(++i, f150501.getJishu());
			ps.setString(++i, Utility.getDateTime());
			ps.setString(++i, Utility.getUser());
			ps.setString(++i, Utility.getDateTime());
			ps.setString(++i, Utility.getUser());

			ps.execute();

			sql = "INSERT INTO qingqiushudetail" + "(seikyuid," + "juchubango,"
					+ "shohinbango," + "tanka," + "kosu," + "kingaku,"
					+ "CREATE_TIME," + "CREATE_USER," + "UPDATE_TIME,"
					+ "UPDATE_USER)" + "VALUES (?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			for (MeisaiList meisai : f150501.getMeisaiList()) {
				i = 0;
				ps.setString(++i, seikyuid);
				ps.setString(++i, meisai.getJuchubango());
				ps.setString(++i, meisai.getShohinbango());
				String tanka = "0";
				if (!Utility.isEmptyString(meisai.getTanka())) {
					tanka = meisai.getTanka();
				}
				ps.setString(++i, tanka);
				ps.setString(++i, meisai.getKosu());
				ps.setString(++i, meisai.getKingaku());
				ps.setString(++i, Utility.getDateTime());
				ps.setString(++i, Utility.getUser());
				ps.setString(++i, Utility.getDateTime());
				ps.setString(++i, Utility.getUser());
				ps.execute();
			}
			conn.commit();
			f150501.setHozozumiFlg("1");
			addError(null, "保存成功");
			mode = seikyuid;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	protected void init() throws Exception {
		setTitle("V150501:请求书");
	}

	public F150501 getF150501() {
		return f150501;
	}

	public void setF150501(F150501 f150501) {
		this.f150501 = f150501;
	}

	protected void isValidated() throws Exception {
		if (Utility.isEmptyString(f150501.getEndDay())) {
			addError(null, "请求截止日不能为空");
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
