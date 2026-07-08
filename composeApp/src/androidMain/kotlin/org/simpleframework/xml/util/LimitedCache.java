package org.simpleframework.xml.util;

import com.proje.mobilesales.core.tigerwcf.ServicesClientForTiger;
import java.util.LinkedHashMap;
import java.util.Map;


public class LimitedCache<T> extends LinkedHashMap<Object, T> implements Cache<T> {
    private final int capacity;

    public LimitedCache() {
        this(ServicesClientForTiger.TRANSFER_PART_SIZE);
    }

    public LimitedCache(final int i2) {
        capacity = i2;
    }

    
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

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(final Map.Entry<Object, T> entry) {
        return this.size() > capacity;
    }
}
