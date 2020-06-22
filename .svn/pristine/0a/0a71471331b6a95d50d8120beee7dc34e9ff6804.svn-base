package com.rakuten.r1001.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.CommonOrderBean;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

import jp.co.rakuten.rms.mall.order.api.client.GetOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.ItemModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService_Service;
import jp.co.rakuten.rms.mall.order.api.client.OrderModel;
import jp.co.rakuten.rms.mall.order.api.client.UserAuthModel;

public class testMain {

	public static void main(String[] args) throws Exception {
		String shop = "coverforefront";
		OrderCommon orderCommon = new OrderCommon();
		OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
		List<CommonOrderBean> commonOrderBeanList = orderInfoBean
				.getCommonOrderBeanList();
		// TODO Auto-generated method stub
		List<String> hasomachiList = orderCommon
				.getHasomachiList(commonOrderBeanList);

		OrderApiService_Service api = new OrderApiService_Service();
		// ポートを生成します
		OrderApiService port = api.getOrderApiServicePort();
		// 認証モデルを生成します
		UserAuthModel auth = new UserAuthModel();
		// 認証キーをセットします
		auth.setAuthKey(Utility.getApiKey(shop));
		// 店舗URL をセットします
		auth.setShopUrl(shop);
		// ユーザー名をセットします
		auth.setUserName("dongtze");
		// レスポンスモデルを生成します
		// UpdateOrderResponse
		// getRequestId を呼出します
		GetOrderRequestModel getmode = new GetOrderRequestModel();
		for (String bango : hasomachiList) {
			getmode.getOrderNumber().add(bango);
		}

		GetOrderResponseModel getResponse = port.getOrder(auth, getmode);

		Connection conn = JdbcConnection.getConnection();
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			for (OrderModel order : getResponse.getOrderModel()) {
				List<ItemModel> itemModelList = order.getPackageModel().get(0)
						.getItemModel();
				String orderNo = order.getOrderNumber();
				int noukiday = 2;
				for (ItemModel item : itemModelList) {
					sql = "update common_order_detail_tbl set nouki = ? where juchubango = ? and shouhinbango = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, item.getNormalItemModel().getDelvdateInfo());
					ps.setString(2, orderNo);
					ps.setString(3, item.getItemNumber());
					ps.execute();

					ps.setString(1, item.getNormalItemModel().getDelvdateInfo());
					ps.setString(2, orderNo);
					ps.setString(3, item.getItemNumber().replace("sale", ""));
					ps.execute();

					if (Utility.getNoukiDay(item.getNormalItemModel()
							.getDelvdateInfo()) > noukiday) {
						noukiday = Utility.getNoukiDay(item
								.getNormalItemModel().getDelvdateInfo());
					}
					sql = "delete from tbl00027 where chumonbango = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, orderNo);
					ps.execute();

					String createDay = "";
					sql = "select create_time from common_order_tbl where chumonbango = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, orderNo);
					rs = ps.executeQuery();
					while (rs.next()) {
						createDay = rs.getString("create_time");
					}

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date1 = sdf.parse(createDay);
					Calendar calendar = Calendar.getInstance();
					calendar.setTime(date1);
					calendar.add(Calendar.DATE, noukiday);
					String dateStr = sdf.format(calendar.getTime());

					sql = "INSERT INTO TBL00027 VALUES(?,?,?,?,?,?)";
					ps = conn.prepareStatement(sql);
					ps.setString(1, orderNo);
					ps.setString(2, dateStr);
					ps.setString(3, Utility.getDateTime());
					ps.setString(4, Utility.getUser());
					ps.setString(5, Utility.getDateTime());
					ps.setString(6, Utility.getUser());
					ps.execute();

				}
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
			System.out.println("done");
		}

	}
}
