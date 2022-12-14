package com.rakuten.r1601.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.rakuten.common.action.BaseAction;
import com.rakuten.common.action.OrderCommon;
import com.rakuten.common.bean.OrderApiBean;
import com.rakuten.r1601.form.F160101;
import com.rakuten.util.JdbcConnection;
import com.rakuten.util.Utility;

public class A16010115Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String fileName = null;
	private F160101 f160101 = null;

	@Override
	protected void init() throws Exception {
		setTitle("V160101:小工具");
	}

	@Override
	protected void isValidated() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void exec() throws Exception {
		List<String[]> outList = new ArrayList<>();
		List<String> outListR = new ArrayList<>();
		List<String> outListL = new ArrayList<>();
		List<String> shopList = new ArrayList<>();
		Connection conn = null;
		try {
			System.out.println("ダウンロードstart");
			OrderCommon common = new OrderCommon();
			conn = JdbcConnection.getConnection();
			String sql = "SELECT SHOP_ID FROM rakuten.shop where DELETE_FLG is null and SITE = '楽天'";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				shopList.add(rs.getString("SHOP_ID"));
			}
			rs.close();
			ps.close();
			sql = "SELECT SHOP, CHUMONBANGO FROM rakuten.common_order_tbl where CHUMONSTS1 = '2' and SITE = '楽天'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			Map<String, List<String>> orderNoListMap = new HashMap<>();
			while (rs.next()) {
				if (Objects.isNull(orderNoListMap.get(rs.getString("SHOP")))) {
					orderNoListMap.put(rs.getString("SHOP"), new ArrayList<>());
				}
				orderNoListMap.get(rs.getString("SHOP")).add(rs.getString("CHUMONBANGO"));
			}
			rs.close();
			ps.close();
			if (shopList.isEmpty()) {
				addError(null, "楽天店舗なし、比較ファイル生成失敗");
			} else {
				for (String shop : shopList) {
					System.out.println("比較店舗："+shop+" start");
					try {
						OrderApiBean orderapibean = common.getOrderListByApi(shop);

						if (!Utility.isEmptyList(orderapibean.getMessageList())) {
							for (String msg : orderapibean.getMessageList())
								addError(null, msg);
							break;
						}
						orderNoListMap.get(shop).forEach(d -> {
							if (orderapibean.getRakutenBeanList().stream().filter(v -> v.getJuchubango().equals(d))
									.count() == 0) {
								outListL.add(d);
							}
						});
						orderapibean.getRakutenBeanList().forEach(d->{
							if (orderNoListMap.get(shop).stream().filter(v->v.equals(d.getJuchubango())).count() == 0) {
								outListR.add(d.getJuchubango());
							}
						});
						outList.add(new String[] {"系统比乐天多的订单","乐天比系统多的订单"});
						if (outListL.size() >= outListR.size()) {
							for (int i = 0; i < outListL.size(); i++) {
								if (i < outListR.size()) {
									outList.add(new String[] {outListL.get(i),outListR.get(i)});
								} else {
									outList.add(new String[] {outListL.get(i),""});
								}
							}
						} else {
							for (int i = 0; i < outListR.size(); i++) {
								if (i < outListL.size()) {
									outList.add(new String[] {outListL.get(i),outListR.get(i)});
								} else {
									outList.add(new String[] {"",outListR.get(i)});
								}
							}
						}
					} catch (Exception e) {
						outList.add(new String[] { shop+" error: "+e.getMessage() });
					} finally {
						System.out.println("比較店舗："+shop+" end");
					}
				}
				if (outList.isEmpty()) {

				} else {
					fileName = "rakuten_orderlist_" + ".csv";
					Utility.writeCsvFile(outList, "c:\\temp\\" + fileName);
				}
			}
			System.out.println("ダウンロードend");
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (Objects.nonNull(conn))
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
	 * @return the f160101
	 */
	public F160101 getF160101() {
		return f160101;
	}

	/**
	 * @param f160101 the f160101 to set
	 */
	public void setF160101(F160101 f160101) {
		this.f160101 = f160101;
	}

}
