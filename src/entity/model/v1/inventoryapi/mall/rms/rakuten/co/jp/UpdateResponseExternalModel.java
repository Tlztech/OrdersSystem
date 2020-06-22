/**
 * UpdateResponseExternalModel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp;

public class UpdateResponseExternalModel  extends entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.ResponseModel  implements java.io.Serializable {
    private entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateResponseExternalItem[] updateResponseExternalItem;

    public UpdateResponseExternalModel() {
    }

    public UpdateResponseExternalModel(
           java.lang.String errCode,
           java.lang.String errMessage,
           entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateResponseExternalItem[] updateResponseExternalItem) {
        super(
            errCode,
            errMessage);
        this.updateResponseExternalItem = updateResponseExternalItem;
    }


    /**
     * Gets the updateResponseExternalItem value for this UpdateResponseExternalModel.
     * 
     * @return updateResponseExternalItem
     */
    public entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateResponseExternalItem[] getUpdateResponseExternalItem() {
        return updateResponseExternalItem;
    }


    /**
     * Sets the updateResponseExternalItem value for this UpdateResponseExternalModel.
     * 
     * @param updateResponseExternalItem
     */
    public void setUpdateResponseExternalItem(entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateResponseExternalItem[] updateResponseExternalItem) {
        this.updateResponseExternalItem = updateResponseExternalItem;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateResponseExternalModel)) return false;
        UpdateResponseExternalModel other = (UpdateResponseExternalModel) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.updateResponseExternalItem==null && other.getUpdateResponseExternalItem()==null) || 
             (this.updateResponseExternalItem!=null &&
              java.util.Arrays.equals(this.updateResponseExternalItem, other.getUpdateResponseExternalItem())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getUpdateResponseExternalItem() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getUpdateResponseExternalItem());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getUpdateResponseExternalItem(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateResponseExternalModel.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("java:jp.co.rakuten.rms.mall.inventoryapi.v1.model.entity", "UpdateResponseExternalModel"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateResponseExternalItem");
        elemField.setXmlName(new javax.xml.namespace.QName("java:jp.co.rakuten.rms.mall.inventoryapi.v1.model.entity", "updateResponseExternalItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("java:jp.co.rakuten.rms.mall.inventoryapi.v1.model.entity", "UpdateResponseExternalItem"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("java:jp.co.rakuten.rms.mall.inventoryapi.v1.model.entity", "UpdateResponseExternalItem"));
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
