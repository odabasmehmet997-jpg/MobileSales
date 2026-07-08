package org.ksoap2.serialization;

import okhttp3.HttpUrl;

import java.io.*;
import java.util.Vector;

public class PropertyInfo implements Serializable {
    public static final int MULTI_REF = 2;
    public static final int REF_ONLY = 4;
    public static final int TRANSIENT = 1;
    public PropertyInfo elementType;
    public int flags;
    public boolean multiRef;
    public String name;
    public String namespace;
    public Object type = PropertyInfo.OBJECT_CLASS;
    protected Object value;
    public static final Class OBJECT_CLASS = new Object().getClass();
    public static final Class STRING_CLASS = HttpUrl.FRAGMENT_ENCODE_SET.getClass();
    public static final Class INTEGER_CLASS = Integer.valueOf(0).getClass();
    public static final Class LONG_CLASS = Long.valueOf(0).getClass();
    public static final Class BOOLEAN_CLASS = Boolean.TRUE.getClass();
    public static final Class VECTOR_CLASS = new Vector().getClass();
    public static final PropertyInfo OBJECT_TYPE = new PropertyInfo();
    public void clear() {
        type = PropertyInfo.OBJECT_CLASS;
        flags = 0;
        name = null;
        namespace = null;
    }
    public PropertyInfo getElementType() {
        return elementType;
    }
    public void setElementType(final PropertyInfo propertyInfo) {
        elementType = propertyInfo;
    }
    public int getFlags() {
        return flags;
    }
    public void setFlags(final int i2) {
        flags = i2;
    }
    public boolean isMultiRef() {
        return multiRef;
    }
    public void setMultiRef(final boolean z) {
        multiRef = z;
    }
    public String getName() {
        return name;
    }
    public void setName(final String str) {
        name = str;
    }
    public String getNamespace() {
        return namespace;
    }
    public void setNamespace(final String str) {
        namespace = str;
    }
    public Object getType() {
        return type;
    }
    public void setType(final Object obj) {
        type = obj;
    }
    public Object getValue() {
        return value;
    }
    public void setValue(final Object obj) {
        value = obj;
    }
    public String toString() {
        final StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(name);
        stringBuffer.append(" : ");
        final Object obj = value;
        if (null != obj) {
            stringBuffer.append(obj);
        } else {
            stringBuffer.append("(not set)");
        }
        return stringBuffer.toString();
    }
    public Object clone() {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.flush();
            objectOutputStream.close();
            return new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())).readObject();
        } catch (final NotSerializableException e2) {
            e2.printStackTrace();
            return null;
        } catch (final IOException e3) {
            e3.printStackTrace();
            return null;
        } catch (final ClassNotFoundException e4) {
            e4.printStackTrace();
            return null;
        }
    }
}
