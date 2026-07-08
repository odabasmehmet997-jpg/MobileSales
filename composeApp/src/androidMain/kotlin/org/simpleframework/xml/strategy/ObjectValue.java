package org.simpleframework.xml.strategy;


class ObjectValue implements Value {
    private final Class type;
    private Object value;

    
    public int getLength() {
        return 0;
    }

    
    public boolean isReference() {
        return false;
    }

    public ObjectValue(final Class cls) {
        type = cls;
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
}
