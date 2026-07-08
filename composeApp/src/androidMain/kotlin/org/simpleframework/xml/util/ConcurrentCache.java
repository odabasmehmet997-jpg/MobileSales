package org.simpleframework.xml.util;

import java.util.concurrent.ConcurrentHashMap;


public class ConcurrentCache<T> extends ConcurrentHashMap<Object, T> implements Cache<T> {

    public void cache(final Object obj, final T t) {
        this.put(obj, t);
    }

    
    public T take(final Object obj) {
        return this.remove(obj);
    }

    
    public T fetch(final Object obj) {
        return this.get(obj);
    }
    public boolean contains(final Object obj) {
        return this.containsKey(obj);
    }
}
