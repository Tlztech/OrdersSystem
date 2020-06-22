package batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis.encoding.Base64;

import com.rakuten.r1302.common.A130201Common;
import com.rakuten.r1302.form.OrderList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

import jp.co.rakuten.rms.mall.order.api.client.AsyncReceiptModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.GetOrderResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.GetRequestIdResponseModel;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService;
import jp.co.rakuten.rms.mall.order.api.client.OrderApiService_Service;
import jp.co.rakuten.rms.mall.order.api.client.OrderModel;
import jp.co.rakuten.rms.mall.order.api.client.PackageModel;
import jp.co.rakuten.rms.mall.order.api.client.UpdateOrderRequestModel;
import jp.co.rakuten.rms.mall.order.api.client.UserAuthModel;
import shohinmodel.common.Shohincommon;

public class BatchTest {

	public static void main(String[] args) throws Exception {

		String shop = "coverforefront";

		OrderApiService_Service api = new OrderApiService_Service();
		Shohincommon common = new Shohincommon();
		String serviceSecret = common.getServiceSecret(shop);
		String licenseKey = common.getLicenseKey(shop);
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());

		// ポートを生成します
		OrderApiService port = api.getOrderApiServicePort();
		// 認証モデルを生成します
		UserAuthModel auth = new UserAuthModel();
		// 認証キーをセットします
		auth.setAuthKey(author);
		// 店舗URL をセットします
		auth.setShopUrl(shop);
		// ユーザー名をセットします
		auth.setUserName("dongtze");
		// レスポンスモデルを生成します
		// UpdateOrderResponse
		// getRequestId を呼出します
		GetOrderRequestModel getmode = new GetOrderRequestModel();
		String[] orderNoList = { "308759-20171113-00081719", "308759-20171113-00086701", "308759-20171113-00086703",
				"308759-20171113-00088701", "308759-20171113-00095715", "308759-20171113-00095720",
				"308759-20171113-00096720", "308759-20171113-00097720", "308759-20171113-00098720",
				"308759-20171113-00099720", "308759-20171113-00100708", "308759-20171113-00100720",
				"308759-20171113-00102709", "308759-20171113-00102714", "308759-20171113-00103709",
				"308759-20171113-00104714", "308759-20171113-00105714", "308759-20171113-00109702",
				"308759-20171113-00109707", "308759-20171113-00109714", "308759-20171113-00111707",
				"308759-20171113-00111723", "308759-20171113-00112706", "308759-20171113-00112724",
				"308759-20171113-00113702", "308759-20171113-00113706", "308759-20171113-00114702",
				"308759-20171113-00114710", "308759-20171113-00114716", "308759-20171113-00114723",
				"308759-20171113-00115706", "308759-20171113-00115710", "308759-20171113-00116710",
				"308759-20171113-00116713", "308759-20171113-00116718", "308759-20171113-00117712",
				"308759-20171113-00117713", "308759-20171113-00117722", "308759-20171113-00118705",
				"308759-20171113-00118713", "308759-20171113-00118722", "308759-20171113-00119713",
				"308759-20171113-00120704", "308759-20171113-00120705", "308759-20171113-00120713",
				"308759-20171113-00122704", "308759-20171113-00122705", "308759-20171113-00123704",
				"308759-20171113-00124704", "308759-20171113-1355401261", "308759-20171114-00115723",
				"308759-20171114-00116716", "308759-20171114-00117716", "308759-20171114-00117723",
				"308759-20171114-00118706", "308759-20171114-00120722", "308759-20171114-00121713",
				"308759-20171114-00230809", "308759-20171114-00232809", "308759-20171114-00238821",
				"308759-20171114-00240821", "308759-20171114-00242821", "308759-20171114-00268811",
				"308759-20171114-00272805", "308759-20171114-00273806", "308759-20171114-00274801",
				"308759-20171114-00274806", "308759-20171114-00275801", "308759-20171114-00276806",
				"308759-20171114-00276813", "308759-20171114-00276819", "308759-20171114-00277813",
				"308759-20171114-00277819", "308759-20171114-00278813", "308759-20171114-00278819",
				"308759-20171114-00279813", "308759-20171114-00279819", "308759-20171114-00285814",
				"308759-20171114-00286814", "308759-20171114-00288814", "308759-20171114-00288818",
				"308759-20171114-00289808", "308759-20171114-00290808", "308759-20171114-00290814",
				"308759-20171114-00291812", "308759-20171114-00291814", "308759-20171114-00292802",
				"308759-20171114-00292810", "308759-20171114-00292812", "308759-20171114-00293817",
				"308759-20171114-00294817", "308759-20171114-00295816", "308759-20171114-00296802",
				"308759-20171114-00296817", "308759-20171114-00297802", "308759-20171114-00297807",
				"308759-20171114-00297817", "308759-20171114-00298802", "308759-20171114-00298820",
				"308759-20171114-00299820", "308759-20171114-00300820", "308759-20171114-00301820",
				"308759-20171114-00302807", "308759-20171114-00302822", "308759-20171114-00305815",
				"308759-20171114-00308824", "308759-20171114-00309824", "308759-20171114-00310824" };
		getmode = new GetOrderRequestModel();
		for (String orderno : orderNoList) {
			getmode.getOrderNumber().add(orderno);
		}
		GetOrderResponseModel getResponse = port.getOrder(auth, getmode);
		GetRequestIdResponseModel res = port.getRequestId(auth);
		int reqId = res.getRequestId();

		UpdateOrderRequestModel updatemodel = new UpdateOrderRequestModel();
		updatemodel.setRequestId(reqId);

		for (OrderModel order : getResponse.getOrderModel()) {
			order.setStatus("同梱");
			updatemodel.getOrderModel().add(order);
		}

		AsyncReceiptModel response = port.updateOrder(auth, updatemodel);
		// 実行結果を判定します
		if (response.getErrorCode() != null && response.getErrorCode().equals("N00-000")) {
			// リクエストID の格納処理など

		} else {
		}
	}

	public static List<String[]> getDenpyoAndKaisha(List<String> juchubangoList) throws Exception {
		List<String[]> dataList = new ArrayList<String[]>();
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = JdbcConnection.getConnection();
			String sql = "SELECT Distinct TOIAWASEBANGO , UNSOKAISHA  FROM hassou_tbl WHERE JUCHUBANGO = ?";
			for (String juchubango : juchubangoList) {
				ps = conn.prepareStatement(sql);
				ps.setString(1, juchubango);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					dataList.add(new String[] { rs.getString("TOIAWASEBANGO"), rs.getString("UNSOKAISHA") });
				}
			}
			return dataList;
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public static List<String[]> getDenpyoAndKaishaFast(List<String> juchubangoList, Connection conn) throws Exception {
		List<String[]> dataList = new ArrayList<String[]>();

		PreparedStatement ps = null;

		String sql = "SELECT Distinct TOIAWASEBANGO , UNSOKAISHA  FROM hassou_tbl WHERE JUCHUBANGO = ?";
		for (String juchubango : juchubangoList) {
			ps = conn.prepareStatement(sql);
			ps.setString(1, juchubango);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				dataList.add(new String[] { rs.getString("TOIAWASEBANGO"), rs.getString("UNSOKAISHA") });
			}
		}
		return dataList;

	}
}
