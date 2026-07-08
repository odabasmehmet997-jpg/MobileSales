package org.simpleframework.xml.convert;

import org.simpleframework.xml.strategy.Value;
class Reference implements Value {
    private final Class actual;
    private Object data;
    private final Value value;
    public int getLength() {
        return 0;
    }
    public boolean isReference() {
        return true;
    }
    public Reference(final Value value, final Object obj, final Class cls) {
        actual = cls;
        this.value = value;
        data = obj;
    }
    public Class getType() {
        final Object obj = data;
        if (null != obj) {
            return obj.getClass();
        }
        return actual;
    }
    public Object getValue() {
        return data;
    }
    public void setValue(final Object obj) {
        final Value value = this.value;
        if (null != value) {
            value.setValue(obj);
        }
        data = obj;
    }
}
