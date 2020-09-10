package com.rakuten.common.action;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.apache.axis.encoding.Base64;

import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.Inventoryapi;
import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.InventoryapiLocator;
import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.InventoryapiPort;
import shohinmodel.common.Shohincommon;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.ExternalUserAuthModel;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.GetRequestExternalModel;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.GetResponseExternalItem;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.GetResponseExternalModel;

public class Copy_2_of_TestMain {

	public static void main(String[] args) throws Exception {

		Inventoryapi locator = new InventoryapiLocator();
		InventoryapiPort port = null;

		try {
			port = locator.getinventoryapiPort();
		} catch (ServiceException e) {
			throw e;
		}

		String shopName = "herz";
		Shohincommon common = new Shohincommon();
		String serviceSecret = common.getServiceSecret(shopName);
		String licenseKey = common.getLicenseKey(shopName);
		ExternalUserAuthModel auth = new ExternalUserAuthModel();
		auth.setAuthKey("ESA " + Base64.encode((serviceSecret + ":" + licenseKey).getBytes()));
		auth.setShopUrl(shopName);
		auth.setUserName("dongtze");

		GetRequestExternalModel model = new GetRequestExternalModel();
		model.setItemUrl(new String[] { "nzctx659-10" });
		GetResponseExternalModel result = null;
		try {
			result = port.getInventoryExternal(auth, model);
			System.out.println(result.getErrCode());
			if ("N00-000".equals(result.getErrCode())) {
				GetResponseExternalItem item = result
						.getGetResponseExternalItem()[0];
				System.out.println(item);
			}
		} catch (RemoteException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	
}
