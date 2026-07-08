package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Value;

import java.lang.reflect.Array;

class ArrayInstance implements Instance {
    private final int length;
    private final Class type;
    private final Value value;
    public ArrayInstance(final Value value) {
        length = value.getLength();
        type = value.getType();
        this.value = value;
    }
    public Object getInstance() throws Exception {
        if (value.isReference()) {
            return value.getValue();
        }
        final Object newInstance = Array.newInstance((Class<?>) type, length);
        final Value value = this.value;
        value.setValue(newInstance);
        return newInstance;
    }
    public Object setInstance(final Object obj) {
        final Value value = this.value;
        if (null != value) {
            value.setValue(obj);
        }
        return obj;
    }
    public Class getType() {
        return type;
    }
    public boolean isReference() {
        return value.isReference();
    }
}
