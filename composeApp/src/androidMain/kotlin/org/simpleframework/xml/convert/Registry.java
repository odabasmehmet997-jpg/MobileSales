package org.simpleframework.xml.convert;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

public class Registry {
    private final Cache<Converter> cache = new ConcurrentCache();
    private final RegistryBinder binder = new RegistryBinder();
    public Converter lookup(final Class cls) throws Exception {
        final Converter fetch = cache.fetch(cls);
        return null == fetch ? this.create(cls) : fetch;
    }
    private Converter create(final Class cls) throws Exception {
        final Converter lookup = binder.lookup(cls);
        if (null != lookup) {
            cache.cache(cls, lookup);
        }
        return lookup;
    }
    public Registry bind(final Class cls, final Class cls2) throws Exception {
        if (null != cls) {
            binder.bind(cls, cls2);
        }
        return this;
    }
    public Registry bind(final Class cls, final Converter converter) throws Exception {
        if (null != cls) {
            cache.cache(cls, converter);
        }
        return this;
    }
}
