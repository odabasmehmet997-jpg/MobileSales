package org.simpleframework.xml.convert;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

import java.lang.reflect.Constructor;

class ConverterFactory {
    private final Cache<Converter> cache = new ConcurrentCache();
    public Converter getInstance(final Class cls) throws Exception {
        final Converter fetch = cache.fetch(cls);
        return null == fetch ? this.getConverter(cls) : fetch;
    }
    public Converter getInstance(final Convert convert) throws Exception {
        final Class<? extends Converter> value = convert.value();
        if (value.isInterface()) {
            throw new ConvertException("Can not instantiate %s", value);
        }
        return this.getInstance(value);
    }
    private Converter getConverter(final Class cls) throws Exception {
        final Constructor constructor = this.getConstructor(cls);
        if (null == constructor) {
            throw new ConvertException("No default constructor for %s", cls);
        }
        return this.getConverter(cls, constructor);
    }
    private Converter getConverter(final Class cls, final Constructor constructor) throws Exception {
        final Converter converter = (Converter) constructor.newInstance(null);
        if (null != converter) {
            cache.cache(cls, converter);
        }
        return converter;
    }
    private Constructor getConstructor(final Class cls) throws Exception {
        final Constructor declaredConstructor = cls.getDeclaredConstructor(null);
        if (!declaredConstructor.isAccessible()) {
            declaredConstructor.setAccessible(true);
        }
        return declaredConstructor;
    }
}
