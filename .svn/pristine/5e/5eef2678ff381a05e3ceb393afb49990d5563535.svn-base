package shohinmodel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import com.rakuten.util.Utility;

import jp.co.rakuten.rms.mall.order.api.client.GetOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService_Service;
import jp.co.rakuten.rms.mall.order.api.client.OrderModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderSearchModel;
import jp.co.rakuten.rms.mall.order.api.client.UserAuthModel;

public class WentiOrder {

	public static void main(String[] args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int i = 0;
		while (true) {
			Calendar date = Calendar.getInstance();
			date.add(Calendar.DATE, -62 * i);
			String endDay = sdf.format(date.getTime());
			date.add(Calendar.DATE, -62);
			String startDay = sdf.format(date.getTime());
			startDay = sdf.format(date.getTime());
			GetOrderResponseModel getResponse = getOrder("coverforefront", startDay, endDay);
			if (!"正常終了".equals(getResponse.getMessage())) {
				break;
			} else {
				List<OrderModel> orderList = getResponse.getOrderModel();
				for (OrderModel order : orderList) {
					System.out.println(order.getOrderNumber());
				}
			}
			i++;
		}

	}

	private static GetOrderResponseModel getOrder(String shop, String startDay, String endDay) throws Exception {
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
		OrderSearchModel orderSearchModel = new OrderSearchModel();
		getmode.setOrderSearchModel(orderSearchModel);
		orderSearchModel.setDateType(1);

		getmode.getOrderSearchModel()
				.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(
						Integer.valueOf(startDay.substring(0, 4)), Integer.valueOf(startDay.substring(4, 6)),
						Integer.valueOf(startDay.substring(6, 8)), 0, 0, 0, 000, 0));
		getmode.getOrderSearchModel()
				.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(
						Integer.valueOf(endDay.substring(0, 4)), Integer.valueOf(endDay.substring(4, 6)),
						Integer.valueOf(endDay.substring(6, 8)), 23, 59, 59, 999, 0));
		orderSearchModel.getStatus().add("発送待ち");
		GetOrderResponseModel getResponse = port.getOrder(auth, getmode);

		return getResponse;
	}

}
