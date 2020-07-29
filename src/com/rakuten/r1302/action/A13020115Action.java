package com.rakuten.r1302.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1302.form.OrderList;
import com.rakuten.util.Utility;

public class A13020115Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String fileName = null;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	protected void exec() throws Exception {
		init();
		List<OrderList> orderList = (List<OrderList>) getSessionAttribute("heneimachiList");

		List<String[]> shoriList2 = new ArrayList<String[]>();
		shoriList2.add(new String[] { "注文番号", "住所（県）", "配送会社", "配送方法", "追跡番号", "サイズ" });
		for (OrderList order : orderList) {
			if (!"楽天".equals(order.getSite()) && !"Yahoo Shopping".equals(order.getSite()) 
					&& !"DENA".equals(order.getSite()) && !"ヤフオク".equals(order.getSite()) 
					&& !"ポンパレモール".equals(order.getSite()) && !"qoo10".equals(order.getSite())) {
				
				String haisokaishaName = "";
				if ("1001".equals(order.getHaisokaisha())) {
					haisokaishaName = "ヤマト運輸";
				} else if ("1002".equals(order.getHaisokaisha())) {
					haisokaishaName = "佐川急便";
				}else {
					haisokaishaName = "ヤマト運輸";
				}
				shoriList2.add(new String[] { order.getChumonbango(), order.getTodohuken(), haisokaishaName, order.getHaisohoho(), 
						order.getDenpyobango(), order.getSize()	}
				);
			}
		}

		Utility.writeCsvFile(shoriList2, "c:\\temp\\other_odstats_order.csv");
		fileName = "other_odstats_order.csv";

	}

	public InputStream getInputStream() throws IOException {

		return new FileInputStream(new File("c:/temp/" + fileName));
	}

	protected void init() throws Exception {
		setTitle("V130201:発送処理一覧");

	}

	protected void isValidated() throws Exception {

	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

}
