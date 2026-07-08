package org.simpleframework.xml.core;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class Session implements Map {
    private final Map map;
    private final boolean strict;
    public Session() {
        this(true);
    }
    public Session(final boolean z) {
        map = new HashMap();
        strict = z;
    }
    public boolean isStrict() {
        return strict;
    }
    public Map getMap() {
        return map;
    }
    public int size() {
        return map.size();
    }
    public boolean isEmpty() {
        return map.isEmpty();
    }
    public boolean containsKey(final Object obj) {
        return map.containsKey(obj);
    }
    public boolean containsValue(final Object obj) {
        return map.containsValue(obj);
    }
    public Object get(final Object obj) {
        return map.get(obj);
    }
    public Object put(final Object obj, final Object obj2) {
        return map.put(obj, obj2);
    }
    public Object remove(final Object obj) {
        return map.remove(obj);
    }
    public void putAll(final Map map) {
        this.map.putAll(map);
    }
    public Set keySet() {
        return map.keySet();
    }
    public Collection values() {
        return map.values();
    }
    public Set entrySet() {
        return map.entrySet();
    }
    public void clear() {
        map.clear();
    }
}
