package org.simpleframework.xml.strategy;


class ArrayValue implements Value {
    private final int size;
    private final Class type;
    private Object value;

    
    public boolean isReference() {
        return false;
    }

    public ArrayValue(final Class cls, final int i2) {
        type = cls;
        size = i2;
    }

    
    public Object getValue() {
        return value;
    }

    
    public void setValue(final Object obj) {
        value = obj;
    }

    
    public Class getType() {
        return type;
    }

    
    public int getLength() {
        return size;
    }
}
