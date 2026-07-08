package org.simpleframework.xml.transform;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;


public class RegistryMatcher implements Matcher {
    private final Cache<Transform> transforms = new ConcurrentCache();
    private final Cache<Class> types = new ConcurrentCache();

    public void bind(final Class cls, final Class cls2) {
        types.cache(cls, cls2);
    }

    public void bind(final Class cls, final Transform transform) {
        transforms.cache(cls, transform);
    }

    @Override // org.simpleframework.xml.transform.Matcher
    public Transform match(final Class cls) throws Exception {
        final Transform fetch = transforms.fetch(cls);
        return null == fetch ? this.create(cls) : fetch;
    }

    private Transform create(final Class cls) throws Exception {
        final Class fetch = types.fetch(cls);
        if (null != fetch) {
            return this.create(cls, fetch);
        }
        return null;
    }

    private Transform create(final Class cls, final Class cls2) throws Exception {
        final Transform transform = (Transform) cls2.newInstance();
        if (null != transform) {
            transforms.cache(cls, transform);
        }
        return transform;
    }
}
