/**
 * UpdateSingleRequestExternalItem.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp;

public class UpdateSingleRequestExternalItem  implements java.io.Serializable {
    private entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateRequestExternalItem updateRequestExternalItem;

    public UpdateSingleRequestExternalItem() {
    }

    public UpdateSingleRequestExternalItem(
           entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateRequestExternalItem updateRequestExternalItem) {
           this.updateRequestExternalItem = updateRequestExternalItem;
    }


    /**
     * Gets the updateRequestExternalItem value for this UpdateSingleRequestExternalItem.
     * 
     * @return updateRequestExternalItem
     */
    public entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateRequestExternalItem getUpdateRequestExternalItem() {
        return updateRequestExternalItem;
    }


    /**
     * Sets the updateRequestExternalItem value for this UpdateSingleRequestExternalItem.
     * 
     * @param updateRequestExternalItem
     */
    public void setUpdateRequestExternalItem(entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.UpdateRequestExternalItem updateRequestExternalItem) {
        this.updateRequestExternalItem = updateRequestExternalItem;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof UpdateSingleRequestExternalItem)) return false;
        UpdateSingleRequestExternalItem other = (UpdateSingleRequestExternalItem) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.updateRequestExternalItem==null && other.getUpdateRequestExternalItem()==null) || 
             (this.updateRequestExternalItem!=null &&
              this.updateRequestExternalItem.equals(other.getUpdateRequestExternalItem())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getUpdateRequestExternalItem() != null) {
            _hashCode += getUpdateRequestExternalItem().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(UpdateSingleRequestExternalItem.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("java:jp.co.rakuten.rms.mall.inventoryapi.v1.model.entity", "UpdateSingleRequestExternalItem"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("updateRequestExternalItem");
        elemField.setXmlName(new javax.xml.namespace.QName("java:jp.co.rakuten.rms.mall.inventoryapi.v1.model.entity", "UpdateRequestExternalItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("java:jp.co.rakuten.rms.mall.inventoryapi.v1.model.entity", "UpdateRequestExternalItem"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
