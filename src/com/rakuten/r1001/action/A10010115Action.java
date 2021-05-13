package com.rakuten.r1001.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1001.common.A1001Common;
import com.rakuten.r1001.form.F100101;
import com.rakuten.r1001.form.OrderList;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A10010115Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String fileName = null;
	private F100101 f100101 = null;
	A1001Common a1001Common = new A1001Common();
	private String type = null;
	
	@Override
	protected void init() throws Exception {
		setTitle("V100101:注文一覧");

	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		List<OrderList> orderList = new ArrayList<OrderList>();
		if (f100101 == null) {
			f100101 = new F100101();
			f100101.setKewordType("1");
		} else {
			orderList = a1001Common.getOrderList(f100101, type);
		}
		List<String[]> shoriList = new ArrayList<String[]>();
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		ResultSet rs = null;
		String post;
		String address;
		String telephone;
		try {
			conn = JdbcConnection.getConnection();
			shoriList.add(new String[] { "平台", "店舗名", "注文番号", "商品管理番号", "数量", "名前", "郵便番号", "住所（県）", "住所（以后的地址）", "電話番号", "店舗名", "郵便番号", "発送元住所", "電話番号"});
			for (OrderList order : orderList) {
				post = "";
				address = "";
				telephone = "";
				sql = "SELECT * FROM rakuten.shop  where 1 = 1";
				if (!Utility.isEmptyString(f100101.getSite())) {
					sql = sql + " AND SITE = '" + f100101.getSite() + "'";
				}
				if (!Utility.isEmptyString(f100101.getTenpobetsu())) {
					sql = sql + " AND SHOP_ID = '" + f100101.getTenpobetsu() + "'";
				}
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				
				while (rs.next()) {
					post = rs.getString("SHOP_POST");
					address = rs.getString("SHOP_ADDRESS");
					telephone = rs.getString("SHOP_TEL");
				}
				shoriList.add(new String[] {Utility.isEmptyString(order.getSite())?"":order.getSite(),Utility.isEmptyString(order.getTenpo())?"":order.getTenpo(),order.getChumonbango(),order.getShohinbango(),order.getKosu(),order.getSofusakisha(),order.getSofusakiyubinbango(),order.getSofusakijushotodofuke(),order.getSofusakijushotoshikuchoijou(),order.getSofusakidenwabango(),order.getTenpo(), Utility.isEmptyString(post)?"":post, Utility.isEmptyString(address)?"":address, Utility.isEmptyString(telephone)?"":telephone});
			}
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = sdf1.format(new Date());
			Utility.writeCsvFile(shoriList, "c:\\temp\\orderlist.csv");
			fileName = "orderlist.csv";
			

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			conn.close();
		}
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("c:/temp/" + fileName));
	}
	
	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the f100101
	 */
	public F100101 getF100101() {
		return f100101;
	}

	/**
	 * @param f100101 the f100101 to set
	 */
	public void setF100101(F100101 f100101) {
		this.f100101 = f100101;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
