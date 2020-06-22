package com.rakuten.r1302.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.rakuten.common.action.BaseAction;
import com.rakuten.r1302.form.OrderList;
import com.rakuten.util.Utility;

public class A13020112Action extends BaseAction {

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
		shoriList2.add(new String[] { "OrderId",  "ShipMethod", "ShipInvoiceNumber1", "ShipUrl",
				"StoreStatus","ShipStatus" });
		for (OrderList order : orderList) {
			if ("Yahoo Shopping".equals(order.getSite())) {
				String shipUrl = "";
				if ("1001".equals(order.getHaisokaisha())) {
					shipUrl = "https://toi.kuronekoyamato.co.jp/cgi-bin/tneko";
				} else if ("1002".equals(order.getHaisokaisha())) {
					shipUrl = "http://k2k.sagawa-exp.co.jp/p/sagawa/web/okurijoinput.jsp";
				}
				shoriList2.add(new String[] { order.getChumonbango().replace("coverforefront-", ""), 
						order.getHaisohoho().replace("宅配便", "postage2").replace("宅急便", "postage2").replace("メール便", "postage1"), order.getDenpyobango(), shipUrl, "8" ,"3"});
			}
		}

		Utility.writeCsvFile(shoriList2, "c:\\temp\\odstats_order.csv");
		fileName = "odstats_order.csv";

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
