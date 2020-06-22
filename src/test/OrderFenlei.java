package test;

import java.util.ArrayList;
import java.util.List;

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

public class OrderFenlei {

	public static void main(String[] args) {
		OrderApiService_Service api = new OrderApiService_Service();
		List<String> orderList = new ArrayList<String>();
		orderList.add("308759-20160609-0173758369");
		orderList.add("308759-20160611-0184747255");
		orderList.add("308759-20160613-0189648169");
		orderList.add("308759-20160614-0198089365");
		orderList.add("308759-20160614-0198223186");
		orderList.add("308759-20160614-0198295177");
		orderList.add("308759-20160614-0198374251");
		orderList.add("308759-20160614-0198589391");
		orderList.add("308759-20160614-0198752260");
		orderList.add("308759-20160614-0198930285");
		orderList.add("308759-20160614-0199356283");
		orderList.add("308759-20160614-0199534370");
		orderList.add("308759-20160614-0199708359");
		orderList.add("308759-20160614-0200054373");
		orderList.add("308759-20160614-0200382262");
		orderList.add("308759-20160614-0200387369");
		orderList.add("308759-20160614-0200486264");
		orderList.add("308759-20160614-0200777389");
		orderList.add("308759-20160614-0202110381");
		orderList.add("308759-20160614-0202450256");
		orderList.add("308759-20160614-0202675254");
		orderList.add("308759-20160614-0203471292");
		orderList.add("308759-20160614-0203690259");
		orderList.add("308759-20160614-0204045363");
		orderList.add("308759-20160607-0157174175");
		orderList.add("308759-20160609-0169822264");
		orderList.add("308759-20160612-0184651386");
		orderList.add("308759-20160612-0184729182");
		orderList.add("308759-20160612-0189076389");
		orderList.add("308759-20160613-0183100151");
		orderList.add("308759-20160613-0190544184");
		orderList.add("308759-20160613-0191693391");
		orderList.add("308759-20160613-0192437385");
		orderList.add("308759-20160613-0192617260");
		orderList.add("308759-20160613-0192983274");
		orderList.add("308759-20160613-0194633181");
		orderList.add("308759-20160613-0194776268");
		orderList.add("308759-20160613-0194996267");
		orderList.add("308759-20160613-0195000187");
		orderList.add("308759-20160613-0195060370");
		orderList.add("308759-20160613-0195413254");
		orderList.add("308759-20160613-0195636359");
		orderList.add("308759-20160613-0195743366");
		orderList.add("308759-20160613-0196510376");
		orderList.add("308759-20160613-0196638355");
		orderList.add("308759-20160613-0197188375");
		orderList.add("308759-20160613-0197439382");
		orderList.add("308759-20160613-0197745380");
		orderList.add("308759-20160614-0195588174");
		orderList.add("308759-20160614-0198017251");
		orderList.add("308759-20160610-0178139290");
		orderList.add("308759-20160611-0181730261");
		orderList.add("308759-20160612-0181621151");
		orderList.add("308759-20160612-0183855170");
		orderList.add("308759-20160612-0184598172");
		orderList.add("308759-20160612-0185970161");
		orderList.add("308759-20160612-0186246392");
		orderList.add("308759-20160612-0186672168");
		orderList.add("308759-20160612-0186683168");
		orderList.add("308759-20160612-0186940365");
		orderList.add("308759-20160612-0187570385");
		orderList.add("308759-20160612-0187601278");
		orderList.add("308759-20160612-0187669274");
		orderList.add("308759-20160612-0187689164");
		orderList.add("308759-20160612-0187720365");
		orderList.add("308759-20160612-0188027153");
		orderList.add("308759-20160612-0188477351");
		orderList.add("308759-20160612-0188767266");
		orderList.add("308759-20160612-0188963188");
		orderList.add("308759-20160612-0189127254");
		orderList.add("308759-20160612-0189415358");
		orderList.add("308759-20160612-0189561358");
		orderList.add("308759-20160612-0189617154");
		orderList.add("308759-20160612-0190092178");
		orderList.add("308759-20160612-0190111254");
		orderList.add("308759-20160612-0190275259");
		orderList.add("308759-20160612-0190712366");
		orderList.add("308759-20160612-0191016363");
		orderList.add("308759-20160612-0191140381");
		orderList.add("308759-20160612-0191565254");
		orderList.add("308759-20160612-0191985274");
		orderList.add("308759-20160613-0190307178");
		orderList.add("308759-20160613-0192229379");
		orderList.add("308759-20160613-0192566274");
		orderList.add("308759-20160613-593182062");
		orderList.add("308759-20160609-0169888288");
		orderList.add("308759-20160610-0176975391");
		orderList.add("308759-20160610-0177679251");
		orderList.add("308759-20160611-0179149153");
		orderList.add("308759-20160611-0180422280");
		orderList.add("308759-20160611-0180950174");
		orderList.add("308759-20160611-0181648192");
		orderList.add("308759-20160611-0181976386");
		orderList.add("308759-20160611-0182570279");
		orderList.add("308759-20160611-0182678365");
		orderList.add("308759-20160611-0182732267");
		orderList.add("308759-20160611-0182807280");
		orderList.add("308759-20160611-0183231371");
		orderList.add("308759-20160611-0184101352");
		orderList.add("308759-20160611-0184170378");
		orderList.add("308759-20160611-0184646256");
		orderList.add("308759-20160611-0184999382");
		orderList.add("308759-20160611-0185445363");
		orderList.add("308759-20160611-0185843363");
		orderList.add("308759-20160612-0182807167");
		orderList.add("308759-20160612-0184521359");
		orderList.add("308759-20160612-0185760273");
		orderList.add("308759-20160612-0186145363");
		orderList.add("308759-20160606-0153479168");
		orderList.add("308759-20160608-0164055170");

		// ポートを生成します
		OrderApiService port = api.getOrderApiServicePort();
		// 認証モデルを生成します
		UserAuthModel auth = new UserAuthModel();
		// 認証キーをセットします
		auth.setAuthKey(Utility.getApiKey("coverforefront"));
		// 店舗URL をセットします
		auth.setShopUrl("coverforefront");
		// ユーザー名をセットします
		auth.setUserName("dongtze");
		// レスポンスモデルを生成します
		// UpdateOrderResponse
		// getRequestId を呼出します
		GetOrderRequestModel getmode = new GetOrderRequestModel();

		for (String orderno : orderList) {
			getmode.getOrderNumber().add(orderno);
		}

		GetOrderResponseModel getResponse = port.getOrder(auth, getmode);
		GetRequestIdResponseModel res = port.getRequestId(auth);

		int reqId = res.getRequestId();

		UpdateOrderRequestModel updatemodel = new UpdateOrderRequestModel();
		updatemodel.setRequestId(reqId);

		for (OrderModel order : getResponse.getOrderModel()) {

			order.setStatus("保留");
			updatemodel.getOrderModel().add(order);

		}

		AsyncReceiptModel response = port.updateOrder(auth, updatemodel);

	}

}
