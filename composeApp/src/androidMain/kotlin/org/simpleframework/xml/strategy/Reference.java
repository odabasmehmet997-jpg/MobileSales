package org.simpleframework.xml.strategy;


class Reference implements Value {
    private final Class type;
    private Object value;

    
    public int getLength() {
        return 0;
    }

    
    public boolean isReference() {
        return true;
    }

    public Reference(final Object obj, final Class cls) {
        value = obj;
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
