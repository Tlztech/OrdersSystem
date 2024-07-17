package shohinmodel.common;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.rakuten.util.JdbcConnection;
import com.rakuten.util.SqlUtility;
import com.rakuten.util.Utility;
import com.sun.org.apache.xml.internal.security.utils.Base64;

import shohinmodel.bean.Categories;
import shohinmodel.bean.Category;
import shohinmodel.bean.CategoryInfo;
import shohinmodel.bean.CategoryList;
import shohinmodel.bean.ChildCategories;
import shohinmodel.bean.Image;
import shohinmodel.bean.Images;
import shohinmodel.bean.Inventories;
import shohinmodel.bean.Inventory;
import shohinmodel.bean.Item;
import shohinmodel.bean.ItemInventory;
import shohinmodel.bean.Medicine;
import shohinmodel.bean.Option;
import shohinmodel.bean.OptionValue;
import shohinmodel.bean.OptionValues;
import shohinmodel.bean.Options;
import shohinmodel.bean.Point;
import shohinmodel.bean.TagIds;

public class Shohincommon {
	public String getCategoryXml(String shop, int cateid) throws Exception {
		URL url = new URL("https://api.rms.rakuten.co.jp/es/1.0/categoryapi/shop/category/get?categoryId=" + cateid);
		String serviceSecret = getServiceSecret(shop);
		String licenseKey = getLicenseKey(shop);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		// conn.setRequestProperty("contentType", "UTF-8");
		conn.setRequestProperty("Authorization", author);
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.setRequestMethod("GET");// 设置提交方法
		try {
			conn.connect();
		} catch (ConnectException e) {
			try {
				conn.connect();
			} catch (ConnectException e2) {
				conn.connect();
			}
		}
		BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) conn.getInputStream()));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();

		System.out.println(sb.toString());
		return sb.toString();

	}

	public void updateCategory(String shop, String updatexml) throws Exception {

		URL url = new URL("https://api.rms.rakuten.co.jp/es/1.0/categoryapi/shop/category/update");
		String serviceSecret = getServiceSecret(shop);
		String licenseKey = getLicenseKey(shop);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());

		conn.setRequestProperty("Authorization", author);
		conn.setDoOutput(true);
		// conn.setRequestProperty("Pragma:", "no-cache");
		conn.setRequestProperty("Cache-Control", "no-cache");
		conn.setRequestProperty("Content-Type", "text/xml");

		// 得到请求的输出流对象
		OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
		// 把数据写入请求的Body
		out.write(new String(updatexml.getBytes("UTF-8")));

		System.out.println(new String(updatexml.getBytes("UTF-8")));
		out.flush();
		out.close();
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		} catch (SocketTimeoutException e) {
			in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		}
		String line = null;
		StringBuffer sb1 = new StringBuffer();
		while ((line = in.readLine()) != null) {
			sb1.append(line);
		}
		in.close();
		System.out.println(sb1.toString());

	}

	public String getCategoryHtml(List<Item> mainItemList, List<Item> commonItemList, String shop) {
		List<Item> shoriList = new ArrayList<Item>();
		if (Utility.isEmptyList(mainItemList)) {
			shoriList = commonItemList.subList(0, 6);
		} else if (mainItemList.size() >= 10) {
			shoriList = mainItemList.subList(0, 6);
		} else {
			mainItemList.addAll(commonItemList);
			shoriList = mainItemList.subList(0, 6);
		}
		String html = "<table cellspacing=\"1\" cellpadding=\"1\" width=\"100%\"><tr><td width=\"100%\" colspan=\"2\"><img width=\"100%\" src=\"https://www.rakuten.ne.jp/gold/coverforefront/pick.jpg\"></td></tr>";
		for (int i = 0; i < shoriList.size(); i++) {
			Item item1 = shoriList.get(i);
			Item item2 = shoriList.get(++i);
			String url = item1.getImages().getImage().get(0).getImageUrl();
			url = url.replace(("https://image.rakuten.co.jp/" + shop + "/cabinet/"), "");
			html = (html + "<tr>");
			html += ("<td width=\"50%\"><a href=\"https://item.rakuten.co.jp/" + shop + "/" + item1.getItemUrl()
					+ "\"><img src=https://thumbnail.image.rakuten.co.jp/@0_mall/" + shop + "/cabinet/" + url
					+ "?_ex=168x168&s=0&r=1\" width=\"98%\"></a>"
					+ Utility.numberFormatNoen(String.valueOf(item1.getItemPrice())) + "円(送料"
					+ (item1.isIncludedPostage() ? "込)</td>" : "別)</td>"));
			url = item2.getImages().getImage().get(0).getImageUrl();
			url = url.replace(("https://image.rakuten.co.jp/" + shop + "/cabinet/"), "");
			html += ("<td width=\"50%\"><a href=\"https://item.rakuten.co.jp/" + shop + "/" + item2.getItemUrl()
					+ "\"><img src=https://thumbnail.image.rakuten.co.jp/@0_mall/" + shop + "/cabinet/" + url
					+ "?_ex=168x168&s=0&r=1\" width=\"98%\"></a>"
					+ Utility.numberFormatNoen(String.valueOf(item2.getItemPrice())) + "円(送料"
					+ (item2.isIncludedPostage() ? "込)</td>" : "別)</td>"));
			html = (html + "</tr>");
		}
		html = html + "</table>";
		return html;
	}

	public String getCategoryListXml(String shop) throws Exception {
		URL url = new URL("https://api.rms.rakuten.co.jp/es/1.0/categoryapi/shop/categories/get");
		String serviceSecret = getServiceSecret(shop);
		String licenseKey = getLicenseKey(shop);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		// conn.setRequestProperty("contentType", "UTF-8");
		conn.setRequestProperty("Authorization", author);
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.setRequestMethod("GET");// 设置提交方法
		try {
			conn.connect();
		} catch (ConnectException e) {
			try {
				conn.connect();
			} catch (ConnectException e2) {
				conn.connect();
			}
		}
		BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) conn.getInputStream()));
		String line = null;
		StringBuffer sb = new StringBuffer();
		while ((line = in.readLine()) != null) {
			sb.append(line);
		}
		in.close();

		// System.out.println(sb.toString());
		return sb.toString();

	}

	public String getItemXml(String shop, String shohinbango) throws Exception {
		URL url = new URL("https://api.rms.rakuten.co.jp/es/1.0/item/get?itemUrl=" + shohinbango);
		String serviceSecret = getServiceSecret(shop);
		String licenseKey = getLicenseKey(shop);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		String author = "ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes());
		// conn.setRequestProperty("contentType", "UTF-8");
		conn.setRequestProperty("Authorization", author);
		conn.setDoOutput(true);// 打开写入属性
		conn.setDoInput(true);// 打开读取属性
		conn.setRequestMethod("GET");// 设置提交方法
		conn.connect();
		
//		BufferedReader in = new BufferedReader(new InputStreamReader((InputStream) conn.getInputStream()));
		InputStream inputStream = (InputStream) conn.getInputStream();
//		String line = null;
//		StringBuffer sb = new StringBuffer();
	
//		while ((line = in.readLine()) != null) {
//			sb.append(line);
//			
//		}
		byte[] buffer = new byte[100];
        int len = -1;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1){
        
            outputStream.write(buffer, 0, len);
        }
        
        outputStream.close();
        inputStream.close();
	

		// System.out.println(sb.toString());
		return outputStream.toString();

	}

	public void deleteShohinXml_tbl() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "delete from shohinxml_tbl";
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public void downloadItem(String shop, String shohinbango, String type, Connection conn) throws Exception {

		String itemxml = getItemXml(shop, shohinbango);
		Document document = DocumentHelper.parseText(itemxml);

		Element root = document.getRootElement();
		Element itemGetResult = root.element("itemGetResult");
		Element status = root.element("status");
		String requestId = status.element("requestId").getText();
		String code = itemGetResult.element("code").getText();

		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;

		// sql = "INSERT INTO shohinupdatehistory_tbl
		// VALUES(?,?,?,?,?,?,?,?,?)";
		// ps = conn.prepareStatement(sql);
		int i = 0;
		// ps.setString(++i, requestId);
		// ps.setString(++i, shop);
		// ps.setString(++i, shohinbango);
		// ps.setString(++i, code);
		// ps.setString(++i, type);
		// ps.setString(++i, Utility.getDateTime());
		// ps.setString(++i, Utility.getUser());
		// ps.setString(++i, Utility.getDateTime());
		// ps.setString(++i, Utility.getUser());
		// ps.execute();
	
		if ("N000".equals(code)) {
			boolean ariFlg = false;
			sql = "select count(*) count from shohinxml_tbl where itemurl = ? and shop = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shohinbango);
			ps.setString(2, shop);
			rs = ps.executeQuery();
			while (rs.next()) {
				int count = rs.getInt("count");
				if (count > 0) {
					ariFlg = true;
				}
			}

			i = 0;
			if (ariFlg) {
				sql = "update shohinxml_tbl set shohinxml = ?,update_time = ?,update_user = ? where itemurl = ? and shop = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(++i, itemxml);
				ps.setString(++i, Utility.getDateTime());
				ps.setString(++i, Utility.getUser());
				ps.setString(++i, shohinbango);
				ps.setString(++i, shop);
				ps.execute();
			} else {
				sql = "insert into shohinxml_tbl values(?,?,?,?,?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(++i, shop);
				ps.setString(++i, shohinbango);
				ps.setString(++i, itemxml);
				ps.setString(++i, Utility.getDateTime());
				ps.setString(++i, Utility.getUser());
				ps.setString(++i, Utility.getDateTime());
				ps.setString(++i, Utility.getUser());
				ps.execute();
			}

			shohinbango = shohinbango.replace("sale", "");
			Item item = getItemBean(itemxml);
			ItemInventory itemInventory = item.getItemInventory();
			if (itemInventory != null) {
				List<Inventories> inventories_s = itemInventory.getInventories();
				if (!Utility.isEmptyList(inventories_s)) {

					sql = "UPDATE TBL00011 SET JAPANESE_NAME =? , PIC_URL = ? ,UPDATE_TIME = ? , UPDATE_USER = ? WHERE COMMODITY_ID = ?";
					ps = conn.prepareStatement(sql);
					ps.setString(1, item.getItemName());
					String pirurl = "";
					try {
						pirurl = ("https://thumbnail.image.rakuten.co.jp/@0_mall/"
								+ item.getImages().getImage().get(0).getImageUrl()
										.substring(item.getImages().getImage().get(0).getImageUrl()
												.indexOf("https://image.rakuten.co.jp/"))
								+ "?_ex=200x200&s=2&r=1").replace("https://image.rakuten.co.jp/", "");
					} catch (Exception e) {
						System.out.println(item.getImages().getImage().get(0).getImageUrl());
					}
					ps.setString(2, pirurl);
					ps.setString(3, Utility.getDateTime());
					ps.setString(4, Utility.getUser());
					ps.setString(5, shohinbango);
					ps.execute();

					for (Inventories inventories : inventories_s) {
						List<Inventory> inventory_s = inventories.getInventory();
						for (Inventory inventory : inventory_s) {
							String detail_no = "";
							if (!Utility.isEmptyString(inventory.getChildNoHorizontal())) {
								detail_no = detail_no + inventory.getChildNoHorizontal();
							}
							if (!Utility.isEmptyString(inventory.getChildNoVertical())) {
								detail_no = detail_no + inventory.getChildNoVertical();
							}
							int count = 0;
							sql = "SELECT COUNT(*) COUNT FROM TBL00012 WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, shohinbango);
							ps.setString(2, detail_no);
							rs = ps.executeQuery();
							while (rs.next()) {
								count = rs.getInt("COUNT");
							}
							if (count > 0) {
								sql = "UPDATE TBL00012 SET COMM_DESCRIBE = ?,RE_PRICE = ?,UPDATE_TIME = ? , YOKONAME = ?, SHITAGANAME = ?, UPDATE_USER = ? WHERE COMMODITY_ID = ? AND DETAIL_NO = ?";
								ps = conn.prepareStatement(sql);
								String describe = "";
								if (!Utility.isEmptyString(inventory.getOptionNameHorizontal())) {
									describe = describe + inventory.getOptionNameHorizontal();
								}
								if (!Utility.isEmptyString(inventory.getOptionNameVertical())) {
									describe = (describe + "\r\n" + inventory.getOptionNameVertical());
								}
								ps.setString(1, describe);

								ps.setString(2, String.valueOf(item.getItemPrice()));
								ps.setString(3, Utility.getDateTime());
								String optionNameHorizontal = "";
								if (!Utility.isEmptyString(inventory.getOptionNameHorizontal())) {
									optionNameHorizontal = inventory.getOptionNameHorizontal();
								}
								ps.setString(4, optionNameHorizontal);
								String optionNameVertical = "";
								if (!Utility.isEmptyString(inventory.getOptionNameVertical())) {
									optionNameVertical = inventory.getOptionNameVertical();
								}
								ps.setString(5, optionNameVertical);
								ps.setString(6, "Shohincommon");
								ps.setString(7, shohinbango);
								ps.setString(8, detail_no);

								ps.executeUpdate();
							} else {
								sql = SqlUtility.getSql("SQLR0001012");
								ps = conn.prepareStatement(sql);
								ps.setString(1, shohinbango);
								ps.setString(2, detail_no);
								String describe = "";
								if (!Utility.isEmptyString(inventory.getOptionNameHorizontal())) {
									describe = describe + inventory.getOptionNameHorizontal();
								}
								if (!Utility.isEmptyString(inventory.getOptionNameVertical())) {
									describe = (describe + "\r\n" + inventory.getOptionNameVertical());
								}
								ps.setString(3, describe);
								ps.setString(4, pirurl);
								ps.setString(5, "0");
								ps.setString(6, String.valueOf(item.getItemPrice()));
								ps.setString(7, "0");
								ps.setString(8, "0");
								ps.setString(9, "0");
								ps.setString(10, "");
								ps.setString(11, "");
								ps.setString(12, "");
								ps.setString(13, "0");
								ps.setString(14, Utility.getDateTime());
								ps.setString(15, "Shohincommon");
								ps.setString(16, Utility.getDateTime());
								ps.setString(17, "Shohincommon");
								String optionNameHorizontal = "";
								if (!Utility.isEmptyString(inventory.getOptionNameHorizontal())) {
									optionNameHorizontal = inventory.getOptionNameHorizontal();
								}
								ps.setString(18, optionNameHorizontal);
								String optionNameVertical = "";
								if (!Utility.isEmptyString(inventory.getOptionNameVertical())) {
									optionNameVertical = inventory.getOptionNameVertical();
								}
								ps.setString(19, optionNameVertical);

								ps.execute();
							}

							String maxBarcode = null;

							sql = "SELECT COUNT(*) COUNT FROM TBL00016 WHERE COMMODITY_ID = ?";
							ps = conn.prepareStatement(sql);
							ps.setString(1, shohinbango + detail_no);
							rs = ps.executeQuery();
							count = 0;
							while (rs.next()) {
								count = rs.getInt("COUNT");
							}
							if (count == 0) {
								sql = "SELECT MAX(BARCODE)+1 MAX_BARCODE FROM TBL00016";
								ps = conn.prepareStatement(sql);
								rs = ps.executeQuery();
								while (rs.next()) {
									maxBarcode = rs.getString("MAX_BARCODE");
								}

								sql = "INSERT INTO TBL00016 VALUES(?,?)";
								ps = conn.prepareStatement(sql);
								ps.setString(1, shohinbango + detail_no);
								ps.setString(2, maxBarcode);
								ps.execute();
							}

						}
					}
				}
			}

		}
		conn.commit();

	}

	public int[] getMainCateIdTrend() {
		return new int[] { 100, 142, 143, 144, 145, 146, 147, 148, 151, 337, 102 };
	}

	public int[] getMainCateIdCover() {
		return new int[] { 123, 124, 142, 143, 144, 145, 146, 148, 150, 380 };
	}

	public List<Integer> getDownCateId(int cateid, CategoryList categoryList, String shop) throws Exception {
		List<Integer> cateidlist = new ArrayList<Integer>();
		List<Category> categorys = categoryList.getCategory();
		for (Category category : categorys) {
			if (isThisCate(cateid, category) != null) {
				getChildCateId(cateidlist, isThisCate(cateid, category));

			}
		}
		return cateidlist;

	}

	private Category isThisCate(int cateid, Category category) {

		if (category != null) {
			if (cateid == category.getCategoryId()) {
				return category;
			} else {
				ChildCategories childCategories = category.getChildCategories();
				if (childCategories != null) {
					List<Category> categoryList = childCategories.getCategoryList();
					for (Category ccategory : categoryList) {
						if (isThisCate(cateid, ccategory) != null) {
							return isThisCate(cateid, ccategory);
						}

					}
				}
			}

		}
		return null;
	}

	private void getChildCateId(List<Integer> cateidlist, Category category) {
		if (category != null) {
			cateidlist.add(category.getCategoryId());
			ChildCategories childCategories = category.getChildCategories();
			if (childCategories != null) {
				List<Category> categoryList = childCategories.getCategoryList();
				for (Category ccategory : categoryList) {
					getChildCateId(cateidlist, ccategory);
				}
			}
		}
	}

	public List<String[]> getShohinXmlList(String shop) throws Exception {
		List<String[]> shohinXmlList = new ArrayList<String[]>();

		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT * from  shohinxml_Tbl where shop = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shop);
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinXmlList.add(new String[] { rs.getString("itemurl"), rs.getString("shohinxml") });
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return shohinXmlList;
	}

	public List<Item> getMainRankList(String shop, List<Integer> cateidlist, List<String[]> shohinxmlList)
			throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> shohinbangoList = new ArrayList<String>();
		List<String> shoribangoList = new ArrayList<String>();
		List<Item> itemlList = new ArrayList<Item>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, -20);
		String chumonnichiji = sdf.format(date.getTime());

		try {
			conn = JdbcConnection.getConnection();

			for (String[] shohinxmlInfo : shohinxmlList) {
				for (int cateid : cateidlist) {

					if (shohinxmlInfo[1].contains("<categoryId>" + cateid + "</categoryId>")) {
						shohinbangoList.add(shohinxmlInfo[0]);
					}
				}
			}

			sql = "select  substring_index(shouhinbango,'-',1) shohinbango,sum(kosu) count from common_order_detail_tbl t1 left join common_order_tbl t2 on t1.JUCHUBANGO = t2.CHUMONBANGO where t2.CHUMONNICHIJI >=? and substring_index(shouhinbango,'-',1)<> 'nvsw008' and(";
			for (int i = 0; i < shohinbangoList.size(); i++) {
				String shohinbango = shohinbangoList.get(i);
				if (i == 0) {
					sql = (sql + " substring_index(shouhinbango,'-',1)= '" + shohinbango + "'");
				} else {
					sql = (sql + " or substring_index(shouhinbango,'-',1)= '" + shohinbango + "'");
				}
			}
			sql = sql + " ) group by shohinbango order by count desc";
			ps = conn.prepareStatement(sql);

			ps.setString(1, chumonnichiji);
			rs = ps.executeQuery();
			while (rs.next()) {
				shoribangoList.add(rs.getString("shohinbango"));
			}
			if (shoribangoList.size() > 50) {
				shoribangoList = shoribangoList.subList(0, 50);
			}
			for (String shohinbango : shoribangoList) {
				if ("trend777".equals(shop) && "sjpj083".equals(shohinbango)) {
					shohinbango = "salesjpj083";
				}
				sql = "SELECT * from  shohinxml_tbl where shop = ? and itemurl = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shop);
				ps.setString(2, shohinbango);
				rs = ps.executeQuery();
				while (rs.next()) {
					itemlList.add(getItemBean(rs.getString("shohinxml")));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return itemlList;
	}

	public List<Item> getCommonRankList(String shop) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> shohinbangoList = new ArrayList<String>();

		List<Item> itemlList = new ArrayList<Item>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar date = Calendar.getInstance();
		date.add(Calendar.DATE, -7);
		String chumonnichiji = sdf.format(date.getTime());
		try {
			conn = JdbcConnection.getConnection();
			sql = "select  substring_index(shouhinbango,'-',1) shohinbango,sum(kosu) count from common_order_detail_tbl t1 left join common_order_tbl t2 on t1.JUCHUBANGO = t2.CHUMONBANGO where t2.CHUMONNICHIJI >=? and substring_index(shouhinbango,'-',1)<> 'nvsw008'  group by shohinbango order by count desc";
			ps = conn.prepareStatement(sql);

			ps.setString(1, chumonnichiji);
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinbangoList.add(rs.getString("shohinbango"));
			}
			if (shohinbangoList.size() > 50) {
				shohinbangoList = shohinbangoList.subList(0, 50);
			}
			for (String shohinbango : shohinbangoList) {
				if ("trend777".equals(shop) && "sjpj083".equals(shohinbango)) {
					shohinbango = "salesjpj083";
				}
				sql = "SELECT * from  shohinxml_tbl where shop = ? and itemurl = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shop);
				ps.setString(2, shohinbango);
				rs = ps.executeQuery();
				while (rs.next()) {
					itemlList.add(getItemBean(rs.getString("shohinxml")));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return itemlList;
	}

	public void downloadShohin(String logKey) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		List<String> shohinbangoList = new ArrayList<String>();
		EventCommon eventcommon = new EventCommon();
		// List<String> exceptListTrend = eventcommon.getTodayList("trend777");
		// exceptListTrend.addAll(eventcommon.getTommorowList("trend777"));

		List<String> exceptListCover = eventcommon.getTodayList("coverforefront");
		exceptListCover.addAll(eventcommon.getTommorowList("coverforefront"));

		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT commodity_id from  tbl00011";
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();
			while (rs.next()) {
				shohinbangoList.add(rs.getString("commodity_id"));
			}

			List<String> shohinbangoListTrend = new ArrayList<String>();
			// for (String shohinbango : shohinbangoList) {
			// boolean ariFlg = false;
			// for (String exceptBango : exceptListTrend) {
			// if (shohinbango.equals(exceptBango)) {
			// ariFlg = true;
			// }
			// }
			// if (!ariFlg) {
			// shohinbangoListTrend.add(shohinbango);
			// }
			// }

			List<String> shohinbangoListCover = new ArrayList<String>();
			for (String shohinbango : shohinbangoList) {
				boolean ariFlg = false;
				for (String exceptBango : exceptListCover) {
					if (shohinbango.equals(exceptBango)) {
						ariFlg = true;
					}
				}
				if (!ariFlg) {
					shohinbangoListCover.add(shohinbango);
				}
			}

			// String expectTrend = " where shop = 'trend777' ";
			// for (String expectString : exceptListTrend) {
			// expectTrend += (" and itemurl <> '" + expectString + "'");
			// }
			//
			String expectCover = " where shop = 'coverforefront' ";
			for (String expectString : exceptListCover) {
				expectCover += (" and itemurl <> '" + expectString + "'");
			}

			// sql = ("delete from shohinxml_tbl" + expectTrend);
			// ps = conn.prepareStatement(sql);

			// ps.execute();

			// sql = ("delete from shohinxml_tbl" + expectCover);
			// ps = conn.prepareStatement(sql);
			//
			// ps.execute();
			// conn.commit();

			Shohincommon common = new Shohincommon();
			// common.deleteShohinXml_tbl();
			// for (String shohinbango : shohinbangoListTrend) {
			// sql =
			// "select count(*) count from shohinxml_tbl where shop = 'trend777'
			// and itemurl = ?";
			// ps = conn.prepareStatement(sql);
			// ps.setString(1, shohinbango);
			// rs = ps.executeQuery();
			// boolean ariflg = false;
			// while (rs.next()) {
			// if (rs.getInt("count") > 0) {
			// ariflg = true;
			// }
			// }
			// if (ariflg) {
			// continue;
			// }
			// System.out.println("trend777 " + shohinbango);
			// if (!Utility.isEmptyString(logKey)) {
			// Utility.addLog("trend777 " + shohinbango, logKey);
			// }
			// common.downloadItem("trend777", shohinbango, "1");
			//
			// }
			for (String shohinbango : shohinbangoListCover) {
				sql = "select count(*) count from shohinxml_tbl where shop = 'coverforefront' and itemurl = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, shohinbango);
				rs = ps.executeQuery();
				boolean ariflg = false;
				while (rs.next()) {
					if (rs.getInt("count") > 0) {
						ariflg = true;
					}
				}
				if (ariflg) {
					continue;
				}
				System.out.println("coverforefront  " + shohinbango);
				if (!Utility.isEmptyString(logKey)) {
					Utility.addLog("coverforefront  " + shohinbango, logKey);
				}
				
				common.downloadItem("coverforefront", shohinbango, "1", conn);
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	public CategoryList getCategoryList(String categoryListxml) throws Exception {
		CategoryList categoryList = null;
		Category category = null;
		Document document = DocumentHelper.parseText(categoryListxml);
		Element root = document.getRootElement();
		Element categoriesGetResult = root.element("categoriesGetResult");
		String code = categoriesGetResult.element("code").getText();
		if ("N000".equals(code)) {
			categoryList = new CategoryList();
			List<Category> categories = new ArrayList<Category>();
			categoryList.setCategory(categories);
			Element categoryList_e = categoriesGetResult.element("categoryList");
			@SuppressWarnings("unchecked")
			List<Element> category_es = categoryList_e.elements("category");
			for (Element category_e : category_es) {
				category = new Category();
				categories.add(category);

				setCategory(category, category_e);

			}
		}

		return categoryList;
	}

	@SuppressWarnings("unchecked")
	private void setCategory(Category category, Element category_e) {
		// カテゴリID
		if (category_e.element("categoryId") != null) {
			category.setCategoryId(Integer.valueOf(category_e.element("categoryId").getText()));
		}

		// カテゴリレベル
		if (category_e.element("categoryLevel") != null) {
			category.setCategoryLevel(Integer.valueOf(category_e.element("categoryLevel").getText()));
		}

		// カテゴリ名
		if (category_e.element("name") != null) {
			category.setName(category_e.element("name").getText());
		}

		// カテゴリステータス
		if (category_e.element("status") != null) {
			category.setStatus(Integer.valueOf(category_e.element("status").getText()));
		}

		// 優先度
		if (category_e.element("categoryWeight") != null) {
			category.setCategoryWeight(Integer.valueOf(category_e.element("categoryWeight").getText()));
		}
		// 子カテゴリ情報リスト
		if (category_e.element("childCategories") != null) {
			ChildCategories childCategories = new ChildCategories();
			category.setChildCategories(childCategories);
			List<Category> ccategoryList = new ArrayList<Category>();
			childCategories.setCategoryList(ccategoryList);
			Category ccategory = null;
			Element childCategories_e = category_e.element("childCategories");
			List<Element> ccategory_es = childCategories_e.elements("category");
			for (Element ccategory_e : ccategory_es) {
				ccategory = new Category();
				ccategoryList.add(ccategory);
				setCategory(ccategory, ccategory_e);
			}
		}

	}

	@SuppressWarnings("unchecked")
	public Item getItemBean(String shohinxml) throws Exception {

		Item item = null;

		Document document = DocumentHelper.parseText(shohinxml);
		Element root = document.getRootElement();
		Element itemGetResult = root.element("itemGetResult");
		String code = itemGetResult.element("code").getText();
		if ("N000".equals(code)) {
			item = new Item();

			Element item_e = itemGetResult.element("item");

			// 商品管理番号
			if (item_e.element("itemUrl") != null) {
				item.setItemUrl(item_e.element("itemUrl").getText());
			}
			// 商品番号
			if (item_e.element("itemNumber") != null) {
				item.setItemNumber(item_e.element("itemNumber").getText());
			}
			// 商品名
			if (item_e.element("itemName") != null) {
				item.setItemName(item_e.element("itemName").getText());
			}
			// 販売価格
			if (item_e.element("itemPrice") != null) {
				String itemPrice = item_e.element("itemPrice").getText();
				if (!Utility.isEmptyString(itemPrice)) {
					item.setItemPrice(Integer.valueOf(itemPrice));
				}
			}
			// 全商品ディレクトリID
			if (item_e.element("genreId") != null) {
				String genreId = item_e.element("genreId").getText();
				if (!Utility.isEmptyString(genreId)) {
					item.setGenreId(Integer.valueOf(genreId));
				}
			}
			// カタログID
			if (item_e.element("catalogId") != null) {
				item.setCatalogId(item_e.element("catalogId").getText());
			}
			// PC用商品説明文
			if (item_e.element("descriptionForPC") != null) {
				item.setDescriptionForPC(item_e.element("descriptionForPC").getText());
			}
			// モバイル用商品説明文
			if (item_e.element("descriptionForMobile") != null) {
				item.setDescriptionForMobile(item_e.element("descriptionForMobile").getText());
			}
			// スマートフォン用商品説明文
			if (item_e.element("descriptionForSmartPhone") != null) {
				item.setDescriptionForSmartPhone(item_e.element("descriptionForSmartPhone").getText());
			}
			// 動画URL
			if (item_e.element("movieUrl") != null) {
				item.setMovieUrl(item_e.element("movieUrl").getText());
			}
			// PC用キャッチコピー
			if (item_e.element("catchCopyForPC") != null) {
				item.setCatchCopyForPC(item_e.element("catchCopyForPC").getText());
			}
			// モバイル用キャッチコピー
			if (item_e.element("catchCopyForMobile") != null) {
				item.setCatchCopyForMobile(item_e.element("catchCopyForMobile").getText());
			}
			// PC用販売説明文
			if (item_e.element("descriptionBySalesMethod") != null) {
				item.setDescriptionBySalesMethod(item_e.element("descriptionBySalesMethod").getText());
			}
			// 注文ボタン
			String isSaleButton = item_e.element("isSaleButton").getText();
			if ("true".equals(isSaleButton)) {
				item.setSaleButton(true);
			} else if ("false".equals(isSaleButton)) {
				item.setSaleButton(false);
			}

			// 資料請求ボタン
			String isDocumentButton = item_e.element("isDocumentButton").getText();
			if ("true".equals(isDocumentButton)) {
				item.setDocumentButton(true);
			} else if ("false".equals(isDocumentButton)) {
				item.setDocumentButton(false);
			}
			// 商品問い合わせボタン
			String isInquiryButton = item_e.element("isInquiryButton").getText();
			if ("true".equals(isInquiryButton)) {
				item.setInquiryButton(true);
			} else if ("false".equals(isInquiryButton)) {
				item.setInquiryButton(false);
			}

			// 再入荷のお知らせボタン
			String isStockNoticeButton = item_e.element("isStockNoticeButton").getText();
			if ("true".equals(isStockNoticeButton)) {
				item.setStockNoticeButton(true);
			} else if ("false".equals(isStockNoticeButton)) {
				item.setStockNoticeButton(false);
			}

			// 商品情報レイアウト
			if (item_e.element("itemLayout") != null) {
				String itemLayout = item_e.element("itemLayout").getText();
				if (!Utility.isEmptyString(itemLayout)) {
					item.setItemLayout(Integer.valueOf(itemLayout));
				}
			}
			// 消費税
			String isIncludedTax = item_e.element("isIncludedTax").getText();
			if ("true".equals(isIncludedTax)) {
				item.setIncludedTax(true);
			} else if ("false".equals(isIncludedTax)) {
				item.setIncludedTax(false);
			}
			// 送料
			String isIncludedPostage = item_e.element("isIncludedPostage").getText();
			if ("true".equals(isIncludedPostage)) {
				item.setIncludedPostage(true);
			} else if ("false".equals(isIncludedPostage)) {
				item.setIncludedPostage(false);
			}
			// 代引き手数料
			String isIncludedCashOnDeliveryPostage = item_e.element("isIncludedCashOnDeliveryPostage").getText();
			if ("true".equals(isIncludedCashOnDeliveryPostage)) {
				item.setIncludedCashOnDeliveryPostage(true);
			} else if ("false".equals(isIncludedCashOnDeliveryPostage)) {
				item.setIncludedCashOnDeliveryPostage(false);
			}
			// 表示価格
			if (item_e.element("displayPrice") != null) {
				String displayPrice = item_e.element("displayPrice").getText();
				if (!Utility.isEmptyString(displayPrice)) {
					item.setDisplayPrice(Integer.valueOf(displayPrice));
				}
			}
			// 注文受付数
			if (item_e.element("orderLimit") != null) {
				String orderLimit = item_e.element("orderLimit").getText();
				if (!Utility.isEmptyString(orderLimit)) {
					item.setOrderLimit(Integer.valueOf(orderLimit));
				}
			}
			// 個別送料
			if (item_e.element("postage") != null) {
				String postage = item_e.element("postage").getText();
				if (!Utility.isEmptyString(postage)) {
					item.setPostage(Integer.valueOf(postage));
				}
			}
			// 送料区分１
			if (item_e.element("postageSegment1") != null) {
				String postageSegment1 = item_e.element("postageSegment1").getText();
				if (!Utility.isEmptyString(postageSegment1)) {
					item.setPostageSegment1(Integer.valueOf(postageSegment1));
				}
			}
			// 送料区分２
			if (item_e.element("postageSegment2") != null) {
				String postageSegment2 = item_e.element("postageSegment2").getText();
				if (!Utility.isEmptyString(postageSegment2)) {
					item.setPostageSegment2(Integer.valueOf(postageSegment2));
				}
			}
			// のし対応
			String isNoshiEnable = item_e.element("isNoshiEnable").getText();
			if ("true".equals(isNoshiEnable)) {
				item.setNoshiEnable(true);
			} else if ("false".equals(isNoshiEnable)) {
				item.setNoshiEnable(false);
			}
			// 期間限定販売フラグ
			String isTimeSale = item_e.element("isTimeSale").getText();
			if ("true".equals(isTimeSale)) {
				item.setTimeSale(true);
			} else if ("false".equals(isTimeSale)) {
				item.setTimeSale(false);
			}
			// 期間限定販売開始日
			// <timeSaleStartDateTime>2015-09-01T00:00:00+09:00</timeSaleStartDateTime>
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPAN);
			if (item_e.element("timeSaleStartDateTime") != null) {
				String timeSaleStartDateTime = item_e.element("timeSaleStartDateTime").getText().replace("T", " ")
						.replace("+09:00", "");
				if (!Utility.isEmptyString(timeSaleStartDateTime)) {
					item.setTimeSaleStartDateTime(sdf.parse(timeSaleStartDateTime));
				}
			}
			// 期間限定販売終了日
			if (item_e.element("timeSaleEndDateTime") != null) {
				String timeSaleEndDateTime = item_e.element("timeSaleEndDateTime").getText().replace("T", " ")
						.replace("+09:00", "");
				if (!Utility.isEmptyString(timeSaleEndDateTime)) {
					item.setTimeSaleEndDateTime(sdf.parse(timeSaleEndDateTime));
				}
			}
			// サーチ非表示
			String isUnavailableForSearch = item_e.element("isUnavailableForSearch").getText();
			if ("true".equals(isUnavailableForSearch)) {
				item.setUnavailableForSearch(true);
			} else if ("false".equals(isUnavailableForSearch)) {
				item.setUnavailableForSearch(false);
			}
			// 闇市パスワード
			if (item_e.element("limitedPasswd") != null) {
				item.setLimitedPasswd(item_e.element("limitedPasswd").getText());
			}
			// モバイル表示
			String isAvailableForMobile = item_e.element("isAvailableForMobile").getText();
			if ("true".equals(isAvailableForMobile)) {
				item.setAvailableForMobile(true);
			} else if ("false".equals(isAvailableForMobile)) {
				item.setAvailableForMobile(false);
			}
			// 倉庫指定
			String isDepot = item_e.element("isDepot").getText();
			if ("true".equals(isDepot)) {
				item.setDepot(true);
			} else if ("false".equals(isDepot)) {
				item.setDepot(false);
			}
			// あす楽配送管理番号
			if (item_e.element("asurakuDeliveryId") != null) {
				item.setAsurakuDeliveryId(item_e.element("asurakuDeliveryId").getText());
			}
			// サイズ表リンクコード
			if (item_e.element("sizeChartLinkCode") != null) {
				item.setSizeChartLinkCode(item_e.element("sizeChartLinkCode").getText());
			}
			// レビュー表示
			if (item_e.element("reviewDisp") != null) {
				String reviewDisp = item_e.element("reviewDisp").getText();
				if (!Utility.isEmptyString(reviewDisp)) {
					item.setReviewDisp(Integer.valueOf(reviewDisp));
				}
			}
			// 二重価格文言ID
			if (item_e.element("displayPriceId") != null) {
				item.setDisplayPriceId(item_e.element("displayPriceId").getText());
			}
			// 表示優先度
			if (item_e.element("itemWeight") != null) {
				String itemWeight = item_e.element("itemWeight").getText();
				if (!Utility.isEmptyString(itemWeight)) {
					item.setItemWeight(Integer.valueOf(itemWeight));
				}
			}
			// ヘッダー・フッター・レフトナビのテンプレートID
			if (item_e.element("layoutCommonId") != null) {
				String layoutCommonId = item_e.element("layoutCommonId").getText();
				if (!Utility.isEmptyString(layoutCommonId)) {
					item.setLayoutCommonId(Integer.valueOf(layoutCommonId));
				}
			}
			// 表示項目の並び順のテンプレートID
			if (item_e.element("layoutMapId") != null) {
				String layoutMapId = item_e.element("layoutMapId").getText();
				if (!Utility.isEmptyString(layoutMapId)) {
					item.setLayoutMapId(Integer.valueOf(layoutMapId));
				}
			}
			// 共通説明文(小)のテンプレートID
			if (item_e.element("textSmallId") != null) {
				String textSmallId = item_e.element("textSmallId").getText();
				if (!Utility.isEmptyString(textSmallId)) {
					item.setTextSmallId(Integer.valueOf(textSmallId));
				}
			}
			// 目玉商品のテンプレートID
			if (item_e.element("lossLeaderId") != null) {
				String lossLeaderId = item_e.element("lossLeaderId").getText();
				if (!Utility.isEmptyString(lossLeaderId)) {
					item.setLossLeaderId(Integer.valueOf(lossLeaderId));
				}
			}
			// 共通説明文(大)のテンプレートID
			if (item_e.element("textLargeId") != null) {
				String textLargeId = item_e.element("textLargeId").getText();
				if (!Utility.isEmptyString(textLargeId)) {
					item.setTextLargeId(Integer.valueOf(textLargeId));
				}
			}
			// 商品画像情報リスト
			if (item_e.element("images") != null) {
				Images images = new Images();
				item.setImages(images);
				List<Element> image_es = item_e.element("images").elements("image");
				if (image_es != null) {
					List<Image> image = new ArrayList<Image>();
					images.setImage(image);

					for (Element image_e : image_es) {

						Image a_image = new Image();
						image.add(a_image);
						// 商品画像URL
						if (image_e.element("imageUrl") != null) {
							a_image.setImageUrl(image_e.element("imageUrl").getText());
						}

						// 商品画像名
						if (image_e.element("imageAlt") != null) {
							a_image.setImageAlt(image_e.element("imageAlt").getText());
						}

					}

				}
			}

			// Select/Checkbox用項目情報リスト
			if (item_e.element("options") != null) {
				Options options = new Options();
				item.setOptions(options);
				List<Element> option_es = item_e.element("options").elements("option");
				if (option_es != null) {
					List<Option> option = new ArrayList<Option>();
					options.setOption(option);

					for (Element option_e : option_es) {

						Option a_option = new Option();
						option.add(a_option);

						// Select/Checkbox用項目名
						if (option_e.element("optionName") != null) {
							a_option.setOptionName(option_e.element("optionName").getText());
						}

						// 選択肢スタイル
						if (option_e.element("optionStyle") != null) {
							if (!Utility.isEmptyString(option_e.element("optionStyle").getText())) {
								a_option.setOptionStyle(Integer.valueOf(option_e.element("optionStyle").getText()));
							}
						}

						// Select/Checkbox用選択肢情報リスト
						if (option_e.element("optionValues") != null) {
							OptionValues optionValues = new OptionValues();
							a_option.setOptionValues(optionValues);

							List<Element> optionValue_es = option_e.element("optionValues").elements("optionValue");
							if (optionValue_es != null) {
								List<OptionValue> optionValue = new ArrayList<OptionValue>();
								optionValues.setOptionValue(optionValue);

								for (Element optionValue_e : optionValue_es) {

									OptionValue a_optionValue = new OptionValue();
									optionValue.add(a_optionValue);
									// Select/Checkbox用選択肢
									if (optionValue_e.element("value") != null) {
										a_optionValue.setValue(optionValue_e.element("value").getText());
									}

								}

							}
						}

					}

				}
			}
			// ポイント情報
			if (item_e.element("point") != null) {
				Point point = new Point();
				item.setPoint(point);
				// ポイント変倍率
				if (item_e.element("point").element("pointRate") != null) {
					if (!Utility.isEmptyString(item_e.element("point").element("pointRate").getText())) {
						point.setPointRate(Integer.valueOf(item_e.element("point").element("pointRate").getText()));
					}
				}

				// ポイント変倍適応期間（開始）
				if (item_e.element("point").element("pointRateStart") != null) {
					if (!Utility.isEmptyString(item_e.element("point").element("pointRateStart").getText())) {
						point.setPointRateStart(sdf.parse(item_e.element("point").element("pointRateStart").getText()
								.replace("T", " ").replace("+09:00", "")));
					}
				}
				// ポイント変倍適応期間（終了）
				if (item_e.element("point").element("pointRateEnd") != null) {
					if (!Utility.isEmptyString(item_e.element("point").element("pointRateEnd").getText())) {
						point.setPointRateEnd(sdf.parse(item_e.element("point").element("pointRateEnd").getText()
								.replace("T", " ").replace("+09:00", "")));
					}
				}
			}

			// 商品在庫情報
			if (item_e.element("itemInventory") != null) {
				ItemInventory itemInventory = new ItemInventory();
				item.setItemInventory(itemInventory);

				// 在庫タイプ（在庫種別）
				if (item_e.element("itemInventory").element("inventoryType") != null) {
					if (!Utility.isEmptyString(item_e.element("itemInventory").element("inventoryType").getText())) {
						itemInventory.setInventoryType(
								Integer.valueOf(item_e.element("itemInventory").element("inventoryType").getText()));

					}
				}
				// 在庫情報リスト
				if (item_e.element("itemInventory").elements("inventories") != null) {
					List<Inventories> inventories = new ArrayList<Inventories>();
					itemInventory.setInventories(inventories);

					List<Element> inventories_es = item_e.element("itemInventory").elements("inventories");
					for (Element inventories_e : inventories_es) {
						Inventories a_inventories = new Inventories();
						inventories.add(a_inventories);
						// 在庫情報
						if (inventories_e.elements("inventory") != null) {
							List<Inventory> inventory_s = new ArrayList<Inventory>();
							List<Element> inventory_es = inventories_e.elements("inventory");
							a_inventories.setInventory(inventory_s);
							for (Element inventory_e : inventory_es) {
								Inventory a_inventory = new Inventory();
								inventory_s.add(a_inventory);

								// 在庫数
								if (inventory_e.element("inventoryCount") != null) {
									if (!Utility.isEmptyString(inventory_e.element("inventoryCount").getText())) {
										a_inventory.setInventoryCount(
												Integer.valueOf(inventory_e.element("inventoryCount").getText()));

									}
								}
								// 項目選択肢別在庫用縦軸選択肢子番号
								if (inventory_e.element("childNoVertical") != null) {
									a_inventory.setChildNoVertical(inventory_e.element("childNoVertical").getText());
								}
								// 項目選択肢別在庫用横軸選択肢子番号
								if (inventory_e.element("childNoHorizontal") != null) {
									a_inventory
											.setChildNoHorizontal(inventory_e.element("childNoHorizontal").getText());
								}
								// 項目選択肢別在庫用縦軸選択肢
								if (inventory_e.element("optionNameVertical") != null) {
									a_inventory
											.setOptionNameVertical(inventory_e.element("optionNameVertical").getText());
								}
								// 項目選択肢別在庫用横軸選択肢
								if (inventory_e.element("optionNameHorizontal") != null) {
									a_inventory.setOptionNameHorizontal(
											inventory_e.element("optionNameHorizontal").getText());
								}
								// 項目選択肢別在庫用取り寄せ可能表示
								if (inventory_e.element("isBackorderAvailable") != null) {
									if ("true".equals(inventory_e.element("isBackorderAvailable").getText())) {
										a_inventory.setBackorderAvailable(true);
									} else if ("false".equals(inventory_e.element("isBackorderAvailable").getText())) {
										a_inventory.setBackorderAvailable(false);
									}
								}
								// 在庫あり時納期管理番号
								if (inventory_e.element("normalDeliveryDateId") != null) {
									a_inventory.setNormalDeliveryDateId(
											inventory_e.element("normalDeliveryDateId").getText());
								}
								// 在庫切れ時納期管理番号
								if (inventory_e.element("backorderDeliveryDateId") != null) {
									a_inventory.setBackorderDeliveryDateId(
											inventory_e.element("backorderDeliveryDateId").getText());
								}
								// 在庫切れ時の注文受付
								if (inventory_e.element("isBackorder") != null) {
									if ("true".equals(inventory_e.element("isBackorder").getText())) {
										a_inventory.setBackorder(true);
									} else if ("false".equals(inventory_e.element("isBackorder").getText())) {
										a_inventory.setBackorder(false);
									}
								}
								// 在庫戻し設定
								if (inventory_e.element("isRestoreInventoryFlag") != null) {
									if ("true".equals(inventory_e.element("isRestoreInventoryFlag").getText())) {
										a_inventory.setRestoreInventoryFlag(true);
									} else
										if ("false".equals(inventory_e.element("isRestoreInventoryFlag").getText())) {
										a_inventory.setRestoreInventoryFlag(false);
									}
								}
							}
						}

					}

				}
				// 項目選択肢別在庫用縦軸選択肢項目名
				if (item_e.element("itemInventory").element("verticalName") != null) {
					itemInventory.setVerticalName(item_e.element("itemInventory").element("verticalName").getText());
				}
				// 項目選択肢別在庫用横軸選択肢項目名
				if (item_e.element("itemInventory").element("horizontalName") != null) {
					itemInventory
							.setHorizontalName(item_e.element("itemInventory").element("horizontalName").getText());
				}
				// 在庫数表示
				if (item_e.element("itemInventory").element("inventoryQuantityFlag") != null) {
					if (!Utility.isEmptyString(
							item_e.element("itemInventory").element("inventoryQuantityFlag").getText())) {
						itemInventory.setInventoryQuantityFlag(Integer
								.valueOf(item_e.element("itemInventory").element("inventoryQuantityFlag").getText()));

					}
				}
				// 項目選択肢別在庫用残り表示閾値
				if (item_e.element("itemInventory").element("inventoryDisplayFlag") != null) {
					if (!Utility
							.isEmptyString(item_e.element("itemInventory").element("inventoryDisplayFlag").getText())) {
						itemInventory.setInventoryDisplayFlag(Integer
								.valueOf(item_e.element("itemInventory").element("inventoryDisplayFlag").getText()));

					}
				}
			}
			// // 薬事情報
			if (item_e.element("medicine") != null) {
				Medicine medicine = new Medicine();

				// 医薬品説明文
				if (item_e.element("medicine").element("medCaption") != null) {
					medicine.setMedCaption(item_e.element("medicine").element("medCaption").getText());
				}
				// 医薬品注意事項
				if (item_e.element("medicine").element("medAttention") != null) {
					medicine.setMedAttention(item_e.element("medicine").element("medAttention").getText());
				}

			}
			// カテゴリ情報リスト
			if (item_e.element("categories") != null) {
				Categories categories = new Categories();
				item.setCategories(categories);
				List<Element> categoryInfo_es = item_e.element("categories").elements("categoryInfo");
				if (categoryInfo_es != null) {
					List<CategoryInfo> categoryInfo = new ArrayList<CategoryInfo>();
					categories.setCategoryInfo(categoryInfo);

					for (Element categoryInfo_e : categoryInfo_es) {

						CategoryInfo a_categoryInfo = new CategoryInfo();
						categoryInfo.add(a_categoryInfo);
						// カテゴリセット管理番号
						if (categoryInfo_e.element("categorySetManageNumber") != null) {
							a_categoryInfo.setCategorySetManageNumber(
									categoryInfo_e.element("categorySetManageNumber").getText());
						}

						// カテゴリID
						if (categoryInfo_e.element("categoryId") != null) {
							if (!Utility.isEmptyString(categoryInfo_e.element("categoryId").getText())) {
								a_categoryInfo
										.setCategoryId(Integer.valueOf(categoryInfo_e.element("categoryId").getText()));
							}
						}
						// 複数表示形式
						if (categoryInfo_e.element("isPluralItemPage") != null) {
							if (!Utility.isEmptyString(categoryInfo_e.element("isPluralItemPage").getText())) {
								if ("true".equals(categoryInfo_e.element("isPluralItemPage").getText())) {
									a_categoryInfo.setPluralItemPage(true);
								} else if ("false".equals(categoryInfo_e.element("isPluralItemPage").getText())) {
									a_categoryInfo.setPluralItemPage(false);
								}
							}
						}

					}

				}
			}
			// タグIDリスト
			if (item_e.element("tagIds") != null) {
				TagIds tagIds = new TagIds();
				item.setTagIds(tagIds);

				if (item_e.element("tagIds").elements("tagId") != null) {
					List<String> tagId = new ArrayList<String>();
					tagIds.setTagId(tagId);

					List<Element> tagId_es = item_e.element("tagIds").elements("tagId");
					for (Element tagId_e : tagId_es) {
						tagId.add(tagId_e.getText());
					}
				}
			}

		}
		return item;
	}

	public String convertBeanToXml(Item item) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.JAPAN);
		String itemxml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request><itemUpdateRequest><item>";

		// 商品管理番号
		String itemUrl = item.getItemUrl();
		// 商品番号
		String itemNumber = item.getItemNumber();
		// 商品名
		String itemName = item.getItemName();
		// 販売価格
		int itemPrice = item.getItemPrice();
		// 全商品ディレクトリID
		int genreId = item.getGenreId();
		// カタログID
		String catalogId = item.getCatalogId();
		// PC用商品説明文
		String descriptionForPC = item.getDescriptionForPC();
		// モバイル用商品説明文
		String descriptionForMobile = item.getDescriptionForMobile();
		// スマートフォン用商品説明文
		String descriptionForSmartPhone = item.getDescriptionForSmartPhone();
		// 動画URL
		String movieUrl = item.getMovieUrl();
		// PC用キャッチコピー
		String catchCopyForPC = item.getCatchCopyForPC();
		// モバイル用キャッチコピー
		String catchCopyForMobile = item.getCatchCopyForMobile();
		// PC用販売説明文
		String descriptionBySalesMethod = item.getDescriptionBySalesMethod();
		// 注文ボタン
		boolean isSaleButton = item.isSaleButton();
		// 資料請求ボタン
		boolean isDocumentButton = item.isDocumentButton();
		// 商品問い合わせボタン
		boolean isInquiryButton = item.isInquiryButton();
		// 再入荷のお知らせボタン
		boolean isStockNoticeButton = item.isStockNoticeButton();
		// 商品情報レイアウト
		int itemLayout = item.getItemLayout();
		// 消費税
		boolean isIncludedTax = item.isIncludedTax();
		// 送料
		boolean isIncludedPostage = item.isIncludedPostage();
		// 代引き手数料
		boolean isIncludedCashOnDeliveryPostage = item.isIncludedCashOnDeliveryPostage();
		// 表示価格
		int displayPrice = item.getDisplayPrice();
		// 注文受付数
		int orderLimit = item.getOrderLimit();
		// 個別送料
		int postage = item.getPostage();
		// 送料区分１
		int postageSegment1 = item.getPostageSegment1();
		// 送料区分２
		int postageSegment2 = item.getPostageSegment2();
		// のし対応
		boolean isNoshiEnable = item.isNoshiEnable();
		// 期間限定販売フラグ
		boolean isTimeSale = item.isTimeSale();
		// 期間限定販売開始日
		// <timeSaleStartDateTime>2015-09-01T00:00:00+09:00</timeSaleStartDateTime>
		Date timeSaleStartDateTime_d = item.getTimeSaleStartDateTime();
		String timeSaleStartDateTime = "";
		if (timeSaleStartDateTime_d != null) {
			timeSaleStartDateTime = sdf.format(timeSaleStartDateTime_d);
			timeSaleStartDateTime = timeSaleStartDateTime.replace(" ", "T") + "+09:00";
		}
		// 期間限定販売終了日
		Date timeSaleEndDateTime_d = item.getTimeSaleEndDateTime();
		String timeSaleEndDateTime = "";
		if (timeSaleEndDateTime_d != null) {
			timeSaleEndDateTime = sdf.format(timeSaleEndDateTime_d);
			timeSaleEndDateTime = timeSaleEndDateTime.replace(" ", "T") + "+09:00";
		}
		// サーチ非表示
		boolean isUnavailableForSearch = item.isUnavailableForSearch();
		// 闇市パスワード
		String limitedPasswd = item.getLimitedPasswd();
		// モバイル表示
		boolean isAvailableForMobile = item.isAvailableForMobile();
		// 倉庫指定
		boolean isDepot = item.isDepot();
		// あす楽配送管理番号
		String asurakuDeliveryId = item.getAsurakuDeliveryId();
		// サイズ表リンクコード
		String sizeChartLinkCode = item.getSizeChartLinkCode();
		// レビュー表示
		int reviewDisp = item.getReviewDisp();
		// 二重価格文言ID
		String displayPriceId = item.getDisplayPriceId();
		// 表示優先度
		int itemWeight = item.getItemWeight();
		// ヘッダー・フッター・レフトナビのテンプレートID
		int layoutCommonId = item.getLayoutCommonId();
		// 表示項目の並び順のテンプレートID
		int layoutMapId = item.getLayoutMapId();
		// 共通説明文(小)のテンプレートID
		int textSmallId = item.getTextSmallId();
		// 目玉商品のテンプレートID
		int lossLeaderId = item.getLossLeaderId();
		// 共通説明文(大)のテンプレートI在
		int textLargeId = item.getTextLargeId();
		// 商品画像情報リスト
		Images images = item.getImages();
		// Select/Checkbox用項目情報リスト
		Options options = item.getOptions();
		// ポイント情報
		Point point = item.getPoint();
		// 商品在庫情報
		ItemInventory itemInventory = item.getItemInventory();
		// 薬事情報
		Medicine medicine = item.getMedicine();
		// カテゴリ情報リスト
		Categories categories = item.getCategories();
		// タグIDリスト
		TagIds tagIds = item.getTagIds();

		if (!Utility.isEmptyString(itemUrl)) {
			itemxml += ("<itemUrl>" + itemUrl + "</itemUrl>");
		}
		if (!Utility.isEmptyString(itemNumber)) {
			itemxml += ("<itemNumber>" + itemNumber + "</itemNumber>");
		}
		if (!Utility.isEmptyString(itemName)) {
			itemxml += ("<itemName>" + itemName + "</itemName>");
		}

		itemxml += ("<itemPrice>" + itemPrice + "</itemPrice>");

		if (genreId != 0) {
			itemxml += ("<genreId>" + genreId + "</genreId>");
		}
		if (!Utility.isEmptyString(catalogId)) {
			itemxml += ("<catalogId>" + catalogId + "</catalogId>");
		}
		if (images != null) {
			itemxml += "<images>";
			// 商品画像情報
			List<Image> image_s = images.getImage();
			for (Image image : image_s) {
				itemxml += "<image>";
				// 商品画像URL
				String imageUrl = image.getImageUrl();
				if (!Utility.isEmptyString(imageUrl)) {
					itemxml += ("<imageUrl>" + imageUrl + "</imageUrl>");
				}
				// 商品画像名
				String imageAlt = image.getImageAlt();
				if (!Utility.isEmptyString(imageAlt)) {
					itemxml += ("<imageAlt>" + imageAlt + "</imageAlt>");
				}

				itemxml += "</image>";
			}

			itemxml += "</images>";
		}

		if (!Utility.isEmptyString(descriptionForPC)) {
			itemxml += ("<descriptionForPC>" + "<![CDATA[" + descriptionForPC + "]]>" + "</descriptionForPC>");
		}
		if (!Utility.isEmptyString(descriptionForMobile)) {
			itemxml += ("<descriptionForMobile>" + "<![CDATA[" + descriptionForMobile + "]]>"
					+ "</descriptionForMobile>");
		}
		if (!Utility.isEmptyString(descriptionForSmartPhone)) {
			itemxml += ("<descriptionForSmartPhone>" + "<![CDATA[" + descriptionForSmartPhone + "]]>"
					+ "</descriptionForSmartPhone>");
		}
		if (!Utility.isEmptyString(movieUrl)) {
			itemxml += ("<movieUrl>" + movieUrl + "</movieUrl>");
		}
		if (options != null) {
			itemxml += "<options>";

			// Select/Checkbox用項目情報
			List<Option> option_s = options.getOption();
			for (Option option : option_s) {
				itemxml += "<option>";

				// Select/Checkbox用項目名
				String optionName = option.getOptionName();
				if (!Utility.isEmptyString(optionName)) {
					itemxml += ("<optionName>" + optionName + "</optionName>");
				}
				// 選択肢スタイル
				int optionStyle = option.getOptionStyle();
				if (optionStyle != 0) {
					itemxml += ("<optionStyle>" + optionStyle + "</optionStyle>");
				}
				// Select/Checkbox用選択肢情報リスト
				OptionValues optionValues = option.getOptionValues();
				if (optionValues != null) {
					itemxml += "<optionValues>";
					// Select/Checkbox用選択肢情報
					List<OptionValue> optionValue_s = optionValues.getOptionValue();
					for (OptionValue optionValue : optionValue_s) {
						itemxml += "<optionValue>";
						// Select/Checkbox用選択肢
						String value = optionValue.getValue();
						if (!Utility.isEmptyString(value)) {
							itemxml += ("<value>" + value + "</value>");
						}
						itemxml += "</optionValue>";
					}

					itemxml += "</optionValues>";
				}

				itemxml += "</option>";
			}

			itemxml += "</options>";
		}
		if (tagIds != null) {
			itemxml += "<tagIds>";

			List<String> tagId_s = tagIds.getTagId();
			for (String tagId : tagId_s) {
				if (!Utility.isEmptyString(tagId)) {
					itemxml += ("<tagId>" + tagId + "</tagId>");
				}
			}

			itemxml += "</tagIds>";
		}

		if (!Utility.isEmptyString(catchCopyForPC)) {
			itemxml += ("<catchCopyForPC>" + catchCopyForPC + "</catchCopyForPC>");
		}

		if (!Utility.isEmptyString(catchCopyForMobile)) {
			itemxml += ("<catchCopyForMobile>" + catchCopyForMobile + "</catchCopyForMobile>");
		}

		if (!Utility.isEmptyString(descriptionBySalesMethod)) {
			itemxml += ("<descriptionBySalesMethod>" + "<![CDATA[" + descriptionBySalesMethod + "]]>"
					+ "</descriptionBySalesMethod>");
		}

		itemxml += ("<isSaleButton>" + isSaleButton + "</isSaleButton>");

		itemxml += ("<isDocumentButton>" + isDocumentButton + "</isDocumentButton>");

		itemxml += ("<isInquiryButton>" + isInquiryButton + "</isInquiryButton>");

		itemxml += ("<isStockNoticeButton>" + isStockNoticeButton + "</isStockNoticeButton>");

		if (itemLayout != 0) {
			itemxml += ("<itemLayout>" + itemLayout + "</itemLayout>");
		}

		itemxml += ("<isIncludedTax>" + isIncludedTax + "</isIncludedTax>");

		itemxml += ("<isIncludedPostage>" + isIncludedPostage + "</isIncludedPostage>");

		itemxml += ("<isIncludedCashOnDeliveryPostage>" + isIncludedCashOnDeliveryPostage
				+ "</isIncludedCashOnDeliveryPostage>");

		if (displayPrice != 0) {
			itemxml += ("<displayPrice>" + displayPrice + "</displayPrice>");
		}

		itemxml += ("<orderLimit>" + orderLimit + "</orderLimit>");

		itemxml += ("<postage>" + postage + "</postage>");

		if (postageSegment1 != 0) {
			itemxml += ("<postageSegment1>" + postageSegment1 + "</postageSegment1>");
		}

		if (postageSegment2 != 0) {
			itemxml += ("<postageSegment2>" + postageSegment2 + "</postageSegment2>");
		}

		itemxml += ("<isNoshiEnable>" + isNoshiEnable + "</isNoshiEnable>");

		itemxml += ("<isTimeSale>" + isTimeSale + "</isTimeSale>");

		if (!Utility.isEmptyString(timeSaleStartDateTime)) {
			itemxml += ("<timeSaleStartDateTime>" + timeSaleStartDateTime + "</timeSaleStartDateTime>");
		}

		if (!Utility.isEmptyString(timeSaleEndDateTime)) {
			itemxml += ("<timeSaleEndDateTime>" + timeSaleEndDateTime + "</timeSaleEndDateTime>");
		}

		itemxml += ("<isUnavailableForSearch>" + isUnavailableForSearch + "</isUnavailableForSearch>");

		if (!Utility.isEmptyString(limitedPasswd)) {
			itemxml += ("<limitedPasswd>" + limitedPasswd + "</limitedPasswd>");
		}

		itemxml += ("<isAvailableForMobile>" + isAvailableForMobile + "</isAvailableForMobile>");

		itemxml += ("<isDepot>" + isDepot + "</isDepot>");

		if (point != null) {
			itemxml += "<point>";

			// ポイント変倍率
			int pointRate = point.getPointRate();
			if (pointRate != 0) {
				itemxml += ("<pointRate>" + pointRate + "</pointRate>");
			}
			// ポイント変倍適応期間（開始）
			Date pointRateStart_d = point.getPointRateStart();
			if (pointRateStart_d != null) {
				String pointRateStart = sdf.format(pointRateStart_d);
				pointRateStart = pointRateStart.replace(" ", "T") + "+09:00";
				itemxml += ("<pointRateStart>" + pointRateStart + "</pointRateStart>");
			}

			// ポイント変倍適応期間（終了）
			Date pointRateEnd_d = point.getPointRateEnd();
			if (pointRateEnd_d != null) {
				String pointRateEnd = sdf.format(pointRateEnd_d);
				pointRateEnd = pointRateEnd.replace(" ", "T") + "+09:00";
				itemxml += ("<pointRateEnd>" + pointRateEnd + "</pointRateEnd>");
			}

			itemxml += "</point>";
		}

		if (itemInventory != null) {
			itemxml += "<itemInventory>";

			// 在庫タイプ（在庫種別）
			int inventoryType = itemInventory.getInventoryType();
			itemxml += ("<inventoryType>" + inventoryType + "</inventoryType>");
			// 在庫情報リスト
			List<Inventories> inventories_s = itemInventory.getInventories();
			for (Inventories inventories : inventories_s) {
				itemxml += "<inventories>";
				// 在庫情報
				List<Inventory> inventory_s = inventories.getInventory();
				for (Inventory inventory : inventory_s) {
					itemxml += "<inventory>";
					// 在庫数
					int inventoryCount = inventory.getInventoryCount();
					itemxml += ("<inventoryCount>" + inventoryCount + "</inventoryCount>");
					// 項目選択肢別在庫用縦軸選択肢子番号
					String childNoVertical = inventory.getChildNoVertical();
					if (!Utility.isEmptyString(childNoVertical)) {
						itemxml += ("<childNoVertical>" + childNoVertical + "</childNoVertical>");
					}
					// 項目選択肢別在庫用横軸選択肢子番号
					String childNoHorizontal = inventory.getChildNoHorizontal();
					if (!Utility.isEmptyString(childNoHorizontal)) {
						itemxml += ("<childNoHorizontal>" + childNoHorizontal + "</childNoHorizontal>");
					}
					// 項目選択肢別在庫用縦軸選択肢
					String optionNameVertical = inventory.getOptionNameVertical();
					if (!Utility.isEmptyString(optionNameVertical)) {
						itemxml += ("<optionNameVertical>" + optionNameVertical + "</optionNameVertical>");
					}
					// 項目選択肢別在庫用横軸選択肢
					String optionNameHorizontal = inventory.getOptionNameHorizontal();
					if (!Utility.isEmptyString(optionNameHorizontal)) {
						itemxml += ("<optionNameHorizontal>" + optionNameHorizontal + "</optionNameHorizontal>");
					}
					// 項目選択肢別在庫用取り寄せ可能表示
					boolean isBackorderAvailable = inventory.isBackorderAvailable();
					itemxml += ("<isBackorderAvailable>" + isBackorderAvailable + "</isBackorderAvailable>");
					// 在庫あり時納期管理番号
					String normalDeliveryDateId = inventory.getNormalDeliveryDateId();
					if (!Utility.isEmptyString(normalDeliveryDateId)) {
						itemxml += ("<normalDeliveryDateId>" + normalDeliveryDateId + "</normalDeliveryDateId>");
					}
					// 在庫切れ時納期管理番号
					String backorderDeliveryDateId = inventory.getBackorderDeliveryDateId();
					if (!Utility.isEmptyString(backorderDeliveryDateId)) {
						itemxml += ("<backorderDeliveryDateId>" + backorderDeliveryDateId
								+ "</backorderDeliveryDateId>");
					}
					// 在庫切れ時の注文受付
					boolean isBackorder = inventory.isBackorder();
					itemxml += ("<isBackorder>" + isBackorder + "</isBackorder>");
					// 在庫戻し設定
					boolean isRestoreInventoryFlag = inventory.isRestoreInventoryFlag();
					itemxml += ("<isRestoreInventoryFlag>" + isRestoreInventoryFlag + "</isRestoreInventoryFlag>");
					itemxml += "</inventory>";
				}

				itemxml += "</inventories>";
			}
			// 項目選択肢別在庫用縦軸選択肢項目名
			String verticalName = itemInventory.getVerticalName();
			if (!Utility.isEmptyString(verticalName)) {
				itemxml += ("<verticalName>" + verticalName + "</verticalName>");
			}
			// 項目選択肢別在庫用横軸選択肢項目名
			String horizontalName = itemInventory.getHorizontalName();
			if (!Utility.isEmptyString(horizontalName)) {
				itemxml += ("<horizontalName>" + horizontalName + "</horizontalName>");
			}
			// 在庫数表示
			int inventoryQuantityFlag = itemInventory.getInventoryQuantityFlag();
			if (inventoryType == 1) {
				itemxml += ("<inventoryQuantityFlag>" + inventoryQuantityFlag + "</inventoryQuantityFlag>");
			}
			// 項目選択肢別在庫用残り表示閾値
			int inventoryDisplayFlag = itemInventory.getInventoryDisplayFlag();
			itemxml += ("<inventoryDisplayFlag>" + inventoryDisplayFlag + "</inventoryDisplayFlag>");

			itemxml += "</itemInventory>";
		}

		if (!Utility.isEmptyString(asurakuDeliveryId)) {
			itemxml += ("<asurakuDeliveryId>" + asurakuDeliveryId + "</asurakuDeliveryId>");
		}

		if (!Utility.isEmptyString(sizeChartLinkCode)) {
			itemxml += ("<sizeChartLinkCode>" + sizeChartLinkCode + "</sizeChartLinkCode>");
		}

		itemxml += ("<reviewDisp>" + reviewDisp + "</reviewDisp>");

		if (medicine != null) {
			itemxml += "<medicine>";

			// 医薬品説明文
			String medCaption = medicine.getMedCaption();
			if (!Utility.isEmptyString(medCaption)) {
				itemxml += ("<medCaption>" + medCaption + "</medCaption>");
			}
			// 医薬品注意事項
			String medAttention = medicine.getMedAttention();
			if (!Utility.isEmptyString(medAttention)) {
				itemxml += ("<medAttention>" + medAttention + "</medAttention>");
			}
			itemxml += "</medicine>";
		}

		if (displayPrice > 0) {
			itemxml += ("<displayPriceId>" + displayPriceId + "</displayPriceId>");
		}

		if (categories != null) {
			itemxml += "<categories>";

			List<CategoryInfo> categoryInfo_s = categories.getCategoryInfo();
			for (CategoryInfo categoryInfo : categoryInfo_s) {
				itemxml += "<categoryInfo>";
				// カテゴリセット管理番号
				String categorySetManageNumber = categoryInfo.getCategorySetManageNumber();
				if (!Utility.isEmptyString(categorySetManageNumber)) {
					itemxml += ("<categorySetManageNumber>" + categorySetManageNumber + "</categorySetManageNumber>");
				}
				// カテゴリID
				int categoryId = categoryInfo.getCategoryId();
				itemxml += ("<categoryId>" + categoryId + "</categoryId>");
				// 複数表示形式
				boolean isPluralItemPage = categoryInfo.isPluralItemPage();
				itemxml += ("<isPluralItemPage>" + isPluralItemPage + "</isPluralItemPage>");
				itemxml += "</categoryInfo>";
			}

			itemxml += "</categories>";
		}

		itemxml += ("<itemWeight>" + itemWeight + "</itemWeight>");

		itemxml += ("<layoutCommonId>" + layoutCommonId + "</layoutCommonId>");

		itemxml += ("<layoutMapId>" + layoutMapId + "</layoutMapId>");

		itemxml += ("<textSmallId>" + textSmallId + "</textSmallId>");

		itemxml += ("<lossLeaderId>" + lossLeaderId + "</lossLeaderId>");

		itemxml += ("<textLargeId>" + textLargeId + "</textLargeId>");

		itemxml += "</item></itemUpdateRequest></request>";
		return itemxml;
	}

	public String getLicenseKey(String shop) {
		String licenseKey = null;
		Connection conn = null;
		try {
			conn = com.rakuten.util.JdbcConnection.getConnection();
			PreparedStatement ps = null;
			String sql= "SELECT LICENSE_KEY FROM rakuten.shop where SITE = '楽天' AND SHOP_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shop);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				licenseKey = rs.getString("LICENSE_KEY");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return licenseKey;
	}

	public String getServiceSecret(String shop) {
		String serviceSecret = null;
		Connection conn = null;
		try {
			conn = com.rakuten.util.JdbcConnection.getConnection();
			PreparedStatement ps = null;
			String sql= "SELECT SERVICE_KEY FROM rakuten.shop where SITE = '楽天' AND SHOP_ID = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shop);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				serviceSecret = rs.getString("SERVICE_KEY");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return serviceSecret;
	}

	public String getShohinXmlFromDB(String shop, String shohinbango) throws Exception {
		String shohinXml = null;
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		try {
			conn = JdbcConnection.getConnection();
			sql = "SELECT shohinxml from  shohinxml_tbl where shop = ? and itemurl = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, shop);
			ps.setString(2, shohinbango);
			rs = ps.executeQuery();
			while (rs.next()) {
				shohinXml = rs.getString("shohinxml");
			}

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
		return shohinXml;
	}
}
