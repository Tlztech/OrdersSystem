package test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.common.bean.ShouhinStsBean;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class ZaikoShobun {

	public static void main(String[] args) throws Exception {

		OrderCommon orderCommon = new OrderCommon();
		OrderInfoBean orderinfoBean = orderCommon.getOrderInfo();

		List<ShouhinStsBean> shouhinStsBeanList = orderinfoBean.getShouhinStsBeanList();

		List<String> shohinbangoList = new ArrayList<String>();
		Connection conn = null;

		try {
			conn = JdbcConnection.getConnection();

			PreparedStatement ps = null;

			String sql = "SELECT concat(commodity_id,detail_no) shohinbango FROM TBL00012 WHERE STOCK_JP > 0";
			ps = conn.prepareStatement(sql);
			ResultSet rs1 = ps.executeQuery();
			while (rs1.next()) {
				shohinbangoList.add(rs1.getString("shohinbango"));
			}
		} finally {
			conn.close();
		}
		List<String[]> dataList = new ArrayList<String[]>();
		for (String shohinbango : shohinbangoList) {
			boolean ariFlg = false;
			for (ShouhinStsBean bean : shouhinStsBeanList) {
				if (bean.getShouhinbango().equalsIgnoreCase(shohinbango)) {
					ariFlg = true;
					// 残り個数(日本)
					System.out.println(bean.getShouhinbango() + "	" + bean.getNokorikosuJp());
					dataList.add(new String[] { bean.getShouhinbango(), bean.getNokorikosuJp() });
				}
			}

			if (!ariFlg) {

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

				// 日本個数
				System.out.println(shohinbango + "	" + stockjp);
				dataList.add(new String[] { shohinbango, stockjp });
			}
		}
		Utility.writeCsvFile(dataList, "D://fuck.csv");
	}

}
