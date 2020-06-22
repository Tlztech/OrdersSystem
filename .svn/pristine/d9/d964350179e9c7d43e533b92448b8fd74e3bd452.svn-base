package com.rakuten.common.action;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.Inventoryapi;
import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.InventoryapiLocator;
import jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi.InventoryapiPort;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.ExternalUserAuthModel;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.GetRequestExternalModel;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.GetResponseExternalItem;
import entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.GetResponseExternalModel;

public class GetStockInfo extends BaseAction {

	private static final long serialVersionUID = -1L;

	protected void exec() throws Exception {
		Inventoryapi locator = new InventoryapiLocator();
		InventoryapiPort port = null;

		try {
			port = locator.getinventoryapiPort();
		} catch (ServiceException e) {
			throw e;
		}

		ExternalUserAuthModel auth = new ExternalUserAuthModel();
		auth.setAuthKey("6de01b2c19c810ec4644baa7dc1f30a5");
		auth.setShopUrl("trend777");
		auth.setUserName("dongtze");

		GetRequestExternalModel model = new GetRequestExternalModel();
		model.setItemUrl(new String[] { "salesjpj083" });
		GetResponseExternalModel result = null;
		try {
			result = port.getInventoryExternal(auth, model);
			if ("N00-000".equals(result.getErrCode())) {
				GetResponseExternalItem item = result
						.getGetResponseExternalItem()[0];
			}
		} catch (RemoteException e) {
			// TODO: handle exception
		}

	}

	protected void init() {
	}

	protected void isValidated() throws Exception {
	}

	@Override
	protected void fieldCheck() throws Exception {
		// TODO Auto-generated method stub

	}

}
