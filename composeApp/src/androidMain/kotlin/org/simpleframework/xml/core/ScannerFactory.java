package org.simpleframework.xml.core;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class ScannerFactory {
    private final Cache<Scanner> cache = new ConcurrentCache();
    private final Support support;
    public ScannerFactory(final Support support) {
        this.support = support;
    }
    public Scanner getInstance(final Class cls) throws Exception {
        Scanner objectScanner;
        Scanner fetch = cache.fetch(cls);
        if (null == fetch) {
            final Detail detail = support.getDetail(cls);
            if (support.isPrimitive(cls)) {
                objectScanner = new PrimitiveScanner(detail);
            } else {
                objectScanner = new ObjectScanner(detail, support);
                if (objectScanner.isPrimitive() && !support.isContainer(cls)) {
                    objectScanner = new DefaultScanner(detail, support);
                }
            }
            fetch = objectScanner;
            cache.cache(cls, fetch);
        }
        return fetch;
    }
}
