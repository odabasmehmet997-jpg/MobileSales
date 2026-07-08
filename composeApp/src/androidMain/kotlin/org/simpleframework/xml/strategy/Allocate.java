package org.simpleframework.xml.strategy;

import java.util.Map;


class Allocate implements Value {
    private final String key;
    private final Map map;
    private final Value value;

    
    public boolean isReference() {
        return false;
    }

    public Allocate(final Value value, final Map map, final String str) {
        this.value = value;
        this.map = map;
        key = str;
    }

    
    public Object getValue() {
        return map.get(key);
    }

    
    public void setValue(final Object obj) {
        final String str = key;
        if (null != str) {
            map.put(str, obj);
        }
        value.setValue(obj);
    }

    
    public Class getType() {
        return value.getType();
    }

    
    public int getLength() {
        return value.getLength();
    }
}
