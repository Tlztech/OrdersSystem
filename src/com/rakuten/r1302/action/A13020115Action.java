package com.rakuten.r1302.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.rakuten.common.DeliveryCompany;
import com.rakuten.common.action.BaseAction;
import com.rakuten.r1302.common.A130201Common;
import com.rakuten.r1302.form.F130201;
import com.rakuten.r1302.form.OrderList;
import com.rakuten.util.Utility;

public class A13020115Action extends BaseAction {

	private static final long serialVersionUID = 1L;
	private String fileName = null;
	F130201 f130201 = null;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	protected void exec() throws Exception {
		init();
		A130201Common a130201Common = new A130201Common();
		List<OrderList> orderList = (List<OrderList>) getSessionAttribute("heneimachiList");
		
		List<String> downloadList = a130201Common.getHaneimachiList("0");
		downloadList.addAll(a130201Common.getHaneimachiList("1"));
		
		orderList = orderList.stream().filter(n->downloadList.contains(n.getChumonbango())).collect(Collectors.toList());

		List<String[]> shoriList2 = new ArrayList<String[]>();
		List<String> shoriList = new ArrayList<String>();
		shoriList2.add(new String[] { "注文番号", "住所（県）", "配送会社", "配送方法", "追跡番号", "サイズ" });
		for (OrderList order : orderList) {
			if (!"楽天".equals(order.getSite()) && !"Yahoo".equals(order.getSite()) 
					&& !"DENA".equals(order.getSite()) && !"ヤフオク".equals(order.getSite()) 
					&& !"ポンパレモール".equals(order.getSite()) && !"qoo10".equals(order.getSite())) {
				if ("".equals(f130201.getSite()) || order.getSite().equals(f130201.getSite())) {
					String haisokaishaName = "";
					if (DeliveryCompany.YAMATO.getTag().equals(order.getHaisokaisha())) {
						haisokaishaName = DeliveryCompany.YAMATO.getName();
					} else if (DeliveryCompany.SAGAWA.getTag().equals(order.getHaisokaisha())) {
						haisokaishaName = DeliveryCompany.SAGAWA.getName();
					}else if (DeliveryCompany.POST.getTag().equals(order.getHaisokaisha())) {
						haisokaishaName = DeliveryCompany.POST.getName();
					}else {
						haisokaishaName = DeliveryCompany.YAMATO.getName();
					}
					shoriList2.add(new String[] { order.getChumonbango(), order.getTodohuken(), haisokaishaName, order.getHaisohoho(), 
							order.getDenpyobango(), order.getSize()	}
					);
					shoriList.add(order.getChumonbango());
				}
			}
		}

		Utility.writeCsvFile(shoriList2, "c:\\temp\\other_odstats_order.csv");
		fileName = "other_odstats_order.csv";
		a130201Common.setHaneiToCSVDownloaded(shoriList);

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
	
	/**
	 * @return the f130201
	 */
	public F130201 getF130201() {
		return f130201;
	}

	/**
	 * @param f130201
	 *            the f130201 to set
	 */
	public void setF130201(F130201 f130201) {
		this.f130201 = f130201;
	}

}
