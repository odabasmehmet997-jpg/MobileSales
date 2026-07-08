package org.simpleframework.xml.convert;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class RegistryBinder {
    private final Cache<Class> cache = new ConcurrentCache();
    private final ConverterFactory factory = new ConverterFactory();
    public Converter lookup(final Class cls) throws Exception {
        final Class fetch = cache.fetch(cls);
        if (null != fetch) {
            return this.create(fetch);
        }
        return null;
    }
    private Converter create(final Class cls) throws Exception {
        return factory.getInstance(cls);
    }
    public void bind(final Class cls, final Class cls2) throws Exception {
        cache.cache(cls, cls2);
    }
}
