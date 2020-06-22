package shohinmodel;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.views.xslt.ArrayAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import shohinmodel.common.EventCommon;

public class Copyoftest11 {

	public static void main(String[] args) throws Exception {

		URL url = new URL("https://api.rms.rakuten.co.jp/es/2.0/order/searchOrder/");
		String serviceSecret = "SP308759_ciMfQ60q20mv6lqj";
		String licenseKey = "SL308759_Y8QIeIqwnRtisj6c";
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		System.out.println(author);
		conn.setRequestProperty("Authorization", author);
		conn.setDoOutput(true);
		// conn.setRequestProperty("Pragma:", "no-cache");

		conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");

		conn.setRequestMethod("POST");
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.connect();
//		InputStream inputStream = (InputStream) conn.getInputStream();
//		
//		byte[] buffer = new byte[100];
//        int len = -1;
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        while((len = inputStream.read(buffer)) != -1){
//        
//            outputStream.write(buffer, 0, len);
//        }
//        
//        outputStream.close();
//        inputStream.close();
//	

		// System.out.println(sb.toString());
//		 outputStream.toString();

		// 得到请求的输出流对象
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		// 把数据写入请求的Body
		String searchxml = "{\"dateType\":1,\"startDatetime\":\"2018-07-31T00:00:00+0900\",\"endDatetime\":\"2018-08-01T10:00:00+0900\",\"PaginationRequestModel\":{\"requestRecordsAmount\":1000,\"requestPage\":1,\"SortModelList\":[{\"sortColumn\":1,\"sortDirection\":1}]}}";
		out.write(new String(searchxml.getBytes("UTF-8")));

		System.out.println(new String(searchxml.getBytes("UTF-8")));
		out.flush();
		out.close();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (Exception e) {
			e.toString();
		}
		String line = null;
		StringBuffer sb1 = new StringBuffer();
		while ((line = in.readLine()) != null) {
			sb1.append(line);
		}
		in.close();
		
		
		ObjectMapper mapper = new ObjectMapper();
		Map m = mapper.readValue(sb1.toString(), Map.class);
		System.out.println(sb1.toString());

	}

}
