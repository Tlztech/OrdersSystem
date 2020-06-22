package test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class NewAPITest {

	public static void main(String[] args) throws Exception {
		String shop = "coverforefront";
		URL url = new URL("https://api.rms.rakuten.co.jp/es/2.0/order/searchOrder/");
		String serviceSecret = getServiceSecret(shop);
		String licenseKey = getLicenseKey(shop);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		// conn.setRequestProperty("contentType", "UTF-8");
		conn.setRequestProperty("Authorization", author);
		conn.setRequestProperty("shopUrl", shop);
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.setRequestMethod("GET");// 设置提交方法
		
			conn.connect();
	
		BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) conn.getInputStream()));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();

		System.out.println(sb.toString());

	}

	public static String getLicenseKey(String shop) {
		if ("trend777".equals(shop)) {
			return "SL306685_SgpPqimoNd8eJ7xd";
		} else if ("coverforefront".equals(shop)) {
			return "SL308759_Y8QIeIqwnRtisj6c";
		} else {
			return null;
		}
	}

	public static String getServiceSecret(String shop) {
		if ("trend777".equals(shop)) {
			return "SP306685_TxVVl5qWQeMh6bPK";
		} else if ("coverforefront".equals(shop)) {
			return "SP308759_ciMfQ60q20mv6lqj";
		} else {
			return null;
		}
	}
}
