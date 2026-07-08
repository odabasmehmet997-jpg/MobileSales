package org.simpleframework.xml.core;


class Revision {
    private boolean equal = true;
    public double getDefault() {
        return 1.0d;
    }
    public boolean compare(final Object obj, final Object obj2) {
        if (null != obj2) {
            equal = obj2.equals(obj);
        } else if (null != obj) {
            equal = obj.equals(Double.valueOf(1.0d));
        }
        return equal;
    }
    public boolean isEqual() {
        return equal;
    }
}
