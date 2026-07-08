package org.ksoap2.serialization;
public class SoapPrimitive extends AttributeContainer {
    protected String name;
    protected String namespace;
    protected Object value;
    public static final Object NullSkip = new Object();
    public static final Object NullNilElement = new Object();
    public SoapPrimitive(final String str, final String str2, final Object obj) {
        namespace = str;
        name = str2;
        value = obj;
    }
    public boolean equals(final Object obj) {
        if (!(obj instanceof SoapPrimitive soapPrimitive)) {
            return false;
        }
        if (!name.equals(soapPrimitive.name)) {
            return false;
        }
        final String str = namespace;
        if (null == str) {
            if (null != soapPrimitive.namespace) {
                return false;
            }
        } else if (!str.equals(soapPrimitive.namespace)) {
            return false;
        }
        final Object obj2 = value;
        if (null == obj2) {
            if (null != soapPrimitive.value) {
                return false;
            }
        } else if (!obj2.equals(soapPrimitive.value)) {
            return false;
        }
        return this.attributesAreEqual(soapPrimitive);
    }
    public int hashCode() {
        final int hashCode = name.hashCode();
        final String str = namespace;
        return hashCode ^ (null == str ? 0 : str.hashCode());
    }
    public String toString() {
        final Object obj = value;
        if (null != obj) {
            return obj.toString();
        }
        return null;
    }
    public String getNamespace() {
        return namespace;
    }
    public String getName() {
        return name;
    }
    public Object getValue() {
        return value;
    }
}
