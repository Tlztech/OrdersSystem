package com.rakuten.r0601.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r0601.ap.CheckCommodityAp;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A06010206Action extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String result = null;
	private String shouhinbango = null;
	private String kosu = null;
	private String biko = null;

	protected void exec() throws Exception {
		if (!Utility.isEmptyString(result) && result.startsWith("false")) {
			return;
		}
		String commodityId = "";
		if (shouhinbango.indexOf("-") > 0) {
			commodityId = shouhinbango.substring(0, shouhinbango.indexOf("-"));
		} else {
			commodityId = shouhinbango;
		}

		String picUrl = Utility.getPicUrl(commodityId);
		result = (shouhinbango + "&%&" + kosu + "&%&" + biko + "&%&" + picUrl);
	}

	protected void init() {
		setTitle("V060102:添加发货单");
	}

	protected void isValidated_SourceVersion() throws Exception {
		String commodityId = "";
		String detailNo = "";
		if (Utility.isEmptyString(kosu)) {
			kosu = "1";
		}

		if (shouhinbango.indexOf("-") > 0) {
			commodityId = shouhinbango.substring(0, shouhinbango.indexOf("-"));
			detailNo = shouhinbango.substring(shouhinbango.indexOf("-"));
		} else if (shouhinbango.startsWith("1000000")) {
			commodityId = shouhinbango;
			detailNo = "";
		} else {
			commodityId = "1000000" + shouhinbango;
			detailNo = "";
		}
		CheckCommodityAp checkCommodityAp = new CheckCommodityAp();
		if (!checkCommodityAp.execute(commodityId, detailNo)) {
			Connection conn = null;
			PreparedStatement ps = null;
			String sql = null;
			ResultSet rs = null;
			try {
				conn = JdbcConnection.getConnection();
				sql = "SELECT * FROM TBL00016 WHERE BARCODE = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, commodityId);
				rs = ps.executeQuery();
				String comm = "";
				while (rs.next()) {
					comm = rs.getString("COMMODITY_ID");
				}
				if (Utility.isEmptyString(comm)) {

					result = "false";
					result += "&%&商品编号或条形码不存在！";
				} else {
					shouhinbango = comm;
					if (shouhinbango.indexOf("-") > 0) {
						commodityId = shouhinbango.substring(0,
								shouhinbango.indexOf("-"));
						detailNo = shouhinbango.substring(shouhinbango
								.indexOf("-"));
					} else {
						commodityId = shouhinbango;
						detailNo = "";
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				throw e;
			} finally {
				conn.close();
			}

		}

	}
	
	protected void isValidated() throws Exception {
		String commodityId = "";
		String detailNo = "";
		if (Utility.isEmptyString(kosu)) {
			kosu = "1";
		}

		if (shouhinbango.indexOf("-") > 0) {
			commodityId = shouhinbango.substring(0, shouhinbango.indexOf("-"));
			detailNo = shouhinbango.substring(shouhinbango.indexOf("-"));
		} else  {
			commodityId = shouhinbango;
			detailNo = "-0-0";
		}
		CheckCommodityAp checkCommodityAp = new CheckCommodityAp();
		if (!checkCommodityAp.execute(commodityId, detailNo)) {
			result = "false";
			result += "&%&商品编号或条形码不存在！";
		}

	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getShouhinbango() {
		return shouhinbango;
	}

	public void setShouhinbango(String shouhinbango) {
		this.shouhinbango = shouhinbango;
	}

	public String getKosu() {
		return kosu;
	}

	public void setKosu(String kosu) {
		this.kosu = kosu;
	}

	public String getBiko() {
		return biko;
	}

	public void setBiko(String biko) {
		this.biko = biko;
	}

}
