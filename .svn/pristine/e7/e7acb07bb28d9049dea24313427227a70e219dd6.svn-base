/**
 * GetResponseExternalModel.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp;

public class GetResponseExternalModel  extends entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.ResponseModel  implements java.io.Serializable {
    private entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.GetResponseExternalItem[] getResponseExternalItem;

    public GetResponseExternalModel() {
    }

    public GetResponseExternalModel(
           java.lang.String errCode,
           java.lang.String errMessage,
           entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.GetResponseExternalItem[] getResponseExternalItem) {
        super(
            errCode,
            errMessage);
        this.getResponseExternalItem = getResponseExternalItem;
    }


    /**
     * Gets the getResponseExternalItem value for this GetResponseExternalModel.
     * 
     * @return getResponseExternalItem
     */
    public entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.GetResponseExternalItem[] getGetResponseExternalItem() {
        return getResponseExternalItem;
    }


    /**
     * Sets the getResponseExternalItem value for this GetResponseExternalModel.
     * 
     * @param getResponseExternalItem
     */
    public void setGetResponseExternalItem(entity.model.v1.inventoryapi.mall.rms.rakuten.co.jp.GetResponseExternalItem[] getResponseExternalItem) {
        this.getResponseExternalItem = getResponseExternalItem;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetResponseExternalModel)) return false;
        GetResponseExternalModel other = (GetResponseExternalModel) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.getResponseExternalItem==null && other.getGetResponseExternalItem()==null) || 
             (this.getResponseExternalItem!=null &&
              java.util.Arrays.equals(this.getResponseExternalItem, other.getGetResponseExternalItem())));
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
        if (getGetResponseExternalItem() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getGetResponseExternalItem());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getGetResponseExternalItem(), i);
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
        new org.apache.axis.description.TypeDesc(GetResponseExternalModel.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("java:jp.co.rakuten.rms.mall.inventoryapi.v1.model.entity", "GetResponseExternalModel"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("getResponseExternalItem");
        elemField.setXmlName(new javax.xml.namespace.QName("java:jp.co.rakuten.rms.mall.inventoryapi.v1.model.entity", "getResponseExternalItem"));
        elemField.setXmlType(new javax.xml.namespace.QName("java:jp.co.rakuten.rms.mall.inventoryapi.v1.model.entity", "GetResponseExternalItem"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("java:jp.co.rakuten.rms.mall.inventoryapi.v1.model.entity", "GetResponseExternalItem"));
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
