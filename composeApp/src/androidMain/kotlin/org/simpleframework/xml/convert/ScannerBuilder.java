package org.simpleframework.xml.convert;

import org.simpleframework.xml.util.ConcurrentCache;

import java.lang.annotation.Annotation;

class ScannerBuilder extends ConcurrentCache<Scanner> {
    public Scanner build(final Class<?> cls) {
        final Scanner scanner = this.get(cls);
        if (null != scanner) {
            return scanner;
        }
        final Entry entry = new Entry(cls);
        this.put(cls, entry);
        return entry;
    }
    private static class Entry extends ConcurrentCache<Annotation> implements Scanner {
        private final Class root;
        public Entry(final Class cls) {
            root = cls;
        }
        public <T extends Annotation> T scan(final Class<T> cls) {
            if (!this.contains(cls)) {
                final Annotation find = this.find(cls);
                if (null != cls && null != find) {
                    this.put(cls, find);
                }
            }
            return (T) this.get(cls);
        }
        private <T extends Annotation> T find(final Class<T> cls) {
            for (Class cls2 = root; null != cls2; cls2 = cls2.getSuperclass()) {
                final T t = (T) cls2.getAnnotation(cls);
                if (null != t) {
                    return t;
                }
            }
            return null;
        }
    }
}
