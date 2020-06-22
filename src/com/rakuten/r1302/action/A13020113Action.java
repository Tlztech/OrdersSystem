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

public class A13020113Action extends BaseAction {

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
		shoriList2.add(new String[] { "注文番号", "送付先ID", "発送明細ID", "お荷物伝票番号", "配送会社", "発送日" });
		for (OrderList order : orderList) {
			if ("楽天".equals(order.getSite())) {
				String shipUrl = "";
				if ("1001".equals(order.getHaisokaisha())) {
					shipUrl = "https://toi.kuronekoyamato.co.jp/cgi-bin/tneko";
				} else if ("1002".equals(order.getHaisokaisha())) {
					shipUrl = "http://k2k.sagawa-exp.co.jp/p/sagawa/web/okurijoinput.jsp";
				}
				shoriList2.add(new String[] { order.getChumonbango(), "", "", order.getDenpyobango(),
						// ba heimao de 1001 huan cheng you ju de 1003 linshi chuli fang fa
						"1001".equals(order.getHaisokaisha()) ? "1003" : order.getHaisokaisha(),
						new SimpleDateFormat("yyyyMMdd").format(new Date()) });
			}
		}

		Utility.writeCsvFile(shoriList2, "c:\\temp\\rakuten_odstats_order.csv");
		fileName = "rakuten_odstats_order.csv";

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
