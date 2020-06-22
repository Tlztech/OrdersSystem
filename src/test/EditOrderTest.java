package test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;

import com.rakuten.util.Utility;

import jp.co.rakuten.rms.mall.order.api.client.AsyncReceiptModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.GetRequestIdResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.ItemModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService_Service;
import jp.co.rakuten.rms.mall.order.api.client.OrderModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderSearchModel;
import jp.co.rakuten.rms.mall.order.api.client.PackageModel;
import jp.co.rakuten.rms.mall.order.api.client.PersonModel;
import jp.co.rakuten.rms.mall.order.api.client.UnitErrorModel;
import jp.co.rakuten.rms.mall.order.api.client.UpdateOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.UserAuthModel;

public class EditOrderTest {

	public static void main(String[] args) throws Exception {
		String shop = "coverforefront";

		List<String> messageList = new ArrayList<String>();

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

		orderSearchModel.getStatus().add("新規受付");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar date = Calendar.getInstance();
		String endDay = sdf.format(date.getTime());
		date.add(Calendar.DATE, -62);
		String startDay = sdf.format(date.getTime());
		getmode.getOrderSearchModel()
				.setStartDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(
						Integer.valueOf(startDay.substring(0, 4)), Integer.valueOf(startDay.substring(4, 6)),
						Integer.valueOf(startDay.substring(6, 8)), 0, 0, 0, 000, 0));
		getmode.getOrderSearchModel()
				.setEndDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(
						Integer.valueOf(endDay.substring(0, 4)), Integer.valueOf(endDay.substring(4, 6)),
						Integer.valueOf(endDay.substring(6, 8)), 23, 59, 59, 999, 0));
		GetOrderResponseModel getResponse = port.getOrder(auth, getmode);
		GetRequestIdResponseModel res = port.getRequestId(auth);
		if (!Utility.isEmptyList(res.getUnitError())) {
			List<UnitErrorModel> unitList = res.getUnitError();
			for (UnitErrorModel unit : unitList) {
				messageList.add(unit.getErrorCode() + unit.getMessage() + unit.getOrderKey());

			}
		}
		int reqId = res.getRequestId();
		UpdateOrderRequestModel updatemodel = new UpdateOrderRequestModel();
		updatemodel.setRequestId(reqId);

		editOrder(getResponse.getOrderModel(), updatemodel);

		AsyncReceiptModel response = port.updateOrder(auth, updatemodel);

		// 実行結果を判定します
		if (response.getErrorCode() != null && response.getErrorCode().equals("N00-000")) {

		} else if (response.getErrorCode().equals("W00-000")) {
			List<UnitErrorModel> unitList = response.getUnitError();
			for (UnitErrorModel unit : unitList) {
				messageList.add(unit.getErrorCode() + unit.getMessage() + unit.getOrderKey());

			}
		} else {
			messageList.add(response.getErrorCode() + response.getMessage());
		}

	}

	private static void editOrder(List<OrderModel> orderList, UpdateOrderRequestModel updatemodel) {
		// 检查同捆
		List<OrderModel> donkonList = checkDonkon(orderList);
		for (OrderModel order : orderList) {
			String orderNo = order.getOrderNumber();
			// 如果是同捆order放到同捆栏
			boolean donkonari = false;
			for (OrderModel donkonorder : donkonList) {
				if (orderNo.equals(donkonorder.getOrderNumber())) {
					donkonari = true;
					break;
				}
			}
			if (donkonari) {
				order.setStatus("同梱");
				updatemodel.getOrderModel().add(order);
				continue;
			}
			// 判断包邮类型
			// 1:宅急便包邮
			// 2:信封包邮
			// 3:不包邮(可以信封）
			// 4:不包邮（不能信封）
			int type = checkBaoyou(order);
			// 支払方法名
			String settlement = order.getSettlementModel().getSettlementName();
			// とど府県
			String prefecture = order.getPackageModel().get(0).getSenderModel().getPrefecture();

			if ("クレジットカード".equals(settlement)) {
				order.setStatus("クレジット");
			} else if ("銀行振込".equals(settlement)) {
				order.setStatus("銀行");
			} else if ("代金引換".equals(settlement)) {
				order.setStatus("代引き");
			} else if ("セブンイレブン前払".equals(settlement) || "ローソン前払".equals(settlement)) {
				order.setStatus("コンビニ");
			} else {
				order.setStatus("ローソン前払");
			}

			if (prefecture.contains("沖縄")) {
				order.setStatus("沖縄");
			} else if (1 == type) {
				order.getDeliveryModel().setDeliveryName("宅配便");
				order.getPackageModel().get(0).setPostagePrice(0);
			} else if (2 == type) {
				if (!settlement.equals("代金引換")) {
					order.getDeliveryModel().setDeliveryName("メール便");
					order.getPackageModel().get(0).setPostagePrice(0);

				} else {
					order.getDeliveryModel().setDeliveryName("宅配便");
					for (ItemModel item : order.getPackageModel().get(0).getItemModel()) {
						item.setIsIncludedPostage(false);
					}
					order.getPackageModel().get(0).setPostagePrice(540);

				}
			} else if (3 == type) {
				for (ItemModel item : order.getPackageModel().get(0).getItemModel()) {
					item.setIsIncludedPostage(false);
				}
				if (!settlement.equals("代金引換")) {
					order.getDeliveryModel().setDeliveryName("メール便");
					order.getPackageModel().get(0).setPostagePrice(180);
				} else {
					order.getDeliveryModel().setDeliveryName("宅配便");
					order.getPackageModel().get(0).setPostagePrice(540);
				}
			} else if (4 == type) {
				order.getDeliveryModel().setDeliveryName("宅配便");
				order.getPackageModel().get(0).setPostagePrice(540);
				for (ItemModel item : order.getPackageModel().get(0).getItemModel()) {
					item.setIsIncludedPostage(false);
				}
			}
			
			updatemodel.getOrderModel().add(order);
		}

	}

	private static int checkBaoyou(OrderModel order) {
		// 取得全部商品
		List<ItemModel> itemList = order.getPackageModel().get(0).getItemModel();
		int takyubinmuryou = 0;
		int merubinmuryou = 0;
		int merubinfuka = 0;
		int itemcount = itemList.size();
		Long price = order.getGoodsPrice();
		for (ItemModel item : itemList) {
			String name = item.getItemName();
			String choice = item.getSelectedChoice();
			if ((name.contains("送料無料") && !name.contains("メール便送料無料")) || price >= 5480L) {
				takyubinmuryou++;
				continue;
			} else if (name.contains("メール便送料無料")) {
				merubinmuryou++;
				continue;
			} else if (choice.contains("メール便対応不可")) {
				merubinfuka++;
			}
		}
		// 有一个宅急便包邮就整体包邮
		if (takyubinmuryou > 0) {
			return 1;
		} else if (merubinfuka == 0 && Double.valueOf(merubinmuryou) / Double.valueOf(itemcount) > 0.66) {
			return 2;
		} else if (merubinfuka == 0) {
			return 3;
		} else {
			return 4;
		}
	}

	private static List<OrderModel> checkDonkon(List<OrderModel> orderList) {
		List<OrderModel> shoriList = new ArrayList<OrderModel>();

		for (OrderModel order : orderList) {
			List<PackageModel> packageList = order.getPackageModel();
			String orderNo = order.getOrderNumber();
			PackageModel packageModel = packageList.get(0);
			PersonModel person = packageModel.getSenderModel();
			String name = person.getFamilyName() + person.getFirstName();
			String address = person.getPrefecture() + person.getCity() + person.getSubAddress();
			for (OrderModel order2 : orderList) {
				List<PackageModel> packageList2 = order2.getPackageModel();
				String orderNo2 = order2.getOrderNumber();
				PackageModel packageModel2 = packageList2.get(0);
				PersonModel person2 = packageModel2.getSenderModel();
				String name2 = person2.getFamilyName() + person2.getFirstName();
				String address2 = person2.getPrefecture() + person2.getCity() + person2.getSubAddress();

				if (!orderNo.equals(orderNo2) && name.equals(name2) && address.equals(address2)) {
					shoriList.add(order);
					break;
				}
			}

		}

		return shoriList;
	}

}
