package org.ksoap2.serialization;

import java.util.Hashtable;
import java.util.Vector;
public class SoapObject extends AttributeContainer implements KvmSerializable, HasInnerText {
    private static final String EMPTY_STRING = "";
    protected Object innerText;
    protected String name;
    protected String namespace;
    protected Vector properties;
    public SoapObject() {
        this("", "");
    }
    public SoapObject(final String str, final String str2) {
        properties = new Vector();
        namespace = str;
        name = str2;
    }
    public boolean equals(final Object obj) {
        final int size;
        if (!(obj instanceof SoapObject soapObject)) {
            return false;
        }
        if (!name.equals(soapObject.name) || !namespace.equals(soapObject.namespace) || (size = properties.size()) != soapObject.properties.size()) {
            return false;
        }
        for (int i2 = 0; i2 < size; i2++) {
            if (!soapObject.isPropertyEqual(properties.elementAt(i2), i2)) {
                return false;
            }
        }
        return this.attributesAreEqual(soapObject);
    }
    public boolean isPropertyEqual(final Object obj, final int i2) {
        if (i2 >= this.getPropertyCount()) {
            return false;
        }
        final Object elementAt = properties.elementAt(i2);
        if ((obj instanceof PropertyInfo propertyInfo) && (elementAt instanceof PropertyInfo propertyInfo2)) {
            return propertyInfo.getName().equals(propertyInfo2.getName()) && propertyInfo.getValue().equals(propertyInfo2.getValue());
        }
        if ((obj instanceof SoapObject) && (elementAt instanceof SoapObject)) {
            return obj.equals(elementAt);
        }
        return false;
    }
    public String getName() {
        return name;
    }
    public String getNamespace() {
        return namespace;
    }
    public Object getProperty(final int i2) {
        final Object elementAt = properties.elementAt(i2);
        if (elementAt instanceof PropertyInfo) {
            return ((PropertyInfo) elementAt).getValue();
        }
        return elementAt;
    }
    public String getPropertyAsString(final int i2) {
        return ((PropertyInfo) properties.elementAt(i2)).getValue().toString();
    }
    public Object getProperty(final String str) {
        final Integer propertyIndex = this.propertyIndex(str);
        if (null != propertyIndex) {
            return this.getProperty(propertyIndex.intValue());
        }
        throw new RuntimeException("illegal property: " + str);
    }
    public Object getProperty(final String str, final String str2) {
        final Integer propertyIndex = this.propertyIndex(str, str2);
        if (null != propertyIndex) {
            return this.getProperty(propertyIndex.intValue());
        }
        throw new RuntimeException("illegal property: " + str2);
    }
    public Object getPropertyByNamespaceSafely(final String str, final String str2) {
        final Integer propertyIndex = this.propertyIndex(str, str2);
        if (null != propertyIndex) {
            return this.getProperty(propertyIndex.intValue());
        }
        return new NullSoapObject();
    }
    public String getPropertyByNamespaceSafelyAsString(final String str, final String str2) {
        final Object property;
        final Integer propertyIndex = this.propertyIndex(str, str2);
        if (null == propertyIndex || null == (property = getProperty(propertyIndex.intValue()))) {
            return "";
        }
        return property.toString();
    }
    public Object getPropertySafely(final String str, final String str2, final Object obj) {
        final Integer propertyIndex = this.propertyIndex(str, str2);
        return null != propertyIndex ? this.getProperty(propertyIndex.intValue()) : obj;
    }
    public String getPropertySafelyAsString(final String str, final String str2, final Object obj) {
        final Integer propertyIndex = this.propertyIndex(str, str2);
        if (null == propertyIndex) {
            if (null == obj) {
                return "";
            }
            return obj.toString();
        }
        final Object property = this.getProperty(propertyIndex.intValue());
        if (null == property) {
            return "";
        }
        return property.toString();
    }
    public Object getPrimitiveProperty(final String str, final String str2) {
        final Integer propertyIndex = this.propertyIndex(str, str2);
        if (null != propertyIndex) {
            final PropertyInfo propertyInfo = (PropertyInfo) properties.elementAt(propertyIndex.intValue());
            if (SoapObject.class != propertyInfo.getType() && null != propertyInfo.getValue()) {
                return propertyInfo.getValue();
            }
            final PropertyInfo propertyInfo2 = new PropertyInfo();
            propertyInfo2.setType(String.class);
            propertyInfo2.setValue("");
            propertyInfo2.setName(str2);
            propertyInfo2.setNamespace(str);
            return propertyInfo2.getValue();
        }
        throw new RuntimeException("illegal property: " + str2);
    }
    public String getPrimitivePropertyAsString(final String str, final String str2) {
        final Integer propertyIndex = this.propertyIndex(str, str2);
        if (null != propertyIndex) {
            final PropertyInfo propertyInfo = (PropertyInfo) properties.elementAt(propertyIndex.intValue());
            if (SoapObject.class != propertyInfo.getType() && null != propertyInfo.getValue()) {
                return propertyInfo.getValue().toString();
            }
            return "";
        }
        throw new RuntimeException("illegal property: " + str2);
    }
    public Object getPrimitivePropertySafely(final String str, final String str2) {
        final Integer propertyIndex = this.propertyIndex(str, str2);
        if (null != propertyIndex) {
            final PropertyInfo propertyInfo = (PropertyInfo) properties.elementAt(propertyIndex.intValue());
            if (SoapObject.class != propertyInfo.getType() && null != propertyInfo.getValue()) {
                return propertyInfo.getValue().toString();
            }
            final PropertyInfo propertyInfo2 = new PropertyInfo();
            propertyInfo2.setType(String.class);
            propertyInfo2.setValue("");
            propertyInfo2.setName(str2);
            propertyInfo2.setNamespace(str);
            return propertyInfo2.getValue();
        }
        return new NullSoapObject();
    }
    public String getPrimitivePropertySafelyAsString(final String str, final String str2) {
        final Integer propertyIndex = this.propertyIndex(str, str2);
        if (null != propertyIndex) {
            final PropertyInfo propertyInfo = (PropertyInfo) properties.elementAt(propertyIndex.intValue());
            if (SoapObject.class != propertyInfo.getType() && null != propertyInfo.getValue()) {
                return propertyInfo.getValue().toString();
            }
        }
        return "";
    }
    public boolean hasProperty(final String str, final String str2) {
        return null != propertyIndex(str, str2);
    }
    public String getPropertyAsString(final String str, final String str2) {
        final Integer propertyIndex = this.propertyIndex(str, str2);
        if (null != propertyIndex) {
            return this.getProperty(propertyIndex.intValue()).toString();
        }
        throw new RuntimeException("illegal property: " + str2);
    }
    public String getPropertyAsString(final String str) {
        final Integer propertyIndex = this.propertyIndex(str);
        if (null != propertyIndex) {
            return this.getProperty(propertyIndex.intValue()).toString();
        }
        throw new RuntimeException("illegal property: " + str);
    }
    public boolean hasProperty(final String str) {
        return null != propertyIndex(str);
    }
    public Object getPropertySafely(final String str) {
        final Integer propertyIndex = this.propertyIndex(str);
        if (null != propertyIndex) {
            return this.getProperty(propertyIndex.intValue());
        }
        return new NullSoapObject();
    }
    public String getPropertySafelyAsString(final String str) {
        final Object property;
        final Integer propertyIndex = this.propertyIndex(str);
        if (null == propertyIndex || null == (property = getProperty(propertyIndex.intValue()))) {
            return "";
        }
        return property.toString();
    }
    public Object getPropertySafely(final String str, final Object obj) {
        final Integer propertyIndex = this.propertyIndex(str);
        return null != propertyIndex ? this.getProperty(propertyIndex.intValue()) : obj;
    }
    public String getPropertySafelyAsString(final String str, final Object obj) {
        final Integer propertyIndex = this.propertyIndex(str);
        if (null == propertyIndex) {
            if (null == obj) {
                return "";
            }
            return obj.toString();
        }
        final Object property = this.getProperty(propertyIndex.intValue());
        if (null == property) {
            return "";
        }
        return property.toString();
    }
    public Object getPrimitiveProperty(final String str) {
        final Integer propertyIndex = this.propertyIndex(str);
        if (null != propertyIndex) {
            final PropertyInfo propertyInfo = (PropertyInfo) properties.elementAt(propertyIndex.intValue());
            if (SoapObject.class != propertyInfo.getType() && null != propertyInfo.getValue()) {
                return propertyInfo.getValue();
            }
            final PropertyInfo propertyInfo2 = new PropertyInfo();
            propertyInfo2.setType(String.class);
            propertyInfo2.setValue("");
            propertyInfo2.setName(str);
            return propertyInfo2.getValue();
        }
        throw new RuntimeException("illegal property: " + str);
    }
    public String getPrimitivePropertyAsString(final String str) {
        final Integer propertyIndex = this.propertyIndex(str);
        if (null != propertyIndex) {
            final PropertyInfo propertyInfo = (PropertyInfo) properties.elementAt(propertyIndex.intValue());
            if (SoapObject.class != propertyInfo.getType() && null != propertyInfo.getValue()) {
                return propertyInfo.getValue().toString();
            }
            return "";
        }
        throw new RuntimeException("illegal property: " + str);
    }
    public Object getPrimitivePropertySafely(final String str) {
        final Integer propertyIndex = this.propertyIndex(str);
        if (null != propertyIndex) {
            final PropertyInfo propertyInfo = (PropertyInfo) properties.elementAt(propertyIndex.intValue());
            if (SoapObject.class != propertyInfo.getType() && null != propertyInfo.getValue()) {
                return propertyInfo.getValue().toString();
            }
            final PropertyInfo propertyInfo2 = new PropertyInfo();
            propertyInfo2.setType(String.class);
            propertyInfo2.setValue("");
            propertyInfo2.setName(str);
            return propertyInfo2.getValue();
        }
        return new NullSoapObject();
    }
    public String getPrimitivePropertySafelyAsString(final String str) {
        final Integer propertyIndex = this.propertyIndex(str);
        if (null != propertyIndex) {
            final PropertyInfo propertyInfo = (PropertyInfo) properties.elementAt(propertyIndex.intValue());
            if (SoapObject.class != propertyInfo.getType() && null != propertyInfo.getValue()) {
                return propertyInfo.getValue().toString();
            }
        }
        return "";
    }
    private Integer propertyIndex(final String str) {
        if (null == str) {
            return null;
        }
        for (int i2 = 0; i2 < properties.size(); i2++) {
            if (str.equals(((PropertyInfo) properties.elementAt(i2)).getName())) {
                return Integer.valueOf(i2);
            }
        }
        return null;
    }
    private Integer propertyIndex(final String str, final String str2) {
        if (null == str2 || null == str) {
            return null;
        }
        for (int i2 = 0; i2 < properties.size(); i2++) {
            final PropertyInfo propertyInfo = (PropertyInfo) properties.elementAt(i2);
            if (str2.equals(propertyInfo.getName()) && str.equals(propertyInfo.getNamespace())) {
                return Integer.valueOf(i2);
            }
        }
        return null;
    }
    public int getPropertyCount() {
        return properties.size();
    }
    public void getPropertyInfo(final int i2, final Hashtable hashtable, final PropertyInfo propertyInfo) {
        this.getPropertyInfo(i2, propertyInfo);
    }
    public void getPropertyInfo(final int i2, final PropertyInfo propertyInfo) {
        final Object elementAt = properties.elementAt(i2);
        if (elementAt instanceof PropertyInfo propertyInfo2) {
            propertyInfo.name = propertyInfo2.name;
            propertyInfo.namespace = propertyInfo2.namespace;
            propertyInfo.flags = propertyInfo2.flags;
            propertyInfo.type = propertyInfo2.type;
            propertyInfo.elementType = propertyInfo2.elementType;
            propertyInfo.value = propertyInfo2.value;
            propertyInfo.multiRef = propertyInfo2.multiRef;
            return;
        }
        propertyInfo.name = null;
        propertyInfo.namespace = null;
        propertyInfo.flags = 0;
        propertyInfo.type = null;
        propertyInfo.elementType = null;
        propertyInfo.value = elementAt;
        propertyInfo.multiRef = false;
    }
    public PropertyInfo getPropertyInfo(final int i2) {
        final Object elementAt = properties.elementAt(i2);
        if (elementAt instanceof PropertyInfo) {
            return (PropertyInfo) elementAt;
        }
        return null;
    }
    public SoapObject newInstance() {
        final SoapObject soapObject = new SoapObject(namespace, name);
        for (int i2 = 0; i2 < properties.size(); i2++) {
            final Object elementAt = properties.elementAt(i2);
            if (elementAt instanceof PropertyInfo) {
                soapObject.addProperty((PropertyInfo) ((PropertyInfo) properties.elementAt(i2)).clone());
            } else if (elementAt instanceof SoapObject) {
                soapObject.addSoapObject(((SoapObject) elementAt).newInstance());
            }
        }
        for (int i3 = 0; i3 < this.getAttributeCount(); i3++) {
            final AttributeInfo attributeInfo = new AttributeInfo();
            this.getAttributeInfo(i3, attributeInfo);
            soapObject.addAttribute(attributeInfo);
        }
        return soapObject;
    }
    public void setProperty(final int i2, final Object obj) {
        final Object elementAt = properties.elementAt(i2);
        if (elementAt instanceof PropertyInfo) {
            ((PropertyInfo) elementAt).setValue(obj);
        }
    }
    public SoapObject addProperty(final String str, final Object obj) {
        final PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.name = str;
        propertyInfo.type = null == obj ? PropertyInfo.OBJECT_CLASS : obj.getClass();
        propertyInfo.value = obj;
        return this.addProperty(propertyInfo);
    }
    public SoapObject addProperty(final String str, final String str2, final Object obj) {
        final PropertyInfo propertyInfo = new PropertyInfo();
        propertyInfo.name = str2;
        propertyInfo.namespace = str;
        propertyInfo.type = null == obj ? PropertyInfo.OBJECT_CLASS : obj.getClass();
        propertyInfo.value = obj;
        return this.addProperty(propertyInfo);
    }
    public SoapObject addPropertyIfValue(final String str, final String str2, final Object obj) {
        return null != obj ? this.addProperty(str, str2, obj) : this;
    }
    public SoapObject addPropertyIfValue(final String str, final Object obj) {
        return null != obj ? this.addProperty(str, obj) : this;
    }
    public SoapObject addPropertyIfValue(final PropertyInfo propertyInfo, final Object obj) {
        if (null == obj) {
            return this;
        }
        propertyInfo.setValue(obj);
        return this.addProperty(propertyInfo);
    }
    public SoapObject addProperty(final PropertyInfo propertyInfo) {
        properties.addElement(propertyInfo);
        return this;
    }
    public SoapObject addPropertyIfValue(final PropertyInfo propertyInfo) {
        if (null != propertyInfo.value) {
            properties.addElement(propertyInfo);
        }
        return this;
    }
    public SoapObject addSoapObject(final SoapObject soapObject) {
        properties.addElement(soapObject);
        return this;
    }
    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer(name + "{");
        for (int i2 = 0; i2 < this.getPropertyCount(); i2++) {
            final Object elementAt = properties.elementAt(i2);
            if (elementAt instanceof PropertyInfo) {
                stringBuffer.append(((PropertyInfo) elementAt).getName());
                stringBuffer.append("=");
                stringBuffer.append(this.getProperty(i2));
                stringBuffer.append("; ");
            } else {
                stringBuffer.append(elementAt.toString());
            }
        }
        stringBuffer.append("}");
        return stringBuffer.toString();
    }
    public Object getInnerText() {
        return innerText;
    }
    public void setInnerText(final Object obj) {
        innerText = obj;
    }
    public void removePropertyInfo(final Object obj) {
        properties.remove(obj);
    }
}
