package org.ksoap2.serialization;

import java.util.Hashtable;

public interface KvmSerializable {
    Object getProperty(int i2);
    int getPropertyCount();
    void getPropertyInfo(int i2, Hashtable hashtable, PropertyInfo propertyInfo);
    void setProperty(int i2, Object obj);
}
