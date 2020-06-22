/**
 * InventoryapiPort.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package jp.co.rakuten.rms.inventoryapi.rms.mall.inventoryapi;

public interface InventoryapiPort extends java.rmi.Remote {
    public entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.GetResponseExternalModel getInventoryExternal(entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.ExternalUserAuthModel externalUserAuthModel, entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.GetRequestExternalModel getRequestExternalModel) throws java.rmi.RemoteException;
    public entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateResponseExternalModel updateInventoryExternal(entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.ExternalUserAuthModel externalUserAuthModel, entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateRequestExternalModel updateRequestExternalModel) throws java.rmi.RemoteException;
    public entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateResponseExternalModel updateSingleInventoryExternal(entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.ExternalUserAuthModel externalUserAuthModel, entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateSingleRequestExternalModel updateRequestExternalModel) throws java.rmi.RemoteException;
}
