package org.simpleframework.xml.core;

import org.simpleframework.xml.strategy.Value;

class OverrideValue implements Value {
    private final Class type;
    private final Value value;
    public OverrideValue(final Value value, final Class cls) {
        this.value = value;
        type = cls;
    }
    public Object getValue() {
        return value.getValue();
    }
    public void setValue(final Object obj) {
        value.setValue(obj);
    }
    public Class getType() {
        return type;
    }
    public int getLength() {
        return value.getLength();
    }
    public boolean isReference() {
        return value.isReference();
    }
}
