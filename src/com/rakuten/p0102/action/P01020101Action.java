package com.rakuten.p0102.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShohinStsInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.p0102.form.P010201;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class P01020101Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private P010201 p010201 = null;
	private String shohinbango = null;
	private String testReslut = null;

	protected void exec() throws Exception {

		OrderCommon orderCommon = new OrderCommon();
		OrderInfoBean orderinfoBean = orderCommon.getOrderInfo();

		List<ShouhinStsBean> shouhinStsBeanList = orderinfoBean
				.getShouhinStsBeanList();
		List<CommonOrderBean> commonOrderBeanList = orderinfoBean
				.getCommonOrderBeanList();
		StringBuffer sb = new StringBuffer();
		boolean ariFlg = false;
		for (ShouhinStsBean bean : shouhinStsBeanList) {
			if (bean.getShouhinbango().equals(shohinbango)) {
				ariFlg = true;
				// 商品番号
				sb.append(bean.getShouhinbango());
				sb.append("\r\n");
				sb.append("_____________________________________________________");
				sb.append("\r\n");
				sb.append("基本情報:");
				sb.append("\r\n");
				sb.append("\r\n");

				sb.append("商品番号:" + bean.getShouhinbango() + ",");
				sb.append("\r\n");
				// 上海在庫数
				sb.append("上海在庫数:" + bean.getShanghaiStock() + ",     ");
				// 日本個数
				sb.append("日本個数:" + bean.getNihonStock() + ",     ");
				// 入荷中個数
				sb.append("入荷中個数:" + bean.getNyukachukosu() + ",     ");
				// 運送途中個数
				sb.append("運送途中個数:" + bean.getUnsochukosu() + ",");
				sb.append("\r\n");
				// 残り個数(上海)
				sb.append("残り個数(上海):" + bean.getNokorikosuSh() + ",     ");
				// 残り個数(日本)
				sb.append("残り個数(日本):" + bean.getNokorikosuJp() + ",     ");
				// 残り個数(運送)
				sb.append("残り個数(運送):" + bean.getNokorikosuUnso() + ",     ");
				// 残り個数(入荷)
				sb.append("残り個数(入荷):" + bean.getNokorikosuNyuka() + ",");
				sb.append("\r\n");
				sb.append("\r\n");
				List<ShohinStsInfoBean> detailList = bean
						.getShohinStsInfoBeanList();

				sb.append("_____________________________________________________");
				sb.append("\r\n");
				sb.append("運送情報:");
				sb.append("\r\n");
				sb.append("\r\n");
				List<String[]> unsoList = orderCommon.getUnsochuList();
				for (int i = 0; i < unsoList.size(); i++) {
					if (unsoList.get(i)[0].equals(shohinbango)) {
						sb.append("発送日:" + unsoList.get(i)[3] + "  ");
						sb.append("伝票番号:" + unsoList.get(i)[2] + "  ");
						sb.append("個数:" + unsoList.get(i)[1]);
						sb.append("\r\n");
					}
				}

				sb.append("_____________________________________________________");
				sb.append("\r\n");
				sb.append("入荷情報:");
				sb.append("\r\n");
				sb.append("\r\n");
				List<String[]> nyukaList = orderCommon.getNyukaChuList();
				for (int i = 0; i < nyukaList.size(); i++) {
					if (nyukaList.get(i)[0].equals(shohinbango)) {
						sb.append("入荷日:" + nyukaList.get(i)[2] + "  ");
						sb.append("個数:" + nyukaList.get(i)[1]);
						sb.append("\r\n");
					}
				}

				sb.append("_____________________________________________________");
				sb.append("\r\n");
				sb.append("注文情報:");
				sb.append("\r\n");
				sb.append("\r\n");

				for (ShohinStsInfoBean detail : detailList) {
					String juchubango = detail.getJuchubango();
					for (CommonOrderBean commonOrder : commonOrderBeanList) {
						if (commonOrder.getJuchubango().equals(juchubango)) {
							sb.append("ステータス最終設定日時:");
							sb.append("\r\n");

							sb.append("注文ステータス:");
							sb.append("\r\n");
						}
					}
					sb.append("受注番号:" + juchubango);
					sb.append("\r\n");
					sb.append("注文日時:" + detail.getChumonnichiji());
					sb.append("\r\n");
					sb.append("必要個数:"
							+ String.valueOf(Integer.valueOf(detail
									.getHoryukosuSh())
									+ Integer.valueOf(detail.getHoryukosuJp())
									+ Integer.valueOf(detail.getHoryukosuUnso())
									+ Integer.valueOf(detail
											.getHoryukosuNyuka())
									+ Integer.valueOf(detail.getHiyuyokosu())));
					sb.append("\r\n");
					sb.append("保留個数(上海stock):" + detail.getHoryukosuSh());
					sb.append("\r\n");

					sb.append("保留個数(日本stock):" + detail.getHoryukosuJp());
					sb.append("\r\n");

					sb.append("保留個数(運送途中stock):" + detail.getHoryukosuUnso());
					sb.append("\r\n");
					if (!Utility.isEmptyList(detail.getUnsohoryuList())) {
						for (int i = 0; i < detail.getUnsohoryuList().size(); i++) {
							sb.append("伝票番号:" + detail.getDenpyoubango().get(i)
									+ "  ");
							sb.append("発送日:" + detail.getHasoubi().get(i)
									+ "  ");
							sb.append("保留個数:"
									+ detail.getUnsohoryuList().get(i));
							sb.append("\r\n");
						}
					}

					sb.append("保留個数(入荷中stock):" + detail.getHoryukosuNyuka());
					sb.append("\r\n");
					if (!Utility.isEmptyList(detail.getNyukahoryuList())) {
						for (int i = 0; i < detail.getNyukahoryuList().size(); i++) {
							sb.append("入荷日:" + detail.getNyukabi().get(i)
									+ "  ");
							sb.append("保留個数:"
									+ detail.getNyukahoryuList().get(i));
							sb.append("\r\n");
						}
					}

					sb.append("残り必要個数:" + detail.getHiyuyokosu());
					sb.append("\r\n");

					sb.append("保留ステータス:" + detail.getHoryusts());
					sb.append("\r\n");
					sb.append("\r\n");
				}
			}
		}
		if (!ariFlg) {
			sb.append(shohinbango);
			sb.append("\r\n");
			sb.append("_____________________________________________________");
			sb.append("\r\n");
			sb.append("基本情報:");
			sb.append("\r\n");
			sb.append("\r\n");

			sb.append("商品番号:" + shohinbango + ",");
			sb.append("\r\n");
			List<String[]> unsoList = orderCommon.getUnsochuList();
			List<String[]> nyukaList = orderCommon.getNyukaChuList();

			Connection conn = null;
			String stocksh = "";
			try {
				conn = JdbcConnection.getConnection();

				PreparedStatement ps = null;

				String sql = "SELECT * FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, Utility.getCommodityId(shohinbango));
				ps.setString(2, Utility.getDetailN0(shohinbango));
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					stocksh = rs.getString("STOCK_SH");
				}
			} finally {
				conn.close();
			}

			String stockjp = "";
			try {
				conn = JdbcConnection.getConnection();

				PreparedStatement ps = null;

				String sql = "SELECT * FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, Utility.getCommodityId(shohinbango));
				ps.setString(2, Utility.getDetailN0(shohinbango));
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					stockjp = rs.getString("STOCK_JP");
				}
			} finally {
				conn.close();
			}

			// 上海在庫数
			sb.append("上海在庫数:" + stocksh + ",     ");
			// 日本個数
			sb.append("日本個数:" + stockjp + ",     ");

			sb.append("\r\n");
			sb.append("\r\n");

			sb.append("_____________________________________________________");
			sb.append("\r\n");
			sb.append("運送情報:");
			sb.append("\r\n");
			sb.append("\r\n");

			for (int i = 0; i < unsoList.size(); i++) {
				if (unsoList.get(i)[0].equals(shohinbango)) {
					sb.append("発送日:" + unsoList.get(i)[3] + "  ");
					sb.append("伝票番号:" + unsoList.get(i)[2] + "  ");
					sb.append("個数:" + unsoList.get(i)[1]);
					sb.append("\r\n");
				}
			}

			sb.append("_____________________________________________________");
			sb.append("\r\n");
			sb.append("入荷情報:");
			sb.append("\r\n");
			sb.append("\r\n");

			for (int i = 0; i < nyukaList.size(); i++) {
				if (nyukaList.get(i)[0].equals(shohinbango)) {
					sb.append("入荷日:" + nyukaList.get(i)[2] + "  ");
					sb.append("個数:" + nyukaList.get(i)[1]);
					sb.append("\r\n");
				}
			}

			sb.append("_____________________________________________________");
			sb.append("\r\n");
			sb.append("注文情報:なし");

		}
		testReslut = sb.toString();
	}

	protected void init() {
		setTitle("P010101:商品分类检索");
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
	}

	public String getShohinbango() {
		return shohinbango;
	}

	public void setShohinbango(String shohinbango) {
		this.shohinbango = shohinbango;
	}

	public P010201 getP010201() {
		return p010201;
	}

	public void setP010201(P010201 p010201) {
		this.p010201 = p010201;
	}

	public String getTestReslut() {
		return testReslut;
	}

	public void setTestReslut(String testReslut) {
		this.testReslut = testReslut;
	}

}
