package com.rakuten.r0801.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0801.bean.ViviCommodityInfo;
import com.rakuten.r0801.common.A0801Common;
import com.rakuten.r0801.form.F080101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A08010102Action extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String site = null;
	private F080101 f080101 = null;
	private String logKey = null;
	private A0801Common a0801Common = new A0801Common();

	protected void exec() throws Exception {
		site = f080101.getPtype();
		if ("01".equals(site)) {
			getCommodityVivi(f080101.getRinku());
		}
	}

	/**
	 * 获取当月vivi网站商品信息
	 * 
	 * @throws Exception
	 */
	private void getCommodityVivi(String url) throws Exception {
		List<ViviCommodityInfo> infoList = a0801Common.getViviInfoList(url,
				logKey);
		a0801Common.insertDB(infoList, f080101.getShumokumeishou(),
				f080101.getRinku());
	}

	protected void init() {
		setTitle("V080101:生成商品清单");

	}

	protected void isValidated() throws Exception {
		String meishou = f080101.getShumokumeishou();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();

			sql = "SELECT COUNT(*) COUNT FROM TBL00019 WHERE SHUMOKUMEI = ?";
			int count = 0;
			ps = conn.prepareStatement(sql);
			ps.setString(1, meishou);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt("COUNT");
			}

			if (count > 1) {
				addError(null, "種目名はすでに存在しています");
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
		if (f080101 == null) {
			addError(null, "入力エラー");
		}
		if (Utility.isEmptyString(f080101.getShumokumeishou())) {
			addError(null, "種目名は必須です");
		}
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public F080101 getF080101() {
		return f080101;
	}

	public void setF080101(F080101 f080101) {
		this.f080101 = f080101;
	}

	public String getLogKey() {
		return logKey;
	}

	public void setLogKey(String logKey) {
		this.logKey = logKey;
	}

}
