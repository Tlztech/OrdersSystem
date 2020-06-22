package test;

import java.util.List;

import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.OrderInfoBean;
import com.rakuten.util.Utility;

import jp.co.rakuten.rms.mall.order.api.client.AsyncReceiptModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.GetRequestIdResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService_Service;
import jp.co.rakuten.rms.mall.order.api.client.OrderModel;
import jp.co.rakuten.rms.mall.order.api.client.UpdateOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.UserAuthModel;

public class okure {

	public static void main(String[] args) throws Exception {
		OrderCommon orderCommon = new OrderCommon();
		OrderInfoBean orderInfoBean = orderCommon.getOrderInfo();
		List<String> okureList = orderCommon.getOkureList(orderInfoBean);

		OrderApiService_Service api = new OrderApiService_Service();

		String shopname = "coverforefront";
		// ポートを生成します
		OrderApiService port = api.getOrderApiServicePort();
		// 認証モデルを生成します
		UserAuthModel auth = new UserAuthModel();
		// 認証キーをセットします
		auth.setAuthKey(Utility.getApiKey(shopname));
		// 店舗URL をセットします
		auth.setShopUrl(shopname);
		// ユーザー名をセットします
		auth.setUserName("dongtze");
		// レスポンスモデルを生成します
		// UpdateOrderResponse
		// getRequestId を呼出します
		GetOrderRequestModel getmode = new GetOrderRequestModel();

		for (String orderNo : okureList) {
			getmode.getOrderNumber().add(orderNo);
		}

		GetOrderResponseModel getResponse = port.getOrder(auth, getmode);
		GetRequestIdResponseModel res = port.getRequestId(auth);

		int reqId = res.getRequestId();

		UpdateOrderRequestModel updatemodel = new UpdateOrderRequestModel();
		updatemodel.setRequestId(reqId);

		for (OrderModel order : getResponse.getOrderModel()) {

			for (String orderno : okureList) {
				if (orderno.equals(order.getOrderNumber())) {

					order.setStatus("クレジット");
					updatemodel.getOrderModel().add(order);
					break;
				}
			}

		}

		AsyncReceiptModel response = port.updateOrder(auth, updatemodel);

	}

}
