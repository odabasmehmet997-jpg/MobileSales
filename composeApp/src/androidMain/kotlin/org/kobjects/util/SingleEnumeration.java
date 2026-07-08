package org.kobjects.util;

import java.util.Enumeration;

public class SingleEnumeration implements Enumeration {
    Object object;
    public SingleEnumeration(final Object obj) {
        object = obj;
    }
    public boolean hasMoreElements() {
        return null != this.object;
    }
    public Object nextElement() {
        final Object obj = object;
        object = null;
        return obj;
    }
}
