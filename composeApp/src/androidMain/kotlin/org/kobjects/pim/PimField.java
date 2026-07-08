package org.kobjects.pim;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;

public class PimField {
    String name;
    Hashtable properties;
    Object value;
    public PimField(final PimField pimField) {
        this(pimField.name);
        final Object obj = pimField.value;
        if (obj instanceof String[]) {
            final int length = ((String[]) obj).length;
            final String[] strArr = new String[length];
            System.arraycopy(obj, 0, strArr, 0, length);
            value = strArr;
        } else {
            value = obj;
        }
        if (null != pimField.properties) {
            properties = new Hashtable();
            Iterator iterator = pimField.properties.keySet().iterator();
            while (iterator.hasNext()) {
                final String str = (String) iterator.next();
                properties.put(str, pimField.properties.get(str));
            }
        }
    }
    public PimField(final String str) {
        name = str;
    }
    public Enumeration propertyNames() {
        return properties.keys();
    }
    public void setProperty(final String str, final String str2) {
        if (null == this.properties) {
            if (null == str2) {
                return;
            } else {
                properties = new Hashtable();
            }
        }
        if (null == str2) {
            properties.remove(str);
        } else {
            properties.put(str, str2);
        }
    }
    public void setValue(final Object obj) {
        value = obj;
    }
    public Object getValue() {
        return value;
    }
    public String toString() {
        final String str;
        final StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (null != this.properties) {
            str = ";" + properties;
        } else {
            str = "";
        }
        sb.append(str);
        sb.append(":");
        sb.append(value);
        return sb.toString();
    }
    public String getProperty(final String str) {
        final Hashtable hashtable = properties;
        if (null == hashtable) {
            return null;
        }
        return (String) hashtable.get(str);
    }
    public boolean getAttribute(final String str) {
        final String property = this.getProperty("type");
        return null != property && -1 != property.indexOf(str);
    }
    public void setAttribute(String str, final boolean z) {
        if (this.getAttribute(str) == z) {
            return;
        }
        final String property = this.getProperty("type");
        if (z) {
            if (null != property && 0 != property.length()) {
                str = property + str;
            }
        } else {
            int indexOf = property.indexOf(str);
            if (0 < indexOf) {
                indexOf--;
            }
            if (-1 != indexOf) {
                str = property.substring(0, indexOf) + property.substring(indexOf + str.length() + 1);
            } else {
                str = property;
            }
        }
        this.setProperty("type", str);
    }
}
